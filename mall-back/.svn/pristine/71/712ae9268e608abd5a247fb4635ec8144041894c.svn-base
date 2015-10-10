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
 * 存放商品各类类型编码及名称，发布商品时可以动态的生成页面，录入不同类型的价格。 Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-6-1 下午5:42:53
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: baibo@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
@Table(name = "T_ITEM_PRICE_TYPE")
@Entity
public class ItemPriceType {

	public static Map<String, String> priceTypeMap = null;
	static {
		priceTypeMap = new HashMap<String, String>();
		priceTypeMap.put("MARKET_PRICE", "市场价");
		priceTypeMap.put("SHOP_PRICE", "商城价");
		priceTypeMap.put("RED_PRICE", "红钻价");
	}
	

	@Transient
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

	/** @add_by macl@c-platform.com**/
	private String price;
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

	@Column(name = "PROVINCE", length = 20)
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Column(name = "AREA_CODE", length = 20)
	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	@Column(name = "PRICE_TYPE_CODE", length = 20)
	public String getPriceTypeCode() {
		return priceTypeCode;
	}

	public void setPriceTypeCode(String priceTypeCode) {
		this.priceTypeCode = priceTypeCode;
	}

	@Column(name = "REMARK", length = 500)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "PRICE_TYPE", length = 20)
	public String getPriceType() {
		return priceType;
	}

	public void setPriceType(String priceType) {
		this.priceType = priceType;
	}

	@Transient
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

}
