package com.slabs.expense.tracker.core.web.services;

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

import com.slabs.expense.tracker.common.db.entity.Expense;
import com.slabs.expense.tracker.common.db.entity.ExpenseCategory;
import com.slabs.expense.tracker.core.ResponseGenerator;
import com.slabs.expense.tracker.core.ResponseStatus;
import com.slabs.expense.tracker.core.ServiceFactory;
import com.slabs.expense.tracker.core.exception.ExpenseTrackerException;
import com.slabs.expense.tracker.core.services.ExpenseCategoryService;
import com.slabs.expense.tracker.core.services.ExpenseService;
import com.slabs.expense.tracker.core.services.ExpenseTypeService;
import com.slabs.expense.tracker.core.services.Services;
import com.slabs.expense.tracker.webservice.response.Operation;
import com.slabs.expense.tracker.webservice.response.Response;

@Path("exptr-web")
public class ExpenseWebService {

	private static final Logger L = LoggerFactory.getLogger(ExpenseWebService.class);

	@Path("expensecategory/")
	@POST
	@Consumes(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response insertExpenseCategory(List<ExpenseCategory> records) throws ExpenseTrackerException {
		try {
			ExpenseCategoryService service = ServiceFactory.getInstance().getService(Services.EXPENSE_CATEGORY_SERVICE, ExpenseCategoryService.class);
			return ResponseGenerator.getSuccessResponse(service.insert(records), Operation.INSERT);
		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
			throw new ExpenseTrackerException(e, ResponseStatus.SERVER_ERROR);
		}
	}

	@Path("expensecategory/")
	@PUT
	@Consumes(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response updateExpenseCategory(List<ExpenseCategory> records) throws ExpenseTrackerException {
		try {
			ExpenseCategoryService service = ServiceFactory.getInstance().getService(Services.EXPENSE_CATEGORY_SERVICE, ExpenseCategoryService.class);
			return ResponseGenerator.getSuccessResponse(service.update(records), Operation.UPDATE);
		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
			throw new ExpenseTrackerException(e, ResponseStatus.SERVER_ERROR);
		}
	}

	@Path("expensecategory/")
	@GET
	@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getExpenseCategory(@QueryParam("categoryId") Integer categoryId) throws ExpenseTrackerException {
		try {
			ExpenseCategoryService service = ServiceFactory.getInstance().getService(Services.EXPENSE_CATEGORY_SERVICE, ExpenseCategoryService.class);
			return ResponseGenerator.getSuccessResponse(service.select(categoryId), Operation.SELECT);

		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
			throw new ExpenseTrackerException(e, ResponseStatus.SERVER_ERROR);
		}
	}

	@Path("expensecategory/")
	@DELETE
	@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response deleteExpenseCategory(List<ExpenseCategory> records) throws ExpenseTrackerException {
		try {
			ExpenseCategoryService service = ServiceFactory.getInstance().getService(Services.EXPENSE_CATEGORY_SERVICE, ExpenseCategoryService.class);
			return ResponseGenerator.getSuccessResponse(service.delete(records), Operation.DELETE);
		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
			throw new ExpenseTrackerException(e, ResponseStatus.SERVER_ERROR);
		}

	}

	@Path("expense/expensenames/")
	@GET
	@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getItemNames() throws ExpenseTrackerException {
		try {
			ExpenseService service = ServiceFactory.getInstance().getService(Services.EXPENSE_SERVICE, ExpenseService.class);
			return ResponseGenerator.getSuccessResponse(service.selectExpenseNames(), Operation.SELECT);
		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
			throw new ExpenseTrackerException(e, ResponseStatus.SERVER_ERROR);
		}
	}

	@Path("expensetype/")
	@GET
	@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getExpenseType() throws ExpenseTrackerException {
		try {
			ExpenseTypeService service = ServiceFactory.getInstance().getService(Services.EXPENSE_TYPE_SERVICE, ExpenseTypeService.class);
			return ResponseGenerator.getSuccessResponse(service.select(), Operation.SELECT);
		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
			throw new ExpenseTrackerException(e, ResponseStatus.SERVER_ERROR);
		}
	}

	@Path("expense/")
	@GET
	@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getExpense(@QueryParam("username") String username, @QueryParam("year") Integer year, @QueryParam("month") Integer month, @QueryParam("fetchTopExpense") boolean fetchTopExpense)
			throws ExpenseTrackerException {
		try {
			ExpenseService service = ServiceFactory.getInstance().getService(Services.EXPENSE_SERVICE, ExpenseService.class);
			if (username == null) {
				return ResponseGenerator.getExceptionResponse(ResponseStatus.BAD_REQUEST, "Parameter {username} is required");
			}
			return ResponseGenerator.getSuccessResponse(service.select(username, year, month, fetchTopExpense), Operation.SELECT);
		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
			throw new ExpenseTrackerException(e, ResponseStatus.SERVER_ERROR);
		}
	}

	@Path("expense/")
	@POST
	@Consumes(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response insertExpense(List<Expense> records) throws ExpenseTrackerException {
		try {
			ExpenseService service = ServiceFactory.getInstance().getService(Services.EXPENSE_SERVICE, ExpenseService.class);
			return ResponseGenerator.getSuccessResponse(service.insert(records), Operation.INSERT);
		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
			throw new ExpenseTrackerException(e, ResponseStatus.SERVER_ERROR);
		}
	}

	@Path("expense")
	@PUT
	@Consumes(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response updateExpense(List<Expense> records) throws ExpenseTrackerException {
		try {
			ExpenseService service = ServiceFactory.getInstance().getService(Services.EXPENSE_SERVICE, ExpenseService.class);
			return ResponseGenerator.getSuccessResponse(service.update(records), Operation.UPDATE);
		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
			throw new ExpenseTrackerException(e, ResponseStatus.SERVER_ERROR);
		}
	}

	@Path("expense/")
	@DELETE
	@Consumes(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response deleteExpense(List<Expense> records) throws ExpenseTrackerException {
		try {
			ExpenseService service = ServiceFactory.getInstance().getService(Services.EXPENSE_SERVICE, ExpenseService.class);
			return ResponseGenerator.getSuccessResponse(service.delete(records), Operation.DELETE);
		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
			throw new ExpenseTrackerException(e, ResponseStatus.SERVER_ERROR);
		}
	}

}
