package com.cplatform.mall.back.websys.entity;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.cplatform.mall.back.entity.IdEntity;

/**
 * 终端管理表. <br>
 * 用于存放数据库映射实体.
 * <p>
 * Copyright: Copyright (c) 2013-5-24 上午11:01:01
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author zhaowei@c-platform.com
 * @version 1.0.0
 */
@Entity
@Table(name = "T_SYS_POS")
public class SysPos extends IdEntity implements java.io.Serializable {

	/** 类型map */
	private static Map<String, String> typeMap = null;
	static {
		typeMap = new HashMap<String, String>();
		typeMap.put("1", "商户");
		typeMap.put("2", "商品");
	}

	// Fields
	private Long id;

	private String name;

	private String src;

	private String type;

	private String factory;

	// Constructors

	/** default constructor */
	public SysPos() {
	}

	/** full constructor */
	public SysPos(Long id, String name, String src, String type, String factory) {
		this.id = id;
		this.name = name;
		this.src = src;
		this.type = type;
		this.factory = factory;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	@Column(name = "SRC")
	public String getSrc() {
		return src;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "TYPE")
	public String getType() {
		return type;
	}

	public void setFactory(String factory) {
		this.factory = factory;
	}

	@Column(name = "FACTORY")
	public String getFactory() {
		return factory;
	}
	// ----bus -method---

	/***
	 * 获取类型map
	 * 
	 * @return
	 */
	@Transient
	public static Map<String, String> getTypeMap() {
		return typeMap;
	}

	/**
	 * 获取类型名称
	 * 
	 * @return
	 */
	@Transient
	public String getTypeName() {
		return typeMap.get(this.getType());
	}
}
