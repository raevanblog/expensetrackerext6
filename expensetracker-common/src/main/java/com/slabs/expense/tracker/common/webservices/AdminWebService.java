package com.slabs.expense.tracker.common.webservices;

import java.util.List;

import com.slabs.expense.tracker.common.database.entity.UserInfo;
import com.slabs.expense.tracker.common.exception.ExpenseTrackerException;
import com.slabs.expense.tracker.common.webservice.response.Response;

public interface AdminWebService {

	public Response deleteUser(List<UserInfo> records) throws ExpenseTrackerException;

}
