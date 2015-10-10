package com.cplatform.b2c.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 老商城订单历史. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2013-9-10 下午1:52:14
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author yangxm@c-platform.com
 * @version 1.0.0
 */
@Entity
@Table(name = "szmall_order_history")
public class SzMallOrderHistory {

	private String orderId;

	private Long userId;

	private String orderTime;

	private String storeName;

	private String goodsName;

	private String totalCost;

	private String productcost;

	private String status;

	@Id
	@Column(name = "order_id")
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@Column(name = "user_id")
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "order_time")
	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	@Column(name = "store_name")
	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	@Column(name = "goods_name")
	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	@Column(name = "totalcost")
	public String getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(String totalCost) {
		this.totalCost = totalCost;
	}

	@Column(name = "productcost")
	public String getProductcost() {
		return productcost;
	}

	public void setProductcost(String productcost) {
		this.productcost = productcost;
	}

	@Column(name = "status")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
