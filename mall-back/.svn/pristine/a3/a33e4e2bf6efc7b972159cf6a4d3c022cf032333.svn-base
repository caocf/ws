package com.cplatform.mall.back.lottery.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonProperty;

@Table(name = "T_LOTTERY_LOG")
@Entity
public class Logger {
	
	private Long id;
	private Long activeId;
	private String targetId;
	private String content;
	private String hitTime;
	private Long prizeId;
	
	@SequenceGenerator(name = "seq_lottery_log", sequenceName = "SEQ_LOTTERY_LOG")
	@Id
	@GeneratedValue(generator = "seq_lottery_log")
	@JsonProperty
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name = "ACTIVE_ID", precision = 8, scale = 0)
	public Long getActiveId() {
		return activeId;
	}
	public void setActiveId(Long activeId) {
		this.activeId = activeId;
	}
	@Column(name = "TARGET_ID", length = 21)
	public String getTargetId() {
		return targetId;
	}
	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}
	@Column(name = "CONTENT", length = 200)
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Column(name = "HIT_TIME", length = 6)
	public String getHitTime() {
		return hitTime;
	}
	public void setHitTime(String hitTime) {
		this.hitTime = hitTime;
	}
	@Column(name = "PRIZE_ID", precision = 8, scale = 0)
	public Long getPrizeId() {
		return prizeId;
	}
	public void setPrizeId(Long prizeId) {
		this.prizeId = prizeId;
	}
	
	private Long hitLevel;
	//private String phone;
	@Transient
	public Long getHitLevel() {
		return hitLevel;
	}
	@Transient
	public void setHitLevel(Long hitLevel) {
		this.hitLevel = hitLevel;
	}
//	@Transient
//	public String getPhone() {
//		return phone;
//	}
//	@Transient
//	public void setPhone(String phone) {
//		this.phone = phone;
//	}
	/** 查询用创建开始时间 **/
	private String beginTime;

	/** 查询用创建结束时间 **/
	private String endTime;
	

	@Transient
	public String getBeginTime() {
		return beginTime;
	}
	@Transient
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	@Transient
	public String getEndTime() {
		return endTime;
	}
	@Transient
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
}
