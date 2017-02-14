package com.slabs.expense.tracker.webservices.exception;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.slabs.expense.tracker.core.exception.ExpenseTrackerException;
import com.slabs.expense.tracker.webservices.core.ResponseGenerator;
import com.slabs.expense.tracker.webservices.core.ResponseStatus;


/**
 * {@link WebServiceException} is a custom exception class implementing
 * {@link ExceptionMapper} of Jersey implementation.
 * 
 * 
 * @author Shyam Natarajan
 *
 */
@Provider
public class WebServiceException extends ExpenseTrackerException
		implements ExceptionMapper<WebServiceException> {

	private static final long serialVersionUID = 4186663480879812030L;

	private ResponseStatus status;

	@Context
	private HttpHeaders headers;

	public WebServiceException() {
		super();
	}

	public WebServiceException(String message, ResponseStatus status, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.status = status;
	}

	public WebServiceException(String message, ResponseStatus status, Throwable cause) {
		super(message, cause);
		this.status = status;
	}

	public WebServiceException(String message, ResponseStatus status) {
		super(message);
		this.status = status;
	}

	public WebServiceException(Throwable cause, ResponseStatus status) {
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
	public Response toResponse(WebServiceException exception) {

		return Response.status(exception.status.getStatusCode())
				.entity(ResponseGenerator.getExceptionResponse(exception.status, exception))
				.type(headers.getMediaType()).build();
	}

}
