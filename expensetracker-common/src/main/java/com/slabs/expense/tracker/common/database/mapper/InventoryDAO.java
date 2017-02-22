package com.slabs.expense.tracker.common.database.mapper;

import java.util.List;

import com.slabs.expense.tracker.common.database.entity.Inventory;

public interface InventoryDAO {

	public Integer createInventory(List<Inventory> records);

	public Integer deleteInventory(Inventory record);

	public List<Inventory> getInventory(String username, int year, int month);

	public Integer updateInventory(Inventory record);

}
