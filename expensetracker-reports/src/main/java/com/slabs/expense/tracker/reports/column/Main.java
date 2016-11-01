package com.slabs.expense.tracker.reports.column;

import java.sql.Connection;
import java.sql.DriverManager;

import com.slabs.expense.tracker.common.db.entity.UserInfo;
import com.slabs.expense.tracker.reports.Month;
import com.slabs.expense.tracker.reports.MonthlyExpenseReport;

public class Main {

	public static void main(String[] args) throws Exception {

		Class.forName("org.apache.derby.jdbc.ClientDriver");
		Connection connection = DriverManager.getConnection("jdbc:derby://localhost:1527/EXPENSETRACKER", "expensetracker", "expensetracker");

		UserInfo userInfo = new UserInfo();
		userInfo.setFirstName("Shyam");
		userInfo.setLastName("Natarajan");
		userInfo.setMobile("9894362480");
		userInfo.setEmail("shyamcse07@gmail.com");

		MonthlyExpenseReport report = new MonthlyExpenseReport(userInfo, Month.getMonth(9), 2016);
		report.setDataSource("select * from EXPENSETRACKER.EXPENSE where username='shyamcse07' and YEAR(expdate)=2016 and MONTH(expdate)=10",
				connection);
		report.buildReport().show();

	}

}
