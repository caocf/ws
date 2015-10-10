package com.cplatform.mall.back.store.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.cplatform.mall.back.entity.IdEntity;
/**
 * 
 * 业务门店标签关系表. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2013-5-31 下午03:58:28
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * @author zhaowei@c-platform.com
 * @version 1.0.0
 */
@Entity
@Table(name = "T_SHOP_TAG")
public class ShopTag extends IdEntity implements java.io.Serializable {
	
	/** 序列 **/
	private Long id;
	/** 类型 **/
	private Long shopClass;
	/** 门店ID **/
	private Long shopId;
	/** 标签属性内容 **/
	private String tag;
	
	public void setShopClass(Long shopClass) {
	    this.shopClass = shopClass;
    }
	@Column(name = "SHOP_CLASS")
	public Long getShopClass() {
	    return shopClass;
    }
	
	public void setShopId(Long shopId) {
	    this.shopId = shopId;
    }
	@Column(name = "SHOP_ID")
	public Long getShopId() {
	    return shopId;
    }
	
	public void setTag(String tag) {
	    this.tag = tag;
    }
	@Column(name = "TAG")
	public String getTag() {
	    return tag;
    }
	
	// -------------bus method ---------------------
	private String shopName;
	@Transient
	public void setShopName(String shopName) {
	    this.shopName = shopName;
    }
	@Transient
	public String getShopName() {
	    return shopName;
    }
	
}
