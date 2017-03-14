package com.slabs.expense.tracker.common.services;

import java.util.List;

import com.slabs.expense.tracker.common.database.entity.Dictionary;
import com.slabs.expense.tracker.common.database.entity.Expense;
import com.slabs.expense.tracker.common.database.entity.Graph;

public interface ExpenseService {

	public Integer createExpense(List<Expense> records) throws Exception;

	public Integer updateExpense(List<Expense> records) throws Exception;

	public List<Expense> selectExpense(String username, Integer year, Integer month,
			boolean fetchTopExpense) throws Exception;

	public Double getTotalExpense(String username, Integer year, Integer month) throws Exception;

	public List<Graph> getMonthWiseTotalExpense(String username, Integer year) throws Exception;

	public List<Graph> getCategoryWiseTotalExpense(String username, Integer year, Integer month)
			throws Exception;

	public List<Expense> selectExpenseById(Integer id) throws Exception;

	public Integer deleteExpense(List<Expense> records) throws Exception;

	public List<Dictionary> selectExpenseNames() throws Exception;
	
}
