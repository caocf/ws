package com.cplatform.mall.dc.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * T_DC_MENU实体类
 * 
 * @author zhuangxx@c-platform.com
 * @since 2013-7-10
 */
@Entity
@Table(name = "T_DC_MENU")
public class DcMenu implements Serializable {
	private static final long serialVersionUID = -6668597656314912594L;

	private String menuCode;
	private String menuName;
	private String parentCode;
	private String menuUrl;
	private boolean leafYn;

	@Column(name = "MENU_CODE")
	@Id
	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	@Column(name = "MENU_NAME")
	@Basic
	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	@Column(name = "PARENT_CODE")
	@Basic
	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	@Column(name = "MENU_URL")
	@Basic
	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	@Column(name = "LEAF_YN")
	@Basic
	public boolean isLeafYn() {
		return leafYn;
	}

	public void setLeafYn(boolean leafYn) {
		this.leafYn = leafYn;
	}
}
