package com.slabs.expensetracker.common.services;

import java.util.List;

import com.slabs.expensetracker.common.database.entity.Expense;
import com.slabs.expensetracker.common.database.entity.Graph;
import com.slabs.expensetracker.common.database.entity.Units;

public interface ExpenseService {

	public Integer createExpense(List<Expense> records) throws Exception;

	public Integer updateExpense(List<Expense> records) throws Exception;

	public List<Expense> selectExpense(String username, Integer year, Integer month, boolean fetchTopExpense) throws Exception;

	public Double getTotalExpense(String username, Integer year, Integer month) throws Exception;

	public List<Graph> getMonthWiseTotalExpense(String username, Integer year) throws Exception;

	public List<Graph> getCategoryWiseTotalExpense(String username, Integer year, Integer month) throws Exception;

	public List<Expense> selectExpenseById(Integer id) throws Exception;

	public Integer deleteExpense(List<Expense> records) throws Exception;

	public List<Units> getExpenseUnits() throws Exception;

	public Integer updateExpenseUnits(List<Units> records) throws Exception;

	public Integer deleteExpenseUnits(List<Units> records) throws Exception;

	public Integer createExpenseUnits(List<Units> records) throws Exception;

}
