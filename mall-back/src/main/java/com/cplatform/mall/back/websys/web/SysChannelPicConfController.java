package com.cplatform.mall.back.websys.web;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.commons.lang3.StringUtils;
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
import com.cplatform.mall.back.item.entity.ItemSale;
import com.cplatform.mall.back.sys.entity.SysRegion;
import com.cplatform.mall.back.sys.service.SysRegionService;
import com.cplatform.mall.back.utils.AppConfig;
import com.cplatform.mall.back.utils.JsonRespWrapper;
import com.cplatform.mall.back.utils.LogUtils;
import com.cplatform.mall.back.websys.dao.ChannelFloorDao;
import com.cplatform.mall.back.websys.dao.FloorCatalogDao;
import com.cplatform.mall.back.websys.dao.SysChannelPicConfDao;
import com.cplatform.mall.back.websys.entity.ChannelCatalogConfig;
import com.cplatform.mall.back.websys.entity.ChannelCatalogRcmdConfig;
import com.cplatform.mall.back.websys.entity.ChannelFloor;
import com.cplatform.mall.back.websys.entity.SysChannelPicConf;
import com.cplatform.mall.back.websys.service.BsFileService;
import com.cplatform.mall.back.websys.service.ChannelFloorService;
import com.cplatform.mall.back.websys.service.SysChannelPicConfService;

/**
 * Title. <br>
 * 频道图片管理控制类 Description.
 * <p/>
 * Copyright: Copyright (c) 13-6-13
 * <p/>
 * Company: 北京宽连十方数字技术有限公司
 * <p/>
 * Author: zhouhui@c-platform.com
 * <p/>
 * Version: 1.0
 * <p/>
 */

@Controller
@RequestMapping("/websys/channel/catalog/")
public class SysChannelPicConfController {

	@Autowired
	private LogUtils logUtils; // TODO

	@Autowired
	private SysChannelPicConfService service;

	@Autowired
	private SysChannelPicConfDao dao;

	@Autowired
	BsFileService bsFileService;

	@Autowired
	AppConfig appConfig;

	@Autowired
	SysRegionService regionService;
	
	@Autowired
	ChannelFloorService channelfloorService;
	
	@Autowired
	private ChannelFloorDao channelFloorDao;
	
	@Autowired
	private SysRegionService sysRegionService;
	
	@Autowired
	private FloorCatalogDao floorCatalogDao;
	
	@RequestMapping(value = "pic_list")
	public String templateList(@ModelAttribute("sysChannelPicConf") SysChannelPicConf scpc,
	        @RequestParam(value = "page", required = false, defaultValue = "1") int page, HttpSession session, Model model) throws SQLException {
		Page<SysChannelPicConf> pagedata = service.getSysTempLateInfo(scpc, page, Page.DEFAULT_PAGESIZE);
		List<SysChannelPicConf> data = pagedata.getData();
		for (SysChannelPicConf channel : data) {
			String[] regionCodes = channel.getRegionCode().split(",");
			String regionCodeName = "";
			for (int i = 0; i < regionCodes.length; i++) {
				if (i == regionCodes.length - 1) {
					regionCodeName += sysRegionService.findByRegionCode(regionCodes[i]).getRegionName();
				} else {
					regionCodeName += sysRegionService.findByRegionCode(regionCodes[i]).getRegionName() + ",";
				}
			}
			channel.setRegionName(regionCodeName);
		}
		model.addAttribute("pageData", pagedata);
		model.addAttribute("groupMap", ChannelCatalogRcmdConfig.groupMap);
		model.addAttribute("channelMap", ChannelCatalogRcmdConfig.channelMap);
		return "websys/channel/catalog/pic_list";
	}

	/**
	 * 添加板块事件转到添加页面
	 * 
	 * @param session
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "pic_add", method = RequestMethod.GET)
	public String picAdd(HttpSession session, Model model) throws SQLException {
		SysChannelPicConf info = new SysChannelPicConf();
		info.setPicIndex(99);
		model.addAttribute("method", "add");
		model.addAttribute("channelMap", ChannelCatalogRcmdConfig.channelMap);
		model.addAttribute("groupMap", ChannelCatalogRcmdConfig.groupMap);
		model.addAttribute("info", info);
		return "websys/channel/catalog/pic_add";
	}

	/**
	 * 添加模块事件 执行添加访问数据库
	 * 
	 * @param SysTemplateEvent
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "pic_add", method = RequestMethod.POST)
	@ResponseBody
	public Object templateSave(MultipartFile uploadFile, @ModelAttribute("info") SysChannelPicConf scpc, HttpServletRequest request) throws Exception {
		String region = "";
		String[] regionCodes = request.getParameterValues("regionCode");
		 if(StringUtils.isEmpty(scpc.getRegionCode())){
			return JsonRespWrapper.successAlert("请检查楼层配置，该配置下没有区域，或者没有选择区域！");
		}
		for(int i=0;i<regionCodes.length;i++){
			if (i == regionCodes.length - 1) {
				region += regionCodes[i];
			} else {
				region += regionCodes[i] + ",";
			}
		}
		scpc.setRegionCode(region);
		if (uploadFile == null || uploadFile.isEmpty()) {
			// 提示用户必须要上传文件
			return JsonRespWrapper.successAlert("请上传图片文件。");// 弹出提示
		}
		service.save(scpc, uploadFile);
		logUtils.logAdd("添加模块事件 执行添加访问数据库","添加成功");
		// 提示并跳转
		return JsonRespWrapper.success("添加成功", "/websys/channel/catalog/pic_list");
	}

	/**
	 * 编辑模块事件访问到编辑页面
	 * 
	 * @param id
	 * @param session
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "pic_edit", method = RequestMethod.GET)
	public String templateEdit(@RequestParam(value = "id") int id, HttpSession session, Model model) throws SQLException {
		SysChannelPicConf scpc = dao.findById(id);

		model.addAttribute("groupMap", ChannelCatalogRcmdConfig.groupMap);
		model.addAttribute("channelMap", ChannelCatalogRcmdConfig.channelMap);
		model.addAttribute("info", scpc);
		model.addAttribute("method", "edit");
		
		ChannelFloor  channelFloor = channelFloorDao.findChannelFloorByChannelAndFloor(scpc.getChannel(), scpc.getPostion());
		String[] areas = channelFloor.getRegionCode().split(",");
		
		String regionCode = scpc.getRegionCode();
		String[] regionCodes = regionCode.split(",");
		
		List<String> regionAll = Arrays.asList(regionCodes);
		List<String> regionAll2 = Arrays.asList(areas);
		
		String htmlStr = "";
		for(int i=0;i<regionCodes.length;i++){
			if(regionAll2.contains(regionCodes[i])){
				htmlStr += "<input type='checkbox' name='regionCode' checked='checked' value='"+regionCodes[i]+"'/>" +"<label>"+sysRegionService.findByRegionCode(regionCodes[i]).getRegionName()+"</label>";
			}
		}
		for(int i=0;i<areas.length;i++){
			if(!regionAll.contains(areas[i])){
				htmlStr += "<input type='checkbox' name='regionCode'  value='"+areas[i]+"'/>" +"<label>"+sysRegionService.findByRegionCode(areas[i]).getRegionName()+"</label>";
			}
		}
		model.addAttribute("htmlStr", htmlStr);
		return "websys/channel/catalog/pic_add";
	}

	@RequestMapping(value = "/getSelect")
	@ResponseBody
	public Object getSelect(@RequestParam(value = "channelId") Integer channelId,
			@RequestParam(value = "regionCode") String regionCode, Model model) throws SQLException {
		
		ChannelFloor channelfloor = new ChannelFloor();
		List<ChannelFloor> listChannelFloor = channelfloorService.findChannelFloorByrf(channelfloor, channelId, regionCode);
		return listChannelFloor;
	}
	
	/**
	 * 编辑模块事件执行编辑访问数据库
	 * 
	 * @param SysTemplateEvent
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "pic_edit", method = RequestMethod.POST)
	@ResponseBody
	public Object templateUpdate(MultipartFile uploadFile, @ModelAttribute("info") SysChannelPicConf scpc, HttpServletRequest request)
	        throws Exception {
		String region = "";
		String[] regionCodes = request.getParameterValues("regionCode");
		 if(StringUtils.isEmpty(scpc.getRegionCode())){
			return JsonRespWrapper.successAlert("请检查楼层配置，该配置下没有区域，或者没有选择区域！");
		}
		for(int i=0;i<regionCodes.length;i++){
			if (i == regionCodes.length - 1) {
				region += regionCodes[i];
			} else {
				region += regionCodes[i] + ",";
			}
		}
		scpc.setRegionCode(region);
		service.save(scpc, uploadFile);
		logUtils.logModify("编辑模块事件执行编辑访问数据库", "操作完成,模块编号：",+scpc.getId().longValue());
		String backUrl = request.getParameter("backUrl");
		return JsonRespWrapper.success("修改成功", backUrl);
	}

	/**
	 * 删除模块事件
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "pic_del")
	@ResponseBody
	public Object templateDel(@RequestParam(value = "id") int id) {

		dao.delete(id);
		logUtils.logModify("删除模块事件执行编辑访问数据库", "操作完成,模块编号：",+Long.valueOf(id));
		return JsonRespWrapper.success("删除成功！", "/websys/channel/catalog/pic_list");
	}
}
