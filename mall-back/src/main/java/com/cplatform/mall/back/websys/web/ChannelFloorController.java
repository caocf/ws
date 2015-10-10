package com.cplatform.mall.back.websys.web;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cplatform.dbhelp.page.Page;
import com.cplatform.mall.back.sys.dao.SysTypeDao;
import com.cplatform.mall.back.sys.service.SysRegionService;
import com.cplatform.mall.back.utils.JsonRespWrapper;
import com.cplatform.mall.back.utils.LogUtils;
import com.cplatform.mall.back.websys.dao.ChannelFloorDao;
import com.cplatform.mall.back.websys.entity.ChannelFloor;
import com.cplatform.mall.back.websys.service.ChannelFloorService;

@Controller
@RequestMapping("/websys/channel/floor")
public class ChannelFloorController {

	@Autowired
	ChannelFloorService channelfloorService;

	@Autowired
	ChannelFloorDao channelFloorDao;

	@Autowired
	SysRegionService regionService;
	
	@Autowired
	private LogUtils logUtils;

	@Autowired
	SysTypeDao sysTypeDao;
	
	@Autowired
	private SysRegionService sysRegionService;

	@RequestMapping(value = { "/list", "/", "" })
	public String listChannelFloor(ChannelFloor channelfloor, @RequestParam(value = "page", required = false, defaultValue = "1") int page, Model model)
	        throws SQLException {
		Page<ChannelFloor>  pagedata = channelfloorService.findChannelFloor(channelfloor, page, Page.DEFAULT_PAGESIZE);
		List<ChannelFloor> data = pagedata.getData();
		for (ChannelFloor channel : data) {
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
		model.addAttribute("groupMap", channelfloor.groupMap);
		model.addAttribute("channelMap", channelfloor.channelMap);
		return "/websys/channel/floor/floor-list";
	}
	
	@RequestMapping(value = "preAdd")
	public String preAdd(Model model) {
		ChannelFloor channelfloor = new ChannelFloor();
		channelfloor.setOrderIndex(99);
		model.addAttribute("groupMap", channelfloor.groupMap);
		model.addAttribute("channelMap", channelfloor.channelMap);
		model.addAttribute("channelFloor", channelfloor);
		model.addAttribute("method", "add");
		return "/websys/channel/floor/floor-add";
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
	public Object add(ChannelFloor channelfloor, MultipartFile uploadFile, Model model,HttpServletRequest request) throws Exception {
		String msg = "";
		if (channelfloor.getId() != null) {
			msg = "修改成功！";
			 channelFloorDao.delete(channelfloor.getId());
			if(channelfloorService.getCount(channelfloor.getFloorId(),channelfloor.getChannel())>0){
					return JsonRespWrapper.successAlert("所选区域已有相同楼层配置，请重新选择！");
				}
		}else{
			 msg = "添加成功！";
			 if(channelfloorService.getCount(channelfloor.getFloorId(),channelfloor.getChannel())>0){
					return JsonRespWrapper.successAlert("所选区域已有相同楼层配置，请重新选择！");
				}
		}
		channelfloorService.saveChannelFloor(channelfloor);
		logUtils.logModify("楼层修改","修改成功", +channelfloor.getId());
		String backUrl = request.getParameter("backUrl");
		return JsonRespWrapper.success(msg, backUrl);
	}

	@RequestMapping(value = "preEdit")
	public String preEdit(@RequestParam(value = "id", required = true) Long id, Model model) {
		channelfloorService.preEdit(id, model);
		return "/websys/channel/floor/floor-add";
	}

	/**
	 * 删除楼层
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Object deleteItem(@RequestParam Long id, Model model) {
		
		channelFloorDao.delete(id);
		logUtils.logModify("楼层删除","修改成功", +id);
		return JsonRespWrapper.success("删除成功！", "/websys/channel/floor/list");
	}
	
}
