package com.slabs.expense.tracker.core.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.slabs.expense.tracker.common.db.entity.Dictionary;
import com.slabs.expense.tracker.common.db.entity.Expense;
import com.slabs.expense.tracker.core.ResponseGenerator;
import com.slabs.expense.tracker.database.mapper.ExpenseMapper;
import com.slabs.expense.tracker.webservice.response.Operation;
import com.slabs.expense.tracker.webservice.response.Response;

@Service(value = "expense")
@Transactional(isolation = Isolation.READ_COMMITTED, timeout = 2000)
public class ExpenseService {

	private static final Logger L = LoggerFactory.getLogger(ExpenseService.class);

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

	public List<Expense> select(String username, Integer month, Integer year) throws Exception {
		return mapper.getExpense(username, month, year);
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
