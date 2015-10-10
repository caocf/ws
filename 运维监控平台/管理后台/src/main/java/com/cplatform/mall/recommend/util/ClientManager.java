package com.cplatform.mall.recommend.util;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.SyncBasicHttpParams;

/**
 * 连接管理器. <br>
 * 默认保留200个连接，参数可通过-Dcom.cplatform.httpclient.poolsize=XXX配置.
 * <p>
 * Copyright: Copyright (c) 2012-8-22 上午9:50:58
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author jiajc@c-platform.com
 * @version 1.0.0
 */
public class ClientManager {

	private static PoolingClientConnectionManager manager = null;

	private ClientManager() {

	}

	public static synchronized ClientConnectionManager getConnectionManager() {
		if (manager == null) {
			int poolSize = Integer.parseInt(System.getProperty("com.cplatform.httpclient.poolsize", "400"));
			manager = new PoolingClientConnectionManager();
			manager.setMaxTotal(poolSize);
			manager.setDefaultMaxPerRoute(poolSize);
		}
		return manager;
	}

	public static HttpClient getHttpClient(int connectionTimeout, int soTimeout) {
		HttpClient client = null;
		HttpParams params = new SyncBasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(params, connectionTimeout);
		HttpConnectionParams.setSoTimeout(params, soTimeout);
		client = new DefaultHttpClient(getConnectionManager(), params);
		return client;
	}

	public static HttpClient getHttpClient() {
		return getHttpClient(3000, 3000);
	}

	public static String getPoolState() {
		return manager == null ? "" : manager.getTotalStats().toString();
	}
}
