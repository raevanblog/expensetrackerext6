package com.slabs.expense.tracker.reports.provider;

import java.awt.Color;

import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.component.FillerBuilder;
import net.sf.dynamicreports.report.builder.style.FontBuilder;
import net.sf.dynamicreports.report.builder.style.PenBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;

public class StyleProvider {

	private FontBuilder defaultFont;

	public StyleProvider(FontBuilder defaultFont) {
		this.defaultFont = defaultFont;
	}

	public StyleBuilder getStyle() {
		return getStyle(defaultFont.getFont().getFontName(), defaultFont.getFont().getFontSize(), false, false);
	}	

	public StyleBuilder getStyle(Integer fontSize) {
		return getStyle().setFontSize(fontSize);
	}

	public StyleBuilder getStyle(String fontName, Integer fontSize, boolean bold, boolean italic) {
		return DynamicReports.stl.style().setFont(DynamicReports.stl.font(fontName, bold, italic, fontSize));
	}

	public StyleBuilder getStyle(HorizontalTextAlignment alignment) {
		return getStyle().setHorizontalTextAlignment(alignment);
	}

	public StyleBuilder getBoldStyle(String fontName, Integer fontSize, boolean italic) {
		return getStyle(fontName, fontSize, true, italic).bold();
	}

	public StyleBuilder getBoldStyle(String fontName, Integer fontSize, boolean italic, HorizontalTextAlignment alignment) {
		return getStyle(fontName, fontSize, true, italic).bold();
	}

	public StyleBuilder getBoldStyle() {
		return getStyle().bold();
	}

	public StyleBuilder getBoldStyle(HorizontalTextAlignment alignment) {
		return getBoldStyle().setHorizontalTextAlignment(alignment);
	}

	public StyleBuilder getColumnHeader(String fontName, Integer fontSize, Color foreGroundColor, Color backGroundColor, boolean italic) {
		return getBoldStyle(fontName, fontSize, italic, HorizontalTextAlignment.CENTER).setBackgroundColor(backGroundColor).setForegroundColor(foreGroundColor)
				.setBorder(DynamicReports.stl.pen1Point());
	}

	public StyleBuilder getColumnHeader(Color foreGroundColor, Color backGroundColor, boolean italic) {
		return getBoldStyle(HorizontalTextAlignment.CENTER).setBackgroundColor(backGroundColor).setForegroundColor(foreGroundColor).setItalic(italic).setBorder(DynamicReports.stl.pen1Point());
	}

	public StyleBuilder getTitleStyle(String fontName, Integer fontSize, boolean bold, boolean italic, HorizontalTextAlignment alignment) {
		return getStyle(fontName, fontSize, bold, italic).setHorizontalTextAlignment(alignment);
	}

	public StyleBuilder getTitleStyle(boolean bold, boolean italic, HorizontalTextAlignment alignment) {
		return getStyle().setFontSize(14).setBold(bold).setItalic(italic).setHorizontalTextAlignment(alignment);
	}

	public FillerBuilder getFiller() {
		return DynamicReports.cmp.filler();
	}

	public FillerBuilder getDefaultFillerLine(Integer height) {
		return getFiller().setStyle(getStyle().setTopBorder(DynamicReports.stl.pen1Point())).setFixedHeight(height);
	}

	public FillerBuilder getFillerLine(PenBuilder pen, Integer height) {		
		return getFiller().setStyle(getStyle().setTopBorder(pen)).setFixedHeight(height);
	}

	public FillerBuilder getFillerLine(PenBuilder pen, Integer height, Integer width) {
		return getFillerLine(pen, height).setFixedWidth(width);
	}

	public FillerBuilder getEmptyFiller(Integer height) {
		return getFiller().setFixedHeight(height);
	}

	public FillerBuilder getEmptyFiller(Integer height, Integer width) {
		return getEmptyFiller(height).setFixedWidth(width);
	}

}
