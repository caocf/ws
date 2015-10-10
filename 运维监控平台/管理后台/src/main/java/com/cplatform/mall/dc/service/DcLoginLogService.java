package com.cplatform.mall.dc.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplatform.dbhelp.DbHelper;
import com.cplatform.dbhelp.page.Page;
import com.cplatform.mall.dc.entity.DcLoginLog;

/**
 * 登录日志服务类
 * 
 * @author zhuangxx@c-platform.com
 * @since 2013-7-13
 */
@Service
public class DcLoginLogService {

	@Autowired
	private DbHelper dbHelper;

	/**
	 * 分页查找操作员列表
	 * 
	 * @param nickName
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public Page<DcLoginLog> findAllLoginLogsPage(String content,
			String fromTime, String toTime, int pageNo) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.setLength(0);
		sql.append("SELECT * FROM T_DC_LOGIN_LOG llog where 1=1  ");
		List<Object> params = new ArrayList<Object>();
		if (StringUtils.isNotBlank(content)) {
			sql.append(" and (llog.USER_NAME LIKE ? OR llog.USER_CODE LIKE ?) ");
			params.add("%" + content + "%");
			params.add("%" + content + "%");
		}

		if (StringUtils.isNotBlank(fromTime)) {
			sql.append("and llog.LOGIN_TIME > ? ");
			params.add(fromTime.replaceAll("-", "") + "000000");
		}

		if (StringUtils.isNotBlank(toTime)) {
			sql.append("and llog.LOGIN_TIME < ? ");
			params.add(toTime.replaceAll("-", "") + "235959");
		}

		sql.append(" order by llog.LOGIN_TIME  desc ");
		return dbHelper.getPage(sql.toString(), DcLoginLog.class, pageNo,
				Page.DEFAULT_PAGESIZE, params.toArray());
	}

}
