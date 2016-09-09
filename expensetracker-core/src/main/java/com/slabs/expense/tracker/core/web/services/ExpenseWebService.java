package com.slabs.expense.tracker.core.web.services;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.slabs.expense.tracker.common.db.entity.ExpenseCategory;
import com.slabs.expense.tracker.core.ServiceFactory;
import com.slabs.expense.tracker.core.services.ExpenseCategoryService;
import com.slabs.expense.tracker.core.services.Services;

@Path("exptr-web-api")
public class ExpenseWebService {

	@Path("expensecategory/")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<ExpenseCategory> getExpenseCategory() throws Exception {
		ExpenseCategoryService service = (ExpenseCategoryService) ServiceFactory.getInstance()
				.getService(Services.EXPENSE_CATEGORY_SERVICE);

		return service.select(null);
	}

	@Path("expensecategory/category_id/{category_id}")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<ExpenseCategory> getExpenseCategory(@PathParam("category_id") String categoryId) throws Exception {
		ExpenseCategoryService service = (ExpenseCategoryService) ServiceFactory.getInstance()
				.getService(Services.EXPENSE_CATEGORY_SERVICE);
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("category_id", categoryId);

		return service.select(parameters);
	}

}
