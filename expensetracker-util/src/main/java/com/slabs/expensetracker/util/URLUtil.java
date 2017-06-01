package com.slabs.expensetracker.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class URLUtil {
	
	private static final Logger L = LoggerFactory.getLogger(URLUtil.class);

	private static String APP_NAME;

	private static String HOST;

	private static int PORT;

	private static boolean IS_SECURED;

	private static final String HTTP = "http";

	private static final String HTTPS = "https";

	private static final String SLASH_FWD = "/";

	private static final String COLON = ":";

	private static String PROTOCOL = HTTP;

	public static void initialize(String host, int port, String appName, boolean isSecured) {
		L.info("Initializing URL Utility...");
		HOST = host;
		PORT = port;
		APP_NAME = appName;
		IS_SECURED = isSecured;
		if (isSecured) {
			PROTOCOL = HTTPS;
		}
	}

	public static String getAppUrl() {
		StringBuilder b = new StringBuilder();
		return b.append(PROTOCOL).append("://").append(HOST).append(COLON).append(PORT).append(SLASH_FWD).append(APP_NAME).append(SLASH_FWD)
				.toString();
	}

	public static String getImageUrl(String name) {
		StringBuilder b = new StringBuilder(getAppUrl());
		return b.append("resources/images/").append(name).toString();
	}

	public static String getAppName() {
		return APP_NAME;
	}

	public static String getHost() {
		return HOST;
	}

	public static int getPort() {
		return PORT;
	}

}
