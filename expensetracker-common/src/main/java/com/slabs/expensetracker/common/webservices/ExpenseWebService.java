package com.slabs.expensetracker.common.webservices;

import java.util.List;

import com.slabs.expensetracker.common.database.entity.Expense;
import com.slabs.expensetracker.common.database.entity.ExpenseCategory;
import com.slabs.expensetracker.common.database.entity.Units;
import com.slabs.expensetracker.common.webservice.response.Response;
import com.slabs.expensetracker.common.exception.ExpenseTrackerException;

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

	public Response getExpenseRange(String username) throws ExpenseTrackerException;
}
