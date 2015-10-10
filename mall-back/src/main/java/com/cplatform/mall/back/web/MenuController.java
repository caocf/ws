package com.cplatform.mall.back.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jasig.cas.client.util.AssertionHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.cplatform.mall.back.entity.SysMenu;
import com.cplatform.mall.back.model.SessionUser;
import com.cplatform.mall.back.service.MenuService;
import com.cplatform.mall.back.utils.AppConfig;
import com.cplatform.mall.back.utils.Constants;

/**
 * Title. <br>
 * Description.
 * <p/>
 * Copyright: Copyright (c) 13-5-21 上午11:21
 * <p/>
 * Company: 北京宽连十方数字技术有限公司
 * <p/>
 * Author: nicky
 * <p/>
 * Version: 1.0
 * <p/>
 */
@Controller
@RequestMapping("/menu")
public class MenuController {

	@Autowired
	AppConfig appConfig;

	@Autowired
	MenuService menuService;

	@RequestMapping("")
	public String frame() {
		return "menu/frame";
	}

	@RequestMapping("head")
	public String head(Model model) {
		model.addAttribute("clientUrl", appConfig.getSsoClient());
		model.addAttribute("logoutUrl", appConfig.getSsoServer() + "/logout");
		// 取sso传来的数据方法示例。
		// Principal的id为登录帐号
		// attributes存附加的一些信息 注意大小写
		String userName = (String) AssertionHolder.getAssertion().getPrincipal().getAttributes().get("USER_NAME");
		model.addAttribute("loginName", userName);
		return "menu/head";
	}

	@RequestMapping("container")
	public String container() {
		return "menu/container";
	}

	@RequestMapping("welcome")
	public String welcome(Model model) {
		model.addAttribute("sessionUser", SessionUser.getSessionUser());
		return "menu/welcome";
	}

	/* 功能菜单数据获取 */
	@RequestMapping("tree")
	@ResponseBody
	public Object menuTree(HttpServletRequest request, HttpSession session) {

		if (!SessionUser.isLogin()) {// 没有登录不显示菜单
			return "";
		}

		List<SysMenu> list = menuService.menusOfUser(session);

		List<Map<String, String>> ret = new ArrayList<Map<String, String>>();
		for (SysMenu po : list) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("id", po.getMenuCode());
			map.put("pid", po.getMenuPcode());
			map.put("name", po.getMenuName());
			if (po.isLeafYn()) { // 如果为叶子节点
				map.put("url", "menu/jump/" + po.getMenuCode());
			} else {
				map.put("url", po.getMenuUrl());
			}
			ret.add(map);
		}
		return ret;
	}

	/* 历史菜单数据获取 */
	@RequestMapping("fav")
	@ResponseBody
	public Object fav(@RequestParam(value = "clear", required = false, defaultValue = "false") boolean clear, HttpServletRequest request,
	        HttpSession session) {
		SessionUser sessionUser = (SessionUser) session.getAttribute(SessionUser.SESSION_USER_KEY);

		if (!SessionUser.isLogin()) {// 没有登录
			return null;
		}

		Long userId = sessionUser.getId();

		// 传入参数clear=true,则清空历史记录
		if (clear) {
			menuService.clearData(userId);
			return null;
		}

		// 获取历史菜单数据
		List ret = new Vector();
		String menuCode = null;
		Map map = null;
		SysMenu menu = null;

		List<Map.Entry<String, Long>> list = menuService.getSortedData(userId);
		if (list != null) {
			for (Map.Entry<String, Long> entry : list) {
				menuCode = entry.getKey();
				menu = menuService.allMenuMap().get(menuCode);
				if (menu == null)
					continue;
				map = new HashMap();
				map.put("text", menu.getMenuName()); // 显示文本
				map.put("id", "source/" + menuCode); // source不能更改，为客户端跟节点id
				map.put("iconCls", "fav-ico");
				map.put("leaf", true); // 叶子节点
				map.put("href", request.getServletPath() + "/menu/jump/" + menu.getMenuCode()); // 点击链接
				map.put("cls", "file"); // 样式定义
				map.put("qtip", menuService.getPathByModelCode(menu.getModelCode()));// tips,显示全路径
				map.put("hrefTarget", request.getParameter("target")); // 链接目标框架名
				ret.add(map);
			}
		}

		return ret;
	}

	/* 负责点击菜单叶子节点的跳转，并记录点击历史 */
	@RequestMapping(value = "jump/{menuCode}")
	public String jump(@PathVariable String menuCode, HttpSession session) {
		SessionUser sessionUser = (SessionUser) session.getAttribute(SessionUser.SESSION_USER_KEY);
		Long userId = sessionUser.getId();

		String url = menuService.allMenuMap().get(menuCode).getMenuUrl();
		// 记录返回的路径
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		session.setAttribute(Constants.QUERY_BACK_URL, request.getContextPath() + url);
		menuService.addData(userId, menuCode);
		return "redirect:" + url;
	}

}
