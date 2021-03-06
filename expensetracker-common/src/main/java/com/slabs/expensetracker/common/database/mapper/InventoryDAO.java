package com.slabs.expensetracker.common.database.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.slabs.expensetracker.common.database.entity.Inventory;

@Component(value = "InventoryDAO")
public interface InventoryDAO {

	public Integer createInventory(List<Inventory> records);

	public Integer deleteInventory(Inventory record);

	public List<Inventory> getInventory(String username);

	public Integer updateInventory(Inventory record);

}
