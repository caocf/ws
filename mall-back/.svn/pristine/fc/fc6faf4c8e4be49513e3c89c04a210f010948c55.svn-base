package com.cplatform.mall.back.sys.entity;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author mudeng@c-platform.com
 * @version 1.0.0
 */
@javax.persistence.Table(name = "T_OPEN_CUSTOMER")
@Entity
public class OpenCustomer {

	private Long appId;

	private String appKey;

	private String name;

	private String remark;

	private String ips;

	private String status;

	private String payNotify;

	private String appType;

	private Long shopClass;

	private Long shopId;

	private Long storeId;

	private String shopName;

	private String storeName;

	private String appTypeName;

	private String shopClassName;

	@SuppressWarnings("unused")
	private String statusName;

	public static Map<String, String> statusMap = null;
	static {
		statusMap = new HashMap<String, String>();
		statusMap.put("0", "新建");
		statusMap.put("1", "审核驳回");
		statusMap.put("2", "测试中");
		statusMap.put("3", "上线");
		statusMap.put("4", "下线");
	}

	public static Map<String, String> appTypeMap = null;
	static {
		appTypeMap = new HashMap<String, String>();
		appTypeMap.put("1", "垂直频道 ");
		appTypeMap.put("2", "商户");
	}

	public static Map<String, String> shopClassMap = null;
	static {
		shopClassMap = new HashMap<String, String>();
		shopClassMap.put("1", "业务门店");
		shopClassMap.put("2", "商户");
		shopClassMap.put("3", "渠道商 ");
	}

	@Transient
	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	@Transient
	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	@Transient
	public String getStatusName() {
		return statusMap.get(status);
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	@Transient
	public String getAppTypeName() {
		return appTypeMap.get(appType);
	}

	public void setAppTypeName(String appTypeName) {
		this.appTypeName = appTypeName;
	}

	@Transient
	public String getShopClassName() {
		return shopClassMap.get(shopClass + "");
	}

	public void setShopClassName(String shopClassName) {
		this.shopClassName = shopClassName;
	}

	@SequenceGenerator(name = "seq_comm", sequenceName = "SEQ_OPEN_CUSTOMER")
	@Id
	@GeneratedValue(generator = "seq_comm")
	@JsonProperty
	public Long getAppId() {
		return appId;
	}

	public void setAppId(Long appId) {
		this.appId = appId;
	}

	@Column(name = "APP_KEY")
	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "IPS")
	public String getIps() {
		return ips;
	}

	public void setIps(String ips) {
		this.ips = ips;
	}

	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "PAY_NOTIFY")
	public String getPayNotify() {
		return payNotify;
	}

	public void setPayNotify(String payNotify) {
		this.payNotify = payNotify;
	}

	@Column(name = "APP_TYPE")
	public String getAppType() {
		return appType;
	}

	public void setAppType(String appType) {
		this.appType = appType;
	}

	@Column(name = "SHOP_CLASS")
	public Long getShopClass() {
		return shopClass;
	}

	public void setShopClass(Long shopClass) {
		this.shopClass = shopClass;
	}

	@Column(name = "SHOP_ID")
	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	@Column(name = "STORE_ID")
	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

}
