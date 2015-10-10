package com.cplatform.mall.back.giftcard.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.cplatform.mall.back.entity.IdEntity;

/**
 * 激活任务扩展表映射 . <br>
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
@Table(name = "T_GIFT_CARD_TASK_EXT ")
public class GiftCardTaskExt extends IdEntity implements java.io.Serializable {

	private Long id;

	private Long taskId;

	private String serialNo;

	private Long status;
	
	private String createTime;
	
	private String actTime;
	
	private Long applyId;
	
	@Column(name = "APPLY_ID")
	public Long getApplyId() {
		return applyId;
	}

	public void setApplyId(Long appltId) {
		this.applyId = appltId;
	}

	@Column(name = "TASK_ID")
	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	@Column(name = "SERIAL_NO")
	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	@Column(name = "STATUS")
	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	@Column(name = "CREATE_TIME")
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Column(name = "ACT_TIME")
	public String getActTime() {
		return actTime;
	}

	public void setActTime(String actTime) {
		this.actTime = actTime;
	}
	
	
}
