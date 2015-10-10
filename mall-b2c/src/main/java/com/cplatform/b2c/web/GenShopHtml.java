package com.cplatform.b2c.web;

import java.io.File;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cplatform.b2c.constant.StoreIndexOrder;
import com.cplatform.b2c.model.TShopHomepageShow;
import com.cplatform.b2c.model.TShopSettings;
import com.cplatform.b2c.model.TStore;
import com.cplatform.b2c.service.AreaService;
import com.cplatform.b2c.service.ShopService;
import com.cplatform.b2c.util.AppConfig;
import com.cplatform.b2c.util.PathUtil;

/**
 * 商户展示Controller
 * <p>
 * Copyright: Copyright (c) 2013-5-31 下午05:04:46
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author zouyl@c-platform.com
 * @version 1.0.0
 */
@Controller
public class GenShopHtml extends BaseController {

	/**
	 * 商户分类：1--业务门店 2--结算商户 3--渠道商
	 **/
	private final static Integer shopClass = 2;

	private final Map<String, String> nullMap = new HashMap<String, String>();

	@Autowired
	private ShopService shopService;

	@Autowired
	private AreaService areaService;

	@Autowired
	private AppConfig appConfig;

	@Autowired
	private PathUtil pathUtil;

	@RequestMapping(value = "/shop/gen/batch")
	@ResponseBody
	public String genItemBatch(Model model) {

		File f = new File(appConfig.getB2c_Item_SaveRootPath() + "template/shopid.txt");
		List<String> list = new ArrayList<String>();
		try {
			list = FileUtils.readLines(f);

			for (String id : list) {

				genItem(id, null, model);
				genItemAll(id, model);

			}
		}
		catch (Exception e1) {
			e1.printStackTrace();
		}
		return "生成成功！";

	}

	@RequestMapping(value = "/notice/shop/gen")
	@ResponseBody
	public String genShop(@RequestParam("shopId") String shopId, Model model) {

		try {
			genItem(shopId, null, model);
			genItemAll(shopId, model);
		}
		catch (Exception e) {

			e.printStackTrace();
			return "{RET:1,MSG:'" + e.toString() + "'}";
		}

		return "{RET:0,MSG:'生成成功！'}";
	}

	@RequestMapping(value = "/shop/gen")
	@ResponseBody
	public String genItem(@RequestParam("shopId") String shopId, @RequestParam(value = "action", required = false) String action, Model model)
	        throws Exception {
		this.setPath("shop", appConfig.getB2c_Item_SaveRootPath() + pathUtil.getSavePathById(PathUtil.TYPE_SHOP, Long.valueOf(shopId)));

		HashMap<String, Object> map = new HashMap<String, Object>();

		TStore tStore = shopService.getStore(shopId);
		if (tStore == null) {
			return "商铺信息不存在";
		}

		TShopSettings tShopSettings = shopService.getTShopSettings(Integer.parseInt(shopId), shopClass);
		List<TShopHomepageShow> floorList = shopService.getShelfList(Integer.parseInt(shopId), shopClass);

		map.put("shopId", shopId);
		map.put("store", tStore);
		String areaCode = tStore.getAreaCode() == null ? null : tStore.getAreaCode().toString();
		String areaFullName = areaService.getFullName(areaCode);
		map.put("areaFullName", areaFullName);

		map.put("shopSet", tShopSettings == null ? nullMap : tShopSettings);
		map.put("floorList", floorList);

		map.put("shop_url", pathUtil.getPathById(PathUtil.TYPE_SHOP, Long.valueOf(shopId)));
		map.put("shop2_url", pathUtil.getPathById(PathUtil.TYPE_SHOP_2, Long.valueOf(shopId)));
		map.put("action", action);
		if ("preview".equals(action)) {
			StringWriter strWriter = this.getResponseData(map);
			return strWriter.toString();
		} else {
			try {
				this.setResponseData(map);
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "生成成功！";
	}

	@RequestMapping(value = "/shop/gen_all")
	@ResponseBody
	public String genItemAll(@RequestParam("shopId") String shopId, Model model) throws Exception {

		this.setPath("shop_all", appConfig.getB2c_Item_SaveRootPath() + pathUtil.getSavePathById(PathUtil.TYPE_SHOP_2, Long.valueOf(shopId)));

		HashMap<String, Object> map = new HashMap<String, Object>();
		TShopSettings tShopSettings = shopService.getTShopSettings(Integer.parseInt(shopId), shopClass);
		TStore tStore = shopService.getStore(shopId);
		String areaCode = tStore.getAreaCode() == null ? null : tStore.getAreaCode().toString();
		String areaFullName = areaService.getFullName(areaCode);

		map.put("shopId", shopId);
		map.put("store", tStore);
		map.put("areaFullName", areaFullName);
		map.put("shopSet", tShopSettings == null ? nullMap : tShopSettings);

		map.put("shop_url", pathUtil.getPathById(PathUtil.TYPE_SHOP, Long.valueOf(shopId)));
		map.put("shop2_url", pathUtil.getPathById(PathUtil.TYPE_SHOP_2, Long.valueOf(shopId)));

		map.put("storeOrderMap", StoreIndexOrder.map);

		try {
			this.setResponseData(map);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return "生成成功！";
	}

}
