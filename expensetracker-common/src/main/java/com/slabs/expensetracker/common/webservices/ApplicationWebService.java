package com.slabs.expensetracker.common.webservices;

import com.slabs.expensetracker.common.webservice.response.Response;
import com.slabs.expensetracker.common.exception.ExpenseTrackerException;

public interface ApplicationWebService {
	public Response getExpenseType() throws ExpenseTrackerException;

	public Response getIncomeType() throws ExpenseTrackerException;

	public Response getCurrencyType() throws ExpenseTrackerException;

	public Response getItemNames(String type, String username) throws ExpenseTrackerException;
	
}
