package com.slabs.expense.tracker.core.web.services;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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
import com.slabs.expense.tracker.core.services.Services;
import com.slabs.expense.tracker.webservice.response.Operation;
import com.slabs.expense.tracker.webservice.response.Response;

@Path("exptr-web-api")
public class ExpenseWebService {

	private static final Logger L = LoggerFactory.getLogger(ExpenseWebService.class);

	@Path("expensecategory/")
	@POST
	@Consumes(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response insertExpenseCategory(List<ExpenseCategory> records) throws ExpenseTrackerException {
		try {
			ExpenseCategoryService service = ServiceFactory.getInstance().getService(Services.EXPENSE_SERVICE,
					ExpenseCategoryService.class);
			return ResponseGenerator.getSucessResponse(service.insert(records), Operation.INSERT);
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
			ExpenseCategoryService service = ServiceFactory.getInstance().getService(Services.EXPENSE_CATEGORY_SERVICE,
					ExpenseCategoryService.class);
			return ResponseGenerator.getSucessResponse(service.update(records), Operation.UPDATE);
		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
			throw new ExpenseTrackerException(e, ResponseStatus.SERVER_ERROR);
		}
	}

	@Path("expensecategory/")
	@GET
	@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getExpenseCategory() throws ExpenseTrackerException {
		return getExpenseCategory(null);
	}

	@Path("expensecategory/categoryid/{category_id}")
	@GET
	@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getExpenseCategory(@PathParam("category_id") Integer categoryId) throws ExpenseTrackerException {
		try {
			ExpenseCategoryService service = ServiceFactory.getInstance().getService(Services.EXPENSE_CATEGORY_SERVICE,
					ExpenseCategoryService.class);
			return ResponseGenerator.getSucessResponse(service.select(categoryId), Operation.SELECT);

		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
			throw new ExpenseTrackerException(e, ResponseStatus.SERVER_ERROR);
		}
	}

	@Path("expensecategory/")
	@DELETE
	@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response deleteExpenseCategory() throws ExpenseTrackerException {
		return deleteExpenseCategory(null);
	}

	@Path("expensecategory/categoryid/{category_id}")
	@DELETE
	@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response deleteExpenseCategory(@PathParam("category_id") Integer categoryId) throws ExpenseTrackerException {
		try {
			ExpenseCategoryService service = ServiceFactory.getInstance().getService(Services.EXPENSE_CATEGORY_SERVICE,
					ExpenseCategoryService.class);
			return ResponseGenerator.getSucessResponse(service.delete(categoryId), Operation.DELETE);
		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
			throw new ExpenseTrackerException(e, ResponseStatus.SERVER_ERROR);
		}

	}

	@Path("expense/")
	@POST
	@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response insertExpense(List<Expense> records) throws ExpenseTrackerException {
		try {
			ExpenseService service = ServiceFactory.getInstance().getService(Services.EXPENSE_SERVICE,
					ExpenseService.class);
			return service.insert(records);
		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
			throw new ExpenseTrackerException(e, ResponseStatus.SERVER_ERROR);
		}
	}

}
