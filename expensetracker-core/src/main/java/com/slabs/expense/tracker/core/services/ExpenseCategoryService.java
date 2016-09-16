package com.slabs.expense.tracker.core.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.slabs.expense.tracker.common.db.entity.ExpenseCategory;
import com.slabs.expense.tracker.database.mapper.ExpenseMapper;

@Service(value = "expensecategory")
@Transactional(isolation = Isolation.READ_COMMITTED, timeout = 2000)
public class ExpenseCategoryService {

	private static final Logger L = LoggerFactory.getLogger(ExpenseCategoryService.class);

	@Autowired
	private ExpenseMapper mapper;

	public List<ExpenseCategory> select(Integer categoryId) throws Exception {

		return mapper.retrieveCategory(categoryId);
	}

	public Integer delete(Integer categoryId) throws Exception {
		return mapper.deleteCategory(categoryId);
	}

}
