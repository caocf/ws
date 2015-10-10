package com.cplatform.b2c.welfare.entity;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;

import org.codehaus.jackson.annotate.JsonProperty;



/**
 * Title. 协议json<br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-11-6 下午02:00:46
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: guolt@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
public class OrderCenterJsonModel {
 
	private Long U_ID;
	private List<GoodsModel> GOODS;
	private AddressInfoModel ADDRESS_INFO;
	private String USER_REMARK;
	private String SUBJECT;
	private Long CREATE_SOURCE;
	private String ORDER_TYPE;
	private String BUSINESS_ID;
	
   
	
	
    /**
     * 获取 u_ID
     * @return u_ID
     */
    public Long getU_ID() {
    	return U_ID;
    }

	
    /**
     * 设置 uID
     * @param uID 
     */
    public void setU_ID(Long UID) {
    	U_ID = UID;
    }

	/**
     * 获取 gOODS
     * @return gOODS
     */
    public List<GoodsModel> getGOODS() {
    	return GOODS;
    }
	
    /**
     * 设置 gOODS
     * @param gOODS 
     */
    public void setGOODS(List<GoodsModel> gOODS) {
    	GOODS = gOODS;
    }
	
    /**
     * 获取 aDDRESS_INFO
     * @return aDDRESS_INFO
     */
    public AddressInfoModel getADDRESS_INFO() {
    	return ADDRESS_INFO;
    }
	
    /**
     * 设置 aDDRESSINFO
     * @param aDDRESSINFO 
     */
    public void setADDRESS_INFO(AddressInfoModel aDDRESSINFO) {
    	ADDRESS_INFO = aDDRESSINFO;
    }
	
    /**
     * 获取 uSER_REMARK
     * @return uSER_REMARK
     */
    public String getUSER_REMARK() {
    	return USER_REMARK;
    }
	
    /**
     * 设置 uSERREMARK
     * @param uSERREMARK 
     */
    public void setUSER_REMARK(String uSERREMARK) {
    	USER_REMARK = uSERREMARK;
    }
	
    /**
     * 获取 sUBJECT
     * @return sUBJECT
     */
    public String getSUBJECT() {
    	return SUBJECT;
    }
	
    /**
     * 设置 sUBJECT
     * @param sUBJECT 
     */
    public void setSUBJECT(String sUBJECT) {
    	SUBJECT = sUBJECT;
    }
	
    /**
     * 获取 cREATE_SOURCE
     * @return cREATE_SOURCE
     */
    public Long getCREATE_SOURCE() {
    	return CREATE_SOURCE;
    }
	
    /**
     * 设置 cREATESOURCE
     * @param cREATESOURCE 
     */
    public void setCREATE_SOURCE(Long cREATESOURCE) {
    	CREATE_SOURCE = cREATESOURCE;
    }
	
    /**
     * 获取 oRDER_TYPE
     * @return oRDER_TYPE
     */
    public String getORDER_TYPE() {
    	return ORDER_TYPE;
    }
	
    /**
     * 设置 oRDERTYPE
     * @param oRDERTYPE 
     */
    public void setORDER_TYPE(String oRDERTYPE) {
    	ORDER_TYPE = oRDERTYPE;
    }
	
    /**
     * 获取 bUSINESS_ID
     * @return bUSINESS_ID
     */
    public String getBUSINESS_ID() {
    	return BUSINESS_ID;
    }
	
    /**
     * 设置 bUSINESSID
     * @param bUSINESSID 
     */
    public void setBUSINESS_ID(String bUSINESSID) {
    	BUSINESS_ID = bUSINESSID;
    }
	
	
}
