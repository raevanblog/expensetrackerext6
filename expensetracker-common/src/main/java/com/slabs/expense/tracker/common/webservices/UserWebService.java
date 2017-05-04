package com.slabs.expense.tracker.common.webservices;

import java.util.List;

import com.slabs.expense.tracker.common.database.entity.UserInfo;
import com.slabs.expense.tracker.common.database.entity.UserSettings;
import com.slabs.expense.tracker.common.exception.ExpenseTrackerException;
import com.slabs.expense.tracker.webservice.response.Response;

public interface UserWebService {

	public Response createUser(UserInfo record) throws ExpenseTrackerException;

	public Response updateUser(UserInfo record) throws ExpenseTrackerException;

	public Response changePassword(UserInfo record) throws ExpenseTrackerException;

	public Response deleteUser(List<UserInfo> records) throws ExpenseTrackerException;

	public Response getUser(String username) throws ExpenseTrackerException;

	public Response getUsers() throws ExpenseTrackerException;

	public Response getUserSettings(String username) throws ExpenseTrackerException;

	public Response createUserSettings(UserSettings record) throws ExpenseTrackerException;

	public Response updateUserSettings(UserSettings record) throws ExpenseTrackerException;

}
