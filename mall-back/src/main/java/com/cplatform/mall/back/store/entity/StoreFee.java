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
 * 商户费率信息表 Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-6-5 上午11:15:40
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: LiuDong@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
@Entity
@Table(name = "t_store_fee")
public class StoreFee implements java.io.Serializable {

	private Long id;

	private Long storeId;

	private String merchid;

	/**
	 * 现金
	 */
	private Long capitalType1;

	/**
	 * 商城币
	 */
	private Long capitalType2;

	/**
	 * 商城币
	 */
	private Long capitalType3;
	
	/**
	 * 话费
	 */
	private Long capitalType4;

	@Column(name = "CAPITAL_TYPE1")
	public Long getCapitalType1() {
		return capitalType1;
	}

	public void setCapitalType1(Long capitalType1) {
		this.capitalType1 = capitalType1;
	}

	@Column(name = "CAPITAL_TYPE2")
	public Long getCapitalType2() {
		return capitalType2;
	}

	public void setCapitalType2(Long capitalType2) {
		this.capitalType2 = capitalType2;
	}

	@Column(name = "CAPITAL_TYPE3")
	public Long getCapitalType3() {
		return capitalType3;
	}

	public void setCapitalType3(Long capitalType3) {
		this.capitalType3 = capitalType3;
	}
	
	@Column(name = "CAPITAL_TYPE4")
	public Long getCapitalType4() {
		return capitalType4;
	}

	public void setCapitalType4(Long capitalType4) {
		this.capitalType4 = capitalType4;
	}



	private Long tradeMode;

	private String feeType;

	private String productionType;

	private String clearType;

	private String effortdate;

	private String expirydate;

	private String feedrtflag;

	private String feeperiodunit;

	private String fcName;

	private Long feemode;

	private Long feemothod;

	private String beganamount;

	private String minfeeamount;

	private String maxfeeamount;

	private Long feebasicflag;

	private String feelevelflag;

	private String feelvlbasicflag;

	private String upreference1;

	private String fixfeeamount1;

	private String feerate1;

	private String upreference2;

	private String fixfeeamount2;

	private String feerate2;

	private String upreference3;

	private String fixfeeamount3;

	private String feerate3;

	private String upreference4;

	private String fixfeeamount4;

	private String feerate4;

	private String upreference5;

	private String fixfeeamount5;

	private String feerate5;

	private String createTime;

	private String syncTime;

	private Long createUser;

	private Long status;

	private Long syncGyFlag;
	
	private String phoneEffortdate;
	
	private String tempDate;

	@Transient
	public Long getSyncGyFlag() {
		return syncGyFlag;
	}

	public void setSyncGyFlag(Long syncGyFlag) {
		this.syncGyFlag = syncGyFlag;
	}

	private Long syncGyFlag1;

	private Long syncGyFlag2;
	
	@Column(name = "SYNC_GY_FLAG1")
	public Long getSyncGyFlag1() {
		return syncGyFlag1;
	}

	public void setSyncGyFlag1(Long syncGyFlag1) {
		this.syncGyFlag1 = syncGyFlag1;
	}

	@Column(name = "SYNC_GY_FLAG2")
	public Long getSyncGyFlag2() {
		return syncGyFlag2;
	}

	public void setSyncGyFlag2(Long syncGyFlag2) {
		this.syncGyFlag2 = syncGyFlag2;
	}

	@Column(name = "SYNC_GY_FLAG3")
	public Long getSyncGyFlag3() {
		return syncGyFlag3;
	}

	public void setSyncGyFlag3(Long syncGyFlag3) {
		this.syncGyFlag3 = syncGyFlag3;
	}

	private Long syncGyFlag3;
	
	private Long syncGyFlag4;
	
	@Column(name = "SYNC_GY_FLAG4")
	public Long getSyncGyFlag4() {
		return syncGyFlag4;
	}
	
	public void setSyncGyFlag4(Long syncGyFlag4) {
		this.syncGyFlag4 = syncGyFlag4;
	}



	// 商户名称
	private String name;

	// 商户类别
	private Long shopClass;

	// 区域名称
	private String areaName;

	private String beginTime;

	private String productionTypeName;

	@Transient
	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

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

	@Transient
	public String getProductionTypeName() {
		return productionTypeName;
	}

	public void setProductionTypeName(String productionTypeName) {
		this.productionTypeName = productionTypeName;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	private String endTime;

	public static Map<Long, String> capitalTypemap = null;

	public static Map<String, String> feeTypeMap = null;

	public static Map<String, String> clearTypeMap = null;

	public static Map<String, String> feedrtflagMap = null;

	public static Map<Long, String> feemodeMap = null;

	public static Map<Long, String> feemothodMap = null;

	public static Map<String, String> feelevelflagMap = null;

	public static Map<Long, String> feelvlbasicflagMap = null;

	public static Map<Long, String> statusMap = null;

	public static Map<Long, String> syncGyFlagMap = null;

	public static Map<String, String> feeperiodunitMap = null;
	static {

		statusMap = new HashMap<Long, String>();
		statusMap.put(0L, "未审核");
		// statusMap.put(1L, "待审核");
		// statusMap.put(2L, "审核中");
		statusMap.put(3L, "审核通过");
		statusMap.put(4L, "审核驳回");

		capitalTypemap = new HashMap<Long, String>();
		capitalTypemap.put(0L, "否");
		capitalTypemap.put(1L, "是");

		feeTypeMap = new HashMap<String, String>();
		feeTypeMap.put("01", "消费佣金");
		feeTypeMap.put("02", "渠道佣金");

		clearTypeMap = new HashMap<String, String>();
		clearTypeMap.put("C", "商品类别(C)");
		clearTypeMap.put("P", "商品(P)");

		feedrtflagMap = new HashMap<String, String>();
		feedrtflagMap.put("D", "商户/代理点支付费用");
		feedrtflagMap.put("C", "商户/代理点收入费用");

		feemodeMap = new HashMap<Long, String>();
		feemodeMap.put(0L, "不收取");
		feemodeMap.put(1L, "实时单笔收取");
		feemodeMap.put(2L, "单笔计算、按周期收取");
		feemodeMap.put(3L, "按周期汇总计算并收取");
		feemodeMap.put(4L, "按周期汇总轧差并收取");

		feemothodMap = new HashMap<Long, String>();
		feemothodMap.put(1L, "按固定金额收取");
		feemothodMap.put(2L, "按金额固定百分比收取");
		feemothodMap.put(3L, "按照商品差价计算");

		feelevelflagMap = new HashMap<String, String>();
		feelevelflagMap.put(" ", "空格");
		feelevelflagMap.put("0", "套档");
		feelevelflagMap.put("1", "分层");
		// feelevelflagMap.put(" ", "固定计费");
		// feelevelflagMap.put("1", "分层计费");

		feelvlbasicflagMap = new HashMap<Long, String>();
		feelvlbasicflagMap.put(0L, "金额");
		feelvlbasicflagMap.put(1L, "笔数");

		syncGyFlagMap = new HashMap<Long, String>();
		syncGyFlagMap.put(0L, "未同步 ");
		syncGyFlagMap.put(1L, "已同步");
		syncGyFlagMap.put(2L, "待审核");
		syncGyFlagMap.put(3L, "生效");// 审核通过
		syncGyFlagMap.put(4L, "审核退回");
		syncGyFlagMap.put(5L, "失效/删除");

		feeperiodunitMap = new HashMap<String, String>();
		feeperiodunitMap.put("0", "年");
		feeperiodunitMap.put("1", "月");
		feeperiodunitMap.put("2", "日");
	}

	@Transient
	public String getFeeperiodunitName() {
		return feeperiodunitMap.get(this.feeperiodunit);
	}

	@Transient
	public String getCapitallTypeName() {
		return capitalTypemap.get(this.capitalType1);
	}

	@Transient
	public String getCapital2TypeName() {
		return capitalTypemap.get(this.capitalType2);
	}

	@Transient
	public String getCapital3TypeName() {
		return capitalTypemap.get(this.capitalType3);
	}
	@Transient
	public String getCapital4TypeName() {
		return capitalTypemap.get(this.capitalType4);
	}

	@Transient
	public String getSyncGyFlag1Name() {
		return syncGyFlagMap.get(this.syncGyFlag1);
	}

	@Transient
	public String getSyncGyFlag2Name() {
		return syncGyFlagMap.get(this.syncGyFlag2);
	}

	@Transient
	public String getSyncGyFlag3Name() {
		return syncGyFlagMap.get(this.syncGyFlag3);
	}
	
	@Transient
	public String getSyncGyFlag4Name() {
		return syncGyFlagMap.get(this.syncGyFlag4);
	}


	@Transient
	public String getFeeTypeName() {
		return feeTypeMap.get(this.getFeeType());
	}

	@Transient
	public String getClearTypeName() {
		return clearTypeMap.get(this.getClearType());
	}

	@Transient
	public String getFeedrtflagName() {
		return feedrtflagMap.get(this.getFeedrtflag());
	}

	@Transient
	public String getFeemodeName() {
		return feemodeMap.get(this.getFeemode());
	}

	@Transient
	public String getFeemothodName() {
		return feemothodMap.get(this.getFeemothod());
	}

	@Transient
	public String getFeelevelflagName() {
		return feelevelflagMap.get(this.getFeelevelflag());
	}

	@Transient
	public String getFeelvlbasicflagName() {
		return feelvlbasicflagMap.get(this.getFeelvlbasicflag());
	}

	@Transient
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@SequenceGenerator(name = "seq_store", sequenceName = "SEQ_STORE")
	@Id
	@GeneratedValue(generator = "seq_store")
	@JsonProperty
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "STORE_ID")
	public Long getStoreId() {
		return this.storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	@Column(name = "MERCHID")
	public String getMerchid() {
		return this.merchid;
	}

	public void setMerchid(String merchid) {
		this.merchid = merchid;
	}

	@Column(name = "TRADE_MODE")
	public Long getTradeMode() {
		return this.tradeMode;
	}

	public void setTradeMode(Long tradeMode) {
		this.tradeMode = tradeMode;
	}

	@Column(name = "FEE_TYPE")
	public String getFeeType() {
		return this.feeType;
	}

	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}

	@Column(name = "PRODUCTION_TYPE")
	public String getProductionType() {
		return this.productionType;
	}

	public void setProductionType(String productionType) {
		this.productionType = productionType;
	}

	@Column(name = "CLEAR_TYPE")
	public String getClearType() {
		return this.clearType;
	}

	public void setClearType(String clearType) {
		this.clearType = clearType;
	}

	@Column(name = "EFFORTDATE")
	public String getEffortdate() {
		return this.effortdate;
	}

	public void setEffortdate(String effortdate) {
		this.effortdate = effortdate;
	}

	@Column(name = "EXPIRYDATE")
	public String getExpirydate() {
		return this.expirydate;
	}

	public void setExpirydate(String expirydate) {
		this.expirydate = expirydate;
	}

	@Column(name = "FEEDRTFLAG")
	public String getFeedrtflag() {
		return this.feedrtflag;
	}

	public void setFeedrtflag(String feedrtflag) {
		this.feedrtflag = feedrtflag;
	}

	@Column(name = "FEEPERIODUNIT")
	public String getFeeperiodunit() {
		return this.feeperiodunit;
	}

	public void setFeeperiodunit(String feeperiodunit) {
		this.feeperiodunit = feeperiodunit;
	}

	@Column(name = "FC_NAME")
	public String getFcName() {
		return this.fcName;
	}

	public void setFcName(String fcName) {
		this.fcName = fcName;
	}

	@Column(name = "FEEMODE")
	public Long getFeemode() {
		return this.feemode;
	}

	public void setFeemode(Long feemode) {
		this.feemode = feemode;
	}

	@Column(name = "FEEMOTHOD")
	public Long getFeemothod() {
		return this.feemothod;
	}

	public void setFeemothod(Long feemothod) {
		this.feemothod = feemothod;
	}

	@Column(name = "BEGANAMOUNT")
	public String getBeganamount() {
		return this.beganamount;
	}

	public void setBeganamount(String beganamount) {
		this.beganamount = beganamount;
	}

	@Column(name = "MINFEEAMOUNT")
	public String getMinfeeamount() {
		return this.minfeeamount;
	}

	public void setMinfeeamount(String minfeeamount) {
		this.minfeeamount = minfeeamount;
	}

	@Column(name = "MAXFEEAMOUNT")
	public String getMaxfeeamount() {
		return this.maxfeeamount;
	}

	public void setMaxfeeamount(String maxfeeamount) {
		this.maxfeeamount = maxfeeamount;
	}

	@Column(name = "FEEBASICFLAG")
	public Long getFeebasicflag() {
		return this.feebasicflag;
	}

	public void setFeebasicflag(Long feebasicflag) {
		this.feebasicflag = feebasicflag;
	}

	@Column(name = "FEELEVELFLAG")
	public String getFeelevelflag() {
		return this.feelevelflag;
	}

	public void setFeelevelflag(String feelevelflag) {
		this.feelevelflag = feelevelflag;
	}

	@Column(name = "FEELVLBASICFLAG")
	public String getFeelvlbasicflag() {
		return this.feelvlbasicflag;
	}

	public void setFeelvlbasicflag(String feelvlbasicflag) {
		this.feelvlbasicflag = feelvlbasicflag;
	}

	@Column(name = "UPREFERENCE1")
	public String getUpreference1() {
		return this.upreference1;
	}

	public void setUpreference1(String upreference1) {
		this.upreference1 = upreference1;
	}

	@Column(name = "FIXFEEAMOUNT1")
	public String getFixfeeamount1() {
		return this.fixfeeamount1;
	}

	public void setFixfeeamount1(String fixfeeamount1) {
		this.fixfeeamount1 = fixfeeamount1;
	}

	@Column(name = "FEERATE1")
	public String getFeerate1() {
		return this.feerate1;
	}

	public void setFeerate1(String feerate1) {
		this.feerate1 = feerate1;
	}

	@Column(name = "UPREFERENCE2")
	public String getUpreference2() {
		return this.upreference2;
	}

	public void setUpreference2(String upreference2) {
		this.upreference2 = upreference2;
	}

	@Column(name = "FIXFEEAMOUNT2")
	public String getFixfeeamount2() {
		return this.fixfeeamount2;
	}

	public void setFixfeeamount2(String fixfeeamount2) {
		this.fixfeeamount2 = fixfeeamount2;
	}

	@Column(name = "FEERATE2")
	public String getFeerate2() {
		return this.feerate2;
	}

	public void setFeerate2(String feerate2) {
		this.feerate2 = feerate2;
	}

	@Column(name = "UPREFERENCE3")
	public String getUpreference3() {
		return this.upreference3;
	}

	public void setUpreference3(String upreference3) {
		this.upreference3 = upreference3;
	}

	@Column(name = "FIXFEEAMOUNT3")
	public String getFixfeeamount3() {
		return this.fixfeeamount3;
	}

	public void setFixfeeamount3(String fixfeeamount3) {
		this.fixfeeamount3 = fixfeeamount3;
	}

	@Column(name = "FEERATE3")
	public String getFeerate3() {
		return this.feerate3;
	}

	public void setFeerate3(String feerate3) {
		this.feerate3 = feerate3;
	}

	@Column(name = "UPREFERENCE4")
	public String getUpreference4() {
		return this.upreference4;
	}

	public void setUpreference4(String upreference4) {
		this.upreference4 = upreference4;
	}

	@Column(name = "FIXFEEAMOUNT4")
	public String getFixfeeamount4() {
		return this.fixfeeamount4;
	}

	public void setFixfeeamount4(String fixfeeamount4) {
		this.fixfeeamount4 = fixfeeamount4;
	}

	@Column(name = "FEERATE4")
	public String getFeerate4() {
		return this.feerate4;
	}

	public void setFeerate4(String feerate4) {
		this.feerate4 = feerate4;
	}

	@Column(name = "UPREFERENCE5")
	public String getUpreference5() {
		return this.upreference5;
	}

	public void setUpreference5(String upreference5) {
		this.upreference5 = upreference5;
	}

	@Column(name = "FIXFEEAMOUNT5")
	public String getFixfeeamount5() {
		return this.fixfeeamount5;
	}

	public void setFixfeeamount5(String fixfeeamount5) {
		this.fixfeeamount5 = fixfeeamount5;
	}

	@Column(name = "FEERATE5")
	public String getFeerate5() {
		return this.feerate5;
	}

	public void setFeerate5(String feerate5) {
		this.feerate5 = feerate5;
	}

	@Column(name = "CREATE_TIME")
	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Column(name = "SYNC_TIME")
	public String getSyncTime() {
		return this.syncTime;
	}

	public void setSyncTime(String syncTime) {
		this.syncTime = syncTime;
	}

	@Column(name = "CREATE_USER")
	public Long getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(Long createUser) {
		this.createUser = createUser;
	}

	@Column(name = "STATUS")
	public Long getStatus() {
		return this.status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	private Long capitalType;

	@Transient
	public Long getCapitalType() {
		return capitalType;
	}

	public void setCapitalType(Long capitalType) {
		this.capitalType = capitalType;
	}

	@Transient
	public String getStatusName() {
		return statusMap.get(this.getStatus());
	}
	@Column(name = "PHONE_EFFORTDATE")
	public String getPhoneEffortdate() {
		return phoneEffortdate;
	}

	public void setPhoneEffortdate(String phoneEffortdate) {
		this.phoneEffortdate = phoneEffortdate;
	}
	
	@Transient
	public String getTempDate() {
		return tempDate;
	}

	public void setTempDate(String tempDate) {
		this.tempDate = tempDate;
	}
	
	
}
