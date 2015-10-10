package com.cplatform.mall.back.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * http请求 <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-6-19 下午5:30:54
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: mudeng@ca-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
@Service
public class HttpUrlUtils {
	private static final Logger log = Logger.getLogger(HttpUrlUtils.class);

	@Autowired
	private AppConfig config;
	
	@Autowired
	private LogUtils logUtils;


	/**
	 * post请求
	 * 
	 * @param obj
	 * @return
	 * @throws Exception 
	 */
	public String getHttpIfCard(Object obj) throws Exception {
		String msg = "";
		String url = config.getIfCardUrl();
		HttpURLConnection con = null;
		OutputStream out = null;
		BufferedReader br = null;
		try {
			JSONObject jsonObject = JSONObject.fromObject(obj);
			String code = jsonObject.toString();
			byte[] data = code.getBytes("utf-8");

			con = (HttpURLConnection) new URL(url).openConnection();
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setRequestMethod("POST");
			con.setConnectTimeout(1000 * 10);
			con.setRequestProperty("event-transaction-id", "1000");
			con.setRequestProperty("event-type-id", "1000");
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
			JSONObject json = JSONObject.fromObject(str.toString());
//			msg = json.toString();
			if(json.get("payment")==null){
				msg = null;
			}else{
				msg = json.getString("payment");
			}
		}
		catch (Exception e) {
			logUtils.logAdd("调用获取礼金券使用情况接口失败", e.getMessage());
			throw new Exception("调用获取礼金券使用情况接口失败："+e.getMessage());
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
}
