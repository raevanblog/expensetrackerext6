package com.slabs.expense.tracker.core.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.slabs.expense.tracker.common.db.entity.ExpenseType;
import com.slabs.expense.tracker.database.mapper.ExpenseMapper;

@Service(value = "ExpenseTypeService")
public class ExpenseTypeService {

	@Autowired
	private ExpenseMapper mapper;

	public List<ExpenseType> select() throws Exception {
		return mapper.getExpenseTypes();
	}

	public Integer update(List<ExpenseType> records) throws Exception {
		int noOfRecords = 0;
		for (ExpenseType record : records) {
			noOfRecords = noOfRecords + mapper.updateExpenseType(record);
		}
		return noOfRecords;
	}

	public Integer insert(List<ExpenseType> records) throws Exception {
		return mapper.insertExpenseType(records);
	}

	public Integer delete(Integer id) throws Exception {
		return mapper.deleteExpenseType(id);
	}

}
