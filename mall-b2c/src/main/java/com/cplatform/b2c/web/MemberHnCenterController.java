package com.cplatform.b2c.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cplatform.b2c.model.PageInfo;
import com.cplatform.b2c.model.SessionUser;
import com.cplatform.b2c.model.TActOrder;
import com.cplatform.b2c.model.TOrderRefund;
import com.cplatform.b2c.service.MemberCenterService;
import com.cplatform.b2c.service.MemberHnCenterService;
import com.cplatform.b2c.util.AppConfig;
import com.cplatform.b2c.util.Constants;
import com.cplatform.csso.agent.http.SSOAgent;
import com.cplatform.order.ActOrderGoodsInfo;
import com.cplatform.order.ActOrderInfo;
import com.cplatform.order.ActOrderPaymentInfo;
import com.cplatform.order.ActOrderService;
import com.cplatform.pay.PayOrderInfo;
import com.cplatform.pay.PayServiceClient;
import com.cplatform.sso.lmsh.bean.LoginUserBean;
import com.cplatform.verifycode.Decrypt;

/**
 * 个人中心河南商品菜单. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2014-1-8 下午5:33:09
 * <p>
 * Company: 苏州宽连信息技术有限公司
 * <p>
 * 
 * @author zhangdong-ca@c-platform.com
 * @version 1.0.0
 */
@Controller
public class MemberHnCenterController {

	private final Logger logger = Logger.getLogger(MemberHnCenterController.class);

	@Autowired
	private MemberHnCenterService memberHnCenterService;

	@Autowired
	private AppConfig appConfig;

	@Autowired
	private MemberCenterService centerService;

	@Autowired
	private ActOrderService client;

	@Autowired
	private PayServiceClient payServiceClient;

	/**
	 * 河南用户 我的积分兑换商品
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/center/hn/orderManager")
	public String getHnOrders(HttpServletRequest request, HttpServletResponse response, Model model) {
		String curpage = request.getParameter("curpage");
		if (!StringUtils.isNotBlank(curpage)) {
			curpage = "1";
		}
		PageInfo pageInfo = new PageInfo();
		pageInfo.setCurPage(Integer.valueOf(curpage));
		String name = request.getParameter("key");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String status = request.getParameter("status");
		// 是否货到付款
		String isdelivery = request.getParameter("isdelivery");
		String flag = request.getParameter("flag");
		LoginUserBean userinfo = null;
	    userinfo = SessionUser.getSessionUser(response);
		String userId = userinfo.getId().toString();

		logger.info("河南积分：" + "name：" + name + "---startTime:" + startTime + "---endTime：" + endTime + "---status：" + status);

		// 获取当前用户的订单
		List<TActOrder> list = memberHnCenterService.getHnOrders(userId, name, pageInfo, startTime, endTime, status, flag, isdelivery,
		                                                         Long.valueOf(Constants.ORDER_TYPE_INTEGRAL));
		model.addAttribute("list", list);
		model.addAttribute("status", status);
		model.addAttribute("webRoot", appConfig.getWebRoot());
		model.addAttribute("unpay", memberHnCenterService.getHnOrdersByStatus(userId, "1", "0", Long.valueOf(Constants.ORDER_TYPE_INTEGRAL)));
		model.addAttribute("sent", memberHnCenterService.getHnOrdersByStatus(userId, "3", "", Long.valueOf(Constants.ORDER_TYPE_INTEGRAL)));
		model.addAttribute("delivery", memberHnCenterService.getHnOrdersByDelivery(userId, "1", Long.valueOf(Constants.ORDER_TYPE_INTEGRAL)));
		model.addAttribute("isdelivery", isdelivery);
		model.addAttribute("pageInfos",
		                   memberHnCenterService.getHnOrdersScript(userId, name, pageInfo, startTime, endTime, status, flag, isdelivery,
		                                                           Long.valueOf(Constants.ORDER_TYPE_INTEGRAL)));
		return "center/hn-orders";
	}

	/**
	 * 河南用户 我的积分兑换商品
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/center/couponManager")
	public String getHnOrdersCoupon(HttpServletRequest request, HttpServletResponse response, Model model) {
		String curpage = request.getParameter("curpage");
		if (!StringUtils.isNotBlank(curpage)) {
			curpage = "1";
		}
		PageInfo pageInfo = new PageInfo();
		pageInfo.setCurPage(Integer.valueOf(curpage));
		String name = request.getParameter("key");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String status = request.getParameter("status");
		// 是否货到付款
		String isdelivery = request.getParameter("isdelivery");
		String flag = request.getParameter("flag");
		LoginUserBean userinfo = null;
		userinfo = new SSOAgent(request, response).loginUserInfo("mall");
		String userId = userinfo.getId().toString();
		// 获取当前用户的订单
		List<TActOrder> list = memberHnCenterService.getHnOrders(userId, name, pageInfo, startTime, endTime, status, flag, isdelivery,
		                                                         Long.valueOf(Constants.ORDER_TYPE_ECOUPON));
		model.addAttribute("list", list);
		model.addAttribute("status", status);
		model.addAttribute("webRoot", appConfig.getWebRoot());
		model.addAttribute("unpay", memberHnCenterService.getHnOrdersByStatus(userId, "1", "0", Long.valueOf(Constants.ORDER_TYPE_ECOUPON)));
		model.addAttribute("sent", memberHnCenterService.getHnOrdersByStatus(userId, "3", "", Long.valueOf(Constants.ORDER_TYPE_ECOUPON)));
		model.addAttribute("delivery", memberHnCenterService.getHnOrdersByDelivery(userId, "1", Long.valueOf(Constants.ORDER_TYPE_ECOUPON)));
		model.addAttribute("isdelivery", isdelivery);
		model.addAttribute("pageInfos",
		                   memberHnCenterService.getHnOrdersScript(userId, name, pageInfo, startTime, endTime, status, flag, isdelivery,
		                                                           Long.valueOf(Constants.ORDER_TYPE_ECOUPON)));
		return "center/hn-coupon-orders";
	}

	/**
	 * 查看订单详情
	 * 
	 * @param request
	 * @param response
	 * @param orderId
	 *            订单编号
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/center/hnOrderView", method = RequestMethod.GET)
	public String viewOrder(HttpServletRequest request, HttpServletResponse response, @RequestParam("orderId") String orderId,
	        @RequestParam("actionType") String actionType, Model model) {
		ActOrderInfo info = new ActOrderInfo();
		List<PayOrderInfo> payOrderInfos = new ArrayList<PayOrderInfo>();
		String amount = "";
		String payment = "";
		String express = "";
		String isXuNi = "";
		int status = 1;
		List<Object[]> codes = new ArrayList<Object[]>();
		// Map<String, String> map = new HashMap<String, String>();
		try {
			LoginUserBean userinfo = null;
			userinfo = new SSOAgent(request, response).loginUserInfo("mall");
			info = client.getActOrderInfo(Long.valueOf(orderId));
			if (userinfo != null && info != null && userinfo.getId().equals(info.getUserId())) {

				logger.info("orderView=info=========================" + info);
				// 查询支付信息
				payOrderInfos = payServiceClient.getPayOrderInfosByActOrderId(Long.valueOf(orderId));
				logger.info("orderView=payOrderInfos=========================" + payOrderInfos);
				int goodAmount = 0;
				int discount = 0;
				StringBuffer ids = new StringBuffer();
				for (int i = 0; i < info.getGoodsInfos().size(); i++) {
					ActOrderGoodsInfo good = info.getGoodsInfos().get(i);
					goodAmount += good.getCount() * good.getPayPrice();
					discount += good.getDiscount();
					if (i > 0) {
						ids.append(",");
					}
					ids.append(good.getGoodsId());
				}
				express = centerService.getExpress(ids.toString());
				isXuNi = centerService.getIsXuNi(ids.toString());
				logger.info("orderView=isXuNi=========================" + isXuNi);
				amount = memberHnCenterService.getTotal(info, goodAmount, discount, isXuNi);
				status = centerService.getStatus(isXuNi, info);
				logger.info("orderView=status=========================" + status);
				int i = 0;
				for (ActOrderPaymentInfo pay : info.getPaymentInfos()) {
					if (i > 0) {
						payment += "+";
					}
					if (StringUtils.equals(pay.getCurrency(), "cash")) {
						payment += "现金";
					} else if (StringUtils.equals(pay.getCurrency(), "coin")) {
						payment += "商城币";
					} else if (StringUtils.equals(pay.getCurrency(), "score")) {
						payment += "积分";
					} else if (StringUtils.equals(pay.getCurrency(), "balance")) {
						payment += "话费";
					}
					// payment += StringUtils.equals(pay.getCurrency(), "cash")
					// ?
					// "现金" : "商城币";
				}
				// map = centerService.getPayInfo(info);
				// 拉手的code需要解密
				if (StringUtils.equals("1", isXuNi)) {
					codes = centerService.getVerifyCode(info.getId());
					if (codes != null && codes.size() > 0) {
						for (int j = 0; j < codes.size(); j++) {
							Object[] codeObj = codes.get(j);
							if (info.getOrderType() != null && info.getOrderType() == 20) {
								String num2pwd = Decrypt.decrypt(codeObj[1].toString());
								if (num2pwd.indexOf("@@@") > 0) {
									String[] str = num2pwd.split("@@@");
									codeObj[1] = str[0];
									codeObj[3] = str[1];
								}
							}
						}
					}
				}
				model.addAttribute("flag", 1);
			} else {
				model.addAttribute("flag", 0);
			}
		}
		catch (NumberFormatException e) {
			logger.error(e.getMessage());
		}
		catch (Exception e) {
			logger.error(e.getMessage());
		}
		model.addAttribute("info", info);
		if (null != info && null != info.getExpressInfo()) {
			model.addAttribute("number", info.getExpressInfo().getExpressCompanyName());
			model.addAttribute("name", info.getExpressInfo().getExpressNo());
		} else {
			model.addAttribute("number", "");
			model.addAttribute("name", "");
		}
		model.addAttribute("amount", amount.toString());
		model.addAttribute("payOrderInfos", payOrderInfos);
		model.addAttribute("payment", payment);
		model.addAttribute("express", express);
		// model.addAttribute("payCash", map.get("cash"));
		// model.addAttribute("payCoin", map.get("coin"));
		// model.addAttribute("payScore", map.get("score"));
		// model.addAttribute("payBalance", map.get("balance"));
		model.addAttribute("isXuNi", isXuNi);
		model.addAttribute("status", status);
		model.addAttribute("codes", codes);
		model.addAttribute("actionType", actionType);
		model.addAttribute("pageTitle", memberHnCenterService.getPageTitle(actionType));
		return "center/hn-view-order";
	}

	/**
	 * 查看河南退款单
	 * 
	 * @param request
	 * @param orderId
	 *            订单编号
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/center/hnRefund", method = RequestMethod.GET)
	public String getRefund(HttpServletRequest request, @RequestParam("orderId") String orderId, @RequestParam("actionType") String actionType,
	        Model model) {
		ActOrderInfo info = new ActOrderInfo();
		List<TOrderRefund> refunds = new ArrayList<TOrderRefund>();
		try {
			info = client.getActOrderInfo(Long.valueOf(orderId));
			refunds = centerService.getRefunds(orderId, info);
		}
		catch (NumberFormatException e) {
			logger.error(e.getMessage());
		}
		catch (Exception e) {
			logger.error(e.getMessage());
		}
		model.addAttribute("list", refunds);
		model.addAttribute("actionType", actionType);
		model.addAttribute("pageTitle", memberHnCenterService.getPageTitle(actionType));
		return "center/hn-refund";
	}
}
