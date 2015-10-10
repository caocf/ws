package com.cplatform.b2c.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TItemCommentReply entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_ITEM_COMMENT_REPLY")
public class TItemCommentReply implements java.io.Serializable {

	// Fields

	/** serialVersionUID */
	private static final long serialVersionUID = 5059067102465184110L;

	private Integer id;

	private String content;

	private String updateTime;

	private String nickname;

	private Integer userId;

	private Integer commentId;

	// Constructors

	/** default constructor */
	public TItemCommentReply() {
	}

	/** minimal constructor */
	public TItemCommentReply(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public TItemCommentReply(Integer id, String content, String updateTime,
			String nickname, Integer userId, Integer commentId) {
		this.id = id;
		this.content = content;
		this.updateTime = updateTime;
		this.nickname = nickname;
		this.userId = userId;
		this.commentId = commentId;
	}

	// Property accessors
	@Id
	@Column(name = "ID", unique = true, nullable = false, precision = 8, scale = 0)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "CONTENT", length = 200)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "UPDATE_TIME", length = 14)
	public String getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "NICKNAME", length = 100)
	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Column(name = "USER_ID", precision = 8, scale = 0)
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "COMMENT_ID", precision = 8, scale = 0)
	public Integer getCommentId() {
		return this.commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

}