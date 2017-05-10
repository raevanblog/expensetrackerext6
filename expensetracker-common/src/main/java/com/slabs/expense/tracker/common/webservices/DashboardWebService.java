package com.slabs.expense.tracker.common.webservices;

import com.slabs.expense.tracker.common.exception.ExpenseTrackerException;
import com.slabs.expense.tracker.webservice.response.Response;

public interface DashboardWebService {

	public Response getSummary(String username, int year, int month) throws ExpenseTrackerException;

}
