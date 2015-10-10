package com.cplatform.mall.back.item.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 卡密实体类
 * @Title
 * @Description
 * @Copyright: Copyright (c) 2013-11-12
 * @Company: 北京宽连十方数字技术有限公司
 * @Author chencheng
 * @Version: 1.0
 *
 */
@Entity
@Table(name="T_VERIFY_CARD_CODE_COMMON")
public class VerifyCardCode  implements Serializable{
	
	private static final long serialVersionUID = -2509697668017232102L;

	private Long id;

	private String cardId;

	private String cardKey;
	
	/**
	 * 商户编号
	 */
	private String storeId;

	/**
	 * 商品编号
	 */
	private String itemId;

	/**
	 * 创建日期
	 */
	private String createDate;

	private String generateDate;

	/**
	 * 码状态 0：已制码,未验证 1：已撤销 2：使用中 3：已使用 4：已过期 100: 初始化，未使用
	 */
	private Integer status;


	private String orderId;

	private String userId;
	
	private Long importId;
	
	private Long codeNameId;

	
	@Id
	@SequenceGenerator(name = "SEQ_VERIFY_CARD_CODE_COMMON", sequenceName = "SEQ_VERIFY_CARD_CODE_COMMON")
	@GeneratedValue(generator="SEQ_VERIFY_CARD_CODE_COMMON")
	public Long getId() {
		return id;
	}

	@Column(name="CARD_ID")
	public String getCardId() {
		return cardId;
	}
	@Column(name="CARD_KEY")
	public String getCardKey() {
		return cardKey;
	}
	@Column(name="STORE_ID")
	public String getStoreId() {
		return storeId;
	}
	@Column(name="ITEM_ID")
	public String getItemId() {
		return itemId;
	}
	@Column(name="CREATE_DATE")
	public String getCreateDate() {
		return createDate;
	}
	@Column(name="GENERATE_DATE")
	public String getGenerateDate() {
		return generateDate;
	}
	@Column(name="STATUS")
	public Integer getStatus() {
		return status;
	}
	@Column(name="ORDER_ID")
	public String getOrderId() {
		return orderId;
	}
	@Column(name="USER_ID")
	public String getUserId() {
		return userId;
	}
	@Column(name="IMPORT_ID")
	public Long getImportId() {
		return importId;
	}
	@Column(name="CODE_NAME_ID")
	public Long getCodeNameId() {
		return codeNameId;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public void setCardKey(String cardKey) {
		this.cardKey = cardKey;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public void setGenerateDate(String generateDate) {
		this.generateDate = generateDate;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setImportId(Long importId) {
		this.importId = importId;
	}

	public void setCodeNameId(Long codeNameId) {
		this.codeNameId = codeNameId;
	}
	
	


	

	
}
