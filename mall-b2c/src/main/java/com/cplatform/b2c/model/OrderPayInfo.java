package com.cplatform.b2c.model;

import java.math.BigDecimal;

/**
 * User: chenke Date: 13-11-28 Time: 上午10:42
 * 
 * @since 2014-1-6 下午4:10:41
 * @describe 添加hnScorePay 河南积分支付
 * @author zhangdong-ca@c-platform.com
 */

public class OrderPayInfo {

	// 订单ID
	private Long orderId;

	// 订单总价
	private BigDecimal payAmounts;

	// 订单(拉手)总价
	private BigDecimal lsPayAmounts;

	// 订单(电影票)总价
	private BigDecimal movieAmounts;

	/**
	 * 现金、商城币、积分、话费 支付 1，支持；0，不支持
	 */
	private String cashPay;

	private String coinPay;

	private String scorePay;

	private String billPay;

	private String hnScorePay; // 河南

	// 0，独立支付；1，组合支付
	private String payType;

	// 拥有商城币，可用商城币
	// (以下所有)单位：元
	private BigDecimal coin;

	private BigDecimal allowCoin;

	private BigDecimal score;

	// 需要的积分
	private BigDecimal payingScore;

	private BigDecimal balance;

	private BigDecimal allowBalance;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public BigDecimal getPayAmounts() {
		return payAmounts;
	}

	public void setPayAmounts(BigDecimal payAmounts) {
		this.payAmounts = payAmounts;
	}

	public BigDecimal getLsPayAmounts() {
		return lsPayAmounts;
	}

	public void setLsPayAmounts(BigDecimal lsPayAmounts) {
		this.lsPayAmounts = lsPayAmounts;
	}

	public BigDecimal getMovieAmounts() {
		return movieAmounts;
	}

	public void setMovieAmounts(BigDecimal movieAmounts) {
		this.movieAmounts = movieAmounts;
	}

	public String getCashPay() {
		return cashPay;
	}

	public void setCashPay(String cashPay) {
		this.cashPay = cashPay;
	}

	public String getCoinPay() {
		return coinPay;
	}

	public void setCoinPay(String coinPay) {
		this.coinPay = coinPay;
	}

	public String getScorePay() {
		return scorePay;
	}

	public void setScorePay(String scorePay) {
		this.scorePay = scorePay;
	}

	public String getBillPay() {
		return billPay;
	}

	public void setBillPay(String billPay) {
		this.billPay = billPay;
	}

	public String getHnScorePay() {
		return hnScorePay;
	}

	public void setHnScorePay(String hnScorePay) {
		this.hnScorePay = hnScorePay;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public BigDecimal getCoin() {
		return coin;
	}

	public void setCoin(BigDecimal coin) {
		this.coin = coin;
	}

	public BigDecimal getAllowCoin() {
		return allowCoin;
	}

	public void setAllowCoin(BigDecimal allowCoin) {
		this.allowCoin = allowCoin;
	}

	public BigDecimal getScore() {
		return score;
	}

	public void setScore(BigDecimal score) {
		this.score = score;
	}

	public BigDecimal getPayingScore() {
		return payingScore;
	}

	public void setPayingScore(BigDecimal payingScore) {
		this.payingScore = payingScore;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public BigDecimal getAllowBalance() {
		return allowBalance;
	}

	public void setAllowBalance(BigDecimal allowBalance) {
		this.allowBalance = allowBalance;
	}
}
