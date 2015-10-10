package com.cplatform.b2c.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cplatform.b2c.model.TChannelCatalogRcmdConf;


@Repository
public class TChannelCatalogRcmdConfDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<TChannelCatalogRcmdConf> getList(Integer groupId, Integer channel, Integer type){
		Criteria c = sessionFactory.getCurrentSession().createCriteria(TChannelCatalogRcmdConf.class);
		c.add(Restrictions.eq("channel", channel));//1-商户, 2-商品
		c.add(Restrictions.eq("groupId", groupId));// 顶级分类
		c.add(Restrictions.eq("type", type));// 顶级分类
		c.addOrder(Order.asc("orderIndex"));
		List<TChannelCatalogRcmdConf> list = c.list();
		return list;
	}
	
	@SuppressWarnings("unchecked")
    public List<TChannelCatalogRcmdConf> getList(Integer channel){
		Criteria c = sessionFactory.getCurrentSession().createCriteria(TChannelCatalogRcmdConf.class);
		c.add(Restrictions.eq("channel", channel));//1-商户, 2-商品
		c.addOrder(Order.asc("orderIndex"));
		List<TChannelCatalogRcmdConf> list = c.list();
		return list;
	}
	
	@SuppressWarnings("unchecked")
    public List<TChannelCatalogRcmdConf> getListByCode(Integer channel, String regionCode){
		Criteria c = sessionFactory.getCurrentSession().createCriteria(TChannelCatalogRcmdConf.class);
		c.add(Restrictions.eq("channel", channel));//1-商户, 2-商品
		//c.add(Restrictions.eq("regionCode", regionCode));//地市CODE
		c.add(Restrictions.like("regionCode", "%" + regionCode+ "%"));
		c.addOrder(Order.asc("orderIndex"));
		List<TChannelCatalogRcmdConf> list = c.list();
		return list;
	}
	
	@SuppressWarnings("unchecked")
    public List<TChannelCatalogRcmdConf> getListByCode(Integer channel){
		Criteria c = sessionFactory.getCurrentSession().createCriteria(TChannelCatalogRcmdConf.class);
		c.add(Restrictions.eq("channel", channel));//1-商户, 2-商品
		//c.add(Restrictions.eq("regionCode", regionCode));//地市CODE
		c.add(Restrictions.like("regionCode", "0"));
		c.addOrder(Order.asc("orderIndex"));
		List<TChannelCatalogRcmdConf> list = c.list();
		return list;
	}
	
}
