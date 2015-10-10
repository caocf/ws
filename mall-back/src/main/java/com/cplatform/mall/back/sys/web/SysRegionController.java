package com.cplatform.mall.back.sys.web;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cplatform.dbhelp.page.Page;
import com.cplatform.mall.back.sys.entity.SysRegion;
import com.cplatform.mall.back.sys.service.SysRegionService;
import com.cplatform.mall.back.utils.JsonRespWrapper;
import com.cplatform.mall.back.utils.LogUtils;
import com.google.common.collect.Maps;

/**
 * 行政区域控制类 Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-5-27 下午8:03:25
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: LiuDong@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
@Controller
@RequestMapping("/sys/region")
public class SysRegionController {

	private final String MODULE_NAME = "区域管理";

	@Autowired
	private SysRegionService regionService;

	@Autowired
	private LogUtils logUtils;

	/**
	 * 获取页面显示数据
	 * 
	 * @param sysRegon
	 * @param page
	 * @param regionLevel
	 * @param parentRegion
	 * @param request
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/list")
	public String regionList(@ModelAttribute("region") SysRegion sysRegon,
	        @RequestParam(value = "page", required = false, defaultValue = "1") int page,
	        @RequestParam(value = "regionLevel", required = false, defaultValue = "0") Long regionLevel,
	        @RequestParam(value = "parentRegion", required = false, defaultValue = "0") String parentRegion, HttpServletRequest request, Model model)
	        throws SQLException {

		sysRegon.setParentRegion(parentRegion);
		sysRegon.setRegionLevel(regionLevel);
		Page<SysRegion> pageRegon = regionService.findRegion(sysRegon, page, Page.DEFAULT_PAGESIZE);
		model.addAttribute("pageData", pageRegon);
		model.addAttribute("regionLevel", regionLevel);
		model.addAttribute("parentRegion", parentRegion);
		// if (parentRegion != 0l) {
		List<SysRegion> regonList = this.regionService.findByRegionLevel(0L);
		model.addAttribute("regionList", regonList);
		// }
		return "sys/region/region-list";
	}

	/**
	 * 跳转到添加区域页面
	 * 
	 * @param regionLevel
	 * @param parentRegion
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/regionAdd", method = RequestMethod.GET)
	public String regionAdd(@RequestParam(value = "regionLevel", required = false, defaultValue = "0") Long regionLevel,
	        @RequestParam(value = "parentRegion", required = false, defaultValue = "0") String parentRegion, Model model) throws SQLException {
		if (!"0".equals(parentRegion)) {
			SysRegion regon = this.regionService.findByRegionCode(parentRegion);
			model.addAttribute("regionName", regon.getRegionName());
		} else {
			model.addAttribute("regionName", "空");
		}
		SysRegion region = new SysRegion();
		region.setParentRegion(parentRegion);
		region.setRegionLevel(regionLevel);
		model.addAttribute("region", region);
		return "sys/region/region-add";
	}

	/**
	 * 添加区域信息
	 * 
	 * @param sysRegon
	 * @return
	 */
	@RequestMapping(value = "/regionAdd", method = RequestMethod.POST)
	@ResponseBody
	public Object regionSave(@ModelAttribute("region") SysRegion sysRegion) {

		logUtils.logAdd("添加区域信息！", "添加成功"+sysRegion.getId());
		this.regionService.addOrUpdate(sysRegion);

		// return "regon/list";
		return JsonRespWrapper.success("添加成功", "/sys/region/list.do?parentRegion=" + sysRegion.getParentRegion());
	}

	/**
	 * 跳转到编辑区域页面
	 * 
	 * @param id
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/regionEdit", method = RequestMethod.GET)
	public String regionEdit(@RequestParam(value = "id") Long id, Model model) throws SQLException {
		SysRegion region = this.regionService.findById(id);
		if (!"0".equals(region.getParentRegion())) {
			SysRegion regon1 = this.regionService.findByRegionCode(region.getParentRegion());
			model.addAttribute("regionName", regon1.getRegionName());
		} else {
			model.addAttribute("regionName", "空");
		}
		model.addAttribute("region", region);
		return "sys/region/region-edit";
	}

	/**
	 * 修改区域信息
	 * 
	 * @param sysRegon
	 * @return
	 */
	@RequestMapping(value = "/regionEdit", method = RequestMethod.POST)
	@ResponseBody
	public Object regionUpdate(@ModelAttribute("region") SysRegion sysRegion) {
		logUtils.logModify("修改区域信息！", "添加成功"+sysRegion.getId());
		this.regionService.addOrUpdate(sysRegion);
		// return "regon/list";
		return JsonRespWrapper.success("修改成功", "/sys/region/list.do?parentRegion=" + sysRegion.getParentRegion());
	}

	/**
	 * 删除区域信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/regionDel")
	@ResponseBody
	public Object regionDel(@RequestParam(value = "id") Long id) {
		SysRegion sysRegion = this.regionService.findById(id);
		logUtils.logModify("删除区域信息！", "删除成功！"+sysRegion.getId());
		List<SysRegion> regonList = this.regionService.findByParentRegion(sysRegion.getRegionCode());
		if (regonList != null && regonList.size() > 0) {
			return JsonRespWrapper.successAlert("请先删除子区域，然后在删除该区域");
		} else {
			this.regionService.del(id);
		}

		// return "regon/list";
		return JsonRespWrapper.success("删除成功", "/sys/region/list");
	}

	// -----------common regon select ------------------
	@RequestMapping(value = "selectRegion")
	@ResponseBody
	public Object showSelectRegion(@RequestParam(required = true, defaultValue = "0") String pid,
	        @RequestParam(required = true, defaultValue = "region") String regionType,
	        @RequestParam(required = true, defaultValue = "1") Integer level, Model model) throws SQLException {
		HashMap<String, Object> map = Maps.newHashMap();
		// 所有子区域
		List<SysRegion> regionList = null;
		if ("region".equals(regionType)) {// 行政区域
			regionList = regionService.findRegions(pid, level);
		} else if ("area".equals(regionType)) {// 省市2及
			regionList = regionService.findAreaByParentRegion(pid);
		}

		// 根据pid获得现实的区域
		// List<SysRegion> ret = new ArrayList<SysRegion>();
		// if ("0".equals(pid)) {
		// ret.add(SysRegion.CHINA_REGION);
		// } else {
		// ret.add(regionService.findByRegionCode(pid));
		// }
		// ret.addAll(regionList);
		map.put("regionList", regionList);
		// 获取当前区域
		// SysRegion pRegion = regionService.findByRegionCode(pid);
		// map.put("pRegion", pRegion);
		return map;
	}
}
