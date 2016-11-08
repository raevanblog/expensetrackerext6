package com.slabs.expense.tracker.core.web.services;

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
import com.slabs.expense.tracker.core.ResponseGenerator;
import com.slabs.expense.tracker.core.ResponseStatus;
import com.slabs.expense.tracker.core.ServiceFactory;
import com.slabs.expense.tracker.core.exception.ExpenseTrackerException;
import com.slabs.expense.tracker.core.services.Services;
import com.slabs.expense.tracker.core.services.UserService;
import com.slabs.expense.tracker.webservice.response.Operation;
import com.slabs.expense.tracker.webservice.response.Response;

@Path("exptr-web")
public class UserWebService {

	private static final Logger L = LoggerFactory.getLogger(UserWebService.class);

	@Path("user")
	@POST
	@Consumes(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response createUser(UserInfo record) throws ExpenseTrackerException {
		try {
			UserService service = ServiceFactory.getInstance().getService(Services.USER_SERVICE, UserService.class);
			return ResponseGenerator.getSuccessResponse(service.create(record), Operation.INSERT);
		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
			throw new ExpenseTrackerException(e, ResponseStatus.SERVER_ERROR);
		}
	}

	@Path("user")
	@PUT
	@Consumes(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response updateUser(UserInfo record) throws ExpenseTrackerException {
		try {
			UserService service = ServiceFactory.getInstance().getService(Services.USER_SERVICE, UserService.class);
			return ResponseGenerator.getSuccessResponse(service.update(record), Operation.UPDATE);
		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
			throw new ExpenseTrackerException(e, ResponseStatus.SERVER_ERROR);
		}
	}

	@Path("user")
	@DELETE
	@Consumes(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response deleteUser(List<UserInfo> records) throws ExpenseTrackerException {
		try {
			UserService service = ServiceFactory.getInstance().getService(Services.USER_SERVICE, UserService.class);
			return ResponseGenerator.getSuccessResponse(service.delete(records), Operation.DELETE);
		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
			throw new ExpenseTrackerException(e, ResponseStatus.SERVER_ERROR);
		}
	}

	@Path("user")
	@GET
	@Consumes(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getUser(@QueryParam("username") String username) throws ExpenseTrackerException {
		try {
			UserService service = ServiceFactory.getInstance().getService(Services.USER_SERVICE, UserService.class);
			return ResponseGenerator.getSuccessResponse(service.select(username, false), Operation.SELECT);
		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
			throw new ExpenseTrackerException(e, ResponseStatus.SERVER_ERROR);
		}
	}

	@Path("user")
	@GET
	@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getUsers() throws ExpenseTrackerException {
		try {
			UserService service = ServiceFactory.getInstance().getService(Services.USER_SERVICE, UserService.class);
			return ResponseGenerator.getSuccessResponse(service.selectAll(false), Operation.SELECT);
		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
			throw new ExpenseTrackerException(e, ResponseStatus.SERVER_ERROR);
		}
	}

}
