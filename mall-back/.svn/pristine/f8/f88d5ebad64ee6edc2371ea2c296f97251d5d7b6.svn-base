package com.cplatform.mall.back.utils.interceptor;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jasig.cas.client.util.AssertionHolder;
import org.jasig.cas.client.validation.Assertion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cplatform.mall.back.model.SessionUser;
import com.cplatform.mall.back.service.MenuService;
import com.cplatform.mall.back.sys.dao.SysUnitDao;
import com.cplatform.mall.back.sys.dao.UserDao;
import com.cplatform.mall.back.sys.entity.SysUser;
import com.cplatform.mall.back.sys.service.UserService;

/**
 * Title. <br>
 * Description.
 * <p/>
 * Copyright: Copyright (c) 13-5-29 下午2:52
 * <p/>
 * Company: 北京宽连十方数字技术有限公司
 * <p/>
 * Author: nicky
 * <p/>
 * Version: 1.0
 * <p/>
 */
public class RoleGenInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	UserDao userDao;

	@Autowired
	MenuService menuService;

	@Autowired
	SysUnitDao sysUnitDao;

	@Autowired
	UserService userService;

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

		HttpSession session = request.getSession();

		final Assertion assertion = AssertionHolder.getAssertion();

		// cas 已验证， 但是本地没有扩展session信息
		if (assertion != null && session.getAttribute(SessionUser.SESSION_USER_KEY) == null) {

			String userCode = assertion.getPrincipal().getName();

			genLocalSession(session, userCode);
		}

		return super.preHandle(request, response, handler);
	}

	private void genLocalSession(HttpSession session, String userCode) throws SQLException {

		SysUser user = userDao.findByUserCode(userCode);

		SessionUser sessionUser = new SessionUser();
		sessionUser.setPrivilege(menuService.loadUserPrivilege(user.getId()));
		sessionUser.setId(user.getId());
		sessionUser.setEmail(user.getEmail());
		sessionUser.setMobile(user.getTerminalId());
		sessionUser.setName(user.getUserName());
		sessionUser.setStatus(user.getStatus());
		sessionUser.setUserType(user.getFlag().intValue());
		sessionUser.setUnitId(user.getUnitId());
		sessionUser.setSysUser(user);
		sessionUser.setSysUnit(sysUnitDao.findOne(user.getUnitId()));

		// 如果是省单位或者地市单位，获得该单位的子单位
		sessionUser.setChidrenUnitList(sysUnitDao.findByParentUnitId(user.getUnitId()));
		// 获得管理的区域
		sessionUser.setAreaList(userService.getUserRegon(user.getId()));

		session.setAttribute(SessionUser.SESSION_USER_KEY, sessionUser);
	}

}