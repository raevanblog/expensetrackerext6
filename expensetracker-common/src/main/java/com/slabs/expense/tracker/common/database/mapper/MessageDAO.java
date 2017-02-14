package com.slabs.expense.tracker.common.database.mapper;

import java.util.List;

import com.slabs.expense.tracker.common.database.entity.Message;

public interface MessageDAO {

	public Integer insertMessage(List<Message> records);

	public Integer updateMessage(Message record);

	public Integer deleteMessage(Message record);

	public List<Message> getMessage(String username, boolean isNew);

	public Integer insertQuery(Message record);

	public Integer updateQuery(Message record);

	public Integer deleteQuery(Message record);

	public List<Message> getQuery(boolean isNew);
}
