package com.cplatform.mall.back.sys.entity;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.cplatform.mall.back.entity.IdEntity;

/**
 * Title. <br>
 * Description.
 * <p/>
 * Copyright: Copyright (c) 13-5-21 下午2:15
 * <p/>
 * Company: 北京宽连十方数字技术有限公司
 * <p/>
 * Author: nicky
 * <p/>
 * Version: 1.0
 * <p/>
 */
@javax.persistence.Table(name = "T_SYS_USER")
@Entity
public class SysUser extends IdEntity {

	/* 用户状态 */

	/**
	 * 用户状态 － 有效
	 */
	public static final Long STATUS_VALID = 1L;

	/**
	 * 用户状态 － 无效
	 */
	public static final Long STATUS_INVALID = 2L;

	/**
	 * 用户状态 － 删除
	 */
	public static final Long STATUS_DELETED = 3L;

	/* 用户类型 */

	/**
	 * 用户类型 － 超级管理员
	 */
	public static final int USER_TYPE_MASTER = 0;

	private String userCode;

	@javax.persistence.Column(name = "USER_CODE")
	@Basic
	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	private String userPwd;

	@javax.persistence.Column(name = "USER_PWD")
	@Basic
	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	private String userName;

	@javax.persistence.Column(name = "USER_NAME")
	@Basic
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	private String terminalId;

	@javax.persistence.Column(name = "TERMINAL_ID")
	@Basic
	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	private String email;

	@javax.persistence.Column(name = "EMAIL")
	@Basic
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	private String remark;

	@javax.persistence.Column(name = "REMARK")
	@Basic
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	private String validTime;

	@javax.persistence.Column(name = "VALID_TIME")
	@Basic
	public String getValidTime() {
		return validTime;
	}

	public void setValidTime(String validTime) {
		this.validTime = validTime;
	}

	private String createTime;

	@javax.persistence.Column(name = "CREATE_TIME")
	@Basic
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	private String changePwdTime;

	@javax.persistence.Column(name = "CHANGE_PWD_TIME")
	@Basic
	public String getChangePwdTime() {
		return changePwdTime;
	}

	public void setChangePwdTime(String changePwdTime) {
		this.changePwdTime = changePwdTime;
	}

	private String updateTime;

	@javax.persistence.Column(name = "UPDATE_TIME")
	@Basic
	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	private Long updateUserId;

	@javax.persistence.Column(name = "UPDATE_USER_ID")
	@Basic
	public Long getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(Long updateUserId) {
		this.updateUserId = updateUserId;
	}

	private int lockStatus;

	@javax.persistence.Column(name = "LOCK_STATUS")
	@Basic
	public int getLockStatus() {
		return lockStatus;
	}

	public void setLockStatus(int lockStatus) {
		this.lockStatus = lockStatus;
	}

	private int status;

	@javax.persistence.Column(name = "STATUS")
	@Basic
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	private Integer flag;

	@javax.persistence.Column(name = "FLAG")
	@Basic
	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	private Long unitId;

	@javax.persistence.Column(name = "UNIT_ID")
	@Basic
	public Long getUnitId() {
		return unitId;
	}

	public void setUnitId(Long unitId) {
		this.unitId = unitId;
	}

	// -------------bus method ---------------------
	private String startTime;

	private String endTime;

	private String confirmPass;

	public static Map<Integer, String> userTypeMap = new HashMap<Integer, String>();
	static {
		userTypeMap.put(1, "单位管理员");
		userTypeMap.put(2, "普通用户");
		userTypeMap.put(3, "客服");
	}

	@Transient
	public String getStartTime() {
		return startTime;
	}

	@Transient
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	@Transient
	public String getEndTime() {
		return endTime;
	}

	@Transient
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	/**
	 * 获取用户类型对应的中文名称
	 * 
	 * @return
	 */
	@Transient
	public String getUserTypeName() {
		switch (this.getFlag()) {
			case 0:
				return "超级管理员";
			default:
				return userTypeMap.get(this.getFlag());
		}
	}

	/**
	 * 获取用户状态对应的中文名称
	 * 
	 * @return
	 */
	@Transient
	public String getStatusTypeName() {
		switch (this.getStatus()) {
			case 1:
				return "正常";
			case 2:
				return "暂停";
			default:
				return "删除";
		}
	}

	/**
	 * 获取用户锁定状态对应的中文名称
	 * 
	 * @return
	 */
	@Transient
	public String getLockStatusTypeName() {
		switch (this.getLockStatus()) {
			case 0:
				return "正常";

			default:
				return "已锁定";
		}
	}

	@Transient
	public String getConfirmPass() {
		return confirmPass;
	}

	@Transient
	public void setConfirmPass(String confirmPass) {
		this.confirmPass = confirmPass;
	}
}
