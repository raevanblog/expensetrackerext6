package com.slabs.expense.tracker.reports.column.data.type;

import net.sf.dynamicreports.report.builder.datatype.BigDecimalType;

public class Rupees extends BigDecimalType {

	private static final long serialVersionUID = 1L;

	@Override
	public String getPattern() {
		return "₹ #,###.00";
	}

}
