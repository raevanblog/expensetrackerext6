package com.slabs.expense.tracker.reports.column.data.type;

public enum CurrencyType {

	DOLLAR(Dollar.class), RUPEES(Rupees.class);

	private Class<?> className;

	CurrencyType(Class<?> className) {
		this.className = className;
	}

	public Class<?> getTypeClass() {
		return this.className;
	}

}
