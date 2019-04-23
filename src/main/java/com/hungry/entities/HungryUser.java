package com.hungry.entities;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.hungry.models.Status;

@Entity
@Table(name="hungry_users")
public class HungryUser extends Status {

	@Id
	@Column(name="user_id")
	private int hungryUserId;
	@Column(name="first_name")
	private String firstName;
	@Column(name="last_name")
	private String lastName;
	@Column(name="phone_number")
	private String phone;
	@Column(name="user_img")
	private String userImg;
	
	@Column(name="registration_date")
	private String registrationDate;
	@Column(name="user_password")
	private String password;
	@Column(name="orders_history")
	private String ownOrders;
	@Embedded
    private AccessToken accessToken;
	
	

	public HungryUser() {
		super();
	}
	
	

	public HungryUser(String firstName, String lastName, String phone, String userImg, String registrationDate,
			String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.userImg = userImg;
		this.registrationDate = registrationDate;
		this.password = password;
	}



	public HungryUser(String firstName, String lastName, String phone, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.password = password;
	}

	public int getHungryUserId() {
		return hungryUserId;
	}

	public void setHungryUserId(int hungryUserId) {
		this.hungryUserId = hungryUserId;
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

	public String getOwnOrders() {
		return ownOrders;
	}

	public void setOwnOrders(String ownOrders) {
		this.ownOrders = ownOrders;
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



	public String getUserImg() {
		return userImg;
	}



	public void setUserImg(String userImg) {
		this.userImg = userImg;
	}



	@Override
	public String toString() {
		return "HungryUser [hungryUserId=" + hungryUserId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", phone=" + phone + ", userImg=" + userImg + ", registrationDate=" + registrationDate + ", password="
				+ password + ", ownOrders=" + ownOrders + ", accessToken=" + accessToken + "]";
	}


}
