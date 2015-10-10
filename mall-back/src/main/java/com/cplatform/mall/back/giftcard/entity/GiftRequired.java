package com.cplatform.mall.back.giftcard.entity;

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
 * 
 * 礼品卡需求实体类
 * 
 * */


@Table(name = "T_GIFT_REQUIRED")
@Entity
public class GiftRequired {
	private Long batchNo;
	private String modelNo;
	private Long cardNum;
	private String issuingTime;
	private String effortDate;
	private String expiryDate;
	private Long exchangeMode;
	private Long status;
	private String cardFaceMsg;
	private Long printFaceStatus;
	private Long bindItemStatus;
	private Long makeCardStatus;
	private Long parValue;
	private String remark;
	private String auditTime;
	private String auditMsg;
	private Long auditUserId;
	private String createdTime;
	private Long createUserId;
	private String requiredUser;
	private String stocks;
	private String sremark;
	private Long stocksIn;
	private Long stocksOut;
	private String faceImg;
	private String regionCode;
	private Long storeId;
	private String storeName;
	private Long saleId;
	
	private String cutoffDay;
	/**
	 * 绑定商品状态
	 */
	private Integer bindStatus;
	/**
	 * 卡兑换数量
	 */
	private Integer exchangeNum;
	
	private Long dimcodeStatus;
	
	@SequenceGenerator(name = "seq_batch_no", sequenceName = "SEQ_GIFT_REQUIRED")
	@Id
	@GeneratedValue(generator = "seq_batch_no")
	@JsonProperty
	public Long getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(Long batchNo) {
		this.batchNo = batchNo;
	}
	
	@Column(name="sale_id")
	public Long getSaleId() {
		return saleId;
	}
	public void setSaleId(Long saleId) {
		this.saleId = saleId;
	}
	@Column(name="MODEL_NO")
	public String getModelNo() {
		return modelNo;
	}
	public void setModelNo(String modelNo) {
		this.modelNo = modelNo;
	}
	@Column(name="CARD_NUM")
	public Long getCardNum() {
		return cardNum;
	}
	public void setCardNum(Long cardNum) {
		this.cardNum = cardNum;
	}
	@Column(name="ISSUING_TIME")
	public String getIssuingTime() {
		return issuingTime;
	}
	public void setIssuingTime(String issuingTime) {
		this.issuingTime = issuingTime;
	}
	@Column(name="EFFORT_DATE")
	public String getEffortDate() {
		return effortDate;
	}
	public void setEffortDate(String effortDate) {
		this.effortDate = effortDate;
	}
	@Column(name="EXPIRY_DATE")
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	@Column(name="EXCHANGE_MODE")
	public Long getExchangeMode() {
		return exchangeMode;
	}
	public void setExchangeMode(Long exchangeMode) {
		this.exchangeMode = exchangeMode;
	}
	@Column(name="STATUS")
	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
	}
	@Column(name="CARD_FACE_MSG")
	public String getCardFaceMsg() {
		return cardFaceMsg;
	}
	public void setCardFaceMsg(String cardFaceMsg) {
		this.cardFaceMsg = cardFaceMsg;
	}
	@Column(name="PRINT_FACE_STATUS")
	public Long getPrintFaceStatus() {
		return printFaceStatus;
	}
	public void setPrintFaceStatus(Long printFaceStatus) {
		this.printFaceStatus = printFaceStatus;
	}
	@Column(name="BIND_ITEM_STATUS")
	public Long getBindItemStatus() {
		return bindItemStatus;
	}
	public void setBindItemStatus(Long bindItemStatus) {
		this.bindItemStatus = bindItemStatus;
	}
	@Column(name="MAKE_CARD_STATUS")
	public Long getMakeCardStatus() {
		return makeCardStatus;
	}
	public void setMakeCardStatus(Long makeCardStatus) {
		this.makeCardStatus = makeCardStatus;
	}
	@Column(name="PAR_VALUE")
	public Long getParValue() {
		return parValue;
	}
	public void setParValue(Long parValue) {
		this.parValue = parValue;
	}
	@Column(name="REMARK")
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Column(name="AUDIT_TIME")
	public String getAuditTime() {
		return auditTime;
	}
	public void setAuditTime(String auditTime) {
		this.auditTime = auditTime;
	}
	@Column(name="AUDIT_MSG")
	public String getAuditMsg() {
		return auditMsg;
	}
	public void setAuditMsg(String auditMsg) {
		this.auditMsg = auditMsg;
	}
	@Column(name="AUDIT_USER_ID")
	public Long getAuditUserId() {
		return auditUserId;
	}
	public void setAuditUserId(Long auditUserId) {
		this.auditUserId = auditUserId;
	}
	@Column(name="CREATED_TIME")
	public String getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}
	@Column(name="CREATE_USER_ID")
	public Long getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}
	@Column(name="REQUIRED_USER")
	public String getRequiredUser() {
		return requiredUser;
	}
	public void setRequiredUser(String requiredUser) {
		this.requiredUser = requiredUser;
	}
	
	@Column(name="FACE_IMG")
	public String getFaceImg() {
		return faceImg;
	}
	public void setFaceImg(String faceImg) {
		this.faceImg = faceImg;
	}
	@Column(name="REGION_CODE")
	public String getRegionCode() {
		return regionCode;
	}
	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}
	@Column(name="STORE_ID")
	public Long getStoreId() {
		return storeId;
	}
	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}


	
	@Column(name="CUTOFF_DAY")
	public String getCutoffDay() {
		return cutoffDay;
	}
	public void setCutoffDay(String cutoffDay) {
		this.cutoffDay = cutoffDay;
	}




	public static Map<Long, String> statusMap = null;
	static {
		statusMap = new HashMap<Long, String>();
		statusMap.put(-1L, "已删除");
		statusMap.put(0L, "草稿");
		statusMap.put(1L, "待审核");
		statusMap.put(2L, "审核通过");
		statusMap.put(3L, "审核驳回");
	}
	@Transient
	public String getStatusName() {
		return statusMap.get(this.status);
	}
	
	
	public static Map<Long, String> printFaceStatusMap = null;
	static {
		printFaceStatusMap = new HashMap<Long, String>();
		printFaceStatusMap.put(0L, "未印刷");
		printFaceStatusMap.put(1L, "印刷中");
		printFaceStatusMap.put(2L, "印刷完成");
	}
	@Transient
	public String getPrintFaceStatusName() {
		return printFaceStatusMap.get(this.printFaceStatus);
	}
	
	public static Map<Long, String> bindItemStatusMap = null;
	static {
		bindItemStatusMap = new HashMap<Long, String>();
		bindItemStatusMap.put(0L, "未绑定");
		bindItemStatusMap.put(1L, "已绑定");
	}
	@Transient
	public String getBindItemStatusName() {
		return bindItemStatusMap.get(this.bindItemStatus);
	}
	
	public static Map<Long, String> makeCardStatusMap = null;
	static {
		makeCardStatusMap = new HashMap<Long, String>();
		makeCardStatusMap.put(0L, "未制卡");
		makeCardStatusMap.put(1L, "已制卡");
	}
	@Transient
	public String getMakeCardStatusName() {
		return makeCardStatusMap.get(this.makeCardStatus);
	}
	
	
	public static Map<Long, String> exchangeModeMap = null;
	static {
		exchangeModeMap = new HashMap<Long, String>();
		exchangeModeMap.put(1L, "一兑一");
		exchangeModeMap.put(2L, "二兑一");
		exchangeModeMap.put(3L, "三兑一");
		exchangeModeMap.put(4L, "四兑一");
		exchangeModeMap.put(5L, "五兑一");
		exchangeModeMap.put(6L, "六兑一");
		exchangeModeMap.put(7L, "七兑一");
		exchangeModeMap.put(8L, "八兑一");
		exchangeModeMap.put(9L, "九兑一");
		exchangeModeMap.put(10L, "十兑一");
	}
	@Transient
	public String getExchangeModeName() {
		return exchangeModeMap.get(this.exchangeMode);
	}
	@Transient
	public Integer getBindStatus() {
		return bindStatus;
	}
	public void setBindStatus(Integer bindStatus) {
		this.bindStatus = bindStatus;
	}
	
	public static Map<String, String> bindStatusMap = null;
	static {
		bindStatusMap = new HashMap<String, String>();
		bindStatusMap.put("-1", "已删除");
		bindStatusMap.put("0", "草稿");
		bindStatusMap.put("1", "待审核");
		bindStatusMap.put("2", "审核通过");
		bindStatusMap.put("3", "审核失败");
	}
	@Transient
	public String getBindStatusName(){
		return bindStatusMap.get(this.getBindStatus()+"");
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
	public String getStocks() {
		return stocks;
	}
	public void setStocks(String stocks) {
		this.stocks = stocks;
	}
	@Transient
	public String getSremark() {
		return sremark;
	}
	public void setSremark(String sremark) {
		this.sremark = sremark;
	}
	@Transient
	public Long getStocksIn() {
		return stocksIn;
	}

	public void setStocksIn(Long stocksIn) {
		this.stocksIn = stocksIn;
	}

	@Transient
	public Long getStocksOut() {
		return stocksOut;
	}

	public void setStocksOut(Long stocksOut) {
		this.stocksOut = stocksOut;
	}
	@Transient
	public Integer getExchangeNum() {
		return exchangeNum;
	}
	public void setExchangeNum(Integer exchangeNum) {
		this.exchangeNum = exchangeNum;
	}
	
	@Transient
	public Long getDimcodeStatus() {
		return dimcodeStatus;
	}
	@Transient
	public void setDimcodeStatus(Long dimcodeStatus) {
		this.dimcodeStatus = dimcodeStatus;
	}
	public static Map<Long, String> dimcodeStatusMap = null;
	static {
		dimcodeStatusMap = new HashMap<Long, String>();
		dimcodeStatusMap.put(0L, "未生成");
		dimcodeStatusMap.put(1L, "已生成");
	}
	@Transient
	public String getDimcodeStatusName() {
		return dimcodeStatusMap.get(this.dimcodeStatus);
	}
	@Transient
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	
}
