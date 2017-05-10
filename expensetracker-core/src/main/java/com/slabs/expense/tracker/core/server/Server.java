package com.slabs.expense.tracker.core.server;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.slabs.expense.tracker.common.constants.Constants;
import com.slabs.expense.tracker.common.exception.ExpenseTrackerException;
import com.slabs.expense.tracker.core.scheduler.ReportingScheduler;
import com.slabs.expense.tracker.util.Mailer;
import com.slabs.expense.tracker.util.MarkerEngine;
import com.slabs.expense.tracker.util.PropertiesUtil;
import com.slabs.expense.tracker.util.URLUtil;
import com.slabs.expense.tracker.util.exception.UtilityException;

/**
 * {@link Server} is a singleton class, which will initialize the core of
 * Expense Tracker application.
 * 
 * @author Shyam Natarajan
 *
 */
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

	public void initialize(String ip, int port, String appName, boolean isSecured) {

		try {
			Properties properties = PropertiesUtil.getFromClassPath(Constants.EXPENSETRACKER_PROPERTIES);

			if (Boolean.valueOf(properties.getProperty("expensetracker.enable.report.dispatcher"))) {
				L.info("Initializing Reporting Scheduler...");
				ReportingScheduler.getInstance().initialize();
				ReportingScheduler.getInstance().startScheduler();
				ReportingScheduler.getInstance().scheduleMonthlyReportDispatcher();
			}

			L.info("Initializing Marker Engine...");
			MarkerEngine.initialize();

			L.info("Initializing Url Utility...");
			URLUtil.initialize(ip, port, appName, isSecured);

			Mailer.initialize(PropertiesUtil.getFromClassPath(Constants.MAIL_PROPERTIES), Constants.APP_NAME);

		} catch (UtilityException e) {
			L.error("Exception occurred while starting the application, {}", e);
			System.exit(1);
		} catch (ExpenseTrackerException e) {
			L.error("Exception occurred while starting the application, {}", e);
			System.exit(1);
		}

	}

}
