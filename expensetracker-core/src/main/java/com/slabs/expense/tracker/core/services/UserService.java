package com.slabs.expense.tracker.core.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.slabs.expense.tracker.common.db.entity.UserInfo;
import com.slabs.expense.tracker.database.mapper.UserMapper;

@Service(value = "user")
@Transactional(isolation = Isolation.READ_COMMITTED, timeout = 2000)
public class UserService {

	@Autowired
	private UserMapper mapper;

	public UserInfo select(String username) throws Exception {
		return mapper.getUser(username);
	}

	public List<UserInfo> selectAll() throws Exception {
		return mapper.getAllUsers();
	}

	public Integer update(List<UserInfo> records) throws Exception {
		int noOfRecords = 0;
		for (UserInfo record : records) {
			noOfRecords = noOfRecords + mapper.updateUser(record);
		}
		return noOfRecords;
	}

	public Integer delete(List<UserInfo> records) throws Exception {
		int noOfRecords = 0;
		for (UserInfo record : records) {
			noOfRecords = noOfRecords + mapper.deleteUser(record);
		}
		return noOfRecords;
	}

}
