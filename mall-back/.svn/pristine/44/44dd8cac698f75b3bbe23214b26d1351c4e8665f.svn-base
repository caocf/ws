package com.cplatform.mall.back.order.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Transient;

/**
 * 订单管理页面展示使用 Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-6-8 上午10:36:30
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: LiuDong@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
public class OrderPage {

	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	private String subject;

	private String shopSubject;// 商户标题

	private String actType;

	private String closeStatus;

	private String payStatus;

	private String verifyStatus;

	private String createTime;

	private String userId;

	private String userName;

	private String[] goodsNames;

	public String[] getGoodsNames() {

		return goodsNames;
	}

	public void setGoodsNames(String[] goodsNames) {
		this.goodsNames = goodsNames;
	}

	private String price;

	public String getActType() {
		return actType;
	}

	public void setActType(String actType) {
		this.actType = actType;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getShopSubject() {
		return shopSubject;
	}

	public void setShopSubject(String shopSubject) {
		this.shopSubject = shopSubject;
	}

	public String getCloseStatus() {
		return closeStatus;
	}

	public void setCloseStatus(String closeStatus) {
		this.closeStatus = closeStatus;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public String getVerifyStatus() {
		return verifyStatus;
	}

	public void setVerifyStatus(String verifyStatus) {
		this.verifyStatus = verifyStatus;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	/** 业务类型map */
	private static Map<String, String> actTypeMap = null;
	static {
		actTypeMap = new HashMap<String, String>();
		actTypeMap.put("20", "短信购");
		// actTypeMap.put("50", "短信购（测试）");
		// actTypeMap.put("41", "普通购买（测试）");
		actTypeMap.put("40", "普通购买");
	}

	@Transient
	public static Map<String, String> getActTypeMap() {
		return actTypeMap;
	}

	@Transient
	public String getActTypeName() {
		String actTypeName = actTypeMap.get(this.getActType() + "");
		return actTypeName == null ? this.getActType() + "" : actTypeName;
	}

	/** 关闭状态map */
	private static Map<String, String> closeStatusMap = null;
	static {
		closeStatusMap = new HashMap<String, String>();
		closeStatusMap.put("1", "已关闭");
		closeStatusMap.put("0", "正常");
	}

	@Transient
	public static Map<String, String> getCloseStatusMap() {
		return closeStatusMap;
	}

	@Transient
	public String getCloseStatusName() {
		return closeStatusMap.get(this.getCloseStatus() + "");
	}

	/** 支付状态map */
	private static Map<String, String> payStatusMap = null;
	static {
		payStatusMap = new HashMap<String, String>();
		payStatusMap.put("1", "已关闭");
		payStatusMap.put("0", "正常");
	}

	@Transient
	public static Map<String, String> getPayStatusMap() {
		return payStatusMap;
	}

	@Transient
	public String getPayStatusName() {
		return payStatusMap.get(this.getPayStatus() + "");
	}

}
