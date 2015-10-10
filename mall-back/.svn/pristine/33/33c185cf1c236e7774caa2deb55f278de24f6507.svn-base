package com.cplatform.mall.back.websys.web;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cplatform.dbhelp.page.Page;
import com.cplatform.mall.back.sys.entity.SysRegion;
import com.cplatform.mall.back.sys.service.SysRegionService;
import com.cplatform.mall.back.utils.JsonRespWrapper;
import com.cplatform.mall.back.utils.LogUtils;
import com.cplatform.mall.back.websys.dao.ChannelFloorDao;
import com.cplatform.mall.back.websys.entity.ChannelCatalogConfig;
import com.cplatform.mall.back.websys.entity.ChannelCatalogRcmdConfig;
import com.cplatform.mall.back.websys.entity.ChannelFloor;
import com.cplatform.mall.back.websys.service.ChannelFloorService;
import com.cplatform.mall.back.websys.service.FloorCatalogService;

/**
 * 频道楼层分类服务类. <br>
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
@RequestMapping(value = "/websys/channel/catalog")
public class FloorCatalogController {

	@Autowired
	private FloorCatalogService floorCatalogService;
	
	@Autowired
	ChannelFloorService channelfloorService;
	
	@Autowired
	private LogUtils logUtils;
	
	@Autowired
	private ChannelFloorDao channelFloorDao;
	
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
	public String list(
			ChannelCatalogConfig channelCatalog,
			@RequestParam(value = "page", defaultValue = "1", required = false) int page,
			Model model) throws SQLException {
		
		Page<ChannelCatalogConfig>  pagedata = floorCatalogService.list(channelCatalog, page, Page.DEFAULT_PAGESIZE);
		List<ChannelCatalogConfig> data = pagedata.getData();
		for (ChannelCatalogConfig channel : data) {
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
		
		ChannelCatalogConfig catalogConfig = new ChannelCatalogConfig();
		model.addAttribute("groupMap", ChannelCatalogRcmdConfig.groupMap);
		model.addAttribute("channelMap", ChannelCatalogRcmdConfig.channelMap);
		model.addAttribute("statusMap", ChannelCatalogConfig.statusMap);
		model.addAttribute("catalogConfig", catalogConfig);
		return "/websys/channel/floor/floorCatalog-list";
	}

	/**
	 * 频道分类添加预处理
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "preAdd")
	public String preAdd(Model model) {
	
		ChannelCatalogConfig channelCatalog = new ChannelCatalogConfig();
		channelCatalog.setOrderIndex(99);
		
		model.addAttribute("groupMap", ChannelCatalogRcmdConfig.groupMap);
		model.addAttribute("channelMap", ChannelCatalogRcmdConfig.channelMap);
		model.addAttribute("channelCatalog", channelCatalog);
		model.addAttribute("method", "add");
	

		return "/websys/channel/floor/floorCatalog-add";
	}

	/**
	 * 添加
	 * 
	 * @param channelCatalog
	 * @param model
	 * @return
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@RequestMapping(value = "add")
	@ResponseBody
	public Object add(ChannelCatalogConfig channelCatalog, String items,
			Model model,HttpServletRequest request) throws IllegalAccessException,
			InvocationTargetException {
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
		String msg = "添加成功！";
		if (channelCatalog.getId() != null) {
			msg = "修改成功！";
		}
		if (StringUtils.isEmpty(items)) {
			msg = "请选择商品";
		}else {
			String[] itemIds = items.split(",");
			ChannelCatalogConfig ctmp = null;
			for (String itemid : itemIds) {
				ctmp = new ChannelCatalogConfig();
				BeanUtils.copyProperties(ctmp, channelCatalog);
				ctmp.setItemId(Long.parseLong(itemid));
				floorCatalogService.save(ctmp);
				   logUtils.logAdd("频道分类添加预处理", "操作成功！");
			}
		}

		String backUrl = request.getParameter("backUrl");
		return JsonRespWrapper.success(msg, backUrl);
	}

	@RequestMapping(value = "preEdit")
	public String preEdit(@RequestParam(value = "id", required = true) Long id,
			Model model) throws SQLException {

		floorCatalogService.preEdit(id, model);

		return "/websys/channel/floor/floorCatalog-add";
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
	public Object deleteItem(@RequestParam(value = "ids") String ids,
			Model model) {
		String[] idarray = ids.split(",");
	try {
	
		for (String id : idarray) {
			if ("".equals(id)) {
				continue;
			}
			floorCatalogService.delete(Long.valueOf(id));
			   logUtils.logDelete("频道分类删除预处理", "操作成功！"+id);
		}
	} catch (Exception e) {
		
	}
		return JsonRespWrapper.success("删除成功！", "/websys/channel/catalog/list");
	}

	/**
	 * 开启
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/turnOn")
	@ResponseBody
	public Object turnOn(@RequestParam Long id,Long isValid, Model model,HttpServletRequest request) {
		//System.out.println(isValid);
		ChannelCatalogConfig catalogConfig = floorCatalogService.findById(id);
		//System.out.println(isValid);
		catalogConfig.setIsValid(isValid);
		if(0==catalogConfig.getIsValid())
		{
			
			 return JsonRespWrapper.successAlert("此商品已下架！，不能启动，sorry");
			
		}
		floorCatalogService.setFloorCatalogStatus(id, 1);
		String backUrl = request.getParameter("backUrl");
		return JsonRespWrapper.success("启用成功", backUrl);
	}

	@RequestMapping(value = "/getSelect")
	@ResponseBody
	public Object getSelect(@RequestParam(value = "channelId") Integer channelId,
			@RequestParam(value = "regionCode") String regionCode, Model model) throws SQLException {
		
		ChannelFloor channelfloor = new ChannelFloor();
		List<ChannelFloor> listChannelFloor = channelfloorService.findChannelFloorByrf(channelfloor, channelId, regionCode);
		return listChannelFloor;
	}
	
	@RequestMapping(value = "/selectArea")
	@ResponseBody
	public Object selectArea(@RequestParam(value = "channel", required = true) String channel,@RequestParam(value = "floor", required = true) String floor, Model model) throws SQLException {
		List<SysRegion> regions = new ArrayList<SysRegion>();
		ChannelFloor  channelFloor = channelFloorDao.findChannelFloorByChannelAndFloor(Integer.parseInt(channel), Integer.parseInt(floor));
		if(null == channelFloor){
			return regions;
		}
		String[] areas = channelFloor.getRegionCode().split(",");
		for(int i=0;i<areas.length;i++){
			SysRegion sysRegion = sysRegionService.findByRegionCode(areas[i]);
			regions.add(sysRegion);
		}
		String regionArray = JSONArray.fromObject(regions).toString();
		return regionArray;
	}

	/**
	 * 禁用
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/turnOff")
	@ResponseBody
	public Object turnOff(@RequestParam Long id, Model model,HttpServletRequest request) {
		
		floorCatalogService.setFloorCatalogStatus(id, 0);
		String backUrl = request.getParameter("backUrl");
		return JsonRespWrapper.success("禁用成功", backUrl);
	}

	/*
	 * 修改排序
	 */
	@RequestMapping(value = "/editSort")
	@ResponseBody
	public Object editSort(@RequestParam Long id, Integer orderIndex,
			Model model) {
		
		floorCatalogService.setFloorCatalogOrder(id, orderIndex);
		   logUtils.logModify("频道分类编辑预处理", "操作成功！"+id);
		return JsonRespWrapper.successAlert("修改成功！");
	}
}
