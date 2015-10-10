package com.cplatform.mall.back.lottery.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplatform.dbhelp.DbHelper;
import com.cplatform.dbhelp.page.Page;
import com.cplatform.mall.back.lottery.dao.PrizeDao;
import com.cplatform.mall.back.lottery.entity.Prize;
import com.cplatform.mall.back.utils.data.RoleDataUtils;

@Service
public class PrizeService {
	@Autowired
	private DbHelper dbHelper;
	@Autowired
	private PrizeDao prizeDao;
	
	public void save(Prize prize){
		prizeDao.save(prize);
	}
	public Prize findById(Long id){
		Prize prize = prizeDao.findOne(id);
		return prize;
	}
	public void delete(Long id){
		prizeDao.delete(id);
	}
	public Page<Prize> findPrizeList(Prize prize, int pageNo, int pageSize) throws SQLException {
		StringBuilder sqlBuff = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sqlBuff.setLength(0);
		sqlBuff.append("select t.id id, ");
		sqlBuff.append("t.name name, ");
		sqlBuff.append("t1.name activeName, ");
		sqlBuff.append("t1.status status, ");
		sqlBuff.append("t.active_id activeId, ");
//		sqlBuff.append("t1.start_time startTime, ");
//		sqlBuff.append("t1.stop_time stopTime, ");
		sqlBuff.append("t.numbers  numbers, ");
		sqlBuff.append("t.hit_probability hitProbability ");
		sqlBuff.append("from t_lottery_prize t ");
		sqlBuff.append("join t_lottery_active t1 ");
		sqlBuff.append("on t.active_id = t1.id ");
		sqlBuff.append("where 1 = 1");
		if (StringUtils.isNotEmpty(prize.getName())) {
			sqlBuff.append(" and t.name = ? ");
			params.add(prize.getName().trim());
		}
		if ( null != prize.getId() ){
			sqlBuff.append(" and t.id = ? ");
			params.add(prize.getId());
		}
		if (StringUtils.isNotEmpty(prize.getBeginTime())) {
			sqlBuff.append(" and t1.create_time > ?  ");
			params.add(prize.getBeginTime()+"000000");
		}
		if (StringUtils.isNotEmpty(prize.getEndTime())) {
			sqlBuff.append(" and t1.create_time < ?  ");
			params.add(prize.getEndTime()+"000000");
		}
		if (null != prize.getStatus()){
			sqlBuff.append(" and t1.status = ?  ");
			params.add(prize.getStatus());
		}
		sqlBuff.append(RoleDataUtils.getRoleData(RoleDataUtils.MODULE_LOTTERY));
		sqlBuff.append("order by t.id desc ");
		Page<Prize> pageData = dbHelper.getPage(sqlBuff.toString(), Prize.class, pageNo, pageSize, params.toArray());
		return pageData;
	} 
	public String hitLevelIsAdd(Long activeId,Long hitLevel) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from t_lottery_prize t where t.active_id = ? and t.hit_level = ? ");
		Prize prize = dbHelper.getBean(sql.toString(), Prize.class, new Object[]{activeId,hitLevel});
		if(prize == null){
			return "is";
		}else{
			return "not";
		}
	}
	
}
