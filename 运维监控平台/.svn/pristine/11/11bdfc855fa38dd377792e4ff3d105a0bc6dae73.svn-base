package com.cplatform.mall.dc.utils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.cplatform.mall.dc.web.OrderController;

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
public class ChartDataUtil {
	
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
	public static JSONObject getChartJson(String title,String titleY,List<String> colsTitleList,List<Map> colsContentList){
		JSONObject json = new JSONObject();
		
		// 属性
		Map<String, String> attribute = new HashMap<String, String>();
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
	
	public static JSONObject getChartJson(String title,String titleY,List<String> colsTitleList,List<Map> colsContentList,String start,String end){
		JSONObject json = getChartJson(title, titleY, colsTitleList, colsContentList);
		json.put("start", start);
		json.put("end", end);
		return json;
	}
	
	/**
	 * 控制点颜色
	 * @param list 数值
	 * @param begin 开始范围
	 * @param end	结束范围
	 * @param color 颜色
	 * @return
	 */
	public static List<Object> pointColor(List<?> list,Double begin,Double end,String color){
		 List<Object> dataList =new ArrayList<Object>();
		    
		 for(Object obj:list) {
			 double value=Double.parseDouble(obj.toString());
			 if(value>=begin && value<= end){
				 dataList.add("{y:"+value+",marker:{fillColor:\"#"+color+"\",lineColor:\"#"+color+"\"}}");
			 }else{
				 dataList.add(value);
			 }
		}
		 
		return dataList;
	}
	
	
	/**
	 * 保留小数点
	 * @param value
	 * @param pattern  #.##
	 * @return
	 */
	public static Double doubleFormat(double value,String pattern){
		DecimalFormat decimal=new DecimalFormat(pattern);
		Double results =Double.parseDouble(decimal.format(value));
		return results ;
	}
	
}
