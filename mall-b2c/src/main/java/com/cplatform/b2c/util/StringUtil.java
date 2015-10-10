package com.cplatform.b2c.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang.text.StrBuilder;

/**
 * 字符串操作类. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) Apr 7, 2009 11:38:15 AM
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: JinLei
 * <p>
 * Version: 1.0
 * <p>
 */
public class StringUtil {

	public static String changeCharset(String str, String fromCharset, String toCharset) {
		if (isEmpty(str)) {
			return str;
		}

		String src, dest;
		src = str;

		try {
			dest = new String(src.getBytes(fromCharset), toCharset);

			if (dest.length() == src.length()) {
				dest = src;
			}
		}
		catch (Exception e) {
			return src;
		}

		return dest;
	}

	/**
	 * 把NULL值转化为空("")
	 * 
	 * @param obj
	 *            传入一个object
	 * @return String strValue=null,return"";str=str
	 */
	public static String getString(Object obj) {
		try {
			if (obj == null) {
				return "";
			}
			return obj.toString();
		}
		catch (Exception ex) {
			return "";
		}
	}

	/**
	 * 把NULL值转化为空("")
	 * 
	 * @param strValue
	 *            String 传入一个字符串
	 * @return String strValue=null,return"";str=str
	 */

	public static String getString(String str) {
		return StringUtils.defaultString(str);
	}

	/**
	 * if the input string is empty, then return the default string
	 * 
	 * @param str
	 *            Input String
	 * @param def
	 *            Default value
	 * @return string
	 */
	public static String getString(String str, String def) {
		return StringUtils.defaultIfEmpty(str, def);
	}

	/**
	 * evaluate the input string is null, or made up of space charactor
	 * 
	 * @param str
	 *            Input String
	 * @return if empty, then true, else false
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.trim().length() == 0;

	}

	/**
	 * 判断字符串是否是纯数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumric(String str) {
		str = StringUtil.getString(str);
		if ("".equals(str)) {
			return false;
		}

		return StringUtils.isNumeric(str);
	}

	/**
	 * iso-8859-1转码为GBK
	 * 
	 * @param change
	 *            String
	 * @throws UnsupportedEncodingException
	 * @return String
	 */
	public static String isoToGbk(String change) {
		return changeCharset(change, "iso-8859-1", "gbk");
	}

	/**
	 * GBK转码为iso-8859-1
	 * 
	 * @param change
	 *            String
	 * @throws UnsupportedEncodingException
	 * @return String
	 */
	public static String gbkToIso(String change) {
		return changeCharset(change, "gbk", "iso-8859-1");
	}
	
	/**
	 * 校验是否为有效的手机号码<br/>
	 * 如果手机号码必须大于0位，不全为数字返回false.
	 * 
	 * @param terminal
	 * @return
	 */
	public static boolean isTerminal(String terminal) {
		if (terminal == null || terminal == "") {
			return false;
		}

		String s = "1234567890";

		// 将号码转化为char数组,判断是否都为数字
		char[] cTerminal = terminal.toCharArray();

		for (char t : cTerminal) {
			String str = String.valueOf(t);
			if (s.indexOf(str) == -1) {
				return false;
			}
		}

		return true;
	}
	
	/**
	 * 是否是有效email
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email){	       
        return true;
    }

	public static String replace(String str, String srcPattern, String dstPattern) {
		StrBuilder strBuilder = new StrBuilder(str);
		return strBuilder.replaceAll(srcPattern, dstPattern).toString();
	}

	/**
	 * 替换first字符串
	 * 
	 * @param sAll
	 * @param sOld
	 * @param sNew
	 * @return
	 */
	public static String replaceFirst(String str, String srcPattern, String dstPattern) {
		StrBuilder strBuilder = new StrBuilder(str);
		return strBuilder.replaceFirst(srcPattern, dstPattern).toString();
	}

	/**
	 * 分割字符串为数组
	 * 
	 * @param aaa
	 * @param hhh
	 * @return
	 */
	public static String[] split(String str, String sep) {
		return StringUtils.splitPreserveAllTokens(str, sep);
	}

	/**
	 * 字符串转化为boolean型
	 * 
	 * @param str
	 * @return
	 */
	public static boolean strToBool(String str) {
		return BooleanUtils.toBoolean(str);
	}

	/**
	 * 转化字符{strFrom}
	 * 
	 * @param strFrom
	 *            String 转换前字符
	 * @return int
	 * @说明:把String型转化为int型
	 */

	public static int strToInt(String str) {
		return NumberUtils.toInt(str);
	}

	public static int strToInt(String str, int defaultInt) {
		return NumberUtils.toInt(str, defaultInt);
	}
	
	/**
	 * 转化字符串 ，字符串转化为double
	 * 
	 * @param str
	 *            原始字符串
	 * @return double数据
	 */
	public static double strToDouble(String str){
		return NumberUtils.toDouble(str);
	}
	public static double strToDouble(String str,double defaultDouble){
		return NumberUtils.toDouble(str,defaultDouble);
	}

	/**
	 * 转化字符{strFrom}
	 * 
	 * @param strFrom
	 *            String 转换前字符
	 * @return long
	 * @说明:把String型转化为long型
	 */

	public static long strToLong(String str) {
		return NumberUtils.toLong(str);
	}

	/**
	 * 截取str中len长度的字符串
	 * 
	 * @param str
	 * @param len
	 * @return
	 */
	public static String subStr(String str, int len) {
		if (str == null) {
			return "";
		}
		if (str.length() > len) {
			return str.substring(0, len);
		}
		return str;
	}

	/**
	 * 转半角的函数(DBC case) 任意字符串 半角字符串 全角空格为12288，半角空格为32
	 * 其他字符半角(33-126)与全角(65281-65374)的对应关系是：均相差65248
	 * 
	 * @param input
	 * @return
	 */
	public static String toDBC(String input) {
		char[] c = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == 12288) {
				c[i] = (char) 32;
				continue;
			}
			if (c[i] > 65280 && c[i] < 65375) {
				c[i] = (char) (c[i] - 65248);
			}
		}
		return new String(c);
	}

	/**
	 * 转全角的函数(SBC case) 任意字符串 全角字符串 全角空格为12288，半角空格为32
	 * 其他字符半角(33-126)与全角(65281-65374)的对应关系是：均相差65248
	 * 
	 * @param input
	 * @return
	 */
	public static String toSBC(String input) {
		// 半角转全角：
		char[] c = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == 32) {
				c[i] = (char) 12288;
				continue;
			}
			if (c[i] < 127) {
				c[i] = (char) (c[i] + 65248);
			}
		}
		return new String(c);
	}


	/**
	 * 将字符串数组转化为分隔符号分隔的字符串
	 * 
	 * @param strs
	 * @param sep
	 * @return
	 */
	public static String join(String[] strs , String sep){
		return StringUtils.join(strs,sep);
	}
	public static String join(String[] strs){
		return join(strs,",");
	}
	
	/**
	 * 数组转化为list
	 * 
	 * @param strs
	 * @return
	 */
	public static List<String> strToList(String[] strs){
		List<String> strList = new ArrayList<String>();
		
		if(strs != null){
			for(String str : strs){
				strList.add(str);
			}			
		}
		
		return strList;		
	}
	
	/**
	 * 分隔符分隔的字符串转化为list
	 * 
	 * @param str
	 * @param sep
	 * @return
	 */
	public static List<String> strToList(String str, String sep){
		return StringUtil.strToList(StringUtil.split(str,sep));
	}
	
	/**
	 * 字符串转set
	 * 
	 * @param str
	 * @param sep
	 * @return
	 */
	public static Set<String> strToSet(String str, String sep) {
		return StringUtil.strToSet(StringUtil.split(str, sep));
	}
	
	public static Set<String> strToSet(String[] arr) {
		Set<String> hs = new LinkedHashSet<String>();
		if(arr != null){
			for(String str : arr){
				hs.add(str);
			}
		}
		return hs;
	}
}
