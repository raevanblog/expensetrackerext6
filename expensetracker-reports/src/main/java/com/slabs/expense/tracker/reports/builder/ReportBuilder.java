package com.slabs.expense.tracker.reports.builder;

import com.slabs.expense.tracker.reports.provider.ColumnProvider;
import com.slabs.expense.tracker.reports.provider.StyleProvider;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.component.ComponentBuilders;
import net.sf.dynamicreports.report.builder.style.FontBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilders;

public class ReportBuilder {

	private static ReportBuilder builder;

	private static final String DEFAULT_FONT = "Times New Roman";

	private static final int DEFAULT_FONT_SIZE = 10;

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

	public JasperReportBuilder createJasperReportBuilder() {
		return DynamicReports.report();
	}

	public ComponentBuilders getComponentBuilders() {
		return DynamicReports.cmp;
	}

	public StyleBuilders getStyleBuilders() {
		return DynamicReports.stl;
	}

	public ColumnProvider getColumnProvider() {
		return new ColumnProvider();
	}

	public StyleProvider getStyleProvider() {
		return new StyleProvider(getDefaultFont());
	}

	public FontBuilder getDefaultFont() {
		return DynamicReports.stl.font().setFontName(DEFAULT_FONT).setFontSize(DEFAULT_FONT_SIZE);
	}

}
