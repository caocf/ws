package com.cplatform.b2c.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cplatform.b2c.dto.CreateOrderDTO;
import com.cplatform.b2c.dto.CreateOrderResponse;
import com.cplatform.b2c.dto.PayDTO;
import com.cplatform.b2c.dto.PayOrderResponse;
import com.cplatform.b2c.dto.QueryWelfareNewResp;
import com.cplatform.b2c.dto.QueryWelfareResp;
import com.cplatform.b2c.util.AppConfig;
import com.cplatform.b2c.util.Constants;
import com.cplatform.b2c.util.HttpClientUtils;
import com.cplatform.b2c.util.JsonMapper;
import com.cplatform.order.ActOrderInfo;
import com.cplatform.order.ActOrderPaymentInfo;
import com.cplatform.order.ActOrderServiceClient;
import com.cplatform.order.ActOrderStatus;
import com.cplatform.order.CommonResponse;
import com.cplatform.pay.PayChannel;
import com.google.common.collect.Lists;

/**
 * User: cuikai Date: 13-11-5 Time: 上午10:13
 */
@Component
public class WelfareOrderService {

	private static final Logger logger = Logger.getLogger("business");

	private static final String RED_ENVELOPES = "red.envelopes";

	@Autowired
	private AppConfig appConfig;

	@Autowired
	private JmsMessageService jmsMessageService;

	@Autowired
	private ActOrderServiceClient actOrderClient;

	private Long createOrderRequest(CreateOrderDTO orderDTO) throws Exception {

		JsonMapper mapper = JsonMapper.buildNormalMapper();
		String queryString = mapper.toJson(orderDTO);

		String resp = HttpClientUtils.httpPost(appConfig.getCreateOrderUri(), queryString);

		CreateOrderResponse orderResponse = mapper.fromJson(resp, CreateOrderResponse.class);

		if (!orderResponse.getFlag().equals("0")) { // error
			throw new Exception("创建订单失败:" + orderResponse.getMsg());
		}
		return orderResponse.getOrderId();
	}

	private PayOrderResponse payRequest(PayDTO payDTO) throws Exception {

		JsonMapper mapper = JsonMapper.buildNormalMapper();
		String queryString = mapper.toJson(payDTO);

		String resp = HttpClientUtils.httpPost(appConfig.getPayOrderUri(), queryString);

		PayOrderResponse payResponse = mapper.fromJson(resp, PayOrderResponse.class);
		return payResponse;
	}

	public List<ActOrderPaymentInfo> buildOrderPayment(int totalAmount) {
		List<ActOrderPaymentInfo> paymentInfos = Lists.newArrayList();

		ActOrderPaymentInfo paymentInfo = new ActOrderPaymentInfo();
		paymentInfo.setChannal(PayChannel.RED_PACKAGE);
		paymentInfo.setCurrency(RED_ENVELOPES);
		paymentInfo.setAmount(totalAmount);
		paymentInfos.add(paymentInfo);
		return paymentInfos;
	}

	public ActOrderInfo bindingPaymentToOrder(Long orderId, List<ActOrderPaymentInfo> paymentInfos) throws Exception {

		// 更新支付项目，接口不支持一次写入订单

		CommonResponse response = actOrderClient.updatePaymentInfo(orderId, paymentInfos);
		if (response.getStatusCode() != Constants.STATUS_OK) {
			logger.error("更新支付项目出错:" + response.getStatusCode() + "," + response.getStatusText());
			throw new Exception("更新支付项目出错");
		}

		return actOrderClient.getActOrderInfo(orderId);
	}

	public void updateOrderToPaying(Long orderId) throws Exception {

		CommonResponse response = actOrderClient.updateStatus(orderId, ActOrderStatus.STATUS_TYPE_PAY, ActOrderStatus.PAY_STATUS_PAYING, "正在支付中",
		                                                      "{'method':'updateOrderToPaying'}");
		if (response.getStatusCode() != CommonResponse.STATUS_OK) {
			logger.error("更新订单状态失败:" + response.getStatusCode() + "," + response.getStatusText());
			throw new Exception("更新订单状态失败");
		}

	}

	public QueryWelfareResp queryWelfareRequest(String terminalId) throws Exception {

		JsonMapper mapper = JsonMapper.buildNormalMapper();
		String merchid = appConfig.getRedPackageMerchid();// "888002600018099";
		// String queryString = "{\"mobile\":\"" + terminalId +
		// "\",\"merchid\":\"" + merchid + "\"}";
		String queryString = "mobile=" + terminalId + "&merchid=" + merchid;
		logger.info("调用红包余额查询接口开始：" + appConfig.getWelfareQueryUri() + "?" + queryString);
		String respJson = HttpClientUtils.httpGet(appConfig.getWelfareQueryUri(), queryString);
		QueryWelfareNewResp newResp = mapper.fromJson(respJson, QueryWelfareNewResp.class);
		logger.info("调用红包余额查询接口结束：" + newResp.toString());

		QueryWelfareResp resp = new QueryWelfareResp();
		if (newResp != null && newResp.getBonus() != null && newResp.getBonus().size() > 0) {
			List<QueryWelfareNewResp.Bonu> bonus = newResp.getBonus();
			for (QueryWelfareNewResp.Bonu bonu : bonus) {
				if (appConfig.getRedPackageRepsonseType().equals(bonu.getType())) {
					resp.setAmt(bonu.getAmt());
					resp.setType(bonu.getType());
					break;
				}
			}
		}

		resp.setPayOrderId(newResp.getPayOrderId());
		resp.setPayOrderRecordId(newResp.getPayOrderRecordId());
		resp.setStatusCode(newResp.getStatusCode());
		resp.setStatusText(newResp.getStatusText());

		return resp;
	}

	public int getWelfareTotal(String terminalId) throws Exception {
		try {
			QueryWelfareResp welfareResp = this.queryWelfareRequest(terminalId);
			if (welfareResp.getStatusCode() == 0) {
				return welfareResp.getAmt();
			}
		}
		catch (Exception e) {
			String mobiles = appConfig.getWelfareAdminMobile();
			String[] mobile = StringUtils.split(mobiles, ",");
			for (int i = 0; i < mobile.length; i++) {
				jmsMessageService.sendSms("用户[" + terminalId + "]红包金额查询异常", mobile[i]);
			}
			logger.error(e.getMessage(), e);
		}
		return 0;
	}

	public boolean isValid(ActOrderInfo orderInfo, Long userId) {
		if (orderInfo == null || !(userId.equals(orderInfo.getUserId()))) {
			return false;
		}
		return true;
	}

	public Long buildWelfareOrder(Long userId, int buyCount) throws Exception {

		CreateOrderDTO.Good good = new CreateOrderDTO.Good();
		CreateOrderDTO orderDTO = new CreateOrderDTO();

		good.setCount(buyCount);
		good.setId(appConfig.getWelfareGoodsId());

		orderDTO.setUserId(userId);
		orderDTO.setGoods(Lists.newArrayList(good));
		orderDTO.setSubject("红包差额补订单");
		orderDTO.setCreateSource(1L);

		return this.createOrderRequest(orderDTO);
	}

	public PayOrderResponse cashPay(Long userId, Long orderId, String channel) throws Exception {
		// if (StringUtils.equals(channel, "alipay")) {
		// channel = PayChannel.UNIFY_CART_APPLY_ALIPAY;
		// }
		if (StringUtils.equals(channel, "sjzf")) {
			channel = PayChannel.UNIFY_CART_APPLY_CMPAY;
		} else {
			channel = PayChannel.UNIFY_CART_APPLY_ALIPAY;
		}
		PayDTO payDTO = new PayDTO();
		String returnUrl = appConfig.getServer_host() + appConfig.getWebRoot() + "order/welfare/payment-success.chtml";
		payDTO.setUserId(userId);
		payDTO.setOrderId(orderId);
		payDTO.setChannel(channel);
		payDTO.setPayForm("only_cash");
		payDTO.setReturnURL(returnUrl);

		return this.payRequest(payDTO);
	}
}
