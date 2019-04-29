package com.hungry.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Required;

import com.hungry.models.Status;

@Entity
@Table(name = "hungry_users")
public class User implements Serializable {

	@Id
	@Column(name = "user_id")
	private int userId;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "phone_number")
	private String phone;
	@Column(name="email_address")
	private String email;
	@Column(name = "user_img")
	private String userImg;
	
	@Column(name = "user_img_location")
	private String userImgLocation;
	
	@Column(name = "registration_date")
	private String registrationDate;
	@Column(name = "user_password")
	private String password;
	// @Column(name="orders_id")
	@Transient
	private JSONArray OrdersId;
	@Embedded
	private AccessToken accessToken;

	public User() {
		super();
	}	

	public User(String firstName, String lastName, String phone, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.password = password;
	}
	
	

	public User(String firstName, String lastName, String phone, String userImg, String userImgLocation,
			String registrationDate, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.userImg = userImg;
		this.userImgLocation = userImgLocation;
		this.registrationDate = registrationDate;
		this.password = password;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
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

	@Transient
	public String getFullName() {
		return this.firstName + " " + this.lastName;
	}

	public String getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public AccessToken getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(AccessToken accessToken) {
		this.accessToken = accessToken;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserImg() {
		return userImg;
	}

	public void setUserImg(String userImg) {
		this.userImg = userImg;
	}
	
	

	public String getUserImgLocation() {
		return userImgLocation;
	}

	public void setUserImgLocation(String userImgLocation) {
		this.userImgLocation = userImgLocation;
	}

	public JSONArray getOrdersId() {
		return OrdersId;
	}

	public void setOrdersId(JSONArray ordersId) {
		OrdersId = ordersId;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", phone=" + phone
				+ ", email=" + email + ", userImg=" + userImg + ", userImgLocation=" + userImgLocation
				+ ", registrationDate=" + registrationDate + ", password=" + password + ", OrdersId=" + OrdersId
				+ ", accessToken=" + accessToken + "]";
	}

	

}
