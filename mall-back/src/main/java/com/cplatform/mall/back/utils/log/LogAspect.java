package com.cplatform.mall.back.utils.log;

import org.aspectj.lang.JoinPoint;

import com.cplatform.mall.back.utils.LogUtils;

//@Aspect
//@Component
public class LogAspect {

	//@Autowired
	LogUtils logUtils;

	//@AfterReturning(value = "execution(public * com.cplatform.mall.back.*.service.*.*(..))", returning = "result")
	public void doAfterReturning(JoinPoint join, Object result) {
//		System.out.println("test aop------" + join.getSignature().getName());
		System.out.println("test aop------" +join.getTarget().getClass());
	}
}
