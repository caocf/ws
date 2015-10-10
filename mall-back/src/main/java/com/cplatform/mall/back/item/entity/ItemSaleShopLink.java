package com.cplatform.mall.back.item.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 已发布商品销售门店表 Title. <br>
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
@Table(name = "T_ITEM_SALE_SHOP_LINK")
@Entity
public class ItemSaleShopLink {

	private Long id;

	/** 发布商品ID **/
	private Long saleId;

	/** 商户ID **/
	private Long storeId;

	/**
	 * 业务门店ID 如果是全商户门店都可以使用，则只存商户ID，该业务门店ID可以允许为空
	 **/
	private Long shopId;

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

	@Column(name = "SALE_ID", precision = 8, scale = 0)
	public Long getSaleId() {
		return saleId;
	}

	public void setSaleId(Long saleId) {
		this.saleId = saleId;
	}

	@Column(name = "STORE_ID", precision = 8, scale = 0)
	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	@Column(name = "SHOP_ID", precision = 8, scale = 0)
	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

}
