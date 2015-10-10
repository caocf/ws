package com.cplatform.b2c.web;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.LazyDynaBean;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cplatform.b2c.model.ItemEventBean;
import com.cplatform.b2c.model.ItemStaticBean;
import com.cplatform.b2c.service.VSearchGoodService;
import com.cplatform.b2c.util.AppConfig;
import com.cplatform.b2c.util.HttpClientUtils;
import com.cplatform.b2c.util.PathUtil;

/**
 * 标题、简要说明.
 * <p>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2013-6-19 上午10:48:14
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author zhengmz@c-platform.com
 * @version 1.0.0
 */
@Controller
public class NoticeController {

	/** 日志记录器 */
	private final Logger logger = Logger.getLogger(getClass());

	@Autowired
	private AppConfig appConfig;

	@Autowired
	private PathUtil pathUtil;

	@Autowired
	private VSearchGoodService vSearchGoodService;

	@RequestMapping(value = "/tools/shop")
	public String Shopforword(@RequestParam("storeId") String storeId, Model model) {

		return "redirect:" + appConfig.getWebRoot() + pathUtil.getPathById(PathUtil.TYPE_SHOP, Long.valueOf(storeId));
	}

	@RequestMapping(value = "/tools/item")
	public String forword(@RequestParam("saleId") String saleId, Model model) {

		return "redirect:" + appConfig.getWebRoot() + pathUtil.getPathById(PathUtil.TYPE_ITEM, Long.valueOf(saleId));
	}

	/*
	 * 商户最终页面生成通知接口
	 */
	@SuppressWarnings({ "static-access", "deprecation" })
	@RequestMapping(value = "/notice/item/gen")
	@ResponseBody
	public String genItem(HttpServletRequest request, Model model) {

		// 验证ip地址有效性，原则上是内网地址访问，不考虑代理透传
		if (!HttpClientUtils.isTrustIp(request.getRemoteAddr(), appConfig.getB2c_Index_TIP())) {

			return "{RET:0,MSG:'非法访问！'}";

		}

		String transactionId = request.getHeader("event-transaction-id");

		String preview = request.getHeader("is-preview");

		if (StringUtils.isEmpty(preview))
			preview = "0";

		String result = "";
		try {

			String eventTypeId = request.getHeader("event-type-id");
			String event = IOUtils.toString(request.getInputStream());

			// String event = IOUtils.toString(request.getInputStream(),
			// "UTF-8");
			logger.debug("------event-------:" + event);
			JSONObject obj = new JSONObject().fromObject(event);

			ItemEventBean itemEventBean = new ItemEventBean();
			itemEventBean = (ItemEventBean) JSONObject.toBean(obj, ItemEventBean.class);

			ItemStaticBean itemStaticBean = ItemStaticBean.newInstance(itemEventBean);
			if (preview.equals("0"))
				itemStaticBean.setWebRoot(appConfig.getWebRoot());
			else
				itemStaticBean.setWebRoot(appConfig.getServer_host().trim() + appConfig.getWebRoot());

			itemStaticBean.setImgSvrHost(appConfig.getImgSvrHost());

			itemStaticBean.setShop_url(pathUtil.getPathById(PathUtil.TYPE_SHOP, Long.valueOf(itemStaticBean.getShop_id())));

			itemStaticBean.setImg_path(pathUtil.getPathById(PathUtil.TYPE_ITEM_PIC1, Long.valueOf(itemStaticBean.getItem_no())));

			DynaBean bean = new LazyDynaBean();

			bean.set("path",
			         appConfig.getB2c_Item_SaveRootPath() + pathUtil.getSavePathById(PathUtil.TYPE_ITEM, Long.valueOf(itemStaticBean.getItem_no())));

			bean.set("map", itemStaticBean);

			JSONObject itemObj = new JSONObject().fromObject(bean);

			URL url = new URL(appConfig.getB2c_Item_Notice_Path());
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setRequestProperty("event-transaction-id", transactionId);
			connection.setRequestProperty("event-type-id", eventTypeId);
			connection.setRequestProperty("preview", preview);

			connection.setRequestProperty("Content-Length", Integer.toString(itemObj.toString().length()));
			OutputStream outputStream = connection.getOutputStream();

			outputStream.write(itemObj.toString().getBytes("UTF-8"));
			outputStream.flush();
			outputStream.close();

			int responseCode = connection.getResponseCode();

			if (responseCode == HttpURLConnection.HTTP_OK) {
				byte[] responseData = IOUtils.toByteArray(connection.getInputStream());
				result = IOUtils.toString(responseData, "UTF-8");
				logger.debug(result);
			}
			connection.disconnect();

		}
		catch (Exception e) {
			logger.error(e);
			return "{RET:1,MSG:'" + e.toString() + "'}";
		}

		return result;
	}
}
