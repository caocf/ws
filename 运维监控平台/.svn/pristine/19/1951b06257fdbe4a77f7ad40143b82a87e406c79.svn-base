package com.cplatform.mall.dc.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplatform.dbhelp.DbHelper;
import com.cplatform.mall.dc.model.BackUpInfo;

/**
 * 备份查询 <br>
 * <p>
 * Copyright: Copyright (c) 2014-5-7 下午03:18:09
 * <p>
 * Company: 苏州宽连信息技术有限公司
 * <p>
 * 
 * @author guyu@c-platform.com
 * @version 1.0.0
 */
@Service
public class BackUpService {
	
	@Autowired
	DbHelper dbHelper;
	
	public enum Type { 
		SC("程序"), DB("数据库");
		private final String type;

		Type(String type) {
			this.type = type;
		}

		public String getType() {
			return type;
		}
	} 
	
	public List<BackUpInfo> getBackUp(Type type,String time) throws SQLException {
		String sql = "select * from t_backup_stat_monitor where plat_name='商城' and type=? " +
				"and check_time=? order by substr(back_file,22,34) ";
		List<Map<String, String>> list = dbHelper.getMapList(sql,new Object[]{type.getType(),time.substring(0,8)});
		List<BackUpInfo> infoList = new ArrayList<BackUpInfo>();
		List<BackUpInfo> temp = new ArrayList<BackUpInfo>();
		if(list!=null&&list.size()>0){
			for(Map<String,String> map:list){
				BackUpInfo info = new BackUpInfo();
				info.setId(map.get("seq_id"));
				info.setPlatName(map.get("plat_name"));
				info.setType(map.get("type"));
				info.setBackUpTime(map.get("backup_time"));
				info.setFileSize(map.get("file_size"));
				info.setCheckTime(map.get("check_time"));
				info.setBackFile(map.get("back_file"));
				if("0".equals(info.getFileSize())){
					infoList.add(info);
				}else{
					temp.add(info);
				}
			}
		}
		infoList.addAll(temp);
		return infoList;
	}
	

}
