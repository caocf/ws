package com.cplatform.mall.back.sys.web;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cplatform.dbhelp.page.Page;
import com.cplatform.mall.back.sys.entity.SysLogInfo;
import com.cplatform.mall.back.sys.entity.SysLoginInfo;
import com.cplatform.mall.back.sys.service.SysInfoService;

@Controller
@RequestMapping("/sys/log")
public class SysLogController {

	@Autowired
	SysInfoService sysInfoService;

	@RequestMapping("list")
	public String sysLogList(SysLogInfo logInfo, @RequestParam(required = false, value = "page", defaultValue = "1") int page, Model model)
	        throws SQLException {
		Page<SysLogInfo> logPage = sysInfoService.findSysLog(logInfo, page, Page.DEFAULT_PAGESIZE);
		model.addAttribute("logInfo", logInfo);
		model.addAttribute("pageData", logPage);
		model.addAttribute("operTypeMap", SysLogInfo.getOperTypeMap());
		return "sys/log/sys-log-list";
	}

	@RequestMapping("login-list")
	public String sysLoginLogList(SysLoginInfo loginInfo, @RequestParam(required = false, value = "page", defaultValue = "1") int page, Model model)
	        throws SQLException {
		Page<SysLoginInfo> logPage = sysInfoService.findSysLoginLog(loginInfo, page, Page.DEFAULT_PAGESIZE);
		model.addAttribute("loginInfo", loginInfo);
		model.addAttribute("pageData", logPage);
		model.addAttribute("flagMap", SysLoginInfo.getFlagMap());
		return "sys/log/sys-login-list";
	}

}
