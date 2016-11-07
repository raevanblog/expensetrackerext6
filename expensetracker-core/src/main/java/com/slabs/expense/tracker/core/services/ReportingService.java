package com.slabs.expense.tracker.core.services;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.slabs.expense.tracker.common.db.column.Column;
import com.slabs.expense.tracker.common.db.entity.Expense;
import com.slabs.expense.tracker.common.db.entity.UserInfo;
import com.slabs.expense.tracker.database.mapper.ExpenseMapper;
import com.slabs.expense.tracker.database.mapper.IncomeMapper;
import com.slabs.expense.tracker.database.mapper.UserMapper;
import com.slabs.expense.tracker.reports.Month;
import com.slabs.expense.tracker.reports.MonthlyExpenseReport;
import com.slabs.expense.tracker.reports.column.data.type.CurrencyType;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;

@Service(value = "ReportingService")
public class ReportingService {

	@Autowired
	private ExpenseMapper eMapper;

	@Autowired
	private IncomeMapper iMapper;

	@Autowired
	private UserMapper uMapper;

	public JasperReportBuilder monthlyReport(String username, Integer year, Integer month) throws Exception {
		List<UserInfo> info = uMapper.getUser(username, Boolean.FALSE);
		if (info != null && !info.isEmpty()) {
			List<Expense> expenses = eMapper.getExpense(username, year, month);

			MonthlyExpenseReport report = new MonthlyExpenseReport(info.get(0), Month.getMonth(month), year, CurrencyType.DOLLAR);
			report.addPageNumber();
			report.subTotalPrice();
			report.groupBy(Column.EXPTYPE, Boolean.TRUE);
			report.setDataSource(expenses);
			return report.buildReport();
		}
		return null;
	}

}
