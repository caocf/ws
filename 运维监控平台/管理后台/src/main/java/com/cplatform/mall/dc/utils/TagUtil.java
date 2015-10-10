package com.cplatform.mall.dc.utils;

import java.util.Calendar;

import org.springframework.ui.Model;

public class TagUtil {
	public static void tagYear(Model model) {
		Calendar cal = Calendar.getInstance();
		int currentYear = cal.get(Calendar.YEAR);
		int currentMonth = cal.get(Calendar.MONTH);

		model.addAttribute("beginYear", currentYear - 3);
		model.addAttribute("endYear", currentYear + 3);

		// 1月份的上个月为去年12月
		if (currentMonth == Calendar.JANUARY) {
			currentYear--;
			currentMonth = 12;
		}
		model.addAttribute("selectedYear", currentYear);
		model.addAttribute("selectedMonth", currentMonth);
	}

	public static void tagYear(Model model, String selectedYear, String selectedMonth) {
		Calendar cal = Calendar.getInstance();
		int currentYear = cal.get(Calendar.YEAR);

		model.addAttribute("beginYear", currentYear - 3);
		model.addAttribute("endYear", currentYear + 3);

		model.addAttribute("selectedYear", selectedYear);
		model.addAttribute("selectedMonth", Integer.parseInt(selectedMonth));
	}
	
	public static String numberCharCN2Arab(String month){
		if(month.equals("1") || month.equals("01")){
			return "一月";
		}else if(month.equals("2") || month.equals("02")){
			return "二月";
		}else if(month.equals("3") || month.equals("03")){
			return "三月";
		}else if(month.equals("4") || month.equals("04")){
			return "四月";
		}else if(month.equals("5") || month.equals("05")){
			return "五月";
		}else if(month.equals("6") || month.equals("06")){
			return "六月";
		}else if(month.equals("7") || month.equals("07")){
			return "七月";
		}else if(month.equals("8") || month.equals("08")){
			return "八月";
		}else if(month.equals("9") || month.equals("09")){
			return "九月";
		}else if(month.equals("10")){
			return "十月";
		}else if(month.equals("11")){
			return "十一月";
		}else if(month.equals("12")){
			return "十二月";
		}
		return null;
	}
}
