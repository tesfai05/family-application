package com.tesfai.familyapp.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.tesfai.familyapp.controller.MemberController;



@RestControllerAdvice
public class ExceptionClass extends Exception{
	
	private static final long serialVersionUID = 1L;
	private final Logger log=LoggerFactory.getLogger(MemberController.class);
	
	@ExceptionHandler(MemberNotFoundException.class)
	private final ResponseEntity<Object> handleMemberNotFoundException(MemberNotFoundException ex, WebRequest request) {
		log.error(ex.getMessage());
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(MemberAlreadyExistsException.class)
	private final ResponseEntity<Object> handleMemberAlreadyExistsException(MemberAlreadyExistsException ex, WebRequest request) {
		log.error(ex.getMessage());
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
	}


	@ExceptionHandler(Exception.class)
	private final ResponseEntity<Object> handleException(Exception ex, WebRequest request) {
		log.error(ex.getMessage());
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
