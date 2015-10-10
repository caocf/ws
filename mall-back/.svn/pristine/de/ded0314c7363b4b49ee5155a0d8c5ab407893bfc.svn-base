package com.cplatform.mall.back.cont.mms.util;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

/**
 * Ajax请求返回包的工具类 <br>
 * 固定模式提供json对象输出到页面方法
 * <p>
 * Copyright: Copyright (c) Apr 27, 2009 9:20:07 AM
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: chengyao
 * <p>
 * Version: 1.0
 * <p>
 */
public class AjaxUtils {

	public static Object SUCCESS = null;

	static {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", Boolean.TRUE);
		SUCCESS = JSONObject.fromObject(map);
	}

	/**
	 * 用于action中直接返回错误信息
	 * 
	 * @param response
	 *            action中的response对象
	 * @param msg
	 *            错误字符
	 * @return 返回固定为null
	 */
	public static String getFailReturn(String msg) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", Boolean.FALSE);
		map.put("msg", msg);
		JSONObject jsonObject = JSONObject.fromObject(map);
		return jsonObject.toString();
	}

}
