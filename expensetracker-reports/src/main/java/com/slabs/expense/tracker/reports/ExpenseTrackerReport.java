package com.slabs.expense.tracker.reports;

import java.awt.Color;
import java.sql.Connection;
import java.util.Collection;

import com.slabs.expense.tracker.common.db.entity.UserInfo;
import com.slabs.expense.tracker.reports.builder.ReportBuilder;
import com.slabs.expense.tracker.reports.provider.ColumnProvider;
import com.slabs.expense.tracker.reports.provider.StyleProvider;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.component.ComponentBuilders;
import net.sf.dynamicreports.report.builder.component.VerticalListBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilders;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;

public class ExpenseTrackerReport {

	protected ReportBuilder builder = ReportBuilder.getInstance();

	protected ColumnProvider cProvider = builder.getColumnProvider();

	protected ComponentBuilders cBuilders = builder.getComponentBuilders();

	protected StyleProvider sProvider = builder.getStyleProvider();

	protected StyleBuilders sBuilders = builder.getStyleBuilders();

	protected JasperReportBuilder report;

	protected static final Color DEFAULT_HEADER_COLOR = new Color(127, 217, 244);

	private static final String APPLICATION_NAME = "Expense Tracker";

	protected UserInfo userInfo;

	protected Integer year;

	protected Month month;

	public ExpenseTrackerReport(UserInfo userInfo, Month month, Integer year) {
		this.userInfo = userInfo;
		this.month = month;
		this.year = year;
		this.report = ReportBuilder.getInstance().createJasperReportBuilder();
		this.report.setDefaultFont(ReportBuilder.getInstance().getDefaultFont());
	}

	protected void setDataSource(Collection<? extends Object> dataSource) {
		report.setDataSource(dataSource);
	}

	public void setDataSource(String sql, Connection connection) {
		report.setDataSource(sql, connection);
	}

	public void addPageNumber() {
		report.pageFooter(cBuilders.pageXofY().setStyle(sProvider.getBoldStyle(HorizontalTextAlignment.CENTER)));
	}

	public void addTitle() {
		
	}

}
