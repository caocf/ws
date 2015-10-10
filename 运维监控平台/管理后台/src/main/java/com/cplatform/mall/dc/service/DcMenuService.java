package com.cplatform.mall.dc.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.cplatform.dbhelp.DbHelper;
import com.cplatform.mall.dc.dao.DcUserDao;
import com.cplatform.mall.dc.entity.DcMenu;
import com.cplatform.mall.dc.entity.DcRole;
import com.cplatform.mall.dc.model.MenuPrivilege;
import com.cplatform.mall.dc.model.SessionUser;
import com.cplatform.mall.dc.model.StackSysMenu;
import com.google.common.collect.Lists;

/**
 * 菜单服务类
 * 
 * @author zhuangxx@c-platform.com
 * @since 2013-7-10
 */
@Service
public class DcMenuService {

	protected static final Log logger = LogFactory.getLog(DcMenuService.class);

	@Autowired
	private DcUserDao dcUserDao;

	@Autowired
	DbHelper dbHelper;

	// @Autowired
	// private UserRolePrivilegeDao userRolePrivilegeDao;

	@Autowired
	CommonCacheService commonCacheService;

	@Autowired
	private DcRoleService dcRoleService;

	/**
	 * 从HttpSession中获取用户权限列表
	 * 
	 * @param session
	 *            HttpSession
	 * @return 登录用户拥有的菜单、权限的Map
	 */
	public Map<String, MenuPrivilege> getPrivilege(HttpSession session) {
		SessionUser sessionUser = SessionUser.getSessionUser();
		Assert.notNull(sessionUser, "user information in session cannot be null");
		return sessionUser.getPrivilege();
	}

	/**
	 * 将登录用户拥有的菜单、权限的Map保存至HttpSession
	 * 
	 * @param session
	 *            HttpSession
	 * @param map
	 *            登录用户拥有的菜单、权限的Map
	 */
	public void savePrivilege(HttpSession session, Map<String, MenuPrivilege> map) {
		SessionUser sessionUser = SessionUser.getSessionUser();
		Assert.notNull(sessionUser, "user information in session cannot be null");
		sessionUser.setPrivilege(map);
	}

	/**
	 * 查找用户是否有某个按钮的权限
	 * 
	 * @param session
	 *            HttpSession
	 * @param modelCode
	 *            菜单的模块名
	 * @param button
	 *            本页面的按钮名
	 * @return 是否有权限
	 * @throws SQLException
	 */
	// public boolean hasButton(HttpSession session, String modelCode, String
	// button) {
	//
	// SessionUser sessionUser = SessionUser.getSessionUser();
	//
	// if (sessionUser == null) {
	// return false;
	// }
	//
	// // // 超级管理员
	// // if (sessionUser.getType() == ShopUser.TYPE_ADMIN) {
	// // return true;
	// // }
	// //
	// // // 取出权限map
	// // Map<String, MenuPrivilege> map = sessionUser.getPrivilege();
	// //
	// // // 如果权限为空直接返回false
	// // if (map == null) {
	// // return false;
	// // }
	// //
	// // int shopClass = sessionUser.getShopClass();
	//
	// // 循环检查权限map元素
	// Collection<MenuPrivilege> privileges = map.values();
	// for (MenuPrivilege menu : privileges) {
	//
	// // 找到对应的model code项，查找是否存在button
	// DcMenu item =
	// commonCacheService.getAllMenuMap(shopClass).get(menu.getMenuCode());
	// String mCode = (menu == null ? "" : item.getModelCode());
	//
	// if (modelCode.equals(mCode)) {
	// Set<String> sbt = menu.getButtons();
	// if (sbt != null) {
	// return sbt.contains(button);
	// }
	// break;
	// }
	// }
	// return false;
	// }

	public List<StackSysMenu> menuOfUser(SessionUser sessionUser) throws SQLException {
		List<DcMenu> ms = filteredMenus(sessionUser);

		List<StackSysMenu> result = new ArrayList<StackSysMenu>();
		int pos = 0;
		while (pos < ms.size()) {
			StackSysMenu ssm = toStack(ms.get(pos));
			result.add(ssm);
			pos = findChildMenu(ms, pos + 1, ssm);
		}
		return result;
	}

	public int findChildMenu(List<DcMenu> all, int pos, StackSysMenu ssm) {
		String mcode = ssm.getMenuCode();

		while (pos < all.size()) {
			DcMenu thism = all.get(pos);
			if (thism.getParentCode().equals(mcode)) {
				StackSysMenu ok = toStack(thism);
				ssm.getChildMenus().add(ok);
				pos = findChildMenu(all, pos + 1, ok);
			} else {
				return pos;
			}
		}
		return pos;
	}

	private StackSysMenu toStack(DcMenu menu) {
		StackSysMenu result = new StackSysMenu();
		try {
			PropertyUtils.copyProperties(result, menu);
		} catch (Exception e) {
			//
		}
		return result;
	}

	//
	//
	// /**
	// * 从session加载用户拥有的菜单列表
	// *
	// * @param sessionUser
	// * SessionUser
	// * @return 菜单列表
	// */
	public List<DcMenu> filteredMenus(SessionUser sessionUser) throws SQLException {
		String sql = "select r.* from t_dc_user u, t_dc_user_role ur, t_dc_role r where ur.user_id = u.id and r.id = ur.role_id and u.user_code = '"
				+ sessionUser.getCode() + "'";
		List<DcRole> listRole = dbHelper.getBeanList(sql, DcRole.class);

		if (listRole != null && !listRole.isEmpty()) {
			for (DcRole dcRole : listRole) {
				if (dcRole.getId() == 0) {
					sql = "select * from t_dc_menu order by menu_code";
					Map map = MsOsUptimeService.MsOslistMenu;
					Map map2 = PVService.PVMenu;
					List<DcMenu> listMenu = dbHelper.getBeanList(sql, DcMenu.class);
					List<DcMenu> newList = new ArrayList<DcMenu>();
					for(DcMenu dc:listMenu){
						newList.add(dc);
						if(dc.getMenuCode().equals(map.get("key"))){
							newList.addAll((List<DcMenu>)map.get("list"));
						}
						if(dc.getMenuCode().equals(map2.get("key"))){
							newList.addAll((List<DcMenu>)map2.get("list"));
						}
					}
					return newList;
				}
			}
		}
		sql = "select distinct m.*from t_dc_user u, t_dc_user_role ur, t_dc_role r, t_dc_role_menu rm, t_dc_menu m where ur.user_id = u.id and r.id = ur.role_id and rm.role_id = ur.role_id and m.menu_code = rm.menu_code and u.user_code = '"
				+ sessionUser.getCode() + "' and u.status = 1 and r.status = 1 order by m.menu_code";

		List<DcMenu> listMenu = dbHelper.getBeanList(sql, DcMenu.class);

		return listMenu;
	}

	public List<DcMenu> getPathMenus(String url, int shopClass) {
		List<DcMenu> menus = Lists.newArrayList();
		DcMenu menu = findMenuByUrl(url, shopClass);
		if (menu != null
				&& !FilenameUtils.normalizeNoEndSeparator(url).equals(
						FilenameUtils.normalizeNoEndSeparator(menu.getMenuUrl()))) {
			// menus.add(new DcMenu());
		}
		while (menu != null) {
			menus.add(menu);
			Map<String, DcMenu> map = commonCacheService.getAllMenuMap();
			String pCode = menu.getParentCode();
			menu = map.get(pCode);
		}
		Collections.reverse(menus);
		return menus;
	}

	private DcMenu findMenuByUrl(String url, int shopClass) {
		List<DcMenu> all = commonCacheService.getAllMenuList();
		for (DcMenu sysMenu : all) {
			if (url.startsWith(sysMenu.getMenuUrl())) {
				return sysMenu;
			}
		}
		return null;
	}

	public static class Level1Menu {
		private String code;
		private String name;
		private List<Level2Menu> menu;

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public List<Level2Menu> getMenu() {
			return menu;
		}

		public void setMenu(List<Level2Menu> menu) {
			this.menu = menu;
		}
	}

	public static class Level2Menu {
		private String code;
		private String name;
		private String url;

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}
	}
}
