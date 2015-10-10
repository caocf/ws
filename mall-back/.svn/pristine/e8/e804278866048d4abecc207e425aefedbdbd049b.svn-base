package com.cplatform.mall.back.sys.entity;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import com.cplatform.mall.back.entity.IdEntity;

/**
 * Title. 系统费率表<br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-6-15 下午4:04:08
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: BaiJie
 * <p>
 * Version: 1.0
 * <p>
 */
@Entity
@Table(name = "T_SYS_FEE")
public class SysFee extends IdEntity {

	private String name;

	private Float fee;

	private Long valid;

	private Long syncGyFlag;

	private String syncGyTime;

	private String decrfee;

	private String storeName;

	private Long storeId;
	
	//清算类别
	private String clearType;

	@Transient
	public String getClearType() {
		return clearType;
	}

	public void setClearType(String clearType) {
		this.clearType = clearType;
	}

	@Transient
	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	@Column(name = "store_id")
	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	@Column(name = "decr_fee")
	public String getDecrfee() {
		return decrfee;
	}

	public void setDecrfee(String decrfee) {
		this.decrfee = decrfee;
	}

	public static Map<Long, String> validMap = null;

	public static Map<Long, String> syncGyFlagMap = null;

	static {

		validMap = new HashMap<Long, String>();
		validMap.put(1L, "有效 ");
		validMap.put(0L, "无效");

		syncGyFlagMap = new HashMap<Long, String>();
		syncGyFlagMap.put(0L, "未同步 ");
		syncGyFlagMap.put(1L, "同步成功");

	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "fee")
	public Float getFee() {
		return fee;
	}

	public void setFee(Float fee) {
		this.fee = fee;
	}

	@Column(name = "valid")
	public Long getValid() {
		return valid;
	}

	public void setValid(Long valid) {
		this.valid = valid;
	}

	@Column(name = "sync_Gy_Flag")
	public Long getSyncGyFlag() {
		return syncGyFlag;
	}

	public void setSyncGyFlag(Long syncGyFlag) {
		this.syncGyFlag = syncGyFlag;
	}

	@Column(name = "sync_Gy_Time")
	public String getSyncGyTime() {
		return syncGyTime;
	}

	public void setSyncGyTime(String syncGyTime) {
		this.syncGyTime = syncGyTime;
	}

	@Transient
	public String getSyncGyFlagName() {
		return syncGyFlagMap.get(this.getSyncGyFlag());
	}

	@Transient
	public static Map<Long, String> getValidMap() {
		return validMap;
	}

	@Transient
	public String getValidName() {
		return validMap.get(this.getValid());
	}

}
