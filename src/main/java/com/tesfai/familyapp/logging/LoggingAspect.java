package com.tesfai.familyapp.logging;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
	
	private static final Logger LOGGER =LoggerFactory.getLogger(LoggingAspect.class);
	//called before every method call
	@Before("execution(public * com.example.FamilySite.controller.MemberController.*(..))")
	public void logBefore() {
		LOGGER.info(".......Method called Before.....");
	}
	
	//called after every method call irrespective of exception or return successfully
	@After("execution(public * com.example.FamilySite.controller.MemberController.*(..))")
	public void logAfter() {
		LOGGER.info(".......Method called After.....");
	}

	//called after throwing an exception
	@AfterThrowing("execution(public * com.example.FamilySite.controller.MemberController.*(..))")
	public void logAfterThrowing() {
		LOGGER.info(".......Method called After Throwing Exception.....");
	}
	
	//called after successful execution
	@AfterReturning("execution(public * com.example.FamilySite.controller.MemberController.*(..))")
	public void logAfterReturning() {
		LOGGER.info(".......Method called After Returning  value.....");
	}
}
