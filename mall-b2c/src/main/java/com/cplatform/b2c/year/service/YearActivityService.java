package com.cplatform.b2c.year.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplatform.dbhelp.DbHelper;
import com.cplatform.dbhelp.page.ListPage;

@Service
public class YearActivityService {

	@Autowired
	DbHelper dbHelper;

	public ListPage<Map<String, Object>> findYearActivity(int page, int pageSize, String areaCode) throws SQLException {
		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();

		sql.append(" select tya.id, tya.sale_id,tya.goods_name,tya.goods_price,tya.image,tya.source,tya.price_type,tya.market_price,tya.goods_area_type,tygr.city_name ");

		sql.append(" from T_YEAR_ACTIVITY tya left join T_YEAR_GOODS_REGION tygr on tya.SALE_ID = tygr.sale_id  where 1=1");

		if (StringUtils.isNotEmpty(areaCode)) {
			sql.append(" and tygr.city_code = ?");
			params.add(areaCode);
		} else {
			sql.append(" and  tya.type =  0 ");
		}
		sql.append(" order by tya.create_time desc, tya.id");
		return dbHelper.getNativeMapPage(sql.toString(), page, pageSize, params.toArray());
	}

	/**
	 * 根据手机号码查归属地
	 * 
	 * @param terminalId
	 *            手机号码
	 * @return
	 * @throws SQLException
	 */
	public String queryAreaCode(String terminalId) throws SQLException {
		if (terminalId == null || terminalId.length() != 11) {
			return null;
		}
		String sql = "select area_code from t_sys_segment where segment_code = ?";
		String areaCode = dbHelper.queryScalar(sql, terminalId.substring(0, 7));
		return areaCode;
	}

}
