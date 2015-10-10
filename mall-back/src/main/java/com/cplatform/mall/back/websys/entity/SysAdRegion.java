package com.cplatform.mall.back.websys.entity;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.cplatform.mall.back.entity.IdEntity;

/**
 * 广告投放区域管理表. <br>
 * 用于存放数据库映射实体.
 * <p>
 * Copyright: Copyright (c) 2013-5-28 上午11:06:30
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author zhaowei@c-platform.com
 * @version 1.0.0
 */
@Entity
@Table(name = "T_SYS_AD_REGION")
public class SysAdRegion extends IdEntity implements java.io.Serializable {

	// Fields
	private Long id;

	private String regionCode;

	private Long adId;

	// Constructors

	public void setAdId(Long adId) {
		this.adId = adId;
	}

	@javax.persistence.Column(name = "AD_ID")
	@Basic
	public Long getAdId() {
		return adId;
	}

	@javax.persistence.Column(name = "region_code")
	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	// -------------bus method ---------------------
	private String regionName;
	
	@Transient
	public void setRegionName(String regionName) {
	    this.regionName = regionName;
    }
	@Transient
	public String getRegionName() {
	    return regionName;
    }
}
