package com.cplatform.mall.back.item.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 已发布商品会员购买限制表Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-6-1 下午5:42:53
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: baibo@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
@Table(name = "T_ITEM_SALE_USER_LEVEL_LINK")
@Entity
public class ItemSaleUserLevelaLink {

	private Long id;

	/** 发布商品ID **/
	private Long saleId;

	/** 允许购买会员级别 **/
	private String userLevel;

	/**
	 * 获取 id
	 * 
	 * @return id
	 */
	@SequenceGenerator(name = "seq_item", sequenceName = "SEQ_SYS_ITEM_ID")
	@Id
	@GeneratedValue(generator = "seq_item")
	@JsonProperty
	public Long getId() {
		return id;
	}

	/**
	 * 设置 id
	 * 
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "SALE_ID", precision = 8, scale = 0)
	public Long getSaleId() {
		return saleId;
	}

	public void setSaleId(Long saleId) {
		this.saleId = saleId;
	}

	@Column(name = "USER_LEVEL", precision = 4, scale = 0)
	public String getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(String userLevel) {
		this.userLevel = userLevel;
	}

}
