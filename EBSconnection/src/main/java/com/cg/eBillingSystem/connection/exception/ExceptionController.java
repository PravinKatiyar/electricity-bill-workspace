package com.cg.eBillingSystem.connection.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class ExceptionController {

	@ExceptionHandler(value = EntityNotFoundException.class)
	/**
	 * 
	 * @param exception
	 * @return
	 */
	public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException exception) {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}
}
