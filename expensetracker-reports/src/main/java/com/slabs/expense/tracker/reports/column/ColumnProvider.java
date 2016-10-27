package com.slabs.expense.tracker.reports.column;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;

public class ColumnProvider {

	private DynamicReports report;

	public ColumnProvider(DynamicReports report) {
		this.report = report;
	}

	public TextColumnBuilder<? extends Object> getColumn(Column column) {
		return report.col.column(column.columnName, column.mappingName, column.datatype);
	}

	public TextColumnBuilder<? extends Object> getEmptyColumn() {
		return report.col.emptyColumn();
	}

	public TextColumnBuilder<? extends Object> getRowNumberColumn(String title) {
		if (title == null) {
			return report.col.reportRowNumberColumn();
		}
		return report.col.reportRowNumberColumn(title);
	}

	public TextColumnBuilder<? extends Object> multiply(TextColumnBuilder<? extends Number> column1, TextColumnBuilder<? extends Number> column2, String title) {
		return column1.multiply(column2).setTitle(title);
	}

	public void groupBy(JasperReportBuilder reportBuilder, TextColumnBuilder<String> column) {
		reportBuilder.groupBy(column);
	}

}
