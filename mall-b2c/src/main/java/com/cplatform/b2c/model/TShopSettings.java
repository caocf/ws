package com.cplatform.b2c.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TShopSettings entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_SHOP_SETTINGS", schema = "LIFE")
public class TShopSettings implements java.io.Serializable {

	// Fields

	/** serialVersionUID */
    private static final long serialVersionUID = -8566008846418678233L;

	private Integer id;

	private String shopPicUrl;

	private String homepageWord;

	private Integer shopClass;

	private Integer shopId;

	private String picName;

	// Constructors

	/** default constructor */
	public TShopSettings() {
	}

	/** minimal constructor */
	public TShopSettings(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public TShopSettings(Integer id, String shopPicUrl, String homepageWord, Integer shopClass, Integer shopId, String picName) {
		this.id = id;
		this.shopPicUrl = shopPicUrl;
		this.homepageWord = homepageWord;
		this.shopClass = shopClass;
		this.shopId = shopId;
		this.picName = picName;
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

	@Column(name = "SHOP_PIC_URL", length = 100)
	public String getShopPicUrl() {
		return this.shopPicUrl;
	}

	public void setShopPicUrl(String shopPicUrl) {
		this.shopPicUrl = shopPicUrl;
	}

	@Column(name = "HOMEPAGE_WORD")
	public String getHomepageWord() {
		return this.homepageWord;
	}

	public void setHomepageWord(String homepageWord) {
		this.homepageWord = homepageWord;
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

	@Column(name = "PIC_NAME", length = 100)
	public String getPicName() {
		return this.picName;
	}

	public void setPicName(String picName) {
		this.picName = picName;
	}

}