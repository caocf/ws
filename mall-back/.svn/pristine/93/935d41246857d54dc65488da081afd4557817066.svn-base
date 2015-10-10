package com.cplatform.mall.back.sys.service;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplatform.dbhelp.DbHelper;
import com.cplatform.dbhelp.page.Page;
import com.cplatform.mall.back.model.SessionUser;
import com.cplatform.mall.back.sys.dao.SysRegionDao;
import com.cplatform.mall.back.sys.dao.SysUserRegonDao;
import com.cplatform.mall.back.sys.dao.SysUserRoleDao;
import com.cplatform.mall.back.sys.dao.UserDao;
import com.cplatform.mall.back.sys.entity.SysRegion;
import com.cplatform.mall.back.sys.entity.SysUnit;
import com.cplatform.mall.back.sys.entity.SysUser;
import com.cplatform.mall.back.sys.entity.SysUserRegion;
import com.cplatform.mall.back.sys.entity.SysUserRole;
import com.cplatform.mall.back.utils.data.RoleDataUtils;
import com.cplatform.util2.TimeStamp;

/**
 * Title. <br>
 * Description.
 * <p/>
 * Copyright: Copyright (c) 13-5-21 下午6:28
 * <p/>
 * Company: 北京宽连十方数字技术有限公司
 * <p/>
 * Author: nicky
 * <p/>
 * Version: 1.0
 * <p/>
 */
@Service
public class UserService {

	@Autowired
	DbHelper dbHelper;

	@Autowired
	UserDao userDao;

	@Autowired
	SysUserRoleDao sysUserRoleDao;

	@Autowired
	SysUserRegonDao sysUserRegonDao;

	@Autowired
	SysRegionDao sysRegonDao;

	public SysUserRegion addOrUpdateSysUserRegon(SysUserRegion sysUserRegon) {
		return sysUserRegonDao.save(sysUserRegon);
	}

	public List<SysUserRegion> findSysUserRegonByUserId(Long userId) {
		return sysUserRegonDao.findByUserId(userId);
	}

	public void delSysUserRegon(Long id) {
		sysUserRegonDao.delete(id);
	}

	public SysUserRole findSysUserRoleById(Long id) {
		return sysUserRoleDao.findById(id);
	}

	public List<SysUserRole> findSysUserRoleByUserId(Long userId) {
		return sysUserRoleDao.findByUserId(userId);
	}

	public SysUserRole addOrUpdateSysUserRole(SysUserRole sysUserRole) {
		return sysUserRoleDao.save(sysUserRole);
	}

	public void delSysUserRole(Long id) {
		sysUserRoleDao.delete(id);
	}

	public SysUser addOrUpdateSysUser(SysUser user) {
		return userDao.save(user);
	}

	public SysUser findSysUserById(Long id) {
		return userDao.findById(id);
	}

	public SysUser findByUserCode(String userCode) {
		return userDao.findByUserCode(userCode);
	}

	public SysUser findByEmail(String email) {
		return userDao.findByEmail(email);
	}

	public void delSysUser(Long id) {
		userDao.delete(id);
	}

	public List<SysUser> findByFlag(Long flag) {
		return userDao.findByFlag(flag);
	}

	public SysRegion findByRegonCode(String regonCode) {
		return sysRegonDao.findByRegionCode(regonCode);
	}

	public List<SysUnit> getSysUnitList() throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.setLength(0);
		sql.append("select * ");
		sql.append("       from t_sys_unit t where t.flag = 0 ");
		sql.append("      start with t.parent_unit_id = '0' ");
		sql.append("     connect by t.parent_unit_id = prior t.id ");
		sql.append("      order by t.id");
		return dbHelper.getBeanList(sql.toString(), SysUnit.class, null);

	}

	public List<SysRegion> getSysRegon(Long parentRegon) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.setLength(0);
		sql.append("select * ");
		sql.append("     from t_sys_region t ");
		sql.append("    start with t.parent_region =? ");
		sql.append("   connect by t.parent_region = prior t.region_code ");
		sql.append("    order by t.id");
		Object[] params = new Object[] { parentRegon };
		return dbHelper.getBeanList(sql.toString(), SysRegion.class, params);
	}

	/**
	 * 获得一个用户对应的管理区域。 从用户对应管理的区域获得，如果没有获得，继承从单位管理的区域获得。
	 * 
	 * @param userId
	 * @return
	 * @throws SQLException
	 */
	public List<SysRegion> getUserRegon(Long userId) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.setLength(0);
		sql.append("select * ");
		sql.append("     from t_sys_region t ");
		sql.append("     where region_level <= 1 "); // 只拿出地市数据
		sql.append("    start with t.region_code in ");
		sql.append(" (select region_code from t_sys_user_region where user_id =? ) ");
		sql.append("   connect by t.parent_region = prior t.region_code ");
		sql.append("    order by t.region_code");
		Object[] params = new Object[] { userId };
		List<SysRegion> regionList = dbHelper.getBeanList(sql.toString(), SysRegion.class, params);
		if (regionList == null || regionList.isEmpty()) {// 从单位表中获得，该用户的管理区域
			sql.setLength(0);
			sql.append("select * ");
			sql.append("     from t_sys_region t ");
			sql.append("    where region_level <= 1 ");// 只拿出地市数据
			sql.append("    start with t.region_code =  ");
			sql.append("    (select tunit.area_code  from t_sys_unit tunit ,t_sys_user tuser where tunit.id = tuser.unit_id and tuser.id = ?  )   ");
			sql.append("   connect by t.parent_region = prior t.region_code ");
			sql.append("    order by t.region_code");
			regionList = dbHelper.getBeanList(sql.toString(), SysRegion.class, params);
		}
		return regionList;
	}

	public Page<SysUser> listLockUser(SysUser user, int page) throws SQLException {
		Object[] params = new Object[] {};
		StringBuilder sql = new StringBuilder(100);

		sql.append("select * from t_sys_user where 1=1 ");
		if (user != null) {
			if (user.getLockStatus() != -1) {
				sql.append(" and lock_status = ? ");
				params = ArrayUtils.add(params, user.getLockStatus());
			}

		}
		sql.append("and flag <> '0'  ");
		sql.append("order by id desc");

		return dbHelper.getPage(sql.toString(), SysUser.class, page, Page.DEFAULT_PAGESIZE, params);
	}

	public Page<SysUser> listUser(SysUser user, HttpSession session, int page) throws SQLException {

		SessionUser sessionUser = (SessionUser) session.getAttribute(SessionUser.SESSION_USER_KEY);

		Object[] params = new Object[] {};
		StringBuilder sql = new StringBuilder(100);

		sql.append("select * from t_sys_user where 1 = 1 and status != 3 ");

		if (sessionUser.getUserType() != SysUser.USER_TYPE_MASTER) {
			sql.append("and (update_user_id = ? or id = ?) ");
			params = ArrayUtils.add(params, sessionUser.getId());
			params = ArrayUtils.add(params, sessionUser.getId());
		}

		if (StringUtils.isNotBlank(user.getStartTime())) {
			String startTime = user.getStartTime() + "000000";
			sql.append("and create_time > ? ");
			params = ArrayUtils.add(params, startTime);
		}
		if (StringUtils.isNotBlank(user.getEndTime())) {
			String endTime = user.getEndTime() + "235959";
			sql.append("and create_time < ? ");
			params = ArrayUtils.add(params, endTime);
		}

		if (StringUtils.isNotBlank(user.getUserName())) {
			sql.append("and user_name like ? ");
			params = ArrayUtils.add(params, "%" + user.getUserName().trim() + "%");
		}
		if (StringUtils.isNotBlank(user.getUserCode())) {
			sql.append("and user_code like ? ");
			params = ArrayUtils.add(params, "%" + user.getUserCode().trim() + "%");
		}

		// 排除超级管理员和商户
		sql.append("and flag <> '0'  ");

		if (user.getFlag() != null) {
			sql.append("and flag = ? ");
			params = ArrayUtils.add(params, user.getFlag());
		}
		sql.append(RoleDataUtils.getRoleData(RoleDataUtils.MODULE_USER));// 控制数据访问
		sql.append(" order by id desc");
		return dbHelper.getPage(sql.toString(), SysUser.class, page, Page.DEFAULT_PAGESIZE, params);

	}

	public void delLoginHistory(Long userId) throws SQLException {

		Object[] params = new Object[] { userId, TimeStamp.getTime(TimeStamp.YYYYMMDD) };
		StringBuilder sql = new StringBuilder(100);
		sql.setLength(0);
		sql.append("delete from t_sys_login_log t ");
		sql.append(" where t.user_id = ? ");
		sql.append("   and t.success_flag = 0 ");
		sql.append("   and substr(t.login_time, 0, 8) = ?");
		dbHelper.execute(sql.toString(), params);

	}

}
