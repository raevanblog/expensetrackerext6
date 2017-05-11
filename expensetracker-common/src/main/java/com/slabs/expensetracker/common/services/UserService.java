package com.slabs.expensetracker.common.services;

import java.util.List;

import com.slabs.expensetracker.common.database.entity.UserInfo;
import com.slabs.expensetracker.common.database.entity.UserSettings;

public interface UserService {

	public List<UserInfo> selectUser(String username, boolean includeSettings,
			boolean includePassword) throws Exception;

	public Boolean checkAvailability(String type, String value, boolean includePassword) throws Exception;

	public List<UserInfo> selectAllUsers(boolean includePassword) throws Exception;

	public Integer updateUser(UserInfo record) throws Exception;

	public Integer createUser(UserInfo record) throws Exception;

	public UserSettings getUserSettings(String username) throws Exception;

	public Integer createUserSettings(UserSettings record) throws Exception;

	public Integer updateUserSettings(UserSettings record) throws Exception;

}
