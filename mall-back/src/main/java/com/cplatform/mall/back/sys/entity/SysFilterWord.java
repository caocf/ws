package com.cplatform.mall.back.sys.entity;

// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.cplatform.mall.back.entity.IdEntity;

/**
 * Title.系统过滤字表 <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-5-21 下午5:22:13
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: BaiJie
 * <p>
 * Version: 1.0
 * <p>
 */
@Entity
@Table(name = "T_SYS_FILTER_WORD")
public class SysFilterWord extends IdEntity implements java.io.Serializable {

	// Fields

	private String word;

	private Short inTag;

	private String unitId;

	private Long updateUserId;

	private String updateTime;

	// Constructors

	/** default constructor */
	public SysFilterWord() {
	}

	/** full constructor */
	public SysFilterWord(String word, Short inTag, String unitId, Long updateUserId, String updateTime) {
		this.word = word;
		this.inTag = inTag;
		this.unitId = unitId;
		this.updateUserId = updateUserId;
		this.updateTime = updateTime;
	}

	// Property accessors

	@Column(name = "WORD", length = 50)
	public String getWord() {
		return this.word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	@Column(name = "IN_TAG", precision = 3, scale = 0)
	public Short getInTag() {
		return this.inTag;
	}

	public void setInTag(Short inTag) {
		this.inTag = inTag;
	}

	@Column(name = "UNIT_ID", length = 20)
	public String getUnitId() {
		return this.unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	@Column(name = "UPDATE_USER_ID", precision = 9, scale = 0)
	public Long getUpdateUserId() {
		return this.updateUserId;
	}

	public void setUpdateUserId(Long updateUserId) {
		this.updateUserId = updateUserId;
	}

	@Column(name = "UPDATE_TIME", length = 14)
	public String getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
	private String userName;

	 @Transient
    public String getUserName() {
    	return userName;
    }

	 @Transient
    public void setUserName(String userName) {
    	this.userName = userName;
    }
	



}