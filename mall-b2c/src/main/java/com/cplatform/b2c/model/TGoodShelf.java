package com.cplatform.b2c.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TGoodShelf entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_GOOD_SHELF")
public class TGoodShelf implements java.io.Serializable {

	// Fields

	/** serialVersionUID */
    private static final long serialVersionUID = 5580398875153741615L;

	private Integer id;

	private String title;

	private Integer pid;

	private Integer levelFlag;

	private Integer orderIndex;

	private Integer shopClass;

	private Integer shopId;

	private Integer shopUserId;

	private String updateTime;

	private String imgUrl;

	// Constructors

	/** default constructor */
	public TGoodShelf() {
	}

	/** minimal constructor */
	public TGoodShelf(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public TGoodShelf(Integer id, String title, Integer pid, Integer levelFlag, Integer orderIndex, Integer shopClass, Integer shopId,
	                  Integer shopUserId, String updateTime, String imgUrl) {
		this.id = id;
		this.title = title;
		this.pid = pid;
		this.levelFlag = levelFlag;
		this.orderIndex = orderIndex;
		this.shopClass = shopClass;
		this.shopId = shopId;
		this.shopUserId = shopUserId;
		this.updateTime = updateTime;
		this.imgUrl = imgUrl;
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

	@Column(name = "TITLE", length = 50)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "PID", precision = 9, scale = 0)
	public Integer getPid() {
		return this.pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	@Column(name = "LEVEL_FLAG", precision = 1, scale = 0)
	public Integer getLevelFlag() {
		return this.levelFlag;
	}

	public void setLevelFlag(Integer levelFlag) {
		this.levelFlag = levelFlag;
	}

	@Column(name = "ORDER_INDEX", precision = 2, scale = 0)
	public Integer getOrderIndex() {
		return this.orderIndex;
	}

	public void setOrderIndex(Integer orderIndex) {
		this.orderIndex = orderIndex;
	}

	@Column(name = "SHOP_CLASS", precision = 1, scale = 0)
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

	@Column(name = "IMG_URL", length = 100)
	public String getImgUrl() {
		return this.imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

}