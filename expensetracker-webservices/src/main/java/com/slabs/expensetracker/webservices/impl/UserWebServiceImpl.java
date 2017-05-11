package com.slabs.expensetracker.webservices.impl;

import java.util.List;

import javax.xml.ws.WebServiceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.slabs.expensetracker.common.database.entity.UserInfo;
import com.slabs.expensetracker.common.database.entity.UserSettings;
import com.slabs.expensetracker.common.webservice.response.Operation;
import com.slabs.expensetracker.common.webservice.response.Response;
import com.slabs.expensetracker.common.exception.ExpenseTrackerException;
import com.slabs.expensetracker.common.services.AdminService;
import com.slabs.expensetracker.common.services.EmailService;
import com.slabs.expensetracker.common.services.UserService;
import com.slabs.expensetracker.common.webservices.UserWebService;
import com.slabs.expensetracker.webservices.response.ResponseGenerator;
import com.slabs.expensetracker.webservices.response.ResponseStatus;

/**
 * {@link UserWebServiceImpl} - Web Service for retrieving/updating User
 * information
 * 
 * @author Shyam Natarajan
 *
 */
@RestController
@RequestMapping(value = "api")
public class UserWebServiceImpl implements UserWebService {

	@Autowired
	private UserService service;

	@Autowired
	private EmailService emailService;

	@Autowired
	private AdminService adminService;

	/**
	 * 
	 * @param record
	 *            {@link UserInfo} - User record to INSERT
	 * @return {@link com.slabs.expense.tracker.webservice.response.Response}
	 * @throws WebServiceException
	 *             throws {@link WebServiceException}
	 */
	@RequestMapping(value = "user", method = { RequestMethod.POST }, produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	@Override
	public Response createUser(@RequestBody UserInfo record) throws ExpenseTrackerException {
		try {
			Response response = ResponseGenerator.getSuccessResponse(service.createUser(record), Operation.INSERT);
			if (response.getResult().getNoOfRecords() != 0) {
				emailService.sendActivationEmail(record);
			}
			return response;
		} catch (Exception e) {
			throw new ExpenseTrackerException(e);
		}
	}

	/**
	 * 
	 * @param record
	 *            {@link UserInfo} - User record to UPDATE
	 * @return {@link com.slabs.expense.tracker.webservice.response.Response}
	 * @throws WebServiceException
	 *             throws {@link WebServiceException}
	 */
	@RequestMapping(value = "user", method = { RequestMethod.PUT }, produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	@Override
	public Response updateUser(@RequestBody UserInfo record) throws ExpenseTrackerException {
		try {
			return ResponseGenerator.getSuccessResponse(service.updateUser(record), Operation.UPDATE);
		} catch (Exception e) {
			throw new ExpenseTrackerException(e);
		}
	}

	/**
	 * 
	 * @param record
	 *            {@link UserInfo} - User record to UPDATE
	 * @return {@link com.slabs.expense.tracker.webservice.response.Response}
	 * @throws WebServiceException
	 *             throws {@link WebServiceException}
	 */
	@RequestMapping(value = "user/password", method = { RequestMethod.PUT }, produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	@Override
	public Response changePassword(@RequestBody UserInfo record) throws ExpenseTrackerException {
		try {
			List<UserInfo> list = service.selectUser(record.getUsername(), Boolean.FALSE, Boolean.TRUE);
			UserInfo user = list.get(0);
			if (!user.getPassword().equals(record.getPassword())) {
				return ResponseGenerator.getExceptionResponse(ResponseStatus.BAD_REQUEST, "Password is wrong");
			}
			return ResponseGenerator.getSuccessResponse(service.updateUser(record), Operation.UPDATE);
		} catch (Exception e) {
			throw new ExpenseTrackerException(e);
		}
	}

	/**
	 * 
	 * @param records
	 *            {@link UserInfo} - List of records to DELETE
	 * @return {@link com.slabs.expense.tracker.webservice.response.Response}
	 * @throws WebServiceException
	 *             throws {@link WebServiceException}
	 */
	@RequestMapping(value = "user", method = { RequestMethod.DELETE }, produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	@Override
	public Response deleteUser(@RequestBody List<UserInfo> records) throws ExpenseTrackerException {
		try {
			return ResponseGenerator.getSuccessResponse(adminService.deleteUser(records), Operation.DELETE);
		} catch (Exception e) {
			throw new ExpenseTrackerException(e);
		}
	}

	/**
	 * 
	 * @param username
	 *            {@link String} - Username of the user
	 * @return {@link com.slabs.expense.tracker.webservice.response.Response}
	 * @throws WebServiceException
	 *             throws {@link WebServiceException}
	 */
	@RequestMapping(value = "user", method = { RequestMethod.GET }, produces = { "application/json", "application/xml" })
	@Override
	public Response getUser(@RequestParam(name = "username") String username) throws ExpenseTrackerException {
		try {
			return ResponseGenerator.getSuccessResponse(service.selectUser(username, Boolean.TRUE, Boolean.FALSE), Operation.SELECT);
		} catch (Exception e) {
			throw new ExpenseTrackerException(e);
		}
	}

	/**
	 * This method will retrieve the list of all users in the
	 * {@link com.slabs.expense.tracker.webservice.response.Response}
	 * 
	 * @return {@link com.slabs.expense.tracker.webservice.response.Response}
	 * @throws WebServiceException
	 *             throws {@link WebServiceException}
	 */
	@RequestMapping(value = "users", method = { RequestMethod.GET }, produces = { "application/json", "application/xml" })
	@Override
	public Response getUsers() throws ExpenseTrackerException {
		try {
			return ResponseGenerator.getSuccessResponse(service.selectAllUsers(false), Operation.SELECT);
		} catch (Exception e) {
			throw new ExpenseTrackerException(e);
		}
	}

	/**
	 * This method will retrieve the User Settings
	 * 
	 * @return {@link com.slabs.expense.tracker.webservice.response.Response}
	 * @throws WebServiceException
	 *             throws {@link WebServiceException}
	 */
	@RequestMapping(value = "user/settings", method = { RequestMethod.GET }, produces = { "application/json", "application/xml" })
	@Override
	public Response getUserSettings(@RequestParam(name = "username") String username) throws ExpenseTrackerException {
		try {
			return ResponseGenerator.getSuccessResponse(service.getUserSettings(username), Operation.SELECT);
		} catch (Exception e) {
			throw new ExpenseTrackerException(e);
		}
	}

	/**
	 * This method will create user settings
	 * 
	 * 
	 * @return {@link com.slabs.expense.tracker.webservice.response.Response}
	 * @throws WebServiceException
	 *             throws {@link WebServiceException}
	 */
	@RequestMapping(value = "user/settings", method = { RequestMethod.POST }, produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	@Override
	public Response createUserSettings(@RequestBody UserSettings record) throws ExpenseTrackerException {
		try {
			return ResponseGenerator.getSuccessResponse(service.createUserSettings(record), Operation.SELECT);
		} catch (Exception e) {
			throw new ExpenseTrackerException(e);
		}
	}

	/**
	 * This method will create user settings
	 * 
	 * 
	 * @return {@link com.slabs.expense.tracker.webservice.response.Response}
	 * @throws WebServiceException
	 *             throws {@link WebServiceException}
	 */
	@RequestMapping(value = "user/settings", method = { RequestMethod.PUT }, produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	@Override
	public Response updateUserSettings(@RequestBody UserSettings record) throws ExpenseTrackerException {
		try {
			return ResponseGenerator.getSuccessResponse(service.updateUserSettings(record), Operation.SELECT);
		} catch (Exception e) {
			throw new ExpenseTrackerException(e);
		}
	}

}
