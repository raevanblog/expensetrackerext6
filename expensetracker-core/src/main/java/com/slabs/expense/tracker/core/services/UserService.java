package com.slabs.expense.tracker.core.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.slabs.expense.tracker.common.db.entity.UserInfo;
import com.slabs.expense.tracker.database.mapper.UserMapper;

@Service(value = "UserService")
@Transactional(isolation = Isolation.READ_COMMITTED, timeout = 2000)
public class UserService {

	@Autowired
	private UserMapper mapper;

	public List<UserInfo> select(String username, boolean includePassword) throws Exception {
		return mapper.getUser(username, includePassword);
	}

	public List<UserInfo> selectAll(boolean includePassword) throws Exception {
		return mapper.getUser(null, includePassword);
	}

	public Integer update(UserInfo record) throws Exception {
		return mapper.updateUser(record);
	}

	public Integer create(UserInfo record) throws Exception {
		return mapper.createUser(record);
	}

	public Integer delete(List<UserInfo> records) throws Exception {
		int noOfRecords = 0;
		for (UserInfo record : records) {
			noOfRecords = noOfRecords + mapper.deleteUser(record);
		}
		return noOfRecords;
	}

}
