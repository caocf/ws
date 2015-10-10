package com.cplatform.mall.dc.web;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.cplatform.mall.dc.dao.DcUserDao;
import com.cplatform.mall.dc.entity.DcUser;
import com.cplatform.mall.dc.model.SessionUser;
import com.cplatform.mall.dc.service.CommonCacheService;
import com.cplatform.mall.dc.service.DcMenuService;
import com.cplatform.mall.dc.service.DcUserService;
import com.cplatform.mall.dc.utils.AppConfig;
import com.cplatform.mall.dc.utils.LogUtils;

/**
 * 主页控制器 <br>
 * <p>
 * Copyright: Copyright (c) 2014-1-10 下午2:13:47
 * <p>
 * Company: 苏州宽连信息技术有限公司
 * <p>
 * 
 * @author zhuangxx@c-platform.com
 * @version 1.0.0
 */
@Controller
@RequestMapping("/")
public class MainController {

	private static final Log LOGGER = LogFactory.getLog(MainController.class);

	@Autowired
	AppConfig appConfig;

	@Autowired
	DcUserDao dcUserDao;

	@Autowired
	DcUserService dcUserService;

	@Autowired
	DcMenuService menuService;

	@Autowired
	CommonCacheService commonCacheService;

	@Autowired
	LogUtils logUtils;


	@RequestMapping("home")
	public String home(Model model, HttpSession session) throws SQLException {
		SessionUser sessionUser = SessionUser.getSessionUser();
		model.addAttribute(SessionUser.SESSION_USER_KEY, sessionUser.getMenus());

		// 先默认进来跳互联网页面
		//return "redirect:/businessOverview/internet";
        return "home";
	}

	@RequestMapping("resetCache")
	@ResponseBody
	public Object resetCache() {
		commonCacheService.cacheEvict();
		return "ok";
	}

	/*
	 * 用于js脚本的全局变量
	 */
	@RequestMapping(value = "/static/global.js", produces = { "application/x-javascript", "text/javascript", "application/javascript" })
	@ResponseBody
	public String jsGlobal(WebRequest webRequest) {
		StringBuilder sb = new StringBuilder();
		sb.append("var G_CTX_ROOT = '").append(webRequest.getContextPath()).append("';\n");
		sb.append("var XH_EXT = '").append(appConfig.getXheditorExt()).append("';\n");
		sb.append("var XH_IMG_EXT = '").append(appConfig.getXheditorImageExt()).append("';\n");
		sb.append("var XH_MAXSIZE = '").append(appConfig.getXheditorMaxSize()).append("';\n");
		sb.append("var XH_DOMAIN = '").append(appConfig.getXheditorDomain()).append("';\n");
		return sb.toString();
	}

	@RequestMapping("logout")
	public String logout(HttpSession session, HttpServletResponse response) {
		session.invalidate();
		dcUserService.clearCookieToken(response);
		return "redirect:/login";
	}

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login() {
		SessionUser sessionUser = SessionUser.getSessionUser();
		if (sessionUser != null) {
			// 登录日志
			logUtils.recordLoginLog(sessionUser);
			return "redirect:/home";
		}
		return "login";
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	@ResponseBody
	public String loginPost(@RequestParam("username") String code, @RequestParam("password") String pass,
	        @RequestParam("verifyCode") String verifyCode,
	        @RequestParam(value = "savelogin", required = false, defaultValue = "false") boolean savelogin, HttpServletRequest request,
	        HttpServletResponse response, HttpSession session) throws Exception {

		try {
			if (StringUtils.isEmpty(verifyCode) || StringUtils.equals("请输入验证码", verifyCode)) {
				return "请输入验证码";
			}

			if (!verifyCode.equalsIgnoreCase((String) session.getAttribute(SessionUser.SESSION_IMAGE_CODE_KEY))) {
				return "验证码输入不正确";
			}

			DcUser user = dcUserService.checkLogin(code, pass, false);

			if (user == null) {
				return "登录失败，请确认是否输入正确的用户名和密码";
			} else {
				if (savelogin) {
					dcUserService.writeCookieToken(response, dcUserService.cryptCookieToken(user));
				} else {
					dcUserService.clearCookieToken(response);
				}

				dcUserService.writeLoginSession(request, user);

				// 登录日志
				logUtils.recordLoginLog(SessionUser.getSessionUser());
				return null;
			}
		}
		catch (Exception ex) {
			LOGGER.warn("处理登录失败。", ex);
			return "由于网络或服务器原因，导致登录失败，请稍候再试！";
		}

	}
}
