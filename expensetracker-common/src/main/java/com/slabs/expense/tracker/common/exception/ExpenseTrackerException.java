package com.slabs.expense.tracker.common.exception;

/**
 * {@link ExpenseTrackerException} is a custom exception class.
 * 
 * @author Shyam Natarajan
 *
 */
public class ExpenseTrackerException extends Exception {

	private static final long serialVersionUID = -9175347861593491223L;

	public ExpenseTrackerException() {
		super();
	}

	public ExpenseTrackerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ExpenseTrackerException(String message, Throwable cause) {
		super(message, cause);
	}

	public ExpenseTrackerException(String message) {
		super(message);
	}

	public ExpenseTrackerException(Throwable cause) {
		super(cause);
	}
}
