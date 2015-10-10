package com.cplatform.mall.back.item.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonProperty;

@Entity
@Table(name = "T_ITEM_GROUP_LINK")
public class ItemGroupLink {

	private Long id;

	/** 套餐ID */
	private Long itemOrgId;

	/** 已发布商品ID */
	private Long itemSaleId;

	/**
	 * 获取 id
	 * 
	 * @return id
	 */
	@SequenceGenerator(name = "seq_itemGroupInk", sequenceName = "SEQ_SYS_ITEM_GROUP_LINK_ID")
	@Id
	@GeneratedValue(generator = "seq_itemGroupInk")
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
	 * 获取 itemOrgId
	 * 
	 * @return itemOrgId
	 */
	public Long getItemOrgId() {
		return itemOrgId;
	}

	/**
	 * 设置 itemOrgId
	 * 
	 * @param itemOrgId
	 */
	public void setItemOrgId(Long itemOrgId) {
		this.itemOrgId = itemOrgId;
	}

	/**
	 * 获取 itemSaleId
	 * 
	 * @return itemSaleId
	 */
	public Long getItemSaleId() {
		return itemSaleId;
	}

	/**
	 * 设置 itemSaleId
	 * 
	 * @param itemSaleId
	 */
	public void setItemSaleId(Long itemSaleId) {
		this.itemSaleId = itemSaleId;
	}

}
