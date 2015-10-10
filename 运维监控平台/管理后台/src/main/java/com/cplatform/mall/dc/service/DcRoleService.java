package com.cplatform.mall.dc.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplatform.dbhelp.DbHelper;
import com.cplatform.dbhelp.page.ListPage;
import com.cplatform.mall.dc.dao.DcRoleDao;
import com.cplatform.mall.dc.dao.DcRoleMenuDao;
import com.cplatform.mall.dc.entity.DcRole;
import com.cplatform.mall.dc.entity.DcRoleMenu;

/**
 * 角色管理服务类
 * 
 * @author zhuangxx@c-platform.com
 * @since 2013-7-11
 */
@Service
public class DcRoleService {
	@Autowired
	DcRoleDao dcRoleDao;

	@Autowired
	DcRoleMenuDao dcRoleMenuDao;

	@Autowired
	DbHelper dbHelper;

	/**
	 * 按照id查询
	 * 
	 * @param id 角色id
	 * @return 查询结果
	 */
	public DcRole findOne(Long id) {
		return dcRoleDao.findOne(id);

	}

	/**
	 * 查询所有
	 * 
	 * @param page 页码
	 * @param pageSize 每页大小
	 * @return 查询结果
	 * @throws SQLException
	 */
	public ListPage<Map<String, Object>> findAll(int page, int pageSize) throws SQLException {
		String sql = "select r.*, case when ur.userCount is null then 0 else ur.userCount end userCount from t_dc_role r left join (select role_id, count(user_id) userCount from t_dc_user_role group by role_id) ur on ur.role_id = r.id where r.status = 1";

		return dbHelper.getNativeMapPage(sql, page, pageSize);
	}

	/**
	 * 保存
	 * 
	 * @param dcRole 角色对象
	 */
	public void save(DcRole dcRole) {
		dcRoleDao.save(dcRole);
	}

	/**
	 * 更新
	 * 
	 * @param dcRole 角色对象
	 * @throws SQLException
	 */
	public void update(DcRole dcRole) throws SQLException {
		String sql = "update t_dc_role set role_name = ?, remark = ? where id = ? ";
		dbHelper.execute(sql, dcRole.getRoleName(), dcRole.getRemark(), dcRole.getId());
	}

	/**
	 * 删除
	 * 
	 * @param dcRole 角色对象
	 * @throws SQLException
	 */
	public void delete(DcRole dcRole) throws SQLException {
		String sql = "update t_dc_role set status = 0 where id = " + dcRole.getId();
		dbHelper.execute(sql);
	}

	/**
	 * 获取已有权限列表
	 * 
	 * @param id 角色id
	 * @return 已有权限列表
	 * @throws SQLException
	 */
	public List<Map<String, Object>> findAuthority(Long id) throws SQLException {
		String sql = "select m.* from t_dc_role_menu rm left join t_dc_menu m on m.menu_code = rm.menu_code where rm.role_id = " + id;
		return dbHelper.getNativeMapList(sql);
	}

	/**
	 * 获取所有权限列表
	 * 
	 * @return 所有权限列表
	 * @throws SQLException
	 */
	public List<Map<String, Object>> findAuthority() throws SQLException {
		String sql = "select * from t_dc_menu";
		return dbHelper.getNativeMapList(sql);
	}

	/**
	 * 保存菜单权限
	 * 
	 * @param id 角色id
	 * @param menus 菜单权限
	 * @throws SQLException
	 */
	public void saveAuthority(Long id, List<String> menus) throws SQLException {
		// 清空原有数据
		String sql = "delete from t_dc_role_menu where role_id = " + id;
		dbHelper.execute(sql);

		// 如果新分配菜单列表为空，则不再分配菜单。
		if (menus == null || menus.isEmpty()) {
			return;
		}

		// 分配选择的菜单
		for (String menu : menus) {
			DcRoleMenu rm = new DcRoleMenu();
			rm.setRoleId(id);
			rm.setMenuCode(menu);
			dcRoleMenuDao.save(rm);
		}
	}

	public int checkRoleName(String roleName) {
		try {
			DcRole role = dcRoleDao.findByRoleName(roleName);
			if (role == null) {
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}

	public String getUserRoleName(Long userid) throws SQLException {
		StringBuffer roleName = new StringBuffer("");
		String sql = "SELECT r.* FROM T_DC_ROLE r, T_DC_USER_ROLE ur WHERE r.id = ur.role_id AND r.status = 1 AND ur.user_id = " + userid;
		List<DcRole> dcRoles = dbHelper.getBeanList(sql, DcRole.class);
		for (int i = 0; i < dcRoles.size(); i++) {
			if (dcRoles.get(i) != null && StringUtils.isNotBlank(dcRoles.get(i).getRoleName())) {
				if (i != dcRoles.size() - 1) {
					roleName.append(dcRoles.get(i).getRoleName() + ",");
				} else {
					roleName.append(dcRoles.get(i).getRoleName());
				}
			}
		}
		return roleName.toString();
	}
}
