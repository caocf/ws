package com.cplatform.b2c.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cplatform.b2c.model.TShopCustomerService;
import com.cplatform.b2c.service.ShopService;
import com.cplatform.b2c.util.AppConfig;

/**
 * 标题、简要说明. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2013-8-16 上午11:20:23
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author zouyl@c-platform.com
 * @version 1.0.0
 */
@Controller
public class ShopAjaxController {

	@Autowired
	private ShopService shopService;

	@Autowired
	AppConfig appConfig;

	/**
	 * 获取商户分类数据
	 * 
	 * @param response
	 * @param shopId
	 */
	@RequestMapping(value = "/shop/getfexinlist")
	@ResponseBody
	public String getFeixinList(HttpServletResponse response, @RequestParam("shopId") String shopId, @RequestParam("shopClass") String shopClass) {
		response.setContentType("text/json; charset=UTF-8");
		List<TShopCustomerService> list = shopService.getFeixinList(Integer.parseInt(shopId), Integer.parseInt(shopClass));
		Map<String, List<TShopCustomerService>> retMap = new HashMap<String, List<TShopCustomerService>>();
		retMap.put("data", list);

		JSONObject json = JSONObject.fromObject(retMap);
		return json.toString();
	}

}
