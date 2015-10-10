package com.cplatform.mall.back.giftcard.entity;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.cplatform.mall.back.entity.IdEntity;


/**
 * Title. <br>
 * 礼品卡出库申请表
 * <p>
 * Copyright: Copyright (c) 2013-6-8
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: mudeng@ca-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
@Entity
@Table(name = "T_GIFT_APPLY_OUT")
public class GiftApplyOut extends IdEntity implements java.io.Serializable {

	/**
	 * 
	 */
	public static Map<Long, String> typeMap = null;
	static {
		typeMap = new HashMap<Long, String>();
		typeMap.put(0L, "线上申请");
		typeMap.put(1L, "线下申请");
	}
	public static Map<Integer, String> payStatusMap = null;
	static {
		payStatusMap = new HashMap<Integer, String>();
		payStatusMap.put(0, "未支付");
		payStatusMap.put(2, "已支付");
	}
	public static Map<Long, String> statusMap = null;
	static {
		statusMap = new HashMap<Long, String>();
		statusMap.put(0L, "未审核");
		statusMap.put(1L, "审核通过 ");
		statusMap.put(2L, "审核驳回");
		statusMap.put(3L, "已删除 ");
		statusMap.put(4L, "已出库");
		statusMap.put(5L, "已激活");
//		statusMap.put(6L, "已发卡");
	}
	private static final long serialVersionUID = 1L;
	private Long type;	 		//申请类型,0：线上申请，1：线下申请
	private Long applyUser;			//申请人
	private String applyUserName;			//申请人
	private String applyTime;			//申请时间
	private Long orderId;		//支付订单
	private Integer payStatus;		//支付状态，0未支付，1已支付
	private Double payment;			//金额,分
	private String payUnit;			//付款单位全称
	private String payBank;		//付款行名称
	private String contactName;		//联系人
	private String guarantorName;		//担保人
	private String address;				//收货地址 
	private String cellphoneNumber;		//联系电话
	private String zipCode;		//邮政编码
	private String accountedName;		//入账人
	private String accountedCode;	//入账单号
	private String accountedTime;		//入账日期
	private Long status;			//状态 0：未审核 1：审核通过 2：审核驳回 3:已删除 4：已出库 5：已激活 6:已发卡
	private Long auditUser;			//审核人
	private String auditTime;			//审核时间
	private String auditAdvice;		//审核意见
	private Long outStatus;		//出库状态
	private Long activeStatus;	//激活状态
	private String remark;		//备注
	private String receiveName;			//出库领用人

	@Transient
	public String getApplyUserName() {
		return applyUserName;
	}
	public void setApplyUserName(String applyUserName) {
		this.applyUserName = applyUserName;
	}
	@Transient
	public String getTypeName() {
		return typeMap.get(type);
	}
	@Transient
	public String getPayStatusName() {
		return payStatusMap.get(payStatus);
	}
	@Transient
	public String getStatusName() {
		return statusMap.get(status);
	}
	
	@Column(name="type")
	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}
	@Column(name="apply_user")
	public Long getApplyUser() {
		return applyUser;
	}

	public void setApplyUser(Long applyUser) {
		this.applyUser = applyUser;
	}
	@Column(name="apply_time")
	public String getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}
	@Column(name="order_id")
	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	@Column(name="pay_status")
	public Integer getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}
	@Column(name="payment")
	public Double getPayment() {
		return payment;
	}

	public void setPayment(Double payment) {
		this.payment = payment;
	}
	@Column(name="pay_unit")
	public String getPayUnit() {
		return payUnit;
	}

	public void setPayUnit(String payUnit) {
		this.payUnit = payUnit;
	}
	@Column(name="pay_bank")
	public String getPayBank() {
		return payBank;
	}

	public void setPayBank(String payBank) {
		this.payBank = payBank;
	}
	@Column(name="contact_name")
	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	@Column(name="guarantor_name")
	public String getGuarantorName() {
		return guarantorName;
	}

	public void setGuarantorName(String guarantorName) {
		this.guarantorName = guarantorName;
	}
	@Column(name="address")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	@Column(name="cellphone_number")
	public String getCellphoneNumber() {
		return cellphoneNumber;
	}

	public void setCellphoneNumber(String cellphoneNumber) {
		this.cellphoneNumber = cellphoneNumber;
	}
	@Column(name="zip_code")
	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	@Column(name="accounted_name")
	public String getAccountedName() {
		return accountedName;
	}

	public void setAccountedName(String accountedName) {
		this.accountedName = accountedName;
	}
	@Column(name="accounted_code")
	public String getAccountedCode() {
		return accountedCode;
	}

	public void setAccountedCode(String accountedCode) {
		this.accountedCode = accountedCode;
	}
	@Column(name="accounted_time")
	public String getAccountedTime() {
		return accountedTime;
	}

	public void setAccountedTime(String accountedTime) {
		this.accountedTime = accountedTime;
	}
	@Column(name="status")
	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}
	@Column(name="audit_user")
	public Long getAuditUser() {
		return auditUser;
	}

	public void setAuditUser(Long auditUser) {
		this.auditUser = auditUser;
	}
	@Column(name="audit_time")
	public String getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(String auditTime) {
		this.auditTime = auditTime;
	}
	@Column(name="audit_advice")
	public String getAuditAdvice() {
		return auditAdvice;
	}

	public void setAuditAdvice(String auditAdvice) {
		this.auditAdvice = auditAdvice;
	}
	@Column(name="out_status")
	public Long getOutStatus() {
		return outStatus;
	}

	public void setOutStatus(Long outStatus) {
		this.outStatus = outStatus;
	}
	@Column(name="active_status")
	public Long getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(Long activeStatus) {
		this.activeStatus = activeStatus;
	}

	@Column(name="remark")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Column(name="receive_name")
	public String getReceiveName() {
		return receiveName;
	}

	public void setReceiveName(String receiveName) {
		this.receiveName = receiveName;
	}
	
}
