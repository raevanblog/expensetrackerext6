package com.slabs.expense.tracker.web.services;

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

import com.slabs.expense.tracker.common.db.entity.UserInfo;
import com.slabs.expense.tracker.core.ServiceFactory;
import com.slabs.expense.tracker.core.services.AdminService;
import com.slabs.expense.tracker.core.services.EmailService;
import com.slabs.expense.tracker.core.services.Services;
import com.slabs.expense.tracker.core.services.UserService;
import com.slabs.expense.tracker.web.services.core.ResponseGenerator;
import com.slabs.expense.tracker.web.services.core.ResponseStatus;
import com.slabs.expense.tracker.web.services.exception.WebServiceException;
import com.slabs.expense.tracker.webservice.response.Operation;
import com.slabs.expense.tracker.webservice.response.Response;

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
	public Response createUser(UserInfo record) throws WebServiceException {
		try {
			UserService service = ServiceFactory.getInstance().getService(Services.USER_SERVICE, UserService.class);
			EmailService emailService = ServiceFactory.getInstance().getService(Services.EMAIL_SERVICE, EmailService.class);
			Response response = ResponseGenerator.getSuccessResponse(service.create(record), Operation.INSERT);
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
	public Response updateUser(UserInfo record) throws WebServiceException {
		try {
			UserService service = ServiceFactory.getInstance().getService(Services.USER_SERVICE, UserService.class);
			return ResponseGenerator.getSuccessResponse(service.update(record), Operation.UPDATE);
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
	public Response changePassword(UserInfo record) throws WebServiceException {
		try {
			UserService service = ServiceFactory.getInstance().getService(Services.USER_SERVICE, UserService.class);
			List<UserInfo> list = service.select(record.getUsername(), true);
			UserInfo user = list.get(0);
			if (!user.getPassword().equals(record.getPassword())) {
				return ResponseGenerator.getExceptionResponse(ResponseStatus.BAD_REQUEST, "Password is wrong");
			}
			return ResponseGenerator.getSuccessResponse(service.update(record), Operation.UPDATE);
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
	public Response deleteUser(List<UserInfo> records) throws WebServiceException {
		try {
			AdminService service = ServiceFactory.getInstance().getService(Services.ADMIN_SERVICE, AdminService.class);
			return ResponseGenerator.getSuccessResponse(service.deleteUser(records), Operation.DELETE);
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
	public Response getUser(@QueryParam("username") String username) throws WebServiceException {
		try {
			UserService service = ServiceFactory.getInstance().getService(Services.USER_SERVICE, UserService.class);
			return ResponseGenerator.getSuccessResponse(service.select(username, false), Operation.SELECT);
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
	public Response getUsers() throws WebServiceException {
		try {
			UserService service = ServiceFactory.getInstance().getService(Services.USER_SERVICE, UserService.class);
			return ResponseGenerator.getSuccessResponse(service.selectAll(false), Operation.SELECT);
		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
			throw new WebServiceException(e, ResponseStatus.SERVER_ERROR);
		}
	}

	/**
	 * This method will check for the username availability
	 * 
	 * @param username
	 *            {@link String} - Username of the user
	 * @return {@link com.slabs.expense.tracker.webservice.response.Response}
	 * @throws WebServiceException
	 */
	@Path("user/username")
	@GET
	@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response isUserNameAvailable(@QueryParam("checkAvailable") String username) throws WebServiceException {
		try {
			UserService service = ServiceFactory.getInstance().getService(Services.USER_SERVICE, UserService.class);
			return ResponseGenerator.getSuccessResponse(service.isUserNameAvailable(username), Operation.CHECK);
		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
			throw new WebServiceException(e, ResponseStatus.SERVER_ERROR);
		}
	}

}