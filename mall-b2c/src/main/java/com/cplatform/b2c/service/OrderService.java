package com.cplatform.b2c.service;

import static com.cplatform.b2c.util.Constants.PANIC_BUYING;
import static com.cplatform.b2c.util.Constants.RED_MEMBER;
import static com.cplatform.b2c.util.Constants.RED_USER_LEVELS;
import static com.cplatform.b2c.util.Constants.UNION_USER_LEVELS;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplatform.b2c.dto.CallBalance;
import com.cplatform.b2c.dto.GiftCardRequest;
import com.cplatform.b2c.dto.GiftCardResponse;
import com.cplatform.b2c.dto.ItemSaleDataDTO;
import com.cplatform.b2c.dto.MarketGoodsDTO;
import com.cplatform.b2c.model.ItemSalePayment;
import com.cplatform.b2c.model.OrderPayInfo;
import com.cplatform.b2c.model.SessionUser;
import com.cplatform.b2c.repository.ThirdInterDao;
import com.cplatform.b2c.repository.UnionMemberDao;
import com.cplatform.b2c.util.AppConfig;
import com.cplatform.b2c.util.Constants;
import com.cplatform.dbhelp.DbHelper;
import com.cplatform.order.ActOrderGoodsInfo;
import com.cplatform.order.ActOrderInfo;
import com.cplatform.order.ActOrderPaymentInfo;
import com.cplatform.order.ActOrderStatus;
import com.cplatform.pay.PayOrderInfo;
import com.cplatform.pay.PaymentInfo;
import com.cplatform.pay.RequestOperate;
import com.google.common.collect.Maps;

/**
 * 订单服务类
 * <p/>
 * Copyright: Copyright (c) 13-6-16 下午3:33
 * <p/>
 * Company: 北京宽连十方数字技术有限公司
 * <p/>
 * Author: nicky
 * <p/>
 * Version: 1.0
 * <p/>
 */
@Service
public class OrderService {

	private final Logger logger = Logger.getLogger(getClass());

	private static Logger businessLogger = Logger.getLogger("business");

	@Autowired
	DbHelper dbHelper;

	@Autowired
	AppConfig appConfig;

	@Autowired
	CommonCacheService commonCacheService;

	@Autowired
	private UnionMemberDao unionMemberDao;

	@Autowired
	private ThirdInterDao interfaceDao;

	/**
	 * 商户给出的发票信息
	 * 
	 * @param storeId
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String, String>> findShopInvoices(String storeId) throws SQLException {
		String sql = "select invoice_id as id, invoice_name as name from t_shop_invoice where shop_id = ?";
		List<Map<String, String>> result = dbHelper.getMapList(sql, storeId);
		return result;
	}

	public List<Map<String, String>> findOrderByUserIdAndBusinessId(Long userId, String businessId) throws Exception {
		String sql = "select id from t_act_order where user_id=? and ext_info=?";
		return dbHelper.getMapList(sql, userId, businessId);
	}

	public Map<String, String> findGoodsPayTypeById(Long id) throws Exception {
		String sql = "select cash_idgoods,coin_idgoods,score_idgoods from t_item_sale where id=?";
		return dbHelper.getMap(sql, id);
	}

	public ActOrderPaymentInfo findOrderPaymentByOrderId(Long orderId) throws Exception {
		String sql = "select * from t_act_order_payment p where p.act_order_id=? ";
		return dbHelper.getBean(sql, ActOrderPaymentInfo.class, orderId);
	}

	public ItemSalePayment findItemPaymentByItem(Long itemId) throws Exception {
		String sql = "select * from T_ITEM_SALE_PAYMENT p where p.item_id=?";
		return dbHelper.getBean(sql, ItemSalePayment.class, itemId);
	}

	@Deprecated
	public JSONObject getItemFromInterface(String itemId) {
		return interfaceDao.getItem(itemId);
	}

	@Deprecated
	public GiftCardResponse getGiftCardResponse(GiftCardRequest cardRequest) {
		return interfaceDao.getGiftCardResponse(cardRequest);
	}

	@Deprecated
	public JSONObject getGroupItemsFromInterface(String itemId) {
		return interfaceDao.getGroupItemsFromInterface(itemId);
	}

	@Deprecated
	public CallBalance getBalanceInfo(String mobile) {
		return interfaceDao.getBalanceInfo(mobile);
	}

	/**
	 * 根据商品、用户权益从新设置商品的商城价
	 * 
	 * @param itemInfo
	 * @param type
	 * @param sessionUser
	 * @param businessId
	 * @return
	 */
	public String fillPrice(ItemSaleDataDTO itemInfo, String type, SessionUser sessionUser, String businessId) {
		String price = getCheckPrice(itemInfo, sessionUser, type, businessId);
		if (Constants.AUCTION_RETURN_NULL.equals(price)) {
			return Constants.AUCTION_RETURN_NULL;
		} else {
			itemInfo.getItem().setShopPrice(new BigDecimal(price));
		}
		return null;
	}

	/**
	 * 判断商品是竞拍商品，设置相应的商品商城价
	 * 
	 * @param itemInfo
	 * @param sessionUser
	 * @param type
	 * @param businessId
	 * @return
	 */
	public String getCheckPrice(ItemSaleDataDTO itemInfo, SessionUser sessionUser, String type, String businessId) {
		// 判断是否是竞拍商品
		if (StringUtils.isBlank(type) || (!type.equals("1") && !type.equals("2"))) { // 不是竞拍商品
			return this.getSuitablePrice(itemInfo, sessionUser);
		}
		// 竞拍商品
		if (StringUtils.isNotBlank(type) && ("1".equals(type) || "2".equals(type))) {
			String itemId = String.valueOf(itemInfo.getItem().getId());
			JSONObject auctionGoodInfo = interfaceDao.getAuctionOrderInterface(itemId, type, businessId);
			if (auctionGoodInfo != null && !auctionGoodInfo.isEmpty()) {
				String userId = auctionGoodInfo.getString("confirm_userId");
				if (userId.equals(String.valueOf(sessionUser.getId()))) {
					return auctionGoodInfo.getString("confirm_price");
				}
			}
			logger.error("判断是否竞拍商品出现异常---type：" + type + "，businessId：" + businessId + "，itemId：" + itemInfo.getItem().getId());
			return Constants.AUCTION_RETURN_NULL;
		}
		return Constants.AUCTION_RETURN_NULL;
	}

	/**
	 * 判断用户权益、商品支持会员价格，重新设置商品商城价
	 * 
	 * @param itemInfo
	 * @param sessionUser
	 * @return
	 */
	public String getSuitablePrice(ItemSaleDataDTO itemInfo, SessionUser sessionUser) {

		boolean redMember = isRedMember(sessionUser);
		boolean unionMember = isUnionMember(sessionUser.getTerminalId());
		String price = "";
		StringBuilder buffer = new StringBuilder();

		if (redMember && unionMember) {
			buffer.append(sessionUser.getTerminalId() + "同时是商盟会员和红钻会员");
			Map<String, String> pricemap = getPricemap(itemInfo);
			String redPrice = pricemap.get(RED_USER_LEVELS);
			String unionPrice = pricemap.get(UNION_USER_LEVELS);
			if (StringUtils.isNotBlank(redPrice) && StringUtils.isNotBlank(unionPrice)) {
				int result = new BigDecimal(redPrice).compareTo(new BigDecimal(unionPrice));
				if (result > 0) { // 红钻价 > 商盟价
					price = pricemap.get(UNION_USER_LEVELS);
				} else if (result < 0) {
					price = pricemap.get(RED_USER_LEVELS); // 红钻价 < 商盟价
				} else {
					price = pricemap.get(RED_USER_LEVELS); // 两个价格相等
				}
			} else if (StringUtils.isBlank(redPrice)) {
				price = pricemap.get(UNION_USER_LEVELS);
			} else if (StringUtils.isBlank(unionPrice)) {
				price = pricemap.get(RED_USER_LEVELS);
			}
		} else if (unionMember) {
			buffer.append(sessionUser.getTerminalId() + "是商盟会员");
			price = getPricemap(itemInfo).get(UNION_USER_LEVELS);
		} else if (redMember) {
			buffer.append(sessionUser.getTerminalId() + "是红钻会员");
			price = getPricemap(itemInfo).get(RED_USER_LEVELS);
		}
		if (StringUtils.isBlank(price)) {
			price = String.valueOf(itemInfo.getItem().getShopPrice());
		}
		businessLogger.info(buffer.toString() + ",最终商品价格:" + price);
		return price;
	}

	private Map<String, String> getPricemap(ItemSaleDataDTO itemInfo) {
		Map<String, String> map = Maps.newHashMap();
		List<ItemSaleDataDTO.ItemPrice> prices = itemInfo.getItemPrice();
		for (ItemSaleDataDTO.ItemPrice price : prices) {
			map.put(price.getPriceTypeCode(), String.valueOf(price.getPrice()));
		}
		return map;
	}

	/**
	 * 判断是否是商盟会员
	 * 
	 * @param terminalId
	 *            用户手机号
	 * @return true：会员；false：不是会员；
	 */
	public boolean isUnionMember(String terminalId) {
		if (appConfig.isTest())
			return false;
		UnionMemberDao.UnionMember unionMember = unionMemberDao.getUnionMemberInfo(terminalId);

		return unionMember.getMember() != null && unionMember.getMember().equals("true") && !unionMember.getBossSet().equals("540001")
		        && !unionMember.getBossSet().equals("540005") && !unionMember.getBossSet().equals("700001");
	}

	/**
	 * 判断是否是红钻会员
	 * 
	 * @param user
	 * @return true：会员；false：不是会员；
	 */
	public boolean isRedMember(SessionUser user) {

		return user.getRedMember() != null && user.getRedMember().equals(1);
	}

	/**
	 * 处理参数传来的使用的减免商城币，返回真正应该使用的商城币数量,单位分
	 * 
	 * @param orderInfo
	 * @param useCoin
	 */

	public int checkCoinPay(ActOrderInfo orderInfo, String useCoin) {
		int coinMoney = new BigDecimal(NumberUtils.toDouble(useCoin, 0)).setScale(2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100))
		        .intValue();
		if (coinMoney > orderInfo.getTotalPayAmount()) {
			coinMoney = orderInfo.getTotalPayAmount();
		}
		return coinMoney;
	}

	/**
	 * 检查order信息，是否可以支付
	 * 
	 * @return
	 */
	public String checkPayOrder(ActOrderInfo actOrderInfo, HttpServletResponse response) {
		SessionUser sessionUser = SessionUser.getSessionUser(response);
		String defaultMsg = "发生错误，支付遇到问题||";
		if (sessionUser == null) {
			return "请先登录";
		}

		if (!sessionUser.getId().equals(actOrderInfo.getUserId())) {
			logger.warn("非法的订单访问：user:" + sessionUser.getId() + ", orderId: " + actOrderInfo.getId());
			return defaultMsg + "订单不存在";
		}

		// 订单已关闭
		if (actOrderInfo.getCloseStatus() == ActOrderStatus.CLOSE_STATUS_CLOSED) {
			return defaultMsg + "订单已关闭";
		}

		// 订单已删除
		if (actOrderInfo.getDeleteStatus() == ActOrderInfo.DELETE_YES) {
			return defaultMsg + "订单已删除";
		}

		// 订单已支付
		if (actOrderInfo.getPayStatus() == ActOrderStatus.PAY_STATUS_PAID) {
			return "支付成功||" + "订单已支付成功";
		}

		return null;
	}

	/**
	 * 获取到剩余还需要支付的支付项目
	 * 
	 * @param paymentInfos
	 * @param payedInfo
	 * @return
	 */
	public List<ActOrderPaymentInfo> getLeftPayInfos(List<ActOrderPaymentInfo> paymentInfos, Map<String, Integer> payedInfo) {
		List<ActOrderPaymentInfo> leftPayInfos = new ArrayList<ActOrderPaymentInfo>();
		for (ActOrderPaymentInfo paymentInfo : paymentInfos) {
			ActOrderPaymentInfo leftpay = new ActOrderPaymentInfo();
			try {
				BeanUtils.copyProperties(leftpay, paymentInfo);
			}
			catch (Exception e) {
				logger.error("获取到剩余还需要支付的支付项目", e);
			}

			if (payedInfo.containsKey(leftpay.getCurrency())) {
				leftpay.setAmount(leftpay.getAmount() - payedInfo.get(leftpay.getCurrency()));
			}

			if (leftpay.getAmount() > 0) {
				leftPayInfos.add(leftpay);
			}
		}
		return leftPayInfos;
	}

	/**
	 * 判断订单是否第一次支付
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean isFirstPay(ActOrderInfo actOrderInfo) throws Exception {
		return actOrderInfo.getPayStatus() == ActOrderStatus.PAY_STATUS_UNPAID;
	}

	/**
	 * 计算已付款的金额，及类型
	 * 
	 * @param payOrderInfos
	 * @return
	 */
	public Map<String, Integer> getAllPayedInformation(List<PayOrderInfo> payOrderInfos) {
		Map<String, Integer> result = new HashMap<String, Integer>();
		for (PayOrderInfo payOrderInfo : payOrderInfos) {
			if (payOrderInfo.getStatus() != PayOrderInfo.STATUS_SUCCESS)
				continue;
			List<PaymentInfo> paymentInfos = payOrderInfo.getPayments();
			for (PaymentInfo paymentInfo : paymentInfos) {
				String currency = paymentInfo.getCurrency();
				int countAmount = 0;
				if (result.containsKey(currency)) {
					countAmount = result.get(currency);
				}
				result.put(currency, countAmount + paymentInfo.getAmount());
			}
		}
		return result;
	}

	/**
	 * 检查是否存在退款信息
	 * 
	 * @param payOrderInfos
	 * @return
	 */
	public boolean existRefundInfo(List<PayOrderInfo> payOrderInfos) {
		for (PayOrderInfo payOrderInfo : payOrderInfos) {
			if (payOrderInfo.getOperate() == RequestOperate.Refund) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 获取商城币或积分
	 * 
	 * @param type
	 *            0商城币 1积分
	 * @return
	 */
	public double getMallCoin(int type, HttpServletResponse response) {
		SessionUser sessionUser = SessionUser.getSessionUser(response);
		if (sessionUser == null)
			return 0;
		String mobile = sessionUser.getTerminalId();
		if (StringUtils.isEmpty(mobile))
			return 0;
		JSONObject obj = interfaceDao.getCoinFromInterface(mobile);
		if (obj == null)
			return 0;
		if ("0".equals(obj.getString("statusCode"))) {
			return obj.getDouble("coin");
		}
		return 0;
	}

	public boolean isInSaleArea(ItemSaleDataDTO itemInfo, HttpServletResponse response) {
		SessionUser sessionUser = SessionUser.getSessionUser(response);
		String userArea = sessionUser.getAreaCode();
		String notAllowSaleArea = itemInfo.getItem().getRegionCodes();
		if (StringUtils.isBlank(notAllowSaleArea) || StringUtils.isBlank(userArea))
			return true;
		String saleAreaStr = notAllowSaleArea.toString();
		String[] t = saleAreaStr.split(",");
		for (String s : t) {
			if (StringUtils.contains(s, userArea)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断商品是否支持会员价
	 * 
	 * @param itemInfo
	 * @return
	 */
	public boolean isVipGoods(ItemSaleDataDTO itemInfo) {
		List<ItemSaleDataDTO.ItemPrice> itemPrices = itemInfo.getItemPrice();
		if (!itemPrices.isEmpty()) {
			boolean haveVipPrice = false;
			for (ItemSaleDataDTO.ItemPrice itemPrice : itemPrices) {
				if (itemPrice.getPrice() != null && !"".equals(itemPrice.getPrice())) {
					haveVipPrice = haveVipPrice || true;
					break;
				}
			}
			return haveVipPrice;
		}
		return false;
	}

	/**
	 * 检查商品对于会员级别的限制
	 * 
	 * @param item
	 * @param response
	 * @return null表示当前会员不限制购买，其他字符返回会员级别限制的项目，比如L1
	 */
	public String checkUserLevelLimit(ItemSaleDataDTO item, HttpServletResponse response) {
		String ul = item.getItem().getUserLevels();
		if (StringUtils.isBlank(ul)) {
			return null;
		} else {

			SessionUser sessionUser = SessionUser.getSessionUser(response);
			if (sessionUser == null)
				return null;

			MarketGoodsDTO marketGoodsDTO = item.getMarketGoodsProperty();
			if (PANIC_BUYING.equals(item.getItem().getIseckill()) && marketGoodsDTO != null) {
				String buyLimit = marketGoodsDTO.getBuyLimit();
				if (StringUtils.isBlank(buyLimit) || "0".equals(buyLimit)) {
					return null;
				}

				if (StringUtils.equals(buyLimit, "2")) {
					ul = RED_USER_LEVELS;
				} else if (StringUtils.equals(buyLimit, "1")) {
					ul = UNION_USER_LEVELS;
				} else if (StringUtils.contains(buyLimit, "1") && StringUtils.contains(buyLimit, "2")) {
					ul = RED_USER_LEVELS + "," + UNION_USER_LEVELS;
				}
			}
			return checkMemberSpecial(sessionUser, ul);
		}
	}

	private String checkMemberSpecial(SessionUser user, String userLevels) {
		boolean redMember = user.getRedMember() == RED_MEMBER;
		boolean unionMember = isUnionMember(user.getTerminalId());
		if (RED_USER_LEVELS.equals(userLevels)) {
			if (!redMember)
				return RED_USER_LEVELS;
		} else if (UNION_USER_LEVELS.equals(userLevels)) {
			if (!unionMember)
				return UNION_USER_LEVELS;
		} else if (userLevels.contains(RED_USER_LEVELS) && userLevels.contains(UNION_USER_LEVELS)) {
			if (!(redMember || unionMember))
				return UNION_USER_LEVELS;
		}
		return null;
	}

	/**
	 * 获取订单支持的支付方式，生成订单时商品的支付方式必须一致
	 * 
	 * @param payInfo
	 * @param actOrderInfo
	 * @return
	 * @throws Exception
	 */
	public OrderPayInfo getOrderPayType(OrderPayInfo payInfo, ActOrderInfo actOrderInfo) throws Exception {
		List<ActOrderGoodsInfo> actOrderGoodsInfos = actOrderInfo.getGoodsInfos();
		for (ActOrderGoodsInfo goodsInfo : actOrderGoodsInfos) {
			Long id = goodsInfo.getGoodsId();
			Map<String, String> payTypes = findGoodsPayTypeById(id);
			payInfo.setCashPay(payTypes.get("cash_idgoods"));
			payInfo.setCoinPay(payTypes.get("coin_idgoods"));
			payInfo.setScorePay(payTypes.get("score_idgoods"));

			ItemSalePayment payment = findItemPaymentByItem(id);
			if (payment != null) {
				if (payment.getBillPay() != null) {
					payInfo.setBillPay(payment.getBillPay().toString());
				}
				if (payment.getPayType() != null) {
					payInfo.setPayType(payment.getPayType().toString());
				}
				if (payment.getHnScorePay() != null) {
					// 河南积分的判断
					payInfo.setHnScorePay(payment.getHnScorePay().toString());
				}
			}
			break;
		}
		return payInfo;
	}

	/**
	 * 允许使用商城币上限
	 * 
	 * @return
	 */
	public double getAllowCoin() {
		// 获取商城币
		return -1;
	}

}
