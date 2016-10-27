package com.slabs.expense.tracker.reports.column;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.builder.component.ComponentBuilders;
import net.sf.dynamicreports.report.builder.component.VerticalListBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilders;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.exception.DRException;

public class MonthlyExpenseReport {

	public void buildReport() throws DRException {
		JasperReportBuilder report = ReportBuilder.getInstance().createJasperReportBuilder();
		StyleProvider sProvider = ReportBuilder.getInstance().getStyleProvider();
		ColumnProvider cProvider = ReportBuilder.getInstance().getColumnProvider();
		ComponentBuilders cBuilders = ReportBuilder.getInstance().getComponentBuilders();
		StyleBuilders sBuilders = ReportBuilder.getInstance().getStyleBuilders();

		report.title(getTitle(cBuilders, sBuilders, sProvider));
		report.show();

	}

	private ComponentBuilder<? extends Object, ? extends Object> getTitle(ComponentBuilders cBuilders, StyleBuilders sBuilders, StyleProvider sProvider) {
		DynamicReports dr = ReportBuilder.getInstance().getDynamicReports();
		StyleBuilder titleStyle = sProvider.getTitleStyle(14, "Cambria", true, false, HorizontalTextAlignment.CENTER);
		StyleBuilder titleStyle1 = sProvider.getStyle(10, "Cambria", true, false);

		VerticalListBuilder vLisTop = cBuilders.verticalList();
		VerticalListBuilder vList = cBuilders.verticalList();
		vList.add(cBuilders.text("Name : Shyam Natarajan").setStyle(titleStyle1), cBuilders.text("Email : shyamcse07@gmail.com").setStyle(titleStyle1),
				cBuilders.text("Mobile : 9894362480").setStyle(titleStyle1));

		VerticalListBuilder vListTitle = cBuilders.verticalList();
		vListTitle.add(cBuilders.text("Expense Tracker").setStyle(titleStyle), cBuilders.text("Monthly Report - October,2016").setStyle(titleStyle));

		vLisTop.add(vListTitle, cBuilders.verticalGap(10), sProvider.getFillerLine(dr.stl.pen1Point(), 10), vList.setFixedDimension(200, 50));

		return vLisTop;
	}

}
