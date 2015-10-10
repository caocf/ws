package com.cplatform.mall.dataif.model;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonAutoDetect
public class ItemInfo {

	@JsonProperty("id")
	private String gId;

	@JsonProperty("itemId")
	private String gOrgId;

	@JsonProperty("name")
	private String gName;

	@JsonProperty("shortName")
	private String gShortName;

	@JsonProperty("storeId")
	private String gStoreId;

	@JsonProperty("storeName")
	private String gStoreName;

	@JsonProperty("storeShortName")
	private String gStoreShortName;

	@JsonProperty("typeId")
	private String gTypeId;

	@JsonProperty("typeName")
	private String gTypeName;

	@JsonProperty("brand")
	private String gBrand;

	@JsonProperty("createTime")
	private String gCreateTime;

	@JsonProperty("updatTime")
	private String gUpdateTime;

	@JsonProperty("marketContent")
	private String gMarketContent;

	@JsonProperty("property")
	private String gProperty;

	@JsonProperty("clicknum")
	private String gClickNum;

	@JsonProperty("tags")
	private String gTags;

	@JsonProperty("webPath")
	private String gWebPath;

	@JsonProperty("marketPrice")
	private String gMarketPrice;

	@JsonProperty("shopPrice")
	private String gShopPrice;

	//@JsonProperty("redPrice")
	//private String gRedPrice;

	@JsonProperty("otherPrice")
	private String gOtherPrice;

	@JsonProperty("salenum")
	private String gSaleNum;

	@JsonProperty("commentnum")
	private String gCommnetNum;

	@JsonProperty("usernum")
	private String gUserNum;

	@JsonProperty("collectnum")
	private String gCollectNum;

	@JsonProperty("stocknum")
	private String gStockNum;

	@JsonProperty("logisticsFee")
	private String gLogisticsFee;

	@JsonProperty("logisticsFeeType")
	private String gLogisticsFeeType;

	@JsonProperty("postFlag")
	private String gPostFlag;

	@JsonProperty("itemMode")
	private String gItemMode;

	@JsonProperty("startTime")
	private String gStartTime;

	@JsonProperty("stopTime")
	private String gStopTime;

	@JsonProperty("rank")
	private String gRank;

	@JsonProperty("warmPrompt")
	private String gWarmPrompt;

	@JsonProperty("groupFlag")
	private String gGroupFlag;
	
	@JsonProperty("paymentCash")
	private String gPaymentCash;
	
	@JsonProperty("paymentCoin")
	private String gPaymentCoin;
	
	@JsonProperty("paymentScore")
	private String gPaymentScore;
	
	@JsonProperty("isValid")
	private String gIsValid;
	
	@JsonProperty("iseckill")
	private String gIseckill;
	
	@JsonProperty("iseckillPrice")
	private String gIseckillPrice;
	
	@JsonProperty("regionCodes")
	private String gRegionCodes;
	
	@JsonProperty("cashIdgoods")
	private String gCashIdgoods;
	
	@JsonProperty("coinIdgoods")
	private String gCoinIdgoods;
	
	@JsonProperty("scoreIdgoods")
	private String gScoreIdgoods;
	
	@JsonProperty("userLevels")
	private String gUserLevels;
	
	//所有会员价中的最低价
	@JsonProperty("minPrice")
	private String gMinPrice;
	
	/**商品支付方式扩展   start>>>**/
	
	//组合支付方式 0：单一支付，1：组合支付
	@JsonProperty("payType")
	private String gPayType;

	// 现金支付比例，1～9之间的任一整数
	@JsonProperty("cashPayRatio")	
	private String gCashPayRatio;
	
	//商城币或积分的支付比例
	@JsonProperty("otherPayRatio")	
	private String gOtherPayRatio;
	
	// 话费支付，0：不支持，1：支持
	@JsonProperty("billPay")	
	private String gBillPay;
	
	/**商品支付方式扩展   end<<<<**/
	
	public String getgMinPrice() {
		return gMinPrice;
	}

	public void setgMinPrice(String gMinPrice) {
		this.gMinPrice = gMinPrice;
	}

	public String getgId() {
		return gId;
	}

	public String getgPaymentCash() {
		return gPaymentCash;
	}

	public void setgPaymentCash(String gPaymentCash) {
		this.gPaymentCash = gPaymentCash;
	}

	public String getgPaymentCoin() {
		return gPaymentCoin;
	}

	public void setgPaymentCoin(String gPaymentCoin) {
		this.gPaymentCoin = gPaymentCoin;
	}

	public String getgPaymentScore() {
		return gPaymentScore;
	}

	public void setgPaymentScore(String gPaymentScore) {
		this.gPaymentScore = gPaymentScore;
	}

	public void setgId(String gId) {
		this.gId = gId;
	}

	public String getgOrgId() {
		return gOrgId;
	}

	public void setgOrgId(String gOrgId) {
		this.gOrgId = gOrgId;
	}

	public String getgName() {
		return gName;
	}

	public void setgName(String gName) {
		this.gName = gName;
	}

	public String getgShortName() {
		return gShortName;
	}

	public void setgShortName(String gShortName) {
		this.gShortName = gShortName;
	}

	public String getgStoreId() {
		return gStoreId;
	}

	public void setgStoreId(String gStoreId) {
		this.gStoreId = gStoreId;
	}

	public String getgStoreName() {
		return gStoreName;
	}

	public void setgStoreName(String gStoreName) {
		this.gStoreName = gStoreName;
	}

	public String getgStoreShortName() {
		return gStoreShortName;
	}

	public void setgStoreShortName(String gStoreShortName) {
		this.gStoreShortName = gStoreShortName;
	}

	public String getgTypeId() {
		return gTypeId;
	}

	public void setgTypeId(String gTypeId) {
		this.gTypeId = gTypeId;
	}

	public String getgTypeName() {
		return gTypeName;
	}

	public void setgTypeName(String gTypeName) {
		this.gTypeName = gTypeName;
	}

	public String getgBrand() {
		return gBrand;
	}

	public void setgBrand(String gBrand) {
		this.gBrand = gBrand;
	}

	public String getgCreateTime() {
		return gCreateTime;
	}

	public void setgCreateTime(String gCreateTime) {
		this.gCreateTime = gCreateTime;
	}

	public String getgUpdateTime() {
		return gUpdateTime;
	}

	public void setgUpdateTime(String gUpdateTime) {
		this.gUpdateTime = gUpdateTime;
	}

	public String getgMarketContent() {
		return gMarketContent;
	}

	public void setgMarketContent(String gMarketContent) {
		this.gMarketContent = gMarketContent;
	}

	public String getgProperty() {
		return gProperty;
	}

	public void setgProperty(String gProperty) {
		this.gProperty = gProperty;
	}

	public String getgClickNum() {
		return gClickNum;
	}

	public void setgClickNum(String gClickNum) {
		this.gClickNum = gClickNum;
	}

	public String getgTags() {
		return gTags;
	}

	public void setgTags(String gTags) {
		this.gTags = gTags;
	}

	public String getgWebPath() {
		return gWebPath;
	}

	public void setgWebPath(String gWebPath) {
		this.gWebPath = gWebPath;
	}

	public String getgMarketPrice() {
		return gMarketPrice;
	}

	public void setgMarketPrice(String gMarketPrice) {
		this.gMarketPrice = gMarketPrice;
	}

	public String getgShopPrice() {
		return gShopPrice;
	}

	public void setgShopPrice(String gShopPrice) {
		this.gShopPrice = gShopPrice;
	}

//	public String getgRedPrice() {
//		return gRedPrice;
//	}
//
//	public void setgRedPrice(String gRedPrice) {
//		this.gRedPrice = gRedPrice;
//	}

	public String getgOtherPrice() {
		return gOtherPrice;
	}

	public void setgOtherPrice(String gOtherPrice) {
		this.gOtherPrice = gOtherPrice;
	}

	public String getgSaleNum() {
		return gSaleNum;
	}

	public void setgSaleNum(String gSaleNum) {
		this.gSaleNum = gSaleNum;
	}

	public String getgCommnetNum() {
		return gCommnetNum;
	}

	public void setgCommnetNum(String gCommnetNum) {
		this.gCommnetNum = gCommnetNum;
	}

	public String getgUserNum() {
		return gUserNum;
	}

	public void setgUserNum(String gUserNum) {
		this.gUserNum = gUserNum;
	}

	public String getgCollectNum() {
		return gCollectNum;
	}

	public void setgCollectNum(String gCollectNum) {
		this.gCollectNum = gCollectNum;
	}

	public String getgStockNum() {
		return gStockNum;
	}

	public void setgStockNum(String gStockNum) {
		this.gStockNum = gStockNum;
	}

	public String getgLogisticsFee() {
		return gLogisticsFee;
	}

	public void setgLogisticsFee(String gLogisticsFee) {
		this.gLogisticsFee = gLogisticsFee;
	}

	public String getgLogisticsFeeType() {
		return gLogisticsFeeType;
	}

	public void setgLogisticsFeeType(String gLogisticsFeeType) {
		this.gLogisticsFeeType = gLogisticsFeeType;
	}

	public String getgPostFlag() {
		return gPostFlag;
	}

	public void setgPostFlag(String gPostFlag) {
		this.gPostFlag = gPostFlag;
	}

	public String getgItemMode() {
		return gItemMode;
	}

	public void setgItemMode(String gItemMode) {
		this.gItemMode = gItemMode;
	}

	public String getgStartTime() {
		return gStartTime;
	}

	public void setgStartTime(String gStartTime) {
		this.gStartTime = gStartTime;
	}

	public String getgStopTime() {
		return gStopTime;
	}

	public void setgStopTime(String gStopTime) {
		this.gStopTime = gStopTime;
	}

	public String getgRank() {
		return gRank;
	}

	public void setgRank(String gRank) {
		this.gRank = gRank;
	}

	public String getgWarmPrompt() {
		return gWarmPrompt;
	}

	public void setgWarmPrompt(String gWarmPrompt) {
		this.gWarmPrompt = gWarmPrompt;
	}

	public String getgGroupFlag() {
		return gGroupFlag;
	}

	public void setgGroupFlag(String gGroupFlag) {
		this.gGroupFlag = gGroupFlag;
	}

	public String getgIsValid() {
		return gIsValid;
	}

	public void setgIsValid(String gIsValid) {
		this.gIsValid = gIsValid;
	}

	public String getgIseckill() {
		return gIseckill;
	}

	public void setgIseckill(String gIseckill) {
		this.gIseckill = gIseckill;
	}

	public String getgIseckillPrice() {
		return gIseckillPrice;
	}

	public void setgIseckillPrice(String gIseckillPrice) {
		this.gIseckillPrice = gIseckillPrice;
	}

	public String getgRegionCodes() {
		return gRegionCodes;
	}

	public void setgRegionCodes(String gRegionCodes) {
		this.gRegionCodes = gRegionCodes;
	}

	public String getgCashIdgoods() {
		return gCashIdgoods;
	}

	public void setgCashIdgoods(String gCashIdgoods) {
		this.gCashIdgoods = gCashIdgoods;
	}

	public String getgCoinIdgoods() {
		return gCoinIdgoods;
	}

	public void setgCoinIdgoods(String gCoinIdgoods) {
		this.gCoinIdgoods = gCoinIdgoods;
	}

	public String getgScoreIdgoods() {
		return gScoreIdgoods;
	}

	public void setgScoreIdgoods(String gScoreIdgoods) {
		this.gScoreIdgoods = gScoreIdgoods;
	}

	public String getgPayType() {
		return gPayType;
	}

	public void setgPayType(String gPayType) {
		this.gPayType = gPayType;
	}

	public String getgCashPayRatio() {
		return gCashPayRatio;
	}

	public void setgCashPayRatio(String gCashPayRatio) {
		this.gCashPayRatio = gCashPayRatio;
	}

	public String getgOtherPayRatio() {
		return gOtherPayRatio;
	}

	public void setgOtherPayRatio(String gOtherPayRatio) {
		this.gOtherPayRatio = gOtherPayRatio;
	}

	public String getgBillPay() {
		return gBillPay;
	}

	public void setgBillPay(String gBillPay) {
		this.gBillPay = gBillPay;
	}

	public String getgUserLevels() {
		return gUserLevels;
	}

	public void setgUserLevels(String gUserLevels) {
		this.gUserLevels = gUserLevels;
	}

	
	

}
