package com.cplatform.mall.dc.utils;

import com.cplatform.mall.dc.model.SessionUser;

/**
 * 地区权限类
 * 
 * @author zhuangxx@c-platform.com
 * @since 2013-8-2
 */
public class AreaAuthority {
	/**
	 * 获取地区查询权限
	 * 
	 * @return 地区查询权限
	 */
	public static String getAreaStr() {
		SessionUser su = SessionUser.getSessionUser();
		String[] listArea = su.getListArea();
		StringBuilder sb = new StringBuilder();
		if (listArea != null) {
			for (int i = 0; i < listArea.length; i++) {
				sb.append(listArea[i]).append(",");
			}
			sb.delete(sb.length() - 1, sb.length());
		} else {
			sb.append("''");
		}
		return sb.toString();
	}
}
