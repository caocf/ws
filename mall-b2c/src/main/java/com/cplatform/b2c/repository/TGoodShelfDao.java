package com.cplatform.b2c.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cplatform.b2c.model.TGoodShelf;

@Repository
public class TGoodShelfDao {
	
	//private Session session = HibernateUtil.getSession();
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<TGoodShelf> getList(String shopId){
		Criteria c = sessionFactory.getCurrentSession().createCriteria(TGoodShelf.class);
		c.add(Restrictions.eq("shopClass", 2));//商户分类：1--业务门店 2--商户3--渠道商
		c.add(Restrictions.eq("shopId", Integer.valueOf(shopId)));
		c.addOrder(Order.asc("orderIndex"));
		List<TGoodShelf> list = c.list();
		return list;
	}
	
	@SuppressWarnings("unchecked")
    public List<TGoodShelf> getTypeListByParent(Integer PId, Integer shopClass){
		StringBuffer sql = new StringBuffer();
		sql.append("select t.id, ");
		sql.append("	t.pid,   ");
		sql.append("	t.title   ");
		sql.append("from t_good_shelf t ");
		sql.append(" where t.shop_class=:shopClass ");
		sql.append(" connect by prior t.id=t.pid  ");
		sql.append(" start with id=:typeid  ");
		sql.append(" order by order_index  ");

		List<String[]> list = sessionFactory.getCurrentSession().createSQLQuery(sql.toString())
		.setInteger("typeid", PId)
		.setInteger("shopClass", shopClass).list();
		List<TGoodShelf> listRet = new ArrayList<TGoodShelf>();
		
		if(list != null && list.size() > 0){
			for(Object[] ss : list){
				TGoodShelf tmp = new TGoodShelf();
				
				tmp.setId( ((BigDecimal)ss[0] ).intValue());
				tmp.setPid( ((BigDecimal)ss[1] ).intValue());
				tmp.setTitle((String) ss[2]);
				listRet.add(tmp);
			}
		}
		return listRet;
	}
	
}
