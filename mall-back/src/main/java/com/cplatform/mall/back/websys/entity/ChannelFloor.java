package com.cplatform.mall.back.websys.entity;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.cplatform.mall.back.entity.IdEntity;

/**
 * 楼层映射 . <br>
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
@Table(name = "T_CHANNEL_FLOOR_CONF ")
public class ChannelFloor extends IdEntity implements java.io.Serializable {

	// Fields
	private Long id;

	private String floorTitle;

	private Integer floorId;

	private Long typeId;
	
	private Integer orderIndex;

	private Integer channel;
	
	private String regionCode;
	
	private String updateTime;
	
	private String floorName;

	private String channelName;
	
	private String regionName;
	
	private String typeName;
	
	public static Map<Integer, String> groupMap = null;
	static {
		groupMap = new HashMap<Integer, String>();
		groupMap.put(0, "顶楼");
		groupMap.put(1, "1楼");
		groupMap.put(2, "2楼");
		groupMap.put(3, "3楼");
		groupMap.put(4, "4楼");
		groupMap.put(5, "5楼");
	}

	public static Map<Integer, String> channelMap = null;
	static {
		channelMap = new HashMap<Integer, String>();
		channelMap.put(1, "商城");
		channelMap.put(2, "融合首页");


	}
	
	
	@Column(name = "title")
	public String getFloorTitle() {
		return floorTitle;
	}

	public void setFloorTitle(String floorTitle) {
		this.floorTitle = floorTitle;
	}
	@Column(name = "floor_id")
	public Integer getFloorId() {
		return floorId;
	}

	public void setFloorId(Integer floorId) {
		this.floorId = floorId;
	}
	@Column(name = "type_id")
	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}
	@Column(name = "order_index")
	public Integer getOrderIndex() {
		return orderIndex;
	}

	public void setOrderIndex(Integer orderIndex) {
		this.orderIndex = orderIndex;
	}
	@Column(name = "channel")
	public Integer getChannel() {
		return channel;
	}

	public void setChannel(Integer channel) {
		this.channel = channel;
	}
	@Column(name = "region_code")
	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}
	@Column(name = "update_time")
	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取 floorName
	 * 
	 * @return floorName
	 */
	@Transient
	public String getFloorName() {
		return groupMap.get(floorId);
	}

	/**
	 * 设置 floorName
	 * 
	 * @param floorName
	 */
	public void FloorName(String floorName) {
		this.floorName = floorName;
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

	@Transient
	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	
	@Transient
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
}
