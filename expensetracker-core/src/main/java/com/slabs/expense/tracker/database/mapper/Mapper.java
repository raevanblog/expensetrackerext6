package com.slabs.expense.tracker.database.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.slabs.expense.tracker.common.db.entity.ExpenseCategory;

public interface Mapper {

	public List<ExpenseCategory> retrieveCategory(@Param("parameters") Map<String, String> parameters) throws Exception;

}
