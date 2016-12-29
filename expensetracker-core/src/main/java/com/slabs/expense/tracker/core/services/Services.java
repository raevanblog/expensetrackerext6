package com.slabs.expense.tracker.core.services;

/**
 * {@link Services} is an {@link Enum} listing the Expense Tracker services
 * 
 * @author Shyam Natarajan
 *
 */
public enum Services {
	ADMIN_SERVICE("AdminService"), EXPENSE_CATEGORY_SERVICE(
			"ExpenseCategoryService"), EXPENSE_TYPE_SERVICE("ExpenseTypeService"), EXPENSE_SERVICE(
					"ExpenseService"), USER_SERVICE("UserService"), EMAIL_SERVICE(
							"EmailService"), INCOME_SERVICE("IncomeService"), DASHBOARD_SERVICE(
									"DashboardService"), REPORTING_SERVICE(
											"ReportingService"), MESSAGING_SERVICE(
													"MessagingService");

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
