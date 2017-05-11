package com.slabs.expensetracker.common.webservices;

import java.util.List;

import com.slabs.expensetracker.common.database.entity.Inventory;
import com.slabs.expensetracker.common.webservice.response.Response;
import com.slabs.expensetracker.common.exception.ExpenseTrackerException;

public interface InventoryWebService {

	public Response addInventory(List<Inventory> records) throws ExpenseTrackerException;

	public Response deleteInventory(List<Inventory> records) throws ExpenseTrackerException;

	public Response updateInventory(List<Inventory> records) throws ExpenseTrackerException;

	public Response getInventory(String username) throws ExpenseTrackerException;

}
