package com.cplatform.mall.back.order.entity;

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
 * 订单退款单管理表. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2013-10-28 下午07:46:48
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * @author luyd@c-platform.com
 * @version 1.0.0
 */
@Entity
@Table(name = "T_ORDER_REFUND_EXCEPTION")
public class OrderRefundException   implements java.io.Serializable{
	
	private static final long serialVersionUID = -2351319047706189302L;

	/**编号**/
	private Long id;
	 
	/**订单Id或者退款单id**/
	
	private  Long resourceId;
	
	/** 支付方式：现金、商品币、积分、话费或者其他**/
	private   String payment;
	
	
	/** 修改原因 0：清算系统反馈长款**/
	private   Long reason;
	
	/** 修改人**/
	private Long createUserId;
	
	
	/**用户名**/
	private   String userName;



	/**来源类型**/
	private Long resouceType;
	

	/** 修改时间**/
	private String createTime;
	 
	private String remark;
	

	@SequenceGenerator(name = "seq_orderRefundException", sequenceName = "seq_orderRefundException")
	@Id
	@GeneratedValue(generator = "seq_orderRefundException")
	@JsonProperty
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name = "PAYMENT")
	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	
	@Column(name = "REASON")
	public Long getReason() {
		return reason;
	}

	public void setReason(Long reason) {
		this.reason = reason;
	}
	@Column(name = "CREATE_USER_ID")
	public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	
	@Column(name = "CREATE_TIME")
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	
	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	
	@Column(name="RESOURCE_ID")
	public Long getResourceId() {
		return resourceId;
	}


	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}
	
	public static Map<Long, String> reasonMap = null;
	static {
		reasonMap = new HashMap<Long, String>();
		reasonMap.put(0L, "清算系统反馈时间长");
		reasonMap.put(1L, "系统出错");
	}
	
	@Transient
	public String getreasonName() {

		return reasonMap.get(this.getReason());
	}
	
	@Column(name="RESOUCE_TYPE")
	public Long getResouceType() {
		return resouceType;
	}
	public void setResouceType(Long resouceType) {
		this.resouceType = resouceType;
	}
	
	
	@Transient
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
