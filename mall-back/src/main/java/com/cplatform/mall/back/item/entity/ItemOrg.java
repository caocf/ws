package com.cplatform.mall.back.item.entity;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-5-30 上午9:49:14
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: wuyn@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
@Table(name = "T_ITEM_ORG")
@Entity
public class ItemOrg {

	private Long id;

	private String shopName;

	private String typeName;

	/**
	 * 获取 shopName
	 * 
	 * @return shopName
	 */
	@Transient
	public String getShopName() {
		return shopName;
	}

	/**
	 * 设置 shopName
	 * 
	 * @param shopName
	 */
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	/**
	 * 获取 typeName
	 * 
	 * @return typeName
	 */
	@Transient
	public String getTypeName() {
		return typeName;
	}

	/**
	 * 设置 typeName
	 * 
	 * @param typeName
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	/**
	 * 获取 id
	 * 
	 * @return id
	 */
	@SequenceGenerator(name = "seq_item", sequenceName = "SEQ_SYS_ITEM_ID")
	@Id
	@GeneratedValue(generator = "seq_item")
	@JsonProperty
	public Long getId() {
		return id;
	}

	/**
	 * 设置 id
	 * 
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	private Integer itemMode;

	public static Map<Integer, String> itemModeMap = null;
	static {
		itemModeMap = new HashMap<Integer, String>();
		itemModeMap.put(0, "实物");
		itemModeMap.put(1, "虚拟物");
	}

	@Transient
	public String getItemModeName() {
		return itemModeMap.get(this.itemMode);
	}

	private Long typeId;

	/** 0--普通商品 1--优惠套餐 */
	private Integer groupFlag;

	public static Map<Integer, String> groupFlagMap = null;
	static {
		groupFlagMap = new HashMap<Integer, String>();
		groupFlagMap.put(0, "普通商品");
		groupFlagMap.put(1, "优惠套餐");
	}

	@Transient
	public String getGroupFlagName() {
		return groupFlagMap.get(this.groupFlag);
	}

	/** 是否虚拟商品 0-否 1-是 */
	private Integer virtualFlag;

	/** 虚拟商品类型1-卡密 2-直充 */
	private Integer virtualType;

	private String shopIdShopClass;

	/**
	 * 获取 shopIdShopClass
	 * 
	 * @return shopIdShopClass
	 */
	@Transient
	public String getShopIdShopClass() {
		return shopIdShopClass;
	}

	/**
	 * 设置 shopIdShopClass
	 * 
	 * @param shopIdShopClass
	 */
	public void setShopIdShopClass(String shopIdShopClass) {
		this.shopIdShopClass = shopIdShopClass;
	}

	public static Map<Integer, String> virtualTypeMap = null;
	static {
		virtualTypeMap = new HashMap<Integer, String>();
		virtualTypeMap.put(1, "卡密");
		virtualTypeMap.put(2, "直充");
	}

	@Transient
	public String getVirtualTypeName() {
		return virtualTypeMap.get(this.virtualType);
	}

	private String name;

	private String shortName;

	private String warmPrompt;

	private String remark;

	private String itemIds;

	/**
	 * 获取 itemIds
	 * 
	 * @return itemIds
	 */
	@Transient
	public String getItemIds() {
		return itemIds;
	}

	/**
	 * 设置 itemIds
	 * 
	 * @param itemIds
	 */
	public void setItemIds(String itemIds) {
		this.itemIds = itemIds;
	}

	/** 0--草稿 1--待审核 2--审核中 3--审核通过 4--审核驳回 */
	private Integer status;

	public static Map<Integer, String> statusMap = null;
	static {
		statusMap = new HashMap<Integer, String>();
		statusMap.put(0, "草稿");
		statusMap.put(1, "待审核");
		statusMap.put(2, "审核中");
		statusMap.put(3, "审核通过");
		statusMap.put(4, "审核驳回");
	}

	@Transient
	public String getStatusName() {
		return statusMap.get(this.status);
	}

	private Long shopUserId;

	/** 1--业务门店 2--商户 3--渠道商 */
	private Integer shopClass;

	private Long shopId;

	private String updateTime;

	private String brand;

	private Double weight;

	private String createTime;

	private Long createUserId;

	private Long updateUserId;

	/** 营销语 */
	private String marketContent;

	private String queryStartTime;

	private String queryEndTime;

	/**
	 * 获取 queryStartTime
	 * 
	 * @return queryStartTime
	 */
	@Transient
	public String getQueryStartTime() {
		return queryStartTime;
	}

	/**
	 * 设置 queryStartTime
	 * 
	 * @param queryStartTime
	 */
	public void setQueryStartTime(String queryStartTime) {
		this.queryStartTime = queryStartTime;
	}

	/**
	 * 获取 queryEndTime
	 * 
	 * @return queryEndTime
	 */
	@Transient
	public String getQueryEndTime() {
		return queryEndTime;
	}

	/**
	 * 设置 queryEndTime
	 * 
	 * @param queryEndTime
	 */
	public void setQueryEndTime(String queryEndTime) {
		this.queryEndTime = queryEndTime;
	}

	/**
	 * 获取 itemMode
	 * 
	 * @return itemMode
	 */
	@Column(name = "ITEM_MODE", nullable = true)
	public Integer getItemMode() {
		return itemMode;
	}

	/**
	 * 设置 itemMode
	 * 
	 * @param itemMode
	 */
	public void setItemMode(Integer itemMode) {
		this.itemMode = itemMode;
	}

	/**
	 * 获取 typeId
	 * 
	 * @return typeId
	 */
	@Column(name = "TYPE_ID", nullable = true)
	public Long getTypeId() {
		return typeId;
	}

	/**
	 * 设置 typeId
	 * 
	 * @param typeId
	 */
	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	/**
	 * 获取 groupFlag
	 * 
	 * @return groupFlag
	 */
	@Column(name = "GROUP_FLAG", nullable = true)
	public Integer getGroupFlag() {
		return groupFlag;
	}

	/**
	 * 设置 groupFlag
	 * 
	 * @param groupFlag
	 */
	public void setGroupFlag(Integer groupFlag) {
		this.groupFlag = groupFlag;
	}

	/**
	 * 获取 virtualFlag
	 * 
	 * @return virtualFlag
	 */
	@Column(name = "VIRTUAL_FLAG", nullable = true)
	public Integer getVirtualFlag() {
		return virtualFlag;
	}

	/**
	 * 设置 virtualFlag
	 * 
	 * @param virtualFlag
	 */
	public void setVirtualFlag(Integer virtualFlag) {
		this.virtualFlag = virtualFlag;
	}

	/**
	 * 获取 virtualType
	 * 
	 * @return virtualType
	 */
	@Column(name = "VIRTUAL_TYPE", nullable = true)
	public Integer getVirtualType() {
		return virtualType;
	}

	/**
	 * 设置 virtualType
	 * 
	 * @param virtualType
	 */
	public void setVirtualType(Integer virtualType) {
		this.virtualType = virtualType;
	}

	/**
	 * 获取 name
	 * 
	 * @return name
	 */
	@Column(name = "NAME", nullable = true)
	public String getName() {
		return name;
	}

	/**
	 * 设置 name
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取 shortName
	 * 
	 * @return shortName
	 */
	@Column(name = "SHORT_NAME", nullable = true)
	public String getShortName() {
		return shortName;
	}

	/**
	 * 设置 shortName
	 * 
	 * @param shortName
	 */
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	/**
	 * 获取 warmPrompt
	 * 
	 * @return warmPrompt
	 */
	@Column(name = "WARM_PROMPT", nullable = true)
	public String getWarmPrompt() {
		return warmPrompt;
	}

	/**
	 * 设置 warmPrompt
	 * 
	 * @param warmPrompt
	 */
	public void setWarmPrompt(String warmPrompt) {
		this.warmPrompt = warmPrompt;
	}

	/**
	 * 获取 remark
	 * 
	 * @return remark
	 */
	@Column(name = "REMARK", nullable = true)
	public String getRemark() {
		return remark;
	}

	/**
	 * 设置 remark
	 * 
	 * @param remark
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 获取 status
	 * 
	 * @return status
	 */
	@Column(name = "STATUS", nullable = true)
	public Integer getStatus() {
		return status;
	}

	/**
	 * 设置 status
	 * 
	 * @param status
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * 获取 shopUserId
	 * 
	 * @return shopUserId
	 */
	@Column(name = "SHOP_USER_ID", nullable = true)
	public Long getShopUserId() {
		return shopUserId;
	}

	/**
	 * 设置 shopUserId
	 * 
	 * @param shopUserId
	 */
	public void setShopUserId(Long shopUserId) {
		this.shopUserId = shopUserId;
	}

	/**
	 * 获取 shopId
	 * 
	 * @return shopId
	 */
	@Column(name = "SHOP_ID", nullable = true)
	public Long getShopId() {
		return shopId;
	}

	/**
	 * 设置 shopId
	 * 
	 * @param shopId
	 */
	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	/**
	 * 获取 updateTime
	 * 
	 * @return updateTime
	 */
	@Column(name = "UPDATE_TIME", nullable = true)
	public String getUpdateTime() {
		return updateTime;
	}

	/**
	 * 设置 updateTime
	 * 
	 * @param updateTime
	 */
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * 获取 weight
	 * 
	 * @return weight
	 */
	@Column(name = "WEIGHT", nullable = true)
	public Double getWeight() {
		return weight;
	}

	/**
	 * 设置 weight
	 * 
	 * @param weight
	 */
	public void setWeight(Double weight) {
		this.weight = weight;
	}

	/**
	 * 获取 createTime
	 * 
	 * @return createTime
	 */
	@Column(name = "CREATE_TIME", nullable = true)
	public String getCreateTime() {
		return createTime;
	}

	/**
	 * 设置 createTime
	 * 
	 * @param createTime
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获取 createUserId
	 * 
	 * @return createUserId
	 */
	@Column(name = "CREATE_USER_ID", nullable = true)
	public Long getCreateUserId() {
		return createUserId;
	}

	/**
	 * 设置 createUserId
	 * 
	 * @param createUserId
	 */
	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * 获取 updateUserId
	 * 
	 * @return updateUserId
	 */
	@Column(name = "UPDATE_USER_ID", nullable = true)
	public Long getUpdateUserId() {
		return updateUserId;
	}

	/**
	 * 设置 updateUserId
	 * 
	 * @param updateUserId
	 */
	public void setUpdateUserId(Long updateUserId) {
		this.updateUserId = updateUserId;
	}

	/**
	 * 获取 marketContent
	 * 
	 * @return marketContent
	 */
	@Column(name = "MARKET_CONTENT", nullable = true)
	public String getMarketContent() {
		return marketContent;
	}

	/**
	 * 设置 marketContent
	 * 
	 * @param marketContent
	 */
	public void setMarketContent(String marketContent) {
		this.marketContent = marketContent;
	}

	/**
	 * 获取 shopClass
	 * 
	 * @return shopClass
	 */
	@Column(name = "SHOP_CLASS", nullable = true)
	public Integer getShopClass() {
		return shopClass;
	}

	/**
	 * 设置 shopClass
	 * 
	 * @param shopClass
	 */
	public void setShopClass(Integer shopClass) {
		this.shopClass = shopClass;
	}

	/**
	 * 获取 brand
	 * 
	 * @return brand
	 */
	@Column(name = "BRAND", nullable = true)
	public String getBrand() {
		return brand;
	}

	/**
	 * 设置 brand
	 * 
	 * @param brand
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}

	private String tag;

	private String itemName;

	/**
	 * 获取 tag
	 * 
	 * @return tag
	 */
	@Transient
	public String getTag() {
		return tag;
	}

	/**
	 * 设置 tag
	 * 
	 * @param tag
	 */
	public void setTag(String tag) {
		this.tag = tag;
	}

	/**
	 * 获取 itemName
	 * 
	 * @return itemName
	 */
	@Transient
	public String getItemName() {
		return itemName;
	}

	/**
	 * 设置 itemName
	 * 
	 * @param itemName
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

}
