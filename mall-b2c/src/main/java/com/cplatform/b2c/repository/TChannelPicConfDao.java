package com.cplatform.b2c.repository;

import com.cplatform.b2c.model.TChannelPicConf;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TChannelPicConfDao {

	@Autowired
	private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
	public List<TChannelPicConf> getList(String channel, String position) {
		Criteria c = sessionFactory.getCurrentSession().createCriteria(TChannelPicConf.class);
		c.add(Restrictions.eq("channel", Short.parseShort(channel) ));// 商户分类：1--业务门店 2--商户3--渠道商
		c.add(Restrictions.eq("postion", Short.parseShort(position) ));// 位置 0-标识最顶部, 往下依次为1楼,2,3,4
		c.addOrder(Order.asc("picIndex"));
		List<TChannelPicConf> list = c.list();
		return list;
	}
    
    @SuppressWarnings("unchecked")
	public List<TChannelPicConf> getList(String channel) {
		Criteria c = sessionFactory.getCurrentSession().createCriteria(TChannelPicConf.class);
		c.add(Restrictions.eq("channel", Short.parseShort(channel) ));// 商户分类：1--业务门店 2--商户3--渠道商
		c.addOrder(Order.asc("picIndex"));
		List<TChannelPicConf> list = c.list();
		return list;
	}
    
    @SuppressWarnings("unchecked")
	public List<TChannelPicConf> getListByCode(String channel, String regionCode) {
		Criteria c = sessionFactory.getCurrentSession().createCriteria(TChannelPicConf.class);
		c.add(Restrictions.eq("channel", Short.parseShort(channel) ));// 商户分类：1--业务门店 2--商户3--渠道商
		//c.add(Restrictions.eq("regionCode", regionCode ));//地市编号
		c.add(Restrictions.like("regionCode", "%" + regionCode+ "%"));
		c.addOrder(Order.asc("picIndex"));
		List<TChannelPicConf> list = c.list();
		return list;
	}
    
    @SuppressWarnings("unchecked")
   	public List<TChannelPicConf> getListByCode(String channel) {
   		Criteria c = sessionFactory.getCurrentSession().createCriteria(TChannelPicConf.class);
   		c.add(Restrictions.eq("channel", Short.parseShort(channel) ));// 商户分类：1--业务门店 2--商户3--渠道商
   		//c.add(Restrictions.eq("regionCode", regionCode ));//地市编号
   		c.add(Restrictions.like("regionCode", "0"));
   		c.addOrder(Order.asc("picIndex"));
   		List<TChannelPicConf> list = c.list();
   		return list;
   	}
    
}
