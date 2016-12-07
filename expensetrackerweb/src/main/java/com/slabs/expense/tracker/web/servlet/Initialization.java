package com.slabs.expense.tracker.web.servlet;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.slabs.expense.tracker.core.server.Server;

/**
 * Servlet implementation class Initialization
 */
public class Initialization extends HttpServlet {

	private static final Logger L = LoggerFactory.getLogger("InitializationServlet");

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Initialization() {
		super();
	}

	@Override
	public void init() throws ServletException {
		L.info("Initializing servlet 'Initialization'");
		super.init();
		try {
			String host = InetAddress.getLocalHost().getHostName();
			Server.getInstance().initialize(host, 8080, "expensetrackerweb", false);
		} catch (UnknownHostException e) {
			L.error("Exception occurred, {}", e);
			System.exit(1);
		}
	}

}
