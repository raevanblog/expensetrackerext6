package com.slabs.expense.tracker.common.services;

import java.util.List;

import com.slabs.expense.tracker.common.database.entity.Inventory;

public interface InventoryService {

	public Integer createInventory(List<Inventory> records) throws Exception;

	public Integer deleteInventory(List<Inventory> records) throws Exception;

	public List<Inventory> getInventory(String username) throws Exception;

	public Integer updateInventory(List<Inventory> records) throws Exception;

}
