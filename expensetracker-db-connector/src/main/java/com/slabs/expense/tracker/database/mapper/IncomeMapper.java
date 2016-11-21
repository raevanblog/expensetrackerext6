package com.slabs.expense.tracker.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.slabs.expense.tracker.common.db.entity.Graph;
import com.slabs.expense.tracker.common.db.entity.Income;
import com.slabs.expense.tracker.common.db.entity.IncomeType;

/**
 * {@link IncomeMapper} is an interface providing mapper methods for executing
 * query using MyBatis
 * 
 * @author Shyam Natarajan
 *
 */
public interface IncomeMapper {

	public Integer insertIncome(@Param("records") List<Income> records) throws Exception;

	public Integer updateIncome(@Param("record") Income record) throws Exception;

	public Integer deleteIncome(@Param("record") Income record) throws Exception;

	public List<Income> getIncome(@Param("username") String username, @Param("year") Integer year,
			@Param("month") Integer month) throws Exception;

	public Double getTotalIncome(@Param("username") String username, @Param("year") Integer year,
			@Param("month") Integer month) throws Exception;

	public List<Graph> getMonthWiseTotalIncome(@Param("username") String username,
			@Param("year") Integer year) throws Exception;

	public List<IncomeType> getIncomeType() throws Exception;

}
