package com.slabs.expense.tracker.core.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.slabs.expense.tracker.common.db.entity.Dashboard;
import com.slabs.expense.tracker.common.db.entity.Summary;
import com.slabs.expense.tracker.common.db.mapper.ExpenseDAO;
import com.slabs.expense.tracker.common.db.mapper.IncomeDAO;
import com.slabs.expense.tracker.common.services.DashboardService;

/**
 * {@link DashboardServiceImpl} provides API's for retrieving data for Expense
 * Tracker dashboard.
 * 
 * @author Shyam Natarajan
 *
 */
@Service(value = "DashboardService")
public class DashboardServiceImpl implements DashboardService {

	@Autowired
	private ExpenseDAO eMapper;

	@Autowired
	private IncomeDAO iMapper;

	/**
	 * 
	 * @param username
	 *            {@link String} - Username of the user
	 * @param year
	 *            {@link Integer} - Year for which the data is to be retrieved
	 * @param month
	 *            {@link Integer} - Month for which the data is to be retrieved
	 * 
	 * @return {@link com.slabs.expense.tracker.common.db.entity.Dashboard} @
	 *         throws {@link Exception}
	 */
	@Override
	public Dashboard getDashboardData(String username, Integer year, Integer month) throws Exception {
		Double totalExpense = eMapper.getTotalExpense(username, year, month);
		Double totalIncome = iMapper.getTotalIncome(username, year, month);

		if (totalExpense == null) {
			totalExpense = 0.0;
		}

		if (totalIncome == null) {
			totalIncome = 0.0;
		}

		Double cashInHand = totalIncome - totalExpense;

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
