package com.cplatform.mall.back.store.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import com.cplatform.mall.back.entity.IdEntity;

@JsonAutoDetect
@Entity
@Table(name = "t_good_shelf")
public class GoodsShelf extends IdEntity {

	// 货架分类名称
	@JsonProperty("name")
	private String title;

	// 父货架ID
	@JsonProperty("pid")
	private Long pid;

	// 商户编号
	@JsonIgnore
	private Long shopId;

	// 排序
	private Integer orderIndex;

	@JsonProperty("isParent")
	private boolean isParent;

	@Column(name = "title")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "pid")
	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	@Column(name = "shop_id")
	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	@Column(name = "order_index")
	public Integer getOrderIndex() {
		return orderIndex;
	}

	public void setOrderIndex(Integer orderIndex) {
		this.orderIndex = orderIndex;
	}

	@Transient
	public boolean isParent() {
		return isParent;
	}

	public void setParent(boolean isParent) {
		this.isParent = isParent;
	}

}
