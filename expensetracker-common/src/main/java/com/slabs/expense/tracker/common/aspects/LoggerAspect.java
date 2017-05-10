package com.slabs.expense.tracker.common.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAspect {

	private static final Logger L = LoggerFactory.getLogger(LoggerAspect.class);

	@Before("execution(* com.slabs.expense.tracker..*.*(..))")
	public void logException(JoinPoint point) throws Throwable {
		L.error("Exception occurred, ============= {}", point.getSignature().toString());
	}
}
