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
import com.cplatform.mall.dc.model.MsOsDiskSpaceInfo;
import com.cplatform.mall.dc.utils.DateUtil;

/**
 * MsOsDiskSpaceService.java <br>
 * <p>
 * Copyright: Copyright (c) 2014-5-9 上午11:11:57
 * <p>
 * Company: 苏州宽连信息技术有限公司
 * <p>
 * 
 * @author guyu@c-platform.com
 * @version 1.0.0
 */
@Service
public class MsOsDiskSpaceService {

	@Autowired
	DbHelper dbHelper;

	//时间
	public static int TIME_DIF = 15;
	//取数据量
	public static int SEARCH_NUM = 33;
	
	
	private String getStartTime(String time){
		int lastNum = Integer.parseInt(time.substring(10,12));
		if(lastNum==0){
			time = time.substring(0,10)+"01";
		}else if(lastNum>1&&lastNum<15){
			time = time.substring(0,10)+"16";
		}else if(lastNum>16&&lastNum<31){
			time = time.substring(0,10)+"31";
		}else if(lastNum>31&&lastNum<46){
			time = time.substring(0,10)+"46";
		}else if(lastNum>46){
			time = DateUtil.getTime(time, 15).substring(0,10)+"01";
		}
		return time;
	}
	
	private String getEndTime(String time){
		int lastNum = Integer.parseInt(time.substring(10,12));
		if(lastNum==0){
			time = DateUtil.getTime(time, -15).substring(0,10)+"46";
		}else if(lastNum>1&&lastNum<15){
			time = time.substring(0,10)+"01";
		}else if(lastNum>16&&lastNum<31){
			time = time.substring(0,10)+"16";
		}else if(lastNum>31&&lastNum<46){
			time = time.substring(0,10)+"31";
		}else if(lastNum>46){
			time = time.substring(0,10)+"46";
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
		String sql = "select virtual_seq,to_char(ms_time,'yyyyMMddhh24mi') collectTime,ms_mount_on,ms_use " +
				" from ms_os_disk_space where virtual_seq = ? and to_char(ms_time,'yyyyMMddhh24mi')>=? " +
				" and to_char(ms_time,'yyyyMMddhh24mi')<=? and ms_mount_on != '/dev/shm' ";
		List<Map<String, String>> list2 = null;
		try {
			list2 = dbHelper.getMapList(sql,new Object[]{id,startTime.substring(0,12),endTime.substring(0,12)});
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		List<MsOsDiskSpaceInfo> orderList = new ArrayList<MsOsDiskSpaceInfo>();
		if(list2!=null&&list2.size()>0){
			for(Map<String,String> map:list2){
				MsOsDiskSpaceInfo info = new MsOsDiskSpaceInfo();
				info.setId(map.get("virtual_seq"));
				info.setCollectTime(map.get("collectTime"));
				info.setMs_mount_on(map.get("ms_mount_on"));
				info.setMs_use(Double.parseDouble(map.get("ms_use")));
				orderList.add(info);
			}
		}
		// 列名
		List<String> seriesList = new ArrayList<String>();
		
		int num = 0;
		try {
			num = DateUtil.getDifTime(startTime, endTime)/TIME_DIF+1;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Map<Type,List> map = new HashMap<Type,List>();
		List list = new ArrayList();
		if(num>0){
			//获得所有监控磁盘
			List<String> disk_list = new ArrayList<String>();
			if(orderList.size()>0){
				for(MsOsDiskSpaceInfo info:orderList){
					if(!disk_list.contains(info.getMs_mount_on())){
						disk_list.add(info.getMs_mount_on());
					}
				}
			}
			if(orderList.size()>0){
				Map<String,List<Double>> ms_user_map = new HashMap<String,List<Double>>();
				Double value = 0D;
				for(int j=0;j<disk_list.size();j++){
					List<Double> ms_user_list = new ArrayList<Double>();
					String disk_name = disk_list.get(j);
					//存入时间
					for(int i=0;i<num;i++){
						String time = DateUtil.getTime(startTime, i*TIME_DIF);
						if(j==0){
							seriesList.add(DateUtil.getShowTime(time));
						}
						for (MsOsDiskSpaceInfo o : orderList) {
							if (o.getCollectTime().substring(0, 12).equals(time)
									&& o.getMs_mount_on().equals(disk_name)) {
								value = o.getMs_use();
							}
						}
						ms_user_list.add(value);
					}
					ms_user_map.put(disk_name, ms_user_list);
				}
				list.add(ms_user_map);
			}
		}
		map.put(Type.FIELD, seriesList);
		map.put(Type.ms_use, list);
		return map;
	}

	public enum Type { 
		ms_use("ms_use"), FIELD("seriesList");
		private final String type;

		Type(String type) {
			this.type = type;
		}

		public String getType() {
			return type;
		}
	}  	
	
}
