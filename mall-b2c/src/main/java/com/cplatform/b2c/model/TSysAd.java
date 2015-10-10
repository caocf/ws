package com.cplatform.b2c.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 广告表信息
 * <p>
 * Copyright: Copyright (c) Jun 26, 2013 9:25:06 AM
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * @author yangxm@c-platform.com
 * @version 1.0.0
 */
@Entity
@Table(name = "T_SYS_AD", schema = "LIFE")
public class TSysAd{
	// Fields
	/** 广告id */
	private Integer id;
	/** 广告名称 */
	private String adName;
	/** 广告类型 */
	private Integer adType;
	/** 广告类别 */
	private Integer adFlag;
	/** 广告链接 */
	private String link;
	/** 广告负责人 */
	private String linkMan;
	/** 广告内容 */
	private String content;
	/** 广告状态 */
	private Integer status;
	/** 开始时间 */
	private String startTime;
	/** 有效期*/
	private String validTime;
	/** 点击次数 */
	private Integer clickCnt;
	/** 展现次数 */
	private Integer viewCnt;
	/** 创建日期 */
	private String createTime;
	/** 创建人 */
	private Integer createUser;
	/** 广告清单 */
	private TSysAdPosition position;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.EAGER)
	@JoinColumn(name = "POSITION_ID")
	public TSysAdPosition getPosition() {
		return position;
	}

	public void setPosition(TSysAdPosition position) {
		this.position = position;
	}
	//默认构造函数
	public TSysAd(){
		
	}
	//全部参数构造函数
	public TSysAd(Integer id, String adName, Integer adType, Integer adFlag,
			String link, String linkMan, String content, Integer status,
			String startTime, String validTime, Integer clickCnt,
			Integer viewCnt, String createTime, Integer createUser) {
		super();
		this.id = id;
		this.adName = adName;
		this.adType = adType;
		this.adFlag = adFlag;
		this.link = link;
		this.linkMan = linkMan;
		this.content = content;
		this.status = status;
		this.startTime = startTime;
		this.validTime = validTime;
		this.clickCnt = clickCnt;
		this.viewCnt = viewCnt;
		this.createTime = createTime;
		this.createUser = createUser;
	}

	/**
	 * 设置id
	 * @return
	 */
	@Id
	@Column(name = "ID")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * 设置adName
	 * @return
	 */
	@Column(name = "AD_NAME")
	public String getAdName() {
		return adName;
	}

	public void setAdName(String adName) {
		this.adName = adName;
	}
	
	/**
	 * 设置adType
	 * @return
	 */
	@Column(name = "AD_TYPE")
	public Integer getAdType() {
		return adType;
	}

	public void setAdType(Integer adType) {
		this.adType = adType;
	}

	/**
	 * 设置adFlag
	 * @return
	 */
	@Column(name = "AD_FLAG")
	public Integer getAdFlag() {
		return adFlag;
	}

	public void setAdFlag(Integer adFlag) {
		this.adFlag = adFlag;
	}

	/**
	 * 设置link
	 * @return
	 */
	@Column(name = "LINK")
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	/**
	 * 设置linkMan
	 * @return
	 */
	@Column(name = "LINKMAN")
	public String getLinkMan() {
		return linkMan;
	}

	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}

	/**
	 * 设置content
	 * @return
	 */
	@Column(name = "CONTENT")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 设置status
	 * @return
	 */
	@Column(name = "STATUS")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * 设置startTime
	 * @return
	 */
	@Column(name = "START_TIME")
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	
	/**
	 * 设置validTime
	 * @return
	 */
	@Column(name = "VALID_TIME")
	public String getValidTime() {
		return validTime;
	}

	public void setValidTime(String validTime) {
		this.validTime = validTime;
	}

	/**
	 * 设置clickCnt
	 * @return
	 */
	@Column(name = "CLICK_CNT")
	public Integer getClickCnt() {
		return clickCnt;
	}

	public void setClickCnt(Integer clickCnt) {
		this.clickCnt = clickCnt;
	}

	/**
	 * 设置viewCnt
	 * @return
	 */
	@Column(name = "VIEW_CNT")
	public Integer getViewCnt() {
		return viewCnt;
	}

	public void setViewCnt(Integer viewCnt) {
		this.viewCnt = viewCnt;
	}

	/**
	 * 设置createTime
	 * @return
	 */
	@Column(name = "CREATE_TIME")
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	/**
	 * 设置createUser
	 * @return
	 */
	@Column(name = "CREATE_USER")
	public Integer getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}

	
}
