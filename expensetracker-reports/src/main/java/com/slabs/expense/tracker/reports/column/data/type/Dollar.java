package com.slabs.expense.tracker.reports.column.data.type;

/**
 * 
 * {@link Dollar} currency column type
 * 
 * @author Shyam Natarajan
 *
 */
public class Dollar extends Currency {

	private static final long serialVersionUID = 1L;

	private static final String CURRENCY_SYMBOL = "$";

	private static final String CURRENCY_NAME = "USD";

	@Override
	public String getPattern() {
		return CURRENCY_SYMBOL + " #,###.##";
	}

	@Override
	public String currencySymbol() {
		return CURRENCY_SYMBOL;
	}

	@Override
	public String currenyName() {
		return CURRENCY_NAME;
	}

}
