package com.cplatform.mall.back.item.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 已发布商品物流运费表Title. <br>
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
@Table(name = "T_ITEM_LOGISTICS_FEE")
@Entity
public class ItemLogisticsFee {

	private Long id;

	/** 发布商品ID **/
	private Long saleId;

	/** 物流渠道名称 **/
	private String qdName;

	/** 运费 **/
	private Long feeNum;

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

	@Column(name = "SALE_ID", precision = 8, scale = 0)
	public Long getSaleId() {
		return saleId;
	}

	public void setSaleId(Long saleId) {
		this.saleId = saleId;
	}

	@Column(name = "QD_NAME", length = 10)
	public String getQdName() {
		return qdName;
	}

	public void setQdName(String qdName) {
		this.qdName = qdName;
	}

	@Column(name = "FEE_NUM", precision = 8, scale = 2)
	public Long getFeeNum() {
		return feeNum;
	}

	public void setFeeNum(Long feeNum) {
		this.feeNum = feeNum;
	}

}
