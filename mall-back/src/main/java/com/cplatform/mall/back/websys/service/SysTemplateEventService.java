package com.cplatform.mall.back.websys.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cplatform.dbhelp.DbHelper;
import com.cplatform.dbhelp.page.Page;
import com.cplatform.mall.back.utils.AppConfig;
import com.cplatform.mall.back.websys.dao.SysTemplateEventDao;
import com.cplatform.mall.back.websys.entity.SysTemplateEvent;

/**
 * 模版事件管理Service. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2013-6-8
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author zhouhui@c-platform.com
 * @version 1.0.0
 */
@Service
public class SysTemplateEventService {
	@Autowired
	DbHelper dbHelper;

	@Autowired
	AppConfig appConfig;

	@Autowired
	SysTemplateEventDao dao;
	
	/**
	 * 分页查询所有的模版事件
	 * @param info
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws SQLException
	 */
	public Page<SysTemplateEvent> getSysTempLateEventInfo(SysTemplateEvent info, int pageNo, int pageSize) throws SQLException {
	StringBuilder sqlBuff = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sqlBuff.setLength(0);
		sqlBuff.append("select * from t_sys_template_event t where 1 = 1 ");
		if (StringUtils.isNotBlank(info.getName())) {
			sqlBuff.append(" and t.name like ? ");
			params.add("%" + info.getName().trim() + "%");
		}
		sqlBuff.append("order by t.id desc");
		return dbHelper.getPage(sqlBuff.toString(), SysTemplateEvent.class, pageNo, pageSize, params.toArray());

	}


	/**
	 * 查询单个模版事件
	 * @param id
	 * @return
	 */
	public SysTemplateEvent findById(int id) {
		return dao.findById(id);
	}
	
	/**
	 * 保存模版事件
	 * @param sys
	 * @param tName
	 */
	@Transactional
	public void saveTemplate(SysTemplateEvent sys, String tName) {
		String[] str = tName.split(",");
		sys.setTgId(Integer.parseInt(str[0]));
		sys.setTgName(str[1]);
		dao.save(sys);
	}
}
