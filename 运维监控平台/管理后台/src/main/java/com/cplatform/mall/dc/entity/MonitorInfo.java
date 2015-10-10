package com.cplatform.mall.dc.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * t_monitor_info实体类  工单
 * 
 * @author guyu
 * @since 2013-5-16
 */
@Entity
@Table(name = "T_MONITOR_INFO")
public class MonitorInfo implements Serializable {
	
	private static final long serialVersionUID = -7582222168065052576L;
	//seq 序列号
	private Long seqId;
	//平台名称  1:方正，2:高阳   3:宽连
	private String platName;
	
	public static Map<String,String> platMap = new HashMap<String,String>();
	static{
		platMap.put("方正", "1");
		platMap.put("高阳", "2");
		platMap.put("宽连", "3");
	}
	//告警级别 1，2，3，4，5
	private String monitorLevel;
	//告警类型，1:数据库，2:系统
	private Long monitorType;
	//告警内容
	private String monitor_content;
	//告警 是否展示 1:是  2:否
	private String show;
	//告警 是否工单 1:是  2:否
	private String work_order;
	//告警 是否短信 1:是  2:否
	private String send_sms;
	//指定处理人
	private String appoint_deal_name;
	//告警时间
	private String monitor_time;
	//入库时间
	private String collect_time;
	//告警提交人员
	private Long monitor_create_name;
	//是否已经处理   1:未处理  2:已处理 
	private String status;
	//告警内容处理
	private String deal_content;
	//告警处理时间
	private String deal_time;
	//告警处理人员
	private Long deal_name;
	//展示的平台
	private Long plat;
	
	//辅助文本，不入库
	private String txt;
	//辅助名称，不入库
	private String temp_name;
	
	@SequenceGenerator(name = "seq_monitor_info", sequenceName = "seq_monitor_info")
	@Id
	@GeneratedValue(generator = "seq_monitor_info")
	@JsonProperty
	public Long getSeqId() {
		return seqId;
	}
	public void setSeqId(Long seqId) {
		this.seqId = seqId;
	}
	
	@Column(name = "plat_name")
	@Basic
	public String getPlatName() {
		return platName;
	}
	public void setPlatName(String platName) {
		this.platName = platName;
	}
	
	@Column(name = "monitor_level")
	@Basic
	public String getMonitorLevel() {
		return monitorLevel;
	}
	public void setMonitorLevel(String monitorLevel) {
		this.monitorLevel = monitorLevel;
	}
	
	@Column(name = "monitor_type")
	@Basic
	public Long getMonitorType() {
		return monitorType;
	}
	public void setMonitorType(Long monitorType) {
		this.monitorType = monitorType;
	}
	
	@Column(name = "monitor_content")
	@Basic
	public String getMonitor_content() {
		return monitor_content;
	}
	public void setMonitor_content(String monitorContent) {
		monitor_content = monitorContent;
	}
	
	@Column(name = "show")
	@Basic
	public String getShow() {
		return show;
	}
	public void setShow(String show) {
		this.show = show;
	}
	
	@Column(name = "work_order")
	@Basic
	public String getWork_order() {
		return work_order;
	}
	public void setWork_order(String workOrder) {
		work_order = workOrder;
	}
	
	@Column(name = "send_sms")
	@Basic
	public String getSend_sms() {
		return send_sms;
	}
	public void setSend_sms(String sendSms) {
		send_sms = sendSms;
	}
	
	@Column(name = "appoint_deal_name")
	@Basic
	public String getAppoint_deal_name() {
		return appoint_deal_name;
	}
	public void setAppoint_deal_name(String appointDealName) {
		appoint_deal_name = appointDealName;
	}
	
	@Column(name = "monitor_time")
	@Basic
	public String getMonitor_time() {
		return monitor_time;
	}
	public void setMonitor_time(String monitorTime) {
		monitor_time = monitorTime;
	}
	
	@Column(name = "collect_time")
	@Basic
	public String getCollect_time() {
		return collect_time;
	}
	public void setCollect_time(String collectTime) {
		collect_time = collectTime;
	}
	
	@Column(name = "monitor_create_name")
	@Basic
	public Long getMonitor_create_name() {
		return monitor_create_name;
	}
	public void setMonitor_create_name(Long monitorCreateName) {
		monitor_create_name = monitorCreateName;
	}
	
	@Column(name = "status")
	@Basic
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name = "deal_content")
	@Basic
	public String getDeal_content() {
		return deal_content;
	}
	public void setDeal_content(String dealContent) {
		deal_content = dealContent;
	}
	
	@Column(name = "deal_time")
	@Basic
	public String getDeal_time() {
		return deal_time;
	}
	public void setDeal_time(String dealTime) {
		deal_time = dealTime;
	}
	
	@Column(name = "deal_name")
	@Basic
	public Long getDeal_name() {
		return deal_name;
	}
	public void setDeal_name(Long dealName) {
		deal_name = dealName;
	}
	
	@Column(name = "plat")
	@Basic
	public Long getPlat() {
		return plat;
	}
	public void setPlat(Long plat) {
		this.plat = plat;
	}
	
	@Transient
	public String getTxt() {
		return txt;
	}
	public void setTxt(String txt) {
		this.txt = txt;
	}
	
	@Transient
	public String getTemp_name() {
		return temp_name;
	}
	public void setTemp_name(String tempName) {
		temp_name = tempName;
	}
	
}
