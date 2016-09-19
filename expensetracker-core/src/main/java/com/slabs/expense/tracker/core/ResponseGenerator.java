package com.slabs.expense.tracker.core;

import java.util.List;

import com.slabs.expense.tracker.webservice.response.Operation;
import com.slabs.expense.tracker.webservice.response.Response;
import com.slabs.expense.tracker.webservice.response.Result;

public class ResponseGenerator {

	private static final String NO_DATA_FOUND = "NO_DATA_FOUND";

	private static final String DATA_FOUND = "DATA_FOUND";

	public static Response getExceptionResponse(ResponseStatus status, Throwable cause) {
		Response resp = new Response();
		resp.setStatus_Code(status.getStatusCode());
		resp.setStatus_Message(status.name());
		resp.setSuccess(false);
		resp.setException(cause.getMessage());
		return resp;
	}

	private static Response getSucessResponse() {
		Response response = new Response();
		response.setStatus_Code(ResponseStatus.OK.getStatusCode());
		response.setStatus_Message(ResponseStatus.OK.name());
		response.setSuccess(true);
		return response;
	}

	public static Response getSucessResponse(List<? extends Object> list, Operation operation) {
		Response response = getSucessResponse();
		Result result = new Result();
		result.setOperation(operation);
		if (list != null && !list.isEmpty()) {
			result.setNoOfRecords(list.size());
			result.getAny().addAll(list);
			result.setMessage(DATA_FOUND);
		} else {
			result.setMessage(NO_DATA_FOUND);
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
			result.setMessage(NO_DATA_FOUND);
		} else {
			result.setMessage(DATA_FOUND);
		}

		response.setResult(result);
		return response;
	}

}
