package com.cplatform.mall.dc.web;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cplatform.mall.dc.service.MsOsDiskSpaceService;
import com.cplatform.mall.dc.service.MsOsMpstatService;
import com.cplatform.mall.dc.service.MsOsUptimeService;
import com.cplatform.mall.dc.service.MsOsUptimeService.Type;
import com.cplatform.mall.dc.utils.ChartDataUtil;

/**
 * MsOsUptimeController.java <br>
 * <p>
 * Copyright: Copyright (c) 2014-5-8 下午05:23:00
 * <p>
 * Company: 苏州宽连信息技术有限公司
 * <p>
 * 
 * @author guyu@c-platform.com
 * @version 1.0.0
 */
@Controller
@RequestMapping("/uptime")
public class MsOsUptimeController {

	@Autowired
	MsOsUptimeService service;
	
	@Autowired
	MsOsMpstatService service2;
	
	@Autowired
	MsOsDiskSpaceService service3;
	
	
	public static final String RET_CODE_SUCCESS = "0000";
	public static final String RET_CODE_NO_UPDATE = "0001";

	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String tomain(Model model) throws SQLException {
		model.addAttribute("req_url","/uptime/getView");
		model.addAttribute("req_url2","/uptime/getView2");
		model.addAttribute("req_url3","/uptime/getView3");
		return "/msosuptime/view";
	}
	
	@RequestMapping(value = "/getView", method = RequestMethod.POST)
	@ResponseBody
	public String getView(Model model,String start,String end,String id) throws SQLException {
		if(id!=null){
			Map<Type,List>  map = new HashMap<Type,List>();
			Map<String,String> services = MsOsUptimeService.services;
			String serviceName = services.get(id);
			if(start==null&&end==null){
				map = service.getField(id);
			}else{
				start = start.replace("-", "").replace(" ", "").replace(":", "");
				end = end.replace("-", "").replace(" ", "").replace(":", "");
				map = service.getField(start,end, id);
			}
			if(map==null||map.size()==0){
				JSONObject obj = new JSONObject();
				obj.put("retCode", RET_CODE_NO_UPDATE);
				return obj.toString();
			}
			List<String> colsTitleList=(List<String>)map.get(Type.FIELD);
			List<Map> colsContentList=new ArrayList<Map>();
			Map<String,Object> dataMap1=new HashMap<String, Object>();
	 		dataMap1.put("name", "1分钟负载");
	 		dataMap1.put("data", map.get(Type.MS1));
	 		Map<String,Object> dataMap2=new HashMap<String, Object>();
	 		dataMap2.put("name", "5分钟负载");
	 		dataMap2.put("data", map.get(Type.MS5));
	 		Map<String,Object> dataMap3=new HashMap<String, Object>();
	 		dataMap3.put("name", "15分钟负载");
	 		dataMap3.put("data", map.get(Type.MS15));
	 		colsContentList.add(dataMap1);
	 		colsContentList.add(dataMap2);
	 		colsContentList.add(dataMap3);
	 		List<String> starts = (List<String>)map.get(Type.START);
			String startTime = "";
			if(starts!=null&&starts.size()>0){
				startTime = starts.get(0);
			}
			List<String> ends = (List<String>)map.get(Type.END);
			String endTime = "";
			if(ends!=null&&ends.size()>0){
				endTime = ends.get(0);
			}
			return ChartDataUtil.getChartJson(serviceName+"服务器负载", "", colsTitleList, colsContentList, startTime, endTime).toString();
		}
		return null;
	}
	
	@RequestMapping(value = "/getView2", method = RequestMethod.POST)
	@ResponseBody
	public String getView2(Model model,String start,String end,String id) throws SQLException {
		if(id!=null){
			Map<MsOsMpstatService.Type,List>  map = new HashMap<MsOsMpstatService.Type,List>();
			Map<String,String> services = MsOsUptimeService.services;
			String serviceName = services.get(id);
			if(start==null&&end==null){
				map = service2.getField(id);
			}else{
				start = start.replace("-", "").replace(" ", "").replace(":", "");
				end = end.replace("-", "").replace(" ", "").replace(":", "");
				map = service2.getField(start,end, id);
			}
			if(map==null||map.size()==0){
				JSONObject obj = new JSONObject();
				obj.put("retCode", RET_CODE_NO_UPDATE);
				return obj.toString();
			}
			List<String> colsTitleList=(List<String>)map.get(MsOsMpstatService.Type.FIELD);
			List<Map> colsContentList=new ArrayList<Map>();
			Map<String,Object> dataMap1=new HashMap<String, Object>();
	 		dataMap1.put("name", "cpu_user");
	 		dataMap1.put("data", map.get(MsOsMpstatService.Type.cpu_user));
	 		Map<String,Object> dataMap2=new HashMap<String, Object>();
	 		dataMap2.put("name", "cpu_sys");
	 		dataMap2.put("data", map.get(MsOsMpstatService.Type.cpu_sys));
	 		Map<String,Object> dataMap3=new HashMap<String, Object>();
	 		dataMap3.put("name", "iowait");
	 		dataMap3.put("data", map.get(MsOsMpstatService.Type.iowait));
	 		colsContentList.add(dataMap1);
	 		colsContentList.add(dataMap2);
	 		colsContentList.add(dataMap3);
			return ChartDataUtil.getChartJson(serviceName+"CPU使用率", "", colsTitleList, colsContentList).toString();
		}
		return null;
	}
	
	
	@RequestMapping(value = "/getView3", method = RequestMethod.POST)
	@ResponseBody
	public String getView3(Model model,String start,String end,String id) throws SQLException {
		if(id!=null){
			Map<MsOsDiskSpaceService.Type,List>  map = new HashMap<MsOsDiskSpaceService.Type,List>();
			Map<String,String> services = MsOsUptimeService.services;
			String serviceName = services.get(id);
			if(start==null&&end==null){
				map = service3.getField(id);
			}else{
				start = start.replace("-", "").replace(" ", "").replace(":", "");
				end = end.replace("-", "").replace(" ", "").replace(":", "");
				map = service3.getField(start,end, id);
			}
			if(map==null||map.size()==0){
				JSONObject obj = new JSONObject();
				obj.put("retCode", RET_CODE_NO_UPDATE);
				return obj.toString();
			}
			List<String> colsTitleList=(List<String>)map.get(MsOsDiskSpaceService.Type.FIELD);
			List<Map> colsContentList=new ArrayList<Map>();
			Map<String,List<Double>> ms_user_map = (Map<String,List<Double>>)(map.get(MsOsDiskSpaceService.Type.ms_use).get(0));
			for(Iterator<String> keys = ms_user_map.keySet().iterator();keys.hasNext();){
				Map<String,Object> dataMap1=new HashMap<String, Object>();
		 		String name = keys.next();
				dataMap1.put("name", name);
		 		dataMap1.put("data", ms_user_map.get(name));
		 		colsContentList.add(dataMap1);
			}
			return ChartDataUtil.getChartJson(serviceName+"磁盘使用率", "", colsTitleList, colsContentList).toString();
		}
		return null;
	}
}
