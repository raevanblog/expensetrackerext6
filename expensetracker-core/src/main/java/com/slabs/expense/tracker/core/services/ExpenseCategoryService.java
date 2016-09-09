package com.slabs.expense.tracker.core.services;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.slabs.expense.tracker.common.db.entity.ExpenseCategory;
import com.slabs.expense.tracker.database.mapper.ExpenseMapper;

@Service(value = "ExpenseCategoryService")
@Transactional(isolation=Isolation.READ_COMMITTED, timeout=2000)
public class ExpenseCategoryService implements BaseService {

	private static final Logger L = LoggerFactory.getLogger(ExpenseCategoryService.class);

	@Autowired	
	private ExpenseMapper mapper;

	public List<ExpenseCategory> selectExpenseCategory(Map<String, String> parameters) throws Exception {
		return mapper.retrieveCategory(parameters);
	}
}
