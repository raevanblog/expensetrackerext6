package com.slabs.expensetracker.common.webservices;

import java.util.List;

import com.slabs.expensetracker.common.database.entity.Message;
import com.slabs.expensetracker.common.webservice.response.Response;
import com.slabs.expensetracker.common.exception.ExpenseTrackerException;

public interface MessagingWebService {

	public Response getMessages(String username, Boolean isNew) throws ExpenseTrackerException;

	public Response createMessage(List<Message> messages) throws ExpenseTrackerException;

	public Response updateMessage(List<Message> messages) throws ExpenseTrackerException;

	public Response deleteMessage(List<Message> messages) throws ExpenseTrackerException;

	public Response getQueries(Boolean isNew) throws ExpenseTrackerException;

	public Response createQuery(Message message) throws ExpenseTrackerException;

	public Response deleteQuery(List<Message> messages) throws ExpenseTrackerException;

}
