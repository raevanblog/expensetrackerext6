package com.slabs.expensetracker.common.webservices;

import java.util.List;

import com.slabs.expensetracker.common.database.entity.UserInfo;
import com.slabs.expensetracker.common.webservice.response.Response;
import com.slabs.expensetracker.common.exception.ExpenseTrackerException;

public interface AdminWebService {

	public Response deleteUser(List<UserInfo> records) throws ExpenseTrackerException;

}
