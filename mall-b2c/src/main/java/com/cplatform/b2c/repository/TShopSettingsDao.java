package com.cplatform.b2c.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cplatform.b2c.model.TShopSettings;

@Repository
public class TShopSettingsDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
    public TShopSettings get(Integer shopId, Integer shopClass){
		Criteria c = sessionFactory.getCurrentSession().createCriteria(TShopSettings.class);
		c.add(Restrictions.eq("shopId", shopId));
		c.add(Restrictions.eq("shopClass", shopClass));
		c.setMaxResults(1);
		List list = c.list();
		if(list != null && list.size() > 0){
			return (TShopSettings) list.get(0);
		}else{
			return null;
		}
	}
	
}
