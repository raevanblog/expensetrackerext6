package com.slabs.expense.tracker.core.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.slabs.expense.tracker.common.constants.Constants;
import com.slabs.expense.tracker.common.db.entity.Message;
import com.slabs.expense.tracker.common.db.entity.UserInfo;
import com.slabs.expense.tracker.common.db.mapper.MessageDAO;
import com.slabs.expense.tracker.common.services.MessageService;
import com.slabs.expense.tracker.util.MarkerEngine;

/**
 * {@link MessageServiceImpl} provides API for INSERT, DELETE, UPDATE, SELECT on
 * MESSAGE & QUERY table
 * 
 * @author Shyam Natarajan
 *
 */
@Service(value = "MessageService")
@Transactional(isolation = Isolation.READ_COMMITTED, timeout = 2000)
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageDAO dao;

	/**
	 * 
	 * @param user
	 *            {@link UserInfo} User record
	 * @throws Exception
	 *             throws {@link Exception}
	 */
	@Override
	public void sendWelcomeMessage(UserInfo user) throws Exception {
		Message message = new Message();
		Map<String, String> model = new HashMap<String, String>();
		model.put("fname", user.getFirstName());
		model.put("lname", user.getLastName());

		List<Message> messages = new ArrayList<Message>();
		String content = MarkerEngine.process(Constants.WELCOME_TEMPLATE, model);

		message.setMsgdate(new Date());
		message.setSubject("Welcome");
		message.setMsgto(user.getUsername());
		message.setMsgfrom("admin");
		message.setMessage(content);
		message.setIsNew("Y");

		messages.add(message);

		createMessage(messages);

	}

	/**
	 * 
	 * @param username
	 *            {@link String} - Username of the user
	 * @return {@link Message} - List of Message records
	 * @throws Exception
	 *             throws {@link Exception}
	 */
	@Override
	public List<Message> getMessages(String username, boolean isNew) throws Exception {
		return dao.getMessage(username, isNew);
	}

	/**
	 * 
	 * @param messages
	 *            {@link Message} - List of Messages to create
	 * @return {@link Integer} - Number of messages created
	 * @throws Exception
	 *             throws {@link Exception}
	 */
	@Override
	public Integer createMessage(List<Message> messages) throws Exception {
		return dao.insertMessage(messages);
	}

	/**
	 * 
	 * @param messages
	 *            {@link Message} - List of Messages to update
	 * @return {@link Integer} - Number of messages updated
	 * @throws Exception
	 *             throws {@link Exception}
	 */
	@Override
	public Integer updateMessage(List<Message> messages) throws Exception {
		int noOfRecords = 0;
		for (Message message : messages) {
			noOfRecords = noOfRecords + dao.updateMessage(message);
		}
		return noOfRecords;
	}

	/**
	 * 
	 * @param messages
	 *            {@link Message} - List of Message records to delete
	 * @param isDeleteOldMsg
	 *            {@link Boolean} - Boolean flag to delete all messages or only
	 *            old messages
	 * @return {@link Integer} - Number of records deleted
	 * @throws Exception
	 *             throws {@link Exception}
	 */
	@Override
	public Integer deleteMessage(List<Message> messages) throws Exception {
		int noOfRecords = 0;
		for (Message message : messages) {
			noOfRecords = noOfRecords + dao.deleteMessage(message);
		}
		return noOfRecords;
	}

	/**
	 * 
	 * @param queries
	 *            {@link Message} - List of Query to create
	 * @return {@link Integer} - Number of queries created
	 * @throws Exception
	 *             throws {@link Exception}
	 */
	@Override
	public Integer createQuery(Message query) throws Exception {
		return dao.insertQuery(query);
	}

	/**
	 * 
	 * @param queries
	 *            {@link Message} - List of Messages to update
	 * @return {@link Integer} - Number of messages updated
	 * @throws Exception
	 *             throws {@link Exception}
	 */
	@Override
	public Integer updateQuery(List<Message> queries) throws Exception {
		int noOfRecords = 0;
		for (Message message : queries) {
			noOfRecords = noOfRecords + dao.updateQuery(message);
		}
		return noOfRecords;
	}

	/**
	 * 
	 * @param queries
	 *            {@link Message} - List of Message records to delete
	 * @param isDeleteOldMsg
	 *            {@link Boolean} - Boolean flag to delete all messages or only
	 *            old messages
	 * @return {@link Integer} - Number of records deleted
	 * @throws Exception
	 *             throws {@link Exception}
	 */
	@Override
	public Integer deleteQuery(List<Message> queries) throws Exception {
		int noOfRecords = 0;
		for (Message message : queries) {
			noOfRecords = noOfRecords + dao.deleteQuery(message);
		}
		return noOfRecords;
	}

	/**
	 * 
	 * @param isNew
	 *            {@link Boolean} - true to retrieve only new queries
	 * @return {@link Message} - List of Message records
	 * @throws Exception
	 *             throws {@link Exception}
	 */
	@Override
	public List<Message> getQueries(boolean isNew) throws Exception {
		return dao.getQuery(isNew);
	}

}
