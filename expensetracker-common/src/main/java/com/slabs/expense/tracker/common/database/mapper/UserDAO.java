package com.slabs.expense.tracker.common.database.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.slabs.expense.tracker.common.database.entity.UserInfo;
import com.slabs.expense.tracker.common.database.entity.UserSettings;

/**
 * {@link UserDAO} is an interface providing mapper methods for executing query
 * using MyBatis
 * 
 * @author Shyam Natarajan
 *
 */
@Component(value = "UserDAO")
public interface UserDAO {

	public List<UserInfo> getUser(String username, boolean includePassword);

	public List<UserInfo> getUserBy(String type, String value, boolean includePassword);

	public Integer createUser(UserInfo record);

	public Integer deleteUser(UserInfo record);

	public Integer updateUser(UserInfo record);

	public Integer activateUser(String username, String isActivate);

	public Integer setActivationKey(String username, String activationKey);

	public Integer deleteUserFeature(String username);

	public UserSettings getUserSettings(String username);

	public Integer createUserSettings(UserSettings record);

	public Integer updateUserSettings(UserSettings record);

}
