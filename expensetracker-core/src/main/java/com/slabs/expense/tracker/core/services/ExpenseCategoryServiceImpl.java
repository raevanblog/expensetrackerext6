package com.slabs.expense.tracker.core.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.slabs.expense.tracker.common.database.entity.ExpenseCategory;
import com.slabs.expensetracker.common.database.mapper.ExpenseDAO;
import com.slabs.expensetracker.common.services.ExpenseCategoryService;

/**
 * {@link ExpenseCategoryServiceImpl} provides API for INSERT, UPDATE, DELETE, SELECT on
 * EXPENSE_CATEGORY table
 * 
 * @author Shyam Natarajan
 *
 */
@Service(value = "ExpenseCategoryService")
@Transactional(isolation = Isolation.READ_COMMITTED, timeout = 2000)
public class ExpenseCategoryServiceImpl implements ExpenseCategoryService {

	@Autowired
	private ExpenseDAO mapper;

	/**
	 * 
	 * @param records
	 *            {@link ExpenseCategory} - List of records to INSERT
	 * @return {@link Integer} - No of records inserted
	 * @throws Exception
	 *             throws {@link Exception}
	 */
	@Override
	public Integer createCategory(List<ExpenseCategory> records) throws Exception {

		return mapper.insertExpenseCategory(records);
	}

	/**
	 * 
	 * @param records
	 *            {@link ExpenseCategory} - List of records to UPDATE
	 * @return {@link Integer} - No of records updated.
	 * @throws Exception
	 *             throws {@link Exception}
	 * 
	 */
	@Override
	public Integer updateCategory(List<ExpenseCategory> records) throws Exception {
		Integer noOfRecords = 0;
		for (ExpenseCategory record : records) {
			noOfRecords = noOfRecords + mapper.updateExpenseCategory(record);
		}
		return noOfRecords;
	}

	/**
	 * 
	 * @param username
	 *            {@link String} - Username of the user
	 * @return {@link ExpenseCategory} - List of Expense Category records
	 * @throws Exception
	 *             throws {@link Exception}
	 */
	@Override
	public List<ExpenseCategory> selectCategory(String username) throws Exception {
		return mapper.retrieveExpenseCategory(username);
	}

	/**
	 * 
	 * @param records
	 *            {@link ExpenseCategory} - List of records to DELETE
	 * @return {@link Integer} - No of records deleted.
	 * @throws Exception
	 *             throws {@link Exception}
	 */
	@Override
	public Integer deleteCategory(List<ExpenseCategory> records) throws Exception {
		Integer noOfRecords = 0;
		for (ExpenseCategory record : records) {
			noOfRecords = noOfRecords + mapper.deleteExpenseCategory(record);
		}
		return noOfRecords;
	}

}
