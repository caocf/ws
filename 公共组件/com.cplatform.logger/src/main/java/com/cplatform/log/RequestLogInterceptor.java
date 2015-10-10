package com.cplatform.log;

import java.util.Collection;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 请求拦截器.用于记录请求方地址.
 * <p>
 * Copyright: Copyright (c) 2013-1-8 上午11:21:27
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author chengfan@c-platform.com
 * @version 1.0.0
 */
public class RequestLogInterceptor implements HandlerInterceptor {

	private Logger requestInfoLogger = Logger.getLogger("com.cplatform.log.RequestLog.request.info");

	private Logger responseInfoLogger = Logger.getLogger("com.cplatform.log.RequestLog.response.info");

	private ThreadLocal<Long> timeMakrs = new ThreadLocal<Long>();

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		LogManager.exists("");
		try {
			double timeCost = -1;
			Long tm0 = timeMakrs.get();
			if (tm0 != null) {
				timeCost = System.nanoTime() - tm0;
				timeCost = timeCost / 1000000;
			}
			if (responseInfoLogger.isTraceEnabled()) {
				StringBuilder buf = new StringBuilder();
				buf.append("Http Response：").append(response.getStatus());
				buf.append(", ").append(String.format("%.2f", timeCost)).append("ms");
				responseInfoLogger.trace(buf.toString());
			}
			Logger logger = getResponseHeaderLogger();
			if (logger != null && logger.isTraceEnabled()) {
				Collection<String> names = response.getHeaderNames();
				for (String name : names) {
					String value = response.getHeader(name);
					logger.trace(name + ": " + value);
				}
			}
		}
		catch (Exception e) {
			responseInfoLogger.error(e, e);
		}
	}

	/**
	 * 获取 requestHeaderLogger
	 * 
	 * @return requestHeaderLogger
	 */
	private Logger getRequestHeaderLogger() {
		Logger logger = LogManager.exists("com.cplatform.log.RequestLog.request.header");
		return logger;
	}

	/**
	 * 获取 responseHeaderLogger
	 * 
	 * @return responseHeaderLogger
	 */
	private Logger getResponseHeaderLogger() {
		Logger logger = LogManager.exists("com.cplatform.log.RequestLog.response.header");
		return logger;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		try {
			//
			timeMakrs.set(System.nanoTime());
			//
			if (requestInfoLogger.isTraceEnabled()) {
				//
				String host = request.getRemoteAddr();
				if (request.getHeader("X-Real-IP") != null) {
					host = host + "/" + request.getHeader("X-Real-IP");
				}
				StringBuilder buf = new StringBuilder();
				buf.append("Receive Http Request：");
				buf.append(host).append(", ").append(request.getRequestURI());
				String params = request.getQueryString();// paramsToString(request);
				if (StringUtils.isBlank(params) == false) {
					buf.append(", ").append(params);
				}
				String pid = request.getHeader("se-pid");
				if (StringUtils.isBlank(pid) == false) {
					buf.append(", pid=").append(pid);
				}
				String pinfo = request.getHeader("se-pinfo");
				if (StringUtils.isBlank(pid) == false) {
					buf.append(", pinfo=").append(pinfo);
				}
				requestInfoLogger.trace(buf.toString());
			}
			//
			Logger logger = getRequestHeaderLogger();
			if (logger != null && logger.isTraceEnabled()) {
				Enumeration<String> names = request.getHeaderNames();
				while (names.hasMoreElements()) {
					String name = names.nextElement();
					String value = request.getHeader(name);
					logger.trace(name + ": " + value);
				}
			}
		}
		catch (Exception e) {
			requestInfoLogger.error(e, e);
		}
		return true;
	}

}
