package com.cplatform.mall.back.giftcard.entity;

public class ReturnData {
	private String status; //返回的状态，1：成功，0：失败
	private String msg;  //返回的提示信息
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
