package com.cplatform.mall.back.item.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-6-4 下午12:22:56
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: wuyn@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
@Entity
@Table(name = "t_item_tag")
public class ItemTag {

	private Long id;

	private Long itemId;

	private String tag;

	/**
	 * 获取 id
	 * 
	 * @return id
	 */
	@SequenceGenerator(name = "seq_item_tag", sequenceName = "SEQ_SYS_ITEM_TAG_ID")
	@Id
	@GeneratedValue(generator = "seq_item_tag")
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
	 * 获取 tag
	 * 
	 * @return tag
	 */
	@Column(name = "TAG")
	public String getTag() {
		return tag;
	}

	/**
	 * 设置 tag
	 * 
	 * @param tag
	 */
	public void setTag(String tag) {
		this.tag = tag;
	}

}
