package com.cplatform.mall.back.sys.entity;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.cplatform.mall.back.entity.IdEntity;

@Entity
@Table(name = "T_SYS_ROLE_PRIVILEGE")
public class SysRolePrivilege extends IdEntity {
	
	private Long roleId;
	private String menuCode;
	private String menuBtn;
	
	@javax.persistence.Column(name = "ROLE_ID")
	@Basic
    public Long getRoleId() {
    	return roleId;
    }
	
    public void setRoleId(Long roleId) {
    	this.roleId = roleId;
    }
    @javax.persistence.Column(name = "MENU_CODE")
	@Basic
    public String getMenuCode() {
    	return menuCode;
    }
	
    public void setMenuCode(String menuCode) {
    	this.menuCode = menuCode;
    }
    @javax.persistence.Column(name = "MENU_BTN")
   	@Basic
    public String getMenuBtn() {
    	return menuBtn;
    }
	
    public void setMenuBtn(String menuBtn) {
    	this.menuBtn = menuBtn;
    }

}
