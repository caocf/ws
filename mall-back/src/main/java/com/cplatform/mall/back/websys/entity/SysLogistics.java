package com.cplatform.mall.back.websys.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.cplatform.mall.back.entity.IdEntity;

/**
 * 物流信息表 Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-6-4 下午5:15:01
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: LiuDong@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
@Entity
@Table(name = "t_sys_logistics")
public class SysLogistics extends IdEntity {

	private String name;
	private Long isValid;
	private String interFace;
	private String ename;

	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "IS_VALID")
	public Long getIsValid() {
		return isValid;
	}

	public void setIsValid(Long isValid) {
		this.isValid = isValid;
	}

	@Column(name = "INTERFACE")
	public String getInterFace() {
		return interFace;
	}

	public void setInterFace(String interFace) {
		this.interFace = interFace;
	}

	@Transient
	public String getIsValidName() {

		switch (this.getIsValid().intValue()) {
			case 0:
				return "无效";
			default:
				return "有效";

		}

	}
	@Column(name = "ename")
	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}
	

}
