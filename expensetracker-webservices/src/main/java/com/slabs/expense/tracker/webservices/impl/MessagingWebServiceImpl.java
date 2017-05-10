package com.slabs.expense.tracker.webservices.impl;

import java.util.List;

import javax.xml.ws.WebServiceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.slabs.expense.tracker.common.database.entity.Message;
import com.slabs.expense.tracker.common.exception.ExpenseTrackerException;
import com.slabs.expense.tracker.common.services.MessageService;
import com.slabs.expense.tracker.common.services.Services;
import com.slabs.expense.tracker.common.webservices.MessagingWebService;
import com.slabs.expense.tracker.core.ServiceFactory;
import com.slabs.expense.tracker.webservice.response.Operation;
import com.slabs.expense.tracker.webservice.response.Response;
import com.slabs.expense.tracker.webservices.response.ResponseGenerator;

/**
 * {@link MessagingWebServiceImpl} - Web Service for sending and recieving
 * Message/Queries
 * 
 * @author Shyam Natarajan
 *
 */
@RestController
@RequestMapping(value = "api")
public class MessagingWebServiceImpl implements MessagingWebService {

	private static final Logger L = LoggerFactory.getLogger(MessagingWebServiceImpl.class);

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
	@RequestMapping(value = "message", method = { RequestMethod.GET }, produces = { "application/json", "application/xml" })
	@Override
	public Response getMessages(@RequestParam(name = "username") String username, @RequestParam(name = "isNew", required = false) Boolean isNew)
			throws ExpenseTrackerException {
		try {
			MessageService service = ServiceFactory.getInstance().getService(Services.MESSAGING_SERVICE, MessageService.class);
			return ResponseGenerator.getSuccessResponse(service.getMessages(username, isNew), Operation.SELECT);
		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
			throw new ExpenseTrackerException(e);
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
	@RequestMapping(value = "message", method = { RequestMethod.POST }, produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	@Override
	public Response createMessage(List<Message> messages) throws ExpenseTrackerException {
		try {
			MessageService service = ServiceFactory.getInstance().getService(Services.MESSAGING_SERVICE, MessageService.class);
			return ResponseGenerator.getSuccessResponse(service.createMessage(messages), Operation.INSERT);
		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
			throw new ExpenseTrackerException(e);
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
	@RequestMapping(value = "message", method = { RequestMethod.PUT }, produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	@Override
	public Response updateMessage(List<Message> messages) throws ExpenseTrackerException {
		try {
			MessageService service = ServiceFactory.getInstance().getService(Services.MESSAGING_SERVICE, MessageService.class);
			return ResponseGenerator.getSuccessResponse(service.updateMessage(messages), Operation.UPDATE);
		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
			throw new ExpenseTrackerException(e);
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
	@RequestMapping(value = "message", method = { RequestMethod.DELETE }, produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	@Override
	public Response deleteMessage(List<Message> messages) throws ExpenseTrackerException {
		try {
			MessageService service = ServiceFactory.getInstance().getService(Services.MESSAGING_SERVICE, MessageService.class);
			return ResponseGenerator.getSuccessResponse(service.deleteMessage(messages), Operation.INSERT);
		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
			throw new ExpenseTrackerException(e);
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
	@RequestMapping(value = "query", method = { RequestMethod.GET }, produces = { "application/json", "application/xml" })
	@Override
	public Response getQueries(@RequestParam(name = "isNew", required = false) Boolean isNew) throws ExpenseTrackerException {
		try {
			MessageService service = ServiceFactory.getInstance().getService(Services.MESSAGING_SERVICE, MessageService.class);
			return ResponseGenerator.getSuccessResponse(service.getQueries(isNew), Operation.SELECT);
		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
			throw new ExpenseTrackerException(e);
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
	@RequestMapping(value = "query", method = { RequestMethod.POST }, produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	@Override
	public Response createQuery(Message message) throws ExpenseTrackerException {
		try {
			MessageService service = ServiceFactory.getInstance().getService(Services.MESSAGING_SERVICE, MessageService.class);
			return ResponseGenerator.getSuccessResponse(service.createQuery(message), Operation.INSERT);
		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
			throw new ExpenseTrackerException(e);
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
	@RequestMapping(value = "query", method = { RequestMethod.DELETE }, produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	@Override
	public Response deleteQuery(List<Message> messages) throws ExpenseTrackerException {
		try {
			MessageService service = ServiceFactory.getInstance().getService(Services.MESSAGING_SERVICE, MessageService.class);
			return ResponseGenerator.getSuccessResponse(service.deleteQuery(messages), Operation.DELETE);
		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
			throw new ExpenseTrackerException(e);
		}
	}

}
