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

import com.cplatform.mall.dc.service.OrderService;
import com.cplatform.mall.dc.service.OrderService.Type;
import com.cplatform.mall.dc.utils.ChartDataUtil;

/**
 * 订单统计<br>
 * <p>
 * Copyright: Copyright (c) 2014-1-2 上午9:53:32
 * <p>
 * Company: 苏州宽连信息技术有限公司* <p>
 * 
 * @author guyu@c-platform.com
 * @version 1.0.0
 */
@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	OrderService orderService;
	
	public static final String RET_CODE_SUCCESS = "0000";
	public static final String RET_CODE_NO_UPDATE = "0001";

	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String tomain(Model model) throws SQLException {
		model.addAttribute("req_url","/order/getAllOrder");
		return "/right/view";
	}
	
	@RequestMapping(value = "/web", method = RequestMethod.GET)
	public String toweb(Model model) throws SQLException {
		model.addAttribute("type","web");
		model.addAttribute("req_url","/order/getWebOrder");
		return "/right/view";
	}
	
	@RequestMapping(value = "/wap", method = RequestMethod.GET)
	public String towapb(Model model) throws SQLException {
		model.addAttribute("type","wap");
		model.addAttribute("req_url","/order/getWebOrder");
		return "/right/view";
	}
	
	@RequestMapping(value = "/client", method = RequestMethod.GET)
	public String toclient(Model model) throws SQLException {
		model.addAttribute("type","client");
		model.addAttribute("req_url","/order/getWebOrder");
		return "/right/view";
	}
	
	@RequestMapping(value = "/getAllOrder", method = RequestMethod.POST)
	@ResponseBody
	public String getAllOrder(Model model,String start,String end,String searchType) throws SQLException {
		if(start==null&&end==null){
			Map<Type,List>  map = orderService.getInit();
	
			if(map==null||map.size()==0){
				JSONObject obj = new JSONObject();
				obj.put("retCode", RET_CODE_NO_UPDATE);
				return obj.toString();
			}
			List<String> colsTitleList=(List<String>)map.get(Type.FIELD);
			List<Map> colsContentList=new ArrayList<Map>();
			Map<String,Object> dataMap1=new HashMap<String, Object>();
	 		dataMap1.put("name", "web订单");
	 		dataMap1.put("data", map.get(Type.WEB));
	 		Map<String,Object> dataMap2=new HashMap<String, Object>();
	 		dataMap2.put("name", "wap订单");
	 		dataMap2.put("data", map.get(Type.WAP));
	 		Map<String,Object> dataMap3=new HashMap<String, Object>();
	 		dataMap3.put("name", "客户端订单");
	 		dataMap3.put("data", map.get(Type.CLIENT));
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
			return ChartDataUtil.getChartJson( "订单统计", "", colsTitleList, colsContentList, startTime, endTime).toString();
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
			Map<String,Object> dataMap1=new HashMap<String, Object>();
	 		dataMap1.put("name", "web订单");
	 		dataMap1.put("data", map.get(Type.WEB));
	 		Map<String,Object> dataMap2=new HashMap<String, Object>();
	 		dataMap2.put("name", "wap订单");
	 		dataMap2.put("data", map.get(Type.WAP));
	 		Map<String,Object> dataMap3=new HashMap<String, Object>();
	 		dataMap3.put("name", "客户端订单");
	 		dataMap3.put("data", map.get(Type.CLIENT));
	 		colsContentList.add(dataMap1);
	 		colsContentList.add(dataMap2);
	 		colsContentList.add(dataMap3);
			return ChartDataUtil.getChartJson("订单统计", "", colsTitleList, colsContentList).toString();
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
	public String getWebOrder(Model model,String type,String start,String end, String searchType) throws SQLException {
		Type otype = null;
		if(type!=null){
			if("web".equals(type)){
				otype = Type.WEB;
			}
			if("wap".equals(type)){
				otype = Type.WAP;
			}
			if("client".equals(type)){
				otype = Type.CLIENT;
			}
		}
		String name = type;
		if(type.equals("client")){
			name = "客户端";
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
				dataMap1.put("name", name+"订单");
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
			return ChartDataUtil.getChartJson(name+"订单统计", "", colsTitleList, colsContentList, startTime, endTime).toString();
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
			Map<String,Object> dataMap1=new HashMap<String, Object>();
			dataMap1.put("name", name+"订单");
	 		dataMap1.put("data", map.get(otype));
	 		colsContentList.add(dataMap1);
			return ChartDataUtil.getChartJson(name+"订单统计", "", colsTitleList, colsContentList).toString();
		}
	}
	

}
