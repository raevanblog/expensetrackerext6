package com.slabs.expense.tracker.common.db.mapper;

import java.util.List;

import com.slabs.expense.tracker.common.db.entity.UserInfo;

/**
 * {@link UserDAO} is an interface providing mapper methods for executing query
 * using MyBatis
 * 
 * @author Shyam Natarajan
 *
 */
public interface UserDAO {

	public List<UserInfo> getUser(String username, boolean includePassword);

	public Integer createUser(UserInfo record);

	public Integer deleteUser(UserInfo record);

	public Integer updateUser(UserInfo record);

	public Integer activateUser(String username, String isActivate);

	public Integer setActivationKey(String username, String activationKey);

	public Integer deleteUserFeature(String username);

}
