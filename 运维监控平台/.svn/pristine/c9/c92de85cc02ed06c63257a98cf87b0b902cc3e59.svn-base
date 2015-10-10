package com.cplatform.mall.dc.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.cplatform.mall.dc.dao.DcMenuDao;
import com.cplatform.mall.dc.entity.DcMenu;

/**
 * Title. <br>
 * Description.
 * <p/>
 * Copyright: Copyright (c) 13-6-17 下午5:08
 * <p/>
 * Company: 苏州宽连信息技术有限公司
 * <p/>
 * Author:
 * <p/>
 * Version: 1.0
 * <p/>
 */
@Service
public class CommonCacheService {

	@Autowired
	DcMenuDao menuDao;

	@CacheEvict(value = { "menus", "regions" }, allEntries = true)
	public void cacheEvict() {
		// Do Nothing
	}

	@Cacheable(value = "menus", key = "'SysMenuList'")
	public List<DcMenu> getAllMenuList() {
		return menuDao.findAll();
	}

	@Cacheable(value = "menus", key = "'SysMenuMap'")
	public Map<String, DcMenu> getAllMenuMap() {
		Map<String, DcMenu> menuMap = new HashMap<String, DcMenu>();
		List<DcMenu> menuList = getAllMenuList();
		for (DcMenu menu : menuList) {
			menuMap.put(menu.getMenuCode(), menu);
		}
		return menuMap;
	}
}
