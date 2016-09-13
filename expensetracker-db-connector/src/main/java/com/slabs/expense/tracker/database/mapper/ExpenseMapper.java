package com.slabs.expense.tracker.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.slabs.expense.tracker.common.db.entity.ExpenseCategory;

public interface ExpenseMapper {

	public List<ExpenseCategory> retrieveCategory(@Param("categoryId") Integer categoryId) throws Exception;

}
