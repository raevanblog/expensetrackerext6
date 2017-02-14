package com.slabs.expense.tracker.common.database.mapper;

import java.util.List;

import com.slabs.expense.tracker.common.database.entity.Dictionary;
import com.slabs.expense.tracker.common.database.entity.Expense;
import com.slabs.expense.tracker.common.database.entity.ExpenseCategory;
import com.slabs.expense.tracker.common.database.entity.ExpenseType;
import com.slabs.expense.tracker.common.database.entity.Graph;

/**
 * {@link ExpenseDAO} is an interface providing mapper methods for executing
 * query using MyBatis
 * 
 * @author Shyam Natarajan
 *
 */
public interface ExpenseDAO {

	public Integer insertExpense(List<Expense> records);

	public List<Expense> getExpense(String username, Integer year, Integer month);

	public List<Expense> getTopExpense(String username, Integer year, Integer month);

	public Double getTotalExpense(String username, Integer year, Integer month);

	public List<Graph> getMonthWiseTotalExpense(String username, Integer year);

	public List<Graph> getCategoryWiseTotalExpense(String username, Integer year, Integer month);

	public Integer updateExpense(Expense record);

	public List<Expense> getExpenseById(Integer id);

	public Integer deleteExpense(Expense record);

	public List<Dictionary> selectExpenseNames();

	public Integer insertExpenseCategory(List<ExpenseCategory> records) throws Exception;

	public Integer updateExpenseCategory(ExpenseCategory record);

	public List<ExpenseCategory> retrieveExpenseCategory(String username) throws Exception;

	public Integer deleteExpenseCategory(ExpenseCategory record);

	public List<ExpenseType> getExpenseTypes();

	public Integer insertExpenseType(List<ExpenseType> records);

	public Integer updateExpenseType(ExpenseType record);

	public Integer deleteExpenseType(Integer id);

}
