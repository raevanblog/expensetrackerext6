package com.slabs.expense.tracker.web.servlet;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.slabs.expensetracker.core.server.Server;

/**
 * Servlet implementation class Initialization
 */
public class ApplicationLoader extends HttpServlet {

	private static final Logger L = LoggerFactory.getLogger(ApplicationLoader.class);

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ApplicationLoader() {
		super();
	}

	@Override
	public void init() throws ServletException {
		L.info("Application Loader is starting...");
		super.init();
		try {
			String host = InetAddress.getLocalHost().getHostName();
			Server.getInstance().initialize(host, 8084, "expensetrackerweb", false);
		} catch (UnknownHostException e) {
			L.error("Exception occurred, {}", e);
			System.exit(1);
		}
	}

}
