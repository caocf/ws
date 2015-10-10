package com.cplatform.b2c.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 客户端支付请求封装 User: cuikai Date: 13-9-2 Time: 下午12:08
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

	@JsonProperty("SCORE")
	private int score; // 积分

	@JsonProperty("COIN")
	private int coin; // 商城币

	@JsonProperty("BALANCE")
	private int balance; // 话费

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

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getCoin() {
		return coin;
	}

	public void setCoin(int coin) {
		this.coin = coin;
	}

	public String getReturnURL() {
		return returnURL;
	}

	public void setReturnURL(String returnURL) {
		this.returnURL = returnURL;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
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
