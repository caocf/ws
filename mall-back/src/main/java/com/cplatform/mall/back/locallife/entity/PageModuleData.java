package com.cplatform.mall.back.locallife.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.cplatform.mall.back.entity.IdEntity;


/**
 * Title. <br>
 * 模块数据信息表
 * <p>
 * Copyright: Copyright (c) 2013-6-8
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: mudeng@ca-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
@Entity
@Table(name = "T_PAGE_MODULE_DATA")
public class PageModuleData extends IdEntity implements java.io.Serializable {

	private Long id;			
	private Long moduleId;	//模块ID
	private String content;		//内容
	private Long status;		//状态 0：未审核 1：审核通过 2：审核驳回
	private Long sortNo;		//排序
	private String regionCode;		//省地市编码
	private String startTime;		//开始时间
	private String endTime;		//结束时间
	private Long templetId;	//关联数据模板
	private Long createUser;	//创建人
	private String createTime;		//创建时间
	private Long updateUser;	//修改人
	private String updateTime;		//修改时间
	private Long auditUser;	//审核人
	private String auditTime;		//审核时间
	private String auditAdvice;			//审核意见
	private String moduleCode; 
	private String contentTitle; 
	
	private String auditUserName;

	private String createUserName;
	
	private String updateUserName;
	
	private String regionName;

	private String tempName;
	
	private String moduleName;
	
	
	@Transient
	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	@Transient
	public String getAuditUserName() {
		return auditUserName;
	}

	public void setAuditUserName(String auditUserName) {
		this.auditUserName = auditUserName;
	}

	@Transient
	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	
	@Transient
	public String getTempName() {
		return tempName;
	}

	public void setTempName(String tempName) {
		this.tempName = tempName;
	}

	@Transient
	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	@Transient
	public String getUpdateUserName() {
		return updateUserName;
	}

	public void setUpdateUserName(String updateUserName) {
		this.updateUserName = updateUserName;
	}



//	@Id
//	@GeneratedValue
//	@Column(name="ID")
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}

	@Column(name="MODULE_ID")
	public Long getModuleId() {
		return moduleId;
	}

	public void setModuleId(Long moduleId) {
		this.moduleId = moduleId;
	}

	@Column(name="CONTENT")
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	

	@Column(name="STATUS")
	public Long getStatus() {
		return status;
	}


	public void setStatus(Long status) {
		this.status = status;
	}

	@Column(name="SORT_NO")
	public Long getSortNo() {
		return sortNo;
	}

	public void setSortNo(Long sortNo) {
		this.sortNo = sortNo;
	}

	@Column(name="REGION_CODE")
	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	@Column(name="START_TIME")
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	@Column(name="END_TIME")
	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	@Column(name="TEMPLET_ID")
	public Long getTempletId() {
		return templetId;
	}

	public void setTempletId(Long templetId) {
		this.templetId = templetId;
	}

	@Column(name="CREATE_USER")
	public Long getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Long createUser) {
		this.createUser = createUser;
	}

	@Column(name="CREATE_TIME")
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Column(name="UPDATE_USER")
	public Long getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(Long updateUser) {
		this.updateUser = updateUser;
	}

	@Column(name="UPDATE_TIME")
	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name="AUDIT_USER")
	public Long getAuditUser() {
		return auditUser;
	}

	public void setAuditUser(Long auditUser) {
		this.auditUser = auditUser;
	}

	@Column(name="AUDIT_TIME")
	public String getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(String auditTime) {
		this.auditTime = auditTime;
	}

	@Column(name="AUDIT_ADVICE")
	public String getAuditAdvice() {
		return auditAdvice;
	}

	public void setAuditAdvice(String auditAdvice) {
		this.auditAdvice = auditAdvice;
	}

	@Column(name="MODULE_CODE")
	public String getModuleCode() {
		return moduleCode;
	}

	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}

	@Column(name="CONTENT_TITLE")
	public String getContentTitle() {
		return contentTitle;
	}

	public void setContentTitle(String contentTitle) {
		this.contentTitle = contentTitle;
	}

	
}
