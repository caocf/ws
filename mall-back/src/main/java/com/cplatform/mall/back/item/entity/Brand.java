package com.cplatform.mall.back.item.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 商品品牌表 Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-6-1 下午5:42:53
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: baibo@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
@Table(name = "T_BRAND")
@Entity
public class Brand {

	private Long id;

	/** 品牌名称 **/
	private String name;

	/** 品牌描述 **/
	private String remark;

	/** 品牌网站 **/
	private String website;

	/** 品牌图片 **/
	private String brandImg;

	/** 创建时间 **/
	private String createTime;

	/**
	 * 是否有效 0-无效 1-有效
	 **/
	private String isValid;

	/**
	 * 获取 id
	 * 
	 * @return id
	 */
	@SequenceGenerator(name = "seq_item", sequenceName = "SEQ_SYS_ITEM_ID")
	@Id
	@GeneratedValue(generator = "seq_item")
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

	@Column(name = "NAME", length = 50)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "REMARK", length = 200)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "WEBSITE", length = 100)
	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	@Column(name = "BRAND_IMG", length = 100)
	public String getBrandImg() {
		return brandImg;
	}

	public void setBrandImg(String brandImg) {
		this.brandImg = brandImg;
	}

	@Column(name = "CREATE_TIME", length = 14)
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Column(name = "IS_VALID", precision = 1, scale = 0)
	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	private String isreUpload;

	private String queryStartTime;

	private String queryEndTime;

	@Transient
	public String getIsreUpload() {
		return isreUpload;
	}

	@Transient
	public void setIsreUpload(String isreUpload) {
		this.isreUpload = isreUpload;
	}

	@Transient
	public String getQueryStartTime() {
		return queryStartTime;
	}

	@Transient
	public void setQueryStartTime(String queryStartTime) {
		this.queryStartTime = queryStartTime;
	}

	@Transient
	public String getQueryEndTime() {
		return queryEndTime;
	}

	@Transient
	public void setQueryEndTime(String queryEndTime) {
		this.queryEndTime = queryEndTime;
	}

}
