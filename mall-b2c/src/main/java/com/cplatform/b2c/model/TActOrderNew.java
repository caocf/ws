package com.cplatform.b2c.model;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import net.sf.json.JSONObject;

/**
 * 业务订单.
 * <p>
 * Copyright: Copyright (c) Jun 13, 2013 3:38:32 PM
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author zhangyinf@c-platform.com
 * @version 1.0.0
 */
@Entity
@Table(name = "t_act_order")
public class TActOrderNew {

	/** serialVersionUID */
	private static final long serialVersionUID = -2680122655463160355L;

	/** 业务类型 */
	private int actType;

	/** close Description */
	private String closeDescription;

	/** close Status */
	private int closeStatus = 0;

	/** 删除状态，只针对个人用户的删除 */
	private int deleteStatus = 0;

	/** deleteTime */
	private String deleteTime;

	/** close Time */
	private String closeTime;

	/** 创建时间 */
	private String createTime;

	/** 订单超时时间，直从下单(Create_Time)到支付完成时间的时间，（单位秒） */
	private int expireTime;

	/** 订单对应的商品信息清单 */
	private List<TActOrderGoods> goodsInfos = new ArrayList<TActOrderGoods>();

	/** 订单支付信息 */
	private List<TActOrderPayment> payments = new ArrayList<TActOrderPayment>();

	/** 订单对应的物流信息 */
	private TActOrderExpress expressInfo;

	/** 业务订单号 */
	private long id;

	/** payDescription */
	private String payDescription;

	/** payStatus */
	private int payStatus;

	/** payTime */
	private String payTime;

	/** 商户Id */
	private long shopId;

	/** 商户标题 */
	private String shopSubject;

	/** actOrderSubject */
	private String subject;

	/** userId */
	private long userId;

	private String payAmount;

	private int status;

	private String isComment;

	private String payCoin;

	private String payCash;

	private String isRefund;

	private String remark;

	private String uuid;

	private Long orderType;

	private String extInfo;

	private boolean isXuNi;

	private Integer payOnDelivery;

	private VActOrderStatus orderStatus;

	/**
	 * 用于代金券-个人业务查询页面 0 or NULL (其他）:商城 1：商盟 2：联动
	 */
	private String source;

	/**
	 * 用于代金券-个人业务查询页面 商盟,联动
	 */
	private String goodsName;

	@Transient
	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	@Transient
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	@Transient
	public boolean isXuNi() {
		return isXuNi;
	}

	public void setXuNi(boolean isXuNi) {
		this.isXuNi = isXuNi;
	}

	@Transient
	public String getIsRefund() {
		return isRefund;
	}

	public void setIsRefund(String isRefund) {
		this.isRefund = isRefund;
	}

	@Transient
	public String getPayCoin() {
		return payCoin;
	}

	public void setPayCoin(String payCoin) {
		this.payCoin = payCoin;
	}

	@Transient
	public String getPayCash() {
		return payCash;
	}

	public void setPayCash(String payCash) {
		this.payCash = payCash;
	}

	@Transient
	public String getIsComment() {
		return isComment;
	}

	public void setIsComment(String isComment) {
		this.isComment = isComment;
	}

	@Transient
	public int getStatus() {
		// 处理联动和联盟
		if ("1".equals(source) || "2".equals(source)) {
			return status;
		}
		if (!isXuNi) {
			if (null != expressInfo) {
				if (expressInfo.getStatus() == 2) {
					status = 4;// 已完成
				} else if (expressInfo.getStatus() == 1) {
					status = 3;// 待收货
				} else if (closeStatus == 1) {
					status = 5;// 已取消
				} else if (payStatus == 2) {
					status = 2;// 已付款
				} else if (payStatus == 0 || payStatus == 1) {
					status = 1;// 待付款
				} else if (expressInfo.getStatus() == 0) {
					status = 2;// 已付款
				}
			} else if (closeStatus == 1) {
				status = 5;// 已取消
			} else if (payStatus == 2) {
				status = 2;// 已付款
			} else if (payStatus == 0 || payStatus == 1) {
				status = 1;// 待付款
			}
		} else {
			if (closeStatus == 1) {
				status = 5;// 已取消
			} else if (payStatus == 2) {
				status = 2;// 已付款
			} else if (payStatus == 0 || payStatus == 1) {
				status = 1;// 待付款
			}
		}
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Transient
	public String getPayAmount() {
		int sum = 0;
		String dd = "0";
		// 处理联动和联盟
		if ("1".equals(source) || "2".equals(source)) {
			return payAmount;
		}
		// 商品价格
		if (goodsInfos != null && goodsInfos.isEmpty() == false) {
			for (TActOrderGoods goodsInfo : goodsInfos) {
				sum += goodsInfo.getPayPrice() * goodsInfo.getCount() - goodsInfo.getDiscount();
			}
			if (null != expressInfo) {
				sum += expressInfo.getExpressCost();
			}
			DecimalFormat fnum = new DecimalFormat("##0.00");
			dd = fnum.format(Float.valueOf(sum) / 100);
		}

		return dd;
	}

	public void setPayAmount(String payAmount) {
		this.payAmount = payAmount;
	}

	/**
	 * 获取 actType
	 * 
	 * @return actType
	 */
	@Column(name = "act_type")
	public int getActType() {
		return actType;
	}

	/**
	 * 获取 closeDescription
	 * 
	 * @return closeDescription
	 */
	@Column(name = "close_description")
	public String getCloseDescription() {
		return closeDescription;
	}

	/**
	 * 获取 closeStatus
	 * 
	 * @return closeStatus
	 */
	@Column(name = "close_status")
	public int getCloseStatus() {
		return closeStatus;
	}

	/**
	 * 获取 closeTime
	 * 
	 * @return closeTime
	 */
	@Column(name = "close_time")
	public String getCloseTime() {
		return closeTime;
	}

	/**
	 * 获取 createTime
	 * 
	 * @return createTime
	 */
	@Column(name = "create_time")
	public String getCreateTime() {
		return createTime;
	}

	/**
	 * @return 订单超时时间，直从下单(Create_Time)到支付完成时间的时间，（单位秒）
	 */
	@Column(name = "expire_time")
	public int getExpireTime() {
		return expireTime;
	}

	/**
	 * 获取 goodsInfos
	 * 
	 * @return goodsInfos
	 */
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<TActOrderGoods> getGoodsInfos() {
		return goodsInfos;
	}

	/**
	 * 获取 id
	 * 
	 * @return id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "gen")
	@SequenceGenerator(name = "gen", sequenceName = "seq_act_order")
	@Column(name = "id")
	public long getId() {
		return id;
	}

	/**
	 * 获取 payDescription
	 * 
	 * @return payDescription
	 */
	@Column(name = "pay_description")
	public String getPayDescription() {
		return payDescription;
	}

	/**
	 * 获取 payStatus
	 * 
	 * @return payStatus
	 */
	@Column(name = "pay_status")
	public int getPayStatus() {
		return payStatus;
	}

	/**
	 * 获取 payTime
	 * 
	 * @return payTime
	 */
	@Column(name = "pay_time")
	public String getPayTime() {
		return payTime;
	}

	/**
	 * 获取 shopId
	 * 
	 * @return shopId
	 */
	@Column(name = "shop_id")
	public long getShopId() {
		return shopId;
	}

	/**
	 * 获取 shopSubject
	 * 
	 * @return shopSubject
	 */
	@Column(name = "shop_subject")
	public String getShopSubject() {
		return shopSubject;
	}

	/**
	 * 获取 subject
	 * 
	 * @return subject
	 */
	@Column(name = "subject")
	public String getSubject() {
		return subject;
	}

	/**
	 * 获取 订单总支付金额（单位分）<br>
	 * 数量来自于getGoodsInfos()方法和getExpressInfo()中的数据<br>
	 * 总金额=sum(商品支付单价*商品数量-商品折扣)+运费
	 * 
	 * @return 订单总支付金额（单位分）
	 */
	@Transient
	public int getTotalPayAmount() {
		int sum = 0;
		// 商品价格
		if (goodsInfos != null && goodsInfos.isEmpty() == false) {
			for (TActOrderGoods goodsInfo : goodsInfos) {
				sum += goodsInfo.getPayPrice() * goodsInfo.getCount() - goodsInfo.getDiscount();
			}
		}
		return sum;
	}

	/**
	 * 获取 userId
	 * 
	 * @return userId
	 */
	@Column(name = "user_id")
	public long getUserId() {
		return userId;
	}

	/**
	 * 设置 actType
	 * 
	 * @param actType
	 *            actType
	 */
	public void setActType(int actType) {
		this.actType = actType;
	}

	/**
	 * 设置 closeDescription
	 * 
	 * @param closeDescription
	 *            closeDescription
	 */
	public void setCloseDescription(String closeDescription) {
		this.closeDescription = closeDescription;
	}

	/**
	 * 设置 closeStatus
	 * 
	 * @param closeStatus
	 *            closeStatus
	 */
	public void setCloseStatus(int closeStatus) {
		this.closeStatus = closeStatus;
	}

	/**
	 * 设置 closeTime
	 * 
	 * @param closeTime
	 *            closeTime
	 */
	public void setCloseTime(String closeTime) {
		this.closeTime = closeTime;
	}

	/**
	 * 设置 createTime
	 * 
	 * @param createTime
	 *            createTime
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	/**
	 * 设置 订单超时时间，直从下单(Create_Time)到支付完成时间的时间，（单位秒）
	 * 
	 * @param expireTime
	 *            订单超时时间，直从下单(Create_Time)到支付完成时间的时间，（单位秒）
	 */
	public void setExpireTime(int expireTime) {
		this.expireTime = expireTime;
	}

	/**
	 * 设置 goodsInfos
	 * 
	 * @param goodsInfos
	 *            goodsInfos
	 */
	public void setGoodsInfos(List<TActOrderGoods> goodsInfos) {
		this.goodsInfos = goodsInfos;
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
	 * 设置 payDescription
	 * 
	 * @param payDescription
	 *            payDescription
	 */
	public void setPayDescription(String payDescription) {
		this.payDescription = payDescription;
	}

	/**
	 * 设置 payStatus
	 * 
	 * @param payStatus
	 *            payStatus
	 */
	public void setPayStatus(int payStatus) {
		this.payStatus = payStatus;
	}

	/**
	 * 设置 payTime
	 * 
	 * @param payTime
	 *            payTime
	 */
	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}

	/**
	 * 设置 shopId
	 * 
	 * @param shopId
	 *            shopId
	 */
	public void setShopId(long shopId) {
		this.shopId = shopId;
	}

	/**
	 * 设置 shopSubject
	 * 
	 * @param shopSubject
	 *            shopSubject
	 */
	public void setShopSubject(String shopSubject) {
		this.shopSubject = shopSubject;
	}

	/**
	 * 设置 subject
	 * 
	 * @param subject
	 *            subject
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * 设置 userId
	 * 
	 * @param userId
	 *            userId
	 */
	public void setUserId(long userId) {
		this.userId = userId;
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

	@OneToOne(mappedBy = "order", fetch = FetchType.LAZY)
	public TActOrderExpress getExpressInfo() {
		return expressInfo;
	}

	public void setExpressInfo(TActOrderExpress expressInfo) {
		this.expressInfo = expressInfo;
	}

	@Column(name = "delete_status")
	public int getDeleteStatus() {
		return deleteStatus;
	}

	public void setDeleteStatus(int deleteStatus) {
		this.deleteStatus = deleteStatus;
	}

	@Column(name = "delete_time")
	public String getDeleteTime() {
		return deleteTime;
	}

	public void setDeleteTime(String deleteTime) {
		this.deleteTime = deleteTime;
	}

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<TActOrderPayment> getPayments() {
		return payments;
	}

	public void setPayments(List<TActOrderPayment> payments) {
		this.payments = payments;
	}

	@Column(name = "UUID")
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	@Column(name = "ORDER_TYPE")
	public Long getOrderType() {
		return orderType;
	}

	public void setOrderType(Long orderType) {
		this.orderType = orderType;
	}

	@Column(name = "EXT_INFO")
	public String getExtInfo() {
		return extInfo;
	}

	public void setExtInfo(String extInfo) {
		this.extInfo = extInfo;
	}

	@Column(name = "PAY_ON_DELIVERY")
	public Integer getPayOnDelivery() {
		return payOnDelivery;
	}

	public void setPayOnDelivery(Integer payOnDelivery) {
		this.payOnDelivery = payOnDelivery;
	}

	// @OneToOne(mappedBy = "order", fetch = FetchType.LAZY)
	@OneToOne
	@PrimaryKeyJoinColumn
	public VActOrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(VActOrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

}
