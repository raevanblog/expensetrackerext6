package com.slabs.expense.tracker.webservices.impl;

import java.util.List;

import javax.xml.ws.WebServiceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.slabs.expense.tracker.common.database.entity.UserInfo;
import com.slabs.expense.tracker.common.database.entity.UserSettings;
import com.slabs.expense.tracker.common.exception.ExpenseTrackerException;
import com.slabs.expense.tracker.common.services.AdminService;
import com.slabs.expense.tracker.common.services.EmailService;
import com.slabs.expense.tracker.common.services.Services;
import com.slabs.expense.tracker.common.services.UserService;
import com.slabs.expense.tracker.common.webservices.UserWebService;
import com.slabs.expense.tracker.core.ServiceFactory;
import com.slabs.expense.tracker.webservice.response.Operation;
import com.slabs.expense.tracker.webservice.response.Response;
import com.slabs.expense.tracker.webservices.response.ResponseGenerator;
import com.slabs.expense.tracker.webservices.response.ResponseStatus;

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

	private static final Logger L = LoggerFactory.getLogger(UserWebServiceImpl.class);

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
	public Response createUser(UserInfo record) throws ExpenseTrackerException {
		try {
			UserService service = ServiceFactory.getInstance().getService(Services.USER_SERVICE, UserService.class);
			EmailService emailService = ServiceFactory.getInstance().getService(Services.EMAIL_SERVICE, EmailService.class);
			Response response = ResponseGenerator.getSuccessResponse(service.createUser(record), Operation.INSERT);
			if (response.getResult().getNoOfRecords() != 0) {
				emailService.sendActivationEmail(record);
			}
			return response;
		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
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
	public Response updateUser(UserInfo record) throws ExpenseTrackerException {
		try {
			UserService service = ServiceFactory.getInstance().getService(Services.USER_SERVICE, UserService.class);
			return ResponseGenerator.getSuccessResponse(service.updateUser(record), Operation.UPDATE);
		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
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
	public Response changePassword(UserInfo record) throws ExpenseTrackerException {
		try {
			UserService service = ServiceFactory.getInstance().getService(Services.USER_SERVICE, UserService.class);
			List<UserInfo> list = service.selectUser(record.getUsername(), Boolean.FALSE, Boolean.TRUE);
			UserInfo user = list.get(0);
			if (!user.getPassword().equals(record.getPassword())) {
				return ResponseGenerator.getExceptionResponse(ResponseStatus.BAD_REQUEST, "Password is wrong");
			}
			return ResponseGenerator.getSuccessResponse(service.updateUser(record), Operation.UPDATE);
		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
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
	public Response deleteUser(List<UserInfo> records) throws ExpenseTrackerException {
		try {
			AdminService service = ServiceFactory.getInstance().getService(Services.ADMIN_SERVICE, AdminService.class);
			return ResponseGenerator.getSuccessResponse(service.deleteUser(records), Operation.DELETE);
		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
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
			UserService service = ServiceFactory.getInstance().getService(Services.USER_SERVICE, UserService.class);
			return ResponseGenerator.getSuccessResponse(service.selectUser(username, Boolean.TRUE, Boolean.FALSE), Operation.SELECT);
		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
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
			UserService service = ServiceFactory.getInstance().getService(Services.USER_SERVICE, UserService.class);
			return ResponseGenerator.getSuccessResponse(service.selectAllUsers(false), Operation.SELECT);
		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
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
			UserService service = ServiceFactory.getInstance().getService(Services.USER_SERVICE, UserService.class);
			return ResponseGenerator.getSuccessResponse(service.getUserSettings(username), Operation.SELECT);
		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
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
	public Response createUserSettings(UserSettings record) throws ExpenseTrackerException {
		try {
			UserService service = ServiceFactory.getInstance().getService(Services.USER_SERVICE, UserService.class);
			return ResponseGenerator.getSuccessResponse(service.createUserSettings(record), Operation.SELECT);
		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
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
	public Response updateUserSettings(UserSettings record) throws ExpenseTrackerException {
		try {
			UserService service = ServiceFactory.getInstance().getService(Services.USER_SERVICE, UserService.class);
			return ResponseGenerator.getSuccessResponse(service.updateUserSettings(record), Operation.SELECT);
		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
			throw new ExpenseTrackerException(e);
		}
	}

}
