package com.slabs.expense.tracker.core;

import java.util.ArrayList;
import java.util.List;

import com.slabs.expense.tracker.webservice.response.Operation;
import com.slabs.expense.tracker.webservice.response.Response;
import com.slabs.expense.tracker.webservice.response.Result;

public class ResponseGenerator {

	private static final String NO_DATA_FOUND = "NO_DATA_FOUND";

	private static final String DATA_FOUND = "DATA_FOUND";

	private static final String DATA_INSERTED = "DATA_INSERTED";

	private static final String NO_DATA_INSERTED = "NO_DATA_INSERTED";

	public static Response getExceptionResponse(ResponseStatus status, Throwable cause) {
		Response resp = new Response();
		resp.setStatus_Code(status.getStatusCode());
		resp.setStatus_Message(status.getMessage());
		resp.setSuccess(false);
		resp.setException(cause.getMessage());
		return resp;
	}

	public static Response getExceptionResponse(ResponseStatus status, String message) {
		Response resp = new Response();
		resp.setStatus_Code(status.getStatusCode());
		resp.setStatus_Message(status.getMessage());
		resp.setSuccess(false);
		resp.setException(message);
		return resp;
	}

	private static Response getSucessResponse() {
		Response response = new Response();
		response.setStatus_Code(ResponseStatus.OK.getStatusCode());
		response.setStatus_Message(ResponseStatus.OK.getMessage());
		response.setSuccess(true);
		return response;
	}

	public static Response getSucessResponse(Object o, Operation operation) {
		List<Object> list = new ArrayList<Object>();
		list.add(o);
		return getSucessResponse(list, operation);
	}

	public static Response getSucessResponse(List<? extends Object> list, Operation operation) {
		Response response = getSucessResponse();
		Result result = new Result();
		result.setOperation(operation);
		if (list != null && !list.isEmpty()) {
			result.setNoOfRecords(list.size());
			result.getAny().addAll(list);
			if (Operation.INSERT == operation) {
				result.setMessage(DATA_INSERTED);
			} else {
				result.setMessage(DATA_FOUND);
			}
		} else {
			if (Operation.INSERT == operation) {
				result.setMessage(NO_DATA_INSERTED);
			} else {
				result.setMessage(NO_DATA_FOUND);
			}
			result.setNoOfRecords(0);
		}
		response.setResult(result);
		return response;
	}

	public static Response getSucessResponse(int noOfRecords, Operation operation) {
		Response response = getSucessResponse();

		Result result = new Result();
		result.setNoOfRecords(noOfRecords);
		result.setOperation(operation);

		if (noOfRecords == 0) {
			if (Operation.INSERT == operation) {
				result.setMessage(NO_DATA_INSERTED);
			} else {
				result.setMessage(NO_DATA_FOUND);
			}
		} else {
			if (Operation.INSERT == operation) {
				result.setMessage(DATA_INSERTED);
			} else {
				result.setMessage(DATA_FOUND);
			}
		}

		response.setResult(result);
		return response;
	}

}
