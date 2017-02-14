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

import com.slabs.expense.tracker.common.db.entity.Message;
import com.slabs.expense.tracker.common.exception.ExpenseTrackerException;
import com.slabs.expense.tracker.common.services.MessageService;
import com.slabs.expense.tracker.common.services.Services;
import com.slabs.expense.tracker.core.ServiceFactory;
import com.slabs.expense.tracker.webservice.response.Operation;
import com.slabs.expense.tracker.webservice.response.Response;
import com.slabs.expense.tracker.webservices.exception.WebServiceException;
import com.slabs.expense.tracker.webservices.response.ResponseGenerator;
import com.slabs.expense.tracker.webservices.response.ResponseStatus;

/**
 * {@link MessagingWebService} - Web Service for sending and recieving
 * Message/Queries
 * 
 * @author Shyam Natarajan
 *
 */
@Path("exptr-web")
public class MessagingWebService {

	private static final Logger L = LoggerFactory.getLogger(MessagingWebService.class);

	/**
	 * 
	 * @param username
	 *            {@link String} Username of the user
	 * @param isNew
	 *            {@link Boolean} True to retrieve only new messages
	 * @return {@link Response}
	 * @throws WebServiceException
	 *             throws {@link WebServiceException}
	 */
	@Path("/message")
	@GET
	@Consumes(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getMessages(@QueryParam("username") String username,
			@QueryParam("isNew") Boolean isNew) throws ExpenseTrackerException {
		try {
			MessageService service = ServiceFactory.getInstance()
					.getService(Services.MESSAGING_SERVICE, MessageService.class);
			return ResponseGenerator.getSuccessResponse(service.getMessages(username, isNew),
					Operation.SELECT);
		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
			throw new WebServiceException(e, ResponseStatus.SERVER_ERROR);
		}
	}

	/**
	 * 
	 * @param messages
	 *            {@link List} - List of Message records
	 * @return {@link Response}
	 * @throws WebServiceException
	 *             throws {@link WebServiceException}
	 */
	@Path("/message")
	@POST
	@Consumes(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response createMessage(List<Message> messages) throws ExpenseTrackerException {
		try {
			MessageService service = ServiceFactory.getInstance()
					.getService(Services.MESSAGING_SERVICE, MessageService.class);
			return ResponseGenerator.getSuccessResponse(service.createMessage(messages),
					Operation.INSERT);
		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
			throw new WebServiceException(e, ResponseStatus.SERVER_ERROR);
		}
	}
	
	/**
	 * 
	 * @param messages
	 *            {@link List} - List of Message records
	 * @return {@link Response}
	 * @throws WebServiceException
	 *             throws {@link WebServiceException}
	 */
	@Path("/message")
	@PUT
	@Consumes(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response updateMessage(List<Message> messages) throws ExpenseTrackerException {
		try {
			MessageService service = ServiceFactory.getInstance()
					.getService(Services.MESSAGING_SERVICE, MessageService.class);
			return ResponseGenerator.getSuccessResponse(service.updateMessage(messages),
					Operation.UPDATE);
		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
			throw new WebServiceException(e, ResponseStatus.SERVER_ERROR);
		}
	}

	/**
	 * 
	 * @param messages
	 *            {@link List} - List of Message records
	 * @return {@link Response}
	 * @throws WebServiceException
	 *             throws {@link WebServiceException}
	 */
	@Path("/message")
	@DELETE
	@Consumes(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response deleteMessage(List<Message> messages) throws ExpenseTrackerException {
		try {
			MessageService service = ServiceFactory.getInstance()
					.getService(Services.MESSAGING_SERVICE, MessageService.class);
			return ResponseGenerator.getSuccessResponse(service.deleteMessage(messages),
					Operation.INSERT);
		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
			throw new WebServiceException(e, ResponseStatus.SERVER_ERROR);
		}
	}

	/**
	 * 
	 * 
	 * @param isNew
	 *            {@link Boolean} True to retrieve only new queries
	 * @return {@link Response}
	 * @throws WebServiceException
	 *             throws {@link WebServiceException}
	 */
	@Path("/query")
	@GET
	@Consumes(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getQueries(@QueryParam("isNew") Boolean isNew) throws ExpenseTrackerException {
		try {
			MessageService service = ServiceFactory.getInstance()
					.getService(Services.MESSAGING_SERVICE, MessageService.class);
			return ResponseGenerator.getSuccessResponse(service.getQueries(isNew),
					Operation.SELECT);
		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
			throw new WebServiceException(e, ResponseStatus.SERVER_ERROR);
		}
	}

	/**
	 * 
	 * @param messages
	 *            {@link Message} Message record
	 * @return {@link Response}
	 * @throws WebServiceException
	 *             throws {@link WebServiceException}
	 */
	@Path("/query")
	@POST
	@Consumes(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response createQuery(Message message) throws ExpenseTrackerException {
		try {
			MessageService service = ServiceFactory.getInstance()
					.getService(Services.MESSAGING_SERVICE, MessageService.class);
			return ResponseGenerator.getSuccessResponse(service.createQuery(message),
					Operation.INSERT);
		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
			throw new WebServiceException(e, ResponseStatus.SERVER_ERROR);
		}
	}

	/**
	 * 
	 * @param messages
	 *            {@link List} List of Message records
	 * @return {@link Response}
	 * @throws WebServiceException
	 *             throws {@link WebServiceException}
	 */
	@Path("/query")
	@DELETE
	@Consumes(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response deleteQuery(List<Message> messages) throws ExpenseTrackerException {
		try {
			MessageService service = ServiceFactory.getInstance()
					.getService(Services.MESSAGING_SERVICE, MessageService.class);
			return ResponseGenerator.getSuccessResponse(service.deleteQuery(messages),
					Operation.DELETE);
		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
			throw new WebServiceException(e, ResponseStatus.SERVER_ERROR);
		}
	}

}
