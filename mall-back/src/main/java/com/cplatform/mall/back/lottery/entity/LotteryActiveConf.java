package com.cplatform.mall.back.lottery.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @Title	活动抽奖配置
 * @Description
 * @Copyright: Copyright (c) 2013-7-26
 * @Company: 北京宽连十方数字技术有限公司
 * @Author chencheng
 * @Version: 1.0
 *
 */
@Entity
@Table(name = "T_LOTTERY_ACTIVE_CONF")
public class LotteryActiveConf {
	/**
	 * 主键id
	 */
	private Long id;
	/**
	 * 活动id
	 */
	private Long activeId;
	/**
	 * 活动抽奖key
	 */
	private String key;
	/**
	 * 活动抽奖value
	 */
	private String value;
	@SequenceGenerator(name = "seq_lottery_active_conf", sequenceName = "SEQ_LOTTERY_ACTIVE_CONF")
	@Id
	@GeneratedValue(generator = "seq_lottery_active_conf")
	@JsonProperty
	public Long getId() {
		return id;
	}
	@Column(name = "ACTIVE_ID")
	public Long getActiveId() {
		return activeId;
	}
	@Column(name = "KEY")
	public String getKey() {
		return key;
	}
	@Column(name = "VALUE")
	public String getValue() {
		return value;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setActiveId(Long activeId) {
		this.activeId = activeId;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	

}
