package com.slabs.expense.tracker.common.database.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.slabs.expense.tracker.common.database.entity.Graph;
import com.slabs.expense.tracker.common.database.entity.Income;
import com.slabs.expense.tracker.common.database.entity.IncomeType;

/**
 * {@link IncomeDAO} is an interface providing mapper methods for executing
 * query using MyBatis
 * 
 * @author Shyam Natarajan
 *
 */
@Component(value = "IncomeDAO")
public interface IncomeDAO {

	public Integer insertIncome(List<Income> records);

	public Integer updateIncome(Income record);

	public Integer deleteIncome(Income record);

	public List<Income> getIncome(String username, Integer year, Integer month);

	public Double getTotalIncome(String username, Integer year, Integer month);

	public List<Graph> getMonthWiseTotalIncome(String username, Integer year);

	public List<IncomeType> getIncomeType();

}
