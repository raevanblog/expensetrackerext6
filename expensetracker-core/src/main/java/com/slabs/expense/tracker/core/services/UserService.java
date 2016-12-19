package com.slabs.expense.tracker.core.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.slabs.expense.tracker.common.db.entity.Message;
import com.slabs.expense.tracker.common.db.entity.UserInfo;
import com.slabs.expense.tracker.database.mapper.UserDAO;

/**
 * {@link UserService} provides API for INSERT, DELETE, UPDATE, SELECT on
 * USERINFO table
 * 
 * @author Shyam Natarajan
 *
 */
@Service(value = "UserService")
@Transactional(isolation = Isolation.READ_COMMITTED, timeout = 2000)
public class UserService {

	@Autowired
	private UserDAO dao;

	/**
	 * 
	 * @param username
	 *            {@link String} - Username of the user
	 * @param includePassword
	 *            {@link Boolean} - True to include password in the response
	 *            entity
	 * @return {@link UserInfo} - User record
	 * @throws Exception
	 *             throws {@link Exception}
	 */
	public List<UserInfo> select(String username, boolean includePassword) throws Exception {
		return dao.getUser(username, includePassword);
	}

	/**
	 * 
	 * @param username
	 *            {@link String} - Username of the user
	 * @return {@link Boolean} - True if username is available else false false
	 * @throws Exception
	 *             throws {@link Exception}
	 */
	public Boolean isUserNameAvailable(String username) throws Exception {
		List<UserInfo> list = dao.getUser(username, Boolean.FALSE);
		if (list != null && list.isEmpty()) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param includePassword
	 *            {@link Boolean} - True to include password in the response
	 *            entity
	 * @return {@link UserInfo} - List of user record
	 * @throws Exception
	 *             throws {@link Exception}
	 */
	public List<UserInfo> selectAll(boolean includePassword) throws Exception {
		return dao.getUser(null, includePassword);
	}

	/**
	 * 
	 * @param record
	 *            {@link UserInfo} - User record to UPDATE
	 * @return {@link Integer} - No of records updated
	 * @throws Exception
	 *             throws {@link Exception}
	 */
	public Integer update(UserInfo record) throws Exception {
		return dao.updateUser(record);
	}

	/**
	 * 
	 * @param record
	 *            {@link UserInfo} - User record to CREATE
	 * @return {@link Integer} - No of records created
	 * @throws Exception
	 *             throws {@link Exception}
	 */
	public Integer create(UserInfo record) throws Exception {
		int noOfRecords = dao.createUser(record);
		return noOfRecords;
	}

	/**
	 * 
	 * @param username
	 *            {@link String} - Username of the user
	 * @return {@link Message} - List of Message records
	 * @throws Exception
	 *             throws {@link Exception}
	 */
	public List<Message> getMessages(String username, boolean isNew) throws Exception {
		return dao.getMessage(username, isNew);
	}

}
