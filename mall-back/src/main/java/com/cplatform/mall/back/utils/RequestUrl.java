package com.cplatform.mall.back.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 
 * Title.直接请求URL的工具类 <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-9-24 下午05:01:43
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: macl@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
public class RequestUrl {
	private static final String GET = "GET";
	private static final String POST = "POST";

	public static String get(String url) throws Exception {
		return doReq(GET, url);
	}

	public static String post(String url) throws Exception {
		return doReq(POST, url);
	}

	private static String doReq(String method, String url) throws Exception {
		HttpURLConnection httpConn = null;
		OutputStream out = null;
		BufferedReader br = null;
		try {
			httpConn = (HttpURLConnection) new URL(url.toString())
					.openConnection();
			httpConn.setDoInput(true);
			httpConn.setDoOutput(true);
			httpConn.setRequestMethod(method);
			httpConn.setConnectTimeout(10000); // 超时时间
			httpConn.setRequestProperty("event-transaction-id", "1000");
			httpConn.setRequestProperty("event-type-id", "1000");
			httpConn.setRequestProperty("Content-type", "text/html");
			out = httpConn.getOutputStream();
			out.flush();
			out.close();
			br = new BufferedReader(new InputStreamReader(httpConn
					.getInputStream(), "utf-8"));
			final StringBuffer msg = new StringBuffer(255);
			String line = "";
			while ((line = br.readLine()) != null) {
				msg.append(line);
			}
			return msg.toString();
		} catch (Exception e) {
			throw e;
		} finally {

			if (out != null) {
				try {
					out.flush();
					out.close();
				} catch (IOException e) {
					throw e;
				}
			}
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					throw e;
				}
			}
			httpConn.disconnect();
		}
	}
}
