package com.slabs.expense.tracker.core.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.slabs.expense.tracker.common.db.entity.Message;
import com.slabs.expense.tracker.common.db.entity.UserInfo;
import com.slabs.expense.tracker.database.mapper.UserDAO;

@Service(value = "AdminService")
@Transactional(isolation = Isolation.READ_COMMITTED, timeout = 2000)
public class AdminService {

	@Autowired
	private UserDAO dao;

	/**
	 * 
	 * @param username
	 *            {@link String} - Username of the user
	 * @param isActivate
	 *            {@link String} - 'Y' to activate and 'N' to de-activate
	 * @return {@link Boolean} - True if it is activated or deactivate
	 * @throws Exception
	 *             throws {@link Exception}
	 */
	public boolean activateUser(String username, String isActivate) throws Exception {
		return dao.activateUser(username, isActivate) > 0 ? Boolean.TRUE : Boolean.FALSE;
	}

	/**
	 * 
	 * @param records
	 *            {@link UserInfo} - List of user records to DELETE
	 * @return {@link Integer} - No of records deleted
	 * @throws Exception
	 *             throws {@link Exception}
	 */
	public Integer deleteUser(List<UserInfo> records) throws Exception {
		int noOfRecords = 0;
		for (UserInfo record : records) {
			noOfRecords = noOfRecords + dao.deleteUser(record);
		}
		return noOfRecords;
	}

	/**
	 * 
	 * @param messages
	 *            {@link Message} - List of Messages to create
	 * @return {@link Integer} - Number of messages created
	 * @throws Exception
	 *             throws {@link Exception}
	 */
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
	public Integer deleteMessage(List<Message> messages, boolean isDeleteOldMsg) throws Exception {
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
	public Integer deleteQuery(List<Message> queries, boolean isDeleteOldMsg) throws Exception {
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
	public List<Message> getQuery(boolean isNew) throws Exception {
		return dao.getQuery(isNew);
	}

}
