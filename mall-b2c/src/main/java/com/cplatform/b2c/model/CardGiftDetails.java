package com.cplatform.b2c.model;

import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "T_MARKET_CARD_GIFT_DETAILS")
public class CardGiftDetails {

	private Long id;

	private String status;

	private String mobile;

	private String orderId;

	private BigDecimal useMoney;

	private String useTime;

	private String createTime;

	private String updateTime;

	private String cardLink;

	private CardGift gift;

	@Id
	@Column(name = "ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "CREATE_TIME")
	@Basic
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Column(name = "UPDATE_TIME")
	@Basic
	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "STATUS")
	@Basic
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "CARD_LINK")
	@Basic
	public String getCardLink() {
		return cardLink;
	}

	public void setCardLink(String cardLink) {
		this.cardLink = cardLink;
	}

	@Column(name = "MOBILE")
	@Basic
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "ORDER_ID")
	@Basic
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@Column(name = "USE_MONEY")
	@Basic
	public BigDecimal getUseMoney() {
		return useMoney;
	}

	public void setUseMoney(BigDecimal useMoney) {
		this.useMoney = useMoney;
	}

	@Column(name = "USE_TIME")
	@Basic
	public String getUseTime() {
		return useTime;
	}

	public void setUseTime(String useTime) {
		this.useTime = useTime;
	}

	private String useTimeS;

	private String useTimeE;

	private String createTimeS;

	private String createTimeE;

	@Transient
	public String getUseTimeS() {
		return useTimeS;
	}

	public void setUseTimeS(String useTimeS) {
		this.useTimeS = useTimeS;
	}

	@Transient
	public String getUseTimeE() {
		return useTimeE;
	}

	public void setUseTimeE(String useTimeE) {
		this.useTimeE = useTimeE;
	}

	@Transient
	public String getCreateTimeS() {
		return createTimeS;
	}

	public void setCreateTimeS(String createTimeS) {
		this.createTimeS = createTimeS;
	}

	@Transient
	public String getCreateTimeE() {
		return createTimeE;
	}

	public void setCreateTimeE(String createTimeE) {
		this.createTimeE = createTimeE;
	}

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.EAGER)
	@JoinColumn(name = "CARD_ID")
	public CardGift getGift() {
		return gift;
	}

	public void setGift(CardGift gift) {
		this.gift = gift;
	}

	public CardGiftDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CardGiftDetails(Long id, String status, String mobile, String orderId, BigDecimal useMoney, String useTime, String createTime,
	                       String updateTime, String cardLink, CardGift gift, String useTimeS, String useTimeE, String createTimeS, String createTimeE) {
		super();
		this.id = id;
		this.status = status;
		this.mobile = mobile;
		this.orderId = orderId;
		this.useMoney = useMoney;
		this.useTime = useTime;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.cardLink = cardLink;
		this.gift = gift;
		this.useTimeS = useTimeS;
		this.useTimeE = useTimeE;
		this.createTimeS = createTimeS;
		this.createTimeE = createTimeE;
	}

}
