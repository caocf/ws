package com.cplatform.mall.back.giftcard.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.cplatform.mall.back.entity.IdEntity;

/**
 * 激活任务表映射 . <br>
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
@Table(name = "T_GIFT_CARD_TASK ")
public class GiftCardTask extends IdEntity implements java.io.Serializable {

	private Long id;

	private Long resources;

	private Long status;
	
	private Long batchNo;

	private String filePath;

	private String serialStartNo;

	private String serialEndNo;

	private String remark;

	private String createTime;

	@Column(name = "RESOURCES")
	public Long getResources() {
		return resources;
	}

	public void setResources(Long resources) {
		this.resources = resources;
	}

	@Column(name = "STATUS")
	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	@Column(name = "BATCH_NO")
	public Long getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(Long batchNo) {
		this.batchNo = batchNo;
	}

	@Column(name = "FILE_PATH")
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Column(name = "SERIAL_START_NO")
	public String getSerialStartNo() {
		return serialStartNo;
	}

	public void setSerialStartNo(String serialStartNo) {
		this.serialStartNo = serialStartNo;
	}

	@Column(name = "SERIAL_END_NO")
	public String getSerialEndNo() {
		return serialEndNo;
	}

	public void setSerialEndNo(String serialEndNo) {
		this.serialEndNo = serialEndNo;
	}

	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "CREATE_TIME")
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	
}
