package com.cplatform.mall.back.lottery.entity;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonProperty;

@Table(name = "T_LOTTERY_PRIZE")
@Entity
public class Prize {
	private Long id;
	private String name;
	private Long activeId;
	private Long hitLevel;
	private String hitProbability;
	private Long hitLimit;
	private String hitMsg;
	private Long numbers;
	private String description;
	private String position;
	@SequenceGenerator(name = "seq_lottery_prize", sequenceName = "SEQ_LOTTERY_PRIZE")
	@Id
	@GeneratedValue(generator = "seq_lottery_prize")
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
	@Column(name = "HIT_LEVEL", precision = 2, scale = 0)
	public Long getHitLevel() {
		return hitLevel;
	}
	public void setHitLevel(Long hitLevel) {
		this.hitLevel = hitLevel;
	}
	@Column(name = "HIT_PROBABILITY", length = 5)
	public String getHitProbability() {
		return hitProbability;
	}
	public void setHitProbability(String hitProbability) {
		this.hitProbability = hitProbability;
	}
	@Column(name = "HIT_LIMIT", precision = 2, scale = 0)
	public Long getHitLimit() {
		return hitLimit;
	}
	public void setHitLimit(Long hitLimit) {
		this.hitLimit = hitLimit;
	}
	
	@Column(name = "HIT_MSG", length = 100)
	public String getHitMsg() {
		return hitMsg;
	}
	public void setHitMsg(String hitMsg) {
		this.hitMsg = hitMsg;
	}
	@Column(name = "NUMBERS", precision = 6, scale = 0)
	public Long getNumbers() {
		return numbers;
	}
	public void setNumbers(Long numbers) {
		this.numbers = numbers;
	}
	@Column(name = "NAME", length = 200)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "DESCRIPTION", length = 200)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name = "POSITION", length = 20)
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}



	public static Map<Long, String> statusMap = null;
	static {
		statusMap = new HashMap<Long, String>();
		statusMap.put(0L, "草稿");
		statusMap.put(1L, "审核中");
		statusMap.put(2L, "审核通过");
		statusMap.put(3L, "驳回");
	}
	@Transient
	public static Map<Long, String> getStatusMap() {
		return statusMap;
	}
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
//	@Transient
//	public String getStartTime() {
//		return startTime;
//	}
//	@Transient
//	public void setStartTime(String startTime) {
//		this.startTime = startTime;
//	}
//	@Transient
//	public String getStopTime() {
//		return stopTime;
//	}
//	@Transient
//	public void setStopTime(String stopTime) {
//		this.stopTime = stopTime;
//	}
	//private String startTime;
	//private String stopTime;
	private String activeName;
	private Long status;
	@Transient
	public String getActiveName() {
		return activeName;
	}
	@Transient
	public void setActiveName(String activeName) {
		this.activeName = activeName;
	}
	@Transient
	public Long getStatus() {
		return status;
	}
	@Transient
	public void setStatus(Long status) {
		this.status = status;
	}
	@Transient
	public String getStatusName() {
		return statusMap.get(this.status);
	}
	
}
