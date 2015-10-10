package com.cplatform.mall.back.item.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 商品支付方式扩展表 Version: 1.0
 */
@Table(name = "T_ITEM_SALE_PAYMENT")
@Entity
public class ItemSalePayment {

	@SequenceGenerator(name = "seq_item_payment", sequenceName = "SEQ_ITEM_PAYMENT")
	@Id
	@GeneratedValue(generator = "seq_item_payment")
	@JsonProperty
	private Long id;

	@Column(name = "ITEM_ID", precision = 8, scale = 0)
	private Long itemId;

	@Column(name = "PAY_TYPE", precision = 1, scale = 0)
	private Long payType;

	@Column(name = "CASH_PAY_RATIO", precision = 1, scale = 0)
	private Long cashPayRatio;

	@Column(name = "OTHER_PAY_RATIO", precision = 1, scale = 0)
	private Long otherPayRatio;

	@Column(name = "BILL_PAY", precision = 1, scale = 0)
	private Long billPay;
	
	@Column(name = "DELIVERY_PAY", precision = 1, scale = 0)
	private Long deliveryPay;

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

	public Long getDeliveryPay() {
		return deliveryPay;
	}

	public void setDeliveryPay(Long deliveryPay) {
		this.deliveryPay = deliveryPay;
	}
	
	

}
