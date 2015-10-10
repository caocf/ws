package com.cplatform.b2c.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.lang3.StringUtils;

/**
 * Title. <br>
 * Description.
 * <p/>
 * Copyright: Copyright (c) 13-6-9 下午3:05
 * <p/>
 * Company: 北京宽连十方数字技术有限公司
 * <p/>
 * Author: nicky
 * <p/>
 * Version: 1.0
 * <p/>
 */
public class CommonUtils {

	/**
	 * Contacts the remote URL and returns the response.
	 * 
	 * @param constructedUrl
	 *            the url to contact.
	 * @param encoding
	 *            the encoding to use.
	 * @return the response.
	 */
	public static String getResponseFromServer(final URL constructedUrl, final String encoding) {
		return getResponseFromServer(constructedUrl, HttpsURLConnection.getDefaultHostnameVerifier(), encoding);
	}

	/**
	 * Contacts the remote URL and returns the response.
	 * 
	 * @param url
	 *            the url to contact.
	 * @param encoding
	 *            the encoding to use.
	 * @return the response.
	 */
	public static String getResponseFromServer(final String url, String encoding) {
		try {
			return getResponseFromServer(new URL(url), encoding);
		}
		catch (final MalformedURLException e) {
			throw new IllegalArgumentException(e);
		}
	}

	/**
	 * Contacts the remote URL and returns the response.
	 * 
	 * @param constructedUrl
	 *            the url to contact.
	 * @param hostnameVerifier
	 *            Host name verifier to use for HTTPS connections.
	 * @param encoding
	 *            the encoding to use.
	 * @return the response.
	 */
	public static String getResponseFromServer(final URL constructedUrl, final HostnameVerifier hostnameVerifier, final String encoding) {
		URLConnection conn = null;
		try {
			conn = constructedUrl.openConnection();
			if (conn instanceof HttpsURLConnection) {
				((HttpsURLConnection) conn).setHostnameVerifier(hostnameVerifier);
			}
			final BufferedReader in;

			if (StringUtils.isEmpty(encoding)) {
				in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			} else {
				in = new BufferedReader(new InputStreamReader(conn.getInputStream(), encoding));
			}

			String line;
			final StringBuilder stringBuffer = new StringBuilder(255);

			while ((line = in.readLine()) != null) {
				stringBuffer.append(line);
				stringBuffer.append("\n");
			}
			return stringBuffer.toString();
		}
		catch (final Exception e) {
			throw new RuntimeException(e);
		}
		finally {
			if (conn != null && conn instanceof HttpURLConnection) {
				((HttpURLConnection) conn).disconnect();
			}
		}

	}

	public static BigDecimal toYuan(int price) {
		return new BigDecimal(price).divide(new BigDecimal(100)).setScale(2);
	}

	public static BigDecimal toYuan(BigDecimal price) {
		return price.divide(new BigDecimal(100)).setScale(2);
	}

	public static int toFen(String useCoin) {
		return new BigDecimal(useCoin).setScale(2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).intValue();
	}

	/**
	 * 河南积分换算
	 * 
	 * @param price
	 * @return
	 */
	public static int toHnScore(String price) {
		return new BigDecimal(price).setScale(2, BigDecimal.ROUND_HALF_UP).intValue();
	}

	public static String randomKey(int sLen) {
		String base = "1234567890";
		String temp = "";
		int i;
		int p;

		for (i = 0; i < sLen; i++) {
			p = (int) (Math.random() * 10);
			temp += base.substring(p, p + 1);
		}
		return temp;
	}

	/**
	 * 隐藏手机号码的方法
	 * 
	 * @param terminal_id
	 * @param startIndex
	 * @param length
	 * @param flag
	 * @return
	 */
	public static String hideTerminalid(String terminal_id, int startIndex, int length, String flag) {
		if (terminal_id.length() != 11) {
			return null;
		}
		if (length > 11 || length < 1 || startIndex > 10 || startIndex < 1 || flag.length() != 1) {
			return null;
		}
		String per_terminal_id = terminal_id.substring(0, startIndex);
		String suffix_terminal_id = terminal_id.substring(startIndex + length);
		StringBuffer flagStr = new StringBuffer();
		for (int i = 0; i < length; i++) {
			flagStr.append(flag);
		}
		String str_terminal_id = per_terminal_id + flagStr + suffix_terminal_id;
		return str_terminal_id;
	}
}
