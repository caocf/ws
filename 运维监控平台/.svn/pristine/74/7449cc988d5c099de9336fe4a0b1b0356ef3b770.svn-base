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

import com.cplatform.mall.dc.service.OrderPayStatusService;
import com.cplatform.mall.dc.service.OrderPayStatusService.Type;
import com.cplatform.mall.dc.utils.ChartDataUtil;

/**
 * 订单统计<br>
 * <p>
 * Copyright: Copyright (c) 2014-1-2 上午9:53:32
 * <p>
 * Company: 苏州宽连信息技术有限公�? * <p>
 * 
 * @author guyu@c-platform.com
 * @version 1.0.0
 */
@Controller
@RequestMapping("/orderPayStatus")
public class OrderPayStatusController {

	@Autowired
	OrderPayStatusService orderService;
	
	public static final String RET_CODE_SUCCESS = "0000";
	public static final String RET_CODE_NO_UPDATE = "0001";

	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String tomain(Model model) throws SQLException {
		model.addAttribute("req_url","/orderPayStatus/getAllOrder");
		return "/right/view3";
	}
	
	@RequestMapping(value = "/web", method = RequestMethod.GET)
	public String toweb(Model model) throws SQLException {
		model.addAttribute("type","web");
		model.addAttribute("req_url","/orderPayStatus/getWebOrder");
		return "/right/view3";
	}
	
	@RequestMapping(value = "/client", method = RequestMethod.GET)
	public String toclient(Model model) throws SQLException {
		model.addAttribute("type","client");
		model.addAttribute("req_url","/orderPayStatus/getWebOrder");
		return "/right/view3";
	}
	
	@RequestMapping(value = "/getAllOrder", method = RequestMethod.POST)
	@ResponseBody
	public String getAllOrder(Model model,String start,String end, String searchType) throws SQLException {
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
			List<Double> websuccess = map.get(Type.WEBSUCCESS);
			List<Double> webfaile = map.get(Type.WEBFAILE);
			List<Double> clientsuccess = map.get(Type.CLIENTSUCCESS);
			List<Double> clientfaile = map.get(Type.CLIENTFAILE);
			List<Double> success = new ArrayList<Double>();
			List<Double> faile = new ArrayList<Double>();
			List<Double> rate = new ArrayList<Double>();
			for(int i=0;i<websuccess.size();i++){
				double succ = websuccess.get(i)+clientsuccess.get(i);
				success.add(succ);
				double fai = webfaile.get(i)+clientfaile.get(i);
				faile.add(fai);
				double ra = 0.0;
				if((succ+fai)>0)
					ra = succ*100/(succ+fai);
				 
				rate.add(ChartDataUtil.doubleFormat(ra,"#.##"));
			}
	 		dataMap1.put("name", "成功订单");
	 		dataMap1.put("type", "spline");
	 		dataMap1.put("yAxis", 1);
	 		dataMap1.put("data", success);
	 		Map<String,Object> dataMap2=new HashMap<String, Object>();
	 		dataMap2.put("name", "失败订单");
	 		dataMap2.put("type", "spline");
	 		dataMap2.put("yAxis", 1);
	 		dataMap2.put("data", faile);
	 		Map<String,Object> dataMap3=new HashMap<String, Object>();
	 		dataMap3.put("name", "成功订单");
	 		dataMap3.put("type", "column");
	 		dataMap3.put("yAxis", 0);
	 		dataMap3.put("data", rate);
	 		colsContentList.add(dataMap3);
	 		colsContentList.add(dataMap1);
	 		colsContentList.add(dataMap2);
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
			return ChartDataUtil.getChartJson("订单成功失败统计", "", colsTitleList, colsContentList, startTime, endTime).toString();
		}else{
			start = start.replace("-", "").replace(" ", "").replace(":", "");
			end = end.replace("-", "").replace(" ", "").replace(":", "");
			if(start.length()==8){
				start = start + "000000";
			}
			if(end.length()==8){
				end = end + "000000";
			}
			Map<Type,List>  map = orderService.getAllOrder(start, end, Integer.parseInt(searchType));
			if(map==null||map.size()==0){
				JSONObject obj = new JSONObject();
				obj.put("retCode", RET_CODE_NO_UPDATE);
				return obj.toString();
			}
			List<String> colsTitleList=map.get(Type.FIELD);
			List<Map> colsContentList=new ArrayList<Map>();

			Map<String,Object> dataMap1=new HashMap<String, Object>();
			List<Double> websuccess = map.get(Type.WEBSUCCESS);
			List<Double> webfaile = map.get(Type.WEBFAILE);
			List<Double> clientsuccess = map.get(Type.CLIENTSUCCESS);
			List<Double> clientfaile = map.get(Type.CLIENTFAILE);
			List<Double> success = new ArrayList<Double>();
			List<Double> faile = new ArrayList<Double>();
			List<Double> rate = new ArrayList<Double>();
			for(int i=0;i<websuccess.size();i++){
				double succ = websuccess.get(i)+clientsuccess.get(i);
				success.add(succ);
				double fai = webfaile.get(i)+clientfaile.get(i);
				faile.add(fai);
				double ra = 0.0;
				if((succ+fai)>0)
					ra = succ*100/(succ+fai);
				rate.add(ChartDataUtil.doubleFormat(ra,"#.##"));
			}
	 		dataMap1.put("name", "成功订单");
	 		dataMap1.put("type", "spline");
	 		dataMap1.put("yAxis", 1);
	 		dataMap1.put("data", success);
	 		Map<String,Object> dataMap2=new HashMap<String, Object>();
	 		dataMap2.put("name", "失败订单");
	 		dataMap2.put("type", "spline");
	 		dataMap2.put("yAxis", 1);
	 		dataMap2.put("data", faile);
	 		Map<String,Object> dataMap3=new HashMap<String, Object>();
	 		dataMap3.put("name", "成功订单");
	 		dataMap3.put("type", "column");
	 		dataMap3.put("yAxis", 0);
	 		dataMap3.put("data", rate);
	 		colsContentList.add(dataMap3);
	 		colsContentList.add(dataMap1);
	 		colsContentList.add(dataMap2);
			
			return ChartDataUtil.getChartJson("订单成功失败统计", "", colsTitleList, colsContentList).toString();
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
			Map<String,Object> dataMap1=new HashMap<String, Object>();
			Map<String,Object> dataMap2=new HashMap<String, Object>();
			Map<String,Object> dataMap3=new HashMap<String, Object>();
			List<Double> success = new ArrayList<Double>();
			List<Double> faile = new ArrayList<Double>();
			List<Double> rate = new ArrayList<Double>();
			if (type.equals("web")) {
				success = map.get(Type.WEBSUCCESS);
				faile = map.get(Type.WEBFAILE);
			}else if (type.equals("client")) {
				success = map.get(Type.CLIENTSUCCESS);
				faile = map.get(Type.CLIENTFAILE);
			}
			dataMap1.put("name", name + "成功订单");
			dataMap1.put("type", "spline");
			dataMap1.put("yAxis", 1);
			dataMap1.put("data", success);
			dataMap2.put("name", name + "失败订单");
			dataMap2.put("type", "spline");
			dataMap2.put("yAxis", 1);
			dataMap2.put("data", faile);
			for (int i = 0; i < success.size(); i++) {
				double ra = 0.0;
				if((success.get(i) + faile.get(i))>0)
					ra = success.get(i) * 100
					/ (success.get(i) + faile.get(i));
				rate.add(ChartDataUtil.doubleFormat(ra,"#.##"));
			}
			dataMap3.put("name", "web成功订单");
			dataMap3.put("type", "column");
			dataMap3.put("yAxis", 0);
			dataMap3.put("data", rate);
		 	colsContentList.add(dataMap3);
		 	colsContentList.add(dataMap1);
		 	colsContentList.add(dataMap2);
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
			return ChartDataUtil.getChartJson(name+"订单成功失败统计", "", colsTitleList, colsContentList, startTime, endTime).toString();
		}else{
			start = start.replace("-", "").replace(" ", "").replace(":", "");
			end = end.replace("-", "").replace(" ", "").replace(":", "");
			if(start.length()==8){
				start = start + "000000";
			}
			if(end.length()==8){
				end = end + "000000";
			}
			String ty = "1";
			if(type.equals("client")){
				ty = "3";
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
			Map<String,Object> dataMap2=new HashMap<String, Object>();
			Map<String,Object> dataMap3=new HashMap<String, Object>();
			List<Double> success = new ArrayList<Double>();
			List<Double> faile = new ArrayList<Double>();
			List<Double> rate = new ArrayList<Double>();
			if (type.equals("web")) {
				success = map.get(Type.WEBSUCCESS);
				faile = map.get(Type.WEBFAILE);
			}else if (type.equals("client")) {
				success = map.get(Type.CLIENTSUCCESS);
				faile = map.get(Type.CLIENTFAILE);
			}
			dataMap1.put("name", name + "成功订单");
			dataMap1.put("type", "spline");
			dataMap1.put("yAxis", 1);
			dataMap1.put("data", success);
			dataMap2.put("name", name + "失败订单");
			dataMap2.put("type", "spline");
			dataMap2.put("yAxis", 1);
			dataMap2.put("data", faile);
			for (int i = 0; i < success.size(); i++) {
				double ra = 0.0;
				if((success.get(i) + faile.get(i))>0)
					ra = success.get(i) * 100
					/ (success.get(i) + faile.get(i));
				rate.add(ChartDataUtil.doubleFormat(ra,"#.##"));
			}
			dataMap3.put("name", "web成功订单");
			dataMap3.put("type", "column");
			dataMap3.put("yAxis", 0);
			dataMap3.put("data", rate);
		 	colsContentList.add(dataMap3);
		 	colsContentList.add(dataMap1);
		 	colsContentList.add(dataMap2);
			return ChartDataUtil.getChartJson( name+"订单统计", "", colsTitleList, colsContentList).toString();
		}
	}

}
