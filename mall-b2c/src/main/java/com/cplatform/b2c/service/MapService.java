package com.cplatform.b2c.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cplatform.b2c.model.TShop;
import com.cplatform.b2c.repository.TMapDao;

/**
 * 地图操作类
 * <p>
 * Copyright: Copyright (c) 2013-6-1 上午10:12:16
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author 季霁
 * @version 1.0.0
 */
@Service
@Transactional
public class MapService {

	@Autowired
	private TMapDao tMapDao;

	public List<TShop> getMapInfo(Integer[] id) {
		return tMapDao.getMapInfo(id);
	}

	/**
	 * 根据商品编号进行验证是否输入验证门店
	 * 
	 * @param itemId
	 *            商品编号
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String, String>> verifyShop(Long itemId) throws SQLException {
		return tMapDao.verifyShop(itemId);
	}

}
