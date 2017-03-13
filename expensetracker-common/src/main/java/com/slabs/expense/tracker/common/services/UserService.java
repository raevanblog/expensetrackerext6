package com.slabs.expense.tracker.common.services;

import java.util.List;

import com.slabs.expense.tracker.common.database.entity.UserInfo;

public interface UserService {

	public List<UserInfo> selectUser(String username, boolean includeSettings,
			boolean includePassword) throws Exception;

	public Boolean isUserNameAvailable(String username) throws Exception;

	public List<UserInfo> selectAllUsers(boolean includePassword) throws Exception;

	public Integer updateUser(UserInfo record) throws Exception;

	public Integer createUser(UserInfo record) throws Exception;

}
