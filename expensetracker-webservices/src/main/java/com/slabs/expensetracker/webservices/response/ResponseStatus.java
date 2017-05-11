package com.slabs.expensetracker.webservices.response;

/**
 * {@link ResponseStatus}
 * 
 * @author Shyam Natarajan
 *
 */
public enum ResponseStatus {

	OK(200, "Successful"), BAD_REQUEST(400, "Bad Request"), UNAUTHORIZED(401, "Unauthorized Access"), LOGIN_TIMEOUT(440, "Login Time Out"), FORBIDDEN(
			403, "Forbidden"), DATA_NOT_FOUND(404, "No data found"), SERVER_ERROR(500,
					"Server Error"), SERVICE_UNAVAILABLE(503, "Service Unavailable"), ACTIVATION_FAILED(600, "Activation Failed");

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
