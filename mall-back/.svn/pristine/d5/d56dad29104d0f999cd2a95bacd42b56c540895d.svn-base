package com.cplatform.mall.back.sys.entity;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import com.cplatform.mall.back.entity.IdEntity;

/**
 * 分类管理表. <br>
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
@JsonAutoDetect
@Entity
@Table(name = "T_SYS_TYPE")
public class SysType extends IdEntity implements java.io.Serializable {

	/** 类型map */
	private static Map<String, String> typeMap = null;
	static {
		typeMap = new HashMap<String, String>();
		typeMap.put("1", "商户");
		typeMap.put("2", "商品");
	}
	
	 private static Map<String,String> isValidMap   = null;
	 
	  
	 static {
		 
		 isValidMap  = new HashMap<String, String>();
		 isValidMap.put("1", "是");
		 isValidMap.put("0", "否");
	 }

	@JsonProperty("pid")
	private Long pId;

	private String name;
	


	private Long isValid;

	@JsonIgnore
	private Long type;

	@JsonIgnore
	private Long syncGyFlag;
	
	

	@JsonProperty("isParent")
	private boolean isParent;

	// Constructors
	@Transient
	public Long getSyncGyFlag() {
		return syncGyFlag;
	}

	public void setSyncGyFlag(Long syncGyFlag) {
		this.syncGyFlag = syncGyFlag;
	}

	private static Map<Long, String> syncGyFlagMap = null;

	static {
		syncGyFlagMap = new HashMap<Long, String>();
		syncGyFlagMap.put(0L, "未同步 ");
		syncGyFlagMap.put(1L, "同步成功");
		syncGyFlagMap.put(2L, "同步失败");
	}

	@Transient
	public String getSyncGyFlagName() {
		return syncGyFlagMap.get(this.syncGyFlag);
	}

	/** default constructor */
	public SysType() {
	}

	/** full constructor */
	public SysType(Long id, Long pId, String name, Long type,Long isValid) {
		this.id = id;
		this.pId = pId;
		this.name = name;
		this.type = type;
		this.isValid= isValid;
	}

	public void setpId(Long pId) {
		this.pId = pId;
	}

	@Column(name = "P_ID", precision = 8, scale = 0)
	public Long getpId() {
		return pId;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "NAME", length = 20)
	public String getName() {
		return name;
	}
      @Column(name="IS_VALID")
	 public Long getIsValid() {
			return isValid;
		}

		public void setIsValid(Long isValid) {
			this.isValid = isValid;
		}
	
	public void setType(Long type) {
		this.type = type;
	}

	@Column(name = "TYPE", precision = 1, scale = 0)
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
		return typeMap.get(this.getType() + "");
	}

	@Transient
	public boolean isParent() {
		return isParent;
	}

	public void setParent(boolean isParent) {
		this.isParent = isParent;
	}
	
	/***
	 * 获取类型map
	 * 
	 * @return
	 */
	@Transient
	public static Map<String, String> getIsValidMap() {
		return isValidMap;
	}

	/**
	 * 获取类型名称
	 * 
	 * @return
	 */
	@Transient
	public String getIsValidName() {
		return isValidMap.get(this.getIsValid() + "");
	}
}
