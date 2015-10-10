package com.cplatform.mall.back.sys.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonProperty;

@Entity
@Table(name = "T_SYS_SEARCH_IDX")
public class SysSearchIdx {

	private Long id;

	private String type;

	private String typename;

	private String updatetime;

	private Long actor;

	private String usercode;

	private String username;

	@Transient
	public String getUsercode() {
		return usercode;
	}

	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}

	@Transient
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@SequenceGenerator(name = "seq_item", sequenceName = "SEQ_SYS_SEARCH_IDX")
	@Id
	@GeneratedValue(generator = "seq_item")
	@JsonProperty
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "type_name")
	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	@Column(name = "update_time")
	public String getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

	@Column(name = "actor")
	public Long getActor() {
		return actor;
	}

	public void setActor(Long actor) {
		this.actor = actor;
	}

}
