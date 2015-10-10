package com.cplatform.b2c.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cplatform.b2c.constant.StoreIndexOrder;
import com.cplatform.b2c.model.PageInfo;
import com.cplatform.b2c.model.TGoodShelf;
import com.cplatform.b2c.model.TGoodshelfGoodsRel;
import com.cplatform.b2c.model.VSearchGood;
import com.cplatform.b2c.service.ShopService;
import com.cplatform.b2c.util.AppConfig;
import com.cplatform.b2c.util.PathUtil;

/**
 * 商户相关控制方法 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2013-6-1 上午10:14:34
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author zouyl@c-platform.com
 * @version 1.0.0
 */
@Controller
public class ShopController {

	@Autowired
	private ShopService shopService;

	@Autowired
	AppConfig appConfig;

	@Autowired
	private PathUtil pathUtil;

	private final static Integer shopClass = 2;

	/**
	 * 获取商户分类数据
	 * 
	 * @param response
	 * @param shopId
	 */
	@RequestMapping(value = "/shop/sorts")
	public void getSorts(HttpServletResponse response, @RequestParam("shopId") String shopId) {
		response.setContentType("text/json; charset=UTF-8");
		Map<String, String> allSort = new TreeMap<String, String>();
		Map<String, String> imageMap = new HashMap<String, String>();

		List<TGoodShelf> list = shopService.getList(shopId);
		Integer index = 10000;
		for (TGoodShelf t : list) {
			allSort.put(index.toString() + "_" + t.getId().toString() + "_" + t.getTitle(), t.getPid().toString());
			if (t.getImgUrl() != null && !StringUtils.isEmpty(t.getImgUrl())) {
				imageMap.put(t.getId().toString(), appConfig.getImgSvrHost() + appConfig.getB2c_Store_Shelf_Path() + t.getImgUrl());
			}
			index++;
		}

		Map<String, Map<String, String>> sorts = new HashMap<String, Map<String, String>>();

		sorts.put("allSort", allSort);
		sorts.put("imageMap", imageMap);

		PrintWriter out;
		try {
			out = response.getWriter();
			JSONObject json = JSONObject.fromObject(sorts);
			if (json != null) {
				out.print(json);
			}
			out.flush();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取商户分类数据
	 * 
	 * @param response
	 * @param shopId
	 */
	@RequestMapping(value = "/shop/floor")
	public void getFloor(HttpServletResponse response, @RequestParam("floorId") String floorId, @RequestParam("goodNum") String goodNum,
	        @RequestParam("storeId") String storeId) {
		response.setContentType("text/json; charset=UTF-8");

		if (goodNum == null || "".equals(goodNum)) {
			goodNum = "0";
		}

		List<VSearchGood> productList = new ArrayList<VSearchGood>();
		if ("0".equals(floorId)) {
			productList = shopService.getProductListBy(Integer.parseInt(storeId), null, null, null, null, StoreIndexOrder.valueOf(3), 0, 12);
		} else {
			Integer[] ids = new Integer[] {};
			List<TGoodshelfGoodsRel> goodList = shopService.getShelfGoodList(Integer.parseInt(floorId));
			for (TGoodshelfGoodsRel good : goodList) {
				ids = (Integer[]) ArrayUtils.add(ids, good.getGoodId());
			}
			productList = shopService.getProductList(ids, Integer.parseInt(goodNum));
		}

		List<Map<String, String>> retList = new ArrayList<Map<String, String>>();
		if (productList != null) {
			for (VSearchGood good : productList) {
				Map<String, String> product = new HashMap<String, String>();
				product.put("img", pathUtil.getPathById(2, good.getId().getGId()) + "N3/" + good.getGWebPath());
				product.put("marketPrice", good.getGMarketPrice());
				product.put("price", good.getGShopPrice().toString());
				product.put("redPrice", good.getGRedPrice());
				product.put("title", good.getGName());
				product.put("discountFlag", "1");
				product.put("path", pathUtil.getPathById(1, good.getId().getGId()));
				product.put("marketContent", good.getGMarketContent());
				product.put("source", good.getGSource());
				product.put("iseckill", good.getGIseckill().toString());
				retList.add(product);
			}
		}

		PrintWriter out;
		try {
			out = response.getWriter();
			JSONArray json = JSONArray.fromObject(retList);
			if (json != null) {
				out.print(json);
			}
			out.flush();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取商户分类数据
	 * 
	 * @param response
	 * @param shopId
	 */
	@RequestMapping(value = "/shop/page")
	public void getOnePage(HttpServletResponse response, @RequestParam("curpage") String curpage, @RequestParam("storeId") Integer GStoreId,
	        @RequestParam("name") String GName, @RequestParam("priceLow") String GShopPriceLow, @RequestParam("priceHigh") String GShopPriceHigh,
	        @RequestParam("shelfId") String shelfId, @RequestParam("storeOrder") String storeOrder) {
		response.setContentType("text/json; charset=UTF-8");

		int firstResult = (Integer.valueOf(curpage) - 1) * 12;
		int maxResults = 12;
		Integer[] GIds = null;
		boolean nullFlag = false;
		Integer totalCount;
		List<Map<String, String>> retList = new ArrayList<Map<String, String>>();

		if (shelfId != null && !"".equals(shelfId)) {
			GIds = shopService.getGIdsByShelfId(Integer.parseInt(shelfId), shopClass);
			if (GIds == null || GIds.length == 0) {
				nullFlag = true;
			}
		}

		if (storeOrder == null || "".equals(storeOrder)) {
			storeOrder = "3";// 最新上架为默认排序方式
		}

		int storeOrderInt = Integer.parseInt(storeOrder);

		if (nullFlag) {
			totalCount = 0;
		} else {
			totalCount = shopService.getProductCountBy(GStoreId, GName, GShopPriceLow, GShopPriceHigh, GIds);
			List<VSearchGood> productList = shopService.getProductListBy(GStoreId, GName, GShopPriceLow, GShopPriceHigh, GIds,
			                                                             StoreIndexOrder.valueOf(storeOrderInt), firstResult, maxResults);

			for (VSearchGood good : productList) {
				Map<String, String> product = new HashMap<String, String>();
				product.put("img", pathUtil.getPathById(2, good.getId().getGId()) + "N3/" + good.getGWebPath());
				product.put("marketPrice", good.getGMarketPrice());
				product.put("price", good.getGShopPrice().toString());
				product.put("redPrice", good.getGRedPrice());
				product.put("title", good.getGName());
				product.put("discountFlag", "1");
				product.put("path", pathUtil.getPathById(1, good.getId().getGId()));
				product.put("createTime", good.getGCreateTime());
				product.put("marketContent", good.getGMarketContent());
				product.put("source", good.getGSource());
				product.put("iseckill", good.getGIseckill().toString());
				retList.add(product);
			}
		}

		PageInfo pageInfo = new PageInfo(Integer.valueOf(curpage), totalCount, maxResults);
		String pageStr = pageInfo.getScript();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("productList", retList);
		map.put("pageStr", pageStr);

		PrintWriter out;
		try {
			out = response.getWriter();
			JSONObject json = JSONObject.fromObject(map);
			if (json != null) {
				out.print(json);
			}
			out.flush();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

}
