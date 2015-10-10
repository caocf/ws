package com.cplatform.mall.back.utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplatform.mall.back.model.ItemEventBean;

/**
 * 商品页面静态化接口 Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-6-19 下午5:30:54
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: LiuDong@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
@Service
public class PageStaticInterface {
	private static final Logger log = Logger.getLogger(PageStaticInterface.class);

	@Autowired
	private AppConfig config;
	
	@Autowired
	private LogUtils logUtils;

	/**
	 * 生成商户门店
	 * 
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	public String makeStoreShop(Long id) throws Exception {
		String msg = "";
		String url = config.getMakeStoreShopUrl() + id;
		HttpURLConnection con = null;
		OutputStream out = null;
		BufferedReader br = null;
		try {
			con = (HttpURLConnection) new URL(url).openConnection();
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setRequestMethod("GET");
			con.setConnectTimeout(1000 * 10);
			out = con.getOutputStream();
			out.flush();
			out.close();
			br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
			final StringBuffer str = new StringBuffer(255);
			String line = "";
			while ((line = br.readLine()) != null) {
				str.append(line);
			}
			log.info("调用生成商户门店的URL："+ url);
			log.info("生成商户门店接口返回的信息:" + str.toString());
			JSONObject json = JSONObject.fromObject(str.toString());
			msg = json.getString("MSG");
		}
		catch (Exception e) {
			logUtils.logAdd("调生成商户门店接口失败：", e.getMessage());
			throw new Exception("调生成商户门店接口失败："+e.getMessage());
		}
		finally {
			if (out != null) {
				try {
					out.flush();
					out.close();
				}
				catch (IOException e) {
					log.error(e.getMessage());
				}
			}
			if (br != null) {
				try {
					br.close();
				}
				catch (IOException e) {
					log.error(e.getMessage());
				}
			}
		}
		return msg;

	}

	/**
	 * 商品页面静态化
	 * 
	 * @param obj
	 * @return
	 * @throws Exception 
	 */
	public String pageStatic(ItemEventBean obj) throws Exception {
		String id = "";
		String itemMode = obj.getItem_mode();
		if("0".equals(itemMode)){
			id = "1000";
		}
		if("1".equals(itemMode)){
			id="1001";
		}
		String msg = "";
		String url = config.getPageStaticUrl();
		HttpURLConnection con = null;
		OutputStream out = null;
		BufferedReader br = null;
		try {
			JSONObject jsonObject = JSONObject.fromObject(obj);
			String code = jsonObject.toString();
			log.info("发送给商品页面静态化接口的数据: " + code);
			byte[] data = code.getBytes("utf-8");

			con = (HttpURLConnection) new URL(url).openConnection();
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setRequestMethod("POST");
			con.setConnectTimeout(1000 * 10);
			con.setRequestProperty("event-transaction-id", id);
			con.setRequestProperty("event-type-id", id);
			con.setRequestProperty("Content-Length", String.valueOf(data.length));
			con.setRequestProperty("Content-type", "text/html");

			out = con.getOutputStream();
			out.write(data);
			out.flush();
			out.close();
			br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
			final StringBuffer str = new StringBuffer(255);
			String line = "";
			while ((line = br.readLine()) != null) {
				str.append(line);
			}
			log.info("商品页面静态化接口返回的数据：" + str.toString());
			JSONObject json = JSONObject.fromObject(str.toString());
			msg = json.getString("MSG");
		}
		catch (Exception e) {
			logUtils.logAdd("调用页面静态化接口失败", e.getMessage());
			throw new Exception("调用页面静态化接口失败："+e.getMessage());
		}
		finally {
			if (out != null) {
				try {
					out.flush();
					out.close();
				}
				catch (IOException e) {
					log.error(e.getMessage());
				}
			}
			if (br != null) {
				try {
					br.close();
				}
				catch (IOException e) {
					log.error(e.getMessage());
				}
			}
		}
		return msg;
	}
	
	
	
	/**
	 * 商品预览接口
	 * 
	 * @param obj
	 * @return
	 * @throws Exception 
	 */
	public String pageBrowse(ItemEventBean obj) throws Exception {
		String id = "";
		String itemMode = obj.getItem_mode();
		if("0".equals(itemMode)){
			id = "1000";
		}
		if("1".equals(itemMode)){
			id="1001";
		}
		String url = config.getPageStaticUrl();
		HttpURLConnection con = null;
		OutputStream out = null;
		final StringBuffer str = new StringBuffer(0);
		BufferedReader br = null;
		try {
			JSONObject jsonObject = JSONObject.fromObject(obj);
			String code = jsonObject.toString();
			log.info("发送商品预览接口的数据: " + code);
			byte[] data = code.getBytes("utf-8");
			con = (HttpURLConnection) new URL(url).openConnection();
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setRequestMethod("POST");
			con.setConnectTimeout(1000 * 10);
			con.setRequestProperty("event-transaction-id", id);
			con.setRequestProperty("event-type-id", id);
			con.setRequestProperty("is-preview", "1");
			con.setRequestProperty("Content-Length", Integer.toString(data.length));
			con.setRequestProperty("Content-type", "text/html");
			out = con.getOutputStream();
			out.write(data);
			out.flush();
			out.close();
			br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
			
			String line = "";
			while ((line = br.readLine()) != null) {
				str.append(line);
			}
			log.info("商品预览接口返回的数据: " + str.toString());
		}
		catch (Exception e) {
			logUtils.logAdd("商品预览接口异常", e.getMessage());
			throw new Exception("调用页面接口失败："+e.getMessage());
		}
		finally {
			if (out != null) {
				try {
					out.flush();
					out.close();
				}
				catch (IOException e) {
					log.error(e.getMessage());
				}
			}
			if (br != null) {
				try {
					br.close();
				}
				catch (IOException e) {
					log.error(e.getMessage());
				}
			}
		}
		return str.toString();
	}
	
	
	

	/**
	 * 刷新前台页面
	 * 
	 * @modify_by macl@c-platform.com 
	 *  此方法url有错，建议调用makeStoreShop()方法生成商户店铺。
	 *  
	 * @modify_date 2013-8-28
	 * 
	 * @param shopId
	 */
	@Deprecated
	public boolean refreshFrontPage(Long shopId) {
		HttpURLConnection conn = null;
		OutputStreamWriter out = null;
		try { // getRefreshFrontPageUrl()
			String urlStr = config.getRefreshFrontPageUrl();
			URL url = new URL(urlStr);
			conn = (HttpURLConnection) url.openConnection();
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setUseCaches(false);
			conn.setConnectTimeout(1000 * 2);
			out = new OutputStreamWriter(conn.getOutputStream());
			out.write("shopId=" + shopId);
			out.flush();
			out.close();

			ByteArrayOutputStream baos = new ByteArrayOutputStream(1024 * 64);
			InputStream inputStream = conn.getInputStream();
			try {
				byte[] buf = new byte[1024 * 64];
				int n;
				while ((n = inputStream.read(buf)) >= 0) {
					baos.write(buf, 0, n);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				inputStream.close();
			}
			byte[] responseContent = baos.toByteArray();
			String str = new String(responseContent, "utf-8");
			JSONObject json = JSONObject.fromObject(str.toString());
			String retCode = json.getString("RET");
			return "0".equals(retCode);
		} catch (MalformedURLException e) {
			logUtils.logOther("通知前台刷新页面异常：", e.getMessage());
		} catch (IOException e) {
			// logUtils.logOther("通知前台刷新页面异常：" + e.getMessage());
		} finally {
			if (conn != null) {
				conn.disconnect();
				conn = null;
			}
		}

		return false;
	}
	

	
}
