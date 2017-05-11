package com.slabs.expensetracker.reports.builder;

import java.awt.Color;
import java.io.Serializable;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.renderer.category.BarRenderer;

import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.dynamicreports.report.definition.chart.DRIChartCustomizer;

public class BarChartCustomizer implements DRIChartCustomizer, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public void customize(JFreeChart chart, ReportParameters reportParameters) {
		BarRenderer renderer = (BarRenderer) chart.getCategoryPlot().getRenderer();
		renderer.setShadowPaint(Color.LIGHT_GRAY);
		renderer.setShadowVisible(Boolean.TRUE);		
		CategoryAxis domainAxis = chart.getCategoryPlot().getDomainAxis();
		domainAxis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 6.0));
	}

}
