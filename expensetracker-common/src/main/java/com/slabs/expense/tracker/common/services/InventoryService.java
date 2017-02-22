package com.slabs.expense.tracker.common.services;

import java.util.List;

import com.slabs.expense.tracker.common.database.entity.Inventory;

public interface InventoryService {

	public Integer createInventory(List<Inventory> records) throws Exception;

	public Integer deleteInventory(Inventory record) throws Exception;

	public List<Inventory> getInventory(String username, int year, int month) throws Exception;

	public Integer updateInventory(Inventory record) throws Exception;
	
}
