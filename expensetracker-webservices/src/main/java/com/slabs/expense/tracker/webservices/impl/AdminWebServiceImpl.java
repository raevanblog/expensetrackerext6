package com.slabs.expense.tracker.webservices.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.slabs.expense.tracker.common.database.entity.UserInfo;
import com.slabs.expense.tracker.common.webservice.response.Operation;
import com.slabs.expense.tracker.common.webservice.response.Response;
import com.slabs.expense.tracker.webservices.response.ResponseGenerator;
import com.slabs.expensetracker.common.exception.ExpenseTrackerException;
import com.slabs.expensetracker.common.services.AdminService;
import com.slabs.expensetracker.common.webservices.AdminWebService;

/**
 * {@link AdminWebServiceImpl} - Webservice for Administrator
 * 
 * @author Shyam Natarajan
 *
 */
@RestController
@RequestMapping(value = "api")
public class AdminWebServiceImpl implements AdminWebService {

	@Autowired
	private AdminService service;

	@RequestMapping(value = "admin/user", method = { RequestMethod.DELETE }, produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	@Override
	public Response deleteUser(@RequestBody List<UserInfo> records) throws ExpenseTrackerException {
		try {
			return ResponseGenerator.getSuccessResponse(service.deleteUser(records), Operation.DELETE);
		} catch (Exception e) {
			throw new ExpenseTrackerException(e);
		}
	}
}
