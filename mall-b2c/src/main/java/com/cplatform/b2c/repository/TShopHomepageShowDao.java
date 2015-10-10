package com.cplatform.b2c.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cplatform.b2c.model.TShopHomepageShow;

@Repository
public class TShopHomepageShowDao {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public List<TShopHomepageShow> getList(Integer shopId, Integer shopClass) {
		Criteria c = sessionFactory.getCurrentSession().createCriteria(TShopHomepageShow.class);
		c.add(Restrictions.eq("shopId", shopId));
		c.add(Restrictions.eq("shopClass", shopClass));
		c.add(Restrictions.eq("status", Short.parseShort("1")));
		c.addOrder(Order.asc("orderIndex"));
		List<TShopHomepageShow> list = c.list();
		return list;
	}
	
	

}
