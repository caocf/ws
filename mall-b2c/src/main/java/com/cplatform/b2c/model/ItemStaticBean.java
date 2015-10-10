package com.cplatform.b2c.model;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.BeanUtils;

/**
 * 标题、简要说明.
 * <p>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2013-6-19 下午12:06:12
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author zhengmz@c-platform.com
 * @version 1.0.0
 */
public class ItemStaticBean extends ItemEventBean {

	public static ItemStaticBean newInstance(ItemEventBean itemEventBean) {
		ItemStaticBean bean = new ItemStaticBean();

		BeanUtils.copyProperties(itemEventBean, bean);

		return bean;

	}

	private String webRoot;

	private String imgSvrHost;

	private String img_path;

	private String shop_url;

	private String nav_url;

	@SuppressWarnings("unused")
	private String itemNameforJs;

	public String getItemNameforJs() {
		return StringEscapeUtils.escapeJavaScript(this.getItem_name());
	}

	public void setItemNameforJs(String itemNameforJs) {
		this.itemNameforJs = itemNameforJs;
	}

	public String getWebRoot() {
		return webRoot;
	}

	public void setWebRoot(String webRoot) {
		this.webRoot = webRoot;
	}

	public String getImg_path() {
		return img_path;
	}

	public void setImg_path(String img_path) {
		this.img_path = img_path;
	}

	public String getShop_url() {
		return shop_url;
	}

	public void setShop_url(String shop_url) {
		this.shop_url = shop_url;
	}

	public String getNav_url() {
		return nav_url;
	}

	public void setNav_url(String nav_url) {
		this.nav_url = nav_url;
	}

	public String getImgSvrHost() {
		return imgSvrHost;
	}

	public void setImgSvrHost(String imgSvrHost) {
		this.imgSvrHost = imgSvrHost;
	}

}
