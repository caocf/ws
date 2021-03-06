package com.cplatform.mall.back.giftcard.entity;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @Title	卡兑换记录实体类
 * @Description
 * @Copyright: Copyright (c) 2013-9-26
 * @Company: 北京宽连十方数字技术有限公司
 * @Author chencheng
 * @Version: 1.0
 *
 */
@Entity
@Table(name="T_GIFT_CARD_CB_HANDLE")
public class GiftCardCbHandle {
	private Long id;
	/**
	 * 用户id
	 */
	private Long userId;
	/**
	 * 卡号
	 */
	private String serialNo;
	/**
	 * 礼品卡请求流水号
	 */
	private Long usId;
	/**
	 * 回调事件id
	 */
	private Long cbId;
	/**
	 * 状态，1：正常 2：重复的回调，不予处理，3:创建订单异常  
	 */
	private Long status;
	/**
	 * 订单id
	 */
	private Long orderId;
	private String orderResult;
	private String createTime;
	/**
	 * 卡需求批次号
	 */
	private Long batchNo;
	/**
	 * 卡型号
	 */
	private String modelNo;
	/**
	 * 礼品卡状态，0：未兑换 ，1：已兑换
	 */
	private Long cardStatus;
	/**
	 * 用户昵称
	 */
	private String userName;
	/**
	 * 用户手机号
	 */
	private String terminalId;
	/**
	 * 用户邮箱
	 */
	private String email;
	@Id
	public Long getId() {
		return id;
	}
	@Column(name="USER_ID")
	public Long getUserId() {
		return userId;
	}
	@Column(name="SERIAL_NO")
	public String getSerialNo() {
		return serialNo;
	}
	@Column(name="US_ID")
	public Long getUsId() {
		return usId;
	}
	@Column(name="CB_ID")
	public Long getCbId() {
		return cbId;
	}
	@Column(name="STATUS")
	public Long getStatus() {
		return status;
	}
	@Column(name="ORDER_ID")
	public Long getOrderId() {
		return orderId;
	}
	@Column(name="ORDER_RESULT")
	public String getOrderResult() {
		return orderResult;
	}
	@Column(name="CREATE_TIME")
	public String getCreateTime() {
		return createTime;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public void setUsId(Long usId) {
		this.usId = usId;
	}
	public void setCbId(Long cbId) {
		this.cbId = cbId;
	}
	public void setStatus(Long status) {
		this.status = status;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public void setOrderResult(String orderResult) {
		this.orderResult = orderResult;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public static Map<String, String> statusMap = null;
	static {
		statusMap = new HashMap<String, String>();
		statusMap.put("1", "已兑换");
		statusMap.put("2", "重复回调，不予解决");
		statusMap.put("3", "兑换异常");
		statusMap.put("4", "未兑换");
	}
	@Transient
	public String getStatusName(){
		return statusMap.get(this.getStatus()+"");
	}
	@Transient
	public Long getBatchNo() {
		return batchNo;
	}
	@Transient
	public String getModelNo() {
		return modelNo;
	}
	@Transient
	public Long getCardStatus() {
		return cardStatus;
	}
	public void setBatchNo(Long batchNo) {
		this.batchNo = batchNo;
	}
	public void setModelNo(String modelNo) {
		this.modelNo = modelNo;
	}
	public void setCardStatus(Long cardStatus) {
		this.cardStatus = cardStatus;
	}
	public static Map<String, String> cardStatusMap = null;
	static {
		cardStatusMap = new HashMap<String, String>();
		cardStatusMap.put("0", "未兑换");
		cardStatusMap.put("1", "已兑换");
	}
	@Transient
	public String getCardStatusName(){
		return cardStatusMap.get(this.getCardStatus()+"");
	}
	@Transient
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
	@Transient
	public String getTerminalId() {
		return terminalId;
	}
	@Transient
	public String getEmail() {
		return email;
	}
	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	

}
