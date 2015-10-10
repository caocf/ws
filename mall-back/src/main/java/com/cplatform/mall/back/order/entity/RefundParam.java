package com.cplatform.mall.back.order.entity;


/**
 * @Title	退款接口请求参数对象
 * @Description
 * @Copyright: Copyright (c) 2013-8-23
 * @Company: 北京宽连十方数字技术有限公司
 * @Author chencheng
 * @Version: 1.0
 *
 */
public class RefundParam {
	private String currency;
	private double amount;
	private String payChannel;
	public String getCurrency() {
		return currency;
	}
	public double getAmount() {
		return amount;
	}
	public String getPayChannel() {
		return payChannel;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public void setPayChannel(String payChannel) {
		this.payChannel = payChannel;
	}
	

}
