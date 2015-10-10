package com.cplatform.b2c.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cplatform.b2c.model.TGoodShelf;
import com.cplatform.b2c.model.TSysType;
import com.cplatform.b2c.util.StringUtil;

@Repository
public class TSysTypeDao {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
    public List<TSysType> getTypeListByParent(Integer PId, Integer channel){
		StringBuffer sql = new StringBuffer();
		sql.append("select t.id, ");
		sql.append("	t.p_id,   ");
		sql.append("	t.name   ");
		sql.append("from t_sys_type t ");
		sql.append(" where t.type=:type ");
		sql.append(" and t.is_valid=1   ");
		sql.append(" connect by prior t.id=t.p_id  ");
		sql.append(" start with id=:typeid  ");
		sql.append(" order by id  ");

		List<Object[]> list = sessionFactory.getCurrentSession().createSQLQuery(sql.toString())
		.setInteger("typeid", PId)
		.setInteger("type", channel).list();
		List<TSysType> listRet = new ArrayList<TSysType>();
		
		if(list != null && list.size() > 0){
			for(Object[] ss : list){
				TSysType tmp = new TSysType();
				
				tmp.setId(Integer.parseInt(StringUtil.getString(ss[0])));
				tmp.setPId(Integer.parseInt(StringUtil.getString(ss[1])));
				tmp.setName((String) ss[2]);
				listRet.add(tmp);
			}
		}
		return listRet;
	}
	
	@SuppressWarnings("unchecked")
	public List<TSysType> getTopTypeList(Integer channel){
		Criteria c = sessionFactory.getCurrentSession().createCriteria(TSysType.class);
		c.add(Restrictions.eq("type", channel));//1-商户, 2-商品
		c.add(Restrictions.eq("PId", Integer.valueOf("0")));// 顶级分类
		c.add(Restrictions.eq("isValid", Integer.valueOf("1")));
		c.addOrder(Order.asc("id"));
		List<TSysType> list = c.list();
		return list;
	}
	
	
	@SuppressWarnings("unchecked")
    public List<TSysType> getAllList(Integer channel){
		Criteria c = sessionFactory.getCurrentSession().createCriteria(TSysType.class);
		c.add(Restrictions.eq("type", channel));//1-商户, 2-商品
		c.add(Restrictions.eq("isValid", Integer.valueOf("1")));
		c.addOrder(Order.asc("id"));
		List<TSysType> list = c.list();
		return list;
	}
	
	
	@SuppressWarnings("unchecked")
    public List<TSysType> getParentTypeList(Integer typeId, Integer channel){
		StringBuffer sql = new StringBuffer();
		sql.append("select t.id, ");
		sql.append("	t.p_id,   ");
		sql.append("	t.name   ");
		sql.append("from t_sys_type t ");
		sql.append(" where t.type=:type ");
		sql.append(" and t.is_valid=1   ");
		sql.append(" connect by prior t.p_id=t.id ");
		sql.append(" start with id=:typeid  ");
		sql.append("  order by level desc  ");

		List<String[]> list = sessionFactory.getCurrentSession().createSQLQuery(sql.toString())
		.setInteger("typeid", typeId)
		.setInteger("type", channel).list();
		List<TSysType> listRet = new ArrayList<TSysType>();
		
		if(list != null && list.size() > 0){
			for(Object[] ss : list){
				TSysType tmp = new TSysType();

				tmp.setId( ((BigDecimal)ss[0] ).intValue());
				tmp.setPId( ((BigDecimal)ss[1] ).intValue());
				tmp.setName((String) ss[2]);
				listRet.add(tmp);
			}
		}
		return listRet;
	}
	
}
