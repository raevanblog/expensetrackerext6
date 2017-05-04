package com.slabs.expense.tracker.webservices;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.slabs.expense.tracker.common.database.entity.Inventory;
import com.slabs.expense.tracker.common.exception.ExpenseTrackerException;
import com.slabs.expense.tracker.common.services.InventoryService;
import com.slabs.expense.tracker.common.services.Services;
import com.slabs.expense.tracker.core.ServiceFactory;
import com.slabs.expense.tracker.webservice.response.Operation;
import com.slabs.expense.tracker.webservice.response.Response;
import com.slabs.expense.tracker.webservices.exception.WebServiceException;
import com.slabs.expense.tracker.webservices.response.ResponseGenerator;
import com.slabs.expense.tracker.webservices.response.ResponseStatus;

/**
 * {@link InventoryWebService} - Web Service for retrieving/updating Inventory
 * 
 * @author Shyam Natarajan
 *
 */
@Path("exptr-web")
public class InventoryWebService {

	@Path("inventory/")
	@POST
	@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response addInventory(List<Inventory> records) throws ExpenseTrackerException {
		try {
			InventoryService service = ServiceFactory.getInstance().getService(Services.INVENTORY_SERVICE, InventoryService.class);

			return ResponseGenerator.getSuccessResponse(service.createInventory(records), Operation.INSERT);

		} catch (Exception e) {
			throw new WebServiceException(e, ResponseStatus.SERVER_ERROR);
		}
	}

	@Path("inventory/")
	@DELETE
	@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response deleteInventory(List<Inventory> records) throws ExpenseTrackerException {
		try {
			InventoryService service = ServiceFactory.getInstance().getService(Services.INVENTORY_SERVICE, InventoryService.class);

			return ResponseGenerator.getSuccessResponse(service.deleteInventory(records), Operation.DELETE);
		} catch (Exception e) {
			throw new WebServiceException(e, ResponseStatus.SERVER_ERROR);
		}
	}

	@Path("inventory/")
	@PUT
	@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response updateInventory(List<Inventory> records) throws ExpenseTrackerException {
		try {
			InventoryService service = ServiceFactory.getInstance().getService(Services.INVENTORY_SERVICE, InventoryService.class);

			return ResponseGenerator.getSuccessResponse(service.updateInventory(records), Operation.UPDATE);
		} catch (Exception e) {
			throw new WebServiceException(e, ResponseStatus.SERVER_ERROR);
		}
	}

	@Path("inventory/")
	@GET
	@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getInventory(@QueryParam("username") String username) throws ExpenseTrackerException {
		try {
			InventoryService service = ServiceFactory.getInstance().getService(Services.INVENTORY_SERVICE, InventoryService.class);

			return ResponseGenerator.getSuccessResponse(service.getInventory(username), Operation.SELECT);
		} catch (Exception e) {
			throw new WebServiceException(e, ResponseStatus.SERVER_ERROR);
		}
	}

}
