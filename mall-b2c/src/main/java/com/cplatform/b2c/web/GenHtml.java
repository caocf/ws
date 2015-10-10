package com.cplatform.b2c.web;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.LazyDynaBean;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;

import com.cplatform.b2c.model.ItemEventBean;
import com.cplatform.b2c.model.ItemStaticBean;
import com.cplatform.b2c.model.VSearchGood;
import com.cplatform.b2c.service.ItemService;
import com.cplatform.b2c.service.VSearchGoodService;
import com.cplatform.b2c.util.AppConfig;
import com.cplatform.b2c.util.PathUtil;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 标题、简要说明.
 * <p>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2013-5-29 上午10:50:00
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author zhengmz@c-platform.com
 * @version 1.0.0
 */

@Controller
public class GenHtml {

	/** 日志记录器 */
	private final Logger logger = Logger.getLogger(getClass());

	@Autowired
	private ItemService itemService;

	@Autowired
	private VSearchGoodService vSearchGoodService;

	@Autowired
	private AppConfig appConfig;

	@Autowired
	private PathUtil pathUtil;

	@RequestMapping(value = "/notice/item/test")
	@ResponseBody
	public String test(Model model) {
		String result = "";
		ItemEventBean itemEventBean = new ItemStaticBean();

		itemEventBean.setAfter_service("test");
		itemEventBean.setItem_intro("test");
		itemEventBean.setItem_mode("0");
		itemEventBean.setItem_name("test");
		itemEventBean.setItem_no("1");
		String[] imgs = { "1.jpg" };
		itemEventBean.setImgs(imgs);

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("key1", "value1");

		itemEventBean.setItem_param(params);
		itemEventBean.setItem_type("1");
		itemEventBean.setPay_hint("test");
		itemEventBean.setPay_method("test");
		itemEventBean.setShop_id("1");
		itemEventBean.setShop_name("test");

		JSONObject itemObj = new JSONObject().fromObject(itemEventBean);

		try {

			URL url = new URL("http://192.168.1.14/mall-b2c/notice/item/gen.chtml");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setRequestProperty("event-transaction-id", "1000");
			connection.setRequestProperty("event-type-id", "1000");
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

		}

		return result;

	}

	@RequestMapping(value = "/item/pic/batch")
	public String genItemBatchPic(Model model) {

		File f = new File("/webapp/home/mall/template/id.txt");
		List<String> list = new ArrayList<String>();
		try {
			list = FileUtils.readLines(f);
		}
		catch (IOException e1) {
			e1.printStackTrace();
		}

		for (String id : list) {

			VSearchGood searchGood = vSearchGoodService.get(id);
			if (searchGood != null) {
				if (!StringUtils.isEmpty(searchGood.getGWebPath())) {

					File f1 = new File(searchGood.getGWebPath());

					String name = f1.getName().toLowerCase();

					File srcFile = new File("/suzhoumall/" + searchGood.getGWebPath());

					for (int i = 0; i <= 5; i++) {

						File destFile = new File("/home/mall" + pathUtil.getSavePathById(PathUtil.TYPE_ITEM_PIC, searchGood.getId().getGId()) + "N"
						        + i + "/" + name);

						try {
							FileUtils.copyFile(srcFile, destFile);
						}
						catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						logger.info("/suzhoumall/" + searchGood.getGWebPath() + "-->" + "/home/mall"
						        + pathUtil.getSavePathById(PathUtil.TYPE_ITEM_PIC, searchGood.getId().getGId()) + "N" + i + "/" + name);
					}

				}
			}
		}

		return "";
	}

	@RequestMapping(value = "/item/batch")
	public String genItemBatch(Model model) {

		try {
			File f = new File("/webapp/home/mall/template/id.txt");
			List<String> list = FileUtils.readLines(f);

			for (String id : list) {

				VSearchGood searchGood = vSearchGoodService.get(id);

				if (searchGood != null) {

					DynaBean bean = new LazyDynaBean();

					int sid = searchGood.getId().getGId();
					
					bean.set("path", appConfig.getB2c_Item_SaveRootPath() + pathUtil.getSavePathById(PathUtil.TYPE_ITEM, sid));

					HashMap<String, Object> map = new HashMap<String, Object>();
					map.put("itemNameforJs", StringEscapeUtils.escapeJavaScript(searchGood.getGName()));
					map.put("item_name", searchGood.getGName());
					map.put("item_no", sid);

					map.put("pay_method",
					        "<span>商城币</span><span>支付宝</span> <span>农行网银</span> <span>手机支付</span><a href='http://mall.12580life.com:8080/ExPlatWeb/' target='_blank'>积分/M值兑换</a>");

					map.put("pay_hint", "本商品支持现金和商城币同时支付,其中商城币仅限苏州市移动手机用户。");

					String[] other_imgs = new String[1];
					if (searchGood.getGWebPath() != null) {
						File f1 = new File(searchGood.getGWebPath());

						String name = f1.getName().toLowerCase();
						other_imgs[0] = name;
					}
					HashMap<String, String> params = new HashMap<String, String>();
					params.put("key1", "value1");

					map.put("item_param", params);

					map.put("img_path", pathUtil.getPathById(PathUtil.TYPE_ITEM_PIC, sid));
					map.put("imgs", other_imgs);
					map.put("shop_name", searchGood.getGStoreName());
					map.put("shop_url", pathUtil.getPathById(PathUtil.TYPE_SHOP, Long.valueOf(searchGood.getGStoreId())));

					map.put("item_intro", searchGood.getGRemark());
					// map.put("item_param", searchGood.getGTags());

					map.put("adid", "112");

					map.put("nav_url",
					        "<div class='position'>您所在的位置&gt;<a href='/pc_l7r81258.htm'>手机数码</a>&gt;<a href='http://mall.12580life.com/mobilegc.htm'>G3天地</a>&gt;<a href='/pc_c83o3336.htm'>品牌手机</a>&gt;<a href='/pc_htbm3337.htm'>诺基亚</a>&gt; 商品详情</div>");
					map.put("after_service", "售后服务");

					map.put("webRoot", appConfig.getWebRoot());
					map.put("imgSvrHost", appConfig.getImgSvrHost());

					map.put("item_mode", "0");

					bean.set("map", map);

					JSONObject obj = new JSONObject().fromObject(bean);

					URL url = new URL("http://localhost:8080/StaticService/template/event/sync/");
					HttpURLConnection connection = (HttpURLConnection) url.openConnection();
					connection.setDoInput(true);
					connection.setDoOutput(true);
					connection.setRequestProperty("event-transaction-id", "1000");
					connection.setRequestProperty("event-type-id", "1000");
					connection.setRequestProperty("Content-Length", Integer.toString(obj.toString().length()));
					OutputStream outputStream = connection.getOutputStream();

					outputStream.write(obj.toString().getBytes("UTF-8"));
					outputStream.flush();
					outputStream.close();

					int responseCode = connection.getResponseCode();
					// / String responseMessage =
					// connection.getResponseMessage();

					if (responseCode == HttpURLConnection.HTTP_OK) {
						byte[] responseData = IOUtils.toByteArray(connection.getInputStream());
						String responseText = IOUtils.toString(responseData, "UTF-8");
						logger.debug(responseText);
					}
					connection.disconnect();
				}

			}

		}
		catch (Exception e) {
			logger.error(e.toString());
			e.printStackTrace();
		}

		return "";
	}

	@RequestMapping(value = "/item/gen")
	@ResponseBody
	public String genItem(@RequestParam("name") String filename, Model model) {

		itemService.getBasicInfo("1");

		String path = ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/");

		String s_path = path + "\\template";
		String i_path = path + "\\item\\" + filename + ".htm";

		Configuration cfg = new Configuration();
		Template template;

		try {
			cfg.setDirectoryForTemplateLoading(new File(s_path));
			cfg.setLocale(java.util.Locale.CHINESE);
			cfg.setNumberFormat("0.00");

			cfg.setDefaultEncoding("utf-8");
			template = cfg.getTemplate(filename + ".ftl");

			File file = new File(i_path);

			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(i_path), "utf-8"));

			template.setOutputEncoding("utf-8");

			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("item_name", "电水'壶");
			map.put("itemNameforJs", StringEscapeUtils.escapeJavaScript("电水'壶"));
			map.put("item_no", "200072");
			map.put("img_path", "/img/item/1234");
			map.put("pay_method",
			        "<span>商城币</span><span>支付宝</span> <span>农行网银</span> <span>手机支付</span><a href='http://mall.12580life.com:8080/ExPlatWeb/' target='_blank'>积分/M值兑换</a>");

			map.put("pay_hint", "本商品支持现金和商城币同时支付,其中商城币仅限苏州市移动手机用户。");

			String[] other_imgs = new String[5];
			other_imgs[0] = "001.jpg";
			other_imgs[1] = "002.jpg";
			other_imgs[2] = "003.jpg";
			other_imgs[3] = "004.jpg";
			other_imgs[4] = "005.jpg";

			map.put("imgs", other_imgs);
			map.put("shop_name", "我的小店");
			map.put("shop_url", "http://mall.12580life.com/store/web/index.jsp?storeId=szlz");

			map.put("item_intro",
			        "<div class='i_new_infoCont' id='imgShow' style='display: block;'><p><img border='0' src='http://183.213.31.6/userfiles/product/img/20130430/editor/1367315626635.jpg'></p><p><img border='0' src='http://183.213.31.6/userfiles/product/img/20130430/editor/1367315636939.jpg'></p><p><img border='0' src='http://183.213.31.6/userfiles/product/img/20130430/editor/1367315649877.jpg'></p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p></div>");

			HashMap<String, String> params = new HashMap<String, String>();
			params.put("商品品牌", "诺基亚");
			params.put("商品型号", "--");
			params.put("外观", "直板");
			params.put("商品型号1", "--");
			params.put("外观1", "直板");
			params.put("外观2", "直板");
			map.put("item_param", params);
			map.put("nav_url",
			        "<div class='position'>您所在的位置&gt;<a href='/pc_l7r81258.htm'>手机数码</a>&gt;<a href='http://mall.12580life.com/mobilegc.htm'>G3天地</a>&gt;<a href='/pc_c83o3336.htm'>品牌手机</a>&gt;<a href='/pc_htbm3337.htm'>诺基亚</a>&gt; 商品详情</div>");
			map.put("adid", "112");

			map.put("nav_url",
			        "<div class='position'>您所在的位置&gt;<a href='/pc_l7r81258.htm'>手机数码</a>&gt;<a href='http://mall.12580life.com/mobilegc.htm'>G3天地</a>&gt;<a href='/pc_c83o3336.htm'>品牌手机</a>&gt;<a href='/pc_htbm3337.htm'>诺基亚</a>&gt; 商品详情</div>");
			map.put("after_service", "售后服务");
			map.put("item_mode", "0");
			map.put("webRoot", appConfig.getWebRoot());
			map.put("shop_id", "123");

			template.process(map, out);

		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e.toString();
		}
		return "生成成功！";

	}

	@RequestMapping(value = "/item/genVirtual")
	@ResponseBody
	public String genVirtualItem(@RequestParam("name") String filename, Model model) {

		itemService.getBasicInfo("1");

		String path = ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/");

		String s_path = path + "\\template\\";
		String i_path = path + "\\item\\" + filename + ".htm";

		Configuration cfg = new Configuration();
		Template template;

		try {
			cfg.setDirectoryForTemplateLoading(new File(s_path));
			cfg.setLocale(java.util.Locale.CHINESE);
			cfg.setNumberFormat("0.00");

			cfg.setDefaultEncoding("utf-8");
			template = cfg.getTemplate(filename + ".ftl");

			File file = new File(i_path);

			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(i_path), "utf-8"));

			// TODO 读取编码
			template.setOutputEncoding("utf-8");

			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("item_name", "电水'壶");
			map.put("itemNameforJs", StringEscapeUtils.escapeJavaScript("电水'壶"));
			map.put("item_no", "200072");
			map.put("img_path", "/img/item/1234");
			map.put("pay_method",
			        "<span>商城币</span><span>支付宝</span> <span>农行网银</span> <span>手机支付</span><a href='http://mall.12580life.com:8080/ExPlatWeb/' target='_blank'>积分/M值兑换</a>");

			map.put("pay_hint", "本商品支持现金和商城币同时支付,其中商城币仅限苏州市移动手机用户。");

			String[] other_imgs = new String[5];
			other_imgs[0] = "001.jpg";
			other_imgs[1] = "002.jpg";
			other_imgs[2] = "003.jpg";
			other_imgs[3] = "004.jpg";
			other_imgs[4] = "005.jpg";

			map.put("imgs", other_imgs);
			map.put("shop_name", "我的小店");
			map.put("shop_url", "http://mall.12580life.com/store/web/index.jsp?storeId=szlz");

			map.put("item_intro",
			        "<div class='i_new_infoCont' id='imgShow' style='display: block;'><p><img border='0' src='http://183.213.31.6/userfiles/product/img/20130430/editor/1367315626635.jpg'></p><p><img border='0' src='http://183.213.31.6/userfiles/product/img/20130430/editor/1367315636939.jpg'></p><p><img border='0' src='http://183.213.31.6/userfiles/product/img/20130430/editor/1367315649877.jpg'></p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p></div>");

			map.put("item_param",
			        "<div class='new_guige'><table width='98%'><tbody><tr><td align='right'><span style='color:#AAAAAA;'>商品品牌：</span></td><td>诺基亚</td><td align='right'><span style=' color:#AAAAAA;'>商品型号：</span></td><td>--</td></tr><tr><td align='right' width='200'><span style='color:#AAAAAA;'>外观：</span></td><td>直板</td><td align='right' width='200'><span style=' color:#AAAAAA;'>操作系统：</span></td><td>--</td></tr><tr><td align='right' width='200'><span style='color:#AAAAAA;'>上市日期：</span></td><td>--</td><td align='right' width='200'><span style=' color:#AAAAAA;'>智能/非智能机：</span></td><td>智能机</td></tr></tbody></table></div>");
			String add = "{\"addresses\":[{\"city\":\"苏州\",\"shops\":[{\"URL\":\"\",\"cityCode\":\"0512\",\"shopAdrr\":\"食色无双\",\"shopId\":\"1000064\",\"shopName\":\"周的验证门店\",\"shopTel\":\"26566366\"},{\"URL\":\"\",\"cityCode\":\"0512\",\"shopAdrr\":\"XXX\",\"shopId\":\"1000040\",\"shopName\":\"测试门店\",\"shopTel\":\"1529456464\"}]},{\"city\":\"无锡\",\"shops\":[{\"URL\":\"\",\"cityCode\":\"0510\",\"shopAdrr\":\"打算\",\"shopId\":\"1000063\",\"shopName\":\"门店测试\",\"shopTel\":\"15134567874\"}]}]}";
			JSONObject obj = JSONObject.fromObject(add);
			JSONArray addr = obj.getJSONArray("addresses");
			Map<String, List<String[]>> addresses = new HashMap<String, List<String[]>>();
			for (int i = 0; i < addr.size(); i++) {
				JSONObject adde = addr.getJSONObject(i);
				String key = adde.getString("city");
				JSONArray shops = adde.getJSONArray("shops");
				List<String[]> addrs = new ArrayList<String[]>();
				for (int j = 0; j < shops.size(); j++) {
					JSONObject shop = shops.getJSONObject(j);
					String url = shop.getString("URL");
					String cityCode = shop.getString("cityCode");
					String shopAddr = shop.getString("shopAdrr");
					String shopId = shop.getString("shopId");
					String shopName = shop.getString("shopName");
					String shopTel = shop.getString("shopTel");
					addrs.add(new String[] { shopId, cityCode, shopName, shopAddr, shopTel, url });
				}
				addresses.put(key, addrs);
			}
			map.put("addresses", addresses);
			map.put("nav_url",
			        "<div class='position'>您所在的位置&gt;<a href='/pc_l7r81258.htm'>手机数码</a>&gt;<a href='http://mall.12580life.com/mobilegc.htm'>G3天地</a>&gt;<a href='/pc_c83o3336.htm'>品牌手机</a>&gt;<a href='/pc_htbm3337.htm'>诺基亚</a>&gt; 商品详情</div>");
			map.put("adid", "112");

			map.put("nav_url",
			        "<div class='position'>您所在的位置&gt;<a href='/pc_l7r81258.htm'>手机数码</a>&gt;<a href='http://mall.12580life.com/mobilegc.htm'>G3天地</a>&gt;<a href='/pc_c83o3336.htm'>品牌手机</a>&gt;<a href='/pc_htbm3337.htm'>诺基亚</a>&gt; 商品详情</div>");
			map.put("after_service", "售后服务");
			map.put("item_mode", "0");
			map.put("webRoot", appConfig.getWebRoot());
			map.put("shop_id", "123");
			map.put("sale_num", "123");
			template.process(map, out);

		}
		catch (Exception e) {
			e.printStackTrace();
			return e.toString();
		}
		return "生成成功！";

	}

	@RequestMapping(value = "/ad/t")
	@ResponseBody
	public void ad(@RequestParam("adid") String adid, @RequestParam("eid") String eid, HttpServletResponse response) {

		response.setContentType("text/html; charset=GBK");
		String d = "document.write('<div class='fb-welcome'><em style='color:#666;float:left;'>您好，欢迎来到移动商城！</em><select id='loginType' style='padding:2px;float:left; font-size:12px; width:100px; margin:4px 5px 0; *margin:6px 5px 0;'><option value='2' >移动手机用户</option><option value='1'>互联网用户</option></select><input type='button' value='' onclick='loginMall();' style='width:44px; height:20px; background:url(/image/loginnewbtn.gif) no-repeat;float:left; border:0 none;  margin-top:6px; cursor:pointer; margin-right:7px' /><a style='float:left;' href='http://u.12580life.com/zc.html?channel=00100&backurl=http://mall.12580life.com/'>免费注册</a></div>');";
		String k = "document.getElementById('"
		        + eid
		        + "').innerHTML='<div class=\"fb-welcome\"><em style=\"color:#666;float:left;\">您好，欢迎来到移动商城！</em><select id=\"loginType\" style=\"padding:2px;float:left; font-size:12px; width:100px; margin:4px 5px 0; *margin:6px 5px 0;\"><option value=\"2\" >移动手机用户</option><option value=\"1\">互联网用户</option></select><input type=\"button\" value=\"\" onclick=\"loginMall();\" style=\"width:44px; height:20px; background:url(/image/loginnewbtn.gif) no-repeat;float:left; border:0 none;  margin-top:6px; cursor:pointer; margin-right:7px\" /><a style=\"float:left;\" href=\"http://u.12580life.com/zc.html?channel=00100&backurl=http://mall.12580life.com/\">免费注册</a></div>' ";
		String n = "document.getElementById('"
		        + eid
		        + "').innerHTML='"
		        + "<table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tbody><tr align=\"center\"><td align=\"center\"><a href=\"http://mall.12580life.com/mobilegc-p_635729342.htm\" target=\"_blank\">"
		        + "<img src=\"http://mall.12580life.com/userfiles/adcontent//img/0/1364454661948.jpg\" width=\"176\" height=\"248\" border=\"0\"></a></td></tr><tr align=\"center\"><td align=\"center\"><a href=\"http://mall.12580life.com/jsp/group/groupNew.jsp\" target=\"_blank\">"
		        + "<img src=\"http://mall.12580life.com/userfiles/adcontent//img/0/1364454661948.jpg\" width=\"176\" height=\"420\" border=\"0\"></a></td></tr><tr align=\"center\"><td align=\"center\"><a href=\"http://mall.12580life.com/contractPhone/contractPhone.jsp\" target=\"_blank\">"
		        + "<img src=\"http://mall.12580life.com/userfiles/adcontent//img/0/1364454661948.jpg\" width=\"176\" height=\"72\" border=\"0\"></a></td></tr><tr align=\"center\"><td align=\"center\"><a href=\"http://mall.12580life.com/p_u3nv18096.htm\" target=\"_blank\">"
		        + "<img src=\"http://mall.12580life.com/userfiles/adcontent//img/1/1363079641653.jpg\" width=\"176\" height=\"248\" border=\"0\"></a></td></tr><tr align=\"center\"><td align=\"center\"><a href=\"http://mall.12580life.com/p_6zh624527.htm\" target=\"_blank\">"
		        + "<img src=\"http://mall.12580life.com/userfiles/adcontent//img/0/1364454661948.jpg\" width=\"176\" height=\"248\" border=\"0\"></a></td></tr></tbody></table>'";

		String m = "var k = '{\"name\":\"ffff\",\"ad\":[{\"img\":\"http://mall.12580life.com/userfiles/adcontent//img/0/1364454661948.jpg\",\"url\":\"222333\"},{\"img\":\"http://mall.12580life.com/userfiles/adcontent//img/1/1363079641653.jpg\",\"url\":\"222333\"},{\"img\":\"http://mall.12580life.com/userfiles/adcontent//img/0/1364454661948.jpg\",\"url\":\"222333\"},{\"img\":\"http://mall.12580life.com/userfiles/adcontent//img/1/1363079641653.jpg\",\"url\":\"222333\"},{\"img\":\"http://mall.12580life.com/userfiles/adcontent//img/0/1364454661948.jpg\",\"url\":\"222333\"},{\"img\":\"http://mall.12580life.com/userfiles/adcontent//img/1/1363079641653.jpg\",\"url\":\"222333\"}]}\';";
		m = m + "displayAD(k,'" + eid + "');";

		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(m);

			out.flush();
		}
		catch (IOException e) {

			e.printStackTrace();
		}

	}
}
