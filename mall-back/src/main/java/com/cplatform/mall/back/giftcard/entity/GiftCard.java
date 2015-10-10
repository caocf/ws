package com.cplatform.mall.back.giftcard.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * 礼品卡实体类
 * 
 * */

@Table(name = "T_GIFT_CARD")
@Entity
public class GiftCard {
	private String serialNo;
	private Long batchNo;
	private Long memberId;
	private Long exchangeStatus;
	private String exchangeTime;
	private Long status;
	private String receiveTime;
	private String createdTime;
	private Long storageStatus;
	private String paymentDay;
	
	@Id
	@Column(name="SERIAL_NO")
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	@Column(name="BATCH_NO")
	public Long getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(Long batchNo) {
		this.batchNo = batchNo;
	}
	@Column(name="MEMBER_ID")
	public Long getMemberId() {
		return memberId;
	}
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	@Column(name="EXCHANGE_STATUS")
	public Long getExchangeStatus() {
		return exchangeStatus;
	}
	public void setExchangeStatus(Long exchangeStatus) {
		this.exchangeStatus = exchangeStatus;
	}
	@Column(name="EXCHANGE_TIME")
	public String getExchangeTime() {
		return exchangeTime;
	}
	public void setExchangeTime(String exchangeTime) {
		this.exchangeTime = exchangeTime;
	}
	@Column(name="STATUS")
	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
	}
	@Column(name="RECEIVE_TIME")
	public String getReceiveTime() {
		return receiveTime;
	}
	public void setReceiveTime(String receiveTime) {
		this.receiveTime = receiveTime;
	}
	@Column(name="CREATED_TIME")
	public String getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}
	@Column(name="STORAGE_STATUS")
	public Long getStorageStatus() {
		return storageStatus;
	}
	public void setStorageStatus(Long storageStatus) {
		this.storageStatus = storageStatus;
	}
	@Column(name="PAYMENT_DAY")
	public String getPaymentDay() {
		return paymentDay;
	}
	public void setPaymentDay(String paymentDay) {
		this.paymentDay = paymentDay;
	}
	
	
}
