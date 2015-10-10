package com.cplatform.mall.dc.model;

/**
 * 负载<br>
 * <p>
 * Copyright: Copyright (c) 2014-5-8 下午03:40:39
 * <p>
 * Company: 苏州宽连信息技术有限公司
 * <p>
 * 
 * @author guyu@c-platform.com
 * @version 1.0.0
 */
public class MsOsUptimeInfo {

	private String id;

	/** 时间 */
	private String collectTime;

	private Double load1;
	
	private Double load5;
	
	private Double load15;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCollectTime() {
		return collectTime;
	}

	public void setCollectTime(String collectTime) {
		this.collectTime = collectTime;
	}

	public Double getLoad1() {
		return load1;
	}

	public void setLoad1(Double load1) {
		this.load1 = load1;
	}

	public Double getLoad5() {
		return load5;
	}

	public void setLoad5(Double load5) {
		this.load5 = load5;
	}

	public Double getLoad15() {
		return load15;
	}

	public void setLoad15(Double load15) {
		this.load15 = load15;
	}

}
