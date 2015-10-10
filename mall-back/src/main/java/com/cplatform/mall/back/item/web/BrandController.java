package com.cplatform.mall.back.item.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.cplatform.mall.back.item.dao.BrandDao;
import com.cplatform.mall.back.item.entity.Brand;
import com.cplatform.mall.back.item.service.BrandService;
import com.cplatform.mall.back.utils.JsonRespWrapper;
import com.cplatform.mall.back.utils.LogUtils;

/**
 * 品牌管理 Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-6-3 上午8:49:28
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: baibo@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
@Controller
@RequestMapping(value = "item/brand")
public class BrandController {

	@Autowired
	private BrandDao brandDao;

	@Autowired
	private LogUtils logUtils;
	
	private static final Logger log = Logger.getLogger(ItemManageController.class);

	@Autowired
	private BrandService brandService;

	@RequestMapping(value = "/list")
	public String itemList(Brand brand, @RequestParam(value = "page", defaultValue = "1", required = false) int page, HttpSession session, Model model)
	        throws SQLException {

		Page<Brand> brandPage = brandService.listBrand(brand, page, Page.DEFAULT_PAGESIZE);
		model.addAttribute("pageData", brandPage);
		return "/item/brand/brand-list";
	}

	/**
	 * 进入品牌增加页面
	 * 
	 * @param model
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	@RequestMapping(value = "/addbrand", method = RequestMethod.GET)
	public String toAddBrand(Model model) throws IOException, Exception {
		Brand brand = new Brand();
		model.addAttribute("brand", brand);
		// 跳转到列表页面
		return "/item/brand/brand-add";
	}

	/**
	 * 完成品牌增加
	 * 
	 * @param model
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	@RequestMapping(value = "/addbrand", method = RequestMethod.POST)
	@ResponseBody
	public Object addStore(MultipartFile uploadfile, @ModelAttribute("brand") Brand brand, Model model, HttpServletRequest request,
	        BindingResult result) throws IOException, Exception {
		try {
			brandService.saveUpdateBrand(brand, uploadfile);
			logUtils.logAdd("品牌添加", "操作成功");
			// 跳转到列表页面
			return JsonRespWrapper.success("操作成功", "/item/brand/list");
		}
		catch (Exception ex) {
			// 未知的错误消息，一般是exception catch后通知用户
			logUtils.logAdd("品牌添加", "操作失败");
			log.error(ex.getMessage());
			return JsonRespWrapper.error(ex.getMessage());
		}
	}

	/**
	 * 做品牌逻辑删除操作
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delete/{id}")
	@ResponseBody
	public Object delete(@PathVariable Long id, Model model) {
		try {
			brandService.deleteBrand(id);
			logUtils.logAdd("品牌删除", "操作成功，品牌编号:"+id);
			return JsonRespWrapper.successRefreshBack("操作成功");
		}
		catch (Exception ex) {
			logUtils.logAdd("品牌删除", "操作失败，品牌编号:"+id);
			// 未知的错误消息，一般是exception catch后通知用户
			log.error(ex.getMessage());
			return JsonRespWrapper.error(ex.getMessage());
		}
	}

	/**
	 * 进入品牌修改页面
	 * 
	 * @param id
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable Long id, Model model) throws SQLException {
		Brand brand = brandDao.findOne(id);
		model.addAttribute("brand", brand);
		return "/item/brand/brand-edit";
	}

	/**
	 * 完成品牌修改页面
	 * 
	 * @param id
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Object doEdit(MultipartFile uploadfile, @ModelAttribute("brand") Brand brand, Model model, HttpServletRequest request, BindingResult result)
	        throws IOException, Exception {
		try {
			brandService.saveUpdateBrand(brand, uploadfile);
			// 跳转到列表页面
			// return JsonRespWrapper.successRefreshBack("操作成功");
			// 返回到来源页面
			String backUrl = request.getParameter("backUrl");
			logUtils.logAdd("品牌修改", "操作成功，品牌编号:"+brand.getId());
			return JsonRespWrapper.success("操作成功", backUrl);
		}
		catch (Exception ex) {
			logUtils.logAdd("品牌修改", "操作失败，品牌编号:"+brand.getId());
			// 未知的错误消息，一般是exception catch后通知用户
			log.error(ex.getMessage());
			return JsonRespWrapper.error(ex.getMessage());
		}
	}

	/**
	 * 品牌查看页面
	 * 
	 * @param id
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable Long id, Model model) throws SQLException {
		Brand brand = brandDao.findOne(id);
		model.addAttribute("brand", brand);
		return "/item/brand/brand-view";
	}

	/**
	 * 选择品牌
	 * 
	 * @param name
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/selectBrand")
	public String selectBrand(@RequestParam(required = false) String name, Model model) throws SQLException {
		model.addAttribute("brandList", brandService.listBrand(name));
		return "/item/brand/select-brand";
	}
}
