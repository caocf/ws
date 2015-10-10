package com.cplatform.b2c.repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cplatform.b2c.model.TShop;
import com.cplatform.dbhelp.DbHelper;

@Repository
public class TShopDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private DbHelper dbHelper;

	@SuppressWarnings("unchecked")
	public TShop get(String id) {
		Criteria c = sessionFactory.getCurrentSession().createCriteria(TShop.class);
		c.add(Restrictions.eq("id", Integer.parseInt(id)));// 商户分类：1--业务门店
		                                                   // 2--商户3--渠道商
		c.setMaxResults(1);
		List list = c.list();
		if (list != null && list.size() > 0) {
			return (TShop) list.get(0);
		} else {
			return null;
		}
	}

	public List<Map<String, Object>> getOtherItemsByShopId(Long storeId, Long saleId) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from ( ");
		sql.append(" select ext1.*,price.price as vipprice from (select sale.id,sale.name,sale.market_price,sale.shop_price,ext.sale_num,sale.cash_idgoods,sale.coin_idgoods,sale.score_idgoods,pay.bill_pay ");
		sql.append(" from t_item_sale sale,t_item_sale_ext ext,t_item_sale_payment pay ");
		sql.append(" where sale.id=ext.sale_id and sale.id=pay.item_id  and sale.iseckill=5 and sale.status=3 and sale.IS_VALID=1 and sale.IS_VIEW=1 and sale.store_id=? and sale.id<>? ) ext1 left join T_ITEM_PRICE price on ext1.id=PRICE.ITEM_ID and PRICE.PRICE_TYPE_CODE='L1' order by ext1.sale_num desc  )");
		sql.append(" where rownum<=3");
		List<Object> params = new ArrayList<Object>();
		params.add(storeId);
		params.add(saleId);
		return dbHelper.getNativeMapList(sql.toString(), params.toArray());
	}

	public List<Map<String, Object>> getOtherAgentItemsByShopId(Long storeId, Long saleId) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from ( ");
		sql.append(" select ext1.*,price.price as vipprice from (select sale.id,sale.name,sale.market_price,sale.shop_price,ext.sale_num,sale.cash_idgoods,sale.coin_idgoods,sale.score_idgoods,pay.bill_pay ");
		sql.append(" from t_item_sale sale,t_item_sale_ext ext,t_item_sale_payment pay ");
		sql.append(" where sale.id=ext.sale_id and sale.id=pay.item_id  and sale.iseckill=5 and sale.status=3 and sale.IS_VALID=1 and sale.IS_VIEW=1 and sale.agent_store_id=? and sale.id<>? ) ext1 left join T_ITEM_PRICE price on ext1.id=PRICE.ITEM_ID and PRICE.PRICE_TYPE_CODE='L1' order by ext1.sale_num desc  )");
		sql.append(" where rownum<=3");
		List<Object> params = new ArrayList<Object>();
		params.add(storeId);
		params.add(saleId);
		return dbHelper.getNativeMapList(sql.toString(), params.toArray());
	}

	/**
	 * 获取江苏省编号
	 * 
	 * @param storeId
	 * @return
	 * @throws SQLException
	 */
	public Map<String, String> getProvinceCodeByStoreId(Long storeId) throws SQLException {
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.setLength(0);
		sqlBuilder.append("SELECT g.* ");
		sqlBuilder.append("  FROM T_STORE s, T_SYS_REGION g ");
		sqlBuilder.append(" WHERE s.area_code = g.region_code ");
		sqlBuilder.append("   AND s.id = ? ");
		Map<String, String> map = dbHelper.getMap(sqlBuilder.toString(), storeId);
		return map;
	}
}
