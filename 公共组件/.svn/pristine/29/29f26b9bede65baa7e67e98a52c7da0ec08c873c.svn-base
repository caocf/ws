package com.cplatform.log;

import java.lang.reflect.Method;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;

/**
 * 跟踪日志记录器.
 * <p>
 * <p>
 * Copyright: Copyright (c) 2013-5-28 上午7:13:21
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author chengfan@c-platform.com
 * @version 1.0.0
 */
public class TraceLogger {

	/** 日志级别 */
	private String loggerLevel;

	/** 日志名称 */
	private String loggerName;

	/** parameterNameDiscoverer */
	private ParameterNameDiscoverer parameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();

	/** 是否展示参数 */
	private Boolean showParams;

	/** 对象toString的方法: json bean */
	private String toStringType = null;

	/**
	 * 跟踪方法，aop:around方式触发
	 * 
	 * @param pjp
	 *            Proceeding Join Point
	 * @return Method return value
	 * @throws Throwable
	 *             Throwable
	 */
	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		// 默认设置
		Level logLevel = Level.TRACE;
		String subject = "";
		boolean showParam = true;
		Method method = null;

		// 获取跟踪对象和参数
		Signature signature = pjp.getSignature();
		if (signature instanceof MethodSignature) {
			MethodSignature methodSignature = (MethodSignature) signature;
			method = methodSignature.getMethod();
			TraceLog traceLog = method.getAnnotation(TraceLog.class);
			if (traceLog != null) {
				logLevel = Level.toLevel(traceLog.logLevel());
				if (loggerLevel != null) {
					logLevel = Level.toLevel(loggerLevel);
				}
				subject = traceLog.subject();
				showParam = traceLog.showParams();
			}
		}

		// 日志
		String className = pjp.getSignature().getDeclaringType().getName();
		String name = (loggerName == null) ? className : loggerName;
		Logger logger = Logger.getLogger(name);
		boolean needLog = logger.isEnabledFor(logLevel);

		long time0 = System.nanoTime();

		// 记录方法执行前日志
		if (needLog) {
			StringBuilder buf = new StringBuilder();
			buf.append(subject).append(" ").append(signature).append(" start ");
			if ((this.showParams != null && showParam) || (showParams != null && showParams) || (showParams == null && showParam)) {
				buf.append("(");
				String[] paramNames = parameterNameDiscoverer.getParameterNames(method);
				for (int i = 0; i < pjp.getArgs().length; i++) {
					Object obj = pjp.getArgs()[i];
					if (paramNames != null) {
						buf.append(paramNames[i]);
					} else {
						buf.append("p").append(i);
					}
					buf.append("=").append(objToString(obj)).append(", ");
				}
				buf.setLength(buf.length() - 2);
				buf.append(")");
			} else {
				buf.append("(...)");
			}
			logger.log(logLevel, buf.toString().trim());
		}

		// 执行
		try {
			Object retVal = pjp.proceed();
			// 记录执行后日志
			if (needLog) {
				long time1 = System.nanoTime();
				StringBuilder buf = new StringBuilder();
				buf.append(subject).append(" ").append(signature).append(" finish ");
				buf.append("(").append(getTime(time0, time1)).append("ms)");
				// 判断响应结果
				if (method != null && method.getReturnType() != Void.TYPE) {
					buf.append(" ").append(objToString(retVal));
				}
				logger.log(logLevel, buf.toString().trim());
			}
			return retVal;
		}
		catch (Exception ex) {
			// 记录异常日志
			if (needLog) {
				long time1 = System.nanoTime();
				StringBuilder buf = new StringBuilder();
				if (loggerName != null) {
					buf.append(className).append(" ");
				}
				buf.append(subject).append(" exception ");
				buf.append("(").append(getTime(time0, time1)).append("ms)");
				buf.append(": ").append(ex);
				logger.log(logLevel, buf.toString().trim(), ex);
			}
			throw ex;
		}

	}

	public String getLoggerLevel() {
		return loggerLevel;
	}

	public String getLoggerName() {
		return loggerName;
	}

	/**
	 * 获取 showParams
	 * 
	 * @return showParams
	 */
	public Boolean getShowParams() {
		return showParams;
	}

	/**
	 * 获取毫秒数
	 * 
	 * @param time0
	 *            时间点0
	 * @param time1
	 *            时间点1
	 * @return 0.00格式的毫秒数
	 */
	private String getTime(long time0, long time1) {
		double t = Math.abs(time1 - time0);
		t = t / 1000000;
		return String.format("%.2f", t);
	}

	/**
	 * 获取 toStringType
	 * 
	 * @return toStringType
	 */
	public String getToStringType() {
		return toStringType;
	}

	/**
	 * 依据指定的方法转换对象成String
	 * 
	 * @param obj
	 *            需要转换的对象
	 * @return 依据指定的方法转换成的String
	 */
	private String objToString(Object obj) {
		try {
			if (obj instanceof Boolean) {
				return String.valueOf(obj);
			} else if (obj instanceof Number) {
				return String.valueOf(obj);
			} else if (obj instanceof CharSequence) {
				return String.valueOf(obj);
			} else if (obj instanceof Character) {
				return String.valueOf(obj);
			} else if (obj != null && "json".equals(this.toStringType)) {
				return JSONObject.fromObject(obj).toString();
			} else if (obj != null && "bean".equals(this.toStringType)) {
				@SuppressWarnings("rawtypes")
				Map map = BeanUtils.describe(obj);
				return String.valueOf(map);
			} else if (obj != null && "none".equals(this.toStringType)) {
				return "?";
			} else {
				return String.valueOf(obj);
			}
		}
		catch (Exception ex) {
			return String.valueOf(obj);
		}
	}

	public void setLoggerLevel(String loggerLevel) {
		this.loggerLevel = loggerLevel;
	}

	public void setLoggerName(String loggerName) {
		this.loggerName = loggerName;
	}

	/**
	 * 设置 showParams
	 * 
	 * @param showParams
	 *            showParams
	 */
	public void setShowParams(Boolean showParams) {
		this.showParams = showParams;
	}

	/**
	 * 设置 toStringType
	 * 
	 * @param toStringType
	 *            toStringType
	 */
	public void setToStringType(String toStringType) {
		this.toStringType = toStringType;
	}
}
