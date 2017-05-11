package com.slabs.expensetracker.reports.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.slabs.expensetracker.common.database.entity.Expense;
import com.slabs.expensetracker.common.database.entity.Income;
import com.slabs.expensetracker.common.database.entity.UserInfo;
import com.slabs.expensetracker.common.database.mapper.ExpenseDAO;
import com.slabs.expensetracker.common.database.mapper.IncomeDAO;
import com.slabs.expensetracker.common.database.mapper.UserDAO;
import com.slabs.expensetracker.reports.Month;
import com.slabs.expensetracker.reports.MonthlyExpenseReport;
import com.slabs.expensetracker.reports.column.data.type.CurrencyType;

import net.sf.dynamicreports.jasper.builder.JasperConcatenatedReportBuilder;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;

/**
 * {@link ReportingService} provides API for generating reports using
 * {@link DynamicReports}
 * 
 * @author Shyam Natarajan
 *
 */
@Service(value = "ReportingService")
public class ReportingService {

	@Autowired
	private ExpenseDAO expenseDao;

	@Autowired
	private IncomeDAO incomeDao;

	@Autowired
	private UserDAO userDao;

	/**
	 * This method will return {@link JasperReportBuilder} if the User exists
	 * for the given username else it will return <code>null</code>
	 * 
	 * @param username
	 *            {@link String} - Username of the user.
	 * @param year
	 *            {@link Integer} - Year for which the report need to be
	 *            generated
	 * @param month
	 *            {@link Integer} - Month for which the report need to be
	 *            generated
	 * @return {@link JasperReportBuilder}
	 * @throws Exception
	 *             throws {@link Exception}
	 */
	public JasperConcatenatedReportBuilder generateMonthlyExpenseReport(String username, Integer year, Integer month, String currencyName)
			throws Exception {
		List<UserInfo> info = userDao.getUser(username, Boolean.FALSE);
		if (info != null && !info.isEmpty()) {
			List<Expense> expenses = expenseDao.getExpense(username, year, month);
			List<Income> income = incomeDao.getIncome(username, year, month);
			if (year != null) {
				if (month != null) {
					if (!expenses.isEmpty()) {
						MonthlyExpenseReport report = new MonthlyExpenseReport(info.get(0), Month.getMonth(month), year,
								CurrencyType.getCurrency(currencyName));						
						report.enableSubTotal();
						report.setDataSourceForExpense(expenses);
						report.setDataSourceForIncome(income);
						return report.buildReport();
					}
				}
			}
		}
		return null;
	}

}