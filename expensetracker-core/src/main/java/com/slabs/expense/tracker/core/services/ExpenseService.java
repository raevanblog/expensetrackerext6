package com.slabs.expense.tracker.core.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.slabs.expense.tracker.common.db.entity.Dictionary;
import com.slabs.expense.tracker.common.db.entity.Expense;
import com.slabs.expense.tracker.database.mapper.ExpenseMapper;

@Service(value = "expense")
@Transactional(isolation = Isolation.READ_COMMITTED, timeout = 2000)
public class ExpenseService {

	@Autowired
	private ExpenseMapper mapper;

	public Integer insert(List<Expense> records) throws Exception {
		return mapper.insertExpense(records);
	}

	public Integer update(List<Expense> records) throws Exception {
		int noOfRecords = 0;
		for (Expense record : records) {
			noOfRecords = noOfRecords + mapper.updateExpense(record);
		}
		return noOfRecords;
	}

	public List<Expense> select(String username, Integer month, Integer year, boolean fetchTopExpense)
			throws Exception {
		if (fetchTopExpense) {
			return mapper.getTopExpense(username, month, year);
		} else {
			return mapper.getExpense(username, month, year);
		}
	}

	public List<Expense> selectById(Integer id) throws Exception {
		return mapper.getExpenseById(id);
	}

	public Integer delete(List<Expense> records) throws Exception {
		int noOfRecords = 0;
		for (Expense record : records) {
			noOfRecords = noOfRecords + mapper.deleteExpense(record);
		}
		return noOfRecords;
	}

	public List<Dictionary> selectExpenseNames() throws Exception {
		return mapper.selectExpenseNames();
	}
}
