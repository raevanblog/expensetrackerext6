package com.slabs.expense.tracker.core;

public enum ResponseStatus {

	OK(200), BAD_REQUEST(400), UNAUTHORIZED(401), LOGIN_TIMEOUT(440), FORBIDDEN(403), SERVER_ERROR(500), SERVICE_UNAVAILABLE(503);

	private int statusCode;

	ResponseStatus(int statusCode) {
		this.statusCode = statusCode;
	}

	public int getStatusCode() {
		return statusCode;
	}

	@Override
	public String toString() {
		return String.valueOf(this.statusCode);
	}

}
