package com.cplatform.mall.dc.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;


/**
 * 根据当签当前月份获取该月的周次
 * @author zhangdong
 *
 */
@Component
public class WeeksOfMonthUtils {

	public static final String MMdd = "MM.dd";

	public static String addDays(String strTime, int days) {
		SimpleDateFormat sdf = new SimpleDateFormat(TimeUtil.TARGET_3);
		try {
			Date d = sdf.parse(strTime);
			return TimeUtil.format(DateUtils.addDays(d, days), MMdd);
		} catch (ParseException e) {
			e.printStackTrace();
			return "";
		}
	}
	
	public List<String> getMonthWeek(int month){
		List<String> list = new ArrayList<String>();
		Calendar cal = Calendar.getInstance();

		cal.set(Calendar.YEAR, Calendar.getInstance().get(Calendar.YEAR));
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.DAY_OF_MONTH, 1);

		Calendar c = (Calendar) cal.clone();
		c.add(Calendar.MONTH, 1);
		boolean isFirst = true;
		while (!c.before(cal)) {
			int day = cal.get(Calendar.DAY_OF_WEEK);
			if (day == Calendar.MONDAY) {
				String monday = TimeUtil.format(cal.getTime(),
						TimeUtil.TARGET_3);
				if (isFirst) {
					String date = String.valueOf(cal.get(Calendar.DATE));
					if (!"01".equals(date) && !"1".equals(date)) {
						list.add(0, WeeksOfMonthUtils.addDays(monday, -7) +"-"+WeeksOfMonthUtils.addDays(monday, -1));
						isFirst = false;
					}
				}
				list.add(TimeUtil.format(cal.getTime(), MMdd) + "-" + WeeksOfMonthUtils.addDays(monday, 6));
				isFirst = false;
			}
			cal.add(Calendar.DATE, 1);
		}
		return list;
	}
	
	public void getWeekOfMonth(int month, Model model){
		model.addAttribute("sweeks", getMonthWeek(month-1));
	}
	
	/**
	 * 算出默认周期
	 * @param list
	 * @param sdate 0701格式 
	 * @return
	 */
	public Map<String, String> getCurrentWeek(){
		int month = Calendar.getInstance().get(Calendar.MONDAY);
		List<String> list = getMonthWeek(month);
		Map<String, String> map = new HashMap<String, String>();
		for(int i = 0; i < list.size(); i++){
			Integer startDay = Integer.parseInt(list.get(i).split("-")[0].replaceAll("\\.", "").trim());
			Integer endDay = Integer.parseInt(list.get(i).split("-")[1].replaceAll("\\.", "").trim());
			int day = Calendar.getInstance().get(Calendar.DATE);
			String dayStr = "";
			if(day < 10){
				dayStr = "0"+day;
			}else{
				dayStr = ""+day;
			}
			int sdate = Integer.parseInt((month+1)+dayStr);
			if(sdate >= startDay && sdate <= endDay){
				if(i == 0){
					if(month == 0){
						return null;
					}else{
						List<String> weeks = getMonthWeek(month - 1);
						map.put("sweek", weeks.get(weeks.size() - 1));
						map.put("month", String.valueOf(month+1));
						return map;
					}
				}else{
					map.put("sweek", list.get(i - 1));
					map.put("month", String.valueOf(month+1));
					return map;
				}
			}
		}
		return null;
	}
}
