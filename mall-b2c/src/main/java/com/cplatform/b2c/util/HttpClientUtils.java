package com.cplatform.b2c.util;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.rmi.UnknownHostException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.SSLException;

import net.sf.json.JSONObject;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpStatus;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

/**
 * 搜索相关控制方法 类详细说明.
 * <p/>
 * Copyright: Copyright (c) 2013-6-9 上午10:05:00
 * <p/>
 * Company: 北京宽连十方数字技术有限公司
 * <p/>
 * 
 * @author yangxm@c-platform.com
 * @author cuikai
 */
public class HttpClientUtils {

	private static final Logger logger = Logger.getLogger("http");

	private static final String ckey = "abcdefghijklmnopqrstuvwx";

	private static PoolingHttpClientConnectionManager connManager = null;

	private static CloseableHttpClient httpClient = null;

	/**
	 * 最大连接数
	 */
	private final static int MAX_TOTAL_CONNECTIONS = 100;

	/**
	 * 每个路由最大连接数
	 */
	private final static int MAX_ROUTE_CONNECTIONS = 50;

	private static final int CONNECT_TIMEOUT = 8000;

	private static final int SOCKET_TIMEOUT = 8000;

	static {
		HttpRequestRetryHandler myRetryHandler = customRetryHandler();
		connManager = new PoolingHttpClientConnectionManager();
		connManager.setMaxTotal(MAX_TOTAL_CONNECTIONS);
		connManager.setDefaultMaxPerRoute(MAX_ROUTE_CONNECTIONS);
		HttpHost localhost = new HttpHost("locahost", 80);
		connManager.setMaxPerRoute(new HttpRoute(localhost), 50);
		httpClient = HttpClients.custom().setConnectionManager(connManager).setRetryHandler(myRetryHandler).build();
	}

	@Deprecated
	public static String httpGet(String url, String param, Integer manager_timeout, Integer so_timeout) throws Exception {

		return httpGet(url, param);
	}

	@Deprecated
	public static String httpPost(String url, String queryString, Integer manager_timeout, Integer so_timeout) throws Exception {

		return httpPost(url, queryString);
	}

	public static String httpGet(String url, String param) throws IOException {

		CloseableHttpResponse response = null;

		HttpGet httpGet = new HttpGet(url + "?" + param);

		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(CONNECT_TIMEOUT).setSocketTimeout(SOCKET_TIMEOUT).build();
		httpGet.setConfig(requestConfig);
		try {
			response = httpClient.execute(httpGet);

			if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
				logger.error("get请求失败[" + url + "," + param + "]");
			}
			HttpEntity entity = response.getEntity();
			String resp = EntityUtils.toString(entity, "utf-8");
			logger.info("get请求[:" + url + "," + param + "]" + ",响应:[" + resp + "]");
			return resp;
		}
		catch (Exception e) {
			throw new IOException(e.getMessage());
		}
		finally {
			if (response != null) {
				response.close();
			}
			httpGet.releaseConnection();
		}
	}

	public static String httpPost(String url, String queryString, String... headerValue) throws IOException {

		CloseableHttpResponse response = null;

		HttpPost httpPost = new HttpPost(url);

		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(CONNECT_TIMEOUT).setSocketTimeout(SOCKET_TIMEOUT).build();
		httpPost.setConfig(requestConfig);

		StringEntity myEntity = new StringEntity(queryString, ContentType.APPLICATION_JSON);

		httpPost.setEntity(myEntity);

		if (headerValue.length > 0) {
			httpPost.setHeader("KW", headerValue[0]);
		}
		try {
			response = httpClient.execute(httpPost);

			if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
				logger.error("post请求失败[" + url + "," + queryString + "]");
			}
			HttpEntity entity = response.getEntity();
			String resp = EntityUtils.toString(entity, "utf-8");
			logger.info("post请求[" + url + "," + queryString + "],响应:[" + resp + "]");
			return resp;
		}
		catch (Exception e) {
			throw new IOException(e.getMessage());
		}
		finally {
			if (response != null) {
				response.close();
			}
			httpPost.releaseConnection();
		}
	}

	private static HttpRequestRetryHandler customRetryHandler() {
		HttpRequestRetryHandler myRetryHandler = new HttpRequestRetryHandler() {

			@Override
			public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
				if (executionCount >= 3) {
					// Do not retry if over max retry count
					return false;
				}
				if (exception instanceof InterruptedIOException) {
					// Timeout
					return false;
				}
				if (exception instanceof NoHttpResponseException) {
					return true;
				}
				if (exception instanceof UnknownHostException) {
					// Unknown host
					return false;
				}
				if (exception instanceof ConnectTimeoutException) {
					// Connection refused
					return false;
				}
				if (exception instanceof SSLException) {
					// SSL handshake exception
					return false;
				}
				HttpClientContext clientContext = HttpClientContext.adapt(context);
				HttpRequest request = clientContext.getRequest();
				boolean idempotent = !(request instanceof HttpEntityEnclosingRequest);
				if (idempotent) {
					// Retry if the request is considered idempotent
					return true;
				}
				return false;
			}

		};
		return myRetryHandler;
	}

	/**
	 * 获取签名串
	 */
	public static String doSign(String baseStr) throws Exception {
		SecretKeySpec key = new SecretKeySpec(ckey.getBytes("UTF-8"), "HmacSHA1");
		Mac mac = Mac.getInstance("HmacSHA1");
		mac.init(key);
		byte[] bytes = mac.doFinal(baseStr.substring(1).getBytes("UTF-8"));
		return new String(Base64.encodeBase64(bytes));
	}

	/**
	 * 获取 nonce
	 * 
	 * @return nonce
	 */
	public static String getNonce() {
		return RandomStringUtils.random(6, false, true);
	}

	/**
	 * 获取 timestamp
	 * 
	 * @return timestamp
	 */
	public static String getTimestamp() {
		return String.valueOf(System.currentTimeMillis());
	}

	/**
	 * 组建post方法的body
	 */
	public static JSONObject makeJsonBody(String keyword, String store_id, String region_code, String type_id, String brand, String sort,
	        String params, String curpage, int pagesize) throws Exception {
		String jsonStr = "{\"query\":\"goodsList\"}";
		JSONObject json = JSONObject.fromObject(jsonStr);
		String nonce_ = getNonce();
		json.put("nonce", nonce_);
		json.put("ckey", "uniongoods");
		json.put("timestamp", getTimestamp());
		if (type_id.equals("0")) {
			type_id = "";
		}
		json.put("type_id", type_id);
		json.put("keyword", keyword);
		json.put("store_id", store_id);
		json.put("region_code", region_code);
		json.put("brand", brand);
		json.put("params", params);// intel;AMD
		json.put("sort", sort);
		json.put("page", curpage);
		json.put("pagesize", pagesize + "");

		String baseStr = json.toString() + nonce_;
		try {
			json.put("signature", doSign(baseStr));
		}
		catch (Exception e) {
			throw new Exception(e);
		}
		return json;
	}

	/**
	 * 判断是否是信任ip
	 * 
	 * @param trustedIp
	 *            ：以逗号分隔的ip地址
	 * @param remoteIp
	 *            ：需验证的ip地址
	 * @return
	 */
	public static boolean isTrustIp(String remoteIp, String trustedIp) {

		boolean flag = false;
		if (StringUtils.isEmpty(trustedIp)) {
			flag = true;
		} else {

			String[] ips = trustedIp.split(",");

			for (String string : ips) {

				if (StringUtils.equals(remoteIp, string)) {
					flag = true;
					break;
				}

			}
		}

		return flag;
	}

	public static String httpPostC(String url, String queryString, Integer manager_timeout, Integer so_timeout) throws Exception {

		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);

		StringEntity myEntity = new StringEntity(queryString, ContentType.create("application/json", "UTF-8"));

		httpPost.setEntity(myEntity);

		CloseableHttpResponse response = httpClient.execute(httpPost);
		try {
			if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {

			}
			HttpEntity entity = response.getEntity();
			return EntityUtils.toString(entity, "utf-8");
		}
		finally {
			response.close();
		}
	}

}
