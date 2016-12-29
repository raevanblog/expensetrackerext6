package com.slabs.expense.tracker.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.slabs.expense.tracker.common.db.entity.Message;
import com.slabs.expense.tracker.common.db.entity.UserInfo;

/**
 * {@link UserDAO} is an interface providing mapper methods for executing query
 * using MyBatis
 * 
 * @author Shyam Natarajan
 *
 */
public interface UserDAO {

	public List<UserInfo> getUser(@Param("username") String username,
			@Param("includePassword") boolean includePassword) throws Exception;

	public Integer createUser(@Param("record") UserInfo record) throws Exception;

	public Integer deleteUser(@Param("record") UserInfo record) throws Exception;

	public Integer updateUser(@Param("record") UserInfo record) throws Exception;

	public Integer activateUser(@Param("username") String username,
			@Param("isActivate") String isActivate) throws Exception;

	public Integer setActivationKey(@Param("username") String username,
			@Param("activationKey") String activationKey) throws Exception;

	public Integer deleteUserFeature(@Param("username") String username) throws Exception;
	
}
