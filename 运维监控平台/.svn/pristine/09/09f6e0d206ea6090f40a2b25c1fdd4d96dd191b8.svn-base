package com.cplatform.mall.dc.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cplatform.dbhelp.DbHelper;
import com.cplatform.dbhelp.page.ListPage;
import com.cplatform.mall.dc.dao.DcUserDao;
import com.cplatform.mall.dc.dao.DcUserRoleDao;
import com.cplatform.mall.dc.entity.DcArea;
import com.cplatform.mall.dc.entity.DcUser;
import com.cplatform.mall.dc.entity.DcUserRole;
import com.cplatform.mall.dc.model.SessionUser;
import com.cplatform.mall.dc.utils.AesWithCbcService;
import com.cplatform.mall.dc.utils.AppConfig;

/**
 * 用户管理服务类 <br>
 * <p>
 * Copyright: Copyright (c) 2013-12-26 下午6:49:12
 * <p>
 * Company: 苏州宽连信息技术有限公司
 * <p>
 * 
 * @author zhuangxx@c-platform.com
 * @version 1.0.0
 */
@Service
public class DcUserService {

	@Autowired
	DcUserDao dcUserDao;

	@Autowired
	AppConfig appConfig;

	@Autowired
	DcMenuService menuService;

	@Autowired
	DcUserRoleDao dcUserRoleDao;

	@Autowired
	AesWithCbcService aesWithCbcService;

	@Autowired
	DbHelper dbHelper;

	@Autowired
	DcRoleService dcRoleService;

	/**
	 * 解析token，返回登录
	 * 
	 * @param token
	 * @return
	 * @throws Exception
	 */
	public DcUser parseToken(String token) throws Exception {
		String md5 = token.substring(0, 32);
		String info = aesWithCbcService.decrypt(token.substring(32));
		String compileMd5 = DigestUtils.md5Hex(info);
		if (!md5.equals(compileMd5)) {
			throw new RuntimeException("wrong token.");
		}
		String[] infos = info.split("~");
		String code = infos[0];
		String pass = infos[1];
		return checkLogin(code, pass, true);
	}

	/**
	 * 检查给定名密码正确
	 * 
	 * @param code
	 * @param pass
	 * @param digested
	 *            传入密码是否已摘要
	 * @return
	 */
	public DcUser checkLogin(String code, String pass, boolean digested) {
		DcUser user = dcUserDao.findByCodeAndStatus(code, DcUser.STATUS_VALID);
		if (user == null) {
			return null;
		}
		// salt = register time;
		if (!digested) {
			pass = md5pass(pass, null);
		}
		if (!user.getPwd().equals(pass)) {
			return null;
		} else {
			return user;
		}
	}

	/**
	 * 登录状态写session
	 * 
	 * @param request
	 * @param user
	 * @throws SQLException
	 */
	public void writeLoginSession(HttpServletRequest request, DcUser user) throws SQLException {
		// get more
		SessionUser sessionUser = new SessionUser();

		try {
			PropertyUtils.copyProperties(sessionUser, user);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("error in copy properties to session.", e);
		}

		sessionUser.setUserId(sessionUser.getId());

		sessionUser.setMenus(menuService.menuOfUser(sessionUser));

		sessionUser.setListArea(getListArea(user));

		sessionUser.setAreas(getAreas(user.getArea()));

		request.getSession().setAttribute(SessionUser.SESSION_USER_KEY, sessionUser);
	}

	private List<DcArea> getAreas(String area) throws SQLException {
		String sql = "select * from t_dc_area where area_code in (" + area + ") order by area_code";
		return dbHelper.getBeanList(sql, DcArea.class);
	}

	/**
	 * 获取地市数据查看权限
	 * 
	 * @param user
	 *            用户
	 * @return 地市数据查看权限
	 * @throws SQLException
	 */
	private String[] getListArea(DcUser user) throws SQLException {
		String area = user.getArea();
		if (area != null && !"".equals(area)) {
			return area.split(",");
		}
		return null;
	}

	/**
	 * 把token值写入到cookie
	 * 
	 * @param response
	 * @param token
	 */
	public void writeCookieToken(HttpServletResponse response, String token) {
		Cookie cookie = new Cookie(AppConfig.COOKIE_TOKEN, token);
		cookie.setMaxAge(appConfig.getCookiesMaxAge());
		response.addCookie(cookie);
	}

	/**
	 * 从浏览器清除对应的token cookie
	 * 
	 * @param response
	 */
	public void clearCookieToken(HttpServletResponse response) {
		Cookie cookie = new Cookie(AppConfig.COOKIE_TOKEN, null);
		cookie.setMaxAge(0);
		response.addCookie(cookie);
	}

	/**
	 * 生成cookie所需的登录密文
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public String cryptCookieToken(DcUser user) throws Exception {
		String info = user.getCode() + "~" + user.getPwd() + "~" + String.valueOf(System.currentTimeMillis());
		String md5 = DigestUtils.md5Hex(info);
		return md5 + aesWithCbcService.encrypt(info);
	}

	/**
	 * 对原始密码进行摘要并加入salt
	 * 
	 * @param pwd
	 *            密码
	 * @param salt
	 * @return
	 */
	public String md5pass(String pwd, String salt) {
		return DigestUtils.md5Hex(pwd + (salt == null ? "" : salt));
	}

	/**
	 * 分页显示列表
	 * 
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public ListPage<Map<String, Object>> findAllDcUsersPage(int page, int pageSize) throws SQLException {
		// String sql =
		// "select du.*,dr.role_name from t_dc_user du, t_dc_role dr, t_dc_user_role dur where dr.id = dur.role_id and du.id = dur.user_id ";
		// String sql =
		// "select u.*, t.role_name from t_dc_user u left join( select du.id,wmsys.wm_concat(dr.role_name) role_name from t_dc_user du, t_dc_role dr, t_dc_user_role dur where dr.id = dur.role_id and du.id = dur.user_id and dr.status = 1 group by du.id) t on t.id = u.id where u.status = 1";
		// return dbHelper.getNativeMapPage(sql, page, pageSize);
		String sql = "select u.* from t_dc_user u where u.status = 1";
		ListPage<Map<String, Object>> allDcUsers = dbHelper.getNativeMapPage(sql, page, pageSize);
		for (int i = 0; i < allDcUsers.getList().size(); i++) {
			Map<String, Object> dcUser = allDcUsers.getList().get(i);
			dcUser.put("role_name", dcRoleService.getUserRoleName(Long.parseLong(allDcUsers.getList().get(i).get("id").toString())));
		}
		return allDcUsers;
	}

	/**
	 * 增加角色
	 * 
	 * @param DcUser
	 */
	@Transactional
	public void addDcUser(DcUser dcUser) {
		DcUser du = dcUserDao.save(dcUser);// 增加操作员
		if (dcUser.getRoleId() != null) {
			List<DcUserRole> dcUserRoleList = new ArrayList<DcUserRole>();
			for (Long roleId : dcUser.getRoleId()) {
				DcUserRole DcUserRole = new DcUserRole();
				DcUserRole.setRoleId(roleId);
				DcUserRole.setUserId(du.getId());
				dcUserRoleList.add(DcUserRole);
			}
			dcUserRoleDao.save(dcUserRoleList);// 增加操作员权限
		}
	}

	/**
	 * 编辑角色
	 */
	@Transactional
	public void editDcUser(DcUser dcUser) {
		DcUser du = dcUserDao.save(dcUser);// 增加操作员
		dcUserRoleDao.deleteByUserId(du.getId());// 删除角色
		if (dcUser.getRoleId() != null) {
			List<DcUserRole> dcUserRoleList = new ArrayList<DcUserRole>();
			for (Long roleId : dcUser.getRoleId()) {
				DcUserRole DcUserRole = new DcUserRole();
				DcUserRole.setRoleId(roleId);
				DcUserRole.setUserId(du.getId());
				dcUserRoleList.add(DcUserRole);
			}
			dcUserRoleDao.save(dcUserRoleList);// 增加的角色
		}
	}

	/**
	 * 删除用户
	 * 
	 * @param id
	 * @throws SQLException
	 */
	@Transactional
	public void deleteDcUser(long id) throws SQLException {
		String sql = "update t_dc_user set status = 0 where id = " + id;
		dbHelper.execute(sql);
	}

	/**
	 * 获得获得用户角色
	 * 
	 * @param userId
	 * @return
	 */
	public List<DcUserRole> findOpRoleByUserId(long userId) {
		return dcUserRoleDao.findByUserId(userId);
	}

	/**
	 * 验证编辑角色信息
	 * 
	 * @param userId
	 * @param session
	 * @return
	 */
	public boolean checkAccountPermission(long userId, HttpSession session) {
		SessionUser sessionUser = (SessionUser) session.getAttribute(SessionUser.SESSION_USER_KEY);
		if (sessionUser == null) {
			return false;
		}

		DcUser dcUser = this.dcUserDao.findOne(sessionUser.getId());
		if (dcUser == null) {
			return false;
		}

		return true;
	}

	/**
	 * 验证增加或修改的名 是否重复
	 * 
	 * @param code
	 */
	public int checkCodeOnly(String code) {
		DcUser op = this.dcUserDao.findByCode(code);
		if (op == null) {
			return 0;
		}
		return 1;
	}

	public void saveAuthority(Long id, String area) throws SQLException {
		// // 转化area格式
		// StringBuilder sb = new StringBuilder();
		// if (area != null && !"".equals(area)) {
		// String[] areas = area.split(",");
		// if (areas != null && areas.length > 0) {
		// for (String str : areas) {
		// sb.append("'").append(str).append("',");
		// }
		// sb.delete(sb.length() - 1, sb.length());
		// }
		// }

		if (area == null) {
			area = "";
		}
		// 清空原有数据
		String sql = "update t_dc_user set area = '" + area + "' where id = " + id;
		dbHelper.execute(sql);
	}
	
	/**
	 * 根据指定名称获得角色编号
	 * @param names
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String, String>> findRolesByNames(String names){
		StringBuffer sql = new StringBuffer("select id,role_name from T_DC_ROLE where role_name in (");
		String[] n = names.split(",");
		Object[] obj = new Object[]{};
		for(String name : n){
			sql.append("?,");
			obj = ArrayUtils.add(obj, name);
		}
		String sq = sql.substring(0,sql.length()-1)+")";
		System.out.println(sq);
		List<Map<String, String>> roles = null;
		try {
			roles = dbHelper.getMapList(sq,obj);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return roles;
	}

}
