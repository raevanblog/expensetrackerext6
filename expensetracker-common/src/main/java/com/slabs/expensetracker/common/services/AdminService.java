package com.slabs.expensetracker.common.services;

import java.util.List;

import com.slabs.expensetracker.common.database.entity.UserInfo;

public interface AdminService {
	
	public boolean activateUser(String username, String isActivate) throws Exception;
	
	public Integer deleteUser(List<UserInfo> records)  throws Exception;
}
