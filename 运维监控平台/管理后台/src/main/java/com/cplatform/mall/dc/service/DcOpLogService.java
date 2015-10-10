package com.cplatform.mall.dc.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplatform.dbhelp.DbHelper;
import com.cplatform.mall.dc.entity.DcOpLog;

/**
 * 操作日志服务类
 * 
 * @author zhuangxx@c-platform.com
 * @since 2013-7-12
 */
@Service
public class DcOpLogService {
	@Autowired
	private DbHelper dbHelper;

	public Object findAll(String startTime, String endTime, int page,
			int pageSize) throws SQLException {
		StringBuilder sql = new StringBuilder(
				"select * from t_dc_operate_log opLog where 1 = 1");
		List<Object> params = new ArrayList<Object>();

		if (StringUtils.isNotBlank(startTime)) {
			sql.append(" and opLog.OP_TIME > ? ");
			params.add(startTime.replaceAll("-", "") + "000000");
		}

		if (StringUtils.isNotBlank(endTime)) {
			sql.append(" and opLog.OP_TIME < ? ");
			params.add(endTime.replaceAll("-", "") + "235959");
		}

		sql.append(" order by opLog.OP_TIME desc ");

		return dbHelper.getPage(sql.toString(), DcOpLog.class, page, pageSize,
				params.toArray());
	}

}
