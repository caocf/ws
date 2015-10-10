package com.cplatform.b2c.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import net.sf.json.JSONObject;

/**
 * 业务订单内商品信息.
 * <p>
 * 业务订单与商品信息呈1:n关系
 * <p>
 * Copyright: Copyright (c) Jun 13, 2013 3:36:06 PM
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author zhangyinf@c-platform.com
 * @version 1.0.0
 */
@Entity
@Table(name = "t_act_order_goods")
public class TActOrderGoods {

	/** serialVersionUID */
	private static final long serialVersionUID = -2680122655463160355L;

	/** 商品数量 */
	private int count;

	/** 折扣金额, 单位分 */
	private int discount;

	/** 结算单价，单位分 */
	private int feePrice;

	/** 商品说明（备注）例如...红色、蓝色、长的、短的 */
	private String goodsDescription;

	/** 商品Id */
	private long goodsId;

	/** 商品标题 */
	private String goodsSubject;

	/** 记录id */
	private long id;

	/** 支付单价，单位分 */
	private int payPrice;

	/** 退款金额，单位分 */
	private int refundAmount;

	/** 退款数量 */
	private int refundCount;

	/** 退款说明 */
	private String refundDescription;

	/** refundStatus */
	private int refundStatus;

	/** refundTime */
	private String refundTime;

	/** 验证码序号，用于与验证中心记录对应 */
	private String verifyCodeId;

	/** verifyDescription */
	private String verifyDescription;

	/** verifyStatus */
	private int verifyStatus;

	/** verifyTime */
	private String verifyTime;

	private String fileName;

	private TActOrder order;

	private String ticketTime;

	private String url;

	private String imgPath;

	private Long goodsVersion;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "act_order_id")
	public TActOrder getOrder() {
		return order;
	}

	public void setOrder(TActOrder order) {
		this.order = order;
	}

	/**
	 * 获取 count
	 * 
	 * @return count
	 */
	@Column(name = "count")
	public int getCount() {
		return count;
	}

	/**
	 * 获取 折扣金额, 单位分
	 * 
	 * @return 折扣金额, 单位分
	 */
	@Column(name = "discount")
	public int getDiscount() {
		return discount;
	}

	/**
	 * 获取 feePrice
	 * 
	 * @return feePrice
	 */
	@Column(name = "fee_price")
	public int getFeePrice() {
		return feePrice;
	}

	/**
	 * 获取 goodsDescription
	 * 
	 * @return goodsDescription
	 */
	@Column(name = "goods_description")
	public String getGoodsDescription() {
		return goodsDescription;
	}

	/**
	 * 获取 goodsId
	 * 
	 * @return goodsId
	 */
	@Column(name = "goods_id")
	public long getGoodsId() {
		return goodsId;
	}

	/**
	 * 获取 goodsSubject
	 * 
	 * @return goodsSubject
	 */
	@Column(name = "goods_subject")
	public String getGoodsSubject() {
		return goodsSubject;
	}

	/**
	 * 获取 id
	 * 
	 * @return id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "gen")
	@SequenceGenerator(name = "gen", sequenceName = "seq_act_order_sub")
	@Column(name = "id")
	public long getId() {
		return id;
	}

	/**
	 * 获取 payPrice
	 * 
	 * @return payPrice
	 */
	@Column(name = "pay_price")
	public int getPayPrice() {
		return payPrice;
	}

	/**
	 * 获取 退款金额，单位分
	 * 
	 * @return 退款金额，单位分
	 */
	@Column(name = "refund_amount")
	public int getRefundAmount() {
		return refundAmount;
	}

	/**
	 * 获取 退款数量
	 * 
	 * @return 退款数量
	 */
	@Column(name = "refund_count")
	public int getRefundCount() {
		return refundCount;
	}

	/**
	 * 获取 退款说明
	 * 
	 * @return 退款说明
	 */
	@Column(name = "refund_description")
	public String getRefundDescription() {
		return refundDescription;
	}

	/**
	 * 获取 refundStatus
	 * 
	 * @return refundStatus
	 */
	@Column(name = "refund_status")
	public int getRefundStatus() {
		return refundStatus;
	}

	/**
	 * 获取 refundTime
	 * 
	 * @return refundTime
	 */
	@Column(name = "refund_time")
	public String getRefundTime() {
		return refundTime;
	}

	/**
	 * 获取 验证码序号，用于与验证中心记录对应
	 * 
	 * @return 验证码序号，用于与验证中心记录对应
	 */
	@Column(name = "verify_code_id", length = 30)
	public String getVerifyCodeId() {
		return verifyCodeId;
	}

	/**
	 * 获取 verifyDescription
	 * 
	 * @return verifyDescription
	 */
	@Column(name = "verify_description")
	public String getVerifyDescription() {
		return verifyDescription;
	}

	/**
	 * 获取 verifyStatus
	 * 
	 * @return verifyStatus
	 */
	@Column(name = "verify_status")
	public int getVerifyStatus() {
		return verifyStatus;
	}

	/**
	 * 获取 verifyTime
	 * 
	 * @return verifyTime
	 */
	@Column(name = "verify_time")
	public String getVerifyTime() {
		return verifyTime;
	}

	/**
	 * 设置 count
	 * 
	 * @param count
	 *            count
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * 设置 折扣金额, 单位分
	 * 
	 * @param discount
	 *            折扣金额, 单位分
	 */
	public void setDiscount(int discount) {
		this.discount = discount;
	}

	/**
	 * 设置 feePrice
	 * 
	 * @param feePrice
	 *            feePrice
	 */
	public void setFeePrice(int feePrice) {
		this.feePrice = feePrice;
	}

	/**
	 * 设置 goodsDescription
	 * 
	 * @param goodsDescription
	 *            goodsDescription
	 */
	public void setGoodsDescription(String goodsDescription) {
		this.goodsDescription = goodsDescription;
	}

	/**
	 * 设置 goodsId
	 * 
	 * @param goodsId
	 *            goodsId
	 */
	public void setGoodsId(long goodsId) {
		this.goodsId = goodsId;
	}

	/**
	 * 设置 goodsSubject
	 * 
	 * @param goodsSubject
	 *            goodsSubject
	 */
	public void setGoodsSubject(String goodsSubject) {
		this.goodsSubject = goodsSubject;
	}

	/**
	 * 设置 id
	 * 
	 * @param id
	 *            id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * 设置 payPrice
	 * 
	 * @param payPrice
	 *            payPrice
	 */
	public void setPayPrice(int payPrice) {
		this.payPrice = payPrice;
	}

	/**
	 * 设置 退款金额，单位分
	 * 
	 * @param refundAmount
	 *            退款金额，单位分
	 */
	public void setRefundAmount(int refundAmount) {
		this.refundAmount = refundAmount;
	}

	/**
	 * 设置 退款数量
	 * 
	 * @param refundCount
	 *            退款数量
	 */
	public void setRefundCount(int refundCount) {
		this.refundCount = refundCount;
	}

	/**
	 * 设置 退款说明
	 * 
	 * @param refundDescription
	 *            退款说明
	 */
	public void setRefundDescription(String refundDescription) {
		this.refundDescription = refundDescription;
	}

	/**
	 * 设置 refundStatus
	 * 
	 * @param refundStatus
	 *            refundStatus
	 */
	public void setRefundStatus(int refundStatus) {
		this.refundStatus = refundStatus;
	}

	/**
	 * 设置 refundTime
	 * 
	 * @param refundTime
	 *            refundTime
	 */
	public void setRefundTime(String refundTime) {
		this.refundTime = refundTime;
	}

	/**
	 * 设置 验证码序号，用于与验证中心记录对应
	 * 
	 * @param verifyCodeIndex
	 *            验证码序号，用于与验证中心记录对应
	 */
	public void setVerifyCodeId(String verifyCodeId) {
		this.verifyCodeId = verifyCodeId;
	}

	/**
	 * 设置 verifyDescription
	 * 
	 * @param verifyDescription
	 *            verifyDescription
	 */
	public void setVerifyDescription(String verifyDescription) {
		this.verifyDescription = verifyDescription;
	}

	/**
	 * 设置 verifyStatus
	 * 
	 * @param verifyStatus
	 *            verifyStatus
	 */
	public void setVerifyStatus(int verifyStatus) {
		this.verifyStatus = verifyStatus;
	}

	/**
	 * 设置 verifyTime
	 * 
	 * @param verifyTime
	 *            verifyTime
	 */
	public void setVerifyTime(String verifyTime) {
		this.verifyTime = verifyTime;
	}

	@Override
	public String toString() {
		try {
			return JSONObject.fromObject(this).toString();
		}
		catch (Exception ex) {
			return super.toString();
		}
	}

	@Transient
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Transient
	public String getTicketTime() {
		return ticketTime;
	}

	public void setTicketTime(String ticketTime) {
		this.ticketTime = ticketTime;
	}

	@Transient
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Transient
	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	@Column(name = "GOODS_VERSION")
	public Long getGoodsVersion() {
		return goodsVersion;
	}

	public void setGoodsVersion(Long goodsVersion) {
		this.goodsVersion = goodsVersion;
	}

}
