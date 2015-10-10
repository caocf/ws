package com.cplatform.mall.back.comment.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 门店评论表 Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-6-4 上午10:23:16
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: LiuDong@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */

@Table(name = "T_SHOP_COMMENT")
@Entity
public class ShopComment implements Serializable {

	private Long id;

	@SequenceGenerator(name = "seq_item_COMMENT", sequenceName = "SEQ_ITEM_COMMENT")
	@Id
	@GeneratedValue(generator = "seq_item_COMMENT")
	@JsonProperty
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "CONTENT")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "UPDATE_TIME")
	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "USER_ID")
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "STATUS")
	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	@Column(name = "SHOP_CLASS")
	public Long getShopClass() {
		return shopClass;
	}

	public void setShopClass(Long shopClass) {
		this.shopClass = shopClass;
	}

	@Column(name = "SHOP_ID")
	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	@Transient
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Transient
	public String getStatusName(){

		switch (this.getStatus().intValue()) {
			case 0:
				return "未审核";
			case 1:
				return "审核通过";
			default:
				return "审核驳回";

		}
	
	}
	
	@Transient
	public String getShopClassName(){

		switch (this.getShopClass().intValue()) {
			case 1:
				return "业务门店";
			case 2:
				return "商户";
			default:
				return "渠道商";

		}
	
	}


	private String content;

	private String updateTime;

	private Long userId;

	private Long status;

	private Long shopClass;

	private Long shopId;

	private String name;

}
