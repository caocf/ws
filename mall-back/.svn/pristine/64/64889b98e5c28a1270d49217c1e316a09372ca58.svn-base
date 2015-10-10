package com.cplatform.mall.back.lottery.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplatform.dbhelp.DbHelper;
import com.cplatform.mall.back.lottery.dao.LotteryActiveConfDao;
import com.cplatform.mall.back.lottery.entity.LotteryActiveConf;
import com.cplatform.mall.back.lottery.entity.LotteryTarget;

/**
 * @Title
 * @Description
 * @Copyright: Copyright (c) 2013-7-26
 * @Company: 北京宽连十方数字技术有限公司
 * @Author chencheng
 * @Version: 1.0
 *
 */
@Service(value="lotteryActiveConfService")
public class LotteryActiveConfService {
	@Autowired
	private DbHelper dbHelper;
	@Autowired
	private LotteryActiveConfDao lotteryActiveConfDao;
	
	public void save(LotteryActiveConf lotteryActiveConf){
		lotteryActiveConfDao.save(lotteryActiveConf);
	}
	
	/**
	 * 根据活动id查询活动配置
	 * @param activeId
	 * @return
	 * @throws SQLException
	 */
	public List<LotteryActiveConf> findByActiveId(Long activeId) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("select t.* from t_lottery_active_conf t ");
		sql.append(" where 1=1  ");
		List<Object> params = new ArrayList<Object>();
		if(activeId!=null){
			sql.append(" and t.active_id=? ");
			params.add(activeId);
		}
		sql.append(" order by t.id asc ");
		return dbHelper.getBeanList(sql.toString(), LotteryActiveConf.class, new Object[]{activeId});
	}
	
	public List<LotteryActiveConf> findByActiveIdAndKey(Long activeId,String key) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("select t.* from t_lottery_active_conf t ");
		sql.append(" where 1=1  ");
		List<Object> params = new ArrayList<Object>();
		if(activeId!=null){
			sql.append(" and t.active_id=? ");
			params.add(activeId);
		}
		if(key!=null){
			sql.append(" and t.key = ?");
			params.add(key);
		}
		sql.append(" order by t.id desc ");
		return dbHelper.getBeanList(sql.toString(), LotteryActiveConf.class, params.toArray());
	}
	
	public void deleteByActiveIdAndKey(Long activeId,String key) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("delete from t_lottery_active_conf t ");
		sql.append(" where t.active_id =? ");
		List<Object> params = new ArrayList<Object>();
		params.add(activeId);
		sql.append(" and t.key =? ");
		params.add(key);
		dbHelper.execute(sql.toString(), params);
	}
	

}
