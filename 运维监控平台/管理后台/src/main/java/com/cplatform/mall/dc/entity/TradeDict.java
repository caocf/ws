package com.cplatform.mall.dc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonProperty;

@Entity
@Table(name = "T_SYS_TRADE_DICT")
public class TradeDict implements Serializable {

	private static final long serialVersionUID = -3305875403316135029L;

	private Long id;
	private Long pid;
	private String name;
	private Long sortLevel;

	@Id
	@GeneratedValue
	@JsonProperty
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "SORT_LEVEL")
	public Long getSortLevel() {
		return sortLevel;
	}

	public void setSortLevel(Long sortLevel) {
		this.sortLevel = sortLevel;
	}

}
