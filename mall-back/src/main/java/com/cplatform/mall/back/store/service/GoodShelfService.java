package com.cplatform.mall.back.store.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplatform.dbhelp.DbHelper;
import com.cplatform.mall.back.store.entity.GoodsShelf;

@Service
public class GoodShelfService {

	@Autowired
	DbHelper dbHelper;

	public List<GoodsShelf> getTreeGoodShelf(Long shopId, Long pid) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("select t.*,  decode(connect_by_isleaf,'1','0','0','1') is_Parent ");
		sql.append("  from t_good_shelf t ");
		sql.append(" where shop_id = ? ");
		sql.append(" start with pid = 0 ");
		sql.append("connect by pid = prior id");

		return dbHelper.getBeanList(sql.toString(), GoodsShelf.class, new Object[] { shopId });
	}
}
