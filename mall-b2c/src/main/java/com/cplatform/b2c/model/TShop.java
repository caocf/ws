package com.cplatform.b2c.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TShop entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_SHOP")
public class TShop implements java.io.Serializable {

	// Fields

	/** serialVersionUID */
    private static final long serialVersionUID = -3590909786628480833L;

	private Integer id;

	private Integer acShopId;

	private String name;

	private String shortName;

	private String areaCode;

	private Boolean sort;

	private String discountDetail;

	private Boolean isChain;

	private Boolean isBaseShop;

	private String baseShopName;

	private String createTime;

	private String updateTime;

	private String startTime;

	private String stopTime;

	private String address;

	private String phone;

	private String openTime;

	private String busLine;

	private String area;

	private String parkPlace;

	private Integer avgSpend;

	private String roomNum;

	private String remark;

	private String mapLong;

	private String mapDim;

	private Integer shopUserId;

	private Integer status;

	private Boolean isValid;

	private Boolean shopClass;

//	private Boolean syncFzFlag;

	// Constructors

	/** default constructor */
	public TShop() {
	}

	/** minimal constructor */
	public TShop(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public TShop(Integer id, Integer acShopId, String name, String shortName, String areaCode, Boolean sort, String discountDetail, Boolean isChain,
	             Boolean isBaseShop, String baseShopName, String createTime, String updateTime, String startTime, String stopTime, String address,
	             String phone, String openTime, String busLine, String area, String parkPlace, Integer avgSpend, String roomNum, String remark,
	             String mapLong, String mapDim, Integer shopUserId, Integer status, Boolean isValid, Boolean shopClass, Boolean syncFzFlag) {
		this.id = id;
		this.acShopId = acShopId;
		this.name = name;
		this.shortName = shortName;
		this.areaCode = areaCode;
		this.sort = sort;
		this.discountDetail = discountDetail;
		this.isChain = isChain;
		this.isBaseShop = isBaseShop;
		this.baseShopName = baseShopName;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.startTime = startTime;
		this.stopTime = stopTime;
		this.address = address;
		this.phone = phone;
		this.openTime = openTime;
		this.busLine = busLine;
		this.area = area;
		this.parkPlace = parkPlace;
		this.avgSpend = avgSpend;
		this.roomNum = roomNum;
		this.remark = remark;
		this.mapLong = mapLong;
		this.mapDim = mapDim;
		this.shopUserId = shopUserId;
		this.status = status;
		this.isValid = isValid;
		this.shopClass = shopClass;
//		this.syncFzFlag = syncFzFlag;
	}

	// Property accessors
	@Id
	@Column(name = "ID", unique = true, nullable = false, precision = 8, scale = 0)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "AC_SHOP_ID", precision = 8, scale = 0)
	public Integer getAcShopId() {
		return this.acShopId;
	}

	public void setAcShopId(Integer acShopId) {
		this.acShopId = acShopId;
	}

	@Column(name = "NAME", length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "SHORT_NAME", length = 20)
	public String getShortName() {
		return this.shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	@Column(name = "AREA_CODE", length = 10)
	public String getAreaCode() {
		return this.areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	@Column(name = "SORT", precision = 1, scale = 0)
	public Boolean getSort() {
		return this.sort;
	}

	public void setSort(Boolean sort) {
		this.sort = sort;
	}

	@Column(name = "DISCOUNT_DETAIL", length = 100)
	public String getDiscountDetail() {
		return this.discountDetail;
	}

	public void setDiscountDetail(String discountDetail) {
		this.discountDetail = discountDetail;
	}

	@Column(name = "IS_CHAIN", precision = 1, scale = 0)
	public Boolean getIsChain() {
		return this.isChain;
	}

	public void setIsChain(Boolean isChain) {
		this.isChain = isChain;
	}

	@Column(name = "IS_BASE_SHOP", precision = 1, scale = 0)
	public Boolean getIsBaseShop() {
		return this.isBaseShop;
	}

	public void setIsBaseShop(Boolean isBaseShop) {
		this.isBaseShop = isBaseShop;
	}

	@Column(name = "BASE_SHOP_NAME", length = 50)
	public String getBaseShopName() {
		return this.baseShopName;
	}

	public void setBaseShopName(String baseShopName) {
		this.baseShopName = baseShopName;
	}

	@Column(name = "CREATE_TIME", length = 14)
	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Column(name = "UPDATE_TIME", length = 14)
	public String getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "START_TIME", length = 14)
	public String getStartTime() {
		return this.startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	@Column(name = "STOP_TIME", length = 14)
	public String getStopTime() {
		return this.stopTime;
	}

	public void setStopTime(String stopTime) {
		this.stopTime = stopTime;
	}

	@Column(name = "ADDRESS", length = 100)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "PHONE", length = 50)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "OPEN_TIME", length = 50)
	public String getOpenTime() {
		return this.openTime;
	}

	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}

	@Column(name = "BUS_LINE", length = 50)
	public String getBusLine() {
		return this.busLine;
	}

	public void setBusLine(String busLine) {
		this.busLine = busLine;
	}

	@Column(name = "AREA", length = 20)
	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	@Column(name = "PARK_PLACE", length = 20)
	public String getParkPlace() {
		return this.parkPlace;
	}

	public void setParkPlace(String parkPlace) {
		this.parkPlace = parkPlace;
	}

	@Column(name = "AVG_SPEND", precision = 8, scale = 0)
	public Integer getAvgSpend() {
		return this.avgSpend;
	}

	public void setAvgSpend(Integer avgSpend) {
		this.avgSpend = avgSpend;
	}

	@Column(name = "ROOM_NUM", length = 50)
	public String getRoomNum() {
		return this.roomNum;
	}

	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}

	@Column(name = "REMARK", length = 200)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "MAP_LONG", length = 20)
	public String getMapLong() {
		return this.mapLong;
	}

	public void setMapLong(String mapLong) {
		this.mapLong = mapLong;
	}

	@Column(name = "MAP_DIM", length = 20)
	public String getMapDim() {
		return this.mapDim;
	}

	public void setMapDim(String mapDim) {
		this.mapDim = mapDim;
	}

	@Column(name = "SHOP_USER_ID", precision = 9, scale = 0)
	public Integer getShopUserId() {
		return this.shopUserId;
	}

	public void setShopUserId(Integer shopUserId) {
		this.shopUserId = shopUserId;
	}

	@Column(name = "STATUS", precision = 9, scale = 0)
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "IS_VALID", precision = 1, scale = 0)
	public Boolean getIsValid() {
		return this.isValid;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}

	@Column(name = "SHOP_CLASS", precision = 1, scale = 0)
	public Boolean getShopClass() {
		return this.shopClass;
	}

	public void setShopClass(Boolean shopClass) {
		this.shopClass = shopClass;
	}

//	@Column(name = "SYNC_FZ_FLAG", precision = 1, scale = 0)
//	public Boolean getSyncFzFlag() {
//		return this.syncFzFlag;
//	}
//
//	public void setSyncFzFlag(Boolean syncFzFlag) {
//		this.syncFzFlag = syncFzFlag;
//	}

}