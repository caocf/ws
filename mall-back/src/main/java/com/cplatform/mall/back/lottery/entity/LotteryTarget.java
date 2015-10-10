package com.cplatform.mall.back.lottery.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @Title	抽奖目标对象
 * @Description
 * @Copyright: Copyright (c) 2013-7-23
 * @Company: 北京宽连十方数字技术有限公司
 * @Author chencheng
 * @Version: 1.0
 *
 */
@Entity
@Table(name = "T_LOTTERY_TARGET")
public class LotteryTarget {
	/**
	 * 主键id
	 */
	private Long id;
	/**
	 * 活动id
	 */
	private Long activeId;
	/**
	 * 手机号码
	 */
	private String phone;
	/**
	 * 已中奖次数
	 */
	private Long numHad;
	/**
	 * 可中奖次数
	 */
	private Long numCould;
	
	@SequenceGenerator(name = "seq_lottery_target", sequenceName = "SEQ_LOTTERY_TARGET")
	@Id
	@GeneratedValue(generator = "seq_lottery_target")
	@JsonProperty
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name = "ACTIVE_ID")
	public Long getActiveId() {
		return activeId;
	}
	public void setActiveId(Long activeId) {
		this.activeId = activeId;
	}
	@Column(name = "PHONE")
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Column(name = "NUM_HAD")
	public Long getNumHad() {
		return numHad;
	}
	public void setNumHad(Long numHad) {
		this.numHad = numHad;
	}
	@Column(name = "NUM_COULD")
	public Long getNumCould() {
		return numCould;
	}
	public void setNumCould(Long numCould) {
		this.numCould = numCould;
	}
	

}
