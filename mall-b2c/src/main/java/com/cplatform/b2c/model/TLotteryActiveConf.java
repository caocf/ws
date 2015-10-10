package com.cplatform.b2c.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 
 * @author jioajian
 *	活动配置
 */
@Entity
@Table(name = "T_LOTTERY_ACTIVE_CONF")
public class TLotteryActiveConf implements Serializable{
	
	
	private static final long serialVersionUID = 6277655327897327387L;

	private Integer id;
	
	private Integer activeId;
	
	private String key;
	
	private String value;
	
	@Id
	@Column(name = "ID", unique = true, nullable = false, precision = 9, scale = 0)
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "ACTIVE_ID")
	public Integer getActiveId() {
		return activeId;
	}
	
	
	public void setActiveId(Integer activeId) {
		this.activeId = activeId;
	}
	
	@Column(name = "KEY")
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
	@Column(name = "VALUE")
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
	
}
