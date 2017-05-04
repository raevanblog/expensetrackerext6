package com.slabs.expense.tracker.common.webservices;

import java.util.List;

import com.slabs.expense.tracker.common.database.entity.Income;
import com.slabs.expense.tracker.common.exception.ExpenseTrackerException;
import com.slabs.expense.tracker.webservice.response.Response;

public interface IncomeWebService {
	
	public Response getIncome(String username, Integer year, Integer month) throws ExpenseTrackerException;

	public Response addIncome(List<Income> records) throws ExpenseTrackerException;

	public Response updateIncome(List<Income> records) throws ExpenseTrackerException;

	public Response deleteIncome(List<Income> records) throws ExpenseTrackerException;
}
