package com.cplatform.mall.back.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

/**
 * 标题、简要说明.
 * <p>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2013-5-30 下午2:36:07
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author zhengmz@c-platform.com
 * @version 1.0.0
 */
public class HttpUtil {

	/** 默认超时时间 (毫秒) */
	private static final int DEFAULT_TIMEOUT = 1000 * 6;

	/** 默认重试次数 */
	private static final int DEFAULT_RETRY_TIMES = 0;

	// get请求获取数据
	public static String getData(String url) {

		String content = "";

		HttpClient client = new HttpClient();

		// 设置链接超时
		client.getHttpConnectionManager().getParams()
				.setConnectionTimeout(DEFAULT_TIMEOUT);

		// 设置读取超时
		client.getHttpConnectionManager().getParams()
				.setSoTimeout(DEFAULT_TIMEOUT);

		// 设置重连次数，默认不重连
		client.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler(DEFAULT_RETRY_TIMES, false));

		GetMethod get = new GetMethod(url);
		// 链接的路径如：http://www.baidu.com
		// 使用系统提供的默认的恢复策略,此处HttpClient的恢复策略可以自定义（通过实现接口HttpMethodRetryHandler来实现）。
		// get.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
		// new DefaultHttpMethodRetryHandler());

		try {
			client.executeMethod(get);

			// System.out.println(get.getResponseBodyAsString());

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					get.getResponseBodyAsStream(), "GBK"));
			String tmp = null;

			while ((tmp = reader.readLine()) != null) {
				content += tmp + "\r\n";
			}

			System.out.println(new String(content.getBytes("GBK"), "GBK"));

		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			get.releaseConnection();
		}
		return content;
	}
}
