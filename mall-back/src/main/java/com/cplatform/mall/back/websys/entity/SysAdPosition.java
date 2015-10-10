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
 * 广告位置管理表. <br>
 * 用于存放数据库映射实体.
 * <p>
 * Copyright: Copyright (c) 2013-5-28 上午11:06:30
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * @author zhaowei@c-platform.com
 * @version 1.0.0
 */
@Entity
@Table(name = "T_SYS_AD_POSITION")
public class SysAdPosition extends IdEntity implements java.io.Serializable {
	
	/** 广告位置模板文件 **/
	public static final String AD_POSITION_TEMPLATE_KEY = "AD_POSITION_TEMPLATE";
	/** 类型map */
	private static Map<String, String> typeMap = null;
	static {
		typeMap = new HashMap<String, String>();
		typeMap.put("1", "图片");
		typeMap.put("2", "文字");
		typeMap.put("3", "flash");
	}
	// Fields
	private Long id;
	
	private String name;
	
	private Long width;
	
	private Long height;
	
	private String position;
	
	private String code;
	
	private Long type;
	
	private Long num;

	// Constructors
	
	public void setName(String name) {
	    this.name = name;
    }

	@javax.persistence.Column(name = "NAME")
	@Basic
	public String getName() {
	    return name;
    }

	public void setWidth(Long width) {
	    this.width = width;
    }

	@javax.persistence.Column(name = "WIDTH")
	@Basic
	public Long getWidth() {
	    return width;
    }

	public void setHeight(Long height) {
	    this.height = height;
    }

	@javax.persistence.Column(name = "HEIGHT")
	@Basic
	public Long getHeight() {
	    return height;
    }

	public void setPosition(String position) {
	    this.position = position;
    }

	@javax.persistence.Column(name = "POSITION")
	@Basic
	public String getPosition() {
	    return position;
    }

	public void setCode(String code) {
	    this.code = code;
    }

	@javax.persistence.Column(name = "CODE")
	@Basic
	public String getCode() {
	    return code;
    }

	public void setType(Long type) {
	    this.type = type;
    }
	
	

	@javax.persistence.Column(name = "TYPE")
	@Basic
	public Long getType() {
	    return type;
    }
	
	@javax.persistence.Column(name = "NUM")
	@Basic
	public Long getNum() {
		return num;
	}

	public void setNum(Long num) {
		this.num = num;
	}
	
	// ----bus -method---
	@Transient
	public static Map<String, String> getTypeMap() {
		return typeMap;
	}

	@Transient
	public String getTypeName() {
		return typeMap.get(this.getType() + "");
	}
	
}
