package com.cplatform.mall.dc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * T_DC_LOGIN_LOG实体类
 * 
 * @author zhangdong
 * @since 2013-7-11
 */
@Entity
@Table(name = "T_DC_LOGIN_LOG")
public class DcLoginLog implements Serializable {
	private static final long serialVersionUID = -450004322408273710L;

	private Long id;
	private String userName;
	private Long userId;
	private String ip;
	private String loginTime;
	private String userCode;

	@SequenceGenerator(name = "seq_dc_login_log", sequenceName = "SEQ_DC_LOGIN_LOG")
	@Id
	@GeneratedValue(generator = "seq_dc_login_log")
	@JsonProperty
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	@Column(name = "user_code")
	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
}
