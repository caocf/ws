package com.cplatform.mall.back.item.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @Title	第三方码信息实体类
 * @Description
 * @Copyright: Copyright (c) 2013-10-21
 * @Company: 北京宽连十方数字技术有限公司
 * @Author chencheng
 * @Version: 1.0
 *
 */
//@Entity
//@Table(name="T_THIRD_CODE")
public class ThirdCode implements java.io.Serializable{

	private static final long serialVersionUID = -1217121590148139352L;

	private Long id;
	private String code;
	private Long itemId;
	/**
	 * 码状态。0：正常1：已撤销2：使用中3：已使用4：已过期
	 */
	private Long status;
	private String createTime;
	private Long userId;
	private Long orderId;
	
	private Long importId;
	/**
	 * 商户id
	 */
	private Long storeId;
	
//	@SequenceGenerator(name = "seq_third_code", sequenceName = "SEQ_THIRD_CODE")
//	@Id
//	@GeneratedValue(generator = "seq_third_code")
//	@JsonProperty
	public Long getId() {
		return id;
	}
//	@Column(name = "CODE")
	public String getCode() {
		return code;
	}
//	@Column(name = "ITEM_ID")
	public Long getItemId() {
		return itemId;
	}
//	@Column(name = "STATUS")
	public Long getStatus() {
		return status;
	}
//	@Column(name = "CREATE_TIME")
	public String getCreateTime() {
		return createTime;
	}
//	@Column(name = "USER_ID")
	public Long getUserId() {
		return userId;
	}
//	@Column(name = "ORDER_ID")
	public Long getOrderId() {
		return orderId;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	public void setStatus(Long status) {
		this.status = status;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
//	@Column(name = "IMPORT_ID")
	public Long getImportId() {
		return importId;
	}
	public void setImportId(Long importId) {
		this.importId = importId;
	}
//	@Column(name = "STORE_ID")
	public Long getStoreId() {
		return storeId;
	}
	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}
	
}
