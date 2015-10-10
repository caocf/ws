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
import com.cplatform.mall.dc.model.OrderInfo;
import com.cplatform.mall.dc.utils.AppConfig;
import com.cplatform.mall.dc.utils.ChartDataUtil;
import com.cplatform.mall.dc.utils.DateUtil;

/**
 * 支付订单统计 <br>
 * <p>
 * Copyright: Copyright (c) 2014-4-29 上午10:07:06
 * <p>
 * Company: 苏州宽连信息技术有限公司
 * <p>
 * 
 * @author guyu@c-platform.com
 * @version 1.0.0
 */
@Service
public class OrderService {
	
	@Autowired
	private AppConfig config;

	@Autowired
	DbHelper dbHelper;

	//时间差
	public static int TIME_DIF = 10;
	//取数据量
	public static int SEARCH_NUM = 20;
	
	private String START_TIME_CACHE;
	
	private Map<Type,List> cache = new HashMap<Type,List>();
	
	@PostConstruct
	public void init() throws SQLException {
		SEARCH_NUM = config.getWebShowNum();
		String startTime = DateUtil.getTime(-(TIME_DIF*(SEARCH_NUM-1)));
		startTime = startTime.substring(0,11)+"0";
		START_TIME_CACHE = startTime;
		String sql = "select * from t_act_order_num where substr(collect_time,0,12)>=?";
		List<Map<String, String>> list = dbHelper.getMapList(sql,new Object[]{startTime});
		List<OrderInfo> orderList = new ArrayList<OrderInfo>();
		if(list!=null&&list.size()>0){
			for(Map<String,String> map:list){
				OrderInfo order = new OrderInfo();
				order.setId(map.get("seq_id"));
				order.setType(map.get("act_type"));
				order.setCollectTime(map.get("collect_time"));
				order.setFlowNum(ChartDataUtil.doubleFormat(Double.parseDouble(map.get("flow_num")), "#.##"));
				orderList.add(order);
			}
		}
		// 列名称
		List<String> seriesList = new ArrayList<String>();
		List<Double> webList=new ArrayList<Double>();
		List<Double> wapList=new ArrayList<Double>();
		List<Double> clientList=new ArrayList<Double>();
		if(orderList.size()>0){
			//存入时间
			for(int i=0;i<SEARCH_NUM;i++){
				Double webvalue = 0D;
				Double wapvalue = 0D;
				Double clientvalue = 0D;
				String time = DateUtil.getTime(startTime, i*TIME_DIF);
				if(i==0){
					List<String> start = new ArrayList<String>();
					start.add(DateUtil.getWebTime(time));
					cache.put(Type.START, start);
				}
				if(i==(SEARCH_NUM-1)){
					List<String> end = new ArrayList<String>();
					end.add(DateUtil.getWebTime(time));
					cache.put(Type.END, end);
				}
				seriesList.add(DateUtil.getShowTime(time));
				for(OrderInfo o : orderList){
					if(o.getCollectTime().substring(0,12).equals(time)){
						if(o.getType().equals("1")){
							webvalue = o.getFlowNum();
						}
						if(o.getType().equals("2")){
							wapvalue = o.getFlowNum();
						}
						if(o.getType().equals("3")){
							clientvalue = o.getFlowNum();
						}
					}
					
				}
				webList.add(webvalue);
				wapList.add(wapvalue);
				clientList.add(clientvalue);
			}
		}
		cache.put(Type.FIELD, seriesList);
		cache.put(Type.WEB, webList);
		cache.put(Type.WAP, wapList);
		cache.put(Type.CLIENT, clientList);
	}

	public enum Type { 
		//1、web 2、wap 3：client
		WEB("1"), WAP("2"), CLIENT("3"), FIELD("seriesList"), START("start"), END("end");
		private final String type;

		Type(String type) {
			this.type = type;
		}

		public String getType() {
			return type;
		}
	}  	
	
	
	
	/**
	 * 获取订单数总量
	 * 
	 * @param condition
	 *            条件模型对象
	 * @return 数据列表
	 * @throws SQLException
	 */
	public Map<Type,List> getInit() throws SQLException {
		String startTime = DateUtil.getTime(-(TIME_DIF*(SEARCH_NUM-1)));
		startTime = startTime.substring(0,11)+"0";
		//刷新时若时间不变，不更新
		if (!startTime.equals(START_TIME_CACHE)) {
			START_TIME_CACHE = startTime;
			init();
		}
		return cache;
	}
	
	
	public Map<Type,List> getAllOrder(String start, String end, int type) throws SQLException {
		if(!start.endsWith("0")){
			start = DateUtil.getTime(start,10);
			start = start.substring(0,11)+"0";
		}
		end = end.substring(0,11)+"0";
		int num = 0;
		String sql = "";
		//type:1、分 2、小时 3、天 4、周  5、月
		if(type == 1){
			try {
				num = DateUtil.getDifTime(start, end)/TIME_DIF+1;
			} catch (ParseException e) {
				e.printStackTrace();
			}
			sql = "select * from t_act_order_num where substr(collect_time,0,12)>=? and substr(collect_time,0,12)<=?";
		}else if(type == 2){
			try {
				num = DateUtil.getDifTime(start, end)/(TIME_DIF*6)+1;
				sql = "select act_type,substr(collect_time,0,10) collect_time,sum(flow_num) flow_num " +
						"from t_act_order_num " +
						"where substr(collect_time,0,12)>=? and substr(collect_time,0,12)<=?" +
						"group by act_type,substr(collect_time,0,10)";
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
				sql = "select act_type,substr(collect_time,0,8) collect_time,sum(flow_num) flow_num " +
						"from t_act_order_num " +
						"where substr(collect_time,0,12)>=? and substr(collect_time,0,12)<=?" +
						"group by act_type,substr(collect_time,0,8)";
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}else if(type == 5){
			try {
				num = DateUtil.getDifMonth(start, end) + 1;
				sql = "select act_type,substr(collect_time,0,6) collect_time,sum(flow_num) flow_num " +
						"from t_act_order_num " +
						"where substr(collect_time,0,12)>=? and substr(collect_time,0,12)<=?" +
						"group by act_type,substr(collect_time,0,6)";
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		Map<Type,List> listMap = new HashMap<Type,List>();
		List<Map<String, String>> list = dbHelper.getMapList(sql,new Object[]{start.substring(0,12),end.substring(0,12)});
		// 列名称
		List<String> seriesList = new ArrayList<String>();
		List<Double> webList=new ArrayList<Double>();
		List<Double> wapList=new ArrayList<Double>();
		List<Double> clientList=new ArrayList<Double>();
		List<OrderInfo> orderList = new ArrayList<OrderInfo>();
		if(list!=null&&list.size()>0){
			for(Map<String,String> map:list){
				OrderInfo order = new OrderInfo();
				order.setType(map.get("act_type"));
				order.setCollectTime(map.get("collect_time"));
				order.setFlowNum(ChartDataUtil.doubleFormat(Double.parseDouble(map.get("flow_num")), "#.##"));
				orderList.add(order);
			}
		}
		if(orderList.size()>0&&num>0){
			//存入时间
			for(int i=0;i<num;i++){
				Double webvalue = 0D;
				Double wapvalue = 0D;
				Double clientvalue = 0D;
				String time = "";
				if(type==1){
					time = DateUtil.getTime(start, i * TIME_DIF);
				}else if(type==2){
					time = DateUtil.getTime(start, i* TIME_DIF *6).substring(0,10);
				}else if (type == 3) {
					time = DateUtil.getTime(start, i * TIME_DIF * 6 * 24)
					.substring(0, 8);
				} else if (type == 4) {
					time = DateUtil.getTime(start, i * TIME_DIF * 6 * 24 * 7)
					.substring(0, 8);
				}else if (type == 5) {
					time = DateUtil.getMonth(start, i).substring(0, 6);
				}
				String showTime = DateUtil.getShowTime(time);
				String endTime = time;
				if(type == 4){
					endTime = DateUtil.getTime(start, (i * 7 + 6) * TIME_DIF * 6 * 24)
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
				for(OrderInfo o : orderList){
					if(type==1){
						if(o.getCollectTime().substring(0,12).equals(time)){
							if(o.getType().equals("1")){
								webvalue = o.getFlowNum();
							}
							if(o.getType().equals("2")){
								wapvalue = o.getFlowNum();
							}
							if(o.getType().equals("3")){
								clientvalue = o.getFlowNum();
							}
						}
					}else if(type == 2){
						if(o.getCollectTime().substring(0,10).equals(time)){
							if(o.getType().equals("1")){
								webvalue = o.getFlowNum();
							}
							if(o.getType().equals("2")){
								wapvalue = o.getFlowNum();
							}
							if(o.getType().equals("3")){
								clientvalue = o.getFlowNum();
							}
						}
					}else if(type == 3){
						if(o.getCollectTime().substring(0,8).equals(time)){
							if(o.getType().equals("1")){
								webvalue = o.getFlowNum();
							}
							if(o.getType().equals("2")){
								wapvalue = o.getFlowNum();
							}
							if(o.getType().equals("3")){
								clientvalue = o.getFlowNum();
							}
						}
					}else if(type == 4){
						for(int j=0;j<7;j++){
							String newDate = DateUtil.getTime(time,60*24*j).substring(0,8);
							if(o.getCollectTime().substring(0,8).equals(newDate)){
								if(o.getType().equals("1")){
									webvalue = o.getFlowNum();
								}
								if(o.getType().equals("2")){
									wapvalue = o.getFlowNum();
								}
								if(o.getType().equals("3")){
									clientvalue = o.getFlowNum();
								}
							}
						}
					}else if(type == 5){
						if(o.getCollectTime().substring(0,6).equals(time)){
							if(o.getType().equals("1")){
								webvalue = o.getFlowNum();
							}
							if(o.getType().equals("2")){
								wapvalue = o.getFlowNum();
							}
							if(o.getType().equals("3")){
								clientvalue = o.getFlowNum();
							}
						}
					}
				}
				webList.add(webvalue);
				wapList.add(wapvalue);
				clientList.add(clientvalue);
				seriesList.add(showTime);
			}
		}
		// 列名称
		listMap.put(Type.FIELD, seriesList);
		listMap.put(Type.WEB, webList);
		listMap.put(Type.WAP, wapList);
		listMap.put(Type.CLIENT, clientList);
		return listMap;
	}
	

}
