package com.slabs.expense.tracker.webservices.impl;

import java.util.List;

import javax.xml.ws.WebServiceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.slabs.expense.tracker.common.database.entity.Expense;
import com.slabs.expense.tracker.common.database.entity.ExpenseCategory;
import com.slabs.expense.tracker.common.database.entity.Units;
import com.slabs.expense.tracker.common.webservice.response.Operation;
import com.slabs.expense.tracker.common.webservice.response.Response;
import com.slabs.expense.tracker.webservices.response.ResponseGenerator;
import com.slabs.expense.tracker.webservices.response.ResponseStatus;
import com.slabs.expensetracker.common.exception.ExpenseTrackerException;
import com.slabs.expensetracker.common.services.ExpenseCategoryService;
import com.slabs.expensetracker.common.services.ExpenseService;
import com.slabs.expensetracker.common.webservices.ExpenseWebService;

/**
 * {@link ExpenseWebServiceImpl} - Web Service for retrieving/updating Expense
 * 
 * @author Shyam Natarajan
 *
 */
@RestController
@RequestMapping(value = "api")
public class ExpenseWebServiceImpl implements ExpenseWebService {

	@Autowired
	private ExpenseService service;

	@Autowired
	private ExpenseCategoryService categoryService;

	/**
	 * 
	 * @param records
	 *            {@link Units} - List of records to INSERT
	 * @return {@link com.slabs.expense.tracker.webservice.response.Response}
	 * @throws WebServiceException
	 *             throws {@link WebServiceException}
	 */
	@RequestMapping(value = "expenseunits", method = { RequestMethod.POST }, produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	@Override
	public Response createExpenseUnits(@RequestBody List<Units> records) throws ExpenseTrackerException {
		try {
			return ResponseGenerator.getSuccessResponse(service.createExpenseUnits(records), Operation.INSERT);
		} catch (Exception e) {
			throw new ExpenseTrackerException(e);
		}
	}

	/**
	 * 
	 * @param records
	 *            {@link Units} - List of records to UPDATE
	 * @return {@link com.slabs.expense.tracker.webservice.response.Response}
	 * @throws WebServiceException
	 *             throws {@link WebServiceException}
	 */
	@RequestMapping(value = "expenseunits", method = { RequestMethod.PUT }, produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	@Override
	public Response updateExpenseUnits(@RequestBody List<Units> records) throws ExpenseTrackerException {
		try {
			return ResponseGenerator.getSuccessResponse(service.updateExpenseUnits(records), Operation.UPDATE);
		} catch (Exception e) {
			throw new ExpenseTrackerException(e);
		}
	}

	/**
	 * 
	 * @param username
	 *            {@link String} - Username of the User
	 * @return {@link com.slabs.expense.tracker.webservice.response.Response}
	 * @throws WebServiceException
	 *             throws {@link WebServiceException}
	 */
	@RequestMapping(value = "expenseunits", method = { RequestMethod.GET }, produces = { "application/json", "application/xml" })
	@Override
	public Response getExpenseUnits() throws ExpenseTrackerException {
		try {
			return ResponseGenerator.getSuccessResponse(service.getExpenseUnits(), Operation.SELECT);
		} catch (Exception e) {
			throw new ExpenseTrackerException(e);
		}
	}

	/**
	 * 
	 * @param records
	 *            {@link Units} - List of records to DELETE
	 * @return {@link com.slabs.expense.tracker.webservice.response.Response}
	 * @throws WebServiceException
	 *             throws {@link WebServiceException}
	 */
	@RequestMapping(value = "expenseunits", method = { RequestMethod.DELETE }, produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	@Override
	public Response deleteExpenseUnits(@RequestBody List<Units> records) throws ExpenseTrackerException {
		try {
			return ResponseGenerator.getSuccessResponse(service.deleteExpenseUnits(records), Operation.DELETE);
		} catch (Exception e) {
			throw new ExpenseTrackerException(e);
		}

	}

	/**
	 * 
	 * @param records
	 *            {@link ExpenseCategory} - List of records to INSERT
	 * @return {@link com.slabs.expense.tracker.webservice.response.Response}
	 * @throws WebServiceException
	 *             throws {@link WebServiceException}
	 */
	@RequestMapping(value = "expensecategory", method = { RequestMethod.POST }, produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	@Override
	public Response insertExpenseCategory(@RequestBody List<ExpenseCategory> records) throws ExpenseTrackerException {
		try {
			return ResponseGenerator.getSuccessResponse(categoryService.createCategory(records), Operation.INSERT);
		} catch (Exception e) {
			throw new ExpenseTrackerException(e);
		}
	}

	/**
	 * 
	 * @param records
	 *            {@link ExpenseCategory} - List of records to UPDATE
	 * @return {@link com.slabs.expense.tracker.webservice.response.Response}
	 * @throws WebServiceException
	 *             throws {@link WebServiceException}
	 */
	@RequestMapping(value = "expensecategory", method = { RequestMethod.PUT }, produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	@Override
	public Response updateExpenseCategory(@RequestBody List<ExpenseCategory> records) throws ExpenseTrackerException {
		try {
			return ResponseGenerator.getSuccessResponse(categoryService.updateCategory(records), Operation.UPDATE);
		} catch (Exception e) {
			throw new ExpenseTrackerException(e);
		}
	}

	/**
	 * 
	 * @param username
	 *            {@link String} - Username of the User
	 * @return {@link com.slabs.expense.tracker.webservice.response.Response}
	 * @throws WebServiceException
	 *             throws {@link WebServiceException}
	 */
	@RequestMapping(value = "expensecategory", method = { RequestMethod.GET }, produces = { "application/json", "application/xml" })
	@Override
	public Response getExpenseCategory(@RequestParam(name = "username") String username) throws ExpenseTrackerException {
		try {
			return ResponseGenerator.getSuccessResponse(categoryService.selectCategory(username), Operation.SELECT);
		} catch (Exception e) {
			throw new ExpenseTrackerException(e);
		}
	}

	/**
	 * 
	 * @param records
	 *            {@link ExpenseCategory} - List of records to DELETE
	 * @return {@link com.slabs.expense.tracker.webservice.response.Response}
	 * @throws WebServiceException
	 *             throws {@link WebServiceException}
	 */
	@RequestMapping(value = "expensecategory", method = { RequestMethod.DELETE }, produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	@Override
	public Response deleteExpenseCategory(@RequestBody List<ExpenseCategory> records) throws ExpenseTrackerException {
		try {
			return ResponseGenerator.getSuccessResponse(categoryService.deleteCategory(records), Operation.DELETE);
		} catch (Exception e) {
			throw new ExpenseTrackerException(e);
		}

	}

	/**
	 * 
	 * @param username
	 *            {@link String} - Username of the user
	 * @param year
	 *            {@link Integer} - Year for which expense need to be retrieved
	 * @param month
	 *            {@link Integer} - Month for which expense need to be retrieved
	 * @param fetchTopExpense
	 *            {@link Boolean} - True to fetch top expense for the given
	 *            <code>year</code> and <code>month</code>
	 * @return {@link com.slabs.expense.tracker.webservice.response.Response}
	 * @throws WebServiceException
	 *             throws {@link WebServiceException}
	 */
	@RequestMapping(value = "expense", method = { RequestMethod.GET }, produces = { "application/json", "application/xml" })
	@Override
	public Response getExpense(@RequestParam(name = "username") String username, @RequestParam(name = "year") Integer year,
			@RequestParam(name = "month", required = false) Integer month,
			@RequestParam(name = "fetchTopExpense", required = false) boolean fetchTopExpense) throws ExpenseTrackerException {
		try {
			if (username == null) {
				return ResponseGenerator.getExceptionResponse(ResponseStatus.BAD_REQUEST, "Parameter {username} is required");
			}
			return ResponseGenerator.getSuccessResponse(service.selectExpense(username, year, month, fetchTopExpense), Operation.SELECT);
		} catch (Exception e) {
			throw new ExpenseTrackerException(e);
		}
	}

	/**
	 * 
	 * @param records
	 *            {@link Expense} - List of records to INSERT
	 * @return {@link com.slabs.expense.tracker.webservice.response.Response}
	 * @throws WebServiceException
	 *             throws {@link WebServiceException}
	 */
	@RequestMapping(value = "expense", method = { RequestMethod.POST }, produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	@Override
	public Response createExpense(@RequestBody List<Expense> records) throws ExpenseTrackerException {
		try {
			return ResponseGenerator.getSuccessResponse(service.createExpense(records), Operation.INSERT);
		} catch (Exception e) {
			throw new ExpenseTrackerException(e);
		}
	}

	/**
	 * 
	 * @param records
	 *            {@link Expense} - List of records to UPDATE
	 * @return {@link com.slabs.expense.tracker.webservice.response.Response}
	 * @throws WebServiceException
	 *             throws {@link WebServiceException}
	 */
	@RequestMapping(value = "expense", method = { RequestMethod.PUT }, produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	@Override
	public Response updateExpense(@RequestBody List<Expense> records) throws ExpenseTrackerException {
		try {
			return ResponseGenerator.getSuccessResponse(service.updateExpense(records), Operation.UPDATE);
		} catch (Exception e) {
			throw new ExpenseTrackerException(e);
		}
	}

	/**
	 * 
	 * @param records
	 *            {@link Expense} - List of records to DELETE
	 * @return {@link com.slabs.expense.tracker.webservice.response.Response}
	 * @throws WebServiceException
	 *             throws {@link WebServiceException}
	 */
	@RequestMapping(value = "expense", method = { RequestMethod.DELETE }, produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	@Override
	public Response deleteExpense(@RequestBody List<Expense> records) throws ExpenseTrackerException {
		try {
			return ResponseGenerator.getSuccessResponse(service.deleteExpense(records), Operation.DELETE);
		} catch (Exception e) {
			throw new ExpenseTrackerException(e);
		}
	}

}
