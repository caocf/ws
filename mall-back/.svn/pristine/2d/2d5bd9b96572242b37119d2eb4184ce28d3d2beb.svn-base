package com.cplatform.mall.back.sys.entity;

// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.cplatform.mall.back.entity.IdEntity;

/**
 * Title.系统区域表 <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-5-21 下午5:21:38
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: BaiJie
 * <p>
 * Version: 1.0
 * <p>
 */
@Entity
@Table(name = "T_SYS_AREA_INFO")
public class SysAreaInfo extends IdEntity implements java.io.Serializable {

	// Fields

	private Integer id;

	private String areaCode;

	private String areaName;

	private String provinceName;

	// 地市全拼
	private String areaSpell;

	// 所属省ID
	private Long provId;

	// Constructors

	/** default constructor */
	public SysAreaInfo() {
	}

	/** minimal constructor */
	public SysAreaInfo(Integer id, String areaCode, String areaName, String provinceName) {
		this.id = id;
		this.areaCode = areaCode;
		this.areaName = areaName;
		this.provinceName = provinceName;
	}

	// Property accessors

	@Column(name = "AREA_CODE", nullable = false, length = 8)
	public String getAreaCode() {
		return this.areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	@Column(name = "AREA_NAME", nullable = false, length = 20)
	public String getAreaName() {
		return this.areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	@Column(name = "PROVINCE_NAME", nullable = false, length = 20)
	public String getProvinceName() {
		return this.provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	@Column(name = "area_spell")
	public String getAreaSpell() {
		return areaSpell;
	}

	public void setAreaSpell(String areaSpell) {
		this.areaSpell = areaSpell;
	}

	@Column(name = "prov_id")
	public Long getProvId() {
		return provId;
	}

	public void setProvId(Long provId) {
		this.provId = provId;
	}

}