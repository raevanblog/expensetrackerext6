package com.slabs.expense.tracker.common.webservices;

import java.util.List;

import com.slabs.expense.tracker.common.database.entity.Inventory;
import com.slabs.expense.tracker.common.exception.ExpenseTrackerException;
import com.slabs.expense.tracker.webservice.response.Response;

public interface InventoryWebService {

	public Response addInventory(List<Inventory> records) throws ExpenseTrackerException;

	public Response deleteInventory(List<Inventory> records) throws ExpenseTrackerException;

	public Response updateInventory(List<Inventory> records) throws ExpenseTrackerException;

	public Response getInventory(String username) throws ExpenseTrackerException;

}
