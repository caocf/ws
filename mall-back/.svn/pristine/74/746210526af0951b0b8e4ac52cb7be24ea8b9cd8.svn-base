package com.cplatform.mall.back.store.entity;

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
 * 门店管理表. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2013-5-29 下午07:46:48
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author zhaowei@c-platform.com
 * @version 1.0.0
 */
@Entity
@Table(name = "T_SHOP")
public class Shop implements java.io.Serializable {



	/** 是否签约门店map */
	private static Map<String, String> sortMap = null;
	static {
		sortMap = new HashMap<String, String>();
		sortMap.put("0", "非签约");
		sortMap.put("1", "签约");
	
	}

	/** 是否连锁map */
	private static Map<String, String> isChainMap = null;
	static {
		isChainMap = new HashMap<String, String>();
		isChainMap.put("0", "否");
		isChainMap.put("1", "是");
	}

	/** 是否总店map */
	private static Map<String, String> isBaseShopMap = null;
	static {
		isBaseShopMap = new HashMap<String, String>();
		isBaseShopMap.put("0", "否");
		isBaseShopMap.put("1", "是");
	}

	/** 门店状态map */
	private static Map<String, String> statusMap = null;
	static {
		statusMap = new HashMap<String, String>();
		statusMap.put("0", "草稿");
		statusMap.put("1", "待审核");
		statusMap.put("2", "审核中");
		statusMap.put("3", "审核通过");
		statusMap.put("4", "审核驳回");
	}

	/** 门店是否有效map */
	private static Map<String, String> isValidMap = null;
	static {
		isValidMap = new HashMap<String, String>();
		isValidMap.put("0", "无效");
		isValidMap.put("1", "有效");
	}

	/** 门店分类map */
	private static Map<String, String> shopClassMap = null;
	static {
		shopClassMap = new HashMap<String, String>();
		shopClassMap.put("1", "业务门店");
		shopClassMap.put("2", "结算商户");
		shopClassMap.put("3", "渠道商");
	}

	// 表名
	public static final String TABLE_NAME = "T_SHOP";

	// 门店状态
	/**
	 * 门店状态 － 草稿
	 */
	public static final Long STATUS_0 = 0L;

	/**
	 * 门店状态 － 待审核
	 */
	public static final Long STATUS_1 = 1L;

	/**
	 * 门店状态 － 审核中
	 */
	public static final Long STATUS_2 = 2L;

	/**
	 * 门店状态 － 审核通过
	 */
	public static final Long STATUS_3 = 3L;

	/**
	 * 门店状态 － 审核驳回
	 */
	public static final Long STATUS_4 = 4L;

	// 门店分类
	/**
	 * 门店分类 － 业务门店
	 */
	public static final Long SHOP_CLASS_1 = 1L;

	/**
	 * 门店分类 － 结算商户
	 */
	public static final Long SHOP_CLASS_2 = 2L;

	/**
	 * 门店分类 － 渠道商
	 */
	public static final Long SHOP_CLASS_3 = 3L;

	// 门店签约状态
	/**
	 * 非签约
	 */
	public static final Long SORT_0 = 0L;

	/**
	 * 签约
	 */
	public static final Long SORT_1 = 1L;

	// 是否连锁
	/**
	 * 否
	 */
	public static final Long IS_CHAIN_0 = 0L;

	/**
	 * 是
	 */
	public static final Long IS_CHAIN_1 = 1L;

	// 是否总店
	/**
	 * 否
	 */
	public static final Long IS_BASE_SHOP_0 = 0L;

	/**
	 * 是
	 */
	public static final Long IS_BASE_SHOP_1 = 1L;

	// 是否有效
	/**
	 * 否
	 */
	public static final Long IS_VALID_0 = 0L;

	/**
	 * 是
	 */
	public static final Long IS_VALID_1 = 1L;

	/** 序列 **/
	private Long id;

	/** 结算商户编号 **/
	private Long acShopId;

	/** 商户名称 **/
	private String name;

	/** 商户简称 **/
	private String shortName;

	/** 归属地市 **/
	private String areaCode;

	/** 是否签约商户 **/
	private Long sort;

	/** 折扣内容 **/
	private String discountDetail;

	/** 是否连锁 **/
	private Long isChain;

	/** 是否总店 **/
	private Long isBaseShop;

	/** 总店名称 **/
	private String baseShopName;

	/** 创建时间 **/
	private String createTime;

	/** 更新时间 **/
	private String updateTime;

	/** 有效开始时间 **/
	private String startTime;

	/** 有效结束时间 **/
	private String stopTime;

	/** 商户地址 **/
	private String address;

	/** 联系电话 **/
	private String phone;

	/** 营业时间 **/
	private String openTime;

	/** 公交线路 **/
	private String busLine;

	/** 面积 **/
	private String area;

	/** 停车位 **/
	private String parkPlace;

	/** 人均消费 **/
	private Long avgSpend;

	/** 包间数 **/
	private String roomNum;

	/** 详细介绍 **/
	private String remark;

	/** 地图坐标经度 **/
	private String mapLong;

	/** 地图坐标纬度 **/
	private String mapDim;

	/** 创建商户账号编号 **/
	private Long shopUserId;

	/** 商户状态 **/
	private Long status;

	/** 商户是否有效 **/
	private Long isValid;

	/** 商户分类 **/
	private Long shopClass;

	/** 业务门店LOGO图 **/
	private String logo;

	public void setId(Long id) {
		this.id = id;
	}

	@SequenceGenerator(name = "seq_shop", sequenceName = "SEQ_SHOP")
	@Id
	@GeneratedValue(generator = "seq_shop")
	@JsonProperty
	public Long getId() {
		return id;
	}

	public void setAcShopId(Long acShopId) {
		this.acShopId = acShopId;
	}

	@Column(name = "AC_SHOP_ID")
	public Long getAcShopId() {
		return acShopId;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	@Column(name = "SHORT_NAME")
	public String getShortName() {
		return shortName;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	@Column(name = "AREA_CODE")
	public String getAreaCode() {
		return areaCode;
	}

	public void setSort(Long sort) {
		this.sort = sort;
	}

	@Column(name = "SORT")
	public Long getSort() {
		return sort;
	}

	public void setDiscountDetail(String discountDetail) {
		this.discountDetail = discountDetail;
	}

	@Column(name = "DISCOUNT_DETAIL")
	public String getDiscountDetail() {
		return discountDetail;
	}

	public void setIsChain(Long isChain) {
		this.isChain = isChain;
	}

	@Column(name = "IS_CHAIN")
	public Long getIsChain() {
		return isChain;
	}

	public void setIsBaseShop(Long isBaseShop) {
		this.isBaseShop = isBaseShop;
	}

	@Column(name = "IS_BASE_SHOP")
	public Long getIsBaseShop() {
		return isBaseShop;
	}

	public void setBaseShopName(String baseShopName) {
		this.baseShopName = baseShopName;
	}

	@Column(name = "BASE_SHOP_NAME")
	public String getBaseShopName() {
		return baseShopName;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Column(name = "CREATE_TIME")
	public String getCreateTime() {
		return createTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "UPDATE_TIME")
	public String getUpdateTime() {
		return updateTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	@Column(name = "START_TIME")
	public String getStartTime() {
		return startTime;
	}

	public void setStopTime(String stopTime) {
		this.stopTime = stopTime;
	}

	@Column(name = "STOP_TIME")
	public String getStopTime() {
		return stopTime;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "ADDRESS")
	public String getAddress() {
		return address;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "PHONE")
	public String getPhone() {
		return phone;
	}

	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}

	@Column(name = "OPEN_TIME")
	public String getOpenTime() {
		return openTime;
	}

	public void setBusLine(String busLine) {
		this.busLine = busLine;
	}

	@Column(name = "BUS_LINE")
	public String getBusLine() {
		return busLine;
	}

	public void setArea(String area) {
		this.area = area;
	}

	@Column(name = "AREA")
	public String getArea() {
		return area;
	}

	public void setParkPlace(String parkPlace) {
		this.parkPlace = parkPlace;
	}

	@Column(name = "PARK_PLACE")
	public String getParkPlace() {
		return parkPlace;
	}

	public void setAvgSpend(Long avgSpend) {
		this.avgSpend = avgSpend;
	}

	@Column(name = "AVG_SPEND")
	public Long getAvgSpend() {
		return avgSpend;
	}

	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}

	@Column(name = "ROOM_NUM")
	public String getRoomNum() {
		return roomNum;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}

	public void setMapLong(String mapLong) {
		this.mapLong = mapLong;
	}

	@Column(name = "MAP_LONG")
	public String getMapLong() {
		return mapLong;
	}

	public void setMapDim(String mapDim) {
		this.mapDim = mapDim;
	}

	@Column(name = "MAP_DIM")
	public String getMapDim() {
		return mapDim;
	}

	public void setShopUserId(Long shopUserId) {
		this.shopUserId = shopUserId;
	}

	@Column(name = "SHOP_USER_ID")
	public Long getShopUserId() {
		return shopUserId;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	@Column(name = "STATUS")
	public Long getStatus() {
		return status;
	}

	public void setIsValid(Long isValid) {
		this.isValid = isValid;
	}

	@Column(name = "IS_VALID")
	public Long getIsValid() {
		return isValid;
	}

	public void setShopClass(Long shopClass) {
		this.shopClass = shopClass;
	}

	@Column(name = "SHOP_CLASS")
	public Long getShopClass() {
		return shopClass;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	@Column(name = "LOGO")
	public String getLogo() {
		return logo;
	}

	// ----bus -method---
	@Transient
	public static Map<String, String> getSortMap() {
		return sortMap;
	}

	@Transient
	public String getSortName() {
		return sortMap.get(this.getSort() + "");
	}

	@Transient
	public static Map<String, String> getIsChainMap() {
		return isChainMap;
	}

	@Transient
	public String getIsChainName() {
		return isChainMap.get(this.getIsChain() + "");
	}

	@Transient
	public static Map<String, String> getIsBaseShopMap() {
		return isBaseShopMap;
	}

	@Transient
	public String getIsBaseShopName() {
		return isBaseShopMap.get(this.getIsBaseShop() + "");
	}

	@Transient
	public static Map<String, String> getStatusMap() {
		return statusMap;
	}

	@Transient
	public String getStatusName() {
		return statusMap.get(this.getStatus() + "");
	}

	@Transient
	public static Map<String, String> getIsValidMap() {
		return isValidMap;
	}

	@Transient
	public String getIsValidName() {
		return isValidMap.get(this.getIsValid() + "");
	}

	@Transient
	public static Map<String, String> getShopClassMap() {
		return shopClassMap;
	}

	@Transient
	public String getShopClassName() {
		return shopClassMap.get(this.getShopClass() + "");
	}

	private String startTimeBegin;

	private String startTimeEnd;

	private String stopTimeBegin;

	private String stopTimeEnd;

	private String regionName;

	private String storeName;

	private String createUserName;

	private String typeName;

	private Long fzShopId;

	@Transient
	public void setStartTimeBegin(String startTimeBegin) {
		this.startTimeBegin = startTimeBegin;
	}

	@Transient
	public String getStartTimeBegin() {
		return startTimeBegin;
	}

	@Transient
	public void setStartTimeEnd(String startTimeEnd) {
		this.startTimeEnd = startTimeEnd;
	}

	@Transient
	public String getStartTimeEnd() {
		return startTimeEnd;
	}

	@Transient
	public void setStopTimeBegin(String stopTimeBegin) {
		this.stopTimeBegin = stopTimeBegin;
	}

	@Transient
	public String getStopTimeBegin() {
		return stopTimeBegin;
	}

	@Transient
	public void setStopTimeEnd(String stopTimeEnd) {
		this.stopTimeEnd = stopTimeEnd;
	}

	@Transient
	public String getStopTimeEnd() {
		return stopTimeEnd;
	}

	@Transient
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	@Transient
	public String getRegionName() {
		return regionName;
	}

	@Transient
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	@Transient
	public String getStoreName() {
		return storeName;
	}

	@Transient
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	@Transient
	public String getCreateUserName() {
		return createUserName;
	}

	@Transient
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@Transient
	public String getTypeName() {
		return typeName;
	}

	@Transient
	public void setFzShopId(Long fzShopId) {
		this.fzShopId = fzShopId;
	}

	@Transient
	public Long getFzShopId() {
		return fzShopId;
	}
}
