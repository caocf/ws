package com.cplatform.b2c.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONSerializer;

import org.springframework.validation.FieldError;

import com.google.common.collect.Maps;

public class JsonRespWrapper extends HashMap<String, Object> {

	public JsonRespWrapper json(String key, Object value) {
		this.put(key, value);
		return this;
	}

	public JsonRespWrapper json(List<String> keys, List<Object> values) {
		if (keys == null)
			return this;
		for (int i = 0; i < keys.size(); i++) {
			this.put(keys.get(i), values.get(i));
		}
		return this;
	}

	public static JsonRespWrapper success() {
		return json(true);
	}

	public static JsonRespWrapper success(String msg) {
		return success().json("msg", msg);
	}

	public static JsonRespWrapper success(String key, Object value) {
		return success().json(key, value);
	}

	public static JsonRespWrapper success(List<String> keys, List<Object> values) {
		return success().json(keys, values);
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
	public static JsonRespWrapper redirectSuccess(String message, String redirectUrl) {
		return success().json("message", message).json("url", redirectUrl);
	}

	public static JsonRespWrapper failure() {
		return json(false);
	}

	public static JsonRespWrapper failure(String msg) {
		return failure().json("msg", msg);
	}

	public static JsonRespWrapper failure(String key, Object value) {
		return failure().json(key, value);
	}

	public static JsonRespWrapper failure(List<String> keys, List<Object> values) {
		return failure().json(keys, values);
	}

	public static JsonRespWrapper json() {
		JsonRespWrapper map = new JsonRespWrapper();
		return map;
	}

	public static JsonRespWrapper json(boolean success) {
		return json().json("success", success);
	}

	public static JsonRespWrapper json(String key, Object value, boolean success) {
		return json(success).json(key, value);
	}

	public static JsonRespWrapper json(List<String> keys, List<Object> values, boolean success) {
		JsonRespWrapper obj = json(success);
		return obj.json(keys, values);
	}

	/**
	 * 数据验证，错误时返回
	 * 
	 * @param errors
	 * @return
	 */
	public static JsonRespWrapper failure(List<FieldError> errors) {
		Map<String, String> er = Maps.newLinkedHashMap();
		for (FieldError fe : errors) {
			er.put(fe.getField(), fe.getDefaultMessage());
		}
		return json("msg", er, false);
	}

	@Override
	public String toString() {
		return JSONSerializer.toJSON(this).toString();
	}
}
