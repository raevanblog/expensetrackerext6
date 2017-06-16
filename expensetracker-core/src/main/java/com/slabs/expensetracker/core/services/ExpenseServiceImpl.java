package com.slabs.expensetracker.core.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.slabs.expensetracker.common.database.entity.Expense;
import com.slabs.expensetracker.common.database.entity.Graph;
import com.slabs.expensetracker.common.database.entity.Units;
import com.slabs.expensetracker.common.database.mapper.ExpenseDAO;
import com.slabs.expensetracker.common.services.ExpenseService;

/**
 * {@link ExpenseServiceImpl} provides API for INSERT,SELECT,UPDATE, DELETE on
 * EXPENSE table
 * 
 * @author Shyam Natarajan
 *
 */
@Service(value = "ExpenseService")
@Transactional(isolation = Isolation.READ_COMMITTED, timeout = 2000)
public class ExpenseServiceImpl implements ExpenseService {

	@Autowired
	private ExpenseDAO mapper;

	/**
	 * 
	 * @param records
	 *            {@link Expense} - List of records to INSERT
	 * @return {@link Integer} - No of records inserted
	 * @throws Exception
	 *             throws {@link Exception}
	 */
	@Override
	public Integer createExpense(List<Expense> records) throws Exception {
		return mapper.insertExpense(records);
	}

	/**
	 * 
	 * @param records
	 *            {@link Expense} - List of records to UPDATE
	 * @return {@link Integer} - No of records updated
	 * @throws Exception
	 *             throws {@link Exception}
	 */
	@Override
	public Integer updateExpense(List<Expense> records) throws Exception {
		int noOfRecords = 0;
		for (Expense record : records) {
			noOfRecords = noOfRecords + mapper.updateExpense(record);
		}
		return noOfRecords;
	}

	/**
	 * 
	 * @param username
	 *            {@link String} - Username of the user
	 * @param year
	 *            {@link Integer} - Year for which the expense need to be
	 *            retrieved
	 * @param month
	 *            {@link Integer} - Month for which the expense need to be
	 *            retrieved
	 * @param fetchTopExpense
	 *            {@link Boolean} - True to fetch only the top expense for the
	 *            month and year specified, in each category.
	 * @return {@link Expense} - List of expense records
	 * @throws Exception
	 *             throws {@link Exception}
	 */
	@Override
	public List<Expense> selectExpense(String username, Integer year, Integer month, boolean fetchTopExpense) throws Exception {
		if (fetchTopExpense) {
			return mapper.getTopExpense(username, year, month);
		} else {
			return mapper.getExpense(username, year, month);
		}
	}

	/**
	 * 
	 * @param username
	 *            {@link String} - Username of the user
	 * @param year
	 *            {@link Integer} - Year for which the expense need to be
	 *            retrieved
	 * @param month
	 *            {@link Integer} - Month for which the expense need to be
	 *            retrieved
	 * @return {@link Double} - Total expense for the month and year specified
	 * @throws Exception
	 *             throws {@link Exception}
	 */
	@Override
	public Double getTotalExpense(String username, Integer year, Integer month) throws Exception {
		return mapper.getTotalExpense(username, year, month);
	}

	/**
	 * 
	 * @param username
	 *            {@link String} - Username of the user
	 * @param year
	 *            {@link Integer} - Year for which the expense need to be
	 *            retrieved
	 * @return {@link Graph} - list of graph records
	 * 
	 * @throws Exception
	 *             throws {@link Exception}
	 */
	@Override
	public List<Graph> getMonthWiseTotalExpense(String username, Integer year) throws Exception {
		List<Graph> list = mapper.getMonthWiseTotalExpense(username, year);
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
				graph.setExpense(0);
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
	 * @param username
	 *            {@link String} - Username of the user
	 * @param year
	 *            {@link Integer} - Year for which the expense need to be
	 *            retrieved
	 * @param month
	 *            {@link Integer} - Month for which the expense need to be
	 *            retrieved
	 * @return {@link Graph} - list of graph records
	 * @throws Exception
	 *             throws {@link Exception}
	 */
	@Override
	public List<Graph> getCategoryWiseTotalExpense(String username, Integer year, Integer month) throws Exception {
		return mapper.getCategoryWiseTotalExpense(username, year, month);
	}

	/**
	 * 
	 * @param id
	 *            {@link Integer} - Expense record id in EXPENSE table.
	 * @return {@link Expense} - Expense record matching the id
	 * @throws Exception
	 *             throws {@link Exception}
	 */
	@Override
	public List<Expense> selectExpenseById(Integer id) throws Exception {
		return mapper.getExpenseById(id);
	}

	/**
	 * 
	 * @param records
	 *            {@link Expense} - List of expense records to DELETE
	 * @return {@link Integer} - No of records deleted
	 * @throws Exception
	 *             throws {@link Exception}
	 */
	@Override
	public Integer deleteExpense(List<Expense> records) throws Exception {
		int noOfRecords = 0;
		for (Expense record : records) {
			noOfRecords = noOfRecords + mapper.deleteExpense(record);
		}
		return noOfRecords;
	}

	/**
	 * 
	 */
	@Override
	public List<Units> getExpenseUnits() throws Exception {
		return mapper.getExpenseUnits();
	}

	/**
	 * 
	 */
	@Override
	public Integer updateExpenseUnits(List<Units> records) throws Exception {
		int noOfRecords = 0;
		for (Units record : records) {
			noOfRecords = noOfRecords + mapper.updateExpenseUnits(record);
		}
		return noOfRecords;

	}

	/**
	 * 
	 */
	@Override
	public Integer deleteExpenseUnits(List<Units> records) throws Exception {
		int noOfRecords = 0;
		for (Units record : records) {
			noOfRecords = noOfRecords + mapper.deleteExpenseUnits(record);
		}
		return noOfRecords;
	}

	/**
	 * 
	 */
	@Override
	public Integer createExpenseUnits(List<Units> records) throws Exception {
		return mapper.createExpenseUnits(records);
	}

	@Override
	public List<Integer> getExpenseYearRange(String username, String itemName) throws Exception {
		List<Integer> result = new ArrayList<Integer>();
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);

		Map<String, Integer> expenseRange = mapper.getExpenseYearRange(username, itemName);

		if (expenseRange != null && !expenseRange.isEmpty()) {
			Integer minYear = expenseRange.get("minm");
			Integer maxYear = expenseRange.get("maxm");

			if (minYear == maxYear) {
				result.add(maxYear);
			} else {

				for (int i = minYear; i <= maxYear; i++) {
					result.add(i);
				}

				if (!result.contains(currentYear)) {
					result.add(currentYear);
				}
			}

		} else {
			result.add(currentYear);
		}
		return result;
	}

	@Override
	public List<Integer> getExpenseMonthRange(String username, Integer year, String itemName) throws Exception {
		List<Integer> result = new ArrayList<Integer>();
		int currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;

		Map<String, Integer> expenseRange = mapper.getExpenseMonthRange(username, year, itemName);

		if (expenseRange != null && !expenseRange.isEmpty()) {
			Integer minMonth = expenseRange.get("minm");
			Integer maxMonth = expenseRange.get("maxm");

			if (minMonth == maxMonth) {
				result.add(maxMonth);
			} else {

				for (int i = minMonth; i <= maxMonth; i++) {
					result.add(i);
				}

				if (!result.contains(currentMonth)) {
					result.add(currentMonth);
				}
			}

		} else {
			result.add(currentMonth);
		}
		return result;
	}

	/**
	 * @param itemName
	 *            - Item Name
	 * @param username
	 *            - Username of the user.
	 * 
	 * @return {@link Graph} - List of Graph data.
	 * @throws {@link
	 *             Exception}
	 */
	@Override
	public List<Graph> getPriceGraph(String username, String itemName, Integer year) throws Exception {
		if (year == null || year == 0) {
			return mapper.getPriceGraph(itemName, username);
		} else {
			return mapper.getPriceGraphForYear(itemName, year, username);
		}
	}

	/**
	 * 
	 * @param month
	 *            {@link Integer} - Expense Month
	 * 
	 * @param year
	 *            {@link Integer} - Expense Year
	 * 
	 * @param username
	 *            {@link String} - Username of the user
	 * 
	 * @return {@link Graph} - List of Graph data.
	 * @throws {@link
	 *             Exception}
	 * 
	 */
	@Override
	public List<Graph> getExpenseTrend(String username, Integer year, Integer month) throws Exception {
		List<Graph> categoryWiseTotalExpense = mapper.getCategoryWiseTotalExpense(username, year, month);
		double totalExpense = 0;

		for (Graph graph : categoryWiseTotalExpense) {
			totalExpense = totalExpense + graph.getExpense();
		}

		for (Graph graph : categoryWiseTotalExpense) {
			graph.setAvg((graph.getExpense() / totalExpense) * 100);
		}

		return categoryWiseTotalExpense;
	}

}
