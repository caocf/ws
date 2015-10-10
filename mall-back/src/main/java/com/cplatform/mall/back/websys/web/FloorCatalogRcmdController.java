package com.cplatform.mall.back.websys.web;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cplatform.dbhelp.page.Page;
import com.cplatform.mall.back.sys.service.SysRegionService;
import com.cplatform.mall.back.utils.JsonRespWrapper;
import com.cplatform.mall.back.utils.LogUtils;
import com.cplatform.mall.back.utils.PathUtil.PathInfo;
import com.cplatform.mall.back.websys.entity.ChannelCatalogConfig;
import com.cplatform.mall.back.websys.entity.ChannelCatalogRcmdConfig;
import com.cplatform.mall.back.websys.entity.ChannelFloor;
import com.cplatform.mall.back.websys.entity.SysChannelPicConf;
import com.cplatform.mall.back.websys.service.BsFileService;
import com.cplatform.mall.back.websys.service.ChannelFloorService;
import com.cplatform.mall.back.websys.service.FloorCatalogRcmdService;

/**
 * 频道楼层推荐服务类. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-6-14 下午2:25:40
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: wuyn@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
@Controller
@RequestMapping(value = "/websys/channel/recommend")
public class FloorCatalogRcmdController {

	@Autowired
	private FloorCatalogRcmdService floorCatalogRcmdService;

	@Autowired
	BsFileService bsFileService;

	@Autowired
	ChannelFloorService channelfloorService;

	
	@Autowired
	private LogUtils logUtils;
	
	@Autowired
	private SysRegionService sysRegionService;
	/**
	 * 列表
	 * 
	 * @param channelCatalog
	 * @param page
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "list")
	public String list(ChannelCatalogRcmdConfig ChannelCatalogRcmdConfig,
	        @RequestParam(value = "page", defaultValue = "1", required = false) int page, Model model) throws SQLException {

		Page<ChannelCatalogRcmdConfig> pagedata = floorCatalogRcmdService.list(ChannelCatalogRcmdConfig, page, Page.DEFAULT_PAGESIZE);
		List<ChannelCatalogRcmdConfig> data = pagedata.getData();
		for (ChannelCatalogRcmdConfig channel : data) {
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
		model.addAttribute("statusMap", ChannelCatalogConfig.statusMap);

		return "/websys/channel/floor/recommend-list";
	}

	/**
	 * 频道分类添加预处理
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "preAdd")
	public String preAdd(Model model) {
	try {
		ChannelCatalogRcmdConfig channelCatalogRcmd = new ChannelCatalogRcmdConfig();
		channelCatalogRcmd.setType(1);
		channelCatalogRcmd.setOrderIndex(99);
		model.addAttribute("groupMap", ChannelCatalogRcmdConfig.groupMap);
		model.addAttribute("channelMap", ChannelCatalogRcmdConfig.channelMap);
		model.addAttribute("channelCatalogRcmd", channelCatalogRcmd);
		model.addAttribute("method", "add");
	} catch (Exception e) {
	
	}

		return "/websys/channel/floor/recommend-add";
	}

	/**
	 * 添加
	 * 
	 * @param channelCatalog
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "add")
	@ResponseBody
	public Object add(ChannelCatalogRcmdConfig channelCatalog, MultipartFile uploadFile, Model model,HttpServletRequest request) throws Exception {
		String region = "";
		String[] regionCodes = request.getParameterValues("regionCode");
		 if(StringUtils.isEmpty(channelCatalog.getRegionCode())){
			return JsonRespWrapper.successAlert("请检查楼层配置，该配置下没有区域，或者没有选择区域！");
		}
		for(int i=0;i<regionCodes.length;i++){
			if (i == regionCodes.length - 1) {
				region += regionCodes[i];
			} else {
				region += regionCodes[i] + ",";
			}
		}
		channelCatalog.setRegionCode(region);
		if (uploadFile != null && !uploadFile.isEmpty()) {
			// //判断同一频道同一楼层是否设置过图片
			// if (null == channelCatalog.getId()) {
			// List<ChannelCatalogRcmdConfig> channelList =
			// floorCatalogRcmdService.findChannelIsExit(channelCatalog.getGroupId(),
			// channelCatalog
			// .getChannel(), channelCatalog.getType());
			// if (channelList.size() > 0) {
			// return JsonRespWrapper.success("同一频道同一楼层只允许设置一张图片！",
			// "/websys/channel/recommend/list");
			// }
			// }
			// modify by xq if else
			PathInfo pathUtil = bsFileService.dealModuleFile(uploadFile, BsFileService.MODULE_CHANNEL_CMD_PIC);
			channelCatalog.setImagePath(pathUtil.getRealWebPath(""));
		}

		String msg = "添加成功！";
		if (channelCatalog.getId() != null) {
			// add by xq
			if (null == channelCatalog.getImagePath()) {
				ChannelCatalogRcmdConfig channelCatalogRcmdConfig = floorCatalogRcmdService.findChannelCatalogRcmdConfigById(channelCatalog.getId());
				if (null != channelCatalogRcmdConfig) {
					channelCatalog.setImagePath(channelCatalogRcmdConfig.getImagePath());
				}
			}
			msg = "修改成功！";
		}

		floorCatalogRcmdService.save(channelCatalog);
		logUtils.logModify("频道分类编辑预处理", "操作成功！"+channelCatalog.getId());

		String backUrl = request.getParameter("backUrl");
		return JsonRespWrapper.success(msg, backUrl);
	}

	@RequestMapping(value = "preEdit")
	public String preEdit(@RequestParam(value = "id", required = true) Long id, Model model) throws SQLException {

		floorCatalogRcmdService.preEdit(id, model);

		return "/websys/channel/floor/recommend-add";
	}

	@RequestMapping(value = "/getSelect")
	@ResponseBody
	public Object getSelect(@RequestParam(value = "channelId") Integer channelId, @RequestParam(value = "regionCode") String regionCode, Model model)
	        throws SQLException {

		ChannelFloor channelfloor = new ChannelFloor();
		List<ChannelFloor> listChannelFloor = channelfloorService.findChannelFloorByrf(channelfloor, channelId, regionCode);
		return listChannelFloor;
	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Object deleteItem(@RequestParam Long id, Model model) {
	
		floorCatalogRcmdService.delete(id);
		logUtils.logDelete("频道分类删除", "删除成功！频道编号："+id);
		return JsonRespWrapper.successReload("删除成功！");
	}
}
