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
 * T_DC_OPERATE_LOG实体类
 * 
 * @author zhuangxx@c-platform.com
 * @since 2013-7-12
 */
@Entity
@Table(name = "T_DC_OPERATE_LOG")
public class DcOpLog implements Serializable {
	private static final long serialVersionUID = -6757324784923443269L;

	private Long id;
	private String userId;
	private String opTime;
	private Long opType;
	private String module;
	private String userName;
	private String opObject;

	@SequenceGenerator(name = "seq_dc_operate_log", sequenceName = "SEQ_DC_OPERATE_LOG")
	@Id
	@GeneratedValue(generator = "seq_dc_operate_log")
	@JsonProperty
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "USER_ID")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "OP_TIME")
	public String getOpTime() {
		return opTime;
	}

	public void setOpTime(String opTime) {
		this.opTime = opTime;
	}

	@Column(name = "OP_TYPE")
	public Long getOpType() {
		return opType;
	}

	public void setOpType(Long opType) {
		this.opType = opType;
	}

	@Column(name = "MODULE")
	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	@Column(name = "USER_NAME")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "OP_OBJECT")
	public String getOpObject() {
		return opObject;
	}

	public void setOpObject(String opObject) {
		this.opObject = opObject;
	}
}
