package com.slabs.expensetracker.common.services;

/**
 * {@link Services} is an {@link Enum} listing the Expense Tracker services
 * 
 * @author Shyam Natarajan
 *
 */
public enum Services {
	
	ADMIN_SERVICE("AdminService"),
	APPLICATION_SERVICE("ApplicationService"),
	EXPENSE_CATEGORY_SERVICE("ExpenseCategoryService"), 	
	EXPENSE_SERVICE("ExpenseService"),
	INVENTORY_SERVICE("InventoryService"),
	USER_SERVICE("UserService"),
	EMAIL_SERVICE("EmailService"), 
	INCOME_SERVICE("IncomeService"), 
	DASHBOARD_SERVICE("DashboardService"), 
	REPORTING_SERVICE("ReportingService"), 
	MESSAGING_SERVICE("MessageService");

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
