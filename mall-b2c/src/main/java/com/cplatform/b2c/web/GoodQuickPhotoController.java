package com.cplatform.b2c.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cplatform.b2c.service.GoodQuickPhotoService;
import com.cplatform.b2c.util.AppConfig;
import com.cplatform.b2c.util.PathUtil;

/**
 * 商品快照详情. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) Mar 4, 2014 3:54:18 PM
 * <p>
 * Company: 宽连信息(苏州)技术有限公司
 * <p>
 * 
 * @author wangtaob@c-platform.com
 * @version 1.0.0
 */
@Controller
public class GoodQuickPhotoController {

	private final Logger logger = Logger.getLogger(getClass());

	@Autowired
	private GoodQuickPhotoService service;

	@Autowired
	private AppConfig appConfig;

	@Autowired
	private PathUtil pathUtil;

	/**
	 * 根据商品id和版本号跳转到快照页面
	 * 
	 * @param response
	 * @param model
	 * @param goodId
	 * @param verisonId
	 * @return 成功跳转到快照页面 其他跳转到404
	 */
	@RequestMapping(value = "/quickphoto/item", method = RequestMethod.GET)
	public String getAddress(HttpServletRequest request, HttpServletResponse response, Model model, @RequestParam("item_id") String goodId,
	        @RequestParam("verison_id") String verisonId) {

		String respJson = service.getQuickPhoto(goodId, verisonId);
		if (StringUtils.isBlank(goodId) && !StringUtils.isNumeric(goodId)) {
			return "error/404";
		}
		if (StringUtils.isBlank(verisonId)) {
			return "redirect:" + appConfig.getWebRoot() + pathUtil.getPathById(PathUtil.TYPE_ITEM, Long.valueOf(goodId));
		}
		if (!"".equals(respJson)) {
			JSONObject json = JSONObject.fromObject(respJson);
			if ("0".equals(json.getString("code"))) {
				String itemString = json.getString("snapshot");
				JSONObject res = JSONObject.fromObject(itemString);

				String imgPath = res.optString("g_img_path");
				if (StringUtils.isNotBlank(imgPath)) {
					String[] arr = imgPath.split(",");
					model.addAttribute("imgs", arr);
				}
				logger.info("返回参数内容 ：" + res);
				model.addAttribute("good", res);
				return "goods/quickphoto";
			} else {
				return "redirect:" + appConfig.getWebRoot() + pathUtil.getPathById(PathUtil.TYPE_ITEM, Long.valueOf(goodId));
			}

		} else {
			return "redirect:" + appConfig.getWebRoot() + pathUtil.getPathById(PathUtil.TYPE_ITEM, Long.valueOf(goodId));
		}

	}
}
