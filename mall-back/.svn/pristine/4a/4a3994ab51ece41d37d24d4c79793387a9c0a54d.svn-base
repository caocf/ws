package com.cplatform.mall.back.websys.entity;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.cplatform.mall.back.entity.IdEntity;
/**
 * 
 * 广告管理表. <br>
 * 用于存放数据库映射实体.
 * <p>
 * Copyright: Copyright (c) 2013-5-28 上午11:06:30
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * @author zhaowei@c-platform.com
 * @version 1.0.0
 */
@Entity
@Table(name = "T_SYS_AD")
public class SysAd extends IdEntity implements java.io.Serializable {
	
	/** 广告图片文件 **/
	public static final String AD_TYPE_IMG_KEY = "AD_TYPE_IMG";
	/** 类别map */
	private static Map<String, String> adFlagMap = null;
	static {
		adFlagMap = new HashMap<String, String>();
		adFlagMap.put("0", "内部广告");
		adFlagMap.put("1", "外部广告");
	}
	/** 类型map */
	private static Map<String, String> adTypeMap = null;
	static {
		adTypeMap = new HashMap<String, String>();
		adTypeMap.put("1", "图片");
		adTypeMap.put("2", "文字");
		adTypeMap.put("3", "脚本");
	}
	/** 状态map */
	private static Map<String, String> statusMap = null;
	static {
		statusMap = new HashMap<String, String>();
		statusMap.put("0", "未审核");
		statusMap.put("1", "审核通过");
		statusMap.put("2", "审核驳回");
	}
	//广告状态
	/**
	 * 广告状态 － 未审核
	 */
	public static final Long STATUS_0 = 0L;
	/**
	 * 广告状态 － 审核通过
	 */
	public static final Long STATUS_1 = 1L;
	/**
	 * 广告状态 － 审核驳回
	 */
	public static final Long STATUS_2 = 2L;
	// Fields
	private Long id;
	
	private String adName;
	
	private Long adType;
	
	private Long adFlag;
	
	private String link;
	
	private String linkman;
	
	private String content;
	
	private Long status;
	
	private String startTime;
	
	private String validTime;
	
	private Long clickCnt;
	
	private Long viewCnt;
	
	private String createTime;
	
	private Long createUser;
	
	private Long positionId;
	
	// Constructors

	public void setAdName(String adName) {
	    this.adName = adName;
    }

	@javax.persistence.Column(name = "AD_NAME")
	@Basic
	public String getAdName() {
	    return adName;
    }

	public void setAdType(Long adType) {
	    this.adType = adType;
    }

	@javax.persistence.Column(name = "AD_TYPE")
	@Basic
	public Long getAdType() {
	    return adType;
    }

	public void setAdFlag(Long adFlag) {
	    this.adFlag = adFlag;
    }

	@javax.persistence.Column(name = "AD_FLAG")
	@Basic
	public Long getAdFlag() {
	    return adFlag;
    }

	public void setLink(String link) {
	    this.link = link;
    }

	@javax.persistence.Column(name = "LINK")
	@Basic
	public String getLink() {
	    return link;
    }

	public void setLinkman(String linkman) {
	    this.linkman = linkman;
    }

	@javax.persistence.Column(name = "LINKMAN")
	@Basic
	public String getLinkman() {
	    return linkman;
    }

	public void setContent(String content) {
	    this.content = content;
    }

	@javax.persistence.Column(name = "CONTENT")
	@Basic
	public String getContent() {
	    return content;
    }

	public void setStatus(Long status) {
	    this.status = status;
    }

	@javax.persistence.Column(name = "STATUS")
	@Basic
	public Long getStatus() {
	    return status;
    }

	public void setStartTime(String startTime) {
	    this.startTime = startTime;
    }

	@javax.persistence.Column(name = "START_TIME")
	@Basic
	public String getStartTime() {
	    return startTime;
    }

	public void setValidTime(String validTime) {
	    this.validTime = validTime;
    }

	@javax.persistence.Column(name = "VALID_TIME")
	@Basic
	public String getValidTime() {
	    return validTime;
    }

	public void setClickCnt(Long clickCnt) {
	    this.clickCnt = clickCnt;
    }

	@javax.persistence.Column(name = "CLICK_CNT")
	@Basic
	public Long getClickCnt() {
	    return clickCnt;
    }

	public void setViewCnt(Long viewCnt) {
	    this.viewCnt = viewCnt;
    }

	@javax.persistence.Column(name = "VIEW_CNT")
	@Basic
	public Long getViewCnt() {
	    return viewCnt;
    }

	public void setCreateTime(String createTime) {
	    this.createTime = createTime;
    }

	@javax.persistence.Column(name = "CREATE_TIME")
	@Basic
	public String getCreateTime() {
	    return createTime;
    }

	public void setCreateUser(Long createUser) {
	    this.createUser = createUser;
    }

	@javax.persistence.Column(name = "CREATE_USER")
	@Basic
	public Long getCreateUser() {
	    return createUser;
    }

	public void setPositionId(Long positionId) {
	    this.positionId = positionId;
    }

	@javax.persistence.Column(name = "POSITION_ID")
	@Basic
	public Long getPositionId() {
	    return positionId;
    }
	
	// -------------bus method ---------------------
	private String startTimeBegin;
	
	private String startTimeEnd;
	
	private String validTimeBegin;
	
	private String validTimeEnd;
	
	private Long regionCode;
	
	private String regionName;
	
	private String isRegion;
	
	private String positionName;
	
	private String position;
	
	@Transient
	public void setStartTimeBegin(String startTimeBegin) {
	    this.startTimeBegin = startTimeBegin;
    }

	@Transient
	public String getStartTimeBegin() {
	    return startTimeBegin;
    }

	@Transient
	public void setStartTimeEnd(String startTimeEnd) {
	    this.startTimeEnd = startTimeEnd;
    }

	@Transient
	public String getStartTimeEnd() {
	    return startTimeEnd;
    }

	@Transient
	public void setValidTimeBegin(String validTimeBegin) {
	    this.validTimeBegin = validTimeBegin;
    }

	@Transient
	public String getValidTimeBegin() {
	    return validTimeBegin;
    }

	@Transient
	public void setValidTimeEnd(String validTimeEnd) {
	    this.validTimeEnd = validTimeEnd;
    }

	@Transient
	public String getValidTimeEnd() {
	    return validTimeEnd;
    }

	@Transient
	public void setRegionCode(Long regionCode) {
	    this.regionCode = regionCode;
    }

	@Transient
	public Long getRegionCode() {
	    return regionCode;
    }

	@Transient
	public void setRegionName(String regionName) {
	    this.regionName = regionName;
    }

	@Transient
	public String getRegionName() {
	    return regionName;
    }	
	@Transient
	public void setIsRegion(String isRegion) {
	    this.isRegion = isRegion;
    }
	@Transient
	public String getIsRegion() {
	    return isRegion;
    }
	
	@Transient
	public void setPositionName(String positionName) {
	    this.positionName = positionName;
    }
	@Transient
	public String getPositionName() {
	    return positionName;
    }
	// ----bus -method---

	@Transient
	public static Map<String, String> getAdFlagMap() {
		return adFlagMap;
	}

	@Transient
	public String getAdFlagName() {
		return adFlagMap.get(this.getAdFlag() + "");
	}
	
	@Transient
	public static Map<String, String> getAdTypeMap() {
		return adTypeMap;
	}

	@Transient
	public String getAdTypeName() {
		return adTypeMap.get(this.getAdType() + "");
	}
	
	@Transient
	public static Map<String, String> getStatusMap() {
		return statusMap;
	}

	@Transient
	public String getStatusName() {
		return statusMap.get(this.getStatus() + "");
	}
	@Transient
	public String getPosition() {
		return position;
	}
	@Transient
	public void setPosition(String position) {
		this.position = position;
	}
	

}
