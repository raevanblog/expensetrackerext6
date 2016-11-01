package com.slabs.expense.tracker.reports.provider;

import com.slabs.expense.tracker.reports.column.Column;
import com.slabs.expense.tracker.reports.column.DateFormatter;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.column.ValueColumnBuilder;

public class ColumnProvider {

	public ColumnProvider() {

	}

	@SuppressWarnings("unchecked")
	public TextColumnBuilder<? extends Object> getColumn(Column column) {
		return DynamicReports.col.column(column.titleName, column.mappingName, column.datatype);
	}

	@SuppressWarnings("unchecked")
	public ValueColumnBuilder getDateColumn(Column column, String dateFormat) {
		return DynamicReports.col.column(column.titleName, column.mappingName, column.datatype).setValueFormatter(new DateFormatter(dateFormat));
	}

	@SuppressWarnings("unchecked")
	public ValueColumnBuilder getDateColumn(Column column) {
		return DynamicReports.col.column(column.titleName, column.mappingName, column.datatype).setValueFormatter(new DateFormatter());
	}

	public TextColumnBuilder<? extends Object> getEmptyColumn() {
		return DynamicReports.col.emptyColumn();
	}

	public TextColumnBuilder<? extends Object> getRowNumberColumn(String title) {
		if (title == null) {
			return DynamicReports.col.reportRowNumberColumn().setFixedColumns(3);
		}

		return DynamicReports.col.reportRowNumberColumn(title).setFixedColumns(3);
	}

	public TextColumnBuilder<? extends Object> multiply(TextColumnBuilder<? extends Number> column1, TextColumnBuilder<? extends Number> column2, String title) {
		return column1.multiply(column2).setTitle(title);
	}

	public void groupBy(JasperReportBuilder reportBuilder, TextColumnBuilder<String> column) {
		reportBuilder.groupBy(column);
	}

}
