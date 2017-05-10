package com.slabs.expense.tracker.webservices.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.slabs.expense.tracker.common.database.entity.Graph;
import com.slabs.expense.tracker.common.exception.ExpenseTrackerException;
import com.slabs.expense.tracker.common.services.ExpenseService;
import com.slabs.expense.tracker.common.services.IncomeService;
import com.slabs.expense.tracker.common.webservices.GraphWebService;
import com.slabs.expense.tracker.webservice.response.Operation;
import com.slabs.expense.tracker.webservice.response.Response;
import com.slabs.expense.tracker.webservices.response.ResponseGenerator;
import com.slabs.expense.tracker.webservices.response.ResponseStatus;

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

	private static final String EXPENSE_VS_INCOME_MONTHLY = "EXPENSE_VS_INCOME_MONTHLY";

	private static final String CATEGORY_EXPENSE_TOTAL = "CATEGORY_EXPENSE_TOTAL";

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
	 * @return {@link com.slabs.expense.tracker.webservice.response.Response}
	 * @throws ExpenseTrackerException
	 *             throws {@link ExpenseTrackerException}
	 */
	@RequestMapping(value = "graph", method = { RequestMethod.GET }, produces = { "application/json", "application/xml" })
	@Override
	public Response getGraph(@RequestParam(name = "username") String username, @RequestParam(name = "year") Integer year,
			@RequestParam(name = "month", required = false) Integer month, @RequestParam(name = "type") String type) throws ExpenseTrackerException {
		try {
			if (EXPENSE_VS_INCOME_MONTHLY.equals(type)) {
				return ResponseGenerator.getSuccessResponse(getMonthlyExpenseVsIncome(username, year), Operation.SELECT);
			} else if (CATEGORY_EXPENSE_TOTAL.equals(type)) {
				return ResponseGenerator.getSuccessResponse(getCategoryWiseTotalExpense(username, year, month), Operation.SELECT);
			} else {
				return ResponseGenerator.getExceptionResponse(ResponseStatus.BAD_REQUEST, "Requested graph type is Wrong");
			}
		} catch (Exception e) {
			throw new ExpenseTrackerException(e);
		}
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
