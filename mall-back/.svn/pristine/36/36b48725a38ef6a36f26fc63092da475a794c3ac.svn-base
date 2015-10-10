package com.cplatform.mall.back.cont.mms.util;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Title. 过滤过长的字段<br>
 * Description.
 * <p>
 * Copyright: Copyright (c) Nov 30, 2009 5:33:53 PM
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: wangxin@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
@SuppressWarnings("unchecked")
public class TitleUtil implements Map {

	@Override
	public void clear() {

	}

	@Override
	public boolean containsKey(Object key) {
		return false;
	}

	@Override
	public boolean containsValue(Object value) {
		return false;
	}

	public Object cutString(Object key, int length) {
		if (key == null) {
			return null;
		}

		if (key instanceof String == false) {
			return null;
		}

		String str = key.toString();

		if (str.length() > length) {
			return str.substring(0, length) + "...";
		} else {
			return str;
		}

	}

	@Override
	public Set entrySet() {
		return null;
	}

	@Override
	public Object get(Object key) {
		if (key == null) {
			return null;
		}

		if (key instanceof String == false) {
			return null;
		}

		String str = key.toString();

		if (str.length() > 8) {
			return str.substring(0, 8) + "...";
		} else {
			return str;
		}

	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public Set keySet() {
		return null;
	}

	@Override
	public Object put(Object key, Object value) {
		return null;
	}

	@Override
	public void putAll(Map m) {

	}

	@Override
	public Object remove(Object key) {
		return null;
	}

	@Override
	public int size() {
		return 0;
	}

	@Override
	public Collection values() {
		return null;
	}
}
