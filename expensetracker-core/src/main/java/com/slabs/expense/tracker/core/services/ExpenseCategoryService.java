package com.slabs.expense.tracker.core.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.slabs.expense.tracker.common.db.entity.ExpenseCategory;
import com.slabs.expense.tracker.database.mapper.ExpenseMapper;

/**
 * {@link ExpenseCategory} provides API for INSERT, UPDATE, DELETE, SELECT on
 * EXPENSE_CATEGORY table
 * 
 * @author Shyam Natarajan
 *
 */
@Service(value = "ExpenseCategoryService")
@Transactional(isolation = Isolation.READ_COMMITTED, timeout = 2000)
public class ExpenseCategoryService {

	@Autowired
	private ExpenseMapper mapper;

	/**
	 * 
	 * @param records
	 *            {@link ExpenseCategory} - List of records to INSERT
	 * @return {@link Integer} - No of records inserted
	 * @throws Exception
	 *             throws {@link Exception}
	 */
	public Integer insert(List<ExpenseCategory> records) throws Exception {

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
	public Integer update(List<ExpenseCategory> records) throws Exception {
		Integer noOfRecords = 0;
		for (ExpenseCategory record : records) {
			noOfRecords = noOfRecords + mapper.updateExpenseCategory(record);
		}
		return noOfRecords;
	}

	/**
	 * 
	 * @param categoryId
	 *            {@link Integer} - Category Id to SELECT
	 * @return {@link ExpenseCategory} - List of Expense Category records
	 * @throws Exception
	 *             throws {@link Exception}
	 */
	public List<ExpenseCategory> select(Integer categoryId) throws Exception {
		return mapper.retrieveExpenseCategory(categoryId);
	}

	/**
	 * 
	 * @param records
	 *            {@link ExpenseCategory} - List of records to DELETE
	 * @return {@link Integer} - No of records deleted.
	 * @throws Exception
	 *             throws {@link Exception}
	 */
	public Integer delete(List<ExpenseCategory> records) throws Exception {
		Integer noOfRecords = 0;
		for (ExpenseCategory record : records) {
			noOfRecords = noOfRecords + mapper.deleteExpenseCategory(record);
		}
		return noOfRecords;
	}

}
