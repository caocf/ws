package com.cplatform.b2c.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cplatform.b2c.model.Survey;
import com.cplatform.b2c.service.SurveyService;
import com.cplatform.b2c.util.AppConfig;
import com.cplatform.csso.agent.http.SSOAgent;
import com.cplatform.sso.lmsh.bean.LoginUserBean;

/**
 * 调查问卷相关说明. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) July 18, 2013 1:41:21 PM
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author jicf@c-platform.com
 * @version 1.0.0
 */
@Controller
public class SurveyController {

	@Autowired
	private SurveyService surveyService;

	@Autowired
	private AppConfig appConfig;

	/**
	 * 添加问卷内容
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/saveSurvey", method = RequestMethod.POST)
	public String saveSurvey(HttpServletRequest request, HttpServletResponse response, Model model) {
		LoginUserBean userinfo = null;

		String surveyId = request.getParameter("surveyId");

		for (int i = 1; i < 5; i++) {
			String question = request.getParameter("question" + i);
			String answer = request.getParameter("answer" + i);
			Survey survey = new Survey();
			userinfo = new SSOAgent(request, response).loginUserInfo("mall");
			if (userinfo != null) {
				String userId = userinfo.getId().toString();
				survey.setUserId(Integer.parseInt(userId));
			}

			survey.setSurveyId(Integer.parseInt(surveyId));
			survey.setQuestionId(Integer.parseInt(question));
			survey.setAnswer(answer);
			surveyService.saveSurvey(survey);
		}

		model.addAttribute("webRoot", appConfig.getWebRoot());
		return "survey/end";
	}
}
