package com.cplatform.mall.dc.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * T_DC_AREA实体类
 * 
 * @author zhuangxx@c-platform.com
 * @since 2013-7-15
 */
@Entity
@Table(name = "T_DC_AREA")
public class DcArea implements Serializable {
	private static final long serialVersionUID = -62523417284888346L;

	private Long id;
	private String areaCode;
	private String areaName;
	private Long provinceId;
	private String provinceName;

	@SequenceGenerator(name = "seq_dc_role", sequenceName = "SEQ_DC_ROLE")
	@Id
	@GeneratedValue(generator = "seq_dc_role")
	@JsonProperty
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "AREA_CODE")
	@Basic
	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	@Column(name = "AREA_NAME")
	@Basic
	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	@Column(name = "PROVINCE_ID")
	@Basic
	public Long getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}

	@Column(name = "PROVINCE_NAME")
	@Basic
	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
}
