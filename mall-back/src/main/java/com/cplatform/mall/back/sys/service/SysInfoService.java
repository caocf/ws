package com.cplatform.mall.back.sys.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplatform.dbhelp.DbHelper;
import com.cplatform.dbhelp.page.Page;
import com.cplatform.mall.back.sys.dao.SysBlackUserDao;
import com.cplatform.mall.back.sys.dao.SysFilterWordDao;
import com.cplatform.mall.back.sys.entity.SysBlackUser;
import com.cplatform.mall.back.sys.entity.SysFilterWord;
import com.cplatform.mall.back.sys.entity.SysLogInfo;
import com.cplatform.mall.back.sys.entity.SysLoginInfo;
import com.cplatform.mall.back.sys.entity.SysRole;
import com.cplatform.mall.back.sys.entity.SysSegment;
import com.cplatform.mall.back.sys.entity.SysTestTerminalId;
import com.cplatform.mall.back.sys.entity.SysUnit;

/**
 * Title. 系统管理相关service<br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-5-21 下午5:17:09
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: BaiJie
 * <p>
 * Version: 1.0
 * <p>
 */
@Service
public class SysInfoService {

	@Autowired
	private DbHelper dbHelper;

	@Autowired
	private SysBlackUserDao sysBlackUserDao;

	@Autowired
	private SysFilterWordDao sysFilterWordDao;

	/**
	 * 黑名单批量提交
	 * 
	 * @param list
	 *            数据
	 * @return
	 * @throws SQLException
	 */
	public void addBlackUsers(Set<String> set) throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO T_SYS_BLACK_USER ");
		sql.append("  (ID, TERMINAL_ID, LEV_TAG, IN_TAG, REMARK,UNIT_ID) ");
		sql.append("VALUES ");
		sql.append("  (SEQ_SYS_COMM_ID.NEXTVAL, ?, ?, ?, ?, ?)");
		List<String> dataList = new ArrayList<String>(set);
		List<Object[]> listArry = new ArrayList<Object[]>();
		for (String str : dataList) {
			listArry.add(str.split("#"));
		}
		dbHelper.batch(sql.toString(), listArry);
	}

	/**
	 * 查询黑名单分页
	 * 
	 * @param terminalId
	 *            要查询的手机号码
	 * @param pageNo
	 *            页面
	 * @param pageSize
	 *            每页显示
	 * @return
	 * @throws SQLException
	 */
	public Page<SysBlackUser> findBlackUser(String terminalId, int pageNo, int pageSize) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from T_SYS_BLACK_USER where 1=1 ");
		List params = new ArrayList();
		if (StringUtils.isNotEmpty(terminalId)) {
			sql.append(" and TERMINAL_ID = ? ");
			params.add(terminalId);
		}
		sql.append(" order by ID desc ");
		return dbHelper.getPage(sql.toString(), SysBlackUser.class, pageNo, pageSize, params.toArray());
	}

	/**
	 * 黑名单分页查询
	 * 
	 * @param blackUser
	 *            查询条件
	 * @param page
	 *            当前页面
	 * @param pageSize
	 *            页面大小
	 * @return
	 * @throws SQLException
	 */
	public Page<SysBlackUser> listBlackUser(SysBlackUser blackUser, int pageNo, int pageSize) throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("select * from t_sys_black_user where 1=1 ");
		List<Object> params = null;
		if (blackUser != null) {
			params = new ArrayList<Object>();
			if (blackUser.getId() != null) {
				sql.append(" and id = ? ");
				params.add(blackUser.getId());
			}
			if (blackUser.getInTag() != null) {
				sql.append(" and in_tag = ?");
				params.add(blackUser.getInTag());
			}
			if (blackUser.getLevTag() != null) {
				sql.append(" and lev_tag = ?");
				params.add(blackUser.getLevTag());
			}
			if (StringUtils.isNotEmpty(blackUser.getTerminalId())) {
				sql.append(" and terminal_id like '%" + blackUser.getTerminalId().trim() + "%'");
			}
			if (StringUtils.isNotEmpty(blackUser.getStartTime())) {
				sql.append(" and update_time  > ?");
				params.add(blackUser.getStartTime());
			}
			if (StringUtils.isNotEmpty(blackUser.getEndTime())) {
				sql.append(" and update_time < ? ");
				params.add(blackUser.getEndTime());
			}
		}
		return dbHelper.getPage(sql.toString(), SysBlackUser.class, pageNo, pageSize, params.toArray());
	}

	/**
	 * 查询过滤字
	 * 
	 * @param word
	 *            过滤字
	 * @param pageNo
	 *            页面
	 * @param pageSize
	 *            每页显示
	 * @return
	 * @throws SQLException
	 */
	public Page<SysFilterWord> findFilterWord(SysFilterWord filterWord, int pageNo) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql
		        .append("select f.id,f.WORD,f.IN_TAG,f.UNIT_ID,f.UPDATE_USER_ID,to_date(f.UPDATE_TIME,'yyyy-MM-dd HH24:mi:ss') as UPDATE_TIME,u.USER_NAME   from T_SYS_FILTER_WORD f,T_SYS_USER u  where f.UPDATE_USER_ID=u.id ");
		List params = new ArrayList();
		if (StringUtils.isNotEmpty(filterWord.getWord())) {
			sql.append(" and f.word like ? ");
			params.add("%" + filterWord.getWord().trim() + "%");
		}
		sql.append(" order by f.UPDATE_TIME desc ");

		return dbHelper.getPage(sql.toString(), SysFilterWord.class, pageNo, Page.DEFAULT_PAGESIZE, params.toArray());
	}

	/**
	 * 过滤字批量提交
	 * 
	 * @param list
	 *            数据
	 * @return
	 * @throws SQLException
	 */
	public void addBathFilterWord(Set set) throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("insert into t_sys_filter_word ");
		sql.append("  (ID, WORD, IN_TAG, UNIT_ID, UPDATE_USER_ID, update_time) ");
		sql.append("values ");
		sql.append("  (SEQ_SYS_COMM_ID.NEXTVAL, ?, ?, ?, ?, ?)");
		List<String> dataList = new ArrayList<String>(set);
		List<Object[]> listArry = new ArrayList<Object[]>();
		for (String str : dataList) {
			listArry.add(str.split("#"));
		}
		dbHelper.batch(sql.toString(), listArry);
	}

	/**
	 * 检查关键字是否重复
	 * 
	 * @param list
	 *            数据
	 * @return false:有重复;true:不重复
	 * @throws SQLException
	 */
	public boolean checkFilterWord(String word, String unitId) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.setLength(0);
		sql.append("select count(1) from t_sys_filter_word where word=? and unit_id=? ");
		String num = dbHelper.queryScalar(sql.toString(), new Object[] { word, unitId });
		if ("0".equals(num)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 验证是否含有敏感字
	 * 
	 * @param word
	 * @return
	 */
	public boolean checkFilterWord(String word) {
		return sysFilterWordDao.countFilterwordNum(word) > 0 ? true : false;
	}

	/**
	 * 角色查询
	 * 
	 * @param roleName
	 *            角色名称
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            每页显示
	 * @return
	 * @throws SQLException
	 */
	public Page<SysRole> findRole(String roleName, int pageNo, int pageSize) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from T_SYS_ROLE where 1=1 ");
		List params = new ArrayList();
		if (StringUtils.isNotEmpty(roleName)) {
			sql.append(" and ROLE_NAME like ? ");
			params.add("%" + roleName + "%");
		}
		sql.append(" order by UPDATE_TIME desc ");
		return dbHelper.getPage(sql.toString(), SysRole.class, pageNo, pageSize, params.toArray());
	}

	/**
	 * 查询测试号码
	 * 
	 * @param terminalId
	 *            测试号码
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            每页显示
	 * @return
	 * @throws SQLException
	 */
	public Page<SysTestTerminalId> findTestTerminalId(String terminalId, int pageNo, int pageSize) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from T_SYS_TEST_TERMINAL_ID where 1=1 ");
		List params = new ArrayList();
		if (StringUtils.isNotEmpty(terminalId)) {
			sql.append(" and TERMINAL_ID = ? ");
			params.add(terminalId);
		}
		sql.append(" order by UPDATE_TIME desc ");
		return dbHelper.getPage(sql.toString(), SysTestTerminalId.class, pageNo, pageSize, params.toArray());
	}

	/**
	 * 查询号段
	 * 
	 * @param areaCode
	 *            区域码
	 * @param segCode
	 *            号段码
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            每页显示
	 * @return
	 * @throws SQLException
	 */
	public Page<SysSegment> findSysSegment(SysSegment sysSegment, int pageNo) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from t_sys_segment where 1=1 ");
		List params = new ArrayList();
		if (null != sysSegment) {
			if (StringUtils.isNotEmpty(sysSegment.getAreaCode())) {
				sql.append(" and area_code = ? ");
				params.add(sysSegment.getAreaCode().trim());
			}
			if (StringUtils.isNotEmpty(sysSegment.getSegmentCode())) {
				sql.append(" and segment_code = ? ");
				params.add(sysSegment.getSegmentCode().trim());
			}
		}
		return dbHelper.getPage(sql.toString(), SysSegment.class, pageNo, Page.DEFAULT_PAGESIZE, params.toArray());
	}

	/**
	 * 单位查询
	 * 
	 * @param SysUnit
	 *            单位查询条件
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            每页显示
	 * @return
	 * @throws SQLException
	 */
	public Page<SysUnit> findSysUnit(SysUnit sysUnit, int pageNo, int pageSize) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from t_sys_unit where 1=1 ");
		List params = new ArrayList();
		if (sysUnit != null) {
			if (null != sysUnit.getId()) {
				sql.append("  and id = ? ");
				params.add(sysUnit.getId());
			}

			if (StringUtils.isNotEmpty(sysUnit.getName())) {
				sql.append("  and name like ? ");
				params.add("%" + sysUnit.getName() + "%");
			}

		}
		sql.append(" order by update_time desc ,id desc ");
		return dbHelper.getPage(sql.toString(), SysUnit.class, pageNo, pageSize, params.toArray());
	}

	/**
	 * 批量添加黑名单
	 * 
	 * @param blackUser
	 * @throws SQLException
	 */
	public int addBlackUserBatch(SysBlackUser blackUser, List<String> list) throws SQLException {
		Set<String> set = new HashSet<String>();

		for (String terminalId : list) {
			if (sysBlackUserDao.findByTerminalId(terminalId).size() == 0 && terminalId.length() == 11) {
				String str = terminalId + "#" + blackUser.getLevTag() + "#" + blackUser.getInTag() + "#"
				        + (blackUser.getRemark() == null ? "" : blackUser.getRemark()) + "#" + blackUser.getUnitId();
				set.add(str);
			}
		}
		addBlackUsers(set);
		return set.size();
	}

	/**
	 * 查询系统操作日志
	 * 
	 * @param logInfo
	 *            查询条件对象
	 * @param pageNo
	 *            页面
	 * @param pageSize
	 *            每页显示
	 * @return
	 * @throws SQLException
	 */
	public Page<SysLogInfo> findSysLog(SysLogInfo logInfo, int pageNo, int pageSize) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from t_sys_log where 1=1 ");
		List params = new ArrayList();
		if (logInfo != null) {
			if (StringUtils.isNotEmpty(logInfo.getUserName())) {
				sql.append(" and user_name like ? ");
				params.add("%" + logInfo.getUserName().trim() + "%");
			}
			if (logInfo.getId()!=null) {
				sql.append(" and id = ? ");
				params.add(logInfo.getId());
			}
			if (StringUtils.isNotEmpty(logInfo.getUserType())) {
				sql.append(" and user_type = ? ");
				params.add(logInfo.getUserType());
			}
			if (StringUtils.isNotEmpty(logInfo.getOperType())) {
				sql.append(" and oper_type = ? ");
				params.add(logInfo.getOperType());
			}

			if (StringUtils.isNotEmpty(logInfo.getModule())) {
				sql.append(" and module like ? ");
				params.add("%" + logInfo.getModule() + "%");
			}
			if (StringUtils.isNotEmpty(logInfo.getDescription())) {
				sql.append(" and description like ? ");
				params.add("%" + logInfo.getDescription() + "%");
			}
			if (StringUtils.isNotBlank(logInfo.getStartTime())) {
				String startTime = logInfo.getStartTime() + "000000";
				sql.append("and oper_Time > ? ");
				params.add(startTime);
			}

			if (StringUtils.isNotBlank(logInfo.getEndTime())) {
				String endTime = logInfo.getEndTime() + "235959";
				sql.append("and oper_Time < ? ");
				params.add(endTime);
			}

		}
		sql.append(" order by id desc ");
		return dbHelper.getPage(sql.toString(), SysLogInfo.class, pageNo, pageSize, params.toArray());
	}

	/**
	 * 查询系统登录日志
	 * 
	 * @param loginfInfo
	 *            查询对象
	 * @param pageNo
	 *            页面
	 * @param pageSize
	 *            每页显示
	 * @return
	 * @throws SQLException
	 */
	public Page<SysLoginInfo> findSysLoginLog(SysLoginInfo loginfInfo, int pageNo, int pageSize) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from t_sys_login_log where 1=1 ");
		List params = new ArrayList();
		if (loginfInfo != null) {
			if (StringUtils.isNotEmpty(loginfInfo.getUserName())) {
				sql.append(" and user_name like ? ");
				params.add("%" + loginfInfo.getUserName() + "%");
			}

			if (loginfInfo.getUserType() != null) {
				sql.append(" and user_type = ? ");
				params.add(loginfInfo.getUserType());
			}

			if (StringUtils.isNotBlank(loginfInfo.getStartTime())) {
				String startTime = loginfInfo.getStartTime() + "000000";
				sql.append("and login_time > ? ");
				params.add(startTime);
			}

			if (StringUtils.isNotBlank(loginfInfo.getEndTime())) {
				String endTime = loginfInfo.getEndTime() + "235959";
				sql.append("and login_time < ? ");
				params.add(endTime);
			}

		}
		sql.append(" order by login_time desc ");
		return dbHelper.getPage(sql.toString(), SysLoginInfo.class, pageNo, pageSize, params.toArray());
	}

	/**
	 * 判断字符串是否为数字 add by mudeng
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}

}
