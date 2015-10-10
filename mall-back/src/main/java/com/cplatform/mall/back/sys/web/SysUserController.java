package com.cplatform.mall.back.sys.web;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cplatform.dbhelp.page.Page;
import com.cplatform.mall.back.model.SessionUser;
import com.cplatform.mall.back.order.service.OrderRefundService;
import com.cplatform.mall.back.sys.entity.SysRegion;
import com.cplatform.mall.back.sys.entity.SysRole;
import com.cplatform.mall.back.sys.entity.SysUnit;
import com.cplatform.mall.back.sys.entity.SysUser;
import com.cplatform.mall.back.sys.entity.SysUserRegion;
import com.cplatform.mall.back.sys.entity.SysUserRole;
import com.cplatform.mall.back.sys.service.SysRoleService;
import com.cplatform.mall.back.sys.service.UserService;
import com.cplatform.mall.back.sys.web.validator.UserValidator;
import com.cplatform.mall.back.utils.JsonRespWrapper;
import com.cplatform.mall.back.utils.LogUtils;
import com.cplatform.util2.TimeStamp;
import com.cplatform.util2.security.MD5;

/**
 * Title. <br>
 * 用户管理控制类 Description.
 * <p/>
 * Copyright: Copyright (c) 13-5-21 下午7:28
 * <p/>
 * Company: 北京宽连十方数字技术有限公司
 * <p/>
 * Author: nicky
 * <p/>
 * Version: 1.0
 * <p/>
 */

@Controller
@RequestMapping("/sys/user")
public class SysUserController {
	private static Logger log=Logger.getLogger(SysUserController.class);

	private final String MODULE_NAME = "用户管理";

	@Autowired
	private LogUtils logUtils;

	@Autowired
	private UserService userService;

	@Autowired
	private UserValidator userValidator;

	@Autowired
	private SysRoleService sysRoleService;

	@RequestMapping(value = "list")
	public String userList(SysUser user, @RequestParam(value = "page", required = false, defaultValue = "1") int page, HttpSession session,
	        Model model) throws SQLException {
		SessionUser sessionUser = (SessionUser) session.getAttribute(SessionUser.SESSION_USER_KEY);
		Page<SysUser> userlist = userService.listUser(user, session, page);
		model.addAttribute("pageData", userlist);
		model.addAttribute("userTypeMap", SysUser.userTypeMap);
		model.addAttribute("sessionUserId", sessionUser.getId());
		return "sys/user/user-list";
	}

	@RequestMapping(value = "userAdd", method = RequestMethod.GET)
	public String userAdd(HttpSession session, Model model) throws SQLException {
		SysUser user = new SysUser();
		SessionUser sessionUser = (SessionUser) session.getAttribute(SessionUser.SESSION_USER_KEY);
		List<SysRole> sysRoleList = this.sysRoleService.findByUpdateUserId(sessionUser.getId());
		model.addAttribute("user", user);
		model.addAttribute("sysRoleList", sysRoleList);

		if (sessionUser.getUnitId() == 0L) {
			List<SysUnit> unitList = this.userService.getSysUnitList();
			model.addAttribute("unitList", unitList);
		}
		if (sessionUser.getUnitId() == 0L || sessionUser.getSysUnit().getUnitType() == 1L) {
			model.addAttribute("pid", 0);
		} else {
			model.addAttribute("pid", sessionUser.getSysUnit().getAreaCode());
		}
		model.addAttribute("unitId", sessionUser.getUnitId());
		model.addAttribute("userTypeMap", SysUser.userTypeMap);
		model.addAttribute("unitType", sessionUser.getSysUnit().getUnitType());
		return "sys/user/user-add";
	}

	@RequestMapping(value = "userAdd", method = RequestMethod.POST)
	@ResponseBody
	public Object userSave(@ModelAttribute("user") SysUser sysUser, HttpServletRequest request, HttpSession session, BindingResult result) {

		// 提交表单的服务端验证，不是关键项目可以跳过此步骤。
		userValidator.validate(sysUser, result);
		if (result.hasErrors()) {
			return JsonRespWrapper.error(result.getFieldErrors());
		}

		
		sysUser.setFlag(1);
		String[] roles = request.getParameterValues("role");
		if (roles == null) {
			return JsonRespWrapper.successAlert("请选择角色！");
		}
		String regon = request.getParameter("regon");
		try {
			sysUser.setStatus(1);
			String time = TimeStamp.getTime(TimeStamp.YYYYMMDDhhmmss);
			sysUser.setValidTime(time.substring(0, 8));
			sysUser.setCreateTime(time);
			sysUser.setChangePwdTime(time);
			sysUser.setUpdateTime(time);
			sysUser.setUserPwd(MD5.digest2Str(sysUser.getUserPwd()));
			SessionUser sessionUser = (SessionUser) session.getAttribute(SessionUser.SESSION_USER_KEY);
			if (sysUser.getUnitId() == null || "".equals(sysUser.getUnitId())) {

				sysUser.setUnitId(sessionUser.getUnitId());
			}
			sysUser.setUpdateUserId(sessionUser.getId());

			// 执行业务逻辑
			sysUser = userService.addOrUpdateSysUser(sysUser);
			for (String role : roles) {
				SysUserRole sysUserRole = new SysUserRole();
				sysUserRole.setRoleId(Long.valueOf(role));
				sysUserRole.setUserId(sysUser.getId());
				userService.addOrUpdateSysUserRole(sysUserRole);
			}

			if (StringUtils.isNotBlank(regon)) {
				String[] regions = regon.split(",");
				for (String reg : regions) {
					SysUserRegion sysUserRegon = new SysUserRegion();
					sysUserRegon.setRegionCode(reg);
					sysUserRegon.setUserId(sysUser.getId());
					logUtils.logAdd("添加角色","添加成功！角色编号：" +sysUser.getId());
					userService.addOrUpdateSysUserRegon(sysUserRegon);
				}
			}

			/************* 跳转的各种方法 ********************/

			// 只提示信息
			// return JsonRespWrapper.successAlert("操作成功！");

			// 提示并跳转
			return JsonRespWrapper.success("添加成功", "/sys/user/list");

			// 直接跳转
			// return JsonRespWrapper.successJump("/user/list");

			// 对于想自己在客户端用js执行一些方法，可以直接返回success()，然后在客户端做一些操作

		}
		catch (Exception ex) {
			logUtils.logAdd("添加角色","添加失败！", +sysUser.getId());
			// 未知的错误消息，一般是exception catch后通知用户
			log.error(ex.getMessage());
			return JsonRespWrapper.error(ex.getMessage());
		}

	}

	@RequestMapping(value = "/userEdit", method = RequestMethod.GET)
	public String userEdit(@RequestParam(value = "id") Long id, HttpSession session, Model model) throws SQLException {
		SessionUser sessionUser = (SessionUser) session.getAttribute(SessionUser.SESSION_USER_KEY);
		SysUser sysUser = this.userService.findSysUserById(id);
		model.addAttribute("user", sysUser);
		List<SysRole> sysRoleList = this.sysRoleService.findByUpdateUserId(sessionUser.getId());
		model.addAttribute("sysRoleList", sysRoleList);

		if (sessionUser.getUnitId() == 0L) {
			List<SysUnit> unitList = this.userService.getSysUnitList();
			model.addAttribute("unitList", unitList);
			model.addAttribute("pid", 0);
		}
		if (sessionUser.getUnitId() == 0L || sessionUser.getSysUnit().getUnitType() == 1L) {
			model.addAttribute("pid", 0);
		} else {
			SysRegion sysRegon = this.userService.findByRegonCode(sessionUser.getSysUnit().getAreaCode());
			// sysRegonList = userService.getSysRegon(sysRegon.getId());
			model.addAttribute("pid", sysRegon.getRegionCode());
		}

		List<SysUserRole> sysUserRoleList = this.userService.findSysUserRoleByUserId(sysUser.getId());
		model.addAttribute("sysUserRoleList", sysUserRoleList);
		List<SysUserRegion> sysUserRegonList = this.userService.findSysUserRegonByUserId(sysUser.getId());
		String regon = "";
		String regonName = "";
		String json = "[";
		if (sysUserRegonList != null) {
			for (SysUserRegion vo : sysUserRegonList) {
				regon += vo.getRegionCode() + ",";
				SysRegion sysregon = this.userService.findByRegonCode(vo.getRegionCode());
				regonName += sysregon.getRegionName() + ",";
				String obj = "{code:'" + vo.getRegionCode() + "',name:'" + sysregon.getRegionName() + "'},";
				json += obj;
			}
		}
		if (json.length() > 1) {
			json = json.substring(0, json.length() - 1) + "]";
		} else {
			json += "]";
		}
		model.addAttribute("json", json);
		// model.addAttribute("sysUserRegonList", sysUserRegonList);
		// model.addAttribute("sysRegonList", sysRegonList);
		model.addAttribute("regon", regon);
		model.addAttribute("userTypeMap", SysUser.userTypeMap);
		model.addAttribute("regonName", regonName);
		model.addAttribute("unitId", sessionUser.getUnitId());
		model.addAttribute("unitType", sessionUser.getSysUnit().getUnitType());
		return "sys/user/user-edit";
	}

	@RequestMapping(value = "userEdit", method = RequestMethod.POST)
	@ResponseBody
	public Object userUpdate(@ModelAttribute("user") SysUser sysUser, HttpServletRequest request, HttpSession session, BindingResult result) {
		SessionUser sessionUser = (SessionUser) session.getAttribute(SessionUser.SESSION_USER_KEY);
		SysUser oldUser = this.userService.findSysUserById(sysUser.getId());
		if (sysUser.getUserPwd() == null || "".equals(sysUser.getUserPwd())) {
			sysUser.setUserPwd(oldUser.getUserPwd());
			if(StringUtils.isEmpty(sysUser.getConfirmPass())){
				sysUser.setConfirmPass(oldUser.getUserPwd());
			}
			userValidator.validate(sysUser, result);
		} else {
			//在user密码加密前对user进行验证
			userValidator.validate(sysUser, result);
			sysUser.setUserPwd(MD5.digest2Str(sysUser.getUserPwd()));
		}
		
		if (result.hasErrors()) {
			return JsonRespWrapper.error(result.getFieldErrors());
		}
//		if (sysUser.getFlag() == null) {
//			return JsonRespWrapper.successAlert("请选择标识！");
//		}
		
		sysUser.setFlag(1);
		String[] roles = request.getParameterValues("role");
		if (roles == null) {
			return JsonRespWrapper.successAlert("请选择角色！");
		}
		String regon = request.getParameter("regon");

		String time = TimeStamp.getTime(TimeStamp.YYYYMMDDhhmmss);
		sysUser.setStatus(oldUser.getStatus());
		sysUser.setValidTime(oldUser.getValidTime());
		sysUser.setCreateTime(oldUser.getCreateTime());
		sysUser.setChangePwdTime(oldUser.getChangePwdTime());
		sysUser.setUpdateTime(time);
		sysUser.setUpdateUserId(sessionUser.getId());
		if (sysUser.getUnitId() == null || "".equals(sysUser.getUnitId())) {
			sysUser.setUnitId(oldUser.getUnitId());
		}

		// 执行业务逻辑
		sysUser = userService.addOrUpdateSysUser(sysUser);

		List<SysUserRole> sysUserRoleList = this.userService.findSysUserRoleByUserId(sysUser.getId());
		for (SysUserRole vo : sysUserRoleList) {
			userService.delSysUserRole(vo.getId());
		}

		for (String role : roles) {
			SysUserRole sysUserRole = new SysUserRole();
			sysUserRole.setRoleId(Long.valueOf(role));
			sysUserRole.setUserId(sysUser.getId());
			userService.addOrUpdateSysUserRole(sysUserRole);
		}

		List<SysUserRegion> sysUserRegonList = this.userService.findSysUserRegonByUserId(sysUser.getId());
		for (SysUserRegion vo : sysUserRegonList) {
			userService.delSysUserRegon(vo.getId());
		}

		if (StringUtils.isNotBlank(regon)) {
			String[] regions = regon.split(",");
			for (String reg : regions) {

				SysUserRegion sysUserRegon = new SysUserRegion();
				sysUserRegon.setRegionCode(reg);
				sysUserRegon.setUserId(sysUser.getId());
				logUtils.logModify("修改角色","修改成功！角色编号："+sysUser.getId());
				userService.addOrUpdateSysUserRegon(sysUserRegon);
			}
		}
		return JsonRespWrapper.success("修改成功", "/sys/user/list");
	}

	@RequestMapping(value = "userDel")
	@ResponseBody
	public Object userDel(@RequestParam(value = "id") Long id) {
		SysUser user = this.userService.findSysUserById(id);
		user.setStatus(3);
		logUtils.logDelete("删除角色","删除成功！角色编号："+id);
		this.userService.addOrUpdateSysUser(user);
		return JsonRespWrapper.success("删除成功！", "/sys/user/list");
	}

	@RequestMapping(value = "lockList")
	public String lockList(SysUser user, @RequestParam(value = "lockStatus", required = false, defaultValue = "-1") int lockStatus,
	        @RequestParam(value = "page", required = false, defaultValue = "1") int page, HttpSession session, Model model) throws SQLException {
		user.setLockStatus(lockStatus);
		Page<SysUser> lockUserlist = userService.listLockUser(user, page);
		model.addAttribute("pageData", lockUserlist);
		return "sys/user/user-lock-list";
	}

	@RequestMapping(value = "userUnlock")
	@ResponseBody
	public Object userUnlock(@RequestParam(value = "id") Long id) throws SQLException {
		SysUser user = this.userService.findSysUserById(id);
		user.setLockStatus(0);
		user.setChangePwdTime(TimeStamp.getTime(TimeStamp.YYYYMMDDhhmmss));
		userService.addOrUpdateSysUser(user);
//		try {
			this.userService.delLoginHistory(user.getId());
			logUtils.logDelete("删除角色","删除成功！角色编号：" +id);
//		}
//		catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return JsonRespWrapper.success("解锁成功！", "/sys/user/lockList");
	}

	@RequestMapping(value = "changePwd", method = RequestMethod.GET)
	public String changePwd(Model model) {

		return "/sys/user/pwd-change";
	}

	@RequestMapping(value = "changePwd", method = RequestMethod.POST)
	@ResponseBody
	public Object changePwdAct(HttpServletRequest request, String orgPwd, String dstPwd, String confirmPwd) {
		if (StringUtils.isEmpty(orgPwd) || StringUtils.isEmpty(dstPwd) || StringUtils.isEmpty(confirmPwd)) {
			return JsonRespWrapper.successAlert("修改密码不能为空");
		}
		// 两次密码不一致
		if (!dstPwd.equals(confirmPwd)) {
			return JsonRespWrapper.successAlert("两次密码不一致");
		}
		SessionUser sessionUser = (SessionUser) request.getSession().getAttribute(SessionUser.SESSION_USER_KEY);
		if (sessionUser == null) {
			return JsonRespWrapper.successJump("/logout");
		}

		SysUser user = userService.findSysUserById(sessionUser.getId());
		logUtils.logModify("修改密码！", "修改成功！编号："+user.getId());
		if (!user.getUserPwd().equals(MD5.digest2Str(orgPwd))) {// 老密码输入是否正确
			return JsonRespWrapper.successAlert("原密码不正确");
		}
		if (user.getUserPwd().equals(MD5.digest2Str(dstPwd))) {// 新密码和老密码相同
			return JsonRespWrapper.successAlert("新密码和原密码相同");
		}
		user.setUserPwd(MD5.digest2Str(dstPwd));
		userService.addOrUpdateSysUser(user);

		return JsonRespWrapper.success("修改成功", "/menu/welcome");
	}
}
