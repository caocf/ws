package com.cplatform.mall.back.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.FieldError;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.google.common.collect.Maps;

public class JsonRespWrapper {

	public static Object uploadSuccess(String url) {
		HashMap<String, Object> map = Maps.newHashMap();
		map.put("err", "");
		map.put("msg", url);
		return map;
	}

	public static Object uploadError(String message) {
		HashMap<String, Object> map = Maps.newHashMap();
		map.put("err", message);
		map.put("msg", "");
		return map;
	}

	public static Object denied() {
		return error("没有权限.");
	}

	/**
	 * 只返回成功标志
	 * 
	 * @return
	 */
	public static Object success() {
		HashMap<String, Object> map = Maps.newHashMap();
		map.put("success", true);
		return map;
	}

	/**
	 * 提示成功消息
	 * 
	 * @return
	 */
	public static Object successAlert(String message) {
		return success(message, null);
	}

	/**
	 * 直接跳转
	 * 
	 * @param redirectUrl
	 *            可以是http打头，或是绝对路径以/开头
	 * @return
	 */
	public static Object successJump(String redirectUrl) {
		return success(null, redirectUrl);
	}

	/**
	 * 成功，提示且跳转，如果message为空，则直接跳转。如果redirectUrl为空，则不跳转
	 * 
	 * @param message
	 *            提示信息
	 * @param redirectUrl
	 *            可以是http打头，或是绝对路径以/开头
	 * @return
	 */
	public static Object success(String message, String redirectUrl) {
		Map<String, Object> obj = (Map<String, Object>) success();
		obj.put("message", message);
		obj.put("url", redirectUrl);
		return obj;
	}

	/**
	 * 返回成功，并刷新当前页面
	 * 
	 * @param message
	 * @return
	 */
	public static Object successReload(String message) {
		return success(message, "javascript:window.location.reload()");
	}

	/**
	 * 返回成功，并返回前一个页面
	 * 
	 * @param message
	 * @return
	 */
	public static Object successBack(String message) {
		return success(message, "javascript:window.history.go(-1)");
	}

	/**
	 * 返回前一个页面，并且重新请求
	 * 
	 * @param message
	 * @return
	 */
	public static Object successRefreshBack(String message) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String url = (String) request.getSession().getAttribute(Constants.QUERY_BACK_URL);
		if (StringUtils.isEmpty(url)) {
			return successBack(message);
		}
		// 处理一下url，因为客户端js已经把contextpath 增加了，所以这块 需要把拼接的contextPath 去掉
		url = url.replace(request.getContextPath(), "");
		return success(message, url);
	}

	/**
	 * 成功，且传入键值对
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public static Object success(List<String> key, List<String> value) {
		return json(key, value, true);
	}

	public static Object error(String errorMessage) {
		return json("errorMessage", errorMessage, false);
	}

	public static Object error(Map<String, String> errors) {
		return json("errors", errors, false);
	}

	public static Object error(List<FieldError> errors) {
		Map<String, String> er = Maps.newLinkedHashMap();
		for (FieldError fe : errors) {
			er.put(fe.getField(), fe.getDefaultMessage());
		}
		return json("errors", er, false);
	}

	private static Object json(String key, Object value, boolean success) {
		HashMap<String, Object> map = Maps.newHashMap();
		map.put("success", success);
		if (key != null) {
			map.put(key, value);
		}
		return map;
	}

	private static Object json(List<String> key, List<String> value, boolean success) {
		HashMap<String, Object> map = Maps.newHashMap();
		map.put("success", success);
		if (key != null) {
			for (int i = 0; i < key.size(); i++) {
				map.put(key.get(i), value.get(i));
			}
		}
		return map;
	}

}
