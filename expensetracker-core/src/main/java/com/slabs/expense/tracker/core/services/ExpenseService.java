package com.slabs.expense.tracker.core.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

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
	ExpenseMapper mapper;

	public Response insert(List<Expense> records) throws Exception {
		return ResponseGenerator.getSucessResponse(mapper.insertExpense(records), Operation.INSERT);
	}

	public Response update(List<Expense> records) throws Exception {
		int noOfRecords = 0;
		for (Expense record : records) {
			noOfRecords = noOfRecords + mapper.updateExpense(record);
		}
		return ResponseGenerator.getSucessResponse(noOfRecords, Operation.UPDATE);
	}

	public Response select(String username, Integer month, Integer year) throws Exception {
		return ResponseGenerator.getSucessResponse(mapper.getExpense(username, month, year), Operation.SELECT);
	}

	public Response selectById(Integer id) throws Exception {
		return ResponseGenerator.getSucessResponse(mapper.getExpenseById(id), Operation.SELECT);
	}

	public Response delete(String username, Integer id) throws Exception {
		return ResponseGenerator.getSucessResponse(mapper.deleteExpense(username, id), Operation.DELETE);
	}

	public Response selectExpenseTypes(String username) throws Exception {
		return ResponseGenerator.getSucessResponse(mapper.selectExpenseTypes(username), Operation.SELECT);
	}
}
