package com.slabs.expense.tracker.core.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.slabs.expense.tracker.common.constants.Constants;
import com.slabs.expense.tracker.common.database.entity.Expense;
import com.slabs.expense.tracker.common.database.entity.Inventory;
import com.slabs.expense.tracker.common.database.mapper.InventoryDAO;
import com.slabs.expense.tracker.common.services.ExpenseService;
import com.slabs.expense.tracker.common.services.InventoryService;

@Service(value = "InventoryService")
@Transactional(isolation = Isolation.READ_COMMITTED, timeout = 2000)
public class InventoryServiceImpl implements InventoryService {

	@Autowired
	private InventoryDAO inventoryDao;

	@Autowired
	private ExpenseService expenseService;

	@Override
	public Integer createInventory(List<Inventory> records) throws Exception {
		int noOfRecords = 0;
		List<Expense> expenseList = null;

		for (Inventory record : records) {
			if (null != record.getExpId()) {
				if (expenseList == null) {
					expenseList = new ArrayList<Expense>();
				}

				Expense expense = new Expense();
				expense.setInventoryInd(Constants.Y);
				expense.setId(record.getExpId());

				expenseList.add(expense);
			}
		}

		if (expenseList != null) {
			noOfRecords = noOfRecords + inventoryDao.createInventoryByExpenseId(records);
			noOfRecords = noOfRecords + expenseService.updateExpense(expenseList);
		} else {
			noOfRecords = noOfRecords + inventoryDao.createInventory(records);
		}

		return noOfRecords;
	}

	@Override
	public Integer deleteInventory(List<Inventory> records) throws Exception {
		int noOfRecords = 0;
		for (Inventory record : records) {
			noOfRecords = noOfRecords + inventoryDao.deleteInventory(record);
		}
		return noOfRecords;
	}

	@Override
	public List<Inventory> getInventory(String username, Integer year, Integer month)
			throws Exception {
		return inventoryDao.getInventory(username, year, month);
	}

	@Override
	public Integer updateInventory(List<Inventory> records) throws Exception {
		int noOfRecords = 0;
		for (Inventory record : records) {
			noOfRecords = noOfRecords + inventoryDao.updateInventory(record);
		}
		return noOfRecords;
	}

}
