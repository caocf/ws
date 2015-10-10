package com.cplatform.mall.back.smsbuy.entity;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 短信购帮助配置实体类. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-6-16 下午3:37:30
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: wuyn@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
@Entity
@Table(name = "T_SMSBUY_HELP")
public class SmsbuyHelp {

	private String helpSpcode;

	private String rootSpcode;

	private String helpArea;

	private String helpAreaName;

	private String recommendType;

	private String recommendSpcode;

	private String recommendSms;

	public static Map<String, String> recommendTypeMap = new HashMap<String, String>();
	static {
		recommendTypeMap.put("priority", "按优先级");
		recommendTypeMap.put("hot", "按最新商品");
		recommendTypeMap.put("manual", "按人工推荐");
	}

	@Transient
	public String getRecommendTypeName() {
		return recommendTypeMap.get(recommendType);
	}

	/**
	 * 获取 rootSpcode
	 * 
	 * @return rootSpcode
	 */
	@Transient
	public String getRootSpcode() {
		return rootSpcode;
	}

	/**
	 * 设置 rootSpcode
	 * 
	 * @param rootSpcode
	 */
	public void setRootSpcode(String rootSpcode) {
		this.rootSpcode = rootSpcode;
	}

	/**
	 * 获取 helpAreaName
	 * 
	 * @return helpAreaName
	 */
	@Transient
	public String getHelpAreaName() {
		return helpAreaName;
	}

	/**
	 * 设置 helpAreaName
	 * 
	 * @param helpAreaName
	 */
	public void setHelpAreaName(String helpAreaName) {
		this.helpAreaName = helpAreaName;
	}

	/**
	 * 获取 helpSpcode
	 * 
	 * @return helpSpcode
	 */
	@Column(name = "HELP_SPCODE")
	@Id
	public String getHelpSpcode() {
		return helpSpcode;
	}

	/**
	 * 设置 helpSpcode
	 * 
	 * @param helpSpcode
	 */
	public void setHelpSpcode(String helpSpcode) {
		this.helpSpcode = helpSpcode;
	}

	/**
	 * 获取 helpArea
	 * 
	 * @return helpArea
	 */
	@Column(name = "HELP_AREA")
	public String getHelpArea() {
		return helpArea;
	}

	/**
	 * 设置 helpArea
	 * 
	 * @param helpArea
	 */
	public void setHelpArea(String helpArea) {
		this.helpArea = helpArea;
	}

	/**
	 * 获取 recommendType
	 * 
	 * @return recommendType
	 */
	@Column(name = "RECOMMEND_TYPE")
	public String getRecommendType() {
		return recommendType;
	}

	/**
	 * 设置 recommendType
	 * 
	 * @param recommendType
	 */
	public void setRecommendType(String recommendType) {
		this.recommendType = recommendType;
	}

	/**
	 * 获取 recommendSpcode
	 * 
	 * @return recommendSpcode
	 */
	@Column(name = "RECOMMEND_SPCODE")
	public String getRecommendSpcode() {
		return recommendSpcode;
	}

	/**
	 * 设置 recommendSpcode
	 * 
	 * @param recommendSpcode
	 */
	public void setRecommendSpcode(String recommendSpcode) {
		this.recommendSpcode = recommendSpcode;
	}

	/**
	 * 获取 recommendSms
	 * 
	 * @return recommendSms
	 */
	@Column(name = "RECOMMEND_SMS")
	public String getRecommendSms() {
		return recommendSms;
	}

	/**
	 * 设置 recommendSms
	 * 
	 * @param recommendSms
	 */
	public void setRecommendSms(String recommendSms) {
		this.recommendSms = recommendSms;
	}
}
