package com.cplatform.mall.back.order.entity;

import com.cplatform.pay.CommonRequest;

public class CallbackRequest extends CommonRequest {

	/** serialVersionUID */
	private static final long serialVersionUID = -4415430018297922428L;

	/** STATUS_OK */
	public static final int STATUS_OK = 0;

	/** status */
	private int statusCode;

	/** statusText */
	private String statusText;

	/**
	 * 获取 statusCode
	 * 
	 * @return statusCode
	 */
	public int getStatusCode() {
		return statusCode;
	}

	/**
	 * 获取 statusText
	 * 
	 * @return statusText
	 */
	public String getStatusText() {
		return statusText;
	}

	/**
	 * 设置 statusCode
	 * 
	 * @param statusCode
	 *            statusCode
	 */
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	/**
	 * 设置 statusText
	 * 
	 * @param statusText
	 *            statusText
	 */
	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}

}
