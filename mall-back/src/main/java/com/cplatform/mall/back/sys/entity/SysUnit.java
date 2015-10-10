package com.cplatform.mall.back.sys.entity;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.cplatform.mall.back.entity.IdEntity;

/**
 * 单位表Entity. <br>
 * 创建单位表实体类便于单位数据操纵.
 * <p>
 * Copyright: Copyright (c) 2013-5-22 上午09:24:27
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author zhaowei@c-platform.com
 * @version 1.0.0
 */
@Entity
@Table(name = "T_SYS_UNIT")
public class SysUnit extends IdEntity implements java.io.Serializable {

	/**
	 * 电商基地
	 */
	public static final Long UNIT_TYPE_BASE = 1l;
	




	/**
	 * 省
	 */
	public static final Long UNIT_TYPE_PROVINCE = 2l;

	/**
	 * 市
	 */
	public static final Long UNIT_TYPE_CITY = 3l;

	/** 单位名称 **/
	private String name;

	/** 单位归属地市，匹配T_SYS_region表中AREA_CODE **/
	private String areaCode;

	/** 单位分类，1 电商基地, 2 省移动,3 市单位 **/
	private Long unitType;

	/** 备注 **/
	private String remark;

	/** 单位标识，0表示正常，9表示注销 **/
	private Long flag;

	/** 单位变更用户ID，匹配T_SYS_USER_V2表中ID **/
	private Long updateUserId;

	/** 单位更新时间 **/
	private String updateTime;

	/** 单位所属省ID **/
	private Long provId;

	private Long parentUnitId;

	public static Map<Long, String> unitTypeMap = new HashMap<Long, String>();
	static {
		unitTypeMap.put(UNIT_TYPE_BASE, "电商基地");
		unitTypeMap.put(UNIT_TYPE_PROVINCE, "省单位");
		unitTypeMap.put(UNIT_TYPE_CITY, "市单位");
	}

	@Transient
	public String getUnitTypeName() {
		return unitTypeMap.get(this.getUnitType());
	}

	private String regionName;

	@Transient
	public String getRegionName() {
		if ("0".equals(this.getAreaCode())) {
			return "全国";
		}
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	@javax.persistence.Transient
	public String getFlagTyprName() {
		switch (this.getFlag().intValue()) {
			case 0:
				return "正常";
			default:
				return "已销户";

		}
	}

	/** default constructor */
	public SysUnit() {
	}

	/** minimal constructor */
	public SysUnit(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	// Property accessors
	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "NAME", nullable = false, length = 100)
	@Basic
	public String getName() {
		return name;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	@Column(name = "AREA_CODE", nullable = false, length = 8)
	@Basic
	public String getAreaCode() {
		return areaCode;
	}

	public void setUnitType(Long unitType) {
		this.unitType = unitType;
	}

	@Column(name = "UNIT_TYPE", nullable = false, precision = 1, scale = 0)
	@Basic
	public Long getUnitType() {
		return unitType;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "REMARK", nullable = true, length = 200)
	@Basic
	public String getRemark() {
		return remark;
	}

	public void setFlag(Long flag) {
		this.flag = flag;
	}

	@Column(name = "FLAG", nullable = false, precision = 1, scale = 0)
	@Basic
	public Long getFlag() {
		return flag;
	}

	public void setUpdateUserId(Long updateUserId) {
		this.updateUserId = updateUserId;
	}

	@Column(name = "UPDATE_USER_ID", nullable = false, precision = 9, scale = 0)
	@Basic
	public Long getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "UPDATE_TIME", nullable = false, length = 14)
	@Basic
	public String getUpdateTime() {
		return updateTime;
	}

	public void setProvId(Long provId) {
		this.provId = provId;
	}

	@Column(name = "PROV_ID", nullable = true, precision = 9, scale = 0)
	@Basic
	public Long getProvId() {
		return provId;
	}

	public void setParentUnitId(Long parentUnitId) {
		this.parentUnitId = parentUnitId;
	}

	@Column(name = "PARENT_UNIT_ID", nullable = true, precision = 9, scale = 0)
	@Basic
	public Long getParentUnitId() {
		return parentUnitId;
	}

}
