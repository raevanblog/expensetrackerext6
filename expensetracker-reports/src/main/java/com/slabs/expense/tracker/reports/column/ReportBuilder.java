package com.slabs.expense.tracker.reports.column;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.component.ComponentBuilders;
import net.sf.dynamicreports.report.builder.style.StyleBuilders;

public class ReportBuilder {

	private static DynamicReports reports = new DynamicReports();

	private static ReportBuilder builder;

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

	public DynamicReports getDynamicReports() {
		return reports;
	}

	public JasperReportBuilder createJasperReportBuilder() {
		return reports.report();
	}

	public ComponentBuilders getComponentBuilders() {
		return reports.cmp;
	}

	public StyleBuilders getStyleBuilders() {
		return reports.stl;
	}

	public ColumnProvider getColumnProvider() {
		return new ColumnProvider(reports);
	}

	public StyleProvider getStyleProvider() {
		return new StyleProvider(reports);
	}

}
