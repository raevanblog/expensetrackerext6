package com.slabs.expense.tracker.core.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.slabs.expense.tracker.common.db.entity.ExpenseType;
import com.slabs.expense.tracker.database.mapper.ExpenseMapper;

/**
 * {@link ExpenseTypeService} provides API for INSERT, DELETE, UPDATE, SELECT on
 * EXPENSETYPE table
 * 
 * @author Shyam Natarajan
 *
 */
@Service(value = "ExpenseTypeService")
public class ExpenseTypeService {

	@Autowired
	private ExpenseMapper mapper;

	/**
	 * 
	 * @return {@link ExpenseType} - List of records from EXPENSETYPE table
	 * @throws Exception
	 *             throws {@link Exception}
	 */
	public List<ExpenseType> select() throws Exception {
		return mapper.getExpenseTypes();
	}

	/**
	 * 
	 * @param records
	 *            {@link ExpenseType} - List of records to UPDATE.
	 * @return {@link Integer} - No of records updated.
	 * @throws Exception
	 *             throws {@link Exception}
	 */
	public Integer update(List<ExpenseType> records) throws Exception {
		int noOfRecords = 0;
		for (ExpenseType record : records) {
			noOfRecords = noOfRecords + mapper.updateExpenseType(record);
		}
		return noOfRecords;
	}

	/**
	 * 
	 * @param records
	 *            {@link ExpenseType} - List of records to INSERT.
	 * @return {@link Integer} - No of records inserted.
	 * @throws Exception
	 *             throws {@link Exception}
	 */
	public Integer insert(List<ExpenseType> records) throws Exception {
		return mapper.insertExpenseType(records);
	}

	/**
	 * 
	 * @param id
	 *            {@link Integer} - Record id for deletion
	 * @return {@link Integer} - No of records deleted
	 * @throws Exception
	 *             throws {@link Exception}
	 */
	public Integer delete(Integer id) throws Exception {
		return mapper.deleteExpenseType(id);
	}

}
