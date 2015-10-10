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
 * T_DC_ROLE_MENU实体类
 * 
 * @author zhuangxx@c-platform.com
 * @since 2013-7-13
 */
@Entity
@Table(name = "T_DC_ROLE_MENU")
public class DcRoleMenu implements Serializable {
	private static final long serialVersionUID = -2181143241833223895L;

	private Long id;
	private Long roleId;
	private String menuCode;

	@SequenceGenerator(name = "seq_dc_role_menu", sequenceName = "SEQ_DC_ROLE_MENU")
	@Id
	@GeneratedValue(generator = "seq_dc_role_menu")
	@JsonProperty
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "ROLE_ID")
	@Basic
	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	@Column(name = "MENU_CODE")
	@Basic
	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}
}
