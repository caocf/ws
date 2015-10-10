package com.cplatform.b2c.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TGoodshelfGoodsRel entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_GOODSHELF_GOODS_REL")
public class TGoodshelfGoodsRel implements java.io.Serializable {

	// Fields

	/** serialVersionUID */
    private static final long serialVersionUID = 8183570776832233231L;

	private Integer id;

	private Integer shelfId;

	private Integer goodId;

	// Constructors

	/** default constructor */
	public TGoodshelfGoodsRel() {
	}

	/** minimal constructor */
	public TGoodshelfGoodsRel(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public TGoodshelfGoodsRel(Integer id, Integer shelfId, Integer goodId) {
		this.id = id;
		this.shelfId = shelfId;
		this.goodId = goodId;
	}

	// Property accessors
	@Id
	@Column(name = "ID", unique = true, nullable = false, precision = 9, scale = 0)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "SHELF_ID", precision = 9, scale = 0)
	public Integer getShelfId() {
		return this.shelfId;
	}

	public void setShelfId(Integer shelfId) {
		this.shelfId = shelfId;
	}

	@Column(name = "GOOD_ID", precision = 9, scale = 0)
	public Integer getGoodId() {
		return this.goodId;
	}

	public void setGoodId(Integer goodId) {
		this.goodId = goodId;
	}

}