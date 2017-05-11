package com.slabs.expensetracker.common.database.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.slabs.expensetracker.common.database.entity.Message;

@Component(value = "MessageDAO")
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
