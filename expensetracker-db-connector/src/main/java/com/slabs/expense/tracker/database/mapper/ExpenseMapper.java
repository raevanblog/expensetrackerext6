package com.slabs.expense.tracker.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Flush;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.executor.BatchResult;

import com.slabs.expense.tracker.common.db.entity.Expense;
import com.slabs.expense.tracker.common.db.entity.ExpenseCategory;
import com.slabs.expense.tracker.common.db.entity.ExpenseType;

public interface ExpenseMapper {

	public Integer insertExpense(@Param("records") List<Expense> records) throws Exception;

	public List<Expense> getExpense(@Param("username") String username, @Param("month") Integer month, @Param("year") Integer year) throws Exception;

	public Integer updateExpense(@Param("record") Expense expense) throws Exception;

	public List<Expense> getExpenseById(@Param("id") Integer id) throws Exception;

	public Integer deleteExpense(@Param("username") String username, @Param("id") Integer id) throws Exception;

	public List<String> selectExpenseTypes(@Param("username") String username) throws Exception;

	public Integer insertExpenseCategory(@Param("records") List<ExpenseCategory> records) throws Exception;

	public Integer updateExpenseCategory(@Param("record") ExpenseCategory record) throws Exception;

	public List<ExpenseCategory> retrieveExpenseCategory(@Param("categoryId") Integer categoryId) throws Exception;

	public Integer deleteExpenseCategory(@Param("categoryId") Integer categoryId) throws Exception;

	public List<ExpenseType> getExpenseTypes() throws Exception;

	public Integer insertExpenseType(@Param("records") List<ExpenseType> records) throws Exception;

	public Integer updateExpenseType(@Param("record") ExpenseType record) throws Exception;

	public Integer deleteExpenseType(@Param("id") Integer id) throws Exception;

	@Flush
	public List<BatchResult> flush();

}
