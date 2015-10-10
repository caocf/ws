package com.cplatform.mall.dc.web;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cplatform.dbhelp.DbHelper;
import com.cplatform.mall.dc.dao.DcRoleDao;
import com.cplatform.mall.dc.dao.DcUserDao;
import com.cplatform.mall.dc.dao.DcUserRoleDao;
import com.cplatform.mall.dc.entity.DcRole;
import com.cplatform.mall.dc.entity.DcUser;
import com.cplatform.mall.dc.service.DcAreaService;
import com.cplatform.mall.dc.service.DcUserService;
import com.cplatform.mall.dc.utils.JsonRespWrapper;
import com.cplatform.mall.dc.utils.LogUtils;
import com.cplatform.mall.dc.web.validator.DcUserPostValidator;

/**
 * 用户管理控制器
 * 
 * @author zhangdong
 * @since 2013-7-12
 */
@Controller
@RequestMapping("/sysMgmt/userMgmt")
public class UserMgmtController {
	@Autowired
	LogUtils logUtil;

	static final Log logger = LogFactory.getLog(UserMgmtController.class);

	@Autowired
	DcUserDao dcUserDao;

	@Autowired
	DcAreaService dcAreaService;

	@Autowired
	DcUserService dcUserService;

	@Autowired
	DcUserPostValidator dcUserPostValidator;

	@Autowired
	DcRoleDao dcRoleDao;

	@Autowired
	DcUserRoleDao dcUserRoleDao;

	@Autowired
	DbHelper dbHelper;

	/**
	 * 用户列表分页
	 */
	@RequestMapping(value = "")
	public String list(
			@RequestParam(value = "page", defaultValue = "1", required = false) int page,
			@RequestParam(value = "pageSize", defaultValue = "20", required = false) int pageSize,
			Model model) throws SQLException {
		logUtil.recordOpLog("系统管理", "用户管理", LogUtils.OP_TYPE_QUERY);
		model.addAttribute("dcUserPage",
				dcUserService.findAllDcUsersPage(page, pageSize));
		return "sysMgmt/userMgmt/list";
	}

	/**
	 * 跳转到新增用户页面
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String toAdd(Model model) {
		DcUser dcUser = new DcUser();
		model.addAttribute("dcUser", dcUser);
		model.addAttribute("roles", dcRoleDao.findByStatus(DcRole.STATUS_USING));
		return "sysMgmt/userMgmt/add";
	}

	/**
	 * 新增用户信息
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Object addDcUserPost(@ModelAttribute("dcUser") DcUser dcUser,
			HttpServletRequest request, HttpSession session,
			BindingResult result) {
		logUtil.recordOpLog("系统管理", "用户管理", LogUtils.OP_TYPE_INSERT);
		if (result.hasErrors()) {
			return JsonRespWrapper.error(result.getFieldErrors());
		} else {
			String newPass = dcUser.getNewPass();
			if (StringUtils.isEmpty(newPass) || newPass.length() < 6 || newPass.length() > 10) {
				newPass = "abcd1234";
			}
			String pass = dcUserService.md5pass(newPass, null);
			dcUser.setPwd(pass);
		}
		dcUser.setStatus(DcUser.STATUS_VALID);
		dcUserService.addDcUser(dcUser);
		logger.info("添加操作员");
		return JsonRespWrapper.success("操作员添加成功", "/sysMgmt/userMgmt");
	}

	/**
	 * 跳转到修改用户页面
	 * 
	 * @throws SQLException
	 */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String toEdit(@PathVariable Long id, Model model)
			throws SQLException {
		DcUser dcUser = dcUserDao.findOne(id);
		String sql = "select * from t_dc_role where status = 1";
		List<DcRole> findAll = dbHelper.getBeanList(sql, DcRole.class);
		List<Long> findById = dcUserRoleDao.findById(id);
		for (DcRole dcRole : findAll) {
			if (findById.contains(dcRole.getId())) {
				dcRole.setFlag(true);
			} else {
				dcRole.setFlag(false);
			}
		}
		model.addAttribute("dcUser", dcUser);
		model.addAttribute("roles", findAll);
		return "sysMgmt/userMgmt/edit";
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
	public String toAuthority(Model model, @PathVariable Long id)
			throws SQLException {
		DcUser user = dcUserDao.findOne(id);
		model.addAttribute("user", user);

		String area = user.getArea();
		String[] areas = null;
		if (area != null && !"".equals(area)) {
			areas = area.split(",");
		}

		List<Map<String, Object>> listArea = dcAreaService.findArea();
		if (listArea != null && !listArea.isEmpty()) {
			if (areas != null && areas.length > 0) {
				for (Map<String, Object> dcArea : listArea) {
					String code = (String) dcArea.get("AREA_CODE");
					for (int i = 0; i < areas.length; i++) {
						if (code != null && code.equals(areas[i])) {
							dcArea.put("exist", "true");
						}
					}
				}
			}
		}
		model.addAttribute("listArea", listArea);

		return "/sysMgmt/userMgmt/authority";
	}

	@RequestMapping(value = "/authority/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Object doAuthority(Model model, @PathVariable Long id,
			@ModelAttribute("user") DcUser user) throws SQLException {
		logUtil.recordOpLog("系统管理", "用户管理", LogUtils.OP_TYPE_UPDATE);
		dcUserService.saveAuthority(id, user.getArea());
		return JsonRespWrapper.success("进入列表页面！", "/sysMgmt/userMgmt");
	}

	/**
	 * 跳转到修改用户页面
	 * 
	 * @param id
	 *            待查看id
	 * @param model
	 *            页面模型
	 * @return
	 * @throws SQLException 
	 */
	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String toView(@PathVariable Long id, Model model) throws SQLException {
		DcUser dcUser = dcUserDao.findOne(id);
		model.addAttribute("dcUser", dcUser);
		String sql = "select * from t_dc_role where status = 1";
		List<DcRole> findAll = dbHelper.getBeanList(sql, DcRole.class);
		model.addAttribute("roles", findAll);
		model.addAttribute("have_roles", dcUserRoleDao.findByUserId(id));
		return "sysMgmt/userMgmt/view";
	}

	/**
	 * 修改用户信息
	 */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Object editDcUserPost(@PathVariable Long id,
			@ModelAttribute("dcUser") DcUser dcUserModel,
			HttpServletRequest request, HttpSession session,
			BindingResult result) {
		logUtil.recordOpLog("系统管理", "用户管理", LogUtils.OP_TYPE_UPDATE);
		DcUser dcUser = this.dcUserDao.findOne(id);
		dcUser.setName(dcUserModel.getName());
		dcUser.setEmail(dcUserModel.getEmail());
		dcUser.setTerminalId(dcUserModel.getTerminalId());
		dcUser.setRoleId(dcUserModel.getRoleId());
		// 需要更新密码
		if (!StringUtils.isEmpty(dcUserModel.getPwd())) {
			dcUserPostValidator.validate(dcUserModel, result);
			if (result.hasErrors()) {
				return JsonRespWrapper.error(result.getFieldErrors());
			} else {
				String pass = dcUserService.md5pass(dcUserModel.getPwd(), null);
				dcUser.setPwd(pass);
			}
		}

		dcUserService.editDcUser(dcUser);
		return JsonRespWrapper.success("操作员编辑成功", "/sysMgmt/userMgmt");
	}

	/**
	 * 删除用户
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "/del/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Object delete(@PathVariable Long id, HttpServletRequest request,
			HttpSession session) throws Exception {
		logUtil.recordOpLog("系统管理", "用户管理", LogUtils.OP_TYPE_DELETE);
		try {
			if (!dcUserService.checkAccountPermission(id, session)) {
				return JsonRespWrapper.error("no permission");
			}
			dcUserService.deleteDcUser(id);
			logger.info("删除操作员 id=" + id);
			// return "redirect:/sysMgmt/userMgmt";
			return JsonRespWrapper.success("删除用户成功", "/sysMgmt/userMgmt");
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			throw ex;
		}
	}

	@RequestMapping(value = "/check", method = RequestMethod.POST)
	@ResponseBody
	public int getCheck(
			@RequestParam(value = "code", required = false) String code) {
		return dcUserService.checkCodeOnly(code);
	}
}
