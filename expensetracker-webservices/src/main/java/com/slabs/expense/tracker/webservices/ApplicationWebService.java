package com.slabs.expense.tracker.webservices;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.slabs.expense.tracker.common.exception.ExpenseTrackerException;
import com.slabs.expense.tracker.common.services.ApplicationService;
import com.slabs.expense.tracker.common.services.Services;
import com.slabs.expense.tracker.core.ServiceFactory;
import com.slabs.expense.tracker.webservice.response.Operation;
import com.slabs.expense.tracker.webservice.response.Response;
import com.slabs.expense.tracker.webservices.exception.WebServiceException;
import com.slabs.expense.tracker.webservices.response.ResponseGenerator;
import com.slabs.expense.tracker.webservices.response.ResponseStatus;

/**
 * {@link ApplicationWebService} - Webservice to retrieve application data.
 * 
 * @author Shyam Natarajan
 *
 */
@Path("exptr-web")
public class ApplicationWebService {

	private static final Logger L = LoggerFactory.getLogger(ApplicationWebService.class);

	/**
	 * This method will return a list of expense type in the
	 * {@link com.slabs.expense.tracker.webservice.response.Response}
	 * 
	 * @return {@link com.slabs.expense.tracker.webservice.response.Response}
	 * @throws WebServiceException
	 *             throws {@link WebServiceException}
	 */
	@Path("application/expensetype/")
	@GET
	@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getExpenseType() throws ExpenseTrackerException {
		try {
			ApplicationService service = ServiceFactory.getInstance()
					.getService(Services.APPLICATION_SERVICE, ApplicationService.class);
			return ResponseGenerator.getSuccessResponse(service.getExpenseType(), Operation.SELECT);
		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
			throw new WebServiceException(e, ResponseStatus.SERVER_ERROR);
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
	@Path("application/incometype/")
	@GET
	@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getIncomeType() throws ExpenseTrackerException {
		try {
			ApplicationService service = ServiceFactory.getInstance()
					.getService(Services.APPLICATION_SERVICE, ApplicationService.class);
			return ResponseGenerator.getSuccessResponse(service.getIncomeType(), Operation.SELECT);
		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
			throw new WebServiceException(e, ResponseStatus.SERVER_ERROR);
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
	@Path("application/currencytype/")
	@GET
	@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getCurrencyType() throws ExpenseTrackerException {
		try {
			ApplicationService service = ServiceFactory.getInstance()
					.getService(Services.APPLICATION_SERVICE, ApplicationService.class);
			return ResponseGenerator.getSuccessResponse(service.getCurrency(), Operation.SELECT);
		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
			throw new WebServiceException(e, ResponseStatus.SERVER_ERROR);
		}
	}

}
