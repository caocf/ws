package com.cplatform.b2c.welfare.entity;


/**
 * Title. T_item_sale<br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-11-4 下午04:01:25
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: guolt@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
public class WelFareModel {

	private Long id;

	/**
	 * 查看T_STORE_CODE_CONFIG的SEND_CHANNEL
	 */
	private Integer sendCodeChannel;

	/** 0-不需要物流配送 1-需要物流配送 */
	private Integer postFlag;

	/**
	 * 销售有效开始时间
	 */
	private String saleStartTime;

	/**
	 * 销售有效结束时间
	 */
	private String saleStopTime;

	/**
	 * 商品库存数量
	 */
	private Long stockNum;

	/**
	 * 0不限制 单个用户购买数量
	 */
	private Integer userPerBuyNum;

	/**
	 * -1已删除0-草稿 1-待审核 2-审核中 3-审核通过 4-审核驳回
	 */
	private Integer status;

	/**
	 * 0-下架 1-上架
	 */
	private Integer isValid;

 

	/**
	 * "1--业务门店2--商户3--渠道商"
	 */
	private Integer shopClass;

	/**
	 * 匹配表t_store 商户id
	 */
	private Integer storeId;

	/**
	 * 市场价
	 */
	private Double marketPrice;


	/**
	 * 商品分类
	 */
	private Integer typeId;

	/**
	 * "是否是优惠套餐，如果是则是N多商品的组合 0-普通商品 1-优惠套餐"
	 */
	private Integer groupFlag;

	/**
	 * 商品名称
	 */
	private String name;

	/**
	 * 商品简称
	 */
	private String shortName;

	/**
	 * 温馨提示
	 */
	private String warmPrompt;

	/**
	 * 商品介绍
	 */
	private String remark;

	/**
	 * 商户用户id
	 */
	private Integer shopUserId;

	/**
	 * 更新时间
	 */
	private String updateTime;

	/**
	 * 品牌
	 */
	private String brand;

	/**
	 * 重量
	 */
	private Double weight;

	/**
	 * 创建时间
	 */
	private String createTime;

	/**
	 * 营销语
	 */
	private String marketContent;

	/**
	 * 商城价
	 */
	private Double shopPrice;

	/**
	 * 封面图路径
	 */
	private String imgPath;

	/**
	 * 对应t_sys_fee表id
	 */
	private Integer feeType;

	/**
	 * 商品是否显示 0，不显示；1显示
	 */
	private Integer isView;

	/**
	 * -1，劳保商品。营销商品类型： 0普通商品 ，1秒杀商品，2礼品卡兑换商品，3促销商品 4礼品卡
	 */
	private Integer iseckill;

	/**
	 * 商品支付方式为现金 ，0不支持， 1支持。
	 */
	private Integer cashIdGoods;

	/**
	 * 商品支付方式为商城币 ，0不支持， 1支持。
	 */
	private Integer coinIdGoods;

	/**
	 * 商品支付方式为积分，0不支持， 1支持。
	 */
	private Integer scoreIdGoods;

	/**
	 * 物流运费
	 */
	private Double logisticsFee;

	/**
	 * 物流计算方式 0-不累计1-按数量
	 */
	private Integer logisticsFeeType;

 /**
  * 附加条件
  * 
  * 
  */
	/**
	 * stockId
	 */
	private Long stockId;
/**
 * 尺码
 */
	private String itemSize;
	/**
	 * 颜色
	 */
	private String itemColor;
	/**
	 * 类型 0 男鞋 1 女 2 儿童
	 */
	private Integer itemType;
	/**
	 * 其他
	 */
	private String itemOther;

	/**
	 * 扩展表里的库存
	 */
	private Integer stockNumber;
	/**
	 * 排序类型
	 */
	
	private Integer orderType;
	
	
	
    /**
     * 获取 id
     * @return id
     */
    public Long getId() {
    	return id;
    }

	
    /**
     * 设置 id
     * @param id 
     */
    public void setId(Long id) {
    	this.id = id;
    }

	/**
	 * 获取 sendCodeChannel
	 * 
	 * @return sendCodeChannel
	 */
	public Integer getSendCodeChannel() {
		return sendCodeChannel;
	}

	/**
	 * 设置 sendCodeChannel
	 * 
	 * @param sendCodeChannel
	 */
	public void setSendCodeChannel(Integer sendCodeChannel) {
		this.sendCodeChannel = sendCodeChannel;
	}

	/**
	 * 获取 postFlag
	 * 
	 * @return postFlag
	 */
	public Integer getPostFlag() {
		return postFlag;
	}

	/**
	 * 设置 postFlag
	 * 
	 * @param postFlag
	 */
	public void setPostFlag(Integer postFlag) {
		this.postFlag = postFlag;
	}

	/**
	 * 获取 saleStartTime
	 * 
	 * @return saleStartTime
	 */
	public String getSaleStartTime() {
		return saleStartTime;
	}

	/**
	 * 设置 saleStartTime
	 * 
	 * @param saleStartTime
	 */
	public void setSaleStartTime(String saleStartTime) {
		this.saleStartTime = saleStartTime;
	}

	/**
	 * 获取 saleStopTime
	 * 
	 * @return saleStopTime
	 */
	public String getSaleStopTime() {
		return saleStopTime;
	}

	/**
	 * 设置 saleStopTime
	 * 
	 * @param saleStopTime
	 */
	public void setSaleStopTime(String saleStopTime) {
		this.saleStopTime = saleStopTime;
	}

	  
	
    /**
     * 获取 stockNum
     * @return stockNum
     */
    public Long getStockNum() {
    	return stockNum;
    }

	
    /**
     * 设置 stockNum
     * @param stockNum 
     */
    public void setStockNum(Long stockNum) {
    	this.stockNum = stockNum;
    }

	/**
	 * 获取 userPerBuyNum
	 * 
	 * @return userPerBuyNum
	 */
	public Integer getUserPerBuyNum() {
		return userPerBuyNum;
	}

	/**
	 * 设置 userPerBuyNum
	 * 
	 * @param userPerBuyNum
	 */
	public void setUserPerBuyNum(Integer userPerBuyNum) {
		this.userPerBuyNum = userPerBuyNum;
	}

	/**
	 * 获取 status
	 * 
	 * @return status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * 设置 status
	 * 
	 * @param status
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * 获取 isValid
	 * 
	 * @return isValid
	 */
	public Integer getIsValid() {
		return isValid;
	}

	/**
	 * 设置 isValid
	 * 
	 * @param isValid
	 */
	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}
 
	/**
	 * 获取 shopClass
	 * 
	 * @return shopClass
	 */
	public Integer getShopClass() {
		return shopClass;
	}

	/**
	 * 设置 shopClass
	 * 
	 * @param shopClass
	 */
	public void setShopClass(Integer shopClass) {
		this.shopClass = shopClass;
	}

	/**
	 * 获取 storeId
	 * 
	 * @return storeId
	 */
	public Integer getStoreId() {
		return storeId;
	}

	/**
	 * 设置 storeId
	 * 
	 * @param storeId
	 */
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
 

	
    /**
     * 获取 marketPrice
     * @return marketPrice
     */
    public Double getMarketPrice() {
    	return marketPrice;
    }

	
    /**
     * 设置 marketPrice
     * @param marketPrice 
     */
    public void setMarketPrice(Double marketPrice) {
    	this.marketPrice = marketPrice;
    }

	/**
	 * 获取 typeId
	 * 
	 * @return typeId
	 */
	public Integer getTypeId() {
		return typeId;
	}

	/**
	 * 设置 typeId
	 * 
	 * @param typeId
	 */
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	/**
	 * 获取 groupFlag
	 * 
	 * @return groupFlag
	 */
	public Integer getGroupFlag() {
		return groupFlag;
	}

	/**
	 * 设置 groupFlag
	 * 
	 * @param groupFlag
	 */
	public void setGroupFlag(Integer groupFlag) {
		this.groupFlag = groupFlag;
	}

	/**
	 * 获取 name
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置 name
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取 shoptName
	 * 
	 * @return shoptName
	 */
	public String getShortName() {
		return shortName;
	}

	/**
	 * 设置 shoptName
	 * 
	 * @param shoptName
	 */
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	/**
	 * 获取 warmPrompt
	 * 
	 * @return warmPrompt
	 */
	public String getWarmPrompt() {
		return warmPrompt;
	}

	/**
	 * 设置 warmPrompt
	 * 
	 * @param warmPrompt
	 */
	public void setWarmPrompt(String warmPrompt) {
		this.warmPrompt = warmPrompt;
	}

	/**
	 * 获取 remark
	 * 
	 * @return remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 设置 remark
	 * 
	 * @param remark
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 获取 shopUserId
	 * 
	 * @return shopUserId
	 */
	public Integer getShopUserId() {
		return shopUserId;
	}

	/**
	 * 设置 shopUserId
	 * 
	 * @param shopUserId
	 */
	public void setShopUserId(Integer shopUserId) {
		this.shopUserId = shopUserId;
	}

	/**
	 * 获取 updateTime
	 * 
	 * @return updateTime
	 */
	public String getUpdateTime() {
		return updateTime;
	}

	/**
	 * 设置 updateTime
	 * 
	 * @param updateTime
	 */
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
 

	
    /**
     * 获取 brand
     * @return brand
     */
    public String getBrand() {
    	return brand;
    }

	
    /**
     * 设置 brand
     * @param brand 
     */
    public void setBrand(String brand) {
    	this.brand = brand;
    }

	/**
	 * 获取 weight
	 * 
	 * @return weight
	 */
	public Double getWeight() {
		return weight;
	}

	/**
	 * 设置 weight
	 * 
	 * @param weight
	 */
	public void setWeight(Double weight) {
		this.weight = weight;
	}

	/**
	 * 获取 createTime
	 * 
	 * @return createTime
	 */
	public String getCreateTime() {
		return createTime;
	}

	/**
	 * 设置 createTime
	 * 
	 * @param createTime
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获取 marketContent
	 * 
	 * @return marketContent
	 */
	public String getMarketContent() {
		return marketContent;
	}

	/**
	 * 设置 marketContent
	 * 
	 * @param marketContent
	 */
	public void setMarketContent(String marketContent) {
		this.marketContent = marketContent;
	}

	/**
	 * 获取 shopPrice
	 * 
	 * @return shopPrice
	 */
	public Double getShopPrice() {
		return shopPrice;
	}

	/**
	 * 设置 shopPrice
	 * 
	 * @param shopPrice
	 */
	public void setShopPrice(Double shopPrice) {
		this.shopPrice = shopPrice;
	}

	/**
	 * 获取 imgPath
	 * 
	 * @return imgPath
	 */
	public String getImgPath() {
		return imgPath;
	}

	/**
	 * 设置 imgPath
	 * 
	 * @param imgPath
	 */
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	/**
	 * 获取 feeType
	 * 
	 * @return feeType
	 */
	public Integer getFeeType() {
		return feeType;
	}

	/**
	 * 设置 feeType
	 * 
	 * @param feeType
	 */
	public void setFeeType(Integer feeType) {
		this.feeType = feeType;
	}

	/**
	 * 获取 isView
	 * 
	 * @return isView
	 */
	public Integer getIsView() {
		return isView;
	}

	/**
	 * 设置 isView
	 * 
	 * @param isView
	 */
	public void setIsView(Integer isView) {
		this.isView = isView;
	}

	
	
    /**
     * 获取 iseckill
     * @return iseckill
     */
    public Integer getIseckill() {
    	return iseckill;
    }

	
    /**
     * 设置 iseckill
     * @param iseckill 
     */
    public void setIseckill(Integer iseckill) {
    	this.iseckill = iseckill;
    }

	/**
	 * 获取 cashIdGoods
	 * 
	 * @return cashIdGoods
	 */
	public Integer getCashIdGoods() {
		return cashIdGoods;
	}

	/**
	 * 设置 cashIdGoods
	 * 
	 * @param cashIdGoods
	 */
	public void setCashIdGoods(Integer cashIdGoods) {
		this.cashIdGoods = cashIdGoods;
	}

	/**
	 * 获取 coinIdGoods
	 * 
	 * @return coinIdGoods
	 */
	public Integer getCoinIdGoods() {
		return coinIdGoods;
	}

	/**
	 * 设置 coinIdGoods
	 * 
	 * @param coinIdGoods
	 */
	public void setCoinIdGoods(Integer coinIdGoods) {
		this.coinIdGoods = coinIdGoods;
	}

	/**
	 * 获取 scoreIdGoods
	 * 
	 * @return scoreIdGoods
	 */
	public Integer getScoreIdGoods() {
		return scoreIdGoods;
	}

	/**
	 * 设置 scoreIdGoods
	 * 
	 * @param scoreIdGoods
	 */
	public void setScoreIdGoods(Integer scoreIdGoods) {
		this.scoreIdGoods = scoreIdGoods;
	}

 
	/**
	 * 获取 logisticsFee
	 * 
	 * @return logisticsFee
	 */
	public Double getLogisticsFee() {
		return logisticsFee;
	}

	/**
	 * 设置 logisticsFee
	 * 
	 * @param logisticsFee
	 */
	public void setLogisticsFee(Double logisticsFee) {
		this.logisticsFee = logisticsFee;
	}

	/**
	 * 获取 logisticsFeeType
	 * 
	 * @return logisticsFeeType
	 */
	public Integer getLogisticsFeeType() {
		return logisticsFeeType;
	}

	/**
	 * 设置 logisticsFeeType
	 * 
	 * @param logisticsFeeType
	 */
	public void setLogisticsFeeType(Integer logisticsFeeType) {
		this.logisticsFeeType = logisticsFeeType;
	}

    
    /**
     * 获取 itemSize
     * @return itemSize
     */
    public String getItemSize() {
    	return itemSize;
    }

	
    /**
     * 设置 itemSize
     * @param itemSize 
     */
    public void setItemSize(String itemSize) {
    	this.itemSize = itemSize;
    }

	
    /**
     * 获取 itemColor
     * @return itemColor
     */
    public String getItemColor() {
    	return itemColor;
    }

	
    /**
     * 设置 itemColor
     * @param itemColor 
     */
    public void setItemColor(String itemColor) {
    	this.itemColor = itemColor;
    }

	
    /**
     * 获取 itemType
     * @return itemType
     */
    public Integer getItemType() {
    	return itemType;
    }

	
    /**
     * 设置 itemType
     * @param itemType 
     */
    public void setItemType(Integer itemType) {
    	this.itemType = itemType;
    }

	
    
    /**
     * 获取 itemOther
     * @return itemOther
     */
    public String getItemOther() {
    	return itemOther;
    }

	
    /**
     * 设置 itemOther
     * @param itemOther 
     */
    public void setItemOther(String itemOther) {
    	this.itemOther = itemOther;
    }

	/**
     * 获取 orderType
     * @return orderType
     */
    public Integer getOrderType() {
    	return orderType;
    }

	
    /**
     * 设置 orderType
     * @param orderType 
     */
    public void setOrderType(Integer orderType) {
    	this.orderType = orderType;
    }

	

	
    
    /**
     * 获取 stockId
     * @return stockId
     */
    public Long getStockId() {
    	return stockId;
    }


	
    /**
     * 设置 stockId
     * @param stockId 
     */
    public void setStockId(Long stockId) {
    	this.stockId = stockId;
    }


	/**
     * 获取 stockNumber
     * @return stockNumber
     */
    public Integer getStockNumber() {
    	return stockNumber;
    }

	
    /**
     * 设置 stockNumber
     * @param stockNumber 
     */
    public void setStockNumber(Integer stockNumber) {
    	this.stockNumber = stockNumber;
    }

	


}
