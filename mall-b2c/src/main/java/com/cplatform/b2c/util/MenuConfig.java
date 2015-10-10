package com.cplatform.b2c.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.jdom.Element;

import com.cplatform.b2c.dto.MenuDTO;

/**
 * 获取个人用户中心菜单.
 * <p>
 * Copyright: Copyright (c) Mar 13, 2014 10:14:41 AM
 * <p>
 * Company: 宽连信息(苏州)技术有限公司
 * <p>
 * 
 * @author wangtaob@c-platform.com
 * @version 1.0.0
 */
public class MenuConfig {
	private static final Logger logger = Logger.getLogger(MenuConfig.class);
	/**
	 * 生成菜单数据结构
	 * @param province
	 * @return
	 */
	public static List<MenuDTO> getMenus(String province) {
		List<MenuDTO> menuDTOs = new ArrayList<MenuDTO>();
		//河南商品不用加载base.xml
		if (!"410000".equals(province)) {
			List<Element> elements = getBaseMenu();
			if (CollectionUtils.isEmpty(elements)) {
				return null;
			}
			menuDTOs = generateMenuDTO(elements);
			logger.info("get base menus");
		}

		Set<String> menuIdSet = new HashSet<String>();
		for (MenuDTO menuDTO : menuDTOs) {
			menuIdSet.add(menuDTO.getMenuId());
		}

		List<Element> customElements = getMenu(province);
		if (!CollectionUtils.isEmpty(customElements)) {
			logger.info("get base menus by province.");
			List<MenuDTO> customMenuDTOs=generateMenuDTO(customElements);
			for(MenuDTO menuDTO:customMenuDTOs){
				if(menuIdSet.contains(menuDTO.getMenuId())){
					continue;
				}
				menuDTOs.add(menuDTO);
			}
		}

		return reBuildMenuTree(menuDTOs);
	}
	
	/**
	 * 支持多级菜单的扩展
	 * @param elements
	 * @return
	 */
	@SuppressWarnings("unchecked")
    private static List<MenuDTO> generateMenuDTO(List<Element> elements){
		if(CollectionUtils.isEmpty(elements)){
			return null;
		}
		List<MenuDTO> menuDTOs = new ArrayList<MenuDTO>();
		for (Element e : elements) {
			MenuDTO menuDTO = new MenuDTO();
			menuDTO.setMenuId(e.getAttributeValue("menuId"));
			menuDTO.setName(e.getAttributeValue("name"));
			menuDTO.setParentId(e.getAttributeValue("parentId"));
			menuDTO.setHref(e.getAttributeValue("href"));
			menuDTO.setType(e.getAttributeValue("type"));
			menuDTO.setSort(Integer.valueOf(e.getAttributeValue("sort")));
			menuDTOs.add(menuDTO);
			List<MenuDTO> cMenuDTOs=generateMenuDTO(e.getChildren());
			if(!CollectionUtils.isEmpty(cMenuDTOs)){
				menuDTOs.addAll(cMenuDTOs);
			}
		}
		return menuDTOs;
	}

	/**
	 * 构造menu的tree结构，并根据sort来排序
	 * @param menuDTOs
	 * @return
	 */
	private static List<MenuDTO> reBuildMenuTree(List<MenuDTO> menuDTOs) {
		if (CollectionUtils.isEmpty(menuDTOs)) {
			return null;
		}
		Map<String,MenuDTO> menuMap=new HashMap<String, MenuDTO>();
		for(MenuDTO menuDTO:menuDTOs){
			menuMap.put(menuDTO.getMenuId(), menuDTO);
		}
		for(MenuDTO menuDTO:menuDTOs){
			if(menuDTO.getParentId()==null){
				continue;
			}
			MenuDTO parentMenuDto=menuMap.get(menuDTO.getParentId());
			if(parentMenuDto==null){
				continue;
			}
		   List<MenuDTO> cMenuDTOs=parentMenuDto.getChildren();
		   if(cMenuDTOs==null){
			   cMenuDTOs=new ArrayList<MenuDTO>();
			   parentMenuDto.setChildren(cMenuDTOs);
		   }
		   cMenuDTOs.add(menuDTO);
		}
		List<MenuDTO> rMenuDTOs=new ArrayList<MenuDTO>();
		for(MenuDTO menuDTO:menuDTOs){
			if(menuDTO.getParentId()==null){
				Collections.sort(menuDTO.getChildren(),MENU_SORT_COMPARATOR);
				rMenuDTOs.add(menuDTO);
			}
		}
		Collections.sort(rMenuDTOs,MENU_SORT_COMPARATOR);
		return rMenuDTOs;
	}

	/**
	 * 获取全网版菜单
	 * 
	 * @return
	 */
	private static List<Element> getBaseMenu() {
		XmlHelper helper = new XmlHelper();
		return helper.XmlParse("");
	}

	/**
	 * 获取个性化定制菜单
	 * 
	 * @return
	 */
	private static List<Element> getMenu(String name) {
		XmlHelper helper = new XmlHelper();
		return helper.XmlParse(name);
	}
	
	/**
	 * 菜单排序comparator
	 */
	public static final Comparator<MenuDTO> MENU_SORT_COMPARATOR = new Comparator<MenuDTO>() {
	    public int compare(MenuDTO m1, MenuDTO m2) {
	      return m1.getSort()-m2.getSort();
	    }
	  };

}
