package com.slabs.expense.tracker.reports;

import java.awt.Color;
import java.math.BigDecimal;
import java.util.List;

import com.slabs.expense.tracker.common.constants.Constants;
import com.slabs.expense.tracker.common.database.column.Column;
import com.slabs.expense.tracker.common.database.entity.Income;
import com.slabs.expense.tracker.common.database.entity.UserInfo;
import com.slabs.expense.tracker.reports.column.data.type.CurrencyType;
import com.slabs.expense.tracker.reports.expression.ExpenseImageResolver;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.chart.AxisFormatBuilder;
import net.sf.dynamicreports.report.builder.chart.BarChartBuilder;
import net.sf.dynamicreports.report.builder.chart.CategoryChartSerieBuilder;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.column.ValueColumnBuilder;
import net.sf.dynamicreports.report.builder.component.HorizontalListBuilder;
import net.sf.dynamicreports.report.builder.component.ImageBuilder;
import net.sf.dynamicreports.report.builder.component.TextFieldBuilder;
import net.sf.dynamicreports.report.builder.component.VerticalListBuilder;
import net.sf.dynamicreports.report.builder.group.CustomGroupBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.builder.subtotal.AggregationSubtotalBuilder;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.constant.VerticalTextAlignment;
import net.sf.dynamicreports.report.exception.DRException;

/**
 * {@link MonthlyExpenseReport} is the report class extending
 * {@link ExpenseTrackerReport}. This builds the monthly report for Expense
 * Tracker application
 * 
 * @author Shyam Natarajan
 *
 */
public class MonthlyExpenseReport extends ExpenseTrackerReport {

	private List<Income> monthlyIncome;

	public MonthlyExpenseReport(UserInfo userInfo, List<Income> monthlyIncome, Month month,
			Integer year) throws InstantiationException, IllegalAccessException {
		this(userInfo, monthlyIncome, month, year, CurrencyType.USD);
	}

	public MonthlyExpenseReport(UserInfo userInfo, List<Income> monthlyIncome, Month month,
			Integer year, CurrencyType currency)
			throws InstantiationException, IllegalAccessException {
		super(userInfo, month, year, currency);
		this.monthlyIncome = monthlyIncome;
		addColumns();
	}

	@Override
	public JasperReportBuilder buildReport() throws DRException {
		addTitle();
		addReportDetails();
		addIncomeDetails();
		addColumnsToReport();
		addSummary();
		return report;
	}

	private void addTitle() {

		HorizontalListBuilder titleContainer = componentBuilder.horizontalList();
		TextFieldBuilder<String> logoTitle = getLogoTitle(VerticalTextAlignment.BOTTOM)
				.setHorizontalTextAlignment(HorizontalTextAlignment.LEFT);

		TextFieldBuilder<String> reportTitle = componentBuilder.text(Constants.MONTHLY_REPORT)
				.setStyle(styleProvider.getBoldStyle());

		TextFieldBuilder<String> monthAnYear = componentBuilder
				.text(new StringBuilder(month.getName()).append(",").append(year).toString())
				.setStyle(styleProvider.getBoldStyle(12)
						.setHorizontalTextAlignment(HorizontalTextAlignment.RIGHT));
		titleContainer.add(getLogo(), monthAnYear).newFlowRow(2);
		titleContainer
				.add(logoTitle,
						reportTitle.setHorizontalTextAlignment(HorizontalTextAlignment.RIGHT))
				.newRow();

		report.title(titleContainer, componentBuilder.verticalGap(5),
				styleProvider.getDefaultFillerLine(10));
	}

	private void addReportDetails() {
		VerticalListBuilder verticalContainer = componentBuilder.verticalList();

		TextFieldBuilder<String> title = componentBuilder.text("Report Owner")
				.setStyle(styleProvider.getBoldStyle(12).setUnderline(Boolean.TRUE));

		HorizontalListBuilder detailContainer = componentBuilder.horizontalList();

		TextFieldBuilder<String> name = componentBuilder
				.text("Name : " + userInfo.getFirstName() + " " + userInfo.getLastName())
				.setStyle(styleProvider.getStyle());
		TextFieldBuilder<String> email = componentBuilder.text("Email : " + userInfo.getEmail())
				.setStyle(styleProvider.getStyle());
		TextFieldBuilder<String> address = componentBuilder
				.text("Address : " + userInfo.getAddress()).setStyle(styleProvider.getStyle());

		detailContainer.add(name).newRow();
		detailContainer.add(email).newRow();
		detailContainer.add(address).newRow(5);

		verticalContainer.add(title, componentBuilder.verticalGap(5), detailContainer);

		report.title(verticalContainer);
	}

	private void addIncomeDetails() {
		VerticalListBuilder verticalContainer = componentBuilder.verticalList();
		Double totalIncome = 0.0;

		TextFieldBuilder<String> title = componentBuilder.text("Income Details")
				.setStyle(styleProvider.getBoldStyle(12).setUnderline(Boolean.TRUE));

		TextFieldBuilder<String> expenseTitle = componentBuilder.text("Expense Details")
				.setStyle(styleProvider.getBoldStyle(12).setUnderline(Boolean.TRUE));

		HorizontalListBuilder detailContainer = componentBuilder.horizontalList();

		for (Income income : monthlyIncome) {
			TextFieldBuilder<String> data = componentBuilder.text(income.getIncometype() + " : "
					+ getCurrency().currencySymbol() + " " + income.getIncome());
			detailContainer.add(data).newRow();
			totalIncome = totalIncome + income.getIncome();
		}

		TextFieldBuilder<String> totIncome = componentBuilder
				.text("Total Income : " + getCurrency().currencySymbol() + " " + totalIncome);
		detailContainer.add(totIncome).newRow();

		verticalContainer.add(title, componentBuilder.verticalGap(5), detailContainer,
				componentBuilder.verticalGap(10), expenseTitle, componentBuilder.verticalGap(5));

		report.title(verticalContainer);
	}

	@SuppressWarnings("rawtypes")
	private void addColumns() {
		StyleBuilder centerAligned = styleProvider.getStyle(HorizontalTextAlignment.CENTER)
				.setBottomPadding(2).setTopPadding(2);
		report.setColumnTitleStyle(
				styleProvider.getColumnHeader(Color.BLACK, DEFAULT_HEADER_COLOR, false));
		report.setColumnStyle(styleProvider.getBorder(centerAligned, 1.0f));

		TextColumnBuilder<Integer> rowNum = columnProvider.getRowNumberColumn("S.No");
		ValueColumnBuilder expDate = columnProvider.getDateColumn(Column.EXPDATE);
		TextColumnBuilder<String> itemName = columnProvider.getColumn(Column.ITEMNAME,
				String.class);
		TextColumnBuilder<String> expenseType = columnProvider
				.getColumn(Column.EXPTYPE, String.class).setFixedColumns(10);
		TextColumnBuilder<String> expenseCategory = columnProvider.getColumn(Column.CATEGORY,
				String.class);
		TextColumnBuilder<BigDecimal> qty = columnProvider.getColumn(Column.QTY, BigDecimal.class)
				.setFixedColumns(5);
		TextColumnBuilder<BigDecimal> price = columnProvider
				.getColumn(Column.PRICE, BigDecimal.class).setDataType(this.currency);
		TextColumnBuilder<BigDecimal> pricePerUnit = columnProvider
				.getColumn(Column.PRICEPERUNIT, BigDecimal.class).setDataType(this.currency);

		addColumns(rowNum, expDate, itemName, expenseType, expenseCategory, qty, price,
				pricePerUnit);
	}

	private void addSummary() {
		report.summary(getExpenseChart());
	}

	private BarChartBuilder getExpenseChart() {
		TextColumnBuilder<String> category = columnProvider.getColumn(Column.CATEGORY,
				String.class);
		TextColumnBuilder<Double> price = columnProvider.getColumn(Column.PRICE, Double.class);

		CategoryChartSerieBuilder priceSeries = chartBuilder.serie(price);
		AxisFormatBuilder axisLabel = chartBuilder.axisFormat().setLabel("Category");

		BarChartBuilder expenseChart = chartBuilder.barChart();
		expenseChart.setTitle("Expense/Category")
				.setTitleFont(getDefaultFont(14, Boolean.TRUE, Boolean.FALSE));
		expenseChart.setCategory(category);
		expenseChart.series(priceSeries);
		expenseChart.setCategoryAxisFormat(axisLabel);

		return expenseChart;

	}

	private void addColumnsToReport() {
		report.columns(getAllColumns());
		report.highlightDetailOddRows();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void groupBy(Column column, boolean subtotalPrice) {
		StyleBuilder grpTitleStyle = styleProvider.getStyle().setBottomPadding(2).setTopPadding(5);
		StyleBuilder imageStyle = styleProvider.getStyle().setBottomPadding(2);

		CustomGroupBuilder group = DynamicReports.grp.group(column.titleName, column.mappingName,
				column.datatype);

		if (Column.EXPTYPE == column) {
			ImageBuilder image = DynamicReports.cmp.image(new ExpenseImageResolver())
					.setFixedDimension(16, 16).setStyle(imageStyle);
			group.addHeaderComponent(image).setStyle(grpTitleStyle);
		}

		if (subtotalPrice) {
			AggregationSubtotalBuilder subtotal = getSubTotalBuilder(Column.PRICE);
			report.subtotalsAtGroupFooter(group, subtotal);
			group.setPrintSubtotalsWhenExpression(
					DynamicReports.exp.printWhenGroupHasMoreThanOneRow(group));
		}
		report.groupBy(group);
	}

	/**
	 * This method will apply sub total to the column {@code Column.PRICE}
	 */
	@SuppressWarnings({ "rawtypes" })
	public void subTotalPrice() {
		AggregationSubtotalBuilder subtotal = getSubTotalBuilder(Column.PRICE);
		subtotal.setStyle(styleProvider.getBoldStyle());
		report.addSubtotalAtSummary(subtotalBuilder.text("Total", getColumn(Column.QTY)), subtotal);
	}

}
