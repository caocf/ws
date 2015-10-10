package com.cplatform.b2c.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;

/**
 * 调用物流价格接口类. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2014-3-28 下午4:24:39
 * <p>
 * Company: 苏州宽连信息技术有限公司
 * <p>
 * 
 * @author zhangdong-ca@c-platform.com
 * @version 1.0.0
 */
public class LogisticsFeeDTO {

	public LogisticsFeeDTO(List<Good> goods, String areaCode) {
		this.goods = goods;
		this.areaCode = areaCode;
	}

	@JsonProperty("GOODS")
	private List<Good> goods = Lists.newArrayList();

	public List<Good> getGoods() {
		return goods;
	}

	public void setGoods(List<Good> goods) {
		this.goods = goods;
	}

	@JsonProperty("AREACODE")
	private final String areaCode; // 手机号码

	public static class Good {

		public Good(int count, Long id) {
			this.count = count;
			this.id = id;
		}

		@JsonProperty("COUNT")
		private int count; // 商品数量

		@JsonProperty("GOOD_ID")
		private Long id; // 商品ID

		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

	}

}
