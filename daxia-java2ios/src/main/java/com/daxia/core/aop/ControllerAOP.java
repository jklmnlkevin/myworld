package com.daxia.core.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 实现aop的一个例子，@Aspect和@Component要同时有。 可惜的是，似乎没法拦截controller层。
 * 
 * @date 2013-6-19
 * 
 */
@Aspect
@Component
public class ControllerAOP {
	private static Logger logger = Logger.getLogger(ControllerAOP.class);
	
	public static final String EDP = "execution(* com.daxia.*.*.controller..*.*(..))";
	private String clientSecret = "123498sdfasdf89ujalkdsf098asdf2332a";

	public ControllerAOP() {
		System.out.println("Create ControllerAOP...");
	}

	@Pointcut(EDP)
	public void pointcut() {
	}

	// spring中Before通知
	@Before("pointcut()")
	public void logBefore() {
	}

	// spring中After通知
	@After("pointcut()")
	public void logAfter() {
	}

	// spring中Around通知
	@Around("pointcut()")
	public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
	    return joinPoint.proceed();
	}

}
