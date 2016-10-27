package com.slabs.expense.tracker.reports.column;

import static net.sf.dynamicreports.report.builder.DynamicReports.stl;

import java.awt.Color;

import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;

public class StyleProvider {

	public static synchronized StyleBuilder getBoldStyle() {
		return stl.style().bold();
	}

	public static synchronized StyleBuilder getBoldStyle(HorizontalTextAlignment alignment) {
		return stl.style(getBoldStyle()).setHorizontalTextAlignment(alignment);
	}

	public static synchronized StyleBuilder getColumnHeader(Integer fontSize, Color foreGroundColor, Color backGroundColor, boolean italic) {
		return stl.style(getBoldStyle(HorizontalTextAlignment.CENTER)).setFontSize(fontSize).setBackgroundColor(backGroundColor)
				.setForegroundColor(foreGroundColor).setItalic(italic);
	}

}
