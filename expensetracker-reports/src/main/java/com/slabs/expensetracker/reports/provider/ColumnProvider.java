package com.slabs.expensetracker.reports.provider;

import com.slabs.expensetracker.common.database.column.Column;
import com.slabs.expensetracker.reports.column.DateFormatter;

import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.column.ValueColumnBuilder;

/**
 * {@link ColumnProvider} will provide the {@link TextColumnBuilder} to use in
 * the report based on the given {@link Column}
 * 
 * @author Shyam Natarajan
 *
 */
public class ColumnProvider {

	public ColumnProvider() {

	}

	/**
	 * 
	 * @param column
	 *            {@link Column}
	 * @return {@link TextColumnBuilder}
	 */
	@SuppressWarnings("unchecked")
	public TextColumnBuilder<? extends Object> getColumn(Column column) {
		return DynamicReports.col.column(column.titleName, column.mappingName, column.datatype);
	}

	/**
	 * 
	 * @param column
	 *            {@link Column}
	 * @param cls
	 *            - {@link Class} to which {@link TextColumnBuilder} has to be
	 *            casted
	 * @return {@link TextColumnBuilder}
	 */
	@SuppressWarnings("unchecked")
	public <T extends Object> TextColumnBuilder<T> getColumn(Column column, Class<T> cls) {
		return (TextColumnBuilder<T>) DynamicReports.col.column(column.titleName, column.mappingName, column.datatype);
	}

	/**
	 * 
	 * @param column
	 *            {@link Column} should be column with {@link java.sql.Date}
	 * @param dateFormat
	 *            {@link String} - date format
	 * @return {@link TextColumnBuilder}
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ValueColumnBuilder getDateColumn(Column column, String dateFormat) {
		return DynamicReports.col.column(column.titleName, column.mappingName, column.datatype).setValueFormatter(new DateFormatter(dateFormat));
	}

	/**
	 * 
	 * @param column
	 *            {@link Column} should be column with {@link java.sql.Date}
	 * @return {@link TextColumnBuilder}
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ValueColumnBuilder getDateColumn(Column column) {
		return DynamicReports.col.column(column.titleName, column.mappingName, column.datatype).setValueFormatter(new DateFormatter());
	}

	/**
	 * Returns empty column {@link TextColumnBuilder}
	 * 
	 * @return {@link TextColumnBuilder}
	 */
	public TextColumnBuilder<? extends Object> getEmptyColumn() {
		return DynamicReports.col.emptyColumn();
	}

	/**
	 * Returns a row number column for the report
	 * 
	 * @param title
	 *            {@link String} - Title for the row number column
	 * @return {@link TextColumnBuilder}
	 */
	public TextColumnBuilder<Integer> getRowNumberColumn(String title) {
		if (title == null) {
			return DynamicReports.col.reportRowNumberColumn().setFixedColumns(3);
		}

		return DynamicReports.col.reportRowNumberColumn(title).setFixedColumns(3);
	}

	/**
	 * This method will multiply column1 and column2 and the result will be
	 * stored in the result column. Column1 and Column2 should be a Number type
	 * 
	 * @param column1
	 *            {@link TextColumnBuilder} - Column 1
	 * @param column2
	 *            {@link TextColumnBuilder} - Column 2
	 * @param title
	 *            {@link String} - Title for the result column
	 * @return {@link TextColumnBuilder}
	 */
	public TextColumnBuilder<? extends Object> multiply(TextColumnBuilder<? extends Number> column1, TextColumnBuilder<? extends Number> column2,
			String title) {
		return column1.multiply(column2).setTitle(title);
	}

}
