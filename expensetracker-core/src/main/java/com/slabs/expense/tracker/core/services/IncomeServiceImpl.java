package com.slabs.expense.tracker.core.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.slabs.expense.tracker.common.database.entity.Graph;
import com.slabs.expense.tracker.common.database.entity.Income;
import com.slabs.expense.tracker.common.database.mapper.IncomeDAO;
import com.slabs.expense.tracker.common.services.IncomeService;

/**
 * {@link IncomeServiceImpl} provides API for INSERT, DELETE, UPDATE, SELECT on
 * INCOME table.
 * 
 * @author Shyam Natarajan
 *
 */
@Service(value = "IncomeService")
@Transactional(isolation = Isolation.READ_COMMITTED, timeout = 2000)
public class IncomeServiceImpl implements IncomeService {

	@Autowired
	private IncomeDAO mapper;

	/**
	 * 
	 * @param username
	 *            {@link String} - Username of the user
	 * @param year
	 *            {@link Integer} - Year for which Income need to be retrieved
	 * @param month
	 *            {@link Integer} - Month for which Income need to be retrieved
	 * @return {@link Income} - List of records
	 * @throws Exception
	 *             throws {@link Exception}
	 */
	@Override
	public List<Income> selectIncome(String username, Integer year, Integer month)
			throws Exception {
		return mapper.getIncome(username, year, month);
	}

	/**
	 * 
	 * @param username
	 *            {@link String} - Username of the user
	 * @param year
	 *            {@link Integer} - Year for which Income need to be retrieved
	 * @param month
	 *            {@link Integer} - Month for which Income need to be retrieved
	 * @return {@link Income} - List of records
	 * @throws Exception
	 *             throws {@link Exception}
	 */
	@Override
	public Double getTotalIncome(String username, Integer year, Integer month) throws Exception {
		return mapper.getTotalIncome(username, year, month);
	}

	/**
	 * 
	 * @param username
	 *            {@link String} - Username of the user
	 * @param year
	 *            {@link Integer} - Year for which Income need to be retrieved
	 * @return {@link Graph} - list of graph records
	 * @throws Exception
	 *             throws {@link Exception}
	 */
	@Override
	public List<Graph> getMonthWiseTotalIncome(String username, Integer year) throws Exception {
		List<Graph> list = mapper.getMonthWiseTotalIncome(username, year);
		List<Graph> newList = new ArrayList<Graph>();
		for (int i = 1; i <= 12; i++) {
			boolean isFound = false;
			for (Graph g : list) {
				g.setYear(year);
				if (i == g.getMonth()) {
					isFound = true;
					break;
				}
			}
			if (!isFound) {
				Graph graph = new Graph();
				graph.setIncome(0);
				graph.setYear(year);
				graph.setMonth(i);
				newList.add(graph);
			}
		}
		if (newList.size() > 0) {
			list.addAll(newList);
		}
		return list;
	}
	
	/**
	 * 
	 * @param records
	 *            {@link Income} - List of records to INSERT
	 * @return {@link Integer} - No of records inserted
	 * @throws Exception
	 *             throws {@link Exception}
	 */
	@Override
	public Integer createIncome(List<Income> records) throws Exception {
		return mapper.insertIncome(records);
	}

	/**
	 * 
	 * @param records
	 *            {@link Income} - List of record to DELETE
	 * @return {@link Integer} - No of records deleted
	 * @throws Exception
	 *             throws {@link Exception}
	 */
	@Override
	public Integer deleteIncome(List<Income> records) throws Exception {
		int noOfRecords = 0;
		for (Income record : records) {
			noOfRecords = noOfRecords + mapper.deleteIncome(record);
		}
		return noOfRecords;
	}

	/**
	 * 
	 * @param records
	 *            {@link Income} - List of records to UPDATE
	 * @return {@link Integer} - No of records updated
	 * @throws Exception
	 *             throws {@link Exception}
	 */
	@Override
	public Integer updateIncome(List<Income> records) throws Exception {
		int noOfRecords = 0;
		for (Income record : records) {
			noOfRecords = noOfRecords + mapper.updateIncome(record);
		}
		return noOfRecords;
	}

}
