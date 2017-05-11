package com.slabs.expense.tracker.reports;

import java.awt.Color;
import java.math.BigDecimal;

import com.slabs.expense.tracker.common.database.entity.UserInfo;
import com.slabs.expense.tracker.reports.column.data.type.CurrencyType;
import com.slabs.expensetracker.common.database.column.Column;
import com.slabs.expensetracker.common.exception.ExpenseTrackerException;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.chart.CategoryChartSerieBuilder;
import net.sf.dynamicreports.report.builder.chart.PieChartBuilder;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.component.TextFieldBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.exception.DRException;

public class IncomeReport extends ExpenseTrackerReport {

	public IncomeReport(UserInfo userInfo, Month month, Integer year) throws InstantiationException, IllegalAccessException {
		this(userInfo, month, year, CurrencyType.USD);
	}

	public IncomeReport(UserInfo userInfo, Month month, Integer year, CurrencyType currency) throws InstantiationException, IllegalAccessException {
		super(userInfo, month, year, currency);
		addColumns();
	}

	@Override
	public void addGridTitle() {
		TextFieldBuilder<String> incomeTitle = componentBuilder.text("Income Details")
				.setStyle(styleProvider.getBoldStyle(14).setUnderline(Boolean.TRUE));

		report.addTitle(incomeTitle, componentBuilder.verticalGap(5));
	}

	private void addColumns() {

		StyleBuilder centerAligned = styleProvider.getStyle(HorizontalTextAlignment.CENTER).setBottomPadding(2).setTopPadding(2);
		report.setColumnTitleStyle(styleProvider.getColumnHeader(Color.BLACK, DEFAULT_HEADER_COLOR, false));
		report.setColumnStyle(styleProvider.getBorder(centerAligned, 1.0f));

		TextColumnBuilder<Integer> rowNum = columnProvider.getRowNumberColumn("S.No");
		TextColumnBuilder<String> incomeType = columnProvider.getColumn(Column.INCOMETYPE, String.class);
		TextColumnBuilder<BigDecimal> income = columnProvider.getColumn(Column.INCOME, BigDecimal.class);

		addColumns(rowNum, incomeType, income);
	}

	private void addSummary() {
		report.summary(componentBuilder.verticalGap(25), getIncomePie());
	}

	private PieChartBuilder getIncomePie() {
		TextColumnBuilder<String> incomeType = columnProvider.getColumn(Column.INCOMETYPE, String.class);
		TextColumnBuilder<Double> income = columnProvider.getColumn(Column.INCOME, Double.class);

		CategoryChartSerieBuilder incomeSeries = chartBuilder.serie(income);

		PieChartBuilder pieChart = chartBuilder.pieChart();
		pieChart.setTitle("Income Pie").setTitleFont(getDefaultFont(14, Boolean.TRUE, Boolean.FALSE));
		pieChart.setKey(incomeType);
		pieChart.setFixedHeight(400);
		pieChart.series(incomeSeries);

		return pieChart;
	}

	@Override
	public JasperReportBuilder buildReport(String reportName) throws DRException {
		addTitle(reportName);
		addGridTitle();
		addColumnsToReport();
		addSummary();
		return report;
	}

	@Override
	public void groupBy(Column column, boolean enableSubtotal) throws ExpenseTrackerException {
		throw new ExpenseTrackerException("Unsupported Operation");
	}

}
