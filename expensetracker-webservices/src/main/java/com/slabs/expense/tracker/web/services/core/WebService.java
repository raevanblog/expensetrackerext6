package com.slabs.expense.tracker.web.services.core;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.netty.httpserver.NettyHttpContainerProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.slabs.expense.tracker.core.server.Server;
import com.slabs.expense.tracker.web.services.ExpenseTrackerServices;

public class WebService {

	private static final Logger L = LoggerFactory.getLogger("WebService Server");

	public static void main(String[] args) {
		Server.getInstance().initialize("0.0.0.0", 9998, "exptr-web", Boolean.FALSE);
		L.info("Starting ExpenseTracker WebService..");
		URI baseUri = UriBuilder.fromUri("http://localhost/").port(9998).build();
		L.info("Registering Web Services API...");
		NettyHttpContainerProvider.createHttp2Server(baseUri, new ExpenseTrackerServices(), null);
		L.info("Server is started and available @ http://localhost:9998/exptr-web");
	}

}
