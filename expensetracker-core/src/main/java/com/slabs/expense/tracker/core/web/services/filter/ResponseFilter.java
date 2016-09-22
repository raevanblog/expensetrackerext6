package com.slabs.expense.tracker.core.web.services.filter;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

@Provider
public class ResponseFilter implements ContainerResponseFilter {

	public void filter(ContainerRequestContext reqContext, ContainerResponseContext respContext) throws IOException {
		respContext.getHeaders().add("Access-Control-Allow-Origin", "*");
		respContext.getHeaders().add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
		respContext.getHeaders().add("Access-Control-Allow-Credentials", "true");
		respContext.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");

	}

}
