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
 * Copyright: Copyright (c) 2013-6-8 下午4:00:10
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: wuyn@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */

@Entity
@Table(name = "T_ITEM_PARAM")
public class ItemParam {

	private Long id;

	private Long typeId;

	private Long itemId;

	private Long paramId;

	private String paramKey;

	private String paramValue;

	private Integer rank;

	public ItemParam() {

	}

	public ItemParam(Long typeId, Long itemId, Long paramId, String paramKey, String paramValue, Integer rank) {
		this.typeId = typeId;
		this.itemId = itemId;
		this.paramId = paramId;
		this.paramKey = paramKey;
		this.paramValue = paramValue;
		this.rank = rank;
	}

	/**
	 * 获取 id
	 * 
	 * @return id
	 */
	@SequenceGenerator(name = "seq_item_param", sequenceName = "SEQ_SYS_ITEM_PARAM_ID")
	@Id
	@GeneratedValue(generator = "seq_item_param")
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
	 * 获取 typeId
	 * 
	 * @return typeId
	 */
	@Column(name = "TYPE_ID")
	public Long getTypeId() {
		return typeId;
	}

	/**
	 * 设置 typeId
	 * 
	 * @param typeId
	 */
	public void setTypeId(Long typeId) {
		this.typeId = typeId;
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
	 * 获取 paramId
	 * 
	 * @return paramId
	 */
	@Column(name = "PARAM_ID")
	public Long getParamId() {
		return paramId;
	}

	/**
	 * 设置 paramId
	 * 
	 * @param paramId
	 */
	public void setParamId(Long paramId) {
		this.paramId = paramId;
	}

	/**
	 * 获取 paramKey
	 * 
	 * @return paramKey
	 */
	@Column(name = "PARAM_KEY")
	public String getParamKey() {
		return paramKey;
	}

	/**
	 * 设置 paramKey
	 * 
	 * @param paramKey
	 */
	public void setParamKey(String paramKey) {
		this.paramKey = paramKey;
	}

	/**
	 * 获取 paramValue
	 * 
	 * @return paramValue
	 */
	@Column(name = "PARAM_VALUE")
	public String getParamValue() {
		return paramValue;
	}

	/**
	 * 设置 paramValue
	 * 
	 * @param paramValue
	 */
	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

	/**
	 * 获取 rank
	 * 
	 * @return rank
	 */
	@Column(name = "RANK")
	public Integer getRank() {
		return rank;
	}

	/**
	 * 设置 rank
	 * 
	 * @param rank
	 */
	public void setRank(Integer rank) {
		this.rank = rank;
	}

}
