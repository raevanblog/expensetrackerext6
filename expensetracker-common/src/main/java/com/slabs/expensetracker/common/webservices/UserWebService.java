package com.slabs.expensetracker.common.webservices;

import java.util.List;

import com.slabs.expensetracker.common.database.entity.UserInfo;
import com.slabs.expensetracker.common.database.entity.UserSettings;
import com.slabs.expensetracker.common.webservice.response.Response;
import com.slabs.expensetracker.common.exception.ExpenseTrackerException;

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
