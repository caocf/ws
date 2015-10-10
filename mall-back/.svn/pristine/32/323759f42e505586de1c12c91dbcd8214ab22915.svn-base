package com.cplatform.mall.back.sys.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.cplatform.mall.back.entity.IdEntity;

/**
 * Title.系统日志对象 <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-5-22 上午9:31:54
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: BaiJie
 * <p>
 * Version: 1.0
 * <p>
 */
@Entity
@Table(name = "t_sys_log")
public class SysLogInfo extends IdEntity implements Serializable {

	private String userId;

	private String userName;

	private String operTime;

	private String operType;

	private String module;

	private String description;

	private String ip;

	private String resultCode;

	private String userType;

	private Long opId;

	@Column(name = "user_id")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "user_name")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "oper_time")
	public String getOperTime() {
		return operTime;
	}

	public void setOperTime(String operTime) {
		this.operTime = operTime;
	}

	@Column(name = "oper_type")
	public String getOperType() {
		return operType;
	}

	public void setOperType(String operType) {
		this.operType = operType;
	}

	@Column(name = "module")
	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "ip")
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Column(name = "result_code")
	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	@Column(name = "user_type")
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	@Column(name = "op_id")
	public Long getOpId() {
		return opId;
	}

	public void setOpId(Long opId) {
		this.opId = opId;
	}

	public String toString() {
		StringBuilder str = new StringBuilder(200);
		str.append("[").append(this.getModule()).append("]:").append(this.getOperTypeName()).append("\t").append(this.getDescription())
		        .append("\t操作时间：").append(this.getOperTime()).append("\t操作人：").append(this.getUserName()).append("[").append(this.getUserId())
		        .append("]");
		return str.toString();
	}

	// ------------------bus method ----------
	/**
	 * 业务使用属性，不参与数据库存储
	 */
	private String startTime;

	private String endTime;

	// 操作类型
	private static Map<String, String> operTypeMap = new HashMap<String, String>();
	static {
		operTypeMap.put("1", "查看");
		operTypeMap.put("2", "添加");
		operTypeMap.put("3", "修改");
		operTypeMap.put("4", "删除");
		operTypeMap.put("5", "审核");
		operTypeMap.put("6", "其他");
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

	@Transient
	public String getOperTypeName() {
		return operTypeMap.get(this.getOperType());
	}

	@Transient
	public static Map<String, String> getOperTypeMap() {
		return operTypeMap;
	}

}
