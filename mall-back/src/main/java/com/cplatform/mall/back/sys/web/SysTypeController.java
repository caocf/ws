package com.cplatform.mall.back.sys.web;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
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
import com.cplatform.mall.back.store.entity.ShopTypeLink;
import com.cplatform.mall.back.store.service.ShopService;
import com.cplatform.mall.back.sys.dao.SysTypeDao;
import com.cplatform.mall.back.sys.entity.SysType;
import com.cplatform.mall.back.sys.service.SysTypeService;
import com.cplatform.mall.back.utils.JsonRespWrapper;
import com.cplatform.mall.back.utils.LogUtils;
import com.cplatform.mall.back.utils.SyncInterface;

/**
 * 分类管理控制器. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2013-5-25 下午03:36:34
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author zhaowei@c-platform.com
 * @version 1.0.0
 */
@Controller
@RequestMapping(value = "/sys/type")
public class SysTypeController {

	@Autowired
	private LogUtils logUtils;

	@Autowired
	private SysTypeDao sysTypeDao;

	@Autowired
	private SysTypeService sysTypeService;

	@Autowired
	private SyncInterface syncInterface;

	@Autowired
	private ShopService shopService;

	/**
	 * 商户分类默认跳转
	 * 
	 * @return
	 */
	@RequestMapping("store")
	public String indexStore() {
		return "sys/type/type_index_store";
	}

	private static final Logger log = Logger.getLogger(ItemManageController.class);

	/**
	 * 商品分类默认跳转
	 * 
	 * @return
	 */
	@RequestMapping("goods")
	public String indexGoods() {
		return "sys/type/type_index_goods";
	}

	/**
	 * 商户分类框架容器
	 * 
	 * @return
	 */
	@RequestMapping("type_container_store")
	public String containerStore() {
		return "sys/type/type_container_store";
	}

	/**
	 * 商品分类框架容器
	 * 
	 * @return
	 */
	@RequestMapping("type_container_goods")
	public String containerGoods() {
		return "sys/type/type_container_goods";
	}

	/**
	 * 商户分类管理菜单数据获取
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/typeTreeStore")
	@ResponseBody
	public Object getStoreTree(HttpServletRequest request) {
		List<SysType> typeList = new ArrayList<SysType>();
		typeList = sysTypeDao.findStoreSysType();
		List<Map<String, String>> ret = new ArrayList<Map<String, String>>();
		for (SysType st : typeList) {
			Map map = new HashMap();
			map.put("id", "" + st.getId()); // 转为string类型避免js报错
			map.put("pid", "" + st.getpId());
			map.put("name", st.getName());
			map.put("url", "sys/type/jumpStore/" + st.getId() + "/0");// 节点菜单链接
			ret.add(map);
		}
		return ret;
	}

	/**
	 * 商品分类管理菜单数据获取
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/typeTreeGoods")
	@ResponseBody
	public Object getGoodsTree(HttpServletRequest request) {
		List<SysType> typeList = new ArrayList<SysType>();
		typeList = sysTypeDao.findGoodsSysType();
		List<Map<String, String>> ret = new ArrayList<Map<String, String>>();
		for (SysType st : typeList) {
			Map map = new HashMap();
			map.put("id", "" + st.getId()); // 转为string类型避免js报错
			map.put("pid", "" + st.getpId());
			map.put("name", st.getName());
			map.put("url", "sys/type/jumpGoods/" + st.getId());// 节点菜单链接
			ret.add(map);
		}
		return ret;
	}

	/**
	 * 商户分类负责点击分类管理菜单叶子节点的跳转
	 * 
	 * @param jumpId
	 *            跳转ID
	 * @param name
	 *            查询条件-类别名称
	 * @param page
	 *            当前页
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "jumpStore/{jumpId}/{refresh}")
	public String jumpStore(@PathVariable Long jumpId, @PathVariable Long refresh, @RequestParam(required = false) String name,
	        @RequestParam(value = "page", required = false, defaultValue = "1") int page, Model model) throws SQLException {
		if (StringUtils.isNotEmpty(name)) {
			refresh = 0L;
		}
		SysType st = new SysType();
		// 这里主要防止初始化页面jumpId为0时查询出错
		if (0 != jumpId) {
			st = sysTypeDao.findOne(jumpId);
		} else {// 初始化页面时默认赋值
			st.setType(1L);
			st.setId(0L);
			st.setName("商户");
		}
		Page<SysType> sysTypePage = sysTypeService.findSysTypeStore(st, name, page);
		model.addAttribute("refresh", refresh);
		model.addAttribute("pId", st.getId());
		model.addAttribute("type", st.getType());
		model.addAttribute("name", st.getName());
		model.addAttribute("sysType", sysTypePage);
		model.addAttribute("typeMap", SysType.getTypeMap());
		return "sys/type/type_list_store";
	}

	/**
	 * 商品分类负责点击分类管理菜单叶子节点的跳转
	 * 
	 * @param jumpId
	 *            跳转ID
	 * @param name
	 *            查询条件-类别名称
	 * @param page
	 *            当前页
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "jumpGoods/{jumpId}/{refresh}")
	public String jumpGoods(@PathVariable Long jumpId, @PathVariable Long refresh, @RequestParam(required = false) String name,
	        @RequestParam(required = false) Long isValid, @RequestParam(value = "page", required = false, defaultValue = "1") int page, Model model)
	        throws SQLException {
		if (StringUtils.isNotEmpty(name)) {
			refresh = 0L;
		}
		SysType st = new SysType();
		// 这里主要防止初始化页面jumpId为0时查询出错
		if (0 != jumpId) {
			st = sysTypeDao.findOne(jumpId);
		} else {// 初始化页面时默认赋值
			st.setType(2L);
			st.setId(0L);
			st.setName("商品");
		}
		Page<SysType> sysTypePage = sysTypeService.findSysTypeGoods(st, name, isValid, page);
		model.addAttribute("refresh", refresh);
		model.addAttribute("pId", st.getId());
		model.addAttribute("type", st.getType());
		model.addAttribute("name", st.getName());
		model.addAttribute("sysType", sysTypePage);
		model.addAttribute("typeMap", SysType.getTypeMap());
		model.addAttribute("isValidMap", SysType.getIsValidMap());
		return "sys/type/type_list_goods";
	}

	/**
	 * 跳转添加类别
	 * 
	 * @param pId
	 *            父节点ID
	 * @param type
	 *            类型
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(@RequestParam(required = false) Long pId, @RequestParam(required = false) Long type, Model model) {
		SysType sysType = new SysType();
		sysType.setpId(pId);
		sysType.setType(type);
		if (pId != 0 && null != pId) {
			SysType pst = sysTypeDao.findOne(pId);
			model.addAttribute("pName", pst.getName());
		} else if (pId == 0) {
			model.addAttribute("pName", pId);
		} else {

		}
		model.addAttribute("method", "add");
		model.addAttribute("sysType", sysType);
		model.addAttribute("isValidMap", SysType.getIsValidMap());
		return "sys/type/type_add";
	}

	/**
	 * 添加类别
	 * 
	 * @param sysType
	 *            分类实体类
	 * @param request
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Object createPost(@ModelAttribute("sysType") SysType sysType, HttpServletRequest request, BindingResult result) {
		try {
			// 执行业务逻辑
			sysType = sysTypeService.add(sysType);
			logUtils.logAdd("添加类别","添加成功！", sysType.getId());
			// 提示并跳转
			if (1L == sysType.getType()) {
				return JsonRespWrapper.success("添加成功", "/sys/type/jumpStore/" + sysType.getpId() + "/1");
			} else if (2L == sysType.getType()) {
				return JsonRespWrapper.success("添加成功", "/sys/type/jumpGoods/" + sysType.getpId() + "/1");
			} else {
				return null;
			}
		}
		catch (Exception ex) {
			logUtils.logAdd("添加类别","添加成功！", sysType.getId());
			log.error(ex.getMessage());
			// 未知的错误消息，一般是exception catch后通知用户
			return JsonRespWrapper.error(ex.getMessage());
		}
	}

	/**
	 * 跳转编辑类别
	 * 
	 * @param id
	 *            待编辑类别ID
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(@RequestParam(required = false) Long id, Model model) {
		SysType st = sysTypeDao.findOne(id);
		SysType pst = sysTypeDao.findOne(st.getpId());
		if (null == pst) {
			model.addAttribute("pName", "无");
		} else {
			model.addAttribute("pName", pst.getName());
		}
		model.addAttribute("method", "edit");
		model.addAttribute("sysType", st);
		model.addAttribute("isValidMap", SysType.getIsValidMap());
		return "sys/type/type_add";
	}

	/**
	 * 编辑类别
	 * 
	 * @param sysType
	 *            分类实体类
	 * @param request
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public Object updatePost(@ModelAttribute("sysType") SysType sysType, HttpServletRequest request, BindingResult result) {
		try {
			// 执行业务逻辑
			sysType = sysTypeService.add(sysType);

			// //@a

			// 提示并跳转
			// if (1L == sysType.getType()) {
			// return JsonRespWrapper.success("修改成功", "/sys/type/jumpStore/" +
			// sysType.getpId() + "/1");
			// } else if (2L == sysType.getType()) {
			// return JsonRespWrapper.success("修改成功", "/sys/type/jumpGoods/" +
			// sysType.getpId() + "/1");
			// } else {
			// return null;
			// }
			logUtils.logModify("商品分类修改", "分类编号："+sysType.getId());
			String backUrl = request.getParameter("backUrl");
			return JsonRespWrapper.success("修改成功", backUrl);
		}
		catch (Exception ex) {
			logUtils.logModify("商品分类修改", "分类编号："+sysType.getId());
			log.error(ex.getMessage());
			// 未知的错误消息，一般是exception catch后通知用户
			return JsonRespWrapper.error(ex.getMessage());
		}
	}

	/**
	 * 删除类别
	 * 
	 * @param id
	 *            待删除类别
	 * @return
	 */
	@RequestMapping(value = "delete/{id}")
	@ResponseBody
	public Object deleteType(@PathVariable Long id) {
		SysType sysType = sysTypeDao.findOne(id);
		List<SysType> typeList = sysTypeDao.findSysTypeAndPid(sysType.getType(), sysType.getId());
		if (1L == sysType.getType() && typeList.size() > 0) {
			return JsonRespWrapper.successAlert("删除失败！该商户分类下有子商户类别");
		} else if (2L == sysType.getType() && typeList.size() > 0) {
			return JsonRespWrapper.successAlert("删除失败！该商品分类下有子商品类别");
		} else {
		}
		if (1L == sysType.getType()) {
			List<ShopTypeLink> shopTypeLinkList = shopService.findShopTypeLinkListByTypeId(id);
			if (0 < shopTypeLinkList.size()) {
				return JsonRespWrapper.successAlert("删除失败！该类别已与门店关联");
			}
		}
		logUtils.logDelete("商品分类删除", "分类编号："+sysType.getId());
		sysTypeService.delete(sysType);
		// 按类型跳转
		if (1L == sysType.getType()) {
			return JsonRespWrapper.success("删除成功", "/sys/type/jumpStore/" + sysType.getpId() + "/1");
		} else if (2L == sysType.getType()) {
			return JsonRespWrapper.success("删除成功", "/sys/type/jumpGoods/" + sysType.getpId() + "/1");
		} else {
			return sysType;
		}

	}

	/**
	 * 通用类型选择弹出框调用
	 * 
	 * @param type
	 *            类型
	 * @param selected
	 *            选择项
	 * @param isone
	 *            是否唯一
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "selectType")
	public String selectType(@RequestParam Long type, @RequestParam(required = false, defaultValue = "") String selected,
	        @RequestParam(required = false, defaultValue = "false") boolean isone, Model model) {

		List<SysType> typeList = sysTypeDao.findSysType(type);

		model.addAttribute("typeList", typeList);
		model.addAttribute("isone", isone);
		model.addAttribute("selected", selected);
		return "sys/type/type_select";
	}

	@RequestMapping(value = "selectItemType")
	@ResponseBody
	public Object selectType(@RequestParam Long type, @RequestParam(required = false, defaultValue = "0") Long id, Model model) throws SQLException {
		List<SysType> typeList = sysTypeService.getTreeType(type, id);
		return typeList;
	}

	@RequestMapping(value = "/ModifyportalIsNo")
	@ResponseBody
	public Object ModifyportalIsNo(@RequestParam(value = "ids") String ids, Model model) {
		SysType sysType = null;
		Long pId = 0L;
		String[] idarray = ids.split(",");
		if (idarray != null) {
			for (String id : idarray) {
				if ("".equals(id)) {
					continue;
				}
				sysType = this.sysTypeService.findOne(Long.valueOf(id));
				pId = sysType.getpId();
				if (sysType != null) {
					if (sysType.getIsValid() != null) {
						sysType.setIsValid(Long.valueOf(0));
						sysTypeService.add(sysType);
					}

				}
			}
		}
		return JsonRespWrapper.success("修改成功", "/sys/type/jumpGoods/" + sysType.getpId() + "/1");

	}

	@RequestMapping(value = "/Modifyportal")
	@ResponseBody
	public Object Modifyportal(@RequestParam(value = "ids") String ids, Model model) {
		SysType sysType = null;
		Long pId = 0L;
		String[] idarray = ids.split(",");
		if (idarray != null) {
			for (String id : idarray) {
				if ("".equals(id)) {
					continue;
				}
				sysType = this.sysTypeService.findOne(Long.valueOf(id));
				pId = sysType.getpId();
				if (sysType != null) {
					if (sysType.getIsValid() != null) {
						sysType.setIsValid(Long.valueOf(1));
						logUtils.logModify("商品分类修改", "分类编号："+sysType.getId());
						sysTypeService.add(sysType);
					}

				}
			}
		}
		return JsonRespWrapper.success("修改成功", "/sys/type/jumpGoods/" + sysType.getpId() + "/1");
	}

	/**
	 * 同步高阳
	 * 
	 * @param id
	 * @return
	 */
	// @RequestMapping(value = "syncGy/{id}")
	// @ResponseBody
	// public Object syncGy(@PathVariable Long id) {
	// SysType sysType = sysTypeDao.findOne(id);
	// boolean flag = syncInterface.syncGoodsType(sysType);
	// // 按类型跳转
	// if (flag) {
	// return JsonRespWrapper.successAlert("同步成功");
	// } else {
	// return JsonRespWrapper.successAlert("同步失败");
	// }
	// }
	// /**
	// * 同步高阳
	// *
	// * @param id
	// * @return
	// */
	// @RequestMapping(value = "syncGy/{id}")
	// @ResponseBody
	// public Object syncGy(@PathVariable Long id) {
	// SysType sysType = sysTypeDao.findOne(id);
	// // boolean flag = syncInterface.syncGoodsType(sysType);
	// SyncGYResponseBean bean = null;
	// if (sysType.getSyncGyFlag() == 0L) {
	// bean = syncInterface.syncAddGoodsType(sysType);
	// if (bean != null) {
	// // if("0".equals(bean.getCode())){
	// sysType.setSyncGyFlag(Long.valueOf(bean.getCode()));
	// this.sysTypeService.add(sysType);
	// // }
	// }
	// } else {
	// bean = syncInterface.syncUpdateGoodsType(sysType);
	// }
	// if (bean != null) {
	// return JsonRespWrapper.successAlert(bean.getMsg());
	// } else {
	// return JsonRespWrapper.successAlert("同步失败");
	// }
	// }

}
