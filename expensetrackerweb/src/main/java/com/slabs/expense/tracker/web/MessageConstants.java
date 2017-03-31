package com.slabs.expense.tracker.web;

public class MessageConstants {
	
	public static final String CONTACT_CUST_SUPPORT = "please contact customer support";

	public static final String EXCEPTION = "Exception occurred, "+ CONTACT_CUST_SUPPORT;

	public static final String CHECK_USRNME_PWD = "Please check your username/password";

	public static final String CHECK_PWD = "Please check your password";

	public static final String LOGIN_SUCCESS = "Login Successful";

	public static final String ACCOUNT_LOCKED = "Your account is locked by the administrator, " + CONTACT_CUST_SUPPORT;

	public static final String ACCOUNT_NOT_VERIFIED = "Your account is not activated, please check your registered email for activation link";

	public static final String INVALID_SESSION = "Session Expired";	

	public static final String AVAILABLE = "Available";

	public static final String USERNAME_NOT_AVAILABLE = "Username is already registered";
	
	public static final String EMAIL_ALREADY_REGISTERED ="Email address is already registered";

	public static final String LOGD_OFF = "Successfully Logged Off";

	public static final String SESSION_ACTIVE = "Session Active";

	public static final String USER_REGISTERED = "User registered successfully";

	public static final String USER_NOT_REGISTERED = "User registration failed, " + CONTACT_CUST_SUPPORT;

	public static final String USER_ACTIVATED_ALREADY = "User activated already, please contact customer support if there is any issue";

	public static final String ACTIVATION_SUCCESSFUL = "Your account is activated sucessfully";

	public static final String ACTIVATION_FAILED = "Activation Failed, " + CONTACT_CUST_SUPPORT;

	public static final String ACTIVATION_FAILED_INVALID_KEY = "Activation failed, invalid activation key";	

	public static final String ACTIVATION_MAILED = "Activation link has been mailed to your registered email";
	
	public static final String MAILED = "Your query has been mailed to Administrator";

	public static final String USER_NOT_FOUND = "We are unable to verify your username, " + CONTACT_CUST_SUPPORT;
}
