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

import com.cplatform.mall.back.entity.IdEntity;

/**
 * 导航栏目配置.<br>
 * 用于存放数据库映射实体.
 * <p>
 * Copyright: Copyright (c) 2013-10-18 上午11:01:01
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author 季霁
 * @version 1.0.0
 */
@Entity
@Table(name = "T_NAV_ITEM")
public class ChannelNaviOper extends IdEntity implements java.io.Serializable {

	
	private Long pageId;
	
	private String pageCode;

	@Column(name = "page_code")
	public String getPageCode() {
		return pageCode;
	}

	public void setPageCode(String pageCode) {
		this.pageCode = pageCode;
	}

	private String code;

	private String title;

	private String regionCode;
	
	private String href;
	
	private int sortNo;
	
	private Long createUser;

	private String createTime;

	private Long updateUser;

	private String updateTime;
	
	private String regionName;

	private String pageCodeTitle;
	
	@Transient
	public String getRegionName() {
		return regionName;
	}

	@Transient
	public String getPageCodeTitle() {
		return pageCodeTitle;
	}

	public void setPageCodeTitle(String pageCodeTitle) {
		this.pageCodeTitle = pageCodeTitle;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public static Map<Integer, String> getChannelMap() {
		return channelMap;
	}

	public static void setChannelMap(Map<Integer, String> channelMap) {
		ChannelNaviOper.channelMap = channelMap;
	}

	public static Map<Integer, String> channelMap = null;
	static {
		channelMap = new HashMap<Integer, String>();
		channelMap.put(1, "商城");
		channelMap.put(2, "融合首页");
	}

	@Column(name = "code")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "title")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	@Column(name = "page_id")
	public Long getPageId() {
		return pageId;
	}

	public void setPageId(Long pageId) {
		this.pageId = pageId;
	}

	@Column(name = "region_code")
	public String getRegionCode() {
		return regionCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + sortNo;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result
				+ ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result
				+ ((createUser == null) ? 0 : createUser.hashCode());
		result = prime * result + ((href == null) ? 0 : href.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((pageId == null) ? 0 : pageId.hashCode());
		result = prime * result
				+ ((regionCode == null) ? 0 : regionCode.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result
				+ ((updateTime == null) ? 0 : updateTime.hashCode());
		result = prime * result
				+ ((updateUser == null) ? 0 : updateUser.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChannelNaviOper other = (ChannelNaviOper) obj;
		if (sortNo != other.sortNo)
			return false;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (createTime == null) {
			if (other.createTime != null)
				return false;
		} else if (!createTime.equals(other.createTime))
			return false;
		if (createUser == null) {
			if (other.createUser != null)
				return false;
		} else if (!createUser.equals(other.createUser))
			return false;
		if (href == null) {
			if (other.href != null)
				return false;
		} else if (!href.equals(other.href))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (pageId == null) {
			if (other.pageId != null)
				return false;
		} else if (!pageId.equals(other.pageId))
			return false;
		if (regionCode == null) {
			if (other.regionCode != null)
				return false;
		} else if (!regionCode.equals(other.regionCode))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (updateTime == null) {
			if (other.updateTime != null)
				return false;
		} else if (!updateTime.equals(other.updateTime))
			return false;
		if (updateUser == null) {
			if (other.updateUser != null)
				return false;
		} else if (!updateUser.equals(other.updateUser))
			return false;
		return true;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	@Column(name = "href")
	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	@Column(name = "sort_no")
	public int getSortNo() {
		return sortNo;
	}


	public void setSortNo(int sortNo) {
		this.sortNo = sortNo;
	}

	@Column(name = "create_user")
	public Long getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Long createUser) {
		this.createUser = createUser;
	}

	@Column(name = "create_time")
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Column(name = "update_user")
	public Long getUpdateUser() {
		return updateUser;
	}

	public ChannelNaviOper() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void setUpdateUser(Long updateUser) {
		this.updateUser = updateUser;
	}

	@Column(name = "update_time")
	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

}
