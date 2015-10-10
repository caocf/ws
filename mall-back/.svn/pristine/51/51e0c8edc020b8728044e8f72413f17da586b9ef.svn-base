package com.cplatform.mall.back.cont.web;

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
import com.cplatform.mall.back.cont.dao.ContentCodeDao;
import com.cplatform.mall.back.cont.entity.ContentCode;
import com.cplatform.mall.back.cont.service.ContentCodeService;
import com.cplatform.mall.back.cont.web.validator.ContCodeValidator;
import com.cplatform.mall.back.model.SessionUser;
import com.cplatform.mall.back.utils.JsonRespWrapper;
import com.cplatform.mall.back.utils.LogUtils;

@Controller
@RequestMapping(value = "/cont/contentCode")
public class ContentCodeController {

	@Autowired
	private ContentCodeService contentCodeService;

	@Autowired
	private ContentCodeDao contentCodeDao;

	@Autowired
	private ContCodeValidator contCodeValidator;

	@Autowired
	private LogUtils logUtils;

	private final static String MODULE_NAME = "内容源管理";

	/**
	 * 列表显示内容源信息
	 * 
	 * @param contentCode
	 * @param page
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/list")
	public String listContentCode(ContentCode contentCode, @RequestParam(value = "page", required = false, defaultValue = "1") int page, Model model)
	        throws SQLException {
		Page<ContentCode> contentCodeList = contentCodeService.listContentCode(contentCode, page, Page.DEFAULT_PAGESIZE);
		model.addAttribute("pageData", contentCodeList);
		return "/cont/contentCode/contentCode-list";
	}

	@RequestMapping(value = "/preUpdate")
	public String preUpdateConetCode(@RequestParam(value = "id", required = true) Long id, Model model) {
		ContentCode contentCode = contentCodeDao.findOne(id);

		model.addAttribute("contentCode", contentCode);
		model.addAttribute("codeTypeMap", ContentCode.getCodeTypeMap());
		model.addAttribute("contTypeMap", ContentCode.getContTypeMap());

		return "/cont/contentCode/contentCode-update";
	}

	@RequestMapping(value = "/update")
	@ResponseBody
	public Object updateConetCode(ContentCode contentCode, Model model, HttpServletRequest request) {
		
		ContentCode oldcode = this.contentCodeDao.findOne(contentCode.getId());
		contentCode.setAreaCode(oldcode.getAreaCode());
		contentCodeDao.save(contentCode);
		logUtils.logModify(MODULE_NAME, "修改id:"+contentCode.getId());
		// return JsonRespWrapper.success("修改成功", "/cont/contentCode/list");
		// 返回来源地址
		String backUrl = request.getParameter("backUrl");
		return JsonRespWrapper.success("修改成功", backUrl);
	}

	@RequestMapping(value = "/preAdd")
	public String repAddContentCode(Model model) {
		ContentCode contentCode = new ContentCode();

		model.addAttribute("contentCode", contentCode);
		model.addAttribute("contTypeMap", ContentCode.getContTypeMap());
		model.addAttribute("codeTypeMap", ContentCode.getCodeTypeMap());
		return "/cont/contentCode/contentCode-add";
	}

	@RequestMapping(value = "/add")
	@ResponseBody
	public Object addContentCode(ContentCode contentCode, BindingResult result) {

		contCodeValidator.validate(contentCode, result);
		if (result.hasErrors()) {
			return JsonRespWrapper.error(result.getFieldErrors());
		}
		contentCode.setAreaCode(SessionUser.getSessionUser().getSysUnit().getAreaCode());
		contentCodeDao.save(contentCode);
		logUtils.logAdd(MODULE_NAME, "添加,id:"+contentCode.getId());
		return JsonRespWrapper.success("添加成功", "/cont/contentCode/list");
	}

	@RequestMapping(value = "/delete")
	@ResponseBody
	public Object deleteContentCode(@RequestParam Long id, Model model) {
		contentCodeDao.delete(id);
		logUtils.logDelete(MODULE_NAME, "删除,id:"+id);
		return JsonRespWrapper.successReload("删除成功");
	}

}
