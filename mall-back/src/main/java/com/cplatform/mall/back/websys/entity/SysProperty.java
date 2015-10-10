package com.cplatform.mall.back.websys.entity;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.cplatform.mall.back.entity.IdEntity;
/**
 * 
 * 属性管理表. <br>
 * 用于存放数据库映射实体.
 * <p>
 * Copyright: Copyright (c) 2013-5-24 上午11:01:01
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * @author zhaowei@c-platform.com
 * @version 1.0.0
 */
@Entity
@Table(name = "T_SYS_PROPERTY")
public class SysProperty extends IdEntity implements java.io.Serializable {
	/** 选择方式map */
	private static Map<String, String> typeMap = null;
	static {
		typeMap = new HashMap<String, String>();
		typeMap.put("0", "单选");
		typeMap.put("1", "多选");
		typeMap.put("2", "录入");
	}
	
	// Fields
	private Long id;
	
	private String content;
	
	private Long type;

	// Constructors

	/** default constructor */
	public SysProperty() {
	}

	/** full constructor */
	public SysProperty(Long id, String content, Long type) {
		this.id = id;
		this.content = content;
		this.type = type;
	}
	
	public void setContent(String content) {
	    this.content = content;
    }
	
	@javax.persistence.Column(name = "CONTENT")
	@Basic
	public String getContent() {
	    return content;
    }
	
	public void setType(Long type) {
	    this.type = type;
    }

	@javax.persistence.Column(name = "TYPE")
	@Basic
	public Long getType() {
	    return type;
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
		return typeMap.get(this.getType()+"");
	}
}
