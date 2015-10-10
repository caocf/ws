package com.cplatform.b2c.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 标题、简要说明.
 * <p>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2013-6-6 下午3:15:22
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author zhengmz@c-platform.com
 * @version 1.0.0
 */
@Service
public class PathUtil {

	public static final int TYPE_ITEM = 1;

	public static final int TYPE_ITEM_PIC = 2;
	
	public static final int TYPE_ITEM_PIC1 = 5;

	public static final int TYPE_SHOP = 3;

	public static final int TYPE_SHOP_2 = 4;

	@Autowired
	private AppConfig appConfig;

	public String getPathById(int type, long id) {

		/*
		 * 算法： 第一层 = ID/(2000*2000) 第二层= (ID mod （2000×2000））/2000 第三层 = ID mod
		 * 2000
		 */
		String path = "";
		long l1 = id / (2000 * 2000);

		long l2 = (id % (2000 * 2000)) / 2000;

		long l3 = id % 2000;

		switch (type) {

		case TYPE_ITEM: {
			path = String.format("%sitem/i_%d_%d_%d.htm",
					appConfig.getWebRoot(), l1, l2, l3);
			break;
		}

		case TYPE_SHOP: {

			path = String.format("%sshop/s_%d_%d_%d.htm",
					appConfig.getWebRoot(), l1, l2, l3);
			break;

		}

		case TYPE_SHOP_2: {

			path = String.format("%sshop/s2_%d_%d_%d.htm",
					appConfig.getWebRoot(), l1, l2, l3);
			break;

		}

		case TYPE_ITEM_PIC: {

			path = String.format("%sitemimg/%d_%d_%d/",
					appConfig.getWebRoot(), l1, l2, l3);
			break;
		}
		
		case TYPE_ITEM_PIC1:{
			path = String.format("itemimg/%d_%d_%d/",
					 l1, l2, l3);
			break;
		}

		}

		return path;
	}

	public String getPathById(String type, String id) {
		if (type == null || id == null) {
			return "";
		}
		return getPathById(Integer.parseInt(type), Long.parseLong(id));
	}

	public String getSavePathById(int type, long id) {

		/*
		 * 算法： 第一层 = ID/(2000*2000) 第二层= (ID mod （2000×2000））/2000 第三层 = ID mod
		 * 2000
		 */
		String path = "";
		long l1 = id / (2000 * 2000);

		long l2 = (id % (2000 * 2000)) / 2000;

		long l3 = id % 2000;

		switch (type) {

		case TYPE_ITEM: {
			path = String.format("/item/%d/%d/i_%d_%d_%d.htm", l1, l2, l1, l2,
					l3);
			break;
		}

		case TYPE_SHOP: {

			path = String.format("/shop/%d/%d/s_%d_%d_%d.htm", l1, l2, l1, l2,
					l3);
			break;

		}

		case TYPE_SHOP_2: {

			path = String.format("/shop/%d/%d/s2_%d_%d_%d.htm", l1, l2, l1, l2,
					l3);
			break;

		}

		case TYPE_ITEM_PIC: {

			path = String.format("/itemimg/%d/%d/%d/", l1, l2, l3, l1, l2, l3);
			break;
		}

		}

		return path;
	}

	public static void main(String[] args) {
		// long number = 2000;

	}
}
