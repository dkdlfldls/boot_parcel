package com.parcel.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Component;

@Aspect
public class MethodTracker {
	private Logger logger = Logger.getLogger(getClass());
	
	@Before(value="execution(* *..*Repository.*(..))")
	public void trackMethod(JoinPoint point) {
		String className = point.getSignature().getDeclaringTypeName();
		String name = point.getSignature().getName();
		Object[] newValues = point.getArgs();
		
		logger.info("className : " + className + "    " + "name : " + name);
	}

	
	
}
