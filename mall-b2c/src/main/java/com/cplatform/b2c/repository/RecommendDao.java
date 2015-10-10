package com.cplatform.b2c.repository;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cplatform.b2c.util.TimeUtil;
import com.cplatform.dbhelp.DbHelper;

/**
 * 智能推荐dao. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2014-2-17 上午11:03:49
 * <p>
 * Company: 苏州宽连信息技术有限公司
 * <p>
 * 
 * @author zhangmeng@c-platform.com
 * @version 1.0.0
 */
@Repository
public class RecommendDao {

	private final Logger logger = Logger.getLogger(RecommendDao.class);

	@Autowired
	private DbHelper dbHelper;

	/**
	 * 按照ID从搜索引擎中获取优惠券信息
	 * 
	 * @param couponIds
	 * @return
	 */
	public List<Map<String, String>> getCouponFromSorlSearch(String couponIds) {
		logger.info("优惠券ID：" + couponIds);
		try {
			List<Map<String, String>> list = getCouponList(couponIds);
			for (Map<String, String> map : list) {
				String yhqName = map.get("SHOP_NAME");
				if (StringUtils.isEmpty(yhqName)) {
					yhqName = map.get("TITLE").replace("优惠券下载", "");
				}
				map.put("YHQ_NAME", yhqName);
				map.put("END_TIME", TimeUtil.format(map.get("END_TIME"), "yyyy-MM-dd"));

				String pic_path = "";
				String[] contentArr = map.get("CONTENT").split(",");
				if (ArrayUtils.isNotEmpty(contentArr)) {
					pic_path = contentArr[0];
				}
				map.put("PIC_PATH", pic_path);
			}

			return list;
		}
		catch (Exception ex) {
			logger.error("", ex);
		}
		return null;
	}

	/**
	 * 按照ID从搜索引擎中获取代金券信息
	 * 
	 * @param djqIds
	 * @param areaCode
	 * @return
	 */
	public List<Map<String, String>> sorlSearchGoods(String djqIds, String areaCode) {
		logger.info("代金券ID：" + djqIds);
		try {
			List<Map<String, String>> list = getQuanGoodsList(djqIds);
			List<Map<String, String>> QGGoodsList = getQGGoodsList(areaCode);
			// 存在抢购商品
			if (QGGoodsList != null && QGGoodsList.size() > 0) {
				logger.info("存在抢购商品");
				for (Map<String, String> QGGoods : QGGoodsList) {
					for (Map<String, String> goods : list) {
						if (QGGoods.get("product_id").equals(goods.get("ID"))) {
							goods.put("SALES_PRICE", QGGoods.get("flash_sale_price"));
							goods.put("BUY_TYPE", QGGoods.get("buy_type"));
							goods.put("ACT_AREA_CODE", QGGoods.get("act_area_code"));
						}
					}
				}
			}

			return list;
		}
		catch (Exception ex) {
			logger.error("获取代金券信息异常，", ex);
			return null;
		}
	}

	/**
	 * 查询优惠券商品信息
	 * 
	 * @param couponIds
	 * @return
	 */
	public List<Map<String, String>> getCouponList(String couponIds) {
		Object[] params = couponIds.split(",");
		StringBuilder sqlBuff = new StringBuilder(200);
		logger.info("查询优惠券商品信息");
		sqlBuff.append("select mms.id,");
		sqlBuff.append("       mms.smil_name,");
		sqlBuff.append("       mms.content,");
		sqlBuff.append("       mms.title,");
		sqlBuff.append("       mms.remark,");
		sqlBuff.append("       mms.end_time,");
		sqlBuff.append("       shop.name shop_name");
		sqlBuff.append("  from cylm.t_cont_mms mms, cylm.t_12580_shop shop");
		sqlBuff.append(" where mms.shop_id = shop.id");
		sqlBuff.append("   and mms.id in (");
		for (int i = 0; i < params.length; i++) {
			sqlBuff.append("?");
			if (i != params.length - 1) {
				sqlBuff.append(",");
			}
		}
		sqlBuff.append(")");
		try {
			return dbHelper.getMapList(sqlBuff.toString(), params);
		}
		catch (Exception e) {
			logger.error("查询优惠券商品信息异常，", e);
			return null;
		}
	}

	/**
	 * 查询代金券商品信息
	 * 
	 * @param goodIds
	 * @return
	 */
	public List<Map<String, String>> getQuanGoodsList(String goodIds) {
		Object[] params = goodIds.split(",");
		StringBuilder sqlBuff = new StringBuilder(200);
		logger.info("查询代金券商品信息");
		sqlBuff.append("select id, good_title, sales_price, original_price, goods_pic_path");
		sqlBuff.append("  from cylm.t_12580_o2o_good_search");
		sqlBuff.append(" where id in (");
		for (int i = 0; i < params.length; i++) {
			sqlBuff.append("?");
			if (i != params.length - 1) {
				sqlBuff.append(",");
			}
		}
		sqlBuff.append(")");
		try {
			return dbHelper.getMapList(sqlBuff.toString(), params);
		}
		catch (Exception e) {
			logger.error("查询代金券商品信息异常，", e);
			return null;
		}
	}

	/**
	 * 根据地区查询抢购商品列表
	 * 
	 * @param areaCode
	 * @return
	 */
	private List<Map<String, String>> getQGGoodsList(String areaCode) {
		StringBuilder sqlBuff = new StringBuilder(200);
		logger.info("查询抢购商品信息");
		sqlBuff.setLength(0);
		sqlBuff.append("select t1.product_id,");
		sqlBuff.append("       t1.flash_sale_price,");
		sqlBuff.append("       t1.buy_type,");
		sqlBuff.append("       t1.area_code        as act_area_code");
		sqlBuff.append("  from cylm.t_act_flash_sale_good t1, cylm.t_act_flash_sale t2");
		sqlBuff.append(" where t2.start_time <= to_char(sysdate, 'yyyymmddhh24miss')");
		sqlBuff.append("   and t2.end_time >= to_char(sysdate, 'yyyymmddhh24miss')");
		sqlBuff.append("   and t1.flash_sale_id = t2.id");
		sqlBuff.append("   and t2.status = 1");
		sqlBuff.append("   and t2.audit_status = 1");
		sqlBuff.append("   and t1.area_code = ?");
		try {
			return dbHelper.getMapList(sqlBuff.toString(), new Object[] { areaCode });
		}
		catch (Exception e) {
			logger.error("查询抢购商品信息异常，", e);
			return null;
		}
	}

	/**
	 * 查询团购商品
	 * 
	 * @param teamIds
	 * @return
	 */
	public List<Map<String, String>> getRecommendTuanList(String teamIds) {
		Object[] params = teamIds.split(",");
		StringBuilder sqlBuff = new StringBuilder(200);
		logger.info("查询团购商品信息");
		sqlBuff.setLength(0);
		sqlBuff.append("select team_id,");
		sqlBuff.append("       ug_title,");
		sqlBuff.append("       ug_goods_name,");
		sqlBuff.append("       ug_market_price,");
		sqlBuff.append("       ug_groupbuy_price,");
		sqlBuff.append("       ug_goods_pic");
		sqlBuff.append("  from cylm.t_12580_u_groupbuy");
		sqlBuff.append(" where ug_starttime <= to_char(sysdate, 'yyyymmddhh24miss')");
		sqlBuff.append("   and ug_endtime >= to_char(sysdate, 'yyyymmddhh24miss')");
		sqlBuff.append("   and team_id in(");
		for (int i = 0; i < params.length; i++) {
			sqlBuff.append("?");
			if (i != params.length - 1) {
				sqlBuff.append(",");
			}
		}
		sqlBuff.append(")");
		sqlBuff.append("   and home_unshow_flag = 0"); // 是否前台展示:0展示，1不展示
		try {
			return dbHelper.getMapList(sqlBuff.toString(), params);
		}
		catch (Exception e) {
			logger.error("查询团购商品信息异常，", e);
			return null;
		}
	}

	/**
	 * 查询商城商品信息
	 * 
	 * @param goodIds
	 * @return
	 */
	public List<Map<String, String>> getRecommendGoodsList(String goodIds) {
		Object[] params = goodIds.split(",");
		StringBuilder sqlBuff = new StringBuilder(200);
		logger.info("查询商城商品信息");
		sqlBuff.append("select sale.id,");
		sqlBuff.append("       sale.name,");
		sqlBuff.append("       sale.market_price,");
		sqlBuff.append("       sale.shop_price,");
		sqlBuff.append("       sale.cash_idgoods,");
		sqlBuff.append("       sale.coin_idgoods,");
		sqlBuff.append("       sale.score_idgoods,");
		sqlBuff.append("       pay.bill_pay,");
		sqlBuff.append("       sale.img_path");
		sqlBuff.append("  from t_item_sale sale, t_item_sale_payment pay");
		sqlBuff.append(" where sale.id = pay.item_id");
		sqlBuff.append("   and sale.id in(");
		for (int i = 0; i < params.length; i++) {
			sqlBuff.append("?");
			if (i != params.length - 1) {
				sqlBuff.append(",");
			}
		}
		sqlBuff.append(")");
		sqlBuff.append(" and sale.STATUS=3");
		sqlBuff.append(" and sale.IS_VALID=1");
		sqlBuff.append(" and sale.IS_view=1");
		try {
			return dbHelper.getMapList(sqlBuff.toString(), params);
		}
		catch (Exception e) {
			logger.error("查询商城商品信息异常，", e);
			return null;
		}
	}

}
