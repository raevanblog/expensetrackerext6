package com.slabs.expense.tracker.reports;

import java.util.Collection;

import com.slabs.expense.tracker.common.database.column.Column;
import com.slabs.expense.tracker.common.database.entity.UserInfo;
import com.slabs.expense.tracker.reports.builder.ReportBuilder;
import com.slabs.expense.tracker.reports.column.data.type.CurrencyType;

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

	private ExpenseReport expenseReport;

	private IncomeReport incomeReport;

	public MonthlyExpenseReport(UserInfo userInfo, Month month, Integer year) throws InstantiationException, IllegalAccessException {
		this(userInfo, month, year, CurrencyType.USD);
	}

	public MonthlyExpenseReport(UserInfo userInfo, Month month, Integer year, CurrencyType currency)
			throws InstantiationException, IllegalAccessException {
		this.expenseReport = new ExpenseReport(userInfo, month, year, currency);
		this.incomeReport = new IncomeReport(userInfo, month, year, currency);
	}

	public JasperConcatenatedReportBuilder buildReport() throws DRException {
		this.report = ReportBuilder.getInstance().createJasperConcatenatedReportBuilder();
		report.concatenate(incomeReport.buildReport(), expenseReport.buildReport());
		report.continuousPageNumbering();
		return this.report;
	}

	public void setDataSourceForExpense(Collection<? extends Object> dataSource) {
		this.expenseReport.setDataSource(dataSource);
	}

	public void setDataSourceForIncome(Collection<? extends Object> dataSource) {
		this.incomeReport.setDataSource(dataSource);
	}

	public void enablePageNumber() {
		this.expenseReport.addPageNumber();
		this.incomeReport.addPageNumber();
	}

	public void enableSubTotal() {
		this.expenseReport.subTotal(Column.PRICE, Column.PRICEPERUNIT);
		this.incomeReport.subTotal(Column.INCOME, Column.INCOMETYPE);
	}

}
