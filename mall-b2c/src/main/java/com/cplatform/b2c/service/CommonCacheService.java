package com.cplatform.b2c.service;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.cplatform.dbhelp.DbHelper;

/**
 * Title. <br>
 * Description.
 * <p/>
 * Copyright: Copyright (c) 13-6-17 下午5:08
 * <p/>
 * Company: 北京宽连十方数字技术有限公司
 * <p/>
 * Author: nicky
 * <p/>
 * Version: 1.0
 * <p/>
 */
@Service
public class CommonCacheService {

	@Autowired
	DbHelper dbHelper;

	@CacheEvict(value = { "sys_region", "sys_logistics", "sys_invoice", "sys_region_spell" }, allEntries = true)
	public void cacheEvict() {
	}

	/**
	 * 根据地区编码获取地区全名
	 * 
	 * @param regionCode
	 * @return
	 * @throws SQLException
	 */
	@Cacheable(value = "sys_region", key = "#regionCode")
	public String getRegionName(String regionCode) {
		try {
			String sql = "select region_name from t_sys_region where region_code = ?";
			return dbHelper.queryScalar(sql, regionCode);
		}
		catch (SQLException ex) {
			// rethrow runtimeException
			throw new RuntimeException(ex);
		}
	}

	@Cacheable(value = "sys_region_spell", key = "#regionCode")
	public String getRegionSpell(String regionCode) {
		try {
			String sql = "select region_spell from t_sys_region where region_code = ?";
			return dbHelper.queryScalar(sql, regionCode);
		}
		catch (SQLException ex) {
			// rethrow runtimeException
			throw new RuntimeException(ex);
		}
	}

	@Cacheable(value = "sys_region_spell", key = "#areaCode.concat(#regionLevel)")
	public String getRegionSpellByAreaCode(String areaCode, String regionLevel) {
		try {
			String sql = "select region_spell from t_sys_region where area_code = ? and region_level = ?";
			return dbHelper.queryScalar(sql, areaCode, regionLevel);
		}
		catch (SQLException ex) {
			// rethrow runtimeException
			throw new RuntimeException(ex);
		}
	}

	@Cacheable(value = "sys_region_areacode", key = "#regionCode")
	public String getRegionAreaCode(String regionCode) {
		try {
			String sql = "select area_code from t_sys_region where region_code = ?";
			return dbHelper.queryScalar(sql, regionCode);
		}
		catch (SQLException ex) {
			// rethrow runtimeException
			throw new RuntimeException(ex);
		}
	}

	@Cacheable(value = "sys_region_child_list", key = "#parentRegion")
	public List<String[]> getChildRegionList(String parentRegion) {
		try {
			String sql = "select region_code from t_sys_region where parent_region=?";
			Object[] params = new Object[] {};
			params = ArrayUtils.add(params, parentRegion);
			return dbHelper.getArrayList(sql, params);
		}
		catch (SQLException ex) {
			// rethrow runtimeException
			throw new RuntimeException(ex);
		}
	}

	@Cacheable(value = "sys_province_region_list", key = "#parentRegion")
	public List<String[]> getProvinceRegionList(String parentRegion) {
		try {
			String sql = "select region_code from t_sys_region where parent_region=? and region_code<>320000 ";
			Object[] params = new Object[] {};
			params = ArrayUtils.add(params, parentRegion);
			return dbHelper.getArrayList(sql, params);
		}
		catch (SQLException ex) {
			// rethrow runtimeException
			throw new RuntimeException(ex);
		}
	}

	/**
	 * 物流公司信息
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	@Cacheable(value = "sys_logistics", key = "#id")
	public String getLogisticsName(String id) throws SQLException {
		String sql = "select name from t_sys_logistics where id = ? and is_valid = 1";
		return dbHelper.queryScalar(sql, id);
	}

	/**
	 * 发票内容
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	@Cacheable(value = "sys_invoice", key = "#id")
	public String getInvoiceName(String id) throws SQLException {
		String sql = "select name from t_sys_invoice where id = ?";
		return dbHelper.queryScalar(sql, id);
	}

	/**
	 * 获取热门关键词
	 * 
	 * @param moduleCode
	 * @return
	 */
	@Cacheable(value = "page_module_data", key = "#moduleCode")
	public List<Map<String, String>> getHotTopic(String moduleCode) {
		List<Map<String, String>> hotTopicMap = new ArrayList<Map<String, String>>();
		try {
			StringBuilder sqlBuff = new StringBuilder(200);
			sqlBuff.append("select content ");
			sqlBuff.append("  from (select content, row_number() over(order by sort_no) row_number ");
			sqlBuff.append("          from t_page_module_data");
			sqlBuff.append("         where module_code = ?");
			sqlBuff.append("           and status = 1)");
			sqlBuff.append(" where row_number <= 10"); // 按sort_no排序，取前10个
			List<String[]> hotTopicList = dbHelper.getArrayList(sqlBuff.toString(), moduleCode);
			if (hotTopicList != null && !hotTopicList.isEmpty()) {
				JSONObject json = null;
				Map<String, String> map = null;
				for (String[] hotTopic : hotTopicList) {
					json = JSONObject.fromObject(hotTopic[0]);
					map = new HashMap<String, String>();
					map.put("title", json.getString("title"));
					map.put("href", json.getString("href"));
					hotTopicMap.add(map);
				}
			}
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
		return hotTopicMap;
	}

	/**
	 * 获取某个地市所辖的区域列表
	 * 
	 * @param regionCode
	 * @return
	 */
	@Cacheable(value = "area_region_list", key = "#regionCode")
	public List<String[]> getRegionListByRegionCode(String regionCode) {
		try {
			StringBuilder sqlBuff = new StringBuilder(200);
			sqlBuff.append("select region_code, region_name ");
			sqlBuff.append("  from t_sys_region ");
			sqlBuff.append(" where parent_region = ?");
			sqlBuff.append("   and substr(region_code, length(region_code) - 1, 2) <> '01'"); // 过滤掉辖区
			sqlBuff.append("   and is_show = 1 "); // 是否显示 0-不显示，1-显示
			sqlBuff.append(" order by sort_num ");
			Object[] params = new Object[] { regionCode };
			return dbHelper.getArrayList(sqlBuff.toString(), params);
		}
		catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
	}

	/**
	 * 获取某个商品分类下的所有子分类
	 * 
	 * @param parentTypeId
	 * @return
	 */
	@Cacheable(value = "sys_type_list", key = "#parentTypeId")
	public List<String[]> getSysTypeByPId(String parentTypeId) {
		// 此处发现可注入，加上判断条件。
		if (parentTypeId != null && !StringUtils.isNumeric(parentTypeId)) {
			return null;
		}
		try {
			StringBuilder sqlBuff = new StringBuilder(200);
			sqlBuff.append("select id, name ");
			sqlBuff.append("  from t_sys_type ");
			sqlBuff.append(" where p_id = ? ");
			sqlBuff.append("   and is_valid = 1"); // 取有效的分类
			Object[] params = new Object[] { parentTypeId };
			return dbHelper.getArrayList(sqlBuff.toString(), params);
		}
		catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
	}

	/**
	 * 根据regionCode获取信息
	 * 
	 * @param regionCode
	 * @return
	 */
	@Cacheable(value = "sys_region_map", key = "#regionCode+'findRegionByRegionCode'")
	public Map<String, String> getRegionByRegionCode(String regionCode) {
		try {
			String sql = "SELECT * FROM t_sys_region WHERE region_code = ?";
			return dbHelper.getMap(sql, regionCode);
		}
		catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
	}

	/**
	 * 绑定邮箱时，发送邮件的内容
	 * 
	 * @return
	 * @throws IOException
	 */
	@Cacheable(value = "'bindEmailContent'", key = "'bindEmail'")
	public String getBindEmailTemplateText() throws IOException {
		InputStream is = getClass().getClassLoader().getResourceAsStream("template/bind-email.txt");
		return IOUtils.toString(is, "utf-8");
	}

	/**
	 * 修改邮箱时，发送邮件的内容
	 * 
	 * @return
	 * @throws IOException
	 */
	@Cacheable(value = "'editEmailContent'", key = "'editEmail'")
	public String getEditEmailTemplateText() throws IOException {
		InputStream is = getClass().getClassLoader().getResourceAsStream("template/edit-email.txt");
		return IOUtils.toString(is, "utf-8");
	}

}
