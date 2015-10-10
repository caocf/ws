package com.cplatform.mall.dc.utils.fusionChart;

import javax.servlet.http.HttpSession;

/**
 * 这是对图表进行操作的工具类
 * 
 * @author zhangdong
 * 
 */
public class FunctionsUtils {

	/**
	 * 函数帮助你特殊字符转义XML
	 * 
	 * @param strItem
	 * @param forDataURL
	 * @return
	 */
	public static String escapeXML(String strItem, boolean forDataURL) {
		if (strItem != null) {
			if (forDataURL == true) {
				strItem = strItem.replaceAll("'", "&apos;");
			} else {
				strItem = strItem.replaceAll("%", "%25");
				strItem = strItem.replaceAll("'", "%26apos;");
				strItem = strItem.replaceAll("&", "%26");
			}
			strItem = strItem.replaceAll("<", "&lt;");
			strItem = strItem.replaceAll(">", "&gt;");
			// 目前不考虑任何特殊字符在这里
		}
		return strItem;
	}

	/**
	 * 根据用户选的paletter来绘制图表返回一个1-5的值
	 * 
	 * @param session
	 * @return
	 */
	public static String getPalette(HttpSession session) {
		String palette = null;
		if (null != session.getAttribute("palette")) {
			palette = (String) session.getAttribute("palette");
		}
		if (null == palette) {
			palette = "2";
		}
		return palette;
	}

	/**
	 * 获取初始化图标的时候是否带有动态效果 0.静态 1.动态
	 * 
	 * @param session
	 * @return
	 */
	public static String getAnimationState(HttpSession session) {
		String animation = null;
		if (null != session.getAttribute("animation")) {
			animation = (String) session.getAttribute("animation");
		}
		if (null == animation) {
			animation = "0";
		} else if (!animation.equals("0")) {
			animation = "1";
		}
		return animation;
	}

	/**
	 * 返回字幕颜色
	 * 
	 * @return
	 */
	public static String getCaptionFontColor() {
		// 返回一个十六进制颜色代码没有#
		// FFC30C - Yellow Color
		return "666666";
	}
}
