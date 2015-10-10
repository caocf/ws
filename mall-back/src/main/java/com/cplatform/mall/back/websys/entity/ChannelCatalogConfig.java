package com.cplatform.mall.back.websys.entity;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonProperty;

@Entity
@Table(name = "T_CHANNEL_CATALOG_CONF")
public class ChannelCatalogConfig {

	private Long id;

	private Long itemId;

	private Integer groupId;

	private String updateTime;

	private Integer orderIndex;

	private Integer status;

	private Integer channel;

	private String itemOrgName;

	private String regionCode;

	private String regionName;
	

	private Long isValid;

	/**
	 * 获取 itemOrgName
	 * 
	 * @return itemOrgName
	 */
	@Transient
	public String getItemOrgName() {
		return itemOrgName;
	}
	
	/**
	 * 设置 itemOrgName
	 * 
	 * @param itemOrgName
	 */
	public void setItemOrgName(String itemOrgName) {
		this.itemOrgName = itemOrgName;
	}

	/**
	 * 获取 statusName
	 * 
	 * @return statusName
	 */
	@Transient
	public String getStatusName() {
		return statusMap.get(status);
	}

	/**
	 * 获取 channelName
	 * 
	 * @return channelName
	 */
	@Transient
	public String getChannelName() {
		return ChannelCatalogRcmdConfig.channelMap.get(channel);
	}

	/**
	 * 获取 groupName
	 * 
	 * @return groupName
	 */
	@Transient
	public String getGroupName() {
		return ChannelCatalogRcmdConfig.groupMap.get(groupId);
	}

	public static Map<Integer, String> statusMap = null;
	static {
		statusMap = new HashMap<Integer, String>();
		statusMap.put(0, "禁用");
		statusMap.put(1, "启用");
	}

	/**
	 * 获取 id
	 * 
	 * @return id
	 */
	@SequenceGenerator(name = "seq_channelCatalog", sequenceName = "SEQ_CHANNEL_CATALOG_CONF")
	@Id
	@GeneratedValue(generator = "seq_channelCatalog")
	@JsonProperty
	public Long getId() {
		return id;
	}

	/**
	 * 设置 id
	 * 
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 获取 itemId
	 * 
	 * @return itemId
	 */
	@Column(name = "item_id")
	public Long getItemId() {
		return itemId;
	}

	/**
	 * 设置 itemId
	 * 
	 * @param itemId
	 */
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	/**
	 * 获取 groupId
	 * 
	 * @return groupId
	 */
	@Column(name = "group_id")
	public Integer getGroupId() {
		return groupId;
	}

	/**
	 * 设置 groupId
	 * 
	 * @param groupId
	 */
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	/**
	 * 获取 updateTime
	 * 
	 * @return updateTime
	 */
	@Column(name = "update_time")
	public String getUpdateTime() {
		return updateTime;
	}

	/**
	 * 设置 updateTime
	 * 
	 * @param updateTime
	 */
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * 获取 orderIndex
	 * 
	 * @return orderIndex
	 */
	@Column(name = "order_index")
	public Integer getOrderIndex() {
		return orderIndex;
	}

	/**
	 * 设置 orderIndex
	 * 
	 * @param orderIndex
	 */
	public void setOrderIndex(Integer orderIndex) {
		this.orderIndex = orderIndex;
	}

	/**
	 * 获取 status
	 * 
	 * @return status
	 */
	@Column(name = "status")
	public Integer getStatus() {
		return status;
	}

	/**
	 * 设置 status
	 * 
	 * @param status
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * 获取 channel
	 * 
	 * @return channel
	 */
	@Column(name = "channel")
	public Integer getChannel() {
		return channel;
	}

	/**
	 * 设置 channel
	 * 
	 * @param channel
	 */
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

	@Transient
	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	
	
	@Transient
	public Long getIsValid() {
		return isValid;
	}

	public void setIsValid(Long isValid) {
		this.isValid = isValid;
	}

}
