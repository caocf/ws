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
public class TMapDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private DbHelper dbHelper;

	@SuppressWarnings("unchecked")
	public List<TShop> getMapInfo(Integer[] id) {
		List<TShop> list = new ArrayList<TShop>();
		Criteria c = sessionFactory.getCurrentSession().createCriteria(TShop.class);
		c.add(Restrictions.in("id", id));// id
		list = c.list();
		return list;
	}

	/**
	 * 根据商品编号进行验证是否输入验证门店
	 * 
	 * @param itemId
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String, String>> verifyShop(Long itemId) throws SQLException {
		String sql = "SELECT * FROM t_item_verify_shop_link l WHERE l.sale_id = ? ";
		return dbHelper.getMapList(sql, itemId);
	}

}
