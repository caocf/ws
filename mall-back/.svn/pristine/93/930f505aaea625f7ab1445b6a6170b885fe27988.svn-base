package com.cplatform.mall.back.sys.entity;

// default package

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.cplatform.mall.back.entity.IdEntity;

/**
 * Title. 系统角色表<br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-5-21 下午5:22:48
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: BaiJie
 * <p>
 * Version: 1.0
 * <p>
 */
@Entity
@Table(name = "T_SYS_ROLE")
public class SysRole extends IdEntity {

	// Fields

	private String roleName;

	private Long updateUserId;

	private String remark;

	private String updateTime;
	
	private String menuPrivilege;

	@Transient
    public String getMenuPrivilege() {
    	return menuPrivilege;
    }

	
    public void setMenuPrivilege(String menuPrivilege) {
    	this.menuPrivilege = menuPrivilege;
    }

	@Column(name = "UPDATE_TIME", length = 14)
	@Basic
	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	private Long unitId;

	@Column(name = "ROLE_NAME", nullable = false, length = 50)
	@Basic
	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Column(name = "REMARK", length = 200)
	@Basic
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "update_user_id")
	@Basic
	public Long getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(Long updateUserId) {
		this.updateUserId = updateUserId;
	}

	@Column(name = "UNIT_ID")
	@Basic
	public Long getUnitId() {
		return unitId;
	}

	public void setUnitId(Long unitId) {
		this.unitId = unitId;
	}

}