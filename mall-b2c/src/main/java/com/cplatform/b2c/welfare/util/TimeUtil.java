package com.cplatform.b2c.welfare.util;



import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.cplatform.util2.TimeStamp;

/**
 * Title. 时间工具类<br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-1-21 下午05:53:28
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: guolt@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
public class TimeUtil {

	/**
	 * 根据输入的格式返回需要的时间日期
	 * 
	 * @param formate
	 *            时间格式
	 * @return 时间
	 */
	public static String getSysTime(String formate) {
		String str = null;
		try {

			str = TimeStamp.getFormatTime(formate);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
		return str;
	}

	/**
	 * 获得星期
	 * 
	 * @return 星期，1礼拜日，2礼拜一...7礼拜6
	 */
	public static int getDayOfWeek() {
		return Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 获得13位当前时间
	 * 
	 * @return 当前时间
	 */
	public static String get13SysTime() {
		String str = null;
		try {

			str = TimeStamp.getFormatTime("yyyy-MM-dd HH");
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
		return str;
	}

	/**
	 * 获得19位当前时间,格式yyyy-MM-dd HH:mm:ss
	 * 
	 * @return
	 */
	public static String get19SysTime() {
		String str = null;
		try {
			str = TimeStamp.getFormatTime("yyyy-MM-dd HH:mm:ss");
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
		return str;
	}

	/**
	 * 获得11位系统当前时间,格式HH:mm MM-dd
	 * 
	 * @return 系统当前时间
	 */
	public static String get11SysTime() {

		try {
			return TimeStamp.getFormatTime("HH:mm MM-dd");
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 获得当前的时间，格式HH:mm
	 * 
	 * @return 系统当前时间
	 */
	public static String getHourAndMinute() {
		try {
			return TimeStamp.getFormatTime("HH:mm");
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 获得10位系统当前时间,格式yyyy-MM-dd
	 * 
	 * @return 系统当前时间
	 */
	public static String get10SysTime() {
		try {
			return TimeStamp.getFormatTime("yyyy-MM-dd");
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 获得7位系统当前时间,格式yyyy-MM
	 * 
	 * @return 系统当前时间
	 */
	public static String get7SysTime() {
		try {
			return TimeStamp.getFormatTime("yyyy-MM");
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 获得前几个月的日期
	 * 
	 * @param past
	 *            前几个月
	 * @return 日期，格式yyyy-MM
	 */
	public static String get7PastTime(int past) {
		Calendar cal = Calendar.getInstance();
		// 获得前7天前日期
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - past);
		Date date = cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		String preDate = sdf.format(date);
		return preDate;
	}

	/**
	 * 获得前多少天的日期
	 * 
	 * @param past
	 *            前多少天
	 * @return 日期，格式yyyy-MM-DD
	 */
	public static String get10PastTime(int past) {
		Calendar cal = Calendar.getInstance();
		// 获得前7天前日期
		cal.set(Calendar.DATE, cal.get(Calendar.DATE) - past);
		Date date = cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String preDate = sdf.format(date);
		return preDate;
	}

	/**
	 * 获得当前分钟
	 * 
	 * @return 当前分钟
	 * @throws Exception
	 *             抛出异常
	 */
	public static String getMinute() {
		try {
			return TimeStamp.getFormatTime("mm");
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 获得今天的日期（dd）
	 * 
	 * @return 日
	 */
	public static String getDay() {
		try {
			return TimeStamp.getFormatTime("dd");
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 获得时间
	 * 
	 * @return 时间，格式为yyyyMMdd * @throws Exception
	 */
	public static String getYyyyMMddHHTime() {
		try {
			return TimeStamp.getFormatTime("yyyyMMddHH");
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

}
