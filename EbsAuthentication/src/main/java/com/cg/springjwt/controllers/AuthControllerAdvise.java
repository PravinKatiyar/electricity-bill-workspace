package com.cg.springjwt.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

//@RestControllerAdvice
public class AuthControllerAdvise {

//	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handlerOtherException(Exception exp){
		return new ResponseEntity<String>(exp.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
