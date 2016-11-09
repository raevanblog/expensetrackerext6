package com.slabs.expense.tracker.reports.provider;

import java.awt.Color;

import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.component.FillerBuilder;
import net.sf.dynamicreports.report.builder.style.FontBuilder;
import net.sf.dynamicreports.report.builder.style.PenBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;

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
		return getStyle(defaultFont.getFont().getFontName(), defaultFont.getFont().getFontSize(), false, false);
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
		return DynamicReports.stl.style().setFont(DynamicReports.stl.font(fontName, bold, italic, fontSize));
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
	public StyleBuilder getBoldStyle(String fontName, Integer fontSize, boolean italic, HorizontalTextAlignment alignment) {
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
	public StyleBuilder getColumnHeader(String fontName, Integer fontSize, Color foreGroundColor, Color backGroundColor, boolean italic) {
		return getBoldStyle(fontName, fontSize, italic, HorizontalTextAlignment.CENTER).setBackgroundColor(backGroundColor)
				.setForegroundColor(foreGroundColor).setBorder(DynamicReports.stl.pen1Point());
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
	public StyleBuilder getColumnHeader(Color foreGroundColor, Color backGroundColor, boolean italic) {
		return getBoldStyle(HorizontalTextAlignment.CENTER).setBackgroundColor(backGroundColor).setForegroundColor(foreGroundColor).setItalic(italic)
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
	public StyleBuilder getTitleStyle(String fontName, Integer fontSize, boolean bold, boolean italic, HorizontalTextAlignment alignment) {
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
	public StyleBuilder getTitleStyle(boolean bold, boolean italic, HorizontalTextAlignment alignment) {
		return getStyle().setFontSize(14).setBold(bold).setItalic(italic).setHorizontalTextAlignment(alignment);
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
		return getFiller().setStyle(getStyle().setTopBorder(DynamicReports.stl.pen1Point())).setFixedHeight(height);
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
