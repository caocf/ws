package com.cplatform.b2c.util;

import net.rubyeye.xmemcached.MemcachedClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cplatform.b2c.dto.UserCenterObj;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * memcache读写静态类. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2014-3-7 上午10:46:58
 * <p>
 * Company: 苏州宽连信息技术有限公司
 * <p>
 * 
 * @author zhangdong-ca@c-platform.com
 * @version 1.0.0
 */
@Component
public abstract class AbstractMemCacheWr {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private MemcachedClient memcachedClient;

	abstract String bindEmailKeyPre();

	abstract String editEmailKeyPre();

	ObjectMapper objectMapper = new ObjectMapper();

	protected <T> T readKey(String key, String type, Class<T> clazz) {
		try {
			String data = this.memcachedClient.get(key(key, type));
			if (data != null) {
				return objectMapper.readValue(data, clazz);
			}
		}
		catch (final Exception e) {
			logger.error("Failed fetching {} ", key + ":" + type, e);
		}
		return null;
	}

	protected void writeObject(String key, String type, int exp, Object value) {
		if (logger.isTraceEnabled())
			logger.trace("Adding {}: {}", key + ":" + type, value);
		try {
			if (!this.memcachedClient.set(key(key, type), exp, objectMapper.writeValueAsString(value))) {
				logger.error("Failed adding {}", value);
			}
		}
		catch (final InterruptedException e) {
			logger.warn("Interrupted while waiting for response to async add operation for {}." + "Cannot determine whether add was successful.",
			            value);
		}
		catch (final Exception e) {
			logger.error("Failed adding {}", value, e);
		}
	}

	protected boolean deleteKey(String key, String type) {
		if (logger.isTraceEnabled())
			logger.trace("Deleting {}", key);
		try {
			return this.memcachedClient.delete(key(key, type));
		}
		catch (final Exception e) {
			logger.error("Failed deleting {}", key + ":" + type, e);
		}
		return false;
	}

	/**
	 * 生成key值
	 * 
	 * @param id
	 * @return
	 */
	private String key(String id, String type) {
		if (UserCenterObj.BIND_EMAIL.equals(type)) {
			return bindEmailKeyPre() + id;
		} else if (UserCenterObj.EDIT_EMAIL.equals(type)) {
			return editEmailKeyPre() + id;
		}
		return "";
	}
}
