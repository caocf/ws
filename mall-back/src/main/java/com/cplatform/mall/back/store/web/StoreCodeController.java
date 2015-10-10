package com.cplatform.mall.back.store.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

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
import com.cplatform.mall.back.model.SessionUser;
import com.cplatform.mall.back.store.entity.StoreCode;
import com.cplatform.mall.back.store.entity.StoreCodeConfig;
import com.cplatform.mall.back.store.service.StoreCodeService;
import com.cplatform.mall.back.store.web.validator.StoreCodeValidator;
import com.cplatform.mall.back.utils.JsonRespWrapper;
import com.cplatform.mall.back.utils.LogUtils;
import com.cplatform.util2.TimeStamp;

@Controller
@RequestMapping(value = "/store/code")
public class StoreCodeController {

	private static final Logger log = Logger.getLogger(StoreCodeController.class);

	@Autowired
	private StoreCodeService storeCodeService;

	@Autowired
	private StoreCodeValidator storeCodeValidator;

	@Autowired
	private LogUtils logUtils;

	/**
	 * 列表
	 * 
	 * @param contentCode
	 * @param page
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/list")
	public String storeList(StoreCode code, @RequestParam(value = "page", required = false, defaultValue = "1") int page, Model model)
	        throws SQLException {
		List<StoreCodeConfig> configList = storeCodeService.getCodeConfig();
		Page<StoreCode> pageData = storeCodeService.PageStoreList(code, page);
		model.addAttribute("pageData", pageData);
		model.addAttribute("configList", configList);
		model.addAttribute("sendChannelIdMap", code.sendChannelIdMap);
		model.addAttribute("sendTypeIdMap", code.sendTypeIdMap);
		return "/store/code/code-list";
	}

	/**
	 * 进入增加页面
	 * 
	 * @param model
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String toAddPage(Model model) throws IOException, Exception {
		StoreCode storeCode = new StoreCode();
		List<StoreCodeConfig> list = storeCodeService.getCodeConfig();
		// System.out.println(list.size());
		model.addAttribute("storeCode", storeCode);
		model.addAttribute("codelist", list);
		model.addAttribute("sendChannelIdMap", storeCode.sendChannelIdMap);
		model.addAttribute("sendTypeJson", JSONObject.fromObject(storeCode.sendTypeIdMap));
		return "/store/code/code-add";
	}

	/**
	 * 增加商户码信息
	 * 
	 * @param model
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Object addCode(@ModelAttribute("storeCode") StoreCode code, Model model, HttpServletRequest request, BindingResult result)
	        throws IOException, Exception {
		// 获取发码渠道
		String[] sendChannelIdList = request.getParameterValues("sendChannelId");
		String[] sendTypeIdList = request.getParameterValues("sendTypeId");
		storeCodeValidator.validate(sendChannelIdList, sendTypeIdList, result);
		if (result.hasErrors()) {
			return JsonRespWrapper.error(result.getFieldErrors());
		}
		String msg = "添加成功！";
		if (sendChannelIdList.length > 0) {
			for (int i = 0; i < sendChannelIdList.length; i++) {
				if (sendChannelIdList[i] != null && sendTypeIdList[i] != null && !"".equals(sendChannelIdList[i]) && !"".equals(sendTypeIdList[i])) {
					StoreCode storeCode = new StoreCode();
					storeCode.setStoreId(code.getStoreId());
					storeCode.setSendChannelId(Long.parseLong(sendChannelIdList[i]));
					storeCode.setSendTypeId(Long.parseLong(sendTypeIdList[i]));
					storeCode.setStartTime(code.getStartTime().replaceAll("-", "").replaceAll(":", "").replaceAll(" ", ""));
					storeCode.setStopTime(code.getStopTime().replaceAll("-", "").replaceAll(":", "").replaceAll(" ", ""));
					storeCode.setOperUser(SessionUser.getSessionUser().getId().toString());
					storeCode.setOperTime(TimeStamp.getTime(TimeStamp.YYYYMMDDhhmmss));
					StoreCode codeInfo = storeCodeService.findStoreCodeIsExit(storeCode.getStoreId(), storeCode.getSendChannelId(), storeCode
					        .getSendTypeId(), null);
					if (codeInfo != null) {
						msg += storeCode.sendChannelIdMap.get(Long.parseLong(sendChannelIdList[i])) + "-"
						        + storeCode.sendTypeIdMap.get(sendTypeIdList[i]) + "已存在，添加失败！";
					} else {
						storeCodeService.addStoreCode(storeCode);
					}
					logUtils.logAdd("商户码管理", "商户码添加成功");
				}
			}
		}
		return JsonRespWrapper.success(msg, "/store/code/list");
	}

	/**
	 * 进入修改页面
	 * 
	 * @param model
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String toEditPage(@PathVariable Long id, Model model) throws IOException, Exception {
		StoreCode storeCode = storeCodeService.findStoreCodeById(id);
		List<StoreCodeConfig> list = storeCodeService.getCodeConfig();
		model.addAttribute("storeCode", storeCode);
		model.addAttribute("codelist", list);
		model.addAttribute("sendChannelIdMap", storeCode.sendChannelIdMap);
		model.addAttribute("sendTypeJson", JSONObject.fromObject(storeCode.sendTypeIdMap));
		return "/store/code/code-edit";
	}

	/**
	 * 修改
	 * 
	 * @param model
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public Object editCode(@ModelAttribute("storeCode") StoreCode code, Model model, HttpServletRequest request, BindingResult result)
	        throws IOException, Exception {
		code.setStartTime(code.getStartTime().replaceAll("-", "").replaceAll(":", "").replaceAll(" ", ""));
		code.setStopTime(code.getStopTime().replaceAll("-", "").replaceAll(":", "").replaceAll(" ", ""));
		code.setOperUser(SessionUser.getSessionUser().getId().toString());
		code.setOperTime(TimeStamp.getTime(TimeStamp.YYYYMMDDhhmmss));
		storeCodeValidator.validate(code, result);
		if (result.hasErrors()) {
			return JsonRespWrapper.error(result.getFieldErrors());
		}
		storeCodeService.addStoreCode(code);
		logUtils.logModify("商户码管理", "商户码修改,编号：" + code.getId());
		String backUrl = request.getParameter("backUrl");
		return JsonRespWrapper.success("修改成功！", backUrl);
	}

	/**
	 * 删除
	 * 
	 * @param model
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	@RequestMapping(value = "/delete/{id}")
	@ResponseBody
	public Object delete(@PathVariable Long id, Model model) {
		try {
			storeCodeService.delStoreCode(id);
		}
		catch (SQLException e) {
			log.error("删除商户码：" + e.getMessage());
		}
		logUtils.logModify("商户码管理", "商户码删除,编号：" + id);
		return JsonRespWrapper.successReload("删除成功！");
	}
}
