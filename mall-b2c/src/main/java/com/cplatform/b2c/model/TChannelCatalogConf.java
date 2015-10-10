package com.cplatform.b2c.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TChannelCatalogConf entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_CHANNEL_CATALOG_CONF")
public class TChannelCatalogConf implements java.io.Serializable {

	// Fields

	/** serialVersionUID */
    private static final long serialVersionUID = -1147798619760354476L;

	private Integer id;

	private Integer itemId;

	private Short groupId;

	private String updateTime;

	private Short orderIndex;

	private Short status;

	private Short channel;
	
	private String regionCode;

	// Constructors

	/** default constructor */
	public TChannelCatalogConf() {
	}

	/** minimal constructor */
	public TChannelCatalogConf(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public TChannelCatalogConf(Integer id, Integer itemId, Short groupId, String updateTime, Short orderIndex, Short status, Short channel, String regionCode) {
		this.id = id;
		this.itemId = itemId;
		this.groupId = groupId;
		this.updateTime = updateTime;
		this.orderIndex = orderIndex;
		this.status = status;
		this.channel = channel;
		this.regionCode = regionCode;
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

	@Column(name = "ITEM_ID", precision = 8, scale = 0)
	public Integer getItemId() {
		return this.itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	@Column(name = "GROUP_ID", precision = 4, scale = 0)
	public Short getGroupId() {
		return this.groupId;
	}

	public void setGroupId(Short groupId) {
		this.groupId = groupId;
	}

	@Column(name = "UPDATE_TIME", length = 14)
	public String getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "ORDER_INDEX", precision = 4, scale = 0)
	public Short getOrderIndex() {
		return this.orderIndex;
	}

	public void setOrderIndex(Short orderIndex) {
		this.orderIndex = orderIndex;
	}

	@Column(name = "STATUS", precision = 1, scale = 0)
	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	@Column(name = "CHANNEL", precision = 2, scale = 0)
	public Short getChannel() {
		return this.channel;
	}

	public void setChannel(Short channel) {
		this.channel = channel;
	}

	@Column(name = "REGION_CODE")
    public String getRegionCode() {
    	return regionCode;
    }

	
    public void setRegionCode(String regionCode) {
    	this.regionCode = regionCode;
    }

}