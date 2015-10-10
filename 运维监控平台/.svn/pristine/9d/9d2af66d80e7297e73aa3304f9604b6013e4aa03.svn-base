package com.cplatform.mall.dc.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * T_DC_USER实体类 <br>
 * <p>
 * Copyright: Copyright (c) 2013-12-26 下午6:48:18
 * <p>
 * Company: 苏州宽连信息技术有限公司
 * <p>
 * 
 * @author zhuangxx@c-platform.com
 * @version 1.0.0
 */
@Entity
@Table(name = "T_DC_USER")
public class DcUser implements Serializable {

	private static final long serialVersionUID = -4361489806343978360L;

	/**
	 * 用户状态 － 正常
	 */
	public static final int STATUS_VALID = 1;

	/**
	 * 用户状态 －暂停
	 */
	public static final int STATUS_PAUSE = 2;

	/**
	 * 用户状态 － 删除
	 */
	public static final int STATUS_DEL = 3;

	/**
	 * 用户类型 － 超级管理员
	 */
	public static final int TYPE_ADMIN = 0;

	/**
	 * 用户类型-单位管理员
	 */
	public static final int TYPE_COMPANY_ADMIN = 1;

	/**
	 * 用户类型 － 普通用户
	 */
	public static final int TYPE_COMPANY_USER = 2;

	private Long id;

	private String code;

	private String name;

	private String pwd;

	private String terminalId;

	private String email;

	private int status;

	private String area;

	@SequenceGenerator(name = "seq_dc_user", sequenceName = "SEQ_DC_USER")
	@Id
	@GeneratedValue(generator = "seq_dc_user")
	@JsonProperty
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "USER_CODE")
	@Basic
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "USER_NAME")
	@Basic
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "USER_PWD")
	@Basic
	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	@Column(name = "TERMINAL_ID")
	@Basic
	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	@Column(name = "EMAIL")
	@Basic
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "STATUS")
	@Basic
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Column(name = "AREA")
	@Basic
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	/**
	 * 修改密码
	 */
	private String oldPass;

	private String confirmPass;

	private String newPass;

	@Transient
	public String getOldPass() {
		return oldPass;
	}

	@Transient
	public void setOldPass(String oldPass) {
		this.oldPass = oldPass;
	}

	@Transient
	public String getNewPass() {
		return newPass;
	}

	@Transient
	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}

	@Transient
	public String getConfirmPass() {
		return confirmPass;
	}

	@Transient
	public void setConfirmPass(String confirmPass) {
		this.confirmPass = confirmPass;
	}

	/**
	 * 角色
	 */
	private List<Long> roleId = new ArrayList<Long>();

	@Transient
	public List<Long> getRoleId() {
		return roleId;
	}

	@Transient
	public void setRoleId(List<Long> roleId) {
		this.roleId = roleId;
	}

}
