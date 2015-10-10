package com.cplatform.b2c.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cplatform.b2c.service.AdService;

/**
 * 广告相关控制方法 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2013-6-26 上午11:34:00
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author yangxm@c-platform.com
 */

@Controller
public class AdController {

	private static final Log logger = LogFactory.getLog(AdController.class);

	@Autowired
	private AdService adService;

	/**
	 * 根据广告CODE获取广告内容
	 * 
	 * @param adid
	 *            广告代码
	 * @param response
	 */
	@RequestMapping(value = "/ad/getAd")
	@ResponseBody
	public void getAdById(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html; charset=utf-8");
		String disfunction = request.getParameter("disfunction");
		String code = request.getParameter("code");
		String appendElement = request.getParameter("appendElement");
		String position = request.getParameter("position");
		String region_code = request.getParameter("region_code");
		String adJson = "";
		try {
			// 从数据库获取广告内容
			adJson = adService.getAdPositionList(code, position, region_code);
		}
		catch (Exception e) {
			logger.error("get ad list error " + e.getMessage());
		}

		// 写到页面的内容
		String m = "var k = eval(" + adJson + ");";
		m = m + disfunction + "(k,'" + code + "','" + appendElement + "');";
		logger.info("write ad json");
		// 输出json
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(m);

			out.flush();
		}
		catch (IOException e) {
			logger.error("get ad error " + e.getMessage());
		}
	}
}
