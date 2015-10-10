package com.cplatform.mall.back.websys.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.cplatform.mall.back.entity.IdEntity;
import com.cplatform.mall.back.store.entity.Store;
import com.cplatform.mall.back.sys.entity.SysType;

/**
 * 商品分类参数管理表. <br>
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
@Table(name = "T_SYS_TYPE_ITEM_PARAM")
public class SysTypeItemParam extends IdEntity implements java.io.Serializable {

	/** 参数类型map */
	private static Map<String, String> paramTypeMap = null;
	static {
		paramTypeMap = new HashMap<String, String>();
		paramTypeMap.put("1", "单选");
		paramTypeMap.put("2", "复选");
		paramTypeMap.put("3", "自填");
	}

	/** 是否必填map */
	private static Map<String, String> requiredFlagMap = null;
	static {
		requiredFlagMap = new HashMap<String, String>();
		requiredFlagMap.put("0", "非必填");
		requiredFlagMap.put("1", "必填");
	}

	/** 是否必填map */
	private static Map<String, String> searchFlagMap = null;
	static {
		searchFlagMap = new HashMap<String, String>();
		searchFlagMap.put("0", "否");
		searchFlagMap.put("1", "是");
	}

	// Fields
	private Long id;

	private Long typeId;

	private String paramKey;

	private String paramValue;

	@Transient
	public List<String> getParamValueList() {
		String[] strs = this.paramValue.split(";");
		List<String> list = new ArrayList<String>();
		for (String str : strs) {
			list.add(str);
		}
		return list;
	}

	private Long rank;

	private Long paramType;

	private Long requiredFlag;

	private Long searchFlag;

	private Long shopId;

	private Long shopClass;

	private String userParamValue;;

	// Constructors

	@Transient
	public String getUserParamValue() {
		return userParamValue;
	}

	public void setUserParamValue(String userParamValue) {
		this.userParamValue = userParamValue;
	}

	/** default constructor */
	public SysTypeItemParam() {
	}

	/** mini constructor */
	public SysTypeItemParam(Long id, Long typeId, String paramKey, String paramValue, Long rank) {
		this.id = id;
		this.typeId = typeId;
		this.paramKey = paramKey;
		this.paramValue = paramValue;
		this.rank = rank;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	@javax.persistence.Column(name = "TYPE_ID")
	@Basic
	public Long getTypeId() {
		return typeId;
	}

	public void setParamKey(String paramKey) {
		this.paramKey = paramKey;
	}

	@javax.persistence.Column(name = "PARAM_KEY")
	@Basic
	public String getParamKey() {
		return paramKey;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

	@javax.persistence.Column(name = "PARAM_VALUE")
	@Basic
	public String getParamValue() {
		return paramValue;
	}

	public void setRank(Long rank) {
		this.rank = rank;
	}

	@javax.persistence.Column(name = "RANK")
	@Basic
	public Long getRank() {
		return rank;
	}

	public void setParamType(Long paramType) {
		this.paramType = paramType;
	}

	@javax.persistence.Column(name = "PARAM_TYPE")
	@Basic
	public Long getParamType() {
		return paramType;
	}

	public void setRequiredFlag(Long requiredFlag) {
		this.requiredFlag = requiredFlag;
	}

	@javax.persistence.Column(name = "REQUIRED_FLAG")
	@Basic
	public Long getRequiredFlag() {
		return requiredFlag;
	}

	public void setSearchFlag(Long searchFlag) {
		this.searchFlag = searchFlag;
	}

	@javax.persistence.Column(name = "SEARCH_FLAG")
	@Basic
	public Long getSearchFlag() {
		return searchFlag;
	}

	// --------------bus method-------------
	@Transient
	public static Map<String, String> getParamTypeMap() {
		return paramTypeMap;
	}

	@Transient
	public String getParamTypeName() {
		return paramTypeMap.get(this.getParamType() + "");
	}

	@Transient
	public static Map<String, String> getRequiredFlagMap() {
		return requiredFlagMap;
	}

	@Transient
	public String getRequiredFlagName() {
		return requiredFlagMap.get(this.getRequiredFlag() + "");
	}

	@Transient
	public static Map<String, String> getSearchFlagMap() {
		return searchFlagMap;
	}

	@Transient
	public String getSearchFlagName() {
		return searchFlagMap.get(this.getSearchFlag() + "");
	}

	private String typeName;

	private String storeName;

	@Transient
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@Transient
	public String getTypeName() {
		return typeName;
	}

	private SysType sysType;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "TYPE_ID", insertable = false, updatable = false)
	public SysType getSysType() {
		return sysType;
	}

	public void setSysType(SysType sysType) {
		this.sysType = sysType;
	}

	@Column(name = "shop_id")
	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	@Column(name = "shop_class")
	public Long getShopClass() {
		return shopClass;
	}

	public void setShopClass(Long shopClass) {
		this.shopClass = shopClass;
	}

	@Transient
	public String getShopClassNmae() {
		return Store.shopClassMap.get(this.getShopClass());
	}

	@Transient
	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

}
