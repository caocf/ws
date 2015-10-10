package com.cplatform.mall.back.order.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonProperty;

import com.cplatform.order.ActOrderGoodsInfo;

/**
 * 业务订单商品表 Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-6-7 下午5:28:45
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: LiuDong@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */

public class OrderGoods extends ActOrderGoodsInfo {

	private String goodsName;

	private String shopName;
	/** 单个商品退款现金 **/
	private Double cashSingle;
	/** 单个商品退款商城币 **/
	private Double coinSigle;

	@Transient
	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	@Transient
	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public void setCashSingle(Double cashSingle) {
	    this.cashSingle = cashSingle;
    }
	@Transient
	public Double getCashSingle() {
	    return cashSingle;
    }

	public void setCoinSigle(Double coinSigle) {
	    this.coinSigle = coinSigle;
    }
	@Transient
	public Double getCoinSigle() {
	    return coinSigle;
    }

}
