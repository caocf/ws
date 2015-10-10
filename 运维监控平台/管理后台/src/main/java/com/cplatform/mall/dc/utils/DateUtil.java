package com.cplatform.mall.dc.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.lang3.StringUtils;

/**
 * 日期工具类
 * 
 * @author zhuangxx@c-platform.com
 * @since 2013-8-2
 */
public class DateUtil {
	public static final long A_DAY = 1000 * 60 * 60 * 24;

	/**
	 * 获取昨天的字符串
	 * 
	 * @return
	 */
	public static String getYeasterday() {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(c.getTimeInMillis() - A_DAY);

		String year = String.valueOf(c.get(Calendar.YEAR));

		String month = String.valueOf(c.get(Calendar.MONTH) + 1);
		if (c.get(Calendar.MONTH) <= Calendar.SEPTEMBER) {
			month = "0" + month;
		}

		String day = String.valueOf(c.get(Calendar.DATE));
		if (c.get(Calendar.DATE) <= 9) {
			day = "0" + day;
		}

		return year + month + day;
	}

	/**
	 * 获取上个月的字符串
	 * 
	 * @return
	 */
	public static String getLastMonth() {
		Calendar c = Calendar.getInstance();

		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);

		if (month == Calendar.JANUARY) {
			year--;
			month = 12;
		}

		if (month <= 9) {
			return String.valueOf(year) + "0" + String.valueOf(month);
		} else {
			return String.valueOf(year) + String.valueOf(month);
		}
	}

	/**
	 * 获取日历
	 * @param date 日期
	 * @return 日历
	 */
	public static Calendar getCalendar(Date date) {
		if (date == null) {
			date = new Date();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c;
	}

	/**
	 * 增加时间
	 * @param date 日期
	 * @param calendarField 年月日时分秒等
	 * @param amount 数量（可以是负数）
	 * @return 日期
	 */
	public static Date add(Date date, int calendarField, int amount) {
		Calendar c = getCalendar(date);
		c.add(calendarField, amount);
		return c.getTime();
	}

	/**
	 * 增加月数
	 * @param date 日期
	 * @param amount 数量（可以是负数）
	 * @return 日期
	 */
	public static Date addMonths(Date date, int amount) {
		return add(date, Calendar.MONTH, amount);
	}

	/**
	 * 增加天数
	 * @param date 日期
	 * @param amount 数量（可以是负数）
	 * @return 日期
	 */
	public static Date addDays(Date date, int amount) {
		return add(date, Calendar.DAY_OF_MONTH, amount);
	}

	public static Date getFirstDayOfMonth(Date date) {
		Calendar c = getCalendar(date);
		c.set(Calendar.DAY_OF_MONTH, 1);
		return c.getTime();
	}

	public static Date getFirstDayOfMonth() {
		return getFirstDayOfMonth(null);
	}

	public static Date getFirstDayOfNextMonth(Date date) {
		Calendar c = getCalendar(date);
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.add(Calendar.MONTH, 1);
		return c.getTime();
	}

	public static Date getFirstDayOfNextMonth() {
		return getFirstDayOfNextMonth(null);
	}

	public static Date getLastDayOfMonth(Date date) {
		return addDays(getFirstDayOfNextMonth(date), -1);
	}

	public static Date getLastDayOfMonth() {
		return getLastDayOfMonth(null);
	}

	public static Date parse(String date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return new Date();
		}
	}

	public static Date parse(String year, String month, String day) {
		Calendar c = getCalendar(null);
		if (!StringUtils.isBlank(year)) {
			c.set(Calendar.YEAR, Integer.parseInt(year));
		}
		if (!StringUtils.isBlank(month)) {
			c.set(Calendar.MONTH, Integer.parseInt(month) - 1);
		}
		if (!StringUtils.isBlank(day)) {
			c.set(Calendar.DATE, Integer.parseInt(day));
		}
		return c.getTime();
	}

	public static String format(Date date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}
	
	public static String format(String date, String old_pattern, String new_pattern) {
		return format(parse(date, old_pattern), new_pattern);
	}
	
	
	public static String getTime(int difTime) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmm");
		Calendar nowTime = Calendar.getInstance();
		nowTime.add(Calendar.MINUTE, difTime);
		return sf.format(nowTime.getTime());
	}
	
	public static String getTime(String date,int difTime) {
		if(date.length()>12){
			date = date.substring(0,12);
		}
		if(date.length()==10){
			date = date+"00";
		}
		if(date.length()==8){
			date = date+"0000";
		}
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmm");
		Date d = null;
		try {
			d = sf.parse(date);
			Calendar time = Calendar.getInstance();
			time.setTime(d);
			time.add(Calendar.MINUTE, difTime);
			return sf.format(time.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String getWebTime(String date){
		if(date.length()==12){
			date = date + "00";
		}
		if(date.length()==10){
			date = date+"0000";
		}
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
		SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return sf2.format(sf.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	public static String getMonth(String date,int month) {
		if(date.length()>12){
			date = date.substring(0,12);
		}
		if(date.length()==10){
			date = date+"00";
		}
		if(date.length()==8){
			date = date+"0000";
		}
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmm");
		Date d = null;
		try {
			d = sf.parse(date);
			Calendar time = Calendar.getInstance();
			time.setTime(d);
			time.add(Calendar.MONTH, month);
			return sf.format(time.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static String getModelTime(String time){
		if(time.length()==8){
			time = time + "0000";
		}
		if(time.length()==10){
			time = time + "00";
		}
		if(time.length()>12){
			time = time.substring(0,12);
		}
		return time;
	}
	
	public static int getDifTime(String start,String end) throws ParseException {
		start = getModelTime(start);
		end = getModelTime(end);
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmm");
		Date startTime = sf.parse(start);
		Date endTime = sf.parse(end);
		int day = (int)((endTime.getTime()-startTime.getTime())/60000);
		return day;
	}
	
	public static int getDifDay(String start,String end) throws ParseException {
		start = getModelTime(start).substring(0,8)+"0000";
		end = getModelTime(end).substring(0,8)+"0000";
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmm");
		Date startTime = sf.parse(start);
		Date endTime = sf.parse(end);
		int day = (int)((endTime.getTime()-startTime.getTime())/(60000*60*24));
		return day;
	}
	
	public static int getDifHour(String start,String end) throws ParseException {
		start = getModelTime(start).substring(0,10)+"00";
		end = getModelTime(end).substring(0,10)+"00";
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmm");
		Date startTime = sf.parse(start);
		Date endTime = sf.parse(end);
		int day = (int)((endTime.getTime()-startTime.getTime())/(60000*60));
		return day;
	}
	
	public static int getDifMonth(String start,String end) throws ParseException {
		start = getModelTime(start);
		end = getModelTime(end);
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmm");
		Date startDate = sf.parse(start);
		Date endDate = sf.parse(end);
		if (startDate.after(endDate)) {
            Date t = startDate;
            startDate = endDate;
            endDate = t;
        }
		Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(startDate);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(endDate);
        Calendar temp = Calendar.getInstance();
        temp.setTime(endDate);
        temp.add(Calendar.DATE, 1);

        int year = endCalendar.get(Calendar.YEAR)
                - startCalendar.get(Calendar.YEAR);
        int month = endCalendar.get(Calendar.MONTH)
                - startCalendar.get(Calendar.MONTH);

        if ((startCalendar.get(Calendar.DATE) == 1)
                && (temp.get(Calendar.DATE) == 1)) {
            return year * 12 + month + 1;
        } else if ((startCalendar.get(Calendar.DATE) != 1)
                && (temp.get(Calendar.DATE) == 1)) {
            return year * 12 + month;
        } else if ((startCalendar.get(Calendar.DATE) == 1)
                && (temp.get(Calendar.DATE) != 1)) {
            return year * 12 + month;
        } else {
            return (year * 12 + month - 1) < 0 ? 0 : (year * 12 + month);
        }
	}
	
	
	public static String getShowTime(String time) {
		String newDate = "";
		if(time.length()>=12){
			time = time.substring(4,12);
			SimpleDateFormat sf = new SimpleDateFormat("MMddHHmm");
			Date date;
			try {
				date = sf.parse(time);
				SimpleDateFormat sf2 = new SimpleDateFormat("M月d日 HH:mm");
				newDate = sf2.format(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}else if(time.length()==10){
			time = time.substring(4,10);
			SimpleDateFormat sf = new SimpleDateFormat("MMddHH");
			Date date;
			try {
				date = sf.parse(time);
				SimpleDateFormat sf2 = new SimpleDateFormat("M月d日HH时");
				newDate = sf2.format(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}else if(time.length()==8){
			time = time.substring(4,8);
			SimpleDateFormat sf = new SimpleDateFormat("MMdd");
			Date date;
			try {
				date = sf.parse(time);
				SimpleDateFormat sf2 = new SimpleDateFormat("M月d日");
				newDate = sf2.format(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}else if(time.length()==10){
			time = time.substring(4,10);
			SimpleDateFormat sf = new SimpleDateFormat("MMddHH");
			Date date;
			try {
				date = sf.parse(time);
				SimpleDateFormat sf2 = new SimpleDateFormat("M月d日HH时");
				newDate = sf2.format(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}else if(time.length()==6){
			SimpleDateFormat sf = new SimpleDateFormat("yyyyMM");
			Date date;
			try {
				date = sf.parse(time);
				SimpleDateFormat sf2 = new SimpleDateFormat("yyyy年M月");
				newDate = sf2.format(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return newDate;
		
	}
	
	
	public static void main(String[] args) throws ParseException {
//		System.out.println(DateUtil.getTime(-60));
//		System.out.println(DateUtil.getTime("20140424143512", 10));
//		try {
//			System.out.println(DateUtil.getDifTime("201404241435", "201404241535"));
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println(DateUtil.getShowTime("2014112411"));
//		System.out.println(DateUtil.getTime(-(19*10)));
//		System.out.println(DateUtil.getDifTime("20140426", "20140425"));
//		System.out.println(getDifMonth("20120205", "20140425"));
//		System.out.println(DateUtil.getMonth("20140424143512", -3));
//		System.out.println(getShowTime("201405"));
//		System.out.println(getWebTime("20140424143512"));
		System.out.println(getDifHour("201405030100", "201405040100"));
	}
}
