package com.cplatform.b2c.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class MenuCodeUtil {

	// 我购买的商品
	public static final String MENU_CODE_ORDER_MANAGER = "MENU_CODE_ORDER_MANAGER";

	// 我的福利
	public static final String MENU_CODE_ORDER_WELFARE = "MENU_CODE_ORDER_WELFARE";

	/**
	 * 码状态map
	 */
	public static Map<String, String> menuMap = null;
	static {
		menuMap = new HashMap<String, String>();
		menuMap.put(MENU_CODE_ORDER_MANAGER, "orderManager_我购买的商品");
		menuMap.put(MENU_CODE_ORDER_WELFARE, "redpackage_我的福利");
	}

	/**
	 * 获取菜单对应的参数
	 * 
	 * @param menuKey
	 * @return
	 */
	public String getMenuValue(String menuKey) {
		String menuValue = menuMap.get(MENU_CODE_ORDER_MANAGER);
		if (StringUtils.isNotBlank(menuKey)) {
			menuValue = menuMap.get(menuKey);
		}
		return menuValue;
	}

}
