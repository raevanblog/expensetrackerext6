package com.slabs.expense.tracker.core.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.slabs.expense.tracker.common.db.entity.UserInfo;
import com.slabs.expense.tracker.common.db.mapper.UserDAO;
import com.slabs.expense.tracker.common.services.UserService;

/**
 * {@link UserServiceImpl} provides API for INSERT, DELETE, UPDATE, SELECT on
 * USERINFO table
 * 
 * @author Shyam Natarajan
 *
 */
@Service(value = "UserService")
@Transactional(isolation = Isolation.READ_COMMITTED, timeout = 2000)
public class UserServiceImpl implements UserService {

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
	@Override
	public List<UserInfo> selectUser(String username, boolean includePassword) throws Exception {
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
	@Override
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
	@Override
	public List<UserInfo> selectAllUsers(boolean includePassword) throws Exception {
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
	@Override
	public Integer updateUser(UserInfo record) throws Exception {
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
	@Override
	public Integer createUser(UserInfo record) throws Exception {
		int noOfRecords = dao.createUser(record);
		return noOfRecords;
	}

}