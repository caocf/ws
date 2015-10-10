package com.cplatform.mall.dc.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplatform.dbhelp.DbHelper;
import com.cplatform.mall.dc.model.InterfaceInfo;
import com.cplatform.mall.dc.utils.DateUtil;

/**
 * 接口查询 <br>
 * <p>
 * Copyright: Copyright (c) 2014-5-7 下午05:51:57
 * <p>
 * Company: 苏州宽连信息技术有限公司
 * <p>
 * 
 * @author guyu@c-platform.com
 * @version 1.0.0
 */
@Service
public class InterfaceService {
	
	@Autowired
	DbHelper dbHelper;
	
	public enum Type { 
		TYPE("type"), NAME("inter_name"), VALUES("VALUES"),START("START"),END("END"), FIELD("seriesList");
		private final String type;

		Type(String type) {
			this.type = type;
		}

		public String getType() {
			return type;
		}
	} 
	
	public Map<Type,Object> getInit() throws SQLException {
		String sql = "select t.* from t_interface_monitor t, " +
				" (select inter_name,type,max(collect_time) collect_time from t_interface_monitor where type !='interface'  group by inter_name,type)t1 " +
				" where t.inter_name=t1.inter_name and t.type=t1.type and t.collect_time =t1.collect_time";
		List<Map<String, String>> list = dbHelper.getMapList(sql,new Object[]{});
		List<String> typeList = new ArrayList<String>();
		Map<String,List<String[]>> nameList = new HashMap<String,List<String[]>>();
		Map<Type,Object> cache = new HashMap<Type,Object>();
		if(list!=null&&list.size()>0){
			for(int i=0;i<list.size();i++){
				Map<String, String> obj = list.get(i);
				String type = obj.get("type");
				String name = obj.get("inter_name");
				String status = obj.get("status");
				if(!typeList.contains(type)){
					typeList.add(type);
					List<String[]> list2 = new ArrayList<String[]>();
					list2.add(new String[]{name,status});
					nameList.put(type, list2);
				}else{
					List<String[]> list3 = nameList.get(type);
					if(list3!=null&&list3.size()>0){
						list3.add(new String[]{name,status});
						nameList.put(type, list3);
					}
				}
			}
			cache.put(Type.TYPE, typeList);
			cache.put(Type.NAME, nameList);
		}
		return cache;
	}
	
	public Map<Type, Object> getInterface(String type, String inter_name) throws SQLException {
		String startTime = DateUtil.getTime(-100);
		Map<Type, Object> map = getInterface(startTime,null,type,inter_name);
		map.put(Type.START, DateUtil.getWebTime(startTime));
		map.put(Type.END, DateUtil.getWebTime(DateUtil.getTime(0)));
		return map;
	}
	
	public Map<Type, Object> getInterface(String start,String end,String type, String inter_name) throws SQLException {
		Date startTime = new Date();
		List<Map<String, String>> list = new ArrayList<Map<String,String>>();
		Object[] objs = new Object[]{};
		StringBuffer sql = new StringBuffer(
				"select * from t_interface_monitor t "
						+ "where substr(collect_time,0,12)>=? ");
		objs = ArrayUtils.add(objs, start.substring(0,12));
		if (end != null) {
			sql.append("and substr(collect_time,0,12)<=? ");
			objs = ArrayUtils.add(objs, end.substring(0,12));
		}
		if (type!=null&&inter_name != null) {
			sql.append(" and type = ? and inter_name = ? ");
			objs = ArrayUtils.add(objs, type);
			objs = ArrayUtils.add(objs, inter_name);
		}else{
			return null;
		}
		sql.append("order by collect_time");
		list = dbHelper.getMapList(sql.toString(),objs);
		List<InterfaceInfo> interList = new ArrayList<InterfaceInfo>();
		Map<Type,Object> map = new HashMap<Type,Object>();
		if(list!=null&&list.size()>0){
			for(int i=0;i<list.size();i++){
				Map<String, String> obj = list.get(i);
				InterfaceInfo info = new InterfaceInfo();
				info.setId(obj.get("seq_id"));
				info.setInterName(obj.get("inter_name"));
				info.setInterUrl(obj.get("inter_url"));
				info.setType(obj.get("type"));
				info.setCollectTime(obj.get("collect_time"));
				info.setStatus(obj.get("status"));
				interList.add(info);
			}
			if(type!=null&&inter_name!=null){
				map = getMap(inter_name, type, interList);
			}
		}
		Date endTime = new Date();
		System.out.println("查询接口数据耗时："+((double)(endTime.getTime()-startTime.getTime()))/1000+"秒");
		
		return map;
	}
	
	private Map<Type, Object> getMap(String name,String type,List<InterfaceInfo> interList){
		Map<Type,Object> map = new HashMap<Type,Object>();
		List<String> seriesList = new ArrayList<String>();
		List<Double> values=new ArrayList<Double>();
		
		for(InterfaceInfo info : interList){
			seriesList.add(DateUtil.getShowTime(info.getCollectTime()));
			values.add(Double.parseDouble(info.getStatus()));
		}
		map.put(Type.FIELD, seriesList);
		map.put(Type.NAME, name);
		map.put(Type.TYPE, type);
		map.put(Type.VALUES, values);
		return map;
	}

}
