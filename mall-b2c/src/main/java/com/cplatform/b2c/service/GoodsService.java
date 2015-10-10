package com.cplatform.b2c.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.rubyeye.xmemcached.MemcachedClient;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.cplatform.b2c.model.SessionUser;
import com.cplatform.b2c.repository.GoodsDao;
import com.cplatform.b2c.util.AppConfig;
import com.cplatform.b2c.util.Constants;

/**
 * 代金券频道Service. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2013-12-24 下午3:19:33
 * <p>
 * Company: 苏州宽连信息技术有限公司
 * <p>
 * 
 * @author zhangmeng@c-platform.com
 * @version 1.0.0
 */
@Service
public class GoodsService {

	/** 日志记录器 */
	private final Logger logger = Logger.getLogger(getClass());

	@Autowired
	GoodsDao goodsDao;

	@Autowired
	CommonCacheService commonCacheService;

	@Autowired
	AppConfig appConfig;

	@Autowired
	MemcachedClient memcachedClient;

	/** 用户未登录且未选定地市时，默认地市为“南京” */
	private static final String DEFAULT_QUERY_REGION_CODE = "320100";

	/** memcache缓存key值前缀 */
	private static final String MEMCACHED_KEY_PREFIX = "djq_";

	private static final long MEMCACHED_TIMEOUT = 10 * 1000;

	/** memcached缓存有效期(30分钟). */
	private static final int MEMCACHED_EXPIRE_TIME = 30 * 60;

	@Autowired
	private JmsMessageService jmsMessageService;

	/**
	 * 获取代金券热门关键词
	 * 
	 * @param regionCode
	 * @return
	 */
	public List<Map<String, String>> getHotTopic(String regionCode) {
		List<Map<String, String>> hotTopicMap = new ArrayList<Map<String, String>>();
		try {
			List<String[]> hotTopicList = goodsDao.getHotTopicList(regionCode);
			if (hotTopicList != null && !hotTopicList.isEmpty()) {
				JSONObject json = null;
				Map<String, String> map = null;
				for (String[] hotTopic : hotTopicList) {
					json = JSONObject.fromObject(hotTopic[0]);
					map = new HashMap<String, String>();
					map.put("title", json.getString("title"));
					map.put("href", json.getString("href"));
					hotTopicMap.add(map);
				}
			}
		}
		catch (Exception e) {
			logger.error("获取代金券热门关键词失败，", e);
		}
		return hotTopicMap;
	}

	/**
	 * 获取登录用户所在城市regionCode，若未登录，则取默认值“南京”
	 * 
	 * @param response
	 * @return
	 */
	public String getSessionUserArea(HttpServletResponse response) {
		String regionCode = "";
		// 判断是否登录，登录则取登录用户所在地市regionCode，否则取默认值“南京”
		SessionUser user = SessionUser.getSessionUser(response);
		if (user != null) {
			regionCode = user.getAreaCode();
		} else {
			regionCode = DEFAULT_QUERY_REGION_CODE;
		}

		return regionCode;
	}

	/**
	 * 生成区域模块页面html
	 * 
	 * @param title
	 * @param regionCode
	 * @param subRegionCode
	 * @param goodsType
	 * @param subGoodsType
	 * @param price
	 * @param sort
	 * @param isNewOrder
	 * @param allowScore
	 * @param allowCoin
	 * @param allowBill
	 * @param regionCountMap
	 * @return
	 */
	public String getRegionHtml(String title, String regionCode, String subRegionCode, String goodsType, String subGoodsType, String price,
	        String sort, String isNewOrder, String allowScore, String allowCoin, String allowBill, Map<String, Long> regionCountMap) {
		StringBuilder htmlBuff = new StringBuilder(2000);
		try {
			htmlBuff.append("<div class=\"quan_menu_content\">");
			htmlBuff.append("<a href=\"")
			        .append(getStaticUrl(title, regionCode, "", goodsType, subGoodsType, price, sort, isNewOrder, allowScore, allowCoin, allowBill,
			                             "1")).append("\"");
			// 如果没有选中任何区域，则“全部”默认选中
			if (StringUtils.isEmpty(subRegionCode)) {
				htmlBuff.append(" class=\"hover\"");
			}
			htmlBuff.append(">全部<span>&nbsp;</span></a>");
			List<String[]> regionList = commonCacheService.getRegionListByRegionCode(regionCode);
			// Long count = null;
			if (regionList != null && !regionList.isEmpty()) {
				String[] region = null;
				for (int i = 0; i < regionList.size(); i++) {
					region = regionList.get(i);
					// count = regionCountMap.get(region[0]);
					// count = (count == null) ? 0L : count;
					// if (count > 0L) {
					htmlBuff.append("<a onclick=\"\" href=\"")
					        .append(getStaticUrl(title, regionCode, region[0], goodsType, subGoodsType, price, sort, isNewOrder, allowScore,
					                             allowCoin, allowBill, "1")).append("\"");
					if (region[0].equals(subRegionCode)) {
						htmlBuff.append(" class=\"hover\">").append(region[1]);
					} else {
						htmlBuff.append(" >").append(region[1]);
					}

					// htmlBuff.append("<span>").append(count.longValue()).append("</span>");
					htmlBuff.append("<span>&nbsp;</span>");
					htmlBuff.append("</a>");
					// }
				}
			}
			htmlBuff.append("</div>");
		}
		catch (Exception e) {
			logger.error("生成区域模块静态html出现异常，", e);
		}

		return htmlBuff.toString();
	}

	/**
	 * 生成商品分类模块页面html
	 * 
	 * @param title
	 * @param regionCode
	 * @param subRegionCode
	 * @param goodsType
	 * @param subGoodsType
	 * @param price
	 * @param sort
	 * @param isNewOrder
	 * @param allowScore
	 * @param allowCoin
	 * @param allowBill
	 * @param goodsTypeCountMap
	 * @return
	 */
	public String getGoodsTypeHtml(String title, String regionCode, String subRegionCode, String goodsType, String subGoodsType, String price,
	        String sort, String isNewOrder, String allowScore, String allowCoin, String allowBill, Map<String, Long> goodsTypeCountMap) {
		StringBuilder htmlBuff = new StringBuilder(2000);
		try {
			htmlBuff.append("<div class=\"quan_menu_content\" id=\"quan_menu_nav\">");
			htmlBuff.append("<div class=\"quan_menu_tab\">");
			htmlBuff.append("<a href=\"")
			        .append(getStaticUrl(title, regionCode, subRegionCode, "", "", price, sort, isNewOrder, allowScore, allowCoin, allowBill, "1"))
			        .append("\"");
			// 如果没有选中任何商品分类，则“全部”默认选中
			if (StringUtils.isEmpty(goodsType)) {
				htmlBuff.append(" class=\"hover\"");
			}
			htmlBuff.append(">全部<span>&nbsp;</span></a>");
			List<String[]> goodsTypeList = commonCacheService.getSysTypeByPId(Constants.VIRTUAL_GOODS_TYPE_PID);
			Long count = null;
			// 生成二级商品分类静态html
			if (goodsTypeList != null && !goodsTypeList.isEmpty()) {
				for (String[] goodsTypeArr : goodsTypeList) {
					count = goodsTypeCountMap.get(goodsTypeArr[0]);
					count = (count == null) ? 0L : count;
					if (count > 0L) {
						htmlBuff.append("<a href=\"")
						        .append(getStaticUrl(title, regionCode, subRegionCode, goodsTypeArr[0], "", price, sort, isNewOrder, allowScore,
						                             allowCoin, allowBill, "1")).append("\"");
						if (goodsTypeArr[0].equals(goodsType)) {
							htmlBuff.append(" class=\"hover\">").append(goodsTypeArr[1]);
						} else {
							htmlBuff.append(" >").append(goodsTypeArr[1]);
						}
						htmlBuff.append("<span>").append(count.longValue()).append("&nbsp;</span>");
						htmlBuff.append("</a>");
					}
				}
			}
			boolean flag = false;
			StringBuilder tmpBuff = new StringBuilder(200);
			// 如果二级商品分类不为空，则将二级商品分类的子分类也展示出来
			if (StringUtils.isNotEmpty(goodsType)) {
				List<String[]> subGoodsTypeList = commonCacheService.getSysTypeByPId(goodsType);
				if (subGoodsTypeList != null && !subGoodsTypeList.isEmpty()) {
					tmpBuff.append("<div class=\"quan_menu_tab_content\">");
					for (String[] subGoodsTypeArr : subGoodsTypeList) {
						count = goodsTypeCountMap.get(subGoodsTypeArr[0]);
						count = (count == null) ? 0L : count;
						if (count > 0L) {
							flag = true;
							tmpBuff.append("<a href=\"")
							        .append(getStaticUrl(title, regionCode, subRegionCode, goodsType, subGoodsTypeArr[0], price, sort, isNewOrder,
							                             allowScore, allowCoin, allowBill, "1")).append("\"");
							if (subGoodsTypeArr[0].equals(subGoodsType)) {
								tmpBuff.append(" class=\"hover\">").append(subGoodsTypeArr[1]);
							} else {
								tmpBuff.append(" >").append(subGoodsTypeArr[1]);
							}
							tmpBuff.append("<span>").append(count.longValue()).append("&nbsp;</span>");
							tmpBuff.append("</a>");
						}
					}
					tmpBuff.append("</div>");
				}
			}
			if (flag) {
				htmlBuff.append(tmpBuff);
			}
			htmlBuff.append("</div>");
		}
		catch (Exception e) {
			logger.error("生成商品分类模块静态html出现异常，", e);
		}

		return htmlBuff.toString();
	}

	/**
	 * 生成价格区间模块页面html
	 * 
	 * @param title
	 * @param regionCode
	 * @param subRegionCode
	 * @param goodsType
	 * @param subGoodsType
	 * @param price
	 * @param sort
	 * @param isNewOrder
	 * @param allowScore
	 * @param allowCoin
	 * @param allowBill
	 * @param priceCountMap
	 * @return
	 */
	private String getPriceHtml(String title, String regionCode, String subRegionCode, String goodsType, String subGoodsType, String price,
	        String sort, String isNewOrder, String allowScore, String allowCoin, String allowBill, Map<String, Long> priceCountMap) {
		StringBuilder htmlBuff = new StringBuilder(1000);
		try {
			htmlBuff.append("<div class=\"quan_menu_content\">");
			htmlBuff.append("<a href=\"")
			        .append(getStaticUrl(title, regionCode, subRegionCode, goodsType, subGoodsType, "", sort, isNewOrder, allowScore, allowCoin,
			                             allowBill, "1")).append("\"");
			// 如果没有选中任何价格区间，则“全部”默认选中
			if (StringUtils.isEmpty(price)) {
				htmlBuff.append(" class=\"hover\"");
			}
			htmlBuff.append(">全部<span>&nbsp;</span></a>");
			Map<String, String> priceMap = Constants.PRICE_MAP;
			String[] priceKeys = priceMap.keySet().toArray(new String[priceMap.size()]);
			Long count = null;
			for (String priceKey : priceKeys) {
				count = priceCountMap.get(priceKey);
				count = (count == null) ? 0 : count;
				if (count > 0L) {
					htmlBuff.append("<a href=\"")
					        .append(getStaticUrl(title, regionCode, subRegionCode, goodsType, subGoodsType, priceKey, sort, isNewOrder, allowScore,
					                             allowCoin, allowBill, "1")).append("\"");
					if (priceKey.equals(price)) {
						htmlBuff.append(" class=\"hover\">").append(priceMap.get(priceKey));
					} else {
						htmlBuff.append(">").append(priceMap.get(priceKey));
					}
					htmlBuff.append("<span>").append(count.longValue()).append("&nbsp;</span>");
					htmlBuff.append("</a>");
				}
			}

			htmlBuff.append("</div>");
		}
		catch (Exception e) {
			logger.error("生成价格区间模块静态html出现异常，", e);
		}

		return htmlBuff.toString();
	}

	/**
	 * 生成分页栏html
	 * 
	 * @param title
	 * @param regionCode
	 * @param subRegionCode
	 * @param goodsType
	 * @param subGoodsType
	 * @param price
	 * @param sort
	 * @param isNewOrder
	 * @param allowScore
	 * @param allowCoin
	 * @param allowBill
	 * @param curpage
	 * @param pageSize
	 * @param totalCount
	 * @param totalPage
	 * @return
	 */
	private String getPageInfoHtml(String title, String regionCode, String subRegionCode, String goodsType, String subGoodsType, String price,
	        String sort, String isNewOrder, String allowScore, String allowCoin, String allowBill, String curpage, int pageSize, long totalCount,
	        long totalPage) {
		StringBuilder htmlBuff = new StringBuilder(1000);
		try {
			if (!"1".equals(curpage)) {
				// 首页
				htmlBuff.append("<a href=\"")
				        .append(getStaticUrl(title, regionCode, subRegionCode, goodsType, subGoodsType, price, sort, isNewOrder, allowScore,
				                             allowCoin, allowBill, "1")).append("\">首页</a>");
				// 上一页
				htmlBuff.append("<a href=\"")
				        .append(getStaticUrl(title, regionCode, subRegionCode, goodsType, subGoodsType, price, sort, isNewOrder, allowScore,
				                             allowCoin, allowBill, String.valueOf(Long.valueOf(curpage) - 1))).append("\">上一页</a>");
			}
			for (long i = 1; i <= totalPage; i++) {
				htmlBuff.append("<a href=\"")
				        .append(getStaticUrl(title, regionCode, subRegionCode, goodsType, subGoodsType, price, sort, isNewOrder, allowScore,
				                             allowCoin, allowBill, String.valueOf(i))).append("\"");
				if (Long.valueOf(curpage) == i) {
					htmlBuff.append(" class=\"hover\">").append(i).append("</a>");
				} else {
					htmlBuff.append(">").append(i).append("</a>");
				}
			}
			if (Long.valueOf(curpage) != totalPage) {
				// 下一页
				htmlBuff.append("<a href=\"")
				        .append(getStaticUrl(title, regionCode, subRegionCode, goodsType, subGoodsType, price, sort, isNewOrder, allowScore,
				                             allowCoin, allowBill, String.valueOf(Long.valueOf(curpage) + 1))).append("\">下一页</a>");
				// 末页
				htmlBuff.append("<a href=\"")
				        .append(getStaticUrl(title, regionCode, subRegionCode, goodsType, subGoodsType, price, sort, isNewOrder, allowScore,
				                             allowCoin, allowBill, String.valueOf(totalPage))).append("\">末页</a>");
			}
			htmlBuff.append("<br/>共 ").append(totalCount).append(" 条,每页 ").append(pageSize).append(" 条");
		}
		catch (Exception e) {
			logger.error("生成分页栏html出现异常，", e);
		}

		return htmlBuff.toString();
	}

	/**
	 * 生成页面顶部分页栏
	 * 
	 * @param title
	 * @param regionCode
	 * @param subRegionCode
	 * @param goodsType
	 * @param subGoodsType
	 * @param price
	 * @param sort
	 * @param isNewOrder
	 * @param allowScore
	 * @param allowCoin
	 * @param allowBill
	 * @param curpage
	 * @param totalPage
	 * @return
	 */
	private String getTopPageInfoHtml(String title, String regionCode, String subRegionCode, String goodsType, String subGoodsType, String price,
	        String sort, String isNewOrder, String allowScore, String allowCoin, String allowBill, String curpage, long totalPage) {
		StringBuilder buff = new StringBuilder(1000);
		buff.append("<div class=\"quan_menu_page_nav\"> ");
		buff.append("<label>第").append(curpage).append("/").append(totalPage).append(":&nbsp;</label> ");
		// 前一页
		buff.append("<a href=\"");
		if ("1".equals(curpage)) {
			buff.append("#");
		} else {
			buff.append(getStaticUrl(title, regionCode, subRegionCode, goodsType, subGoodsType, price, sort, isNewOrder, allowScore, allowCoin,
			                         allowBill, String.valueOf(Long.valueOf(curpage) - 1)));
		}
		buff.append("\" class=\"quan_menu_btn_prev\"></a>");
		// 后一页
		buff.append("<a href=\"");
		if (Long.valueOf(curpage) == totalPage) {
			buff.append("#");
		} else {
			buff.append(getStaticUrl(title, regionCode, subRegionCode, goodsType, subGoodsType, price, sort, isNewOrder, allowScore, allowCoin,
			                         allowBill, String.valueOf(Long.valueOf(curpage) + 1)));
		}
		buff.append("\" class=\"quan_menu_btn_next\"></a></div>");
		return buff.toString();
	}

	/**
	 * 获取页面链接
	 * 
	 * @param title
	 * @param regionCode
	 * @param subRegionCode
	 * @param goodsType
	 * @param subGoodsType
	 * @param price
	 * @param sort
	 * @param isNewOrder
	 * @param allowScore
	 * @param allowCoin
	 * @param allowBill
	 * @param curpage
	 * @return
	 */
	public String getStaticUrl(String title, String regionCode, String subRegionCode, String goodsType, String subGoodsType, String price,
	        String sort, String isNewOrder, String allowScore, String allowCoin, String allowBill, String curpage) {
		StringBuilder urlBuf = new StringBuilder(200);
		urlBuf.append(appConfig.getShlmVoucherSrvHost()).append("goods/goodsList.chtml?");
		urlBuf.append("title=").append(title);
		urlBuf.append("&regionCode=").append(regionCode);
		urlBuf.append("&subRegionCode=").append(subRegionCode);
		urlBuf.append("&goodsType=").append(goodsType);
		urlBuf.append("&subGoodsType=").append(subGoodsType);
		urlBuf.append("&price=").append(price);
		urlBuf.append("&sort=").append(sort);
		urlBuf.append("&isNewOrder=").append(isNewOrder);
		urlBuf.append("&allowScore=").append(allowScore);
		urlBuf.append("&allowCoin=").append(allowCoin);
		urlBuf.append("&allowBill=").append(allowBill);
		urlBuf.append("&curpage=").append(curpage);
		return urlBuf.toString();
	}

	/**
	 * @param title
	 * @param regionCode
	 * @param subRegionCode
	 * @param goodsType
	 * @param subGoodsType
	 * @param price
	 * @param sort
	 * @param isNewOrder
	 * @param allowScore
	 * @param allowCoin
	 * @param allowBill
	 * @param curpage
	 * @param pageSize
	 */
	@SuppressWarnings("unchecked")
	public void solrSearchGoods(String title, String regionCode, String subRegionCode, String goodsType, String subGoodsType, String price,
	        String sort, String isNewOrder, String allowScore, String allowCoin, String allowBill, String curpage, int pageSize, Model model) {
		// 如果三级商品分类不为空，则取三级商品分类查询，否则取二级商品分类查询
		String queryGoodsType = StringUtils.isNotEmpty(subGoodsType) ? subGoodsType : (StringUtils.isNotEmpty(goodsType) ? goodsType : "");
		Map<String, Object> retMap = null;
		if ((StringUtils.isNotEmpty(regionCode) && StringUtils.isNotEmpty(curpage) && Integer.parseInt(curpage) <= 5)
		        && (StringUtils.isEmpty(title) && StringUtils.isEmpty(subRegionCode) && StringUtils.isEmpty(goodsType)
		                && StringUtils.isEmpty(subGoodsType) && StringUtils.isEmpty(price) && StringUtils.equals(sort, "0")
		                && StringUtils.isEmpty(isNewOrder) && StringUtils.isEmpty(allowScore) && StringUtils.isEmpty(allowCoin) && StringUtils
		                    .isEmpty(allowBill))) {
			String key = MEMCACHED_KEY_PREFIX + regionCode + curpage;
			// 首页数据从缓存中获取（仅有地市和分页参数）
			try {
				retMap = memcachedClient.get(key, MEMCACHED_TIMEOUT);
			}
			catch (Exception e) {
				logger.error("从Memcache中获取代金券频道数据异常，", e);
			}
			if (retMap == null) {
				logger.info("memcache中未找到代金券【regionCode=" + regionCode + ",curpage=" + curpage + "】缓存数据，从solr中获取。");
				retMap = goodsDao.sorlSearchGoods(title, regionCode, subRegionCode, queryGoodsType, price, sort, isNewOrder, allowScore, allowCoin,
				                                  allowBill, curpage, pageSize);
				List<Map<String, Object>> resultList = (List<Map<String, Object>>) retMap.get("resultList");
				if (resultList != null && !resultList.isEmpty()) {
					logger.info("从solr中获取到代金券数据【regionCode=" + regionCode + ",curpage=" + curpage + "】");
					try {
						memcachedClient.set(key, MEMCACHED_EXPIRE_TIME, retMap);
					}
					catch (Exception e) {
						logger.error("将代金券首页数据缓存到memcache出现异常，", e);
					}
				} else {
					logger.info("从solr中未获取到代金券数据【regionCode=" + regionCode + ",curpage=" + curpage + "】");
					try {
						// 首页无数据，发送告警短信
						String mobileList = appConfig.getDjqSmsMobileList();
						String[] mobiles = mobileList.split(",");
						for (String mobile : mobiles) {
							jmsMessageService.sendSms(appConfig.getDjqSmsMessage() + "(" + regionCode + "/" + curpage + ")", mobile);
						}
					}
					catch (Exception ex) {
						logger.warn("代金券首页无数据，发送告警短信出现异常：", ex);
					}
					return;
				}
			}
		}
		if (retMap == null) {
			retMap = goodsDao.sorlSearchGoods(title, regionCode, subRegionCode, queryGoodsType, price, sort, isNewOrder, allowScore, allowCoin,
			                                  allowBill, curpage, pageSize);
		}
		List<Map<String, Object>> goodsList = (List<Map<String, Object>>) retMap.get("resultList");
		if (goodsList != null) {
			model.addAttribute("goodsList", goodsList);
		}

		// 生成区域模块页面html
		Map<String, Long> regionCountMap = (Map<String, Long>) retMap.get("regionCountMap");
		regionCountMap = (regionCountMap == null) ? new HashMap<String, Long>() : regionCountMap;
		model.addAttribute("regionHtml",
		                   getRegionHtml(title, regionCode, subRegionCode, goodsType, subGoodsType, price, sort, isNewOrder, allowScore, allowCoin,
		                                 allowBill, regionCountMap));

		// 生成商品分类模块页面html
		Map<String, Long> goodsTypeCountMap = (Map<String, Long>) retMap.get("goodsTypeCountMap");
		goodsTypeCountMap = (goodsTypeCountMap == null) ? new HashMap<String, Long>() : goodsTypeCountMap;
		model.addAttribute("goodsTypeHtml",
		                   getGoodsTypeHtml(title, regionCode, subRegionCode, goodsType, subGoodsType, price, sort, isNewOrder, allowScore,
		                                    allowCoin, allowBill, goodsTypeCountMap));

		// 生成价格区间模块页面html
		Map<String, Long> priceCountMap = (Map<String, Long>) retMap.get("priceCountMap");
		priceCountMap = (priceCountMap == null) ? new HashMap<String, Long>() : priceCountMap;
		model.addAttribute("priceHtml",
		                   getPriceHtml(title, regionCode, subRegionCode, goodsType, subGoodsType, price, sort, isNewOrder, allowScore, allowCoin,
		                                allowBill, priceCountMap));

		// 生成分页条
		Long totalCount = (Long) retMap.get("totalCount");
		totalCount = (totalCount == null) ? 0L : totalCount;
		Long totalPage = (Long) retMap.get("totalPage");
		totalPage = (totalPage == null) ? 0L : totalPage;
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("totalPage", totalPage);
		if (totalPage > 1) {
			model.addAttribute("pageInfoHtml",
			                   getPageInfoHtml(title, regionCode, subRegionCode, goodsType, subGoodsType, price, sort, isNewOrder, allowScore,
			                                   allowCoin, allowBill, curpage, pageSize, totalCount, totalPage));
			model.addAttribute("topPageInfoHtml",
			                   getTopPageInfoHtml(title, regionCode, subRegionCode, goodsType, subGoodsType, price, sort, isNewOrder, allowScore,
			                                      allowCoin, allowBill, curpage, totalPage));
		}
	}

	/**
	 * 初始化排序和各种支付方式等过滤条件跳转url
	 * 
	 * @param title
	 * @param regionCode
	 * @param subRegionCode
	 * @param goodsType
	 * @param subGoodsType
	 * @param price
	 * @param sort
	 * @param isNewOrder
	 * @param allowScore
	 * @param allowCoin
	 * @param allowBill
	 * @param curpage
	 * @param model
	 */
	public void createOrderUrlAndPayUrl(String title, String regionCode, String subRegionCode, String goodsType, String subGoodsType, String price,
	        String sort, String isNewOrder, String allowScore, String allowCoin, String allowBill, String curpage, Model model) {
		// 排序（默认排序）
		model.addAttribute("defaultOrderUrl",
		                   getStaticUrl(title, regionCode, subRegionCode, goodsType, subGoodsType, price, "0", isNewOrder, allowScore, allowCoin,
		                                allowBill, curpage));
		// 排序（销量倒序）
		model.addAttribute("descSaleNumUrl",
		                   getStaticUrl(title, regionCode, subRegionCode, goodsType, subGoodsType, price, "-1", isNewOrder, allowScore, allowCoin,
		                                allowBill, curpage));
		// 排序（销量正序）
		model.addAttribute("ascSaleNumUrl",
		                   getStaticUrl(title, regionCode, subRegionCode, goodsType, subGoodsType, price, "1", isNewOrder, allowScore, allowCoin,
		                                allowBill, curpage));
		// 排序（时间倒序）
		model.addAttribute("descOnlineTimeUrl",
		                   getStaticUrl(title, regionCode, subRegionCode, goodsType, subGoodsType, price, "-2", isNewOrder, allowScore, allowCoin,
		                                allowBill, curpage));
		// 排序（时间正序）
		model.addAttribute("ascOnlineTimeUrl",
		                   getStaticUrl(title, regionCode, subRegionCode, goodsType, subGoodsType, price, "2", isNewOrder, allowScore, allowCoin,
		                                allowBill, curpage));

		// 排序（时间倒序）
		model.addAttribute("descPriceUrl",
		                   getStaticUrl(title, regionCode, subRegionCode, goodsType, subGoodsType, price, "-3", isNewOrder, allowScore, allowCoin,
		                                allowBill, curpage));
		// 排序（时间正序）
		model.addAttribute("ascPriceUrl",
		                   getStaticUrl(title, regionCode, subRegionCode, goodsType, subGoodsType, price, "3", isNewOrder, allowScore, allowCoin,
		                                allowBill, curpage));
		// 单击“只看新单”复选框跳转url
		model.addAttribute("checkNewOrderUrl",
		                   getStaticUrl(title, regionCode, subRegionCode, goodsType, subGoodsType, price, sort, "1".equals(isNewOrder) ? "" : "1",
		                                allowScore, allowCoin, allowBill, "1"));
		// 单击“积分/M值”复选框跳转url
		model.addAttribute("checkAllowScoreUrl",
		                   getStaticUrl(title, regionCode, subRegionCode, goodsType, subGoodsType, price, sort, isNewOrder,
		                                "1".equals(allowScore) ? "" : "1", allowCoin, allowBill, "1"));
		// 单击“商城币”复选框跳转url
		model.addAttribute("checkAllowCoinUrl",
		                   getStaticUrl(title, regionCode, subRegionCode, goodsType, subGoodsType, price, sort, isNewOrder, allowScore,
		                                "1".equals(allowCoin) ? "" : "1", allowBill, "1"));
		// 单击“话费”复选框跳转url
		model.addAttribute("checkAllowBillUrl",
		                   getStaticUrl(title, regionCode, subRegionCode, goodsType, subGoodsType, price, sort, isNewOrder, allowScore, allowCoin,
		                                "1".equals(allowBill) ? "" : "1", "1"));
	}

}
