package com.parcel.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Component;

/**
 * Method에 대한 AOP설정
 * @author Spring
 * AOP(Aspect oriented programming)
 */
@Aspect
public class MethodTracker {
	private Logger logger = Logger.getLogger(getClass());
	
	
	/**
	 * 메소드가 실행되는 기록을 시스템 로그에 남긴다.
	 * 어드바이스 = Before
	 * 포인트컷 = * *..*Repository.*(..)
	 * @param point
	 */
	@Before(value="execution(* *..*Repository.*(..))")
	public void trackMethod(JoinPoint point) {
		String className = point.getSignature().getDeclaringTypeName();
		String name = point.getSignature().getName();
		Object[] newValues = point.getArgs();
		
		logger.info("className : " + className + "    " + "name : " + name);
	}

	
	
}
