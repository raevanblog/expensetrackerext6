package com.slabs.expense.tracker.reports.column.data.type;

import com.slabs.expensetracker.common.constants.Constants;

/**
 * {@link CurrencyType} is an {@link Enum} for curreny types
 * 
 * @author Shyam Natarajan
 *
 */
public enum CurrencyType {

	USD(Dollar.class), INR(Rupee.class);

	private Class<?> className;

	CurrencyType(Class<?> className) {
		this.className = className;
	}

	public Class<?> getTypeClass() {
		return this.className;
	}

	public static CurrencyType getCurrency(String currencyName) {
		if (Constants.INR.equals(currencyName)) {
			return CurrencyType.INR;
		} else if (Constants.USD.equals(currencyName)) {
			return CurrencyType.USD;
		}
		return null;
	}

}
