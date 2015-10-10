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

@Entity
@Table(name = "T_STORE_CODE")
public class StoreCode {

	@SequenceGenerator(name = "seq_store_code", sequenceName = "SEQ_STORE_CODE")
	@Id
	@GeneratedValue(generator = "seq_store_code")
	@JsonProperty
	private Long id;

	@Column(name = "STORE_ID", precision = 8, scale = 0)
	private Long storeId;

	@Column(name = "SEND_CHANNEL_ID", precision = 8, scale = 0)
	private Long sendChannelId;

	@Column(name = "SEND_TYPE_ID", precision = 8, scale = 0)
	private Long sendTypeId;

	@Column(name = "STARTTIME", length = 14)
	private String startTime;

	@Column(name = "STOPTIME", length = 14)
	private String stopTime;

	@Column(name = "OPER_USER", length = 20)
	private String OperUser;

	@Column(name = "OPERTIME", length = 14)
	private String OperTime;

	@Transient
	private String storeName;

	@Transient
	private String areaName;

	@Transient
	private String errorMsg;

	@Transient
	private String FZFlag;

	public static Map<Long, String> sendChannelIdMap = new HashMap<Long, String>();

	static {
		sendChannelIdMap.put(0L, "自有码");
		sendChannelIdMap.put(1L, "方正码");
		sendChannelIdMap.put(2L, "第三方");
	}

	@Transient
	public String getSendChannelName() {
		return sendChannelIdMap.get(this.sendChannelId);
	}

	public static Map<String, String> sendTypeIdMap = new HashMap<String, String>();

	static {
		sendTypeIdMap.put("1", "按照订单发码");
		sendTypeIdMap.put("2", "按照商品个数发码");
	}

	@Transient
	public String getSendTypeName() {
		return sendTypeIdMap.get(this.sendTypeId + "");
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public Long getSendChannelId() {
		return sendChannelId;
	}

	public void setSendChannelId(Long sendChannelId) {
		this.sendChannelId = sendChannelId;
	}

	public Long getSendTypeId() {
		return sendTypeId;
	}

	public void setSendTypeId(Long sendTypeId) {
		this.sendTypeId = sendTypeId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getStopTime() {
		return stopTime;
	}

	public void setStopTime(String stopTime) {
		this.stopTime = stopTime;
	}

	public String getOperUser() {
		return OperUser;
	}

	public void setOperUser(String operUser) {
		OperUser = operUser;
	}

	public String getOperTime() {
		return OperTime;
	}

	public void setOperTime(String operTime) {
		OperTime = operTime;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getFZFlag() {
		return FZFlag;
	}

	public void setFZFlag(String fZFlag) {
		FZFlag = fZFlag;
	}

}
