package com.slabs.expense.tracker.webservices.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.slabs.expense.tracker.common.database.entity.Inventory;
import com.slabs.expense.tracker.common.exception.ExpenseTrackerException;
import com.slabs.expense.tracker.common.services.InventoryService;
import com.slabs.expense.tracker.common.webservices.InventoryWebService;
import com.slabs.expense.tracker.common.webservice.response.Operation;
import com.slabs.expense.tracker.common.webservice.response.Response;
import com.slabs.expense.tracker.webservices.response.ResponseGenerator;

/**
 * {@link InventoryWebServiceImpl} - Web Service for retrieving/updating
 * Inventory
 * 
 * @author Shyam Natarajan
 *
 */
@RestController
@RequestMapping(value = "api")
public class InventoryWebServiceImpl implements InventoryWebService {

	@Autowired
	private InventoryService service;

	@RequestMapping(value = "inventory", method = { RequestMethod.POST }, produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	@Override
	public Response addInventory(@RequestBody List<Inventory> records) throws ExpenseTrackerException {
		try {
			return ResponseGenerator.getSuccessResponse(service.createInventory(records), Operation.INSERT);
		} catch (Exception e) {
			throw new ExpenseTrackerException(e);
		}
	}

	@RequestMapping(value = "inventory", method = { RequestMethod.DELETE }, produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	@Override
	public Response deleteInventory(@RequestBody List<Inventory> records) throws ExpenseTrackerException {
		try {
			return ResponseGenerator.getSuccessResponse(service.deleteInventory(records), Operation.DELETE);
		} catch (Exception e) {
			throw new ExpenseTrackerException(e);
		}
	}

	@RequestMapping(value = "inventory", method = { RequestMethod.PUT }, produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	@Override
	public Response updateInventory(@RequestBody List<Inventory> records) throws ExpenseTrackerException {
		try {
			return ResponseGenerator.getSuccessResponse(service.updateInventory(records), Operation.UPDATE);
		} catch (Exception e) {
			throw new ExpenseTrackerException(e);
		}
	}

	@RequestMapping(value = "inventory", method = { RequestMethod.GET }, produces = { "application/json", "application/xml" })
	@Override
	public Response getInventory(@RequestParam(name = "username") String username) throws ExpenseTrackerException {
		try {
			return ResponseGenerator.getSuccessResponse(service.getInventory(username), Operation.SELECT);
		} catch (Exception e) {
			throw new ExpenseTrackerException(e);
		}
	}

}
