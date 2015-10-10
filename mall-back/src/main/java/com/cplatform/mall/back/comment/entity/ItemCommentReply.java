package com.cplatform.mall.back.comment.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonProperty;
/**
 * 商品回复
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-6-3 下午7:04:19
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: LiuDong@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
@Table(name = "T_ITEM_COMMENT_REPLY")
@Entity
public class ItemCommentReply implements Serializable {

	private Long id;

	@SequenceGenerator(name = "seq_item_COMMENT", sequenceName = "SEQ_ITEM_COMMENT")
	@Id
	@GeneratedValue(generator = "seq_item_COMMENT")
	@JsonProperty
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	private String content;

	@Column(name = "CONTENT")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "UPDATE_TIME")
	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "NICKNAME")
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	@Column(name = "USER_ID")
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "COMMENT_ID")
	public Long getCommentId() {
		return commentId;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

	private String updateTime;

	private String nickName;

	private Long userId;

	private Long commentId;

}
