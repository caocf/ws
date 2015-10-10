package com.cplatform.b2c.web.order;

import static com.cplatform.b2c.util.Constants.COUPON_BUYING;
import static com.cplatform.b2c.util.Constants.ITEM_SOURCE_LASHOU;
import static com.cplatform.b2c.util.Constants.ITEM_SOURCE_MTICKET;
import static com.cplatform.b2c.util.Constants.ITEM_SOURCE_YTICKET_NYL;
import static com.cplatform.b2c.util.Constants.ITEM_SOURCE_YTICKET_YL;
import static com.cplatform.b2c.util.Constants.ORDER_TYPE_AUCTION;
import static com.cplatform.b2c.util.Constants.ORDER_TYPE_COUPON;
import static com.cplatform.b2c.util.Constants.ORDER_TYPE_LASHOU;
import static com.cplatform.b2c.util.Constants.ORDER_TYPE_SPIKE;
import static com.cplatform.b2c.util.Constants.STATUS_OK;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cplatform.b2c.cache.CachedObjectType;
import com.cplatform.b2c.dto.CreateOrderDTO;
import com.cplatform.b2c.dto.CreateOrderResponse;
import com.cplatform.b2c.dto.ItemSaleDataDTO;
import com.cplatform.b2c.dto.PayDTO;
import com.cplatform.b2c.dto.PayOrderResponse;
import com.cplatform.b2c.model.SessionUser;
import com.cplatform.b2c.repository.ThirdInterDao;
import com.cplatform.b2c.service.CartService;
import com.cplatform.b2c.service.JmsMessageService;
import com.cplatform.b2c.service.MemberCenterService;
import com.cplatform.b2c.service.MemberInvoiceService;
import com.cplatform.b2c.service.OrderService;
import com.cplatform.b2c.service.OrderService2;
import com.cplatform.b2c.service.PerformService;
import com.cplatform.b2c.service.TBonusTerminalService;
import com.cplatform.b2c.service.VerifyCodeService;
import com.cplatform.b2c.util.AppConfig;
import com.cplatform.b2c.util.CommonUtils;
import com.cplatform.b2c.util.Constants;
import com.cplatform.b2c.util.CookieUtils;
import com.cplatform.b2c.util.JsonRespWrapper;
import com.cplatform.order.ActOrderGoodsInfo;
import com.cplatform.order.ActOrderInfo;
import com.cplatform.order.ActOrderPaymentInfo;
import com.cplatform.pay.PayChannel;

/**
 * User: cuikai Date: 13-11-21 Time: 上午9:37
 */
@Controller
@RequestMapping("/order")
public class OrderController {

	private final Logger logger = Logger.getLogger("business");

	private final String purchaseExpectedUrl = "http://mall.12580life.com/order/buynow.chtml";

	private final String purchaseExpectedUrlOt = "http://mall.12580life.com/order/confirm.chtml";

	@Autowired
	private OrderService orderService;

	@Autowired
	private OrderService2 orderService2;

	@Autowired
	private ThirdInterDao thirdInterDao;

	@Autowired
	private MemberInvoiceService memberInvoiceService;

	@Autowired
	private CartService cartService;

	@Autowired
	private VerifyCodeService verifyCodeService;

	@Autowired
	private AppConfig appConfig;

	@Autowired
	private JmsMessageService jmsMessageService;

	@Autowired
	private PerformService performService;

	@Autowired
	private MemberCenterService memberCenterService;

	@Autowired
	private TBonusTerminalService tBonusTerminalService;

	/**
	 * 确认订单，并且下订单，获取订单号
	 * 
	 * @param addressId
	 *            收货地址id，对应t_member_address
	 * @param invoiceId
	 *            开票信息id，对应t_member_invoice
	 * @param invoiceContentId
	 *            发票内容id，对应t_sys_invoice
	 * @param items
	 *            加入订单的商品信息， (itemid)_(quantity)[_selprop1[_selprop2]], ..n..
	 * @param remark
	 *            买家给商户的留言信息
	 */
	@RequestMapping("purchase")
	@ResponseBody
	public Object purchase(@RequestParam(value = "buynow", defaultValue = "false") String buynow,
	        @RequestParam(value = "addressId", required = false) Long addressId,
	        @RequestParam(value = "invoiceId", required = false) String invoiceId,
	        @RequestParam(value = "invoiceContentId", required = false) String invoiceContentId, @RequestParam(value = "items") String items,
	        @RequestParam(value = "remark", required = false) String remark, @RequestParam(value = "giftId", required = false) String giftId,
	        @RequestParam(value = "verifyCode", defaultValue = "") String verifyCode,
	        @CookieValue(value = "confirm_businessId", required = false) String businessId,
	        @CookieValue(value = "confirm_type", required = false) String type, HttpServletResponse response, HttpServletRequest req)
	        throws Exception {
		SessionUser sessionUser = SessionUser.getSessionUser(response);
		if (sessionUser == null) {
			return JsonRespWrapper.failure("请登录");
		}
		String fatherUrl = req.getHeader("Referer");
		if (StringUtils.isNotBlank(fatherUrl)) {
			if (!fatherUrl.startsWith(purchaseExpectedUrl) && !fatherUrl.startsWith(purchaseExpectedUrlOt)) {
				return JsonRespWrapper.failure("下单出错了，建议您在浏览器设置中清空缓存后再次尝试~");
			}
		} else {
			return JsonRespWrapper.failure("下单出错了，建议您在浏览器设置中清空缓存后再次尝试~");
		}
		String verifyCodeKey = CachedObjectType.VERIFY_CREATE_ORDER.getPrefix() + sessionUser.getId();
		if (StringUtils.isNotBlank(verifyCode)) {
			if (null != sessionUser && StringUtils.isNotBlank(sessionUser.getTerminalId())) {
				String message = checkVerifyCode(verifyCode, verifyCodeKey, sessionUser.getTerminalId());
				if (!"".equals(message)) {
					return JsonRespWrapper.failure(message);
				}
			} else {
				return JsonRespWrapper.failure("未绑定移动手机号码，请前往“个人中心>基本资料”进行绑定");
			}
		}
		if (String.valueOf(ORDER_TYPE_AUCTION).equals(type) || String.valueOf(ORDER_TYPE_SPIKE).equals(type)) {
			List<Map<String, String>> order = orderService.findOrderByUserIdAndBusinessId(sessionUser.getId(), businessId);
			if (!(order == null || order.size() == 0)) {
				return JsonRespWrapper.failure("竞拍或秒杀订单已存在");
			}
		}
		CreateOrderDTO createOrderDTO = new CreateOrderDTO();
		createOrderDTO.setUserId(sessionUser.getId());
		createOrderDTO.setUserRemark(remark);
		createOrderDTO.setAddressId(addressId);
		createOrderDTO.setBusinessId(businessId);
		createOrderDTO.setCreateSource((long) ActOrderInfo.CREATE_SOURCE_WEB);

		createOrderDTO = memberInvoiceService.fillInvoiceInfo(createOrderDTO, invoiceId, invoiceContentId, sessionUser.getId());

		try {
			createOrderDTO.setGoods(orderService2.fillGoods(items, giftId, businessId, type, sessionUser, addressId));
		}
		catch (OrderCommonException e) {
			logger.error(e.getMessage(), e);
			return JsonRespWrapper.failure(e.getMessage());
		}

		// 商品来源修改代码
		if (StringUtils.isNotBlank(type)) {
			logger.info("进入传入了orderType的分支：orderType=" + type);
			String itemSource = createOrderDTO.getGoods().get(0).getSource();
			if (ITEM_SOURCE_LASHOU.equals(itemSource)) {
				createOrderDTO.setOrderType(String.valueOf(ORDER_TYPE_LASHOU));
				logger.info("进入传入了orderType的分支后，判断拉手的商品来源，插入orderType=（ORDER_TYPE_LASHOU）" + ORDER_TYPE_LASHOU);
			} else {
				createOrderDTO.setOrderType(type);
			}
		} else {
			String itemSource = createOrderDTO.getGoods().get(0).getSource();
			String eckill = createOrderDTO.getGoods().get(0).getEckill();
			if (ITEM_SOURCE_LASHOU.equals(itemSource)) {
				createOrderDTO.setOrderType(String.valueOf(ORDER_TYPE_LASHOU));
			} else if (ITEM_SOURCE_MTICKET.equals(itemSource)) {
				createOrderDTO.setOrderType(String.valueOf(Constants.ORDER_TYPE_MTICKET));
			} else if (ITEM_SOURCE_YTICKET_YL.equals(itemSource)) {
				createOrderDTO.setOrderType(String.valueOf(Constants.ORDER_TYPE_PERFORM_YONGLE));
			} else if (ITEM_SOURCE_YTICKET_NYL.equals(itemSource)) {
				createOrderDTO.setOrderType(String.valueOf(Constants.ORDER_TYPE_PERFORM_STORE));
			} else if (Constants.ITEM_SOURCE_HN_INTEGRAL.equals(itemSource)) {
				createOrderDTO.setOrderType(String.valueOf(Constants.ORDER_TYPE_INTEGRAL));
			} else if (Constants.ITEM_SOURCE_ECOUPON.equals(itemSource)) {
				createOrderDTO.setOrderType(String.valueOf(Constants.ORDER_TYPE_ECOUPON));
			}

			// if (PANIC_BUYING.equals(eckill)) {
			// createOrderDTO.setOrderType(String.valueOf(ORDER_TYPE_SPECIAL));
			// } else
			if (COUPON_BUYING.equals(eckill)) {
				createOrderDTO.setOrderType(String.valueOf(ORDER_TYPE_COUPON));
			} else if (Constants.LABOUR_BUYING.equals(eckill)) { // 劳保商品
				// 目标库用户判断
				boolean isBonusTerminal = tBonusTerminalService.isBonusTerminal(sessionUser);
				if (!isBonusTerminal) {
					return tBonusTerminalService.unBonusTerminal();
				}

				int exprieTime = 60 * 30;
				createOrderDTO.setOrderType(String.valueOf(Constants.ORDER_TYPE_WELFARE));
				logger.info("设置订单超时时间---劳保商品");
				logger.info("劳保设置前，失效时间：" + createOrderDTO.getExpireTime());
				if (createOrderDTO.getExpireTime() != 0) {
					// 为劳保商品设置超时时间
					if (createOrderDTO.getExpireTime() > exprieTime) {
						createOrderDTO.setExpireTime(exprieTime);
					}
				} else {
					createOrderDTO.setExpireTime(exprieTime);
				}
				logger.info("劳保设置后，失效时间：" + createOrderDTO.getExpireTime());
			} else if (Constants.PROMPT_BUYING.equals(eckill)) { // 限时抢购商品
				logger.info("设置订单超时时间---限时抢购商品");
				logger.info("限时抢购设置前，失效时间：" + createOrderDTO.getExpireTime());
				int exprieTime = 60 * 60; // 暂定为1小时，超过时间订单自动取消
				createOrderDTO.setOrderType(String.valueOf(Constants.ORDER_TYPE_PROMPT));
				if (createOrderDTO.getExpireTime() != 0) {
					// 限时抢购商品
					if (createOrderDTO.getExpireTime() > exprieTime) {
						createOrderDTO.setExpireTime(exprieTime);
					}
				} else {
					createOrderDTO.setExpireTime(exprieTime);
				}
				logger.info("限时抢购设置后，失效时间：" + createOrderDTO.getExpireTime());
			}

		}

		// 为演出票设置超时时间10分钟
		String itemSource = createOrderDTO.getGoods().get(0).getSource();
		if (Constants.ITEM_SOURCE_YTICKET_YL.equals(itemSource) || Constants.ITEM_SOURCE_YTICKET_NYL.equals(itemSource)) {
			logger.info("设置订单超时时间---演出票商品");
			logger.info("演出票设置前，失效时间：" + createOrderDTO.getExpireTime());
			int exprieTime = 600;
			if (StringUtils.isNotBlank(appConfig.getPerformExpireTime())) {
				exprieTime = Integer.parseInt(appConfig.getPerformExpireTime());
			}
			if (createOrderDTO.getExpireTime() != 0) {
				if (createOrderDTO.getExpireTime() > exprieTime) {
					createOrderDTO.setExpireTime(exprieTime);
				}
			} else {
				createOrderDTO.setExpireTime(exprieTime);
			}
			logger.info("演出票设置后，失效时间：" + createOrderDTO.getExpireTime());
		}

		List<String> goodsId = orderService2.getGoodIds(createOrderDTO.getGoods());
		if (!"true".equals(buynow)) {
			boolean hasitem = cartService.checkCartItems(goodsId, sessionUser.getId());
			if (!hasitem)
				return JsonRespWrapper.failure("您已提交过该订单，或该订单的商品不在购物车中，请重新选购！");
		}

		// 创建订单
		CreateOrderResponse createOrderResponse = orderService2.createOrder(createOrderDTO);
		if (createOrderResponse != null && String.valueOf(STATUS_OK).equals(createOrderResponse.getFlag())) {
			verifyCodeService.removeVerifyCode(verifyCodeKey);
			Long orderId = createOrderResponse.getOrderId();

			// 删除购物车数据
			try {
				cartService.delCartItems(goodsId, sessionUser.getId());
			}
			catch (Exception ex) {
				logger.warn("删除已加入订单的购物车商品出错", ex);
			}
			// 因为商城抵用券等都是跟用户手机号绑定的。即当用户没绑定手机号时，改用户下没有抵用券、礼金券
			if (StringUtils.isNotBlank(giftId) && StringUtils.isNotBlank(sessionUser.getTerminalId())) {
				orderService2.callCardInterface(giftId, sessionUser.getTerminalId(), orderId, createOrderDTO.getGoods());
			}
			CookieUtils.removeCookieFromRoot(response, "confirm_businessId");
			CookieUtils.removeCookieFromRoot(response, "confirm_type");
			return JsonRespWrapper.success("orderId", orderId);

		} else {
			logger.error("创建订单失败, " + createOrderResponse.getMsg());
			return JsonRespWrapper.failure("创建订单失败, " + createOrderResponse.getMsg());
		}
	}

	@RequestMapping("pay")
	@ResponseBody
	public Object pay(@RequestParam(value = "id") Long orderId, @RequestParam(value = "useCoin", defaultValue = "0") String coin,
	        @RequestParam(value = "useScore", defaultValue = "0") String score,
	        @RequestParam(value = "payType", defaultValue = "") String cashPayType,
	        @RequestParam(value = "verifyCode", defaultValue = "-1") String verifyCode,
	        @RequestParam(value = "payForm", defaultValue = "") String payForm,
	        @RequestParam(value = "useBalance", defaultValue = "0") String balance, HttpServletResponse httpServletResponse) throws Exception {

		int useCoin = Math.abs(CommonUtils.toFen(coin));
		int useScore = Math.abs(CommonUtils.toFen(score));
		int useBalance = Math.abs(CommonUtils.toFen(balance));

		SessionUser user = SessionUser.getSessionUser(httpServletResponse);
		String verifyCodeKey = CachedObjectType.VERIFY_PAY.getPrefix() + user.getId();

		if (useCoin + useScore + useBalance > 0) {
			if (null != user && StringUtils.isNotBlank(user.getTerminalId())) {
				String message = checkVerifyCode(verifyCode, verifyCodeKey, user.getTerminalId());
				if (!"".equals(message)) {
					return JsonRespWrapper.failure(message);
				}
			} else {
				return JsonRespWrapper.failure("未绑定移动手机号码，请前往“个人中心>基本资料”进行绑定");
			}
		}
		// 查询订单信息
		ActOrderInfo actOrderInfo = orderService2.getOrderInfo(orderId);
		logger.info("查询订单信息" + orderId + ":" + actOrderInfo);
		if (actOrderInfo != null) {
			// 判断二次支付时有支付信息就不走校验
			List<ActOrderPaymentInfo> paymentInfos = actOrderInfo.getPaymentInfos();
			// 有支付信息不需要校验
			if (paymentInfos != null && paymentInfos.size() > 0) {
				// for(ActOrderPaymentInfo paymentInfo:paymentInfos){
				// paymentInfo.getChannal()
				// }balance score coin cash
				logger.info("===================================" + "支付信息存在的" + paymentInfos.toArray());
			} else {
				logger.info("===================================" + "支付信息不存在的进入验证");
				// 判断url中支付方式是否和商品支持的支付方式一致
				List<ActOrderGoodsInfo> goodsInfos = actOrderInfo.getGoodsInfos();
				if (goodsInfos != null && goodsInfos.size() > 0) {
					for (ActOrderGoodsInfo info : goodsInfos) {
						String backString = checkItemHavePayMode(payForm, info.getGoodsId());
						if (backString.equals("fail")) {
							return JsonRespWrapper.failure("该订单中商品对应的支付方式和您选择的支付方式不一致！");
						}
					}
				}
			}

			// 永乐演出票订单
			Integer orderType = actOrderInfo.getOrderType();
			logger.info("orderId" + orderId + "orderType:" + orderType);
			if (Constants.ORDER_TYPE_PERFORM_YONGLE == orderType) {
				logger.info("永乐演出票订单调演出票内部接口订单号：" + orderId);
				ActOrderGoodsInfo goodsInfo = actOrderInfo.getGoodsInfos().get(0);
				Long itemId = goodsInfo.getGoodsId();
				Map<String, String> ticketInfo = performService.getPerformTicketInfo(Long.toString(itemId));
				if (ticketInfo != null) {
					String productId = ticketInfo.get("product_id");// 永乐票品ID
					String productPlayid = ticketInfo.get("product_play_id");// 永乐票价ID
					String buyType = "0";// 买卖类型（只接受0/1，其他均为无效, 0-购买 1-登记）
					String result = thirdInterDao.performBeforeOrder(productId, productPlayid, buyType, goodsInfo.getCount(),
					                                                 CommonUtils.toYuan(goodsInfo.getPayPrice()));
					logger.info("订单号：" + orderId + "返回报文:" + result);
					if (StringUtils.isNotBlank(result)) {
						JSONObject jsonObj = JSONObject.fromObject(result);
						if (!"200".equals(jsonObj.getString("code"))) {
							return JsonRespWrapper.failure(jsonObj.getString("message"));
						}
					} else {
						return JsonRespWrapper.failure("错误的订单信息");
					}
				} else {
					return JsonRespWrapper.failure("商品信息不存在");
				}
			}
		} else {
			return JsonRespWrapper.failure("订单不存在");
		}
		PayDTO payDTO = new PayDTO();
		payDTO.setReturnURL(appConfig.getServer_host() + appConfig.getWebRoot() + "order/payment-success.chtml");
		payDTO.setPayType("web");
		if (cashPayType.equals("cmpay")) {
			payDTO.setChannel(PayChannel.UNIFY_CART_APPLY_CMPAY);
		} else if (cashPayType.equals("alipay")) {
			payDTO.setChannel(PayChannel.UNIFY_CART_APPLY_ALIPAY);
		}
		payDTO.setPayForm(payForm);
		payDTO.setScore(useScore);
		payDTO.setCoin(useCoin);
		payDTO.setBalance(useBalance);
		payDTO.setOrderId(orderId);
		payDTO.setUserId(user.getId());

		PayOrderResponse payOrderResponse = orderService2.payOrder(payDTO);

		if (payOrderResponse != null && String.valueOf(STATUS_OK).equals(payOrderResponse.getFlag())) {
			verifyCodeService.removeVerifyCode(verifyCodeKey);
			// thirdInterDao.notifyWhenPayed(payDTO);
			String url = payOrderResponse.getRedirectUrl();
			if (StringUtils.isNotEmpty(url)) {
				return JsonRespWrapper.success("type", "redirect").json("url", url);
			} else if (StringUtils.isNotEmpty(payOrderResponse.getFormActionUrl())) {
				return JsonRespWrapper.success("type", "form").json("action", payOrderResponse.getFormActionUrl())
				        .json("input", payOrderResponse.getHtml());
			} else {
				return JsonRespWrapper.success("type", "success").json("orderId", orderId);
			}
		} else {
			logger.error("支付异常: " + payOrderResponse.getMsg());
		}
		return JsonRespWrapper.failure("订单支付异常,请联系客服");
	}

	@RequestMapping("sendMessage")
	@ResponseBody
	public Object sendMessage(@RequestParam(value = "SMSFrom", defaultValue = "") String SMSFrom, HttpServletResponse resp) {

		SessionUser user = SessionUser.getSessionUser(resp);

		String verifyCode = "";
		String message = "";
		if ("orderConfirm".equals(SMSFrom)) {
			verifyCode = verifyCodeService.generateVerifyCode(CachedObjectType.VERIFY_CREATE_ORDER.getPrefix() + user.getId().longValue());
			message = MessageFormat.format(appConfig.getSmsCreateOrder(), verifyCode);
		} else {
			verifyCode = verifyCodeService.generateVerifyCode(CachedObjectType.VERIFY_PAY.getPrefix() + user.getId().longValue());
			message = MessageFormat.format(appConfig.getSmsPayCoin(), verifyCode);
		}
		try {
			jmsMessageService.sendSms(message, user.getTerminalId());
		}
		catch (Exception ex) {
			logger.warn("发送验证码消息失败, 验证码：" + verifyCode, ex);
		}

		return JsonRespWrapper.success("type", "success");// .json("randomKey",
		                                                  // verifyCode);
	}

	@RequestMapping("payment-success")
	public String paySuccess(@RequestParam(value = "orderId", defaultValue = "") Long orderId, HttpServletResponse response,
	        HttpServletRequest request) {
		SessionUser user = SessionUser.getSessionUser(response);

		if (orderId == null) {
			return "cart/pay-success";
		}
		if (user == null) {
			return null;
		} else {
			try {
				ActOrderInfo actOrderInfo = orderService2.getOrderInfo(orderId);
				if (actOrderInfo != null && actOrderInfo.getUserId() == user.getId()) {
					if (Constants.ORDER_TYPE_INTEGRAL == actOrderInfo.getOrderType() || Constants.ORDER_TYPE_ECOUPON == actOrderInfo.getOrderType()) {
						return "cart/pay-hn-success";
					}
					return "cart/pay-success";
				} else {
					return null;
				}
			}
			catch (Exception e) {
				logger.error("调用支付成功页面异常: 订单ID【" + orderId + "】\n" + e.getMessage());
				return null;
			}
		}
	}

	@RequestMapping("payCallback")
	public String payCallback() {
		// return "cart/pay-callback";
		return null;
	}

	@RequestMapping("pay-redirect")
	public String payRedirect() {
		return "cart/pay-redirect";
	}

	private String checkVerifyCode(String verifyCode, String verifyCodeKey, String userTerminalId) {
		String cachedCode = verifyCodeService.getVerifyCode(verifyCodeKey);
		if (cachedCode == null) {
			return "验证码已过期，请重新获取";
		} else if (!verifyCode.equals(cachedCode)) {
			String message = "";
			if (verifyCodeKey.contains(CachedObjectType.VERIFY_CREATE_ORDER.getPrefix())) {
				message = "下单";
			} else if (verifyCodeKey.contains(CachedObjectType.VERIFY_PAY.getPrefix())) {
				message = "支付";
			}
			logger.info("用户【" + userTerminalId + "】," + message + "输入验证码【" + verifyCode + "】，期望验证码【" + cachedCode + "】");
			return "验证码错误";
		}
		return "";
	}

	@RequestMapping("pay_hn")
	@ResponseBody
	public Object pay_hn(@RequestParam(value = "id") Long orderId, @RequestParam(value = "useCoin", defaultValue = "0") String coin,
	        @RequestParam(value = "useScore", defaultValue = "0") String score,
	        @RequestParam(value = "payType", defaultValue = "") String cashPayType,
	        @RequestParam(value = "verifyCode", defaultValue = "-1") String verifyCode,
	        @RequestParam(value = "payForm", defaultValue = "") String payForm,
	        @RequestParam(value = "useBalance", defaultValue = "0") String balance, HttpServletResponse httpServletResponse) throws Exception {

		int useCoin = Math.abs(CommonUtils.toFen(coin));

		// 此处河南积分支付不需要进行转换
		// int useScore = Math.abs(CommonUtils.toFen(score));
		// int useScore = Math.abs((int) (Math.floor(Double.valueOf(score))));
		int useScore = Math.abs(CommonUtils.toHnScore(score));

		int useBalance = Math.abs(CommonUtils.toFen(balance));

		SessionUser user = SessionUser.getSessionUser(httpServletResponse);
		String verifyCodeKey = CachedObjectType.VERIFY_PAY.getPrefix() + user.getId();

		// 此处河南商品支付不需要进行发码验证

		PayDTO payDTO = new PayDTO();
		payDTO.setReturnURL(appConfig.getServer_host() + appConfig.getWebRoot() + "order/payment-success.chtml");
		payDTO.setPayType("web");
		if (cashPayType.equals("cmpay")) {
			payDTO.setChannel(PayChannel.UNIFY_CART_APPLY_CMPAY);
		} else if (cashPayType.equals("alipay")) {
			payDTO.setChannel(PayChannel.UNIFY_CART_APPLY_ALIPAY);
		}
		payDTO.setPayForm(payForm);
		payDTO.setScore(useScore);
		payDTO.setCoin(useCoin);
		payDTO.setBalance(useBalance);
		payDTO.setOrderId(orderId);
		payDTO.setUserId(user.getId());

		PayOrderResponse payOrderResponse = orderService2.payOrder(payDTO);

		if (payOrderResponse != null && String.valueOf(STATUS_OK).equals(payOrderResponse.getFlag())) {
			verifyCodeService.removeVerifyCode(verifyCodeKey);
			// thirdInterDao.notifyWhenPayed(payDTO);
			String url = payOrderResponse.getRedirectUrl();
			if (StringUtils.isNotEmpty(url)) {
				return JsonRespWrapper.success("type", "redirect").json("url", url);
			} else if (StringUtils.isNotEmpty(payOrderResponse.getFormActionUrl())) {
				return JsonRespWrapper.success("type", "form").json("action", payOrderResponse.getFormActionUrl())
				        .json("input", payOrderResponse.getHtml());
			} else {
				return JsonRespWrapper.success("type", "success").json("orderId", orderId);
			}
		} else {
			logger.error("支付异常: " + payOrderResponse.getMsg());
		}
		return JsonRespWrapper.failure("订单支付异常,请联系客服");
	}

	/**
	 * 判断支付地址中的payForm是不是购买商品所支持的支付方式
	 * 
	 * @param payForm
	 * @param goodsId
	 * @return
	 */
	private String checkItemHavePayMode(String payForm, Long goodsId) {
		ItemSaleDataDTO itemSaleDataDTO = thirdInterDao.getItemById(goodsId + "");
		if (StringUtils.isNotBlank(payForm)) {
			if (payForm.equals("cash_and_coin")) {
				if (itemSaleDataDTO.getItem().getCashIdgoods() == 1 && itemSaleDataDTO.getItem().getCoinIdgoods() == 1) {
					return "ok";
				} else {
					return "fail";
				}
			} else if (payForm.equals("cash_and_score")) {
				if (itemSaleDataDTO.getItem().getCashIdgoods() == 1 && itemSaleDataDTO.getItem().getScoreIdgoods() == 1) {
					return "ok";
				} else {
					return "fail";
				}
			} else if (payForm.equals("only_cash")) {
				if (itemSaleDataDTO.getItem().getCashIdgoods() == 1) {
					return "ok";
				} else {
					return "fail";
				}
			} else if (payForm.equals("only_coin")) {
				if (itemSaleDataDTO.getItem().getCoinIdgoods() == 1) {
					return "ok";
				} else {
					return "fail";
				}
			} else if (payForm.equals("only_score")) {
				if (itemSaleDataDTO.getItem().getScoreIdgoods() == 1) {
					return "ok";
				} else {
					return "fail";
				}
			} else if (payForm.equals("only_balance")) {
				if (itemSaleDataDTO.getItem().getBillPay() == 1) {
					return "ok";
				} else {
					return "fail";
				}
			} else if (payForm.equals("cash_and_balance")) {
				if (itemSaleDataDTO.getItem().getCashIdgoods() == 1 && itemSaleDataDTO.getItem().getBillPay() == 1) {
					return "ok";
				} else {
					return "fail";
				}
			} else if (payForm.equals("score_and_balance")) {
				if (itemSaleDataDTO.getItem().getScoreIdgoods() == 1 && itemSaleDataDTO.getItem().getBillPay() == 1) {
					return "ok";
				} else {
					return "fail";
				}
			} else if (payForm.equals("coin_and_balance")) {
				if (itemSaleDataDTO.getItem().getCoinIdgoods() == 1 && itemSaleDataDTO.getItem().getBillPay() == 1) {
					return "ok";
				} else {
					return "fail";
				}
			} else if (payForm.equals("ems.pay_on_delivery")) {

			}
		} else {
			return "fail";
		}
		return "fail";
	}
}
