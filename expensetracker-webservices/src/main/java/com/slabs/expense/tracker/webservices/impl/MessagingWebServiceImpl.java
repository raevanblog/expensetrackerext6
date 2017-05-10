package com.slabs.expense.tracker.webservices.impl;

import java.util.List;

import javax.xml.ws.WebServiceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.slabs.expense.tracker.common.database.entity.Message;
import com.slabs.expense.tracker.common.exception.ExpenseTrackerException;
import com.slabs.expense.tracker.common.services.MessageService;
import com.slabs.expense.tracker.common.webservices.MessagingWebService;
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

	@Autowired
	private MessageService service;

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
			return ResponseGenerator.getSuccessResponse(service.getMessages(username, isNew), Operation.SELECT);
		} catch (Exception e) {
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
	public Response createMessage(@RequestBody List<Message> messages) throws ExpenseTrackerException {
		try {
			return ResponseGenerator.getSuccessResponse(service.createMessage(messages), Operation.INSERT);
		} catch (Exception e) {
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
	public Response updateMessage(@RequestBody List<Message> messages) throws ExpenseTrackerException {
		try {
			return ResponseGenerator.getSuccessResponse(service.updateMessage(messages), Operation.UPDATE);
		} catch (Exception e) {
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
	public Response deleteMessage(@RequestBody List<Message> messages) throws ExpenseTrackerException {
		try {
			return ResponseGenerator.getSuccessResponse(service.deleteMessage(messages), Operation.INSERT);
		} catch (Exception e) {
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
			return ResponseGenerator.getSuccessResponse(service.getQueries(isNew), Operation.SELECT);
		} catch (Exception e) {
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
	public Response createQuery(@RequestBody Message message) throws ExpenseTrackerException {
		try {
			return ResponseGenerator.getSuccessResponse(service.createQuery(message), Operation.INSERT);
		} catch (Exception e) {
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
	public Response deleteQuery(@RequestBody List<Message> messages) throws ExpenseTrackerException {
		try {
			return ResponseGenerator.getSuccessResponse(service.deleteQuery(messages), Operation.DELETE);
		} catch (Exception e) {
			throw new ExpenseTrackerException(e);
		}
	}

}
