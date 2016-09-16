package com.slabs.expense.tracker.core;

import java.util.List;

import com.slabs.expense.tracker.webservice.response.Response;
import com.slabs.expense.tracker.webservice.response.Result;

public class ResponseGenerator {

	public static Response getExceptionResponse(ResponseStatus status, Throwable cause) {
		Response resp = new Response();
		resp.setStatus_Code(status.getStatusCode());
		resp.setStatus_Message(status.name());
		resp.setException(cause.getMessage());
		return resp;
	}

	public static Response getSucessResponse(List<? extends Object> list) {
		Response response = new Response();
		response.setStatus_Code(ResponseStatus.OK.getStatusCode());
		response.setStatus_Message(ResponseStatus.OK.name());

		Result result = new Result();
		if (list != null) {
			result.setNoOfRecords(list.size());
			result.getAny().addAll(list);
		} else {
			result.setNoOfRecords(0);
		}
		response.setResult(result);
		return response;
	}

}
