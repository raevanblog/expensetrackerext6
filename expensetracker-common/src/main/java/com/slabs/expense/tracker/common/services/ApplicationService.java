package com.slabs.expense.tracker.common.services;

import java.util.List;

import com.slabs.expense.tracker.common.database.entity.Currency;
import com.slabs.expense.tracker.common.database.entity.ExpenseType;
import com.slabs.expense.tracker.common.database.entity.IncomeType;

public interface ApplicationService {

	public List<ExpenseType> getExpenseType() throws Exception;

	public Integer updateExpenseType(List<ExpenseType> records) throws Exception;

	public Integer createExpenseType(List<ExpenseType> records) throws Exception;

	public Integer deleteExpenseType(ExpenseType record) throws Exception;

	public List<IncomeType> getIncomeType() throws Exception;

	public Integer deleteIncomeType(IncomeType record) throws Exception;

	public Integer createIncomeType(List<IncomeType> records) throws Exception;

	public Integer updateIncomeType(List<IncomeType> records) throws Exception;

	public List<Currency> getCurrency() throws Exception;

	public Integer deleteCurrency(Currency record) throws Exception;

	public Integer updateCurrency(List<Currency> records) throws Exception;

	public Integer createCurrency(List<Currency> records) throws Exception;
	
}
