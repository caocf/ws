package com.cplatform.b2c.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cplatform.b2c.model.TGoodshelfGoodsRel;

@Repository
public class TGoodshelfGoodsRelDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
    public List<TGoodshelfGoodsRel> getList(Integer shelfId){
		Criteria c = sessionFactory.getCurrentSession().createCriteria(TGoodshelfGoodsRel.class);
		c.add(Restrictions.eq("shelfId", shelfId));
		c.addOrder(Order.asc("id"));
		List<TGoodshelfGoodsRel> list = c.list();
		return list;
	}
	
	@SuppressWarnings("unchecked")
    public List<TGoodshelfGoodsRel> getList(Integer[] shelfIds){
		if(shelfIds == null || shelfIds.length == 0){
			return null;
		}
		
		Criteria c = sessionFactory.getCurrentSession().createCriteria(TGoodshelfGoodsRel.class);
		c.add(Restrictions.in("shelfId", shelfIds));
		c.addOrder(Order.asc("id"));
		List<TGoodshelfGoodsRel> list = c.list();
		return list;
	}
	
}
