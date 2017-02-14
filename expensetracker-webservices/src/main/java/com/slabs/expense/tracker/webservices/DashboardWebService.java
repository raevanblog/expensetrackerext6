package com.slabs.expense.tracker.webservices;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.slabs.expense.tracker.common.services.DashboardService;
import com.slabs.expense.tracker.common.services.Services;
import com.slabs.expense.tracker.core.ServiceFactory;
import com.slabs.expense.tracker.core.exception.ExpenseTrackerException;
import com.slabs.expense.tracker.webservice.response.Operation;
import com.slabs.expense.tracker.webservice.response.Response;
import com.slabs.expense.tracker.webservices.core.ResponseGenerator;
import com.slabs.expense.tracker.webservices.core.ResponseStatus;
import com.slabs.expense.tracker.webservices.exception.WebServiceException;

/**
 * {@link DashboardWebService} - Web Service for retrieving details for
 * Dashboard
 * 
 * @author Shyam Natarajan
 *
 */
@Path("exptr-web")
public class DashboardWebService {

	private static final Logger L = LoggerFactory.getLogger(DashboardWebService.class);

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
	@Path("dashboard/")
	@GET
	@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getSummary(@QueryParam("username") String username,
			@QueryParam("year") int year, @QueryParam("month") int month)
			throws ExpenseTrackerException {
		try {
			DashboardService service = ServiceFactory.getInstance()
					.getService(Services.DASHBOARD_SERVICE, DashboardService.class);
			return ResponseGenerator.getSuccessResponse(
					service.getDashboardData(username, year, month), Operation.SELECT);
		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
			throw new WebServiceException(e, ResponseStatus.SERVER_ERROR);
		}
	}

}
