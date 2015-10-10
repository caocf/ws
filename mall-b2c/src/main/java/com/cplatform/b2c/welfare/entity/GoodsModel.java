package com.cplatform.b2c.welfare.entity;


/**
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-11-6 下午02:08:03
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: guolt@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
public class GoodsModel {
	private Integer COUNT;
	private Long GOOD_ID;
	private Integer DISCOUNT;
	
   
    
    /**
     * 获取 cOUNT
     * @return cOUNT
     */
    public Integer getCOUNT() {
    	return COUNT;
    }

	
    /**
     * 设置 cOUNT
     * @param cOUNT 
     */
    public void setCOUNT(Integer cOUNT) {
    	COUNT = cOUNT;
    }

	
    /**
     * 获取 gOOD_ID
     * @return gOOD_ID
     */
    public Long getGOOD_ID() {
    	return GOOD_ID;
    }

	
    /**
     * 设置 gOODID
     * @param gOODID 
     */
    public void setGOOD_ID(Long gOODID) {
    	GOOD_ID = gOODID;
    }

	/**
     * 获取 dISCOUNT
     * @return dISCOUNT
     */
    public Integer getDISCOUNT() {
    	return DISCOUNT;
    }
	
    /**
     * 设置 dISCOUNT
     * @param dISCOUNT 
     */
    public void setDISCOUNT(Integer dISCOUNT) {
    	DISCOUNT = dISCOUNT;
    }
	
 
	 
}
