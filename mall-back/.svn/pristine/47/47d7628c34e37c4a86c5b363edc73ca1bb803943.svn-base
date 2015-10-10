package com.cplatform.mall.back.store.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cplatform.dbhelp.page.Page;
import com.cplatform.mall.back.model.SessionUser;
import com.cplatform.mall.back.model.SyncGYResponseBean;
import com.cplatform.mall.back.store.entity.Store;
import com.cplatform.mall.back.store.entity.StoreSettle;
import com.cplatform.mall.back.store.service.StoreService;
import com.cplatform.mall.back.store.service.StoreSettleService;
import com.cplatform.mall.back.store.web.validator.StoreSettleValidator;
import com.cplatform.mall.back.utils.AppConfig;
import com.cplatform.mall.back.utils.Constants;
import com.cplatform.mall.back.utils.JsonRespWrapper;
import com.cplatform.mall.back.utils.LogUtils;
import com.cplatform.mall.back.utils.MallUtils;
import com.cplatform.mall.back.utils.SyncInterface;
import com.cplatform.mall.back.websys.entity.SysFile;
import com.cplatform.util2.FileTools;
import com.cplatform.util2.TimeStamp;

/**
 * @Title 结算管理控制层
 * @Description
 * @Copyright: Copyright (c) 2013-7-9
 * @Company: 北京宽连十方数字技术有限公司
 * @Author liudong
 * @Version: 1.0
 */
@Controller
@RequestMapping(value = "/store/storeSettle")
public class StoreSettleController {

	private static final Logger log = Logger.getLogger(StoreSettleController.class);

	@Autowired
	private LogUtils logUtils;

	@Autowired
	StoreSettleService storeSettleService;

	@Autowired
	StoreService storeService;

	@Autowired
	private SyncInterface syncInterface;

	@Autowired
	private StoreSettleValidator soreSettleValidator;

	@Autowired
	private AppConfig config;

	/**
	 * 商户结算审核列表(再用)
	 * 
	 * @param store
	 * @param page
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/auditlist")
	public String auditlist(StoreSettle StoreSettle, @RequestParam(value = "page", required = false, defaultValue = "1") int page, Model model)
	        throws SQLException {
		// store.setShopClass(shopClass);

		Page<StoreSettle> storeSettleList = this.storeSettleService.listStoreSettle(StoreSettle, page, Page.DEFAULT_PAGESIZE);
		model.addAttribute("pageData", storeSettleList);
		model.addAttribute("syncGyFlagMap", StoreSettle.syncGyFlagMap);
		model.addAttribute("statusMap", StoreSettle.statusMap);
		return "/store/settle/settle-audit-list";
	}

	/**
	 * 费率列表页面
	 * 
	 * @param storeId
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/settleList/{storeId}")
	public Object settleList(@PathVariable Long storeId, StoreSettle StoreSettle,
	        @RequestParam(value = "page", required = false, defaultValue = "1") int page, Model model) throws SQLException {
		StoreSettle.setStoreId(storeId);
		Store store = this.storeService.findStoreById(storeId);
		Page<StoreSettle> storeSettleList = this.storeSettleService.listStoreSettle(StoreSettle, page, Page.DEFAULT_PAGESIZE);
		model.addAttribute("store", store);
		model.addAttribute("storeSettleList", storeSettleList);
		return "/store/settle/settle-list";

	}

	/**
	 * 跳转到添加商户结算信息页面
	 * 
	 * @param storeId
	 * @param session
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/settleAdd", method = RequestMethod.GET)
	public Object settleAdd(@RequestParam(value = "storeId") Long storeId, Model model) throws SQLException {

		Store store = this.storeService.findStoreById(storeId);
		model.addAttribute("store", store);
		StoreSettle vo = new StoreSettle();// t
		vo.setStoreId(store.getId());
		vo.setSettlePeriod(0L);
		vo.setSettleDay(1L);
		vo.setSettleDaybit(1L);
		vo.setSettleBeginamt(0L);
		vo.setMinRetainedamt(0L);
		// t_store_settle和t_store表的merchid设置为一致
		vo.setMerchid(store.getMerchid());
		model.addAttribute("storeSettle", vo);
		return "/store/settle/settle-add";

	}

	/**
	 * 保存添加的结算信息
	 * 
	 * @param storeId
	 * @param session
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/settleAdd", method = RequestMethod.POST)
	@ResponseBody
	public Object settleSave(MultipartFile uploadfile, StoreSettle po, Model model, BindingResult result) throws IOException {

		try {
			soreSettleValidator.validate(po, result);
			if (result.hasErrors()) {
				return JsonRespWrapper.error(result.getFieldErrors());
			}
			InputStream is = null;
			String extName = "";
			String fileName = "";
			if (uploadfile != null && !uploadfile.isEmpty()) {
				is = uploadfile.getInputStream();
				/**
				 * 取到扩展名
				 * 
				 * @author liujun
				 */
				fileName = uploadfile.getOriginalFilename();
				extName = FileTools.getExtFilename(fileName);
				if (Constants.EXTNAME.indexOf(extName) < 0) {
					return JsonRespWrapper.error("上传附件格式应是以下格式：" + Constants.EXTNAME);
				}

			}

			po.setCreateUser(SessionUser.getSessionUser().getId());
			po.setCreateTime(TimeStamp.getTime(TimeStamp.YYYYMMDDhhmmss));
			po.setSyncGyFlag(0L);
			po.setStatus(0L);

			/**
			 * 保存商户结算信息。
			 */
			// /this.storeService.saveSroreSettle(po, is);

			this.storeService.saveSroreSettleContractFile(po, extName, is);

			model.addAttribute("storeSettle", po);
			logUtils.logAdd("结算管理", "结算管理添加, 结算编号：" + po.getId());
		}
		catch (Exception e) {
			logUtils.logAdd("结算管理", "结算管理添加失败, 结算编号：" + po.getId());
			log.error(e.getMessage());

			return JsonRespWrapper.error(e.getMessage());

		}

		return JsonRespWrapper.success("操作成功", "/store/storeSettle/settleList/" + po.getStoreId());

	}

	/**
	 * 跳转到修改商户结算信息页面
	 * 
	 * @param storeId
	 * @param session
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/settleEdit", method = RequestMethod.GET)
	public Object settleEdit(@RequestParam(value = "id") Long id, Model model) throws SQLException {

		StoreSettle vo = this.storeService.findStoreSettleById(id);
		if (vo != null) {
			Store store = this.storeService.findStoreById(vo.getStoreId());
			model.addAttribute("store", store);
			SysFile sysFile = this.storeService.getSysFileByStoreId(store.getId());
			model.addAttribute("downurl", sysFile == null ? "" : config.getUploadFilePath() + sysFile.getFileWebPath());
		}
		model.addAttribute("storeSettle", vo);
		return "/store/settle/settle-add";

	}

	/**
	 * 保存修改的结算信息
	 * 
	 * @param storeId
	 * @param session
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/settleEdit", method = RequestMethod.POST)
	@ResponseBody
	public Object settleUpdate(MultipartFile uploadfile, StoreSettle po, Model model, BindingResult result) throws IOException {

		try {
			soreSettleValidator.validate(po, result);
			if (result.hasErrors()) {
				return JsonRespWrapper.error(result.getFieldErrors());
			}
			InputStream is = null;
			String extName = "";
			String fileName = "";
			if (uploadfile != null && !uploadfile.isEmpty()) {
				is = uploadfile.getInputStream();
				/**
				 * 取到扩展名
				 */

				fileName = uploadfile.getOriginalFilename();
				extName = FileTools.getExtFilename(fileName);
				if (Constants.EXTNAME.indexOf(extName) < 0) {
					return JsonRespWrapper.error("上传附件格式应是以下格式：" + Constants.EXTNAME);
				}

			}

			StoreSettle oldpo = this.storeService.findStoreSettleById(po.getId());
			po.setMerchid(oldpo.getMerchid());
			po.setSyncGyFlag(oldpo.getSyncGyFlag());
			po.setSyncTime(oldpo.getSyncTime());
			po.setStatus(0L);
			po.setCreateUser(oldpo.getCreateUser());
			po.setCreateTime(oldpo.getCreateTime());
			// /换用方法，解决不同扩展名的问题
			// /this.storeService.up(po, is);
			this.storeService.updateSroreSettleContractFile(po, extName, is);
			model.addAttribute("storeSettle", po);
			logUtils.logModify("结算管理", "结算管理修改, 结算编号：" + po.getId());
		}
		catch (Exception e) {
			logUtils.logModify("结算管理", "结算管理修改失败, 结算编号：" + po.getId());
			log.error(e.getMessage());
			return JsonRespWrapper.error(e.getMessage());
		}
		return JsonRespWrapper.success("操作成功", "/store/storeSettle/settleList/" + po.getStoreId());

	}

	@RequestMapping(value = "/settleView/{id}")
	public Object settleView(@PathVariable Long id, HttpSession session, Model model) throws SQLException {
		StoreSettle vo = this.storeService.findStoreSettleById(id);
		if (vo != null) {
			Store store = this.storeService.findStoreById(vo.getStoreId());
			model.addAttribute("store", store);
			SysFile sysFile = this.storeService.getSysFileByStoreId(store.getId());

			if (sysFile != null) {
				String downurl = config.getUploadFilePath() + sysFile.getFileWebPath();
				model.addAttribute("downurl", downurl);
			}
		}
		model.addAttribute("storeSettle", vo);
		return "/store/settle/settle-view";

	}

	/**
	 * 商户签署协议下载
	 * 
	 * @param id
	 *            商户结算id
	 * @param session
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author liujun 2013-09-04
	 */
	@RequestMapping(value = "/busiContentDown/{id}")
	public Object busiContentDown(@PathVariable Long id, HttpSession session, Model model, HttpServletRequest request, HttpServletResponse response)
	        throws Exception {

		try {
			File file = null;
			StoreSettle vo = this.storeService.findStoreSettleById(id);
			if (vo != null) {
				Store store = this.storeService.findStoreById(vo.getStoreId());
				model.addAttribute("store", store);
				SysFile sysFile = this.storeService.getSysFileByStoreId(store.getId());

				if (sysFile != null) {
					String downurl = sysFile.getFileAbsPath();
					String extName = "";
					if (downurl != null && !"".equals(downurl)) {
						file = new File(downurl);
						int indexPos = downurl.lastIndexOf(".");
						extName = downurl.substring(indexPos);
						String mimeType = new MimetypesFileTypeMap().getContentType(file);
						// String
						// formatTime=TimeUtil.format(TimeStamp.getTime(TimeStamp.YYYYMMDDhhmmss),
						// TimeUtil.SOURCE_1, TimeUtil.TARGET_1);
						String formatTime = TimeStamp.getTime(TimeStamp.YYYYMMDDhhmmss);

						String fileName = store.getName() + "(" + formatTime + ")" + extName;
						// log.info("fileName:"+fileName);
						MallUtils.downLodaFileOutputStream(request, response, FileUtils.openInputStream(file), mimeType, fileName);

					}

				}
			}
		}
		
		catch (Exception e) {
			log.error(e.getMessage());
			logUtils.logAdd("下载商户签署协议附件异常", e.getMessage());
			return JsonRespWrapper.error(e.getMessage());

		}
		// //return JsonRespWrapper.error(e.getMessage());

		return JsonRespWrapper.successReload("签署协议下载完成");

	}

	@RequestMapping(value = "/settleDel/{id}")
	@ResponseBody
	public Object settleDel(@PathVariable Long id, Model model) {
		StoreSettle po = storeService.findStoreSettleById(id);
		if (po.getStatus() == 3L) {
			return JsonRespWrapper.successAlert("当前结算信息已经审核通过，不能被删除");
		}
		if (po.getSyncGyFlag() != null && 0L != po.getSyncGyFlag()) {
			return JsonRespWrapper.successAlert("当前结算信息已经同步过清结算系统，不能被删除");
		}
		storeService.delStoreSettleById(id);
		logUtils.logDelete("结算管理", "结算管理删除, 结算编号：" + po.getId());
		return JsonRespWrapper.success("操作成功", "/store/storeSettle/settleList/" + po.getStoreId());
	}

	@RequestMapping(value = "/auditPage/{id}")
	public String toAuditPage(@PathVariable Long id, Model model) {
		model.addAttribute("id", id);
		return "/store/settle/settle_audit";
	}

	@RequestMapping(value = "/settleAudit")
	@ResponseBody
	public Object settleAudit(@RequestParam(value = "id") Long id, @RequestParam(value = "status") Long status, Model model,
	        HttpServletRequest request) {
		StoreSettle po = this.storeService.findStoreSettleById(id);
		po.setStatus(status);
		this.storeSettleService.auditSettle(po);
		logUtils.logAudit("结算管理", "结算管理审核, 结算编号：" + po.getId());
		// 审核通过同步清算系统
		String msg = "";
		if (po.getStatus() == 3) {
			Store store = this.storeService.findStoreById(po.getStoreId());
			if (store != null) {
				if (store.getMerchid() == null) {
					msg = "该商户基本信息未同步成功，暂不能同步。";
				} else {
					SyncGYResponseBean bean = null;
					if (po.getMerchid() == null) {
						po.setMerchid(store.getMerchid());
					}
					if (po.getSyncGyFlag() == 0L) {
						bean = syncInterface.syncAddSettle(po);
					} else {
						bean = syncInterface.syncUpdateSettle(po);
					}
					if (bean != null) {
						if (bean.isSyncSuccess()) {
							po.setSyncGyFlag(1L);
							po.setMerchid(bean.getMerchid());
							po.setSyncTime(TimeStamp.getTime(TimeStamp.YYYYMMDDhhmmss));
							this.storeService.addOrUpdataStoreSettle(po);
						}
						msg = bean.getMsg();
					} else {
						msg = "同步失败。";
					}
				}
				log.info(po.getId() + msg);
			}
		}
		// 返回到来源页面
		String backUrl = request.getParameter("backUrl");
		return JsonRespWrapper.success("操作成功！同步清算系统：" + msg, backUrl);
	}

	@RequestMapping(value = "/sync")
	@ResponseBody
	public Object sync(@RequestParam(value = "ids") String ids, Model model, HttpServletRequest request) {
		String msg = "操作完成。";
		String message = "";
		SyncGYResponseBean bean = null;
		try {
			String[] idarray = ids.split(",");
			if (idarray != null) {
				// 获取当前的登录用户
				SessionUser sessionUser = (SessionUser) request.getSession().getAttribute(SessionUser.SESSION_USER_KEY);
				for (String id : idarray) {
					if ("".equals(id)) {
						continue;
					}
					StoreSettle vo = this.storeService.findStoreSettleById(Long.valueOf(id));
					if (vo != null) {
						SysFile sysFile = this.storeService.getSysFileByStoreId(vo.getStoreId());
						Store store = this.storeService.findStoreById(vo.getStoreId());
						if (store == null) {
							continue;
						}
						if (store.getMerchid() == null) {
							message = vo.getId() + "该商户基本信息未同步成功，暂不能同步。";
							msg += message;
							continue;
						}
						// if (store.getSyncGyFlag() != 3L) {
						// msg += vo.getId() + "该商户基本信息清结算平台未审核通过，暂不能同步。";
						// continue;
						// }
						if (vo.getStatus() != 3) {
							message = vo.getId() + "状态：未审核通过，暂不能同步。";
							msg += message;
							continue;
						}
						if (vo.getMerchid() == null) {
							vo.setMerchid(store.getMerchid());
						}
						if (vo.getSyncGyFlag() == 0L) {
							bean = syncInterface.syncAddSettle(vo);
						} else {
							bean = syncInterface.syncUpdateSettle(vo);
						}
						if (bean != null) {
							if (bean.isSyncSuccess()) {
								vo.setSyncGyFlag(1L);
								vo.setMerchid(bean.getMerchid());
								vo.setSyncTime(TimeStamp.getTime(TimeStamp.YYYYMMDDhhmmss));
								this.storeService.addOrUpdataStoreSettle(vo);
							}
							message = vo.getId() + bean.getMsg();
							msg += message;
						} else {
							message = vo.getId() + "同步失败。";
							msg += message;
						}

						// ///////////////////////////////////////////////////////////////////////
						/***
						 * 增加商户合同维护的开发。
						 * 
						 * @author liujun 2013-09-03 只有在商户文件存在的情况下同步
						 */

						if (sysFile != null && sysFile.getFileAbsPath() != null && !"".equals(sysFile.getFileAbsPath())) {
							File file = null;
							file = new File(sysFile.getFileAbsPath());
							InputStream input;
							input = new FileInputStream(file);
							// /FileUploadSendGykj fileUplodaGykj=new
							// FileUploadSendGykj();
							log.info("附件开始ftp上传开始");
							String uploadFlag = MallUtils.uploadFileFtp(config.getFtpServerIp(), Integer.parseInt(config.getFtpServerPort()), config
							        .getFtpUser(), config.getFtpPasswd(), config.getFtpRemotePath(), file.getName(), input);
							log.info("附件开始ftp上传结束");
							// ////上传附件到ftp结束
							if ("1".equals(uploadFlag)) {
								if (sysFile != null && store != null && vo != null) {

									bean = syncInterface.syncAddBusiContent(store, vo, sysFile);

									if (bean != null) {

										if (bean.getCode().lastIndexOf("00000") > 0) {
											message = " 商户合同维护新增同步成功！";
											msg += message;
										} else {
											bean = syncInterface.syncUpdateBusiContent(store, vo, sysFile);
											if (bean.getCode().lastIndexOf("00000") > 0) {
												message = " 商户合同维护修改同步成功！";
												msg += message;
												continue;
											}
											message = " 商户合同维护新增同步失败！";
											msg += message;

										}

									}
								}

							}

						}

					}
					logUtils.logOther("商户结算审核", "同步清算系统：" + message, sessionUser.getId());
				}
			}
		}
		catch (Exception e) {
			log.error(e.getMessage());
			logUtils.logOther("商户结算信息，商户合同维护同步出现异常", e.getMessage());
			return JsonRespWrapper.error(e.getMessage());

		}

		// return JsonRespWrapper.success(msg, "/store/storeSettle/auditlist");
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
				StoreSettle vo = this.storeService.findStoreSettleById(Long.valueOf(id));
				if (vo != null) {
					if (vo.getMerchid() == null) {
						message = vo.getId() + "该结算信息未同步成功，暂不能查询。";
						msg += message;
						continue;
					}

					if (vo.getStatus() != 3) {
						message = vo.getId() + "状态：未审核通过，暂不能查询。";
						msg += message;
						continue;
					}
					bean = syncInterface.queryStoreSettle(vo);

					if (bean != null) {
						if (bean.isSyncSuccess()) {
							vo.setSyncGyFlag(bean.getSettleInfoStatus());
							vo.setMerchid(bean.getMerchid());
							// vo.setSyncTime(TimeStamp.getTime(TimeStamp.YYYYMMDDhhmmss));
							this.storeService.addOrUpdataStoreSettle(vo);
							message = vo.getId() + "状态：" + StoreSettle.syncGyFlagMap.get(vo.getSyncGyFlag()) + "。";
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
				if (sessionUser != null) {
					logUtils.logOther("商户结算审核", "查询清算系统：" + message, sessionUser.getId());
				} else {
					// 监控程序调用时sessionUser为空
					log.info("业务监控程序商户结算查询清算系统:" + message);
				}
			}
		}
		// return JsonRespWrapper.success(msg, "/store/storeSettle/auditlist");
		return JsonRespWrapper.successReload(msg);
	}
}
