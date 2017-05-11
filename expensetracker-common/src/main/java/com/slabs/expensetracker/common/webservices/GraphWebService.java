package com.slabs.expensetracker.common.webservices;

import com.slabs.expensetracker.common.webservice.response.Response;
import com.slabs.expensetracker.common.exception.ExpenseTrackerException;

public interface GraphWebService {
	public Response getGraph(String username, Integer year, Integer month, String type) throws ExpenseTrackerException;
}
