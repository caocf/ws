package com.cplatform.mall.dc.web;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cplatform.mall.dc.service.InterfaceService;
import com.cplatform.mall.dc.service.InterfaceService.Type;
import com.cplatform.mall.dc.utils.ChartDataUtil;

/**
 * 接口统计 <br>
 * <p>
 * Copyright: Copyright (c) 2014-5-7 下午04:24:05
 * <p>
 * Company: 苏州宽连信息技术有限公司
 * <p>
 * 
 * @author guyu@c-platform.com
 * @version 1.0.0
 */
@Controller
@RequestMapping("/inter")
public class InterfaceController {

	@Autowired
	InterfaceService service;
	
	public static final String RET_CODE_SUCCESS = "0000";
	public static final String RET_CODE_NO_UPDATE = "0001";
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String tomain(Model model) throws SQLException {
		Map<Type, Object> map = service.getInit();
		model.addAttribute("interType",map.get(Type.TYPE));
		model.addAttribute("interValues",map.get(Type.NAME));
		return "/interface/main";
	}
	
	@RequestMapping(value = "/getDetail", method = RequestMethod.GET)
	public String todetail(Model model,String type,String name) throws SQLException {
		model.addAttribute("name",name);
		model.addAttribute("type",type);
		model.addAttribute("req_url","/inter/detail");
		return "/interface/view";
	}
	
	@RequestMapping(value = "/detail", method = RequestMethod.POST)
	@ResponseBody
	public String toweb(Model model,String start,String end,String name,String type) throws SQLException {
		Map<Type,Object> map = new HashMap<Type,Object>();
		if(start==null&&end==null){
			map = service.getInterface(type, name);
		}else{
			start = start.replace("-", "").replace(" ", "").replace(":", "");
			end = end.replace("-", "").replace(" ", "").replace(":", "");
			map = service.getInterface(start,end,type, name);
		}
		if(map==null||map.size()==0){
			JSONObject obj = new JSONObject();
			obj.put("retCode", RET_CODE_NO_UPDATE);
			return obj.toString();
		}
		List<String> colsTitleList = (List<String>) map.get(Type.FIELD);
		List<Map> colsContentList = new ArrayList<Map>();
		Map<String, Object> dataMap1 = new HashMap<String, Object>();
		String inter_name = map.get(Type.NAME).toString();
		String inter_type = map.get(Type.TYPE).toString();
		dataMap1.put("name", type + " " + inter_name);
		dataMap1.put("data", ChartDataUtil.pointColor((List<Double>) map
				.get(Type.VALUES), 1D, 1D, "FF0087"));
		Object starts = map.get(Type.START);
		String startTime = "";
		if (starts != null) {
			startTime = starts.toString();
		}
		Object ends = map.get(Type.END);
		String endTime = "";
		if (ends != null) {
			endTime = ends.toString();
		}
		colsContentList.add(dataMap1);
		return ChartDataUtil.getChartJson(type + " " + inter_name, "",colsTitleList, colsContentList,startTime,endTime).toString();
	}

}
