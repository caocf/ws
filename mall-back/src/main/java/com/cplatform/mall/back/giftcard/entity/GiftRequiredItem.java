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
 * @Title	礼品卡需求和商品关系实体类
 * @Description
 * @Copyright: Copyright (c) 2013-9-22
 * @Company: 北京宽连十方数字技术有限公司
 * @Author chencheng
 * @Version: 1.0
 *
 */
@Entity
@Table(name = "T_GIFT_REQUIRED_ITEM")
public class GiftRequiredItem implements java.io.Serializable{

	private static final long serialVersionUID = 7765687678931362352L;
	
	public static Map<String, String> statusMap = null;
	static {
		statusMap = new HashMap<String, String>();
		statusMap.put("-1", "删除");
		statusMap.put("0", "草稿");
		statusMap.put("1", "待审核");
		statusMap.put("2", "审核通过");
		statusMap.put("3", "审核失败");
	}
	/**
	 * 主键id
	 */
	private Long id;
	/**
	 * 卡批次号
	 */
	private Long batchNo;
	/**
	 * 商品id
	 */
	private Long itemId;
	/**
	 * 状态， -1：删除，0：草稿，1：待审核，2：审核通过,3:审核失败
	 */
	private Integer status;
	/**
	 * 审核时间
	 */
	private String auditTime;
	/**
	 * 审核意见
	 */
	private String auditMsg;
	/**
	 * 审核用户id
	 */
	private Long auditUserId;
	/**
	 * 商户编号
	 */
	private Long storeId;
	/**
	 * 创建时间
	 */
	private String createdTime;
	/**
	 * 创建者用户id
	 */
	private Long createdUserId;
	/**
	 * 备注
	 */
	private String remark;
	
	/**
	 * 状态名称
	 */
	private String statusName;
	/**
	 * 商品名称
	 */
	private String itemName;
	/**
	 * 商户名称
	 */
	private String storeName;
	
	private String dimcodeImg;
	
	
	private String dimcodeWapUrl;
	
	
	private Long dimcodeStatus;
	
	private String dimcdeTime;
	
	//二维码路径
	private String fileWebPath;
	
	@SequenceGenerator(name = "seq_gift_required_item", sequenceName = "SEQ_GIFT_REQUIRED_ITEM")
	@Id
	@GeneratedValue(generator = "seq_gift_required_item")
	@JsonProperty
	public Long getId() {
		return id;
	}
	@Column(name="BATCH_NO")
	public Long getBatchNo() {
		return batchNo;
	}
	@Column(name="ITEM_ID")
	public Long getItemId() {
		return itemId;
	}
	@Column(name="STATUS")
	public Integer getStatus() {
		return status;
	}
	@Column(name="AUDIT_TIME")
	public String getAuditTime() {
		return auditTime;
	}
	@Column(name="AUDIT_MSG")
	public String getAuditMsg() {
		return auditMsg;
	}
	@Column(name="AUDIT_USER_ID")
	public Long getAuditUserId() {
		return auditUserId;
	}
	@Column(name="STORE_ID")
	public Long getStoreId() {
		return storeId;
	}
	@Column(name="CREATED_TIME")
	public String getCreatedTime() {
		return createdTime;
	}
	@Column(name="CREATED_USER_ID")
	public Long getCreatedUserId() {
		return createdUserId;
	}
	@Column(name="REMARK")
	public String getRemark() {
		return remark;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setBatchNo(Long batchNo) {
		this.batchNo = batchNo;
	}
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public void setAuditTime(String auditTime) {
		this.auditTime = auditTime;
	}
	public void setAuditMsg(String auditMsg) {
		this.auditMsg = auditMsg;
	}
	public void setAuditUserId(Long auditUserId) {
		this.auditUserId = auditUserId;
	}
	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}
	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}
	public void setCreatedUserId(Long createdUserId) {
		this.createdUserId = createdUserId;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Transient
	public String getStatusName() {
		return statusMap.get(this.getStatus()+"");
	}
	@Transient
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	@Transient
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	@Column(name="DIMCODE_IMG")
	public String getDimcodeImg() {
		return dimcodeImg;
	}
	public void setDimcodeImg(String dimcodeImg) {
		this.dimcodeImg = dimcodeImg;
	}
	@Column(name="DIMCODE_WAP_URL")
	public String getDimcodeWapUrl() {
		return dimcodeWapUrl;
	}
	public void setDimcodeWapUrl(String dimcodeWapUrl) {
		this.dimcodeWapUrl = dimcodeWapUrl;
	}
	@Column(name="DIMCODE_STATUS")
	public Long getDimcodeStatus() {
		return dimcodeStatus;
	}
	public void setDimcodeStatus(Long dimcodeStatus) {
		this.dimcodeStatus = dimcodeStatus;
	}
	@Column(name="DIMCODE_TIME")
	public String getDimcdeTime() {
		return dimcdeTime;
	}
	public void setDimcdeTime(String dimcdeTime) {
		this.dimcdeTime = dimcdeTime;
	}
	@Transient
	public String getFileWebPath() {
		return fileWebPath;
	}
	public void setFileWebPath(String fileWebPath) {
		this.fileWebPath = fileWebPath;
	}
	
	
	
	
}
