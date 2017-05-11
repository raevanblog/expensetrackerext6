package com.slabs.expense.tracker.common.webservices;

import java.util.List;

import com.slabs.expense.tracker.common.database.entity.Expense;
import com.slabs.expense.tracker.common.database.entity.ExpenseCategory;
import com.slabs.expense.tracker.common.database.entity.Units;
import com.slabs.expense.tracker.common.exception.ExpenseTrackerException;
import com.slabs.expense.tracker.common.webservice.response.Response;

public interface ExpenseWebService {

	public Response createExpenseUnits(List<Units> records) throws ExpenseTrackerException;

	public Response updateExpenseUnits(List<Units> records) throws ExpenseTrackerException;

	public Response getExpenseUnits() throws ExpenseTrackerException;

	public Response deleteExpenseUnits(List<Units> records) throws ExpenseTrackerException;

	public Response insertExpenseCategory(List<ExpenseCategory> records) throws ExpenseTrackerException;

	public Response updateExpenseCategory(List<ExpenseCategory> records) throws ExpenseTrackerException;

	public Response getExpenseCategory(String username) throws ExpenseTrackerException;

	public Response deleteExpenseCategory(List<ExpenseCategory> records) throws ExpenseTrackerException;

	public Response getExpense(String username, Integer year, Integer month, boolean fetchTopExpense) throws ExpenseTrackerException;

	public Response createExpense(List<Expense> records) throws ExpenseTrackerException;

	public Response updateExpense(List<Expense> records) throws ExpenseTrackerException;

	public Response deleteExpense(List<Expense> records) throws ExpenseTrackerException;
}
