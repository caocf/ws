package com.cplatform.mall.back.item.virtual.entity;


import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonAutoDetect
public class ShopAddress {
	private String shopId;
	private String cityCode;
	private String shopName;
	private String shopAdrr;
	private String shopTel;
	private String URL;
	
	@JsonProperty("shopId")
	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	@JsonProperty("cityCode")
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	@JsonProperty("shopName")
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	@JsonProperty("shopAddr")
	public String getShopAdrr() {
		return shopAdrr;
	}
	public void setShopAdrr(String shopAdrr) {
		this.shopAdrr = shopAdrr;
	}
	@JsonProperty("shopTel")
	public String getShopTel() {
		return shopTel;
	}
	public void setShopTel(String shopTel) {
		this.shopTel = shopTel;
	}
	@JsonProperty("URL")
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	
}
