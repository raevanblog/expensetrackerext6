package com.slabs.expensetracker.webservices.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.slabs.expensetracker.common.constants.Constants;
import com.slabs.expensetracker.common.database.entity.Graph;
import com.slabs.expensetracker.common.exception.ExpenseTrackerException;
import com.slabs.expensetracker.common.services.ExpenseService;
import com.slabs.expensetracker.common.services.IncomeService;
import com.slabs.expensetracker.common.webservice.response.Operation;
import com.slabs.expensetracker.common.webservice.response.Response;
import com.slabs.expensetracker.common.webservices.GraphWebService;
import com.slabs.expensetracker.webservices.core.MessageConstants;
import com.slabs.expensetracker.webservices.response.ResponseGenerator;

/**
 * 
 * {@link GraphWebServiceImpl} - Web Service for retrieving for Chart
 * 
 * @author Shyam Natarajan
 *
 */
@RestController
@RequestMapping(value = "api")
public class GraphWebServiceImpl implements GraphWebService {

	@Autowired
	private ExpenseService eService;

	@Autowired
	private IncomeService iService;

	/**
	 * 
	 * @param username
	 *            {@link String} - Username of the year
	 * @param year
	 *            {@link Integer} - Year for which data need to be retrieved
	 * @param month
	 *            {@link Integer} - Month for which data need to be retrieved
	 * 
	 * @param name
	 *            {@link String} - Item name for which data need to be retrieved
	 * 
	 * @param type
	 *            {@link String} - Type of Graph. Supported types are
	 *            EXPENSE_VS_INCOME_MONTHLY, CATEGORY_EXPENSE_TOTAL,
	 *            PRICE_GRAPH, PRICE_GRAPH_YEARLY
	 * 
	 * @return {@link com.slabs.expense.tracker.webservice.response.Response}
	 * @throws ExpenseTrackerException
	 *             throws {@link ExpenseTrackerException}
	 * 
	 *             ------------- EXPENSE_VS_INCOME_MONTHLY --------------
	 * 
	 *             Required Parameters - username, year
	 * 
	 *             ------------- CATEGORY_EXPENSE_TOTAL -------------
	 * 
	 *             Required Parameters - username, year, month
	 * 
	 *             ------------- PRICE_GRAPH -------------
	 * 
	 *             Required Parameters - username, name
	 * 
	 *             ------------- PRICE_GRAPH_YEARLY --------------
	 * 
	 *             Required Parameters - username, name, year
	 */
	@RequestMapping(value = "graph", method = { RequestMethod.GET }, produces = { "application/json", "application/xml" })
	@Override
	public Response getGraph(@RequestParam(name = "username") String username, @RequestParam(name = "year", required = false) Integer year,
			@RequestParam(name = "month", required = false) Integer month, @RequestParam(name = "name", required = false) String name, @RequestParam(name = "type") String type)
			throws ExpenseTrackerException {
		try {
			if (Constants.EXPENSE_VS_INCOME_MONTHLY.equals(type)) {
				return ResponseGenerator.getSuccessResponse(getMonthlyExpenseVsIncome(username, year), Operation.SELECT);
			} else if (Constants.CATEGORY_EXPENSE_TOTAL.equals(type)) {
				return ResponseGenerator.getSuccessResponse(getCategoryWiseTotalExpense(username, year, month), Operation.SELECT);
			} else if (Constants.PRICE_GRAPH.equals(type)) {
				return ResponseGenerator.getSuccessResponse(getPriceGraph(username, name, null), Operation.SELECT);
			} else if (Constants.PRICE_GRAPH_YEARLY.equals(type)) {
				return ResponseGenerator.getSuccessResponse(getPriceGraph(username, name, year), Operation.SELECT);
			} else if (Constants.EXPENSE_TREND.equals(type)) {
				return ResponseGenerator.getSuccessResponse(getExpenseTrend(username, year, null), Operation.SELECT);
			} else if (Constants.EXPENSE_TREND_MONTHLY.equals(type)) {
				return ResponseGenerator.getSuccessResponse(getExpenseTrend(username, year, month), Operation.SELECT);
			} else {
				return ResponseGenerator.getExceptionResponse(HttpStatus.BAD_REQUEST, "Invalid Graph Type");
			}
		} catch (Exception e) {
			throw new ExpenseTrackerException(MessageConstants.EXCEPTION, e);
		}
	}

	private List<Graph> getExpenseTrend(String username, Integer year, Integer month) throws Exception {
		return eService.getExpenseTrend(username, year, month);
	}

	private List<Graph> getPriceGraph(String username, String name, Integer year) throws Exception {
		return eService.getPriceGraph(username, name, year);
	}

	private List<Graph> getMonthlyExpenseVsIncome(String username, Integer year) throws Exception {

		List<Graph> eGraph = eService.getMonthWiseTotalExpense(username, year);
		List<Graph> iGraph = iService.getMonthWiseTotalIncome(username, year);

		Comparator<Graph> c = new Comparator<Graph>() {

			@Override
			public int compare(Graph graph1, Graph graph2) {
				return ((Integer) graph1.getMonth()).compareTo(graph2.getMonth());
			}
		};

		Collections.sort(eGraph, c);
		Collections.sort(iGraph, c);

		for (int i = 0; i < eGraph.size(); i++) {
			eGraph.get(i).setIncome(iGraph.get(i).getIncome());
		}

		return eGraph;
	}

	private List<Graph> getCategoryWiseTotalExpense(@RequestParam(name = "username") String username, @RequestParam(name = "year") Integer year,
			@RequestParam(name = "month", required = false) Integer month) throws Exception {
		return eService.getCategoryWiseTotalExpense(username, year, month);
	}

}
