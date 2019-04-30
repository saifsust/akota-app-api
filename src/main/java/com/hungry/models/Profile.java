package com.hungry.models;

import com.hungry.entities.User;

public class Profile {

	private String firstName;
	private String lastName;
	private String emailAddress;
	private String phoneNumber;
	private String imgUrl;
	private String registrationDate;
	public Profile(User user) {
		super();
		this.setFirstName(user.getFirstName());
		this.setLastName(user.getLastName());
		this.setEmailAddress(user.getEmail());
		this.setImgUrl(user.getUserImg());
		this.setPhoneNumber(user.getPhone());
		this.setRegistrationDate(user.getRegistrationDate());
	}

	public String getFirstName() {
		return firstName;
	}

	public String getFullName() {
		return this.firstName + " " + this.lastName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}

	@Override
	public String toString() {
		return "Profile [firstName=" + firstName + ", lastName=" + lastName + ", emailAddress=" + emailAddress
				+ ", phoneNumber=" + phoneNumber + ", imgUrl=" + imgUrl + ", registrationDate=" + registrationDate
				+ "]";
	}

}
