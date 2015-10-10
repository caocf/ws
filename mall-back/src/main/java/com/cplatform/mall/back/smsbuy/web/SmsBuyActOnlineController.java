package com.cplatform.mall.back.smsbuy.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.servlet.ModelAndView;

import com.cplatform.dbhelp.page.Page;
import com.cplatform.mall.back.batch.entity.BatchTask;
import com.cplatform.mall.back.batch.service.BatchTaskService;
import com.cplatform.mall.back.batch.web.validator.BatchTaskValidator;
import com.cplatform.mall.back.model.SessionUser;
import com.cplatform.mall.back.smsbuy.entity.SmsBuyActOnline;
import com.cplatform.mall.back.smsbuy.entity.SmsbuyItemRouter;
import com.cplatform.mall.back.smsbuy.service.SmsBuyActOnlineService;
import com.cplatform.mall.back.smsbuy.web.validator.SmsbuyRouterValidator;
import com.cplatform.mall.back.sys.dao.SysFilterWordDao;
import com.cplatform.mall.back.utils.AppConfig;
import com.cplatform.mall.back.utils.JsonRespWrapper;
import com.cplatform.mall.back.utils.LogUtils;
import com.cplatform.mall.back.websys.service.BsFileService;
import com.cplatform.util2.FileTools;
import com.cplatform.util2.TimeStamp;

/**
 * 短信购活动配置控制器. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-6-15 下午5:21:29
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: wuyn@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
@Controller
@RequestMapping(value = "/smsbuy/act")
public class SmsBuyActOnlineController {

	@Autowired
	private SmsBuyActOnlineService smsBuyActOnlineService;

	@Autowired
	private SmsbuyRouterValidator smsbuyRouterValidator;

	@Autowired
	private BsFileService bsFileService;

	@Autowired
	private AppConfig appConfig;

	@Autowired
	private BatchTaskService batchTaskService;

	@Autowired
	SysFilterWordDao filterDao;

	@Autowired
	private BatchTaskValidator taskValidator;

	@Autowired
	private LogUtils logUtils;

	private static final Logger log = Logger.getLogger(SmsBuyActOnlineController.class);

	/**
	 * 活动配置列表
	 * 
	 * @param smsBuyActOnline
	 * @param page
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "list")
	public String list(SmsBuyActOnline smsBuyActOnline, @RequestParam(value = "page", defaultValue = "1") Integer page, Model model)
	        throws SQLException {
		Page<SmsBuyActOnline> pageList = smsBuyActOnlineService.listSmsBuyAct(smsBuyActOnline, page, Page.DEFAULT_PAGESIZE);

		model.addAttribute("pageData", pageList);
		model.addAttribute("statusMap", SmsBuyActOnline.statusMap);
		return "/smsbuy/act/act-list";
	}

	/**
	 * 商品审核
	 * 
	 * @param smsBuyActOnline
	 * @param page
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "auditList")
	public String auditList(SmsBuyActOnline smsBuyActOnline, @RequestParam(value = "page", defaultValue = "1") Integer page, Model model)
	        throws SQLException {

		Page<SmsBuyActOnline> pageList = smsBuyActOnlineService.listSmsBuyAct(smsBuyActOnline, page, Page.DEFAULT_PAGESIZE);

		model.addAttribute("pageData", pageList);
		model.addAttribute("statusMap", SmsBuyActOnline.statusMap);
		return "/smsbuy/act/act-audit-list";
	}

	/**
	 * 商品指令列表
	 * 
	 * @param smsbuyItemRouter
	 * @param page
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "routerList")
	public String routerList(SmsbuyItemRouter smsbuyItemRouter, @RequestParam(value = "page", defaultValue = "1") Integer page, Model model)
	        throws SQLException {

		Page<SmsbuyItemRouter> pageList = smsBuyActOnlineService.routerList(smsbuyItemRouter, page, Page.DEFAULT_PAGESIZE);

		model.addAttribute("pageData", pageList);

		return "/smsbuy/router/router-list";
	}

	/**
	 * 短信购活动添加预处理
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "preAdd")
	public String preAdd(@RequestParam(value = "actId", required = false) Long actId, Model model) {

		return smsBuyActOnlineService.preAdd(actId, model);
	}

	/**
	 * 短信购活动添加
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "addAct")
	@ResponseBody
	public Object addAct(SmsBuyActOnline smsBuyActOnline, Model model, MultipartFile uploaditemfile, HttpServletRequest request) {
		try {
			if (uploaditemfile != null && uploaditemfile.getOriginalFilename() != null && !"".equals(uploaditemfile.getOriginalFilename())) {
				if (uploaditemfile.getOriginalFilename().indexOf(".xlsx") < 0 && uploaditemfile.getOriginalFilename().indexOf(".xls") < 0) {
					return JsonRespWrapper.successReload("上传文件格式不正确！");
				}
			} else {
				uploaditemfile = null;
			}
			String filepath = request.getParameter("filepath");
			String msg = "";
			String url = request.getParameter("backUrl");
			if (smsBuyActOnline.getActId() != null) {
				msg = "修改成功";
				// 返回来源地址

				String rootSpcode = request.getParameter("rootSpcode");
				smsBuyActOnline.setSpCode(rootSpcode + smsBuyActOnline.getSpCode());
				smsBuyActOnlineService.addAct(smsBuyActOnline);
			} else {// 批量上传
				String rootSpcode = request.getParameter("rootSpcode");
				smsBuyActOnline.setSpCode(rootSpcode + smsBuyActOnline.getSpCode());
				msg = smsBuyActOnlineService.addActAndItem(smsBuyActOnline, uploaditemfile, filepath);
				if ("".equals(msg)) {
					msg = "添加成功";
				}
			}
			logUtils.logAdd("短信购活动管理", "添加短信购活动,活动编号" + smsBuyActOnline.getActId());
			return JsonRespWrapper.success(msg, url);
		}
		catch (Exception e) {
			logUtils.logAdd("短信购活动管理", "添加短信购活动出错,活动编号" + smsBuyActOnline.getActId() + e.getMessage());
			return JsonRespWrapper.error(e.getMessage());
		}
	}

	/**
	 * 商品指令列表
	 * 
	 * @param actId
	 * @param session
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "itemRouterList")
	public String itemRouterList(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "actId") Long actId,
	        Model model) throws SQLException {
		smsBuyActOnlineService.itemRouterList(actId, page, model);
		return "/smsbuy/act/item-router-List";
	}

	/**
	 * 新增商品指令
	 * 
	 * @param actId
	 * @param session
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "addOrUpdateItemRouter")
	public String addOrUpdateItemRouter(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "actId") Long actId,
	        @RequestParam(value = "id", required = false) Long id, Model model) throws SQLException {
		smsBuyActOnlineService.preAddAndUpdateItemRouter(actId, id, page, model);
		return "/smsbuy/act/add-item-router";
	}

	/**
	 * @param actId
	 * @param session
	 * @param model
	 * @return
	 * @throws SQLException
	 * @RequestMapping(value = "preAddItemRouter") public String
	 *                       preAddItemRouter(@RequestParam(value = "page",
	 *                       defaultValue = "1") Integer page,
	 * @RequestParam(value = "actId") Long actId,
	 * @RequestParam(value = "id", required = false) Long id, Model model)
	 *                     throws SQLException {
	 *                     smsBuyActOnlineService.preAddItemRouter(actId, id,
	 *                     page, model); return "/smsbuy/act/act-add2"; }
	 */

	/**
	 * @param actId
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "addItemRouter")
	@ResponseBody
	public Object addItemRouter(@RequestParam(value = "actId") Long actId, SmsbuyItemRouter smsbuyItemRouter, BindingResult result,
	        MultipartFile uploaditemfile, HttpServletRequest request) {
		if (uploaditemfile != null && uploaditemfile.getOriginalFilename() != null && !"".equals(uploaditemfile.getOriginalFilename())) {
			if (uploaditemfile.getOriginalFilename().indexOf(".xlsx") < 0 && uploaditemfile.getOriginalFilename().indexOf(".xls") < 0) {
				return JsonRespWrapper.successReload("上传文件格式不正确！");
			}
		} else {
			uploaditemfile = null;
		}
		String filepath = request.getParameter("filepath");
		String msg = "添加成功！";
		String logFlag = "0";
		try {
			// 批量上传
			if (uploaditemfile != null) {
				msg = smsBuyActOnlineService.addBatchItemRouter(uploaditemfile, actId, filepath, smsbuyItemRouter.getSpCode());
				String time = TimeStamp.getTime(TimeStamp.YYYYMMDDhhmmssxxx);
				String dir1 = time.substring(0, 8);
				String dir2 = time.substring(8, 10);
				String dir3 = time.substring(10);
				String savepath = String.format("%s/%s/", dir1, dir2);
				String ext = FileTools.getExtFilename(uploaditemfile.getOriginalFilename());
				String filename = String.format("sms_%s%s.%s", dir2, dir3, ext);
				FileTools.write(uploaditemfile.getBytes(), appConfig.getResRoot() + "smsBuyFile/" + savepath + filename);
			} else {
				smsbuyItemRouter.setActId(actId);
				smsbuyRouterValidator.validate(smsbuyItemRouter, result);
				if (smsbuyItemRouter.getId() != null) {
					msg = "修改成功！";
					logFlag = "1";
				}
				if (result.hasErrors()) {
					return JsonRespWrapper.error(result.getFieldErrors());
				}
				smsbuyItemRouter.setItemPrice(smsbuyItemRouter.getItemPrice() * 100);
				smsBuyActOnlineService.addItemRouter(smsbuyItemRouter);
			}
		}
		catch (Exception e) {
			JsonRespWrapper.error("操作失败：" + e.getMessage());
		}
		if ("0".equals(logFlag)) {
			logUtils.logAdd("短信购活动管理", "新增商品指令,活动编号：" + actId);
		} else {
			logUtils.logModify("短信购活动管理", "修改商品指令,活动编号：" + actId);
		}
		return JsonRespWrapper.success(msg, "/smsbuy/act/itemRouterList?actId=" + actId);
	}

	/**
	 * 编辑活动信息
	 * 
	 * @param actId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "preEditAct")
	public String preEditAct(@RequestParam(value = "actId", required = true) Long actId, Model model) {
		smsBuyActOnlineService.preEditAct(actId, model);
		model.addAttribute("actId", actId);
		smsBuyActOnlineService.preEditRouter(null, model);
		return "/smsbuy/act/act-edit";
	}

	/**
	 * 编辑活动信息
	 * 
	 * @param actId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "preEditRouter")
	public String preEditRouter(@RequestParam(value = "id", required = true) Long id, Model model) {
		smsBuyActOnlineService.preEditRouter(id, model);
		return "/smsbuy/router/router-add";
	}

	/**
	 * 删除活动
	 * 
	 * @param actId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "deleteAct")
	@ResponseBody
	public Object deleteAct(@RequestParam(value = "actId") Long actId, Model model) {
		String msg = smsBuyActOnlineService.deleteAct(actId);
		logUtils.logDelete("短信购活动管理", "删除活动，活动编号:" + actId);
		return JsonRespWrapper.successReload(msg);
	}

	/**
	 * 删除商品指令
	 * 
	 * @param actId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "deleteRouter")
	@ResponseBody
	public Object deleteRouter(@RequestParam(value = "id") Long id, Model model) {

		String url = smsBuyActOnlineService.deleteRouter(id);
		logUtils.logDelete("短信购活动管理", "删除商品指令，指令编号：" + id);
		return JsonRespWrapper.successReload("删除成功");
	}

	/**
	 * 查看
	 * 
	 * @param actId
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "viewAct")
	public String viewAct(@RequestParam(value = "actId") Long actId,
	        @RequestParam(value = "page", defaultValue = "1", required = false) Integer page, Model model) throws SQLException {
		smsBuyActOnlineService.viewAct(page, actId, model);

		return "/smsbuy/act/act-view";
	}

	/**
	 * 查看
	 * 
	 * @param actId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "viewRouter")
	public String viewRouter(@RequestParam(value = "id") Long id, Model model) {
		smsBuyActOnlineService.viewRouter(id, model);

		return "/smsbuy/router/router-view";

	}

	/**
	 * 活动审核通过
	 * 
	 * @param actId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "auditAct")
	@ResponseBody
	public Object auditAct(@RequestParam(value = "actId") Long actId, Model model) {
		smsBuyActOnlineService.updateActStatus(actId, "online");
		logUtils.logAudit("短信购活动管理", "活动审核通过，活动编号：" + actId);
		return JsonRespWrapper.successReload("审核通过！");
	}

	/**
	 * 活动审核驳回
	 * 
	 * @param actId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "rebutAudit")
	@ResponseBody
	public Object rebutAudit(@RequestParam(value = "actId") Long actId, Model model) {
		smsBuyActOnlineService.updateActStatus(actId, "rebutAudit");
		logUtils.logAudit("短信购活动管理", "活动审核驳回，活动编号：" + actId);
		return JsonRespWrapper.successReload("审核驳回成功！");
	}

	/**
	 * 活动审核下线
	 * 
	 * @param actId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "downAudit")
	@ResponseBody
	public Object downAudit(@RequestParam(value = "actId") Long actId, Model model) {
		// 下线将其修改为待审核
		smsBuyActOnlineService.updateActStatus(actId, "audit");
		logUtils.logOther("短信购活动管理", "活动审核下线成功，活动编号：" + actId);
		return JsonRespWrapper.successReload("下线成功！");
	}

	// @RequestMapping(value = "releaseAct")
	// @ResponseBody
	// public Object releaseAct(@RequestParam(value = "actId") Long actId, Model
	// model) {
	//
	// String msg = smsBuyActOnlineService.updateStatus(actId, "online");
	// return JsonRespWrapper.successReload(msg);
	// // return JsonRespWrapper.success(msg, "/smsbuy/act/auditList");
	// }
	//
	// @RequestMapping(value = "pauseAct")
	// @ResponseBody
	// public Object pauseAct(@RequestParam(value = "actId") Long actId, Model
	// model) {
	// smsBuyActOnlineService.updateStatus(actId, "pause");
	// // return JsonRespWrapper.success("暂停成功！", "/smsbuy/act/auditList");
	// return JsonRespWrapper.successReload("暂停成功！");
	// }
	//
	// @RequestMapping(value = "offlineAct")
	// @ResponseBody
	// public Object offlineAct(@RequestParam(value = "actId") Long actId, Model
	// model) {
	// smsBuyActOnlineService.updateStatus(actId, "offline");
	// // return JsonRespWrapper.success("下线成功！", "/smsbuy/act/auditList");
	// return JsonRespWrapper.successReload("下线成功！");
	// }
	//
	// @RequestMapping(value = "startAct")
	// @ResponseBody
	// public Object startAct(@RequestParam(value = "actId") Long actId, Model
	// model) {
	// smsBuyActOnlineService.updateStatus(actId, "release");
	// // return JsonRespWrapper.success("开始成功！", "/smsbuy/act/auditList");
	// return JsonRespWrapper.successReload("开始成功！");
	// }

	/**
	 * 下载活动配置模板
	 */
	@RequestMapping(value = "downSmsMould")
	public ModelAndView downFile(HttpServletRequest request, HttpServletResponse response) throws IOException, Exception {
		// System.out.println(System.getProperty("user.dir"));
		bsFileService.download(request, response, request.getRealPath("/").replace('\\', '/') + appConfig.getSmsModelUrl(), "smsModel.xlsx");
		return null;
	}

	/**
	 * 活动群发管理
	 * 
	 * @param smsBuyActOnline
	 * @param page
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "actBatchTask")
	public String actBatchTask(SmsBuyActOnline smsBuyActOnline, @RequestParam(value = "page", defaultValue = "1") Integer page, Model model)
	        throws SQLException {
		smsBuyActOnline.setStatus("online");
		Page<SmsBuyActOnline> pageList = smsBuyActOnlineService.listActBatchTask(smsBuyActOnline, page, Page.DEFAULT_PAGESIZE);
		model.addAttribute("pageData", pageList);
		model.addAttribute("statusMap", SmsBuyActOnline.statusMap);
		return "/smsbuy/act/act-batch-task";
	}

	/**
	 * 活动配置短信购群发任务增加页面
	 * 
	 * @param model
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	@RequestMapping(value = "actTaskAdd", method = RequestMethod.GET)
	public String toActTaskAdd(@RequestParam(value = "actId") Long actId, Model model) throws IOException, Exception {
		SmsBuyActOnline actOnline = smsBuyActOnlineService.findById(actId);
		BatchTask task = new BatchTask();
		task.setStartTime(TimeStamp.getTime(14));
		// task.setStopTime(TimeStamp.getTime(14));
		model.addAttribute("task", task);
		model.addAttribute("actOnline", actOnline);
		return "/smsbuy/act/add-act-batch-task";
	}

	/**
	 * 活动配置短信购群发任务增加操作
	 */
	@RequestMapping(value = "actTaskAdd", method = RequestMethod.POST)
	@ResponseBody
	public Object actTaskAdd(MultipartFile uploadfile, MultipartFile uploadblackfile, MultipartFile uploadwhitefile,
	        @ModelAttribute("task") BatchTask task, Model model, HttpServletRequest request, BindingResult result) {
		try {
			// 短信内容过滤
			// if (filterDao.countFilterwordNum(task.getTitle()) > 0) {
			// return JsonRespWrapper.successAlert("短信内容包含敏感字！");
			// }
			SessionUser sessionUser = (SessionUser) request.getSession().getAttribute(SessionUser.SESSION_USER_KEY);
			String unitId = sessionUser.getUnitId().toString();
			task.setCreatorId(sessionUser.getId());
			task.setUnitId(unitId);
			task.setIsNewTerminalId("1");
			taskValidator.validate(task, result);
			if (result.hasErrors()) {
				return JsonRespWrapper.error(result.getFieldErrors());
			}
			task.setService(appConfig.getBillCode());
			batchTaskService.saveTask(task, uploadfile, uploadblackfile, uploadwhitefile, 2l);
			// 返回到来源页面
			String backUrl = request.getParameter("backUrl");
			logUtils.logAdd("活动配置短信购群发任务", "新增群发任务，编号：" + task.getId());
			return JsonRespWrapper.success("操作成功", backUrl);
		}
		catch (Exception ex) {
			logUtils.logAdd("活动配置短信购群发任务", "新增群发任务出错，编号：" + task.getId() + ex.getMessage());
			log.error(ex.getMessage());
			return JsonRespWrapper.error(ex.getMessage());
		}
	}

	/**
	 * 验证指令是否存在
	 */
	@RequestMapping(value = "validateRouter")
	@ResponseBody
	public Object validateRouter(@RequestParam(value = "spCode") String spCode, @RequestParam(value = "command") String command,
	        @RequestParam(value = "id") Long id) {
		return smsbuyRouterValidator.validateRouter(spCode, command, id);
	}
}
