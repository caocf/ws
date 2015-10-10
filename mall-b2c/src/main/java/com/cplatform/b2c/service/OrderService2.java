package com.cplatform.b2c.service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cplatform.b2c.dto.CreateOrderDTO;
import com.cplatform.b2c.dto.CreateOrderResponse;
import com.cplatform.b2c.dto.GiftCardRequest;
import com.cplatform.b2c.dto.GiftCardResponse;
import com.cplatform.b2c.dto.ItemSaleDataDTO;
import com.cplatform.b2c.dto.ItemSaleDataDTO.Item;
import com.cplatform.b2c.dto.LashouResponse;
import com.cplatform.b2c.dto.PayDTO;
import com.cplatform.b2c.dto.PayOrderResponse;
import com.cplatform.b2c.model.SessionUser;
import com.cplatform.b2c.model.TMemberAddress;
import com.cplatform.b2c.repository.ThirdInterDao;
import com.cplatform.b2c.util.Constants;
import com.cplatform.b2c.util.JsonRespWrapper;
import com.cplatform.b2c.util.TimeUtil;
import com.cplatform.b2c.web.order.OrderCommonException;
import com.cplatform.dbhelp.DbHelper;
import com.cplatform.order.ActOrderGoodsInfo;
import com.cplatform.order.ActOrderInfo;
import com.cplatform.order.ActOrderServiceClient;
import com.cplatform.order.ActOrderStatus;
import com.cplatform.order.CommonResponse;
import com.google.common.collect.Lists;

@Component
public class OrderService2 {

	private static final Logger logger = Logger.getLogger("business");

	@Autowired
	private DbHelper dbHelper;

	@Autowired
	private ThirdInterDao thirdInterDao;

	@Autowired
	private ActOrderServiceClient actOrderClient;

	@Autowired
	private PerformService performService;

	@Autowired
	private TItemParamService tItemParamService;

	@Autowired
	private MemberAddressService memberAddressService;

	@Autowired
	private ItemSaleService itemSaleService;

	public int checkGoodsMaxLimitBuy(Long userId, Long itemId) throws SQLException {
		String sql = "select sum(g.count) as sellCount from t_act_order t left join t_act_order_goods g on t.id=g.act_order_id \n"
		        + "where t.user_id=? and g.goods_id=?  and t.pay_status=2";
		String count = dbHelper.getMap(sql, userId, itemId).get("sellCount");
		if (StringUtils.isBlank(count))
			return 0;
		return Integer.valueOf(count);
	}

	public List<String> getGoodIds(List<CreateOrderDTO.Good> goods) throws Exception {

		List<String> goodsId = Lists.newArrayList();
		for (CreateOrderDTO.Good good : goods) {
			goodsId.add(good.getId().toString());
		}
		return goodsId;
	}

	public List<CreateOrderDTO.Good> fillGoods(String items, String giftId, String businessId, String orderType, SessionUser user, Long addressId)
	        throws Exception {

		Validate.notBlank(items, "商品数据为空");

		String[] itemKeys = StringUtils.split(items, ",");

		List<CreateOrderDTO.Good> goods = Lists.newArrayList();

		for (String itemKey : itemKeys) {
			String[] singleItemKeys = StringUtils.split(itemKey, "_");
			if (singleItemKeys.length < 2) {
				logger.warn("填充订单数据，单个商品数据结构错误，长度小于2，信息应该为 itemid_itemquantity{_selprop1_selprop2_...}");
				throw new OrderCommonException("错误的订单信息");
			}
			String itemId = singleItemKeys[0];
			int quantity = NumberUtils.toInt(singleItemKeys[1], 1);

			if (quantity < 0 || quantity > 200) {
				logger.warn("商品数量错误");
				throw new OrderCommonException("错误的订单信息");
			}
			// JSONObject itemInfo = thirdInterDao.getItem(itemId);
			ItemSaleDataDTO itemInfo = thirdInterDao.getItemById(itemId);
			if (itemInfo == null) {
				logger.warn("填充订单数据，接口返回商品信息为空");
				throw new OrderCommonException("错误的订单信息");
			}
			// JSONObject item = itemInfo.getJSONObject("item");
			ItemSaleDataDTO.Item item = itemInfo.getItem();

			// 判断是否是限时购买商品
			if (itemSaleService.checkIsPromptGoods(item)) {
				// 检查商品对于会员级别的限制
				itemSaleService.throwCheckGoogdBuyLimitVip(item, user);

				// 判断限购数量
				itemSaleService.throwErrorMessage(ItemSaleService.ERROR_MESSAGE_LIMIT, "用户您好，购买数量超过限制数量", quantity, item.getUserPerBuyNum());

				// 判断特价库存
				itemSaleService.throwErrorMessage(ItemSaleService.ERROR_MESSAGE_SOTCK, "用户您好，当前库存不足，请继续关注。可能会有惊喜哦!", quantity, item.getStocknum());

				// 判断当前活动时间是否开始
				if (TimeUtil.compareTime(TimeUtil.now(), item.getStopTime()) > 0) {
					throw new OrderCommonException("特价活动结束，商品已下架");
				}

			}

			// 对当前订单，收货人地址进行验证
			boolean isNeedPost = isNeedPost(item);
			if (isNeedPost) {
				if (null != addressId && StringUtils.isNotBlank(addressId.toString())) {
					// 验证当前地址编号是否是该用户所有
					TMemberAddress tMemberAddress = memberAddressService.findAddress(addressId.toString().trim(), user.getId());
					if (null == tMemberAddress || null == tMemberAddress.getId()) { // 不是该用户下的addressId
						logger.info("用户：" + user.getId() + "，物流地址编号：" + addressId + ";该记录不存在(addressId不是该用户所有)");
						throw new OrderCommonException("尊敬的用户，您的收货地址信息有误，建议您重新下单。");
					}
				} else { // addressId为空
					logger.info("用户：" + user.getId() + "，物流地址编号：" + ";addressId不存在，为空");
					throw new OrderCommonException("尊敬的用户，您的收货地址信息有误，建议您重新下单。");
				}
			}

			int discount = 0;
			if (itemKeys.length == 1 && StringUtils.isNotBlank(giftId) && StringUtils.isNotBlank(user.getTerminalId())) {
				int finalPrice = thirdInterDao.getItemFinalPrice(itemId, user.getId().toString(), orderType, businessId);
				Map<String, Integer> map = getDiscountFromGift(user.getTerminalId(), finalPrice * quantity, itemId, giftId);
				if (map != null && !map.isEmpty() && map.get("flag") == 1) {
					discount = map.get("discount");
				} else {
					throw new OrderCommonException("很抱歉，订单总金额不满足该礼金券使用规则，请重新下单！");
				}
			}

			if (Constants.ITEM_SOURCE_LASHOU.equals(item.getSource())) {
				checkLashouItem(itemId, item.getStocknum(), quantity);
			}
			// 商品来源
			String itemSource = item.getSource();
			logger.info("商品来源：" + itemSource);
			logger.info("永乐票品：" + Constants.ITEM_SOURCE_YTICKET_YL);
			// 永乐演出票订单调下单接口
			if (Constants.ITEM_SOURCE_YTICKET_YL.equals(itemSource)) {
				Map<String, String> ticketInfo = performService.getPerformTicketInfo(itemId);
				if (ticketInfo != null) {
					performBeforeOrder(ticketInfo, quantity, item.getShopPrice());
				} else {
					logger.warn("商品信息不存在");
					throw new OrderCommonException("商品信息不存在");
				}
			}

			/*
			 * if (Constants.PANIC_BUYING.equals(item.getIseckill())) { int
			 * alreadyBuy = checkGoodsMaxLimitBuy(user.getId(), item.getId());
			 * int limitBuy = itemInfo.getMarketGoodsProperty().getNumLimit();
			 * if (limitBuy != 0 && (alreadyBuy >= limitBuy || alreadyBuy +
			 * quantity > limitBuy)) { logger.error("最大购买：" + limitBuy + "，已购买:"
			 * + alreadyBuy); throw new OrderCommonException("对不起，该商品每人仅限购买：" +
			 * limitBuy + "个，您已达到购买上限，谢谢！"); } }
			 */
			CreateOrderDTO.Good goodInfo = new CreateOrderDTO.Good();
			goodInfo.setId(item.getId());
			goodInfo.setCount(quantity);
			goodInfo.setDiscount(discount);
			goodInfo.setSource(item.getSource());
			goodInfo.setEckill(item.getIseckill());
			// 添加商品你描述
			// goodInfo.setGoodsDescription(tItemParamService.getItemParamToString(item.getId()));
			goods.add(goodInfo);
		}
		if (goods.size() == 0) {
			logger.warn("填充订单数据，最终订单商品个数为0");
			throw new OrderCommonException("错误的订单信息");
		}
		return goods;
	}

	public void checkLashouItem(String itemId, int stock, int quantity) throws Exception {
		LashouResponse lashouResponse = thirdInterDao.queryLashouItem(itemId);
		if (lashouResponse != null && lashouResponse.getRet().equals("0")) {
			int sellCount = lashouResponse.getSellCount();
			if (quantity > (stock - sellCount)) {
				logger.error("拉手商品[" + itemId + "]库存不足:欲购买数量:" + quantity + ",库存:" + stock + ",销量:" + sellCount);
				throw new OrderCommonException("商品库存不足");
			}
		}
	}

	/**
	 * 验证礼品卡够不够使用
	 * 
	 * @param terminalId
	 * @param totalAmount
	 *            总订单金额，元
	 * @param itemId
	 * @param giftId
	 * @return
	 */
	public Map<String, Integer> getDiscountFromGift(String terminalId, int totalAmount, String itemId, String giftId) {
		GiftCardRequest request = new GiftCardRequest();
		Map<String, Integer> map = new HashMap<String, Integer>();
		request.setMobile(terminalId);
		// 校验营销平台是否可用礼金券接口（价格传元）
		request.setTotalCost(new BigDecimal(totalAmount).divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP));
		request.setItems(Lists.newArrayList(Long.valueOf(itemId)));
		request.setGiftId(Long.valueOf(giftId));
		GiftCardResponse.Data giftCardDetail = thirdInterDao.getGiftCardDetail(request);
		if (giftCardDetail == null || null == giftCardDetail.getGiftId() || null == giftCardDetail.getPrice()) {
			logger.error("礼金券【" + giftId + "】不可用");
			map.put("flag", 0);
			map.put("discount", 0);
			return map;
		}
		int giftPrice = giftCardDetail.getPrice().multiply(new BigDecimal(100)).intValue();

		if (giftPrice <= totalAmount) {
			map.put("flag", 1);
			map.put("discount", giftPrice);
			return map;
		} else {
			map.put("flag", 1);
			map.put("discount", totalAmount);
			return map;
		}
	}

	public CreateOrderResponse createOrder(CreateOrderDTO createOrderDTO) throws Exception {

		return thirdInterDao.createOrder(createOrderDTO);
	}

	public void callCardInterface(String giftId, String terminalId, Long orderId, List<CreateOrderDTO.Good> goods) {
		if (StringUtils.isNotBlank(giftId)) {
			GiftCardRequest cardRequest = new GiftCardRequest();
			cardRequest.setMobile(terminalId);
			cardRequest.setGiftId(Long.valueOf(giftId));
			cardRequest.setOrderId(orderId);
			List<GiftCardRequest.Payment> payments = Lists.newArrayList();
			for (CreateOrderDTO.Good good : goods) {
				GiftCardRequest.Payment payment = new GiftCardRequest.Payment();
				payment.setItemId(good.getId());
				payment.setPayAmount(new BigDecimal(good.getDiscount()).divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP));
				payments.add(payment);
			}
			cardRequest.setPayment(payments);

			GiftCardResponse cardResponse = thirdInterDao.callGiftCardPay(cardRequest);
			if (cardResponse == null || !cardResponse.getFlag().equals("0")) {
				throw new OrderCommonException("礼金券支付失败");
			}
		}
	}

	public PayOrderResponse payOrder(PayDTO payDTO) throws Exception {

		return thirdInterDao.payOrder(payDTO);
	}

	/**
	 * 劳保红包支付
	 * 
	 * @param payDTO
	 * @return
	 * @throws Exception
	 */
	public PayOrderResponse payWelfareOrder(com.cplatform.b2c.model.PayDTO payDTO) throws Exception {

		return thirdInterDao.payWelfareOrder(payDTO);
	}

	public ActOrderInfo getOrderInfo(Long orderId) throws Exception {
		return actOrderClient.getActOrderInfo(orderId);
	}

	// 拉手商品总价合计
	public int getLashouTotalAmount(ActOrderInfo orderInfo) {
		int totalAmount = 0;
		for (ActOrderGoodsInfo goodsInfo : orderInfo.getGoodsInfos()) {
			int payPrice = changeToLaShouPrice(goodsInfo.getPayPrice());
			totalAmount += payPrice * goodsInfo.getCount() - goodsInfo.getDiscount();
		}
		if (orderInfo.getExpressInfo() != null) {
			totalAmount += orderInfo.getExpressInfo().getExpressCost();
		}
		return totalAmount;
	}

	/**
	 * 拉手商品 话费 ，商城币 金额计算
	 * 
	 * @param payPrice
	 *            商品单价, 单位：分
	 * @return 分
	 */
	public int changeToLaShouPrice(int payPrice) {

		return new BigDecimal(payPrice).divide(new BigDecimal(100)).multiply(new BigDecimal("1.06")).setScale(1, BigDecimal.ROUND_UP)
		        .multiply(new BigDecimal(100)).intValue();

	}

	public int changeScoreAmount(int score) {
		return new BigDecimal(score).divide(new BigDecimal(100)).divide(new BigDecimal("0.015"), 0, BigDecimal.ROUND_DOWN)
		        .multiply(new BigDecimal(100)).intValue();
	}

	// 四舍五入
	public int changeToScore(int score) {
		return new BigDecimal(score).divide(new BigDecimal(100)).divide(new BigDecimal("0.015"), 0, BigDecimal.ROUND_HALF_UP)
		        .multiply(new BigDecimal(100)).intValue();
	}

	/**
	 * 永乐演出票下单前接口判断
	 * 
	 * @param ticketInfoMap
	 * @param ticketNumber
	 * @param ticketPrice
	 */
	public void performBeforeOrder(Map<String, String> ticketInfoMap, int ticketNumber, BigDecimal ticketPrice) {

		String productId = ticketInfoMap.get("product_id");// 永乐票品ID
		String productPlayid = ticketInfoMap.get("product_play_id");// 永乐票价ID
		String buyType = "0";// 买卖类型（只接受0/1，其他均为无效, 0-购买 1-登记）
		logger.info("永乐演出票下单前判断能否购买:" + productId + ":" + productPlayid);
		String result = thirdInterDao.performBeforeOrder(productId, productPlayid, buyType, ticketNumber, ticketPrice);
		logger.info("永乐演出票下单前判断能否购买返回报文：" + result);
		if (StringUtils.isNotBlank(result)) {
			JSONObject jsonObj = JSONObject.fromObject(result);
			if (!"200".equals(jsonObj.getString("code"))) {
				throw new OrderCommonException(jsonObj.getString("message"));
			}
		} else {
			throw new OrderCommonException("错误的订单信息");
		}

	}

	/**
	 * 判断当前订单是否该用户所有
	 * 
	 * @param orderUserId
	 *            订单对应的用户
	 * @param currentUserId
	 *            当前登录用户
	 * @return
	 */
	public boolean isCurrentUserOrder(Long orderUserId, Long currentUserId) {
		if (null != orderUserId && null != currentUserId) {
			if (orderUserId.equals(currentUserId)) {
				return true;
			}
			return false;
		}
		return false;
	}

	/**
	 * 电影票商品总价合计
	 * 
	 * @param orderInfo
	 * @return
	 */
	public int getMovieTotalAmount(ActOrderInfo orderInfo) {
		int totalAmount = 0;
		for (ActOrderGoodsInfo goodsInfo : orderInfo.getGoodsInfos()) {
			int payPrice = changeToMoviePrice(goodsInfo.getPayPrice());
			totalAmount += payPrice * goodsInfo.getCount() - goodsInfo.getDiscount();
		}
		if (orderInfo.getExpressInfo() != null) {
			totalAmount += orderInfo.getExpressInfo().getExpressCost();
		}
		return totalAmount;
	}

	/**
	 * 电影票商品 话费 ，商城币 金额计算
	 * 
	 * @param payPrice
	 *            商品单价, 单位：分
	 * @return 分
	 */
	public int changeToMoviePrice(int payPrice) {

		return new BigDecimal(payPrice).divide(new BigDecimal(100)).multiply(new BigDecimal("1.1")).setScale(1, BigDecimal.ROUND_UP)
		        .multiply(new BigDecimal(100)).intValue();

	}

	/**
	 * 电影票支付价格修改
	 * 
	 * @param orderInfo
	 * @return
	 * @throws Exception
	 */
	public Object moviePayBeforeDo(ActOrderInfo orderInfo) throws Exception {
		logger.info("电影票价格修改");
		if (orderInfo == null) {
			return JsonRespWrapper.failure("订单不存在");
		}
		if (orderInfo.getCloseStatus() == ActOrderStatus.CLOSE_STATUS_CLOSED) {
			return JsonRespWrapper.failure("订单已关闭");
		}
		if (orderInfo.getPayStatus() != ActOrderStatus.PAY_STATUS_UNPAID) {
			return JsonRespWrapper.failure("订单已支付");
		}

		List<ActOrderGoodsInfo> goods = new ArrayList<ActOrderGoodsInfo>();
		List<ActOrderGoodsInfo> dtogs = orderInfo.getGoodsInfos();
		for (ActOrderGoodsInfo dtog : dtogs) {
			ActOrderGoodsInfo good = new ActOrderGoodsInfo();
			good.setActOrderId(orderInfo.getId());
			good.setDiscount(dtog.getDiscount());
			if (changeToMoviePrice(dtog.getPayPrice()) > dtog.getPayPrice()) {
				good.setPayPrice(changeToMoviePrice(dtog.getPayPrice()));
			} else {
				good.setPayPrice(dtog.getPayPrice());
			}
			good.setGoodsId(dtog.getGoodsId());
			goods.add(good);
		}

		CommonResponse response = actOrderClient.updateGoodsInfo(orderInfo.getId(), goods);
		if (response.getStatusCode() == CommonResponse.STATUS_OK) {
			return true;
		} else {
			logger.error(response.getStatusCode() + ":::" + response.getStatusText());
			return JsonRespWrapper.failure(response.getStatusText());
		}

	}

	/**
	 * 对当前订单，收货人地址进行验证
	 * 
	 * @return
	 */
	private boolean isNeedPost(Item item) {
		if (null != item && null != item.getId()) {
			if (item.getItemMode() == 0 && item.getPostFlag() == 1) {
				return true;
			}
		}
		return false;
	}

}
