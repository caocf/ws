package com.cplatform.b2c.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * TMemberAddress entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_MEMBER_ADDRESS")
public class TMemberAddress implements java.io.Serializable {

	// Fields

	/** serialVersionUID */
	private static final long serialVersionUID = 8146979151180047857L;

	private Integer id;

	private Integer mid;

	private String remark;

	private String region;

	private String address;

	private String zipcode;

	private String name;

	private String mobile;

	private String phone;

	private String updateTime;

	private String createTime;

	private String lastUseTime;

	private String defaultShipping;

	private String defaultPayType;

	// Constructors

	/** default constructor */
	public TMemberAddress() {
	}

	/** minimal constructor */
	public TMemberAddress(Integer id, Integer mid, String region, String address, String name) {
		this.id = id;
		this.mid = mid;
		this.region = region;
		this.address = address;
		this.name = name;
	}

	/** full constructor */
	public TMemberAddress(Integer id, Integer mid, String remark, String region, String address, String zipcode, String name, String mobile,
	                      String phone, String updateTime, String createTime, String lastUseTime, String defaultShipping, String defaultPayType) {
		this.id = id;
		this.mid = mid;
		this.remark = remark;
		this.region = region;
		this.address = address;
		this.zipcode = zipcode;
		this.name = name;
		this.mobile = mobile;
		this.phone = phone;
		this.updateTime = updateTime;
		this.createTime = createTime;
		this.lastUseTime = lastUseTime;
		this.defaultShipping = defaultShipping;
		this.defaultPayType = defaultPayType;
	}

	// Property accessors
	@SequenceGenerator(name = "seq_comm", sequenceName = "SEQ_SYS_COMM_ID")
	@Id
	@GeneratedValue(generator = "seq_comm")
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "MID")
	public Integer getMid() {
		return this.mid;
	}

	public void setMid(Integer mid) {
		this.mid = mid;
	}

	@Column(name = "REMARK", length = 10)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "REGION")
	public String getRegion() {
		return this.region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	@Column(name = "ADDRESS")
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "ZIPCODE", length = 10)
	public String getZipcode() {
		return this.zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	@Column(name = "NAME")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "MOBILE", length = 20)
	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "PHONE", length = 20)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "UPDATE_TIME")
	public String getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "CREATE_TIME")
	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Column(name = "LAST_USE_TIME")
	public String getLastUseTime() {
		return this.lastUseTime;
	}

	public void setLastUseTime(String lastUseTime) {
		this.lastUseTime = lastUseTime;
	}

	@Column(name = "DEFAULT_SHIPPING")
	public String getDefaultShipping() {
		return this.defaultShipping;
	}

	public void setDefaultShipping(String defaultShipping) {
		this.defaultShipping = defaultShipping;
	}

	@Column(name = "DEFAULT_PAY_TYPE")
	public String getDefaultPayType() {
		return this.defaultPayType;
	}

	public void setDefaultPayType(String defaultPayType) {
		this.defaultPayType = defaultPayType;
	}

}