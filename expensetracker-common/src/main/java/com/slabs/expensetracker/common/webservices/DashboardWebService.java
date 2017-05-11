package com.slabs.expensetracker.common.webservices;

import com.slabs.expensetracker.common.webservice.response.Response;
import com.slabs.expensetracker.common.exception.ExpenseTrackerException;

public interface DashboardWebService {

	public Response getSummary(String username, int year, int month) throws ExpenseTrackerException;

}
