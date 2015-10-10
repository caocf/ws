package com.cplatform.mall.dc.model;

/**
 * 磁盘使用率 <br>
 * <p>
 * Copyright: Copyright (c) 2014-5-9 上午11:08:27
 * <p>
 * Company: 苏州宽连信息技术有限公司
 * <p>
 * 
 * @author guyu@c-platform.com
 * @version 1.0.0
 */
public class MsOsDiskSpaceInfo {

	private String id;

	/** 时间 */
	private String collectTime;

	private String ms_mount_on;
	
	private Double ms_use;
	
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

	public String getMs_mount_on() {
		return ms_mount_on;
	}

	public void setMs_mount_on(String msMountOn) {
		ms_mount_on = msMountOn;
	}

	public Double getMs_use() {
		return ms_use;
	}

	public void setMs_use(Double msUse) {
		ms_use = msUse;
	}

}
