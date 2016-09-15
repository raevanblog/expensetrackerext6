package com.slabs.expense.tracker.core.exception;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExceptionResponse", propOrder = { "status_code", "status_message", "exception" })
@XmlRootElement
public class ExceptionResponse {

	@XmlElement(name = "status_code")
	private int status_code;

	@XmlElement(name = "status_message")
	private String status_message;

	@XmlElement(name = "exception")
	private String exception;

	public ExceptionResponse() {
		super();
	}

	public ExceptionResponse(int status_code, String status_message, String exception) {
		super();
		this.status_code = status_code;
		this.status_message = status_message;
		this.exception = exception;
	}

	public int getStatus_code() {
		return status_code;
	}

	public void setStatus_code(int status_code) {
		this.status_code = status_code;
	}

	public String getStatus_message() {
		return status_message;
	}

	public void setStatus_message(String status_message) {
		this.status_message = status_message;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((exception == null) ? 0 : exception.hashCode());
		result = prime * result + status_code;
		result = prime * result + ((status_message == null) ? 0 : status_message.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExceptionResponse other = (ExceptionResponse) obj;
		if (exception == null) {
			if (other.exception != null)
				return false;
		} else if (!exception.equals(other.exception))
			return false;
		if (status_code != other.status_code)
			return false;
		if (status_message == null) {
			if (other.status_message != null)
				return false;
		} else if (!status_message.equals(other.status_message))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ExceptionResponse [status_code=" + status_code + ", status_message=" + status_message + ", exception="
				+ exception + "]";
	}

}
