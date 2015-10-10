package com.cplatform.mall.dc.utils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * 数据类型格式工具类
 * 
 * @author zhuangxx
 * @since 2013-09-10
 */
public class DataUtil {

	public static final byte STRING = -1;

	public static final byte INTEGER = 0;

	public static final byte DECIMAL_2 = 2;

	public static final byte DECIMAL_4 = 4;

	public static final byte PERCENT_2 = 12;

	public static final byte PERCENT_4 = 14;

	public static final byte DATE = 15;

	private static DecimalFormat df = new DecimalFormat("0");

	private static DecimalFormat df2 = new DecimalFormat("0.00");

	private static DecimalFormat df4 = new DecimalFormat("0.0000");

	private static DecimalFormat df12 = new DecimalFormat("0.00%");

	private static DecimalFormat df14 = new DecimalFormat("0.0000%");

	/**
	 * 转换成固定格式
	 * 
	 * @param value
	 *            值
	 * @param type
	 *            类型
	 * @return 固定格式
	 */
	public static String getRound(String value, byte type) {
		try {
			if (StringUtils.isBlank(value)) {
				return "";
			}
			double val = Double.parseDouble(value);
			return getRound(val, type);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 转换成固定格式
	 * 
	 * @param value
	 *            值
	 * @param type
	 *            类型
	 * @return 固定格式
	 */
	public static String getRound(double value, byte type) {
		String result = null;
		try {
			switch (type) {
				case INTEGER:
					result = df.format(value);
					break;
				case DECIMAL_2:
					result = df2.format(value);
					break;
				case DECIMAL_4:
					result = df4.format(value);
					break;
				case PERCENT_2:
					result = df12.format(value);
					break;
				case PERCENT_4:
					result = df14.format(value);
					break;
				case DATE:
					result = "00";
					int second = Integer.parseInt(df.format(value));
					int minute = second / 60;
					second = second % 60;
					if (minute <= 9) {
						result = result + ":0" + minute;
					} else {
						result = result + ":" + minute;
					}
					if (second <= 9) {
						result = result + ":0" + second;
					} else {
						result = result + ":" + second;
					}
					break;
				default:
					result = String.valueOf(value);
					break;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 转换成固定格式
	 * 
	 * @param values
	 *            值
	 * @param types
	 *            类型
	 * @return 固定格式
	 */
	public static List<String> getRound(double[] values, byte[] types) {
		List<String> result = new ArrayList<String>(values.length);
		for (int i = 0; i < values.length; i++) {
			try {
				switch (types[i]) {
					case INTEGER:
						result.add(df.format(values[i]));
						break;
					case DECIMAL_2:
						result.add(df2.format(values[i]));
						break;
					case DECIMAL_4:
						result.add(df4.format(values[i]));
						break;
					case PERCENT_2:
						result.add(df12.format(values[i]));
						break;
					case PERCENT_4:
						result.add(df14.format(values[i]));
						break;
					default:
						result.add(String.valueOf(values[i]));
						break;
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
