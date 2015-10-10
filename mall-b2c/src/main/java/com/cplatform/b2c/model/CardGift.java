package com.cplatform.b2c.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "T_MARKET_CARD_GIFT")
public class CardGift {

	private Long id;

	private String cardNo;

	private String cardName;

	private String cardDesc;

	private int cardNum;

	private BigDecimal cardMoney;

	private String beginTime;

	private String endTime;

	private String useScope;

	private String limitSum;

	private BigDecimal totalAmount;

	private String createTime;

	private String updateTime;

	private String status;

	private String cardLink;

	private List<CardGiftDetails> details = new ArrayList<CardGiftDetails>();

	@Id
	@Column(name = "ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "CARD_NO")
	@Basic
	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	@Column(name = "CARD_NAME")
	@Basic
	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	@Column(name = "CARD_DESC")
	@Basic
	public String getCardDesc() {
		return cardDesc;
	}

	public void setCardDesc(String cardDesc) {
		this.cardDesc = cardDesc;
	}

	@Column(name = "CARD_NUM")
	@Basic
	public int getCardNum() {
		return cardNum;
	}

	public void setCardNum(int cardNum) {
		this.cardNum = cardNum;
	}

	@Column(name = "CARD_MONEY")
	@Basic
	public BigDecimal getCardMoney() {
		return cardMoney;
	}

	public void setCardMoney(BigDecimal cardMoney) {
		this.cardMoney = cardMoney;
	}

	@Column(name = "BEGIN_TIME")
	@Basic
	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	@Column(name = "END_TIME")
	@Basic
	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	@Column(name = "USE_SCOPE")
	@Basic
	public String getUseScope() {
		return useScope;
	}

	public void setUseScope(String useScope) {
		this.useScope = useScope;
	}

	@Column(name = "LIMIT_SUM")
	@Basic
	public String getLimitSum() {
		return limitSum;
	}

	public void setLimitSum(String limitSum) {
		this.limitSum = limitSum;
	}

	@Column(name = "TOTAL_AMOUNT")
	@Basic
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
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

	private String createTimeS;

	private String createTimeE;

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

	@OneToMany(mappedBy = "gift", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<CardGiftDetails> getDetails() {
		return details;
	}

	public void setDetails(List<CardGiftDetails> details) {
		this.details = details;
	}

}
