package com.cplatform.b2c.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cplatform.b2c.model.TShopCustomerService;

@Repository
public class TShopCustomerServiceDao {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public List<TShopCustomerService> getList(Integer shopId, Integer shopClass) {
		Criteria c = sessionFactory.getCurrentSession().createCriteria(TShopCustomerService.class);
		c.add(Restrictions.eq("shopClass", shopClass));// 商户分类：1--业务门店
													   // 2--商户3--渠道商
		c.add(Restrictions.eq("shopId", shopId));
		c.add(Restrictions.eq("status", 1));           //有效
		c.addOrder(Order.asc("id"));
		List<TShopCustomerService> list = c.list();
		return list;
	}

}
