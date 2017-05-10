package com.slabs.expense.tracker.webservices.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.slabs.expense.tracker.common.database.entity.UserInfo;
import com.slabs.expense.tracker.common.exception.ExpenseTrackerException;
import com.slabs.expense.tracker.common.services.AdminService;
import com.slabs.expense.tracker.common.services.Services;
import com.slabs.expense.tracker.common.webservices.AdminWebService;
import com.slabs.expense.tracker.core.ServiceFactory;
import com.slabs.expense.tracker.webservice.response.Operation;
import com.slabs.expense.tracker.webservice.response.Response;
import com.slabs.expense.tracker.webservices.response.ResponseGenerator;

/**
 * {@link AdminWebServiceImpl} - Webservice for Administrator
 * 
 * @author Shyam Natarajan
 *
 */
@RestController
@RequestMapping(value = "api")
public class AdminWebServiceImpl implements AdminWebService {

	private static final Logger L = LoggerFactory.getLogger(AdminWebServiceImpl.class);

	@RequestMapping(value = "admin/user", method = { RequestMethod.DELETE }, produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	@Override
	public Response deleteUser(List<UserInfo> records) throws ExpenseTrackerException {
		try {
			AdminService service = ServiceFactory.getInstance().getService(Services.ADMIN_SERVICE, AdminService.class);
			return ResponseGenerator.getSuccessResponse(service.deleteUser(records), Operation.DELETE);

		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
			throw new ExpenseTrackerException(e);
		}
	}
}
