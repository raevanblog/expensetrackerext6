package com.slabs.expense.tracker.reports;

import java.awt.Color;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.slabs.expense.tracker.common.db.entity.UserInfo;
import com.slabs.expense.tracker.reports.builder.ReportBuilder;
import com.slabs.expense.tracker.reports.column.Column;
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

	public ExpenseTrackerReport(UserInfo userInfo, Month month, Integer year, CurrencyType currency) throws InstantiationException, IllegalAccessException {
		this.userInfo = userInfo;
		this.month = month;
		this.year = year;
		this.currency = (Currency) currency.getTypeClass().newInstance();
		this.report = ReportBuilder.getInstance().createJasperReportBuilder();
		this.report.setDefaultFont(ReportBuilder.getInstance().getDefaultFont());
	}

	protected void setDataSource(Collection<? extends Object> dataSource) {
		report.setDataSource(dataSource);
	}

	public void setDataSource(String sql, Connection connection) {
		report.setDataSource(sql, connection);
	}

	public void addPageNumber() {
		HorizontalListBuilder hList = cBuilders.horizontalFlowList();
		hList.add(getApplicationName(10), cBuilders.pageXslashY().setStyle(sProvider.getStyle(HorizontalTextAlignment.LEFT)));
		report.pageFooter(sProvider.getDefaultFillerLine(10), hList);
	}

	public TextFieldBuilder<String> getApplicationName(Integer fontSize) {
		TextFieldBuilder<String> appName = cBuilders.text(APPLICATION_NAME);
		StyleBuilder appNameStyle = sProvider.getStyle(fontSize).setHorizontalTextAlignment(HorizontalTextAlignment.LEFT);
		return appName.setStyle(appNameStyle);
	}

	@SuppressWarnings("rawtypes")
	public void addColumns(ValueColumnBuilder... columns) {
		for (ValueColumnBuilder c : columns) {
			this.columns.add(c);
		}
	}

	@SuppressWarnings("rawtypes")
	public ValueColumnBuilder getColumn(Column column) {
		for (ValueColumnBuilder c : columns) {
			if (c.getColumn().getName().equals(column.getMappingName())) {
				return c;
			}
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	public ValueColumnBuilder[] getAllColumns() {
		ValueColumnBuilder[] columns = new ValueColumnBuilder[this.columns.size()];
		return this.columns.toArray(columns);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public AggregationSubtotalBuilder getSubTotalBuilder(final Column column) {
		AggregationSubtotalBuilder subtotal = DynamicReports.sbt.sum(getColumn(column));
		subtotal.setLabel("Total : ").setLabelPosition(Position.LEFT);
		return subtotal;
	}

	public abstract void addTitle();

	public abstract void groupBy(Column column, boolean enableSubtotal);

}
