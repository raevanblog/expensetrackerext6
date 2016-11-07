package com.slabs.expense.tracker.core.server;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.netty.httpserver.NettyHttpContainerProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.slabs.expense.tracker.core.ServiceFactory;
import com.slabs.expense.tracker.core.web.services.ExpenseTrackerServices;

public class Server {

	private static final Logger L = LoggerFactory.getLogger(Server.class);

	private volatile static Server server;

	private Server() {

	}

	public static Server getInstance() {
		if (server == null) {
			synchronized (Server.class) {
				if (server == null) {
					server = new Server();
				}
			}
		}
		return server;
	}

	public void initialize() {

		/*
		 * L.info("Initializing Database Manager...");
		 * DatabaseManager.initialize();
		 */

		L.info("Initializing Service Factory...");
		ServiceFactory.getInstance().initialize();

	}

	private void start() {
		L.info("Starting ExpenseTracker Web Server...");
		URI baseUri = UriBuilder.fromUri("http://localhost/").port(9998).build();
		L.info("Registering Web Services API...");
		NettyHttpContainerProvider.createHttp2Server(baseUri, new ExpenseTrackerServices(), null);
		L.info("Server is started and available @ http://localhost:9998/exptr-web");
	}

	public static void main(String args[]) {
		L.info("Initializing Expense Tracker Server...");
		Server.getInstance().initialize();		
		Server.getInstance().start();
	}

}
