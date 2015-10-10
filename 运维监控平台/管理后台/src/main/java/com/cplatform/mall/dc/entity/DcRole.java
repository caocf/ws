package com.cplatform.mall.dc.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * T_DC_ROLE实体类
 * 
 * @author zhuangxx@c-platform.com
 * @since 2013-7-11
 */
@Entity
@Table(name = "T_DC_ROLE")
public class DcRole implements Serializable {
	private static final long serialVersionUID = -1845587558037594240L;
	
	public static final int STATUS_USING = 1;
	public static final int STATUS_BLOCK = 0;

	private Long id;
	private String roleName;
	private String remark;
	private int status;

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

	@Column(name = "ROLE_NAME")
	@Basic
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Column(name = "REMARK")
	@Basic
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "STATUS")
	@Basic
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * 角色拥有的菜单权限
	 */
	private List<String> menus;

	@Transient
	public List<String> getMenus() {
		return menus;
	}

	@Transient
	public void setMenus(List<String> menus) {
		this.menus = menus;
	}

	/**
	 * 记录该角色是否存在
	 */
	private Boolean flag;

	@Transient
	public Boolean getFlag() {
		return flag;
	}

	@Transient
	public void setFlag(Boolean flag) {
		this.flag = flag;
	}
}
