package com.slabs.expensetracker.core.server;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.slabs.expensetracker.common.constants.Constants;
import com.slabs.expensetracker.common.exception.ExpenseTrackerException;
import com.slabs.expensetracker.core.scheduler.ReportingScheduler;
import com.slabs.expensetracker.util.Mailer;
import com.slabs.expensetracker.util.MarkerEngine;
import com.slabs.expensetracker.util.PropertiesUtil;
import com.slabs.expensetracker.util.URLUtil;
import com.slabs.expensetracker.util.exception.UtilityException;

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
				ReportingScheduler.getInstance().initialize().startScheduler().scheduleMonthlyReportDispatcher();
			}

			MarkerEngine.initialize();
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
