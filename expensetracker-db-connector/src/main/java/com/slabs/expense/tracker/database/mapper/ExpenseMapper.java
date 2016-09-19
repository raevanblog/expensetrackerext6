package com.slabs.expense.tracker.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Flush;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.executor.BatchResult;

import com.slabs.expense.tracker.common.db.entity.Expense;
import com.slabs.expense.tracker.common.db.entity.ExpenseCategory;

public interface ExpenseMapper {

	public Integer insertExpense(@Param("records") List<Expense> records) throws Exception;

	public Integer insertExpenseCategory(@Param("records") List<ExpenseCategory> records) throws Exception;

	public Integer updateExpenseCategory(@Param("record") ExpenseCategory record) throws Exception;

	public List<ExpenseCategory> retrieveExpenseCategory(@Param("categoryId") Integer categoryId) throws Exception;

	public Integer deleteExpenseCategory(@Param("categoryId") Integer categoryId) throws Exception;

	@Flush
	public List<BatchResult> flush();

}
