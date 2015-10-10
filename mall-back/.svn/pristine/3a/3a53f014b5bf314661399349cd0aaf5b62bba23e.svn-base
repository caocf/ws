package com.cplatform.mall.back.item.entity;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonProperty;

import com.cplatform.mall.back.sys.entity.SysFee;

/**
 * 基于商品基本属性所发布销售的商品表 Title. <br>
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
@Table(name = "T_ITEM_SALE")
@Entity
public class ItemSale {

	/** 不限库存，库存初始值 **/
	public static final Long INIT_STOCK_NUM = -1000L;

	/** 逻辑删除标记 */
	public static Long STATUS_DELETE = -1L;

	/** 草稿 */
	public static Long STATUS_BASE_SAVED = 0l;

	/** 待审核 */
	public static Long STATUS_BASE_NO_AUDIT = 1l;

	/** 审核中 */
	public static Long STATUS_BASE_AUDITING = 2l;

	/** 基本资料审核通过 */
	public static Long STATUS_BASE_AUDIT_PASS = 3l;

	/** 基本资料审核驳回 */
	public static Long STATUS_BASE_AUDIT_RETJECT = 4l;

	// /** 发布商品草稿 */
	// public static Long STATUS_SALE_SAVED = 5l;
	//
	// /** 发布商品待审核 */
	// public static Long STATUS_SALE_NO_AUDIT = 6l;
	//
	// /** 发布商品审核中 */
	// public static Long STATUS_SALE_AUDITING = 7l;
	//
	// /** 发布商品审核通过 */
	// public static Long STATUS_SALE_AUDIT_PASS = 8l;
	//
	// /** 发布商品审核通过 */
	// public static Long STATUS_SALE_AUDIT_REJECT = 9l;

	/** 发布商品审核驳回 */
	public static Map<Long, String> virtualTypeMap = null;
	static {
		virtualTypeMap = new HashMap<Long, String>();
		virtualTypeMap.put(1L, "卡密");
		virtualTypeMap.put(2L, "直充");
	}

	public static Map<Long, String> iseckillMap = null;
	static {
		iseckillMap = new HashMap<Long, String>();
		iseckillMap.put(0L, "普通商品");
		iseckillMap.put(1L, "秒杀商品");
		iseckillMap.put(2L, "礼品卡兑换商品");
		iseckillMap.put(3L, "促销商品");
		iseckillMap.put(4L, "礼品卡");
	}
	public static Map<Long, String> sourceMap = null;
	static {
		sourceMap = new HashMap<Long, String>();
		sourceMap.put(0L, "普通");
		sourceMap.put(1L, "拉手");
	}
	//商品来源
	private Long itemSource;

	@Transient
	public String getIseckillName() {

		return iseckillMap.get(this.getIseckill());

	}

	@Transient
	public String getVirtualTypeName() {
		return virtualTypeMap.get(this.virtualType);
	}

	public static Map<Long, String> verifyCodeTypeMap = null;
	static {
		verifyCodeTypeMap = new HashMap<Long, String>();
		verifyCodeTypeMap.put(1l, "一维码");
		verifyCodeTypeMap.put(2l, "二维码");
	}

	public static Map<Long, String> sendCodeModeMap = null;
	static {
		sendCodeModeMap = new HashMap<Long, String>();
		sendCodeModeMap.put(0l, "不发码");
		sendCodeModeMap.put(1l, "按照订单发码");
		sendCodeModeMap.put(2l, "按照商品个数发码");
	}

	public static Map<Long, String> statusMap = null;
	static {
		statusMap = new HashMap<Long, String>();
		statusMap.put(-1L, "已删除");
		statusMap.put(0l, "草稿");
		statusMap.put(1l, "待审核");
		statusMap.put(2l, "审核中");
		statusMap.put(3l, "审核通过");
		statusMap.put(4l, "审核驳回");
		// /statusMap.put(5l, "(发布)草稿");
		// statusMap.put(6l, "(发布)审核中");
		// statusMap.put(7l, "(发布)审核中");
		// statusMap.put(8l, "(发布)审核通过");
		// statusMap.put(9l, "(发布)审核驳回");
	}

	public static Map<Long, String> syncGyFlagMap = null;
	static {
		syncGyFlagMap = new HashMap<Long, String>();
		syncGyFlagMap.put(0L, "未同步");
		syncGyFlagMap.put(1L, "已同步");
		syncGyFlagMap.put(2L, "待审核");// 对应结算status：3
		syncGyFlagMap.put(3L, "审核通过");// 对应结算status：1
		syncGyFlagMap.put(4L, "审核驳回");// 对应结算status：4
		syncGyFlagMap.put(5L, "删除");// 对应结算status：2
	}

	public static Map<Long, String> isValidMap = null;
	static {
		isValidMap = new HashMap<Long, String>();
		isValidMap.put(0L, "下架");
		isValidMap.put(1L, "上架");
	}

	@Transient
	public static Map<Long, String> getIsValidMap() {
		return isValidMap;
	}

	@Transient
	public String getSyncGyFlagName() {
		return syncGyFlagMap.get(this.getSyncGyFlag());
	}

	@Transient
	public String getVerifyCodeTypeName() {
		return verifyCodeTypeMap.get(this.verifyCodeType);
	}

	@Transient
	public String getSendCodeModeName() {
		return sendCodeModeMap.get(this.sendCodeMode);
	}

	@Transient
	public String getStatusName() {
		return statusMap.get(this.status);
	}

	private Long id;

	/** 商品ID和t_item_org匹配 **/
	private Long orgId;

	/** 支付体系价格ID **/
	private Long salePriceId;

	/** 码类型1--维码 2--二维码 **/
	private Long verifyCodeType;

	/** 发码方式0--不发码 1--按照订单发码 2--按照商品个数发码 **/
	private Long sendCodeMode;

	/** 制码方0--平台自己 1--方正码平台 2--第三方应用 **/
	private Long sendCodeChannel;

	/** 制码渠道如果制码方选择第三方应用，则该字段有用 10--85度C 11--鲜芋仙 ... **/
	private Long sendCodeSrc;

	/** 是否需要物流配送 0-不需要物流配送 1-需要物流配送 **/
	private Long postFlag;

	/** 销售有效开始时间 **/
	private String saleStartTime;

	/** 销售有效结束时间 **/
	private String saleStopTime;

	/** 验证有效开始时间 **/
	private String verifyStartTime;

	/** 验证有效结束时间 **/
	private String verifyStopTime;

	/** 商品库存数量 **/
	private Long stockNum;

	/** 单个用户购买数量 0不限制 **/
	private Long userPerBuyNum;

	/** 商品状态0--草稿 1--待审核 2--审核中 3--审核通过 4--审核驳回 **/
	private Long status;

	/** 商品是否有效 0--无效 1--有效 **/
	private Long isValid;

	/** 是否同步高阳0--未成功同步 1--成功同步 **/
	private Long syncGyFlag;

	/** "1--业务门店2--商户3--渠道商" **/
	private Long shopClass;

	/** 结算商户ID匹配表t_store **/
	private Long storeId;

	/** 市场价 **/
	private Float marketPrice;

	/** 商品类型0--实物 1--虚拟物 **/
	private Long itemMode;

	/** 商品分类 **/
	private Long typeId;

	/** "是否是优惠套餐，如果是则是N多商品的组合 0-普通商品 1-优惠套餐" **/
	private Long groupFlag;

	/** 是否虚拟商品 0-否 1-是 */
	private Long virtualFlag;

	/** 虚拟商品类型1-卡密 2-直充 */
	private Long virtualType;

	/** 商品名称 */
	private String name;

	/** 商品简称 */
	private String shortName;

	/** 温馨提示 */
	private String warmPrompt;

	/** 商品介绍 */
	private String remark;

	/** 商户用户id */
	private Long shopUserId;

	/** 更新时间 */
	private String updateTime;

	/** 品牌 */
	private String brand;

	/** 重量 */
	private Double weight;

	/** 是否秒杀 **/
	private Long iseckill;

	/** 秒杀价格 **/

	private Double iseckillPrice;

	/** 物流运费 **/
	private Double logisticsFee;

	/** 物流计算方式 0-不累计1-按数量 **/
	private Long logisticsFeeType;

	/** 商品支付方式现金 **/
	private Long cashIdgoods;

	/** 商品支付方式商城币 **/
	private Long coinIdgoods;

	/** 商品支付方式积分 **/
	private Long scoreIdgoods;

	/** 商品支付方式话费，0不支持， 1支持 **/
	private Long billIdGoods;

	/** 组合支付方式：0 非组合支付方式 1 现金+商城币 2 现金+积分 **/
	private Long groupPayType;

	/** 现金支付比例 **/
	private Long cashPayRatio;

	/** 商城币或积分支付比例 **/
	private Long otherPayRatio;

	/** 支付方式: 0 单一支付类型 1 组合支付类型 **/
	private Long payType;

	/** 方正码平台验证短信附加内容 */
	private String verifyCodeDesc;

	@Column(name = "VERIFY_CODE_DESC")
	public String getVerifyCodeDesc() {
		return verifyCodeDesc;
	}

	public void setVerifyCodeDesc(String verifyCodeDesc) {
		this.verifyCodeDesc = verifyCodeDesc;
	}

	@Transient
	public Long getPayType() {
		return payType;
	}

	public void setPayType(Long payType) {
		this.payType = payType;
	}

	@Transient
	public Long getBillIdGoods() {
		return billIdGoods;
	}

	public void setBillIdGoods(Long billIdGoods) {
		this.billIdGoods = billIdGoods;
	}

	@Transient
	public Long getGroupPayType() {
		return groupPayType;
	}

	public void setGroupPayType(Long groupPayType) {
		this.groupPayType = groupPayType;
	}

	@Transient
	public Long getCashPayRatio() {
		return cashPayRatio;
	}

	public void setCashPayRatio(Long cashPayRatio) {
		this.cashPayRatio = cashPayRatio;
	}

	@Transient
	public Long getOtherPayRatio() {
		return otherPayRatio;
	}

	public void setOtherPayRatio(Long otherPayRatio) {
		this.otherPayRatio = otherPayRatio;
	}

	@Column(name = "LOGISTICS_FEE", precision = 9, scale = 2)
	public Double getLogisticsFee() {
		return logisticsFee;
	}

	public void setLogisticsFee(Double logisticsFee) {
		this.logisticsFee = logisticsFee;
	}

	@Column(name = "LOGISTICS_FEE_TYPE", precision = 1, scale = 0)
	public Long getLogisticsFeeType() {
		return logisticsFeeType;
	}

	public void setLogisticsFeeType(Long logisticsFeeType) {
		this.logisticsFeeType = logisticsFeeType;
	}

	@Column(name = "ISECKILL_PRICE")
	public Double getIseckillPrice() {
		return iseckillPrice;
	}

	public void setIseckillPrice(Double iseckillPrice) {
		this.iseckillPrice = iseckillPrice;
	}

	@Column(name = "SCORE_IDGOODS")
	public Long getScoreIdgoods() {
		return scoreIdgoods;
	}

	public void setScoreIdgoods(Long scoreIdgoods) {
		this.scoreIdgoods = scoreIdgoods;
	}

	@Column(name = "COIN_IDGOODS")
	public Long getCoinIdgoods() {
		return coinIdgoods;
	}

	public void setCoinIdgoods(Long coinIdgoods) {
		this.coinIdgoods = coinIdgoods;
	}

	@Column(name = "CASH_IDGOODS")
	public Long getCashIdgoods() {
		return cashIdgoods;
	}

	public void setCashIdgoods(Long cashIdgoods) {
		this.cashIdgoods = cashIdgoods;
	}

	public static Map<Long, String> cashIdgoodsMap = null;
	static {
		cashIdgoodsMap = new HashMap<Long, String>();
		cashIdgoodsMap.put(0L, "不支持");
		cashIdgoodsMap.put(1L, "现金");
	}

	public static Map<Long, String> coinIdgoodsMap = null;
	static {
		coinIdgoodsMap = new HashMap<Long, String>();
		coinIdgoodsMap.put(0L, "不支持");
		coinIdgoodsMap.put(1L, "商城币");
	}

	public static Map<Long, String> scoreIdgoodsMap = null;
	static {
		scoreIdgoodsMap = new HashMap<Long, String>();
		scoreIdgoodsMap.put(0L, "不支持");
		scoreIdgoodsMap.put(1L, "积分");
	}

	@Transient
	public String getScoreIdgoodsName() {
		return scoreIdgoodsMap.get(this.getScoreIdgoods());
	}

	@Transient
	public String getCashIdgoodsName() {
		return cashIdgoodsMap.get(this.getCashIdgoods());
	}

	@Transient
	public String getCoinIdgoodsName() {
		return coinIdgoodsMap.get(this.getCoinIdgoods());
	}

	/** 更新时间 */
	private String createTime;

	/** 创建人 */
	private Long createUserId;

	/** 更新人 */
	private Long updateUserId;

	/** 营销语 */
	private String marketContent;

	/** 商城价 */
	private Double shopPrice;

	/** 封面图路径 */
	private String imgPath;

	/** 结算价 */
	private Double settlePrice;

	/** 验证天数 **/
	private Long verifyDay;

	/** 商品是否显示 0，不现实；1显示 **/
	private Long isView;

	private String isViewName;

	// 上架时间
	private String groundingTime;

	// 审核时间
	private String auditTime;

	@Column(name = "IS_VIEW", precision = 1, scale = 0)
	public Long getIsView() {
		return isView;
	}

	public void setIsView(Long isView) {
		this.isView = isView;
	}

	@Column(name = "MARKET_PRICE", precision = 9, scale = 2)
	public Float getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(Float marketPrice) {
		this.marketPrice = marketPrice;
	}

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

	@Column(name = "ORG_ID", precision = 8, scale = 0)
	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	@Column(name = "SALE_PRICE_ID", precision = 8, scale = 0)
	public Long getSalePriceId() {
		return salePriceId;
	}

	public void setSalePriceId(Long salePriceId) {
		this.salePriceId = salePriceId;
	}

	@Column(name = "VERIFY_CODE_TYPE", precision = 1, scale = 0)
	public Long getVerifyCodeType() {
		return verifyCodeType;
	}

	public void setVerifyCodeType(Long verifyCodeType) {
		this.verifyCodeType = verifyCodeType;
	}

	@Column(name = "SEND_CODE_MODE", precision = 1, scale = 0)
	public Long getSendCodeMode() {
		return sendCodeMode;
	}

	public void setSendCodeMode(Long sendCodeMode) {
		this.sendCodeMode = sendCodeMode;
	}

	@Column(name = "SEND_CODE_CHANNEL", precision = 1, scale = 0)
	public Long getSendCodeChannel() {
		return sendCodeChannel;
	}

	public void setSendCodeChannel(Long sendCodeChannel) {
		this.sendCodeChannel = sendCodeChannel;
	}

	@Column(name = "SEND_CODE_SRC", precision = 1, scale = 0)
	public Long getSendCodeSrc() {
		return sendCodeSrc;
	}

	public void setSendCodeSrc(Long sendCodeSrc) {
		this.sendCodeSrc = sendCodeSrc;
	}

	@Column(name = "POST_FLAG", precision = 1, scale = 0)
	public Long getPostFlag() {
		return postFlag;
	}

	public void setPostFlag(Long postFlag) {
		this.postFlag = postFlag;
	}

	@Column(name = "SALE_START_TIME", length = 14)
	public String getSaleStartTime() {
		return saleStartTime;
	}

	public void setSaleStartTime(String saleStartTime) {
		this.saleStartTime = saleStartTime;
	}

	@Column(name = "SALE_STOP_TIME", length = 14)
	public String getSaleStopTime() {
		return saleStopTime;
	}

	public void setSaleStopTime(String saleStopTime) {
		this.saleStopTime = saleStopTime;
	}

	@Column(name = "VERIFY_START_TIME", length = 14)
	public String getVerifyStartTime() {
		return verifyStartTime;
	}

	public void setVerifyStartTime(String verifyStartTime) {
		this.verifyStartTime = verifyStartTime;
	}

	@Column(name = "VERIFY_STOP_TIME", length = 14)
	public String getVerifyStopTime() {
		return verifyStopTime;
	}

	public void setVerifyStopTime(String verifyStopTime) {
		this.verifyStopTime = verifyStopTime;
	}

	@Column(name = "STOCK_NUM", precision = 8, scale = 0)
	public Long getStockNum() {
		return stockNum;
	}

	public void setStockNum(Long stockNum) {
		this.stockNum = stockNum;
	}

	@Column(name = "USER_PER_BUY_NUM", precision = 8, scale = 0)
	public Long getUserPerBuyNum() {
		return userPerBuyNum;
	}

	public void setUserPerBuyNum(Long userPerBuyNum) {
		this.userPerBuyNum = userPerBuyNum;
	}

	@Column(name = "STATUS", precision = 1, scale = 0)
	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	@Column(name = "IS_VALID", precision = 1, scale = 0)
	public Long getIsValid() {
		return isValid;
	}

	public void setIsValid(Long isValid) {
		this.isValid = isValid;
	}

	@Column(name = "SYNC_GY_FLAG", precision = 1, scale = 0)
	public Long getSyncGyFlag() {
		return syncGyFlag;
	}

	public void setSyncGyFlag(Long syncGyFlag) {
		this.syncGyFlag = syncGyFlag;
	}

	@Column(name = "SHOP_CLASS", precision = 1, scale = 0)
	public Long getShopClass() {
		return shopClass;
	}

	public void setShopClass(Long shopClass) {
		this.shopClass = shopClass;
	}

	@Column(name = "STORE_ID", precision = 1, scale = 0)
	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	@Column(name = "ITEM_MODE", precision = 1, scale = 0)
	public Long getItemMode() {
		return itemMode;
	}

	public void setItemMode(Long itemMode) {
		this.itemMode = itemMode;
	}

	@Column(name = "TYPE_ID", precision = 8, scale = 0)
	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	@Column(name = "GROUP_FLAG", precision = 1, scale = 0)
	public Long getGroupFlag() {
		return groupFlag;
	}

	public void setGroupFlag(Long groupFlag) {
		this.groupFlag = groupFlag;
	}

	@Column(name = "VIRTUAL_FLAG", precision = 1, scale = 0)
	public Long getVirtualFlag() {
		return virtualFlag;
	}

	public void setVirtualFlag(Long virtualFlag) {
		this.virtualFlag = virtualFlag;
	}

	@Column(name = "VIRTUAL_TYPE", precision = 1, scale = 0)
	public Long getVirtualType() {
		return virtualType;
	}

	public void setVirtualType(Long virtualType) {
		this.virtualType = virtualType;
	}

	@Column(name = "NAME", length = 100)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "SHORT_NAME", length = 100)
	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	@Column(name = "WARM_PROMPT", length = 200)
	public String getWarmPrompt() {
		return warmPrompt;
	}

	public void setWarmPrompt(String warmPrompt) {
		this.warmPrompt = warmPrompt;
	}

	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "SHOP_USER_ID", precision = 8, scale = 0)
	public Long getShopUserId() {
		return shopUserId;
	}

	public void setShopUserId(Long shopUserId) {
		this.shopUserId = shopUserId;
	}

	@Column(name = "UPDATE_TIME", length = 14)
	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "BRAND", length = 20)
	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	@Column(name = "WEIGHT", precision = 8, scale = 2)
	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	@Column(name = "CREATE_TIME", length = 14)
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Column(name = "CREATE_USER_ID", precision = 9, scale = 0)
	public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	@Column(name = "UPDATE_USER_ID", precision = 9, scale = 0)
	public Long getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(Long updateUserId) {
		this.updateUserId = updateUserId;
	}

	@Column(name = "MARKET_CONTENT", length = 200)
	public String getMarketContent() {
		return marketContent;
	}

	public void setMarketContent(String marketContent) {
		this.marketContent = marketContent;
	}

	@Column(name = "SHOP_PRICE", precision = 9, scale = 2)
	public Double getShopPrice() {
		return shopPrice;
	}

	public void setShopPrice(Double shopPrice) {
		this.shopPrice = shopPrice;
	}

	@Column(name = "IMG_PATH", length = 200)
	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	@Column(name = "VERIFY_DAY", precision = 5, scale = 0)
	public Long getVerifyDay() {
		return verifyDay;
	}

	public void setVerifyDay(Long verifyDay) {
		this.verifyDay = verifyDay;
	}

	@Column(name = "SETTLE_PRICE", precision = 9, scale = 2)
	public Double getSettlePrice() {
		return settlePrice;
	}

	public void setSettlePrice(Double settlePrice) {
		this.settlePrice = settlePrice;
	}

	@Column(name = "GROUNDING_TIME", length = 14)
	public String getGroundingTime() {
		return groundingTime;
	}

	public void setGroundingTime(String groundingTime) {
		this.groundingTime = groundingTime;
	}

	@Column(name = "AUDIT_TIME", length = 14)
	public String getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(String auditTime) {
		this.auditTime = auditTime;
	}

	/**
	 * 配送区域
	 **/
	private String postArea;

	/**
	 * 配送区域编码
	 **/
	private String postAreaCode;

	/**
	 * 物流渠道
	 **/
	private String qdName;

	/**
	 * 销售门店
	 **/
	private String saleShopName;

	/**
	 * 销售门店id
	 **/
	private String saleShopIds;

	/**
	 * 购买限制
	 **/
	private String buyLimit;

	/**
	 * 会员限制
	 **/
	private String userLimitName;

	private String userLimitCode;

	/**
	 * 销售地市
	 **/
	private String saleAreaName;

	private String saleAreaCode;

	/**
	 * 验证门店
	 **/
	private String verifyShopName;

	/**
	 * 验证门店id
	 **/
	private String verifyShopIds;

	/** 商户名称 **/
	private String storeName;

	/** 门店名称 **/
	private String shopName;

	/** 商品名称 **/
	private String itemName;

	/**
	 * 购买用户地市限制
	 **/
	private String areaLimitName;

	private String areaLimitCode;

	private String priceTypeCode;

	private String price;

	private String typeName;

	private String tag;

	private String itemIds;

	private Long feeType;

	private SysFee sysFee;

	// 货架分类名称
	private String goodTypeName;

	// 货架分类id
	private String goodTypeId;

	// 套餐商品过滤库存
	private Long stockNumFilter;
	//是否支持货到付款
	private Long deliveryPay;


	@Column(name = "FEE_TYPE")
	public Long getFeeType() {
		return feeType;
	}

	public void setFeeType(Long feeType) {
		this.feeType = feeType;
	}


	@Transient
	public String getPostArea() {
		return postArea;
	}

	@Transient
	public void setPostArea(String postArea) {
		this.postArea = postArea;
	}

	@Transient
	public String getPostAreaCode() {
		return postAreaCode;
	}

	@Transient
	public void setPostAreaCode(String postAreaCode) {
		this.postAreaCode = postAreaCode;
	}

	@Transient
	public String getSaleShopName() {
		return saleShopName;
	}

	@Transient
	public void setSaleShopName(String saleShopName) {
		this.saleShopName = saleShopName;
	}

	@Transient
	public String getSaleShopIds() {
		return saleShopIds;
	}

	@Transient
	public void setSaleShopIds(String saleShopIds) {
		this.saleShopIds = saleShopIds;
	}

	@Transient
	public String getQdName() {
		return qdName;
	}

	@Transient
	public void setQdName(String qdName) {
		this.qdName = qdName;
	}

	@Transient
	public String getBuyLimit() {
		return buyLimit;
	}

	@Transient
	public void setBuyLimit(String buyLimit) {
		this.buyLimit = buyLimit;
	}

	@Transient
	public String getUserLimitName() {
		return userLimitName;
	}

	@Transient
	public void setUserLimitName(String userLimitName) {
		this.userLimitName = userLimitName;
	}

	@Transient
	public String getUserLimitCode() {
		return userLimitCode;
	}

	@Transient
	public void setUserLimitCode(String userLimitCode) {
		this.userLimitCode = userLimitCode;
	}

	@Transient
	public String getAreaLimitName() {
		return areaLimitName;
	}

	@Transient
	public void setAreaLimitName(String areaLimitName) {
		this.areaLimitName = areaLimitName;
	}

	@Transient
	public String getAreaLimitCode() {
		return areaLimitCode;
	}

	@Transient
	public void setAreaLimitCode(String areaLimitCode) {
		this.areaLimitCode = areaLimitCode;
	}

	@Transient
	public String getVerifyShopName() {
		return verifyShopName;
	}

	@Transient
	public void setVerifyShopName(String verifyShopName) {
		this.verifyShopName = verifyShopName;
	}

	@Transient
	public String getVerifyShopIds() {
		return verifyShopIds;
	}

	@Transient
	public void setVerifyShopIds(String verifyShopIds) {
		this.verifyShopIds = verifyShopIds;
	}

	@Transient
	public String getStoreName() {
		return storeName;
	}

	@Transient
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	@Transient
	public String getShopName() {
		return shopName;
	}

	@Transient
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	@Transient
	public String getItemName() {
		return itemName;
	}

	@Transient
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	@Transient
	public String getSaleAreaName() {
		return saleAreaName;
	}

	@Transient
	public void setSaleAreaName(String saleAreaName) {
		this.saleAreaName = saleAreaName;
	}

	@Transient
	public String getSaleAreaCode() {
		return saleAreaCode;
	}

	@Transient
	public void setSaleAreaCode(String saleAreaCode) {
		this.saleAreaCode = saleAreaCode;
	}

	@Transient
	public String getPriceTypeCode() {
		return priceTypeCode;
	}

	@Transient
	public void setPriceTypeCode(String priceTypeCode) {

		this.priceTypeCode = priceTypeCode;
	}

	@Transient
	public String getPrice() {

		return price;
	}

	@Transient
	public void setPrice(String price) {
		this.price = price;
	}

	@Transient
	public String getTypeName() {
		return typeName;
	}

	@Transient
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@Transient
	public String getTag() {
		return tag;
	}

	@Transient
	public void setTag(String tag) {
		this.tag = tag;
	}

	@Transient
	public String getItemIds() {
		return itemIds;
	}

	@Transient
	public void setItemIds(String itemIds) {
		this.itemIds = itemIds;
	}

	public static Map<Long, String> itemModeMap = null;
	static {
		itemModeMap = new HashMap<Long, String>();
		itemModeMap.put(0L, "实物");
		itemModeMap.put(1L, "虚拟物");
	}

	@Transient
	public String getItemModeName() {
		return itemModeMap.get(this.itemMode);
	}

	public static Map<Long, String> groupFlagMap = null;
	static {
		groupFlagMap = new HashMap<Long, String>();
		groupFlagMap.put(0L, "普通商品");
		groupFlagMap.put(1L, "优惠套餐");
	}

	public static Map<Long, String> isViewMap = null;
	static {
		isViewMap = new HashMap<Long, String>();
		isViewMap.put(0L, "否");
		isViewMap.put(1L, "是");
	}

	@Column(name = "ISECKILL")
	public Long getIseckill() {
		return iseckill;
	}

	public void setIseckill(Long iseckill) {
		this.iseckill = iseckill;
	}

	@Transient
	public String getGroupFlagName() {
		return groupFlagMap.get(this.groupFlag);
	}

	@Transient
	public String getIsViewName() {
		return isViewMap.get(this.getIsView());
	}

	public void setIsViewName(String isViewName) {
		this.isViewName = isViewName;
	}

	@Transient
	public String getGoodTypeName() {
		return goodTypeName;
	}

	@Transient
	public void setGoodTypeName(String goodTypeName) {
		this.goodTypeName = goodTypeName;
	}

	@Transient
	public String getGoodTypeId() {
		return goodTypeId;
	}

	@Transient
	public void setGoodTypeId(String goodTypeId) {
		this.goodTypeId = goodTypeId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "FEE_TYPE", insertable = false, updatable = false)
	public SysFee getSysFee() {
		return sysFee;
	}

	public void setSysFee(SysFee sysFee) {
		this.sysFee = sysFee;
	}

	@Transient
	public Long getStockNumFilter() {
		return stockNumFilter;
	}

	@Transient
	public void setStockNumFilter(Long stockNumFilter) {
		this.stockNumFilter = stockNumFilter;
	}
	@Transient
	public Long getDeliveryPay() {
		return deliveryPay;
	}

	public void setDeliveryPay(Long deliveryPay) {
		this.deliveryPay = deliveryPay;
	}
	@Transient
	public Long getItemSource() {
		return itemSource;
	}

	public void setItemSource(Long itemSource) {
		this.itemSource = itemSource;
	}
	

}
