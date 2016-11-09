package com.slabs.expense.tracker.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.slabs.expense.tracker.common.db.entity.UserInfo;

/**
 * {@link UserMapper} is an interface providing mapper methods for executing
 * query using MyBatis
 * 
 * @author Shyam Natarajan
 *
 */
public interface UserMapper {

	public List<UserInfo> getUser(@Param("username") String username, @Param("includePassword") boolean includePassword) throws Exception;

	public Integer createUser(@Param("record") UserInfo record) throws Exception;

	public Integer deleteUser(@Param("record") UserInfo record) throws Exception;

	public Integer updateUser(@Param("record") UserInfo record) throws Exception;
}
