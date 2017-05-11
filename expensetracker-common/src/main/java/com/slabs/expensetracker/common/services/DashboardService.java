package com.slabs.expensetracker.common.services;

import com.slabs.expensetracker.common.database.entity.Dashboard;

public interface DashboardService {

	public Dashboard getDashboardData(String username, Integer year, Integer month) throws Exception;

}
