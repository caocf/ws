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
import com.cplatform.mall.dc.model.FlowInfo;
import com.cplatform.mall.dc.utils.AppConfig;
import com.cplatform.mall.dc.utils.ChartDataUtil;
import com.cplatform.mall.dc.utils.DateUtil;

/**
 * FlowService.java <br>
 * <p>
 * Copyright: Copyright (c) 2014-5-4 下午12:00:35
 * <p>
 * Company: 苏州宽连信息技术有限公司
 * <p>
 * 
 * @author guyu@c-platform.com
 * @version 1.0.0
 */
@Service
public class FlowService {

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
		String sql = "select * from t_com_flow_monitor where substr(collect_time,0,12)>=?";
		List<Map<String, String>> list = dbHelper.getMapList(sql,new Object[]{startTime});
		List<FlowInfo> orderList = new ArrayList<FlowInfo>();
		if(list!=null&&list.size()>0){
			for(Map<String,String> map:list){
				FlowInfo order = new FlowInfo();
				order.setId(map.get("seq_id"));
				order.setType(map.get("type"));
				order.setSpCode(map.get("sp_code"));
				order.setCollectTime(map.get("collect_time"));
				order.setNum(ChartDataUtil.doubleFormat(Double.parseDouble(map.get("flow_num")), "#.##"));
				orderList.add(order);
			}
		}
		// 列名称
		List<String> seriesList = new ArrayList<String>();
		List<Double> mo85=new ArrayList<Double>();
		List<Double> mt85=new ArrayList<Double>();
		List<Double> mo18=new ArrayList<Double>();
		List<Double> mt18=new ArrayList<Double>();
		if(orderList.size()>0){
			//存入时间
			for(int i=0;i<SEARCH_NUM;i++){
				Double vmo85 = 0D;
				Double vmt85 = 0D;
				Double vmo18 = 0D;
				Double vmt18 = 0D;
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
				for(FlowInfo o : orderList){
					if(o.getCollectTime().substring(0,12).equals(time)){
						if(o.getSpCode().equals("10658585")){
							if(o.getType().equalsIgnoreCase("mo"))
								vmo85 = o.getNum();
							if(o.getType().equalsIgnoreCase("mt"))
								vmt85 = o.getNum();
						}
						if(o.getSpCode().equals("10658618")){
							if(o.getType().equalsIgnoreCase("mo"))
								vmo18 = o.getNum();
							if(o.getType().equalsIgnoreCase("mt"))
								vmt18 = o.getNum();
						}
					}
					
				}
				mo85.add(vmo85);
				mt85.add(vmt85);
				mo18.add(vmo18);
				mt18.add(vmt18);
			}
		}
		cache.put(Type.FIELD, seriesList);
		cache.put(Type.mo85, mo85);
		cache.put(Type.mt85, mt85);
		cache.put(Type.mo18, mo18);
		cache.put(Type.mt18, mt18);
	}

	public enum Type { 
		mo85("10658585,mo"),mt85("10658585,mt"), mo18("10658618,mo"), mt18("10658618,mt"), FIELD("seriesList"), START("start"), END("end");
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
	
	
	public Map<Type,List> getAllOrder(String startTime, String endTime,int type) throws SQLException {
		if(!startTime.endsWith("0")){
			startTime = DateUtil.getTime(startTime,10);
			startTime = startTime.substring(0,11)+"0";
		}
		endTime = endTime.substring(0,11)+"0";
		int num = 0;
		String sql = "";
		//type:1、分 2、小时 3、天 4、周  5、月
		if(type == 1){
			try {
				num = DateUtil.getDifTime(startTime, endTime)/TIME_DIF+1;
			} catch (ParseException e) {
				e.printStackTrace();
			}
			sql = "select * from t_com_flow_monitor where substr(collect_time,0,12)>=? and substr(collect_time,0,12)<=?";
		}else if(type == 2){
			try {
				num = DateUtil.getDifHour(startTime, endTime) + 1;
				sql = "select sp_code,type,substr(collect_time,0,10) collect_time,sum(flow_num) flow_num " +
				"from t_com_flow_monitor " +
				"where substr(collect_time,0,12)>=? and substr(collect_time,0,12)<=?" +
				"group by type,sp_code,substr(collect_time,0,10)";
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}else if(type == 3||type ==4){
				try {
					if(type==3)
						num = DateUtil.getDifDay(startTime, endTime) + 1;
					else if(type == 4)
						num = DateUtil.getDifDay(startTime, endTime) / 7 + 1;
					sql = "select sp_code,type,substr(collect_time,0,8) collect_time,sum(flow_num) flow_num "
						+ "from t_com_flow_monitor "
						+ "where substr(collect_time,0,12)>=? and substr(collect_time,0,12)<=?  "
						+ "group by type,sp_code,substr(collect_time,0,8)";
				} catch (ParseException e) {
					e.printStackTrace();
				}
		}else if (type == 5) {
			try {
				num = DateUtil.getDifMonth(startTime, endTime) + 1;
				sql = "select sp_code,type,substr(collect_time,0,6) collect_time,sum(flow_num) flow_num "
						+ "from t_com_flow_monitor "
						+ "where substr(collect_time,0,12)>=? and substr(collect_time,0,12)<=?  "
						+ "group by type,sp_code,substr(collect_time,0,6)";
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		Map<Type,List> listMap = new HashMap<Type,List>();
		List<Map<String, String>> list = dbHelper.getMapList(sql,new Object[]{startTime.substring(0,12),endTime.substring(0,12)});
		// 列名称
		List<String> seriesList = new ArrayList<String>();
		List<Double> mo85=new ArrayList<Double>();
		List<Double> mt85=new ArrayList<Double>();
		List<Double> mo18=new ArrayList<Double>();
		List<Double> mt18=new ArrayList<Double>();
		List<FlowInfo> orderList = new ArrayList<FlowInfo>();
		if(list!=null&&list.size()>0){
			for(Map<String,String> map:list){
				FlowInfo order = new FlowInfo();
				order.setId(map.get("seq_id"));
				order.setType(map.get("type"));
				order.setSpCode(map.get("sp_code"));
				order.setCollectTime(map.get("collect_time"));
				order.setNum(ChartDataUtil.doubleFormat(Double.parseDouble(map.get("flow_num")), "#.##"));
				orderList.add(order);
			}
		}
		if(orderList.size()>0&&num>0){
			//存入时间
			for(int i=0;i<num;i++){
				Double vmo85 = 0D;
				Double vmt85 = 0D;
				Double vmo18 = 0D;
				Double vmt18 = 0D;
				String time = "";
				if(type==1){
					time = DateUtil.getTime(startTime, i*TIME_DIF);
				}else if(type==2){
					time = DateUtil.getTime(startTime, i*TIME_DIF*6).substring(0,10);
				} else if (type == 3) {
					time = DateUtil.getTime(startTime, i * TIME_DIF * 6 * 24)
					.substring(0, 8);
				} else if (type == 4) {
					time = DateUtil.getTime(startTime, i * TIME_DIF * 6 * 24 * 7)
					.substring(0, 8);
				}else if (type == 5) {
					time = DateUtil.getMonth(startTime, i).substring(0, 6);
				}
				String showTime = DateUtil.getShowTime(time);
				String end = time;
				if(type == 4){
					end = DateUtil.getTime(startTime, (i * 7 + 6) * TIME_DIF * 6 * 24)
					.substring(0, 8);
					int dif = 0;
					try {
						dif = DateUtil.getDifTime(end, endTime.substring(0, 8));
					} catch (ParseException e) {
						e.printStackTrace();
					}
					if(dif<0){
						end = endTime.substring(0, 8);
					}
					showTime = showTime + "~" + DateUtil.getShowTime(end);
				}
				for (FlowInfo o : orderList) {
					if (type == 1) {
						if (o.getCollectTime().substring(0, 12).equals(time)) {

							if (o.getSpCode().equals("10658585")) {
								if (o.getType().equalsIgnoreCase("mo"))
									vmo85 = o.getNum();
								if (o.getType().equalsIgnoreCase("mt"))
									vmt85 = o.getNum();
							}
							if (o.getSpCode().equals("10658618")) {
								if (o.getType().equalsIgnoreCase("mo"))
									vmo18 = o.getNum();
								if (o.getType().equalsIgnoreCase("mt"))
									vmt18 = o.getNum();
							}
						}
					} else if(type == 2){
						if (o.getCollectTime().substring(0, 10).equals(time)) {

							if (o.getSpCode().equals("10658585")) {
								if (o.getType().equalsIgnoreCase("mo"))
									vmo85 = o.getNum();
								if (o.getType().equalsIgnoreCase("mt"))
									vmt85 = o.getNum();
							}
							if (o.getSpCode().equals("10658618")) {
								if (o.getType().equalsIgnoreCase("mo"))
									vmo18 = o.getNum();
								if (o.getType().equalsIgnoreCase("mt"))
									vmt18 = o.getNum();
							}
						}

					} else if(type == 3){
						if (o.getCollectTime().substring(0, 8).equals(time)) {

							if (o.getSpCode().equals("10658585")) {
								if (o.getType().equalsIgnoreCase("mo"))
									vmo85 = o.getNum();
								if (o.getType().equalsIgnoreCase("mt"))
									vmt85 = o.getNum();
							}
							if (o.getSpCode().equals("10658618")) {
								if (o.getType().equalsIgnoreCase("mo"))
									vmo18 = o.getNum();
								if (o.getType().equalsIgnoreCase("mt"))
									vmt18 = o.getNum();
							}
						}
					} else if(type == 4){
						for(int j=0;j<7;j++){
							String newDate = DateUtil.getTime(time,60*24*j).substring(0,8);
							if (o.getCollectTime().substring(0, 8).equals(
									newDate)) {
								if (o.getSpCode().equals("10658585")) {
									if (o.getType().equalsIgnoreCase("mo"))
										vmo85 = o.getNum();
									if (o.getType().equalsIgnoreCase("mt"))
										vmt85 = o.getNum();
								}
								if (o.getSpCode().equals("10658618")) {
									if (o.getType().equalsIgnoreCase("mo"))
										vmo18 = o.getNum();
									if (o.getType().equalsIgnoreCase("mt"))
										vmt18 = o.getNum();
								}
							}
						}
						
					}else if(type == 5){
						if (o.getCollectTime().substring(0, 6).equals(time)) {
							if (o.getSpCode().equals("10658585")) {
								if (o.getType().equalsIgnoreCase("mo"))
									vmo85 = o.getNum();
								if (o.getType().equalsIgnoreCase("mt"))
									vmt85 = o.getNum();
							}
							if (o.getSpCode().equals("10658618")) {
								if (o.getType().equalsIgnoreCase("mo"))
									vmo18 = o.getNum();
								if (o.getType().equalsIgnoreCase("mt"))
									vmt18 = o.getNum();
							}
						}
					}
				}
				mo85.add(vmo85);
				mt85.add(vmt85);
				mo18.add(vmo18);
				mt18.add(vmt18);
				seriesList.add(showTime);
			}
		}
		// 列名称
		listMap.put(Type.FIELD, seriesList);
		listMap.put(Type.mo85, mo85);
		listMap.put(Type.mt85, mt85);
		listMap.put(Type.mo18, mo18);
		listMap.put(Type.mt18, mt18);
		return listMap;
	}

}
