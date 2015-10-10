package com.cplatform.b2c.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_LOTTERY_PRIZE")
public class TLotteryPrize implements Serializable{

	private static final long serialVersionUID = 1203715263830301813L;
	
	private Integer id;
	private Integer activeId;
	private Integer hitLevel;
	private String hitProbability;
	private Integer hitLimit;
	private String hitMsg;
	private Integer numbers;
	private String name;
	private String description;
	private String position;
	private Integer used;
	
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
	
	@Column(name = "HIT_LEVEL")
	public Integer getHitLevel() {
		return hitLevel;
	}
	public void setHitLevel(Integer hitLevel) {
		this.hitLevel = hitLevel;
	}
	
	@Column(name = "HIT_PROBABILITY")
	public String getHitProbability() {
		return hitProbability;
	}
	public void setHitProbability(String hitProbability) {
		this.hitProbability = hitProbability;
	}
	
	@Column(name = "HIT_LIMIT")
	public Integer getHitLimit() {
		return hitLimit;
	}
	public void setHitLimit(Integer hitLimit) {
		this.hitLimit = hitLimit;
	}
	
	@Column(name = "HIT_MSG")
	public String getHitMsg() {
		return hitMsg;
	}
	public void setHitMsg(String hitMsg) {
		this.hitMsg = hitMsg;
	}
	
	@Column(name = "NUMBERS")
	public Integer getNumbers() {
		return numbers;
	}
	public void setNumbers(Integer numbers) {
		this.numbers = numbers;
	}
	
	@Column(name = "NAME")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name = "POSITION")
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	
	@Column(name = "USED")
	public Integer getUsed() {
		return used;
	}
	public void setUsed(Integer used) {
		this.used = used;
	}
	
	

}
