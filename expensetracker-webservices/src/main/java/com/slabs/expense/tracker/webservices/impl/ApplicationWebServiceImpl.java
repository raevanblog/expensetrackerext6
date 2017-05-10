package com.slabs.expense.tracker.webservices.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.slabs.expense.tracker.common.exception.ExpenseTrackerException;
import com.slabs.expense.tracker.common.services.ApplicationService;
import com.slabs.expense.tracker.common.services.Services;
import com.slabs.expense.tracker.common.webservices.ApplicationWebService;
import com.slabs.expense.tracker.core.ServiceFactory;
import com.slabs.expense.tracker.webservice.response.Operation;
import com.slabs.expense.tracker.webservice.response.Response;
import com.slabs.expense.tracker.webservices.response.ResponseGenerator;
import com.slabs.expense.tracker.webservices.response.ResponseStatus;

/**
 * {@link ApplicationWebServiceImpl} - Webservice to retrieve application data.
 * 
 * @author Shyam Natarajan
 *
 */
@RestController
@RequestMapping(value = "api")
public class ApplicationWebServiceImpl implements ApplicationWebService {

	private static final Logger L = LoggerFactory.getLogger(ApplicationWebServiceImpl.class);

	/**
	 * This method will return a list of expense type in the
	 * {@link com.slabs.expense.tracker.webservice.response.Response}
	 * 
	 * @return {@link com.slabs.expense.tracker.webservice.response.Response}
	 * @throws WebServiceException
	 *             throws {@link WebServiceException}
	 */
	@RequestMapping(value = "application/expensetype", method = { RequestMethod.GET }, produces = { "application/json", "application/xml" })
	@Override
	public Response getExpenseType() throws ExpenseTrackerException {
		try {
			ApplicationService service = ServiceFactory.getInstance().getService(Services.APPLICATION_SERVICE, ApplicationService.class);
			return ResponseGenerator.getSuccessResponse(service.getExpenseType(), Operation.SELECT);
		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
			throw new ExpenseTrackerException(e);
		}
	}

	/**
	 * This method will return a list of income type in the
	 * {@link com.slabs.expense.tracker.webservice.response.Response}
	 * 
	 * @return {@link com.slabs.expense.tracker.webservice.response.Response}
	 * @throws WebServiceException
	 *             throws {@link WebServiceException}
	 */
	@RequestMapping(value = "application/incometype", method = { RequestMethod.GET }, produces = { "application/json", "application/xml" })
	@Override
	public Response getIncomeType() throws ExpenseTrackerException {
		try {
			ApplicationService service = ServiceFactory.getInstance().getService(Services.APPLICATION_SERVICE, ApplicationService.class);
			return ResponseGenerator.getSuccessResponse(service.getIncomeType(), Operation.SELECT);
		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
			throw new ExpenseTrackerException(e);
		}
	}

	/**
	 * This method will return a list of income type in the
	 * {@link com.slabs.expense.tracker.webservice.response.Response}
	 * 
	 * @return {@link com.slabs.expense.tracker.webservice.response.Response}
	 * @throws WebServiceException
	 *             throws {@link WebServiceException}
	 */
	@RequestMapping(value = "application/currencytype", method = { RequestMethod.GET }, produces = { "application/json", "application/xml" })
	@Override
	public Response getCurrencyType() throws ExpenseTrackerException {
		try {
			ApplicationService service = ServiceFactory.getInstance().getService(Services.APPLICATION_SERVICE, ApplicationService.class);
			return ResponseGenerator.getSuccessResponse(service.getCurrency(), Operation.SELECT);
		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
			throw new ExpenseTrackerException(e);
		}
	}

	/**
	 * This method will return a list of item names for which expenses have been
	 * recorded, in the
	 * {@link com.slabs.expense.tracker.webservice.response.Response}
	 * 
	 * @return {@link com.slabs.expense.tracker.webservice.response.Response}
	 * @throws WebServiceException
	 *             throws {@link WebServiceException}
	 */
	@RequestMapping(value = "application/dictionary", method = { RequestMethod.GET }, produces = { "application/json", "application/xml" })
	@Override
	public Response getItemNames(@RequestParam(name = "type") String type) throws ExpenseTrackerException {
		try {
			ApplicationService service = ServiceFactory.getInstance().getService(Services.APPLICATION_SERVICE, ApplicationService.class);

			if ("items".equals(type)) {
				return ResponseGenerator.getSuccessResponse(service.getExpenseNames(), Operation.SELECT);
			}

			return ResponseGenerator.getExceptionResponse(ResponseStatus.BAD_REQUEST, "Invalid dictionary type");
		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
			throw new ExpenseTrackerException(e);
		}
	}

}
