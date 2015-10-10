package com.cplatform.b2c.welfare.entity;


/**
 * Title. T_ITEM_PARAM_STOCK<br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-11-5 上午10:05:41
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: guolt@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
public class WelFareStock {
	private Integer id;
	/**
	 * t_item_sale.id
	 */
	private Integer itemId;
	/**
	 * 颜色
	 */
	private String itemColor;
	/**
	 * 尺码
	 */
	private String itemSize;
	/**
	 * 库存
	 */
	private Integer stockNum;
	/**
	 * 类型(0 男鞋 1女鞋 2 童鞋)
	 */
	private Integer itemType;
	/**
	 * 备注
	 */
	private String remark;
	
    /**
     * 获取 id
     * @return id
     */
    public Integer getId() {
    	return id;
    }
	
    /**
     * 设置 id
     * @param id 
     */
    public void setId(Integer id) {
    	this.id = id;
    }
	
    /**
     * 获取 itemId
     * @return itemId
     */
    public Integer getItemId() {
    	return itemId;
    }
	
    /**
     * 设置 itemId
     * @param itemId 
     */
    public void setItemId(Integer itemId) {
    	this.itemId = itemId;
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
     * 获取 stockNum
     * @return stockNum
     */
    public Integer getStockNum() {
    	return stockNum;
    }
	
    /**
     * 设置 stockNum
     * @param stockNum 
     */
    public void setStockNum(Integer stockNum) {
    	this.stockNum = stockNum;
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
     * 获取 remark
     * @return remark
     */
    public String getRemark() {
    	return remark;
    }
	
    /**
     * 设置 remark
     * @param remark 
     */
    public void setRemark(String remark) {
    	this.remark = remark;
    }
	
   
	
	
	
}
