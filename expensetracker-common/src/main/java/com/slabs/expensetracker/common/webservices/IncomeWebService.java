package com.slabs.expensetracker.common.webservices;

import java.util.List;

import com.slabs.expensetracker.common.database.entity.Income;
import com.slabs.expensetracker.common.webservice.response.Response;
import com.slabs.expensetracker.common.exception.ExpenseTrackerException;

public interface IncomeWebService {

	public Response getIncome(String username, Integer year, Integer month) throws ExpenseTrackerException;

	public Response addIncome(List<Income> records) throws ExpenseTrackerException;

	public Response updateIncome(List<Income> records) throws ExpenseTrackerException;

	public Response deleteIncome(List<Income> records) throws ExpenseTrackerException;
}
