package com.slabs.expense.tracker.webservices;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.slabs.expense.tracker.common.database.entity.Expense;
import com.slabs.expense.tracker.common.database.entity.ExpenseCategory;
import com.slabs.expense.tracker.common.database.entity.Units;
import com.slabs.expense.tracker.common.exception.ExpenseTrackerException;
import com.slabs.expense.tracker.common.services.ExpenseCategoryService;
import com.slabs.expense.tracker.common.services.ExpenseService;
import com.slabs.expense.tracker.common.services.Services;
import com.slabs.expense.tracker.core.ServiceFactory;
import com.slabs.expense.tracker.webservice.response.Operation;
import com.slabs.expense.tracker.webservice.response.Response;
import com.slabs.expense.tracker.webservices.exception.WebServiceException;
import com.slabs.expense.tracker.webservices.response.ResponseGenerator;
import com.slabs.expense.tracker.webservices.response.ResponseStatus;

/**
 * {@link ExpenseWebService} - Web Service for retrieving/updating Expense
 * 
 * @author Shyam Natarajan
 *
 */
@Path("exptr-web")
public class ExpenseWebService {

	private static final Logger L = LoggerFactory.getLogger(ExpenseWebService.class);

	/**
	 * 
	 * @param records
	 *            {@link Units} - List of records to INSERT
	 * @return {@link com.slabs.expense.tracker.webservice.response.Response}
	 * @throws WebServiceException
	 *             throws {@link WebServiceException}
	 */
	@Path("expenseunits/")
	@POST
	@Consumes(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response createExpenseUnits(List<Units> records) throws ExpenseTrackerException {
		try {
			ExpenseService service = ServiceFactory.getInstance().getService(Services.EXPENSE_SERVICE, ExpenseService.class);
			return ResponseGenerator.getSuccessResponse(service.createExpenseUnits(records), Operation.INSERT);
		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
			throw new WebServiceException(e, ResponseStatus.SERVER_ERROR);
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
	@Path("expenseunits/")
	@PUT
	@Consumes(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response updateExpenseUnits(List<Units> records) throws ExpenseTrackerException {
		try {
			ExpenseService service = ServiceFactory.getInstance().getService(Services.EXPENSE_SERVICE, ExpenseService.class);
			return ResponseGenerator.getSuccessResponse(service.updateExpenseUnits(records), Operation.UPDATE);
		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
			throw new WebServiceException(e, ResponseStatus.SERVER_ERROR);
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
	@Path("expenseunits/")
	@GET
	@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getExpenseUnits(@QueryParam("username") String username) throws ExpenseTrackerException {
		try {
			ExpenseService service = ServiceFactory.getInstance().getService(Services.EXPENSE_SERVICE, ExpenseService.class);
			return ResponseGenerator.getSuccessResponse(service.getExpenseUnits(username), Operation.SELECT);

		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
			throw new WebServiceException(e, ResponseStatus.SERVER_ERROR);
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
	@Path("expenseunits/")
	@DELETE
	@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response deleteExpenseUnits(List<Units> records) throws ExpenseTrackerException {
		try {
			ExpenseService service = ServiceFactory.getInstance().getService(Services.EXPENSE_SERVICE, ExpenseService.class);
			return ResponseGenerator.getSuccessResponse(service.deleteExpenseUnits(records), Operation.DELETE);
		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
			throw new WebServiceException(e, ResponseStatus.SERVER_ERROR);
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
	@Path("expensecategory/")
	@POST
	@Consumes(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response insertExpenseCategory(List<ExpenseCategory> records) throws ExpenseTrackerException {
		try {
			ExpenseCategoryService service = ServiceFactory.getInstance().getService(Services.EXPENSE_CATEGORY_SERVICE, ExpenseCategoryService.class);
			return ResponseGenerator.getSuccessResponse(service.createCategory(records), Operation.INSERT);
		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
			throw new WebServiceException(e, ResponseStatus.SERVER_ERROR);
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
	@Path("expensecategory/")
	@PUT
	@Consumes(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response updateExpenseCategory(List<ExpenseCategory> records) throws ExpenseTrackerException {
		try {
			ExpenseCategoryService service = ServiceFactory.getInstance().getService(Services.EXPENSE_CATEGORY_SERVICE, ExpenseCategoryService.class);
			return ResponseGenerator.getSuccessResponse(service.updateCategory(records), Operation.UPDATE);
		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
			throw new WebServiceException(e, ResponseStatus.SERVER_ERROR);
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
	@Path("expensecategory/")
	@GET
	@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getExpenseCategory(@QueryParam("username") String username) throws ExpenseTrackerException {
		try {
			ExpenseCategoryService service = ServiceFactory.getInstance().getService(Services.EXPENSE_CATEGORY_SERVICE, ExpenseCategoryService.class);
			return ResponseGenerator.getSuccessResponse(service.selectCategory(username), Operation.SELECT);

		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
			throw new WebServiceException(e, ResponseStatus.SERVER_ERROR);
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
	@Path("expensecategory/")
	@DELETE
	@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response deleteExpenseCategory(List<ExpenseCategory> records) throws ExpenseTrackerException {
		try {
			ExpenseCategoryService service = ServiceFactory.getInstance().getService(Services.EXPENSE_CATEGORY_SERVICE, ExpenseCategoryService.class);
			return ResponseGenerator.getSuccessResponse(service.deleteCategory(records), Operation.DELETE);
		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
			throw new WebServiceException(e, ResponseStatus.SERVER_ERROR);
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
	@Path("expense/")
	@GET
	@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getExpense(@QueryParam("username") String username, @QueryParam("year") Integer year, @QueryParam("month") Integer month,
			@QueryParam("fetchTopExpense") boolean fetchTopExpense) throws ExpenseTrackerException {
		try {
			ExpenseService service = ServiceFactory.getInstance().getService(Services.EXPENSE_SERVICE, ExpenseService.class);
			if (username == null) {
				return ResponseGenerator.getExceptionResponse(ResponseStatus.BAD_REQUEST, "Parameter {username} is required");
			}
			return ResponseGenerator.getSuccessResponse(service.selectExpense(username, year, month, fetchTopExpense), Operation.SELECT);
		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
			throw new WebServiceException(e, ResponseStatus.SERVER_ERROR);
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
	@Path("expense/")
	@POST
	@Consumes(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response createExpense(List<Expense> records) throws ExpenseTrackerException {
		try {
			ExpenseService service = ServiceFactory.getInstance().getService(Services.EXPENSE_SERVICE, ExpenseService.class);
			return ResponseGenerator.getSuccessResponse(service.createExpense(records), Operation.INSERT);
		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
			throw new WebServiceException(e, ResponseStatus.SERVER_ERROR);
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
	@Path("expense")
	@PUT
	@Consumes(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response updateExpense(List<Expense> records) throws ExpenseTrackerException {
		try {
			ExpenseService service = ServiceFactory.getInstance().getService(Services.EXPENSE_SERVICE, ExpenseService.class);
			return ResponseGenerator.getSuccessResponse(service.updateExpense(records), Operation.UPDATE);
		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
			throw new WebServiceException(e, ResponseStatus.SERVER_ERROR);
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
	@Path("expense/")
	@DELETE
	@Consumes(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response deleteExpense(List<Expense> records) throws ExpenseTrackerException {
		try {
			ExpenseService service = ServiceFactory.getInstance().getService(Services.EXPENSE_SERVICE, ExpenseService.class);
			return ResponseGenerator.getSuccessResponse(service.deleteExpense(records), Operation.DELETE);
		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
			throw new WebServiceException(e, ResponseStatus.SERVER_ERROR);
		}
	}

}
