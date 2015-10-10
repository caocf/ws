package com.cplatform.b2c.service;

import java.util.concurrent.TimeoutException;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplatform.b2c.util.CommonUtils;

/**
 * User: cuikai Date: 13-7-30 Time: 上午10:58
 */
@Service
public class VerifyCodeService {

	private static final int EXPIRE_TIME = 300000; // 5分钟

	private static final int CACHE_SIZE = 1024 * 1024;

	// private Cache<String, String> cache;

	@Autowired
	MemcachedClient memcachedClient;

	// @PostConstruct
	// public void init() {
	//
	// cache = new LRUCache<String, String>(CACHE_SIZE);
	// }

	public String generateVerifyCode(String cacheKey) {
		try {
			memcachedClient.set(cacheKey, EXPIRE_TIME, CommonUtils.randomKey(6));
			return memcachedClient.get(cacheKey);
		}
		catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (MemcachedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// cache.put(cacheKey, CommonUtils.randomKey(6), EXPIRE_TIME);

		// cache.get(cacheKey);
		return null;
	}

	public String getVerifyCode(String key) {
		try {
			return memcachedClient.get(key);
		}
		catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (MemcachedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return key;

	}

	public void removeVerifyCode(String key) {
		try {
			memcachedClient.delete(key);
		}
		catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (MemcachedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// cache.remove(key);
	}
}
