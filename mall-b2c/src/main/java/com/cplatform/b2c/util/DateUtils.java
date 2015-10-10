package com.cplatform.b2c.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

/**
 * 日期工具类. <br>
 * 获取格式化日期的工具类. Copyright: Copyright (c) 2013-08-05
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: lisong
 * <p>
 * Version: 1.0
 */
@Component
public class DateUtils {

	public static final String FORMAT_DEFAULT = "yyyy-MM-dd HH:mm";

	public static Timestamp getCurrentTimeStamp() throws Exception {
		setTimeZone();
		Calendar c = GregorianCalendar.getInstance();
		return new Timestamp(c.getTimeInMillis());
	}

	public static Timestamp getCurrentTimeStamp2() throws Exception {
		String now = formatTimestamp(getCurrentTimeStamp(), "yyyy-MM-dd HH:mm:ss.SSS");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Date dd = sdf.parse(now);
		return new Timestamp(dd.getTime());
	}

	public static void setTimeZone() {
		TimeZone time = TimeZone.getTimeZone("GMT+8"); // 设置为东八区
		TimeZone.setDefault(time);// 设置时区
	}

	public static String getCurrentDateString() throws Exception {
		SimpleDateFormat sdfDateTime = new SimpleDateFormat("yyyy-MM-dd");
		return sdfDateTime.format(getCurrentTimeStamp());
	}

	public static String getCurrentTimeStampString() throws Exception {
		SimpleDateFormat sdfDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdfDateTime.format(getCurrentTimeStamp());
	}

	public static String getCurrentTimeStampString2() throws Exception {
		SimpleDateFormat sdfDateTime = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return sdfDateTime.format(getCurrentTimeStamp());
	}

	public static String getCurrentTimeStampString4() throws Exception {
		SimpleDateFormat sdfDateTime = new SimpleDateFormat("yyyyMMddHHmmss");
		return sdfDateTime.format(getCurrentTimeStamp());
	}

	public static String getCurrentTimeStampString3() throws Exception {
		SimpleDateFormat sdfDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		return sdfDateTime.format(getCurrentTimeStamp());
	}

	public static String formatTimestamp(Date d, String format) throws Exception {
		if (StringUtils.isEmpty(format)) {
			format = FORMAT_DEFAULT;
		}
		setTimeZone();
		SimpleDateFormat sdfDateTime = new SimpleDateFormat(format);
		// sdfDateTime.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
		return sdfDateTime.format(d);
	}

	/**
	 * 将JDBC日期形式的字符串转换成日期形式。
	 * 
	 * @param dateString
	 *            JDBC日期形式的字符串（yyyy-MM-dd);
	 * @return 日期：Jun 10, 2011
	 */
	public static java.sql.Date getDateByString(String dateString) {
		if (dateString == null || "".equals(dateString)) {
			return (java.sql.Date) new java.util.Date();
		}
		java.sql.Date date = java.sql.Date.valueOf(dateString);
		return date;
	}

	/**
	 * 将日期格式 2011-03-09格式转换成2011年3月9日
	 * 
	 * @param date
	 * @return 日期：Jun 13, 2011
	 */
	public static String transformChinaDateform(java.sql.Date date) {

		if (date == null) {
			return "";
		}

		String dateString = date.toString();
		String[] dateStrings = dateString.split("-");

		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(dateStrings[0]);
		stringBuilder.append("年");
		stringBuilder.append(dateStrings[1]);
		stringBuilder.append("月");
		stringBuilder.append(dateStrings[2]);
		stringBuilder.append("日");

		return stringBuilder.toString();

	}

	/**
	 * 字符串转日期格式
	 * 
	 * @param date
	 * @return
	 */
	public static Date formart(String date) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date d = null;
		try {
			d = df.parse(date);
		}
		catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return d;
	}

	/***
	 * yyyy-MM-dd日期转化
	 * 
	 * @param date
	 * @return
	 */
	public static String formartString(Date date) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(date);
	}

	/**
	 * HH:mm转化
	 * 
	 * @param date
	 * @return
	 */
	public static Date formartH(String date) {
		DateFormat df = new SimpleDateFormat("HH:mm");
		Date d = null;
		try {
			d = df.parse(date);
		}
		catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return d;
	}

	/**
	 * HH:mm:ss 转换
	 * 
	 * @param date
	 * @return
	 */
	public static String formartStringH(Date date) {
		DateFormat df = new SimpleDateFormat("HH:mm:ss");
		return df.format(date);
	}

	/**
	 * 结束时间+延时时间
	 */
	public static String endTime(String startTime, String delayTime) {
		Date date = formartH(startTime);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MINUTE, Integer.parseInt(delayTime));
		return formartStringH(c.getTime());
	}

	/**
	 * 将日期形式的字符串转换成日期形式。
	 * 
	 * @param dateString
	 *            日期形式的字符串（yyyy-MM-dd HH:mm:ss);
	 * @return 日期：Jun 10, 2011
	 * @throws ParseException
	 */
	public static Date parseDateByString(String dateString) throws ParseException {
		if (dateString == null || "".equals(dateString)) {
			return new Date();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = sdf.parse(dateString);
		return date;
	}

	/**
	 * yyyyMMddHHmmss
	 * 
	 * @param date
	 * @return
	 */
	public static Date fromToDate(String data) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = null;
		try {
			date = sdf.parse(data);
		}
		catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}

	public static String fromToString(String data) {
		Date d = fromToDate(data);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(d);
	}

	public static String fromStringToString(String dateString) throws ParseException {
		Date date = parseDateByString(dateString);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(date);
	}

	public static String transformChinaDate(Date date) {

		if (date == null) {
			return "";
		}
		setTimeZone();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
		return sdf.format(date);
	}
}
