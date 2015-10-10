package com.cplatform.mall.back.entity;

// default package

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 审核步骤记录表，涉及多次审核 Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-6-1 下午1:33:06
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: baibo@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
@Entity
@Table(name = "T_AUDIT_STEP")
public class AuditStep extends IdEntity implements java.io.Serializable {

	// Fields

	private Long bsId;

	private Long bsType;

	private Long status;

	private String remark;

	private Long auditUserId;

	private String updateTime;

	private String bsTabel;
	
	private String remarkL;

	

	public static Map<Long, String> statusMap = null;
	static {
		statusMap = new HashMap<Long, String>();
		statusMap.put(0l, "草稿,意见:");
		statusMap.put(1l, "待审核,意见:");
		statusMap.put(2l, "审核中,意见:");
		statusMap.put(3l, "审核通过,意见:");
		statusMap.put(4l, "审核驳回,意见:");
	}
	// Constructors

	@Column(name = "BS_ID", precision = 8, scale = 0)
	public Long getBsId() {
		return bsId;
	}

	public void setBsId(Long bsId) {
		this.bsId = bsId;
	}

	@Column(name = "BS_TYPE", precision = 1, scale = 0)
	public Long getBsType() {
		return bsType;
	}

	public void setBsType(Long bsType) {
		this.bsType = bsType;
	}

	@Column(name = "STATUS", precision = 1, scale = 0)
	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	@Column(name = "REMARK", length = 100)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "AUDIT_USER_ID", precision = 8, scale = 0)
	public Long getAuditUserId() {
		return auditUserId;
	}

	public void setAuditUserId(Long auditUserId) {
		this.auditUserId = auditUserId;
	}

	@Column(name = "BS_TABEL", length = 100)
	public String getBsTabel() {
		return bsTabel;
	}

	@Column(name = "UPDATE_TIME", length = 14)
	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public void setBsTabel(String bsTabel) {
		this.bsTabel = bsTabel;
	}
	@Transient
	public String getRemarkL() {
		return statusMap.get(this.status)+(this.remark==null?"无":this.remark);
	}
	
	public void RemarkL(String remarkL) {
		this.remarkL = remarkL;
	}

}