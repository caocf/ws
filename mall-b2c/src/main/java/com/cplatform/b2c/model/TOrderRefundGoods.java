package com.cplatform.b2c.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 订单退款商品单管理表.. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2014-1-20 上午9:24:06
 * <p>
 * Company: 苏州宽连信息技术有限公司
 * <p>
 * 
 * @author zhangdong-ca@c-platform.com
 * @version 1.0.0
 */
@Entity
@Table(name = "T_ORDER_REFUND_GOODS")
public class TOrderRefundGoods implements java.io.Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = -6435397630661371842L;

	/** 序列 **/
	private Long id;

	/** 退货单编号 **/
	private Long refundId;

	/** 订单商品id **/
	private Long orderGoodsId;

	/** 商品id **/
	private Long goodsId;

	/** 退货数量 **/
	private Long backNumber;

	/** 现金 **/
	private Double cash;

	/** 商城币 **/
	private Double coin;

	@SequenceGenerator(name = "seq_order_refund", sequenceName = "SEQ_ORDER_REFUND")
	@Id
	@GeneratedValue(generator = "seq_order_refund")
	@JsonProperty
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "REFUND_ID")
	public Long getRefundId() {
		return refundId;
	}

	public void setRefundId(Long refundId) {
		this.refundId = refundId;
	}

	@Column(name = "ORDER_GOODS_ID")
	public Long getOrderGoodsId() {
		return orderGoodsId;
	}

	public void setOrderGoodsId(Long orderGoodsId) {
		this.orderGoodsId = orderGoodsId;
	}

	@Column(name = "GOODS_ID")
	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	@Column(name = "BACK_NUMBER")
	public Long getBackNumber() {
		return backNumber;
	}

	public void setBackNumber(Long backNumber) {
		this.backNumber = backNumber;
	}

	public Double getCash() {
		return cash;
	}

	public void setCash(Double cash) {
		this.cash = cash;
	}

	public Double getCoin() {
		return coin;
	}

	public void setCoin(Double coin) {
		this.coin = coin;
	}

	// 查看的时候绑定购买数量
	private int number;

	// 查看的时候绑定已经购买数量
	private Long hasBuyNunber;

	@Transient
	public int getNumber() {
		return number;
	}

	@Transient
	public void setNumber(int number) {
		this.number = number;
	}

	@Transient
	public Long getHasBuyNunber() {
		return hasBuyNunber;
	}

	@Transient
	public void setHasBuyNunber(Long hasBuyNunber) {
		this.hasBuyNunber = hasBuyNunber;
	}

}
