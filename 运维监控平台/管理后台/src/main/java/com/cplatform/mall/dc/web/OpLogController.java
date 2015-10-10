package com.cplatform.mall.dc.web;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cplatform.mall.dc.service.DcOpLogService;
import com.cplatform.mall.dc.utils.LogUtils;

/**
 * 操作日志控制器
 * 
 * @author zhuangxx@c-platform.com
 * @since 2013-7-12
 */
@Controller
@RequestMapping("/sysMgmt/opLog")
public class OpLogController {
	@Autowired
	LogUtils logUtil;

	@Autowired
	DcOpLogService opLogService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String toList(
			String startTime,
			String endTime,
			Model model,
			@RequestParam(value = "page", defaultValue = "0", required = false) int page,
			@RequestParam(value = "pageSize", defaultValue = "20", required = false) int pageSize)
			throws SQLException {
		// logUtil.recordOpLog("系统管理", "操作日志", LogUtils.OP_TYPE_QUERY);
		model.addAttribute("datas",
				opLogService.findAll(startTime, endTime, page, pageSize));
		return "/sysMgmt/opLog/list";
	}
}
