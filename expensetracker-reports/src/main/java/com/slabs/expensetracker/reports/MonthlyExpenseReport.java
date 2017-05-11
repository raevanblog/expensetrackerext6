package com.slabs.expensetracker.reports;

import java.util.Collection;

import com.slabs.expensetracker.common.database.entity.UserInfo;
import com.slabs.expensetracker.common.constants.Constants;
import com.slabs.expensetracker.common.database.column.Column;
import com.slabs.expensetracker.reports.builder.ReportBuilder;
import com.slabs.expensetracker.reports.column.data.type.CurrencyType;

import net.sf.dynamicreports.jasper.builder.JasperConcatenatedReportBuilder;
import net.sf.dynamicreports.report.exception.DRException;

/**
 * {@link MonthlyExpenseReport} is the report class extending
 * {@link ExpenseTrackerReport}. This builds the monthly report for Expense
 * Tracker application
 * 
 * @author Shyam Natarajan
 *
 */
public class MonthlyExpenseReport {

	private JasperConcatenatedReportBuilder report;

	private IndexReport indexReport;

	private IncomeReport incomeReport;

	private ExpenseReport expenseReport;

	public MonthlyExpenseReport(UserInfo userInfo, Month month, Integer year) throws InstantiationException, IllegalAccessException {
		this(userInfo, month, year, CurrencyType.USD);
	}

	public MonthlyExpenseReport(UserInfo userInfo, Month month, Integer year, CurrencyType currency)
			throws InstantiationException, IllegalAccessException {
		this.indexReport = new IndexReport(userInfo, month, year, currency);
		this.incomeReport = new IncomeReport(userInfo, month, year, currency);
		this.expenseReport = new ExpenseReport(userInfo, month, year, currency);
	}

	public JasperConcatenatedReportBuilder buildReport() throws DRException {
		this.report = ReportBuilder.getInstance().createJasperConcatenatedReportBuilder();
		report.concatenate(indexReport.buildReport(Constants.MONTHLY_REPORT), incomeReport.buildReport(Constants.INCOME_REPORT),
				expenseReport.buildReport(Constants.EXPENSE_REPORT));
		report.continuousPageNumbering();
		return this.report;
	}

	public void setDataSourceForExpense(Collection<? extends Object> dataSource) {
		this.expenseReport.setDataSource(dataSource);
	}

	public void setDataSourceForIncome(Collection<? extends Object> dataSource) {
		this.incomeReport.setDataSource(dataSource);
	}

	public void enableSubTotal() {
		this.expenseReport.subTotal(Column.PRICE, Column.PRICEPERUNIT);
		this.incomeReport.subTotal(Column.INCOME, Column.INCOMETYPE);
	}

}
