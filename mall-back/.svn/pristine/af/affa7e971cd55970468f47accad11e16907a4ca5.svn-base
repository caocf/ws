package com.cplatform.mall.back.websys.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "T_CHANNEL_PIC_CONF")
public class SysChannelPicConf {

	private Integer id;

	private String picPath;

	private String picAlt;

	private Integer picIndex;

	private String linkUrl;

	private Integer postion;

	private Integer channel;

	private String updateTime;

	private String postionName;

	private String channelName;

	private String regionCode;

	private String regionName;

	@Id
	@GeneratedValue
	@Column(name = "ID")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "PIC_PATH")
	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	@Column(name = "PIC_ALT")
	public String getPicAlt() {
		return picAlt;
	}

	public void setPicAlt(String picAlt) {
		this.picAlt = picAlt;
	}

	@Column(name = "PIC_INDEX")
	public Integer getPicIndex() {
		return picIndex;
	}

	public void setPicIndex(Integer picIndex) {
		this.picIndex = picIndex;
	}

	@Column(name = "LINK_URL")
	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	@Column(name = "POSTION")
	public Integer getPostion() {
		return postion;
	}

	public void setPostion(Integer postion) {
		this.postion = postion;
	}

	@Column(name = "CHANNEL")
	public Integer getChannel() {
		return channel;
	}

	public void setChannel(Integer channel) {
		this.channel = channel;
	}

	@Column(name = "UPDATE_TIME")
	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	@Transient
	public String getPostionName() {
		return ChannelCatalogRcmdConfig.groupMap.get(postion);
	}

	public void setPostionName(String postionName) {
		this.postionName = postionName;
	}

	@Transient
	public String getChannelName() {
		return ChannelCatalogRcmdConfig.channelMap.get(channel);
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
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

}
