package com.slabs.expense.tracker.reports;

import java.awt.Color;

import com.slabs.expense.tracker.common.db.entity.UserInfo;
import com.slabs.expense.tracker.reports.column.Column;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.column.ValueColumnBuilder;
import net.sf.dynamicreports.report.builder.component.HorizontalListBuilder;
import net.sf.dynamicreports.report.builder.component.TextFieldBuilder;
import net.sf.dynamicreports.report.builder.component.VerticalListBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.exception.DRException;

public class MonthlyExpenseReport extends ExpenseTrackerReport {

	public MonthlyExpenseReport(UserInfo userInfo, Month month, Integer year) {
		super(userInfo, month, year);
	}

	public JasperReportBuilder buildReport() throws DRException {
		addTitle();
		addExpenseDetails();
		addPageNumber();
		return report;
	}

	@Override
	public void addTitle() {
		StyleBuilder userInfoStyle = sProvider.getStyle(10);
		HorizontalListBuilder titleContainer = cBuilders.horizontalList();
		TextFieldBuilder<String> reportTitle = cBuilders.text("Monthly Report").setStyle(sProvider.getBoldStyle());

		VerticalListBuilder userInfoContainer = cBuilders.verticalList();
		
		TextFieldBuilder<String> owner = cBuilders.text("Owner : " + userInfo.getFirstName() + " " + userInfo.getLastName()).setStyle(userInfoStyle);
		TextFieldBuilder<String> email = cBuilders.text("Email : " + userInfo.getEmail()).setStyle(userInfoStyle);
		TextFieldBuilder<String> mobile = cBuilders.text("Mobile : " + userInfo.getMobile()).setStyle(userInfoStyle);

		userInfoContainer.add(owner, email, mobile);
		
		TextFieldBuilder<String> monthAnYear = cBuilders.text(month.getName() + "," + year).setStyle(sProvider.getBoldStyle(HorizontalTextAlignment.RIGHT));
		titleContainer.add(reportTitle, monthAnYear).newRow(2);
		titleContainer.add(userInfoContainer);

		report.addPageHeader(titleContainer, cBuilders.verticalGap(5), sProvider.getFillerLine(DynamicReports.stl.pen1Point(), 10));
	}

	public void addExpenseDetails() {
		StyleBuilder centerStyle = sProvider.getStyle(HorizontalTextAlignment.CENTER);
		report.setColumnTitleStyle(sProvider.getColumnHeader(Color.BLACK, DEFAULT_HEADER_COLOR, false));

		TextColumnBuilder<? extends Object> rowNum = cProvider.getRowNumberColumn("S.No");
		ValueColumnBuilder expDate = cProvider.getDateColumn(Column.EXPDATE);
		TextColumnBuilder<? extends Object> expenseType = cProvider.getColumn(Column.EXPTYPE);
		TextColumnBuilder<? extends Object> expenseCategory = cProvider.getColumn(Column.CATEGORY);
		TextColumnBuilder<? extends Object> qty = cProvider.getColumn(Column.QTY).setStyle(centerStyle);
		TextColumnBuilder<? extends Object> price = cProvider.getColumn(Column.PRICE).setStyle(centerStyle);
		TextColumnBuilder<? extends Object> pricePerUnit = cProvider.getColumn(Column.PRICEPERUNIT).setStyle(centerStyle);

		report.columns(rowNum, expDate, expenseType, expenseCategory, qty, price, pricePerUnit);
		report.highlightDetailEvenRows();
	}

}
