package com.cplatform.b2c.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 商品属性实体. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2014-1-15 下午2:48:06
 * <p>
 * Company: 苏州宽连信息技术有限公司
 * <p>
 * 
 * @author zhangdong-ca@c-platform.com
 * @version 1.0.0
 */
@Entity
@Table(name = "T_ITEM_PARAM")
public class TItemParam implements Serializable {

	private static final long serialVersionUID = 7914692986852560477L;

	private Long id;

	private Long typeId; // 分类编号

	private Long itemId; // 商品编号

	private Long paramId; // 规格参数

	private String paramKey; // 规格参数名

	private String paramValue; // 规格参数值

	private Integer rank; // 排序参数

	private String label; // 自定义面显示内容

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "gen")
	@SequenceGenerator(name = "gen", sequenceName = "SEQ_ITEM_PARAM_RED")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "TYPE_ID")
	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	@Column(name = "ITEM_ID")
	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	@Column(name = "PARAM_ID")
	public Long getParamId() {
		return paramId;
	}

	public void setParamId(Long paramId) {
		this.paramId = paramId;
	}

	@Column(name = "PARAM_KEY")
	public String getParamKey() {
		return paramKey;
	}

	public void setParamKey(String paramKey) {
		this.paramKey = paramKey;
	}

	@Column(name = "PARAM_VALUE")
	public String getParamValue() {
		return paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
