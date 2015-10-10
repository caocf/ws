package com.cplatform.mall.back.sys.entity;

import javax.persistence.Basic;
import javax.persistence.Entity;

import com.cplatform.mall.back.entity.IdEntity;

@javax.persistence.Table(name = "T_SYS_REGION")
@Entity
public class SysRegion extends IdEntity {

	private String regionCode;

	private String regionName;

	private Long regionLevel;

	private String parentRegion;

	private String shortName;

	private String regionSpell;

	private Long isShow;

	private Long sortNum;

	private String areaCode;

	// 全国区域
	public static SysRegion CHINA_REGION;

	static {
		CHINA_REGION = new SysRegion();
		CHINA_REGION.setParentRegion("0");
		CHINA_REGION.setRegionCode("");
		CHINA_REGION.setRegionName("全国");
		CHINA_REGION.setRegionLevel(0L);
		CHINA_REGION.setRegionSpell("china");
		CHINA_REGION.setSortNum(0L);
	}

	@javax.persistence.Column(name = "REGION_CODE")
	@Basic
	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	@javax.persistence.Column(name = "REGION_NAME")
	@Basic
	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	@javax.persistence.Column(name = "REGION_LEVEL")
	@Basic
	public Long getRegionLevel() {
		return regionLevel;
	}

	public void setRegionLevel(Long regionLevel) {
		this.regionLevel = regionLevel;
	}

	@javax.persistence.Column(name = "PARENT_REGION")
	@Basic
	public String getParentRegion() {
		return parentRegion;
	}

	public void setParentRegion(String parentRegion) {
		this.parentRegion = parentRegion;
	}

	@javax.persistence.Column(name = "SHORT_NAME")
	@Basic
	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	@javax.persistence.Column(name = "REGION_SPELL")
	@Basic
	public String getRegionSpell() {
		return regionSpell;
	}

	public void setRegionSpell(String regionSpell) {
		this.regionSpell = regionSpell;
	}

	@javax.persistence.Column(name = "IS_SHOW")
	@Basic
	public Long getIsShow() {
		return isShow;
	}

	public void setIsShow(Long isShow) {
		this.isShow = isShow;
	}

	@javax.persistence.Column(name = "SORT_NUM")
	@Basic
	public Long getSortNum() {
		return sortNum;
	}

	public void setSortNum(Long sortNum) {
		this.sortNum = sortNum;
	}

	@javax.persistence.Column(name = "area_code")
	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

}
