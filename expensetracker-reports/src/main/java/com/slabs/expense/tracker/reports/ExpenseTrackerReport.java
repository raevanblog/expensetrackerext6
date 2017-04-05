package com.slabs.expense.tracker.reports;

import java.awt.Color;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.slabs.expense.tracker.common.constants.Constants;
import com.slabs.expense.tracker.common.database.column.Column;
import com.slabs.expense.tracker.common.database.entity.UserInfo;
import com.slabs.expense.tracker.common.exception.ExpenseTrackerException;
import com.slabs.expense.tracker.reports.builder.ReportBuilder;
import com.slabs.expense.tracker.reports.column.data.type.Currency;
import com.slabs.expense.tracker.reports.column.data.type.CurrencyType;
import com.slabs.expense.tracker.reports.provider.ColumnProvider;
import com.slabs.expense.tracker.reports.provider.StyleProvider;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.chart.ChartBuilders;
import net.sf.dynamicreports.report.builder.column.ValueColumnBuilder;
import net.sf.dynamicreports.report.builder.component.ComponentBuilders;
import net.sf.dynamicreports.report.builder.component.HorizontalListBuilder;
import net.sf.dynamicreports.report.builder.component.ImageBuilder;
import net.sf.dynamicreports.report.builder.component.TextFieldBuilder;
import net.sf.dynamicreports.report.builder.component.VerticalListBuilder;
import net.sf.dynamicreports.report.builder.grid.GridBuilders;
import net.sf.dynamicreports.report.builder.style.FontBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilders;
import net.sf.dynamicreports.report.builder.subtotal.AggregationSubtotalBuilder;
import net.sf.dynamicreports.report.builder.subtotal.SubtotalBuilders;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.constant.VerticalTextAlignment;
import net.sf.dynamicreports.report.exception.DRException;

/**
 * {@link ExpenseTrackerReport} is an abstract class which provides support for
 * building report
 * 
 * @author Shyam Natarajan
 *
 */
public abstract class ExpenseTrackerReport {

	protected ReportBuilder builder = ReportBuilder.getInstance();

	protected ColumnProvider columnProvider = builder.getColumnProvider();

	protected ComponentBuilders componentBuilder = builder.getComponentBuilders();

	protected StyleProvider styleProvider = builder.getStyleProvider();

	protected StyleBuilders styleBuilder = builder.getStyleBuilders();

	protected GridBuilders gridBuilder = builder.getGridBuilders();

	protected SubtotalBuilders subtotalBuilder = builder.getSubtotalBuilders();

	protected ChartBuilders chartBuilder = builder.getChartBuilders();

	protected JasperReportBuilder report;

	@SuppressWarnings("rawtypes")
	protected List<ValueColumnBuilder> columns = new ArrayList<ValueColumnBuilder>();

	protected static final Color DEFAULT_HEADER_COLOR = new Color(127, 217, 244);

	protected UserInfo userInfo;

	protected Integer year;

	protected Month month;

	protected Currency currency;

	public ExpenseTrackerReport(UserInfo userInfo, Month month, Integer year, CurrencyType currency)
			throws InstantiationException, IllegalAccessException {
		this.userInfo = userInfo;
		this.month = month;
		this.year = year;
		this.currency = (Currency) currency.getTypeClass().newInstance();
		this.report = ReportBuilder.getInstance().createJasperReportBuilder();
		this.report.setDefaultFont(ReportBuilder.getInstance().getDefaultFont());
	}

	/**
	 * 
	 * @param dataSource
	 *            {@link Collection}
	 */
	public void setDataSource(Collection<? extends Object> dataSource) {
		report.setDataSource(dataSource);
	}

	/**
	 * This method will take SQL string and {@link Connection} and the
	 * {@link JasperReportBuilder} will make use of this to get the data
	 * 
	 * @param sql
	 *            {@link String} - SQL string
	 * @param connection
	 *            {@link Connection} - SQL connection
	 */
	public void setDataSource(String sql, Connection connection) {
		report.setDataSource(sql, connection);
	}

	/**
	 * This method will add the page number to the report
	 * 
	 */
	public void addPageNumber() {
		HorizontalListBuilder hList = componentBuilder.horizontalFlowList();
		hList.add(getApplicationName(10), componentBuilder.pageXslashY().setStyle(styleProvider.getStyle(HorizontalTextAlignment.LEFT)));
		report.pageFooter(styleProvider.getDefaultFillerLine(10), hList);
	}

	/**
	 * This method will return the application logo.
	 * 
	 * @return {@link ImageBuilder}
	 */
	public ImageBuilder getLogo() {
		return componentBuilder.image(getClass().getResourceAsStream("/images/logo-blue.png")).setFixedDimension(50, 50);
	}

	/**
	 * This method will return the logo title.
	 * 
	 * @param alignment
	 *            {@link VerticalTextAlignment} - To align the title vertically.
	 * @return {@link TextFieldBuilder}
	 */
	public TextFieldBuilder<String> getLogoTitle(VerticalTextAlignment alignment) {
		return componentBuilder.text(Constants.APP_NAME_CC).setStyle(styleProvider.getBoldStyle(14).setVerticalTextAlignment(alignment));
	}

	/**
	 * This method will return the application name using the font size
	 * 
	 * @param fontSize
	 *            - Size of the font
	 * @return {@link TextFieldBuilder}
	 */
	public TextFieldBuilder<String> getApplicationName(Integer fontSize) {
		TextFieldBuilder<String> appName = componentBuilder.text(Constants.APP_NAME_CC);
		StyleBuilder appNameStyle = styleProvider.getStyle(fontSize).setHorizontalTextAlignment(HorizontalTextAlignment.LEFT);
		return appName.setStyle(appNameStyle);
	}

	/**
	 * This method will add the title to the report
	 * 
	 */
	public void addTitle(String reportName) {

		HorizontalListBuilder titleContainer = componentBuilder.horizontalList();
		TextFieldBuilder<String> logoTitle = getLogoTitle(VerticalTextAlignment.BOTTOM).setHorizontalTextAlignment(HorizontalTextAlignment.LEFT);

		TextFieldBuilder<String> reportTitle = componentBuilder.text(reportName).setStyle(styleProvider.getBoldStyle(16));

		TextFieldBuilder<String> monthAnYear = componentBuilder.text(new StringBuilder(month.getName()).append(",").append(year).toString())
				.setStyle(styleProvider.getBoldStyle(12).setHorizontalTextAlignment(HorizontalTextAlignment.RIGHT));
		titleContainer.add(getLogo(), monthAnYear).newFlowRow(2);
		titleContainer.add(logoTitle, reportTitle.setHorizontalTextAlignment(HorizontalTextAlignment.RIGHT)).newRow();

		report.title(titleContainer, componentBuilder.verticalGap(5), styleProvider.getDefaultFillerLine(10));
	}

	/**
	 * This method will add report owner details to the report
	 * 
	 */
	public void addReportOwnner() {
		VerticalListBuilder verticalContainer = componentBuilder.verticalList();

		TextFieldBuilder<String> title = componentBuilder.text("Report Owner").setStyle(styleProvider.getBoldStyle(14).setUnderline(Boolean.TRUE));

		HorizontalListBuilder detailContainer = componentBuilder.horizontalList();

		TextFieldBuilder<String> name = componentBuilder.text("Name : " + userInfo.getFirstName() + " " + userInfo.getLastName())
				.setStyle(styleProvider.getStyle());
		TextFieldBuilder<String> email = componentBuilder.text("Email : " + userInfo.getEmail()).setStyle(styleProvider.getStyle());
		TextFieldBuilder<String> address = componentBuilder.text("Address : " + userInfo.getAddress()).setStyle(styleProvider.getStyle());

		detailContainer.add(name).newRow();
		detailContainer.add(email).newRow();
		detailContainer.add(address).newRow(5);

		verticalContainer.add(title, componentBuilder.verticalGap(5), detailContainer);

		report.title(verticalContainer);
	}

	/**
	 * This method will apply sub total to the column {@code Column.PRICE}
	 */
	@SuppressWarnings({ "rawtypes" })
	public void subTotal(Column subtotalColumn, Column labelColumn) {
		AggregationSubtotalBuilder subtotal = getSubTotalBuilder(subtotalColumn);
		subtotal.setStyle(styleProvider.getBoldStyle(HorizontalTextAlignment.CENTER));
		AggregationSubtotalBuilder<String> label = subtotalBuilder.text("Total", getColumn(labelColumn));
		label.setStyle(styleProvider.getBoldStyle(HorizontalTextAlignment.RIGHT));
		report.addSubtotalAtSummary(label, subtotal);
	}

	/**
	 * This method will add the columns to the report
	 * 
	 * @param columns
	 *            {@link ValueColumnBuilder} Array of column in the report
	 */
	@SuppressWarnings("rawtypes")
	public void addColumns(ValueColumnBuilder... columns) {
		for (ValueColumnBuilder c : columns) {
			this.columns.add(c);
		}
	}

	/**
	 * This method will add all the columns to the Jasper Report
	 * 
	 */
	public void addColumnsToReport() {
		report.columns(getAllColumns());
		report.highlightDetailOddRows();
	}

	/**
	 * This method will return {@link ValueColumnBuilder} for the given
	 * {@link Column}
	 * 
	 * @param column
	 *            {@link Column} - Column for which {@link ValueColumnBuilder}
	 *            will be returned
	 * @return {@link ValueColumnBuilder}
	 */
	@SuppressWarnings("rawtypes")
	public ValueColumnBuilder getColumn(Column column) {
		for (ValueColumnBuilder c : columns) {
			if (c.getColumn().getName().equals(column.getMappingName())) {
				return c;
			}
		}
		return null;
	}

	/**
	 * This method will return all the columns added to the report
	 * 
	 * @return {@link ValueColumnBuilder} - Array of {@link ValueColumnBuilder}
	 */
	@SuppressWarnings("rawtypes")
	public ValueColumnBuilder[] getAllColumns() {
		ValueColumnBuilder[] columns = new ValueColumnBuilder[this.columns.size()];
		return this.columns.toArray(columns);
	}

	/**
	 * This method will apply subtotal to the specified {@link Column}
	 * 
	 * @param column
	 *            {@link Column} - Column for which subtotal to be done
	 * @return {@link AggregationSubtotalBuilder}
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public AggregationSubtotalBuilder getSubTotalBuilder(final Column column) {
		AggregationSubtotalBuilder subtotal = subtotalBuilder.sum(getColumn(column));
		return subtotal;
	}

	/**
	 * 
	 * 
	 * @param fontSize
	 *            {@link Integer} - Font size.
	 * @param bold
	 *            {@link Boolean} - True for bold style.
	 * @param italic
	 *            {@link Boolean} - True for Italic.
	 * @return {@link FontBuilder}
	 * 
	 * 
	 */
	public FontBuilder getDefaultFont(Integer fontSize, boolean bold, boolean italic) {
		return builder.getDefaultFont().setFontSize(fontSize).setBold(bold).setItalic(italic);
	}

	/**
	 * 
	 * @return
	 */
	public Integer getYear() {
		return year;
	}

	/**
	 * 
	 * @return
	 */
	public Month getMonth() {
		return month;
	}

	/**
	 * s
	 * 
	 * @return
	 */
	public Currency getCurrency() {
		return currency;
	}

	/**
	 * This method will build the report
	 * 
	 * @return
	 * @throws DRException
	 */
	public abstract JasperReportBuilder buildReport(String reportName) throws DRException;

	/**
	 * 
	 * This method will apply groupBy for the given {@link Column}
	 * 
	 * @param column
	 *            {@link Column} - Column for which groupBy has to be applied
	 * @param enableSubtotal
	 *            {@link Boolean} - True to apply subtotal for the group.
	 *            Default is false
	 */
	public abstract void groupBy(Column column, boolean enableSubtotal) throws ExpenseTrackerException;
	
	/**
	 * This method will add title to grid.
	 */
	public abstract void addGridTitle() throws ExpenseTrackerException;

}
