package com.cplatform.b2c.welfare.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cplatform.b2c.welfare.entity.MobileModel;
import com.cplatform.b2c.welfare.entity.WelFareDetailVo;
import com.cplatform.b2c.welfare.entity.WelFareLogOrderModel;
import com.cplatform.b2c.welfare.entity.WelFareModel;
import com.cplatform.b2c.welfare.entity.WelFareStock;
import com.cplatform.dbhelp.DbHelper;
import com.cplatform.dbhelp.page.ListPage;

/**
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-11-1 下午01:44:53
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: guolt@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
@Repository
public class WelFareDao {

	JdbcTemplate jdbcTemplate;

	@Autowired
	DbHelper dbHelper;

	@Autowired
	public void setMyDataSource(@Qualifier("dataSource") DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/**
	 * 获取所有属性详情
	 * 
	 * @return
	 */
	public List<String> AllTypeDetail(int itemType, String type) {
		String sql = " select DISTINCT param_value  from T_ITEM_PARAM where type_id = ? and param_key=? order by param_value ";
		List<String> result = null;
		result = jdbcTemplate.queryForList(sql.toString(), new Object[] { itemType, type }, String.class);
		return result;
	}

	/**
	 * 获得所有属性
	 * 
	 * @param itemType
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String, Object>> allTypes(int itemType) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("select DISTINCT tip.param_key from  T_ITEM_PARAM tip left join  T_SYS_TYPE_ITEM_PARAM tsp on tip.PARAM_ID = tsp.id  ");
		sql.append(" where tip.type_id = ? and tsp.PRICE_RULE = 1 order  by param_key");
		return dbHelper.getNativeMapList(sql.toString(), itemType);
	}

	/**
	 * 获取备注
	 * 
	 * @return
	 */
	/*
	 * public List<String> AllRemarks() { StringBuilder sql = new
	 * StringBuilder();
	 * sql.append("  SELECT DISTINCT REMARK FROM..  T_ITEM_PARAM_STOCK  ");
	 * sql.append("  WHERE REMARK IS NOT NULL  "); List<String> result = null;
	 * result = jdbcTemplate.queryForList(sql.toString(), String.class); return
	 * result; }
	 */

	/**
	 * 获取品牌
	 * 
	 * @return
	 */
	public List<String> AllBrands(int itemType) {
		StringBuilder sql = new StringBuilder();
		sql.append("  SELECT DISTINCT BRAND   ");
		sql.append(" 		FROM T_ITEM_SALE A  ");
		sql.append(" WHERE  ");
		sql.append(" 	A.TYPE_ID = ?  ");
		sql.append("        AND A.IS_VALID='1' ");
		sql.append("        AND A.STATUS='3' ");
		sql.append("		AND TO_CHAR(SYSDATE, 'YYYYMMDDHH24MISS') BETWEEN  ");
		sql.append(" 		A.SALE_START_TIME AND A.SALE_STOP_TIME  ");
		sql.append(" order by A.BRAND ");
		List<String> result = null;
		result = jdbcTemplate.queryForList(sql.toString(), new Object[] { itemType }, String.class);
		return result;
	}

	/**
	 * 根据条件查询数量
	 * 
	 * @param model
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int getWelFaresCount(WelFareModel model) {
		int result = 0;
		String itemColor = null;
		String itemSize = null;
		Integer itemType = null;
		String itemOther = null;
		String brand = null;
		if (model != null) {
			itemColor = model.getItemColor();
			itemSize = model.getItemSize();
			itemType = model.getItemType();
			itemOther = model.getItemOther();
			brand = model.getBrand();
		}
		StringBuilder sql = new StringBuilder();
		List args = new ArrayList<Object>();
		sql.append(" SELECT COUNT(1) ");
		sql.append("   FROM T_ITEM_SALE A ");
		sql.append("  WHERE EXISTS ( ");
		sql.append(" SELECT 1 FROM T_ITEM_PARAM_STOCK B WHERE B.ITEM_ID = A.ID  ");

		/***
		 * T_ITEM_PARAM_STOCK 条件筛选开始
		 */

		// 颜色
		if (itemColor != null && !itemColor.equals("")) {
			sql.append(" AND item_Color = ? ");
			args.add(itemColor);
		}

		// 尺码
		if (itemSize != null && !itemSize.equals("")) {
			sql.append(" AND item_Size = ? ");
			args.add(itemSize);
		}

		// 类型 男鞋 女鞋 童鞋
		if (itemType != null && itemType.toString().matches("\\d")) {
			sql.append(" AND item_Type = ? ");
			args.add(itemType);
		}

		// 备注
		if (itemOther != null && !itemOther.equals("")) {
			sql.append(" AND REMARK = ? ");
			args.add(itemOther);
		}
		/**
		 * T_ITEM_PARAM_STOCK 条件筛选结束
		 */

		sql.append("  ) ");
		// 可用
		sql.append("    AND A.IS_VALID = '1' ");
		// 审核通过
		sql.append("    AND A.STATUS = '3' ");

		// 品牌
		if (brand != null && !brand.equals("")) {
			sql.append(" AND A.BRAND = ? ");
			args.add(brand);
		}

		sql.append("    AND TO_CHAR(SYSDATE, 'YYYYMMDDHH24MISS') BETWEEN ");
		sql.append("           A.SALE_START_TIME AND A.SALE_STOP_TIME ");
		try {
			result = jdbcTemplate.queryForInt(sql.toString(), args.toArray());
		}
		catch (Exception e) {
			e.printStackTrace();
			// 数量查询失败将返回0 异常不再向上抛
		}
		return result;
	}

	/**
	 * 根据条件查询列表
	 * 
	 * @param model
	 * @param pageNo
	 * @param pageitemSize
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<WelFareModel> getwelFares(WelFareModel model, int startNo, int endNo) {
		List<WelFareModel> result = null;
		StringBuilder sql = new StringBuilder();
		List args = new ArrayList<Object>();

		/**
		 * 获取多条件查询参数
		 */
		String itemColor = null;
		String itemSize = null;
		Integer itemType = null;
		String others = null;
		String brand = null;
		Integer orderType = null;
		if (model != null) {
			itemColor = model.getItemColor();
			itemSize = model.getItemSize();
			itemType = model.getItemType();
			others = model.getItemOther();
			brand = model.getBrand();
			orderType = model.getOrderType();
		}
		sql.append(" select F2.* from (  ");
		sql.append("  select F1.*,rownum r from ( ");
		sql.append("   ");
		sql.append("   ");
		sql.append(" SELECT A.ID, ");
		sql.append("        A.STOCK_NUM, ");
		sql.append("        A.USER_PER_BUY_NUM, ");
		sql.append("        A.SHOP_CLASS, ");
		sql.append("        A.STORE_ID, ");
		sql.append("        A.MARKET_PRICE, ");
		sql.append("        A.NAME, ");
		sql.append("        A.SHORT_NAME, ");
		sql.append("        A.WARM_PROMPT, ");
		sql.append("        A.REMARK, ");
		sql.append("        A.SHOP_USER_ID, ");
		sql.append("        A.BRAND, ");
		sql.append("        A.WEIGHT, ");
		sql.append("        A.MARKET_CONTENT, ");
		sql.append("        A.SHOP_PRICE, ");
		sql.append("        A.IMG_PATH ");
		sql.append("   FROM T_ITEM_SALE A ");
		sql.append("  WHERE EXISTS ( ");
		sql.append(" SELECT 1 FROM T_ITEM_PARAM_STOCK B WHERE B.ITEM_ID = A.ID  ");
		/***
		 * T_ITEM_PARAM_STOCK 条件筛选开始
		 */
		if (itemColor != null && !itemColor.equals("")) {
			sql.append(" AND item_Color = ? ");
			args.add(itemColor);
		}
		if (itemSize != null && !itemSize.equals("")) {

			sql.append(" AND item_Size = ? ");
			args.add(itemSize);
		}
		if (itemType != null && itemType.toString().matches("\\d")) {
			sql.append(" AND item_Type = ? ");
			args.add(itemType);
		}
		if (others != null && !others.equals("")) {
			sql.append(" AND REMARK = ? ");
			args.add(others);
		}
		/**
		 * T_ITEM_PARAM_STOCK 条件筛选结束
		 */

		sql.append("  ) ");
		// 可用
		sql.append("    AND A.IS_VALID = '1' ");
		// 审核通过
		sql.append("    AND A.STATUS = '3' ");
		// 品牌
		if (brand != null && !brand.equals("")) {
			sql.append(" AND A.BRAND = ? ");
			args.add(brand);
		}

		sql.append("    AND TO_CHAR(SYSDATE, 'YYYYMMDDHH24MISS') BETWEEN ");
		sql.append("           A.SALE_START_TIME AND A.SALE_STOP_TIME ");

		// 排序
		if (orderType != null && orderType == 0) {
			// 价格升序
			sql.append(" ORDER BY A.SHOP_PRICE asc ");
		} /*
		 * else if (orderType != null && orderType == 1) { // 价格降序
		 * sql.append(" ORDER BY A.MARKET_PRICE desc "); }
		 */

		sql.append("     )  F1 ");
		sql.append(" ) F2");
		sql.append(" where F2.r > ? and  F2.r <= ? ");
		args.add(startNo);
		args.add(endNo);
		result = jdbcTemplate.query(sql.toString(), args.toArray(), new BeanPropertyRowMapper(WelFareModel.class));
		return result;
	}

	/**
	 * 根据item_id查询详情
	 * 
	 * @param item_id
	 *            商品id
	 * @return
	 */
	public WelFareDetailVo getDetailByItemIdAndItemType(int id, int itemType) {
		WelFareDetailVo detailVo = new WelFareDetailVo();
		List<String> colorLst = null;
		List<String> sizeLst = null;
		StringBuilder sql = new StringBuilder();
		// 获取颜色
		sql.append("  SELECT DISTINCT ITEM_COLOR FROM T_ITEM_PARAM_STOCK  ");
		sql.append(" WHERE ITEM_ID = ? AND ITEM_TYPE = ? AND ITEM_COLOR IS NOT NULL");
		colorLst = jdbcTemplate.queryForList(sql.toString(), new Object[] { id, itemType }, String.class);
		detailVo.setColorLst(colorLst);

		// 获取尺码
		sql.setLength(0);
		sql.append("  SELECT DISTINCT ITEM_SIZE FROM T_ITEM_PARAM_STOCK  ");
		sql.append(" WHERE ITEM_ID = ? AND ITEM_TYPE = ? AND ITEM_SIZE IS NOT NULL");
		sql.append(" ORDER BY ITEM_SIZE ASC ");
		sizeLst = jdbcTemplate.queryForList(sql.toString(), new Object[] { id, itemType }, String.class);
		detailVo.setSizeLst(sizeLst);

		// 获取其它商品信息
		WelFareModel welfareModel = getRemark(id);
		detailVo.setModel(welfareModel);

		return detailVo;
	}

	/**
	 * 获取商品除尺码颜色外的信息
	 * 
	 * @param id
	 * @return
	 */
	public WelFareModel getRemark(Integer id) {
		StringBuilder sql = new StringBuilder();
		WelFareModel model = null;
		sql.append(" SELECT A.ID, ");
		sql.append("        A.STOCK_NUM, ");
		sql.append("        A.USER_PER_BUY_NUM, ");
		sql.append("        A.SHOP_CLASS, ");
		sql.append("        A.STORE_ID, ");
		sql.append("        A.MARKET_PRICE, ");
		sql.append("        A.NAME, ");
		sql.append("        A.SHORT_NAME, ");
		sql.append("        A.WARM_PROMPT, ");
		sql.append("        A.REMARK, ");
		sql.append("        A.SHOP_USER_ID, ");
		sql.append("        A.BRAND, ");
		sql.append("        A.WEIGHT, ");
		sql.append("        A.MARKET_CONTENT, ");
		sql.append("        A.SHOP_PRICE, ");
		sql.append("        A.IMG_PATH ");
		sql.append(" FROM T_ITEM_SALE A ");
		sql.append(" WHERE ID = ? ");
		model = (WelFareModel) jdbcTemplate.query(sql.toString(), new Object[] { id }, new BeanPropertyRowMapper(WelFareModel.class)).iterator()
		        .next();
		return model;
	}

	/**
	 * 根据参数获取库存、id等信息
	 */
	@SuppressWarnings("unchecked")
	public WelFareStock getStockBy4Param(Integer itemId, String itemColor, String ItemSize, Integer itemType) {
		StringBuilder sql = new StringBuilder();
		List<Object> args = new ArrayList<Object>();
		WelFareStock model = null;
		sql.append(" SELECT ID, ITEM_ID, ITEM_COLOR,ITEM_SIZE, STOCKNUM ,ITEM_TYPE, REMARK");
		sql.append(" FROM T_ITEM_PARAM_STOCK ");
		sql.append("  WHERE ITEM_ID = ? ");
		sql.append("     AND ITEM_COLOR = ? ");
		sql.append("     AND ITEM_SIZE = ? ");
		sql.append("     AND ITEM_TYPE = ? ");
		args.add(itemId);
		args.add(itemColor);
		args.add(ItemSize);
		args.add(itemType);
		model = (WelFareStock) jdbcTemplate.queryForObject(sql.toString(), args.toArray(), new BeanPropertyRowMapper(WelFareStock.class));

		return model;
	}

	@SuppressWarnings("unchecked")
	public List<WelFareStock> getColorsBySize(Integer itemId, String ItemSize, Integer itemType) {
		StringBuilder sql = new StringBuilder();
		List<Object> args = new ArrayList<Object>();
		List<WelFareStock> Lst = null;
		sql.append(" SELECT ID, ITEM_ID, ITEM_COLOR,ITEM_SIZE, STOCKNUM ,ITEM_TYPE, REMARK");
		sql.append(" FROM T_ITEM_PARAM_STOCK ");
		sql.append("  WHERE ITEM_ID = ? ");
		sql.append("     AND ITEM_SIZE = ? ");
		sql.append("     AND ITEM_TYPE = ? ");
		args.add(itemId);
		args.add(ItemSize);
		args.add(itemType);
		Lst = jdbcTemplate.query(sql.toString(), args.toArray(), new BeanPropertyRowMapper(WelFareStock.class));

		return Lst;
	}

	@SuppressWarnings("unchecked")
	public List<WelFareStock> getSizeByColor(Integer itemId, String ItemColor, Integer itemType) {
		StringBuilder sql = new StringBuilder();
		List<Object> args = new ArrayList<Object>();
		List<WelFareStock> Lst = null;
		sql.append(" SELECT ID, ITEM_ID, ITEM_COLOR,ITEM_SIZE, STOCKNUM ,ITEM_TYPE, REMARK");
		sql.append(" FROM T_ITEM_PARAM_STOCK ");
		sql.append("  WHERE ITEM_ID = ? ");
		sql.append("     AND ITEM_COLOR = ? ");
		sql.append("     AND ITEM_TYPE = ? ");
		args.add(itemId);
		args.add(ItemColor);
		args.add(itemType);
		Lst = jdbcTemplate.query(sql.toString(), args.toArray(), new BeanPropertyRowMapper(WelFareStock.class));

		return Lst;
	}

	/**
	 * 更新库存
	 * 
	 * @param id
	 * @return
	 */
	public void updateStore(Long Id) {
		StringBuilder sql = new StringBuilder();
		sql.append(" UPDATE T_ITEM_PARAM_STOCK  ");
		sql.append("    SET STOCKNUM = STOCKNUM - 1 ");
		sql.append("  WHERE ID = ? ");
		int result = jdbcTemplate.update(sql.toString(), new Object[] { Id });
		if (result != 1) {
			throw new RuntimeException("订单数量更新失败,返回更新成功数量为: " + result);
		}
	}

	/**
	 * 添加订单流水日志
	 * 
	 * @param model
	 */
	public void addLogOrderInfo(WelFareLogOrderModel model) {
		StringBuilder sql = new StringBuilder();
		List<Object> args = new ArrayList<Object>();

		sql.append(" INSERT INTO T_ITEM_PARAM_STOCK_RECORD ( ID, STOCK_ID, REMARK, TYPE, QUANTITY) ");
		sql.append(" VALUES ");

		if (model.getId() > 0) {
			sql.append(" ( ?, ?, ?, ?, ?) ");
			args.add(model.getId());
		} else {
			sql.append(" ( SEQ_ITEM_PARAM_RED.NEXTVAL , ?, ?, ?, ?) ");
		}
		args.add(model.getStockId());
		args.add(model.getRemark());
		args.add(model.getType());
		args.add(model.getQuantity());

		jdbcTemplate.update(sql.toString(), args.toArray());
	}

	/**
	 * 生成订单之前确认订单
	 * 
	 * @param stockId
	 * @param itemId
	 * @param color
	 * @param size
	 * @param itemType
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public WelFareModel confirm(Integer stockId, Integer itemId, String color, String size, Integer itemType) {
		WelFareModel welFareModel = null;
		StringBuilder sql = new StringBuilder();
		List<Object> args = new ArrayList<Object>();
		sql.append(" SELECT A.ID STOCKID, A.ITEM_COLOR , A.ITEM_SIZE, A.STOCKNUM STOCKNUMBER, A.ITEM_TYPE,B.ID , B.REMARK,NAME, B.SHORT_NAME,B.IS_VALID ");
		sql.append(" FROM T_ITEM_PARAM_STOCK A, T_ITEM_SALE B");
		sql.append("  WHERE A.ID = ? ");
		sql.append("     AND B.ID = A.ITEM_ID");
		sql.append("     AND ITEM_ID = ? ");
		sql.append("     AND ITEM_COLOR = ? ");
		sql.append("     AND ITEM_TYPE = ? ");
		sql.append("     AND ITEM_SIZE = ? ");
		args.add(stockId);
		args.add(itemId);
		args.add(color);
		args.add(itemType);
		args.add(size);
		welFareModel = (WelFareModel) jdbcTemplate.queryForObject(sql.toString(), args.toArray(), new BeanPropertyRowMapper(WelFareModel.class));

		return welFareModel;
	}

	/**
	 * 获取序列
	 * 
	 * @return
	 */
	public Long getSequence() {
		String sql = " select seq_item_param_red.nextval from dual ";
		Long seq = jdbcTemplate.queryForLong(sql);
		return seq;
	}

	public MobileModel getMobileModelById(Long mobile) {
		MobileModel model = null;
		StringBuilder sql = new StringBuilder();
		sql.append(" select T.ID, ");
		sql.append("        T.NAME, ");
		sql.append("        T.TERMINAL_ID, ");
		sql.append("        T.JOBNUMBER, ");
		sql.append("        T.COMPANY, ");
		sql.append("        T.DEPARTMENT, ");
		sql.append("        T.ADDRESS, ");
		sql.append("        T.REMARK ");
		sql.append("     FROM T_BONUS_TERMINAL T ");
		sql.append(" WHERE T.TERMINAL_ID = ? ");

		model = (MobileModel) jdbcTemplate.query(sql.toString(), new Object[] { mobile }, new BeanPropertyRowMapper(MobileModel.class)).iterator()
		        .next();

		return model;
	}

	public List<String> getMobiles() {

		String sql = "SELECT  A.TERMINAL_ID  FROM T_BONUS_TERMINAL A";

		List<String> mobileLst = jdbcTemplate.queryForList(sql.toString(), String.class);

		return mobileLst;

	}

	public ListPage<Map<String, Object>> getList(WelFareModel welFaremodel, int page, int pageSize) throws SQLException {

		StringBuilder sql = new StringBuilder();
		List<Object> param = new ArrayList<Object>();

		sql.append("SELECT *   FROM  (");
		sql.append("    SELECT m.*,ROW_NUMBER() OVER(PARTITION ");
		sql.append("    BY pid ");
		sql.append("    ORDER BY item_id asc) AS RN   ");
		sql.append("    FROM(");
		sql.append(" select p.item_id,");
		sql.append(" 	 s.pid,");
		sql.append(" 	 s.name,");
		sql.append(" 	 s.img_path,");
		sql.append(" 	 s.market_price,");
		sql.append(" 	 s.shop_price,");
		sql.append(" 	 s.brand,s.IS_VALID,s.STATUS,s.ISECKILL, ");
		sql.append(" 	 s.create_time,");
		sql.append(" 	 p.param_keys,");
		sql.append(" 	 p.param_values");
		sql.append(" from (select t_item_param.item_id,");
		sql.append(" 		 to_char(','||wmsys.wm_concat(t_item_param.param_key))||',' param_keys,");
		sql.append(" 		 to_char(','||wmsys.wm_concat(t_item_param.param_value))||',' param_values");
		sql.append(" 	from t_item_param,t_sys_type_item_param sysp");
		sql.append(" 	where 1=1 ");
		if (welFaremodel.getItemType() != 0) {
			sql.append(" and t_item_param.TYPE_ID = ? ");
			param.add(welFaremodel.getItemType());
		}
		sql.append(" and param_id = sysp.id and sysp.price_rule = 1");
		sql.append(" 	group by item_id) p, ");
		sql.append(" 	t_item_sale s");
		sql.append(" where p.item_id = s.id and s.IS_VALID =1 and s.STATUS=3 and s.ISECKILL = 6 ");

		if (StringUtils.isNotEmpty(welFaremodel.getItemColor())) {
			sql.append(" and p.param_values like ? ");
			param.add("%," + welFaremodel.getItemColor() + ",%");
		}

		if (StringUtils.isNotEmpty(welFaremodel.getItemSize())) {
			sql.append(" and p.param_values like ? ");
			param.add("%," + welFaremodel.getItemSize() + ",%");
		}
		if (StringUtils.isNotEmpty(welFaremodel.getBrand())) {
			sql.append(" and s.BRAND = ? ");
			param.add(welFaremodel.getBrand());
		}
		sql.append(" )m ) WHERE RN <= 1");
		sql.append(" order by create_time desc ");

		return dbHelper.getNativeMapPage(sql.toString(), page, pageSize, param.toArray());
	}

	public ListPage<Map<String, Object>> getListTwo(WelFareModel welFaremodel, int page, int pageSize) throws SQLException {
		StringBuilder sql = new StringBuilder();
		List<Object> param = new ArrayList<Object>();
		sql.append(" select  t2.id as item_id,t2.name,t2.img_path,t2.market_price,t2.shop_price,t2.pid,t2.IS_VALID,t2.STATUS,t2.ISECKILL from  ");
		sql.append(" (select item_id from T_ITEM_PARAM  where item_id in (  ");
		sql.append(" select  t1.item_id from T_ITEM_PARAM t1 where 1=1 ");
		if (welFaremodel.getItemType() != 0) {
			sql.append(" and t1.TYPE_ID = ? ");
			param.add(welFaremodel.getItemType());
		}
		sql.append("   and T1.PARAM_KEY = '颜色' and t1.PARAM_VALUE=?) ");
		sql.append(" and PARAM_KEY='尺码' and PARAM_VALUE=?)tip left join T_ITEM_SALE t2 on T2.id = TIP.ITEM_ID ");
		sql.append(" where t2.IS_VALID =1 and t2.STATUS=3 and t2.ISECKILL = 6 ");
		param.add(welFaremodel.getItemColor());
		param.add(welFaremodel.getItemSize());

		if (StringUtils.isNotEmpty(welFaremodel.getBrand())) {
			sql.append(" and t2.BRAND = ? ");
			param.add(welFaremodel.getBrand());
		}

		sql.append(" order by t2.create_time desc");

		return dbHelper.getNativeMapPage(sql.toString(), page, pageSize, param.toArray());
	}

}
