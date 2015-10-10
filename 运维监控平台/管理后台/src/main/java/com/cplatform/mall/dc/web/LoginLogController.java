package com.cplatform.mall.dc.web;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cplatform.dbhelp.page.Page;
import com.cplatform.mall.dc.entity.DcLoginLog;
import com.cplatform.mall.dc.service.DcLoginLogService;
import com.cplatform.mall.dc.utils.LogUtils;

/**
 * 登录日志控制器
 * 
 * @author zhangdong
 * @since 2013-7-10
 */
@Controller
@RequestMapping("/sysMgmt")
public class LoginLogController {
	@Autowired
	LogUtils logUtil;

	@Autowired
	DcLoginLogService loginLogService;

	/**
	 * 跳转到登录日志页面
	 * 
	 * @param content
	 * @param startTime
	 * @param endTime
	 * @param page
	 * @param model
	 * @return
	 * @throws SQLException
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/loginLog", method = RequestMethod.GET)
	public String toLoginLog(
			String content,
			String startTime,
			String endTime,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			Model model) throws SQLException, UnsupportedEncodingException {
		logUtil.recordOpLog("系统管理", "登录日志", LogUtils.OP_TYPE_QUERY);
		if(StringUtils.isNotBlank(content)){
			content = URLDecoder.decode(content.trim(), "utf-8");
		}
		if(StringUtils.isNotBlank(startTime)){
			startTime = URLDecoder.decode(startTime.trim(), "utf-8");
		}
		if(StringUtils.isNotBlank(endTime)){
			endTime = URLDecoder.decode(endTime.trim(), "utf-8"); 
		}
		Page<DcLoginLog> loginLogPage = loginLogService.findAllLoginLogsPage(
				content, startTime, endTime, page);
		model.addAttribute("loginLogPage", loginLogPage);
		model.addAttribute("content", content);
		model.addAttribute("startTime", startTime);
		model.addAttribute("endTime", endTime);
		return "sysMgmt/loginLog/list";
	}

}
