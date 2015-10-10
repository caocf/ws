package com.cplatform.b2c.repository;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cplatform.b2c.constant.StoreIndexOrder;
import com.cplatform.b2c.model.VSearchGood;
import com.cplatform.b2c.util.PathUtil;

@Repository
public class VSearchGoodDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private PathUtil pathUtil;

	@SuppressWarnings("unchecked")
	public VSearchGood get(String id) {
		String hql = "from VSearchGood where id.GId = :id";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setInteger("id", Integer.valueOf(id));
		List<VSearchGood> list = query.list();
		if (null != list && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<VSearchGood> getList(Integer[] ids) {
		String hql = "from VSearchGood where id.GId in(:ids)";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameterList("ids", ids);
		List<VSearchGood> list = query.list();
		for (VSearchGood good : list) {
			good.setItemPath(pathUtil.getPathById(PathUtil.TYPE_ITEM, Long.valueOf(good.getId().getGId())));
			good.setImgPath(pathUtil.getPathById(PathUtil.TYPE_ITEM_PIC, Long.valueOf(good.getId().getGId())));
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<VSearchGood> getList(Integer[] ids, int maxResults) {
		String hql = "from VSearchGood where GIseckill <> ? AND GIseckill <> ? AND id.GId in(:ids) order by GUpdateTime desc";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);

		// 虚拟商店的商品不在商户店铺前台显示
		query.setParameter(0, 7);
		query.setParameter(1, 8);

		query.setParameterList("ids", ids);
		query.setMaxResults(maxResults);
		List<VSearchGood> list = query.list();
		for (VSearchGood good : list) {
			good.setItemPath(pathUtil.getPathById(PathUtil.TYPE_ITEM, Long.valueOf(good.getId().getGId())));
			good.setImgPath(pathUtil.getPathById(PathUtil.TYPE_ITEM_PIC, Long.valueOf(good.getId().getGId())));
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<VSearchGood> getListBy(Integer[] ids, Integer GGroupFlag) {
		String hql = "from VSearchGood where id.GId in(:ids) and GGroupFlag=:flag";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameterList("ids", ids);
		query.setInteger("flag", GGroupFlag);
		List<VSearchGood> list = query.list();
		for (VSearchGood good : list) {
			good.setItemPath(pathUtil.getPathById(PathUtil.TYPE_ITEM, Long.valueOf(good.getId().getGId())));
			good.setImgPath(pathUtil.getPathById(PathUtil.TYPE_ITEM_PIC, Long.valueOf(good.getId().getGId())));
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<VSearchGood> getListByType(Integer[] typeIds, int maxResults) {
		String hql = "from VSearchGood where GTypeId in(:ids) order by GSaleNum desc";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameterList("ids", typeIds);
		query.setMaxResults(maxResults);
		List<VSearchGood> list = query.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<VSearchGood> getDefListByType(Integer[] typeIds, int maxResults) {
		String hql = "from VSearchGood where GTypeId in(:ids) and GGroupFlag=:flag and GIsView=:isview order by GCreateTime desc";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameterList("ids", typeIds);
		query.setInteger("flag", 0);
		query.setInteger("isview", 1);
		query.setMaxResults(maxResults);
		List<VSearchGood> list = query.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<VSearchGood> getListBy(Integer GStoreId, String GName, String GShopPriceLow, String GShopPriceHigh, Integer[] GIds,
	        StoreIndexOrder order, int firstResult, int maxResults) {
		Criteria c = sessionFactory.getCurrentSession().createCriteria(VSearchGood.class);
		c.add(Restrictions.eq("GStoreId", GStoreId));// 1-商户, 2-商品
		c.add(Restrictions.eq("GIsView", Integer.parseInt("1"))); // 可见
		c.add(Restrictions.eq("GGroupFlag", 0)); // 非优惠套餐

		// 虚拟商店的商品不在商户店铺前台显示
		c.add(Restrictions.ne("GIseckill", 7));
		c.add(Restrictions.ne("GIseckill", 8));

		if (GName != null && !"".equals(GName)) {
			c.add(Restrictions.like("GName", "%" + GName + "%"));
		}
		if (GShopPriceLow != null && !"".equals(GShopPriceLow)) {
			c.add(Restrictions.ge("GShopPriceNumber", Double.parseDouble(GShopPriceLow)));
		}
		if (GShopPriceHigh != null && !"".equals(GShopPriceHigh)) {
			c.add(Restrictions.le("GShopPriceNumber", Double.parseDouble(GShopPriceHigh)));
		}
		if (GIds != null && GIds.length > 0) {
			c.add(Restrictions.in("id.GId", GIds));
		}
		switch (order) {
			case SHOP_PRICE_ASC:
				c.addOrder(Order.asc("GShopPriceNumber"));
				break;
			case SHOP_PRICE_DESC:
				c.addOrder(Order.desc("GShopPriceNumber"));
				break;
			case START_TIME_DESC:
				c.addOrder(Order.desc("GUpdateTime"));
				break;
			case START_TIME_ASC:
				c.addOrder(Order.asc("GUpdateTime"));
				break;
			default:
				c.addOrder(Order.desc("GUpdateTime"));
				break;
		}

		c.addOrder(Order.asc("id.GId"));

		c.setFirstResult(firstResult);
		c.setMaxResults(maxResults);
		List<VSearchGood> list = c.list();
		return list;
	}

	public int getCountBy(Integer GStoreId, String GName, String GShopPriceLow, String GShopPriceHigh, Integer[] GIds) {
		Criteria c = sessionFactory.getCurrentSession().createCriteria(VSearchGood.class);
		c.add(Restrictions.eq("GStoreId", GStoreId));// 1-商户, 2-商品
		c.add(Restrictions.eq("GIsView", Integer.parseInt("1"))); // 可见
		c.add(Restrictions.eq("GGroupFlag", 0)); // 非优惠套餐

		// 虚拟商店的商品不在商户店铺前台显示
		c.add(Restrictions.ne("GIseckill", 7));
		c.add(Restrictions.ne("GIseckill", 8));

		if (GName != null && !"".equals(GName)) {
			c.add(Restrictions.like("GName", "%" + GName + "%"));
		}
		if (GShopPriceLow != null && !"".equals(GShopPriceLow)) {
			c.add(Restrictions.ge("GShopPriceNumber", Double.parseDouble(GShopPriceLow)));
		}
		if (GShopPriceHigh != null && !"".equals(GShopPriceHigh)) {
			c.add(Restrictions.le("GShopPriceNumber", Double.parseDouble(GShopPriceHigh)));
		}
		if (GIds != null && GIds.length > 0) {
			c.add(Restrictions.in("id.GId", GIds));
		}

		return new BigDecimal((Long) c.setProjection(Projections.rowCount()).uniqueResult()).intValue();

	}
}