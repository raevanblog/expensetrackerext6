package com.slabs.expense.tracker.core.web.services;

import java.util.List;

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

import com.slabs.expense.tracker.common.db.entity.Income;
import com.slabs.expense.tracker.core.ResponseGenerator;
import com.slabs.expense.tracker.core.ResponseStatus;
import com.slabs.expense.tracker.core.ServiceFactory;
import com.slabs.expense.tracker.core.exception.ExpenseTrackerException;
import com.slabs.expense.tracker.core.services.IncomeService;
import com.slabs.expense.tracker.core.services.Services;
import com.slabs.expense.tracker.webservice.response.Operation;
import com.slabs.expense.tracker.webservice.response.Response;

/**
 * {@link IncomeWebService} - Web Service for retrieving/updating Income
 * 
 * @author Shyam Natarajan
 *
 */
@Path("exptr-web")
public class IncomeWebService {

	private static final Logger L = LoggerFactory.getLogger(IncomeWebService.class);

	/**
	 * 
	 * @param username
	 *            {@link String} - Username of the user
	 * @param year
	 *            {@link Integer} - Year for which income need to be retrieved
	 * @param month
	 *            {@link Integer} - Month for which income need to be retrieved
	 * @return {@link com.slabs.expense.tracker.webservice.response.Response}
	 * @throws ExpenseTrackerException
	 *             throws {@link ExpenseTrackerException}
	 */
	@Path("income/")
	@GET
	@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getIncome(@QueryParam("username") String username,
			@QueryParam("year") Integer year, @QueryParam("month") Integer month)
			throws ExpenseTrackerException {

		try {
			if (username == null) {
				L.error("Exception occurred, BAD_REQUEST-username is required");
				throw new ExpenseTrackerException("username is required",
						ResponseStatus.BAD_REQUEST);
			}

			IncomeService service = ServiceFactory.getInstance().getService(Services.INCOME_SERVICE,
					IncomeService.class);

			return ResponseGenerator.getSuccessResponse(service.select(username, year, month),
					Operation.SELECT);
		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
			throw new ExpenseTrackerException(e, ResponseStatus.SERVER_ERROR);
		}
	}

	/**
	 * 
	 * @param records
	 *            {@link Income} - List of records to INSERT
	 * @return {@link com.slabs.expense.tracker.webservice.response.Response}
	 * @throws ExpenseTrackerException
	 *             throws {@link ExpenseTrackerException}
	 */
	@Path("income/")
	@POST
	@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response addIncome(List<Income> records) throws ExpenseTrackerException {
		try {
			IncomeService service = ServiceFactory.getInstance().getService(Services.INCOME_SERVICE,
					IncomeService.class);
			return ResponseGenerator.getSuccessResponse(service.insert(records), Operation.INSERT);
		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
			throw new ExpenseTrackerException(e, ResponseStatus.SERVER_ERROR);
		}

	}

	/**
	 * 
	 * @param records
	 *            {@link Income} - List of records to UPDATE
	 * @return {@link com.slabs.expense.tracker.webservice.response.Response}
	 * @throws ExpenseTrackerException
	 *             throws {@link ExpenseTrackerException}
	 */
	@Path("income/")
	@PUT
	@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response updateIncome(List<Income> records) throws ExpenseTrackerException {
		try {
			IncomeService service = ServiceFactory.getInstance().getService(Services.INCOME_SERVICE,
					IncomeService.class);
			return ResponseGenerator.getSuccessResponse(service.update(records), Operation.UPDATE);
		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
			throw new ExpenseTrackerException(e, ResponseStatus.SERVER_ERROR);
		}

	}

	/**
	 * 
	 * @param records
	 *            {@link Income} - List of records to DELETE
	 * @return {@link com.slabs.expense.tracker.webservice.response.Response}
	 * @throws ExpenseTrackerException
	 *             throws {@link ExpenseTrackerException}
	 */
	@Path("income/")
	@DELETE
	@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response deleteIncome(List<Income> records) throws ExpenseTrackerException {
		try {
			IncomeService service = ServiceFactory.getInstance().getService(Services.INCOME_SERVICE,
					IncomeService.class);
			return ResponseGenerator.getSuccessResponse(service.delete(records), Operation.DELETE);
		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
			throw new ExpenseTrackerException(e, ResponseStatus.SERVER_ERROR);
		}

	}

	/**
	 * This method will return a list of income type in the
	 * {@link com.slabs.expense.tracker.webservice.response.Response}
	 * 
	 * @return {@link com.slabs.expense.tracker.webservice.response.Response}
	 * @throws ExpenseTrackerException
	 *             throws {@link ExpenseTrackerException}
	 */
	@Path("incometype/")
	@GET
	@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getIncomeType() throws ExpenseTrackerException {
		try {
			IncomeService service = ServiceFactory.getInstance().getService(Services.INCOME_SERVICE,
					IncomeService.class);
			return ResponseGenerator.getSuccessResponse(service.selectIncomeType(),
					Operation.SELECT);
		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
			throw new ExpenseTrackerException(e, ResponseStatus.SERVER_ERROR);
		}
	}

}
