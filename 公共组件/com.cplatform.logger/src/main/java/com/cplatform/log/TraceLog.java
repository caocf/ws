package com.cplatform.log;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 跟踪注解，用于标记需要跟踪的方法.
 * <p>
 * Spring AOP设置：
 * 
 * <pre>
 *  &lt;bean name="traceLogger" class="com.cplatform.log.TraceLogger" /&gt;
 * 	&lt;aop:config&gt;
 * 		&lt;aop:aspect id="pointcut01" ref="traceLogger"&gt;
 * 			&lt;aop:pointcut expression="@annotation(com.cplatform.log.TraceLog)"
 * 				id="traceLoggerPointcut" /&gt;
 * 			&lt;aop:around pointcut-ref="pointcut01" method="around" /&gt;
 * 		&lt;/aop:aspect&gt;
 * 	&lt;/aop:config&gt;
 * </pre>
 * <p>
 * Copyright: Copyright (c) 2012-8-30 下午5:51:21
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author chengfan@c-platform.com
 * @version 1.0.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TraceLog {

	/**
	 * @return 日志级别
	 */
	String logLevel() default "TRACE";

	/**
	 * @return 是否输出参数
	 */
	boolean showParams() default true;

	/**
	 * @return 日志标题
	 */
	String subject() default "";
}
