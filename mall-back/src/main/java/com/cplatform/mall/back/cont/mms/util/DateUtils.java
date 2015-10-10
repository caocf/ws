package com.cplatform.mall.back.cont.mms.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.apache.log4j.Logger;

import com.cplatform.mall.back.item.web.ItemManageController;

/**
 * 日期操作类. <br>
 * 类详细说明.
 * <p>
 * 修改历史: Jan 15, 2010 1:46:17 PM baowr@c-platform.com <br>
 * 修改说明: 代码规范修改 <br>
 * <p>
 * Copyright: Copyright (c) Jan 14, 2010 5:10:40 PM
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author XXX@c-platform.com
 * @version 1.0.0
 */
public class DateUtils {

	
	
	private static final Logger log = Logger.getLogger(ItemManageController.class);
	/**
	 * 以指定的时间格式将字符串表示的时间转换成Date对象
	 * 
	 * @param pattern
	 *            string类型的日期表示格式
	 * @param strDate
	 *            string类型的日期
	 * @return 日期
	 * @throws ParseException
	 *             ParseException
	 */
	public static final Date convertStringToDate(String pattern, String strDate) throws ParseException {
		SimpleDateFormat df = null;
		Date date = null;
		df = new SimpleDateFormat(pattern);

		try {
			date = df.parse(strDate);
		}
		catch (ParseException pe) {
			pe.printStackTrace();
		}
		return (date);
	}

	/**
	 * 应用SimpleDateFormat,以pattern为参数,将当前的时间转换成字符串表示
	 * 
	 * @param pattern
	 *            转换的格式
	 * @return string类型的日期
	 */
	public static String getDateByPattern(String pattern) {
		java.util.Date dt = new java.util.Date();
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		df.setTimeZone(TimeZone.getDefault());
		return df.format(dt);
	}

	/**
	 * 应用SimpleDateFormat,以pattern为参数,将指定的时间对象转换成字符串表示
	 * 
	 * @param pattern
	 *            转换的格式
	 * @param date
	 *            日期
	 * @return string类型的日期
	 */
	public static String getDateByPattern(String pattern, Date date) {
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		df.setTimeZone(TimeZone.getDefault());
		return df.format(date);
	}

	/**
	 * 应用SimpleDateFormat,以pattern为参数,将指定的long类型表示的时间转换成字符串表示
	 * 
	 * @param pattern
	 *            转换的格式
	 * @param date
	 *            日期
	 * @return string类型的日期
	 */
	public static String getDateByPattern(String pattern, long t) {
		Date date = new Date();
		date.setTime(t);
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		df.setTimeZone(TimeZone.getDefault());
		return df.format(date);
	}

	/**
	 * 应用SimpleDateFormat,以pattern为参数,将指定的时间转换成字符串表示
	 * 
	 * @param pattern
	 *            转换的格式
	 * @param date
	 *            日期
	 * @return string类型的日期
	 */
	public static String getDateByPattern(String pattern, String date) {
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		df.setTimeZone(TimeZone.getDefault());
		return df.format(date);
	}

	/**
	 * 获取当前时间的几号
	 * 
	 * @return 几号
	 */
	public static int getDay() {
		Calendar cld = Calendar.getInstance();
		cld.setTime(new java.util.Date());
		return cld.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 获取date是几号
	 * 
	 * @param date
	 *            日期
	 * @return 几号
	 */
	public static int getDay(Date date) {
		Calendar cld = Calendar.getInstance();
		cld.setTime(date);
		return cld.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 获取自1970年一月一号开始后的t毫秒的日期是几号
	 * 
	 * @param t
	 *            毫秒数
	 * @return 几号
	 */
	public static int getDay(long t) {
		Calendar cld = Calendar.getInstance();
		if (t > 0) {
			cld.setTime(new java.util.Date(t));
		}
		return cld.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 获取自1970年一月一号开始后的t毫秒的完整格式日期
	 * 
	 * @param t
	 *            毫秒数
	 * @return 完整日期
	 */
	public static String getFullDateByLongTime(long t) {
		DateFormat df = DateFormat.getDateInstance(DateFormat.FULL);
		Date now = new Date();
		now.setTime(t);
		String strDate = df.format(now);
		return strDate;
	}

	/**
	 * 获取当前小时
	 * 
	 * @return 小时
	 */
	public static int getHour() {
		Calendar cld = Calendar.getInstance();
		cld.setTime(new java.util.Date());
		return cld.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 获取date的小时数
	 * 
	 * @param date
	 *            日期
	 * @return 小时
	 */
	public static int getHour(Date date) {
		Calendar cld = Calendar.getInstance();
		cld.setTime(date);
		return cld.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 获取自1970年一月一号8点开始后的t毫秒的小时
	 * 
	 * @param t
	 *            毫秒
	 * @return 小时
	 */
	public static int getHour(long t) {
		Calendar cld = Calendar.getInstance();
		if (t > 0) {
			cld.setTime(new java.util.Date(t));
		}
		return cld.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 获取当前日期的分钟数
	 * 
	 * @return 分钟数
	 */
	public static int getMinute() {
		Calendar cld = Calendar.getInstance();
		cld.setTime(new java.util.Date());
		return cld.get(Calendar.MINUTE);
	}

	/**
	 * 获取date的分钟数
	 * 
	 * @param date
	 *            日期
	 * @return 分钟数
	 */
	public static int getMinute(Date date) {
		Calendar cld = Calendar.getInstance();
		cld.setTime(date);
		return cld.get(Calendar.MINUTE);
	}

	/**
	 * 获取自1970年一月一号8点开始后的t毫秒的分钟
	 * 
	 * @param t
	 *            毫秒
	 * @return 分钟
	 */
	public static int getMinute(long t) {
		Calendar cld = Calendar.getInstance();
		if (t > 0) {
			cld.setTime(new java.util.Date(t));
		}
		return cld.get(Calendar.MINUTE);
	}

	/**
	 * 获取当前日期的月份
	 * 
	 * @return 月份
	 */
	public static int getMonth() {
		Calendar cld = Calendar.getInstance();
		cld.setTime(new java.util.Date());
		return cld.get(Calendar.MONTH) + 1;
	}

	/**
	 * 获取date的月份
	 * 
	 * @param date
	 *            日期
	 * @return 月份
	 */
	public static int getMonth(Date date) {
		Calendar cld = Calendar.getInstance();
		cld.setTime(date);
		return cld.get(Calendar.MONTH) + 1;
	}

	/**
	 * 获取自1970年一月一号8点开始后的t毫秒的月份
	 * 
	 * @param t
	 *            毫秒
	 * @return 月份
	 */
	public static int getMonth(long t) {
		Calendar cld = Calendar.getInstance();
		if (t > 0) {
			cld.setTime(new java.util.Date(t));
		}
		return cld.get(Calendar.MONTH) + 1;
	}

	/**
	 * 获取当前毫秒数
	 * 
	 * @return 毫秒
	 */
	public static long getNowInMillis() {
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.getTimeInMillis();
	}

	/**
	 * 获取当前毫秒数的string格式
	 * 
	 * @return 毫秒
	 */
	public static String getNowInMillisString() {
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return new Long(cal.getTimeInMillis()).toString();
	}

	/**
	 * 获取date的毫秒数的string格式
	 * 
	 * @param date
	 *            日期
	 * @return 毫秒
	 */
	public static String getNowInMillisString(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return new Long(cal.getTimeInMillis()).toString();
	}

	/**
	 * 获取当前日期的秒数
	 * 
	 * @return 秒数
	 */
	public static int getSecond() {
		Calendar cld = Calendar.getInstance();
		cld.setTime(new java.util.Date());
		return cld.get(Calendar.SECOND);
	}

	/**
	 * 获取date的秒数
	 * 
	 * @param date
	 *            日期
	 * @return 秒数
	 */
	public static int getSecond(Date date) {
		Calendar cld = Calendar.getInstance();
		cld.setTime(date);
		return cld.get(Calendar.SECOND);
	}

	/**
	 * 获取1970年一月一号八点后t毫秒的日期秒数
	 * 
	 * @param t
	 *            毫秒
	 * @return 秒数
	 */
	public static int getSecond(long t) {
		Calendar cld = Calendar.getInstance();
		if (t > 0) {
			cld.setTime(new java.util.Date(t));
		}
		return cld.get(Calendar.SECOND);
	}

	/**
	 * 获取当前日期年份
	 * 
	 * @return 年份
	 */
	public static int getYear() {
		Calendar cld = Calendar.getInstance();
		cld.setTime(new java.util.Date());
		return cld.get(Calendar.YEAR);
	}

	/**
	 * 获取date日期的年份
	 * 
	 * @param date
	 *            日期
	 * @return 年份
	 */
	public static int getYear(Date date) {
		Calendar cld = Calendar.getInstance();
		cld.setTime(date);
		return cld.get(Calendar.YEAR);
	}

	/**
	 * 获取1970年一月一号8点以后t毫秒的日期年份
	 * 
	 * @param t
	 *            毫秒
	 * @return 年份
	 */
	public static int getYear(long t) {
		Calendar cld = Calendar.getInstance();
		if (t > 0) {
			cld.setTime(new java.util.Date(t));
		}
		return cld.get(Calendar.YEAR);
	}

	/**
	 * 日历对象
	 */
	private Calendar cal;

	/**
	 * 日期对象
	 */
	private final Date date;

	/**
	 * 日期格式化对象
	 */
	private DateFormat df;

	/**
	 * 简单日期格式化对象
	 */
	private SimpleDateFormat sdf;

	/**
	 * 日期string格式
	 */
	private String strDate = "";

	/**
	 * 构造函数
	 */
	public DateUtils() {
		this.date = new Date();
	}

	/**
	 * 构造函数
	 * 
	 * @param cal
	 *            日历
	 */
	public DateUtils(Calendar cal) {
		this.date = cal.getTime();
	}

	/**
	 * 构造函数
	 * 
	 * @param date
	 *            日期
	 */
	public DateUtils(Date date) {
		this.date = date;
	}

	/**
	 * 获取cal对象
	 * 
	 * @return cal
	 */
	public Calendar getCalendar() {
		return this.cal;
	}

	/**
	 * 获取date对象
	 * 
	 * @return date
	 */
	public Date getDate() {
		return this.date;
	}

	/**
	 * 获取日期格式化对象
	 * 
	 * @return df
	 */
	public DateFormat getDateFormat() {
		return this.df;
	}

	/**
	 * 获取默认格式化后的日期
	 * 
	 * @return 日期
	 */
	public String getDefaultDateByDateFormat() {
		this.df = DateFormat.getDateInstance();
		strDate = df.format(this.getDate());
		return strDate;
	}

	/**
	 * 获取完整格式的日期 格式：1970年1月1日 星期二
	 * 
	 * @return 日期
	 */
	public String getFullDateByDateFormat() {
		this.df = DateFormat.getDateInstance(DateFormat.FULL);
		strDate = df.format(this.getDate());
		return strDate;
	}

	/**
	 * 获取Long格式的日期 格式：1970年1月1日
	 * 
	 * @return 日期
	 */
	public String getLongDateByDateFormat() {
		this.df = DateFormat.getDateInstance(DateFormat.LONG);
		strDate = df.format(this.getDate());
		return strDate;
	}

	/**
	 * 获取medium格式的日期 格式：1970-1-1
	 * 
	 * @return 日期
	 */
	public String getMediumDateByDateFormat() {
		this.df = DateFormat.getDateInstance(DateFormat.MEDIUM);
		strDate = df.format(this.getDate());
		return strDate;
	}

	/**
	 * 获取short格式的日期 格式：70-1-1
	 * 
	 * @return 日期
	 */
	public String getShortDateByDateFormat() {
		this.df = DateFormat.getDateInstance(DateFormat.SHORT);
		strDate = df.format(this.getDate());
		return strDate;
	}

	/**
	 * 获取简单日期格式化对象
	 * 
	 * @return sdf
	 */
	public SimpleDateFormat getSimpleDateFormat() {
		return this.sdf;
	}

	/**
	 * 获取string格式的date
	 * 
	 * @return 日期
	 */
	public String getStrDate() {
		return this.strDate;
	}
}
