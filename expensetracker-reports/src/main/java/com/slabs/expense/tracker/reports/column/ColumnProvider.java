package com.slabs.expense.tracker.reports.column;

import net.sf.dynamicreports.report.builder.DynamicReports;

public class ColumnProvider {

	private DynamicReports report;

	public ColumnProvider(DynamicReports report) {
		this.report = report;
	}

	public Object getColumn(Column column) {
		return report.col.column(column.columnName, column.mappingName, column.datatype);
	}

	public Object getEmptyColumn() {
		return report.col.emptyColumn();
	}

	public Object getRowNumberColumn(String title) {
		if (title == null) {
			return report.col.reportRowNumberColumn();
		}
		return report.col.reportRowNumberColumn(title);
	}

}
