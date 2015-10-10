package com.cplatform.b2c.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Formula;

/**
 * VSearchGood entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "V_SEARCH_GOOD")
public class VSearchGood implements java.io.Serializable {

	// Fields

	// private Integer GId;

	// private Integer GOrgId;

	/** serialVersionUID */
	private static final long serialVersionUID = 7103796846035026215L;

	private String GName;

	private String GShortName;

	private Integer GStoreId;

	private String GStoreName;

	private String GStoreShortName;

	private Integer GTypeId;

	private String GTypeName;

	private String GBrand;

	private String GCreateTime;

	private String GUpdateTime;

	private String GMarketContent;

	private String GProperty;

	private Integer GClickNum;

	private Integer GSaleNum;

	private String GTags;

	private String GWebPath;

	private String GMarketPrice;

	private String GShopPrice;
	
	private Double GShopPriceNumber;

	private String GRedPrice;

//	private String GOtherPrice;

	private String GRemark;

	private String itemPath;

	private String imgPath;

	private String GLogisticsFee;

	private Integer GLogisticsFeeType;

	private Integer GPostFlag;

	private Integer GItemMode;

	private Float GRank;
	
	private String GStartTime;
	
	private String GStopTime;
	
	private Integer GIsView;
	
	private Integer GGroupFlag;
	
	private Integer GIseckill;
	
	private String GSource;

	// Fields

	private VSearchGoodId id;

	// Constructors

	/** default constructor */
	public VSearchGood() {
	}

	/** full constructor */
	public VSearchGood(VSearchGoodId id) {
		this.id = id;
	}

	/*
	 * @Column(name = "G_ID", nullable = false, precision = 8, scale = 0) public
	 * Integer getGId() { return this.GId; } public void setGId(Integer GId) {
	 * this.GId = GId; } @Column(name = "G_ORG_ID", precision = 8, scale = 0)
	 * public Integer getGOrgId() { return this.GOrgId; } public void
	 * setGOrgId(Integer GOrgId) { this.GOrgId = GOrgId; }
	 */

	@Column(name = "G_NAME", length = 100)
	public String getGName() {
		return this.GName;
	}

	public void setGName(String GName) {
		this.GName = GName;
	}

	@Column(name = "G_SHORT_NAME", length = 100)
	public String getGShortName() {
		return this.GShortName;
	}

	public void setGShortName(String GShortName) {
		this.GShortName = GShortName;
	}

	@Column(name = "G_STORE_ID", precision = 8, scale = 0)
	public Integer getGStoreId() {
		return this.GStoreId;
	}

	public void setGStoreId(Integer GStoreId) {
		this.GStoreId = GStoreId;
	}

	@Column(name = "G_STORE_NAME", length = 50)
	public String getGStoreName() {
		return this.GStoreName;
	}

	public void setGStoreName(String GStoreName) {
		this.GStoreName = GStoreName;
	}

	@Column(name = "G_STORE_SHORT_NAME", length = 20)
	public String getGStoreShortName() {
		return this.GStoreShortName;
	}

	public void setGStoreShortName(String GStoreShortName) {
		this.GStoreShortName = GStoreShortName;
	}

	@Column(name = "G_TYPE_ID", precision = 8, scale = 0)
	public Integer getGTypeId() {
		return this.GTypeId;
	}

	public void setGTypeId(Integer GTypeId) {
		this.GTypeId = GTypeId;
	}

	@Column(name = "G_TYPE_NAME", length = 20)
	public String getGTypeName() {
		return this.GTypeName;
	}

	public void setGTypeName(String GTypeName) {
		this.GTypeName = GTypeName;
	}

	@Column(name = "G_BRAND", length = 20)
	public String getGBrand() {
		return this.GBrand;
	}

	public void setGBrand(String GBrand) {
		this.GBrand = GBrand;
	}

	@Column(name = "G_CREATE_TIME", length = 14)
	public String getGCreateTime() {
		return this.GCreateTime;
	}

	public void setGCreateTime(String GCreateTime) {
		this.GCreateTime = GCreateTime;
	}

	@Column(name = "G_UPDATE_TIME", length = 14)
	public String getGUpdateTime() {
		return this.GUpdateTime;
	}

	public void setGUpdateTime(String GUpdateTime) {
		this.GUpdateTime = GUpdateTime;
	}

	@Column(name = "G_MARKET_CONTENT", length = 200)
	public String getGMarketContent() {
		return this.GMarketContent;
	}

	public void setGMarketContent(String GMarketContent) {
		this.GMarketContent = GMarketContent;
	}

	@Column(name = "G_PROPERTY", length = 4000)
	public String getGProperty() {
		return this.GProperty;
	}

	public void setGProperty(String GProperty) {
		this.GProperty = GProperty;
	}

	@Column(name = "G_CLICK_NUM", precision = 8, scale = 0)
	public Integer getGClickNum() {
		return this.GClickNum;
	}

	public void setGClickNum(Integer GClickNum) {
		this.GClickNum = GClickNum;
	}

	@Column(name = "G_SALE_NUM")
	public Integer getGSaleNum() {
		return GSaleNum;
	}

	public void setGSaleNum(Integer gSaleNum) {
		GSaleNum = gSaleNum;
	}

	@Column(name = "G_TAGS", length = 4000)
	public String getGTags() {
		return this.GTags;
	}

	public void setGTags(String GTags) {
		this.GTags = GTags;
	}

	@Column(name = "G_WEB_PATH", length = 100, nullable = true)
	public String getGWebPath() {
		return this.GWebPath;
	}

	public void setGWebPath(String GWebPath) {
		this.GWebPath = GWebPath;
	}

	@Column(name = "G_MARKET_PRICE", length = 4000)
	public String getGMarketPrice() {
		return this.GMarketPrice;
	}

	public void setGMarketPrice(String GMarketPrice) {
		this.GMarketPrice = GMarketPrice;
	}

	@Column(name = "G_SHOP_PRICE", length = 4000)
	public String getGShopPrice() {
		return this.GShopPrice;
	}

	public void setGShopPrice(String GShopPrice) {
		this.GShopPrice = GShopPrice;
	}

	@Formula(value="to_number(G_SHOP_PRICE)")
    public Double getGShopPriceNumber() {
    	return GShopPriceNumber;
    }

	
    public void setGShopPriceNumber(Double gShopPriceNumber) {
    	GShopPriceNumber = gShopPriceNumber;
    }

	@Column(name = "G_MIN_PRICE", length = 4000)
	public String getGRedPrice() {
		return this.GRedPrice;
	}

	public void setGRedPrice(String GRedPrice) {
		this.GRedPrice = GRedPrice;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides( { @AttributeOverride(name = "GId", column = @Column(name = "G_ID", nullable = false, precision = 8, scale = 0)),
	        @AttributeOverride(name = "GOrgId", column = @Column(name = "G_ORG_ID", precision = 8, scale = 0)) })
	public VSearchGoodId getId() {
		return this.id;
	}

	public void setId(VSearchGoodId id) {
		this.id = id;
	}

	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "G_REMARK", columnDefinition = "CLOB", nullable = true)
	public String getGRemark() {
		return GRemark;
	}

	public void setGRemark(String gRemark) {
		GRemark = gRemark;
	}

	@Transient
	public String getItemPath() {
		return itemPath;
	}

	public void setItemPath(String itemPath) {
		this.itemPath = itemPath;
	}

	@Transient
	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	@Column(name = "G_LOGISTICS_FEE")
	public String getGLogisticsFee() {
		return GLogisticsFee;
	}

	public void setGLogisticsFee(String logisticsFee) {
		GLogisticsFee = logisticsFee;
	}

	@Column(name = "G_LOGISTICS_FEE_TYPE")
	public Integer getGLogisticsFeeType() {
		return GLogisticsFeeType;
	}

	public void setGLogisticsFeeType(Integer logisticsFeeType) {
		GLogisticsFeeType = logisticsFeeType;
	}

	@Column(name = "G_POST_FLAG")
	public Integer getGPostFlag() {
		return GPostFlag;
	}

	public void setGPostFlag(Integer postFlag) {
		GPostFlag = postFlag;
	}

	@Column(name = "G_ITEM_MODE")
	public Integer getGItemMode() {
		return GItemMode;
	}

	public void setGItemMode(Integer itemMode) {
		GItemMode = itemMode;
	}

	@Column(name = "G_RANK")
	public Float getGRank() {
		return GRank;
	}

	public void setGRank(Float rank) {
		GRank = rank;
	}

	@Column(name = "G_START_TIME")
    public String getGStartTime() {
    	return GStartTime;
    }

	
    public void setGStartTime(String gStartTime) {
    	GStartTime = gStartTime;
    }

    @Column(name = "G_STOP_TIME")
    public String getGStopTime() {
    	return GStopTime;
    }

	
    public void setGStopTime(String gStopTime) {
    	GStopTime = gStopTime;
    }

    @Column(name = "G_IS_VIEW")
    public Integer getGIsView() {
    	return GIsView;
    }

	
    public void setGIsView(Integer gIsView) {
    	GIsView = gIsView;
    }

    @Column(name = "G_GROUP_FLAG")
    public Integer getGGroupFlag() {
    	return GGroupFlag;
    }

	
    public void setGGroupFlag(Integer gGroupFlag) {
    	GGroupFlag = gGroupFlag;
    }

    @Column(name = "G_ISECKILL")
    public Integer getGIseckill() {
    	return GIseckill;
    }

	
    public void setGIseckill(Integer gIseckill) {
    	GIseckill = gIseckill;
    }

    @Column(name = "G_SOURCE")
	public String getGSource() {
		return GSource;
	}

	public void setGSource(String gSource) {
		GSource = gSource;
	}

}