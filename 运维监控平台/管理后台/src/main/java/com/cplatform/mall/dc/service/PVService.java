package com.cplatform.mall.dc.service;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplatform.dbhelp.DbHelper;
import com.cplatform.mall.dc.entity.DcMenu;
import com.cplatform.mall.dc.utils.ChartDataUtil;
import com.cplatform.mall.dc.utils.DateUtil;

/**
 * PVService.java <br>
 * <p>
 * Copyright: Copyright (c) 2014-4-30 下午02:04:42
 * <p>
 * Company: 苏州宽连信息技术有限公司
 * <p>
 * 
 * @author guyu@c-platform.com
 * @version 1.0.0
 */
@Service
public class PVService {
	
	@Autowired
	DbHelper dbHelper;

	// 时间差
	public static int TIME_DIF = 5;
	// 取数据量
	public static int SEARCH_NUM = 20;

	public static final Map PVMenu = new HashMap();
	
	private static String channel_id = "100100100100";

	private Map<Type, List> cache = new HashMap<Type, List>();

	private String getStartTime(String time){
		int lastNum = Integer.parseInt(time.substring(11,12));
		if(lastNum>5){
			time = time.substring(0,11)+"5";
		}else if(lastNum<5){
			time = time.substring(0,11)+"0";
		}
		return time;
	}
	
	private String getEndTime(String time){
		int lastNum = Integer.parseInt(time.substring(11,12));
		if(lastNum>5){
			time = time.substring(0,11)+"5";
		}else if(lastNum<5){
			time = time.substring(0,11)+"0";
		}
		return time;
	}
	
	@PostConstruct
	public void init() throws SQLException {
		String sql = "select url_name from t_web_flow_monitor t group by url_name";
		List<Map<String, String>> list = dbHelper.getMapList(sql,new Object[]{});
		List<DcMenu> menulist = new ArrayList<DcMenu>();
		if(list!=null&&list.size()>0){
			for(int i=0;i<list.size();i++){
				Map<String, String> obj = list.get(i);
				String url_name = obj.get("url_name");
				DcMenu menu = new DcMenu();
				int num = 0;
				if(i<10){
					num = (i+1)*100;
				}else if(i>=10&&i<100){
					num = (i+1)*10;
				}else{
					num = i;
				}
				menu.setMenuCode(channel_id+num);
				menu.setMenuName(url_name);
				menu.setMenuUrl("/PV?url_name="+url_name);
				menu.setParentCode(channel_id);
				menu.setLeafYn(true);
				menulist.add(menu);
			}
		}
		PVMenu.put("key", channel_id);
		PVMenu.put("list", menulist);
	}
	


	/**
	 * 获取订单数总量
	 * 
	 * @param condition
	 *            条件模型对象
	 * @return 数据列表
	 * @throws SQLException
	 */
	public Map<Type,List> getInit(String url_name) throws SQLException {
		String startTime = DateUtil.getTime(-(TIME_DIF*(SEARCH_NUM-1)));
		startTime = getStartTime(startTime);
		String endTime = DateUtil.getTime(0);
		return getAllOrder(startTime, endTime, 1,url_name);
	}


	public Map<Type,List> getAllOrder(String start, String end, int type,String url_name) throws SQLException {
		start = getStartTime(start);
		end = getEndTime(end);
		int num = 0;
		String sql = "";
		//type:1、分 2、小时 3、天 4、周  5、月
		if(type == 1){
			try {
				num = DateUtil.getDifTime(start, end)/TIME_DIF + 1;
				sql = "select substr(url_name,0,13) url_name,substr(collect_time,0,12) collect_time,sum(flow_num) flow_num from t_web_flow_monitor  " +
						"where substr(collect_time,0,12) >=? and substr(collect_time,0,12)<=? and url_name = ? " +
						"group by substr(url_name,0,13) ,substr(collect_time,0,12)";
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}else if(type == 2){
			try {
				num = DateUtil.getDifTime(start, end)/(TIME_DIF * 12) + 1;
				sql = " select substr(url_name,0,13) url_name,substr(collect_time,0,10) collect_time,sum(flow_num) flow_num from t_web_flow_monitor  " +
						"where substr(collect_time,0,12) >=? and substr(collect_time,0,12)<=? and url_name = ?  " +
						"group by substr(url_name,0,13) ,substr(collect_time,0,10)";
				type=2;
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}else if(type == 3||type == 4){
			try {
				if(type == 3)
					num = DateUtil.getDifDay(start, end) + 1;
				else if(type ==4)
					num = DateUtil.getDifDay(start, end) / 7 + 1;
				sql = " select substr(url_name,0,13) url_name,substr(collect_time,0,8) collect_time,sum(flow_num) flow_num from t_web_flow_monitor  " +
						"where substr(collect_time,0,12) >=? and substr(collect_time,0,12)<=? and url_name = ?  " +
						"group by substr(url_name,0,13) ,substr(collect_time,0,8)";
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}else if(type == 5){
			try {
				num = DateUtil.getDifMonth(start, end)+1;
				sql = " select substr(url_name,0,13) url_name,substr(collect_time,0,6) collect_time,sum(flow_num) flow_num from t_web_flow_monitor  " +
						"where substr(collect_time,0,12) >=? and substr(collect_time,0,12)<=? and url_name = ?  " +
						"group by substr(url_name,0,13) ,substr(collect_time,0,6)";
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		// 列名称
		List<String> seriesList=new ArrayList<String>();
		//数据集合
		List<Double> valuelist=new ArrayList<Double>();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<Type,List> listMap = new HashMap<Type,List>();
		if (num > 0) {
			list = dbHelper.getMapList(sql,new Object[]{start.substring(0,12),end.substring(0,12),url_name});
			for (int i = 0; i < num; i++) {
				String time = "";
				if (type == 1) {
					time = DateUtil.getTime(start, i * TIME_DIF);
				} else if (type == 2) {
					time = DateUtil.getTime(start, i * TIME_DIF * 2 * 6)
							.substring(0, 10);
				} else if (type == 3) {
					time = DateUtil.getTime(start, i * TIME_DIF * 2 * 6 * 24)
					.substring(0, 8);
				} else if (type == 4) {
					time = DateUtil.getTime(start, i * TIME_DIF * 2 * 6 * 24 * 7)
					.substring(0, 8);
				} else if (type == 5) {
					time = DateUtil.getMonth(start, i).substring(0, 6);
				}
				String showTime = DateUtil.getShowTime(time);
				String endTime = time;
				if(type == 4){
					endTime = DateUtil.getTime(start, (i * 7 + 6) * TIME_DIF * 2 * 6 * 24)
					.substring(0, 8);
					int dif = 0;
					try {
						dif = DateUtil.getDifTime(endTime, end.substring(0, 8));
					} catch (ParseException e) {
						e.printStackTrace();
					}
					if(dif<0){
						endTime = end.substring(0, 8);
					}
					showTime = showTime + "~" + DateUtil.getShowTime(endTime);
				}
				Double value = 0D;
				for (Map<String, String> map : list) {
					if (type == 1) {
						if (map.get("collect_time").substring(0, 12).equals(
								time)) {
							value += Double.parseDouble(map.get("flow_num"));
						}
						if(i==0){
							List<String> startTemp = new ArrayList<String>();
							startTemp.add(DateUtil.getWebTime(time));
							listMap.put(Type.START, startTemp);
						}
						if(i==(SEARCH_NUM-1)){
							List<String> endTemp = new ArrayList<String>();
							endTemp.add(DateUtil.getWebTime(time));
							listMap.put(Type.END, endTemp);
						}
						
					} else if (type == 2) {
						if (map.get("collect_time").substring(0, 10).equals(
								time)) {
							value += ChartDataUtil.doubleFormat(Double.parseDouble(map.get("flow_num")), "#.##");
						}
					} else if (type == 3) {
						if (map.get("collect_time").substring(0, 8).equals(
								time)) {
							value += ChartDataUtil.doubleFormat(Double.parseDouble(map.get("flow_num")), "#.##");
						}
					} else if (type == 4) {
						for(int j=0;j<7;j++){
							String newDate = DateUtil.getTime(time,60*24*j).substring(0,8);
							if (map.get("collect_time").substring(0, 8).equals(
									newDate)) {
								value += ChartDataUtil.doubleFormat(Double.parseDouble(map.get("flow_num")), "#.##");
							}
						}
					} else if (type == 5) {
						if (map.get("collect_time").substring(0, 6).equals(
								time)) {
							value += ChartDataUtil.doubleFormat(Double.parseDouble(map.get("flow_num")), "#.##");
						}
					}
				}
				valuelist.add(value);
				seriesList.add(showTime);
			}
		}
		listMap.put(Type.FIELD, seriesList);
		listMap.put(Type.VALUE, valuelist);
		return listMap;
	}
	
	public enum Type { 
		VALUE("value"), FIELD("seriesList"), START("start"), END("end");
		private final String type;

		Type(String type) {
			this.type = type;
		}

		public String getType() {
			return type;
		}
	}  	
	
}
