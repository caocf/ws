package com.cplatform.mall.back.store.web;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import jxl.common.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cplatform.dbhelp.page.Page;
import com.cplatform.mall.back.model.SessionUser;
import com.cplatform.mall.back.model.SyncGYResponseBean;
import com.cplatform.mall.back.store.entity.Store;
import com.cplatform.mall.back.store.service.StoreService;
import com.cplatform.mall.back.sys.entity.SysRegion;
import com.cplatform.mall.back.sys.service.SysRegionService;
import com.cplatform.mall.back.utils.JsonRespWrapper;
import com.cplatform.mall.back.utils.LogUtils;
import com.cplatform.mall.back.utils.SyncInterface;

/**
 * 商品协议管理 Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-7-9 上午10:52:51
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: LiuDong@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
@Controller
@RequestMapping(value = "/store/treaty")
public class TreatyController {
	private static final Logger log = Logger.getLogger(TreatyController.class);
	@Autowired
	private LogUtils logUtils;

	@Autowired
	private StoreService storeService;

	@Autowired
	private SysRegionService sysRegonService;

	@Autowired
	private SyncInterface syncInterface;

	/**
	 * 商户信息列表(再用)
	 * 
	 * @param store
	 * @param page
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/list")
	public String storeList(Store store, @RequestParam(value = "page", required = false, defaultValue = "1") int page, Model model)
	        throws SQLException {
		// store.setShopClass(shopClass);
		store.setSort(1L);
		store.setIsValid(1L);
		Page<Store> storeList = storeService.listStore(store, page, Page.DEFAULT_PAGESIZE, "", "");
		model.addAttribute("pageData", storeList);
		model.addAttribute("storeClassMap", Store.shopClassMap);
		model.addAttribute("isValidMap", Store.isValidMap);
		model.addAttribute("syncGyFlagMap", Store.syncGyFlagMap);
		model.addAttribute("statusMap", Store.statusMap);
		model.addAttribute("syncGyFlagMsg", Store.syncGyFlagMsg);
		return "/store/treaty/store-list";
	}

	@RequestMapping(value = "/sync")
	@ResponseBody
	public Object sync(@RequestParam(value = "ids") String ids, Model model, HttpServletRequest request) {
		String msg = "操作完成。";
		String message = "";
		SyncGYResponseBean bean = null;
		String[] idarray = ids.split(",");
		if (idarray != null) {
			// 获取当前的登录用户
			SessionUser sessionUser = (SessionUser) request.getSession().getAttribute(SessionUser.SESSION_USER_KEY);
			for (String id : idarray) {
				if ("".equals(id)) {
					continue;
				}
				Store vo = this.storeService.findStoreById(Long.valueOf(id));

				if (vo != null) {
					if (vo.getStatus() != 3L) {
						message = vo.getId() + "状态：审核未通过，暂不能同步。";
						msg += message;
						continue;
					}
					SysRegion region = this.sysRegonService.findByRegionCode(vo.getAreaCode());
					String oldAreaCode = vo.getAreaCode();
					if (region != null) {
						vo.setAreaCode(region.getRegionName().replaceAll("市", ""));
					}
					if (vo.getSyncGyFlag() == 0L) {
						bean = this.syncInterface.syncAddStore(vo);
					} else {
						bean = this.syncInterface.syncUpdateStore(vo);
					}
					vo.setAreaCode(oldAreaCode);
					if (bean != null) {
						if (bean.isSyncSuccess()) {
							vo.setSyncGyFlag(1L);
							vo.setMerchid(bean.getMerchid());
							this.storeService.saveStore(vo);
						}
						message = vo.getId() + bean.getMsg();
						msg += message;
					} else {
						message = vo.getId() + "同步失败。";
						msg += message;
					}
				}
				logUtils.logOther("商户协议管理", "同步清算系统：" + message, sessionUser.getId());
			}
		}
		// return JsonRespWrapper.success(msg, "/store/treaty/list");
		return JsonRespWrapper.successReload(msg);

	}

	@RequestMapping(value = "/syncQuery")
	@ResponseBody
	public Object syncQuery(@RequestParam(value = "ids") String ids, Model model, HttpServletRequest request) {
		String msg = "操作完成。";
		String message = "";
		SyncGYResponseBean bean = null;
		String[] idarray = ids.split(",");
		if (idarray != null) {
			// 获取当前的登录用户
			SessionUser sessionUser = (SessionUser) request.getSession().getAttribute(SessionUser.SESSION_USER_KEY);
			for (String id : idarray) {
				if ("".equals(id)) {
					continue;
				}
				Store vo = this.storeService.findStoreById(Long.valueOf(id));
				if (vo != null) {
					if (vo.getMerchid() == null) {
						message = vo.getId() + "该商户基本信息未同步成功，暂不能查询。";
						msg += message;
						continue;
					}
					if (vo.getStatus() != 3L) {
						message = vo.getId() + "状态：审核未通过，暂不能查询。";
						msg += message;
						continue;
					}
					bean = this.syncInterface.queryStoreStatus(vo);
					if (bean != null) {
						if (bean.isSyncSuccess()) {
							vo.setSyncGyFlag(bean.getBaseInfoStatus());
							this.storeService.saveStore(vo);
							message = vo.getId() + "状态：" + Store.syncGyFlagMap.get(vo.getSyncGyFlag()) + "。";
							msg += message;
						} else {
							message = vo.getId() + bean.getMsg();
							msg += message;
						}
					} else {
						message = vo.getId() + "查询失败。";
						msg += message;
					}
				}
				if(null == sessionUser){
					//没有登录用户，为“业务监控程序”调用 。
					log.info("业务监控程序查询清算系统:" + message);
				}else{
					logUtils.logOther("商户协议管理", "查询清算系统：" + message, sessionUser.getId());
				}
			}
		}
		// return JsonRespWrapper.success(msg, "/store/treaty/list");
		return JsonRespWrapper.successReload(msg);
	}
}
