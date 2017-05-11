package com.slabs.expensetracker.webservices.core;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.slabs.expensetracker.common.exception.ExpenseTrackerException;
import com.slabs.expensetracker.common.webservice.response.Response;
import com.slabs.expensetracker.webservices.response.ResponseGenerator;

@ControllerAdvice
public class ExceptionProcessor {

	@ExceptionHandler(ExpenseTrackerException.class)
	public ResponseEntity<Response> processException(ExpenseTrackerException exception) {
		return new ResponseEntity<Response>(ResponseGenerator.getExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage()),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
