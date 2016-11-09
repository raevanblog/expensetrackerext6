package com.slabs.expense.tracker.reports.column.data.type;

/**
 * 
 * {@link Rupee} currency column type
 * 
 * @author Shyam Natarajan
 *
 */
public class Rupee extends Currency {

	private static final long serialVersionUID = 1L;

	private static final String CURRENCY_SYMBOL = "₹";

	@Override
	public String getPattern() {
		return "₹ #,###.##";
	}

	@Override
	public String currencySymbol() {
		return CURRENCY_SYMBOL;
	}

}
