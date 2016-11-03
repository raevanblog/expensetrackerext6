package com.slabs.expense.tracker.reports.column.data.type;

public class Rupees extends Currency {

	private static final long serialVersionUID = 1L;

	private static final String CURRENCY_SYMBOL = "₹";

	@Override
	public String getPattern() {
		return "₹ #,###.00";
	}

	@Override
	public String currencySymbol() {
		return CURRENCY_SYMBOL;
	}

}
