package com.cplatform.b2c.welfare.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import com.cplatform.b2c.welfare.entity.AddressInfoModel;
import com.cplatform.b2c.welfare.entity.GoodsModel;
import com.cplatform.b2c.welfare.entity.OrderCenterJsonModel;

/**
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-11-4 下午03:55:39
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: guolt@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
public class Constants {

	public static int PAGE_SIZE = 12;

	public static List<String> MOBILES = new ArrayList<String>();

	public static long REFRESH_TIME = new Date().getTime();

	public static String trans(String src) {
		if (src != null) {
			try {
				src = new String(src.getBytes("iso-8859-1"), "utf-8");
			}
			catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return src;
	}

	/**
	 * UTF-8解码
	 * 
	 * @param src
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String decoderUTF8(String src) {
		String result = null;
		try {
			result = URLDecoder.decode(src, "UTF-8");
		}
		catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = src;
		}
		return result;
	}

	public static void main(String[] args) {

		OrderCenterJsonModel orderJsonMoel = new OrderCenterJsonModel();

		orderJsonMoel.setADDRESS_INFO(new AddressInfoModel());
		List<GoodsModel> lst = new ArrayList<GoodsModel>();
		lst.add(new GoodsModel());
		orderJsonMoel.setGOODS(lst);
		orderJsonMoel.setU_ID(1232l);
		JSONObject json = JSONObject.fromObject(orderJsonMoel);
		String url = "http://112.4.27.9/mall-sapi/api/v1/order/create";
		String queryString = "{\"U_ID\":2549096,\"GOODS\":[{\"COUNT\":2,\"GOOD_ID\":223700,\"DISCOUNT\":0}],\"ADDRESS_ID\":null,\"ADDRESS_INFO\":{\"MOBILE\":\"13770758177\",\"PHONE\":null,\"RECEIVER_NAME\":\"dgjxsf\",\"ADDRESS\":\"egjg\",\"ZIPCODE\":\"123456\",\"REMARK\":null},\"USER_REMARK\":\"\",\"INVOICE_TYPE\":1,\"INVOICE_SUBJECT\":\"0\",\"INVOICE_CONTENT\":\"明细\",\"SUBJECT\":null,\"CREATE_SOURCE\":5,\"ORDER_TYPE\":null,\"BUSINESS_ID\":\"app[1000000009]\"}";
		queryString = "{\"ADDRESS_INFO\":{\"ADDRESS\":\"\",\"MOBILE\":\"18362637354\",\"PHONE\":\"\",\"RECEIVER_NAME\":\"\",\"REMARK\":\"移动员工专享\",\"ZIPCODE\":\"215000\"},"
		        + "\"BUSINESS_ID\":\"\",\"CREATE_SOURCE\":3,\"GOODS\":[{\"COUNT\":1,\"DISCOUNT\":0,\"GOOD_ID\":216629}],\"ORDER_TYPE\":\"10\",\"SUBJECT\":\"正版海绵宝宝情侣拖鞋/暖暖拖鞋/家居拖鞋 男款\",\"USER_REMARK\":\"2013-11-07 16:32:26 流水号:163\",\"U_ID\":2107818}";

		String result = null;
		Integer manager_timeout = 1000;
		Integer so_timeout = 1000;

		try {

		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
