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
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-6-4 下午12:29:40
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: wuyn@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
@Entity
@Table(name = "t_item_property")
public class ItemProperty {

	private Long id;

	private Long itemId;

	private Long propertyId;

	private String content;

	private Long fileId;

	private String propertyName;

	private String imgPath;

	@Column(name = "IMG_PATH")
	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	/**
	 * 获取 propertyName
	 * 
	 * @return propertyName
	 */
	@Transient
	public String getPropertyName() {
		return propertyName;
	}

	/**
	 * 设置 propertyName
	 * 
	 * @param propertyName
	 */
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	/**
	 * 获取 id
	 * 
	 * @return id
	 */

	@SequenceGenerator(name = "seq_item_property", sequenceName = "SEQ_SYS_ITEM_PROPERTY_ID")
	@Id
	@GeneratedValue(generator = "seq_item_property")
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
	@Column(name = "ITEM_ID")
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
	 * 获取 propertyId
	 * 
	 * @return propertyId
	 */
	@Column(name = "PROPERTY_ID")
	public Long getPropertyId() {
		return propertyId;
	}

	/**
	 * 设置 propertyId
	 * 
	 * @param propertyId
	 */
	public void setPropertyId(Long propertyId) {
		this.propertyId = propertyId;
	}

	/**
	 * 获取 content
	 * 
	 * @return content
	 */
	@Column(name = "CONTENT")
	public String getContent() {
		return content;
	}

	/**
	 * 设置 content
	 * 
	 * @param content
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 获取 fileId
	 * 
	 * @return fileId
	 */
	@Column(name = "FILE_ID")
	public Long getFileId() {
		return fileId;
	}

	/**
	 * 设置 fileId
	 * 
	 * @param fileId
	 */
	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}
}
