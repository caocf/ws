package com.cplatform.b2c.web;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cplatform.b2c.service.CommonCacheService;
import com.cplatform.b2c.service.GoodsService;

/**
 * 代金券频道Controller. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2013-12-30 上午9:43:40
 * <p>
 * Company: 苏州宽连信息技术有限公司
 * <p>
 * 
 * @author zhangmeng@c-platform.com
 * @version 1.0.0
 */
@Controller
public class GoodsController {

	@Autowired
	GoodsService goodsService;

	@Autowired
	CommonCacheService commonCacheService;

	/** 每页显示条数，默认48 */
	private static final int GOODS_PAGE_SIZE = 48;

	/**
	 * 代金券频道首页查询
	 * 
	 * @param title
	 * @param regionCode
	 * @param subRegionCode
	 * @param goodsType
	 * @param subGoodsType
	 * @param price
	 * @param sort
	 * @param isNewOrder
	 * @param allowScore
	 * @param allowCoin
	 * @param allowBill
	 * @param curpage
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/goods/goodsList")
	public String queryGoodsList(@RequestParam(value = "title", defaultValue = "") String title,
	        @RequestParam(value = "regionCode", defaultValue = "") String regionCode,
	        @RequestParam(value = "subRegionCode", defaultValue = "") String subRegionCode,
	        @RequestParam(value = "goodsType", defaultValue = "") String goodsType,
	        @RequestParam(value = "subGoodsType", defaultValue = "") String subGoodsType,
	        @RequestParam(value = "price", defaultValue = "") String price, @RequestParam(value = "sort", defaultValue = "0") String sort,
	        @RequestParam(value = "isNewOrder", defaultValue = "") String isNewOrder,
	        @RequestParam(value = "allowScore", defaultValue = "") String allowScore,
	        @RequestParam(value = "allowCoin", defaultValue = "") String allowCoin,
	        @RequestParam(value = "allowBill", defaultValue = "") String allowBill,
	        @RequestParam(value = "curpage", defaultValue = "1") String curpage, HttpServletResponse response, Model model) {
		if (StringUtils.isEmpty(regionCode)) {
			regionCode = goodsService.getSessionUserArea(response);
		} else if (regionCode.length() > 6) {
			// mo通行证登陆后会在regionCode参数后带上ticket信息
			regionCode = regionCode.substring(0, 6);
		}
		model.addAttribute("hotTopicList", goodsService.getHotTopic(regionCode));
		goodsService.solrSearchGoods(title, regionCode, subRegionCode, goodsType, subGoodsType, price, sort, isNewOrder, allowScore, allowCoin,
		                             allowBill, curpage, GOODS_PAGE_SIZE, model);
		goodsService.createOrderUrlAndPayUrl(title, regionCode, subRegionCode, goodsType, subGoodsType, price, sort, isNewOrder, allowScore,
		                                     allowCoin, allowBill, curpage, model);
		model.addAttribute("title", title);
		model.addAttribute("regionCode", regionCode);
		model.addAttribute("regionName", commonCacheService.getRegionName(regionCode));
		model.addAttribute("sort", sort);
		model.addAttribute("isNewOrder", isNewOrder);
		model.addAttribute("allowScore", allowScore);
		model.addAttribute("allowCoin", allowCoin);
		model.addAttribute("allowBill", allowBill);
		model.addAttribute("pageSize", GOODS_PAGE_SIZE);
		return "goods/goods_list";
	}
}
