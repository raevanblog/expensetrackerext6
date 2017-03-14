package com.slabs.expense.tracker.common.services;

import java.util.List;

import com.slabs.expense.tracker.common.database.entity.Graph;
import com.slabs.expense.tracker.common.database.entity.Income;

public interface IncomeService {

	public List<Income> selectIncome(String username, Integer year, Integer month) throws Exception;

	public Double getTotalIncome(String username, Integer year, Integer month) throws Exception;

	public List<Graph> getMonthWiseTotalIncome(String username, Integer year) throws Exception;
	
	public Integer createIncome(List<Income> records) throws Exception;

	public Integer deleteIncome(List<Income> records) throws Exception;

	public Integer updateIncome(List<Income> records) throws Exception;

}
