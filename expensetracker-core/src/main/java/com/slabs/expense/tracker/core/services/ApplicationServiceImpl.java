package com.slabs.expense.tracker.core.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.slabs.expense.tracker.common.database.entity.Currency;
import com.slabs.expense.tracker.common.database.entity.Dictionary;
import com.slabs.expense.tracker.common.database.entity.ExpenseType;
import com.slabs.expense.tracker.common.database.entity.Income;
import com.slabs.expense.tracker.common.database.entity.IncomeType;
import com.slabs.expensetracker.common.database.mapper.ApplicationDAO;
import com.slabs.expensetracker.common.services.ApplicationService;

@Service(value = "ApplicationService")
@Transactional(isolation = Isolation.READ_COMMITTED, timeout = 2000)
public class ApplicationServiceImpl implements ApplicationService {

	@Autowired
	private ApplicationDAO dao;

	/**
	 * 
	 * @return {@link ExpenseType} - List of records from EXPENSETYPE table
	 * @throws Exception
	 *             throws {@link Exception}
	 */
	@Override
	public List<ExpenseType> getExpenseType() throws Exception {
		return dao.getExpenseType();
	}

	/**
	 * 
	 * @param records
	 *            {@link ExpenseType} - List of records to UPDATE.
	 * @return {@link Integer} - No of records updated.
	 * @throws Exception
	 *             throws {@link Exception}
	 */
	@Override
	public Integer updateExpenseType(List<ExpenseType> records) throws Exception {
		int noOfRecords = 0;
		for (ExpenseType record : records) {
			noOfRecords = noOfRecords + dao.updateExpenseType(record);
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
	@Override
	public Integer createExpenseType(List<ExpenseType> records) throws Exception {
		return dao.createExpenseType(records);
	}

	/**
	 * 
	 * @param id
	 *            {@link Integer} - Record id for deletion
	 * @return {@link Integer} - No of records deleted
	 * @throws Exception
	 *             throws {@link Exception}
	 */
	@Override
	public Integer deleteExpenseType(ExpenseType record) throws Exception {
		return dao.deleteExpenseType(record);
	}

	/**
	 * 
	 * @return {@link Income} - List of records
	 * @throws Exception
	 *             throws {@link Exception}
	 */
	@Override
	public List<IncomeType> getIncomeType() throws Exception {
		return dao.getIncomeType();
	}

	@Override
	public Integer deleteIncomeType(IncomeType record) throws Exception {
		return dao.deleteIncomeType(record);
	}

	@Override
	public Integer createIncomeType(List<IncomeType> records) throws Exception {
		return dao.createIncomeType(records);
	}

	@Override
	public Integer updateIncomeType(List<IncomeType> records) throws Exception {
		int noOfRecords = 0;

		for (IncomeType record : records) {
			noOfRecords = noOfRecords + dao.updateIncomeType(record);
		}

		return noOfRecords;
	}

	@Override
	public List<Currency> getCurrency() throws Exception {

		return dao.getCurrency();
	}

	@Override
	public Integer deleteCurrency(Currency record) throws Exception {
		return dao.deleteCurrency(record);
	}

	@Override
	public Integer updateCurrency(List<Currency> records) throws Exception {
		int noOfRecords = 0;

		for (Currency record : records) {
			noOfRecords = noOfRecords + dao.updateCurrency(record);
		}

		return noOfRecords;
	}

	@Override
	public Integer createCurrency(List<Currency> records) throws Exception {
		return dao.createCurrency(records);
	}
	
	
	/**
	 * 
	 * @return {@link Dictionary} - List of Item Names in the EXPENSE
	 *         table
	 * @throws Exception
	 *             throws {@link Exception}
	 */
	@Override
	public List<Dictionary> getExpenseNames() throws Exception {
		return dao.getExpenseNames();
	}
	
}
