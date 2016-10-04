package com.slabs.expense.tracker.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.slabs.expense.tracker.common.db.entity.UserInfo;

public interface UserMapper {

	public UserInfo getUser(@Param("username") String username) throws Exception;

	public List<UserInfo> getAllUsers() throws Exception;

	public Integer createUser(@Param("record") UserInfo record) throws Exception;

	public Integer deleteUser(@Param("record") UserInfo record) throws Exception;

	public Integer updateUser(@Param("record") UserInfo record) throws Exception;
}
