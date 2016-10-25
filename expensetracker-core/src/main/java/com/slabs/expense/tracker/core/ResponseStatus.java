package com.slabs.expense.tracker.core;

public enum ResponseStatus {

	OK(200, "Successful"), BAD_REQUEST(400, "Bad Request"), UNAUTHORIZED(401, "Unauthorized Access"), LOGIN_TIMEOUT(440, "Login Time Out"), FORBIDDEN(403, "Forbidden"), SERVER_ERROR(500,
			"Server Error"), SERVICE_UNAVAILABLE(503, "Service Unavailable");

	private int statusCode;

	private String message;

	ResponseStatus(int statusCode, String message) {
		this.statusCode = statusCode;
		this.message = message;
	}

	public int getStatusCode() {
		return this.statusCode;
	}

	public String getMessage() {
		return this.message;
	}

	@Override
	public String toString() {
		return String.valueOf(this.statusCode);
	}

}
