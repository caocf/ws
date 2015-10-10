package com.cplatform.b2c.util;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * Title: Time
 * </p>
 * <p>
 * Description:
 * </p>
 * 此类主要用来取得本地系统的系统时间并用下面5种格式显示 1. YYMMDDHH 8位 2. YYMMDDHHmm 10位 3. YYMMDDHHmmss
 * 12位 4. YYYYMMDDHHmmss 14位 5. YYMMDDHHmmssxxx 15位 (最后的xxx 是毫秒)
 * <p>
 * Copyright: Copyright (c) 2003
 * </p>
 * <p>
 * Company: xgll
 * </p>
 * 
 * @author lqf
 * @version 1.0
 */
public class CTime {

	public static final int YYMMDDhh = 8;

	public static final int YYMMDDhhmm = 10;

	public static final int YYMMDDhhmmss = 12;

	public static final int YYMMDDhhmmssxxx = 15;

	public static final int YYYYMMDDhhmmss = 14;

	/**
	 * 给定开始时间，得到n天后的日期
	 * 
	 * @param s
	 * @param n
	 * @return
	 * @throws ParseException
	 */
	public static String addDay(String s, int n) {
		String retime = "";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			Calendar cd = Calendar.getInstance();
			cd.setTime(sdf.parse(s));
			cd.add(Calendar.DATE, n);
			retime = sdf.format(cd.getTime());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return retime;
	}

	/**
	 * 检验当前时间是否是 每月25之后到月底18：00之前
	 * 
	 * @return true:是;false:否
	 */
	public static boolean checkTimeAfter25() {
		return checkTimeAfter25(new Date());
	}

	/**
	 * 检验当前时间是否是 每月25之后到月底18：00之前
	 * 
	 * @return true:是;false:否
	 */
	@SuppressWarnings("deprecation")
	public static boolean checkTimeAfter25(Date date) {
		Date sDate = new Date(date.getYear(), date.getMonth(), 26);
		Date eDate = new Date(date.getYear(), date.getMonth() + 1, 1);
		eDate.setTime(eDate.getTime() - 6 * 60 * 60 * 1000);
		return date.getTime() >= sDate.getTime() && date.getTime() < eDate.getTime();
	}

	/**
	 * 将数据库格式时间转换为js日历空间中的时间格式 例如：20060922 -〉2006-09-22
	 */
	public static String DbToJs(String time) {
		if (time == null) {
			return "";
		}
		int len = time.trim().length();
		StringBuffer ret = new StringBuffer();
		switch (len) {
			case 6:
				ret.append("20").append(time.substring(0, 2));
				ret.append("-").append(time.substring(2, 4));
				ret.append("-").append(time.substring(4, 6));
				break;
			case 15:
				ret.append("20").append(time.substring(0, 2));
				ret.append("-").append(time.substring(2, 4));
				ret.append("-").append(time.substring(4, 6));
				break;
			case 8:
				ret.append(time.substring(0, 4));
				ret.append("-").append(time.substring(4, 6));
				ret.append("-").append(time.substring(6, 8));
				break;
			case 14:
				ret.append(time.substring(0, 4));
				ret.append("-").append(time.substring(4, 6));
				ret.append("-").append(time.substring(6, 8));
				ret.append(" ").append(time.substring(8, 10));
				ret.append(":").append(time.substring(10, 12));
				break;
			default:
				ret.append(time);
		}
		return ret.toString();
	}

	/*
	 * #本函数的主要功能是格式化时间，以便于页面显示 #time 时间 可为6位、8位、12位、15位 #return 返回格式化后的时间 #6位
	 * YY年MM月DD日 #8位 YYYY年MM月DD日 #12位 YY年MM月DD日 HH:II:SS #15位 YY年MM月DD日
	 * HH:II:SS:CCC
	 */
	public static String formattime(String time) {
		int length = 0;
		if (time == null || time.length() < 6) {
			return "";
		}
		length = time.length();
		String renstr = "";
		switch (length) {
			case 6:
				renstr = time.substring(0, 2) + "-" + time.substring(2, 4) + "-" + time.substring(4) + "";
				break;
			case 8:
				renstr = time.substring(0, 4) + "-" + time.substring(4, 6) + "-" + time.substring(6, 8) + "";
				break;
			case 12:
				renstr = time.substring(0, 2) + "-" + time.substring(2, 4) + "-" + time.substring(4, 6) + " " + time.substring(6, 8) + ":"
				        + time.substring(8, 10) + ":" + time.substring(10, 12) + "";
				break;
			case 14:
				renstr = time.substring(0, 4) + "-" + time.substring(4, 6) + "-" + time.substring(6, 8) + " " + time.substring(8, 10) + ":"
				        + time.substring(10, 12) + ":" + time.substring(12, 14) + "";
				break;
			case 15:
				renstr = time.substring(0, 2) + "-" + time.substring(2, 4) + "-" + time.substring(4, 6) + " " + time.substring(6, 8) + ":"
				        + time.substring(8, 10) + ":" + time.substring(10, 12) + ":" + time.substring(12);
				break;
			default:
				renstr = time.substring(0, 2) + "-" + time.substring(2, 4) + "-" + time.substring(4) + "";
				break;
		}
		return renstr;
	}

	/*
	 * #本函数的主要功能是格式化时间，以便于页面显示 #time 时间 可为6位、8位、12位、15位 #return 返回格式化后的时间 #6位
	 * YY年MM月DD日 #8位 YYYY年MM月DD日 #12位 YY年MM月DD日 HH:II:SS #15位 YY年MM月DD日
	 * HH:II:SS:CCC
	 */
	public static String formattime2(String time) {
		int length = 0;
		if (time == null || time.length() < 6) {
			return "";
		}
		length = time.length();
		String renstr = "";
		switch (length) {
			case 6:
				renstr = time.substring(0, 4) + "年" + time.substring(4, 6) + "月";
				break;
			case 8:
				renstr = time.substring(0, 4) + "年" + time.substring(4, 6) + "月" + time.substring(6, 8) + "日";
				break;
			case 10:
				renstr = time.substring(0, 4) + "年" + time.substring(4, 6) + "月" + time.substring(6, 8) + "日" + time.substring(8, 10) + "时";
				break;
			case 12:
				renstr = time.substring(0, 4) + "-" + time.substring(4, 6) + "-" + time.substring(6, 8) + " " + time.substring(8, 10) + ":"
				        + time.substring(10, 12);
				break;
			case 14:
				renstr = time.substring(0, 4) + "-" + time.substring(4, 6) + "-" + time.substring(6, 8) + " " + time.substring(8, 10) + ":"
				        + time.substring(10, 12) + ":" + time.substring(12, 14) + "";
				break;

		}
		return renstr;
	}

	/**
	 * 返回当前月份
	 * 
	 * @param format
	 *            如：yyyy年MM月、yyyyMM
	 * @return 2010年02月、201002
	 */
	public static String getCurrentTimeString(String format) {
		DateFormat sdf = new SimpleDateFormat(format);
		Calendar cal = Calendar.getInstance();
		Date dateNow = cal.getTime();
		return sdf.format(dateNow);
	}

	/*
	 * #本函数主要作用是返回当前天数
	 */
	public static String getDay() {
		Calendar time = Calendar.getInstance();
		int day = time.get(Calendar.DAY_OF_MONTH);
		String djday = "";
		if (day < 10) {
			djday = "0" + Integer.toString(day);
		} else {
			djday = Integer.toString(day);
		}
		return djday;
	}

	private static String getFormatTime(int time, int format) {
		StringBuffer numm = new StringBuffer(format);
		int length = String.valueOf(time).length();

		if (format < length) {
			return null;
		}

		for (int i = 0; i < format - length; i++) {
			numm.append("0");
		}
		numm.append(time);
		return numm.toString().trim();
	}

	/*
	 * 本函数作用是返回当前小时
	 */
	public static String getHour() {
		Calendar time = Calendar.getInstance();
		int hour = time.get(Calendar.HOUR_OF_DAY);
		String djhour = "";
		if (hour < 10) {
			djhour = "0" + Integer.toString(hour);
		} else {
			djhour = Integer.toString(hour);
		}
		return djhour;
	}

	/**
	 * 得到给定时间的本周的第几天
	 * 
	 * @param dateTime
	 * @return int
	 */
	public static int getIntWeek(String dateTime) {

		Calendar calendar = Calendar.getInstance();
		int year = 0;
		int month = 0;
		int day = 0;
		// 参数存在
		if (StringUtils.isNotEmpty(dateTime) && dateTime.length() >= 8) {
			year = Integer.parseInt(dateTime.substring(0, 4));
			month = Integer.parseInt(dateTime.substring(4, 6));
			day = Integer.parseInt(dateTime.substring(6, 8));
			calendar.set(year, month - 1, day);
		}
		return calendar.get(Calendar.DAY_OF_WEEK) - 1;

	}

	/*
	 * #本函数作用是返回当前分钟
	 */
	public static String getMin() {
		Calendar time = Calendar.getInstance();
		int min = time.get(Calendar.MINUTE);
		String djmin = "";
		if (min < 10) {
			djmin = "0" + Integer.toString(min);
		} else {
			djmin = Integer.toString(min);
		}
		return djmin;
	}

	/*
	 * #本函数作用是返回当前月份（2位）
	 */
	public static String getMonth() {
		Calendar time = Calendar.getInstance();
		int month = time.get(Calendar.MONTH) + 1;
		String djmonth = "";
		if (month < 10) {
			djmonth = "0" + Integer.toString(month);
		} else {
			djmonth = Integer.toString(month);
		}
		return djmonth;
	}

	/**
	 * 根据系统时间得到n天以后的日期
	 * 
	 * @param int n 天数
	 * @return String 得到的时间 YYYYMMDD
	 */
	public static String getNDayLater(int n) {
		String time = null;
		String dayStr = null;
		String monthStr = null;
		String yearStr = null;
		java.util.Calendar cal = java.util.Calendar.getInstance();
		cal.add(java.util.Calendar.DAY_OF_MONTH, n);
		int day = cal.get(java.util.Calendar.DATE);
		int month = cal.get(java.util.Calendar.MONTH) + 1;
		int year = cal.get(java.util.Calendar.YEAR);
		if (day < 10) {
			dayStr = "0" + Integer.toString(day);
		} else {
			dayStr = Integer.toString(day);
		}
		if (month < 10) {
			monthStr = "0" + Integer.toString(month);
		} else {
			monthStr = Integer.toString(month);
		}
		yearStr = Integer.toString(year);
		time = yearStr + monthStr + dayStr;
		dayStr = null;
		monthStr = null;
		yearStr = null;
		return time;
	}

	/**
	 * 返回下个月份
	 * 
	 * @param format
	 *            如：yyyy年MM月、yyyyMM
	 * @return 2010年03月、201003
	 */
	public static String getNextMonth(String format) {
		DateFormat sdf = new SimpleDateFormat(format);
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 1);
		Date dateNow = cal.getTime();
		return sdf.format(dateNow);
	}

	/**
	 * 根据系统时间得到n月以后的日期
	 * 
	 * @param n
	 *            月数
	 * @return String 得到的时间 YYYYMMDD
	 */
	public static String getNMonthLater(int n) {
		String time = null;
		String dayStr = null;
		String monthStr = null;
		String yearStr = null;
		java.util.Calendar cal = java.util.Calendar.getInstance();
		cal.add(java.util.Calendar.MONTH, n);
		int day = cal.get(java.util.Calendar.DATE);
		int month = cal.get(java.util.Calendar.MONTH) + 1;
		int year = cal.get(java.util.Calendar.YEAR);
		if (day < 10) {
			dayStr = "0" + Integer.toString(day);
		} else {
			dayStr = Integer.toString(day);
		}

		if (month < 10) {
			monthStr = "0" + Integer.toString(month);
		} else {
			monthStr = Integer.toString(month);
		}

		yearStr = Integer.toString(year);
		time = yearStr + monthStr + dayStr;
		dayStr = null;
		monthStr = null;
		yearStr = null;
		return time;
	}

	public static String getNowInMillisString() {
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return new Long(cal.getTimeInMillis()).toString();
	}

	/**
	 * 取得本地系统的时间，时间格式由参数决定
	 * 
	 * @param format
	 *            时间格式由常量决定
	 * @return String 具有format格式的字符串
	 */

	public static String getTime(int format) {

		return getTime(format, 0);
	}

	/**
	 * 
	 */
	public static String getTime(int format, int years) {
		StringBuffer cTime = new StringBuffer(15);
		Calendar time = Calendar.getInstance();
		int miltime = time.get(Calendar.MILLISECOND);
		int second = time.get(Calendar.SECOND);
		int minute = time.get(Calendar.MINUTE);
		int hour = time.get(Calendar.HOUR_OF_DAY);
		int day = time.get(Calendar.DAY_OF_MONTH);
		int month = time.get(Calendar.MONTH) + 1;
		int year = -1;
		if (years != 0) {
			year = years;
		} else {
			year = time.get(Calendar.YEAR);
		}
		time = null;
		if (format != 14) {
			if (year >= 2000) {
				year = year - 2000;
			} else {
				year = year - 1900;
			}
		}
		if (format >= 2) {
			if (format == 14) {
				cTime.append(year);
			} else {
				cTime.append(getFormatTime(year, 2));
			}
		}
		if (format >= 4) {
			cTime.append(getFormatTime(month, 2));
		}
		if (format >= 6) {
			cTime.append(getFormatTime(day, 2));
		}
		if (format >= 8) {
			cTime.append(getFormatTime(hour, 2));
		}
		if (format >= 10) {
			cTime.append(getFormatTime(minute, 2));
		}
		if (format >= 12) {
			cTime.append(getFormatTime(second, 2));
		}
		if (format >= 15) {
			cTime.append(getFormatTime(miltime, 3));
		}
		return cTime.toString().trim();
	}

	/**
	 * 得到当前时间+timeInMillis之后的时间
	 * 
	 * @param timeInMillis
	 *            毫秒数
	 * @return
	 */
	public static String getTimeInMillisLater(String timeInMillis) {
		if (StringUtils.isEmpty(timeInMillis)) {

			timeInMillis = "0";
		}
		Calendar today = Calendar.getInstance();
		Long cur = today.getTimeInMillis() + Long.parseLong(timeInMillis);

		Date dd = new Date(cur);
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");

		return df.format(dd);

	}

	/**
	 * 获取当前系统时间，
	 * 
	 * @param dateTime
	 *            yyyymmdd%
	 * @return 格式 2008年11月14日,星期五
	 */
	public static String getTimeString(String dateTime) {

		StringBuffer str = new StringBuffer(100);
		Calendar calendar = Calendar.getInstance();
		int year = 0;
		int month = 0;
		int day = 0;
		// 参数存在
		if (StringUtils.isNotEmpty(dateTime) && dateTime.length() >= 8) {
			year = Integer.parseInt(dateTime.substring(0, 4));
			month = Integer.parseInt(dateTime.substring(4, 6));
			day = Integer.parseInt(dateTime.substring(6, 8));
			calendar.set(year, month - 1, day);
		}
		year = calendar.get(Calendar.YEAR);
		month = calendar.get(Calendar.MONTH) + 1;
		day = calendar.get(Calendar.DAY_OF_MONTH);
		int i = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		str.append(year).append("年");
		str.append(month).append("月");
		str.append(day).append("日 ");
		str.append(week(i));

		return str.toString();
	}

	public static String getToday() {
		Date dd = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(dd);
	}

	/**
	 * 根据星期几,本周的日期. 如,输入为 1,返回 本周一的日期.
	 * 
	 * @param weekday
	 *            星期几 1--7
	 * @return 日期 如 20070523
	 */
	public static String getWeekDay(int weekday) {
		String res = "";

		Calendar today = Calendar.getInstance();
		int day = today.get(Calendar.DAY_OF_WEEK) - 1;

		int dis = weekday - day;

		today.add(5, dis);

		int year = today.get(Calendar.YEAR);
		int month = today.get(Calendar.MONTH) + 1;
		int date = today.get(Calendar.DATE);

		res = String.valueOf(year) + (month < 10 ? "0" + String.valueOf(month) : String.valueOf(month))
		        + (date < 10 ? "0" + String.valueOf(date) : String.valueOf(date));

		return res;
	}

	/*
	 * #本函数主要作用是返回当前年份 #len 返回位数，2位 4位
	 */
	public static String getYear(int len) {
		Calendar time = Calendar.getInstance();
		int year = time.get(Calendar.YEAR);
		String djyear = Integer.toString(year);
		if (len == 2) {
			djyear = djyear.substring(2);
		}
		return djyear;
	}

	/**
	 * 产生任意位的字符串
	 * 
	 * @param time
	 *            int 要转换格式的时间
	 * @param format
	 *            int 转换的格式
	 * @return String 转换的时间
	 */

	public synchronized static String getYearAdd(int format, int iyear) {
		StringBuffer cTime = new StringBuffer(10);
		Calendar time = Calendar.getInstance();
		time.add(Calendar.YEAR, iyear);
		int miltime = time.get(Calendar.MILLISECOND);
		int second = time.get(Calendar.SECOND);
		int minute = time.get(Calendar.MINUTE);
		int hour = time.get(Calendar.HOUR_OF_DAY);
		int day = time.get(Calendar.DAY_OF_MONTH);
		int month = time.get(Calendar.MONTH) + 1;
		int year = time.get(Calendar.YEAR);
		if (format != 14) {
			if (year >= 2000) {
				year = year - 2000;
			} else {
				year = year - 1900;
			}
		}
		if (format >= 2) {
			if (format == 14) {
				cTime.append(year);
			} else {
				cTime.append(getFormatTime(year, 2));
			}
		}
		if (format >= 4) {
			cTime.append(getFormatTime(month, 2));
		}
		if (format >= 6) {
			cTime.append(getFormatTime(day, 2));
		}
		if (format >= 8) {
			cTime.append(getFormatTime(hour, 2));
		}
		if (format >= 10) {
			cTime.append(getFormatTime(minute, 2));
		}
		if (format >= 12) {
			cTime.append(getFormatTime(second, 2));
		}
		if (format >= 15) {
			cTime.append(getFormatTime(miltime, 3));
		}
		return cTime.toString();
	}

	/**
	 * 
	 */
	public static String getYTime(int years) {
		Date dd = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		return df.format(dd);
	}

	/**
	 * 获取当前时间
	 * 
	 * @return
	 */
	public static String getNowTime() {
		Date dd = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(dd);
	}

	/**
	 * 将js日历控件中的时间转换为数据库格式时间 例如：2006-09-22 -〉20060922
	 */
	public static String JsToDb(String time) {
		if (time == null) {
			return "";
		}
		int len = time.trim().length();
		StringBuffer ret = new StringBuffer();
		switch (len) {
			case 10:
				ret.append(time.substring(0, 4));
				ret.append(time.substring(5, 7));
				ret.append(time.substring(8));
				break;
			case 16:
				ret.append(time.substring(0, 4));
				ret.append(time.substring(5, 7));
				ret.append(time.substring(8, 10));
				ret.append(time.substring(11, 13));
				ret.append(time.substring(14));
				ret.append("00");
				break;
			default:
				ret.append(time);
		}
		return ret.toString();
	}

	/**
	 * 转换日期 YYYY-MM-DD 到 YYYYMMDD
	 * 
	 * @param date
	 * @return
	 */
	public static String tranDateToQuery(String date) {
		String[] d = date.split("-");
		return d[0] + d[1] + d[2];
	}

	/**
	 * 转换日期 YYYYMMDD 到 YYYY-MM-DD
	 * 
	 * @param date
	 * @return
	 */
	public static String tranDateToShow(String date) {
		return date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6, 8);
	}

	/**
	 * 转换日期 YYYY-MM-DD 到 YYYYMMDDhhmmss
	 * 
	 * @param date
	 * @return
	 */
	public static String tranDateToSmil(String date) {
		return tranDateToQuery(date) + "000000";
	}

	/**
	 * 转换时间 HHMMSS 到 HH:MM:SS
	 * 
	 * @param date
	 * @return
	 */
	public static String tranHMSTimeToShow(String time) {
		if (time.length() < 6) {
			return time;
		} else {
			return time.substring(0, 2) + ":" + time.substring(2, 4) + ":" + time.substring(4, 6);
		}
	}

	/**
	 * 将14位日期格式转换成yyyy-MM-dd HH:mm:ss格式
	 * 
	 * @param srcDate
	 *            String
	 * @return String
	 */
	public static String transDate(String srcDate) {
		if (srcDate != null) {
			if (srcDate.trim().length() == 14) {
				return transDateTime(srcDate.trim(), "yyyyMMddHHmmss", "yyyy-MM-dd HH:mm:ss");
			}
			if (srcDate.trim().length() <= 8) {
				return transDateTime(srcDate.trim(), "yyyyMMdd", "yyyy-MM-dd");
			}
			return "";
		} else {
			return "";
		}
	}

	/**
	 * 将日期时间从一种格式转换为另一种格式
	 * 
	 * @param srcTime
	 *            源串
	 * @param srcPattern
	 *            源串格式
	 * @param destPattern
	 *            目标串格式
	 * @return String 目标串
	 */
	public static String transDateTime(String srcTime, String srcPattern, String destPattern) {
		if (srcTime == null) {
			return "";
		}
		try {
			SimpleDateFormat fmt = new SimpleDateFormat();
			fmt.applyPattern(srcPattern);
			Date date = fmt.parse(srcTime);
			fmt.applyPattern(destPattern);
			return fmt.format(date);
		}
		catch (Exception exp) {
		}
		return srcTime;
	}

	/**
	 * 获取周信息
	 * 
	 * @param i
	 *            (0<=i<=6)
	 * @return string
	 */
	public static String week(int i) {

		String week[] = new String[] { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };

		return week[i];
	}

	public void test() {

		try {
			PrintWriter bb = new PrintWriter(new FileWriter("test2.txt", true), true);
			bb.close();
		}
		catch (Exception ex) {
		}

	}

	/**
	 * 获取指定日期所在月份的最后一天
	 * 
	 * @param time
	 * @return
	 */
	public static String getLastDayOfDate(String time) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		String retTime = "";
		try {
			date = sdf.parse(time);
			cal.setTime(date);
			int month = cal.get(Calendar.MONTH) + 1;
			int day = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
			if (month < 10) {
				retTime = time.substring(0, 4) + "-0" + month + "-" + day;
			} else {
				retTime = time.substring(0, 4) + "-" + month + "-" + day;
			}
		}
		catch (ParseException e) {
			e.printStackTrace();
		}

		return retTime;
	}

}
