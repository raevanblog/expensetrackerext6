package com.slabs.expense.tracker.reports.column.data.type;

public class Dollar extends Currency {

	private static final long serialVersionUID = 1L;

	private static final String CURRENCY_SYMBOL = "$";

	@Override
	public String getPattern() {
		return CURRENCY_SYMBOL + " #,###.##";
	}

	@Override
	public String currencySymbol() {
		return CURRENCY_SYMBOL;
	}

}
