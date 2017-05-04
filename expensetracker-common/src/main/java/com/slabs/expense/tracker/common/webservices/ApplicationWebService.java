package com.slabs.expense.tracker.common.webservices;

import com.slabs.expense.tracker.common.exception.ExpenseTrackerException;
import com.slabs.expense.tracker.webservice.response.Response;

public interface ApplicationWebService {
	public Response getExpenseType() throws ExpenseTrackerException;
	public Response getIncomeType() throws ExpenseTrackerException;
	public Response getCurrencyType() throws ExpenseTrackerException;
	public Response getItemNames(String type) throws ExpenseTrackerException;;
}
