package com.slabs.expense.tracker.core.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.slabs.expense.tracker.common.database.entity.UserInfo;
import com.slabs.expensetracker.common.database.mapper.UserDAO;
import com.slabs.expensetracker.common.services.AdminService;

@Service(value = "AdminService")
@Transactional(isolation = Isolation.READ_COMMITTED, timeout = 2000)
public class AdminServiceImpl implements AdminService {

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
	@Override
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
	@Override
	public Integer deleteUser(List<UserInfo> records) throws Exception {
		int noOfRecords = 0;
		for (UserInfo record : records) {
			noOfRecords = noOfRecords + dao.deleteUser(record);
		}
		return noOfRecords;
	}

}
