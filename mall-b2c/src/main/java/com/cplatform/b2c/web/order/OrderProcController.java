package com.cplatform.b2c.web.order;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cplatform.b2c.cart.CartDataManager;
import com.cplatform.b2c.dto.CallBalance;
import com.cplatform.b2c.model.OrderPayInfo;
import com.cplatform.b2c.model.SessionUser;
import com.cplatform.b2c.repository.ThirdInterDao;
import com.cplatform.b2c.service.CartService;
import com.cplatform.b2c.service.JmsMessageService;
import com.cplatform.b2c.service.OrderService;
import com.cplatform.b2c.service.OrderService2;
import com.cplatform.b2c.service.ShopService;
import com.cplatform.b2c.service.UserSercvice;
import com.cplatform.b2c.util.AppConfig;
import com.cplatform.b2c.util.CommonUtils;
import com.cplatform.b2c.util.Constants;
import com.cplatform.b2c.util.PaymentConfirmUtil;
import com.cplatform.order.ActOrderInfo;
import com.cplatform.order.ActOrderPaymentInfo;
import com.cplatform.order.ActOrderServiceClient;
import com.cplatform.pay.PayOrderInfo;
import com.cplatform.pay.PayServiceClient;
import com.google.common.collect.Maps;

/**
 * Title. <br>
 * Description.
 * <p/>
 * Copyright: Copyright (c) 13-6-16 下午3:38
 * <p/>
 * Company: 北京宽连十方数字技术有限公司
 * <p/>
 * Author: nicky
 * <p/>
 * Version: 1.0
 * <p/>
 */
@Controller
@RequestMapping("/order")
public class OrderProcController {

	private final Logger logger = Logger.getLogger("business");

	@Autowired
	private ActOrderServiceClient actOrderClient;

	@Autowired
	private OrderService orderService;

	@Autowired
	private OrderService2 orderService2;

	@Autowired
	private PayServiceClient payServiceClient;

	@Autowired
	private UserSercvice userSercvice;

	@Autowired
	CartService cartService;

	@Autowired
	CartDataManager cartDataManager;

	@Autowired
	AppConfig appConfig;

	@Autowired
	JmsMessageService jmsMessageService;

	@Autowired
	private ThirdInterDao thirdInterDao;

	@Autowired
	private WelfareOrderController welfareOrderController;

	@Autowired
	private ShopService shopService;

	/**
	 * 订单确认支付
	 * 
	 * @param id
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("payment-confirm")
	public String payment(String id, HttpServletResponse response, Model model) throws Exception {

		SessionUser user = SessionUser.getSessionUser(response);
		Long orderId = NumberUtils.toLong(id, 0);
		ActOrderInfo actOrderInfo = actOrderClient.getActOrderInfo(orderId);

		String error = commonCheckOrder(actOrderInfo, response);
		if (error != null) {
			String[] infos = StringUtils.split(error, "||");
			model.addAttribute("title", infos[0]);
			model.addAttribute("information", infos[1]);
			return "/cart/payment-information";
		}

		if (actOrderInfo.getActType() == Constants.SMS_BUY) {
			ActOrderPaymentInfo paymentInfo = orderService.findOrderPaymentByOrderId(orderId);
			if (paymentInfo.getCurrency().trim().equalsIgnoreCase("score")) {
				model.addAttribute("tips", appConfig.getTipsOrderPay());
			}
		}

		OrderPayInfo payInfo = new OrderPayInfo();
		payInfo.setOrderId(orderId);
		payInfo.setPayAmounts(CommonUtils.toYuan(new BigDecimal(actOrderInfo.getTotalPayAmount())));

		// 添加用户手机号为空判断
		if (null != user && StringUtils.isNotBlank(user.getTerminalId())) {
			try {
				Map<String, String> score = userSercvice.queryExchangePoints(user.getTerminalId());
				if (score != null && !score.isEmpty()) {
					payInfo.setScore(new BigDecimal(score.get("score")));
				}
			}
			catch (Exception e) {
				payInfo.setScore(new BigDecimal(0));
				logger.error("积分获取异常");
			}
		} else {
			logger.info("用户没有绑定手机号");
			payInfo.setScore(new BigDecimal(0));
		}

		if (actOrderInfo.getOrderType() == Constants.ORDER_TYPE_LASHOU) {
			int lsPayAmounts = orderService2.getLashouTotalAmount(actOrderInfo);
			payInfo.setLsPayAmounts(CommonUtils.toYuan(new BigDecimal(lsPayAmounts)));
		}

		double userCoin = orderService.getMallCoin(0, response);
		payInfo.setCoin(CommonUtils.toYuan(new BigDecimal(userCoin)));
		double allowCoin = orderService.getAllowCoin();
		payInfo.setAllowCoin(CommonUtils.toYuan(new BigDecimal(allowCoin == -1 ? userCoin : allowCoin)));

		payInfo = orderService.getOrderPayType(payInfo, actOrderInfo);
		if ("1".equals(payInfo.getBillPay())) {
			CallBalance balance = thirdInterDao.getBalanceInfo(SessionUser.getSessionUser(response).getTerminalId());
			if (balance != null) {
				payInfo.setBalance(CommonUtils.toYuan(new BigDecimal(balance.getPayFee())));
				payInfo.setAllowBalance(CommonUtils.toYuan(new BigDecimal(balance.getLimitFee())));
			}
		}
		model.addAttribute("payInfo", payInfo);

		Map<String, Object> orderPayInfo = getPaymentInfoStatus(actOrderInfo);
		// 剩余支付项
		List<ActOrderPaymentInfo> leftPays = (List<ActOrderPaymentInfo>) orderPayInfo.get("leftPayInfos");

		// 订单支付信息
		List<ActOrderPaymentInfo> paymentInfos = (List<ActOrderPaymentInfo>) orderPayInfo.get("paymentInfos");

		// 已经支付项
		Map<String, Integer> payedInfo = (Map<String, Integer>) orderPayInfo.get("payedInfo");
		model.addAttribute("payedInfo", payedInfo);
		model.addAttribute("paymentInfos", paymentInfos);
		model.addAttribute("leftPays", leftPays);

		// 判断需要跳转的支付页面
		if (orderService.isFirstPay(actOrderInfo)) {

			logger.info("支付页面判断");

			/** 判断是否是江苏商户下的订单 */
			// 获取商户的省份编号
			String provinceCode = shopService.getProvinceCodeByStoreId(actOrderInfo.getShopId());

			logger.info("商户所在省的编号：" + provinceCode);

			// 江苏省处理
			if (StringUtils.isNotBlank(provinceCode)) {

				// 登录用户所属省份编号
				String userProvinceCode = "";
				if (null != user && StringUtils.isNotBlank(user.getTerminalId()) && StringUtils.isNotBlank(user.getAreaCode())) { // 判断用户信息
					logger.info("user.getAreaCode()：" + user.getAreaCode());
					userProvinceCode = shopService.getProvinceCodeByRegionCode(user.getAreaCode(), null);
				}
				logger.info("用户所在的省份编号：" + userProvinceCode);

				// 客户归属地和商户归属地作判断
				boolean flag = (provinceCode.equals(userProvinceCode)) ? true : false;
				// 判断是否是江苏下的客户
				if (provinceCode.equals(PaymentConfirmUtil.JS_CODE)) {
					if (flag) {
						// 江苏省本省用户
						logger.info("江苏省本省用户");
						if (StringUtils.isBlank(user.getTerminalId())) {
							logger.info("江苏省本省用户：手机号为空");
							return "cart/" + PaymentConfirmUtil.payment.get(PaymentConfirmUtil.JS_CODE + "_other");
						}
						return genReturnUrl(actOrderInfo, orderId, response, model);
					} else {
						// 江苏省外用户或互联网用户
						logger.info("江苏省外用户或互联网用户");
						return "cart/" + PaymentConfirmUtil.payment.get(PaymentConfirmUtil.JS_CODE + "_other");
					}
				} else if (provinceCode.equals(PaymentConfirmUtil.JL_CODE)) {// 判断是否是吉林省下的客户
					if (flag) {
						// 吉林省本省用户
						logger.info("吉林省本省用户");
						return "cart/" + PaymentConfirmUtil.payment.get(PaymentConfirmUtil.JL_CODE + "_" + PaymentConfirmUtil.JL_CODE);
					} else {
						// 吉林省外省用户或互联网用户
						logger.info("吉林省外省用户或互联网用户");
						return "cart/" + PaymentConfirmUtil.payment.get(PaymentConfirmUtil.JL_CODE + "_other");

					}
				} else if (provinceCode.equals(PaymentConfirmUtil.SX_CODE)) {// 判断是否是陕西省下的客户
					if (flag) {
						// 陕西省本省用户
						logger.info("陕西省本省用户");
						return "cart/" + PaymentConfirmUtil.payment.get(PaymentConfirmUtil.SX_CODE + "_" + PaymentConfirmUtil.SX_CODE);
					} else {
						// 陕西省外省用户或互联网用户
						logger.info("陕西省外省用户");
						return "cart/" + PaymentConfirmUtil.payment.get(PaymentConfirmUtil.SX_CODE + "_other");
					}
				}
			}
			logger.info("商户所属省份编号为空，进入默认支付页面");
			return "cart/" + PaymentConfirmUtil.payment.get("default_page");
		} else {
			if (actOrderInfo.getOrderType() == Constants.ORDER_TYPE_MTICKET) {
				logger.info("电影票继续支付");
				return "cart/continue-pay-movie";
			}
			return "cart/continue-pay";
		}
	}

	private Map<String, Object> getPaymentInfoStatus(ActOrderInfo order) throws Exception {
		Map<String, Object> infos = Maps.newHashMap();
		List<PayOrderInfo> payOrderInfos = payServiceClient.getPayOrderInfosByActOrderId(order.getId());
		List<ActOrderPaymentInfo> paymentInfos = order.getPaymentInfos();
		Map<String, Integer> payedInfo = orderService.getAllPayedInformation(payOrderInfos);
		List<ActOrderPaymentInfo> leftPayInfos = orderService.getLeftPayInfos(paymentInfos, payedInfo);
		infos.put("paymentInfos", paymentInfos);
		infos.put("payedInfo", payedInfo);
		infos.put("leftPayInfos", leftPayInfos);
		return infos;
	}

	private String commonCheckOrder(ActOrderInfo orderInfo, HttpServletResponse response) throws Exception {

		ActOrderInfo actOrderInfo = orderInfo;
		String alert = orderService.checkPayOrder(actOrderInfo, response);
		if (alert != null) {
			return alert;
		}

		List<PayOrderInfo> payOrderInfos = payServiceClient.getPayOrderInfosByActOrderId(actOrderInfo.getId());
		if (orderService.existRefundInfo(payOrderInfos)) {
			return "该订单在退款中或已退款，不能再次支付";
		}

		return null;
	}

	/**
	 * 江苏省，本省用户进行支付页面判断
	 * 
	 * @param actOrderInfo
	 *            订单信息
	 * @return
	 * @throws Exception
	 */
	private String genReturnUrl(ActOrderInfo actOrderInfo, Long orderId, HttpServletResponse response, Model model) throws Exception {
		if (actOrderInfo.getOrderType() == Constants.ORDER_TYPE_LASHOU) {
			return "cart/payment-confirm-ls";
		} else if (actOrderInfo.getOrderType() == Constants.ORDER_TYPE_INTEGRAL || actOrderInfo.getOrderType() == Constants.ORDER_TYPE_ECOUPON) {
			return "cart/payment-confirm-hn";
		} else if (actOrderInfo.getOrderType() == Constants.ORDER_TYPE_WELFARE) { // 劳保商品
			return welfareOrderController.payment(orderId, response, model);
		} else if (actOrderInfo.getOrderType() == Constants.ORDER_TYPE_MTICKET) { // 电影票
			return "cart/payment-confirm-movie";
		} else {
			return "cart/payment-confirm";
		}
	}

}
