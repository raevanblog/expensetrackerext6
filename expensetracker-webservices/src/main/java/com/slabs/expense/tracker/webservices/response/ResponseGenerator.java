package com.slabs.expense.tracker.webservices.response;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import com.slabs.expense.tracker.common.webservice.response.Operation;
import com.slabs.expense.tracker.common.webservice.response.Response;
import com.slabs.expense.tracker.common.webservice.response.Result;
import com.slabs.expense.tracker.webservices.core.MessageConstants;

import net.sf.dynamicreports.jasper.builder.JasperConcatenatedReportBuilder;
import net.sf.dynamicreports.report.exception.DRException;

/**
 * {@link ResponseGenerator} will generate a {@link Response} or
 * {@link javax.ws.rs.core.Response}.
 * 
 * @author Shyam Natarajan
 *
 */
public class ResponseGenerator {

	/**
	 * 
	 * @param status
	 *            - {@link ResponseStatus}
	 * @param cause
	 *            - {@link Throwable}
	 * @return {@link Response}
	 * 
	 * 
	 */
	public static Response getExceptionResponse(ResponseStatus status, Throwable cause) {
		Response response = new Response();
		response.setStatusCode(status.getStatusCode());
		response.setStatus(status.getMessage());
		response.setSuccess(Boolean.FALSE);
		response.setMessage(cause.getMessage());
		return response;
	}

	/**
	 * 
	 * @param status
	 *            - {@link ResponseStatus}
	 * @param message
	 *            - Message to be added to the response.
	 * @return {@link Response}
	 */
	public static Response getExceptionResponse(ResponseStatus status, String message) {
		Response response = new Response();
		response.setStatusCode(status.getStatusCode());
		response.setStatus(status.getMessage());
		response.setSuccess(Boolean.FALSE);
		response.setMessage(message);
		return response;
	}

	private static Response getSuccessResponse() {
		Response response = new Response();
		response.setStatusCode(ResponseStatus.OK.getStatusCode());
		response.setStatus(ResponseStatus.OK.getMessage());
		response.setSuccess(Boolean.TRUE);
		return response;
	}

	/**
	 * 
	 * @param o
	 *            - {@link Object}
	 * @param message
	 *            - {@link String}
	 * @return {@link Response}
	 */
	public static Response getSuccessResponse(Object o, String message) {
		Response response = getSuccessResponse();
		Result result = new Result();
		result.getAny().add(o);
		response.setMessage(message);
		response.setResult(result);
		return response;
	}

	/**
	 * 
	 * 
	 * @param message
	 *            - {@link String}
	 * @return {@link Response}
	 */
	public static Response getSuccessResponse(String message) {
		Response response = getSuccessResponse();
		response.setMessage(message);
		return response;
	}

	/**
	 * 
	 * @param o
	 *            - {@link Object}
	 * @param operation
	 *            - {@link Operation}
	 * @param message
	 *            - {@link String}
	 * 
	 * @return {@link Response}
	 */
	public static Response getSuccessResponse(Object o, Operation operation) {
		List<Object> list = new ArrayList<Object>();
		list.add(o);
		return getSuccessResponse(list, operation);
	}

	/**
	 * 
	 * @param list
	 *            - List of entities.
	 * @param operation
	 *            - {@link Operation}
	 * @return {@link Response}
	 */
	public static Response getSuccessResponse(List<? extends Object> list, Operation operation) {
		Response response = getSuccessResponse();
		Result result = new Result();
		result.setOperation(operation);
		if (list != null && !list.isEmpty()) {
			result.setNoOfRecords(list.size());
			result.getAny().addAll(list);
			if (Operation.INSERT == operation) {
				result.setMessage(MessageConstants.DATA_INSERTED);
			} else {
				result.setMessage(MessageConstants.DATA_FOUND);
			}
		} else {
			if (Operation.INSERT == operation) {
				result.setMessage(MessageConstants.NO_DATA_INSERTED);
			} else {
				result.setMessage(MessageConstants.NO_DATA_FOUND);
			}
			result.setNoOfRecords(0);
		}
		response.setResult(result);
		return response;
	}

	/**
	 * This method will build a response for INSERT, DELETE, UPDATE operations
	 * based on the noOfRecords affected.
	 * 
	 * @param noOfRecords
	 *            - No of Records.
	 * @param operation
	 *            - {@link Operation}
	 * @return {@link Response}
	 */
	public static Response getSuccessResponse(int noOfRecords, Operation operation) {
		Response response = getSuccessResponse();

		Result result = new Result();
		result.setNoOfRecords(noOfRecords);
		result.setOperation(operation);

		if (noOfRecords == 0) {
			if (Operation.INSERT == operation) {
				result.setMessage(MessageConstants.NO_DATA_INSERTED);
			} else {
				result.setMessage(MessageConstants.NO_DATA_FOUND);
			}
		} else {
			if (Operation.INSERT == operation) {
				result.setMessage(MessageConstants.DATA_INSERTED);
			} else {
				result.setMessage(MessageConstants.DATA_FOUND);
			}
		}

		response.setResult(result);
		return response;
	}

	public static void writeResponse(JasperConcatenatedReportBuilder builder, OutputStream outputStream, MediaType type, String fileName)
			throws DRException, IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.add("content-disposition", "inline; filename = " + fileName);
		headers.add("content-type", type.toString());
		builder.toPdf(outputStream);
		outputStream.flush();
	}

	public static void writeResponse(String html, OutputStream outputStream, String fileName) throws DRException, IOException {
		PrintStream stream = new PrintStream(outputStream);

		HttpHeaders headers = new HttpHeaders();
		headers.add("content-disposition", "inline; filename = " + fileName);
		headers.add("content-type", MediaType.TEXT_HTML.toString());

		stream.print(html);
		stream.flush();
		stream.close();
	}

}
