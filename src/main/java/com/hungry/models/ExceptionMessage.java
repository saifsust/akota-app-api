package com.hungry.models;

import java.io.Serializable;
import java.util.Arrays;

public class ExceptionMessage implements Serializable {
	private static final long serialVersionUID = 1L;
	private String message;
	private String[] detail;

	public ExceptionMessage() {
		super();
	}

	public ExceptionMessage(String message, String[] detail) {
		super();
		this.message = message;
		this.detail = detail;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String[] getDetail() {
		return detail;
	}

	public void setDetail(String[] detail) {
		this.detail = detail;
	}

	@Override
	public String toString() {
		return "ExceptionMessage [message=" + message + ", detail=" + Arrays.toString(detail) + "]";
	}

}
