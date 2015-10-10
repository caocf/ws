package com.cplatform.b2c.repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cplatform.dbhelp.DbHelper;

/**
 * 商品 属性. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2014-1-15 下午2:45:15
 * <p>
 * Company: 苏州宽连信息技术有限公司
 * <p>
 * 
 * @author zhangdong-ca@c-platform.com
 * @version 1.0.0
 */
@Repository
public class TItemParamDao {

	@Autowired
	private DbHelper dbHelper;

	/**
	 * 根据商品编号查找出商品属性
	 * 
	 * @param itemId
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String, String>> findItemParamByItemId(Long itemId) throws SQLException {
		if (itemId == null) {
			return null;
		}
		String sql = "SELECT * FROM T_ITEM_PARAM itemParam WHERE itemParam.ITEM_ID = ? ";
		return dbHelper.getMapList(sql, itemId);
	}
}
