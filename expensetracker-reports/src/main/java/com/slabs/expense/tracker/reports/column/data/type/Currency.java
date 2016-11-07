package com.slabs.expense.tracker.reports.column.data.type;

import net.sf.dynamicreports.report.builder.datatype.DoubleType;

public abstract class Currency extends DoubleType {

	private static final long serialVersionUID = 1L;

	public abstract String currencySymbol();

}
