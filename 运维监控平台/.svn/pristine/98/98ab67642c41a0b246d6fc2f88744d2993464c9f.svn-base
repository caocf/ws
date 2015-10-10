package com.cplatform.mall.dc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * T_TMP_JFBB_YWSR实体类
 * 
 * @author zhangdong
 * @since 2013-7-15
 */
@Entity
@Table(name = "T_TMP_JFBB_YWSR")
public class DcJfbbYwsr {

	private Long id;
	private String sdate;
	private String areaCode;
	private String areaName;
	private String cnt1;
	private String cnt2;
	private String cnt3;
	private String cnt4;
	private String cnt5;
	private String cnt6;

	@SequenceGenerator(name = "seq_dc_jfbb_ywsr", sequenceName = "SEQ_DC_LOGIN_LOG")
	@Id
	@GeneratedValue(generator = "seq_dc_jfbb_ywsr")
	@JsonProperty
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSdate() {
		return sdate;
	}

	public void setSdate(String sdate) {
		this.sdate = sdate;
	}

	@Column(name = "AREA_CODE")
	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	@Column(name = "AREA_NAME")
	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getCnt1() {
		return cnt1;
	}

	public void setCnt1(String cnt1) {
		this.cnt1 = cnt1;
	}

	public String getCnt2() {
		return cnt2;
	}

	public void setCnt2(String cnt2) {
		this.cnt2 = cnt2;
	}

	public String getCnt3() {
		return cnt3;
	}

	public void setCnt3(String cnt3) {
		this.cnt3 = cnt3;
	}

	public String getCnt4() {
		return cnt4;
	}

	public void setCnt4(String cnt4) {
		this.cnt4 = cnt4;
	}

	public String getCnt5() {
		return cnt5;
	}

	public void setCnt5(String cnt5) {
		this.cnt5 = cnt5;
	}

	public String getCnt6() {
		return cnt6;
	}

	public void setCnt6(String cnt6) {
		this.cnt6 = cnt6;
	}

}
