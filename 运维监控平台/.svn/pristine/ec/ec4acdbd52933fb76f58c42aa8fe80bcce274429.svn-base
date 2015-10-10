package com.cplatform.mall.dc.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * SQL辅助类
 * 
 * @author zhuangxx@c-platform.com
 * @since 2013-8-2
 */
public class SQLHelper {

	/**
	 * 创建SQL语句
	 * 
	 * @param fieldCount
	 *            字段数量
	 * @param ths
	 *            标题数组
	 * @param tables
	 *            表名数组
	 * @param areaStr
	 *            地区权限
	 * @param startDate
	 *            开始日期
	 * @param endDate
	 *            结束日期
	 * @return SQL语句
	 */
	public static String createSQL(int fieldCount, String[] ths, String[] tables, String areaStr, String startDate, String endDate) {
		String fieldStr = createFieldStr(fieldCount);
		return createSQL(fieldStr, ths, tables, areaStr, startDate, endDate);
	}

	/**
	 * 创建SQL语句
	 * 
	 * @param fieldStr
	 *            查询字段
	 * @param ths
	 *            标题数组
	 * @param tables
	 *            表名数组
	 * @param areaStr
	 *            地区权限
	 * @param startDate
	 *            开始日期
	 * @param endDate
	 *            结束日期
	 * @return SQL语句
	 */
	public static String createSQL(String fieldStr, String[] ths, String[] tables, String areaStr, String startDate, String endDate) {
		if (areaStr == null || "".equals(areaStr)) {
			areaStr = AreaAuthority.getAreaStr();
		}

		StringBuilder sql = new StringBuilder();
		for (int i = 0; i < tables.length; i++) {
			sql.append(createSql(fieldStr, areaStr, ths[i], tables[i], startDate, endDate));
			if (i < tables.length - 1) {
				sql.append(" union all ");
			}
		}
		return sql.toString();
	}
	
	public static String createSQL(int fieldCount, String[] ths, String[] tables, String areaStr, String startDate,
			String endDate, String payType) {
		String fieldStr = SQLHelper.createFieldStr(fieldCount);
		if (areaStr == null || "".equals(areaStr)) {
			areaStr = AreaAuthority.getAreaStr();
		}

		StringBuilder sql = new StringBuilder();
		for (int i = 0; i < tables.length; i++) {
			sql.append(createSql(fieldStr, areaStr, ths[i], tables[i], startDate, endDate, payType));
			if (i < tables.length - 1) {
				sql.append(" union all ");
			}
		}
		return sql.toString();
	}


	/**
	 * 创建查询语句
	 * 
	 * @param fieldCount
	 *            字段数量
	 * @return 形如", NVL(sum(t.cnt1), 0) CNT1"的SQL语句
	 */
	private static String createFieldStr(int fieldCount) {
		StringBuilder sb = new StringBuilder();
		for (int j = 1; j <= fieldCount; j++) {
			sb.append(", NVL(sum(cnt").append(j).append("), null) CNT").append(j);
		}
		return sb.toString();
	}

	/**
	 * 创建子SQL语句
	 * 
	 * @param fieldStr
	 *            查询字段
	 * @param areaStr
	 *            地区权限
	 * @param titleName
	 *            标题名
	 * @param tableName
	 *            表名
	 * @param startDate
	 *            开始日期
	 * @param endDate
	 *            结束日期
	 * @return 子SQL语句
	 */
	private static String createSql(String fieldStr, String areaStr, String titleName, String tableName, String startDate, String endDate) {
		StringBuilder sb = new StringBuilder();

		sb.append("select '").append(titleName).append("' TH").append(fieldStr).append(" from ").append(tableName).append(" where area_code in (").append(areaStr).append(") and sdate >= '")
				.append(startDate).append("' and sdate <= '").append(endDate).append("'");
		return sb.toString();
	}
	
	private static String createSql(String fieldStr, String areaStr, String titleName, String tableName,
			String startDate, String endDate, String payType) {
		StringBuilder sb = new StringBuilder();

		sb.append("select '").append(titleName).append("' TH").append(fieldStr).append(" from ").append(tableName)
				.append(" where area_code in (").append(areaStr).append(") and sdate >= '").append(startDate)
				.append("' and sdate <= '").append(endDate).append("'");
		if(StringUtils.isNotBlank(payType)){
			sb.append(" and PAY_TYPE = '").append(payType).append("' ");
		}
		return sb.toString();
	}
}
