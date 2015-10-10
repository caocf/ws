package com.cplatform.b2c.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cplatform.b2c.model.Survey;
import com.cplatform.b2c.repository.TSurveyDao;

/**
 * 调查问卷相关说明. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) July 18, 2013 11:49:21 AM
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author jicf@c-platform.com
 * @version 1.0.0
 */
@Service
@Transactional
public class SurveyService {

	@Autowired
	private TSurveyDao tSurveyDao;
	
	/**
	 * 添加问卷内容
	 * @param survey
	 * @return
	 */
	@Transactional(readOnly = false)
	public void saveSurvey(Survey survey){
		tSurveyDao.saveSurvey(survey);
	}
	
}
