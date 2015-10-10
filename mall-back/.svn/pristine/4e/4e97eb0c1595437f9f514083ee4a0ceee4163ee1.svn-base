package com.cplatform.mall.back.giftcard.web;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cplatform.dbhelp.page.Page;
import com.cplatform.mall.back.cont.mms.util.TimeUtil;
import com.cplatform.mall.back.giftcard.entity.GiftCardTask;
import com.cplatform.mall.back.giftcard.entity.GiftCardTaskExt;
import com.cplatform.mall.back.giftcard.entity.ReturnData;
import com.cplatform.mall.back.giftcard.service.GiftTaskService;
import com.cplatform.mall.back.utils.JsonRespWrapper;
import com.cplatform.mall.back.utils.ReadExcel;

/**
 * 
 * @author mudeng
 * 礼品卡库存管理Controller
 *
 */
@Controller
@RequestMapping("/giftcard/task")
public class GiftTaskController {

	@Autowired
	GiftTaskService giftTaskService;

	/**
	 * 
	 * @param giftRequired
	 * @param page
	 * @param model
	 * @return
	 * @throws SQLException
	 * 展示成品卡批次列表
	 */
	@RequestMapping(value = { "/list", "/", "" })
	public String list(GiftCardTask giftCardTask, @RequestParam(value = "page", required = false, defaultValue = "1") int page, Model model) {
		try {
			model.addAttribute("pageData", giftTaskService.findGiftTask(giftCardTask, page, Page.DEFAULT_PAGESIZE));
		} catch (Exception e) {
//			e.printStackTrace();
		}
		return "/giftcard/task/list";
	}
	
	@RequestMapping(value = { "/viewlist" })
	public String viewList(GiftCardTaskExt giftCardTaskExt, @RequestParam(value = "page", required = false, defaultValue = "1") int page, Model model, HttpServletRequest request) {
		if(request.getParameter("id")!=null&&request.getParameter("id")!=""){
			giftCardTaskExt.setTaskId(Long.valueOf(request.getParameter("id")));
		}
		try {
			model.addAttribute("pageData", giftTaskService.findGiftTaskExt(giftCardTaskExt, page, Page.DEFAULT_PAGESIZE));
			model.addAttribute("id", giftCardTaskExt.getTaskId());
		} catch (Exception e) {
//			e.printStackTrace();
		}
		return "/giftcard/task/viewlist";
	}
	
	@RequestMapping(value = "preAdd")
	public String preAdd(Model model) {
		GiftCardTask giftCardTask = new GiftCardTask();
		model.addAttribute("data", giftCardTask);
		model.addAttribute("method", "add");
		return "/giftcard/task/add";
	}
	
	@RequestMapping(value = "actCard")
	@ResponseBody
	public Object actCard(Model model){
		try {
			String msg = giftTaskService.actCard();
			return JsonRespWrapper.successAlert(msg);
		} catch (Exception e) {
			return "程序异常，请联系管理员！";
		}
	}
	
	@RequestMapping(value = "add")
	@ResponseBody
	public Object add(GiftCardTask giftCardTask, MultipartFile uploadFile, Model model, HttpServletRequest request) throws Exception {
		ReturnData returnData = new ReturnData();
		giftCardTask.setStatus(0L);
		giftCardTask.setCreateTime(TimeUtil.now());
		if(giftCardTask.getResources() == 0L){
			returnData = giftTaskService.addTaskBybatchNo(giftCardTask.getBatchNo(),giftCardTask);
		}else if(giftCardTask.getResources() == 1L){
			if (uploadFile != null && uploadFile.getOriginalFilename() != null && !"".equals(uploadFile.getOriginalFilename())) {
				if (uploadFile.getOriginalFilename().indexOf(".xlsx") < 0 && uploadFile.getOriginalFilename().indexOf(".xls") < 0) {
					return JsonRespWrapper.successReload("上传文件格式不正确！");
				}
			} else {
				uploadFile = null;
			}
			String filepath = request.getParameter("filepath");
			if (uploadFile != null) {
				String[] serialNos = ReadExcel.getCardData(uploadFile.getInputStream(), 1, filepath);
				if(serialNos == null || serialNos.length<=0){
					return JsonRespWrapper.successAlert("上传的文件没有有效的礼品卡序列号");
				}else{
					giftCardTask.setFilePath(filepath);
					returnData = giftTaskService.addTaskByFile(serialNos,filepath,giftCardTask);
				}
			} else {
				return JsonRespWrapper.successAlert("没有文件上传");
			}
		}else{
			returnData = giftTaskService.addTaskByserialNo(giftCardTask.getSerialStartNo(), giftCardTask.getSerialEndNo(),giftCardTask);
		}
		if("0".equals(returnData.getStatus())){
			return JsonRespWrapper.successAlert("添加失败。"+returnData.getMsg());
		}
		return JsonRespWrapper.success("添加成功。"+returnData.getMsg(), "/giftcard/task/list");
	}

}
