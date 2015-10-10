package com.cplatform.mall.back.order.entity;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @Title	码实体类
 * @Description
 * @Copyright: Copyright (c) 2013-8-16
 * @Company: 北京宽连十方数字技术有限公司
 * @Author chencheng
 * @Version: 1.0
 *
 */
@Entity
@Table(name="T_VERIFY_CODE_INFO")
public class CodeInfo implements java.io.Serializable{

	private static final long serialVersionUID = -5763131920949942736L;
	
	/**
	 * 码编号，唯一标识
	 */
	private String orderId;
	/**
	 * 订单商品id
	 */
	private Long itemOrderId;
	/**
	 * 订单id
	 */
	private	Long actOrderId;
	private String code;
	private String code2D;
	/**
	 * 码状态，0：正常1：已撤销2：使用中3：已使用4：已过期
	 */
	private Long codeStatus;
	private String transDate;
	private String validDate;
	private String expireDate;
	private Long validTimes;
	private Long remainTimes;
	private String terminalId;
	private String itemId;
	private String itemName;
	private Long itemQuantity;
	private Long totalPrice;
	
	
	@Transient
	public String getStartTimeBegin() {
		return startTimeBegin;
	}
	public void setStartTimeBegin(String startTimeBegin) {
		this.startTimeBegin = startTimeBegin;
	}
	@Transient
	public String getStartTimeEnd() {
		return startTimeEnd;
	}
	public void setStartTimeEnd(String startTimeEnd) {
		this.startTimeEnd = startTimeEnd;
	}
	@Transient
	public String getPastTimeBegin() {
		return pastTimeBegin;
	}
	public void setPastTimeBegin(String pastTimeBegin) {
		this.pastTimeBegin = pastTimeBegin;
	}
	@Transient
	public String getPastTimeEnd() {
		return pastTimeEnd;
	}
	public void setPastTimeEnd(String pastTimeEnd) {
		this.pastTimeEnd = pastTimeEnd;
	}

	private String startTimeBegin;

	private String startTimeEnd;

	private String pastTimeBegin;

	private String pastTimeEnd;
	
	/**
	 * FOUNDER：方正STORE：商户CPLATFORM：宽连
	 */
	private String platformId;
	private String storeId;
	private String smsContent;
	/**
	 * 码状态map
	 */
	public static Map<Long, String> statusMap = null;
	static {
		statusMap = new HashMap<Long, String>();
		statusMap.put(0L, "正常");
		statusMap.put(1L, "已撤销");
		statusMap.put(2L, "使用中");
		statusMap.put(3L, "已使用");
		statusMap.put(4L, "已过期");
	}
	@Id
	@Column(name="ORDER_ID")
	public String getOrderId() {
		return orderId;
	}
	@Column(name="ITEM_ORDER_ID")
	public Long getItemOrderId() {
		return itemOrderId;
	}
	@Column(name="ACT_ORDER_ID")
	public Long getActOrderId() {
		return actOrderId;
	}
	@Column(name="CODE")
	public String getCode() {
		return code;
	}
	@Column(name="CODE_2D")
	public String getCode2D() {
		return code2D;
	}
	@Column(name="CODE_STATUS")
	public Long getCodeStatus() {
		return codeStatus;
	}
	@Column(name="TRANS_DATE")
	public String getTransDate() {
		return transDate;
	}
	@Column(name="VALID_DATE")
	public String getValidDate() {
		return validDate;
	}
	@Column(name="EXPIRE_DATE")
	public String getExpireDate() {
		return expireDate;
	}
	@Column(name="VALID_TIMES")
	public Long getValidTimes() {
		return validTimes;
	}
	@Column(name="REMAIN_TIMES")
	public Long getRemainTimes() {
		return remainTimes;
	}
	@Column(name="TERMINAL_ID")
	public String getTerminalId() {
		return terminalId;
	}
	@Column(name="ITEM_ID")
	public String getItemId() {
		return itemId;
	}
	@Column(name="ITEM_NAME")
	public String getItemName() {
		return itemName;
	}
	@Column(name="ITEM_QUANTITY")
	public Long getItemQuantity() {
		return itemQuantity;
	}
	@Column(name="TOTAL_PRICE")
	public Long getTotalPrice() {
		return totalPrice;
	}
	@Column(name="PLATFORM_ID")
	public String getPlatformId() {
		return platformId;
	}
	@Column(name="STORE_ID")
	public String getStoreId() {
		return storeId;
	}
	@Column(name="SMS_CONTENT")
	public String getSmsContent() {
		return smsContent;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public void setItemOrderId(Long itemOrderId) {
		this.itemOrderId = itemOrderId;
	}
	public void setActOrderId(Long actOrderId) {
		this.actOrderId = actOrderId;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public void setCode2D(String code2d) {
		code2D = code2d;
	}
	public void setCodeStatus(Long codeStatus) {
		this.codeStatus = codeStatus;
	}
	public void setTransDate(String transDate) {
		this.transDate = transDate;
	}
	public void setValidDate(String validDate) {
		this.validDate = validDate;
	}
	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}
	public void setValidTimes(Long validTimes) {
		this.validTimes = validTimes;
	}
	public void setRemainTimes(Long remainTimes) {
		this.remainTimes = remainTimes;
	}
	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public void setItemQuantity(Long itemQuantity) {
		this.itemQuantity = itemQuantity;
	}
	public void setTotalPrice(Long totalPrice) {
		this.totalPrice = totalPrice;
	}
	public void setPlatformId(String platformId) {
		this.platformId = platformId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public void setSmsContent(String smsContent) {
		this.smsContent = smsContent;
	}
	
	@Transient
	public String getStatusName() {
		return statusMap.get(this.getCodeStatus());
	}

}
