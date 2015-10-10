package com.cplatform.b2c.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.cplatform.b2c.constant.AreaCode;
import com.cplatform.b2c.model.SessionUser;
import com.cplatform.b2c.service.MemberService;
import com.cplatform.b2c.util.AppConfig;
import com.cplatform.b2c.util.CookieUtils;
import com.cplatform.csso.agent.http.SSOAgent;

/**
 * 判断用户登录自动跳转. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2013-6-21 下午03:55:58
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author zouyl@c-platform.com
 * @version 1.0.0
 */
public class IsLoginFilter implements Filter {

	private static Log log = LogFactory.getLog(IsLoginFilter.class);

	AppConfig appConfig;

	MemberService memberService;

	private String loginUrl;

	/** 日志记录器 */
	private final Logger logger = Logger.getLogger(getClass());

	/**
	 * 不做权限验证的地址列表。可以使前缀路径或是具体的url
	 */
	public List<String> ignorePaths = new ArrayList<String>();

	@Override
	public void destroy() {

	}

	/**
	 * 判断用户是否登陆,并跳转至相应页面
	 */
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException,
	        ServletException {
		HttpServletRequest req = (HttpServletRequest) servletRequest;
		HttpServletResponse res = (HttpServletResponse) servletResponse;
		Cookie areaCodeCookie = CookieUtils.getCookie((HttpServletRequest) servletRequest, "areaCode");
		String areaCode = "";
		if (areaCodeCookie != null) {
			areaCode = areaCodeCookie.getValue();
		}
		if (areaCode != null && !"".equals(areaCode) && AreaCode.heNanAreaCodeSet.contains(areaCode)) {
			loginUrl = appConfig.getB2c_Index_HN_Login_Url();
		} else {
			loginUrl = appConfig.getB2c_Index_Login_Url();
		}

		// 判断用户是否登录
		try {
			// 过滤不需要该过滤器过滤的url
			if (!match(req)) {
				filterChain.doFilter(req, res);
			} else {
				Object userinfo = null;
				if (appConfig.isTest()) {
					userinfo = SessionUser.getSessionUser(res);
				} else {
					userinfo = new SSOAgent(req, res).loginUserInfo("mall");
				}
				if (userinfo != null) {
					filterChain.doFilter(req, res);
				} else {
					if (isAjaxRequest(req)) {
						Map<String, String> retMap = new HashMap<String, String>();
						retMap.put("isLogin", "0");
						retMap.put("msg", "需要登录");
						retMap.put("url", getRedirectUrl(req, res));
						JSONObject result = JSONObject.fromObject(retMap);
						res.setCharacterEncoding("utf-8");
						PrintWriter out = res.getWriter();
						out.print(result);
						out.flush();
						out.close();
					} else {
						res.sendRedirect(getRedirectUrl(req, res));
					}
				}
			}
		}
		catch (Exception e) {
			log.error(e);
			filterChain.doFilter(req, res);
		}
	}

	/**
	 * 判断是否是ajax请求
	 * 
	 * @param req
	 * @return
	 */
	public boolean isAjaxRequest(ServletRequest req) {
		String requestType = ((HttpServletRequest) req).getHeader("X-Requested-With");
		if ("XMLHttpRequest".equalsIgnoreCase(requestType)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * request path 是否匹配忽略地址
	 * 
	 * @param req
	 * @return
	 */
	private boolean match(HttpServletRequest req) {
		if (ignorePaths == null) {
			return false;
		}

		String url = req.getServletPath();
		PathMatcher matcher = new AntPathMatcher();
		for (String ignorePath : ignorePaths) {
			if (matcher.match(ignorePath, url)) {
				return true;
			}
		}
		return false;
	}

	private String getRedirectUrl(HttpServletRequest request, HttpServletResponse response) throws MalformedURLException {
		String basePath = appConfig.getServer_host();
		// String contextPath =
		// request.getSession().getServletContext().getContextPath();
		// String loginUrl = appConfig.getB2c_Index_Login_Url();
		String referer = request.getHeader("Referer");
		StringBuilder buffer = new StringBuilder();
		buffer.append(loginUrl);
		buffer.append(basePath);
		if (isAjaxRequest(request) || "POST".equals(request.getMethod())) {
			if (referer != null) {
				buffer.append(buildServiceUrl(referer, response, true));
			}
			return buffer.toString();
		} else {
			buffer.append(buildServiceUrl(request, response, true));
		}
		return buffer.toString();
	}

	private String buildServiceUrl(String url, HttpServletResponse response, boolean encode) throws MalformedURLException {
		StringBuilder buffer = new StringBuilder();
		URL u = new URL(url);
		buffer.append(u.getPath());
		if (StringUtils.hasText(u.getQuery())) {
			buffer.append("?");
			buffer.append(u.getQuery());
		}
		final String returnValue = encode ? response.encodeURL(buffer.toString()) : buffer.toString();
		return returnValue;
	}

	private String buildServiceUrl(HttpServletRequest request, HttpServletResponse response, boolean encode) {
		StringBuilder buffer = new StringBuilder();
		buffer.append(request.getRequestURI());
		if (StringUtils.hasText(request.getQueryString())) {
			buffer.append("?");
			buffer.append(request.getQueryString());
		}
		final String returnValue = encode ? response.encodeURL(buffer.toString()) : buffer.toString();
		return returnValue;
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(filterConfig.getServletContext());
		appConfig = (AppConfig) ac.getBean("appConfig");
		memberService = (MemberService) ac.getBean("memberService");
		ignorePaths = Arrays.asList(appConfig.getB2c_Filter_Login_Path().split(","));
	}

}
