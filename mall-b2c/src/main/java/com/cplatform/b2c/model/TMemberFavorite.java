package com.cplatform.b2c.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * TMemberFavorite entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_MEMBER_FAVORITE")
public class TMemberFavorite implements java.io.Serializable {

	// Fields

	/** serialVersionUID */
	private static final long serialVersionUID = 2464345431334470309L;

	private Integer id;

	private Integer favoriteId;

	private Integer favoriteType;

	private Integer userId;

	private String updateTime;

	// Constructors

	/** default constructor */
	public TMemberFavorite() {
	}

	/** minimal constructor */
	public TMemberFavorite(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public TMemberFavorite(Integer id, Integer favoriteType, String updateTime, Integer userId, Integer favoriteId) {
		this.id = id;
		this.updateTime = updateTime;
		this.userId = userId;
		this.favoriteId = favoriteId;
		this.favoriteType = favoriteType;
	}

	// Property accessors
	@SequenceGenerator(name = "seq_comm", sequenceName = "SEQ_SYS_COMM_ID")
	@Id
	@GeneratedValue(generator = "seq_comm")
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "UPDATE_TIME", length = 14)
	public String getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "USER_ID", precision = 8, scale = 0)
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "FAVORITE_ID")
	public Integer getFavoriteId() {
		return favoriteId;
	}

	public void setFavoriteId(Integer favoriteId) {
		this.favoriteId = favoriteId;
	}

	@Column(name = "FAVORITE_TYPE")
	public Integer getFavoriteType() {
		return favoriteType;
	}

	public void setFavoriteType(Integer favoriteType) {
		this.favoriteType = favoriteType;
	}

}