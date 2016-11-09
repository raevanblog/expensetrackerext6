package com.slabs.expense.tracker.reports.column.data.type;

/**
 * {@link CurrencyType} is an {@link Enum} for curreny types
 * 
 * @author Shyam Natarajan
 *
 */
public enum CurrencyType {

	DOLLAR(Dollar.class), RUPEES(Rupee.class);

	private Class<?> className;

	CurrencyType(Class<?> className) {
		this.className = className;
	}

	public Class<?> getTypeClass() {
		return this.className;
	}

}
