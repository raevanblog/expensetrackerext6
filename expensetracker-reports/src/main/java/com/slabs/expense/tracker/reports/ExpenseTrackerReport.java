package com.slabs.expense.tracker.reports;

import java.awt.Color;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.slabs.expense.tracker.common.database.column.Column;
import com.slabs.expense.tracker.common.database.entity.UserInfo;
import com.slabs.expense.tracker.reports.builder.ReportBuilder;
import com.slabs.expense.tracker.reports.column.data.type.Currency;
import com.slabs.expense.tracker.reports.column.data.type.CurrencyType;
import com.slabs.expense.tracker.reports.provider.ColumnProvider;
import com.slabs.expense.tracker.reports.provider.StyleProvider;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.column.ValueColumnBuilder;
import net.sf.dynamicreports.report.builder.component.ComponentBuilders;
import net.sf.dynamicreports.report.builder.component.HorizontalListBuilder;
import net.sf.dynamicreports.report.builder.component.TextFieldBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilders;
import net.sf.dynamicreports.report.builder.subtotal.AggregationSubtotalBuilder;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.constant.Position;
import net.sf.dynamicreports.report.exception.DRException;

/**
 * {@link ExpenseTrackerReport} is an abstract class which provides support for
 * building report
 * 
 * @author Shyam Natarajan
 *
 */
public abstract class ExpenseTrackerReport {

	protected ReportBuilder builder = ReportBuilder.getInstance();

	protected ColumnProvider cProvider = builder.getColumnProvider();

	protected ComponentBuilders cBuilders = builder.getComponentBuilders();

	protected StyleProvider sProvider = builder.getStyleProvider();

	protected StyleBuilders sBuilders = builder.getStyleBuilders();

	protected JasperReportBuilder report;

	@SuppressWarnings("rawtypes")
	protected List<ValueColumnBuilder> columns = new ArrayList<ValueColumnBuilder>();

	protected static final Color DEFAULT_HEADER_COLOR = new Color(127, 217, 244);

	private static final String APPLICATION_NAME = "Expense Tracker";

	protected UserInfo userInfo;

	protected Integer year;

	protected Month month;

	protected Currency currency;

	public ExpenseTrackerReport(UserInfo userInfo, Month month, Integer year, CurrencyType currency)
			throws InstantiationException, IllegalAccessException {
		this.userInfo = userInfo;
		this.month = month;
		this.year = year;
		this.currency = (Currency) currency.getTypeClass().newInstance();
		this.report = ReportBuilder.getInstance().createJasperReportBuilder();
		this.report.setDefaultFont(ReportBuilder.getInstance().getDefaultFont());
	}

	/**
	 * 
	 * @param dataSource
	 *            {@link Collection}
	 */
	public void setDataSource(Collection<? extends Object> dataSource) {
		report.setDataSource(dataSource);
	}

	/**
	 * This method will take SQL string and {@link Connection} and the
	 * {@link JasperReportBuilder} will make use of this to get the data
	 * 
	 * @param sql
	 *            {@link String} - SQL string
	 * @param connection
	 *            {@link Connection} - SQL connection
	 */
	public void setDataSource(String sql, Connection connection) {
		report.setDataSource(sql, connection);
	}

	/**
	 * This method will add the page number to the report
	 * 
	 */
	public void addPageNumber() {
		HorizontalListBuilder hList = cBuilders.horizontalFlowList();
		hList.add(getApplicationName(10), cBuilders.pageXslashY().setStyle(sProvider.getStyle(HorizontalTextAlignment.LEFT)));
		report.pageFooter(sProvider.getDefaultFillerLine(10), hList);
	}

	/**
	 * This method will return the application name using the font size
	 * 
	 * @param fontSize
	 *            - Size of the font
	 * @return {@link TextFieldBuilder}
	 */
	public TextFieldBuilder<String> getApplicationName(Integer fontSize) {
		TextFieldBuilder<String> appName = cBuilders.text(APPLICATION_NAME);
		StyleBuilder appNameStyle = sProvider.getStyle(fontSize).setHorizontalTextAlignment(HorizontalTextAlignment.LEFT);
		return appName.setStyle(appNameStyle);
	}

	/**
	 * This method will add the columns to the report
	 * 
	 * @param columns
	 *            {@link ValueColumnBuilder} Array of column in the report
	 */
	@SuppressWarnings("rawtypes")
	public void addColumns(ValueColumnBuilder... columns) {
		for (ValueColumnBuilder c : columns) {
			this.columns.add(c);
		}
	}

	/**
	 * This method will return {@link ValueColumnBuilder} for the given
	 * {@link Column}
	 * 
	 * @param column
	 *            {@link Column} - Column for which {@link ValueColumnBuilder}
	 *            will be returned
	 * @return {@link ValueColumnBuilder}
	 */
	@SuppressWarnings("rawtypes")
	public ValueColumnBuilder getColumn(Column column) {
		for (ValueColumnBuilder c : columns) {
			if (c.getColumn().getName().equals(column.getMappingName())) {
				return c;
			}
		}
		return null;
	}

	/**
	 * This method will return all the columns added to the report
	 * 
	 * @return {@link ValueColumnBuilder} - Array of {@link ValueColumnBuilder}
	 */
	@SuppressWarnings("rawtypes")
	public ValueColumnBuilder[] getAllColumns() {
		ValueColumnBuilder[] columns = new ValueColumnBuilder[this.columns.size()];
		return this.columns.toArray(columns);
	}

	/**
	 * This method will apply subtotal to the specified {@link Column}
	 * 
	 * @param column
	 *            {@link Column} - Column for which subtotal to be done
	 * @return {@link AggregationSubtotalBuilder}
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public AggregationSubtotalBuilder getSubTotalBuilder(final Column column) {
		AggregationSubtotalBuilder subtotal = DynamicReports.sbt.sum(getColumn(column));
		subtotal.setLabel("Total : ").setLabelPosition(Position.LEFT);
		return subtotal;
	}

	/**
	 * This method will build the report
	 * 
	 * @return
	 * @throws DRException
	 */
	public abstract JasperReportBuilder buildReport() throws DRException;

	/**
	 * 
	 * This method will apply groupBy for the given {@link Column}
	 * 
	 * @param column
	 *            {@link Column} - Column for which groupBy has to be applied
	 * @param enableSubtotal
	 *            {@link Boolean} - True to apply subtotal for the group.
	 *            Default is false
	 */
	public abstract void groupBy(Column column, boolean enableSubtotal);

}
