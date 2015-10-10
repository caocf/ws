package com.cplatform.mall.dc.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cplatform.dbhelp.DbHelper;
import com.cplatform.dbhelp.page.ListPage;
import com.cplatform.dbhelp.page.Page;
import com.cplatform.mall.dc.dao.MonitorInfoDao;
import com.cplatform.mall.dc.entity.DcArea;
import com.cplatform.mall.dc.entity.MonitorInfo;
import com.cplatform.mall.dc.model.FlowInfo;
import com.cplatform.mall.dc.utils.ChartDataUtil;
import com.cplatform.mall.dc.utils.Pager;

/**
 * MonitorInfoService.java <br>
 * <p>
 * Copyright: Copyright (c) 2014-5-21 下午04:56:21
 * <p>
 * Company: 苏州宽连信息技术有限公司
 * <p>
 * 
 * @author guyu@c-platform.com
 * @version 1.0.0
 */
@Service
public class MonitorInfoService {

	@Autowired
	MonitorInfoDao monitorInfoDao;
	
	@Autowired
	DbHelper dbHelper;

	/**
	 * 新建任务
	 * 
	 * @param MonitorInfo
	 */
	@Transactional
	public void addMonitorInfo(MonitorInfo info) {
		monitorInfoDao.save(info);
	}

	
	/**
	 * 获取一定数量的工单
	 * @return
	 */
	public List<MonitorInfo> getMonitorList() {
		List<MonitorInfo> list = monitorInfoDao.findShowMonitors();
		if(list.size()>10)
			list = list.subList(0, 10);
		return list;
	}
	
	/**
	 * 获取一定数量的工单
	 * @return
	 */
	public List<MonitorInfo> getMonitorList(String platName) {
		Map<String,String> platMap = MonitorInfo.platMap;
		String plat = platMap.get(platName);
		List<MonitorInfo> list = new ArrayList<MonitorInfo>();
		if(plat!=null)
			list = monitorInfoDao.findShowMonitors(plat);
		if(list.size()>10)
			list = list.subList(0, 10);
		return list;
	}
	
	/**
	 * 根据指定条件获取工单
	 * @return
	 */
	public ListPage<Map<String, Object>> getMonitorListByProperty(String start, String end, String status, String platName, int page, int pageSize) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from t_Monitor_Info t WHERE t.show='1' ");
		Object[] objs = new Object[]{};
		if(start!=null&&end.length()>0){
			sql.append("and t.monitor_time>=? ");
			objs = ArrayUtils.add(objs, start);
		}
		if(end!=null&&end.length()>0){
			sql.append("and t.monitor_time<=? ");
			objs = ArrayUtils.add(objs, end);
		}
		if(status!=null&&!status.equals("0")){
			sql.append("and t.status=? ");
			objs = ArrayUtils.add(objs, status);
		}
		if(!platName.equals("超级管理员")&&!platName.equals("移动")){
			Map<String,String> platMap = MonitorInfo.platMap;
			String plat = platMap.get(platName);
			if(plat!=null){
				sql.append("and t.plat_name=? ");
				objs = ArrayUtils.add(objs, plat);
			}else{
				return null;
			}
		}
		sql.append(" order by seq_id desc");
		ListPage<Map<String, Object>> monitorList = null;
		try {
			monitorList = dbHelper.getNativeMapPage(sql.toString(), page, pageSize, objs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return monitorList;
	}
 
}
