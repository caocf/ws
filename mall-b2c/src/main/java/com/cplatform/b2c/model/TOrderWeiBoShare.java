package com.cplatform.b2c.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 个人中心微博晒单功能. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2013-12-3 上午11:24:20
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author yangxm@c-platform.com
 * @version 1.0.0
 */
@Entity
@Table(name = "T_ORDER_WEIBO_SHARE", schema = "LIFE")
public class TOrderWeiBoShare {

	private Long id;

	private Long orderId;

	private String goodsName;

	private String goodsType;

	private String payTime;

	private String shareTime;

	private int status;

	private String discribe;

	private Long operateId;

	private String lastModify;

	private Long userId;

	private String city;

	private String isLeaguer;

	private int isDel;

	private String mobile;

	@SequenceGenerator(name = "seq_comm", sequenceName = "SEQ_ORDER_WEIBO_SHARE")
	@Id
	@GeneratedValue(generator = "seq_comm")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "ORDER_ID")
	@Basic
	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	@Column(name = "GOODS_NAME")
	@Basic
	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	@Column(name = "GOODS_TYPE")
	@Basic
	public String getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}

	@Column(name = "PAY_TIME")
	@Basic
	public String getPayTime() {
		return payTime;
	}

	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}

	@Column(name = "SHARE_TIME")
	@Basic
	public String getShareTime() {
		return shareTime;
	}

	public void setShareTime(String shareTime) {
		this.shareTime = shareTime;
	}

	@Column(name = "STATUS")
	@Basic
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Column(name = "DISCRIBE")
	@Basic
	public String getDiscribe() {
		return discribe;
	}

	public void setDiscribe(String discribe) {
		this.discribe = discribe;
	}

	@Column(name = "OPERATE_ID")
	@Basic
	public Long getOperateId() {
		return operateId;
	}

	public void setOperateId(Long operateId) {
		this.operateId = operateId;
	}

	@Column(name = "LAST_MODIFY")
	@Basic
	public String getLastModify() {
		return lastModify;
	}

	public void setLastModify(String lastModify) {
		this.lastModify = lastModify;
	}

	@Column(name = "USER_ID")
	@Basic
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "CITY")
	@Basic
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "IS_LEAGUER")
	@Basic
	public String getIsLeaguer() {
		return isLeaguer;
	}

	public void setIsLeaguer(String isLeaguer) {
		this.isLeaguer = isLeaguer;
	}

	@Column(name = "IS_DEL")
	@Basic
	public int getIsDel() {
		return isDel;
	}

	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}

	@Column(name = "MOBILE")
	@Basic
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

}
