package com.cplatform.mall.dc.service;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplatform.dbhelp.DbHelper;
import com.cplatform.mall.dc.model.MsOsMpstatInfo;
import com.cplatform.mall.dc.utils.DateUtil;

/**
 * MsOsMpstatService.java <br>
 * <p>
 * Copyright: Copyright (c) 2014-5-9 上午10:01:32
 * <p>
 * Company: 苏州宽连信息技术有限公司
 * <p>
 * 
 * @author guyu@c-platform.com
 * @version 1.0.0
 */
@Service
public class MsOsMpstatService {

	@Autowired
	DbHelper dbHelper;

	//时间
	public static int TIME_DIF = 5;
	//取数据量
	public static int SEARCH_NUM = 100;
	
	private String getStartTime(String time){
		int lastNum = Integer.parseInt(time.substring(11,12));
		if(lastNum>5){
			time = DateUtil.getTime(time, 5).substring(0,11)+"1";
		}else if(lastNum<5){
			time = time.substring(0,11)+"5";
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
	
	
	public Map<Type,List> getField(String id){
		String endTime = getEndTime(DateUtil.getTime(0));
		String startTime = DateUtil.getTime(endTime,-(TIME_DIF*(SEARCH_NUM-1)));
		return getField(startTime,endTime,id);
	}
	
	public Map<Type,List> getField(String startTime,String endTime,String id){
		startTime = getStartTime(startTime);
		endTime = getEndTime(endTime);
		String sql = "select virtual_seq,to_char(ms_time,'yyyyMMddhh24mi') collectTime,ms_user cpu_user,ms_sys cpu_sys,ms_iowait iowait  " +
				" from ms_os_mpstat where virtual_seq = ? and to_char(ms_time,'yyyyMMddhh24mi')>=? " +
				" and to_char(ms_time,'yyyyMMddhh24mi')<=?";
		List<Map<String, String>> list2 = null;
		try {
			list2 = dbHelper.getMapList(sql,new Object[]{id,startTime.substring(0,12),endTime.substring(0,12)});
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		List<MsOsMpstatInfo> orderList = new ArrayList<MsOsMpstatInfo>();
		if(list2!=null&&list2.size()>0){
			for(Map<String,String> map:list2){
				MsOsMpstatInfo info = new MsOsMpstatInfo();
				info.setId(map.get("virtual_seq"));
				info.setCollectTime(map.get("collectTime"));
				info.setCpu_user(Double.parseDouble(map.get("cpu_user")));
				info.setCpu_sys(Double.parseDouble(map.get("cpu_sys")));
				info.setIowait(Double.parseDouble(map.get("iowait")));
				orderList.add(info);
			}
		}
		// 列名
		List<String> seriesList = new ArrayList<String>();
		List<Double> list1=new ArrayList<Double>();
		List<Double> list5=new ArrayList<Double>();
		List<Double> list15=new ArrayList<Double>();
		int num = 0;
		try {
			num = DateUtil.getDifTime(startTime, endTime)/TIME_DIF+1;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Map<Type,List> map = new HashMap<Type,List>();
		if(num>0){
			if(orderList.size()>0){
				Double value1 = 0D;
				Double value5 = 0D;
				Double value15 = 0D;
				//存入时间
				for(int i=0;i<num;i++){
					String time = DateUtil.getTime(startTime, i*TIME_DIF);
					if(i==0){
						List<String> start = new ArrayList<String>();
						start.add(DateUtil.getWebTime(time));
						map.put(Type.START, start);
					}
					if(i==(num-1)){
						List<String> end = new ArrayList<String>();
						end.add(DateUtil.getWebTime(time));
						map.put(Type.END, end);
					}
					seriesList.add(DateUtil.getShowTime(time));
					for(MsOsMpstatInfo o : orderList){
						if(o.getCollectTime().substring(0,12).equals(time)){
								value1 = o.getCpu_user();
								value5 = o.getCpu_sys();
								value15 = o.getIowait();
						}
					}
					list1.add(value1);
					list5.add(value5);
					list15.add(value15);
				}
			}
		}
		map.put(Type.cpu_user, list1);
		map.put(Type.cpu_sys, list5);
		map.put(Type.iowait, list15);
		map.put(Type.FIELD, seriesList);
		return map;
	}

	public enum Type { 
		cpu_user("ms1"), cpu_sys("ms5"), iowait("ms15"), FIELD("seriesList"), START("start"), END("end");
		private final String type;

		Type(String type) {
			this.type = type;
		}

		public String getType() {
			return type;
		}
	}  	
	
}
