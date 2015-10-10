package com.cplatform.mall.back.smsschedule.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.cplatform.dbhelp.page.Page;

import com.cplatform.mall.back.batch.entity.BatchTask;
import com.cplatform.mall.back.cont.mms.util.TimeUtil;
import com.cplatform.mall.back.model.SessionUser;
import com.cplatform.mall.back.smsschedule.dao.BatchTaskScheduleDao;
import com.cplatform.mall.back.smsschedule.entity.BatchTaskSchedule;
import com.cplatform.mall.back.smsschedule.service.BatchTaskScheduleService;
import com.cplatform.mall.back.utils.AppConfig;
import com.cplatform.mall.back.utils.JsonRespWrapper;
import com.cplatform.mall.back.utils.LogUtils;
import com.google.common.collect.Maps;

/**
 * 短信排期Controller. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-8-21 上午15:22:18
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: jisn@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
@Controller
@RequestMapping(value = "/sms/schedule")
public class BatchScheduleController {

	@Autowired
	private BatchTaskScheduleService batchTaskScheduleService;
	
	@Autowired
	private BatchTaskScheduleDao batchTaskScheduleDao;
	
	@Autowired
	AppConfig appConfig;
	
	@Autowired
	private LogUtils logUtils;

	//@Autowired
	// private SmsbuyHelpValidator smsbuyHelpValidator;
	/**
	 * 列表
	 */
	@RequestMapping(value = "list")
	public String list(
			String userCode,
			Integer status,
			String startTime,
			String endTime,
			String pStartTime,
			String pEndTime,
			@RequestParam(value = "page", defaultValue = "1", required = false) Integer page,
			Model model,HttpServletRequest request) throws SQLException {
		Page<BatchTaskSchedule> pageList = batchTaskScheduleService.list(userCode,
				status, startTime, endTime, pStartTime, pEndTime, page, Page.DEFAULT_PAGESIZE);
		model.addAttribute("pageData", pageList);
		model.addAttribute("statusMap", BatchTaskSchedule.statusMap);
		
		SessionUser sessionUser = (SessionUser) request.getSession()
		.getAttribute(SessionUser.SESSION_USER_KEY);
		
		String userName = sessionUser.getSysUser().getUserCode();
		model.addAttribute("loginName", userName);

		return "/sms/schedule/list";
	}

	/**
	 * 预添加
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "preAdd")
	public String preAdd(Model model) {
		BatchTaskSchedule batchTaskSchedule = new BatchTaskSchedule();
		model.addAttribute("batchTaskSchedule", batchTaskSchedule);
		return "/sms/schedule/add";
	}

	/**
	 * 添加
	 * 
	 * @param batchTaskSchedule
	 * @param result
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "add")
	@ResponseBody
	public Object add(MultipartFile uploadfile,
			BatchTaskSchedule batchTaskSchedule, BindingResult result,
			HttpServletRequest request) {
		// smsbuyHelpValidator.validate(smsbuyHelp, result);
		if (result.hasErrors()) {
			return JsonRespWrapper.error(result.getFieldErrors());
		}

		SessionUser sessionUser = (SessionUser) request.getSession()
				.getAttribute(SessionUser.SESSION_USER_KEY);
		
		try {
			String isZip = request.getParameter("isZip");
			batchTaskScheduleService.add(batchTaskSchedule, sessionUser.getSysUser()
					.getUserCode(), uploadfile,isZip);
		} catch (Exception ex) {
			ex.printStackTrace();
			return JsonRespWrapper.error(ex.getMessage());
		}
		logUtils.logAdd("群发任务排期", "增加操作，排期id："+batchTaskSchedule.getId());
		return JsonRespWrapper.success("操作成功！", "/sms/schedule/list");
	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "delete")
	@ResponseBody
	public Object delete(@RequestParam(value = "id") Long id, Model model) {
		batchTaskScheduleService.delete(id);
		logUtils.logDelete("群发任务排期", "删除操作，排期id："+id);
		return JsonRespWrapper.successReload("删除成功！");
	}
	
	/**
	 * 修改状态
	 * @param id
	 * @param status
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "updateStatus")
	@ResponseBody
	public Object updateStatus(@RequestParam(value = "id") Long id,@RequestParam(value = "status") Integer status, Model model) {
		batchTaskScheduleService.updateStatus(id,status);
		logUtils.logModify("群发任务排期", "取消操作，排期id："+id);
		return JsonRespWrapper.successReload("取消成功！");
	}
	
	/**
	 * 下载号码文件
	 * 
	 * @param savename
	 * @param id
	 * @param flag
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	@RequestMapping(value = "downfile/{id}")
	public ModelAndView downFile(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response)
	        throws IOException, Exception {
		String contentType = "application/octet-stream";
		if(id==0) {
			String templatepath = String.format("%supload/template.txt", appConfig
					.getUploadMmsScheduleDir());
			batchTaskScheduleService.download(request, response, templatepath, "template.txt");
		}else if(id==-1) {
			String templatepath = String.format("%supload/template1.txt", appConfig
					.getUploadMmsScheduleDir());
			batchTaskScheduleService.download(request, response, templatepath, "template.txt");
		}else {
			BatchTaskSchedule batchTaskSchedule = batchTaskScheduleDao.findOne(id);
			String resultFilePath =  batchTaskSchedule.getResultFilePath();
			batchTaskScheduleService.download(request, response, resultFilePath, resultFilePath.substring(resultFilePath.lastIndexOf("/")+1));
		}
		
		return null;
	}	
}
