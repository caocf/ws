package com.cplatform.b2c.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TShopCustomerService entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_SHOP_CUSTOMER_SERVICE")
public class TShopCustomerService implements java.io.Serializable {

	// Fields

	/** serialVersionUID */
    private static final long serialVersionUID = 5186919592490532466L;

	private Integer id;

	private String nickName;

	private String code;

	private Integer shopClass;

	private Integer shopId;
	
	private Integer status;

	// Constructors

	/** default constructor */
	public TShopCustomerService() {
	}

	/** minimal constructor */
	public TShopCustomerService(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public TShopCustomerService(Integer id, String nickName, String code, Integer shopClass, Integer shopId) {
		this.id = id;
		this.nickName = nickName;
		this.code = code;
		this.shopClass = shopClass;
		this.shopId = shopId;
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

	@Column(name = "NICK_NAME", length = 50)
	public String getNickName() {
		return this.nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	@Column(name = "CODE", length = 2000)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
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

	@Column(name = "STATUS")
    public Integer getStatus() {
    	return status;
    }

	
    public void setStatus(Integer status) {
    	this.status = status;
    }

}