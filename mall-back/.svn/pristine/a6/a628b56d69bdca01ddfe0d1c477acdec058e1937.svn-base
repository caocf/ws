package com.cplatform.mall.back.smsbuy.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.multipart.MultipartFile;

import com.cplatform.dbhelp.page.Page;
import com.cplatform.mall.back.batch.entity.BatchTask;
import com.cplatform.mall.back.batch.service.BatchTaskService;
import com.cplatform.mall.back.batch.web.validator.BatchTaskValidator;
import com.cplatform.mall.back.item.web.ItemManageController;
import com.cplatform.mall.back.model.SessionUser;
import com.cplatform.mall.back.smsbuy.entity.SmsbuyItemRouter;
import com.cplatform.mall.back.smsbuy.service.SmsBuyActOnlineService;
import com.cplatform.mall.back.sys.dao.SysFilterWordDao;
import com.cplatform.mall.back.utils.JsonRespWrapper;
import com.cplatform.mall.back.utils.LogUtils;
import com.cplatform.util2.TimeStamp;

/**
 * 短信购群发任务控制器. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-6-15 下午5:21:29
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: luyd@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
@Controller
@RequestMapping(value = "/smsbuy/act")
public class SmsMassTtaskController {

	@Autowired
	private BatchTaskService batchTaskService;

	@Autowired
	private SmsBuyActOnlineService smsBuyActOnlineService;

	@Autowired
	SysFilterWordDao filterDao;

	private static final Logger log = Logger.getLogger(ItemManageController.class);

	@Autowired
	private BatchTaskValidator taskValidator;
	
	@Autowired
	private LogUtils logUtils;

	/**
	 * 商品管理
	 * 
	 * @param smsbuyItemRouter
	 * @param page
	 * @param model
	 * @return
	 * @throws SQLException
	 */

	@RequestMapping(value = "groupList")
	public String routerList(SmsbuyItemRouter smsbuyItemRouter, @RequestParam(value = "page", defaultValue = "1") Integer page, Model model)
	        throws SQLException {
		// 只查询审核通过的商品指令信息
		Page<SmsbuyItemRouter> pageList = smsBuyActOnlineService.itemRouterList(smsbuyItemRouter, page, Page.DEFAULT_PAGESIZE, "online");
		model.addAttribute("pageData", pageList);
		model.addAttribute("statusMap", smsbuyItemRouter.itemStatusMap);
		return "/smsbuy/act/group-list";
	}

	/**
	 * 进入短信群发任务增加页面
	 * 
	 * @param model
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String toAdd(Long actId, Long id, Integer page, Model model) throws IOException, Exception {

		smsBuyActOnlineService.preAddAndUpdateItemRouter(actId, id, page, model);
		BatchTask task = new BatchTask();
		task.setStartTime(TimeStamp.getTime(14));
		model.addAttribute("task", task);
		// 跳转到列表页面
		return "/smsbuy/act/sms_batch_task_add";
	}

	/**
	 * 短信群发任务增加操作
	 * 
	 * @param uploadfile
	 * @param uploadblackfile
	 * @param uploadwhitefile
	 * @param task
	 * @param model
	 * @param request
	 * @param result
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Object add(MultipartFile uploadfile, MultipartFile uploadblackfile, MultipartFile uploadwhitefile, @ModelAttribute("task") BatchTask task,
	        Model model, HttpServletRequest request, BindingResult result) throws IOException, Exception {
		try {
			// 短信内容过滤
			if (filterDao.countFilterwordNum(task.getTitle()) > 0) {
				return JsonRespWrapper.successAlert("短信内容包含敏感字！");
			}
			SessionUser sessionUser = (SessionUser) request.getSession().getAttribute(SessionUser.SESSION_USER_KEY);
			String unitId = sessionUser.getUnitId().toString();
			task.setCreatorId(sessionUser.getId());
			task.setUnitId(unitId);
			task.setIsNewTerminalId("1");
			taskValidator.validate(task, result);
			if (result.hasErrors()) {
				return JsonRespWrapper.error(result.getFieldErrors());
			}
			batchTaskService.saveTask(task, uploadfile, uploadblackfile, uploadwhitefile, 1l);
			// 跳转到列表页面
			logUtils.logAdd("短信购群发管理", "新增群发管理，任务编号："+task.getId());
			return JsonRespWrapper.success("操作成功", "/batch/smsbatchlist");
		}
		catch (Exception ex) {
			// 如何上传群发号码文件内容为空，会抛出异常，
			// 新增数据信息保存到数据库信息不完整(没有号码附件)，要删除该条数据
			// batchTaskDao.delete(task.getId());
			// 未知的错误消息，一般是exception catch后通知用户
			log.error(ex.getMessage());
			logUtils.logAdd("短信购群发管理", "新增群发管理出错，任务编号："+task.getId()+ex.getMessage());
			return JsonRespWrapper.error(ex.getMessage());
		}
	}

}