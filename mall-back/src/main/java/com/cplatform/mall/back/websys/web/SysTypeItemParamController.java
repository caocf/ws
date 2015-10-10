package com.cplatform.mall.back.websys.web;

import java.sql.SQLException;

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

import com.cplatform.dbhelp.page.Page;
import com.cplatform.mall.back.item.web.ItemManageController;
import com.cplatform.mall.back.store.entity.Store;
import com.cplatform.mall.back.store.service.StoreService;
import com.cplatform.mall.back.sys.entity.SysType;
import com.cplatform.mall.back.utils.JsonRespWrapper;
import com.cplatform.mall.back.utils.LogUtils;
import com.cplatform.mall.back.websys.dao.SysTypeItemParamDao;
import com.cplatform.mall.back.websys.entity.SysTypeItemParam;
import com.cplatform.mall.back.websys.service.SysTypeItemParamService;

/**
 * 商品分类参数管理控制器. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2013-5-27 上午09:53:52
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author zhaowei@c-platform.com
 * @version 1.0.0
 */
@Controller
@RequestMapping(value = "/websys/typeitemparam")
public class SysTypeItemParamController {

	@Autowired
	private LogUtils logUtils;

	private static final Logger log = Logger.getLogger(ItemManageController.class);

	@Autowired
	private SysTypeItemParamDao sysTypeItemParamDao;

	@Autowired
	private SysTypeItemParamService sysTypeItemParamService;

	@Autowired
	StoreService storeService;

	/**
	 * 商品分类参数查询
	 * 
	 * @param param
	 *            查询条件
	 * @param page
	 *            当前页
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/query")
	public String list(SysTypeItemParam param, @RequestParam(value = "page", required = false, defaultValue = "1") int page, Model model)
	        throws SQLException {
		Page<SysTypeItemParam> paramPage = sysTypeItemParamService.findSysTypeItemParam(param, page);
		model.addAttribute("param", param);
		model.addAttribute("sysTypeItemParam", paramPage);
		return "websys/typeitemparam/type_param_list";
	}

	/**
	 * 跳转添加参数
	 * 
	 * @param id
	 *            对应分类ID
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(@RequestParam(required = false) Long id, @RequestParam(required = false) String typeName, Model model) {
		SysTypeItemParam param = new SysTypeItemParam();
		SysType sysType = new SysType();
		param.setTypeId(id);
		sysType.setName(typeName);
		param.setSysType(sysType);
		param.setParamType(1L);
		param.setRequiredFlag(0L);
		param.setSearchFlag(0L);
		model.addAttribute("method", "add");
		model.addAttribute("sysTypeItemParam", param);
		model.addAttribute("paramTypeMap", SysTypeItemParam.getParamTypeMap());
		model.addAttribute("requiredFlagMap", SysTypeItemParam.getRequiredFlagMap());
		model.addAttribute("searchFlagMap", SysTypeItemParam.getSearchFlagMap());
		return "websys/typeitemparam/type_param_add";
	}

	/**
	 * 添加参数
	 * 
	 * @param param
	 *            参数实体类
	 * @param request
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Object createPost(@ModelAttribute("sysTypeItemParam") SysTypeItemParam param, HttpServletRequest request, BindingResult result) {

		if (null == param.getTypeId()) {
			return JsonRespWrapper.successAlert("请选择所属分类！");
		}
		try {
			String[] paramKeys = request.getParameterValues("paramKey");
			String[] paramValues = request.getParameterValues("paramValue");
			if (paramKeys.length > 0) {
				for (int i = 0; i < paramKeys.length; i++) {
					SysTypeItemParam itemParam = new SysTypeItemParam();
					itemParam.setTypeId(param.getTypeId());
					itemParam.setParamType(param.getParamType());
					itemParam.setRequiredFlag(param.getRequiredFlag());
					itemParam.setSearchFlag(param.getSearchFlag());
					itemParam.setParamKey(paramKeys[i]);
					itemParam.setParamValue(paramValues[i]);
					sysTypeItemParamDao.save(itemParam);
					logUtils.logAdd("商品分类添加","添加成功，分类编号："+itemParam.getId());
				}
			}
			return JsonRespWrapper.success("添加成功", "/websys/typeitemparam/query");
		}
		catch (Exception ex) {
			logUtils.logAdd("商品分类添加","添加失败，分类编号："+param.getId());
			log.error(ex.getMessage());
			// 未知的错误消息，一般是exception catch后通知用户
			return JsonRespWrapper.error(ex.getMessage());
		}
	}

	/**
	 * 跳转编辑参数
	 * 
	 * @param id
	 *            待编辑参数
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(@RequestParam(required = false) Long id, Model model) {
		SysTypeItemParam param = sysTypeItemParamDao.findOne(id);
		model.addAttribute("method", "edit");
		model.addAttribute("sysTypeItemParam", param);
		model.addAttribute("paramTypeMap", SysTypeItemParam.getParamTypeMap());
		model.addAttribute("requiredFlagMap", SysTypeItemParam.getRequiredFlagMap());
		model.addAttribute("searchFlagMap", SysTypeItemParam.getSearchFlagMap());
		if (param.getShopId() != null) {
			Store store = storeService.findStoreById(param.getShopId());
			param.setStoreName(store.getName());
			model.addAttribute("method", "shopEdit");
			return "websys/typeitemparam/shop_type_param_add";
		} else {
			return "websys/typeitemparam/type_param_edit";
		}
	}

	/**
	 * 编辑参数
	 * 
	 * @param param
	 *            参数实体类
	 * @param request
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public Object updatePost(@ModelAttribute("sysTypeItemParam") SysTypeItemParam param, HttpServletRequest request, BindingResult result) {
		SysTypeItemParam oldparam = sysTypeItemParamDao.findOne(param.getId());
		try {
			
			oldparam.setTypeId(param.getTypeId());
			oldparam.setParamKey(param.getParamKey());
			oldparam.setParamValue(param.getParamValue());

			// 执行业务逻辑
			param = sysTypeItemParamDao.save(oldparam);

			// 提示并跳转
			// return JsonRespWrapper.success("修改成功",
			// "/websys/typeitemparam/query");

			// 返回到来源页面
			logUtils.logModify("商品分类修改","商品分类编号："+oldparam.getId());
			String backUrl = request.getParameter("backUrl");
			return JsonRespWrapper.success("修改成功", backUrl);
			

		}
		catch (Exception ex) {
			logUtils.logModify("商品分类修改","商品分类编号："+oldparam.getId());
			log.error(ex.getMessage());
			// 未知的错误消息，一般是exception catch后通知用户
			return JsonRespWrapper.error(ex.getMessage());
		}
	}

	/**
	 * 删除参数
	 * 
	 * @param id
	 *            待删除参数ID
	 * @return
	 */
	@RequestMapping(value = "delete/{id}")
	@ResponseBody
	public Object deleteParam(@PathVariable Long id) {

		sysTypeItemParamDao.delete(id);
		logUtils.logDelete("商品分类删除","商品分类编号："+id);
		return JsonRespWrapper.success("删除成功！", "/websys/typeitemparam/query");
	}

	// 商户自定义分类

	@RequestMapping(value = "/shopAdd", method = RequestMethod.GET)
	public String shopAdd(@RequestParam(required = false) Long id, Model model) {
		SysTypeItemParam param = new SysTypeItemParam();
		model.addAttribute("method", "shopAdd");
		model.addAttribute("sysTypeItemParam", param);
		return "websys/typeitemparam/shop_type_param_add";
	}

	// 商户分类参数列表
	@RequestMapping(value = "/getTypeParams")
	public String getTypeParams(Long typeId, @RequestParam(required = false) Long shopId, Model model) throws SQLException {
		model.addAttribute("typeItemParamList", sysTypeItemParamService.getShopTypeItemParam(shopId, typeId));
		return "websys/typeitemparam/inc/type_list";
	}

	/**
	 * 添加参数
	 * 
	 * @param param
	 *            参数实体类
	 * @param request
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/shopAdd", method = RequestMethod.POST)
	@ResponseBody
	public Object shopAddAct(@ModelAttribute("sysTypeItemParam") SysTypeItemParam typeItemParam, String[] paramKey, String[] paramValue,
	        BindingResult result) {

		if (null == typeItemParam.getTypeId()) {
			return JsonRespWrapper.successAlert("请选择所属分类！");
		}
		try {
			// 执行业务逻辑
		
			sysTypeItemParamService.saveShopParams(typeItemParam, paramKey, paramValue);
			logUtils.logAdd("商户分类添加", "操作成功!,编号："+typeItemParam.getId());
		
			// 提示并跳转
			return JsonRespWrapper.success("操作成功!", "/websys/typeitemparam/query");

		}
		catch (Exception ex) {
			logUtils.logAdd("商户分类添加","操作失败,编号：", +typeItemParam.getId());
			log.error(ex.getMessage());
			// 未知的错误消息，一般是exception catch后通知用户
			return JsonRespWrapper.error(ex.getMessage());
		}
	}

}
