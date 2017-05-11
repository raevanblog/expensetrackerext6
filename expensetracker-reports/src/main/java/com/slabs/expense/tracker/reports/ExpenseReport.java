package com.slabs.expense.tracker.reports;

import java.awt.Color;
import java.math.BigDecimal;

import com.slabs.expense.tracker.common.database.entity.UserInfo;
import com.slabs.expense.tracker.reports.builder.BarChartCustomizer;
import com.slabs.expense.tracker.reports.column.data.type.CurrencyType;
import com.slabs.expense.tracker.reports.expression.ExpenseImageResolver;
import com.slabs.expensetracker.common.database.column.Column;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.chart.AxisFormatBuilder;
import net.sf.dynamicreports.report.builder.chart.BarChartBuilder;
import net.sf.dynamicreports.report.builder.chart.CategoryChartSerieBuilder;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.column.ValueColumnBuilder;
import net.sf.dynamicreports.report.builder.component.ImageBuilder;
import net.sf.dynamicreports.report.builder.component.TextFieldBuilder;
import net.sf.dynamicreports.report.builder.group.CustomGroupBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.builder.subtotal.AggregationSubtotalBuilder;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.exception.DRException;

public class ExpenseReport extends ExpenseTrackerReport {

	public ExpenseReport(UserInfo userInfo, Month month, Integer year) throws InstantiationException, IllegalAccessException {
		this(userInfo, month, year, CurrencyType.USD);
	}

	public ExpenseReport(UserInfo userInfo, Month month, Integer year, CurrencyType currency) throws InstantiationException, IllegalAccessException {
		super(userInfo, month, year, currency);
		addColumns();
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
	public void addGridTitle() {
		TextFieldBuilder<String> expenseTitle = componentBuilder.text("Expense Details")
				.setStyle(styleProvider.getBoldStyle(14).setUnderline(Boolean.TRUE));

		report.addTitle(expenseTitle, componentBuilder.verticalGap(5));
	}

	@SuppressWarnings("rawtypes")
	private void addColumns() {
		
		StyleBuilder centerAligned = styleProvider.getStyle(HorizontalTextAlignment.CENTER).setBottomPadding(2).setTopPadding(2);
		report.setColumnTitleStyle(styleProvider.getColumnHeader(Color.BLACK, DEFAULT_HEADER_COLOR, false));
		report.setColumnStyle(styleProvider.getBorder(centerAligned, 1.0f));

		TextColumnBuilder<Integer> rowNum = columnProvider.getRowNumberColumn("S.No");
		ValueColumnBuilder expDate = columnProvider.getDateColumn(Column.EXPDATE);
		TextColumnBuilder<String> itemName = columnProvider.getColumn(Column.ITEMNAME, String.class);
		TextColumnBuilder<String> expenseType = columnProvider.getColumn(Column.EXPTYPE, String.class).setFixedColumns(10);
		TextColumnBuilder<String> expenseCategory = columnProvider.getColumn(Column.CATEGORY, String.class);
		TextColumnBuilder<BigDecimal> qty = columnProvider.getColumn(Column.QTY, BigDecimal.class).setFixedColumns(5);
		TextColumnBuilder<String> unit = columnProvider.getColumn(Column.UNIT, String.class);
		TextColumnBuilder<BigDecimal> price = columnProvider.getColumn(Column.PRICE, BigDecimal.class).setDataType(this.currency);
		TextColumnBuilder<BigDecimal> pricePerUnit = columnProvider.getColumn(Column.PRICEPERUNIT, BigDecimal.class).setDataType(this.currency);

		addColumns(rowNum, expDate, itemName, expenseType, expenseCategory, qty, unit, pricePerUnit, price);
	}

	private void addSummary() {
		report.summary(componentBuilder.verticalGap(25), getExpenseChart());
	}

	private BarChartBuilder getExpenseChart() {
		TextColumnBuilder<String> category = columnProvider.getColumn(Column.CATEGORY, String.class);
		TextColumnBuilder<Double> price = columnProvider.getColumn(Column.PRICE, Double.class);

		CategoryChartSerieBuilder priceSeries = chartBuilder.serie(price);
		AxisFormatBuilder axisLabel = chartBuilder.axisFormat().setLabel("Category");

		BarChartBuilder expenseChart = chartBuilder.barChart();
		expenseChart.setTitle("Expense/Category").setTitleFont(getDefaultFont(14, Boolean.TRUE, Boolean.FALSE));
		expenseChart.setCategory(category);
		expenseChart.series(priceSeries);
		expenseChart.setCategoryAxisFormat(axisLabel);
		expenseChart.setShowValues(Boolean.TRUE);
		expenseChart.setFixedHeight(400);
		expenseChart.addCustomizer(new BarChartCustomizer());

		return expenseChart;

	}

	@SuppressWarnings("rawtypes")
	@Override
	public void groupBy(Column column, boolean subtotalPrice) {
		StyleBuilder grpTitleStyle = styleProvider.getStyle().setBottomPadding(2).setTopPadding(5);
		StyleBuilder imageStyle = styleProvider.getStyle().setBottomPadding(2);

		CustomGroupBuilder group = DynamicReports.grp.group(column.titleName, column.mappingName, column.datatype);

		if (Column.EXPTYPE == column) {
			ImageBuilder image = DynamicReports.cmp.image(new ExpenseImageResolver()).setFixedDimension(16, 16).setStyle(imageStyle);
			group.addHeaderComponent(image).setStyle(grpTitleStyle);
		}

		if (subtotalPrice) {
			AggregationSubtotalBuilder subtotal = getSubTotalBuilder(Column.PRICE);
			report.subtotalsAtGroupFooter(group, subtotal);
			group.setPrintSubtotalsWhenExpression(DynamicReports.exp.printWhenGroupHasMoreThanOneRow(group));
		}
		report.groupBy(group);
	}

}
