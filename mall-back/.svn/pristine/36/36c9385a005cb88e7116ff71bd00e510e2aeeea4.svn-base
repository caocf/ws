package com.cplatform.mall.back.smsbuy.entity;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 短信购活动配置表. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-6-15 下午5:02:47
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: wuyn@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
@Entity
@Table(name = "T_SMSBUY_ACT_ONLINE")
public class SmsBuyActOnline {

	private Long actId;

	private String actName;

	private String actDesc;

	private String actArea;

	private String areaName;

	private String startTime;

	private String endTime;

	private String status;

	private Long storeId;

	private String storeName;

	private String spCode;

	private Long taskCount;

	@Transient
	public Long getTaskCount() {
		return taskCount;
	}

	public void setTaskCount(Long taskCount) {
		this.taskCount = taskCount;
	}

	/**
	 * 获取 spCode
	 * 
	 * @return spCode
	 */
	@Column(name = "SP_CODE")
	public String getSpCode() {
		return spCode;
	}

	/**
	 * 设置 spCode
	 * 
	 * @param spCode
	 */
	public void setSpCode(String spCode) {
		this.spCode = spCode;
	}

	/**
	 * 获取 statusName
	 * 
	 * @return statusName
	 */
	@Transient
	public String getStatusName() {
		return statusMap.get(status);
	}

	/**
	 * 获取 storeName
	 * 
	 * @return storeName
	 */
	@Transient
	public String getStoreName() {
		return storeName;
	}

	/**
	 * 设置 storeName
	 * 
	 * @param storeName
	 */
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	/**
	 * 获取 areaName
	 * 
	 * @return areaName
	 */
	@Transient
	public String getAreaName() {
		return areaName;
	}

	/**
	 * 设置 areaName
	 * 
	 * @param areaName
	 */
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	/**
	 * 获取 statusMap
	 * 
	 * @return statusMap
	 */
	public static Map<String, String> getStatusMap() {
		return statusMap;
	}

	/**
	 * 设置 statusMap
	 * 
	 * @param statusMap
	 */
	public static void setStatusMap(Map<String, String> statusMap) {
		SmsBuyActOnline.statusMap = statusMap;
	}

	public static Map<String, String> statusMap = new HashMap<String, String>();
	static {
		statusMap.put("audit", "待审核");// 下线
		statusMap.put("online", "上线");// 审核通过
		statusMap.put("rebutAudit", "审核驳回");
	}

	/**
	 * 获取 actId
	 * 
	 * @return actId
	 */
	@SequenceGenerator(name = "seq_smsbuyAct", sequenceName = "SEQ_SMSBUY_ITEM")
	@Id
	@GeneratedValue(generator = "seq_smsbuyAct")
	@ResponseBody
	public Long getActId() {
		return actId;
	}

	/**
	 * 设置 actId
	 * 
	 * @param actId
	 */
	public void setActId(Long actId) {
		this.actId = actId;
	}

	/**
	 * 获取 actName
	 * 
	 * @return actName
	 */
	@Column(name = "ACT_NAME")
	public String getActName() {
		return actName;
	}

	/**
	 * 设置 actName
	 * 
	 * @param actName
	 */
	public void setActName(String actName) {
		this.actName = actName;
	}

	/**
	 * 获取 actDesc
	 * 
	 * @return actDesc
	 */
	@Column(name = "ACT_DESC")
	public String getActDesc() {
		return actDesc;
	}

	/**
	 * 设置 actDesc
	 * 
	 * @param actDesc
	 */
	public void setActDesc(String actDesc) {
		this.actDesc = actDesc;
	}

	/**
	 * 获取 actArea
	 * 
	 * @return actArea
	 */
	@Column(name = "ACT_AREA")
	public String getActArea() {
		return actArea;
	}

	/**
	 * 设置 actArea
	 * 
	 * @param actArea
	 */
	public void setActArea(String actArea) {
		this.actArea = actArea;
	}

	/**
	 * 获取 startTime
	 * 
	 * @return startTime
	 */
	@Column(name = "START_TIME")
	public String getStartTime() {
		return startTime;
	}

	/**
	 * 设置 startTime
	 * 
	 * @param startTime
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * 获取 endTime
	 * 
	 * @return endTime
	 */
	@Column(name = "END_TIME")
	public String getEndTime() {
		return endTime;
	}

	/**
	 * 设置 endTime
	 * 
	 * @param endTime
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	/**
	 * 获取 status
	 * 
	 * @return status
	 */
	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}

	/**
	 * 设置 status
	 * 
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * 获取 storeId
	 * 
	 * @return storeId
	 */
	@Column(name = "STORE_ID")
	public Long getStoreId() {
		return storeId;
	}

	/**
	 * 设置 storeId
	 * 
	 * @param storeId
	 */
	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

}
