package com.cplatform.b2c.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cplatform.b2c.model.TStore;

@Repository
public class TStoreDao {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public TStore get(String id) {
		Criteria c = sessionFactory.getCurrentSession().createCriteria(TStore.class);
		c.add(Restrictions.eq("id", Integer.parseInt(id)));
		c.setMaxResults(1);
		List list = c.list();
		if (list != null && list.size() > 0) {
			return (TStore) list.get(0);
		} else {
			return null;
		}
	}

}
