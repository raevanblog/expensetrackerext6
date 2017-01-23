package com.slabs.expense.tracker.common.db.mapper;

import java.util.List;

import com.slabs.expense.tracker.common.db.entity.Graph;
import com.slabs.expense.tracker.common.db.entity.Income;
import com.slabs.expense.tracker.common.db.entity.IncomeType;

/**
 * {@link IncomeDAO} is an interface providing mapper methods for executing
 * query using MyBatis
 * 
 * @author Shyam Natarajan
 *
 */
public interface IncomeDAO {

	public Integer insertIncome(List<Income> records);

	public Integer updateIncome(Income record);

	public Integer deleteIncome(Income record);

	public List<Income> getIncome(String username, Integer year, Integer month);

	public Double getTotalIncome(String username, Integer year, Integer month);

	public List<Graph> getMonthWiseTotalIncome(String username, Integer year);

	public List<IncomeType> getIncomeType();

}
