package com.cplatform.mall.back.item.virtual.entity;

import java.util.List;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;
@JsonAutoDetect
public class Address {
	private String city;
	private List<ShopAddress> shops;
	@JsonProperty("city")
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@JsonProperty("shops")
	public List<ShopAddress> getShops() {
		return shops;
	}
	public void setShops(List<ShopAddress> shops) {
		this.shops = shops;
	}	
}
