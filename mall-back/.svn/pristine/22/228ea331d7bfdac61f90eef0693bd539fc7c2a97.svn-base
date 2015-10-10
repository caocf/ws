package com.cplatform.mall.back.comment.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Title.商品评论表<br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-6-1 下午4:26:44
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: BaiJie
 * <p>
 * Version: 1.0
 * <p>
 */
@Table(name = "t_item_comment")
@Entity
public class ItemComment implements Serializable {

	private Long id;

	private String content;

	private Integer type;

	private Integer questionType;

	private String updateTime;

	private String nickname;

	private String userId;

	private Integer status;

	private Long saleId;

	private Long auditUser;

	private String auditTime;

	private Integer rank;

	private Long usefulNum;

	private Long uselessNum;

	private String replyNickName;

	private String replyUpdateTime;

	private String replyContent;

	@Transient
	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	@Transient
	public String getReplyNickName() {
		return replyNickName;
	}

	public void setReplyNickName(String replyNickName) {
		this.replyNickName = replyNickName;
	}

	@Transient
	public String getReplyUpdateTime() {
		return replyUpdateTime;
	}

	public void setReplyUpdateTime(String replyUpdateTime) {
		this.replyUpdateTime = replyUpdateTime;
	}

	// --BS METHOD----

	public static Map<Integer, String> statusMap = null;
	static {
		statusMap = new HashMap<Integer, String>();
		statusMap.put(0, "未审核");
		statusMap.put(1, "审核通过");
		statusMap.put(2, "审核驳回");
	}

	@Transient
	public String getStatusName() {
		return statusMap.get(this.status);
	}

	/**
	 * 商品评论类别
	 */
	public static Map<Integer, String> commentTypeMap = null;
	static {
		commentTypeMap = new HashMap<Integer, String>();
		commentTypeMap.put(1, "评论");
		commentTypeMap.put(2, "咨询");
	}

	/**
	 * 获得评论名称
	 * 
	 * @return
	 */
	@Transient
	public String getTypeName() {
		return commentTypeMap.get(this.getType());
	}

	public static Map<Integer, String> questionTypeMap = null;
	static {
		questionTypeMap = new HashMap<Integer, String>();
		questionTypeMap.put(0, "其他咨询");
		questionTypeMap.put(1, "商品咨询");
		questionTypeMap.put(2, "活动咨询");
		questionTypeMap.put(3, "库存及物流咨询");
		questionTypeMap.put(4, "售后咨询");
	}

	/**
	 * 获得咨询分类名称
	 * 
	 * @return
	 */
	@Transient
	public String getQuestionTypeName() {
		return questionTypeMap.get(this.getQuestionType());
	}

	/***
	 * 查询使用
	 */
	private String startTime;

	private String endTime;

	/** 商品名称 */
	private String name;

	@Transient
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	@Transient
	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	@Transient
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "CONTENT")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "TYPE")
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "QUESTION_TYPE")
	public Integer getQuestionType() {
		return questionType;
	}

	public void setQuestionType(Integer questionType) {
		this.questionType = questionType;
	}

	@Column(name = "UPDATE_TIME")
	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "NICKNAME")
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Column(name = "USER_ID")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "STATUS")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "SALE_ID")
	public Long getSaleId() {
		return saleId;
	}

	public void setSaleId(Long saleId) {
		this.saleId = saleId;
	}

	@Column(name = "AUDIT_USER")
	public Long getAuditUser() {
		return auditUser;
	}

	public void setAuditUser(Long auditUser) {
		this.auditUser = auditUser;
	}

	@Column(name = "AUDIT_TIME")
	public String getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(String auditTime) {
		this.auditTime = auditTime;
	}

	@Column(name = "RANK")
	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	@Column(name = "USEFUL_NUM")
	public Long getUsefulNum() {
		return usefulNum;
	}

	public void setUsefulNum(Long usefulNum) {
		this.usefulNum = usefulNum;
	}

	@Column(name = "USELESS_NUM")
	public Long getUselessNum() {
		return uselessNum;
	}

	public void setUselessNum(Long uselessNum) {
		this.uselessNum = uselessNum;
	}

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

}
