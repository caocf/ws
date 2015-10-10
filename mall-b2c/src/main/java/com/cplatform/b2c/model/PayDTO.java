package com.cplatform.b2c.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 劳保红包商品支付封装类. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2014-1-28 上午9:17:43
 * <p>
 * Company: 苏州宽连信息技术有限公司
 * <p>
 * 
 * @author zhangdong-ca@c-platform.com
 * @version 1.0.0
 */
public class PayDTO {

	// 退款币种
	public static final String CURRENCY_CASH = "cash";

	public static final String CURRENCY_COIN = "coin";

	public static final String CURRENCY_SCORE = "score";

	public static final String CURRENCY_BALANCE = "balance";

	public static final String CURRENCY_REDPACK = "redpack";

	@JsonProperty("U_ID")
	private Long userId;

	@JsonProperty("ORDER_ID")
	private Long orderId;

	@JsonProperty("PAY_TYPE")
	private String payType; // 支付方式：wap or web

	@JsonProperty("RED_PACK")
	private int redpack; // 红包

	@JsonProperty("CASH")
	private int cash; // 现金

	public int getCash() {
		return cash;
	}

	public void setCash(int cash) {
		this.cash = cash;
	}

	public int getRedpack() {
		return redpack;
	}

	public void setRedpack(int redpack) {
		this.redpack = redpack;
	}

	/**
	 * 支付类型：现金，商城币...
	 * 
	 * @see com.cplatform.b2c.service.PayFormChoose
	 */
	@JsonProperty("PAY_FORM")
	private String payForm;

	@JsonProperty("CHANNEL")
	private String channel; // 现金支付渠道

	@JsonProperty("RETURN_URL")
	private String returnURL;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getReturnURL() {
		return returnURL;
	}

	public void setReturnURL(String returnURL) {
		this.returnURL = returnURL;
	}

	public String getPayForm() {
		return payForm;
	}

	public void setPayForm(String payForm) {
		this.payForm = payForm;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
