package com.slabs.expense.tracker.common.services;

import java.util.List;

import com.slabs.expense.tracker.common.db.entity.UserInfo;

public interface AdminService {
	
	public boolean activateUser(String username, String isActivate) throws Exception;
	
	public Integer deleteUser(List<UserInfo> records)  throws Exception;
}
