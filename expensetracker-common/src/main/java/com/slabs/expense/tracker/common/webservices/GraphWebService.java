package com.slabs.expense.tracker.common.webservices;

import com.slabs.expense.tracker.common.exception.ExpenseTrackerException;
import com.slabs.expense.tracker.common.webservice.response.Response;

public interface GraphWebService {
	public Response getGraph(String username, Integer year, Integer month, String type) throws ExpenseTrackerException;
}
