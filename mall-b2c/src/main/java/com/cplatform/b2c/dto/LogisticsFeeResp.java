package com.cplatform.b2c.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 物流价格接口返回信息. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2014-3-28 下午4:24:00
 * <p>
 * Company: 苏州宽连信息技术有限公司
 * <p>
 * 
 * @author zhangdong-ca@c-platform.com
 * @version 1.0.0
 */
public class LogisticsFeeResp {

	@JsonProperty("GOODS")
	private List<Good> goods = new ArrayList<Good>();

	public List<Good> getGoods() {
		return goods;
	}

	public void setGoods(List<Good> goods) {
		this.goods = goods;
	}

	public static class Good {

		@JsonProperty("LOGISTICSFEE")
		private BigDecimal logisticsfee; // 物流价格

		@JsonProperty("GOOD_ID")
		private Long id; // 商品ID

		public BigDecimal getLogisticsfee() {
			return logisticsfee;
		}

		public void setLogisticsfee(BigDecimal logisticsfee) {
			this.logisticsfee = logisticsfee;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}
	}
}
