package com.cplatform.mall.back.websys.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplatform.dbhelp.DbHelper;
import com.cplatform.dbhelp.page.Page;
import com.cplatform.mall.back.utils.LogUtils;
import com.cplatform.mall.back.websys.entity.SysProperty;
/**
 * 
 * 属性相关Service. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2013-5-25 下午03:38:09
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * @author zhaowei@c-platform.com
 * @version 1.0.0
 */
@Service
public class SysPropertyService {
	@Autowired
	private LogUtils logUtils;
	
	@Autowired
	private DbHelper dbHelper;
	
	/**
	 * 属性查询
	 * 
	 * @param sysProperty
	 *            属性实体映射
	 * @param pageNo
	 *            页码
	 * @return
	 * @throws SQLException
	 */
	public Page<SysProperty> findSysProperty(String content, Long type, int pageNo) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from t_sys_property where 1=1 ");
		List params = new ArrayList();
		if (StringUtils.isNotEmpty(content)) {
			sql.append(" and content like ? ");
			params.add("%" + content.trim() + "%");
		}
		if (null != type) {
			sql.append(" and type = ? ");
			params.add(type);
		}
		sql.append(" order by id desc ");
		return dbHelper.getPage(sql.toString(), SysProperty.class, pageNo, Page.DEFAULT_PAGESIZE, params.toArray());
	}
}
