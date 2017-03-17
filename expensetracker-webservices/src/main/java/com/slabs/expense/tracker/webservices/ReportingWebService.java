package com.slabs.expense.tracker.webservices;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.slabs.expense.tracker.common.constants.Constants;
import com.slabs.expense.tracker.common.database.entity.UserSettings;
import com.slabs.expense.tracker.common.exception.ExpenseTrackerException;
import com.slabs.expense.tracker.common.services.Services;
import com.slabs.expense.tracker.common.services.UserService;
import com.slabs.expense.tracker.core.ServiceFactory;
import com.slabs.expense.tracker.reports.Month;
import com.slabs.expense.tracker.reports.service.ReportingService;
import com.slabs.expense.tracker.util.MarkerEngine;
import com.slabs.expense.tracker.webservices.exception.WebServiceException;
import com.slabs.expense.tracker.webservices.response.ContentType;
import com.slabs.expense.tracker.webservices.response.ResponseGenerator;
import com.slabs.expense.tracker.webservices.response.ResponseStatus;
import com.slabs.expense.tracker.webservices.response.StreamingResponse;

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
	@Path("reports")
	@GET
	@Consumes(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response monthlyReport(@QueryParam("username") String username, @QueryParam("year") Integer year, @QueryParam("month") Integer month)
			throws ExpenseTrackerException {
		try {

			String monthName = null;

			if (year == null) {
				throw new WebServiceException("Year is a required parameter", ResponseStatus.BAD_REQUEST);
			}

			if (month != null) {
				monthName = Month.getMonth(month).getName();
			}

			ReportingService service = ServiceFactory.getInstance().getService(Services.REPORTING_SERVICE, ReportingService.class);

			UserService userService = ServiceFactory.getInstance().getService(Services.USER_SERVICE, UserService.class);

			UserSettings settings = userService.getUserSettings(username);

			JasperReportBuilder report = service.generateReport(username, year, month, settings.getCurrency().getCurrtxt());

			if (report != null) {
				return ResponseGenerator.getSuccessResponse(new StreamingResponse(report), getFileName(username, monthName, year),
						ContentType.APPLICATION_PDF_TYPE);
			} else {
				Map<String, String> model = new HashMap<String, String>();
				model.put("response", "Report not available.");
				return ResponseGenerator.getSuccessResponse(new StreamingResponse(MarkerEngine.process(Constants.RESPONSE_TEMPLATE, model)),
						"Response.html", ContentType.TEXT_HTML_TYPE);
			}
		} catch (WebServiceException e) {
			throw e;
		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
			throw new WebServiceException(e, ResponseStatus.SERVER_ERROR);

		}
	}

	private String getFileName(String username, String month, Integer year) {
		if (month != null) {
			return username + "_" + month + "_" + year + ".pdf";
		} else {
			return username + "_" + year + ".pdf";
		}
	}

}
