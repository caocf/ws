package com.cplatform.b2c.filter;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * url过来scripte,html,style详细说明.
 * <p>
 * Copyright: Copyright (c) 2014-1-6 15:24:00
 * <p>
 * Company: 苏州宽连信息技术有限公司
 * <p>
 * 
 * @author yangxm@c-platform.com
 */
public class UrlParameterFilter implements Filter {

	/**
	 * The filter configuration object we are associated with. If this value is
	 * null, this filter instance is not currently configured.
	 */
	protected FilterConfig filterConfig = null;

	/**
	 * Should a character encoding specified by the client be ignored?
	 */
	protected boolean ignore = true;

	/**
	 * Take this filter out of service.
	 */
	@Override
	public void destroy() {
		this.filterConfig = null;
	}

	/**
	 * Select and set (if specified) the character encoding to be used to
	 * interpret request parameters for this request.
	 * 
	 * @param request
	 *            The servlet request we are processing
	 * @param response
	 *            The servlet response we are creating
	 * @param chain
	 *            The filter chain we are processing
	 * @exception IOException
	 *                if an input/output error occurs
	 * @exception ServletException
	 *                if a servlet error occurs
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// 非法脚本过滤
		request.setCharacterEncoding("UTF-8");

		HttpServletRequest hrequest = (HttpServletRequest) request;
		String uri = hrequest.getRequestURI();

		Map paraMap = request.getParameterMap();
		Iterator itr = paraMap.keySet().iterator();
		while (itr.hasNext()) {
			String key = itr.next().toString();
			if (key.contains("$") || WebStringUtils.ifContainXSS(key) || WebStringUtils.ifContainXss(key) || HTMLInputFilter.ifContainXss(key)) {
				// 请求参数中含有非法脚本，转向错误提示页面
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/error/404.jsp");
				dispatcher.forward(request, response);
				return;
			}
			// 获取参数值
			String[] values = request.getParameterValues(key);
			if (values != null) {
				for (int i = 0; i < values.length; i++) {
					if (values[i].contains("$") || WebStringUtils.ifContainXSS(values[i]) || WebStringUtils.ifContainXss(values[i])
					        || HTMLInputFilter.ifContainXss(values[i])) {
						// 请求参数中含有非法脚本，转向错误提示页面
						RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/error/404.jsp");
						dispatcher.forward(request, response);
						return;
					}
				}
			}
		}
		chain.doFilter(request, response);
	}

	/**
	 * Place this filter into service.
	 * 
	 * @param filterConfig
	 *            The filter configuration object
	 * @exception ServletException
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

		this.filterConfig = filterConfig;
		String value = filterConfig.getInitParameter("ignore");
		if (value == null) {
			this.ignore = true;
		} else if (value.equalsIgnoreCase("true")) {
			this.ignore = true;
		} else if (value.equalsIgnoreCase("yes")) {
			this.ignore = true;
		} else {
			this.ignore = false;
		}
	}

}
