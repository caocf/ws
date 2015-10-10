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
 * Title.登录日志对象 <br>
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
@Table(name = "t_sys_login_log")
public class SysLoginInfo extends IdEntity implements Serializable {

	private String userName;

	private Long userId;

	private String ip;

	private String loginTime;

	private Integer successFlag;

	private Integer userType;

	@Column(name = "user_name")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "user_id")
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "ip")
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Column(name = "login_time")
	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	@Column(name = "success_flag")
	public Integer getSuccessFlag() {
		return successFlag;
	}

	public void setSuccessFlag(Integer successFlag) {
		this.successFlag = successFlag;
	}

	@Column(name = "user_type")
	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	// -----bus method-----

	/**
	 * 业务使用属性，不参与数据库存储
	 */
	private String startTime;

	private String endTime;

	private static Map<String, String> flagMap = new HashMap<String, String>();

	static {
		flagMap.put("0", "登录失败");
		flagMap.put("1", "登录成功");
	}

	/**
	 * 状态map
	 * 
	 * @return
	 */
	public static Map<String, String> getFlagMap() {
		return flagMap;
	}

	/**
	 * 获得状态对应的中文名称
	 * 
	 * @return
	 */
	@Transient
	public String getFlagName() {
		return flagMap.get(this.getSuccessFlag());
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

}
