package com.cplatform.b2c.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cplatform.b2c.model.TChannelFloorConf;


@Repository
public class TChannelFloorConfDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<TChannelFloorConf> getList(Integer channel, String regionCode){
		Criteria c = sessionFactory.getCurrentSession().createCriteria(TChannelFloorConf.class);
		c.add(Restrictions.eq("channel", channel));
//		c.add(Restrictions.eq("regionCode", regionCode));
		c.add(Restrictions.like("regionCode", "%" + regionCode+ "%"));
		c.addOrder(Order.asc("floorId"));
		List<TChannelFloorConf> list = c.list();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<TChannelFloorConf> getList(Integer channel){
		Criteria c = sessionFactory.getCurrentSession().createCriteria(TChannelFloorConf.class);
		c.add(Restrictions.eq("channel", channel));
//		c.add(Restrictions.eq("regionCode", regionCode));
		c.add(Restrictions.like("regionCode", "0"));
		c.addOrder(Order.asc("floorId"));
		List<TChannelFloorConf> list = c.list();
		return list;
	}
	
}
