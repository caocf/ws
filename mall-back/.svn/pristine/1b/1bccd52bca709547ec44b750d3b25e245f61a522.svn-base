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
 * 短信购商品指令配置实体类. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-6-16 下午3:22:12
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: wuyn@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
@Entity
@Table(name = "T_SMSBUY_ITEM_ROUTER")
public class SmsbuyItemRouter {

	private Long id;

	private Long actId;

	private String actName;

	private String itemId;

	private String itemName;

	private String rootSpcode;

	private String spCode;

	private Integer cmdOptType;

	private String command;

	private Integer payType;

	private Double itemPrice;

	private Integer isSession;

	private Long priority;

	private String marketMsg;

	private Long taskId;

	private String startTime;

	private String stopTime;

	private Long submitCnt;

	private Long status;

	private Long successCnt;

	private Long failCnt;

	private Long taskCount;

	@Transient
	public Long getTaskCount() {
		return taskCount;
	}

	public void setTaskCount(Long taskCount) {
		this.taskCount = taskCount;
	}

	@Transient
	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public String itemStatus;

	@Column(name = "ITEM_STATUS")
	public String getItemStatus() {
		return itemStatus;
	}

	public void setItemStatus(String itemStatus) {
		this.itemStatus = itemStatus;
	}

	/**
	 * 获取 itemStatusName
	 * 
	 * @return itemStatusName
	 */
	@Transient
	public String getItemStatusName() {
		return itemStatusMap.get(itemStatus);
	}

	public static Map<String, String> itemStatusMap = new HashMap<String, String>();
	static {
		itemStatusMap.put("audit", "待审核");// 下线
		itemStatusMap.put("online", "上线");// 审核通过
		itemStatusMap.put("rebutAudit", "审核驳回");
	}

	@Transient
	public Long getFailCnt() {
		return failCnt;
	}

	public void setFailCnt(Long failCnt) {
		this.failCnt = failCnt;
	}

	@Transient
	public Long getSuccessCnt() {
		return successCnt;
	}

	public void setSuccessCnt(Long successCnt) {
		this.successCnt = successCnt;
	}

	@Transient
	public Long getSubmitCnt() {
		return submitCnt;
	}

	public void setSubmitCnt(Long submitCnt) {
		this.submitCnt = submitCnt;
	}

	@Transient
	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	@Column(name = "MARKETMSG")
	public String getMarketMsg() {
		return marketMsg;
	}

	public void setMarketMsg(String marketMsg) {
		this.marketMsg = marketMsg;
	}

	/**
	 * 获取 actName
	 * 
	 * @return actName
	 */
	@Transient
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
	 * 获取 rootSpcode
	 * 
	 * @return rootSpcode
	 */
	@Transient
	public String getRootSpcode() {
		return rootSpcode;
	}

	@Transient
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	@Transient
	public String getStopTime() {
		return stopTime;
	}

	public void setStopTime(String stopTime) {
		this.stopTime = stopTime;
	}

	/**
	 * 设置 rootSpcode
	 * 
	 * @param rootSpcode
	 */
	public void setRootSpcode(String rootSpcode) {
		this.rootSpcode = rootSpcode;
	}

	/**
	 * 获取 cmdOptTypeName
	 * 
	 * @return cmdOptTypeName
	 */
	@Transient
	public String getCmdOptTypeName() {
		return cmdOptTypeMap.get(cmdOptType);
	}

	public static Map<Integer, String> cmdOptTypeMap = new HashMap<Integer, String>();
	static {
		cmdOptTypeMap.put(1, "入口指令");
		cmdOptTypeMap.put(2, "其它指令");
	}

	@Transient
	public String getPayTypeName() {
		return payTypeMap.get(payType);
	}

	public static Map<Integer, String> payTypeMap = new HashMap<Integer, String>();
	static {
		payTypeMap.put(1, "积分");
		payTypeMap.put(2, "商城币");
		payTypeMap.put(3, "话费");
	}

	/**
	 * 获取 id
	 * 
	 * @return id
	 */
	@SequenceGenerator(name = "seq_smsbuyAct", sequenceName = "SEQ_SMSBUY_ITEM")
	@Id
	@GeneratedValue(generator = "seq_smsbuyAct")
	@ResponseBody
	public Long getId() {
		return id;
	}

	/**
	 * 设置 id
	 * 
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 获取 actId
	 * 
	 * @return actId
	 */
	@Column(name = "ACT_ID")
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
	 * 获取 itemId
	 * 
	 * @return itemId
	 */
	@Column(name = "ITEM_ID")
	public String getItemId() {
		return itemId;
	}

	/**
	 * 设置 itemId
	 * 
	 * @param itemId
	 */
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	/**
	 * 获取 itemName
	 * 
	 * @return itemName
	 */
	@Column(name = "ITEM_NAME")
	public String getItemName() {
		return itemName;
	}

	/**
	 * 设置 itemName
	 * 
	 * @param itemName
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
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
	 * 获取 cmdOptType
	 * 
	 * @return cmdOptType
	 */
	@Column(name = "CMD_OPT_TYPE")
	public Integer getCmdOptType() {
		return cmdOptType;
	}

	/**
	 * 设置 cmdOptType
	 * 
	 * @param cmdOptType
	 */
	public void setCmdOptType(Integer cmdOptType) {
		this.cmdOptType = cmdOptType;
	}

	/**
	 * 获取 command
	 * 
	 * @return command
	 */
	@Column(name = "COMMAND")
	public String getCommand() {
		return command;
	}

	/**
	 * 设置 command
	 * 
	 * @param command
	 */
	public void setCommand(String command) {
		this.command = command;
	}

	/**
	 * 获取 payType
	 * 
	 * @return payType
	 */
	@Column(name = "PAY_TYPE")
	public Integer getPayType() {
		return payType;
	}

	/**
	 * 设置 payType
	 * 
	 * @param payType
	 */
	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	/**
	 * 获取 itemPrice
	 * 
	 * @return itemPrice
	 */
	@Column(name = "ITEM_PRICE")
	public Double getItemPrice() {
		return itemPrice;
	}

	/**
	 * 设置 itemPrice
	 * 
	 * @param itemPrice
	 */
	public void setItemPrice(Double itemPrice) {
		this.itemPrice = itemPrice;
	}

	/**
	 * 获取 isSession
	 * 
	 * @return isSession
	 */
	@Column(name = "IS_SESSION")
	public Integer getIsSession() {
		return isSession;
	}

	/**
	 * 设置 isSession
	 * 
	 * @param isSession
	 */
	public void setIsSession(Integer isSession) {
		this.isSession = isSession;
	}

	@Column(name = "PRIORITY")
	public Long getPriority() {
		return priority;
	}

	public void setPriority(Long priority) {
		this.priority = priority;
	}
}
