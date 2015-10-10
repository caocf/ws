package com.cplatform.mall.back.item.entity;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

/**
 *  
 * Description：在ItemPriceType对象新增一个regionName熟悉。
 * <p>
 * Copyright: Copyright (c) 2013-7-15 下午 
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: macl@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
@Component
public class ItemPriceTypeOnArea {

	public static Map<String, String> priceTypeMap = null;
	static {
		priceTypeMap = new HashMap<String, String>();
		priceTypeMap.put("MARKET_PRICE", "市场价");
		priceTypeMap.put("SHOP_PRICE", "商城价");
		priceTypeMap.put("RED_PRICE", "红钻价");
	}

	public String getPriceTypeName() {
		return priceTypeMap.get(this.priceTypeCode);
	}

	private Long id;

	/** 省份 **/
	private String province;

	/** 地市 **/
	private String areaCode;

	/** 价格类型名称,市场价,商城价,红钻价 **/
	private String priceType;

	/** 来源:T_ITEM_PRICE_TYPE **/
	private String priceTypeCode;

	/** 备注 **/
	private String remark;

	/** 所属区域 **/
	private String regionName;

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	/**
	 * 获取 id
	 * 
	 * @return id
	 */
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

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getPriceTypeCode() {
		return priceTypeCode;
	}

	public void setPriceTypeCode(String priceTypeCode) {
		this.priceTypeCode = priceTypeCode;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPriceType() {
		return priceType;
	}

	public void setPriceType(String priceType) {
		this.priceType = priceType;
	}

}
