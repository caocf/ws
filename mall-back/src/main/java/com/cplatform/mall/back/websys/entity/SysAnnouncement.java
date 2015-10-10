package com.cplatform.mall.back.websys.entity;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.cplatform.mall.back.entity.IdEntity;

/**
 * Title. <br>
 * Description.
 * <p/>
 * Copyright: Copyright (c) 13-6-5 上午11:04
 * <p/>
 * Company: 北京宽连十方数字技术有限公司
 * <p/>
 * Author: nicky
 * <p/>
 * Version: 1.0
 * <p/>
 */
@Entity
@Table(name = "T_SYS_ANNOUNCEMENT")
public class SysAnnouncement extends IdEntity implements java.io.Serializable {

	public static final String DEST_BACK = "0";

	public static final String DEST_SHOP = "1";

	public static final String DEST_UC = "2";

	public static final String DEST_PORTAL = "3";

	public static final String STATUS_NEW = "0";

	public static final String STATUS_REJECT = "1";

	public static final String STATUS_PUB = "2";

	public static final String STATUS_DEL = "3";

	public static Map<String, String> DEST_MAP = new LinkedHashMap<String, String>();

	public static Map<String, String> STATUS_MAP = new LinkedHashMap<String, String>();

	static {
//		DEST_MAP.put(DEST_BACK, "管理后台");
		DEST_MAP.put(DEST_SHOP, "商户自服务");
//		DEST_MAP.put(DEST_UC, "个人中心");
		DEST_MAP.put(DEST_PORTAL, "门户");

		STATUS_MAP.put(STATUS_NEW, "新建");
		STATUS_MAP.put(STATUS_REJECT, "驳回");
		STATUS_MAP.put(STATUS_PUB, "已发布");
		STATUS_MAP.put(STATUS_DEL, "删除");
	}

	private static final long serialVersionUID = 4131015610892727665L;

	private String title;

	private String description;

	private String pubStatus;

	private Long pubUser;

	private String pubTime;

	private String pubDest;

	public Long auditUser;

	private String auditTime;

	private String endTime;
	
	private String advisePic;
	
	private String adviseExtendPic;

	@Column(name = "TITLE")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "PUB_STATUS")
	public String getPubStatus() {
		return pubStatus;
	}

	public void setPubStatus(String pubStatus) {
		this.pubStatus = pubStatus;
	}

	@Column(name = "PUB_USER")
	public Long getPubUser() {
		return pubUser;
	}

	public void setPubUser(Long pubUser) {
		this.pubUser = pubUser;
	}

	@Column(name = "PUB_TIME")
	public String getPubTime() {
		return pubTime;
	}

	public void setPubTime(String pubTime) {
		this.pubTime = pubTime;
	}

	@Column(name = "PUB_DEST")
	public String getPubDest() {
		return pubDest;
	}

	@Column(name = "AUDIT_USER")
	public Long getAuditUser() {
		return auditUser;
	}

	public void setAuditUser(Long auditUser) {
		this.auditUser = auditUser;
	}

	@Column(name = "AUDIT_TIME")
	public String getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(String auditTime) {
		this.auditTime = auditTime;
	}

	public void setPubDest(String pubDest) {
		this.pubDest = pubDest;
	}

	private String queryTimeBegin;

	private String queryTimeEnd;

	private String queryUserCode;

	@Transient
	public String getQueryTimeBegin() {
		return queryTimeBegin;
	}

	@Transient
	public void setQueryTimeBegin(String queryTimeBegin) {
		this.queryTimeBegin = queryTimeBegin;
	}

	@Transient
	public String getQueryTimeEnd() {
		return queryTimeEnd;
	}

	@Transient
	public void setQueryTimeEnd(String queryTimeEnd) {
		this.queryTimeEnd = queryTimeEnd;
	}

	@Transient
	public String getQueryUserCode() {
		return queryUserCode;
	}

	@Transient
	public void setQueryUserCode(String queryUserCode) {
		this.queryUserCode = queryUserCode;
	}

	@Column(name = "end_TIME")
	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	@Column(name = "ADVISE_PIC")
	public String getAdvisePic() {
		return advisePic;
	}

	public void setAdvisePic(String advisePic) {
		this.advisePic = advisePic;
	}
	@Column(name = "ADVISE_EXTEND_PIC")
	public String getAdviseExtendPic() {
		return adviseExtendPic;
	}

	public void setAdviseExtendPic(String adviseExtendPic) {
		this.adviseExtendPic = adviseExtendPic;
	}
	
}
