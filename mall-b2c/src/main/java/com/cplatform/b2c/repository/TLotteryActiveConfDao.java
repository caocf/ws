package com.cplatform.b2c.repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cplatform.b2c.model.TLotteryActiveConf;
import com.cplatform.b2c.model.TLotteryPrize;
import com.cplatform.dbhelp.DbHelper;

@Repository
public class TLotteryActiveConfDao {

	@Autowired
	DbHelper dbHelper;

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public List<TLotteryActiveConf> findActiveConfByActiveId(Integer activeId) {
		String hql = "from TLotteryActiveConf where activeId = ? ";
		return sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, activeId).list();
	}

	@SuppressWarnings("unchecked")
	public List<TLotteryPrize> findPrizeByActiveId(Integer activeId) {
		String hql = "from TLotteryPrize tl where activeId =? ";
		return sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, activeId).list();
	}

	public List<Map<String, Object>> findMessage(int activeId) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("select tll.active_id,tll.target_id,tlp.name from T_LOTTERY_LOG tll  ");
		sql.append(" left join T_LOTTERY_PRIZE tlp on tll.prize_id = tlp.id ");
		sql.append(" where tll.active_id = ? and tll.prize_id <> 0 ");
		sql.append(" order by tll.hit_time desc ");
		return dbHelper.getNativeMapList(sql.toString(), activeId);
	}
}
