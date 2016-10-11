package com.slabs.expense.tracker.core.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.slabs.expense.tracker.common.db.entity.ExpenseCategory;
import com.slabs.expense.tracker.database.mapper.ExpenseMapper;

@Service(value = "expensecategory")
@Transactional(isolation = Isolation.READ_COMMITTED, timeout = 2000)
public class ExpenseCategoryService {

	@Autowired
	private ExpenseMapper mapper;
	
	public Integer insert(List<ExpenseCategory> records) throws Exception {

		return mapper.insertExpenseCategory(records);
	}

	public Integer update(List<ExpenseCategory> records) throws Exception {
		Integer noOfRecords = 0;
		for (ExpenseCategory record : records) {
			noOfRecords = noOfRecords + mapper.updateExpenseCategory(record);
		}
		return noOfRecords;
	}

	public List<ExpenseCategory> select(Integer categoryId) throws Exception {
		return mapper.retrieveExpenseCategory(categoryId);
	}

	public Integer delete(List<ExpenseCategory> records) throws Exception {
		Integer noOfRecords = 0;
		for (ExpenseCategory record : records) {
			noOfRecords = noOfRecords + mapper.deleteExpenseCategory(record);
		}
		return noOfRecords;
	}

}
