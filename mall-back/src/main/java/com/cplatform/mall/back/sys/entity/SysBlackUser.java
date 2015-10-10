package com.cplatform.mall.back.sys.entity;

// default package

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.cplatform.mall.back.entity.IdEntity;

/**
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-5-24 下午6:35:07
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: wuyn@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
@Entity
@Table(name = "T_SYS_BLACK_USER")
public class SysBlackUser extends IdEntity implements java.io.Serializable {

	// Fields
	private Integer id;

	private String terminalId;

	private Integer levTag;

	private Integer inTag;

	private String remark;

	private Long unitId;

	private Long updateUserId;

	private String updateTime;

	private String startTime;

	private String endTime;

	public static Map<Integer, String> levTagMap = new HashMap<Integer, String>();
	static {
		levTagMap.put(0, "平台级（任何业务都不能参加）");
		levTagMap.put(1, "不能接收群发信息");
		levTagMap.put(2, "广告黑名单");
		levTagMap.put(3, "本单位所有业务都不能参加");
	}

	public static Map<Integer, String> inTagMap = new HashMap<Integer, String>();
	static {
		inTagMap.put(0, "手工输入");
		inTagMap.put(1, "批量导入");
	}

	/** default constructor */
	public SysBlackUser() {
	}

	/** minimal constructor */
	public SysBlackUser(Integer id, String terminalId) {
		this.id = id;
		this.terminalId = terminalId;
	}

	/** full constructor */
	public SysBlackUser(Integer id, String terminalId, Integer levTag, Integer inTag, String remark, Long updateUserId, String updateTime) {
		this.id = id;
		this.terminalId = terminalId;
		this.levTag = levTag;
		this.inTag = inTag;
		this.remark = remark;
		this.updateUserId = updateUserId;
		this.updateTime = updateTime;
	}

	public SysBlackUser(String terminalId, Integer levTag, Integer inTag, String remark, Long updateUserId, String updateTime) {
		this.terminalId = terminalId;
		this.levTag = levTag;
		this.inTag = inTag;
		this.remark = remark;
		this.updateUserId = updateUserId;
		this.updateTime = updateTime;
	}

	@Column(name = "UNIT_ID", nullable = false, length = 40)
	public Long getUnitId() {
		return unitId;
	}

	public void setUnitId(Long unitId) {
		this.unitId = unitId;
	}

	@Column(name = "TERMINAL_ID", nullable = false, length = 21)
	public String getTerminalId() {
		return this.terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	@Column(name = "LEV_TAG", precision = 3, scale = 0)
	public Integer getLevTag() {
		return this.levTag;
	}

	public void setLevTag(Integer levTag) {
		this.levTag = levTag;
	}

	@Column(name = "IN_TAG", precision = 3, scale = 0)
	public Integer getInTag() {
		return this.inTag;
	}

	public void setInTag(Integer inTag) {
		this.inTag = inTag;
	}

	@Column(name = "REMARK", length = 50)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "UPDATE_USER_ID", precision = 9, scale = 0, nullable = true)
	public Long getUpdateUserId() {
		return this.updateUserId;
	}

	public void setUpdateUserId(Long updateUserId) {
		this.updateUserId = updateUserId;
	}

	@Column(name = "UPDATE_TIME", length = 14, nullable = true)
	public String getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	@Transient
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	@Transient
	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
}