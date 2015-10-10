package com.cplatform.mall.dc.interceptors;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cplatform.mall.dc.utils.AppConfig;

/**
 * 提供可以不处理指定路径的过滤器
 * <p/>
 * Copyright: Copyright (c) 13-5-30 下午5:06
 * <p/>
 * Company: 苏州宽连信息技术有限公司
 * <p/>
 * Author: chengyao
 * <p/>
 * Version: 1.0
 * <p/>
 */
public abstract class AbstractExcludePathInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	AppConfig appConfig;

	/**
	 * 不做权限验证的地址列表。可以使前缀路径或是具体的url
	 */
	private List<String> ignorePaths;

	/**
	 * 信任的客户端访问地址
	 */
	private List<String> trustRemoteAddrs;

	/**
	 * 接口地址
	 */
	private List<String> interfacePaths;

	public List<String> getInterfacePaths() {
		return interfacePaths;
	}

	public void setInterfacePaths(List<String> interfacePaths) {
		this.interfacePaths = interfacePaths;
	}

	public List<String> getTrustRemoteAddrs() {
		return trustRemoteAddrs;
	}

	public void setTrustRemoteAddrs(List<String> trustRemoteAddrs) {
		this.trustRemoteAddrs = trustRemoteAddrs;
	}

	public List<String> getIgnorePaths() {
		return ignorePaths;
	}

	public void setIgnorePaths(List<String> ignorePaths) {
		this.ignorePaths = ignorePaths;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		// 属于忽略地址
		if (match(request)) {
			return super.preHandle(request, response, handler);
		}

		// 属于接口地址
		if (matchInterfacePath(request)) {
			// 属于可信任客户端地址
			if (matchTrustClientAddr(request)) {
				return super.preHandle(request, response, handler);
			} else {
				return false;
			}
		}

		if (handleInternal(request, response, handler)) {
			return super.preHandle(request, response, handler);
		} else {
			return false;
		}
	}

	protected boolean handleInternal(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		return true;
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
				// System.out.println("true:" + ignorePath + ":" + url);
				return true;
			}
		}
		// System.out.println("false:" + url);
		return false;
	}

	/**
	 * 是否匹配接口地址
	 * 
	 * @param req
	 * @return
	 */
	private boolean matchInterfacePath(HttpServletRequest req) {
		if (interfacePaths == null) {
			return false;
		}

		String url = req.getServletPath();
		PathMatcher matcher = new AntPathMatcher();
		for (String interfacePath : interfacePaths) {
			if (matcher.match(interfacePath, url)) {
				//System.out.println("接口地址 true:" + interfacePath + ":" + url);
				return true;
			}
		}
		//System.out.println("接口地址 false:" + url);
		return false;
	}

	/**
	 * 是否匹配信任客户端地址
	 * 
	 * @param req
	 * @return
	 */
	private boolean matchTrustClientAddr(HttpServletRequest req) {
		if (trustRemoteAddrs == null) {
			return false;
		}

		String clientIPAddr = findClientIPAddr(req);
		PathMatcher matcher = new AntPathMatcher();
		for (String trustRemoteAddr : trustRemoteAddrs) {
			if (matcher.match(trustRemoteAddr, clientIPAddr)) {
				//System.out.println("信任客户端地址 true:" + trustRemoteAddr + ":" + clientIPAddr);
				return true;
			}
		}
		//System.out.println("信任客户端地址 false" + clientIPAddr);
		return false;
	}

	/**
	 * 获得客户端IP
	 * 
	 * @param request
	 * @return
	 */
	private String findClientIPAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
}
