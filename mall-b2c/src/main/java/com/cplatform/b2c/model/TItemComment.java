package com.cplatform.b2c.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * TItemComment entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_ITEM_COMMENT")
public class TItemComment implements java.io.Serializable {

	// Fields

	/** serialVersionUID */
	private static final long serialVersionUID = 2464345431334470309L;

	private Integer id;

	private String content;

	private Integer type;

	private Integer questionType;

	private String updateTime;

	private String nickname;

	private Integer userId;

	private Integer status;

	private Integer saleId;

	private Integer auditUser;

	private String auditTime;

	private Integer rank;

	private Integer usefulNum = 0;

	private Integer uselessNum = 0;

	private Long actOrderId;

	private long storeId;

	// Constructors

	@Column(name = "ACT_ORDER_ID")
	public Long getActOrderId() {
		return actOrderId;
	}

	public void setActOrderId(Long actOrderId) {
		this.actOrderId = actOrderId;
	}

	/** default constructor */
	public TItemComment() {
	}

	/** minimal constructor */
	public TItemComment(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public TItemComment(Integer id, String content, Integer type, Integer questionType, String updateTime, String nickname, Integer userId,
	                    Integer status, Integer saleId, Integer auditUser, String auditTime, Integer rank, Integer usefulNum, Integer uselessNum,
	                    Integer storeId) {
		this.id = id;
		this.content = content;
		this.type = type;
		this.questionType = questionType;
		this.updateTime = updateTime;
		this.nickname = nickname;
		this.userId = userId;
		this.status = status;
		this.saleId = saleId;
		this.auditUser = auditUser;
		this.auditTime = auditTime;
		this.rank = rank;
		this.usefulNum = usefulNum;
		this.uselessNum = uselessNum;
		this.storeId = storeId;
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

	@Column(name = "CONTENT")
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "TYPE")
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "QUESTION_TYPE")
	public Integer getQuestionType() {
		return this.questionType;
	}

	public void setQuestionType(Integer questionType) {
		this.questionType = questionType;
	}

	@Column(name = "UPDATE_TIME")
	public String getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "NICKNAME")
	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Column(name = "USER_ID")
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "STATUS")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "SALE_ID")
	public Integer getSaleId() {
		return this.saleId;
	}

	public void setSaleId(Integer saleId) {
		this.saleId = saleId;
	}

	@Column(name = "AUDIT_USER")
	public Integer getAuditUser() {
		return this.auditUser;
	}

	public void setAuditUser(Integer auditUser) {
		this.auditUser = auditUser;
	}

	@Column(name = "AUDIT_TIME")
	public String getAuditTime() {
		return this.auditTime;
	}

	public void setAuditTime(String auditTime) {
		this.auditTime = auditTime;
	}

	@Column(name = "RANK")
	public Integer getRank() {
		return this.rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	@Column(name = "USEFUL_NUM")
	public Integer getUsefulNum() {
		return this.usefulNum;
	}

	public void setUsefulNum(Integer usefulNum) {
		this.usefulNum = usefulNum;
	}

	@Column(name = "USELESS_NUM")
	public Integer getUselessNum() {
		return this.uselessNum;
	}

	public void setUselessNum(Integer uselessNum) {
		this.uselessNum = uselessNum;
	}

	@Column(name = "STORE_ID")
	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

}