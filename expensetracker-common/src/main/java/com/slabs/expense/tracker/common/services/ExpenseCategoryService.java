package com.slabs.expense.tracker.common.services;

import java.util.List;

import com.slabs.expense.tracker.common.database.entity.ExpenseCategory;

public interface ExpenseCategoryService {

	public Integer createCategory(List<ExpenseCategory> records) throws Exception;

	public Integer updateCategory(List<ExpenseCategory> records) throws Exception;

	public List<ExpenseCategory> selectCategory(String username) throws Exception;

	public Integer deleteCategory(List<ExpenseCategory> records) throws Exception;
}
