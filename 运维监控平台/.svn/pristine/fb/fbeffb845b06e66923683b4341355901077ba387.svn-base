package com.cplatform.mall.dc.web;

import com.cplatform.mall.dc.service.DcUserService;
import com.cplatform.mall.dc.service.CertService;
import com.cplatform.mall.dc.utils.JsonRespWrapper;
import com.cplatform.mall.dc.view.ExcelData;
import com.cplatform.verifycode.VerifyService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;

/**
 * 综合验证
 * <p/>
 * Copyright: Copyright (c) 13-6-1 下午6:47
 * <p/>
 * Company: 苏州宽连信息技术有限公司
 * <p/>
 * Author: chengyao
 * <p/>
 * Version: 1.0
 * <p/>
 */
@Controller
@RequestMapping("/cert")
public class CertController {

	@Autowired
	CertService certService;

	@Autowired
	DcUserService dcUserService;

	@Autowired
	VerifyService codeServiceClient;

	final Log logger = LogFactory.getLog(this.getClass());

	@RequestMapping(value = "code", method = RequestMethod.GET)
	public String code() {
		return "cert/code";
	}

	@RequestMapping(value = "code", method = RequestMethod.POST)
	@ResponseBody
	public Object codePost(String vcode) {
		if (StringUtils.isBlank(vcode)) {
			return JsonRespWrapper.errorField("vcode", "验证码不能为空");
		}

		try {
			return certService.verifyCode(vcode);
		} catch (java.net.ConnectException conEx) {
			logger.warn("验码错误", conEx);
			return JsonRespWrapper.errorField("vcode",
					"验码失败，服务器网络不正常，请联系客服。 <br/>错误详情：");
		} catch (Exception ex) {
			logger.warn("验码错误", ex);
			return JsonRespWrapper.errorField("vcode", "验码失败，请联系客服。 \r\n错误详情："
					+ ex.getMessage());
		}
	}

	@RequestMapping(value = "history")
	public String history(
			String timeRange,
			String queryStartTime,
			String queryEndTime,
			String itemId,
			String itemName,
			String op,
			@RequestParam(value = "page", defaultValue = "0", required = false) int page,
			@RequestParam(value = "pageSize", defaultValue = "20", required = false) int pageSize,
			Model model) throws SQLException {
		String startTime = StringUtils.remove(queryStartTime, "-");
		String endTime = StringUtils.remove(queryEndTime, "-");
		model.addAttribute("datas", certService.getVerifyHistory(timeRange,
				startTime, endTime, itemId, itemName, op, page, pageSize));
		return "cert/history";
	}

	@RequestMapping(value = "history/export")
	public ModelAndView smsSellExport(String timeRange, String startTime,
			String endTime, String itemId, String itemName, String op)
			throws SQLException {
		ExcelData excel = certService.getVerifyHistoryReport(timeRange,
				startTime, endTime, itemId, itemName, op);
		return excel.toModel();
	}

}
