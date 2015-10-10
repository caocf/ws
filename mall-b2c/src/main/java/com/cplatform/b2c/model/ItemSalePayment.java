package com.cplatform.b2c.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * User: cuikai Date: 13-10-24 Time: 上午10:28
 */

public class ItemSalePayment {

	private Long id;

	private Long itemId;

	private Long payType;

	private Long cashPayRatio;

	private Long otherPayRatio;

	private Long billPay;

	private Long hnScorePay; // 河南积分支付

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public Long getPayType() {
		return payType;
	}

	public void setPayType(Long payType) {
		this.payType = payType;
	}

	public Long getCashPayRatio() {
		return cashPayRatio;
	}

	public void setCashPayRatio(Long cashPayRatio) {
		this.cashPayRatio = cashPayRatio;
	}

	public Long getOtherPayRatio() {
		return otherPayRatio;
	}

	public void setOtherPayRatio(Long otherPayRatio) {
		this.otherPayRatio = otherPayRatio;
	}

	public Long getBillPay() {
		return billPay;
	}

	public void setBillPay(Long billPay) {
		this.billPay = billPay;
	}

	public Long getHnScorePay() {
		return hnScorePay;
	}

	public void setHnScorePay(Long hnScorePay) {
		this.hnScorePay = hnScorePay;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
