package com.cplatform.mall.back.locallife.web;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cplatform.dbhelp.page.Page;
import com.cplatform.mall.back.cont.mms.util.TimeUtil;
import com.cplatform.mall.back.locallife.dao.ModuleDataTempletDao;
import com.cplatform.mall.back.locallife.entity.ModuleDataTemplet;
import com.cplatform.mall.back.locallife.entity.PageModule;
import com.cplatform.mall.back.locallife.service.LocalLifeModuleService;
import com.cplatform.mall.back.locallife.service.LocalLifeTempletService;
import com.cplatform.mall.back.model.SessionUser;
import com.cplatform.mall.back.utils.AppConfig;
import com.cplatform.mall.back.utils.JsonRespWrapper;
import com.cplatform.mall.back.utils.LogUtils;

/**
 * Title. <br>
 * 模版管理控制类 Description.
 * <p/>
 * Copyright: Copyright (c) 13-6-9
 * <p/>
 * Company: 北京宽连十方数字技术有限公司
 * <p/>
 * Author: mudeng@c-platform.com
 * <p/>
 * Version: 1.0
 * <p/>
 */

@Controller
@RequestMapping("/locallife/template/")
public class LocalLifeTempletController {

	@Autowired
	private LocalLifeTempletService service;

	@Autowired
	private LocalLifeModuleService moduleService;

	@Autowired
	AppConfig appConfig;
	
	@Autowired
	private  LogUtils logUtils;
	
	@Autowired
	ModuleDataTempletDao dao;

	/**
	 * 模板列表
	 * @param info
	 * @param page
	 * @param session
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "list")
	public String templateList(@ModelAttribute("sysTemplateInfo") ModuleDataTemplet moduleDataTemplet,
	        @RequestParam(value = "page", required = false, defaultValue = "1") int page, HttpSession session, Model model){
		try {
			Page<ModuleDataTemplet> moduleDataTempletPage = service.getModuleDataTempletPage(moduleDataTemplet, page, Page.DEFAULT_PAGESIZE);
			model.addAttribute("pageData", moduleDataTempletPage);
		} catch (Exception e) {
			
		}
		return "locallife/template/list";
	}

	/**
	 * 预添加
	 * 
	 * @param session
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String templateAdd(HttpSession session, Model model) {
		ModuleDataTemplet info = new ModuleDataTemplet();
	try {
		model.addAttribute("info", info);
		model.addAttribute("method", "add");
	} catch (Exception e) {
		
	}
		return "locallife/template/add";
	}

	/**
	 * 添加
	 * 
	 * @param uploadFile
	 * @param sysTempLateInfo
	 * @param request
	 * @param session
	 * @param result
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public Object templateSave(MultipartFile uploadFile, @ModelAttribute("info") ModuleDataTemplet moduleDataTemplet, HttpServletRequest request,
	        HttpSession session) {
	try {
		SessionUser sessionUser = (SessionUser) request.getSession().getAttribute(SessionUser.SESSION_USER_KEY);
			if (uploadFile == null || uploadFile.isEmpty()) {
				// 提示用户必须要上传文件
				return JsonRespWrapper.successAlert("未选择模板文件或者模板文件为空，请重新选择！");// 弹出提示
			}
			moduleDataTemplet.setCreateTime(TimeUtil.now());
			moduleDataTemplet.setCreateUser(sessionUser.getId());
			service.saveTemplate(moduleDataTemplet, uploadFile);
			logUtils.logAdd("本地生活管理", "添加模板，模板ID"+moduleDataTemplet.getId());
	} catch (Exception e) {
		logUtils.logAdd("本地生活管理", "添加模板失败，模板ID"+moduleDataTemplet.getId()+e.getMessage());
	}
		String backUrl = request.getParameter("backUrl");
		//"/locallife/template/list"
		return JsonRespWrapper.success("添加成功",backUrl);
	}

	/**
	 * 预编辑
	 * 
	 * @param id
	 * @param session
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public String templateEdit(@RequestParam(value = "id") Long id, HttpSession session, Model model){
		try {
			ModuleDataTemplet moduleDataTemplet = this.service.findById(id);
			model.addAttribute("info", moduleDataTemplet);
			model.addAttribute("method", "edit");
		} catch (Exception e) {
		}
		return "locallife/template/add";
	}

	/**
	 * 编辑
	 * 
	 * @param uploadFile
	 * @param sysTempLateInfo
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	@ResponseBody
	public Object templateUpdate(MultipartFile uploadFile, @ModelAttribute("info") ModuleDataTemplet moduleDataTemplet, HttpServletRequest request)
	        {
	try {
		SessionUser sessionUser = (SessionUser) request.getSession().getAttribute(SessionUser.SESSION_USER_KEY);
		ModuleDataTemplet moduleDataTemplettemp = service.findById(moduleDataTemplet.getId());
//			if (null == uploadFile) {
//				// 提示用户必须要上传文件
//				return JsonRespWrapper.successAlert("未选择模板文件或者模板文件为空，请重新选择！");// 弹出提示
//			}
			moduleDataTemplet.setCreateTime(moduleDataTemplettemp.getCreateTime());
			moduleDataTemplet.setCreateUser(moduleDataTemplettemp.getCreateUser());
			moduleDataTemplet.setUpdateTime(TimeUtil.now());
			moduleDataTemplet.setUpdateUser(sessionUser.getId());
		service.saveTemplate(moduleDataTemplet, uploadFile);
		logUtils.logModify("修改成功", "");
	} catch (Exception e) {
		logUtils.logModify("修改失败", e.getMessage());
	}
		String backUrl = request.getParameter("backUrl");	
		//"/locallife/template/list"
		return JsonRespWrapper.success("修改成功", backUrl);
	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "del")
	@ResponseBody
	public Object templateDel(@RequestParam(value = "id") Long id) {
		try {
			List<PageModule> listModulePage = moduleService.getPageModuleListByTempId(id);
			if(listModulePage.size() > 0){
				return JsonRespWrapper.successAlert("模板文件已经被使用，模板不能被删除！");// 弹出提示
			}
			dao.delete(id);
			logUtils.logDelete("删除模块", "操作成功，模块编号"+id);
		} catch (Exception e) {
			logUtils.logDelete("删除模块", "操作失败，模块编号"+id);
		}
		return JsonRespWrapper.success("删除成功！", "/locallife/template/list");
	}
}
