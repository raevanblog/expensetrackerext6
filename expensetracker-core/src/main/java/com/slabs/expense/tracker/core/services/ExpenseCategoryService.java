package com.slabs.expense.tracker.core.services;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.slabs.expense.tracker.common.db.entity.ExpenseCategory;
import com.slabs.expense.tracker.core.ResponseGenerator;
import com.slabs.expense.tracker.database.mapper.ExpenseMapper;
import com.slabs.expense.tracker.webservice.response.Operation;
import com.slabs.expense.tracker.webservice.response.Response;

@Service(value = "expensecategory")
@Transactional(isolation = Isolation.READ_COMMITTED, timeout = 2000)
public class ExpenseCategoryService {

	private static final Logger L = LoggerFactory.getLogger(ExpenseCategoryService.class);

	@Autowired
	private ExpenseMapper mapper;

	@Autowired
	private SqlSessionFactory factory;

	public Response insert(List<ExpenseCategory> records) throws Exception {

		return ResponseGenerator.getSucessResponse(mapper.insertExpenseCategory(records), Operation.INSERT);
	}

	public Response update(List<ExpenseCategory> records) throws Exception {
		Integer noOfRecords = 0;
		for (ExpenseCategory record : records) {
			noOfRecords = noOfRecords + mapper.updateExpenseCategory(record);
		}
		return ResponseGenerator.getSucessResponse(noOfRecords, Operation.UPDATE);
	}

	public Response select(Integer categoryId) throws Exception {
		return ResponseGenerator.getSucessResponse(mapper.retrieveExpenseCategory(categoryId), Operation.SELECT);
	}

	public Response delete(List<ExpenseCategory> records) throws Exception {
		Integer noOfRecords = 0;
		for (ExpenseCategory record : records) {
			noOfRecords = noOfRecords + mapper.deleteExpenseCategory(record);
		}
		return ResponseGenerator.getSucessResponse(noOfRecords, Operation.DELETE);
	}

}
