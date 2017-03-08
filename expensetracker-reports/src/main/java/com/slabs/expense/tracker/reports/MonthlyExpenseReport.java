package com.slabs.expense.tracker.reports;

import java.awt.Color;
import java.math.BigDecimal;

import com.slabs.expense.tracker.common.database.column.Column;
import com.slabs.expense.tracker.common.database.entity.UserInfo;
import com.slabs.expense.tracker.reports.column.data.type.CurrencyType;
import com.slabs.expense.tracker.reports.expression.ExpenseImageResolver;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.column.ValueColumnBuilder;
import net.sf.dynamicreports.report.builder.component.HorizontalListBuilder;
import net.sf.dynamicreports.report.builder.component.ImageBuilder;
import net.sf.dynamicreports.report.builder.component.TextFieldBuilder;
import net.sf.dynamicreports.report.builder.group.CustomGroupBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.builder.subtotal.AggregationSubtotalBuilder;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
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

	public MonthlyExpenseReport(UserInfo userInfo, Month month, Integer year) throws InstantiationException, IllegalAccessException {
		this(userInfo, month, year, CurrencyType.DOLLAR);
	}

	public MonthlyExpenseReport(UserInfo userInfo, Month month, Integer year, CurrencyType currency)
			throws InstantiationException, IllegalAccessException {
		super(userInfo, month, year, currency);
		addColumns();		
	}

	@Override
	public JasperReportBuilder buildReport() throws DRException {
		addTitle();
		addColumnsToReport();		
		return report;
	}

	private void addTitle() {

		HorizontalListBuilder titleContainer = cBuilders.horizontalList();
		TextFieldBuilder<String> reportTitle = cBuilders.text("Monthly Report").setStyle(sProvider.getBoldStyle());
		TextFieldBuilder<String> monthAnYear = cBuilders.text(month.getName() + "," + year)
				.setStyle(sProvider.getBoldStyle(HorizontalTextAlignment.RIGHT));
		titleContainer.add(reportTitle, monthAnYear).newRow(2);

		report.title(titleContainer, cBuilders.verticalGap(5), sProvider.getDefaultFillerLine(10));
	}

	@SuppressWarnings("rawtypes")
	private void addColumns() {
		StyleBuilder centerAligned = sProvider.getStyle(HorizontalTextAlignment.CENTER);		
		report.setColumnTitleStyle(sProvider.getColumnHeader(Color.BLACK, DEFAULT_HEADER_COLOR, false));
		report.setColumnStyle(sProvider.getBorder(centerAligned, 1.0f));

		TextColumnBuilder<Integer> rowNum = cProvider.getRowNumberColumn("S.No");
		ValueColumnBuilder expDate = cProvider.getDateColumn(Column.EXPDATE);
		TextColumnBuilder<String> itemName = cProvider.getColumn(Column.ITEMNAME, String.class);
		TextColumnBuilder<String> expenseType = cProvider.getColumn(Column.EXPTYPE, String.class).setFixedColumns(10);
		TextColumnBuilder<String> expenseCategory = cProvider.getColumn(Column.CATEGORY, String.class);
		TextColumnBuilder<BigDecimal> qty = cProvider.getColumn(Column.QTY, BigDecimal.class).setFixedColumns(5);
		TextColumnBuilder<Double> price = cProvider.getColumn(Column.PRICE, Double.class).setDataType(this.currency);
		TextColumnBuilder<Double> pricePerUnit = cProvider.getColumn(Column.PRICEPERUNIT, Double.class).setDataType(this.currency);
					
		addColumns(rowNum, expDate, itemName, expenseType, expenseCategory, qty, price, pricePerUnit);
	}
	
	private void addColumnsToReport() {	
		report.columns(getAllColumns());		
		report.highlightDetailOddRows();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void groupBy(Column column, boolean subtotalPrice) {
		StyleBuilder grpTitleStyle = sProvider.getStyle().setBottomPadding(2).setTopPadding(5);
		StyleBuilder imageStyle = sProvider.getStyle().setBottomPadding(2);

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

	/**
	 * This method will apply sub total to the column {@code Column.PRICE}
	 */
	@SuppressWarnings({ "rawtypes" })
	public void subTotalPrice() {
		AggregationSubtotalBuilder subtotal = getSubTotalBuilder(Column.PRICE);
		report.addSubtotalAtSummary(subtotal);
	}

}
