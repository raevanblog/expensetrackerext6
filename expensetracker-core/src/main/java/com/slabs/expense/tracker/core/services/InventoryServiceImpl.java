package com.slabs.expense.tracker.core.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.slabs.expense.tracker.common.database.entity.Inventory;
import com.slabs.expensetracker.common.database.mapper.InventoryDAO;
import com.slabs.expensetracker.common.services.InventoryService;

@Service(value = "InventoryService")
@Transactional(isolation = Isolation.READ_COMMITTED, timeout = 2000)
public class InventoryServiceImpl implements InventoryService {

	@Autowired
	private InventoryDAO inventoryDao;

	@Override
	public Integer createInventory(List<Inventory> records) throws Exception {
		int noOfRecords = 0;
		noOfRecords = noOfRecords + inventoryDao.createInventory(records);
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
	public List<Inventory> getInventory(String username)
			throws Exception {
		return inventoryDao.getInventory(username);
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
