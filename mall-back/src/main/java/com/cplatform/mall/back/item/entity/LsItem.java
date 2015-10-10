package com.cplatform.mall.back.item.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
//拉手实体扩展类


@Table(name = "T_ITEM_LASHOU")
@Entity
public class LsItem {
	private Long id;
	private Long itemId;
	private String venderItemId;
	private String smsTitle;
	private Long firstCatalog;
	private Long secondCatalog;
	private Long thirdCatalog;
	private Long provinceId;
	private Long cityId;
	private String brand;
	private Long stockMode;
	private Long refundStrategy;
	private Long maxSale;
	private String createTime;
	private String updateTime;
	
	@SequenceGenerator(name = "seq_item_ls", sequenceName = "SEQ_ITEM_LASHOU")
	@Id
	@GeneratedValue(generator = "seq_item_ls")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name = "ITEM_ID")
	public Long getItemId() {
		return itemId;
	}
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	@Column(name = "VENDER_TEAM_ID")
	public String getVenderItemId() {
		return venderItemId;
	}
	public void setVenderItemId(String venderItemId) {
		this.venderItemId = venderItemId;
	}
	@Column(name = "SMS_TITLE")
	public String getSmsTitle() {
		return smsTitle;
	}
	public void setSmsTitle(String smsTitle) {
		this.smsTitle = smsTitle;
	}
	@Column(name = "FIRST_CATALOG")
	public Long getFirstCatalog() {
		return firstCatalog;
	}
	public void setFirstCatalog(Long firstCatalog) {
		this.firstCatalog = firstCatalog;
	}
	@Column(name = "SECOND_CATALOG")
	public Long getSecondCatalog() {
		return secondCatalog;
	}
	public void setSecondCatalog(Long secondCatalog) {
		this.secondCatalog = secondCatalog;
	}
	@Column(name = "THIRD_CATALOG")
	public Long getThirdCatalog() {
		return thirdCatalog;
	}
	public void setThirdCatalog(Long thirdCatalog) {
		this.thirdCatalog = thirdCatalog;
	}
	@Column(name = "PROVINCE_ID")
	public Long getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}
	@Column(name = "CITY_ID")
	public Long getCityId() {
		return cityId;
	}
	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}
	@Column(name = "BRAND")
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	@Column(name = "STOCK_MODE")
	public Long getStockMode() {
		return stockMode;
	}
	public void setStockMode(Long stockMode) {
		this.stockMode = stockMode;
	}
	@Column(name = "REFUND_STRATEGY")
	public Long getRefundStrategy() {
		return refundStrategy;
	}
	public void setRefundStrategy(Long refundStrategy) {
		this.refundStrategy = refundStrategy;
	}
	@Column(name = "MAX_SALE")
	public Long getMaxSale() {
		return maxSale;
	}
	public void setMaxSale(Long maxSale) {
		this.maxSale = maxSale;
	}
	@Column(name = "CREATE_TIME")
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	@Column(name = "UPDATE_TIME")
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
	

}
