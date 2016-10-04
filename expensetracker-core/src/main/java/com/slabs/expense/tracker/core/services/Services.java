package com.slabs.expense.tracker.core.services;

public enum Services {
	EXPENSE_CATEGORY_SERVICE("expensecategory"), EXPENSE_TYPE_SERVICE("expensetype"), EXPENSE_SERVICE(
			"expense"), USER_SERVICE("user");

	private String name;

	Services(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return this.name;
	}

}
