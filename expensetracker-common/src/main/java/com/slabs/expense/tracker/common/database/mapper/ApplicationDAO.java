package com.slabs.expense.tracker.common.database.mapper;

import java.util.List;

import com.slabs.expense.tracker.common.database.entity.Currency;
import com.slabs.expense.tracker.common.database.entity.Dictionary;
import com.slabs.expense.tracker.common.database.entity.ExpenseType;
import com.slabs.expense.tracker.common.database.entity.IncomeType;

public interface ApplicationDAO {

	public List<ExpenseType> getExpenseType();

	public Integer createExpenseType(List<ExpenseType> records);

	public Integer updateExpenseType(ExpenseType record);

	public Integer deleteExpenseType(ExpenseType record);

	public List<IncomeType> getIncomeType();

	public Integer createIncomeType(List<IncomeType> records);

	public Integer updateIncomeType(IncomeType record);

	public Integer deleteIncomeType(IncomeType record);

	public List<Currency> getCurrency();

	public Integer createCurrency(List<Currency> records);

	public Integer updateCurrency(Currency record);

	public Integer deleteCurrency(Currency record);
	
	public List<Dictionary> getExpenseNames();
	
}