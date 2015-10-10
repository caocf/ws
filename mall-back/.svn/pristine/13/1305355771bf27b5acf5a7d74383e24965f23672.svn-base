package com.cplatform.mall.back.lottery.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplatform.dbhelp.DbHelper;
import com.cplatform.mall.back.lottery.dao.LotteryTargetDao;
import com.cplatform.mall.back.lottery.entity.LotteryTarget;

/**
 * @Title
 * @Description
 * @Copyright: Copyright (c) 2013-7-23
 * @Company: 北京宽连十方数字技术有限公司
 * @Author chencheng
 * @Version: 1.0
 *
 */
@Service(value="lotteryTargetService")
public class LotteryTargetService {
	@Autowired
	private DbHelper dbHelper;
	
	@Autowired
	private LotteryTargetDao lotteryTargetDao;
	
	/**
	 * 保存抽奖目标对象
	 * @param lotteryTarget
	 */
	public void save(LotteryTarget lotteryTarget){
		lotteryTargetDao.save(lotteryTarget);
	}
	
	/**
	 * 根据活动id删除抽奖目标对象
	 * @param activeId
	 * @throws SQLException 
	 */
	public void deleteByActiveId(Long activeId) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("delete from t_lottery_target t ");
		sql.append(" where t.active_id =? ");
		dbHelper.execute(sql.toString(), new Object[]{activeId});
	}
	
	
	/**
	 * 根据活动id查询
	 * @param activeId
	 * @return
	 * @throws SQLException
	 */
	public List<LotteryTarget> findByActiveId(Long activeId) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("select t.* from t_lottery_target t ");
		sql.append(" where 1=1  ");
		List<Object> params = new ArrayList<Object>();
		if(activeId!=null){
			sql.append(" and t.active_id=? ");
			params.add(activeId);
		}
		sql.append(" order by t.id desc ");
		if(activeId==null){
			return dbHelper.getBeanList(sql.toString(), LotteryTarget.class, new Object[]{});
		}
		return dbHelper.getBeanList(sql.toString(), LotteryTarget.class, new Object[]{activeId});
	}
	

}
