package com.cplatform.mall.back.store.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.cplatform.mall.back.entity.IdEntity;
import com.cplatform.mall.back.sys.entity.SysType;
/**
 * 
 * 业务门店商户分类关系表. <br>
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
@Table(name = "T_SHOP_TYPE_LINK")
public class ShopTypeLink extends IdEntity implements java.io.Serializable {
	
	/** serialVersionUID */
    private static final long serialVersionUID = -7188997103757344586L;
	/** 序列 **/
	private Long id;
	/** 门店ID **/
	private Long shopId;
	/** 分类ID **/
	private Long typeId;
	
	public void setShopId(Long shopId) {
	    this.shopId = shopId;
    }
	@Column(name = "SHOP_ID")
	public Long getShopId() {
	    return shopId;
    }

	public void setTypeId(Long typeId) {
	    this.typeId = typeId;
    }
	@Column(name = "TYPE_ID")
	public Long getTypeId() {
	    return typeId;
    }
	// -------------bus method ---------------------
	private SysType sysType;
	
	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "TYPE_ID", insertable = false, updatable = false)
	public SysType getSysType() {
		return sysType;
	}

	public void setSysType(SysType sysType) {
		this.sysType = sysType;
	}
}
