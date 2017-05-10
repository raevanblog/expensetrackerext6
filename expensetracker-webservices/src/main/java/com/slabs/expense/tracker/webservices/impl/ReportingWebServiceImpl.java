package com.slabs.expense.tracker.webservices.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.slabs.expense.tracker.common.constants.Constants;
import com.slabs.expense.tracker.common.database.entity.UserSettings;
import com.slabs.expense.tracker.common.exception.ExpenseTrackerException;
import com.slabs.expense.tracker.common.services.Services;
import com.slabs.expense.tracker.common.services.UserService;
import com.slabs.expense.tracker.core.ServiceFactory;
import com.slabs.expense.tracker.reports.Month;
import com.slabs.expense.tracker.reports.service.ReportingService;
import com.slabs.expense.tracker.util.MarkerEngine;
import com.slabs.expense.tracker.webservices.response.ResponseGenerator;

import net.sf.dynamicreports.jasper.builder.JasperConcatenatedReportBuilder;

/**
 * {@link ReportingWebServiceImpl} - Web Service for generating reports
 * 
 * @author Shyam Natarajan
 *
 */
@RestController
@RequestMapping(value = "api")
public class ReportingWebServiceImpl {

	private static final Logger L = LoggerFactory.getLogger(ReportingWebServiceImpl.class);

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
	@RequestMapping(value = "reports", method = { RequestMethod.GET })
	public void monthlyReport(@RequestParam(name = "username") String username, @RequestParam(name = "year") Integer year,
			@RequestParam(name = "month") Integer month, HttpServletRequest request, HttpServletResponse response) throws ExpenseTrackerException {
		try {

			String monthName = null;

			if (year == null) {
				throw new ExpenseTrackerException("Year is a required parameter");
			}

			if (month != null) {
				monthName = Month.getMonth(month).getName();
			}

			ReportingService service = ServiceFactory.getInstance().getService(Services.REPORTING_SERVICE, ReportingService.class);

			UserService userService = ServiceFactory.getInstance().getService(Services.USER_SERVICE, UserService.class);

			UserSettings settings = userService.getUserSettings(username);

			JasperConcatenatedReportBuilder report = service.generateMonthlyExpenseReport(username, year, month, settings.getCurrency().getCurrtxt());

			if (report != null) {
				ResponseGenerator.writeResponse(report, response.getOutputStream(), MediaType.APPLICATION_OCTET_STREAM,
						getFileName(username, monthName, String.valueOf(year), "pdf"));
			} else {
				Map<String, String> model = new HashMap<String, String>();
				model.put("response", "Report not available.");
				ResponseGenerator.writeResponse(MarkerEngine.process(Constants.RESPONSE_TEMPLATE, model), response.getOutputStream(),
						"Response.html");
			}
		} catch (ExpenseTrackerException e) {
			throw e;
		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
			throw new ExpenseTrackerException(e);

		}
	}

	private String getFileName(String username, String month, String year, String type) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(username);
		if (month != null) {
			buffer.append(Constants.UNDERSCORE).append(month);
		}

		if (year != null) {
			buffer.append(Constants.UNDERSCORE).append(year);
		}
		return buffer.append(".").append(type).toString();
	}

}
