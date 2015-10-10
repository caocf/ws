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
 * T_DC_USER_ROLE实体类
 * 
 * @author zhangdong
 * @since 2013-7-13
 */
@Entity
@Table(name = "T_DC_USER_ROLE")
public class DcUserRole implements Serializable {
	private static final long serialVersionUID = -6539902240641035324L;

	private Long id;
	private Long userId;
	private Long roleId;

	@SequenceGenerator(name = "seq_user_role", sequenceName = "SEQ_DC_USER_ROLE")
	@Id
	@GeneratedValue(generator = "seq_user_role")
	@JsonProperty
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "USER_ID")
	@Basic
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "ROLE_ID")
	@Basic
	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
}
