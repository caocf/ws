package com.cplatform.b2c.web;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.rubyeye.xmemcached.MemcachedClient;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import org.apache.log4j.Logger;
import com.cplatform.b2c.dto.MenuDTO;
import com.cplatform.b2c.model.SessionUser;
import com.cplatform.b2c.service.ShopService;
import com.cplatform.b2c.util.JsonRespWrapper;
import com.cplatform.b2c.util.MenuConfig;
import com.cplatform.b2c.util.StringUtil;

/**
 * 商城左侧菜单显示判断. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2014-1-8 下午1:43:01
 * <p>
 * Company: 苏州宽连信息技术有限公司
 * <p>
 * 
 * @author zhangdong-ca@c-platform.com
 * @version 1.0.0
 */
@Controller
public class LeftMenuController {
	
	/** 日志记录器 */
	private final Logger logger = Logger.getLogger(getClass());


	@Autowired
	private ShopService shopService;
	
	@Autowired
	MemcachedClient memcachedClient;
	
	public static String MEMCACHED_KEY_PREFIX="_member_center_left_menu";
	
	/** memcached缓存有效期(30分钟). */
	private static final int MEMCACHED_EXPIRE_TIME = 30 * 60;
	
	//10秒
	private static final long MEMCACHED_TIMEOUT = 10 * 1000;

	/**
	 * 获取当前用户登录所属地区编号
	 * 
	 * @param response
	 * @return
	 */
	@RequestMapping("/user/provRegionCode")
	@ResponseBody
	public Object getLoginUserInfo(HttpServletResponse response) {
		SessionUser sessionUser = SessionUser.getSessionUser(response);
		// 用户所属地区编号(省区号)
		String provRegionCode = null;
		if (sessionUser != null && StringUtils.isNotBlank(sessionUser.getAreaCode())) {
			provRegionCode = shopService.getProvinceCodeByRegionCode(sessionUser.getAreaCode(), null);
		}
		return JsonRespWrapper.success().json("provRegionCode", provRegionCode);
	}

	@RequestMapping("/user/getMenus")
	@ResponseBody
	public Object getMenus(HttpServletResponse response) {
		logger.info("ready to get menus");
		SessionUser sessionUser = SessionUser.getSessionUser(response);
		
		// 用户所属地区编号(省区号)
		String provRegionCode = null; 
		if (sessionUser != null && StringUtils.isNotBlank(sessionUser.getAreaCode())) {
			provRegionCode = shopService.getProvinceCodeByRegionCode(sessionUser.getAreaCode(), null);
		}
		logger.info("get menus ,the provRegionCode is "+provRegionCode);
		List<MenuDTO> menuDTOs=null; 
		try{
			String key = MEMCACHED_KEY_PREFIX+StringUtil.getString(provRegionCode);
			menuDTOs = memcachedClient.get(key, MEMCACHED_TIMEOUT);
			if(CollectionUtils.isEmpty(menuDTOs)){
				menuDTOs= MenuConfig.getMenus(provRegionCode);
				memcachedClient.set(key, MEMCACHED_EXPIRE_TIME, menuDTOs);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			logger.error("memcached获取菜单异常。"+e.getMessage());
		}
		if (CollectionUtils.isEmpty(menuDTOs)) {
			menuDTOs = MenuConfig.getMenus(provRegionCode);
		}
		return JsonRespWrapper.success().json("menuDTOs", menuDTOs);
	}
}
