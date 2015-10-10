package com.cplatform.mall.back.sys.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
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
import com.cplatform.mall.back.item.web.ItemManageController;
import com.cplatform.mall.back.sys.dao.SysSegmentDao;
import com.cplatform.mall.back.sys.entity.SysSegment;
import com.cplatform.mall.back.sys.service.SysInfoService;
import com.cplatform.mall.back.utils.JsonRespWrapper;
import com.cplatform.mall.back.utils.LogUtils;

/**
 * Title. 系统信息-号段-管理控制器<br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-5-22 上午9:37:35
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: BaiJie
 * <p>
 * Version: 1.0
 * <p>
 */
@Controller
@RequestMapping(value = "/sys/segment")
public class SysSegmentController {

	@Autowired
	private LogUtils logUtils;

	@Autowired
	private SysSegmentDao sysSegmentDao;

	@Autowired
	private SysInfoService sysInfoService;
	
	private static final Logger log = Logger.getLogger(ItemManageController.class);

	/**
	 * 获取号段列表
	 * 
	 * @param sysSegment
	 *            号段实体类
	 * @param page
	 *            当前页
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/query")
	public String segmentList(SysSegment sysSegment, @RequestParam(value = "page", required = false, defaultValue = "1") int page, Model model)
	        throws SQLException {
		Page<SysSegment> segmentPage = sysInfoService.findSysSegment(sysSegment, page);
		model.addAttribute("sysSegment", sysSegment);
		model.addAttribute("segmentPage", segmentPage);
		return "/sys/segment/segment_list";
	}

	/**
	 * 跳转添加号段
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {
		SysSegment sysSegment = new SysSegment();
		sysSegment.setMmscId("950001");
		sysSegment.setOperatorCode("JSYD");
		model.addAttribute("method", "add");// 跳转标识（用于与编辑等操作区分）
		model.addAttribute("sysSegment", sysSegment);
		return "/sys/segment/segment_add";
	}

	/**
	 * 添加号段
	 * 
	 * @param sysSegment
	 *            号段实体类
	 * @param request
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Object createPost(@ModelAttribute("sysSegment") SysSegment sysSegment, HttpServletRequest request, BindingResult result) {
		// 判断号段是否重复
		List<SysSegment> sysSegmentList = sysSegmentDao.findBySegmentCode(sysSegment.getSegmentCode());
		if (sysSegmentList.size() > 0) {
			return JsonRespWrapper.successAlert("号段已存在！");
		}
		try {
			// 执行业务逻辑
			sysSegment = sysSegmentDao.save(sysSegment);
			logUtils.logAdd(" 添加号段", "操作成功,分类编号："+sysSegment.getId());
			// 提示并跳转
			return JsonRespWrapper.success("添加成功", "/sys/segment/query");

		}
		catch (Exception ex) {
			// 未知的错误消息，一般是exception catch后通知用户
			logUtils.logAdd(" 添加号段", "操作成功,分类编号："+sysSegment.getId());
			log.error(ex.getMessage());
			return JsonRespWrapper.error(ex.getMessage());
		}
	}

	/**
	 * 跳转编辑号段
	 * 
	 * @param id
	 *            待编辑号段ID
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(@RequestParam(required = false) Long id, Model model) {
		SysSegment sysSegment = sysSegmentDao.findOne(id);
		model.addAttribute("method", "edit");// 跳转标识（用于与编辑等操作区分）
		model.addAttribute("sysSegment", sysSegment);
		return "/sys/segment/segment_add";
	}

	/**
	 * 编辑号段
	 * 
	 * @param oldSegmentCode
	 *            原号段
	 * @param sysSegment
	 *            号段实体类
	 * @param request
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public Object updatePost(@RequestParam(required = false) String oldSegmentCode, @ModelAttribute("sysSegment") SysSegment sysSegment,
	        HttpServletRequest request, BindingResult result) {
		// 判断号段是否重复
		if (!oldSegmentCode.equals(sysSegment.getSegmentCode())) {// 如果号段未被修改则不检查
			List<SysSegment> sysSegmentList = sysSegmentDao.findBySegmentCode(sysSegment.getSegmentCode());
			if (sysSegmentList.size() > 0) {
				return JsonRespWrapper.successAlert("号段已存在！");
			}
		}
		try {
			// 执行业务逻辑
			sysSegment = sysSegmentDao.save(sysSegment);
			logUtils.logModify(" 修改号段", "操作成功,分类编号："+sysSegment.getId());
			// 提示并跳转
			return JsonRespWrapper.success("修改成功", "/sys/segment/query");

		}
		catch (Exception ex) {
			logUtils.logModify(" 修改号段", "操作成功,分类编号："+sysSegment.getId());
			log.error(ex.getMessage());
			// 未知的错误消息，一般是exception catch后通知用户
			return JsonRespWrapper.error(ex.getMessage());
		}
	}

	/**
	 * 删除号段
	 * 
	 * @param id
	 *            待删除号段ID
	 * @return
	 */
	@RequestMapping(value = "delete/{id}")
	@ResponseBody
	public Object deleteSegment(@PathVariable Long id) {
		sysSegmentDao.delete(id);
		logUtils.logModify(" 删除号段", "操作成功,分类编号："+id);
		return JsonRespWrapper.successReload("删除成功！");
	}

	/**
	 * 跳转批量上传号段
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public String upload(Model model) {
		SysSegment sysSegment = new SysSegment();
		sysSegment.setMmscId("950001");
		sysSegment.setOperatorCode("JSYD");
		model.addAttribute("method", "upload");
		model.addAttribute("sysSegment", sysSegment);
		return "/sys/segment/segment_batch";
	}

	/**
	 * 批量上传号段
	 * 
	 * @param uploadFile
	 *            待批量上传号段文件
	 * @param segment
	 *            号段实体类
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	public Object uploadPost(MultipartFile uploadFile, SysSegment segment, Model model) throws IOException {
		if (uploadFile == null) {
			// 提示用户必须要上传文件
			return JsonRespWrapper.success("您选择要导入的文件。", "/sys/segment/segment_batch");// 返回弹出提示
		}
		// 处理上传的文件
		BufferedReader br = new BufferedReader(new InputStreamReader(uploadFile.getInputStream()));
		String line = null;
		SysSegment bu = null;
		while ((line = br.readLine()) != null) {
			if(line.length()<=0){
				logUtils.logOther("空行", "手机号码：" + line);
				continue;//过滤空行
			}
			if(!SysInfoService.isNumeric(line)){
				logUtils.logOther("号段包含非数字字符", "手机号码：" + line);
				continue;//过滤非数字行
			}
			
			// 检查号码是否符合规则
			// 若不符合，则返回弹出提示
			// 校验号码是否已经在黑名单中
			List<SysSegment> segList = sysSegmentDao.findBySegmentCode(line);
			if (segList != null && !segList.isEmpty()) {
				logUtils.logOther("号段批量导入已存在号段", "手机号码：" + line + " 已经在号段表中");
				continue;
			}
			if(line.length()>10){
				logUtils.logOther("号段批量导入不合法号段", "号段：" + line + " 不合法");
				continue;
			}
			bu = new SysSegment(line, segment.getOperatorCode(), segment.getAreaCode(), segment.getMmscId());
			sysSegmentDao.save(bu);
		}
		// 返回弹出提示导入结论
		return JsonRespWrapper.success("批量导入成功。", "/sys/segment/query");
	}
}
