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
 * Copyright: Copyright (c) 2013-5-29 下午07:46:48
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * @author zhaowei@c-platform.com
 * @version 1.0.0
 */
@Entity
@Table(name = "T_ORDER_REFUND")
public class OrderRefund implements java.io.Serializable {
	/** serialVersionUID */
    private static final long serialVersionUID = -3117835077668316602L;
	/** 状态map
	 * 1等待商户确认                                    表示管理人员发起退款单
	 * 2等待审核 / 3商户拒绝确认       表示商户的意见
	 * 4等待系统退款 / 5审核失败       表示移动人员对退款的审核意见
	 * 6退款成功 / 7退款失败                 表示用户的钱是否已经退还
	 */
	private static Map<String, String> statusMap = null;
	static {
		statusMap = new HashMap<String, String>();
		statusMap.put("1", "等待商户确认");
		statusMap.put("2", "等待审核");
		statusMap.put("3", "商户拒绝确认");
		statusMap.put("4", "等待系统退款");
		statusMap.put("5", "审核失败 ");
		statusMap.put("6", "退款成功");
		statusMap.put("7", "退款失败");
	}
	@Transient
	public static Map<String, String> getStatusMap() {
		return statusMap;
	}

	@Transient
	public String getStatusName() {
		return statusMap.get(this.getStatus() + "");
	}
	//状态
	/**
	 * 状态 － 等待商户确认
	 */
	public static final Long STATUS_1 = 1L;
	/**
	 * 状态 － 等待审核
	 */
	public static final Long STATUS_2 = 2L;
	/**
	 * 状态 － 商户拒绝确认
	 */
	public static final Long STATUS_3 = 3L;
	/**
	 * 状态 － 等待系统退款
	 */
	public static final Long STATUS_4 = 4L;
	/**
	 * 状态 － 审核失败
	 */
	public static final Long STATUS_5 = 5L;
	/**
	 * 状态 － 退款成功
	 */
	public static final Long STATUS_6 = 6L;
	/**
	 * 状态 － 退款失败
	 */
	public static final Long STATUS_7 = 7L;
	
	/** 序列 **/
	private Long id;
	/** 订单id **/
	private Long orderId;
	/** 商户id **/
	private Long storeId;
	/** 状态 **/
	private Long status;
	/** 实际退款金额 **/
	private Double refundFee;
	/**
	 * 实际退款现金
	 */
	private Double cashFee;
	/**
	 * 实际退款商城币
	 */
	private Double coinFee;
	/** 申请时间 **/
	private String createTime;
	/** 操作人 **/
	private Long userId;
	/** 申请原因 **/
	private String reason;
	/** 商户操作人 **/
	private Long shopUserId;
	/** 商户操作时间 **/
	private String updateTime;
	/** 商户说明 **/
	private String shopRemark;
	/** 审核人 **/
	private Long auditUserId;
	/** 审核时间 **/
	private String auditTime;
	/** 审核说明 **/
	private String auditRemark;
	/** 处理时间 **/
	private String dealTime;
	/**
	 * 总共退码数量
	 */
	private Long totalCode;
	/**
	 * 成功退码数量
	 */
	private Long successCode;
	/**
	 * 失败退码数量
	 */
	private Long failCode;
	
	/** 用户手机号码 **/
	private String terminalId;	
	
	private Long orderRecordcashId;
	
	private Long orderRecordcoinId;
	
	private Long orderRecordscoreId;
	
	
	private Double scoreFee;
	
	private Double phoneFee;
	
	private Long orderRecordphoneId;
	
	/**
	 * 退款单类型，1平台自己，2拉手
	 */
	private Long refundType;
	
	
	private static Map<String, String> refundTypeMap = null;
	static {
		refundTypeMap = new HashMap<String, String>();
		refundTypeMap.put("1", "平台自己");
		refundTypeMap.put("2", "拉手");
	}
	
	
	public static Map<String, String> getRefundTypeMap() {
		return refundTypeMap;
	}

	public static void setRefundTypeMap(Map<String, String> refundTypeMap) {
		OrderRefund.refundTypeMap = refundTypeMap;
	}

	public void setId(Long id) {
		this.id = id;
	}
	@SequenceGenerator(name = "seq_order_refund", sequenceName = "SEQ_ORDER_REFUND")
	@Id
	@GeneratedValue(generator = "seq_order_refund")
	@JsonProperty
	public Long getId() {
		return id;
	}
	
	public void setOrderId(Long orderId) {
	    this.orderId = orderId;
    }
	@Column(name = "ORDER_ID")
	public Long getOrderId() {
	    return orderId;
    }

	public void setStoreId(Long storeId) {
	    this.storeId = storeId;
    }
	@Column(name = "STORE_ID")
	public Long getStoreId() {
	    return storeId;
    }

	public void setStatus(Long status) {
	    this.status = status;
    }
	@Column(name = "STATUS")
	public Long getStatus() {
	    return status;
    }

	public void setRefundFee(Double refundFee) {
	    this.refundFee = refundFee;
    }
	@Column(name = "REFUND_FEE")
	public Double getRefundFee() {
	    return refundFee;
    }

	public void setCreateTime(String createTime) {
	    this.createTime = createTime;
    }
	@Column(name = "CREATE_TIME")
	public String getCreateTime() {
	    return createTime;
    }

	public void setUserId(Long userId) {
	    this.userId = userId;
    }
	@Column(name = "USER_ID")
	public Long getUserId() {
	    return userId;
    }

	public void setReason(String reason) {
	    this.reason = reason;
    }
	@Column(name = "REASON")
	public String getReason() {
	    return reason;
    }

	public void setShopUserId(Long shopUserId) {
	    this.shopUserId = shopUserId;
    }
	@Column(name = "SHOP_USER_ID")
	public Long getShopUserId() {
	    return shopUserId;
    }

	public void setUpdateTime(String updateTime) {
	    this.updateTime = updateTime;
    }
	@Column(name = "UPDATE_TIME")
	public String getUpdateTime() {
	    return updateTime;
    }

	public void setShopRemark(String shopRemark) {
	    this.shopRemark = shopRemark;
    }
	@Column(name = "SHOP_REMARK")
	public String getShopRemark() {
	    return shopRemark;
    }

	public void setAuditUserId(Long auditUserId) {
	    this.auditUserId = auditUserId;
    }
	@Column(name = "AUDIT_USER_ID")
	public Long getAuditUserId() {
	    return auditUserId;
    }

	public void setAuditTime(String auditTime) {
	    this.auditTime = auditTime;
    }
	@Column(name = "AUDIT_TIME")
	public String getAuditTime() {
	    return auditTime;
    }

	public void setAuditRemark(String auditRemark) {
	    this.auditRemark = auditRemark;
    }
	@Column(name = "AUDIT_REMARK")
	public String getAuditRemark() {
	    return auditRemark;
    }

	public void setDealTime(String dealTime) {
	    this.dealTime = dealTime;
    }
	@Column(name = "DEAL_TIME")
	public String getDealTime() {
	    return dealTime;
    }
	
	//**********bus  method***********
	private String createTimeBegin;
	private String createTimeEnd;
	private String refundVerifyCode;
	private Long itemMode;
	
	public void setCreateTimeBegin(String createTimeBegin) {
	    this.createTimeBegin = createTimeBegin;
    }
	@Transient
	public String getCreateTimeBegin() {
	    return createTimeBegin;
    }
	public void setCreateTimeEnd(String createTimeEnd) {
	    this.createTimeEnd = createTimeEnd;
    }
	@Transient
	public String getCreateTimeEnd() {
	    return createTimeEnd;
    }

	public void setRefundVerifyCode(String refundVerifyCode) {
	    this.refundVerifyCode = refundVerifyCode;
    }
	@Transient
	public String getRefundVerifyCode() {
	    return refundVerifyCode;
    }
	@Column(name = "CASH_FEE")
	public Double getCashFee() {
		return cashFee;
	}
	@Column(name = "COIN_FEE")
	public Double getCoinFee() {
		return coinFee;
	}

	public void setCashFee(Double cashFee) {
		this.cashFee = cashFee;
	}

	public void setCoinFee(Double coinFee) {
		this.coinFee = coinFee;
	}
	@Column(name = "TOTAL_CODE")
	public Long getTotalCode() {
		return totalCode;
	}
	@Column(name = "SUCCESS_CODE")
	public Long getSuccessCode() {
		return successCode;
	}
	
	
	
	@Column(name = "ORDER_RECORD_CASH_ID")
	public Long getOrderRecordcashId() {
		return orderRecordcashId;
	}

	public void setOrderRecordcashId(Long orderRecordcashId) {
		this.orderRecordcashId = orderRecordcashId;
	}
	@Column(name = "ORDER_RECORD_COIN_ID")
	public Long getOrderRecordcoinId() {
		return orderRecordcoinId;
	}

	public void setOrderRecordcoinId(Long orderRecordcoinId) {
		this.orderRecordcoinId = orderRecordcoinId;
	}
	@Column(name = "ORDER_RECORD_SCORE_ID")
	public Long getOrderRecordscoreId() {
		return orderRecordscoreId;
	}

	public void setOrderRecordscoreId(Long orderRecordscoreId) {
		this.orderRecordscoreId = orderRecordscoreId;
	}

	@Transient
	public Long getFailCode() {
		return failCode;
	}

	public void setTotalCode(Long totalCode) {
		this.totalCode = totalCode;
	}

	public void setSuccessCode(Long successCode) {
		this.successCode = successCode;
	}

	public void setFailCode(Long failCode) {
		this.failCode = failCode;
	}
	@Transient
	public Long getItemMode() {
		return itemMode;
	}
	@Transient
	public void setItemMode(Long itemMode) {
		this.itemMode = itemMode;
	}
	@Transient
	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}
	@Column(name = "SCORE_FEE")
	public Double getScoreFee() {
		return scoreFee;
	}

	public void setScoreFee(Double scoreFee) {
		this.scoreFee = scoreFee;
	}

	@Column(name = "PHONE_FEE")
	public Double getPhoneFee() {
		return phoneFee;
	}

	@Column(name = "ORDER_RECORD_PHONE_ID")
	public Long getOrderRecordphoneId() {
		return orderRecordphoneId;
	}

	public void setPhoneFee(Double phoneFee) {
		this.phoneFee = phoneFee;
	}

	public void setOrderRecordphoneId(Long orderRecordphoneId) {
		this.orderRecordphoneId = orderRecordphoneId;
	}
	@Column(name = "REFUND_TYPE")
	public Long getRefundType() {
		return refundType;
	}

	public void setRefundType(Long refundType) {
		this.refundType = refundType;
	}
	@Transient
	public String getRefundTypeName() {
		return refundTypeMap.get(this.getRefundType()+"");
	}
	
	
	
}
