package com.cplatform.mall.back.cont.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.cplatform.mall.back.entity.IdEntity;

/**
 * 彩信实体类
 * <p>
 * Copyright: Copyright (c) 2012-11-22 下午3:19:29
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author jisn@c-platform.com
 * @version 1.0.0
 */
@Entity
@Table(name = "T_CONT_MMS")
public class ContMms extends IdEntity implements Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = 8639016447318442370L;

	public static final int ASTATUS_WAIT = 0;

	public static final int ASTATUS_OK = 1;

	public static final int ASTATUS_REJECT = 2;

	public static Map<Integer, String> ASTATUS_MAP = new HashMap<Integer, String>();

	static {
		ASTATUS_MAP.put(ASTATUS_WAIT, "审核中");
		ASTATUS_MAP.put(ASTATUS_OK, "审核通过");
		ASTATUS_MAP.put(ASTATUS_REJECT, "审核驳回");
	}

	private String contentSrc;

	private String content;

	private String startTime;

	private String endTime;

	private String keyword;

	private String areaCode;

	private String unitId;

	private String remark;

	private Long prodId;

	private Long periodId;

	private Integer status;

	private Long auditorId;

	private String advice;

	private Long updateUserId;

	private String updateTime;

	private String title;

	private String contentPath;

	private String picSize;

	private String smilName;

	private String param1;

	private String param2;

	private Integer contentSize;

	private Integer attachmentType;

	/** default constructor */
	public ContMms() {
	}

	/** full constructor */
	public ContMms(Long id, String contentSrc, String content, String startTime, String endTime, String keyword, String areaCode, String unitId,
	               String remark, Long prodId, Long periodId, Integer status, Long auditorId, String advice, Long updateUserId, String updateTime,
	               String title, String contentPath, String picSize, String smilName, String param1, String param2, Integer contentSize,
	               Integer attachmentType) {
		this.id = id;
		this.contentSrc = contentSrc;
		this.content = content;
		this.startTime = startTime;
		this.endTime = endTime;
		this.keyword = keyword;
		this.areaCode = areaCode;
		this.unitId = unitId;
		this.remark = remark;
		this.prodId = prodId;
		this.periodId = periodId;
		this.status = status;
		this.auditorId = auditorId;
		this.advice = advice;
		this.updateUserId = updateUserId;
		this.updateTime = updateTime;
		this.title = title;
		this.contentPath = contentPath;
		this.picSize = picSize;
		this.smilName = smilName;
		this.param1 = param1;
		this.param2 = param2;
		this.contentSize = contentSize;
	}

	@Transient
	public Integer getAttachmentType() {
		return attachmentType;
	}

	public void setAttachmentType(Integer attachmentType) {
		this.attachmentType = attachmentType;
	}

	public ContMms(String contentSrc, String startTime, String endTime) {
		this.id = id;
		this.contentSrc = contentSrc;
		this.status = 0;
		this.startTime = startTime;
		this.endTime = endTime;
		this.keyword = "guanjz";
		this.title = "biaoti";
	}

	public ContMms(String contentSrc) {
		this.id = id;
		this.contentSrc = contentSrc;
		this.status = 0;
	}

	@Column(name = "ADVICE", length = 200)
	public String getAdvice() {
		return this.advice;
	}

	@Column(name = "AREA_CODE", length = 100)
	public String getAreaCode() {
		return this.areaCode;
	}

	@Column(name = "AUDITOR_ID", precision = 9, scale = 0)
	public Long getAuditorId() {
		return this.auditorId;
	}

	@Column(name = "CONTENT", nullable = false, length = 1000)
	public String getContent() {
		return this.content;
	}

	@Column(name = "CONTENT_SRC", nullable = false, length = 20)
	public String getContentSrc() {
		return this.contentSrc;
	}

	@Column(name = "END_TIME", length = 14)
	public String getEndTime() {
		return this.endTime;
	}

	@Column(name = "KEYWORD", length = 200)
	public String getKeyword() {
		return this.keyword;
	}

	@Transient
	public Long getPeriodId() {
		return this.periodId;
	}

	@Transient
	public Long getProdId() {
		return this.prodId;
	}

	@Column(name = "REMARK", length = 200)
	public String getRemark() {
		return this.remark;
	}

	@Column(name = "START_TIME", length = 14)
	public String getStartTime() {
		return this.startTime;
	}

	@Column(name = "STATUS", nullable = false, precision = 3, scale = 0)
	public Integer getStatus() {
		return this.status;
	}

	@Column(name = "UNIT_ID", length = 20)
	public String getUnitId() {
		return this.unitId;
	}

	@Column(name = "UPDATE_TIME", length = 14)
	public String getUpdateTime() {
		return this.updateTime;
	}

	@Column(name = "UPDATE_USER_ID", precision = 9, scale = 0)
	public Long getUpdateUserId() {
		return this.updateUserId;
	}

	public void setAdvice(String advice) {
		this.advice = advice;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public void setAuditorId(Long auditorId) {
		this.auditorId = auditorId;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setContentSrc(String contentSrc) {
		this.contentSrc = contentSrc;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public void setPeriodId(Long periodId) {
		this.periodId = periodId;
	}

	public void setProdId(Long prodId) {
		this.prodId = prodId;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public void setUpdateUserId(Long updateUserId) {
		this.updateUserId = updateUserId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContentPath() {
		return contentPath;
	}

	public void setContentPath(String contentPath) {
		this.contentPath = contentPath;
	}

	public String getPicSize() {
		return picSize;
	}

	public void setPicSize(String picSize) {
		this.picSize = picSize;
	}

	public String getSmilName() {
		return smilName;
	}

	public void setSmilName(String smilName) {
		this.smilName = smilName;
	}

	public String getParam1() {
		return param1;
	}

	public void setParam1(String param1) {
		this.param1 = param1;
	}

	public String getParam2() {
		return param2;
	}

	public void setParam2(String param2) {
		this.param2 = param2;
	}

	public Integer getContentSize() {
		return contentSize;
	}

	public void setContentSize(Integer contentSize) {
		this.contentSize = contentSize;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
