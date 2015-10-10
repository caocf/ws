package com.cplatform.mall.back.order.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonProperty;

import com.cplatform.order.ActOrderExpressInfo;

/**
 * 业务定案物流表 Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-6-7 下午6:56:42
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: LiuDong@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */

public class OrderExpress extends ActOrderExpressInfo {

	private String shopName;

	@Transient
	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
}
