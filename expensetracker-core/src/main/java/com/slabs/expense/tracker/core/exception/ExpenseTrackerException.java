package com.slabs.expense.tracker.core.exception;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ExpenseTrackerException extends Exception implements ExceptionMapper<ExpenseTrackerException> {

	private static final long serialVersionUID = -9175347861593491223L;

	private ErrorStatus status;
	
	@Context
	private HttpHeaders headers;

	public ExpenseTrackerException() {
		super();
	}

	public ExpenseTrackerException(String message, ErrorStatus status, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.status = status;
	}

	public ExpenseTrackerException(String message, ErrorStatus status, Throwable cause) {
		super(message, cause);
		this.status = status;
	}

	public ExpenseTrackerException(String message, ErrorStatus status) {
		super(message);
		this.status = status;
	}

	public ExpenseTrackerException(Throwable cause, ErrorStatus status) {
		super(cause);
		this.status = status;
	}

	public Response toResponse(ExpenseTrackerException ex) {

		return Response.status(ex.status.getErrorCode())
				.entity(new ExceptionResponse(ex.status.getErrorCode(), ex.status.name(), ex.getMessage()))
				.type(headers.getMediaType()).build();
	}

}
