package com.cplatform.b2c.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TChannelPicConf entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_CHANNEL_PIC_CONF")
public class TChannelPicConf implements java.io.Serializable {

	// Fields

	/** serialVersionUID */
    private static final long serialVersionUID = 4418447875348072970L;

	private Integer id;

	private String picPath;

	private String picAlt;

	private Short picIndex;

	private String linkUrl;

	private Short postion;

	private Short channel;

	private String updateTime;
	
	private String regionCode;

	// Constructors

	/** default constructor */
	public TChannelPicConf() {
	}

	/** minimal constructor */
	public TChannelPicConf(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public TChannelPicConf(Integer id, String picPath, String picAlt, Short picIndex, String linkUrl, Short postion, Short channel, String updateTime, String regionCode) {
		this.id = id;
		this.picPath = picPath;
		this.picAlt = picAlt;
		this.picIndex = picIndex;
		this.linkUrl = linkUrl;
		this.postion = postion;
		this.channel = channel;
		this.updateTime = updateTime;
		this.regionCode = regionCode;
		
	}

	// Property accessors
	@Id
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "PIC_PATH", length = 150)
	public String getPicPath() {
		return this.picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	@Column(name = "PIC_ALT", length = 150)
	public String getPicAlt() {
		return this.picAlt;
	}

	public void setPicAlt(String picAlt) {
		this.picAlt = picAlt;
	}

	@Column(name = "PIC_INDEX")
	public Short getPicIndex() {
		return this.picIndex;
	}

	public void setPicIndex(Short picIndex) {
		this.picIndex = picIndex;
	}

	@Column(name = "LINK_URL", length = 150)
	public String getLinkUrl() {
		return this.linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	@Column(name = "POSTION", precision = 2, scale = 0)
	public Short getPostion() {
		return this.postion;
	}

	public void setPostion(Short postion) {
		this.postion = postion;
	}

	@Column(name = "CHANNEL", precision = 1, scale = 0)
	public Short getChannel() {
		return this.channel;
	}

	public void setChannel(Short channel) {
		this.channel = channel;
	}

	@Column(name = "UPDATE_TIME", length = 14)
	public String getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "REGION_CODE")
    public String getRegionCode() {
    	return regionCode;
    }

	
    public void setRegionCode(String regionCode) {
    	this.regionCode = regionCode;
    }

}