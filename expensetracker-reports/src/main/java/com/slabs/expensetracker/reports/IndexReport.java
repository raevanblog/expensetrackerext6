package com.slabs.expensetracker.reports;

import com.slabs.expense.tracker.common.database.entity.UserInfo;
import com.slabs.expensetracker.common.database.column.Column;
import com.slabs.expensetracker.common.exception.ExpenseTrackerException;
import com.slabs.expensetracker.reports.column.data.type.CurrencyType;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.component.TextFieldBuilder;
import net.sf.dynamicreports.report.builder.component.VerticalListBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.exception.DRException;

public class IndexReport extends ExpenseTrackerReport {

	public IndexReport(UserInfo userInfo, Month month, Integer year) throws InstantiationException, IllegalAccessException {
		this(userInfo, month, year, CurrencyType.USD);
	}

	public IndexReport(UserInfo userInfo, Month month, Integer year, CurrencyType currency) throws InstantiationException, IllegalAccessException {
		super(userInfo, month, year, currency);
	}

	private void addUserNameToIndex() {
		VerticalListBuilder verticalList = componentBuilder.verticalList();
		StyleBuilder centerStyle = styleProvider.getBoldStyle(26).setHorizontalTextAlignment(HorizontalTextAlignment.CENTER);
		TextFieldBuilder<String> name = componentBuilder.text(userInfo.getFirstName() + ' ' + userInfo.getLastName()).setStyle(centerStyle);
		verticalList.add(componentBuilder.vListCell(componentBuilder.verticalGap(100)), componentBuilder.vListCell(name),
				componentBuilder.vListCell(componentBuilder.verticalGap(100)));
		report.title(verticalList);
	}

	@Override
	public JasperReportBuilder buildReport(String reportName) throws DRException {
		addTitle(reportName);
		addUserNameToIndex();
		return report;
	}

	@Override
	public void groupBy(Column column, boolean enableSubtotal) throws ExpenseTrackerException {
		throw new ExpenseTrackerException("Unsupported Operation");
	}

	@Override
	public void addGridTitle() {
		// TODO Auto-generated method stub

	}

}
