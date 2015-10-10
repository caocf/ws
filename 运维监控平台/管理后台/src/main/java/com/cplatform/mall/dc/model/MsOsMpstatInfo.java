package com.cplatform.mall.dc.model;

/**
 * CPU使用率 <br>
 * <p>
 * Copyright: Copyright (c) 2014-5-9 上午10:02:09
 * <p>
 * Company: 苏州宽连信息技术有限公司
 * <p>
 * 
 * @author guyu@c-platform.com
 * @version 1.0.0
 */
public class MsOsMpstatInfo {

	private String id;

	/** 时间 */
	private String collectTime;

	private Double cpu_user;
	
	private Double cpu_sys;
	
	private Double iowait;

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

	public Double getCpu_user() {
		return cpu_user;
	}

	public void setCpu_user(Double cpuUser) {
		cpu_user = cpuUser;
	}

	public Double getCpu_sys() {
		return cpu_sys;
	}

	public void setCpu_sys(Double cpuSys) {
		cpu_sys = cpuSys;
	}

	public Double getIowait() {
		return iowait;
	}

	public void setIowait(Double iowait) {
		this.iowait = iowait;
	}

}
