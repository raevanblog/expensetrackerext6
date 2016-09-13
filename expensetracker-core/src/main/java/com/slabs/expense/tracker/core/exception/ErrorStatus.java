package com.slabs.expense.tracker.core.exception;

public enum ErrorStatus {

	BAD_REQUEST(400), SERVER_ERROR(500);

	private int errorCode;

	ErrorStatus(int errorCode) {
		this.errorCode = errorCode;
	}

	public int getErrorCode() {
		return errorCode;
	}

	@Override
	public String toString() {
		return String.valueOf(this.errorCode);
	}

}
