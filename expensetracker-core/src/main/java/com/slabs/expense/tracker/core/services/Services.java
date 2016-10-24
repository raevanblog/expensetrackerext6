package com.slabs.expense.tracker.core.services;

public enum Services {
	EXPENSE_CATEGORY_SERVICE("ExpenseCategoryService"), EXPENSE_TYPE_SERVICE("ExpenseTypeService"), EXPENSE_SERVICE(
			"ExpenseService"), USER_SERVICE(
					"UserService"), INCOME_SERVICE("IncomeService"), DASHBOARD_SERVICE("DashboardService");

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
