package com.cplatform.b2c.web.order;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cplatform.b2c.dto.PayOrderResponse;
import com.cplatform.b2c.model.PayDTO;
import com.cplatform.b2c.model.SessionUser;
import com.cplatform.b2c.repository.ThirdInterDao;
import com.cplatform.b2c.service.OrderService2;
import com.cplatform.b2c.service.WelfareOrderService;
import com.cplatform.b2c.util.AppConfig;
import com.cplatform.b2c.util.CommonUtils;
import com.cplatform.b2c.util.Constants;
import com.cplatform.b2c.util.JsonRespWrapper;
import com.cplatform.order.ActOrderInfo;
import com.cplatform.order.ActOrderPaymentInfo;
import com.cplatform.order.ActOrderServiceClient;
import com.cplatform.order.ActOrderStatus;
import com.cplatform.pay.PayChannel;

/**
 * User: cuikai Date: 13-11-5 Time: 上午9:51
 */
/**
 * 劳保红包商品支付. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2014-1-22 上午11:35:24
 * <p>
 * Company: 苏州宽连信息技术有限公司
 * <p>
 * 
 * @author zhangdong-ca@c-platform.com
 * @version 1.0.0
 */
@Controller
@RequestMapping("/order/welfare")
public class WelfareOrderController {

	private final Logger logger = Logger.getLogger("WelfareOrderController");

	@Autowired
	private ActOrderServiceClient actOrderClient;

	@Autowired
	private WelfareOrderService welfareOrderService;

	@Autowired
	private OrderService2 orderService2;

	@Autowired
	private AppConfig appConfig;

	@Autowired
	private ThirdInterDao thirdInterDao;

	/**
	 * 劳保商品红包支付跳转
	 * 
	 * @param orderId
	 *            劳保商品
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("toPay")
	public String payment(@RequestParam(value = "orderId") Long orderId, HttpServletResponse response, Model model) throws Exception {

		SessionUser user = SessionUser.getSessionUser(response);

		ActOrderInfo orderInfo = actOrderClient.getActOrderInfo(orderId);

		if (!welfareOrderService.isValid(orderInfo, user.getId())) {
			return "error/404";
		}
		int totalPrice = orderInfo.getTotalPayAmount();

		int totalWelfare = 0;
		// 判断用户是否绑定手机号
		if (null != user && StringUtils.isNotBlank(user.getTerminalId())) {
			totalWelfare = welfareOrderService.getWelfareTotal(user.getTerminalId());
		}

		boolean needCash = false;
		int welfarePay = totalPrice;
		int cashPay = 0;

		if (totalWelfare < totalPrice) {
			// 需现金进行差额补支付
			needCash = true;
			welfarePay = totalWelfare;
			cashPay = totalPrice - welfarePay;
		}

		model.addAttribute("orderId", orderId);
		model.addAttribute("totalPrice", CommonUtils.toYuan(totalPrice));
		model.addAttribute("totalWelfare", CommonUtils.toYuan(totalWelfare));
		model.addAttribute("needCash", needCash);
		model.addAttribute("welfarePay", CommonUtils.toYuan(welfarePay));
		model.addAttribute("cashPay", CommonUtils.toYuan(cashPay));

		return "cart/welfare-new-pay";
	}

	@RequestMapping("pay-old")
	@ResponseBody
	public JsonRespWrapper pay(@RequestParam(value = "orderId") Long orderId, @RequestParam(value = "channel", required = false) String cashChannel,
	        HttpServletResponse response) throws Exception {

		SessionUser user = SessionUser.getSessionUser(response);
		ActOrderInfo orderInfo = actOrderClient.getActOrderInfo(orderId);

		if (!welfareOrderService.isValid(orderInfo, user.getId())) {
			return JsonRespWrapper.failure("你的订单不存在");
		}

		if (orderInfo.getPayStatus() == ActOrderStatus.PAY_STATUS_UNPAID) {

			List<ActOrderPaymentInfo> paymentInfos = welfareOrderService.buildOrderPayment(orderInfo.getTotalPayAmount());
			try {
				welfareOrderService.bindingPaymentToOrder(orderId, paymentInfos);
			}
			catch (Exception e) {
				return JsonRespWrapper.failure("支付信息更新失败,请重试");
			}
			try {
				welfareOrderService.updateOrderToPaying(orderId);
			}
			catch (Exception e) {
				return JsonRespWrapper.failure("订单状态更新失败，请重试");
			}
		}
		int totalWelfare = 0;
		// 判断用户是否绑定手机号
		if (null != user && StringUtils.isNotBlank(user.getTerminalId())) {
			totalWelfare = welfareOrderService.getWelfareTotal(user.getTerminalId());
		}

		int totalPay = orderInfo.getTotalPayAmount();

		if (totalPay <= totalWelfare) {
			return JsonRespWrapper.success("type", "success").json("orderId", orderId);

		} else {
			int buyCount = (totalPay - totalWelfare) / 1; // 单价为1的商品
			Long welfareOrderId = welfareOrderService.buildWelfareOrder(user.getId(), buyCount);

			PayOrderResponse cashPayResp = welfareOrderService.cashPay(user.getId(), welfareOrderId, cashChannel);

			if (String.valueOf(Constants.STATUS_OK).equals(cashPayResp.getFlag())) {
				String url = cashPayResp.getRedirectUrl();
				if (StringUtils.isNotEmpty(url)) {
					return JsonRespWrapper.success("type", "redirect").json("url", url);
				}
				return JsonRespWrapper.success("type", "form").json("action", cashPayResp.getFormActionUrl()).json("input", cashPayResp.getHtml());
			}

			return JsonRespWrapper.failure(cashPayResp.getMsg());
		}
	}

	/**
	 * 劳保红包、现金组合支付
	 * 
	 * @param orderId
	 *            订单编号
	 * @param pack
	 *            红包
	 * @param cash
	 *            现金
	 * @param cashPayType
	 *            现金支付方式
	 * @param payForm
	 *            组合支付方式
	 * @param httpServletResponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("pay")
	@ResponseBody
	public Object pay(@RequestParam(value = "id") Long orderId, @RequestParam(value = "redPack", defaultValue = "0") String pack,
	        @RequestParam(value = "cash", defaultValue = "0") String cash, @RequestParam(value = "payType", defaultValue = "") String cashPayType,
	        @RequestParam(value = "payForm", defaultValue = "") String payForm, HttpServletResponse httpServletResponse) throws Exception {

		int redPack = Math.abs(CommonUtils.toFen(pack));
		int cashAmount = Math.abs(CommonUtils.toFen(cash));

		SessionUser user = SessionUser.getSessionUser(httpServletResponse);

		// 查询订单信息
		ActOrderInfo actOrderInfo = orderService2.getOrderInfo(orderId);
		logger.info("查询订单信息" + orderId + ":" + actOrderInfo);
		if (actOrderInfo == null) {
			return JsonRespWrapper.failure("订单不存在");
		}
		PayDTO payDTO = new PayDTO();
		payDTO.setReturnURL(appConfig.getServer_host() + appConfig.getWebRoot() + "order/payment-success.chtml");
		payDTO.setPayType("web");
		if (cashPayType.equals("cmpay")) {
			payDTO.setChannel(PayChannel.UNIFY_CART_APPLY_CMPAY);
		} else if (cashPayType.equals("alipay")) {
			payDTO.setChannel(PayChannel.UNIFY_CART_APPLY_ALIPAY);
		} else if (PayDTO.CURRENCY_REDPACK.equals(cashPayType)) {
			payDTO.setChannel(PayChannel.RED_PACKAGE);
		}
		payDTO.setPayForm(payForm);
		payDTO.setRedpack(redPack);
		payDTO.setOrderId(orderId);
		payDTO.setUserId(user.getId());
		payDTO.setCash(cashAmount);

		PayOrderResponse payOrderResponse = orderService2.payWelfareOrder(payDTO);

		if (payOrderResponse != null && String.valueOf(Constants.STATUS_OK).equals(payOrderResponse.getFlag())) {
			// thirdInterDao.notifyWhenWelfarePayed(payDTO);
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
		return JsonRespWrapper.failure(payOrderResponse.getMsg());
	}

	@RequestMapping("payment-success")
	public String paySuccess() {
		return "cart/welpay-success";
	}
}
