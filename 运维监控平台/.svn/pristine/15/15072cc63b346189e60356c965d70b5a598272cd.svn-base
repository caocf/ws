package com.cplatform.mall.dc.web;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cplatform.mall.dc.service.FlowService;
import com.cplatform.mall.dc.service.FlowService.Type;
import com.cplatform.mall.dc.utils.ChartDataUtil;

/**
 * FlowController.java <br>
 * <p>
 * Copyright: Copyright (c) 2014-5-4 下午02:31:06
 * <p>
 * Company: 苏州宽连信息技术有限公司
 * <p>
 * 
 * @author guyu@c-platform.com
 * @version 1.0.0
 */
@Controller
@RequestMapping("/flow")
public class FlowController {

	@Autowired
	FlowService orderService;
	
	public static final String RET_CODE_SUCCESS = "0000";
	public static final String RET_CODE_NO_UPDATE = "0001";

	
	@RequestMapping(value = "main85", method = RequestMethod.GET)
	public String tomain85(Model model) throws SQLException {
		model.addAttribute("type","1");
		model.addAttribute("req_url","/flow/getAllOrder");
		return "/right/view";
	}
	
	@RequestMapping(value = "/mo85", method = RequestMethod.GET)
	public String tomo85(Model model) throws SQLException {
		model.addAttribute("type","mo85");
		model.addAttribute("req_url","/flow/getWebOrder");
		return "/right/view";
	}
	
	@RequestMapping(value = "/mt85", method = RequestMethod.GET)
	public String tomt85(Model model) throws SQLException {
		model.addAttribute("type","mt85");
		model.addAttribute("req_url","/flow/getWebOrder");
		return "/right/view";
	}
	
	@RequestMapping(value = "/main18", method = RequestMethod.GET)
	public String tomain18(Model model) throws SQLException {
		model.addAttribute("type","2");
		model.addAttribute("req_url","/flow/getAllOrder");
		return "/right/view";
	}
	
	@RequestMapping(value = "/mo18", method = RequestMethod.GET)
	public String tomo18(Model model) throws SQLException {
		model.addAttribute("type","mo18");
		model.addAttribute("req_url","/flow/getWebOrder");
		return "/right/view";
	}
	
	@RequestMapping(value = "/mt18", method = RequestMethod.GET)
	public String tomt18(Model model) throws SQLException {
		model.addAttribute("type","mt18");
		model.addAttribute("req_url","/flow/getWebOrder");
		return "/right/view";
	}
	
	
	@RequestMapping(value = "/getAllOrder", method = RequestMethod.POST)
	@ResponseBody
	public String getAllOrder(Model model,String type,String start,String end,String searchType) throws SQLException {
		if(start==null&&end==null){
			Map<Type,List>  map = orderService.getInit();
			if(map==null||map.size()==0){
				JSONObject obj = new JSONObject();
				obj.put("retCode", RET_CODE_NO_UPDATE);
				return obj.toString();
			}
			List<String> colsTitleList=(List<String>)map.get(Type.FIELD);
			List<Map> colsContentList=new ArrayList<Map>();
			String name = "";
			if(type.equals("1")){
				name = "10658585";
				Map<String,Object> dataMap1=new HashMap<String, Object>();
		 		dataMap1.put("name", "上行");
		 		dataMap1.put("data", map.get(Type.mo85));
		 		colsContentList.add(dataMap1);
		 		Map<String,Object> dataMap2=new HashMap<String, Object>();
		 		dataMap2.put("name", "下行");
		 		dataMap2.put("data", map.get(Type.mt85));
		 		colsContentList.add(dataMap2);
			}
			if(type.equals("2")){
				name = "10658318";
				Map<String,Object> dataMap1=new HashMap<String, Object>();
		 		dataMap1.put("name", "上行");
		 		dataMap1.put("data", map.get(Type.mo18));
		 		colsContentList.add(dataMap1);
		 		Map<String,Object> dataMap2=new HashMap<String, Object>();
		 		dataMap2.put("name", "下行");
		 		dataMap2.put("data", map.get(Type.mt18));
		 		colsContentList.add(dataMap2);
			}
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
			return ChartDataUtil.getChartJson( name+"通信上下行量", "", colsTitleList, colsContentList, startTime, endTime ).toString();
		}else{
			start = start.replace("-", "").replace(" ", "").replace(":", "");
			end = end.replace("-", "").replace(" ", "").replace(":", "");
			if(start.length()==8){
				start = start + "000000";
			}
			if(end.length()==8){
				end = end + "000000";
			}
			Map<Type,List>  map = orderService.getAllOrder(start,end,Integer.parseInt(searchType));
			if(map==null||map.size()==0){
				JSONObject obj = new JSONObject();
				obj.put("retCode", RET_CODE_NO_UPDATE);
				return obj.toString();
			}
			List<String> colsTitleList=map.get(Type.FIELD);
			List<Map> colsContentList=new ArrayList<Map>();
			String name = "";
			if(type.equals("1")){
				name = "10658585";
				Map<String,Object> dataMap1=new HashMap<String, Object>();
		 		dataMap1.put("name", "上行");
		 		dataMap1.put("data", map.get(Type.mo85));
		 		colsContentList.add(dataMap1);
		 		Map<String,Object> dataMap2=new HashMap<String, Object>();
		 		dataMap2.put("name", "下行");
		 		dataMap2.put("data", map.get(Type.mt85));
		 		colsContentList.add(dataMap2);
			}
			if(type.equals("2")){
				name = "10658318";
				Map<String,Object> dataMap1=new HashMap<String, Object>();
		 		dataMap1.put("name", "上行");
		 		dataMap1.put("data", map.get(Type.mo18));
		 		colsContentList.add(dataMap1);
		 		Map<String,Object> dataMap2=new HashMap<String, Object>();
		 		dataMap2.put("name", "下行");
		 		dataMap2.put("data", map.get(Type.mt18));
		 		colsContentList.add(dataMap2);
			}
			return ChartDataUtil.getChartJson(name+"通信上下行量", "", colsTitleList, colsContentList).toString();
		}
	}
	
	
	/**
	 * 获得WEB订单
	 * 
	 * @param model
	 *            页面模型
	 * @return 列表页面
	 * @throws SQLException
	 */
	@RequestMapping(value = "/getWebOrder", method = RequestMethod.POST)
	@ResponseBody
	public String getWebOrder(Model model,String type,String start,String end,String searchType) throws SQLException {
		Type otype = null;
		String codeName = "";
		String name = "";
		String ty = type.substring(0,2);
		String code = type.substring(2,type.length());
		if(type!=null){
			if("mo".equals(ty)&&code.endsWith("85")){
				otype = Type.mo85;
				codeName = "10658685";
				name = "上行";
			}
			if("mt".equals(ty)&&code.endsWith("85")){
				otype = Type.mt85;
				codeName = "10658685";
				name = "下行";
			}
			if("mo".equals(ty)&&code.endsWith("18")){
				otype = Type.mo18;
				codeName = "10658618";
				name = "上行";
			}
			if("mt".equals(ty)&&code.endsWith("18")){
				otype = Type.mt18;
				codeName = "10658618";
				name = "下行";
			}
		}
		if(start==null&&end==null){
			Map<Type,List>  map = orderService.getInit();
			if(map==null||map.size()==0){
				JSONObject obj = new JSONObject();
				obj.put("retCode", RET_CODE_NO_UPDATE);
				return obj.toString();
			}
			List<String> colsTitleList=map.get(Type.FIELD);
			List<Map> colsContentList=new ArrayList<Map>();
			Map<String,Object>dataMap1=new HashMap<String, Object>();
			if(otype!=null){
				dataMap1.put("name", name);
				dataMap1.put("data",map.get(otype));
		 		colsContentList.add(dataMap1);
			}
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
			return ChartDataUtil.getChartJson(codeName+"通信"+name+"量", "", colsTitleList, colsContentList,startTime,endTime).toString();
		}else{
			start = start.replace("-", "").replace(" ", "").replace(":", "");
			end = end.replace("-", "").replace(" ", "").replace(":", "");
			if(start.length()==8){
				start = start + "000000";
			}
			if(end.length()==8){
				end = end + "000000";
			}
			Map<FlowService.Type,List>  map = orderService.getAllOrder(start,end,Integer.parseInt(searchType));
			if(map==null||map.size()==0){
				JSONObject obj = new JSONObject();
				obj.put("retCode", RET_CODE_NO_UPDATE);
				return obj.toString();
			}
			List<String> colsTitleList=map.get(Type.FIELD);
			List<Map> colsContentList=new ArrayList<Map>();
			Map<String,Object> dataMap1=new HashMap<String, Object>();
			dataMap1.put("name", name);
	 		dataMap1.put("data", map.get(otype));
	 		colsContentList.add(dataMap1);
			return ChartDataUtil.getChartJson(codeName+"通信"+name+"量", "", colsTitleList, colsContentList).toString();
		}
	}
	

}
