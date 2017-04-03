package com.slabs.expense.tracker.reports.builder;

import com.slabs.expense.tracker.reports.provider.ColumnProvider;
import com.slabs.expense.tracker.reports.provider.StyleProvider;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.chart.ChartBuilders;
import net.sf.dynamicreports.report.builder.component.ComponentBuilders;
import net.sf.dynamicreports.report.builder.grid.GridBuilders;
import net.sf.dynamicreports.report.builder.style.FontBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilders;
import net.sf.dynamicreports.report.builder.subtotal.SubtotalBuilders;

/**
 * {@link ReportBuilder} is a singleton class which provides support for
 * building report
 * 
 * @author Shyam Natarajan
 *
 */
public class ReportBuilder {

	private static ReportBuilder builder;

	private static final String DEFAULT_FONT = "Calibri";

	private static final int DEFAULT_FONT_SIZE = 11;

	private ReportBuilder() {

	}

	public static ReportBuilder getInstance() {
		if (builder == null) {
			synchronized (ReportBuilder.class) {
				if (builder == null) {
					synchronized (ReportBuilder.class) {
						builder = new ReportBuilder();
					}
				}
			}
		}
		return builder;
	}

	/**
	 * This method will provide the {@link JasperReportBuilder} for building
	 * report
	 * 
	 * @return {@link JasperReportBuilder}
	 */
	public JasperReportBuilder createJasperReportBuilder() {
		return DynamicReports.report();
	}

	/**
	 * This method will provide the {@link ComponentBuilders} for the report
	 * 
	 * @return {@link ComponentBuilders}
	 */
	public ComponentBuilders getComponentBuilders() {
		return DynamicReports.cmp;
	}

	/**
	 * This method will provide the {@link GridBuilders} for the report
	 * 
	 * @return
	 */
	public GridBuilders getGridBuilders() {
		return DynamicReports.grid;
	}

	/**
	 * This method will provide the {@link StyleBuilders} for the report
	 * 
	 * @return {@link StyleBuilders}
	 */
	public StyleBuilders getStyleBuilders() {
		return DynamicReports.stl;
	}

	/**
	 * This method will provide the {@link ChartBuilders} for the report
	 * 
	 * @return {@link ChartBuilders}
	 */
	public ChartBuilders getChartBuilders() {
		return DynamicReports.cht;
	}

	/**
	 * This method will provide the {@link SubtotalBuilders} for the report
	 * 
	 * @return {@link SubtotalBuilders}
	 */
	public SubtotalBuilders getSubtotalBuilders() {
		return DynamicReports.sbt;
	}

	/**
	 * This method will provide the {@link ColumnProvider} for the report
	 * 
	 * @return {@link ColumnProvider}
	 */
	public ColumnProvider getColumnProvider() {
		return new ColumnProvider();
	}

	/**
	 * This method will provide the {@link StyleProvider} for the report
	 * 
	 * @return {@link StyleProvider}
	 */
	public StyleProvider getStyleProvider() {
		return new StyleProvider(getDefaultFont());
	}

	/**
	 * This method will provide the {@link FontBuilder} for the report
	 * 
	 * @return {@link FontBuilder}
	 */
	public FontBuilder getDefaultFont() {
		return DynamicReports.stl.font().setFontName(DEFAULT_FONT).setFontSize(DEFAULT_FONT_SIZE);
	}

}
