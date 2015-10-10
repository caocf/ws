package com.cplatform.b2c.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * TChannelCatalogConf entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_SURVEY")
public class Survey {
	
	private Integer id;
	
	private Integer surveyId;
	
	private Integer questionId;
	
	private String answer;
	
	private Integer userId;


	// Property accessors
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seqSurvey")
	@SequenceGenerator(name = "seqSurvey",sequenceName = "seq_s_survey")
	@Column(name = "ID", unique = true, nullable = false, precision = 9, scale = 0)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "S_QUESTIONID", precision = 2, scale = 0 )
	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer ionIdquestionId) {
		this.questionId = ionIdquestionId;
	}

	@Column(name = "S_ANSWER", length = 500)
	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@Column(name = "USER_ID", precision = 9, scale = 0 )
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name="S_SURVEYID", precision = 2, scale = 0)
	public Integer getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(Integer surveyId) {
		this.surveyId = surveyId;
	}
	
	

}
