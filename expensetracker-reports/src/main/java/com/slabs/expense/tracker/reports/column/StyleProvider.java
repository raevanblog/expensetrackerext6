package com.slabs.expense.tracker.reports.column;

import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.component.FillerBuilder;

import java.awt.Color;

import org.springframework.core.annotation.AliasFor;

import net.sf.dynamicreports.report.builder.style.PenBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;

public class StyleProvider {

	private DynamicReports reports;

	public StyleProvider(DynamicReports reports) {
		this.reports = reports;
	}

	public StyleBuilder getStyle() {
		return reports.stl.style();
	}

	public StyleBuilder getStyle(Integer fontSize, String fontName, boolean bold, boolean italic) {
		return reports.stl.style().setFont(reports.stl.font(fontName, bold, italic, fontSize));
	}

	public StyleBuilder getStyle(HorizontalTextAlignment alignment) {
		return getStyle().setHorizontalTextAlignment(alignment);
	}

	public StyleBuilder getBoldStyle() {
		return reports.stl.style().bold();
	}

	public StyleBuilder getBoldStyle(HorizontalTextAlignment alignment) {
		return getBoldStyle().setHorizontalTextAlignment(alignment);
	}

	public StyleBuilder getColumnHeader(Integer fontSize, Color foreGroundColor, Color backGroundColor, boolean italic) {
		return getBoldStyle(HorizontalTextAlignment.CENTER).setFontSize(fontSize).setBackgroundColor(backGroundColor).setForegroundColor(foreGroundColor).setItalic(italic);
	}

	public StyleBuilder getTitleStyle(Integer fontSize, String fontName, boolean bold, boolean italic, HorizontalTextAlignment alignment) {
		return getStyle(alignment).setFont(reports.stl.font(fontName, bold, italic, fontSize));
	}

	public FillerBuilder getFillerLine(PenBuilder pen, Integer height) {
		return reports.cmp.filler().setStyle(getStyle().setTopBorder(pen)).setFixedHeight(height);
	}

}
