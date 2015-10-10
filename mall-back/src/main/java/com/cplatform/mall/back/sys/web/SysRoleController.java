package com.cplatform.mall.back.sys.web;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cplatform.dbhelp.page.Page;
import com.cplatform.mall.back.model.SessionUser;
import com.cplatform.mall.back.sys.entity.SysRole;
import com.cplatform.mall.back.sys.entity.SysRolePrivilege;
import com.cplatform.mall.back.sys.service.SysRoleService;
import com.cplatform.mall.back.utils.JsonRespWrapper;
import com.cplatform.mall.back.utils.LogUtils;
import com.cplatform.util2.TimeStamp;

/**
 * 角色控制类 Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-5-27 下午7:58:03
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: LiuDong@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
@Controller
@RequestMapping("/sys/role")
public class SysRoleController {

	private final String MODULE_NAME = "角色管理";

	@Autowired
	private LogUtils logUtils;

	@Autowired
	private SysRoleService roleService;

	/**
	 * 获取页面列表
	 * 
	 * @param roleName
	 * @param page
	 * @param session
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/list")
	public String regonList(@RequestParam(value = "roleName", required = false, defaultValue = "") String roleName,
	        @RequestParam(value = "page", required = false, defaultValue = "1") int page, HttpSession session, Model model) throws SQLException {
		SessionUser sessionUser = (SessionUser) session.getAttribute(SessionUser.SESSION_USER_KEY);

		Long unitId = sessionUser.getUnitId();// == null ? 0L :
		                                      // sessionUser.getUntiId();
		Page<SysRole> pageRegon = roleService.findRegon(roleName, unitId, page, Page.DEFAULT_PAGESIZE);
		model.addAttribute("pageData", pageRegon);
		return "sys/role/role-list";
	}

	/**
	 * 跳转到添加角色页面
	 * 
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/roleAdd", method = RequestMethod.GET)
	public String roleAdd(HttpSession session, Model model) {
		SysRole role = new SysRole();
		model.addAttribute("role", role);
		SessionUser sessionUser = (SessionUser) session.getAttribute(SessionUser.SESSION_USER_KEY);
		String menuPrivilege = "";
		if (sessionUser.isSuperAdmin()) {
			menuPrivilege = roleService.getMenuPrivilege(true, null);
			// logUtils.logAdd("", "");

		} else {
			menuPrivilege = roleService.getMenuPrivilege(false, sessionUser.getId());
			// menuPrivilege = roleService.getMenuPrivilege(false, 12L);
		}
		model.addAttribute("menuPrivilege", menuPrivilege);
		return "sys/role/role-add";
	}

	/**
	 * 添加角色
	 * 
	 * @param role
	 * @param menu_privilege
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/roleAdd", method = RequestMethod.POST)
	@ResponseBody
	public Object roleSave(SysRole role, @RequestParam(value = "menu_privilege", required = false, defaultValue = "") String menu_privilege,
	        HttpSession session, Model model, BindingResult result) {
	try {
		// SysRole role = new SysRole();
		// model.addAttribute("role", role);
		if ("".equals(menu_privilege)) {
			result.rejectValue("menuPrivilege", null, "请选择权限。");
		}
		if (result.hasErrors()) {
			return JsonRespWrapper.error(result.getFieldErrors());
		}
		SessionUser sessionUser = (SessionUser) session.getAttribute(SessionUser.SESSION_USER_KEY);
		role.setUpdateUserId(sessionUser.getId());
		role.setUnitId(sessionUser.getUnitId());
		role.setUpdateTime(TimeStamp.getTime(TimeStamp.YYYYMMDDhhmmss));
		String[] arrayMenuPrivilege = menu_privilege.split(",");
		role = roleService.addOrUpdateRole(role);
		String menuInf = "";
		String menu_code = "";
		String btns = "";
		for (int i = 0; i < arrayMenuPrivilege.length; i++) {
			SysRolePrivilege rolePrivilege = new SysRolePrivilege();
			menuInf = menu_privilege.split(",")[i];
			menu_code = menuInf.split("#")[0];
			rolePrivilege.setMenuCode(menu_code);
			if (menuInf.split("#").length <= 1) {
				btns = "";
			} else {
				btns = menuInf.substring(menu_code.length() + 1, menuInf.length());
			}
			rolePrivilege.setMenuBtn(btns);
			rolePrivilege.setRoleId(role.getId());
			roleService.addOrUpdateRolePrivilege(rolePrivilege);
			logUtils.logAdd("添加角色", "操作成功！角色编号："+role.getId());
		}
	} catch (Exception e) {
		logUtils.logAdd("添加角色", "操作失败！角色编号："+role.getId());
	}
		

		return JsonRespWrapper.success("添加成功", "/sys/role/list");
	}

	/**
	 * 删除角色
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/roleDel")
	@ResponseBody
	public Object roleDel(@RequestParam(value = "id") Long id) {
	try {
		List<SysRolePrivilege> rolePrivlegeList = this.roleService.findByRoleId(id);
		if (rolePrivlegeList != null && rolePrivlegeList.size() > 0) {
			for (int i = 0; i < rolePrivlegeList.size(); i++) {
				this.roleService.delRolePrivilege(rolePrivlegeList.get(i).getId());
			}
		}
		this.roleService.delRole(id);
		logUtils.logDelete("删除角色", "操作成功！角色编号："+id);
	} catch (Exception e) {
		logUtils.logDelete("删除角色", "操作失败！角色编号："+id);
	}

		return JsonRespWrapper.success("删除成功", "/sys/role/list");
	}

	/**
	 * 跳转到修改角色页面
	 * 
	 * @param id
	 * @param session
	 * @param model
	 * @return
	 * @throws SQLException 
	 */
	@RequestMapping(value = "/roleEdit", method = RequestMethod.GET)
	public String roleEdit(@RequestParam(value = "id") Long id, HttpSession session, Model model) throws SQLException {
		SysRole role = this.roleService.findById(id);
		model.addAttribute("role", role);
		SessionUser sessionUser = (SessionUser) session.getAttribute(SessionUser.SESSION_USER_KEY);
		String menuPrivilege = "";
		if (sessionUser.isSuperAdmin()) {
			menuPrivilege = roleService.getMenuPrivilegeForEdit(true, null, role.getId());
		} else {
			menuPrivilege = roleService.getMenuPrivilegeForEdit(false, sessionUser.getId(), role.getId());
			// menuPrivilege = roleService.getMenuPrivilegeForEdit(false, 12L,
			// role.getId());
		}
		model.addAttribute("menuPrivilege", menuPrivilege);
		return "sys/role/role-edit";
	}

	/**
	 * 修改角色
	 * 
	 * @param role
	 * @param menu_privilege
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/roleEdit", method = RequestMethod.POST)
	@ResponseBody
	public Object roleUpdate(SysRole role, @RequestParam(value = "menu_privilege", required = false, defaultValue = "") String menu_privilege,
	        HttpSession session, Model model, BindingResult result) {
	try {
		if ("".equals(menu_privilege)) {
			result.rejectValue("menuPrivilege", null, "请选择权限。");
		}
		if (result.hasErrors()) {
			return JsonRespWrapper.error(result.getFieldErrors());
		}
		SessionUser sessionUser = (SessionUser) session.getAttribute(SessionUser.SESSION_USER_KEY);
		role.setUpdateUserId(sessionUser.getId());
		role.setUnitId(sessionUser.getUnitId());
		role.setUpdateTime(TimeStamp.getTime(TimeStamp.YYYYMMDDhhmmss));
		String[] arrayMenuPrivilege = menu_privilege.split(",");
		role = roleService.addOrUpdateRole(role);
		List<SysRolePrivilege> rolePrivilegeList = this.roleService.findByRoleId(role.getId());
		if (rolePrivilegeList != null && rolePrivilegeList.size() > 0) {
			for (SysRolePrivilege vo : rolePrivilegeList) {
				roleService.delRolePrivilege(vo.getId());
			}
		}
		String menuInf = "";
		String menu_code = "";
		String btns = "";

		for (int i = 0; i < arrayMenuPrivilege.length; i++) {
			SysRolePrivilege rolePrivilege = new SysRolePrivilege();
			menuInf = menu_privilege.split(",")[i];
			menu_code = menuInf.split("#")[0];
			rolePrivilege.setMenuCode(menu_code);
			if (menuInf.split("#").length <= 1) {
				btns = "";
			} else {
				btns = menuInf.substring(menu_code.length() + 1, menuInf.length());
			}
			rolePrivilege.setMenuBtn(btns);
			rolePrivilege.setRoleId(role.getId());
			roleService.addOrUpdateRolePrivilege(rolePrivilege);
			logUtils.logModify("修改角色", "操作成功！角色编号："+role.getId());
		}
	} catch (Exception e) {
		logUtils.logModify("修改角色", "操作失败！角色编号："+role.getId());
	}
		return JsonRespWrapper.success("修改成功", "/sys/role/list");
	}

	/**
	 * 跳转到查看角色页面
	 * 
	 * @param id
	 * @param session
	 * @param model
	 * @return
	 * @throws SQLException 
	 */
	@RequestMapping(value = "/roleView", method = RequestMethod.GET)
	public String roleView(@RequestParam(value = "id") Long id, HttpSession session, Model model) throws SQLException {
		SysRole role = this.roleService.findById(id);
		model.addAttribute("role", role);

		String menuPrivilege = roleService.getMenuPrivilegeForView(role.getId());
		model.addAttribute("menuPrivilege", menuPrivilege);
		return "sys/role/role-view";
	}
}
