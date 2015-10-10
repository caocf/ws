package com.cplatform.b2c.welfare.entity;


/**
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-11-6 下午02:07:57
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: guolt@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
public class AddressInfoModel {
	private String MOBILE;
	private String PHONE;
	private String RECEIVER_NAME;
	private String ADDRESS;
	private String ZIPCODE;
	private String REMARK;
	
    /**
     * 获取 mOBILE
     * @return mOBILE
     */
    public String getMOBILE() {
    	return MOBILE;
    }
	
    /**
     * 设置 mOBILE
     * @param mOBILE 
     */
    public void setMOBILE(String mOBILE) {
    	MOBILE = mOBILE;
    }
	
    /**
     * 获取 pHONE
     * @return pHONE
     */
    public String getPHONE() {
    	return PHONE;
    }
	
    /**
     * 设置 pHONE
     * @param pHONE 
     */
    public void setPHONE(String pHONE) {
    	PHONE = pHONE;
    }
	
    /**
     * 获取 rECEIVER_NAME
     * @return rECEIVER_NAME
     */
    public String getRECEIVER_NAME() {
    	return RECEIVER_NAME;
    }
	
    /**
     * 设置 rECEIVERNAME
     * @param rECEIVERNAME 
     */
    public void setRECEIVER_NAME(String rECEIVERNAME) {
    	RECEIVER_NAME = rECEIVERNAME;
    }
	
    /**
     * 获取 aDDRESS
     * @return aDDRESS
     */
    public String getADDRESS() {
    	return ADDRESS;
    }
	
    /**
     * 设置 aDDRESS
     * @param aDDRESS 
     */
    public void setADDRESS(String aDDRESS) {
    	ADDRESS = aDDRESS;
    }
	
    /**
     * 获取 zIPCODE
     * @return zIPCODE
     */
    public String getZIPCODE() {
    	return ZIPCODE;
    }
	
    /**
     * 设置 zIPCODE
     * @param zIPCODE 
     */
    public void setZIPCODE(String zIPCODE) {
    	ZIPCODE = zIPCODE;
    }
	
    /**
     * 获取 rEMARK
     * @return rEMARK
     */
    public String getREMARK() {
    	return REMARK;
    }
	
    /**
     * 设置 rEMARK
     * @param rEMARK 
     */
    public void setREMARK(String rEMARK) {
    	REMARK = rEMARK;
    }

	/**
     * @param mOBILE
     * @param pHONE
     * @param rECEIVERNAME
     * @param aDDRESS
     * @param zIPCODE
     * @param rEMARK
     */
    public AddressInfoModel(String mOBILE, String pHONE, String rECEIVERNAME, String aDDRESS, String zIPCODE, String rEMARK) {
	    super();
	    MOBILE = mOBILE;
	    PHONE = pHONE;
	    RECEIVER_NAME = rECEIVERNAME;
	    ADDRESS = aDDRESS;
	    ZIPCODE = zIPCODE;
	    REMARK = rEMARK;
    }

	/**
     * 
     */
    public AddressInfoModel() {
	    super();
    }
	
}
