//package com.cplatform.mall.back.order.service;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Comparator;
//import java.util.List;
//
//import javax.annotation.Resource;
//
//import org.apache.commons.lang.RandomStringUtils;
//import org.apache.commons.lang.StringUtils;
//import org.apache.commons.lang.math.RandomUtils;
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import com.cplatform.order.ActOrderGoodsInfo;
//import com.cplatform.order.ActOrderInfo;
//import com.cplatform.order.ActOrderPaymentInfo;
//import com.cplatform.order.ActOrderService;
//import com.cplatform.order.ActOrderStatus;
//import com.cplatform.pay.CommonRequest;
//import com.cplatform.pay.CommonResponse;
//import com.cplatform.pay.PayMode;
//import com.cplatform.pay.PayServiceClient;
//import com.cplatform.pay.PaymentInfo;
//import com.cplatform.pay.RequestOperate;
//import com.cplatform.pay.unify_pay.info.ProductionInfo;
//import com.cplatform.pay.unify_pay.info.UnifyPayRequest;
//import com.cplatform.pay.unify_pay.info.UnifyPayResponse;
//import com.cplatform.pay.unify_pay.info.UnifyPayType;
//
//@Controller
//@RequestMapping("/pay")
//public class PayService extends AbstractService<PayRequest, PayResponse> {
//
//	@Autowired
//	private ActOrderService actOrderService;
//
//	/** dataService */
//	@Autowired
//	private ActDataServiceClient dataService;
//
//	@Autowired
//	private EventClient eventClient;
//
//	/** 日志记录器 */
//	private Logger logger = Logger.getLogger(getClass());
//
//	@Autowired
//	private PayServiceClient payService;
//
//	/** sysConfig */
//	@Resource(name = "sysConfig")
//	private PropertiesConfiguration sysConfig;
//
//	@Autowired
//	private UserTerminalCache userTerminalCache;
//
//	private CommonRequest createCommonRequest(PayRequest request, ActOrderInfo actOrderInfo, ActOrderPaymentInfo paymentInfo) {
//		String payWay = request.getPayChannel();
//		String payChannel = sysConfig.getString("pay." + payWay + ".channel");
//		boolean isWebPay = sysConfig.getBoolean("pay." + payWay + ".web", false);
//
//		long userId = actOrderInfo.getUserId();
//		long shopId = actOrderInfo.getShopId();
//		long actOrderId = actOrderInfo.getId();
//		String paymentCurrency = paymentInfo.getCurrency();
//		int paymentAmount = paymentInfo.getAmount();
//		String remark = "";
//
//		CommonRequest commonRequest = new CommonRequest();
//		// 填写支付请求中通用部分
//		{
//			commonRequest.setActOrderId(actOrderId);
//			commonRequest.setOperate(RequestOperate.Refund);
//			commonRequest.setPayChannel(payChannel);
//			commonRequest.setPaymentAmount(paymentAmount);
//			PaymentInfo info = new PaymentInfo();
//			info.setCurrency(paymentCurrency);
//			info.setAmount(paymentAmount);
//			commonRequest.addPaymentInfo(info);
//			commonRequest.setPayMode(PayMode.Background);
//
//			commonRequest.setRemark(remark);
//			commonRequest.setShopId(shopId);
//			commonRequest.setUserId(userId);
//		}
//		//
//		return commonRequest;
//	}
//	
//
//	private UnifyPayResponse paySubmitUnify(PayRequest request, ActOrderInfo actOrderInfo, ActOrderPaymentInfo paymentInfo) throws Exception {
//		//
//		CommonRequest commonRequest = createCommonRequest(request, actOrderInfo, paymentInfo);
//		//
//		if (StringUtils.isBlank(commonRequest.getPayChannel())) {
//			String text = "没有配置对应的支付渠道";
//			logger.info(text);
//			UnifyPayResponse response = new UnifyPayResponse();
//			response.setStatusCode(-1);
//			response.setStatusText(text);
//			return response;
//		}
//		//
//		long shopId = actOrderInfo.getShopId();
//		GetShopInfoRequest sRequest = new GetShopInfoRequest();
//		sRequest.setShopId(shopId);
//		GetShopInfoResponse sResponse = dataService.getShopInfo(sRequest);
//
//		// 创建统一支付请求
//		UnifyPayRequest unifyPayRequest = new UnifyPayRequest();
//		// 设置支付方式和渠道
//		String payChannel = request.getPayChannel();
//		if ("cmpay".equals(payChannel)) {
//			unifyPayRequest.setUnifyPayType(UnifyPayType.CART_PAY_APPLY);
//			unifyPayRequest.setPayChannel("1");
//		} else if ("alipay".equals(payChannel)) {
//			unifyPayRequest.setUnifyPayType(UnifyPayType.CART_PAY_APPLY);
//			unifyPayRequest.setPayChannel("2");
//		} else if ("scorepay".equals(payChannel)) {
//			unifyPayRequest.setUnifyPayType(UnifyPayType.CART_PAY_SCORE);
//			unifyPayRequest.setPayChannel("0");
//		} else if ("coinpay".equals(payChannel)) {
//			unifyPayRequest.setUnifyPayType(UnifyPayType.CART_PAY_COIN);
//			unifyPayRequest.setPayChannel("0");
//		}
//		// 填写商品信息
//		List<ProductionInfo> productionInfoList = new ArrayList<ProductionInfo>();
//		boolean isVerifyGoods = false;
//		for (ActOrderGoodsInfo goodsInfo : actOrderInfo.getGoodsInfos()) {
//			GetGoodsInfoRequest gRequest = new GetGoodsInfoRequest();
//			gRequest.setGoodsId(goodsInfo.getGoodsId());
//			GetGoodsInfoResponse gResponse = dataService.getGoodsInfo(gRequest);
//			//
//			if (isVerifyGoods == false) {
//				if (gResponse.getVerifyCodeBase() != 0) {
//					isVerifyGoods = true;
//				}
//			}
//			ProductionInfo productionInfo = new ProductionInfo();
//			productionInfo.setMerchId(sResponse.getGySettleId());
//			productionInfo.setBusinesId(String.valueOf(actOrderInfo.getShopId()));
//			productionInfo.setProductionId(String.valueOf(goodsInfo.getGoodsId()));
//			productionInfo.setProductionType(String.valueOf(gResponse.getTypeId()));
//			productionInfo.setPrice(String.valueOf(goodsInfo.getPayPrice()));
//			productionInfo.setQuantity(String.valueOf(goodsInfo.getCount()));
//			productionInfo.setSettlementPrice(String.valueOf(gResponse.getSettlePrice()).replace(".", ""));
//			productionInfo.setAssignedCity("025");
//			productionInfo.setDiscount(String.valueOf(goodsInfo.getDiscount()));
//			productionInfo.setTitle(gResponse.getName());
//			productionInfo.setProductionDesc(goodsInfo.getGoodsDescription());
//			//
//			productionInfoList.add(productionInfo);
//		}
//		unifyPayRequest.setProductionInfos(productionInfoList.toArray(new ProductionInfo[0]));
//		//
//		unifyPayRequest.setMobile(userTerminalCache.getTerminalIdByUserId(actOrderInfo.getUserId()));
//		// 拆分价格
//		splitMoney(commonRequest, unifyPayRequest, actOrderInfo, paymentInfo);
//		// 发送
//		String responseText = payService.submit(commonRequest, unifyPayRequest);
//		UnifyPayResponse payResponse = PayServiceClient.jsonToBean(responseText, UnifyPayResponse.class);
//		//
//		return payResponse;
//	}
//
//
//	private void splitMoney(//
//	        CommonRequest commonRequest, //
//	        UnifyPayRequest unifyPayRequest, //
//	        ActOrderInfo actOrderInfo, //
//	        ActOrderPaymentInfo paymentInfo) throws Exception {
//		// 计算分割比例
//		List<Integer> scales = new ArrayList<Integer>();
//		List<ActOrderPaymentInfo> paymentInfos = actOrderInfo.getPaymentInfos();
//		Collections.sort(paymentInfos, new Comparator<ActOrderPaymentInfo>() {
//
//			@Override
//			public int compare(ActOrderPaymentInfo o1, ActOrderPaymentInfo o2) {
//				Long v1 = o1.getId();
//				Long v2 = o2.getId();
//				return v1.compareTo(v2);
//			}
//		});
//		int index = 0;
//		for (int i = 0; i < paymentInfos.size(); i++) {
//			ActOrderPaymentInfo actOrderPaymentInfo = paymentInfos.get(i);
//			int amount = actOrderPaymentInfo.getAmount();
//			scales.add(amount);
//			if (actOrderPaymentInfo.getId() == paymentInfo.getId()) {
//				index = i;
//			}
//		}
//		// 开始分割金额
//		String vStr;
//		for (ProductionInfo productionInfo : unifyPayRequest.getProductionInfos()) {
//			// 商品单价
//			vStr = productionInfo.getPrice();
//			vStr = splitMoney(vStr, scales, index);
//			productionInfo.setPrice(vStr);
//			// 商品折扣
//			vStr = productionInfo.getDiscount();
//			vStr = splitMoney(vStr, scales, index);
//			productionInfo.setDiscount(vStr);
//			// 商品结算单价
//			vStr = productionInfo.getSettlementPrice();
//			vStr = splitMoney(vStr, scales, index);
//			productionInfo.setSettlementPrice(vStr);
//		}
//		// 运费
//		vStr = unifyPayRequest.getLogisticsFee();
//		vStr = splitMoney(vStr, scales, index);
//		unifyPayRequest.setLogisticsFee(vStr);
//	}
//
//	private int splitMoney(int value, List<Integer> scales, int index) throws Exception {
//		int remain = value;
//		int splitValue = -1;
//		for (int i = 0; i <= index; i++) {
//			int scale = scales.get(i);
//			int totalScale = 0;
//			for (int j = i; j < scales.size(); j++) {
//				totalScale += scales.get(j);
//			}
//			splitValue = (int) Math.round((double) remain * (double) scale / totalScale);
//			remain -= splitValue;
//		}
//		if (splitValue >= 0) {
//			return splitValue;
//		} else {
//			throw new Exception("支付金额计算异常");
//		}
//	}
//
//	private String splitMoney(String value, List<Integer> scales, int index) throws Exception {
//		int v = Integer.parseInt(value);
//		v = splitMoney(v, scales, index);
//		return String.valueOf(v);
//	}
//}
