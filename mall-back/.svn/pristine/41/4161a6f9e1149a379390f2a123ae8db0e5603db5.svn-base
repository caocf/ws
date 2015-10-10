package com.cplatform.mall.back.item.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 商品协议管理关联表. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2013-5-29 下午07:46:48
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author zhaowei@c-platform.com
 * @version 1.0.0
 */
@Entity
@Table(name = "T_HISUN_PRODUCTION_LINK")
public class HisunProductionLink implements java.io.Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = -608166143034288230L;

	/** 序列 **/
	private Long id;

	/** 商品结算资料表id **/
	private Long settleId;

	/** 高阳商品id **/
	private String productionId;

	/** 销售商品id **/
	private Long itemId;

	/** 商品分类 **/
	private String productionType;

	/** 结算价 单位分 **/
	private Double settlePrice;

	/** 创建时间 **/
	private String createTime;

	/** 高阳商品id(现金) **/
	private String productionIdCash;

	/** 高阳商品id（商城币） **/
	private String productionIdCoin;

	/** 高阳商品id（积分） **/
	private String productionIdScore;

	public void setId(Long id) {
		this.id = id;
	}

	@SequenceGenerator(name = "seq_item", sequenceName = "SEQ_SYS_ITEM_ID")
	@Id
	@GeneratedValue(generator = "seq_item")
	@JsonProperty
	public Long getId() {
		return id;
	}

	public void setSettleId(Long settleId) {
		this.settleId = settleId;
	}

	@Column(name = "SETTLE_ID")
	public Long getSettleId() {
		return settleId;
	}

	public void setProductionId(String productionId) {
		this.productionId = productionId;
	}

	@Column(name = "PRODUCTION_ID")
	public String getProductionId() {
		return productionId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	@Column(name = "ITEM_ID")
	public Long getItemId() {
		return itemId;
	}

	public void setProductionType(String productionType) {
		this.productionType = productionType;
	}

	@Column(name = "PRODUCTION_TYPE")
	public String getProductionType() {
		return productionType;
	}

	public void setSettlePrice(Double settlePrice) {
		this.settlePrice = settlePrice;
	}

	@Column(name = "SETTLE_PRICE")
	public Double getSettlePrice() {
		return settlePrice;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Column(name = "CREATE_TIME")
	public String getCreateTime() {
		return createTime;
	}

	public void setProductionIdCash(String productionIdCash) {
		this.productionIdCash = productionIdCash;
	}

	@Column(name = "PRODUCTION_ID_CASH")
	public String getProductionIdCash() {
		return productionIdCash;
	}

	public void setProductionIdCoin(String productionIdCoin) {
		this.productionIdCoin = productionIdCoin;
	}

	@Column(name = "PRODUCTION_ID_COIN")
	public String getProductionIdCoin() {
		return productionIdCoin;
	}

	public void setProductionIdScore(String productionIdScore) {
		this.productionIdScore = productionIdScore;
	}

	@Column(name = "PRODUCTION_ID_SCORE")
	public String getProductionIdScore() {
		return productionIdScore;
	}

	// ***********bus method*********
	private String itemName;

	private Long itemGyStatus;

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	/**
	 * 商品高阳状态
	 * 
	 * @return
	 */
	@Transient
	public String getItemGyStatusName() {
		return ItemSale.syncGyFlagMap.get(this.itemGyStatus);
	}

	@Transient
	public String getItemName() {
		return itemName;
	}

	@Transient
	public Long getItemGyStatus() {
		return itemGyStatus;
	}

	public void setItemGyStatus(Long itemGyStatus) {
		this.itemGyStatus = itemGyStatus;
	}

}