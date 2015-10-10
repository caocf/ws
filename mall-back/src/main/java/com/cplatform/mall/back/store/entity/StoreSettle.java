package com.cplatform.mall.back.store.entity;

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
 * 商户结算信息表 Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-6-5 上午11:05:31
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: LiuDong@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
@Entity
@Table(name = "T_STORE_SETTLE")
public class StoreSettle implements java.io.Serializable {

	/**
	 * 审核通过
	 */

	public static final Long STATUS_AUDIT_PASS = 3L;

	/**
	 * 审核驳回
	 */

	public static final Long STATUS_AUDIT_REJECT = 4L;

	private Long id;

	/**
	 * 商户id
	 */
	private Long storeId;

	/**
	 * 高阳结算id
	 */
	private String merchid;

	/**
	 * 结算类型0：系统结算 1：手工结算
	 */
	private Long settleType;

	/**
	 * 佣金结算0：收支两条线 1：作扣
	 */
	private Long feeSettle;

	/**
	 * 结算周期0：日1：周2：旬3：月4：季5：半年6：年7：指定日
	 */
	private Long settlePeriod;

	/**
	 * 结算日标识结算间隔的数量
	 */
	private Long settleDay;

	/**
	 * 结算日标志位指定结算日时设置一个月31天的具体日期
	 */
	private Long settleDaybit;

	/**
	 * 结算划款天数
	 */
	private Long settleTrfdays;

	/**
	 * 结算起始金额
	 */
	private Long settleBeginamt;

	/**
	 * 最低留存金额
	 */
	private Long minRetainedamt;

	/**
	 * 结算银行的名称
	 */
	private String withdrawBankid;

	/**
	 * 开户行详细信息
	 */
	private String openbankDesc;

	/**
	 * 结算账户
	 */
	private String settleAc;

	/**
	 * 开户人
	 */
	private String bankAcname;

	/**
	 * 生效日期
	 */
	private String effortDate;

	/**
	 * 失效日期
	 */
	private String expiryDate;

	/**
	 * 否成功同步高阳
	 */
	private Long syncGyFlag;

	/**
	 * 状态
	 */
	private Long status;

	/**
	 * 创建时间
	 */
	private String createTime;

	/**
	 * 创建人
	 */
	private Long createUser;

	/** 是否企业账户 */
	private Long isBsAccount;

	/**
	 * 同步时间
	 */
	private String syncTime;

	// 商户名称
	private String name;

	// 商户类别
	private Long shopClass;

	// 区域名称
	private String areaName;

	private String beginTime;

	@Transient
	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	@Transient
	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	private String endTime;

	@Transient
	public String getShopClassName() {
		return Store.shopClassMap.get(this.shopClass);
	}

	public void setShopClass(Long shopClass) {
		this.shopClass = shopClass;
	}

	@Transient
	public Long getShopClass() {
		return this.shopClass;
	}

	public static Map<Long, String> settleTypeMap = null;

	public static Map<Long, String> feeSettleMap = null;

	public static Map<Long, String> settlePeriodMap = null;

	public static Map<Long, String> statusMap = null;

	public static Map<Long, String> syncGyFlagMap = null;
	static {
		settleTypeMap = new HashMap<Long, String>();
		settleTypeMap.put(0L, "系统结算");
		settleTypeMap.put(1L, "手工结算结算");

		feeSettleMap = new HashMap<Long, String>();
		feeSettleMap.put(0L, "收支两条线");
		feeSettleMap.put(1L, "作扣");

		settlePeriodMap = new HashMap<Long, String>();
		settlePeriodMap.put(0L, "日");
		settlePeriodMap.put(1L, "周");
		settlePeriodMap.put(2L, "旬");
		settlePeriodMap.put(3L, "月");
		settlePeriodMap.put(4L, "季");
		settlePeriodMap.put(5L, "半年");
		settlePeriodMap.put(6L, "年");
		settlePeriodMap.put(7L, "指定日");

		statusMap = new HashMap<Long, String>();
		statusMap.put(0L, "未审核");
		// statusMap.put(1L, "待审核");
		// statusMap.put(2L, "审核中");
		statusMap.put(3L, "审核通过");
		statusMap.put(4L, "审核驳回");

		syncGyFlagMap = new HashMap<Long, String>();
		syncGyFlagMap.put(0L, "未同步 ");
		syncGyFlagMap.put(1L, "已同步");
		syncGyFlagMap.put(2L, "待审核");
		syncGyFlagMap.put(3L, "生效");// 审核通过
		syncGyFlagMap.put(4L, "审核退回");
		syncGyFlagMap.put(5L, "失效/删除");

	}

	@Transient
	public String getSyncGyFlagName() {
		return syncGyFlagMap.get(this.syncGyFlag);
	}

	@Transient
	public String getStatusName() {
		return statusMap.get(this.getStatus());
	}

	@Transient
	public String getSettleTypeName() {
		return settleTypeMap.get(this.getSettleType());
	}

	@Transient
	public String getFeeSettleName() {
		return feeSettleMap.get(this.getFeeSettle());
	}

	@Transient
	public String getSettlePeriodName() {
		return settlePeriodMap.get(this.getSettlePeriod());
	}

	@SequenceGenerator(name = "seq_store", sequenceName = "SEQ_STORE")
	@Id
	@GeneratedValue(generator = "seq_store")
	@JsonProperty
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "STORE_ID")
	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	@Column(name = "MERCHID")
	public String getMerchid() {
		return merchid;
	}

	public void setMerchid(String merchid) {
		this.merchid = merchid;
	}

	@Column(name = "SETTLE_TYPE")
	public Long getSettleType() {
		return settleType;
	}

	public void setSettleType(Long settleType) {
		this.settleType = settleType;
	}

	@Column(name = "FEE_SETTLE")
	public Long getFeeSettle() {
		return feeSettle;
	}

	public void setFeeSettle(Long feeSettle) {
		this.feeSettle = feeSettle;
	}

	@Column(name = "SETTLE_PERIOD")
	public Long getSettlePeriod() {
		return settlePeriod;
	}

	public void setSettlePeriod(Long settlePeriod) {
		this.settlePeriod = settlePeriod;
	}

	@Column(name = "SETTLE_DAY")
	public Long getSettleDay() {
		return settleDay;
	}

	public void setSettleDay(Long settleDay) {
		this.settleDay = settleDay;
	}

	@Column(name = "SETTLE_DAYBIT")
	public Long getSettleDaybit() {
		return settleDaybit;
	}

	public void setSettleDaybit(Long settleDaybit) {
		this.settleDaybit = settleDaybit;
	}

	@Column(name = "SETTLE_TRFDAYS")
	public Long getSettleTrfdays() {
		return settleTrfdays;
	}

	public void setSettleTrfdays(Long settleTrfdays) {
		this.settleTrfdays = settleTrfdays;
	}

	@Column(name = "SETTLE_BEGINAMT")
	public Long getSettleBeginamt() {
		return settleBeginamt;
	}

	public void setSettleBeginamt(Long settleBeginamt) {
		this.settleBeginamt = settleBeginamt;
	}

	@Column(name = "MIN_RETAINEDAMT")
	public Long getMinRetainedamt() {
		return minRetainedamt;
	}

	public void setMinRetainedamt(Long minRetainedamt) {
		this.minRetainedamt = minRetainedamt;
	}

	@Column(name = "WITHDRAW_BANKID")
	public String getWithdrawBankid() {
		return withdrawBankid;
	}

	public void setWithdrawBankid(String withdrawBankid) {
		this.withdrawBankid = withdrawBankid;
	}

	@Column(name = "OPENBANK_DESC")
	public String getOpenbankDesc() {
		return openbankDesc;
	}

	public void setOpenbankDesc(String openbankDesc) {
		this.openbankDesc = openbankDesc;
	}

	@Column(name = "SETTLE_AC")
	public String getSettleAc() {
		return settleAc;
	}

	public void setSettleAc(String settleAc) {
		this.settleAc = settleAc;
	}

	@Column(name = "BANK_ACNAME")
	public String getBankAcname() {
		return bankAcname;
	}

	public void setBankAcname(String bankAcname) {
		this.bankAcname = bankAcname;
	}

	@Column(name = "EFFORT_DATE")
	public String getEffortDate() {
		return effortDate;
	}

	public void setEffortDate(String effortDate) {
		this.effortDate = effortDate;
	}

	@Column(name = "EXPIRY_DATE")
	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	@Column(name = "SYNC_GY_FLAG")
	public Long getSyncGyFlag() {
		return syncGyFlag;
	}

	public void setSyncGyFlag(Long syncGyFlag) {
		this.syncGyFlag = syncGyFlag;
	}

	@Column(name = "STATUS")
	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	@Column(name = "CREATE_TIME")
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Column(name = "CREATE_USER")
	public Long getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Long createUser) {
		this.createUser = createUser;
	}

	@Column(name = "SYNC_TIME")
	public String getSyncTime() {
		return syncTime;
	}

	public void setSyncTime(String syncTime) {
		this.syncTime = syncTime;
	}

	@Transient
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Transient
	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	@Column(name = "is_bs_account")
	public Long getIsBsAccount() {
		return isBsAccount;
	}

	public void setIsBsAccount(Long isBsAccount) {
		this.isBsAccount = isBsAccount;
	}

}
