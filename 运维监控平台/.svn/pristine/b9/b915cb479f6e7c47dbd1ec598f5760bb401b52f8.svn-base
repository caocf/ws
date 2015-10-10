package com.cplatform.mall.dc.utils;

import java.util.Calendar;

import javax.servlet.jsp.tagext.TagSupport;

/**
 * 日期标签生成类
 * 
 * @author zhuangxx@c-platform.com
 * @since 2013-08-07
 */
public class DateTag extends TagSupport {
	private static final long serialVersionUID = 4755542739842442904L;

	/**
	 * 获取年份下拉框脚本
	 * 
	 * @param selectedYear
	 *            选择的年份
	 * @return 年份下拉框脚本
	 */
	public static StringBuilder getYearTag(Object selectedYear) {
		StringBuilder tagStr = new StringBuilder("<select id=\"year\" name=\"year\">");
		Calendar c = Calendar.getInstance();
		int currentYear = c.get(Calendar.YEAR);
		for (int i = currentYear - 3; i <= currentYear + 3; i++) {
			if (selectedYear != null && String.valueOf(i).equals(String.valueOf(selectedYear))) {
				tagStr.append("<option value=\"").append(i).append("\" selected=\"selected\">").append(i).append("年").append("</option>");
			} else {
				tagStr.append("<option value=\"").append(i).append("\">").append(i).append("年").append("</option>");
			}
		}
		tagStr.append("</select>");

		return tagStr;
	}

	/**
	 * 获取月份下拉框脚本
	 * 
	 * @param selectedMonth
	 *            选择的月份
	 * @return 月份下拉框脚本
	 */
	public static StringBuilder getMonthTag(Object selectedMonth) {
		StringBuilder tagStr = new StringBuilder("<select id=\"month\" name=\"month\">");
		for (int i = 1; i <= 12; i++) {
			String month = String.valueOf(i);
			if (i <= 9) {
				month = "0" + i;
			}
			if (selectedMonth != null && String.valueOf(month).equals(String.valueOf(selectedMonth))) {
				tagStr.append("<option value=\"").append(month).append("\" selected=\"selected\">").append(month).append("月").append("</option>");
			} else {
				tagStr.append("<option value=\"").append(month).append("\">").append(month).append("月").append("</option>");
			}
		}
		tagStr.append("</select>");

		return tagStr;
	}
}
