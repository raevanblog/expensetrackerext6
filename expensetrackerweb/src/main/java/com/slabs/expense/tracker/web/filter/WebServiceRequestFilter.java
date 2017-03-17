package com.slabs.expense.tracker.web.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import com.slabs.expense.tracker.common.constants.Constants;
import com.slabs.expense.tracker.util.MarkerEngine;
import com.slabs.expense.tracker.util.exception.UtilityException;
import com.slabs.expense.tracker.webservices.response.ResponseGenerator;
import com.slabs.expense.tracker.webservices.response.ResponseStatus;

@Provider
@PreMatching
public class WebServiceRequestFilter implements ContainerRequestFilter {

	@Context
	private HttpServletRequest request;

	@Override
	public void filter(ContainerRequestContext ctx) throws IOException {
		HttpSession session = request.getSession(false);
		String requestPath = ctx.getUriInfo().getRequestUri().getPath();

		if (session == null) {
			if ("/expensetrackerweb/api/exptr-web/reports".equals(requestPath)) {
				Map<String, String> model = new HashMap<String, String>();
				model.put("response",
						"You've been Signed Out... Please sign in to view the report.");
				try {
					ctx.abortWith(Response.status(ResponseStatus.UNAUTHORIZED.getStatusCode())
							.entity(MarkerEngine.process(Constants.RESPONSE_TEMPLATE, model))
							.build());
				} catch (UtilityException e) {
					throw new IOException(e);
				}
			} else {
				ctx.abortWith(Response.status(ResponseStatus.UNAUTHORIZED.getStatusCode())
						.entity(ResponseGenerator.getExceptionResponse(ResponseStatus.UNAUTHORIZED,
								ResponseStatus.UNAUTHORIZED.getMessage()))
						.build());
			}
		}
	}

}
