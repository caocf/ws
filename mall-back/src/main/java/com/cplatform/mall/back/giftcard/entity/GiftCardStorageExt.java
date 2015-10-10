package com.cplatform.mall.back.giftcard.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.cplatform.mall.back.entity.IdEntity;

/**
 * 礼品卡库存扩展表映射 . <br>
 * 用于存放数据库映射实体.
 * <p>
 * Copyright: Copyright (c) 2013-5-24 上午11:01:01
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author mudeng-ca@c-platform.com
 * @version 1.0.0
 */
@Entity
@Table(name = "T_GIFT_CARD_STORAGE_EXT ")
public class GiftCardStorageExt extends IdEntity implements java.io.Serializable {

	private Long id;

	private String serialNo;

	private Long batchNo;

	private Long storageStatus;
	
	private String operatedTime;
	
	private Long operatedUserId;
	
	private String username;
	
	private Long applyId;
	
	@Column(name = "APPLY_ID")
	public Long getApplyId() {
		return applyId;
	}

	public void setApplyId(Long appltId) {
		this.applyId = appltId;
	}

	@Transient
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	
	
	@Column(name = "SERIAL_NO")
	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	@Column(name = "BATCH_NO")
	public Long getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(Long batchNo) {
		this.batchNo = batchNo;
	}

	@Column(name = "STORAGE_STATUS")
	public Long getStorageStatus() {
		return storageStatus;
	}

	public void setStorageStatus(Long storageStatus) {
		this.storageStatus = storageStatus;
	}

	@Column(name = "OPERATED_TIME")
	public String getOperatedTime() {
		return operatedTime;
	}

	public void setOperatedTime(String operatedTime) {
		this.operatedTime = operatedTime;
	}

	@Column(name = "OPERATED_USER_ID")
	public Long getOperatedUserId() {
		return operatedUserId;
	}

	public void setOperatedUserId(Long operatedUserId) {
		this.operatedUserId = operatedUserId;
	}
	
}
