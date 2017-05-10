package com.slabs.expense.tracker.webservices.impl;

import java.util.List;

import javax.xml.ws.WebServiceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.slabs.expense.tracker.common.database.entity.Income;
import com.slabs.expense.tracker.common.exception.ExpenseTrackerException;
import com.slabs.expense.tracker.common.services.IncomeService;
import com.slabs.expense.tracker.common.services.Services;
import com.slabs.expense.tracker.common.webservices.IncomeWebService;
import com.slabs.expense.tracker.core.ServiceFactory;
import com.slabs.expense.tracker.webservice.response.Operation;
import com.slabs.expense.tracker.webservice.response.Response;
import com.slabs.expense.tracker.webservices.response.ResponseGenerator;

/**
 * {@link IncomeWebServiceImpl} - Web Service for retrieving/updating Income
 * 
 * @author Shyam Natarajan
 *
 */
@RestController
@RequestMapping(value = "api")
public class IncomeWebServiceImpl implements IncomeWebService {

	private static final Logger L = LoggerFactory.getLogger(IncomeWebServiceImpl.class);

	/**
	 * 
	 * @param username
	 *            {@link String} - Username of the user
	 * @param year
	 *            {@link Integer} - Year for which income need to be retrieved
	 * @param month
	 *            {@link Integer} - Month for which income need to be retrieved
	 * @return {@link com.slabs.expense.tracker.webservice.response.Response}
	 * @throws WebServiceException
	 *             throws {@link WebServiceException}
	 */
	@RequestMapping(value = "income", method = { RequestMethod.GET }, produces = { "application/json", "application/xml" })
	@Override
	public Response getIncome(@RequestParam(name = "username") String username, @RequestParam(name = "year") Integer year,
			@RequestParam(name = "month", required = false) Integer month) throws ExpenseTrackerException {

		try {
			if (username == null) {
				L.error("Exception occurred, BAD_REQUEST-username is required");
				throw new ExpenseTrackerException("username is required");
			}

			IncomeService service = ServiceFactory.getInstance().getService(Services.INCOME_SERVICE, IncomeService.class);

			return ResponseGenerator.getSuccessResponse(service.selectIncome(username, year, month), Operation.SELECT);
		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
			throw new ExpenseTrackerException(e);
		}
	}

	/**
	 * 
	 * @param records
	 *            {@link Income} - List of records to INSERT
	 * @return {@link com.slabs.expense.tracker.webservice.response.Response}
	 * @throws WebServiceException
	 *             throws {@link WebServiceException}
	 */
	@RequestMapping(value = "income", method = { RequestMethod.POST }, produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	@Override
	public Response addIncome(List<Income> records) throws ExpenseTrackerException {
		try {
			IncomeService service = ServiceFactory.getInstance().getService(Services.INCOME_SERVICE, IncomeService.class);
			return ResponseGenerator.getSuccessResponse(service.createIncome(records), Operation.INSERT);
		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
			throw new ExpenseTrackerException(e);
		}

	}

	/**
	 * 
	 * @param records
	 *            {@link Income} - List of records to UPDATE
	 * @return {@link com.slabs.expense.tracker.webservice.response.Response}
	 * @throws WebServiceException
	 *             throws {@link WebServiceException}
	 */
	@RequestMapping(value = "income", method = { RequestMethod.PUT }, produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	@Override
	public Response updateIncome(List<Income> records) throws ExpenseTrackerException {
		try {
			IncomeService service = ServiceFactory.getInstance().getService(Services.INCOME_SERVICE, IncomeService.class);
			return ResponseGenerator.getSuccessResponse(service.updateIncome(records), Operation.UPDATE);
		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
			throw new ExpenseTrackerException(e);
		}

	}

	/**
	 * 
	 * @param records
	 *            {@link Income} - List of records to DELETE
	 * @return {@link com.slabs.expense.tracker.webservice.response.Response}
	 * @throws WebServiceException
	 *             throws {@link WebServiceException}
	 */
	@RequestMapping(value = "income", method = { RequestMethod.DELETE }, produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	@Override
	public Response deleteIncome(List<Income> records) throws ExpenseTrackerException {
		try {
			IncomeService service = ServiceFactory.getInstance().getService(Services.INCOME_SERVICE, IncomeService.class);
			return ResponseGenerator.getSuccessResponse(service.deleteIncome(records), Operation.DELETE);
		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
			throw new ExpenseTrackerException(e);
		}

	}

}
