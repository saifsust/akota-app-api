package com.hungry.models;

import javax.persistence.Transient;

import com.hungry.entities.AccessToken;

public class Status {
	@Transient
	private boolean success = true;
	@Transient
	private Class className;
	@Transient
	private String method;
	@Transient
	private String message;

	public Status() {
		super();
	}

	public Status(Class className, String method, String message) {
		super();
		this.message = message;
		this.className = className;
		this.method = method;
		if (!this.message.equals(null) || !this.message.equals("")) {
			this.success = false;
		}else this.message="Well Done";
		
		System.out.println(this.message);
		
	}

	public boolean isSuccess() {
		return success;
	}

	public Class getClassName() {
		return className;
	}

	public String getMethod() {
		return method;
	}

	public String getMessage() {
		return message;
	}

	public void setStatus(Class className, String method, String message) {
		this.message = message;
		this.className = className;
		this.method = method;
		if (!this.message.equals(null) || !this.message.equals("")) {
			this.success = false;
		}else this.message="Well Done";
		
	}

	@Override
	public String toString() {
		return "Status [success=" + success + ", message=" + message + "]";
	}

}
