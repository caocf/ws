package com.cplatform.b2c.model;

// "AMOUNT":4.0,"COUNT":2,"DATE":"20130802095707","GOOD_ID":"D3",
// "GOOD_NAME":"福彩3D","ISSUE_NO":"2013207","ORDER_ID":"13754086274568282","PRICE":2.0,"STATUS":"3"},
public class LotteryBean {

	private double AMOUNT;

	private int COUNT;

	private String DATE;

	private String GOOD_ID;

	private String GOOD_NAME;

	private String ISSUE_NO;

	private String ORDER_ID;

	private double PRICE;

	private String STATUS;

	
    public double getAMOUNT() {
    	return AMOUNT;
    }

	
    public void setAMOUNT(double aMOUNT) {
    	AMOUNT = aMOUNT;
    }

	
    public int getCOUNT() {
    	return COUNT;
    }

	
    public void setCOUNT(int cOUNT) {
    	COUNT = cOUNT;
    }

	
    public String getDATE() {
    	return DATE;
    }

	
    public void setDATE(String dATE) {
    	DATE = dATE;
    }

	
    public String getGOOD_ID() {
    	return GOOD_ID;
    }

	
    public void setGOOD_ID(String gOODID) {
    	GOOD_ID = gOODID;
    }

	
    public String getGOOD_NAME() {
    	return GOOD_NAME;
    }

	
    public void setGOOD_NAME(String gOODNAME) {
    	GOOD_NAME = gOODNAME;
    }

	
    public String getISSUE_NO() {
    	return ISSUE_NO;
    }

	
    public void setISSUE_NO(String iSSUENO) {
    	ISSUE_NO = iSSUENO;
    }

	
    public String getORDER_ID() {
    	return ORDER_ID;
    }

	
    public void setORDER_ID(String oRDERID) {
    	ORDER_ID = oRDERID;
    }

	
    public double getPRICE() {
    	return PRICE;
    }

	
    public void setPRICE(double pRICE) {
    	PRICE = pRICE;
    }

	
    public String getSTATUS() {
    	return STATUS;
    }

	
    public void setSTATUS(String sTATUS) {
    	STATUS = sTATUS;
    }
}
