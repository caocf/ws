package com.cplatform.b2c.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.rubyeye.xmemcached.MemcachedClient;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplatform.b2c.repository.RecommendDao;
import com.cplatform.b2c.util.AppConfig;
import com.cplatform.b2c.util.PathUtil;
import com.cplatform.b2c.util.WebUtils;

/**
 * 智能推荐service. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2014-2-17 上午10:46:27
 * <p>
 * Company: 苏州宽连信息技术有限公司
 * <p>
 * 
 * @author zhangmeng@c-platform.com
 * @version 1.0.0
 */
@Service
public class RecommendService {

	private final Logger logger = Logger.getLogger(RecommendService.class);

	/** 连接超时时长 */
	private static final int CONNECT_TIMEOUT = 3000;

	/** 响应超时时长 */
	private static final int READ_TIMEOUT = 3000;

	/** MEMCACHE缓存有效时间，默认为30分钟 * */
	private static final int MEMCACHE_REFRESH_TIME = 1800;

	@Autowired
	AppConfig appConfig;

	@Autowired
	MemcachedClient memcachedClient;

	@Autowired
	RecommendDao dao;

	@Autowired
	PathUtil pathUtil;

	/**
	 * 获取个人中心优惠券推荐位列表
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, String>> getUserCenterRecommendCouponList(String mobile, String topn, String areaCode, String userId, String remoteIp,
	        boolean isLogin) {
		logger.info("请求地市：" + areaCode);
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();

		// 优先从缓存中获取
		String memKey = "";
		if (isLogin) {
			memKey = userId + "_" + areaCode + "_zxyh";
		} else {
			memKey = remoteIp + "_" + areaCode + "_zxyh";
		}
         
		try {
			Object obj = memcachedClient.get(memKey);
			if (obj != null) {
				logger.info("从缓存中获取个人中心优惠券推荐位,memKey=" + memKey);
				return (List<Map<String, String>>) obj;
			}

			// 没有缓存则调用推荐接口获取
			String interfaceUrl = appConfig.getRecommendInterfaceUrl();
			Map<String, String> params = initParams(mobile, "zxyh", topn, areaCode);

			String resp = WebUtils.doPost(interfaceUrl, params, CONNECT_TIMEOUT, READ_TIMEOUT);
			logger.info("调用个人中心优惠券推荐接口resp:" + resp);
			
			if (StringUtils.isNotBlank(resp)) {
				list = getRecommendCouponList(resp);

				if (list != null && !list.isEmpty()) {
					// 将结果缓存到memcache
					memcachedClient.set(memKey, MEMCACHE_REFRESH_TIME, list);
				}
			}
		}
		catch (Exception e) {
			logger.error("调用个人中心优惠券推荐接口获取优惠券信息出现异常：" + e);
		}

		return list;
	}

	@SuppressWarnings("unchecked")
	private List<Map<String, String>> getRecommendCouponList(String resp) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		try {
			if (StringUtils.isNotBlank(resp)) {
				// 解析推荐接口返回的优惠券id
				SAXBuilder builder = new SAXBuilder();
				Document doc = builder.build(new ByteArrayInputStream(resp.getBytes()));
				Element root = doc.getRootElement();
				Element e = root.getChild("result");
				if (e != null) {
					Map<String, String> referMap = new HashMap<String, String>();
					List<Element> itemList = e.getChildren();
					StringBuffer couponIds = new StringBuffer();
					for (Element item : itemList) {
						couponIds.append(item.getChildText("id")).append(',');
						referMap.put(item.getChildText("id"), item.getChildText("referer"));
					}

					if (couponIds.length() > 0) {
						list = dao.getCouponFromSorlSearch(couponIds.deleteCharAt(couponIds.length() - 1).toString());
					}
					for (Map<String, String> map : list) {
						map.put("REFERER", referMap.get(map.get("ID").toString()));
					}
				}
			}
		}
		catch (IOException e) {
			logger.error("调用推荐接口获取优惠券信息出现异常：" + e);
		}
		catch (JDOMException e) {
			logger.error("调用推荐接口获取优惠券信息出现异常：" + e);
		}

		return list;
	}

	/**
	 * 个人中心团购页推荐列表
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, String>> getUserCenterRecommendQuanList(String mobile, String topn, String areaCode, String userId, String remoteIp,
	        boolean isLogin) {
		logger.info("请求地市：" + areaCode);
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		// 优先从缓存中获取
		String memKey = "";
		if (isLogin) {
			memKey = userId + "_" + areaCode + "_zxdj";
		} else {
			memKey = remoteIp + "_" + areaCode + "_zxdj";
		}

		try {
			Object obj = memcachedClient.get(memKey);
			if (obj != null) {
				logger.info("从缓存中获取个人中心代金券推荐,memKey=" + memKey);
				return (List<Map<String, String>>) obj;
			}

			// 没有缓存则调用推荐接口获取
			String interfaceUrl = appConfig.getRecommendInterfaceUrl();
			Map<String, String> params = initParams(mobile, "zxdj", topn, areaCode);

			String resp = WebUtils.doPost(interfaceUrl, params, CONNECT_TIMEOUT, READ_TIMEOUT);
			logger.info("调用个人中心代金券推荐接口resp:" + resp);

			if (StringUtils.isNotBlank(resp)) {
				list = getRecommendQuanList(resp, areaCode);

				if (list != null && !list.isEmpty()) {
					// 将结果缓存到memcache
					memcachedClient.set(memKey, MEMCACHE_REFRESH_TIME, list);
				}
			}
		}
		catch (Exception e) {
			logger.error("调用个人中心代金券推荐接口获取代金券信息出现异常：" + e);
		}

		return list;
	}

	/**
	 * 团购搜索结果页推荐团购
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, String>> getRecommendTuanSearchList(String mobile, String topn, String areaCode, String userId, String remoteIp,
	        boolean isLogin) {
		logger.info("请求地市：" + areaCode);
		// 优先从缓存中获取
		String memKey = "";
		if (isLogin) {
			memKey = userId + "_" + areaCode + "_sstg";
		} else {
			memKey = remoteIp + "_" + areaCode + "_sstg";
		}

		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		try {
			Object obj = memcachedClient.get(memKey);
			if (obj != null) {
				logger.info("从缓存中获取团购搜索页推荐位,memKey=" + memKey);
				return (List<Map<String, String>>) obj;
			}

			// 没有缓存则调用推荐接口获取
			String interfaceUrl = appConfig.getRecommendInterfaceUrl();
			Map<String, String> params = initParams(mobile, "sstg", topn, areaCode);

			String resp = WebUtils.doPost(interfaceUrl, params, CONNECT_TIMEOUT, READ_TIMEOUT);
			logger.info("调用团购搜索页推荐接口resp:" + resp);

			if (StringUtils.isNotBlank(resp)) {
				list = getRecommendTuanList(resp);

				if (list != null && !list.isEmpty()) {
					// 将结果缓存到memcache
					memcachedClient.set(memKey, MEMCACHE_REFRESH_TIME, list);
				}
			}

			return list;
		}
		catch (Exception e) {
			logger.error("调用推荐接口获取团购信息出现异常：" + e);
		}

		return null;
	}

	/**
	 * 团购详情页推荐团购
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, String>> getRecommendTuanDetailList(String mobile, String topn, String areaCode, String userId, String remoteIp,
	        boolean isLogin) {
		logger.info("请求地市：" + areaCode);
		// 优先从缓存中获取
		String memKey = "";
		if (isLogin) {
			memKey = userId + "_" + areaCode + "_xqtg";
		} else {
			memKey = remoteIp + "_" + areaCode + "_xqtg";
		}

		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		try {
			Object obj = memcachedClient.get(memKey);
			if (obj != null) {
				logger.info("从缓存中获取团购详情页推荐位,memKey=" + memKey);
				return (List<Map<String, String>>) obj;
			}

			// 没有缓存则调用推荐接口获取
			String interfaceUrl = appConfig.getRecommendInterfaceUrl();
			Map<String, String> params = initParams(mobile, "xqtg", topn, areaCode);

			String resp = WebUtils.doPost(interfaceUrl, params, CONNECT_TIMEOUT, READ_TIMEOUT);
			logger.info("调用团购详情页推荐接口resp:" + resp);

			if (StringUtils.isNotBlank(resp)) {
				list = getRecommendTuanList(resp);

				if (list != null && !list.isEmpty()) {
					// 将结果缓存到memcache
					memcachedClient.set(memKey, MEMCACHE_REFRESH_TIME, list);
				}
			}

			return list;
		}
		catch (Exception e) {
			logger.error("调用推荐接口获取团购信息出现异常：" + e);
		}

		return null;
	}

	/**
	 * 个人中心团购页推荐列表
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, String>> getUserCenterRecommendTuanList(String mobile, String topn, String areaCode, String userId, String remoteIp,
	        boolean isLogin) {
		logger.info("请求地市：" + areaCode);
		// 优先从缓存中获取
		String memKey = "";
		if (isLogin) {
			memKey = userId + "_" + areaCode + "_zxtg";
		} else {
			memKey = remoteIp + "_" + areaCode + "_zxtg";
		}

		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		try {
			Object obj = memcachedClient.get(memKey);
			if (obj != null) {
				logger.info("从缓存中获取个人中心团购页推荐位,memKey=" + memKey);
				return (List<Map<String, String>>) obj;
			}

			// 没有缓存则调用推荐接口获取
			String interfaceUrl = appConfig.getRecommendInterfaceUrl();
			Map<String, String> params = initParams(mobile, "zxtg", topn, areaCode);

			String resp = WebUtils.doPost(interfaceUrl, params, CONNECT_TIMEOUT, READ_TIMEOUT);
			logger.info("调用个人中心团购页推荐接口resp:" + resp);

			if (StringUtils.isNotBlank(resp)) {
				list = getRecommendTuanList(resp);

				if (list != null && !list.isEmpty()) {
					// 将结果缓存到memcache
					memcachedClient.set(memKey, MEMCACHE_REFRESH_TIME, list);
				}
			}

			return list;
		}
		catch (Exception e) {
			logger.error("调用个人中心团购页推荐接口获取团购信息出现异常：" + e);
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	private List<Map<String, String>> getRecommendTuanList(String resp) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		try {
			if (StringUtils.isNotBlank(resp)) {
				// 解析推荐接口返回的优惠券id
				SAXBuilder builder = new SAXBuilder();
				Document doc = builder.build(new ByteArrayInputStream(resp.getBytes()));
				Element root = doc.getRootElement();
				Element e = root.getChild("result");
				if (e != null) {
					List<Element> itemList = e.getChildren();
					Map<String, String> referMap = new HashMap<String, String>();
					StringBuffer teamIds = new StringBuffer();
					for (Element item : itemList) {
						teamIds.append(item.getChildText("id")).append(',');
						referMap.put(item.getChildText("id"), item.getChildText("referer"));
					}

					if (teamIds.length() > 0) {
						list = dao.getRecommendTuanList(teamIds.deleteCharAt(teamIds.length() - 1).toString());
					}
					if (list != null && !list.isEmpty()) {
						for (Map<String, String> map : list) {
							map.put("REFERER", referMap.get(map.get("TEAM_ID").toString()));
						}
					}
				}
			}
		}
		catch (Exception e) {
			logger.error("调用推荐接口获取团购信息出现异常：", e);
		}

		return list;
	}

	/**
	 * 代金券详情页推荐信息
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, String>> getRecommendQuanDetailList(String mobile, String topn, String areaCode, String userId, String remoteIp,
	        boolean isLogin) {
		// List<Map<String, String>> list = initRecommendQuanList();
		logger.info("请求地市：" + areaCode);
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();

		// 优先从缓存中获取
		String memKey = "";
		if (isLogin) {
			memKey = userId + "_" + areaCode + "_xqdj";
		} else {
			memKey = remoteIp + "_" + areaCode + "_xqdj";
		}

		try {
			Object obj = memcachedClient.get(memKey);
			if (obj != null) {
				logger.info("从缓存中获取代金券详情页推荐,memKey=" + memKey);
				return (List<Map<String, String>>) obj;
			}

			// 没有缓存则调用推荐接口获取
			String interfaceUrl = appConfig.getRecommendInterfaceUrl();
			Map<String, String> params = initParams(mobile, "xqdj", topn, areaCode);

			String resp = WebUtils.doPost(interfaceUrl, params, CONNECT_TIMEOUT, READ_TIMEOUT);
			logger.info("调用代金券详情页推荐接口resp:" + resp);
			if (StringUtils.isNotBlank(resp)) {
				list = getRecommendQuanList(resp, areaCode);

				if (list != null && !list.isEmpty()) {
					// 将结果缓存到memcache
					memcachedClient.set(memKey, MEMCACHE_REFRESH_TIME, list);
				}
			}
		}
		catch (Exception e) {
			logger.error("调用推荐接口获取代金券信息出现异常：" + e);
		}

		return list;
	}

	/**
	 * 代金券详情页推荐信息
	 * 
	 * @return
	 */
	public List<Map<String, String>> getPaySuccessRecommendQuanList(String mobile, String topn, String areaCode, String userId, String remoteIp,
	        boolean isLogin) {
		logger.info("请求地市：" + areaCode);
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();

		// 优先从缓存中获取
		String memKey = "";
		if (isLogin) {
			memKey = userId + "_" + areaCode + "_ddcg";
		} else {
			memKey = remoteIp + "_" + areaCode + "_ddcg";
		}

		try {
			Object obj = memcachedClient.get(memKey);
			if (obj != null) {
				logger.info("从缓存中获取订单成功推荐,memKey=" + memKey);
				return (List<Map<String, String>>) obj;
			}

			// 没有缓存则调用推荐接口获取
			String interfaceUrl = appConfig.getRecommendInterfaceUrl();
			Map<String, String> params = initParams(mobile, "ddcg", topn, areaCode);

			String resp = WebUtils.doPost(interfaceUrl, params, CONNECT_TIMEOUT, READ_TIMEOUT);
			logger.info("调用订单成功推荐接口resp:" + resp);

			if (StringUtils.isNotBlank(resp)) {
				list = getRecommendGoodsList(resp, areaCode);

				if (list != null && !list.isEmpty()) {
					// 将结果缓存到memcache
					memcachedClient.set(memKey, MEMCACHE_REFRESH_TIME, list);
				}
			}
		}
		catch (Exception e) {
			logger.error("调用订单成功推荐接口获取代金券信息出现异常：" + e);
		}

		return list;
	}

	@SuppressWarnings("unchecked")
	private List<Map<String, String>> getRecommendQuanList(String resp, String areaCode) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		try {
			if (StringUtils.isNotBlank(resp)) {
				// 解析推荐接口返回的优惠券id
				SAXBuilder builder = new SAXBuilder();
				Document doc = builder.build(new ByteArrayInputStream(resp.getBytes()));
				Element root = doc.getRootElement();
				List<Element> resultList = root.getChildren("result");
				List<Element> itemList = null;
				StringBuffer ids = new StringBuffer();
				Map<String, String> referMap = new HashMap<String, String>();
				if (resultList != null && resultList.size() > 0) {
					for (Element ele : resultList) {
						if ("cashVoucher".equals(ele.getAttributeValue("ContentType"))) {
							itemList = ele.getChildren();
							for (Element item : itemList) {
								ids.append(item.getChildText("id")).append(',');
								referMap.put(item.getChildText("id"), item.getChildText("referer"));
							}

							if (ids.length() > 0) {
								list = dao.sorlSearchGoods(ids.deleteCharAt(ids.length() - 1).toString(), areaCode);
							}

							if (list != null) {
								for (Map<String, String> map : list) {
									map.put("REFERER", referMap.get(map.get("ID").toString()));
								}
							} else {
								list = new ArrayList<Map<String, String>>();
							}
						}
					}
				}
			}
		}
		catch (IOException e) {
			logger.error("调用推荐接口获取代金券信息出现异常：" + e);
		}
		catch (JDOMException e) {
			logger.error("调用推荐接口获取代金券信息出现异常：" + e);
		}
		return list;
	}

	@SuppressWarnings({ "unchecked", "static-access" })
	private List<Map<String, String>> getRecommendGoodsList(String resp, String areaCode) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		try {
			if (StringUtils.isNotBlank(resp)) {
				// 解析推荐接口返回的商城商品id
				SAXBuilder builder = new SAXBuilder();
				Document doc = builder.build(new ByteArrayInputStream(resp.getBytes()));
				Element root = doc.getRootElement();
				Element e = root.getChild("result");
				if (e != null) {
					List<Element> itemList = e.getChildren();
					Map<String, String> referMap = new HashMap<String, String>();
					StringBuffer teamIds = new StringBuffer();
					for (Element item : itemList) {
						teamIds.append(item.getChildText("id")).append(',');
						referMap.put(item.getChildText("id"), item.getChildText("referer"));
					}

					if (teamIds.length() > 0) {
						list = dao.getRecommendGoodsList(teamIds.deleteCharAt(teamIds.length() - 1).toString());
					}
					if (list != null && !list.isEmpty()) {
						for (Map<String, String> map : list) {
							map.put("HREF", pathUtil.getPathById(pathUtil.TYPE_ITEM, Long.valueOf(map.get("ID"))));
							map.put("IMG_PATH",
							        pathUtil.getPathById(pathUtil.TYPE_ITEM_PIC, Long.valueOf(map.get("ID"))) + "/N5/" + map.get("IMG_PATH"));
							map.put("REFERER", referMap.get(map.get("ID").toString()));
						}
					}
				}
			}
		}
		catch (Exception e) {
			logger.error("调用推荐接口获取商城商品信息出现异常：", e);
		}

		return list;
	}

	/**
	 * 初始化推荐接口参数
	 * 
	 * @param mobile
	 * @param posid
	 * @param topn
	 * @param areaCode
	 * @return
	 */
	private Map<String, String> initParams(String mobile, String posid, String topn, String areaCode) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("customerid", "smweb");
		params.put("m", mobile);
		params.put("ut", "0");
		params.put("posid", posid);
		params.put("topn", topn);
		params.put("pg", "");
		params.put("v", "1.1");
		params.put("id", "");
		params.put("ids", "");
		params.put("uuuid", java.util.UUID.randomUUID().toString());
		params.put("extParams", "area:" + areaCode);

		return params;
	}

}
