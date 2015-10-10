package com.cplatform.b2c.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TChannelFloorConf entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_CHANNEL_FLOOR_CONF")
public class TChannelFloorConf implements java.io.Serializable {

	// Fields

	/** serialVersionUID */
    private static final long serialVersionUID = 3588596446314700206L;

	private Integer id;

	private String title;

	private Integer floorId;

	private Integer typeId;

	private Integer orderIndex;

	private Integer channel;

	private String regionCode;

	private String updateTime;

	// Constructors

	/** default constructor */
	public TChannelFloorConf() {
	}

	/** minimal constructor */
	public TChannelFloorConf(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public TChannelFloorConf(Integer id, String title, Integer floorId, Integer typeId, Integer orderIndex, Integer channel, String regionCode,
	                         String updateTime) {
		this.id = id;
		this.title = title;
		this.floorId = floorId;
		this.typeId = typeId;
		this.orderIndex = orderIndex;
		this.channel = channel;
		this.regionCode = regionCode;
		this.updateTime = updateTime;
	}

	// Property accessors
	@Id
	@Column(name = "ID", unique = true, nullable = false, precision = 8, scale = 0)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "TITLE", length = 100)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "FLOOR_ID", precision = 4, scale = 0)
	public Integer getFloorId() {
		return this.floorId;
	}

	public void setFloorId(Integer floorId) {
		this.floorId = floorId;
	}

	@Column(name = "TYPE_ID", precision = 8, scale = 0)
	public Integer getTypeId() {
		return this.typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	@Column(name = "ORDER_INDEX", precision = 4, scale = 0)
	public Integer getOrderIndex() {
		return this.orderIndex;
	}

	public void setOrderIndex(Integer orderIndex) {
		this.orderIndex = orderIndex;
	}

	@Column(name = "CHANNEL", precision = 4, scale = 0)
	public Integer getChannel() {
		return this.channel;
	}

	public void setChannel(Integer channel) {
		this.channel = channel;
	}

	@Column(name = "REGION_CODE", length = 100)
	public String getRegionCode() {
		return this.regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	@Column(name = "UPDATE_TIME", length = 14)
	public String getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

}