package com.slabs.expense.tracker.util;

import java.util.Properties;
import java.util.Set;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.slabs.expense.tracker.util.exception.UtilityException;

public class Mailer {

	private static final Logger L = LoggerFactory.getLogger(Mailer.class);

	private static Properties properties;

	private static String APP_NAME;

	private static String EMAIL_USERNAME;

	private static String EMAIL_PASSWORD;

	private static String EMAIL_SMTP_PORT;

	private static String EMAIL_HOST;

	private static String EMAIL_ADMIN;

	private static String EMAIL_ADMIN_NAME;

	/**
	 * This method will initialize the Email utility with the following
	 * properties
	 * 
	 * {appName}.email.admin.username {appName}.email.admin.password
	 * {appName}.email.smtp.port {appName}.email.host
	 * {appName}.email.admin.email {appName}.email.admin.name
	 * 
	 * @param props
	 * @param appName
	 * 
	 * 
	 * 
	 */
	public static void initialize(Properties props, String appName) {

		L.info("Initializing Mailer for {}", appName);

		APP_NAME = appName;

		EMAIL_USERNAME = APP_NAME + ".email.admin.username";
		EMAIL_PASSWORD = APP_NAME + ".email.admin.password";
		EMAIL_SMTP_PORT = APP_NAME + ".email.smtp.port";
		EMAIL_HOST = APP_NAME + ".email.host";
		EMAIL_ADMIN = APP_NAME + ".email.admin.email";
		EMAIL_ADMIN_NAME = APP_NAME + ".email.admin.name";

		if (properties == null) {
			properties = props;
		}
	}

	public static Email createSimpleEmail(String content, String subject, String toEmail) throws UtilityException {

		String[] email = { toEmail };
		return createSimpleEmail(content, subject, email);
	}

	public static Email createSimpleEmail(String content, String subject, Set<String> toEmailList) throws UtilityException {

		String[] toEmails = (String[]) toEmailList.toArray();
		return createSimpleEmail(content, subject, toEmails);
	}

	private static Email createSimpleEmail(String content, String subject, String... toEmails) throws UtilityException {

		Email email = null;
		try {
			email = configure(new SimpleEmail());
			email.setMsg(content);
			email.setSubject(subject);
			email.addTo(toEmails);
		} catch (EmailException e) {
			throw new UtilityException("Exception occurred while creating email", e);
		}
		return email;
	}

	public static Email createHtmlEmail(String content, String subject, String toEmail) throws UtilityException {

		String[] email = { toEmail };
		return createHtmlEmail(content, subject, email);
	}

	public static Email createHtmlEmail(String content, String subject, Set<String> toEmailList) throws UtilityException {

		String[] toEmails = (String[]) toEmailList.toArray();
		return createHtmlEmail(content, subject, toEmails);
	}

	private static Email createHtmlEmail(String content, String subject, String... toEmails) throws UtilityException {

		Email email = null;
		try {
			email = configure(new HtmlEmail());
			email.setMsg(content);
			email.setSubject(subject);
			email.addTo(toEmails);

		} catch (EmailException e) {
			throw new UtilityException("Exception occurred while creating email", e);
		}
		return email;
	}

	private static Email configure(Email email) throws UtilityException {

		if (properties == null) {
			throw new UtilityException("Initialize Email Utility, before creating email");
		}

		try {
			email.setAuthenticator(new DefaultAuthenticator(properties.getProperty(EMAIL_USERNAME), properties.getProperty(EMAIL_PASSWORD)));
			email.setSmtpPort(Integer.parseInt(properties.getProperty(EMAIL_SMTP_PORT)));
			email.setHostName(properties.getProperty(EMAIL_HOST));
			email.setFrom(properties.getProperty(EMAIL_ADMIN), properties.getProperty(EMAIL_ADMIN_NAME));
			email.setStartTLSRequired(true);
			email.setStartTLSEnabled(true);
		} catch (EmailException e) {
			throw new UtilityException("Exception occurred while configuring email", e);
		}
		return email;
	}
}