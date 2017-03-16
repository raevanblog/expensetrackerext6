package com.slabs.expense.tracker.reports.provider;

import java.awt.Color;

import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.component.FillerBuilder;
import net.sf.dynamicreports.report.builder.style.FontBuilder;
import net.sf.dynamicreports.report.builder.style.PenBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.constant.LineStyle;

/**
 * {@link StyleProvider} provides style for the report
 * 
 * @author Shyam Natarajan
 *
 */
public class StyleProvider {

	private FontBuilder defaultFont;

	public StyleProvider(FontBuilder defaultFont) {
		this.defaultFont = defaultFont;
	}

	/**
	 * 
	 * @return {@link StyleBuilder}
	 */
	public StyleBuilder getStyle() {
		return getStyle(defaultFont.getFont().getFontName(), defaultFont.getFont().getFontSize(),
				false, false);
	}

	/**
	 * 
	 * @param fontSize
	 *            {@link Integer} - font size
	 * @return {@link StyleBuilder}
	 */
	public StyleBuilder getStyle(Integer fontSize) {
		return getStyle().setFontSize(fontSize);
	}

	/**
	 * 
	 * @param fontName
	 *            {@link String}
	 * @param fontSize
	 *            {@link Integer}
	 * @param bold
	 *            {@link Boolean}
	 * @param italic
	 *            {@link Boolean}
	 * @return {@link StyleBuilder}
	 */
	public StyleBuilder getStyle(String fontName, Integer fontSize, boolean bold, boolean italic) {
		return DynamicReports.stl.style()
				.setFont(DynamicReports.stl.font(fontName, bold, italic, fontSize));
	}

	/**
	 * 
	 * @param alignment
	 *            {@link HorizontalTextAlignment}
	 * @return {@link StyleBuilder}
	 */
	public StyleBuilder getStyle(HorizontalTextAlignment alignment) {
		return getStyle().setHorizontalTextAlignment(alignment);
	}

	/**
	 * 
	 * @param fontName
	 *            {@link String}
	 * @param fontSize
	 *            {@link Integer}
	 * @param italic
	 *            {@link Boolean}
	 * @return {@link StyleBuilder}
	 */
	public StyleBuilder getBoldStyle(String fontName, Integer fontSize, boolean italic) {
		return getStyle(fontName, fontSize, true, italic).bold();
	}

	/**
	 * 
	 * @param fontName
	 *            {@link String}
	 * @param fontSize
	 *            {@link Integer}
	 * @param italic
	 *            {@link Boolean}
	 * @param alignment
	 *            {@link HorizontalTextAlignment}
	 * @return
	 */
	public StyleBuilder getBoldStyle(String fontName, Integer fontSize, boolean italic,
			HorizontalTextAlignment alignment) {
		return getStyle(fontName, fontSize, true, italic).bold();
	}

	/**
	 * 
	 * @return {@link StyleBuilder}
	 */
	public StyleBuilder getBoldStyle() {
		return getStyle().bold();
	}

	/**
	 * 
	 * @param fontSize
	 *            {@link Integer} - Font size
	 * @return {@link StyleBuilder}
	 */
	public StyleBuilder getBoldStyle(Integer fontSize) {
		return getBoldStyle().setFontSize(fontSize);
	}

	/**
	 * 
	 * @param fontName
	 *            {@link String} - Font name
	 * @return {@link StyleBuilder}
	 */
	public StyleBuilder getBoldStyle(String fontName) {
		return getBoldStyle().setFontName(fontName);
	}

	/**
	 * 
	 * @param fontName
	 *            {@link String} - Font name
	 * @param fontSize
	 *            {@link Integer} - Font size
	 * @return {@link StyleBuilder}
	 */
	public StyleBuilder getBoldStyle(String fontName, Integer fontSize) {
		return getBoldStyle(fontName).setFontSize(fontSize);
	}

	/**
	 * 
	 * @param alignment
	 *            {@link HorizontalTextAlignment}
	 * @return {@link StyleBuilder}
	 */
	public StyleBuilder getBoldStyle(HorizontalTextAlignment alignment) {
		return getBoldStyle().setHorizontalTextAlignment(alignment);
	}

	/**
	 * 
	 * @param fontName
	 *            {@link String}
	 * @param fontSize
	 *            {@link Integer}
	 * @param foreGroundColor
	 *            {@link Color}
	 * @param backGroundColor
	 *            {@link Color}
	 * @param italic
	 *            {@link Boolean}
	 * @return {@link StyleBuilder}
	 */
	public StyleBuilder getColumnHeader(String fontName, Integer fontSize, Color foreGroundColor,
			Color backGroundColor, boolean italic) {
		return getBoldStyle(fontName, fontSize, italic, HorizontalTextAlignment.CENTER)
				.setBackgroundColor(backGroundColor).setForegroundColor(foreGroundColor)
				.setBorder(DynamicReports.stl.pen1Point());
	}

	/**
	 * 
	 * @param foreGroundColor
	 *            {@link Color}
	 * @param backGroundColor
	 *            {@link Color}
	 * @param italic
	 *            {@link Boolean}
	 * @return {@link StyleBuilder}
	 */
	public StyleBuilder getColumnHeader(Color foreGroundColor, Color backGroundColor,
			boolean italic) {
		return getBoldStyle(HorizontalTextAlignment.CENTER).setBackgroundColor(backGroundColor)
				.setForegroundColor(foreGroundColor).setItalic(italic)
				.setBorder(DynamicReports.stl.pen1Point());
	}

	/**
	 * 
	 * @param fontName
	 *            {@link String}
	 * @param fontSize
	 *            {@link Integer}
	 * @param bold
	 *            {@link Boolean}
	 * @param italic
	 *            {@link Boolean}
	 * @param alignment
	 *            {@link HorizontalTextAlignment}
	 * @return {@link StyleBuilder}
	 */
	public StyleBuilder getTitleStyle(String fontName, Integer fontSize, boolean bold,
			boolean italic, HorizontalTextAlignment alignment) {
		return getStyle(fontName, fontSize, bold, italic).setHorizontalTextAlignment(alignment);
	}

	/**
	 * 
	 * @param bold
	 *            {@link Boolean}
	 * @param italic
	 *            {@link Boolean}
	 * @param alignment
	 *            {@link HorizontalTextAlignment}
	 * @return {@link StyleBuilder}
	 */
	public StyleBuilder getTitleStyle(boolean bold, boolean italic,
			HorizontalTextAlignment alignment) {
		return getStyle().setFontSize(14).setBold(bold).setItalic(italic)
				.setHorizontalTextAlignment(alignment);
	}

	/**
	 * 
	 * @return {@link StyleBuilder}
	 */
	public StyleBuilder getDefaultBorder() {
		return getStyle().setBorder(DynamicReports.stl.pen1Point());
	}

	/**
	 * 
	 * @param builder
	 *            {@link StyleBuilder}
	 * @param penPoint
	 *            {@link Float} - Border width
	 * @return {@link StyleBuilder}
	 */
	public StyleBuilder getBorder(StyleBuilder builder, Float penPoint) {
		return builder.setBorder(DynamicReports.stl.pen(penPoint, LineStyle.SOLID));
	}

	/**
	 * 
	 * @param builder
	 *            {@link StyleBuilder}
	 * @param penPoint
	 *            {@link Float} - Border width
	 * @return {@link StyleBuilder}
	 */
	public StyleBuilder getDottedBorder(StyleBuilder builder, Float penPoint) {
		return builder.setBorder(DynamicReports.stl.pen(penPoint, LineStyle.DOTTED));
	}

	/**
	 * 
	 * @param builder
	 *            {@link StyleBuilder}
	 * @param penPoint
	 *            {@link Float} - Border width
	 * @return {@link StyleBuilder}
	 */
	public StyleBuilder getDashedBorder(StyleBuilder builder, Float penPoint) {
		return builder.setBorder(DynamicReports.stl.pen(penPoint, LineStyle.DASHED));
	}

	/**
	 * 
	 * @param builder
	 *            {@link StyleBuilder}
	 * @param penPoint
	 *            {@link Float} - Border width
	 * @return {@link StyleBuilder}
	 */
	public StyleBuilder getDoubleBorder(StyleBuilder builder, Float penPoint) {
		return builder.setBorder(DynamicReports.stl.pen(penPoint, LineStyle.DOUBLE));
	}

	/**
	 * 
	 * @return {@link FillerBuilder}
	 */
	public FillerBuilder getFiller() {
		return DynamicReports.cmp.filler();
	}

	/**
	 * 
	 * @param height
	 *            {@link Integer}
	 * @return {@link FillerBuilder}
	 */
	public FillerBuilder getDefaultFillerLine(Integer height) {
		return getFiller().setStyle(getStyle().setTopBorder(DynamicReports.stl.pen1Point()))
				.setFixedHeight(height);
	}

	/**
	 * 
	 * @param pen
	 *            {@link PenBuilder}
	 * @param height
	 *            {@link Integer}
	 * @return {@link FillerBuilder}
	 */
	public FillerBuilder getFillerLine(PenBuilder pen, Integer height) {
		return getFiller().setStyle(getStyle().setTopBorder(pen)).setFixedHeight(height);
	}

	/**
	 * 
	 * @param pen
	 *            {@link PenBuilder}
	 * @param height
	 *            {@link Integer}
	 * @param width
	 *            {@link Integer}
	 * @return {@link FillerBuilder}
	 */
	public FillerBuilder getFillerLine(PenBuilder pen, Integer height, Integer width) {
		return getFillerLine(pen, height).setFixedWidth(width);
	}

	/**
	 * 
	 * @param height
	 *            {@link Integer}
	 * @return {@link FillerBuilder}
	 */
	public FillerBuilder getEmptyFiller(Integer height) {
		return getFiller().setFixedHeight(height);
	}

	/**
	 * 
	 * @param height
	 *            {@link Integer}
	 * @param width
	 *            {@link Integer}
	 * @return {@link FillerBuilder}
	 */
	public FillerBuilder getEmptyFiller(Integer height, Integer width) {
		return getEmptyFiller(height).setFixedWidth(width);
	}
	
}
