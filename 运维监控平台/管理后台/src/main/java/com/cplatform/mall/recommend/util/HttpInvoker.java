package com.cplatform.mall.recommend.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.log4j.Logger;

/**
 * 标题、简要说明. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2012-11-22 下午4:59:10
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author jiajc@c-platform.com
 * @version 1.0.0
 */
public class HttpInvoker {

	private final Logger log = Logger.getLogger(HttpInvoker.class);

	private final HttpClient client;

	private final String serverUrl;

	public HttpInvoker(String serverUrl) {
		this.serverUrl = serverUrl;
		client = ClientManager.getHttpClient();
	}

	/**
	 * @param serverUrl
	 *            服务地址
	 * @param connectionTimeout
	 *            连接超时时间
	 * @param soTimeout
	 *            数据传输超时时间
	 */
	public HttpInvoker(String serverUrl, int connectionTimeout, int soTimeout) {
		this.serverUrl = serverUrl;
		client = ClientManager.getHttpClient(connectionTimeout, soTimeout);
	}

	public String doRequest(String customerId, String phoneNo, String postId, String num) {
		String result = "";
		String url = null;
		if(num==null||num.trim().equals("")){
			 url = serverUrl + "&m=" + phoneNo + "&ut=0&posid=" + postId + "&topn=&pg=&v=1.1t&id=&ids=&uuid=&extParams=area:0515";
			
		}else{
			 url = serverUrl + "&m=" + phoneNo + "&ut=0&posid=" + postId + "&topn="+num+"&pg=&v=1.1t&id=&ids=&uuid=&extParams=area:0515";
		}
		HttpGet get = new HttpGet(url);
		InputStream in = null;
		try {
			HttpResponse response = client.execute(get);
			HttpEntity entity = response.getEntity();

			in = entity.getContent();
			byte[] b = new byte[1024];
			int l;
			while ((l = in.read(b)) != -1) {
				result += new String(b, 0, l, "utf-8");
			}
		}
		catch (UnsupportedEncodingException e) {
			get.abort();
			log.error(e.getMessage(), e);
		}
		catch (ClientProtocolException e) {
			get.abort();
			log.error(e.getMessage(), e);
		}
		catch (IOException e) {
			get.abort();
			log.error(e.getMessage(), e);
			if (e instanceof NoHttpResponseException) {
				log.warn("No reponse ,sleep 1 minute and retry request");
				try {
					Thread.sleep(60 * 1000);
				}
				catch (InterruptedException e1) {
				}
				return doRequest(customerId, phoneNo, postId, num);
			}
		}
		finally {
			if (in != null) {
				try {
					in.close();
				}
				catch (IOException e) {
					log.error(e.getMessage(), e);
				}
			}
			get.releaseConnection();
		}
		return result;
	}
}
