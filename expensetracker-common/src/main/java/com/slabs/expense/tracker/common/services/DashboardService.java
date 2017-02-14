package com.slabs.expense.tracker.common.services;

import com.slabs.expense.tracker.common.database.entity.Dashboard;

public interface DashboardService {

	public Dashboard getDashboardData(String username, Integer year, Integer month) throws Exception;

}
