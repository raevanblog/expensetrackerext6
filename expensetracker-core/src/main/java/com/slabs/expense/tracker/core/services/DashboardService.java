package com.slabs.expense.tracker.core.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.slabs.expense.tracker.common.db.entity.Dashboard;
import com.slabs.expense.tracker.common.db.entity.Summary;
import com.slabs.expense.tracker.database.mapper.ExpenseMapper;
import com.slabs.expense.tracker.database.mapper.IncomeMapper;

@Service(value = "DashboardService")
public class DashboardService {

	@Autowired
	private ExpenseMapper eMapper;

	@Autowired
	private IncomeMapper iMapper;

	public Dashboard getDashboardData(String username, int year, int month) throws Exception {
		Float totalExpense = eMapper.getTotalExpense(username, year, month);
		Float totalIncome = iMapper.getTotalIncome(username, year, month);
		Float cashInHand = totalIncome - totalExpense;

		Dashboard dashboard = new Dashboard();
		Summary summary = new Summary();
		summary.setTotalExpense(totalExpense);
		summary.setTotalIncome(totalIncome);
		summary.setCashInHand(cashInHand);
		summary.setMonth(month);
		summary.setYear(year);
		summary.setUsername(username);
		dashboard.setSummary(summary);

		return dashboard;
	}

}
