package com.slabs.expense.tracker.core.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.slabs.expense.tracker.common.db.entity.ExpenseType;
import com.slabs.expense.tracker.core.ResponseGenerator;
import com.slabs.expense.tracker.database.mapper.ExpenseMapper;
import com.slabs.expense.tracker.webservice.response.Operation;
import com.slabs.expense.tracker.webservice.response.Response;

@Service(value = "expensetype")
public class ExpenseTypeService {

	@Autowired
	private ExpenseMapper mapper;

	public Response select() throws Exception {
		return ResponseGenerator.getSucessResponse(mapper.getExpenseTypes(), Operation.SELECT);
	}

	public Response update(List<ExpenseType> records) throws Exception {
		int noOfRecords = 0;
		for (ExpenseType record : records) {
			noOfRecords = noOfRecords + mapper.updateExpenseType(record);
		}
		return ResponseGenerator.getSucessResponse(noOfRecords, Operation.UPDATE);
	}

	public Response insert(List<ExpenseType> records) throws Exception {
		return ResponseGenerator.getSucessResponse(mapper.insertExpenseType(records), Operation.INSERT);
	}

	public Response delete(Integer id) throws Exception {
		return ResponseGenerator.getSucessResponse(mapper.deleteExpenseType(id), Operation.DELETE);
	}

}
