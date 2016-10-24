package com.slabs.expense.tracker.core.web.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.slabs.expense.tracker.core.ResponseGenerator;
import com.slabs.expense.tracker.core.ResponseStatus;
import com.slabs.expense.tracker.core.ServiceFactory;
import com.slabs.expense.tracker.core.exception.ExpenseTrackerException;
import com.slabs.expense.tracker.core.services.DashboardService;
import com.slabs.expense.tracker.core.services.Services;
import com.slabs.expense.tracker.webservice.response.Operation;
import com.slabs.expense.tracker.webservice.response.Response;

@Path("exptr-web")
public class DashboardWebService {

	private static final Logger L = LoggerFactory.getLogger(DashboardWebService.class);

	@Path("dashboard/")
	@GET
	@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getSummary(@QueryParam("username") String username, @QueryParam("year") int year, @QueryParam("month") int month) throws ExpenseTrackerException {
		try {
			DashboardService service = ServiceFactory.getInstance().getService(Services.DASHBOARD_SERVICE, DashboardService.class);
			return ResponseGenerator.getSucessResponse(service.getDashboardData(username, year, month), Operation.SELECT);
		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
			throw new ExpenseTrackerException(e, ResponseStatus.SERVER_ERROR);
		}
	}

}
