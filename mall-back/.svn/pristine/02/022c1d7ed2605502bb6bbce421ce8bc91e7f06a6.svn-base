package com.cplatform.mall.back.smsbuy.web;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cplatform.dbhelp.page.Page;
import com.cplatform.mall.back.smsbuy.entity.SmsbuyHelp;
import com.cplatform.mall.back.smsbuy.service.SmsbuyHelpService;
import com.cplatform.mall.back.smsbuy.web.validator.SmsbuyHelpValidator;
import com.cplatform.mall.back.utils.JsonRespWrapper;
import com.cplatform.mall.back.utils.LogUtils;

/**
 * 短信购帮助信息Controller. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-6-17 上午11:22:48
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: wuyn@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
@Controller
@RequestMapping(value = "/smsbuy/help")
public class SmsbuyHelpController {

	@Autowired
	private SmsbuyHelpService smsbuyHelpService;

	@Autowired
	private SmsbuyHelpValidator smsbuyHelpValidator;
	
	@Autowired
	private LogUtils logUtils;

	/**
	 * 列表
	 * 
	 * @param page
	 * @param smsbuyHelp
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "helpList")
	public String list(@RequestParam(value = "page", defaultValue = "1", required = false) Integer page, SmsbuyHelp smsbuyHelp, Model model)
	        throws SQLException {

		Page<SmsbuyHelp> pageList = smsbuyHelpService.list(smsbuyHelp, page, Page.DEFAULT_PAGESIZE);
		model.addAttribute("pageData", pageList);
		model.addAttribute("recommendTypeMap", SmsbuyHelp.recommendTypeMap);
		return "/smsbuy/help/help-list";
	}

	/**
	 * 预添加
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "preAdd")
	public String preAdd(Model model) {

		smsbuyHelpService.preAdd(model);

		return "/smsbuy/help/help-add";
	}

	/**
	 * 预修改
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "preEdit")
	public String preEdit(@RequestParam(value = "helpSpcode") String helpSpcode, @RequestParam(value = "helpArea") String helpArea, Model model) {

		smsbuyHelpService.preEdit(helpSpcode, helpArea, model);

		return "/smsbuy/help/help-add";
	}

	/**
	 * 添加
	 * 
	 * @param smsbuyHelp
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "add")
	@ResponseBody
	public Object add(SmsbuyHelp smsbuyHelp, BindingResult result, HttpServletRequest request) {

		smsbuyHelpValidator.validate(smsbuyHelp, result);
		if (result.hasErrors()) {
			return JsonRespWrapper.error(result.getFieldErrors());
		}
		if(smsbuyHelp.getHelpArea()== null || "".equals(smsbuyHelp.getHelpArea())){
			
			smsbuyHelp.setHelpArea("0");
		}
		smsbuyHelpService.add(smsbuyHelp);
		logUtils.logAdd("短信购活动帮助管理", "添加活动帮助");
		 return JsonRespWrapper.success("操作成功！", "/smsbuy/help/helpList");
		//String backUrl = request.getParameter("backUrl");
	//	return JsonRespWrapper.success("操作成功！", backUrl);
	}

	/**
	 * 查看
	 * 
	 * @param smsbuyHelp
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "view")
	public String view(@RequestParam(value = "helpSpcode") String helpSpcode, @RequestParam(value = "helpArea") String helpArea, Model model) {

		smsbuyHelpService.view(helpSpcode, helpArea, model);

		return "/smsbuy/help/help-view";
	}

	/**
	 * 删除
	 * 
	 * @param helpSpcode
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "delete")
	@ResponseBody
	public Object deleteHelp(@RequestParam(value = "helpSpcode") String helpSpcode, @RequestParam(value = "helpArea") String helpArea, Model model) {

		smsbuyHelpService.delete(helpSpcode, helpArea);
		// return JsonRespWrapper.success("删除成功！", "/smsbuy/help/helpList");
		logUtils.logAdd("短信购活动帮助管理", "删除活动帮助");
		return JsonRespWrapper.successReload("删除成功！");
	}
}
