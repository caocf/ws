package com.cplatform.b2c.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplatform.b2c.repository.TItemParamDao;

/**
 * 商品属性. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2014-1-15 下午3:05:14
 * <p>
 * Company: 苏州宽连信息技术有限公司
 * <p>
 * 
 * @author zhangdong-ca@c-platform.com
 * @version 1.0.0
 */
@Service
public class TItemParamService {

	@Autowired
	private TItemParamDao tItemParamDao;

	/**
	 * 根据商品编号查询出商品属性
	 * 
	 * @param itemId
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String, String>> findItemParamByItemId(Long itemId) throws SQLException {
		return tItemParamDao.findItemParamByItemId(itemId);

	}

	/**
	 * 将商品属性改为String
	 * 
	 * @param paramMap
	 * @return
	 */
	public String getItemParamToString(List<Map<String, String>> list) {
		StringBuilder builder = null;
		if (list != null && list.size() > 0) {
			builder = new StringBuilder();
			builder.setLength(0);
			for (Map<String, String> param : list) {
				if (param != null && !param.isEmpty()) {
					builder.append(param.get("PARAM_KEY")).append(":")
					        .append((StringUtils.isNotBlank(param.get("LABEL"))) ? param.get("LABEL") : param.get("PARAM_VALUE"));
				}
			}
			return builder.toString();
		}
		return null;
	}

	/**
	 * 根据商品编号查询出商品属性，再将商品属性改为String
	 * 
	 * @param itemId
	 * @return
	 * @throws SQLException
	 */
	public String getItemParamToString(Long itemId) throws SQLException {
		List<Map<String, String>> list = tItemParamDao.findItemParamByItemId(itemId);
		StringBuilder builder = null;
		if (list != null && list.size() > 0) {
			builder = new StringBuilder();
			builder.setLength(0);
			for (Map<String, String> param : list) {
				if (param != null && !param.isEmpty()) {
					builder.append(param.get("PARAM_KEY")).append(":")
					        .append((StringUtils.isNotBlank(param.get("LABEL"))) ? param.get("LABEL") : param.get("PARAM_VALUE")).append(";");
				}
			}
			return builder.toString();
		}
		return null;
	}
}
