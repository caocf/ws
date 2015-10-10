package com.cplatform.b2c.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import net.sf.json.JSONObject;

/**
 * 业务订单的物流信息（送货地址）.<br>
 * 与业务订单为1:1的关系. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) Jun 14, 2013 9:00:57 AM
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author zhangyinf@c-platform.com
 * @version 1.0.0
 */
@Entity
@Table(name = "t_act_order_express")
public class TActOrderExpress implements Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = -2680122655463160355L;

	/** 业务订单Id */
	private long actOrderId;

	/** 收货地址 */
	private String address;

	/** 收货手机 */
	private String cellphoneNumber;

	/** 物流公司Id */
	private long expressCompanyId;

	/** 物流公司名称 */
	private String expressCompanyName;

	/** 运费，展示用，单位分 */
	private int expressCost;

	/** 快递货运单号 */
	private String expressNo;

	/** 记录id */
	private long id;

	/** 收货人名称 */
	private String receiverName;

	/** 收货时间（确认收货时间），14位时间 */
	private String receiveTime;

	/** 发送时间，14位时间 */
	private String sendTime;

	/** 当前状态 */
	private int status;

	/** 当前状态说 */
	private String statusDescription;

	/** 状态变更时间 */
	private String statusTime;

	/** 收货电话 */
	private String telephoneNumber;

	/** 邮政编码 */
	private String zipCode;

	private TActOrder order;

	@OneToOne
	@JoinColumn(name = "act_order_id", insertable = false, updatable = false, unique = true)
	public TActOrder getOrder() {
		return order;
	}

	public void setOrder(TActOrder order) {
		this.order = order;
	}

	/**
	 * 获取 actOrderId
	 * 
	 * @return actOrderId
	 */
	@Column(name = "act_order_id")
	public long getActOrderId() {
		return actOrderId;
	}

	/**
	 * 获取 address
	 * 
	 * @return address
	 */
	@Column(name = "address")
	public String getAddress() {
		return address;
	}

	/**
	 * 获取 cellphoneNumber
	 * 
	 * @return cellphoneNumber
	 */
	@Column(name = "cellphone_number")
	public String getCellphoneNumber() {
		return cellphoneNumber;
	}

	/**
	 * 获取 expressCompanyId
	 * 
	 * @return expressCompanyId
	 */
	@Column(name = "express_Company_Id")
	public long getExpressCompanyId() {
		return expressCompanyId;
	}

	/**
	 * 获取 expressCompanyName
	 * 
	 * @return expressCompanyName
	 */
	@Column(name = "express_Company_name")
	public String getExpressCompanyName() {
		return expressCompanyName;
	}

	/**
	 * 获取 expressCost
	 * 
	 * @return expressCost
	 */
	@Column(name = "express_cost")
	public int getExpressCost() {
		return expressCost;
	}

	/**
	 * 获取 expressNo
	 * 
	 * @return expressNo
	 */
	@Column(name = "express_no")
	public String getExpressNo() {
		return expressNo;
	}

	/**
	 * 获取 id
	 * 
	 * @return id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "gen")
	@SequenceGenerator(name = "gen", sequenceName = "seq_act_order_sub")
	@Column(name = "id")
	public long getId() {
		return id;
	}

	/**
	 * 获取 receiverName
	 * 
	 * @return receiverName
	 */
	@Column(name = "receiver_name")
	public String getReceiverName() {
		return receiverName;
	}

	/**
	 * 获取 receiveTime
	 * 
	 * @return receiveTime
	 */
	@Column(name = "receive_time", length = 14)
	public String getReceiveTime() {
		return receiveTime;
	}

	/**
	 * 获取 发送时间，14位时间
	 * 
	 * @return 发送时间，14位时间
	 */
	@Column(name = "send_time", length = 14)
	public String getSendTime() {
		return sendTime;
	}

	/**
	 * 获取 status
	 * 
	 * @return status
	 */
	@Column(name = "status")
	public int getStatus() {
		return status;
	}

	/**
	 * 获取 statusDescription
	 * 
	 * @return statusDescription
	 */
	@Column(name = "status_description")
	public String getStatusDescription() {
		return statusDescription;
	}

	/**
	 * 获取 statusTime
	 * 
	 * @return statusTime
	 */
	@Column(name = "status_time")
	public String getStatusTime() {
		return statusTime;
	}

	/**
	 * 获取 telephoneNumber
	 * 
	 * @return telephoneNumber
	 */
	@Column(name = "telephone_number")
	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	/**
	 * 获取 zipCode
	 * 
	 * @return zipCode
	 */
	@Column(name = "zip_code")
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * 设置 actOrderId
	 * 
	 * @param actOrderId
	 *            actOrderId
	 */
	public void setActOrderId(long actOrderId) {
		this.actOrderId = actOrderId;
	}

	/**
	 * 设置 address
	 * 
	 * @param address
	 *            address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * 设置 cellphoneNumber
	 * 
	 * @param cellphoneNumber
	 *            cellphoneNumber
	 */
	public void setCellphoneNumber(String cellphoneNumber) {
		this.cellphoneNumber = cellphoneNumber;
	}

	/**
	 * 设置 expressCompanyId
	 * 
	 * @param expressCompanyId
	 *            expressCompanyId
	 */
	public void setExpressCompanyId(long expressCompanyId) {
		this.expressCompanyId = expressCompanyId;
	}

	/**
	 * 设置 expressCompanyName
	 * 
	 * @param expressCompanyName
	 *            expressCompanyName
	 */
	public void setExpressCompanyName(String expressCompanyName) {
		this.expressCompanyName = expressCompanyName;
	}

	/**
	 * 设置 expressCost
	 * 
	 * @param expressCost
	 *            expressCost
	 */
	public void setExpressCost(int expressCost) {
		this.expressCost = expressCost;
	}

	/**
	 * 设置 expressNo
	 * 
	 * @param expressNo
	 *            expressNo
	 */
	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
	}

	/**
	 * 设置 id
	 * 
	 * @param id
	 *            id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * 设置 receiverName
	 * 
	 * @param receiverName
	 *            receiverName
	 */
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	/**
	 * 设置 receiveTime
	 * 
	 * @param receiveTime
	 *            receiveTime
	 */
	public void setReceiveTime(String receiveTime) {
		this.receiveTime = receiveTime;
	}

	/**
	 * 设置 发送时间，14位时间
	 * 
	 * @param sendTime
	 *            发送时间，14位时间
	 */
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	/**
	 * 设置 status
	 * 
	 * @param status
	 *            status
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * 设置 statusDescription
	 * 
	 * @param statusDescription
	 *            statusDescription
	 */
	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}

	/**
	 * 设置 statusTime
	 * 
	 * @param statusTime
	 *            statusTime
	 */
	public void setStatusTime(String statusTime) {
		this.statusTime = statusTime;
	}

	/**
	 * 设置 telephoneNumber
	 * 
	 * @param telephoneNumber
	 *            telephoneNumber
	 */
	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	/**
	 * 设置 zipCode
	 * 
	 * @param zipCode
	 *            zipCode
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	@Override
	public String toString() {
		try {
			return JSONObject.fromObject(this).toString();
		}
		catch (Exception ex) {
			return super.toString();
		}
	}
}
