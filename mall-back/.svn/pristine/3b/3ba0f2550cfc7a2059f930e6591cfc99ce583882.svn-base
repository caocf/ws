package com.cplatform.mall.back.utils.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cplatform.mall.back.utils.Constants;

/**
 * Title.处理记录参数 <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-7-23 下午5:42:32
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: BaiJie
 * <p>
 * Version: 1.0
 * <p>
 */
public class QueryBackInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
		String url = request.getRequestURI();
		String queryString = request.getQueryString();

		if (StringUtils.isNotEmpty(queryString)) {
			// session.setAttribute(Constants.QUERY_BACK_URL,
			// "javascript:window.history.go(-1)");// 默认返回前一个页面

			// 判断 是否有记录标志，分页标志
			if (queryString.contains("queryFormBackFlag=backForm")) { // TODO:
																	  // 匹配分页暂时有问题，有弹出页面分页的问题，后续解决
				// if (queryString.contains("queryFormBackFlag=backForm") ||
				// queryString.contains("page=")) {
				session.setAttribute(Constants.QUERY_BACK_URL, url + "?" + queryString);
			}
		}
		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		HttpSession session = request.getSession();
		String url = (String) session.getAttribute(Constants.QUERY_BACK_URL);
		if (modelAndView == null) {
			modelAndView = new ModelAndView();
		}
		// Map<String, Object> model = modelAndView.getModel();
		if (StringUtils.isEmpty(url)) {
			url = "javascript:window.history.go(-1)";
			request.setAttribute("queryBackFun", url);
			// model.put("queryBackFun", url);
		} else {
			request.setAttribute("queryBackFun", "window.location=\'" + url + "\';");
			// model.put("queryBackFun", "window.location=\'" + url + "\';");
		}
		request.setAttribute(Constants.REQUEST_BACK_URL, url);
		// model.put(Constants.REQUEST_BACK_URL, url);
		super.postHandle(request, response, handler, modelAndView);
	}
}
