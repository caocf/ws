package com.cplatform.b2c.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * TOrderRefund entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_ORDER_REFUND")
public class TOrderRefund implements java.io.Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = -6949897710280878549L;

	// 退款单状态
	/**
	 * 状态 － 等待商户确认
	 */
	public static final Integer STATUS_1 = 1;

	/**
	 * 状态 － 等待审核
	 */
	public static final Integer STATUS_2 = 2;

	/**
	 * 状态 － 商户拒绝确认
	 */
	public static final Integer STATUS_3 = 3;

	/**
	 * 状态 － 等待系统退款
	 */
	public static final Integer STATUS_4 = 4;

	/**
	 * 状态 － 审核失败
	 */
	public static final Integer STATUS_5 = 5;

	/**
	 * 状态 － 退款成功
	 */
	public static final Integer STATUS_6 = 6;

	/**
	 * 状态 － 退款失败
	 */
	public static final Integer STATUS_7 = 7;

	/**
	 * 状态 － 退款已通知
	 */
	public static final Integer STATUS_8 = 8;

	/**
	 * 状态 － 系统退款中
	 */
	public static final Integer STATUS_9 = 9;

	// 退款币种
	public static final String CURRENCY_CASH = "cash";

	public static final String CURRENCY_COIN = "coin";

	public static final String CURRENCY_SCORE = "score";

	public static final String CURRENCY_BALANCE = "balance";

	public static final String CURRENCY_REDPACK = "redpack";

	// 退款单类型
	public static final Integer REFUND_TYPE_1 = 1;// 平台自己

	public static final Integer REFUND_TYPE_2 = 2;// 拉手

	public static final Integer REFUND_TYPE_3 = 3;// 永乐

	public static final Integer REFUND_TYPE_4 = 4;// 河南积分

	/** 退款来源 */
	// 管理后台
	public static final Integer REFUND_SOURCE_BACKSTAGE = 0;

	// 前台-申请退款
	public static final Integer REFUND_SOURCE_MALL_APPLY = 1;

	// 前台-取消
	public static final Integer REFUND_SOURCE_MALL_CANCEL = 2;

	// Fields

	private Long id;

	private Long orderId;

	private Integer storeId;

	private Integer status;

	private Double refundFee;

	private String createTime;

	private Integer userId;

	private String reason;

	private Integer shopUserId;

	private String updateTime;

	private String shopRemark;

	private Integer auditUserId;

	private String auditTime;

	private String auditRemark;

	private String dealTime;

	private Double cashFee; // 实际退款现金

	private Double coinFee; // 实际退款商城币

	private Long totalCode; // 虚拟退码数量

	private Long successCode;

	private Long orderRecordcashId;

	private Long orderRecordcoinId;

	private Long orderRecordscoreId;

	private Double scoreFee;

	private Double phoneFee;

	private Long orderRecordphoneId;

	private Integer refundType;

	private List<Object[]> refundGoods = new ArrayList<Object[]>();

	private String totalPrice;

	private String flag;

	private Integer refundSource;

	private Double redpackageFee;

	// Constructors

	@Transient
	public String getFlag() {
		if (status == 1) {
			flag = "等待商户确认";
		} else if (status == 2) {
			flag = "等待审核";
		} else if (status == 3) {
			flag = "商户拒绝确认";
		} else if (status == 4) {
			flag = "等待系统退款";
		} else if (status == 5) {
			flag = "审核失败";
		} else if (status == 6) {
			flag = "退款成功";
		} else if (status == 7) {
			flag = "退款失败";
		} else if (status == 8) {
			flag = "退款已通知";
		} else if (status == 9) {
			flag = "系统退款中";
		}
		return flag;
	}

	@Transient
	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Transient
	public List<Object[]> getRefundGoods() {
		return refundGoods;
	}

	public void setRefundGoods(List<Object[]> refundGoods) {
		this.refundGoods = refundGoods;
	}

	/** default constructor */
	public TOrderRefund() {
	}

	/** minimal constructor */
	public TOrderRefund(Long id) {
		this.id = id;
	}

	/** full constructor */
	public TOrderRefund(Long id, Long orderId, Integer storeId, Integer status, Double refundFee, String createTime, Integer userId, String reason,
	                    Integer shopUserId, String updateTime, String shopRemark, Integer auditUserId, String auditTime, String auditRemark,
	                    String dealTime) {
		this.id = id;
		this.orderId = orderId;
		this.storeId = storeId;
		this.status = status;
		this.refundFee = refundFee;
		this.createTime = createTime;
		this.userId = userId;
		this.reason = reason;
		this.shopUserId = shopUserId;
		this.updateTime = updateTime;
		this.shopRemark = shopRemark;
		this.auditUserId = auditUserId;
		this.auditTime = auditTime;
		this.auditRemark = auditRemark;
		this.dealTime = dealTime;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name = "seq_order_refund", sequenceName = "SEQ_ORDER_REFUND")
	@GeneratedValue(generator = "seq_order_refund")
	@Column(name = "ID")
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "ORDER_ID")
	public Long getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	@Column(name = "STORE_ID")
	public Integer getStoreId() {
		return this.storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	@Column(name = "STATUS")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "REFUND_FEE")
	public Double getRefundFee() {
		return this.refundFee;
	}

	public void setRefundFee(Double refundFee) {
		this.refundFee = refundFee;
	}

	@Column(name = "CREATE_TIME")
	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Column(name = "USER_ID")
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "REASON")
	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Column(name = "SHOP_USER_ID")
	public Integer getShopUserId() {
		return this.shopUserId;
	}

	public void setShopUserId(Integer shopUserId) {
		this.shopUserId = shopUserId;
	}

	@Column(name = "UPDATE_TIME")
	public String getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "SHOP_REMARK")
	public String getShopRemark() {
		return this.shopRemark;
	}

	public void setShopRemark(String shopRemark) {
		this.shopRemark = shopRemark;
	}

	@Column(name = "AUDIT_USER_ID")
	public Integer getAuditUserId() {
		return this.auditUserId;
	}

	public void setAuditUserId(Integer auditUserId) {
		this.auditUserId = auditUserId;
	}

	@Column(name = "AUDIT_TIME")
	public String getAuditTime() {
		return this.auditTime;
	}

	public void setAuditTime(String auditTime) {
		this.auditTime = auditTime;
	}

	@Column(name = "AUDIT_REMARK")
	public String getAuditRemark() {
		return this.auditRemark;
	}

	public void setAuditRemark(String auditRemark) {
		this.auditRemark = auditRemark;
	}

	@Column(name = "DEAL_TIME")
	public String getDealTime() {
		return this.dealTime;
	}

	public void setDealTime(String dealTime) {
		this.dealTime = dealTime;
	}

	@Column(name = "CASH_FEE")
	public Double getCashFee() {
		return cashFee;
	}

	public void setCashFee(Double cashFee) {
		this.cashFee = cashFee;
	}

	@Column(name = "COIN_FEE")
	public Double getCoinFee() {
		return coinFee;
	}

	public void setCoinFee(Double coinFee) {
		this.coinFee = coinFee;
	}

	@Column(name = "TOTAL_CODE")
	public Long getTotalCode() {
		return totalCode;
	}

	public void setTotalCode(Long totalCode) {
		this.totalCode = totalCode;
	}

	@Column(name = "SUCCESS_CODE")
	public Long getSuccessCode() {
		return successCode;
	}

	public void setSuccessCode(Long successCode) {
		this.successCode = successCode;
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

	public void setPhoneFee(Double phoneFee) {
		this.phoneFee = phoneFee;
	}

	@Column(name = "ORDER_RECORD_PHONE_ID")
	public Long getOrderRecordphoneId() {
		return orderRecordphoneId;
	}

	public void setOrderRecordphoneId(Long orderRecordphoneId) {
		this.orderRecordphoneId = orderRecordphoneId;
	}

	@Column(name = "REFUND_TYPE")
	public Integer getRefundType() {
		return refundType;
	}

	public void setRefundType(Integer refundType) {
		this.refundType = refundType;
	}

	@Column(name = "REFUND_SOURCE")
	public Integer getRefundSource() {
		return refundSource;
	}

	public void setRefundSource(Integer refundSource) {
		this.refundSource = refundSource;
	}

	@Column(name = "REDPACKAGE_FEE")
	public Double getRedpackageFee() {
		return redpackageFee;
	}

	public void setRedpackageFee(Double redpackageFee) {
		this.redpackageFee = redpackageFee;
	}

}