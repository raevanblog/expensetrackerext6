package com.slabs.expense.tracker.core.web.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.slabs.expense.tracker.core.ContentType;
import com.slabs.expense.tracker.core.ResponseStream;
import com.slabs.expense.tracker.core.ResponseGenerator;
import com.slabs.expense.tracker.core.ResponseStatus;
import com.slabs.expense.tracker.core.ServiceFactory;
import com.slabs.expense.tracker.core.exception.ExpenseTrackerException;
import com.slabs.expense.tracker.core.services.ReportingService;
import com.slabs.expense.tracker.core.services.Services;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;

/**
 * {@link ReportingWebService} - Web Service for generating reports
 * 
 * @author Shyam Natarajan
 *
 */
@Path("exptr-web")
public class ReportingWebService {

	private static final Logger L = LoggerFactory.getLogger(ReportingWebService.class);

	/**
	 * 
	 * @param username
	 *            {@link String} - Username of the user
	 * @param year
	 *            {@link Integer} - Year for which report need to be generated
	 * @param month
	 *            {@link Integer} - Month for which report need to be generated
	 * @return {@link javax.ws.rs.core.Response}
	 * @throws ExpenseTrackerException
	 *             throws {@link ExpenseTrackerException}
	 */
	@Path("report/monthly")
	@GET
	@Consumes(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response monthlyReport(@QueryParam("username") String username,
			@QueryParam("year") Integer year, @QueryParam("month") Integer month)
			throws ExpenseTrackerException {
		try {
			ReportingService service = ServiceFactory.getInstance()
					.getService(Services.REPORTING_SERVICE, ReportingService.class);
			JasperReportBuilder report = service.monthlyReport(username, year, month);
			String fileName = username + "_" + month + "_" + year + ".pdf";
			return ResponseGenerator.getSuccessResponse(new ResponseStream(report), fileName,
					ContentType.APPLICATION_PDF_TYPE);
		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
			throw new ExpenseTrackerException(e, ResponseStatus.SERVER_ERROR);
		}
	}

}
