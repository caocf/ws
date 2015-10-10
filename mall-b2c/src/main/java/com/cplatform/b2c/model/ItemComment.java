package com.cplatform.b2c.model;

import java.util.HashMap;
import java.util.Map;

/**
 * 商品评论 标题、简要说明. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) May 29, 2013 3:25:40 PM
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author zhangyinf@c-platform.com
 * @version 1.0.0
 */
public class ItemComment {

	private Integer id;

	private String short_name;

	private String file_name;

	private String commentContent;

	private Integer rank;

	private String commentTime;

	private String replyContent;

	private String replyTime;

	private String nickname;

	private String question_type;

	private Integer sale_id;

	private String path;

	private Integer status;

	private static Map<String, String> questionType = new HashMap<String, String>();

	static {
		questionType.put("1", "商品咨询");
		questionType.put("2", "促销活动咨询");
		questionType.put("3", "库存及物流咨询");
		questionType.put("4", "售后咨询");
	}

	public String getQuestion_type() {
		return question_type;
	}

	public void setQuestion_type(String question_type) {
		this.question_type = question_type;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getShort_name() {
		return short_name;
	}

	public void setShort_name(String short_name) {
		this.short_name = short_name;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public String getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(String commentTime) {
		this.commentTime = commentTime;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public String getReplyTime() {
		return replyTime;
	}

	public void setReplyTime(String replyTime) {
		this.replyTime = replyTime;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getSale_id() {
		return sale_id;
	}

	public void setSale_id(Integer sale_id) {
		this.sale_id = sale_id;
	}
}
