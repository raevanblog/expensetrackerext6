package com.slabs.expense.tracker.core.web.services;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.slabs.expense.tracker.common.db.entity.UserInfo;
import com.slabs.expense.tracker.core.ResponseGenerator;
import com.slabs.expense.tracker.core.ResponseStatus;
import com.slabs.expense.tracker.core.ServiceFactory;
import com.slabs.expense.tracker.core.exception.ExpenseTrackerException;
import com.slabs.expense.tracker.core.services.AdminService;
import com.slabs.expense.tracker.core.services.Services;
import com.slabs.expense.tracker.webservice.response.Operation;
import com.slabs.expense.tracker.webservice.response.Response;

/**
 * {@link AdminWebService} - Webservice for Administrator
 * 
 * @author Shyam Natarajan
 *
 */
@Path("exptr-web")
public class AdminWebService {

	private static final Logger L = LoggerFactory.getLogger(AdminWebService.class);

	@Path("admin/user")
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
			throw new ExpenseTrackerException(e, ResponseStatus.SERVER_ERROR);
		}
	}

}
