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
import com.cplatform.mall.dc.model.MsOsUptimeInfo;
import com.cplatform.mall.dc.utils.DateUtil;

/**
 * MsOsUptimeService.java <br>
 * <p>
 * Copyright: Copyright (c) 2014-5-8 下午02:32:31
 * <p>
 * Company: 苏州宽连信息技术有限公司
 * <p>
 * 
 * @author guyu@c-platform.com
 * @version 1.0.0
 */
@Service
public class MsOsUptimeService {

	@Autowired
	DbHelper dbHelper;

	//时间
	public static int TIME_DIF = 5;
	//取数据量
	public static int SEARCH_NUM = 100;
	
	private static String channel_id = "100100200";
	
	public static final Map<String,String> services = new HashMap<String,String>();
	public static final Map MsOslistMenu = new HashMap();
	
	
	private String getStartTime(String time){
		int lastNum = Integer.parseInt(time.substring(11,12));
		if(lastNum>6||lastNum==0){
			time = DateUtil.getTime(time, 5).substring(0,11)+"1";
		}else if(lastNum<6&&lastNum>1){
			time = time.substring(0,11)+"6";
		}
		return time;
	}
	
	private String getEndTime(String time){
		int lastNum = Integer.parseInt(time.substring(11,12));
		if(lastNum>6){
			time = time.substring(0,11)+"6";
		}else if(lastNum==0){
			time = DateUtil.getTime(time, -4);
		}else if(lastNum<6&&lastNum>1){
			time = time.substring(0,11)+"1";
		}
		return time;
	}
	
	@PostConstruct
	public void init() throws SQLException {
		String sql = "select virtual_seq,ip from ms_ip_information";
		List<Map<String, String>> list = dbHelper.getMapList(sql,new Object[]{});
		List<DcMenu> menulist = new ArrayList<DcMenu>();
		if(list!=null&&list.size()>0){
			for(int i=0;i<list.size();i++){
				Map<String, String> obj = list.get(i);
				String seq = obj.get("virtual_seq");
				String ip = obj.get("ip");
				services.put(seq, ip);
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
				menu.setMenuName(ip);
				menu.setMenuUrl("/uptime?id="+seq);
				menu.setParentCode(channel_id);
				menu.setLeafYn(true);
				menulist.add(menu);
			}
		}
		MsOslistMenu.put("key", channel_id);
		MsOslistMenu.put("list", menulist);
	}
	
	public Map<Type,List> getField(String id){
		String endTime = getEndTime(DateUtil.getTime(0));
		String startTime = DateUtil.getTime(endTime,-(TIME_DIF*(SEARCH_NUM-1)));
		return getField(startTime,endTime,id);
	}
	
	public Map<Type,List> getField(String startTime,String endTime,String id){
		startTime = getStartTime(startTime);
		endTime = getEndTime(endTime);
		String sql = "select virtual_seq,to_char(ms_time,'yyyyMMddhh24mi') collectTime,load_average_1,load_average_5,load_average_15  " +
				" from ms_os_uptime where virtual_seq = ? and to_char(ms_time,'yyyyMMddhh24mi')>=? " +
				" and to_char(ms_time,'yyyyMMddhh24mi')<=?";
		List<Map<String, String>> list2 = null;
		try {
			list2 = dbHelper.getMapList(sql,new Object[]{id,startTime.substring(0,12),endTime.substring(0,12)});
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		List<MsOsUptimeInfo> orderList = new ArrayList<MsOsUptimeInfo>();
		if(list2!=null&&list2.size()>0){
			for(Map<String,String> map:list2){
				MsOsUptimeInfo info = new MsOsUptimeInfo();
				info.setId(map.get("virtual_seq"));
				info.setCollectTime(map.get("collectTime"));
				info.setLoad1(Double.parseDouble(map.get("load_average_1")));
				info.setLoad5(Double.parseDouble(map.get("load_average_5")));
				info.setLoad15(Double.parseDouble(map.get("load_average_15")));
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
					for(MsOsUptimeInfo o : orderList){
						if(o.getCollectTime().substring(0,12).equals(time)){
								value1 = o.getLoad1();
								value5 = o.getLoad5();
								value15 = o.getLoad15();
						}
					}
					list1.add(value1);
					list5.add(value5);
					list15.add(value15);
				}
			}
		}
		map.put(Type.MS1, list1);
		map.put(Type.MS5, list5);
		map.put(Type.MS15, list15);
		map.put(Type.FIELD, seriesList);
		return map;
	}

	public enum Type { 
		MS1("ms1"), MS5("ms5"), MS15("ms15"), FIELD("seriesList"), START("start"), END("end");
		private final String type;

		Type(String type) {
			this.type = type;
		}

		public String getType() {
			return type;
		}
	}  	
	
}
