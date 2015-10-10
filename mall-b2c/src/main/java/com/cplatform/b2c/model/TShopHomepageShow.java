package com.cplatform.b2c.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TShopHomepageShow entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_SHOP_HOMEPAGE_SHOW", schema = "LIFE")
public class TShopHomepageShow implements java.io.Serializable {

	// Fields

	/** serialVersionUID */
    private static final long serialVersionUID = -4985437462451601816L;

	private Integer id;

	private String title;

	private Integer shelfId;

	private Short goodNum;

	private Short status;

	private Short orderIndex;

	private Integer shopClass;

	private Integer shopId;

	private Integer shopUserId;

	private String updateTime;

	// Constructors

	/** default constructor */
	public TShopHomepageShow() {
	}

	/** minimal constructor */
	public TShopHomepageShow(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public TShopHomepageShow(Integer id, String title, Integer shelfId, Short goodNum, Short status, Short orderIndex, Integer shopClass,
	                         Integer shopId, Integer shopUserId, String updateTime) {
		this.id = id;
		this.title = title;
		this.shelfId = shelfId;
		this.goodNum = goodNum;
		this.status = status;
		this.orderIndex = orderIndex;
		this.shopClass = shopClass;
		this.shopId = shopId;
		this.shopUserId = shopUserId;
		this.updateTime = updateTime;
	}

	// Property accessors
	@Id
	@Column(name = "ID", unique = true, nullable = false, precision = 9, scale = 0)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "TITLE", length = 100)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "SHELF_ID", precision = 9, scale = 0)
	public Integer getShelfId() {
		return this.shelfId;
	}

	public void setShelfId(Integer shelfId) {
		this.shelfId = shelfId;
	}

	@Column(name = "GOOD_NUM", precision = 3, scale = 0)
	public Short getGoodNum() {
		return this.goodNum;
	}

	public void setGoodNum(Short goodNum) {
		this.goodNum = goodNum;
	}

	@Column(name = "STATUS")
	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	@Column(name = "ORDER_INDEX")
	public Short getOrderIndex() {
		return this.orderIndex;
	}

	public void setOrderIndex(Short orderIndex) {
		this.orderIndex = orderIndex;
	}

	@Column(name = "SHOP_CLASS")
	public Integer getShopClass() {
		return this.shopClass;
	}

	public void setShopClass(Integer shopClass) {
		this.shopClass = shopClass;
	}

	@Column(name = "SHOP_ID", precision = 9, scale = 0)
	public Integer getShopId() {
		return this.shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	@Column(name = "SHOP_USER_ID", precision = 9, scale = 0)
	public Integer getShopUserId() {
		return this.shopUserId;
	}

	public void setShopUserId(Integer shopUserId) {
		this.shopUserId = shopUserId;
	}

	@Column(name = "UPDATE_TIME", length = 14)
	public String getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

}