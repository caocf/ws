package com.cplatform.mall.back.websys.entity;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.cplatform.mall.back.entity.IdEntity;

/**
 * 频道分类映射 . <br>
 * 用于存放数据库映射实体.
 * <p>
 * Copyright: Copyright (c) 2013-5-24 上午11:01:01
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author baijie@c-platform.com
 * @version 1.0.0
 */
@Entity
@Table(name = "T_CHANNEL_TYPE ")
public class ChannelType extends IdEntity implements java.io.Serializable {

	// Fields
	private Long typeId;

	private String regionCode;

	private String regionName;

	private Integer channel;

	private String displayName;

	private String typeName;
	
	private String channelName;
	
	public static Map<Integer, String> channelMap = null;
	static {
		channelMap = new HashMap<Integer, String>();
		channelMap.put(1, "商城");
		channelMap.put(2, "融合首页");
	}
	@Column(name = "type_id")
	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	@Column(name = "region_code")
	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	@Transient
	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	@Column(name = "channel")
	public Integer getChannel() {
		return channel;
	}

	public void setChannel(Integer channel) {
		this.channel = channel;
	}

	@Column(name = "display_name")
	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	@Transient
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	/**
	 * 获取 channelName
	 * 
	 * @return channelName
	 */
	@Transient
	public String getChannelName() {
		return channelMap.get(channel);
	}

	/**
	 * 设置 channelName
	 * 
	 * @param channelName
	 */
	public void ChannelName(String channelName) {
		this.channelName = channelName;
	}
}
