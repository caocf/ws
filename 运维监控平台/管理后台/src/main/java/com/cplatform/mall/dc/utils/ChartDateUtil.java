package com.cplatform.mall.dc.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cplatform.mall.dc.web.OrderController;

import net.sf.json.JSONObject;

/**
 * 曲线图数据封装工具 <br>
 * <p>
 * Copyright: Copyright (c) 2014-4-24 下午 15:50:50
 * <p>
 * Company: 苏州宽连信息技术有限公司
 * <p>
 * 
 * @author xujiang@c-platform.com
 * @version 1.0.0
 */
public class ChartDateUtil {
	
	/**
	 * 普通曲线图数据封装 
	 * 
	 * @param divId
	 * @param title
	 * @param titleY
	 * @param colsTitleList
	 * @param colsContentList
	 * @return
	 */
	public static JSONObject getChartJson(String divId,String title,String titleY,List<String> colsTitleList,List<Map> colsContentList){
		JSONObject json = new JSONObject();
		
		// 属性
		Map<String, String> attribute = new HashMap<String, String>();
		attribute.put("id",	divId);
		attribute.put("title", title);
		attribute.put("titleY", titleY);
		json.put("attribute", attribute);
		
		// 列标题
		json.put("colsTitleList", colsTitleList);
		
 		// 列内容
 		json.put("colsContentList", colsContentList);
		json.put("retCode", OrderController.RET_CODE_SUCCESS);
		return json;
	}
}
