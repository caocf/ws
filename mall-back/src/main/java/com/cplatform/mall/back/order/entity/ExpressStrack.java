package com.cplatform.mall.back.order.entity;

import java.util.List;

import net.sf.json.JSONArray;

/**
 * @Title	物流追踪实体类
 * @Description
 * @Copyright: Copyright (c) 2013-8-8
 * @Company: 北京宽连十方数字技术有限公司
 * @Author chencheng
 * @Version: 1.0
 *
 */
public class ExpressStrack {
	/**
	 * 运单号
	 */
	private String nu;
	private String message;
	private Long ischeck;
	/**
	 * 物流公司拼音
	 */
	private String com;
	private String condition;
	private Long status;
	private Long state;
	private List<ExpressStrackData> data;
	public String getMessage() {
		return message;
	}
	
	public String getCom() {
		return com;
	}
	public String getCondition() {
		return condition;
	}
	public Long getStatus() {
		return status;
	}
	public Long getState() {
		return state;
	}
	public List<ExpressStrackData> getData() {
		return data;
	}
	public void setData(List<ExpressStrackData> data) {
		JSONArray jarr = JSONArray.fromObject(data);
	    List<ExpressStrackData> listobject =  (List)jarr.toCollection(jarr,ExpressStrackData.class);
		this.data = listobject;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public Long getIscheck() {
		return ischeck;
	}
	public void setIscheck(Long ischeck) {
		this.ischeck = ischeck;
	}
	public void setCom(String com) {
		this.com = com;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public void setStatus(Long status) {
		this.status = status;
	}
	public void setState(Long state) {
		this.state = state;
	}

	public String getNu() {
		return nu;
	}

	public void setNu(String nu) {
		this.nu = nu;
	}
	
	
}
