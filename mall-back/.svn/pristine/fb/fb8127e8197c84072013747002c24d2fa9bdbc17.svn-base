package com.cplatform.mall.back.utils;

import java.math.BigDecimal;

/**
 * @Title	double型运算工具包
 * @Description
 * @Copyright: Copyright (c) 2013-10-24
 * @Company: 北京宽连十方数字技术有限公司
 * @Author chencheng
 * @Version: 1.0
 *
 */
public class DoubleUtils {
	
	/**
	 * double型数据相减，大数在前(被减数)，小数在后(减数)
	 * @param preDouble	被减数
	 * @param nextDouble	减数
	 * @return
	 */
	public static double doubleMinus(Double preDouble,Double nextDouble){
		BigDecimal nextDecimal=new BigDecimal(nextDouble.toString());
		BigDecimal preDecimal=new BigDecimal(preDouble.toString());
		return preDecimal.subtract(nextDecimal).doubleValue();
	}
	
	/**
	 * double型数据相加
	 * @param preDouble
	 * @param nextDouble
	 * @return
	 */
	public static double doubleAdd(Double preDouble,Double nextDouble){
		BigDecimal nextDecimal=new BigDecimal(nextDouble.toString());
		BigDecimal preDecimal=new BigDecimal(preDouble.toString());
		return preDecimal.add(nextDecimal).doubleValue();
	}
	
	/**
	 * 相乘
	 * @param preDouble
	 * @param nextDouble
	 * @return
	 */
	public static double doubleMultipy(Double preDouble,Double nextDouble){
		BigDecimal nextDecimal=new BigDecimal(nextDouble.toString());
		BigDecimal preDecimal=new BigDecimal(preDouble.toString());
		return preDecimal.multiply(nextDecimal).doubleValue();
	}
	
	/**
	 * 相除
	 * @param preDouble	被除数
	 * @param nextDouble	除数
	 * @return
	 */
	public static double doubleDivide(Double preDouble,Double nextDouble){
		BigDecimal nextDecimal=new BigDecimal(nextDouble.toString());
		BigDecimal preDecimal=new BigDecimal(preDouble.toString());
		return preDecimal.divide(nextDecimal).doubleValue();
	}

}
