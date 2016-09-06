package com.slabs.expense.tracker.core.server;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.slabs.expense.tracker.core.ServiceFactory;
import com.slabs.expense.tracker.core.db.DatabaseManager;

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

	}

}
