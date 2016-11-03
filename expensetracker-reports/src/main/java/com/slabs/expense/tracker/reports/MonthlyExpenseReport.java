package com.slabs.expense.tracker.reports;

import java.awt.Color;
import java.math.BigDecimal;

import com.slabs.expense.tracker.common.db.entity.UserInfo;
import com.slabs.expense.tracker.reports.column.Column;
import com.slabs.expense.tracker.reports.column.data.type.Rupees;
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
import net.sf.dynamicreports.report.constant.Position;
import net.sf.dynamicreports.report.exception.DRException;

public class MonthlyExpenseReport extends ExpenseTrackerReport {

	public MonthlyExpenseReport(UserInfo userInfo, Month month, Integer year) {
		super(userInfo, month, year);
		addColumns();
	}

	public JasperReportBuilder buildReport() throws DRException {
		addTitle();
		addColumnsToReport();
		addPageNumber();
		return report;
	}

	@Override
	public void addTitle() {

		HorizontalListBuilder titleContainer = cBuilders.horizontalList();
		TextFieldBuilder<String> reportTitle = cBuilders.text("Monthly Report").setStyle(sProvider.getBoldStyle());
		TextFieldBuilder<String> monthAnYear = cBuilders.text(month.getName() + "," + year).setStyle(sProvider.getBoldStyle(HorizontalTextAlignment.RIGHT));
		titleContainer.add(reportTitle, monthAnYear).newRow(2);

		report.title(titleContainer, cBuilders.verticalGap(5), sProvider.getDefaultFillerLine(10));
	}

	@SuppressWarnings("rawtypes")
	public void addColumns() {
		StyleBuilder rightAligned = sProvider.getStyle(HorizontalTextAlignment.RIGHT);
		report.setColumnTitleStyle(sProvider.getColumnHeader(Color.BLACK, DEFAULT_HEADER_COLOR, false));

		TextColumnBuilder<Integer> rowNum = cProvider.getRowNumberColumn("S.No");
		ValueColumnBuilder expDate = cProvider.getDateColumn(Column.EXPDATE).setFixedColumns(6);
		TextColumnBuilder<String> expenseType = cProvider.getColumn(Column.EXPTYPE, String.class).setFixedColumns(10);
		;
		TextColumnBuilder<String> expenseCategory = cProvider.getColumn(Column.CATEGORY, String.class);
		TextColumnBuilder<BigDecimal> qty = cProvider.getColumn(Column.QTY, BigDecimal.class).setStyle(rightAligned).setFixedColumns(5);
		TextColumnBuilder<BigDecimal> price = cProvider.getColumn(Column.PRICE, BigDecimal.class).setStyle(rightAligned).setDataType(new Rupees());
		TextColumnBuilder<BigDecimal> pricePerUnit = cProvider.getColumn(Column.PRICEPERUNIT, BigDecimal.class).setStyle(rightAligned);

		addColumns(rowNum, expDate, expenseType, expenseCategory, qty, price, pricePerUnit);
	}

	public void addColumnsToReport() {

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

	@SuppressWarnings({ "rawtypes" })
	public void subTotalPrice() {
		AggregationSubtotalBuilder subtotal = getSubTotalBuilder(Column.PRICE);
		report.addSubtotalAtSummary(subtotal);
	}

}
