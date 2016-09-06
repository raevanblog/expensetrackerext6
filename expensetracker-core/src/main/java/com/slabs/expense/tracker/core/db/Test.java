package com.slabs.expense.tracker.core.db;

import java.util.List;

import com.slabs.expense.tracker.core.ServiceFactory;
import com.slabs.expense.tracker.core.db.entity.ExpenseCategory;
import com.slabs.expense.tracker.core.server.Server;
import com.slabs.expense.tracker.core.services.ExpenseCategoryService;

public class Test {

	public static void main(String[] args) throws Exception {
		Server.getInstance().initialize();
		ExpenseCategoryService service = (ExpenseCategoryService)ServiceFactory.getInstance().getService("ExpenseCategoryService");
		List<ExpenseCategory> ec = service.selectExpenseCategory(null);
		for (ExpenseCategory e : ec) {
			System.out.println(e);
		}
	}

}
