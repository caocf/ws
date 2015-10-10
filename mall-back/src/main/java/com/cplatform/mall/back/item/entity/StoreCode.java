package com.cplatform.mall.back.item.entity;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Transient;

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

public class StoreCode {

	private String id;

	private String code;

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCodeMd5() {
		return codeMd5;
	}

	public void setCodeMd5(String codeMd5) {
		this.codeMd5 = codeMd5;
	}

	public String getCodeRsa() {
		return codeRsa;
	}

	public void setCodeRsa(String codeRsa) {
		this.codeRsa = codeRsa;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getCardKey() {
		return cardKey;
	}

	public void setCardKey(String cardKey) {
		this.cardKey = cardKey;
	}

	public String getCardPassWord() {
		return cardPassWord;
	}

	public void setCardPassWord(String cardPassWord) {
		this.cardPassWord = cardPassWord;
	}

	public String getGenerateDate() {
		return generateDate;
	}

	public void setGenerateDate(String generateDate) {
		this.generateDate = generateDate;
	}

	public String getVerifyDate() {
		return verifyDate;
	}

	public void setVerifyDate(String verifyDate) {
		this.verifyDate = verifyDate;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

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

	public String getStatusName() {
		return statusMap.get(this.status);
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
