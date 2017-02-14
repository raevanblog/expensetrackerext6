package com.slabs.expense.tracker.webservices;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.slabs.expense.tracker.common.db.entity.Graph;
import com.slabs.expense.tracker.common.exception.ExpenseTrackerException;
import com.slabs.expense.tracker.common.services.ExpenseService;
import com.slabs.expense.tracker.common.services.IncomeService;
import com.slabs.expense.tracker.common.services.Services;
import com.slabs.expense.tracker.core.ServiceFactory;
import com.slabs.expense.tracker.webservice.response.Operation;
import com.slabs.expense.tracker.webservice.response.Response;
import com.slabs.expense.tracker.webservices.exception.WebServiceException;
import com.slabs.expense.tracker.webservices.response.ResponseGenerator;
import com.slabs.expense.tracker.webservices.response.ResponseStatus;

/**
 * 
 * {@link GraphWebService} - Web Service for retrieving for Chart
 * 
 * @author Shyam Natarajan
 *
 */
@Path("exptr-web")
public class GraphWebService {

	private static final Logger L = LoggerFactory.getLogger(GraphWebService.class);

	private static final String EXPENSE_VS_INCOME_MONTHLY = "EXPENSE_VS_INCOME_MONTHLY";

	private static final String CATEGORY_EXPENSE_TOTAL = "CATEGORY_EXPENSE_TOTAL";

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
	@Path("graph")
	@GET
	@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getGraph(@QueryParam("username") String username,
			@QueryParam("year") Integer year, @QueryParam("month") Integer month,
			@QueryParam("type") String type) throws ExpenseTrackerException {
		try {
			if (EXPENSE_VS_INCOME_MONTHLY.equals(type)) {
				return ResponseGenerator.getSuccessResponse(
						getMonthlyExpenseVsIncome(username, year), Operation.SELECT);
			} else if (CATEGORY_EXPENSE_TOTAL.equals(type)) {
				return ResponseGenerator.getSuccessResponse(
						getCategoryWiseTotalExpense(username, year, month), Operation.SELECT);
			} else {
				return ResponseGenerator.getExceptionResponse(ResponseStatus.BAD_REQUEST,
						"Requested graph type is Wrong");
			}
		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
			throw new WebServiceException(e, ResponseStatus.SERVER_ERROR);
		}
	}

	private List<Graph> getMonthlyExpenseVsIncome(String username, Integer year) throws Exception {
		ExpenseService eService = ServiceFactory.getInstance()
				.getService(Services.EXPENSE_SERVICE, ExpenseService.class);
		IncomeService iService = ServiceFactory.getInstance()
				.getService(Services.INCOME_SERVICE, IncomeService.class);

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

	private List<Graph> getCategoryWiseTotalExpense(@QueryParam("username") String username,
			@QueryParam("year") Integer year, @QueryParam("month") Integer month) throws Exception {
		ExpenseService service = ServiceFactory.getInstance()
				.getService(Services.EXPENSE_SERVICE, ExpenseService.class);
		return service.getCategoryWiseTotalExpense(username, year, month);
	}

}
