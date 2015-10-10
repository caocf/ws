package com.cplatform.b2c.repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.SortClause;
import org.apache.solr.client.solrj.SolrRequest;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.FacetField.Count;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cplatform.b2c.util.AppConfig;
import com.cplatform.b2c.util.Constants;
import com.cplatform.b2c.util.PathUtil;
import com.cplatform.b2c.util.StringUtil;
import com.cplatform.b2c.util.TimeUtil;
import com.cplatform.dbhelp.DbHelper;
import com.cplatform.dbhelp.util.ArrayUtils;

/**
 * 代金券频道Dao. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2013-12-24 下午3:30:56
 * <p>
 * Company: 苏州宽连信息技术有限公司
 * <p>
 * 
 * @author zhangmeng@c-platform.com
 * @version 1.0.0
 */
@Repository
public class GoodsDao {

	/** 日志记录器 */
	private final Logger logger = Logger.getLogger(getClass());

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	AppConfig appConfig;

	@Autowired
	PathUtil pathUtil;

	@Autowired
	DbHelper dbHelper;

	/** 参数最大长度 */
	private static final int PARAM_MAX_LENGTH = 50;

	/**
	 * 获取代金券热门关键词
	 * 
	 * @param regionCode
	 * @return
	 */
	public List<String[]> getHotTopicList(String regionCode) {
		List<String[]> hotTopicList = new ArrayList<String[]>();
		try {
			StringBuilder sqlBuff = new StringBuilder(200);
			sqlBuff.append("select content ");
			sqlBuff.append("  from (select content, row_number() over(order by sort_no) row_number ");
			sqlBuff.append("          from t_page_module_data");
			sqlBuff.append("         where module_code = 'DJQRM'");
			sqlBuff.append("           and instr(region_code || ',', ?||',') >= 1");
			sqlBuff.append("           and status = 1)");
			sqlBuff.append(" where row_number <= 10"); // 按sort_no排序，取前10个
			hotTopicList = dbHelper.getArrayList(sqlBuff.toString(), regionCode);
		}
		catch (Exception e) {
			logger.error("获取代金券热门关键词失败，", e);
		}
		return hotTopicList;
	}

	/**
	 * 调用搜索引擎查询代金券信息
	 * 
	 * @param title
	 * @param regionCode
	 * @param subRegionCode
	 * @param goodsType
	 * @param price
	 * @param sort
	 * @param isNewOrder
	 * @param allowScore
	 * @param allowCoin
	 * @param allowBill
	 * @param curPage
	 * @param pageSize
	 * @return
	 */
	public Map<String, Object> sorlSearchGoods(String title, String regionCode, String subRegionCode, String goodsType, String price, String sort,
	        String isNewOrder, String allowScore, String allowCoin, String allowBill, String curPage, int pageSize) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		try {
			SolrQuery query = getSolrQuery(title, regionCode, subRegionCode, goodsType, price, sort, isNewOrder, allowScore, allowCoin, allowBill,
			                               curPage, pageSize, false, false, false);
			logger.info("检索条件：" + query.toString());

			Long totalCount = 0L; // 总记录数
			Long totalPage = 0L; // 总页数
			// solr返回的各区域查询数量
			Map<String, Long> regionCountMap = new HashMap<String, Long>();
			// solr返回的各分类查询数量
			Map<String, Long> goodsTypeCountMap = new HashMap<String, Long>();
			// solr返回的各价格区间查询数量
			Map<String, Long> priceCountMap = new HashMap<String, Long>();
			List<Map<String, Object>> ls = new ArrayList<Map<String, Object>>();
			HttpSolrServer solrServer = new HttpSolrServer(appConfig.getQueryGoodsSolrUrl());
			solrServer.setSoTimeout(60000); // socket read timeout
			solrServer.setConnectionTimeout(60000);
			solrServer.setMaxTotalConnections(1000);
			QueryResponse rsp = solrServer.query(query, SolrRequest.METHOD.POST);
			if (rsp != null) {
				SolrDocumentList docs = rsp.getResults();
				logger.info("调用代金券搜索引擎返回结果记录数：" + docs.getNumFound());
				Map<String, Object> dataMap = null;
				String goodsId = "";
				String itemSource = "";
				List<Object> blockList = null;
				for (SolrDocument doc : docs) {
					dataMap = new HashMap<String, Object>();
					goodsId = StringUtil.getString(doc.getFieldValue("id"));
					dataMap.put("id", goodsId); // 商品ID
					dataMap.put("name", StringUtil.getString(doc.getFieldValue("name"))); // 商品名称
					itemSource = StringUtil.getString(doc.getFieldValue("itemSource"));
					dataMap.put("itemSource", itemSource); // 商品来源，M表示是商城的商品。S表示商盟的商品。L表示联动的商品。
					dataMap.put("priceOrigin", StringUtil.getString(doc.getFieldValue("priceOrigin")));// 原价格
					dataMap.put("price", StringUtil.getString(doc.getFieldValue("price"))); // 售价
					String priceMember = StringUtil.getString(doc.getFieldValue("priceMember"));
					if (StringUtils.isNotEmpty(priceMember) && Double.valueOf(priceMember) > 0) {
						dataMap.put("priceMember", priceMember); // 会员价
					}
					dataMap.put("payCash", StringUtil.getString(doc.getFieldValue("payCash"))); // 是否支持现金支付
					dataMap.put("payScore", StringUtil.getString(doc.getFieldValue("payScore"))); // 是否支持积分支付
					dataMap.put("payCoin", StringUtil.getString(doc.getFieldValue("payCoin"))); // 是否支持商城币支付
					dataMap.put("payBill", StringUtil.getString(doc.getFieldValue("payBill"))); // 是否支持话费支付
					dataMap.put("img", getImgPath(goodsId, itemSource, StringUtil.getString(doc.getFieldValue("img")))); // 图片
					dataMap.put("stockNum", StringUtil.getString(doc.getFieldValue("stockNum"))); // 库存
					dataMap.put("saleNum", StringUtil.getString(StringUtil.getString(doc.getFieldValue("saleNum")), "0")); // 销量
					dataMap.put("itemOrder", StringUtil.getString(doc.getFieldValue("itemOrder"))); // 排序字段，默认为0
					dataMap.put("isNewOrder", isNewOrder(StringUtil.getString(doc.getFieldValue("onlineTime")), TimeUtil.addDays(-7))); // 是否是新单
					dataMap.put("priceScore",
					            getScorePrice(itemSource, dataMap.get("payCash").toString(), dataMap.get("payScore").toString(), dataMap.get("price")
					                    .toString(), StringUtil.getString(doc.getFieldValue("priceScore")))); // 积分价
					dataMap.put("priceCoin", StringUtil.getString(doc.getFieldValue("priceCoin"))); // 商城币价
					dataMap.put("url", getDetailPageUrl(itemSource, goodsId)); // 商品详情页url

					blockList = (List<Object>) doc.getFieldValues("blocks");
					if (blockList != null && blockList.size() > 0) {
						dataMap.put("blocks", getBlocksList(regionCode, subRegionCode, blockList));
					}

					ls.add(dataMap);
				}

				if (StringUtils.isEmpty(subRegionCode) && StringUtils.isEmpty(goodsType) && StringUtils.isEmpty(price)) {
					regionCountMap = getFacetFieldMapFromResp(rsp, "saleRegions");
					goodsTypeCountMap = getFacetFieldMapFromResp(rsp, "itemCates");
					priceCountMap = getFacetFieldMapFromResp(rsp, "price");
				} else {
					regionCountMap = getFacetFieldMap(solrServer, title, regionCode, subRegionCode, goodsType, price, sort, isNewOrder, allowScore,
					                                  allowCoin, allowBill, curPage, pageSize, "saleRegions");
					goodsTypeCountMap = getFacetFieldMap(solrServer, title, regionCode, subRegionCode, goodsType, price, sort, isNewOrder,
					                                     allowScore, allowCoin, allowBill, curPage, pageSize, "itemCates");
					priceCountMap = getFacetFieldMap(solrServer, title, regionCode, subRegionCode, goodsType, price, sort, isNewOrder, allowScore,
					                                 allowCoin, allowBill, curPage, pageSize, "price");
				}

				// 设置总条数
				totalCount = docs.getNumFound();
				// 设置总页数
				if (totalCount > 0) {
					if (totalCount % pageSize == 0) {
						totalPage = (docs.getNumFound() / pageSize);
					} else {
						totalPage = (docs.getNumFound() / pageSize + 1);
					}
				}

			}
			retMap.put("resultList", ls);
			retMap.put("totalCount", totalCount);
			retMap.put("totalPage", totalPage);
			retMap.put("regionCountMap", regionCountMap);
			retMap.put("goodsTypeCountMap", goodsTypeCountMap);
			retMap.put("priceCountMap", priceCountMap);
		}
		catch (Exception ex) {
			logger.error("调用搜索引擎查询代金券信息出现异常，", ex);
		}

		return retMap;
	}

	/**
	 * 组装solr查询条件
	 * 
	 * @param title
	 * @param regionCode
	 * @param subRegionCode
	 * @param goodsType
	 * @param price
	 * @param sort
	 * @param isNewOrder
	 * @param allowScore
	 * @param allowCoin
	 * @param allowBill
	 * @param regionFacetFlag
	 * @param itemCatesFacetFlag
	 * @param priceFacetFlag
	 * @return
	 */
	private SolrQuery getSolrQuery(String title, String regionCode, String subRegionCode, String goodsType, String price, String sort,
	        String isNewOrder, String allowScore, String allowCoin, String allowBill, String curPage, int pageSize, boolean regionFacetFlag,
	        boolean itemCatesFacetFlag, boolean priceFacetFlag) {
		SolrQuery query = new SolrQuery();
		query.setQuery("*");
		if (!regionFacetFlag && !itemCatesFacetFlag && !priceFacetFlag) {
			/** 分页设置 */
			// 指定返回结果最多有多少条记录，配合start来实现分页。
			query.setRows(pageSize);
			// 返回第一条记录在完整找到结果中的偏移位置，0开始，一般分页用
			int startIndex = getStartOfAnyPage(Integer.valueOf(curPage), pageSize);
			query.setStart(startIndex);

			if ("1".equals(sort)) { // 销量正序
				query.setSort("saleNum", SolrQuery.ORDER.asc);
			} else if ("-1".equals(sort)) { // 销量倒序
				query.setSort("saleNum", SolrQuery.ORDER.desc);
			} else if ("2".equals(sort)) { // 上架时间正序
				query.setSort("onlineTime", SolrQuery.ORDER.asc);
			} else if ("-2".equals(sort)) { // 上架时间倒序
				query.setSort("onlineTime", SolrQuery.ORDER.desc);
			} else if ("3".equals(sort)) { // 价格正序
				query.setSort("price", SolrQuery.ORDER.asc);
			} else if ("-3".equals(sort)) { // 价格倒序
				query.setSort("price", SolrQuery.ORDER.desc);
			} else {
				// 默认按itemOrder和销量倒序排列
				List<SortClause> sortList = new ArrayList<SolrQuery.SortClause>();
				SortClause clause = new SortClause("itemOrder", SolrQuery.ORDER.desc);
				sortList.add(clause);
				clause = new SortClause("saleNum", SolrQuery.ORDER.desc);
				sortList.add(clause);
				query.setSorts(sortList);
			}
		} else {
			query.setRows(0);
		}

		// 默认增加查询条件
		if (StringUtils.isNotEmpty(title)) {// 模糊查询
			title = title.length() > PARAM_MAX_LENGTH ? title.substring(0, PARAM_MAX_LENGTH) : title;
			title = escapeQueryChars(title);
			query.addFilterQuery("name:*" + title + "*");
		}

		if (StringUtils.isNotEmpty(regionCode)) {
			regionCode = regionCode.length() > PARAM_MAX_LENGTH ? regionCode.substring(0, PARAM_MAX_LENGTH) : regionCode;
			regionCode = escapeQueryChars(regionCode);
			query.addFilterQuery("saleAreas:" + regionCode);
		}

		if (StringUtils.isNotEmpty(subRegionCode) && !regionFacetFlag) {// 所属行政区
			subRegionCode = subRegionCode.length() > PARAM_MAX_LENGTH ? subRegionCode.substring(0, PARAM_MAX_LENGTH) : subRegionCode;
			subRegionCode = escapeQueryChars(subRegionCode);
			query.addFilterQuery("saleRegions:" + subRegionCode);
		}

		if (StringUtils.isNotEmpty(goodsType) && !itemCatesFacetFlag) {// 商品分类
			goodsType = goodsType.length() > PARAM_MAX_LENGTH ? goodsType.substring(0, PARAM_MAX_LENGTH) : goodsType;
			goodsType = escapeQueryChars(goodsType);
			query.addFilterQuery("itemCates:" + goodsType);
		}

		if (StringUtils.isNotEmpty(price) && !priceFacetFlag) {// 售价
			String amount = Constants.REVERSE_PRICE_MAP.get(price);
			if (StringUtils.isNotEmpty(amount)) {
				query.addFilterQuery("price:" + amount);
				// 去掉价格区间右边的闭区间值
				String[] prices = amount.substring(1, amount.length() - 1).split("TO");
				if (!"*".equals(prices[1].trim())) {
					query.addFilterQuery("-price:" + prices[1].trim());
				}
			}
		}

		String newOrderTime = TimeUtil.addDays(-7);
		// 只看新单
		if ("1".equals(isNewOrder)) {
			// 上架时间在一周以内的
			query.addFilterQuery("onlineTime:{" + newOrderTime + " TO " + TimeUtil.now() + "}");
		}

		// 支持积分支付
		if ("1".equals(allowScore)) {
			query.addFilterQuery("payScore:true");
		}
		// 支持商城币支付
		if ("1".equals(allowCoin)) {
			query.addFilterQuery("payCoin:true");
		}
		// 支持话费支付
		if ("1".equals(allowBill)) {
			query.addFilterQuery("payBill:true");
		}

		// 增加数量统计
		if (!regionFacetFlag && !itemCatesFacetFlag && !priceFacetFlag) {
			if (StringUtils.isEmpty(subRegionCode) && StringUtils.isEmpty(goodsType) && StringUtils.isEmpty(price)) {
				query.setFacet(true);
				query.addFacetField("saleRegions"); // 区域
				query.addFacetField("itemCates"); // 分类
				String[] priceArr = Constants.REVERSE_PRICE_MAP.values().toArray(new String[Constants.REVERSE_PRICE_MAP.size()]);
				for (String priceStr : priceArr) {
					query.addFacetQuery("price:" + priceStr);
					// 去掉价格区间右边的闭区间值
					String[] prices = priceStr.substring(1, priceStr.length() - 1).split("TO");
					if (!"*".equals(prices[1].trim())) {
						query.addFacetQuery("price:" + prices[1].trim());
					}
				}
			}
		} else {
			query.setFacet(true);
			if (regionFacetFlag) {
				query.addFacetField("saleRegions"); // 区域
			}
			if (itemCatesFacetFlag) {
				query.addFacetField("itemCates"); // 分类
			}
			if (priceFacetFlag) {
				String[] priceArr = Constants.REVERSE_PRICE_MAP.values().toArray(new String[Constants.REVERSE_PRICE_MAP.size()]);
				for (String priceStr : priceArr) {
					query.addFacetQuery("price:" + priceStr);
					// 去掉价格区间右边的闭区间值
					String[] prices = priceStr.substring(1, priceStr.length() - 1).split("TO");
					if (!"*".equals(prices[1].trim())) {
						query.addFacetQuery("price:" + prices[1].trim());
					}
				}
			}
		}

		return query;
	}

	/**
	 * 获取区域、商品分类和价格区间的数量统计
	 * 
	 * @param solrServer
	 * @param title
	 * @param regionCode
	 * @param subRegionCode
	 * @param goodsType
	 * @param price
	 * @param sort
	 * @param isNewOrder
	 * @param allowScore
	 * @param allowCoin
	 * @param allowBill
	 * @param facetType
	 * @return
	 */
	private Map<String, Long> getFacetFieldMap(HttpSolrServer solrServer, String title, String regionCode, String subRegionCode, String goodsType,
	        String price, String sort, String isNewOrder, String allowScore, String allowCoin, String allowBill, String curPage, int pageSize,
	        String facetType) {
		SolrQuery query = null;
		Map<String, Long> retMap = null;
		if ("saleRegions".equals(facetType)) {
			query = getSolrQuery(title, regionCode, subRegionCode, goodsType, price, sort, isNewOrder, allowScore, allowCoin, allowBill, curPage,
			                     pageSize, true, false, false);
		} else if ("itemCates".equals(facetType)) {
			query = getSolrQuery(title, regionCode, subRegionCode, goodsType, price, sort, isNewOrder, allowScore, allowCoin, allowBill, curPage,
			                     pageSize, false, true, false);
		} else if ("price".equals(facetType)) {
			query = getSolrQuery(title, regionCode, subRegionCode, goodsType, price, sort, isNewOrder, allowScore, allowCoin, allowBill, curPage,
			                     pageSize, false, false, true);
		}
		logger.info("分组统计检索条件：" + query.toString());
		try {
			QueryResponse rsp = solrServer.query(query);
			if (rsp != null) {
				// 获取区域、商品分类和价格区间的数量
				retMap = getFacetFieldMapFromResp(rsp, facetType);
			}
		}
		catch (Exception ex) {
			logger.error("调用搜索引擎查询代金券分组数量统计出现异常，", ex);
		}

		return retMap;
	}

	/**
	 * 从solr结果集中获取区域、商品分类和价格区间的统计数量
	 * 
	 * @param rsp
	 * @param facetType
	 * @return
	 */
	private Map<String, Long> getFacetFieldMapFromResp(QueryResponse rsp, String facetType) {
		FacetField facetField = null;
		Map<String, Long> retMap = new HashMap<String, Long>();
		if ("saleRegions".equals(facetType) || "itemCates".equals(facetType)) {
			facetField = rsp.getFacetField(facetType);
			if (facetField != null) {
				List<Count> countList = facetField.getValues();
				if (countList != null && !countList.isEmpty()) {
					for (Count count : countList) {
						retMap.put(count.getName(), count.getCount());
					}
				}
			}
		} else if ("price".equals(facetType)) {
			Map<String, Integer> priceMap = rsp.getFacetQuery();
			// 价格区间
			if (priceMap != null && !priceMap.isEmpty()) {
				String[] keys = Constants.REVERSE_PRICE_MAP.keySet().toArray(new String[priceMap.size()]);
				String priceZoneStr = "";
				Integer tmpInt = null;
				for (String key : keys) {
					tmpInt = null;
					priceZoneStr = Constants.REVERSE_PRICE_MAP.get(key);
					Integer num = priceMap.get("price:" + priceZoneStr);
					if (num != null) {
						// 去掉价格区间右边的闭区间值
						String[] prices = priceZoneStr.substring(1, priceZoneStr.length() - 1).split("TO");
						if (!"*".equals(prices[1].trim())) {
							tmpInt = priceMap.get("price:" + prices[1].trim());
						}

						retMap.put(key, tmpInt == null ? num.longValue() : (num.longValue() - tmpInt.longValue()));
					}
				}
			}
		}

		return retMap;
	}

	/**
	 * 根据区域获取商品销售街区列表
	 * 
	 * @param regionCode
	 * @param subRegionCode
	 * @param blocksList
	 * @return
	 */
	private List<String> getBlocksList(String regionCode, String subRegionCode, List<Object> blockList) {
		List<String> list = new ArrayList<String>();
		// 匹配串,如果subRegionCode不为空,则取RegionCode,否则取regionCode前四位
		String match = "";
		if (StringUtils.isNotEmpty(subRegionCode)) {
			match = subRegionCode;
		} else if (StringUtils.isNotEmpty(regionCode)) {
			match = regionCode.substring(0, 4);
		}
		if (StringUtils.isNotEmpty(match)) {
			String blockStr = "";
			for (Object obj : blockList) {
				blockStr = obj.toString();
				if (blockStr.startsWith(match)) {
					list.add(blockStr.split("[@]")[1]);
				}
			}
		}
		return list;
	}

	/**
	 * 获取商品详情页url
	 * 
	 * @param itemSource
	 * @param goodsId
	 * @return
	 */
	private String getDetailPageUrl(String itemSource, String goodsId) {
		if ("S".equals(itemSource)) { // 商盟
			return appConfig.getShlmVoucherSrvHost() + "product/i" + goodsId + ".htm";
		} else if ("M".equals(itemSource)) { // 商城
			return appConfig.getServer_host() + "/tools/item.chtml?saleId=" + goodsId;
		} else { // 联动
			return appConfig.getShlmVoucherSrvHost() + "um/channel/js/fillOrder/" + goodsId + ".htm?naviCode=djq";
		}
	}

	/**
	 * 根据商品来源获取完整的图片访问url
	 * 
	 * @param id
	 *            商品id
	 * @param itemSource
	 *            商品来源:M-商城 S-商盟 L-联动
	 * @param imgPath
	 *            图片路径
	 * @return
	 */
	private String getImgPath(String id, String itemSource, String imgPath) {
		if (StringUtils.isEmpty(imgPath)) {
			return "";
		}
		if ("S".equals(itemSource)) { // 商盟
			return appConfig.getShlmVoucherSrvHost() + appConfig.getShlmVoucherImgWebpath() + imgPath;
		} else if ("M".equals(itemSource)) { // 商城
			return appConfig.getImgSvrHost() + pathUtil.getPathById(PathUtil.TYPE_ITEM_PIC, Long.valueOf(id)) + "N1/" + imgPath;
		} else { // 联动
			return imgPath;
		}
	}

	/**
	 * 是否新单（上架时间在一周以内的为新单）
	 * 
	 * @param onlineTime
	 *            上架时间
	 * @param compareTime
	 *            对比时间
	 * @return
	 */
	private boolean isNewOrder(String onlineTime, String compareTime) {
		if (StringUtils.isEmpty(onlineTime)) {
			return false;
		}
		return TimeUtil.compareTime(onlineTime, compareTime) >= 0;
	}

	/**
	 * 根据分页参数获取搜索的起始索引
	 * 
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            每页条数
	 * @return
	 */
	private int getStartOfAnyPage(int pageNo, int pageSize) {
		int startIndex = (pageNo - 1) * pageSize;
		if (startIndex < 0)
			startIndex = 0;
		return startIndex;
	}

	/**
	 * 获取积分价
	 * 
	 * @param itemSource
	 * @param payCash
	 * @param payScore
	 * @param price
	 * @param priceScore
	 * @return
	 */
	public String getScorePrice(String itemSource, String payCash, String payScore, String price, String priceScore) {
		// 如果是现金支付或不支持积分支付，返回空
		if ("true".equals(payCash) || "false".equals(payScore)) {
			return "";
		}
		if ("S".equals(itemSource)) { // 商盟代金券积分价取priceScore
			Double d = Double.valueOf(StringUtil.getString(priceScore, "0"));
			return String.valueOf(Math.round(Math.floor(d / Constants.SCORE_PRICE_RATE)));
		} else if ("M".equals(itemSource)) { // 商城代金券积分价取price
			Double d = Double.valueOf(StringUtil.getString(price, "0"));
			return String.valueOf(Math.round(Math.floor(d / Constants.SCORE_PRICE_RATE)));
		}

		return "";
	}

	/**
	 * 获得商品验证码信息
	 * 
	 * @param order_id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<String[]> getVerifyInfo(String order_id, int flag) {
		StringBuilder sqlBuff = new StringBuilder(200);
		sqlBuff.append(" select t.verify_code, t.verify_flag, t.verify_time,  t.order_id, ");
		sqlBuff.append("  case when end_time>=to_char(sysdate,'yyyymmddhh24miss') ");
		sqlBuff.append(" then 1 ");
		sqlBuff.append(" else 0");
		sqlBuff.append(" end  as isEnd,info_id,PLATFORM_ID,end_time");
		sqlBuff.append("  from cylm.t_12580_validate_code_log t ");
		sqlBuff.append(" where t.sort = 11 ");
		sqlBuff.append("   and t.order_id = :orderId ");
		if (flag == 1) {
			sqlBuff.append("   and t.verify_flag=0 and t.end_time>=to_char(sysdate,'yyyymmddhh24miss') ");
		}

		List<String[]> searchList;
		try {
			searchList = dbHelper.getArrayList(sqlBuff.toString(), new Object[] { order_id });
		}
		catch (SQLException e) {
			logger.error("获取代金券验证码信息异常", e);
			return null;
		}

		return searchList;
	}

	/**
	 * 获取代金券补发短信次数
	 * 
	 * @param order_id
	 * @return
	 */
	public String[] getSendLog(String order_id, String code) {
		StringBuilder sqlBuff = new StringBuilder(200);
		sqlBuff.setLength(0);
		Object[] params = new Object[] {};
		sqlBuff.append("select down_num from  cylm.t_12580_o2o_resend_sms_log");
		sqlBuff.append("   where order_id=? ");
		params = ArrayUtils.add(params, order_id);
		if (StringUtils.isNotBlank(code)) {
			sqlBuff.append("   and verify_code=? ");
			params = ArrayUtils.add(params, code);
		}
		try {
			return dbHelper.getArray(sqlBuff.toString(), params);
		}
		catch (SQLException e) {
			logger.error("获取代金券补发短信次数异常", e);
			return null;
		}
	}

	/**
	 * 保存补发记录
	 * 
	 * @param order_id
	 * @param good_id
	 * @param userId
	 * @param terminal_id
	 * @param code
	 * @return
	 */
	public int inserSendLog(String order_id, String good_id, long userId, String terminal_id, String code) {
		StringBuilder sqlBuff = new StringBuilder(200);
		sqlBuff.setLength(0);
		Object[] params = new Object[] {};
		sqlBuff.append("insert into cylm.t_12580_o2o_resend_sms_log");
		sqlBuff.append("  (id, ");
		sqlBuff.append("  order_id, ");
		sqlBuff.append("   good_id, ");
		sqlBuff.append("   user_id, ");
		sqlBuff.append("   terminal_id, ");
		sqlBuff.append("   verify_code, ");
		sqlBuff.append("   down_num, ");
		sqlBuff.append("   down_time) ");
		sqlBuff.append(" values ");
		sqlBuff.append("  (cylm.SEQ_12580_O2O_RESEND_SMS_LOG.Nextval,?, ?, ?, ?, ?, 0, to_char(sysdate, 'yyyymmddhh24miss')) ");
		params = ArrayUtils.add(params, order_id);
		params = ArrayUtils.add(params, good_id);
		params = ArrayUtils.add(params, userId);
		params = ArrayUtils.add(params, terminal_id);
		params = ArrayUtils.add(params, code);

		try {
			return dbHelper.execute(sqlBuff.toString(), params);
		}
		catch (SQLException e) {
			logger.error("保存商盟代金券补发记录异常", e);
			return -1;
		}
	}

	/**
	 * 更新补发次数
	 * 
	 * @param order_id
	 * @return
	 */
	public int updateSendLog(String order_id, String remark, String code) {
		StringBuilder sqlBuff = new StringBuilder(200);
		sqlBuff.setLength(0);
		Object[] params = new Object[] {};
		sqlBuff.append("update cylm.t_12580_o2o_resend_sms_log");
		sqlBuff.append(" set down_num= down_num+1,");
		sqlBuff.append("  remark= ?,");
		params = ArrayUtils.add(params, remark);
		sqlBuff.append("   down_time=to_char(sysdate, 'yyyymmddhh24miss') ");
		sqlBuff.append("   where order_id=? ");
		params = ArrayUtils.add(params, order_id);
		if (StringUtils.isNotBlank(code)) {
			sqlBuff.append("   and verify_code=? ");
			params = ArrayUtils.add(params, code);
		}
		try {
			return dbHelper.execute(sqlBuff.toString(), params);
		}
		catch (SQLException e) {
			logger.error("更新商盟代金券补发次数异常", e);
			return -1;
		}
	}

	/**
	 * 验证码补发，码未使用且未过期
	 * 
	 * @param code
	 * @param moblie
	 */
	public String resendCode(String code, String mobile, String order_id, String good_id, String end_time) {
		logger.info("代金券验证码补发开始--verify_code:" + code + "      order_id:" + order_id + "      mobile:" + mobile);
		String msg = "";
		try {

			String[] goods = this.getGoodsById(good_id);
			msg = "【mo生活】您已成功购买[good_title]，消费验证码[validateCode]，有效期至[expire_time]。";
			msg = msg.replace("[validateCode]", StringUtils.trimToEmpty(code));
			msg = msg.replace("[expire_time]", TimeUtil.format(StringUtils.trimToEmpty(end_time), "yyyy.MM.dd"));
			msg = msg.replace("[good_title]", StringUtils.trimToEmpty(goods[5]));

			// 插入sms_mt_wait表
			boolean bool = this.insert(mobile, "025", msg);
			if (bool) {// 更新补发次数
				this.updateSendLog(order_id, "", code);
				msg = "手机短信发送成功，请及时查收";
			} else {
				msg = "手机短信发送失败";
			}
		}
		catch (Exception e) {
			logger.error("手机短信发送失败异常", e);
			msg = "手机短信发送失败，错误码：500";
		}
		return msg;
	}

	/**
	 * 获取商品信息
	 * 
	 * @param good_id
	 * @return
	 * @throws SQLException
	 */
	public String[] getGoodsById(String good_id) throws SQLException {
		StringBuilder sqlBuff = new StringBuilder(200);
		sqlBuff.setLength(0);
		Object[] obj = new Object[] { good_id };
		sqlBuff.setLength(0);
		sqlBuff.append("select  t1.goods_sales,  ");
		sqlBuff.append("        t1.need_verifycode,  ");
		sqlBuff.append("        t1.verify_start_time,  ");
		sqlBuff.append("        t1.verify_end_time,  ");
		sqlBuff.append("        t1.stocks,  ");
		sqlBuff.append("        t1.good_title, ");
		sqlBuff.append("        t1.sales_price, ");
		sqlBuff.append("        t1.shop_id, ");
		sqlBuff.append("        t1.area_code, ");
		sqlBuff.append("        t1.sales_permen, ");
		sqlBuff.append("        t1.good_address ");
		sqlBuff.append(" from  cylm.t_12580_O2o_Good t1 ");
		sqlBuff.append("  where 1=1 ");
		sqlBuff.append("  and t1.id = ?");
		String[] str = dbHelper.getArray(sqlBuff.toString(), obj);
		return str;
	}

	/**
	 * 短信下发操作
	 * 
	 * @param terminalId
	 *            手机号码
	 * @param areaCode
	 *            地区编码
	 * @param cont
	 *            发送内容
	 * @return 成功 TRUE, 失败 FALSE
	 */
	public boolean insert(String terminalId, String areaCode, String cont) {
		boolean result = true;
		StringBuilder sqlBuff = new StringBuilder(500);
		sqlBuff.append("insert into cylm.sms_mt_wait ");
		sqlBuff.append("  (sequence_id, ");
		sqlBuff.append("   act_code, ");
		sqlBuff.append("   sp_code, ");
		sqlBuff.append("   fee_terminal_id, ");
		sqlBuff.append("   dest_terminal_id, ");
		sqlBuff.append("   register_delivery, ");
		sqlBuff.append("   msg_content, ");
		sqlBuff.append("   request_time, ");
		sqlBuff.append("   service_id, ");
		sqlBuff.append("   fee_type, ");
		sqlBuff.append("   fee_code, ");
		sqlBuff.append("   msg_format, ");
		sqlBuff.append("   msg_level, ");
		sqlBuff.append("   valid_time, ");
		sqlBuff.append("   area_code, ");
		sqlBuff.append("   operator_code) ");
		sqlBuff.append("values ");
		sqlBuff.append("  (cylm.SEQUENCE_SMS_MT_WAIT.nextval, ");
		sqlBuff.append("   ?, ");
		sqlBuff.append("   ?, ");
		sqlBuff.append("   ?, ");
		sqlBuff.append("   ?, ");
		sqlBuff.append("   '0', ");
		sqlBuff.append("   ?, ");
		sqlBuff.append("   ?, ");
		sqlBuff.append("   'FREE', ");
		sqlBuff.append("   '1', ");
		sqlBuff.append("   '0', ");
		sqlBuff.append("   '', ");
		sqlBuff.append("   '0', ");
		sqlBuff.append("   '120', ");
		sqlBuff.append("   ?, ");
		sqlBuff.append("   ?)");
		Object[] params = new Object[] {};
		params = ArrayUtils.add(params, "LMUT");
		params = ArrayUtils.add(params, "10658585678");
		params = ArrayUtils.add(params, terminalId);
		params = ArrayUtils.add(params, terminalId);
		params = ArrayUtils.add(params, cont);
		params = ArrayUtils.add(params, TimeUtil.now());
		params = ArrayUtils.add(params, areaCode);
		params = ArrayUtils.add(params, "JSYD");
		try {
			result = dbHelper.execute(sqlBuff.toString(), params) > 0;
		}
		catch (Exception e) {
			result = false;
			logger.error("手短信下发操作异常", e);
		}
		return result;
	}

	public static String escapeQueryChars(String s) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			// These characters are part of the query syntax and must be escaped
			if (c == '\\' || c == '+' || c == '-' || c == '!' || c == '(' || c == ')' || c == ':' || c == '^' || c == '[' || c == ']' || c == '\"'
			        || c == '{' || c == '}' || c == '~' || c == '*' || c == '?' || c == '|' || c == '&' || c == ';' || c == '/'
			        || Character.isWhitespace(c)) {
				sb.append('\\');
			}

			sb.append(c);
		}

		return sb.toString();

	}

	/**
	 * 获取代金券订单的验证码列表
	 * 
	 * @param orderId
	 * @return
	 */
	public List<String[]> getVerifyCodeDetail(String orderId) {
		StringBuilder sqlBuff = new StringBuilder(200);
		sqlBuff.append("select id, verify_code, create_time, end_time, verify_time, ");
		sqlBuff.append(" case when verify_flag = 1 or verify_flag = 2 then verify_flag ");
		sqlBuff.append(" when verify_flag = 0 and end_time < to_char(sysdate, 'yyyyMMddhh24miss') then '3' else verify_flag end as verify_flag,");
		sqlBuff.append(" act_name");
		sqlBuff.append("  from cylm.t_12580_validate_code_summary ");
		sqlBuff.append(" where order_id = ? ");
		sqlBuff.append("   and sort = 11");
		try {
			return dbHelper.getArrayList(sqlBuff.toString(), new Object[] { orderId });
		}
		catch (SQLException e) {
			logger.error("获取代金券订单的验证码列表异常", e);
			return null;
		}
	}

	/**
	 * 判断o2o商品是否由第三方平台提供验证码
	 * 
	 * @param goodId
	 * @return
	 */
	public boolean isTpVerifxyCodeGood(String goodId) {
		StringBuilder sqlBuff = new StringBuilder(200);
		Object[] params = new Object[] { goodId };
		sqlBuff.append("select count(*) from cylm.T_12580_TPVERIFYCODE_CONF where product_id=? and product_type='o2o'");
		boolean result = false;
		try {
			String count = dbHelper.queryScalar(sqlBuff.toString(), params);
			if (count != null && Integer.parseInt(count) > 0) {
				result = true;
			}
		}
		catch (SQLException e) {
			logger.error("判断o2o商品是否由第三方平台提供验证码异常", e);
			result = false;
		}
		return result;
	}

}
