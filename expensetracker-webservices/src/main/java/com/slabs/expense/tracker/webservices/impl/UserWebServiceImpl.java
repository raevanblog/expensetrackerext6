package com.slabs.expense.tracker.webservices;

import java.util.List;

import javax.ws.rs.Consumes;
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

import com.slabs.expense.tracker.common.database.entity.UserInfo;
import com.slabs.expense.tracker.common.database.entity.UserSettings;
import com.slabs.expense.tracker.common.exception.ExpenseTrackerException;
import com.slabs.expense.tracker.common.services.AdminService;
import com.slabs.expense.tracker.common.services.EmailService;
import com.slabs.expense.tracker.common.services.Services;
import com.slabs.expense.tracker.common.services.UserService;
import com.slabs.expense.tracker.core.ServiceFactory;
import com.slabs.expense.tracker.webservice.response.Operation;
import com.slabs.expense.tracker.webservice.response.Response;
import com.slabs.expense.tracker.webservices.exception.WebServiceException;
import com.slabs.expense.tracker.webservices.response.ResponseGenerator;
import com.slabs.expense.tracker.webservices.response.ResponseStatus;

/**
 * {@link UserWebService} - Web Service for retrieving/updating User information
 * 
 * @author Shyam Natarajan
 *
 */
@Path("exptr-web")
public class UserWebService {

	private static final Logger L = LoggerFactory.getLogger(UserWebService.class);

	/**
	 * 
	 * @param record
	 *            {@link UserInfo} - User record to INSERT
	 * @return {@link com.slabs.expense.tracker.webservice.response.Response}
	 * @throws WebServiceException
	 *             throws {@link WebServiceException}
	 */
	@Path("user")
	@POST
	@Consumes(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response createUser(UserInfo record) throws ExpenseTrackerException {
		try {
			UserService service = ServiceFactory.getInstance().getService(Services.USER_SERVICE,
					UserService.class);
			EmailService emailService = ServiceFactory.getInstance()
					.getService(Services.EMAIL_SERVICE, EmailService.class);
			Response response = ResponseGenerator.getSuccessResponse(service.createUser(record),
					Operation.INSERT);
			if (response.getResult().getNoOfRecords() != 0) {
				emailService.sendActivationEmail(record);
			}
			return response;
		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
			throw new WebServiceException(e, ResponseStatus.SERVER_ERROR);
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
	@Path("user")
	@PUT
	@Consumes(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response updateUser(UserInfo record) throws ExpenseTrackerException {
		try {
			UserService service = ServiceFactory.getInstance().getService(Services.USER_SERVICE,
					UserService.class);
			return ResponseGenerator.getSuccessResponse(service.updateUser(record),
					Operation.UPDATE);
		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
			throw new WebServiceException(e, ResponseStatus.SERVER_ERROR);
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
	@Path("user/password")
	@PUT
	@Consumes(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response changePassword(UserInfo record) throws ExpenseTrackerException {
		try {
			UserService service = ServiceFactory.getInstance().getService(Services.USER_SERVICE,
					UserService.class);
			List<UserInfo> list = service.selectUser(record.getUsername(), Boolean.FALSE,
					Boolean.TRUE);
			UserInfo user = list.get(0);
			if (!user.getPassword().equals(record.getPassword())) {
				return ResponseGenerator.getExceptionResponse(ResponseStatus.BAD_REQUEST,
						"Password is wrong");
			}
			return ResponseGenerator.getSuccessResponse(service.updateUser(record),
					Operation.UPDATE);
		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
			throw new WebServiceException(e, ResponseStatus.SERVER_ERROR);
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
	@Path("user")
	@DELETE
	@Consumes(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response deleteUser(List<UserInfo> records) throws ExpenseTrackerException {
		try {
			AdminService service = ServiceFactory.getInstance().getService(Services.ADMIN_SERVICE,
					AdminService.class);
			return ResponseGenerator.getSuccessResponse(service.deleteUser(records),
					Operation.DELETE);
		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
			throw new WebServiceException(e, ResponseStatus.SERVER_ERROR);
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
	@Path("user")
	@GET
	@Consumes(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getUser(@QueryParam("username") String username)
			throws ExpenseTrackerException {
		try {
			UserService service = ServiceFactory.getInstance().getService(Services.USER_SERVICE,
					UserService.class);
			return ResponseGenerator.getSuccessResponse(
					service.selectUser(username, Boolean.TRUE, Boolean.FALSE), Operation.SELECT);
		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
			throw new WebServiceException(e, ResponseStatus.SERVER_ERROR);
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
	@Path("user")
	@GET
	@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getUsers() throws ExpenseTrackerException {
		try {
			UserService service = ServiceFactory.getInstance().getService(Services.USER_SERVICE,
					UserService.class);
			return ResponseGenerator.getSuccessResponse(service.selectAllUsers(false),
					Operation.SELECT);
		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
			throw new WebServiceException(e, ResponseStatus.SERVER_ERROR);
		}
	}
	

	/**
	 * This method will retrieve the User Settings
	 * 
	 * @return {@link com.slabs.expense.tracker.webservice.response.Response}
	 * @throws WebServiceException
	 *             throws {@link WebServiceException}
	 */
	@Path("user/settings")
	@GET
	@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getUserSettings(@QueryParam("username") String username)
			throws ExpenseTrackerException {
		try {
			UserService service = ServiceFactory.getInstance().getService(Services.USER_SERVICE,
					UserService.class);
			return ResponseGenerator.getSuccessResponse(service.getUserSettings(username),
					Operation.SELECT);
		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
			throw new WebServiceException(e, ResponseStatus.SERVER_ERROR);
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
	@Path("user/settings")
	@POST
	@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response createUserSettings(UserSettings record) throws ExpenseTrackerException {
		try {
			UserService service = ServiceFactory.getInstance().getService(Services.USER_SERVICE,
					UserService.class);		
			return ResponseGenerator.getSuccessResponse(service.createUserSettings(record),
					Operation.SELECT);
		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
			throw new WebServiceException(e, ResponseStatus.SERVER_ERROR);
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
	@Path("user/settings")
	@PUT
	@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response updateUserSettings(UserSettings record) throws ExpenseTrackerException {
		try {
			UserService service = ServiceFactory.getInstance().getService(Services.USER_SERVICE,
					UserService.class);
			return ResponseGenerator.getSuccessResponse(service.updateUserSettings(record),
					Operation.SELECT);
		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
			throw new WebServiceException(e, ResponseStatus.SERVER_ERROR);
		}
	}

}
