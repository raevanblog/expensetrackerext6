package com.slabs.expense.tracker.core.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.slabs.expense.tracker.common.db.entity.Income;
import com.slabs.expense.tracker.database.mapper.IncomeMapper;

@Service(value = "income")
@Transactional(isolation = Isolation.READ_COMMITTED, timeout = 2000)
public class IncomeService {

	@Autowired
	private IncomeMapper mapper;

	public List<Income> select(String username, Integer month, Integer year) throws Exception {
		return mapper.getIncome(username, month, year);
	}

	public Integer insert(List<Income> records) throws Exception {
		return mapper.insertIncome(records);
	}

	public Integer delete(List<Income> records) throws Exception {
		int noOfRecords = 0;
		for (Income record : records) {
			noOfRecords = noOfRecords + mapper.deleteIncome(record);
		}
		return noOfRecords;
	}

	public Integer update(List<Income> records) throws Exception {
		int noOfRecords = 0;
		for (Income record : records) {
			noOfRecords = noOfRecords + mapper.updateIncome(record);
		}
		return noOfRecords;
	}

}
