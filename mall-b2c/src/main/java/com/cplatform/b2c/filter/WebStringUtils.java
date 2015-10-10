package com.cplatform.b2c.filter;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * url过来scripte,html,style详细说明.
 * <p>
 * Copyright: Copyright (c) 2014-1-6 15:24:00
 * <p>
 * Company: 苏州宽连信息技术有限公司
 * <p>
 * 
 * @author yangxm@c-platform.com
 */
public class WebStringUtils {

	protected static final Logger logger = Logger.getLogger(WebStringUtils.class);

	public final static String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>";

	public final static String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>";

	public final static String regEx_html = "<[^>]+>";

	public final static String regEx_char = "^[A-Z]$";

	public final static String regEx_english = "^[a-zA-Z]+$";

	public final static String regEx_letter_num = "^[0-9a-zA-Z]+$";

	public final static Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);

	public final static Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);

	public final static Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);

	public final static Pattern p_char = Pattern.compile(regEx_char);

	public final static Pattern p_english = Pattern.compile(regEx_english, Pattern.CASE_INSENSITIVE);

	public final static Pattern p_letter_num = Pattern.compile(regEx_letter_num, Pattern.CASE_INSENSITIVE);

	public final static Pattern p_js_envent = Pattern.compile("^.*\\s+on[a-zA-Z]+\\s*=\\s*\\S+.*$", Pattern.CASE_INSENSITIVE);

	public final static FileItemFactory factory = new DiskFileItemFactory();

	public final static ServletFileUpload upload = new ServletFileUpload(factory);

	/**
	 * 判断字符是否中文字符
	 * 
	 * @param c
	 * @return
	 */
	public static boolean isChinese(char c) {

		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);

		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS

		|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS

		|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A

		|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION

		|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION

		|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
			return true;
		}
		return false;
	}

	/**
	 * 获取字符长度,用于控制页面显示
	 * 
	 * @param strName
	 * @param showLength
	 * @return
	 */
	public static String getShowString(String strName, int showLength) {
		if (StringUtils.isEmpty(strName)) {
			return "";
		}
		int length = 0;
		int i = 0;
		char[] ch = strName.toCharArray();
		for (; i < ch.length; i++) {
			char c = ch[i];
			if (isChinese(c) == true) {
				length += 2;
			} else {
				length += 1;
			}
			if (length >= showLength) {
				break;
			}
		}
		if (i >= ch.length - 1) {
			return strName;
		}
		return strName.substring(0, i + 1) + "...";
	}

	/**
	 * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
	 * 
	 * @param v1
	 *            被除数
	 * @param v2
	 *            除数
	 * @param scale
	 *            表示表示需要精确到小数点以后几位。
	 * @return 两个参数的商
	 */
	public static double div(double v1, double v2, int scale) {
		if (scale < 0) {
			return 0;
		}
		BigDecimal b1 = new BigDecimal(String.valueOf(v1));
		BigDecimal b2 = new BigDecimal(String.valueOf(v2));
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
	 * 
	 * @param v1
	 *            被除数
	 * @param v2
	 *            除数
	 * @param scale
	 *            表示表示需要精确到小数点以后几位。
	 * @return 两个参数的商
	 */
	public static double div(Long v1, Long v2, int scale) {
		if (scale < 0) {
			return 0;
		}
		BigDecimal b1 = new BigDecimal(String.valueOf(v1));
		BigDecimal b2 = new BigDecimal(String.valueOf(v2));
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 获取当前数值的货币格式
	 * 
	 * @param v1
	 * @return
	 */
	public static String parseRingPrice(Integer v1) {
		if (null == v1) {
			return "";
		}
		BigDecimal b1 = new BigDecimal(String.valueOf(v1));
		BigDecimal b2 = new BigDecimal("100");
		double temp = b1.divide(b2, 2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return NumberFormat.getCurrencyInstance().format(temp).substring(1);
	}

	public static String parseString(String input, String defaultStr) {
		if (StringUtils.isEmpty(input)) {
			return defaultStr;
		}
		return input.trim();
	}

	/**
	 * @param input
	 * @param tokenChar
	 * @return
	 */
	public static String[] parseStringToArray(String input, String tokenChar) {
		if (StringUtils.isEmpty(input)) {
			return null;
		}
		String[] returns = input.split(tokenChar);
		return returns;
	}

	/**
	 * 将字符串分成等长的数组
	 * 
	 * @param input
	 * @param limitLength
	 * @return
	 */
	public static List getStringToFixedLength(String input, int limitLength) {
		List rl = new ArrayList();
		if (StringUtils.isEmpty(input)) {
			return rl;
		}
		int length = 0;
		int i = 0;
		int start = 0;
		char[] ch = input.toCharArray();
		for (; i < ch.length; i++) {
			char c = ch[i];
			if (isChinese(c) == true) {
				length += 2;
			} else {
				length += 1;
			}
			if (length >= limitLength || i == ch.length - 1) {
				rl.add(input.substring(start, i + 1));
				start = i + 1;
				length = 0;
			}
		}
		return rl;
	}

	/**
	 * 将空的字符串设置默认值
	 * 
	 * @param input
	 * @param defaultStr
	 * @return
	 */
	public static String convertNull(String input, String defaultStr) {
		if (StringUtils.isEmpty(input)) {
			return defaultStr;
		}
		return input.trim();
	}

	/**
	 * 将空的字符串
	 * 
	 * @param input
	 * @return
	 */
	public static String convertNull(String input) {
		if (StringUtils.isEmpty(input)) {
			return "";
		}
		return input.trim();
	}

	/**
	 * 按照正则表达式寻找合适的字符
	 * 
	 * @param source
	 * @param regex
	 * @return
	 */
	public static String getMatchedString(String source, String regex, int indx) {
		if (StringUtils.isEmpty(source)) {
			return "";
		}
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(source);
		String temp = "";
		if (matcher.find()) {
			temp = matcher.group(indx);
			if (null != temp)
				temp = temp.trim();
		}
		return temp;
	}

	public static void main(String[] args) {
		String regex = "<title([\t ]*|[\t ]+.*)>(.*)</title>";
		String source = "<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; "
		        + "charset=UTF-8\"><title>首发百分百</title><link rel=stylesheet\" " + "href=\"../../css/sf/style1.css\" type=\"text/css\">";
		getMatchedString(source, regex, 2);
	}

	public static String html2Text(String inputString) {
		// 含html标签的字符串
		String htmlStr = inputString;
		String textStr = "";
		Matcher m_script;
		Matcher m_style;
		Matcher m_html;
		try {
			m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll(""); // 过滤script标签
			m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll(""); // 过滤style标签
			m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll(""); // 过滤html标签
			textStr = htmlStr;
		}
		catch (Exception e) {
			logger.error("Html2Text: " + e.getMessage());
		}
		return textStr;// 返回文本字符串

	}

	/**
	 * 判断字符串中是否包含脚本语言
	 * 
	 * @param htmlStr
	 * @return
	 */
	public static boolean ifContainXss(String htmlStr) {
		try {
			if (p_script.matcher(htmlStr).find()) {
				return true;
			}
			if (p_style.matcher(htmlStr).find()) {
				return true;
			}
			if (p_html.matcher(htmlStr).find()) {
				return true;
			}
			if (p_js_envent.matcher(htmlStr).find()) {
				return true;
			}
		}
		catch (Exception e) {
			logger.error("Html2Text: " + e.getMessage());
		}
		return false;// 返回文本字符串
	}

	/**
	 * 判断单个字符串
	 * 
	 * @param htmlStr
	 * @return
	 */
	public static boolean checkSingerChar(String htmlStr) {
		try {
			if (p_char.matcher(htmlStr).find()) {
				return true;
			}
		}
		catch (Exception e) {
			logger.error("checkSingerChar: " + e.getMessage());
		}
		return false;// 返回文本字符串
	}

	/**
	 * 判断英文字符串
	 * 
	 * @param htmlStr
	 * @return
	 */
	public static boolean checkEnglish(String htmlStr) {
		try {
			if (p_english.matcher(htmlStr).find()) {
				return true;
			}
		}
		catch (Exception e) {
			logger.error("checkSingerChar: " + e.getMessage());
		}
		return false;// 返回文本字符串
	}

	/**
	 * 判断英文字符和数字串
	 * 
	 * @param htmlStr
	 * @return
	 */
	public static boolean checkEnglishAndNum(String htmlStr) {
		try {
			if (p_letter_num.matcher(htmlStr).find()) {
				return true;
			}
		}
		catch (Exception e) {
			logger.error("checkSingerChar: " + e.getMessage());
		}
		return false;// 返回文本字符串
	}

	/**
	 * 判断是否包含JS事件
	 * 
	 * @param htmlStr
	 * @return
	 */
	public static boolean checkIFJsEvent(String htmlStr) {
		try {
			if (p_js_envent.matcher(htmlStr).find()) {
				return true;
			}
		}
		catch (Exception e) {
			logger.error("checkSingerChar: " + e.getMessage());
		}
		return false;// 返回文本字符串
	}

	/**
	 * 按照正则表达式寻找合适的字符
	 * 
	 * @param source
	 * @param regex
	 * @return
	 */
	public static boolean match(String source, String regex) {
		if (StringUtils.isEmpty(source)) {
			return false;
		}
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(source);
		if (matcher.find()) {
			return true;
		}
		return false;
	}

	public static boolean ifContainXSS(String value) {
		if (StringUtils.isEmpty(value)) {
			return false;
		}
		value = value.toLowerCase();
		if (value.indexOf("eval\\((.*)\\)") != -1) {
			return true;
		}
		if (value.indexOf("[\\\"\\\'][ \\s]*javascript:(.*)[\\\"\\\']") != -1) {
			return true;
		}
		if (value.indexOf("script") != -1) {
			return true;
		}
		if (value.indexOf("@import") != -1) {
			return true;
		}
		if (value.indexOf("xss:expression") != -1) {
			return true;
		}
		if (value.indexOf("\\$") != -1) {
			return true;
		}
		// 获取需要校验的非法字符

		/*
		 * String invalidChar =
		 * sysconfig.querySysConfigValue("CHECK_VALID_CHAR");
		 * if(StringUtils.isEmpty(invalidChar)) { return false; } String[]
		 * invalidArray = invalidChar.split("#"); for(int a = 0; a <
		 * invalidArray.length; a++) {
		 * if(value.indexOf(invalidArray[a].toLowerCase()) != -1) { return true;
		 * } }
		 */
		return false;
	}

	/**
	 * 获取各种类型表单的表单参数
	 * 
	 * @paramrequest HttpServletRequest请求对像
	 * @paramparamName 参数名
	 * @return
	 * @throws Exception
	 * @throwsFileUploadException
	 */
	public static boolean uploadRequestIfXss(HttpServletRequest request) {
		List fileItemList = null;
		try {
			fileItemList = upload.parseRequest(request);
		}
		catch (FileUploadException e) {
			logger.error("" + e);
		}
		if (fileItemList != null) {
			for (Iterator itr = fileItemList.iterator(); itr.hasNext();) {
				FileItem fileItem = (FileItem) itr.next();
				String val = null;
				try {
					if (null != fileItem.getString() && StringUtils.isEmpty(fileItem.getContentType()))
						val = new String(fileItem.getString().getBytes("ISO8859-1"));
				}
				catch (UnsupportedEncodingException e) {
					logger.error("" + e);
				}
				if (WebStringUtils.ifContainXSS(val) || WebStringUtils.ifContainXss(val)) {
					logger.error("参数包含非法字符:" + val);
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 过滤掉输入内容中的script脚本和html字符
	 * 
	 * @param htmlInput
	 * @return
	 */
	public static String filterHtmlInput(String htmlInput) {
		if (StringUtils.isEmpty(htmlInput)) {
			return null;
		}

		htmlInput = htmlInput.replaceAll(regEx_script, "");
		htmlInput = htmlInput.replaceAll(regEx_html, "");
		return htmlInput;
	}

}
