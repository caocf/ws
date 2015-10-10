package com.cplatform.mall.back.store.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cplatform.dbhelp.DbHelper;
import com.cplatform.dbhelp.page.Page;
import com.cplatform.mall.back.store.dao.StoreSettleDao;
import com.cplatform.mall.back.store.entity.StoreSettle;
import com.cplatform.mall.back.utils.data.RoleDataUtils;

/**
 * 结算管理 Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-7-9 下午7:07:38
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: LiuDong@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
@Service
public class StoreSettleService {

	@Autowired
	private DbHelper dbHelper;

	@Autowired
	private StoreSettleDao settleDao;

	public Page<StoreSettle> listStoreSettle(StoreSettle vo, int pageNo, int pagesize) throws SQLException {
		StringBuilder sqlBuff = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sqlBuff.setLength(0);
		sqlBuff.append("select t.*, store.name ");
		sqlBuff.append("  from t_store_settle t ");
		sqlBuff.append("  left join t_store store ");
		sqlBuff.append("    on t.store_id = store.id ");
		sqlBuff.append(" where 1 = 1 and store.sort = 1 and store.is_valid = 1 ");
		if (vo.getStoreId() != null) {
			sqlBuff.append(" and t.store_id = ? ");
			params.add(vo.getStoreId());
		}
		if (StringUtils.isNotEmpty(vo.getName())) {
			sqlBuff.append(" and store.name like ? ");
			params.add("%" + vo.getName() .trim()+ "%");
		}
		if (vo.getId() != null) {
			sqlBuff.append(" and t.id = ? ");
			params.add(vo.getId());
		}
		if (vo.getStatus() != null) {
			sqlBuff.append(" and t.status = ? ");
			params.add(vo.getStatus());
		}
		if (vo.getSyncGyFlag() != null) {
			sqlBuff.append(" and t.sync_gy_flag = ? ");
			params.add(vo.getSyncGyFlag());
		}

		sqlBuff.append(RoleDataUtils.getRoleData(RoleDataUtils.MODULE_STORE));

		sqlBuff.append(" order by t.id desc");
		return dbHelper.getPage(sqlBuff.toString(), StoreSettle.class, pageNo, pagesize, params.toArray());
	}

	@Transactional
	public void auditSettle(StoreSettle vo) {
		this.settleDao.save(vo);

	}

	/**
	 * 查询指定商户结算信息
	 * 
	 * @param id
	 */
	@Transactional
	public StoreSettle findOneStoreSettle(Long id) {
		StoreSettle storeSettle = settleDao.findOne(id);
		return storeSettle;
	}

	/**
	 * 判断结算资料的生效时间与失效时间是否有效
	 * 
	 * @param vo
	 * @return
	 * @throws SQLException
	 */
	public boolean dateIsValid(StoreSettle vo) throws SQLException {
		StringBuilder sqlBuff = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sqlBuff.setLength(0);
		sqlBuff.append("select count(*) ");
		sqlBuff.append("  from t_store_settle t ");
		sqlBuff.append(" where 1 =1 ");
		if (vo.getId() != null) {
			sqlBuff.append(" and t.id != ? ");
			params.add(vo.getId());
		}
		if (vo.getStoreId() != null) {
			sqlBuff.append(" and t.store_id = ? ");
			params.add(vo.getStoreId());
		}
		sqlBuff.append("   and ((t.effort_date <= ? and t.expiry_date >= ? ) or ");
		params.add(vo.getEffortDate());
		params.add(vo.getEffortDate());
		sqlBuff.append("       (t.effort_date <= ? and t.expiry_date >= ? )  ");
		params.add(vo.getExpiryDate());
		params.add(vo.getExpiryDate());
		if (vo.getId() == null) {
			sqlBuff.append("     or  t.effort_date >= ? ");
			params.add(vo.getExpiryDate());
		}
		sqlBuff.append(")");
		String value = this.dbHelper.queryScalar(sqlBuff.toString(), params.toArray());
		return Integer.valueOf(value) == 0;
	}
}
