package com.slabs.expensetracker.common.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.slabs.expensetracker.common.exception.ExpenseTrackerException;

@Aspect
@Component
public class LoggerAspect {

	private static final Logger L = LoggerFactory.getLogger(LoggerAspect.class);

	@AfterThrowing(pointcut = "within(@org.springframework.web.bind.annotation.RestController *)", throwing = "exception")
	public void logException(JoinPoint point, ExpenseTrackerException exception) throws Throwable {
		Throwable cause = exception.getCause();
		if (cause == null) {
			L.error("Exception occurred while executing {}:{} :: {}", point.getSignature().getDeclaringTypeName(), point.getSignature().getName(), exception.getMessage());
		} else {
			L.error("Exception occurred while executing {}:{} :: {} :: Cause, {}", point.getSignature().getDeclaringTypeName(), point.getSignature().getName(), exception, cause.getMessage());
		}
	}
}
