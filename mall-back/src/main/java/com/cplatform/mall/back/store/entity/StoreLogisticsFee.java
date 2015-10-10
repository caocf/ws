package com.cplatform.mall.back.store.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 物流运费 Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-6-6 下午1:56:29
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: LiuDong@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
@Entity
@Table(name = "T_STORE_LOGISTICS_FEE")
public class StoreLogisticsFee {

	private Long id;

	private Long qdId;

	private Float feeNum;

	private Long storeId;
	
	private String name;

	@Transient
    public String getName() {
    	return name;
    }

	
    public void setName(String name) {
    	this.name = name;
    }

	@SequenceGenerator(name = "seq_store", sequenceName = "SEQ_STORE")
	@Id
	@GeneratedValue(generator = "seq_store")
	@JsonProperty
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "STORE_ID")
	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	@Column(name = "QD_ID")
	public Long getQdId() {
		return qdId;
	}

	public void setQdId(Long qdId) {
		this.qdId = qdId;
	}

	@Column(name = "FEE_NUM", precision = 11, scale = 2)
	public Float getFeeNum() {
		return feeNum;
	}

	public void setFeeNum(Float feeNum) {
		this.feeNum = feeNum;
	}

}
