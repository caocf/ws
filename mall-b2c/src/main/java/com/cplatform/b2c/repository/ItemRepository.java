package com.cplatform.b2c.repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cplatform.b2c.model.PageInfo;
import com.cplatform.b2c.model.TItemComment;
import com.cplatform.b2c.model.TMemberFavorite;
import com.cplatform.dbhelp.DbHelper;

/**
 * 商品数据业务层类 标题、简要说明. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) May 29, 2013 4:23:05 PM
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author zhangyinf@c-platform.com
 * @version 1.0.0
 */
@Repository
public class ItemRepository {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private DbHelper dbHelper;

	/**
	 * 获取商品评论
	 * 
	 * @param saleId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getItemComments(PageInfo pageInfo, String saleId) {
		StringBuffer sql = new StringBuffer();
		sql.append("select t.id,");
		sql.append("       t.sale_id,");
		sql.append("       t.nickname,");
		sql.append("       t.rank,");
		sql.append("       t.content,");
		sql.append("       to_char(to_date(t.update_time, 'yyyy-mm-dd hh24:mi:ss'), 'yyyy-mm-dd hh24:mi:ss'),");
		sql.append("       nvl(t.useful_num,0),");
		sql.append("       nvl(t.useless_num,0),");
		sql.append("       r.content replycontent,");
		sql.append("       r.nickname reply,");
		sql.append("       to_char(to_date(r.update_time, 'yyyy-mm-dd hh24:mi:ss'), 'yyyy-mm-dd hh24:mi:ss') replytime,");
		sql.append("       m.terminal_id,");
		sql.append("       r.terminal_id rterminal_id");
		sql.append("  from t_item_comment t");
		sql.append("  left join (select re.*, me.terminal_id");
		sql.append("      from t_item_comment_reply re");
		sql.append("      left join t_member me");
		sql.append("       on re.user_id = me.id");
		sql.append("     where me.terminal_id > 0) r");
		sql.append(" on t.id = r.comment_id");
		sql.append(" left join t_member m on t.user_id=m.id");
		sql.append(" where t.type = 1");
		sql.append("   and t.status = 1");
		sql.append("   and t.sale_id = :saleId");
		sql.append("  order by t.id desc");
		return sessionFactory.getCurrentSession().createSQLQuery(sql.toString()).setLong("saleId", Long.valueOf(saleId))
		        .setFirstResult(pageInfo.getPageRows() * (pageInfo.getCurPage() - 1)).setMaxResults(pageInfo.getPageRows()).list();

	}

	public String getTotalComment(String saleId) {
		StringBuffer sql = new StringBuffer();
		sql.append("select count(*)");
		sql.append("  from t_item_comment t");
		sql.append("  left join t_item_comment_reply r on t.id = r.comment_id");
		sql.append(" where t.type = 1");
		sql.append("   and t.status = 1");
		sql.append("   and t.sale_id = :saleId");
		sql.append("  order by t.id desc");
		String rowTotal = String.valueOf((sessionFactory.getCurrentSession().createSQLQuery(sql.toString()).setLong("saleId", Long.valueOf(saleId))
		        .uniqueResult()));

		return rowTotal;
	}

	/**
	 * 获取分页
	 * 
	 * @param pageInfo
	 * @param saleId
	 * @return
	 */
	public List<Object[]> getItemCommentsScript(PageInfo pageInfo, String saleId) {

		String rowTotal = getTotalComment(saleId);
		pageInfo.setRecordCount(Integer.valueOf(rowTotal));
		pageInfo.setPageTotals();
		List<Object[]> script = new ArrayList<Object[]>();
		script.add(new String[] { pageInfo.getScript("1") });

		return script;
	}

	/**
	 * 获取商品物流等信息
	 * 
	 * @param saleId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<String[]> getBasicInfo(String saleId) {
		StringBuffer sql = new StringBuffer();
		sql.append("select f.fee_num,");
		sql.append("       decode(l.province_code, null, '', l.province_code || ' ') ||");
		sql.append("       decode(l.city_code, null, '', l.city_code || ' ') ||");
		sql.append("       decode(l.region_code, null, '', l.region_code || ' '),");
		sql.append("       decode(e.sale_num, null, 0, e.sale_num),");
		sql.append("       decode(e.click_num, null, 0, e.click_num),");
		sql.append("       decode(e.comment_num, null, 0, e.comment_num),");
		sql.append("       decode(e.user_num, null, 0, e.user_num),");
		sql.append("       decode(e.collect_num, null, 0, e.collect_num),");
		sql.append("       decode(s.stock_num, null, 0, s.stock_num)");
		sql.append("  from t_item_org o");
		sql.append("  left join t_item_logistics_fee f on o.id = f.sale_id");
		sql.append("  left join t_item_post_area_link l on o.id = l.sale_id");
		sql.append("  left join t_item_sale_ext e on o.id = e.sale_id");
		sql.append("  left join t_item_sale s on o.id = s.org_id");
		sql.append(" where o.id = :saleId");

		return sessionFactory.getCurrentSession().createSQLQuery(sql.toString()).setLong("saleId", Long.valueOf(saleId)).list();
	}

	/**
	 * 获取商品成交记录
	 * 
	 * @param pageInfo
	 * @param saleId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getPurchaseRecords(PageInfo pageInfo, String saleId) {
		StringBuffer sql = new StringBuffer();
		sql.append("select o.user_id,");
		sql.append("       (g.pay_price*g.count)/100,");
		sql.append("       to_char(to_date(o.create_time, 'yyyy-mm-dd hh24:mi:ss'),");
		sql.append("               'yyyy-mm-dd hh24:mi:ss'),");
		sql.append("       g.count,");
		sql.append("       '成交'");
		sql.append("  from t_act_order_goods g");
		sql.append("  left join t_act_order o on o.id = g.act_order_id");
		sql.append(" where o.pay_status = 2");
		sql.append("   and g.goods_id = :saleId");
		sql.append("  order by o.create_time desc");

		return sessionFactory.getCurrentSession().createSQLQuery(sql.toString()).setLong("saleId", Long.valueOf(saleId))
		        .setFirstResult(pageInfo.getPageRows() * (pageInfo.getCurPage() - 1)).setMaxResults(pageInfo.getPageRows()).list();

	}

	/**
	 * 获取分页
	 * 
	 * @param pageInfo
	 * @param saleId
	 * @return
	 */
	public List<Object[]> getPurchaseRecordsScript(PageInfo pageInfo, String saleId) {
		StringBuffer sql = new StringBuffer();
		sql.append("select count(*)");
		sql.append("  from t_act_order_goods g");
		sql.append("  left join t_act_order o on o.id = g.act_order_id");
		sql.append(" where o.pay_status = 2");
		sql.append("   and g.goods_id = :saleId");
		sql.append("  order by o.create_time desc");
		String rowTotal = String.valueOf(sessionFactory.getCurrentSession().createSQLQuery(sql.toString()).setLong("saleId", Long.valueOf(saleId))
		        .uniqueResult());
		pageInfo.setRecordCount(Integer.valueOf(rowTotal));
		pageInfo.setPageTotals();
		List<Object[]> script = new ArrayList<Object[]>();
		script.add(new String[] { pageInfo.getScript("2") });

		return script;
	}

	/**
	 * 获取商品所属套餐ID
	 * 
	 * @param saleId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<String[]> getDistountId(String saleId) {
		return sessionFactory
		        .getCurrentSession()
		        .createQuery("select ITEM_ORG_ID from t_item_group_link l, t_item_org o where l.item_org_id = o.id and o.status = 3 and l.ITEM_SALE_ID = :saleId")
		        .setLong("saleId", Long.valueOf(saleId)).list();
	}

	/**
	 * 获取某商品套餐
	 * 
	 * @param itemOrgId
	 * @param saleId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<String[]> getDistountGoods(String itemOrgId, String saleId) {
		return sessionFactory
		        .getCurrentSession()
		        .createQuery("select o.id, o.name, i.file_name form t_item_org o, t_item_group_link l,t_sys_file_img_link i where o.id = l.item_sale_id and o.id = i.lb_id and i.order = 1 and o.id <> :saleId and l.item_org_id = :itemOrgId")
		        .setLong("saleId", Long.valueOf(saleId)).setLong("itemOrgId", Long.valueOf(itemOrgId)).list();
	}

	/**
	 * 获取咨询
	 * 
	 * @param pageInfo
	 * @param saleId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getItemConsults(PageInfo pageInfo, String saleId) {
		StringBuffer sql = new StringBuffer();
		sql.append("select t.id,");
		sql.append("       t.nickname,");
		sql.append("       t.content,");
		sql.append("       to_char(to_date(t.update_time, 'yyyy-mm-dd hh24:mi:ss'), 'yyyy-mm-dd hh24:mi:ss'),");
		sql.append("       decode(t.question_type,1,'商品咨询',2,'促销活动咨询',3,'库存及物流咨询',4,'售后咨询'),");
		sql.append("       r.content replycontent,");
		sql.append("       r.nickname reply,");
		sql.append("       to_char(to_date(r.update_time, 'yyyy-mm-dd hh24:mi:ss'), 'yyyy-mm-dd hh24:mi:ss') replytime");
		sql.append("  from t_item_comment t");
		sql.append("  left join t_item_comment_reply r on t.id = r.comment_id");
		sql.append(" where t.type = 2");
		sql.append("   and t.status = 1");
		sql.append("   and t.sale_id = :saleId");
		sql.append("  order by t.id desc");
		return sessionFactory.getCurrentSession().createSQLQuery(sql.toString()).setLong("saleId", Long.valueOf(saleId))
		        .setFirstResult(pageInfo.getPageRows() * (pageInfo.getCurPage() - 1)).setMaxResults(pageInfo.getPageRows()).list();
	}

	/**
	 * 获取商品额外信息
	 * 
	 * @param saleId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Object[] getItemExt(String saleId) {
		List<Object[]> res = sessionFactory.getCurrentSession()
		        .createSQLQuery("select comment_num, rank from t_item_sale_ext where sale_id = :saleId").setLong("saleId", Long.valueOf(saleId))
		        .list();
		if (null != res && !res.isEmpty()) {
			return res.get(0);
		}
		return null;
	}

	/**
	 * 获取分页
	 * 
	 * @param pageInfo
	 * @param saleId
	 * @return
	 */
	public List<Object[]> getItemConsultsScript(PageInfo pageInfo, String saleId) {
		StringBuffer sql = new StringBuffer();
		sql.append("select count(*)");
		sql.append("  from t_item_comment t");
		sql.append("  left join t_item_comment_reply r on t.id = r.comment_id");
		sql.append(" where t.type = 2");
		sql.append("   and t.status = 1");
		sql.append("   and t.sale_id = :saleId");
		sql.append(" order by t.id desc");
		String rowTotal = String.valueOf(sessionFactory.getCurrentSession().createSQLQuery(sql.toString()).setLong("saleId", Long.valueOf(saleId))
		        .uniqueResult());
		pageInfo.setRecordCount(Integer.valueOf(rowTotal));
		pageInfo.setPageTotals();
		List<Object[]> script = new ArrayList<Object[]>();
		script.add(new String[] { pageInfo.getScript("3") });

		return script;
	}

	/**
	 * 保存评价是否有用
	 * 
	 * @param saleId
	 * @param flag
	 */
	public boolean saveIsUse(String commentId, String flag) {
		String up = "useless_num";
		if (StringUtils.equals(flag, "1")) {
			up = "useful_num";
		}
		int cn = sessionFactory.getCurrentSession().createSQLQuery("update t_item_comment set " + up + " = " + up + " + 1 where id = :commentId")
		        .setLong("commentId", Long.valueOf(commentId)).executeUpdate();
		return cn > 0;
	}

	/**
	 * 更新商品收藏数量
	 * 
	 * @param saleId
	 */
	public boolean updateCollectNum(String saleId, TMemberFavorite favoirte) {
		Session session = sessionFactory.getCurrentSession();
		session.save(favoirte);
		if (favoirte.getFavoriteId() == 1) {
			Object rowTotal = session.createSQLQuery("select count(*) from t_item_sale_ext where sale_id = :saleId")
			        .setLong("saleId", Long.valueOf(saleId)).uniqueResult();
			int cn = 0;
			if (Integer.valueOf(rowTotal.toString()) > 0) {
				cn = session.createSQLQuery("update t_item_sale_ext set collect_num = collect_num + 1 where sale_id = :saleId")
				        .setLong("saleId", Long.valueOf(saleId)).executeUpdate();
			} else {
				cn = session.createSQLQuery("insert into t_item_sale_ext (id, sale_id, collect_num) values(SEQ_COMM_ID.nextval," + saleId + ",1)")
				        .executeUpdate();
			}
			return cn > 0;
		} else {
			return true;
		}
	}

	/**
	 * 获取收藏信息
	 * 
	 * @param favorite
	 * @return
	 */
	public TMemberFavorite getFavorite(TMemberFavorite favorite) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(TMemberFavorite.class);
		criteria.add(Restrictions.eq("favoriteId", favorite.getFavoriteId()));
		criteria.add(Restrictions.eq("userId", favorite.getUserId()));
		List<TMemberFavorite> res = criteria.list();
		if (null != res && res.size() > 0) {
			return res.get(0);
		} else {
			return null;
		}
	}

	/**
	 * 保存评价和咨询
	 * 
	 * @param comment
	 * @return
	 */
	public boolean saveComment(TItemComment comment) {
		try {
			sessionFactory.getCurrentSession().save(comment);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 根据id查找对应的商品
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public Map<String, String> getItemSaleById(Long id) throws SQLException {
		String sql = "SELECT * FROM T_ITEM_SALE s WHERE s.id = ? ";
		return dbHelper.getMap(sql, id);
	}
}
