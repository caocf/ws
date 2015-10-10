package com.cplatform.tag;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.cplatform.mall.dc.entity.DcMenu;
import com.cplatform.mall.dc.service.DcMenuService;

/**
 * Title. <br>
 * Description.
 * <p/>
 * Copyright: Copyright (c) 12-11-12 下午2:15
 * <p/>
 * Company: 苏州宽连信息技术有限公司
 * <p/>
 * Author: chengyao
 * <p/>
 * Version: 1.0
 * <p/>
 */
public class UtilsFunction {

	public static Object getSpringBean(String name) {
		HttpServletRequest curRequest = ((ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes()).getRequest();
		ServletContext servletContext = curRequest.getSession()
				.getServletContext();
		return WebApplicationContextUtils.getWebApplicationContext(
				servletContext).getBean(name);
	}

	public static Object getPathMenus() {
		HttpServletRequest curRequest = ((ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes()).getRequest();
		ServletContext servletContext = curRequest.getSession()
				.getServletContext();
		String url = (String) curRequest
				.getAttribute("javax.servlet.forward.servlet_path");
		// url = "/shop-set/zhaopai";
		if (url == null) {
			url = curRequest.getServletPath();
		}
		DcMenuService menuService = WebApplicationContextUtils
				.getWebApplicationContext(servletContext).getBean(
						DcMenuService.class);
		List<DcMenu> menus = menuService.getPathMenus(url, 1);
		return menus;
	}

}
