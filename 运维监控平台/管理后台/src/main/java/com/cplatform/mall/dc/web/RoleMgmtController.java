package com.cplatform.mall.dc.web;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cplatform.mall.dc.entity.DcRole;
import com.cplatform.mall.dc.service.DcRoleService;
import com.cplatform.mall.dc.utils.JsonRespWrapper;
import com.cplatform.mall.dc.utils.LogUtils;

/**
 * 角色管理控制器
 * 
 * @author zhuangxx@c-platform.com
 * @since 2013-7-12
 */
@Controller
@RequestMapping("/sysMgmt/roleMgmt")
public class RoleMgmtController {
	@Autowired
	LogUtils logUtil;

	@Autowired
	DcRoleService dcRoleService;

	/**
	 * 跳转到列表页面
	 * 
	 * @param model
	 *            页面模型
	 * @param page
	 *            页码
	 * @param pageSize
	 *            分页大小
	 * @return 列表页面
	 * @throws SQLException
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String toList(Model model, @RequestParam(value = "page", defaultValue = "0", required = false) int page,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) throws SQLException {
		logUtil.recordOpLog("系统管理", "角色管理", LogUtils.OP_TYPE_QUERY);
		model.addAttribute("datas", dcRoleService.findAll(page, pageSize));
		return "/sysMgmt/roleMgmt/list";
	}

	/**
	 * 跳转到新增页面
	 * 
	 * @param model
	 *            页面模型
	 * @return 新增页面
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String toAdd(Model model) {
		model.addAttribute("role", new DcRole());
		return "/sysMgmt/roleMgmt/add";
	}

	/**
	 * 跳转到编辑页面
	 * 
	 * @param model
	 *            页面模型
	 * @param id
	 *            待编辑id
	 * @return 编辑页面
	 */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String toEdit(Model model, @PathVariable Long id) {
		model.addAttribute("role", dcRoleService.findOne(id));
		return "/sysMgmt/roleMgmt/edit";
	}

	/**
	 * 跳转到查看页面
	 * 
	 * @param model
	 *            页面模型
	 * @param id
	 *            待查看id
	 * @return 查看页面
	 */
	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String toView(Model model, @PathVariable Long id) throws SQLException {
		model.addAttribute("role", dcRoleService.findOne(id));
		List<Map<String, Object>> listMenus = dcRoleService.findAuthority();
		List<Map<String, Object>> listExistMenus = dcRoleService.findAuthority(id);
		if (listMenus != null && !listMenus.isEmpty() && listExistMenus != null && !listExistMenus.isEmpty()) {
			for (Map<String, Object> menu : listMenus) {
				String menuCode = (String) menu.get("menu_code");
				for (Map<String, Object> existMenu : listExistMenus) {
					String existMenuCode = (String) existMenu.get("menu_code");
					if (menuCode != null && menuCode.equals(existMenuCode)) {
						menu.put("exist", "true");
					}
				}
			}
		}
		model.addAttribute("menus", listMenus);
		return "/sysMgmt/roleMgmt/view";
	}

	/**
	 * 跳转到权限页面
	 * 
	 * @param model
	 *            页面模型
	 * @param id
	 *            待编辑id
	 * @return 权限页面
	 * @throws SQLException
	 */
	@RequestMapping(value = "/authority/{id}", method = RequestMethod.GET)
	public String toAuthority(Model model, @PathVariable Long id) throws SQLException {
		model.addAttribute("role", dcRoleService.findOne(id));
		List<Map<String, Object>> listMenus = dcRoleService.findAuthority();
		List<Map<String, Object>> listExistMenus = dcRoleService.findAuthority(id);
		if (listMenus != null && !listMenus.isEmpty() && listExistMenus != null && !listExistMenus.isEmpty()) {
			for (Map<String, Object> menu : listMenus) {
				String menuCode = (String) menu.get("menu_code");
				for (Map<String, Object> existMenu : listExistMenus) {
					String existMenuCode = (String) existMenu.get("menu_code");
					if (menuCode != null && menuCode.equals(existMenuCode)) {
						menu.put("exist", "true");
					}
				}
			}
		}
		model.addAttribute("menus", listMenus);
		return "/sysMgmt/roleMgmt/authority";
	}

	/**
	 * 执行权限操作
	 * 
	 * @param model
	 *            页面模型
	 * @param id
	 *            待编辑id
	 * @return 跳转到列表页面
	 * @throws SQLException
	 */
	@RequestMapping(value = "/authority/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Object doAuthority(Model model, @PathVariable Long id, @ModelAttribute("role") DcRole role)
			throws SQLException {
		logUtil.recordOpLog("系统管理", "角色管理", LogUtils.OP_TYPE_UPDATE);
		dcRoleService.saveAuthority(id, role.getMenus());
		return JsonRespWrapper.success("进入列表页面！", "/sysMgmt/roleMgmt");
	}

	/**
	 * 执行新增操作
	 * 
	 * @param model
	 *            页面模型
	 * @param dcRole
	 *            待新增实体
	 * @return 跳转到列表页面
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Object doSave(Model model, @ModelAttribute("role") DcRole dcRole) {
		logUtil.recordOpLog("系统管理", "角色管理", LogUtils.OP_TYPE_INSERT);
		dcRole.setStatus(DcRole.STATUS_USING);
		dcRoleService.save(dcRole);
		return JsonRespWrapper.success("进入列表页面！", "/sysMgmt/roleMgmt");
	}

	/**
	 * 执行更新操作
	 * 
	 * @param model
	 *            页面模型
	 * @param dcRole
	 *            待更新实体
	 * @param id
	 *            待更新实体id
	 * @return 跳转到列表页面
	 * @throws SQLException
	 */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Object doUpdate(Model model, @ModelAttribute("role") DcRole dcRole, @PathVariable Long id)
			throws SQLException {
		logUtil.recordOpLog("系统管理", "角色管理", LogUtils.OP_TYPE_UPDATE);
		dcRole.setId(id);
		dcRoleService.update(dcRole);
		return JsonRespWrapper.success("进入列表页面！", "/sysMgmt/roleMgmt");
	}

	/**
	 * 执行删除操作
	 * 
	 * @param model
	 *            页面模型
	 * @param dcRole
	 *            待删除实体
	 * @param id
	 *            待删除实体id
	 * @return 跳转到列表页面
	 * @throws SQLException
	 */
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Object doDelete(Model model, @ModelAttribute("role") DcRole dcRole, @PathVariable Long id)
			throws SQLException {
		logUtil.recordOpLog("系统管理", "角色管理", LogUtils.OP_TYPE_DELETE);
		dcRole.setId(id);
		dcRoleService.delete(dcRole);
		return JsonRespWrapper.success("进入列表页面！", "/sysMgmt/roleMgmt");
	}

	/**
	 * 验证角色名是否已经存在
	 * 
	 * @param roleName
	 *            角色名
	 * @return
	 */
	@RequestMapping(value = "/checkRoleName", method = RequestMethod.POST)
	@ResponseBody
	public int checkRoleName(@RequestParam(value = "roleName", required = false) String roleName) {
		return dcRoleService.checkRoleName(roleName);
	}
}
