package com.cplatform.b2c.repository;

import com.cplatform.b2c.model.TChannelCatalogConf;
import com.cplatform.b2c.util.StringUtil;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TChannelCatalogConfDao {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public List<TChannelCatalogConf> getList(String channel, String groupId) {
		Criteria c = sessionFactory.getCurrentSession().createCriteria(TChannelCatalogConf.class);
		c.add(Restrictions.eq("channel", Short.parseShort(channel)));// 频道分类,用于获取商城类频道的商品
		c.add(Restrictions.eq("groupId", Short.parseShort(groupId)));// 分组ID
		c.add(Restrictions.eq("status", Short.parseShort("1")));//状态为1可用
		c.addOrder(Order.asc("orderIndex"));
		List<TChannelCatalogConf> list = c.list();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<TChannelCatalogConf> getList(String channel) {
		Criteria c = sessionFactory.getCurrentSession().createCriteria(TChannelCatalogConf.class);
		c.add(Restrictions.eq("channel", Short.parseShort(channel)));// 频道分类,用于获取商城类频道的商品
		c.add(Restrictions.eq("status", Short.parseShort("1")));//状态为1可用
		c.addOrder(Order.asc("orderIndex"));
		List<TChannelCatalogConf> list = c.list();
		return list;
	}
	
	@SuppressWarnings("unchecked")
    public List<TChannelCatalogConf> getValidList(String channel, String regionCode) {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" select tcf.item_id, tcf.group_id, tcf.region_code  ");
		sqlBuffer.append(" from t_channel_catalog_conf tcf                    ");
		sqlBuffer.append("left join v_search_good v on tcf.item_id=v.G_ID     ");
		sqlBuffer.append("where tcf.channel=:channel                          ");
		sqlBuffer.append("and tcf.region_code like :regionCode                ");
		sqlBuffer.append("and v.g_id is not null                              ");
		sqlBuffer.append("and tcf.status=1                                    ");
		sqlBuffer.append("and v.g_is_view=1                                   ");
		sqlBuffer.append("order by tcf.order_index asc                        ");
		
		List<String[]> list = sessionFactory.getCurrentSession().createSQLQuery(sqlBuffer.toString())
		.setString("channel", channel).setString("regionCode", "%"+regionCode+"%").list();
		List<TChannelCatalogConf> confList = new ArrayList<TChannelCatalogConf>();
		if(list != null && list.size() > 0){
			for(Object[] strs : list ){
				TChannelCatalogConf conf = new TChannelCatalogConf();
				Integer itemId = strs[0] == null ? 0 : Integer.parseInt(StringUtil.getString(strs[0]));
				Short groupId = strs[1] == null ? 0 : Short.parseShort(StringUtil.getString(strs[1]));
				
				conf.setItemId(itemId);
				conf.setGroupId(groupId);
				conf.setRegionCode((String)strs[2]);
				confList.add(conf);
			}
		}
		
		return confList;
	}
	
	@SuppressWarnings("unchecked")
    public List<TChannelCatalogConf> getValidList(String channel) {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" select tcf.item_id, tcf.group_id, tcf.region_code  ");
		sqlBuffer.append(" from t_channel_catalog_conf tcf                    ");
		sqlBuffer.append("left join v_search_good v on tcf.item_id=v.G_ID     ");
		sqlBuffer.append("where tcf.channel=:channel                          ");
		sqlBuffer.append("and tcf.region_code like :regionCode                ");
		sqlBuffer.append("and v.g_id is not null                              ");
		sqlBuffer.append("and tcf.status=1                                    ");
		sqlBuffer.append("and v.g_is_view=1                                   ");
		sqlBuffer.append("order by tcf.order_index asc                        ");
		
		List<String[]> list = sessionFactory.getCurrentSession().createSQLQuery(sqlBuffer.toString())
		.setString("channel", channel).setString("regionCode", "0").list();
		List<TChannelCatalogConf> confList = new ArrayList<TChannelCatalogConf>();
		if(list != null && list.size() > 0){
			for(Object[] strs : list ){
				TChannelCatalogConf conf = new TChannelCatalogConf();
				Integer itemId = strs[0] == null ? 0 : ((BigDecimal) strs[0]).intValue();
				Short groupId = strs[1] == null ? 0 : ((BigDecimal) strs[1]).shortValue();
				
				conf.setItemId(itemId);
				conf.setGroupId(groupId);
				conf.setRegionCode((String)strs[2]);
				confList.add(conf);
			}
		}
		
		return confList;
	}

}
