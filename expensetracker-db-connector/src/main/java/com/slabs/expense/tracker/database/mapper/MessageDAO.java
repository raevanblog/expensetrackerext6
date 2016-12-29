package com.slabs.expense.tracker.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.slabs.expense.tracker.common.db.entity.Message;

public interface MessageDAO {

	public Integer insertMessage(@Param("records") List<Message> records) throws Exception;

	public Integer updateMessage(@Param("record") Message record) throws Exception;

	public Integer deleteMessage(@Param("record") Message record) throws Exception;

	public List<Message> getMessage(@Param("username") String username,
			@Param("isNew") boolean isNew) throws Exception;

	public Integer insertQuery(@Param("record") Message record) throws Exception;

	public Integer updateQuery(@Param("record") Message record) throws Exception;

	public Integer deleteQuery(@Param("record") Message record) throws Exception;

	public List<Message> getQuery(@Param("isNew") boolean isNew) throws Exception;
}
