package com.slabs.expense.tracker.core.exception;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.slabs.expense.tracker.core.ResponseGenerator;
import com.slabs.expense.tracker.core.ResponseStatus;

/**
 * {@link ExpenseTrackerException} is a custom exception class implementing
 * {@link ExceptionMapper} of Jersey implementation.
 * 
 * @author Shyam Natarajan
 *
 */
@Provider
public class ExpenseTrackerException extends Exception
		implements ExceptionMapper<ExpenseTrackerException> {

	private static final long serialVersionUID = -9175347861593491223L;

	private ResponseStatus status;

	@Context
	private HttpHeaders headers;

	public ExpenseTrackerException() {
		super();
	}

	public ExpenseTrackerException(String message, ResponseStatus status, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.status = status;
	}

	public ExpenseTrackerException(String message, ResponseStatus status, Throwable cause) {
		super(message, cause);
		this.status = status;
	}

	public ExpenseTrackerException(String message, ResponseStatus status) {
		super(message);
		this.status = status;
	}

	public ExpenseTrackerException(Throwable cause, ResponseStatus status) {
		super(cause);
		this.status = status;
	}

	/**
	 * This method will return {@link Response} for the exception that is passed
	 * to this method.
	 * 
	 * @param ex
	 *            - {@link ExpenseTrackerException}
	 * @return - {@link Response}
	 * 
	 */
	@Override
	public Response toResponse(ExpenseTrackerException ex) {

		return Response.status(ex.status.getStatusCode())
				.entity(ResponseGenerator.getExceptionResponse(ex.status, ex))
				.type(headers.getMediaType()).build();
	}

}
