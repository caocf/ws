package com.cplatform.mall.back.smsact.entity;

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
/**
 * 二次开发短信业务配置表. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2013-5-29 下午07:46:48
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * @author zhaowei@c-platform.com
 * @version 1.0.0
 */
@Entity
@Table(name = "T_SMS_ACT_ONLINE")
public class SmsActOnline implements java.io.Serializable {
	/** serialVersionUID */
    private static final long serialVersionUID = 6508867334544572543L;
	/** 活动状态map */
	private static Map<String, String> statusMap = null;
	static {
		statusMap = new HashMap<String, String>();
		statusMap.put("0", "待审核");
		statusMap.put("1", "待发布");
		statusMap.put("2", "商用");
		statusMap.put("3", "暂停");
		statusMap.put("4", "下线");
	}	

	@Transient
	public static Map<String, String> getStatusMap() {
		return statusMap;
	}
	
	@Transient
	public String getStatusName() {
		return statusMap.get(this.getStatus() + "");
	}
	
	//活动状态
	/**
	 * 活动状态 － 待审核
	 */
	public static final String STATUS_0 = "0";
	/**
	 * 活动状态 － 待发布
	 */
	public static final String STATUS_1 = "1";
	/**
	 * 活动状态 － 商用
	 */
	public static final String STATUS_2 = "2";
	/**
	 * 活动状态 － 暂停
	 */
	public static final String STATUS_3 = "3";
	/**
	 * 活动状态 －下线
	 */
	public static final String STATUS_4 = "4";
	
	/** 活动编号 **/
	private Long actId;
	/** 活动名称 **/
	private String actName;
	/** 活动描述 **/
	private String actDesc;
	/** 活动分类 **/
	private String groupId;
	/** 活动开始时间 **/
	private String startTime;
	/** 活动结束时间 **/
	private String endTime;
	/** 活动状态 **/
	private String status;
	
	public void setActId(Long actId) {
		this.actId = actId;
	}
	@SequenceGenerator(name = "seq_sms_act_online", sequenceName = "SEQ_SMS_ACT_ONLINE")
	@Id
	@GeneratedValue(generator = "seq_sms_act_online")
	@JsonProperty
	public Long getActId() {
		return actId;
	}

	public void setActName(String actName) {
	    this.actName = actName;
    }
	@Column(name = "ACT_NAME")
	public String getActName() {
	    return actName;
    }

	public void setActDesc(String actDesc) {
	    this.actDesc = actDesc;
    }
	@Column(name = "ACT_DESC")
	public String getActDesc() {
	    return actDesc;
    }

	public void setGroupId(String groupId) {
	    this.groupId = groupId;
    }
	@Column(name = "GROUP_ID")
	public String getGroupId() {
	    return groupId;
    }

	public void setStartTime(String startTime) {
	    this.startTime = startTime;
    }
	@Column(name = "START_TIME")
	public String getStartTime() {
	    return startTime;
    }

	public void setEndTime(String endTime) {
	    this.endTime = endTime;
    }
	@Column(name = "END_TIME")
	public String getEndTime() {
	    return endTime;
    }

	public void setStatus(String status) {
	    this.status = status;
    }
	@Column(name = "STATUS")
	public String getStatus() {
	    return status;
    }
	
}
