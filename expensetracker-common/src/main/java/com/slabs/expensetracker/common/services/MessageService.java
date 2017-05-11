package com.slabs.expensetracker.common.services;

import java.util.List;

import com.slabs.expensetracker.common.database.entity.Message;
import com.slabs.expensetracker.common.database.entity.UserInfo;

public interface MessageService {

	public void sendWelcomeMessage(UserInfo user) throws Exception;

	public List<Message> getMessages(String username, boolean isNew) throws Exception;

	public Integer createMessage(List<Message> messages) throws Exception;

	public Integer updateMessage(List<Message> messages) throws Exception;

	public Integer deleteMessage(List<Message> messages) throws Exception;

	public Integer createQuery(Message query) throws Exception;

	public Integer updateQuery(List<Message> queries) throws Exception;

	public Integer deleteQuery(List<Message> queries) throws Exception;

	public List<Message> getQueries(boolean isNew) throws Exception;

}
