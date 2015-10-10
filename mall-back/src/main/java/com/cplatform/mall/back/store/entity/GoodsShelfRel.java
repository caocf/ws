package com.cplatform.mall.back.store.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonProperty;

@Entity
@Table(name = "t_goodshelf_goods_rel")
public class GoodsShelfRel {

	private Long id;

	@SequenceGenerator(name = "seq_item", sequenceName = "SEQ_GOODSHELF_GOOD_REL")
	@Id
	@GeneratedValue(generator = "seq_item")
	@JsonProperty
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	// 父货架ID
	private Long shelfId;

	// 商户编号
	private Long goodId;

	@Column(name = "shelf_id")
	public Long getShelfId() {
		return shelfId;
	}

	public void setShelfId(Long shelfId) {
		this.shelfId = shelfId;
	}

	@Column(name = "good_id")
	public Long getGoodId() {
		return goodId;
	}

	public void setGoodId(Long goodId) {
		this.goodId = goodId;
	}

}
