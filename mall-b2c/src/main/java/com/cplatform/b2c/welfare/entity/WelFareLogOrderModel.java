package com.cplatform.b2c.welfare.entity;


/**
 * Title. 流水日志 <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-11-6 下午01:57:06
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: guolt@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
public class WelFareLogOrderModel {

	private long id;
	
	private Long stockId;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 操作类型(0：扣减库存；1：释放库存)
	 */
	private Integer type;
	/**
	 * 库存量
	 */
	private Integer quantity;
	

    
    /**
     * 获取 id
     * @return id
     */
    public long getId() {
    	return id;
    }

	
    /**
     * 设置 id
     * @param id 
     */
    public void setId(long id) {
    	this.id = id;
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
	
    /**
     * 获取 type
     * @return type
     */
    public Integer getType() {
    	return type;
    }
	
    /**
     * 设置 type
     * @param type 
     */
    public void setType(Integer type) {
    	this.type = type;
    }
	
    /**
     * 获取 quantity
     * @return quantity
     */
    public Integer getQuantity() {
    	return quantity;
    }
	
    /**
     * 设置 quantity
     * @param quantity 
     */
    public void setQuantity(Integer quantity) {
    	this.quantity = quantity;
    }
	
}
