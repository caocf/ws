package com.cplatform.mall.back.sys.entity;

// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.cplatform.mall.back.entity.IdEntity;

/**
 * Title.系统号段表 <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-5-21 下午5:23:12
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: BaiJie
 * <p>
 * Version: 1.0
 * <p>
 */
@Entity
@Table(name = "T_SYS_SEGMENT")
public class SysSegment extends IdEntity implements java.io.Serializable {

	// Fields

	private Integer id;

	private String segmentCode;

	private String operatorCode;

	private String areaCode;

	private String mmscId;

	// Constructors

	/** default constructor */
	public SysSegment() {
	}

	/** full constructor */
	public SysSegment(String segmentCode, String operatorCode, String areaCode, String mmscId) {
		this.segmentCode = segmentCode;
		this.operatorCode = operatorCode;
		this.areaCode = areaCode;
		this.mmscId = mmscId;
	}

	// Property accessors

	@Column(name = "SEGMENT_CODE", nullable = false, length = 10)
	public String getSegmentCode() {
		return this.segmentCode;
	}

	public void setSegmentCode(String segmentCode) {
		this.segmentCode = segmentCode;
	}

	@Column(name = "OPERATOR_CODE", nullable = false, length = 50)
	public String getOperatorCode() {
		return this.operatorCode;
	}

	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}

	@Column(name = "AREA_CODE", nullable = false, length = 8)
	public String getAreaCode() {
		return this.areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	@Column(name = "MMSC_ID", nullable = false, length = 20)
	public String getMmscId() {
		return this.mmscId;
	}

	public void setMmscId(String mmscId) {
		this.mmscId = mmscId;
	}

}