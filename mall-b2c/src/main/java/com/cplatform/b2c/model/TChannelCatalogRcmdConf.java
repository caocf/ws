package com.cplatform.b2c.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TChannelCatalogRcmdConf entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_CHANNEL_CATALOG_RCMD_CONF")
public class TChannelCatalogRcmdConf implements java.io.Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = 2077099769908008273L;

	// Fields

	private Integer id;

	private Integer groupId;

	private Integer channel;

	private String displayName;

	private String linkUrl;

	private Integer orderIndex;

	private Integer type;

	private String imagePath;
	
	private String regionCode;

	// Constructors

	/** default constructor */
	public TChannelCatalogRcmdConf() {
	}

	/** minimal constructor */
	public TChannelCatalogRcmdConf(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public TChannelCatalogRcmdConf(Integer id, Integer groupId, Integer channel, String displayName, String linkUrl, Integer orderIndex,
	                               Integer type, String imagePath, String regionCode) {
		this.id = id;
		this.groupId = groupId;
		this.channel = channel;
		this.displayName = displayName;
		this.linkUrl = linkUrl;
		this.orderIndex = orderIndex;
		this.type = type;
		this.imagePath = imagePath;
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

	@Column(name = "GROUP_ID", precision = 4, scale = 0)
	public Integer getGroupId() {
		return this.groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	@Column(name = "CHANNEL", precision = 4, scale = 0)
	public Integer getChannel() {
		return this.channel;
	}

	public void setChannel(Integer channel) {
		this.channel = channel;
	}

	@Column(name = "DISPLAY_NAME", length = 20)
	public String getDisplayName() {
		return this.displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	@Column(name = "LINK_URL")
	public String getLinkUrl() {
		return this.linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	@Column(name = "ORDER_INDEX", precision = 4, scale = 0)
	public Integer getOrderIndex() {
		return this.orderIndex;
	}

	public void setOrderIndex(Integer orderIndex) {
		this.orderIndex = orderIndex;
	}

	@Column(name = "TYPE", precision = 2, scale = 0)
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "IMAGE_PATH", length = 150)
	public String getImagePath() {
		return this.imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	@Column(name = "REGION_CODE")
    public String getRegionCode() {
    	return regionCode;
    }

	
    public void setRegionCode(String regionCode) {
    	this.regionCode = regionCode;
    }

}