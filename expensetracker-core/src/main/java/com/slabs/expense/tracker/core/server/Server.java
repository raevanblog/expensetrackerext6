package com.slabs.expense.tracker.core.server;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.netty.httpserver.NettyHttpContainerProvider;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.slabs.expense.tracker.core.ServiceFactory;
import com.slabs.expense.tracker.core.web.services.ExpenseWebService;

import io.netty.channel.Channel;

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

	public void start() {
		initialize();
		L.info("Starting Server...");
		URI baseUri = UriBuilder.fromUri("http://localhost/").port(9998).build();
		ResourceConfig resourceConfig = new ResourceConfig(ExpenseWebService.class);
		Channel server = NettyHttpContainerProvider.createHttp2Server(baseUri, resourceConfig, null);
	}

}
