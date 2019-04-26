package com.hungry.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

import com.hungry.models.Status;

@Embeddable
public class AccessToken implements Serializable {

	@Column(name = "access_token")
	private String accessToken;
	@Column(name = "expires")
	private int expire;
	@Column(name = "access_date")
	private Timestamp accessDate;

	@Transient
	private int userId;

	public AccessToken() {
		super();
	}
	
	

	public AccessToken(String accessToken, int expire, Timestamp accessDate) {
		super();
		this.accessToken = accessToken;
		this.expire = expire;
		this.accessDate = accessDate;
	}



	public AccessToken(int userId, String accessToken, int expire, Timestamp accessDate) {
		super();
		this.accessToken = accessToken;
		this.expire = expire;
		this.accessDate = accessDate;
		this.userId = userId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public int getExpire() {
		return expire;
	}

	public void setExpire(int expire) {
		this.expire = expire;
	}

	public Timestamp getAccessDate() {
		return accessDate;
	}

	public void setAccessDate(Timestamp accessDate) {
		this.accessDate = accessDate;
	}

	@Override
	public String toString() {
		return "AccessToken [accessToken=" + accessToken + ", expire=" + expire + ", accessDate=" + accessDate
				+ ", userId=" + userId + "]";
	}

}
