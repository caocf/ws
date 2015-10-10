package com.cplatform.mall.back.lottery.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplatform.dbhelp.DbHelper;
import com.cplatform.dbhelp.page.Page;
import com.cplatform.mall.back.lottery.dao.LoggerDao;
import com.cplatform.mall.back.lottery.entity.Logger;
import com.cplatform.mall.back.utils.ExcelExportUtil;
import com.cplatform.mall.back.utils.data.RoleDataUtils;

@Service
public class LoggerService {

	@Autowired
	private DbHelper dbHelper;
	@Autowired
	private LoggerDao loggerDao;

	public void save(Logger logger) {
		loggerDao.save(logger);
	}

	public Page<Logger> findLoggerList(Logger logger, int pageNo, int pageSize)
			throws SQLException {
		StringBuilder sqlBuff = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sqlBuff.setLength(0);
		sqlBuff.append("select t.id id, ");
		sqlBuff.append("t1.hit_level hitLevel, ");
		sqlBuff.append("t.target_id targetId, ");
		sqlBuff.append("t.content content, ");
		sqlBuff.append("t.hit_time hitTime ");
		sqlBuff.append("from t_lottery_log t ");
		sqlBuff.append("join t_lottery_prize t1 ");
		sqlBuff.append("on t.prize_id = t1.id ");
		// sqlBuff.append("join t_lottery_target t2 ");
		// sqlBuff.append("on t.target_id = t2.id ");
		sqlBuff.append("where 1=1 ");
		if (null != logger.getId()) {
			sqlBuff.append(" and t.id = ? ");
			params.add(logger.getId());
		}
		if (StringUtils.isNotEmpty(logger.getBeginTime())) {
			sqlBuff.append(" and t.hit_time > ?  ");
			params.add(logger.getBeginTime()+"000000");
		}
		if (StringUtils.isNotEmpty(logger.getEndTime())) {
			sqlBuff.append(" and t.hit_time < ?  ");
			params.add(logger.getEndTime()+"000000");
		}
		sqlBuff.append(RoleDataUtils.getRoleData(RoleDataUtils.MODULE_LOTTERY));
		sqlBuff.append("order by t.hit_time desc ");
		Page<Logger> pageData = dbHelper.getPage(sqlBuff.toString(),
				Logger.class, pageNo, pageSize, params.toArray());
		return pageData;
	}

	
	public void download(HttpServletRequest request,HttpServletResponse response) throws Exception{
		StringBuilder sqlBuff = new StringBuilder();
		sqlBuff.setLength(0);
		sqlBuff.append("select t.id id, ");
		sqlBuff.append("t1.hit_level hitLevel, ");
		sqlBuff.append("t.target_id targetId, ");
		sqlBuff.append("t.content content, ");
		sqlBuff.append("t.hit_time hitTime ");
		sqlBuff.append("from t_lottery_log t ");
		sqlBuff.append("join t_lottery_prize t1 ");
		sqlBuff.append("on t.prize_id = t1.id ");
		String[] head = {"编号","奖品等级","中奖手机号","中奖内容","中奖时间"};
		ExcelExportUtil.exportExcel(dbHelper.getConn(),head,"lotteryLog_", sqlBuff.toString(),request,response);
	}
}
