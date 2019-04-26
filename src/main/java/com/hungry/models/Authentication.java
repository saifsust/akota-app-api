package com.hungry.models;

public class Authentication {

	private String phone;
	private String password;
	
	public Authentication() {
		super();
	}
	public Authentication(String phone, String password) {
		super();
		this.phone = phone;
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "User [phone=" + phone + ", password=" + password + "]";
	}
	
}
