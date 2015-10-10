package com.cplatform.b2c.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * 标题、简要说明. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2014-1-13 上午11:52:02
 * <p>
 * Company: 苏州宽连信息技术有限公司
 * <p>
 * 
 * @author yangxm@c-platform.com
 * @version 1.0.0
 */
@Entity
@Table(name = "v_act_order_status")
public class VActOrderStatus implements Serializable {

	private Long orderId;

	// 待付款1, 支付中6, 已付款（待发货) 2, 待收货3 , 已完成4, 已取消5 , 其他0
	private int actOrderStatus;

	// 1有退款 0无退款
	private int hasRefund;

	// 0部分退 1全退款
	private int refundAll;

	private TActOrderNew order;

	@OneToOne(mappedBy = "orderStatus")
	@PrimaryKeyJoinColumn
	public TActOrderNew getOrder() {
		return order;
	}

	public void setOrder(TActOrderNew order) {
		this.order = order;
	}

	@Id
	@GeneratedValue(generator = "pkGenerator")
	@GenericGenerator(name = "pkGenerator", strategy = "foreign", parameters = @Parameter(name = "property", value = "order"))
	@Column(name = "act_order_id")
	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	@Column(name = "ORDER_STATUS")
	public int getActOrderStatus() {
		return actOrderStatus;
	}

	public void setActOrderStatus(int actOrderStatus) {
		this.actOrderStatus = actOrderStatus;
	}

	@Column(name = "HAS_REFUND")
	public int getHasRefund() {
		return hasRefund;
	}

	public void setHasRefund(int hasRefund) {
		this.hasRefund = hasRefund;
	}

	@Column(name = "REFUND_ALL")
	public int getRefundAll() {
		return refundAll;
	}

	public void setRefundAll(int refundAll) {
		this.refundAll = refundAll;
	}

}
