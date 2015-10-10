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

import com.cplatform.mall.dc.service.PVService;
import com.cplatform.mall.dc.service.PVService.Type;
import com.cplatform.mall.dc.utils.ChartDataUtil;
/**
 * 订单转化率统计 <br>
 * <p>
 * Copyright: Copyright (c) 2014-4-29 上午10:07:06
 * <p>
 * Company: 苏州宽连信息技术有限公司
 * <p>
 * 
 * @author guyu@c-platform.com
 * @version 1.0.0
 */
@Controller
@RequestMapping("/PV")
public class PVController {

	@Autowired
	PVService orderService;
	
	public static final String RET_CODE_SUCCESS = "0000";
	public static final String RET_CODE_NO_UPDATE = "0001";

	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String tomain(Model model,String url_name) throws SQLException {
		if(url_name!=null){
			model.addAttribute("type",url_name);
		}
		model.addAttribute("req_url","/PV/getWebOrder");
		return "/right/view";
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
		if(start==null&&end==null&&type!=null){
			Map<Type,List>  map = orderService.getInit(type);
			if(map==null||map.size()==0){
				JSONObject obj = new JSONObject();
				obj.put("retCode", RET_CODE_NO_UPDATE);
				return obj.toString();
			}
			List<String> colsTitleList=map.get(Type.FIELD);
			List<Map> colsContentList=new ArrayList<Map>();
			Map<String,Object>dataMap1=new HashMap<String, Object>();
			if(type!=null){
				dataMap1.put("name", "访问量");
				dataMap1.put("data",map.get(Type.VALUE));
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
			return ChartDataUtil.getChartJson("访问量统计", "", colsTitleList, colsContentList, startTime, endTime).toString();
		}else if(type != null){
			start = start.replace("-", "").replace(" ", "").replace(":", "");
			end = end.replace("-", "").replace(" ", "").replace(":", "");
			if(start.length()==8){
				start = start + "000000";
			}
			if(end.length()==8){
				end = end + "000000";
			}
			Map<Type,List>  map = orderService.getAllOrder(start,end,Integer.parseInt(searchType),type);
			if(map==null||map.size()==0){
				JSONObject obj = new JSONObject();
				obj.put("retCode", RET_CODE_NO_UPDATE);
				return obj.toString();
			}
			List<String> colsTitleList=map.get(Type.FIELD);
			List<Map> colsContentList=new ArrayList<Map>();
			Map<String,Object> dataMap1=new HashMap<String, Object>();
			dataMap1.put("name", "访问量");
	 		dataMap1.put("data", map.get(Type.VALUE));
	 		colsContentList.add(dataMap1);
			return ChartDataUtil.getChartJson("访问量统计", "", colsTitleList, colsContentList).toString();
		}
		return null;
	}
	

}
