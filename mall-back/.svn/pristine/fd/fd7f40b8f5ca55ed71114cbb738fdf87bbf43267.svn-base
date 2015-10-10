package com.cplatform.mall.back.lottery.web;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cplatform.dbhelp.page.Page;
import com.cplatform.mall.back.lottery.entity.Logger;
import com.cplatform.mall.back.lottery.service.LoggerService;

@Controller
@RequestMapping(value = "/lottery/log")
public class LoggerController {
	@Autowired
	private LoggerService loggerService;
	/**
	 * 列表
	 * */
	@RequestMapping(value = "/list")
	public String list(Logger logger, @RequestParam(value = "page", required = false, defaultValue = "1") int page,  Model model) throws SQLException {
		Page<Logger> pageData = loggerService.findLoggerList(logger, page, Page.DEFAULT_PAGESIZE);
		model.addAttribute("pageData", pageData);
		return "/lottery/log/log-list";
	}
	/**
	 * 导出
	 * */
	@RequestMapping(value = "/download")
	public void download(HttpServletRequest request,HttpServletResponse response) throws Exception{
		loggerService.download(request, response);
	}
}
