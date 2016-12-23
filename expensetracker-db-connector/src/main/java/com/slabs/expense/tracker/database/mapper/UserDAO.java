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

	public Integer insertMessage(@Param("records") List<Message> records) throws Exception;

	public Integer updateMessage(@Param("record") Message record) throws Exception;

	public Integer deleteMessage(@Param("record") Message record) throws Exception;

	public List<Message> getMessage(@Param("username") String username, boolean isNew)
			throws Exception;

	public Integer insertQuery(@Param("record") Message record) throws Exception;

	public Integer updateQuery(@Param("record") Message record) throws Exception;

	public Integer deleteQuery(@Param("record") Message record) throws Exception;

	public List<Message> getQuery(boolean isNew) throws Exception;

}
