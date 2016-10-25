package com.slabs.expense.tracker.web.filter;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import com.slabs.expense.tracker.core.ResponseGenerator;
import com.slabs.expense.tracker.core.ResponseStatus;

@Provider
@PreMatching
public class WebServiceRequestFilter implements ContainerRequestFilter {

	@Context
	private HttpServletRequest request;

	@Override
	public void filter(ContainerRequestContext ctx) throws IOException {
		HttpSession session = request.getSession(false);

		if (session == null) {
			ctx.abortWith(Response.status(ResponseStatus.UNAUTHORIZED.getStatusCode())
					.entity(ResponseGenerator.getExceptionResponse(ResponseStatus.UNAUTHORIZED, ResponseStatus.UNAUTHORIZED.getMessage())).build());
		}
	}

}
