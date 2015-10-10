package com.cplatform.b2c.repository;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cplatform.b2c.model.PageInfo;
import com.cplatform.b2c.model.Survey;

/**
 * 调查问卷相关说明. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) July 18, 2013 11:59:21 AM
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author jicf@c-platform.com
 * @version 1.0.0
 */
@Repository
public class TSurveyDao {

	

	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * 添加问卷内容
	 * 
	 * @param survey
	 * @return
	 */
	public void saveSurvey(Survey survey) {
		try {
			sessionFactory.getCurrentSession().save(survey);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
