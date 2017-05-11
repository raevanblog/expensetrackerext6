package com.slabs.expense.tracker.common.webservices;

import java.util.List;

import com.slabs.expense.tracker.common.database.entity.Message;
import com.slabs.expense.tracker.common.exception.ExpenseTrackerException;
import com.slabs.expense.tracker.common.webservice.response.Response;

public interface MessagingWebService {

	public Response getMessages(String username, Boolean isNew) throws ExpenseTrackerException;

	public Response createMessage(List<Message> messages) throws ExpenseTrackerException;

	public Response updateMessage(List<Message> messages) throws ExpenseTrackerException;

	public Response deleteMessage(List<Message> messages) throws ExpenseTrackerException;

	public Response getQueries(Boolean isNew) throws ExpenseTrackerException;

	public Response createQuery(Message message) throws ExpenseTrackerException;

	public Response deleteQuery(List<Message> messages) throws ExpenseTrackerException;

}
