package com.cplatform.b2c.repository;

import com.cplatform.b2c.model.TChannelType;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TChannelTypeDao {

	@Autowired
	private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
	public List<TChannelType> getList(Integer channel, String regionCode) {
		Criteria c = sessionFactory.getCurrentSession().createCriteria(TChannelType.class);
		c.add(Restrictions.eq("channel", channel ));// 频道标识 1-商城
		c.add(Restrictions.eq("regionCode", regionCode ));//地市编号
		c.addOrder(Order.asc("id"));
		List<TChannelType> list = c.list();
		return list;
	}
    
}
