package com.cplatform.mall.dc.model;

/**
 * PVInfo.java <br>
 * <p>
 * Copyright: Copyright (c) 2014-4-30 下午01:56:14
 * <p>
 * Company: 苏州宽连信息技术有限公司
 * <p>
 * 
 * @author guyu@c-platform.com
 * @version 1.0.0
 */
public class PVInfo {

	private String id;

	private String urlName;
	
	private String webUrl;

	/** 时间 */
	private String collectTime;

	/** 转换率 */
	private Double num;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUrlName() {
		return urlName;
	}

	public void setUrlName(String urlName) {
		this.urlName = urlName;
	}

	public String getWebUrl() {
		return webUrl;
	}

	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}

	public String getCollectTime() {
		return collectTime;
	}

	public void setCollectTime(String collectTime) {
		this.collectTime = collectTime;
	}

	public Double getNum() {
		return num;
	}

	public void setNum(Double num) {
		this.num = num;
	}


}
