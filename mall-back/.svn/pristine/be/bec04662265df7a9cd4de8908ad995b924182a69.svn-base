package com.cplatform.mall.back.cont.web;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cplatform.dbhelp.page.Page;
import com.cplatform.mall.back.cont.dao.ContentCodeDao;
import com.cplatform.mall.back.cont.entity.ContMms;
import com.cplatform.mall.back.cont.entity.ContentCode;
import com.cplatform.mall.back.cont.mms.bean.Frame;
import com.cplatform.mall.back.cont.mms.util.TimeUtil;
import com.cplatform.mall.back.cont.service.ContMmsService;
import com.cplatform.mall.back.cont.service.ContentCodeService;
import com.cplatform.mall.back.cont.service.ContentService;
import com.cplatform.mall.back.model.SessionUser;
import com.cplatform.mall.back.utils.JsonRespWrapper;
import com.cplatform.mall.back.utils.LogUtils;
import com.cplatform.mall.back.web.validator.ContMmsPostValidator;

/**
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-5-30 下午2:58:24
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: wuyn@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
@Controller
@RequestMapping("/cont/mms/")
public class MmsController {

	static final Log logger = LogFactory.getLog(MmsController.class);

	@Autowired
	private ContentCodeService appInfoService;

	@Autowired
	private ContMmsService contMmsService;

	@Autowired
	private ContentService contentService;

	@Autowired
	private ContentCodeDao contentCodeDao;

	@Autowired
	private ContMmsPostValidator contMmsPostValidator;

	@Autowired
	private ContentCodeService contentCodeService;

	@Autowired
	private LogUtils logUtils;

	private final static String MODULE_NAME = "彩信管理";

	/**
	 * 查看彩信详情
	 * 
	 * @param appId
	 *            应用id
	 * @param id
	 *            短信/彩信id
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/view")
	public String viewContMms(@RequestParam(required = false) Long id, Model model) {
		ContMms contMms = contMmsService.getContMmsById(id);

		/** 将数据库时间转换成页面展示格式 yyyymmddhhmiss -> yyyy-mm-dd hh:mi:ss */
		contMms.setStartTime(TimeUtil.format(contMms.getStartTime(), TimeUtil.SOURCE_1, TimeUtil.TARGET_1));
		contMms.setEndTime(TimeUtil.format(contMms.getEndTime(), TimeUtil.SOURCE_1, TimeUtil.TARGET_1));

		model.addAttribute("contMms", contMms);
		return "/cont/mms/mms-view";
	}

	/**
	 * 打开短信/彩信添加页面
	 * 
	 * @param appId
	 *            应用id
	 * @param contType
	 *            短彩类别 sms-短信 mms-彩信
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/preAdd")
	public String preAdd(Model model) throws SQLException {

		ContMms contMms = new ContMms();
		List<ContentCode> contentCodeList = contentCodeService.getContentCodeList(1L);
		model.addAttribute("contentCodeList", contentCodeList);
		model.addAttribute("contMms", contMms);
		return "cont/mms/mms-add";
	}

	/**
	 * 创建彩信提交
	 * 
	 * @param appId
	 *            应用id
	 * @param contMms
	 *            彩信对象
	 * @param mmsFrameData
	 *            彩信内容字符串
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/add")
	@ResponseBody
	public Object addContMms(@ModelAttribute("mmsFrameData") String mmsFrameData, ContMms contMms, HttpSession session, BindingResult result,
	        HttpServletRequest request) {
		

		/** 验证彩信表单 */
		Collection<Frame> frames = contMmsService.getFrame(mmsFrameData); // 获得彩信对象
		contMmsPostValidator.validate(contMms, result, frames);
		Long contMmsId = contMms.getId();
		if (result.hasErrors()) {
			return JsonRespWrapper.success("彩信内容不合法！", "/cont/mms/list");
		} else {
			SessionUser sessionUser = (SessionUser) session.getAttribute(SessionUser.SESSION_USER_KEY);

			contMms.setUpdateUserId(sessionUser.getId());
			contMms.setUnitId(String.valueOf(sessionUser.getUnitId()));
			contMms = contMmsService.addContMms(contMms);
			contMmsService.createMmsContent(contMms, mmsFrameData, false, frames);
			logUtils.logAdd(MODULE_NAME, "添加,id:"+contMms.getId());

			if (contMmsId == null) {
				return JsonRespWrapper.success("添加成功", "/cont/mms/list");
			} else {
				// 返回来源地址
				String backUrl = request.getParameter("backUrl");
				return JsonRespWrapper.success("修改成功", backUrl);
			}
		}
	}

	/**
	 * 审核前
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/preAudit")
	public String preAuditMms(@RequestParam(required = false) Long id, Model model) {
		ContMms contMms = new ContMms();
		contMms.setId(id);
		model.addAttribute("contMms", contMms);
		return "/cont/mms/mms-audit";
	}

	/**
	 * 审核
	 * 
	 * @param id
	 * @param status
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/audit")
	@ResponseBody
	public Object auditMms(ContMms contMms, String status, HttpSession session, Model model, HttpServletRequest request) throws SQLException {
		
		SessionUser sessionUser = (SessionUser) session.getAttribute(SessionUser.SESSION_USER_KEY);

		contMms.setAuditorId(sessionUser.getId());
		String mag = "审核驳回成功";
		if ("1".equals(status.trim())) {
			mag = "审核成功";
		}
		contMmsService.auditContMms(contMms);
		logUtils.logAudit(MODULE_NAME, "审核,id:"+contMms.getId());
		// 返回来源地址
		String backUrl = request.getParameter("backUrl");
		return JsonRespWrapper.success(mag, backUrl);

	}

	/**
	 * 打开彩信编辑页面
	 * 
	 * @param appId
	 *            应用id
	 * @param id
	 *            短信/彩信id
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "preEdit")
	public String preEditMmsCont(@RequestParam(required = false) Long id, Model model) throws SQLException {

		ContMms contMms = contMmsService.getContMmsById(id);

		// /** 验证编辑权限 */
		// if (!checkContMmsPermission(contMms, session)) {
		// return "redirect:../info/no-permission";
		// }

		/** 将数据库时间转换成页面展示格式 yyyymmddhhmiss -> yyyy-mm-dd hh:mi:ss */
		contMms.setStartTime(TimeUtil.format(contMms.getStartTime(), TimeUtil.SOURCE_1, TimeUtil.TARGET_1));
		contMms.setEndTime(TimeUtil.format(contMms.getEndTime(), TimeUtil.SOURCE_1, TimeUtil.TARGET_1));

		List<ContentCode> contentCodeList = contentCodeService.getContentCodeList(1L);
		model.addAttribute("contentCodeList", contentCodeList);

		model.addAttribute("contMms", contMms);
		// AppInfo app = appInfoService.getAppInfo(appId);
		// model.addAttribute("app", app);
		return "cont/mms/mms-edit";
	}

	/**
	 * 获得应用下的彩信/短信
	 * 
	 * @param pageNumber
	 *            分页总数
	 * @param pageSize
	 *            页面条数
	 * @param filter
	 *            查询条件 彩信/短信审核状态 0-待审核/1-审核通过/2-审核驳回
	 * @param filterCt
	 *            查询条件 彩信/短信分类 0-短信/1-彩信
	 * @param appId
	 *            应用id
	 * @param startTime
	 *            查询条件开始时间
	 * @param endTime
	 *            查询条件结束时间
	 * @param session
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/list")
	public String list(ContMms contMms, @RequestParam(value = "page", required = false, defaultValue = "1") int page, Model model)
	        throws SQLException {
		/** 获得内容列表 */
		Page<ContMms> contentMmsList = contMmsService.listContMms(contMms, page, Page.DEFAULT_PAGESIZE);

		model.addAttribute("pageData", contentMmsList);
		model.addAttribute("statusMap", ContMms.ASTATUS_MAP);
		// /** 内容列表 */
		// model.addAttribute("content", content);

		return "/cont/mms/mms-list";
	}

	/**
	 * 加载已有彩信模块
	 * 
	 * @param mmsId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "loadMms/{mmsId}", method = RequestMethod.GET)
	@ResponseBody
	public Object loadMms(@PathVariable Long mmsId, Model model) {
		return contMmsService.getMmsContent(mmsId);
	}

	/**
	 * 打开彩信编辑模块
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "mmsdiy")
	public String mmsdiy(Model model, HttpServletRequest request) {
		String mmsId = request.getParameter("mmsId");
		model.addAttribute("mmsId", mmsId);
		return "cont/mms/diy/diy-inc";
	}

	@RequestMapping(value = "mmsdiys/{mmsId}", method = RequestMethod.GET)
	public String mmsdiys(@PathVariable String mmsId, Model model) {
		model.addAttribute("mmsId", mmsId);
		return "content/diy/diy-inc";
	}

	/**
	 * 上传彩信附件
	 * 
	 * @param file
	 * @param type
	 *            附件类型 pic-图片 sound-声音文件
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "single_upload", headers = "content-type=multipart/*", produces = "text/html", method = RequestMethod.POST)
	@ResponseBody
	public Object singleUpload(@RequestParam("mmsupfile") MultipartFile file, @RequestParam("type") String type) throws IOException {
		logUtils.logView(MODULE_NAME, "上传文件");
		InputStream is = file.getInputStream();
		String fileName = FilenameUtils.getName(file.getOriginalFilename());
		return contentService.uploadFile(fileName, type, is);
	}

	@RequestMapping(value = "jpye_cxqf_single_crop", method = RequestMethod.GET)
	@ResponseBody
	public Object singleCrop(HttpServletRequest request) throws IOException {
		logUtils.logView(MODULE_NAME, "裁剪图片");
		return contentService.singleCrop(request);
	}

	@RequestMapping(value = "/delete/{id}")
	@ResponseBody
	public Object delete(@PathVariable Long id) {
		// 彩信
		// ContMms contMms = contMmsService.getContMmsById(id);
		// if (!checkContMmsPermission(contMms, session)) {
		// return JsonRespWrapper.error("no permission");
		// }
		
		contMmsService.deleteContMmsById(id);
		logUtils.logDelete(MODULE_NAME, "删除,id:"+id);
		return JsonRespWrapper.successReload("删除成功");
	}

}
