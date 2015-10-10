package com.cplatform.b2c.dto;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;

/**
 * 客户端下单数据集 User: cuikai Date: 13-8-22 Time: 上午10:10
 */
public class CreateOrderDTO {

	@JsonProperty("U_ID")
	private Long userId;

	@JsonProperty("GOODS")
	private List<Good> goods = Lists.newArrayList();

	@JsonProperty("ADDRESS_ID")
	private Long addressId; // 用户收货地址ID

	@JsonProperty("ADDRESS_INFO")
	private Address addressInfo; // 用户收货地址详细信息

	@JsonProperty("USER_REMARK")
	private String userRemark; // 订单备注

	@JsonProperty("INVOICE_TYPE")
	private int invoiceType; // 发票类型

	@JsonProperty("PAY_ON_DELIVERY")
	private int payOnDelivery; // 货到付款 0否 1是

	@JsonProperty("INVOICE_SUBJECT")
	private String invoiceSubject; // 发票抬头，个人写“个人”或用和用户名，公司写“公司名称

	@JsonProperty("INVOICE_CONTENT")
	private String invoiceContent; // 发票内容，形如：商品明细、办公用品、电脑配件、耗材

	@JsonProperty("SUBJECT")
	private String subject; // 订单标题--客户端需求

	@JsonProperty("CREATE_SOURCE")
	private Long createSource; // /** 创建来源，用于标记网页创建的、短信创建的、wap创建的、手机客户端创建的………………
	                           // */

	@JsonProperty("ORDER_TYPE")
	private String orderType; // 订单类型 -- 营销下单 标识秒杀，竞拍

	@JsonProperty("BUSINESS_ID")
	private String businessId; // 业务ID -- 营销下单 ID

	@JsonProperty("EXPIRE_TIME")
	private int expireTime; // 超时时间

	public int getPayOnDelivery() {
		return payOnDelivery;
	}

	public void setPayOnDelivery(int payOnDelivery) {
		this.payOnDelivery = payOnDelivery;
	}

	public int getInvoiceType() {
		return invoiceType;
	}

	public void setInvoiceType(int invoiceType) {
		this.invoiceType = invoiceType;
	}

	public String getInvoiceSubject() {
		return invoiceSubject;
	}

	public void setInvoiceSubject(String invoiceSubject) {
		this.invoiceSubject = invoiceSubject;
	}

	public String getInvoiceContent() {
		return invoiceContent;
	}

	public void setInvoiceContent(String invoiceContent) {
		this.invoiceContent = invoiceContent;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public String getUserRemark() {
		return userRemark;
	}

	public void setUserRemark(String userRemark) {
		this.userRemark = userRemark;
	}

	public List<Good> getGoods() {
		return goods;
	}

	public void setGoods(List<Good> goods) {
		this.goods = goods;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public Address getAddressInfo() {
		return addressInfo;
	}

	public void setAddressInfo(Address addressInfo) {
		this.addressInfo = addressInfo;
	}

	public Long getCreateSource() {
		return createSource;
	}

	public void setCreateSource(Long createSource) {
		this.createSource = createSource;
	}

	public int getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(int expireTime) {
		this.expireTime = expireTime;
	}

	public static class Good {

		@JsonProperty("COUNT")
		private int count; // 商品数量

		@JsonProperty("GOOD_ID")
		private Long id; // 商品ID

		@JsonProperty("DISCOUNT")
		private int discount; // 商品折扣

		// @JsonProperty("GOOD_DESCRIPTION")
		// private String goodsDescription; // 商品描述

		@JsonIgnore
		private String source;

		@JsonIgnore
		private String eckill;

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

		public int getDiscount() {
			return discount;
		}

		public void setDiscount(int discount) {
			this.discount = discount;
		}

		public String getSource() {
			return source;
		}

		public void setSource(String source) {
			this.source = source;
		}

		public String getEckill() {
			return eckill;
		}

		public void setEckill(String eckill) {
			this.eckill = eckill;
		}

		// public String getGoodsDescription() {
		// return goodsDescription;
		// }
		//
		// public void setGoodsDescription(String goodsDescription) {
		// this.goodsDescription = goodsDescription;
		// }
	}

	public static class Address {

		@JsonProperty("MOBILE")
		private String mobile; // 手机号码

		@JsonProperty("PHONE")
		private String phone; // 收货电话

		@JsonProperty("RECEIVER_NAME")
		private String receiverName; // 收货人姓名

		@JsonProperty("ADDRESS")
		private String address; // 收货人地址

		@JsonProperty("ZIPCODE")
		private String zipcode; // 邮政编码

		@JsonProperty("REMARK")
		private String remark;

		public String getMobile() {
			return mobile;
		}

		public void setMobile(String mobile) {
			this.mobile = mobile;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public String getReceiverName() {
			return receiverName;
		}

		public void setReceiverName(String receiverName) {
			this.receiverName = receiverName;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getZipcode() {
			return zipcode;
		}

		public void setZipcode(String zipcode) {
			this.zipcode = zipcode;
		}

		public String getRemark() {
			return remark;
		}

		public void setRemark(String remark) {
			this.remark = remark;
		}
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
