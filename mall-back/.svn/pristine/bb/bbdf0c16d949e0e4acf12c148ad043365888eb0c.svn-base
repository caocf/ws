package com.cplatform.mall.back.item.web;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.cplatform.dbhelp.page.Page;
import com.cplatform.mall.back.item.entity.HisunProductionLink;
import com.cplatform.mall.back.item.entity.HisunProductionSettle;
import com.cplatform.mall.back.item.service.HisunProductionService;
import com.cplatform.mall.back.item.service.ItemManageService;
import com.cplatform.mall.back.model.SessionUser;
import com.cplatform.mall.back.store.entity.Store;
import com.cplatform.mall.back.store.service.ShopService;
import com.cplatform.mall.back.store.service.StoreService;
import com.cplatform.mall.back.sys.entity.SysFee;
import com.cplatform.mall.back.utils.AppConfig;
import com.cplatform.mall.back.utils.JsonRespWrapper;
import com.cplatform.mall.back.utils.LogUtils;
import com.cplatform.mall.back.websys.service.BsFileService;
import com.cplatform.util2.TimeStamp;

/**
 * 商品协议管理控制器. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2013-5-28 上午11:20:06
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author zhaowei@c-platform.com
 * @version 1.0.0
 */
@Controller
@RequestMapping(value = "/item/hisunproduction")
public class HisunProductionController {

	private static Logger logger = Logger.getLogger(HisunProductionService.class);

	private static final Logger log = Logger.getLogger(ItemManageController.class);

	@Autowired
	private LogUtils logUtils;

	@Autowired
	private HisunProductionService hisunProductionService;

	@Autowired
	private StoreService storeService;

	@Autowired
	private ShopService shopService;

	@Autowired
	private ItemManageService itemManageService;

	@Autowired
	private BsFileService bsFileService;

	@Autowired
	private AppConfig config;

	/**
	 * 商品协议查询
	 * 
	 * @param settle
	 *            商品协议实体类
	 * @param page
	 *            当前页
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/settleList")
	public String settleList(@ModelAttribute("settle") HisunProductionSettle settle,
	        @RequestParam(value = "page", required = false, defaultValue = "1") int page, Model model) throws SQLException {
		Page<HisunProductionSettle> settlePage = hisunProductionService.findSettle(settle, page);
		List<HisunProductionSettle> settles = settlePage.getData();
		for (int i = 0; i < settles.size(); i++) {
			HisunProductionSettle st = settles.get(i);
			List<HisunProductionLink> links = hisunProductionService.findSettleItemLinks(st.getId());
			if (links.size() == 0) {
				st.setIsLink(0L);
			} else {
				st.setIsLink(1L);
			}
		}
		model.addAttribute("settlePage", settlePage);
		model.addAttribute("statusMap", HisunProductionSettle.getStatusMap());
		model.addAttribute("typeMap", HisunProductionSettle.getTypeMap());

		return "item/hisunproduction/settle_list";
	}

	/**
	 * 商品协议审核查询
	 * 
	 * @param settle
	 *            商品协议实体类
	 * @param page
	 *            当前页
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/settleAudit")
	public String settleAudit(@ModelAttribute("settle") HisunProductionSettle settle,
	        @RequestParam(value = "page", required = false, defaultValue = "1") int page, Model model) throws SQLException {
		Page<HisunProductionSettle> settlePage = hisunProductionService.findSettleAudit(settle, page);
		model.addAttribute("settlePage", settlePage);
		model.addAttribute("statusMap", HisunProductionSettle.getStatusMap());
		model.addAttribute("typeMap", HisunProductionSettle.getTypeMap());
		return "item/hisunproduction/settle_audit";
	}

	/**
	 * 跳转添加商品协议
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/settleAdd", method = RequestMethod.GET)
	public String settleAdd(HttpServletRequest request, @RequestParam(required = false) Long id, Model model) {
		SessionUser sessionUser = (SessionUser) request.getSession().getAttribute(SessionUser.SESSION_USER_KEY);
		HisunProductionSettle settle = new HisunProductionSettle();
		if (null != id) {
			Store ss = storeService.findStoreById(id);
			if (null != ss) {
				settle.setStoreId(ss.getId().toString());
				settle.setMerchid(ss.getMerchid());
				settle.setStoreName(ss.getName());
			}
		}
		model.addAttribute("method", "add");
		model.addAttribute("settle", settle);
		model.addAttribute("typeMap", HisunProductionSettle.getTypeMap());
		model.addAttribute("regionCode", sessionUser.getSysUnit().getAreaCode());
		return "item/hisunproduction/settle_add";
	}

	/**
	 * 添加商品协议
	 * 
	 * @param request
	 * @param uploadFile
	 * @param settle
	 *            商品协议实体类
	 * @return
	 */
	@RequestMapping(value = "/settleAdd", method = RequestMethod.POST)
	@ResponseBody
	public Object settleAddPost(HttpServletRequest request, MultipartFile uploadFile, @ModelAttribute("settle") HisunProductionSettle settle) {
		SessionUser sessionUser = (SessionUser) request.getSession().getAttribute(SessionUser.SESSION_USER_KEY);
		String[] storeIds = settle.getStoreId().split(",");
		settle.setStoreId(storeIds[0]);
		if (StringUtils.isNotEmpty(settle.getStoreId()) && StringUtils.isEmpty(settle.getMerchid())) {
			Store ss = storeService.findStoreById(Long.valueOf(settle.getStoreId()));
			settle.setMerchid(ss.getMerchid());
		}
		if (!settle.getVerifyexpdate().isEmpty()) {
			settle.setVerifyexpdate(settle.getVerifyexpdate().replace("-", ""));
		}
		settle.setProductionefftime(settle.getProductionefftime().replace("-", "").replace(" ", "").replace(":", ""));
		settle.setProductionexptime(settle.getProductionexptime().replace("-", "").replace(" ", "").replace(":", ""));
		if (!settle.getSettledate1().isEmpty()) {
			settle.setSettledate1(settle.getSettledate1().replace("-", ""));
		}
		if (!settle.getSettledate2().isEmpty()) {
			settle.setSettledate2(settle.getSettledate2().replace("-", ""));
		}
		if (!settle.getSettledate3().isEmpty()) {
			settle.setSettledate3(settle.getSettledate3().replace("-", ""));
		}
		if (StringUtils.isNotEmpty(settle.getContracteffdate())) {
			settle.setContracteffdate(settle.getContracteffdate().replace("-", ""));
		}
		if (StringUtils.isNotEmpty(settle.getContractexpdate())) {
			settle.setContractexpdate(settle.getContractexpdate().replace("-", ""));
		}

		settle.setCreateTime(TimeStamp.getTime(14));
		settle.setCreateUser(sessionUser.getId());
		settle.setStatus(HisunProductionSettle.STATUS_0);
		settle.setServiceid(config.getSyncGyServiceId());
		settle.setType(HisunProductionSettle.TYPE_0);
		if (null == settle.getCapitaltype1()) {
			settle.setCapitaltype1(0L);
		}
		settle.setSyncGyStatus1(HisunProductionSettle.SYNC_GY_STATUS_0);
		if (null == settle.getCapitaltype2()) {
			settle.setCapitaltype2(0L);
		}
		settle.setSyncGyStatus2(HisunProductionSettle.SYNC_GY_STATUS_0);
		if (null == settle.getCapitaltype3()) {
			settle.setCapitaltype3(0L);
		}
		settle.setSyncGyStatus3(HisunProductionSettle.SYNC_GY_STATUS_0);
		if (null == settle.getCapitaltype4()) {
			settle.setCapitaltype4(0L);
		}
		settle.setSyncGyStatus4(HisunProductionSettle.SYNC_GY_STATUS_0);
		try {
			// 执行业务逻辑
			settle.setProductionname("--");
			settle = hisunProductionService.saveSettle(settle, uploadFile, "add");
			logUtils.logAdd("商品协议管理", "id:" + settle.getId());
			return JsonRespWrapper.success("商品协议录入成功", "/item/hisunproduction/settleList");
		}
		catch (Exception ex) {
			log.error(ex.getMessage());
			logUtils.logOther("录入商品协议信息异常：", ex.toString());
			// 未知的错误消息，一般是exception catch后通知用户
			return JsonRespWrapper.error(ex.getMessage());
		}
	}

	/**
	 * 跳转编辑商品协议
	 * 
	 * @param id
	 *            待编辑商品协议ID
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/settleEdit", method = RequestMethod.GET)
	public String settleEdit(@RequestParam(required = false) Long id, HttpServletRequest request, Model model) throws SQLException {
		SessionUser sessionUser = (SessionUser) request.getSession().getAttribute(SessionUser.SESSION_USER_KEY);
		HisunProductionSettle settle = hisunProductionService.findSettle(id);

		model.addAttribute("method", "edit");
		model.addAttribute("settle", settle);
		model.addAttribute("typeMap", HisunProductionSettle.getTypeMap());
		model.addAttribute("regionCode", sessionUser.getSysUnit().getAreaCode());
		List<SysFee> feeList = this.itemManageService.getAllSysFeeList();
		model.addAttribute("feeList", feeList);

		if (StringUtils.isNotEmpty(settle.getStoreId())) {
			Store store = storeService.findStoreById(Long.parseLong(settle.getStoreId()));
			model.addAttribute("storeName", store.getName());
		}

		return "item/hisunproduction/settle_add";
	}

	/**
	 * 编辑商品协议
	 * 
	 * @param request
	 * @param uploadFile
	 * @param settle
	 *            商品协议实体类
	 * @return
	 */
	@RequestMapping(value = "/settleEdit", method = RequestMethod.POST)
	@ResponseBody
	public Object settleEditPost(HttpServletRequest request, MultipartFile uploadFile, @ModelAttribute("settle") HisunProductionSettle settle) {

		if (!settle.getVerifyexpdate().isEmpty()) {
			settle.setVerifyexpdate(settle.getVerifyexpdate().replace("-", ""));
		}
		settle.setProductionefftime(settle.getProductionefftime().replace("-", "").replace(" ", "").replace(":", ""));
		settle.setProductionexptime(settle.getProductionexptime().replace("-", "").replace(" ", "").replace(":", ""));
		if (!settle.getSettledate1().isEmpty()) {
			settle.setSettledate1(settle.getSettledate1().replace("-", ""));
		}
		if (!settle.getSettledate2().isEmpty()) {
			settle.setSettledate2(settle.getSettledate2().replace("-", ""));
		}
		if (!settle.getSettledate3().isEmpty()) {
			settle.setSettledate3(settle.getSettledate3().replace("-", ""));
		}
		if (StringUtils.isNotEmpty(settle.getContracteffdate())) {
			settle.setContracteffdate(settle.getContracteffdate().replace("-", ""));
		}
		if (StringUtils.isNotEmpty(settle.getContractexpdate())) {
			settle.setContractexpdate(settle.getContractexpdate().replace("-", ""));
		}

		settle.setStatus(HisunProductionSettle.STATUS_0);
		if (null == settle.getCapitaltype1()) {
			settle.setCapitaltype1(0L);
		}
		if (null == settle.getCapitaltype2()) {
			settle.setCapitaltype2(0L);
		}
		if (null == settle.getCapitaltype3()) {
			settle.setCapitaltype3(0L);
		}
		if (null == settle.getCapitaltype4()) {
			settle.setCapitaltype4(0L);
		}
		try {
			// 执行业务逻辑
			settle = hisunProductionService.saveSettle(settle, uploadFile, null);

			// 返回到来源页面
			String backUrl = request.getParameter("backUrl");
			logUtils.logModify("商品协议编辑", "id:" + settle.getId());
			return JsonRespWrapper.success("商品协议编辑成功", backUrl);
		}
		catch (Exception ex) {
			log.error(ex.getMessage());
			logUtils.logOther("编辑商品协议信息异常：", ex.toString());
			// 未知的错误消息，一般是exception catch后通知用户
			return JsonRespWrapper.error(ex.getMessage());
		}

	}

	/**
	 * 跳转关联商品
	 * 
	 * @param id
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/itemSettleLink", method = RequestMethod.GET)
	public String itemSettleLink(@RequestParam(required = false) Long id, Model model) throws SQLException {
		HisunProductionSettle settle = hisunProductionService.findOneSettle(id);
		model.addAttribute("settle", settle);
		List<HisunProductionLink> links = hisunProductionService.findSettleItemLinks(id);
		model.addAttribute("itemLinks", links);
		return "item/hisunproduction/settle_link";
	}

	/**
	 * 关联商品
	 * 
	 * @param request
	 * @param link
	 * @return
	 */
	@RequestMapping(value = "/deleteLink")
	@ResponseBody
	public Object itemSettleLinkPost(Long linkId) {
		// 校验该商品是否已经被关联
		hisunProductionService.deleteHisunLink(linkId);
		return JsonRespWrapper.successRefreshBack("操作成功");

	}

	/**
	 * 查看商品协议
	 * 
	 * @param id
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/settleView")
	public String settleView(@RequestParam(required = false) Long id, Model model) throws SQLException {
		HisunProductionSettle settle = hisunProductionService.findSettle(id);
		model.addAttribute("settle", settle);
		model.addAttribute("capitaltype1Map", HisunProductionSettle.getCapitaltype1Map());
		model.addAttribute("capitaltype2Map", HisunProductionSettle.getCapitaltype2Map());
		model.addAttribute("capitaltype3Map", HisunProductionSettle.getCapitaltype3Map());
		model.addAttribute("capitaltype4Map", HisunProductionSettle.getCapitaltype4Map());
		model.addAttribute("settleperiodtypeMap", HisunProductionSettle.getSettleperiodtypeMap());
		model.addAttribute("statusMap", HisunProductionSettle.getStatusMap());
		model.addAttribute("syncGyStatusMap", HisunProductionSettle.getSyncGyStatusMap());
		model.addAttribute("typeMap", HisunProductionSettle.getTypeMap());
		model.addAttribute("verifyflagMap", HisunProductionSettle.getVerifyflagMap());
		model.addAttribute("verifysettleflagMap", HisunProductionSettle.getVerifysettleflagMap());
		// 已经关联的商品
		List<HisunProductionLink> links = hisunProductionService.findSettleItemLinks(id);
		model.addAttribute("itemLinks", links);

		if (StringUtils.isNotEmpty(settle.getStoreId())) {
			Store store = storeService.findStoreById(Long.parseLong(settle.getStoreId()));
			model.addAttribute("storeName", store.getName());
		}

		return "item/hisunproduction/settle_view";
	}

	/**
	 * 删除商品协议
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "settleDelete/{id}")
	@ResponseBody
	public Object settleDelete(@PathVariable Long id) {
		hisunProductionService.deleteSettle(id);
		logUtils.logDelete("删除商品协议", "id" + id);
		return JsonRespWrapper.successReload("删除成功！");

	}

	/**
	 * 商品协议审核
	 * 
	 * @param id
	 * @param status
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/process/{status}/{id}/{whereAbout}")
	@ResponseBody
	public Object process(@PathVariable Long id, @PathVariable Long status, @PathVariable String whereAbout, Model model) {
		// String url = null;
		// if ("view".equals(whereAbout)) {
		// url = "/item/hisunproduction/settleView?id=" + id;
		// } else if ("list".equals(whereAbout)) {
		// url = "/item/hisunproduction/settleList";
		// } else {
		// }
		try {
			HisunProductionSettle settle = hisunProductionService.findOneSettle(id);
			settle.setStatus(HisunProductionSettle.STATUS_1);
			settle = hisunProductionService.saveSettle(settle, null, null);
			logUtils.logAudit("hisun-product", "ID:" + settle.getId().toString() + ",状态 STATUS：" + settle.getStatusName());
			// 审核通过后同步清算系统
			String msg = "";
			if (settle.getStatus() == 1) {
				Store store = storeService.findStoreById(Long.valueOf(settle.getStoreId()));
				if (store != null) {
					if (StringUtils.isEmpty(store.getMerchid())) {
						msg = "商户基本信息未成功同步清算系统，本次同步失败";
					} else {
						try {
							Map<String, String> resultMap = new HashMap<String, String>();
							/**
							 * 判断是否有关联商品 没有，则调用商品协议同步接口 有，则调用商品同步接口
							 */
							resultMap = hisunProductionService.settleSyncGy(settle);
							msg = resultMap.get("msg");
						}
						catch (Exception ex) {
							log.error(ex.getMessage());
							logger.error(ex);
							// 未知的错误消息，一般是exception catch后通知用户
							msg = ex.getMessage();
						}
					}
					log.info(settle.getId() + msg);
				}
			}
			logUtils.logAudit("商品协议审核", "id" + id);
			return JsonRespWrapper.successReload("审核成功！同步清算系统：" + msg);
		}
		catch (Exception ex) {
			// 未知的错误消息，一般是exception catch后通知用户
			logUtils.logAudit("商品协议审核操作失败", "id" + id);
			log.error(ex.getMessage());
			return JsonRespWrapper.error(ex.getMessage());
		}
	}

	/**
	 * 同步清结算
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/syncGy")
	@ResponseBody
	public Object syncGy(@RequestParam(value = "ids") String ids, Model model, HttpServletRequest request) {
		String msg = "";
		String message = "";
		String[] idarray = ids.split(",");
		if (idarray != null) {
			// 获取当前的登录用户
			SessionUser sessionUser = (SessionUser) request.getSession().getAttribute(SessionUser.SESSION_USER_KEY);
			for (String id : idarray) {
				if ("".equals(id)) {
					continue;
				}
				HisunProductionSettle settle = hisunProductionService.findOneSettle(Long.valueOf(id));
				// HisunProductionLink link =
				// hisunProductionService.findOneLink(settle.getId());
				Store store = storeService.findStoreById(Long.valueOf(settle.getStoreId()));
				if (store == null) {
					return JsonRespWrapper.successAlert("商户不存在，同步失败");
				}
				if (StringUtils.isEmpty(store.getMerchid())) {
					return JsonRespWrapper.successAlert("商户基本信息未成功同步清算系统，本次同步失败");
				}
				try {
					Map<String, String> resultMap = new HashMap<String, String>();
					/**
					 * 判断是否有关联商品 没有，则调用商品协议同步接口 有，则调用商品同步接口
					 */
					resultMap = hisunProductionService.settleSyncGy(settle);
					message = settle.getId() + resultMap.get("msg");
					msg += message;
				}
				catch (Exception ex) {
					logUtils.logOther("商品协议管理", "操作失败：" + ex.getMessage() , sessionUser.getId());
					log.error(ex.getMessage());
					logger.error(ex);
					// 未知的错误消息，一般是exception catch后通知用户
					return JsonRespWrapper.error(ex.getMessage());
				}
				logUtils.logOther("商品协议管理", "同步清算系统：" + message, sessionUser.getId());
			}
		}
		return JsonRespWrapper.successReload(msg);
	}

	/**
	 * 同步清结算
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/syncGyQuery")
	@ResponseBody
	public Object syncGyQuery(@RequestParam(value = "ids") String ids, Model model, HttpServletRequest request) {
		String msg = "";
		String message = "";
		String[] idarray = ids.split(",");
		if (idarray != null) {
			// 获取当前的登录用户
			SessionUser sessionUser = (SessionUser) request.getSession().getAttribute(SessionUser.SESSION_USER_KEY);
			for (String id : idarray) {
				if ("".equals(id)) {
					continue;
				}
				HisunProductionSettle settle = hisunProductionService.findOneSettle(Long.valueOf(id));
				// HisunProductionLink link =
				// hisunProductionService.findOneLink(settle.getId());
				Store store = storeService.findStoreById(Long.valueOf(settle.getStoreId()));
				if (store == null) {
					return JsonRespWrapper.successAlert("商户不存在，同步失败");
				}
				if (StringUtils.isEmpty(store.getMerchid())) {
					return JsonRespWrapper.successAlert("商户基本信息未成功同步高阳，本次同步失败");
				}
				try {
					Map<String, String> ret = hisunProductionService.query(settle);
					message = settle.getId() + ret.get("msg");
					msg += message;
				}
				catch (Exception ex) {
					logUtils.logOther("商品协议管理", "操作失败：" + ex.getMessage() , sessionUser.getId());
					log.error(ex.getMessage());
					logger.error(ex);
					// 未知的错误消息，一般是exception catch后通知用户
					return JsonRespWrapper.error(ex.getMessage());
				}
				if (sessionUser != null) {
					logUtils.logOther("商品协议管理", "查询清算系统：" + message, sessionUser.getId());
				} else {
					// 监控程序调用时sessionUser为空
					log.info("业务监控程序商品协议查询清算系统:" + message);
				}
			}
		}
		return JsonRespWrapper.successReload(msg);
	}

	/**
	 * 下载协议文件
	 * 
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	@RequestMapping(value = "/downfile/{id}")
	public ModelAndView downFile(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response) throws IOException, Exception {
		HisunProductionSettle settle = hisunProductionService.findOneSettle(id);
		if (null != settle.getFilePath()) {
			File file = new File(settle.getFilePath());
			String fileName = file.getName();
			bsFileService.download(request, response, settle.getFilePath().toString(), "settle_" + fileName);
		}
		return null;
	}

	@RequestMapping(value = "/getFeeByStore")
	public Object getFeeByStore(@RequestParam(value = "storeId", required = true) Long storeId, Model model) {
		List<SysFee> feeList = null;
		try {
			feeList = this.itemManageService.getValidSysFee(storeId);
		}
		catch (SQLException e) {
			log.error(e.getMessage());
			logger.error(e.getMessage());
		}
		model.addAttribute("feeList", feeList);
		return "item/hisunproduction/select-store-fee";
	}
}
