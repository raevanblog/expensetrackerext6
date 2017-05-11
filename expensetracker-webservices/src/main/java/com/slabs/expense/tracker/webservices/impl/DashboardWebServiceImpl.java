package com.slabs.expense.tracker.webservices.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.slabs.expense.tracker.common.exception.ExpenseTrackerException;
import com.slabs.expense.tracker.common.services.DashboardService;
import com.slabs.expense.tracker.common.webservices.DashboardWebService;
import com.slabs.expense.tracker.common.webservice.response.Operation;
import com.slabs.expense.tracker.common.webservice.response.Response;
import com.slabs.expense.tracker.webservices.response.ResponseGenerator;

/**
 * {@link DashboardWebServiceImpl} - Web Service for retrieving details for
 * Dashboard
 * 
 * @author Shyam Natarajan
 *
 */
@RestController
@RequestMapping(value = "api")
public class DashboardWebServiceImpl implements DashboardWebService {

	@Autowired
	private DashboardService service;

	/**
	 * This method will retrieve the summary of income and expense
	 * 
	 * @param username
	 *            {@link String} - Username of the user
	 * @param year
	 *            {@link Integer} - Year for which details need to be retrieved
	 * @param month
	 *            {@link Integer} - Month for which details need to be retrieved
	 * @return {@link com.slabs.expense.tracker.webservice.response.Response}
	 * @throws ExpenseTrackerException
	 *             throws {@link ExpenseTrackerException}
	 */
	@RequestMapping(value = "dashboard", method = { RequestMethod.GET }, produces = { "application/json", "application/xml" })
	@Override
	public Response getSummary(@RequestParam(name = "username") String username, @RequestParam(name = "year") int year,
			@RequestParam(name = "month", required = true) int month) throws ExpenseTrackerException {
		try {
			return ResponseGenerator.getSuccessResponse(service.getDashboardData(username, year, month), Operation.SELECT);
		} catch (Exception e) {
			throw new ExpenseTrackerException(e);
		}
	}

}
