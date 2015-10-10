package com.cplatform.b2c.welfare.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cplatform.b2c.util.AppConfig;
import com.cplatform.b2c.util.SysTypeUtil;
import com.cplatform.b2c.welfare.entity.WelFareDetailVo;
import com.cplatform.b2c.welfare.entity.WelFareModel;
import com.cplatform.b2c.welfare.entity.WelFareStock;
import com.cplatform.b2c.welfare.service.WelFareService;
import com.cplatform.b2c.welfare.util.Constants;
import com.cplatform.csso.agent.http.SSOAgent;
import com.cplatform.dbhelp.page.ListPage;
import com.cplatform.sso.lmsh.bean.LoginUserBean;

/**
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-11-1 下午01:43:38
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: guolt@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
@Controller
public class WelFareController {

	Logger log = Logger.getLogger(WelFareController.class);

	@Autowired
	WelFareService fareService;

	@Autowired
	private AppConfig appConfig;

	@Autowired
	SysTypeUtil sysTypeUtil;

	private final Logger logger = Logger.getLogger(WelFareController.class);

	/**
	 * 地址适配
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/lbff")
	public String main(HttpServletRequest request, HttpServletResponse response, Model model) {

		return "redirect:welfare/main.chtml";
	}

	/**
	 * 活动入口,商品查询查询
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param page
	 *            页码
	 * @param itemColor
	 *            颜色
	 * @param itemSize
	 *            尺码
	 * @param itemOther
	 *            备注(其他)
	 * @param orderType
	 *            排序类型
	 * @param brand
	 *            品牌
	 * @param itemType
	 *            0男鞋, 1女鞋 , 2童鞋 ,其他查询全部
	 * @return
	 */
	@RequestMapping(value = "/welfare/main")
	public String main(HttpServletRequest request, HttpServletResponse response,
	        Model model,
	        @RequestParam(value = "page", required = false, defaultValue = "1") int page,
	        @RequestParam(value = "item1", required = false, defaultValue = "") String item1,// 颜色
	        @RequestParam(value = "item2", required = false, defaultValue = "") String item2, // 尺码
	        @RequestParam(value = "orderType", required = false, defaultValue = "2") int orderType, // 排序类型
	        @RequestParam(value = "brand", required = false, defaultValue = "") String brand, // 品牌
	        @RequestParam(value = "itemType", required = false, defaultValue = "0") int itemType,
	        @RequestParam(value = "test", required = false, defaultValue = "0") int test) {
		try {
			if (isLogin(request, response) == 2) {// 没登陆
				model.addAttribute("isLogin", 2);
				logger.info("welfare用户没有通过鉴权!");
				// response.sendRedirect("http://js.ac.10086.cn/jsauth/logon.html?channel=00800&backurl=http%3A%2F%2Fh.12580life.com%2Fulogin%2Fpro_sso_sync.jsp%3FbackURL%3Dhttp://mall.12580life.com/lbff.chtml");
				response.sendRedirect("http://ca.12580life.com/login?service=http://mall.12580life.com/lbff.chtml");
			} else if (isLogin(request, response) == 3) {
				model.addAttribute("isLogin", 3);
				logger.info("welfare用户不是目标库用户!");
				return "welfare/index";
			} else if (isLogin(request, response) == 1) {
				logger.info("welfare用户通过鉴权!");
				/**
				 * 先设置encode 的参数
				 */
				model.addAttribute("itemSizeEncode", item1);
				model.addAttribute("brandEncode", brand);
				model.addAttribute("itemColorEncode", item2);

				/**
				 * 转码
				 */
				item1 = Constants.decoderUTF8(item1);
				brand = Constants.decoderUTF8(brand);
				item2 = Constants.decoderUTF8(item2);

				WelFareModel welFaremodel = new WelFareModel();
				welFaremodel.setBrand(brand);
				welFaremodel.setItemColor(item1);
				welFaremodel.setItemType(itemType);
				welFaremodel.setItemSize(item2);
				welFaremodel.setOrderType(orderType);

				ListPage<Map<String, Object>> pageData = new ListPage<Map<String, Object>>();
				pageData = fareService.goodsList(welFaremodel, page, 9);
				/**
				 * 设置返显参数
				 */
				if (orderType == 2) {
					orderType = 1;
				}
				model.addAttribute("isLogin", 1);
				model.addAttribute("item1", item1);
				model.addAttribute("item2", item2);
				model.addAttribute("orderType", orderType);
				model.addAttribute("brand", brand);
				model.addAttribute("itemType", itemType);

				String[] items = { item1, item2 };

				List<Map<String, Object>> types = fareService.allTypes(itemType);
				int i = 0;
				for (Map<String, Object> map : types) {
					List<String> details = fareService.AllDetail(itemType, (String) map.get("PARAM_KEY"));
					map.put("details", details);
					map.put("on", items[i]);
					i++;
				}

				/** 劳保 商品 分类 */
				List<Map<String, Object>> goodsTypes = sysTypeUtil.findLaoBaoTypeByPid(appConfig.getLaoBaoType());
				model.addAttribute("goodsTypes", goodsTypes);

				model.addAttribute("pageData", pageData);
				model.addAttribute("types", types);
				model.addAttribute("brands", fareService.AllBrand(itemType));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			log.error("无法获取页面数据", e);
		}
		return "welfare/index";
	}

	/**
	 * 获取商品详情
	 * 
	 * @param request
	 * @param response
	 * @param itemId
	 * @return
	 */
	@RequestMapping(value = "/welfare/detail")
	@ResponseBody
	public Object detail(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "itemId", required = true) Integer itemId,
	        @RequestParam(value = "itemType", required = true) Integer itemType) {
		JSONObject json = new JSONObject();
		try {
			if (isLogin(request, response) == 1) {

				if (itemId != null && itemId.toString().matches("^\\d+$") && itemType != null && itemType.toString().matches("\\d")) {
					WelFareDetailVo detailVo = fareService.getDetailByItemIdAndItemType(itemId, itemType);
					JSONObject voJson = JSONObject.fromObject(detailVo);
					json.accumulate("item", voJson);
					json.accumulate("info", "success");
				}
			} else {
				json.accumulate("info", "notLogin");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			log.error("获取商品详情失败", e);
			json.accumulate("info", "err");
		}
		return json;
	}

	/**
	 * 获取商品介绍
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param itemId
	 *            商品id
	 * @return
	 */
	@RequestMapping(value = "/welfare/remark")
	public String remark(HttpServletRequest request, HttpServletResponse response, Model model,
	        @RequestParam(value = "itemId", required = true) Integer itemId) {
		try {
			WelFareModel fareModel = fareService.getRemark(itemId);
			model.addAttribute("item", fareModel);
		}
		catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		return "welfare/remark";
	}

	/**
	 * 根据尺寸获取颜色
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param itemId
	 * @param itemSize
	 * @param itemType
	 * @return
	 */
	@RequestMapping(value = "/welfare/getColorsBySize")
	@ResponseBody
	public Object getColorsBySize(HttpServletRequest request, HttpServletResponse response, Model model,
	        @RequestParam(value = "itemId", required = true) Integer itemId, @RequestParam(value = "itemSize", required = true) String itemSize,
	        @RequestParam(value = "itemType", required = true) Integer itemType) {
		List<WelFareStock> lst = null;
		JSONObject json = new JSONObject();
		JSONArray jsonArr = null;
		try {
			itemSize = Constants.decoderUTF8(itemSize);
			lst = fareService.getColorsBySize(itemId, itemSize, itemType);
			jsonArr = JSONArray.fromObject(lst);
			json.accumulate("result", jsonArr);
		}
		catch (Exception e) {
			e.printStackTrace();
			log.error("扩展商品获取失败 ", e);
			json.accumulate("result", "err");
		}
		return json;

	}

	/**
	 * 根据颜色获取尺寸
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param itemId
	 * @param itemSize
	 * @param itemType
	 * @return
	 */
	@RequestMapping(value = "/welfare/getSizeByColor")
	@ResponseBody
	public Object getSizeByColor(HttpServletRequest request, HttpServletResponse response, Model model,
	        @RequestParam(value = "itemId", required = true) Integer itemId, @RequestParam(value = "itemColor", required = true) String itemColor,
	        @RequestParam(value = "itemType", required = true) Integer itemType) {
		List<WelFareStock> lst = null;
		JSONObject json = new JSONObject();
		JSONArray jsonArr = null;
		try {

			itemColor = Constants.decoderUTF8(itemColor);

			lst = fareService.getSizeByColor(itemId, itemColor, itemType);
			jsonArr = JSONArray.fromObject(lst);
			json.accumulate("result", jsonArr);
		}
		catch (Exception e) {
			e.printStackTrace();
			log.error("扩展商品获取失败可能导致没发提交订单", e);
			json.accumulate("result", "err");
		}
		return json;

	}

	/**
	 * 获取库存等信息
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param itemId
	 * @param itemColor
	 * @param itemSize
	 * @param itemType
	 * @return
	 */
	@RequestMapping(value = "/welfare/getStock")
	@ResponseBody
	public Object getStock(HttpServletRequest request, HttpServletResponse response, Model model,
	        @RequestParam(value = "itemId", required = true) Integer itemId, @RequestParam(value = "itemColor", required = true) String itemColor,
	        @RequestParam(value = "itemSize", required = true) String itemSize, @RequestParam(value = "itemType", required = true) Integer itemType) {
		WelFareStock stockModel = null;
		JSONObject json = null;
		try {
			itemSize = Constants.decoderUTF8(itemSize);
			itemColor = Constants.decoderUTF8(itemColor);
			stockModel = fareService.getStockBy4Param(itemId, itemColor, itemSize, itemType);
			json = JSONObject.fromObject(stockModel);
		}
		catch (Exception e) {
			e.printStackTrace();
			log.error("扩展商品获取失败可能导致没发提交订单", e);
		}
		return json;

	}

	/**
	 * 生成订单
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param itemId
	 * @param itemColor
	 * @param itemSize
	 * @return
	 */

	@RequestMapping(value = "/welfare/createOrder")
	@ResponseBody
	public Object createOrder(HttpServletRequest request, HttpServletResponse response, Model model,
	        @RequestParam(value = "stockId", required = true) Integer stockId, @RequestParam(value = "itemId", required = true) Integer itemId,
	        @RequestParam(value = "itemColor", required = true) String itemColor, @RequestParam(value = "itemSize", required = true) String itemSize,
	        @RequestParam(value = "itemType", required = true) Integer itemType) {
		JSONObject json = new JSONObject();
		try {
			// 判断是否是特定会员
			itemSize = Constants.decoderUTF8(itemSize);
			itemColor = Constants.decoderUTF8(itemColor);
			// 判断是否是特定会员
			LoginUserBean loginUser = new SSOAgent(request, response).loginUserInfo("mall");
			Long userId = null;
			String mobile = null;
			Object orderId = null;
			if (loginUser != null) {
				userId = loginUser.getId();
				mobile = loginUser.getTerminalId();
				if (mobile == null || !mobile.matches("^\\d{11}$")) {
					json.accumulate("info", "404");
				} else {

					WelFareModel welfareModel = fareService.confirm(stockId, itemId, itemColor, itemSize, itemType);
					if (welfareModel != null) {
						Integer stockNum = welfareModel.getStockNumber();
						if (stockNum >= 1) {
							if (welfareModel.getIsValid() != null && welfareModel.getIsValid() == 1) {
								orderId = fareService.createOrder(userId, mobile, welfareModel);
								json.accumulate("info", "200");
								json.accumulate("orderId", orderId);
							} else {
								json.accumulate("info", "101");
							}
						} else {
							json.accumulate("info", "000");
						}
					}

				}
			}

			// 库存

		}
		catch (Exception e) {
			e.printStackTrace();
			log.error("生成订单异常", e);
			json.accumulate("info", "500");
		}

		return json;
	}

	/**
	 * 判断是否登录
	 * 
	 * @param request
	 * @param response
	 * @return 0 异常 1 登陆 2: 未登陆 3:不是目标用户
	 */
	public int isLogin(HttpServletRequest request, HttpServletResponse response) {

		int flag = 0;
		try {
			LoginUserBean loginUser = new SSOAgent(request, response).loginUserInfo("mall");
			if (loginUser != null) {
				String mobile = loginUser.getTerminalId();
				logger.info("welfare登录手机号: " + mobile);
				if (mobile != null && fareService.checkMobile(mobile)) {
					flag = 1;
				} else {
					flag = 3;
				}
			} else {
				flag = 2;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return flag;

	}

}
