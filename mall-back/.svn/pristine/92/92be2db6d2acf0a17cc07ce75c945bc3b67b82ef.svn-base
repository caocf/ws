package com.cplatform.mall.back.cont.entity;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.cplatform.mall.back.entity.IdEntity;

/**
 * 
 * 短信内容管理表. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2013-6-17 下午01:38:47
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * @author zhaowei@c-platform.com
 * @version 1.0.0
 */
@Entity
@Table(name = "T_CONT_SMS")
public class ContSms extends IdEntity implements java.io.Serializable {

	/** serialVersionUID */
    private static final long serialVersionUID = -2562279614227714369L;

    /** 状态map */
	private static Map<String, String> statusMap = null;
	static {
		statusMap = new HashMap<String, String>();
		statusMap.put("0", "未审核");
		statusMap.put("1", "审核通过");
		statusMap.put("2", "审核驳回");
	}	

	@Transient
	public static Map<String, String> getStatusMap() {
		return statusMap;
	}
	
	@Transient
	public String getStatusName() {
		return statusMap.get(this.getStatus() + "");
	}

	//状态
	/**
	 * 状态 － 未审核
	 */
	public static final Long STATUS_0 = 0L;
	/**
	 * 状态 － 审核通过
	 */
	public static final Long STATUS_1 = 1L;
	/**
	 * 状态 － 审核驳回
	 */
	public static final Long STATUS_2 = 2L;

	private	Long id;
	private String contentSrc;
	private String content;
	private String startTime;
	private String endTime;
	private String keyword;
	private String areaCode;
	private String unitId;
	private String remark;
	private Long status;
	private Long auditorId;
	private String advice;
	private Long updateUserId;
	private String updateTime;
	
	public void setContentSrc(String contentSrc) {
	    this.contentSrc = contentSrc;
    }
	@Column(name = "CONTENT_SRC")
	public String getContentSrc() {
	    return contentSrc;
    }

	public void setContent(String content) {
	    this.content = content;
    }
	@Column(name = "CONTENT")
	public String getContent() {
	    return content;
    }

	public void setStartTime(String startTime) {
	    this.startTime = startTime;
    }
	@Column(name = "START_TIME")
	public String getStartTime() {
	    return startTime;
    }

	public void setEndTime(String endTime) {
	    this.endTime = endTime;
    }
	@Column(name = "END_TIME")
	public String getEndTime() {
	    return endTime;
    }

	public void setKeyword(String keyword) {
	    this.keyword = keyword;
    }
	@Column(name = "KEYWORD")
	public String getKeyword() {
	    return keyword;
    }

	public void setAreaCode(String areaCode) {
	    this.areaCode = areaCode;
    }
	@Column(name = "AREA_CODE")
	public String getAreaCode() {
	    return areaCode;
    }

	public void setUnitId(String unitId) {
	    this.unitId = unitId;
    }
	@Column(name = "UNIT_ID")
	public String getUnitId() {
	    return unitId;
    }

	public void setRemark(String remark) {
	    this.remark = remark;
    }
	@Column(name = "REMARK")
	public String getRemark() {
	    return remark;
    }

	public void setStatus(Long status) {
	    this.status = status;
    }
	@Column(name = "STATUS")
	public Long getStatus() {
	    return status;
    }

	public void setAuditorId(Long auditorId) {
	    this.auditorId = auditorId;
    }
	@Column(name = "AUDITOR_ID")
	public Long getAuditorId() {
	    return auditorId;
    }

	public void setAdvice(String advice) {
	    this.advice = advice;
    }
	@Column(name = "ADVICE")
	public String getAdvice() {
	    return advice;
    }

	public void setUpdateUserId(Long updateUserId) {
	    this.updateUserId = updateUserId;
    }
	@Column(name = "UPDATE_USER_ID")
	public Long getUpdateUserId() {
	    return updateUserId;
    }

	public void setUpdateTime(String updateTime) {
	    this.updateTime = updateTime;
    }
	@Column(name = "UPDATE_TIME")
	public String getUpdateTime() {
	    return updateTime;
    }

	//****************bus method*******************
	/** 单位名称 **/
	private String unitName;
	/** 内容源名称 **/
	private String srcName;
	
	@Transient
	public void setUnitName(String unitName) {
	    this.unitName = unitName;
    }
	@Transient
	public String getUnitName() {
	    return unitName;
    }
	
	@Transient
	public void setSrcName(String srcName) {
	    this.srcName = srcName;
    }
	@Transient
	public String getSrcName() {
	    return srcName;
    }
}
