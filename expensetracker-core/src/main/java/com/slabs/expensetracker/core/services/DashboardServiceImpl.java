package com.slabs.expensetracker.core.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.slabs.expensetracker.common.database.entity.Dashboard;
import com.slabs.expensetracker.common.database.entity.Summary;
import com.slabs.expensetracker.common.database.mapper.ExpenseDAO;
import com.slabs.expensetracker.common.database.mapper.IncomeDAO;
import com.slabs.expensetracker.common.services.DashboardService;

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
	 * @return {@link com.slabs.expense.tracker.common.database.entity.Dashboard} @
	 *         throws {@link Exception}
	 */
	@Override
	public Dashboard getDashboardData(String username, Integer year, Integer month) throws Exception {
		Double totalExpense = eMapper.getTotalExpense(username, year, month);
		Double creditExpense = eMapper.getCreditExpense(username, year, month);
		Double totalIncome = iMapper.getTotalIncome(username, year, month);

		if (totalExpense == null) {
			totalExpense = 0.0;
		}
		
		if(creditExpense == null) {
			creditExpense = 0.0;
		}

		if (totalIncome == null) {
			totalIncome = 0.0;
		}

		Double cashInHand = totalIncome - totalExpense;

		Dashboard dashboard = new Dashboard();
		Summary summary = new Summary();
		summary.setTotalExpense(totalExpense);
		summary.setCreditExpense(creditExpense);
		summary.setTotalIncome(totalIncome);
		summary.setCashInHand(cashInHand);
		summary.setMonth(month);
		summary.setYear(year);
		summary.setUsername(username);
		dashboard.setSummary(summary);

		return dashboard;
	}

}
