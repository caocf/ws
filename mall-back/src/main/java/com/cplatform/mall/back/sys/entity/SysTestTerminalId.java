package com.cplatform.mall.back.sys.entity;

// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.cplatform.mall.back.entity.IdEntity;

/**
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-5-21 下午6:34:15
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: BaiJie
 * <p>
 * Version: 1.0
 * <p>
 */
@Entity
@Table(name = "T_SYS_TEST_TERMINAL_ID")
public class SysTestTerminalId extends IdEntity implements java.io.Serializable {

	// Fields

	private Integer id;

	private String terminalId;

	private String ownerName;

	private Short testType;

	private Integer actId;

	private String unitId;

	private Short status;

	private String remark;

	private Integer updateUserId;

	private String updateTime;

	// Constructors

	/** default constructor */
	public SysTestTerminalId() {
	}

	/** minimal constructor */
	public SysTestTerminalId(Integer id, String terminalId, Short testType) {
		this.id = id;
		this.terminalId = terminalId;
		this.testType = testType;
	}

	/** full constructor */
	public SysTestTerminalId(Integer id, String terminalId, String ownerName, Short testType, Integer actId, String unitId, Short status,
	                         String remark, Integer updateUserId, String updateTime) {
		this.id = id;
		this.terminalId = terminalId;
		this.ownerName = ownerName;
		this.testType = testType;
		this.actId = actId;
		this.unitId = unitId;
		this.status = status;
		this.remark = remark;
		this.updateUserId = updateUserId;
		this.updateTime = updateTime;
	}

	// Property accessors
	@Column(name = "TERMINAL_ID", nullable = false, length = 21)
	public String getTerminalId() {
		return this.terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	@Column(name = "OWNER_NAME", length = 40)
	public String getOwnerName() {
		return this.ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	@Column(name = "TEST_TYPE", nullable = false, precision = 3, scale = 0)
	public Short getTestType() {
		return this.testType;
	}

	public void setTestType(Short testType) {
		this.testType = testType;
	}

	@Column(name = "ACT_ID", precision = 9, scale = 0)
	public Integer getActId() {
		return this.actId;
	}

	public void setActId(Integer actId) {
		this.actId = actId;
	}

	@Column(name = "UNIT_ID", length = 20)
	public String getUnitId() {
		return this.unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	@Column(name = "STATUS", precision = 3, scale = 0)
	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	@Column(name = "REMARK", length = 200)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "UPDATE_USER_ID", precision = 9, scale = 0)
	public Integer getUpdateUserId() {
		return this.updateUserId;
	}

	public void setUpdateUserId(Integer updateUserId) {
		this.updateUserId = updateUserId;
	}

	@Column(name = "UPDATE_TIME", length = 14)
	public String getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

}