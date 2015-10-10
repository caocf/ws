package com.cplatform.mall.back.websys.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * Title. <br>
 * 模版事件实体类
 * <p>
 * Copyright: Copyright (c) 2013-6-8
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: zhouhui@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
@Entity
@Table(name = "T_SYS_TEMPLATE_EVENT")
public class SysTemplateEvent {

	// 数据表字段
	private Integer id;
	private int code;
	/*
	 * 1：单一模板
	 * 2：模板组
	 */
	private Integer type;
	/**
	 * 模版主键
	 */
	private Integer tgId;
	/**
	 * 模版名称
	 */
	private String tgName;
	/**
	 * 模版事件名称
	 */
	private String name;
	
	/**
	 * 备注
	 */
	private String memo;

	@Id
	@GeneratedValue
	@Column(name="ID")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name="CODE")
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	@Column(name="TYPE")
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name="TGID")
	public Integer getTgId() {
		return tgId;
	}

	public void setTgId(Integer tgId) {
		this.tgId = tgId;
	}

	@Column(name="TGNAME")
	public String getTgName() {
		return tgName;
	}

	public void setTgName(String tgName) {
		this.tgName = tgName;
	}
	
	@Column(name="NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="MEMO")
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}
