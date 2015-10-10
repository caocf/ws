package com.cplatform.mall.back.item.entity;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenericGenerator;

/**
 * 码管理 Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-6-14 上午9:52:30
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: LiuDong@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
@Entity
@Table(name="T_VERIFY_STORE_CODE_COMMON")
public class VerifyStoreCode {
	private String codeMd5;

	private String codeRsa;

	private String cardId;

	private String cardKey;

	/**
	 * 商品编号
	 */
	private String itemId;

	/**
	 * 创建日期
	 */
	private String createDate;

	/**
	 * 制码日期
	 */
	private String generateDate;

	/**
	 * 验码日期
	 */
	private String verifyDate;

	/**
	 * 码状态 0：已制码,未验证 1：已撤销 2：使用中 3：已使用 4：已过期 100: 初始化，未使用
	 */
	private String status;

	private String statusName;

	private String storeId;

	private String cardPassWord;

	private String orderId;

	private String userId;
	
	private Long importId;
	
	private Long codeNameId;

	@Id
	@GenericGenerator(name="verifyCodeId",strategy="assigned")
	@GeneratedValue(generator="verifyCodeId")
	public String getCodeMd5() {
		return codeMd5;
	}

	public void setCodeMd5(String codeMd5) {
		this.codeMd5 = codeMd5;
	}

	@Column(name="CODE_RSA")
	public String getCodeRsa() {
		return codeRsa;
	}

	public void setCodeRsa(String codeRsa) {
		this.codeRsa = codeRsa;
	}

	@Transient
	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	@Transient
	public String getCardKey() {
		return cardKey;
	}

	public void setCardKey(String cardKey) {
		this.cardKey = cardKey;
	}

	@Transient
	public String getCardPassWord() {
		return cardPassWord;
	}

	public void setCardPassWord(String cardPassWord) {
		this.cardPassWord = cardPassWord;
	}

	@Column(name="GENERATE_DATE")
	public String getGenerateDate() {
		return generateDate;
	}

	public void setGenerateDate(String generateDate) {
		this.generateDate = generateDate;
	}

	@Column(name="VERIFY_DATE")
	public String getVerifyDate() {
		return verifyDate;
	}

	public void setVerifyDate(String verifyDate) {
		this.verifyDate = verifyDate;
	}

	@Column(name="STORE_ID")
	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	@Column(name="ORDER_ID")
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@Column(name="USER_ID")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public static Map<String, String> statusMap = null;
	static {
		statusMap = new HashMap<String, String>();
		statusMap.put("0", "已制码，未验证");
		statusMap.put("1", "已撤销");
		statusMap.put("2", "使用中");
		statusMap.put("3", "已使用");
		statusMap.put("4", "已过期");
		statusMap.put("100", "初始化，未使用");
	}

	@Transient
	public String getStatusName() {
		return statusMap.get(this.status);
	}

	@Column(name="ITEM_ID")
	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	@Column(name="CREATE_DATE")
	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	@Column(name="STATUS")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	@Column(name="import_id")
	public Long getImportId() {
		return importId;
	}

	public void setImportId(Long importId) {
		this.importId = importId;
	}

	@Column(name="code_name_id")
	public Long getCodeNameId() {
		return codeNameId;
	}

	public void setCodeNameId(Long codeNameId) {
		this.codeNameId = codeNameId;
	}

	
}
