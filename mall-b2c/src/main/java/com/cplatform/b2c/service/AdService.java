package com.cplatform.b2c.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cplatform.b2c.util.AppConfig;
import com.cplatform.dbhelp.DbHelper;

/**
 * 广告相关类.
 * <p>
 * Copyright: Copyright (c) 2013-6-9 上午9:34:00
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author yangxm@c-platform.com
 */
@Service
@Transactional
public class AdService {

	private static final Log logger = LogFactory.getLog(AdService.class);

	public final static Map<String, String> AREA_REGION = new HashMap<String, String>();
	static {
		AREA_REGION.put("0519", "320400");
		AREA_REGION.put("0512", "320500");
		AREA_REGION.put("0513", "320600");
		AREA_REGION.put("0518", "320700");
		AREA_REGION.put("0517", "320800");
		AREA_REGION.put("0515", "320900");
		AREA_REGION.put("0514", "321000");
		AREA_REGION.put("0511", "321100");
		AREA_REGION.put("0523", "321200");
		AREA_REGION.put("0527", "321300");
		AREA_REGION.put("025", "320100");
		AREA_REGION.put("0510", "320200");
		AREA_REGION.put("0516", "320300");
	}

	@Autowired
	private DbHelper dbHelper;

	@Autowired
	private AppConfig appConfig;

	/**
	 * 获取广告显示数目
	 * 
	 * @param code
	 * @param position
	 * @param region_code
	 * @return
	 * @throws SQLException
	 */
	public Integer getAdNum(String code, String position, String region_code) throws SQLException {
		String getNumSql = "select search.code,search.num from (select ad.id,POS.CODE,pos.num from T_SYS_AD_POSITION pos,T_SYS_AD ad "
		        + " where ad.position_id=POS.ID and POS.CODE=? and POS.POSITION=? ) search, T_SYS_AD_REGION region"
		        + " where search.id = REGION.AD_ID and REGION.REGION_CODE=? and rownum=1";

		Object[] globalObj = new Object[] {};
		globalObj = ArrayUtils.add(globalObj, "global");// 广告代号
		globalObj = ArrayUtils.add(globalObj, position);// 显示位置
		globalObj = ArrayUtils.add(globalObj, region_code);// 地市
		List<String[]> numList = dbHelper.getArrayList(getNumSql, globalObj);
		if (numList != null && numList.size() > 0) {
			return Integer.parseInt(numList.get(0)[1]);
		}
		return null;

	}

	/**
	 * 获取广告json内容
	 * 
	 * @param code
	 * @return
	 * @throws SQLException
	 */
	public String getAdPositionList(String code, String position, String region_code) throws SQLException {
		// 改成dbhelper用sql语句写，直接关联ad，ad_position,ad_region表，并且获取golable默认广告
		// if(position.equals("item")){
		// region_code = AREA_REGION.get(region_code);
		// }
		Integer num = this.getAdNum(code, position, region_code);

		String sql = "select * from (select rownum no,ad.id,AD.AD_NAME,AD.AD_FLAG,AD.AD_TYPE,AD.LINK,AD.CONTENT,AD.STATUS from T_SYS_AD_POSITION pos,T_SYS_AD ad "
		        + "where ad.position_id=POS.ID and POS.CODE=? and POS.POSITION=? ) search, T_SYS_AD_REGION region "
		        + "where search.id = REGION.AD_ID and REGION.REGION_CODE=? and no<=?";
		Object[] params = new Object[] {};
		params = ArrayUtils.add(params, code);// 广告代号
		params = ArrayUtils.add(params, position);// 显示位置
		params = ArrayUtils.add(params, region_code);// 地市
		params = ArrayUtils.add(params, num);
		List<String[]> list = new ArrayList<String[]>();

		list = dbHelper.getArrayList(sql, params);
		// 如果配置的对应广告没有，则再次查询global广告
		if (list == null || list.size() <= 0) {
			Object[] globalObj = new Object[] {};
			globalObj = ArrayUtils.add(globalObj, "global");// 广告代号
			globalObj = ArrayUtils.add(globalObj, position);// 显示位置
			globalObj = ArrayUtils.add(globalObj, region_code);// 地市
			globalObj = ArrayUtils.add(globalObj, num);
			list = dbHelper.getArrayList(sql, globalObj);
		}

		String json = "{";
		if (list != null && list.size() > 0) {
			// 图片类型json
			String t_img = "\"t_img\":[";
			// 文字类型json
			String t_txt = "\"t_txt\":[";
			for (String[] adPosition : list) {
				// Ad_type==1是图片类型，2是文字类型
				if (adPosition[4].equals("1") && adPosition[7].equals("1")) {
					t_img += "{\"f\":\"" + adPosition[3] + "\",\"text\":\"" + adPosition[2] + "\",\"picurl\":\"" + appConfig.getWebRoot()
					        + appConfig.getB2c_ad_path() + adPosition[6] + "\",\"url\":\"" + adPosition[5] + "\"},";
				} else if (adPosition[4].equals("2") && adPosition[7].equals("1")) {
					t_txt += "{\"f\":\"" + adPosition[3] + "\",\"text\":\"" + adPosition[2] + "\",\"picurl\":\"" + adPosition[6] + "\",\"url\":\""
					        + adPosition[5] + "\"},";
				}
			}
			t_img += "],";
			t_txt += "]";
			json += t_img + t_txt;
		}
		json += "}";
		return json;
	}

	/**
	 * 获取广告code
	 * 
	 * @param position
	 * @param region_code
	 * @return
	 * @throws SQLException
	 */
	public List<String[]> getAdCode(String position, String region_code) throws SQLException {
		String sql = "select distinct search.code from (select ad.id,POS.CODE from T_SYS_AD_POSITION pos,T_SYS_AD ad "
		        + " where ad.position_id=POS.ID and POS.POSITION=? ) search, T_SYS_AD_REGION region"
		        + " where search.id = REGION.AD_ID and REGION.REGION_CODE=? ";
		Object[] params = new Object[] {};
		params = ArrayUtils.add(params, position);
		params = ArrayUtils.add(params, region_code);

		return dbHelper.getArrayList(sql, params);
	}

	/**
	 * 获取首页广告方法
	 * 
	 * @param position
	 * @param region_code
	 * @return
	 * @throws SQLException
	 */
	public Map<String, List<String[]>> getHomeAdList(String position, String region_code) throws SQLException {
		Map<String, List<String[]>> returnMap = new HashMap<String, List<String[]>>();
		List<String[]> codeList = this.getAdCode(position, region_code);
		if (codeList != null && codeList.size() > 0) {
			for (String[] code : codeList) {
				String sql = "select * from (select ad.id,AD.AD_NAME,AD.AD_TYPE,AD.LINK,AD.CONTENT from T_SYS_AD_POSITION pos,T_SYS_AD ad "
				        + "where ad.position_id=POS.ID and POS.CODE=? and POS.POSITION=? ) search, T_SYS_AD_REGION region "
				        + "where search.id = REGION.AD_ID and REGION.REGION_CODE=?";

				Object[] params = new Object[] {};
				params = ArrayUtils.add(params, code[0]);// 广告代号
				params = ArrayUtils.add(params, position);// 显示位置
				params = ArrayUtils.add(params, region_code);// 地市
				List<String[]> list = new ArrayList<String[]>();

				list = dbHelper.getArrayList(sql, params);
				// 如果配置的对应广告没有，则再次查询global广告
				if (list == null || list.size() <= 0) {
					Object[] globalObj = new Object[] {};
					globalObj = ArrayUtils.add(globalObj, "global");// 广告代号
					globalObj = ArrayUtils.add(globalObj, position);// 显示位置
					globalObj = ArrayUtils.add(globalObj, region_code);// 地市
					list = dbHelper.getArrayList(sql, globalObj);
				}

				if (list != null && list.size() > 0) {
					returnMap.put(code[0], list);
				}
			}
		}
		return returnMap;
	}
}
