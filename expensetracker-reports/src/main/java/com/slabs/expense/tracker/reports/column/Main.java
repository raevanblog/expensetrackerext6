package com.slabs.expense.tracker.reports.column;

import java.sql.Connection;
import java.sql.DriverManager;

import com.slabs.expense.tracker.common.database.column.Column;
import com.slabs.expense.tracker.common.database.entity.UserInfo;
import com.slabs.expense.tracker.reports.Month;
import com.slabs.expense.tracker.reports.MonthlyExpenseReport;
import com.slabs.expense.tracker.reports.column.data.type.CurrencyType;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;

public class Main {

	public static void main(String[] args) throws Exception {

		Class.forName("org.apache.derby.jdbc.ClientDriver");
		Connection connection = DriverManager.getConnection(
				"jdbc:derby://localhost:1527/EXPENSETRACKER", "expensetracker", "expensetracker");

		UserInfo userInfo = new UserInfo();
		userInfo.setFirstName("Shyam");
		userInfo.setLastName("Natarajan");
		userInfo.setMobile("9894362480");
		userInfo.setEmail("shyamcse07@gmail.com");

		MonthlyExpenseReport report = new MonthlyExpenseReport(userInfo, Month.getMonth(9), 2016,
				CurrencyType.INR);
		report.setDataSource(
				"select * from EXPENSETRACKER.EXPENSE where username='shyamcse07' order by CATEGORY ASC",
				connection);
		report.groupBy(Column.CATEGORY, true);
		report.subTotalPrice();
		JasperReportBuilder rep = report.buildReport();
		rep.show();

	}

}
